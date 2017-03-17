package com.herongtech.console.service.common.dayend;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.herongtech.beancontainer.BeanContainerFactory;
import com.herongtech.commons.tools.StringUtil;
import com.herongtech.console.cache.SysParamCache;
import com.herongtech.console.core.constant.IConstants;
import com.herongtech.console.core.util.DateTimeUtil;
import com.herongtech.console.domain.common.bean.TaskPool;
import com.herongtech.console.domain.common.dao.TaskPoolDao;
import com.herongtech.console.service.ServiceFactory;
import com.herongtech.console.service.common.dayend.common.CreateDigraph;
import com.herongtech.console.service.common.dayend.common.TaskNode;
import com.herongtech.console.service.common.dayend.interfaces.IDayEndSchedulerService;
import com.herongtech.console.service.interfaces.IParamService;
import com.herongtech.context.Context;
import com.herongtech.context.impl.ContextImpl;
import com.herongtech.context.util.ContextUtil;
import com.herongtech.event.Event;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.log.CommonLog;
import com.herongtech.service.IServiceClient;


public class DayEndSchedulerService implements IDayEndSchedulerService{

    private TaskPoolDao dao=new TaskPoolDao();
    
    /**
     * 开启批量作业
     * @throws Exception
     */
    public void startBatch()throws Exception{
        //修改系统状态为 日终状态
        CommonLog.getCommonLogCache().infoMessage("日终任务开启");
        IParamService paramService=ServiceFactory.getParamService();
        //TODO:日终任务开启后，系统应停止做业务 需在拦截器里判断日终状态 待实现
        
        // 判断是否存在非激活状态的作业
        if (this.getDao().countNotDealStatusNum(IConstants.DAY_END.DEAL_STATUS_ACTIVE) > 0) {
            throw new Exception("存在不是【0-未激活 1-已激活】状态的任务");
        }
        // 将所有未激活的作业进行激活操作
        this.getDao().updateAllTaskpoolsStatus(IConstants.DAY_END.DEAL_STATUS_ACTIVE);
        // 修改系统状态为"日终"
        
        paramService.updateparam(IConstants.SysParamConstant.BATCH_START_FLAG, IConstants.SWITCH_FLAG.OPEN);
        
        // 获取营业日期
        String busiDate =DateTimeUtil.getWorkday();
        // YYYYMMDD-1
        String batchBusiDt = busiDate + "-" + IConstants.DAY_END.BATCH_PROC;
        // 修改系统参数表BATCH_BUSIDATE值为当前营业日+初始化(例如 20160919-1),同时刷新缓存
        paramService.updateparam(IConstants.SysParamConstant.BATCH_BUSIDATE, batchBusiDt);

        
    }
    
    
    /**
     * 停止作业
     * @throws Exception
     */
    public void stopBatch()throws Exception{
        CommonLog.getCommonLogCache().infoMessage("日终任务结束");
        ServiceFactory.getParamService().updateparam(IConstants.SysParamConstant.BATCH_START_FLAG, IConstants.SWITCH_FLAG.CLOSE);
        
    }


    public boolean isStartBatch() throws Exception {
        String  batchStartFlag= SysParamCache.getInstance().getSysParam(IConstants.SysParamConstant.BATCH_START_FLAG);
        return IConstants.SWITCH_FLAG.OPEN.equals(batchStartFlag);
    }


    @Override
    public void schedulerTask() throws Exception {
        CommonLog.getCommonLogCache().infoMessage("日终任务调度开始");
        
        // 清算前处理模块作业调度
        this.moduleSchedulerTask(IConstants.DAY_END.TASK_TYPE_PRE);
        // 判断前处理模块是否完成
        if (!isModuleFinish(IConstants.DAY_END.TASK_TYPE_PRE)) {
            return;
        }
        // 清算处理模块作业调度
        this.moduleSchedulerTask(IConstants.DAY_END.TASK_TYPE_CLEAR);
        // 判断清算模块是否完成
        if (!isModuleFinish(IConstants.DAY_END.TASK_TYPE_CLEAR)) {
            return;
        }
        // 初始化模块作业调度
        this.moduleSchedulerTask(IConstants.DAY_END.TASK_TYPE_INIT);
        
    }
    
    /**
     * 按模块进行作业调度
     * @param taskType  作业类型
     * @throws ServiceException
     */
    private  void moduleSchedulerTask(String module) throws Exception {
        // 查询所制定作业类型的所有作业
        List<TaskPool> lists = this.getDao().getActiveTaskpools(module);
        CreateDigraph createDigraph = new CreateDigraph(lists);/* 图节点初始化 */
        createDigraph.create(0);/* 建立图 */
        // 判断是否有符合条件的作业数
        if (!createDigraph.getNodes().isEmpty()) {
            // 获取图的第一个节点
            TaskNode firshNode = createDigraph.getNodes().get(0);
            List<TaskNode> startNode = new ArrayList<TaskNode>();
            createDigraph.searchStartNodes(startNode, firshNode);/* 获取头节点集合 */
            // 遍历图中的每一个节点
            for (TaskNode taskNode : startNode) {
                this.runTaskNode(taskNode);
            }
        }
    }

    /**
     * 运行作业任务节点 
     * @param taskNode  作业节点
     */
    private  void runTaskNode(TaskNode taskNode) throws Exception {
        List<TaskNode> parents = taskNode.getParentNodes();
        // 判断当前节点所有父节点是否已作业完成
        if (this.isParentFinish(parents)) {
            TaskPool taskpool=this.getDao().getTaskPoolByTaskNo(taskNode.getNodeNo());
            if (IConstants.DAY_END.DEAL_STATUS_ACTIVE.equals(taskpool.getDealStatus())) {
                // 作业失效，则将其置为成功，并调度下一个作业。
                if (!taskpool.getEffectiveFlag().equals(IConstants.DAY_END.TASK_EFFECTIVE)) {
                    taskpool.setErrMsg("作业无效，已跳过。");
                    taskpool.setDealStatus(IConstants.DAY_END.DEAL_STATUS_FINISH);
                    taskpool.setBeginTime(DateTimeUtil.get_hhmmss_time());
                    taskpool.setEndTime(DateTimeUtil.get_hhmmss_time());
                    this.getDao().modifyTaskPool(taskpool);
                    return ;
                }
                
                if(IConstants.DAY_END.DELAY_FLAG_YES.equals(taskpool.getDelayFlag())){
                    //如果作业有延时处理，则到了延时时间再处理
                    if(DateTimeUtil.getTime().compareTo(taskpool.getDelayTime())<0){
                        return;
                    }
                    
                }
                
                
                Context context = new ContextImpl();
                ContextUtil.setRequestData(context, taskpool);
                Event event = Event.createEvent(taskpool.getTaskNo(), context);
                //获取本地服务处理通道
                IServiceClient serviceClient = BeanContainerFactory.getBeanContainer(this.getClass().getClassLoader()).getBean("localServiceClient");
                //获取应答事件
                serviceClient.send(event);

            } else if (IConstants.DAY_END.DEAL_STATUS_FINISH.equals(taskpool.getDealStatus())) {
                List<TaskNode> childrens = taskNode.getChildNodes();
                for (TaskNode childTaskNode : childrens) {
                    this.runTaskNode(childTaskNode);
                }
            }
        }
    }
    /**
     * 运行某个作业任务节点 
     */
    public void runOneTask(String taskNo,String taskType) throws Exception {
        TaskPool pool=this.getDao().getTaskPoolByTaskNo(taskNo);
        TaskPool parentPool  = null;
        if(StringUtils.isNotBlank(pool.getParentTaskNo())){
        	parentPool = this.getDao().getTaskPoolByTaskNo(pool.getParentTaskNo());
        	// 判断当前节点的父节点是否已作业完成
        	if (!IConstants.DAY_END.DEAL_STATUS_FINISH.equals(parentPool.getDealStatus())) {
        		throw new BizAppException("当前作业存在没有执行完成的父级作业！");
        	}
        }else{
        	//如果是某个模块的首个作业，则判断上个模块是否完成 
        	if(IConstants.DAY_END.TASK_TYPE_PRE.equals(taskType)){
        		//不做处理
        	}else if(IConstants.DAY_END.TASK_TYPE_CLEAR.equals(taskType)){
        		if(!isModuleFinish(IConstants.DAY_END.TASK_TYPE_PRE)){
        			throw new BizAppException("清算前处理模块存在没有执行完成的作业！");
        		}
        	}else if(IConstants.DAY_END.TASK_TYPE_INIT.equals(taskType)){
        		if(!isModuleFinish(IConstants.DAY_END.TASK_TYPE_CLEAR)){
        			throw new BizAppException("清算模块存在没有执行完成的作业！");
        		}
        	}
        }
        if(pool.getDealStatus()!=null && 
				!IConstants.DAY_END.DEAL_STATUS_ING.equals(pool.getDealStatus()) && 
				!IConstants.DAY_END.DEAL_STATUS_NOTACTIVE.equals(pool.getDealStatus())){
			//可以执行
			if(IConstants.DAY_END.DEAL_STATUS_FINISH.equals(pool.getDealStatus()) && !"1".equals(pool.getRepeatFlag())){
				throw new BizAppException("该任务不允许重复执行！");
			}
			// 作业失效，则将其置为成功，并调度下一个作业。
			if (!pool.getEffectiveFlag().equals(IConstants.DAY_END.TASK_EFFECTIVE)) {
				pool.setErrMsg("作业无效，已跳过。");
				pool.setDealStatus(IConstants.DAY_END.DEAL_STATUS_FINISH);
				pool.setBeginTime(DateTimeUtil.get_hhmmss_time());
				pool.setEndTime(DateTimeUtil.get_hhmmss_time());
				this.getDao().modifyTaskPool(pool);
				return ;
			}
			if(IConstants.DAY_END.DELAY_FLAG_YES.equals(pool.getDelayFlag())){
				//如果作业有延时处理，则到了延时时间再处理
				if(DateTimeUtil.getTime().compareTo(pool.getDelayTime())<0){
					return;
				}
			}
			
			Context context = new ContextImpl();
			ContextUtil.setRequestData(context, pool);
			Event event = Event.createEvent(pool.getTaskNo(), context);
			//获取本地服务处理通道
			IServiceClient serviceClient = BeanContainerFactory.getBeanContainer(this.getClass().getClassLoader()).getBean("localServiceClient");
			//获取应答事件
			serviceClient.send(event);
		}else{
			throw new BizAppException("该任务所处的状态不允许手动执行！");
		}
    }

    /**
     * 判断所有父节点作业处理状态是否为[处理完成]
     * @param parents 父节点作业集合
     * @return true--是   false--否
     */
    private boolean isParentFinish(List<TaskNode> parents) throws Exception {
        for (TaskNode taskNode : parents) {
            TaskPool taskPool =this.getDao().getTaskPoolByTaskNo(taskNode.getNodeNo());
            // 判断处理状态为【作业完成】
            if (!IConstants.DAY_END.DEAL_STATUS_FINISH.equals(taskPool.getDealStatus())) {
                return false;
            }
        }
        return true;
    }

    private boolean isModuleFinish(String module)throws Exception {
        // 获取作业类型下所有有效作业数
        int moduleCount = this.getDao().countTaskTypeNum(module);
        // 获取作业类型下作业完成的作业数
        int finishCount = this.getDao().countDealStatusNumForTaskType(module, IConstants.DAY_END.DEAL_STATUS_FINISH);
        // 通过是否相等来判断模块是否处理完成
        if (moduleCount == finishCount) {
            return true;
        } else {
            return false;
        }

    }
    
    @Override
    public boolean isAuto() throws Exception {
        String  batchAutoFlag= SysParamCache.getInstance().getSysParam(IConstants.SysParamConstant.BATCH_AUTO_FLAG);
        return IConstants.SWITCH_FLAG.OPEN.equals(batchAutoFlag);
    }


    @Override
    public String getStartTime() throws Exception {
        String time =SysParamCache.getInstance().getSysParam(IConstants.SysParamConstant.BATCH_AUTO_TM);
        if (StringUtil.isBlank(time) || time.length() < 6
                || time.substring(0, 2).compareTo("23") > 0
                || time.substring(2, 4).compareTo("59") > 0
                || time.substring(4).compareTo("60") > 0) {
            return "0";
        }
        return time;
    }


    
    public TaskPoolDao getDao() {
        return dao;
    }
    
    
}
