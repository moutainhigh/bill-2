/********************************************
 * 文件名称: T100301Service.java
 * 系统名称: 票据管理平台V2.0
 * 模块名称: 可执行出票登记的票据查询
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: 2.0.0.1
 * 开发人员: yanjl
 * 开发时间: 2016-07-16
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期    修改人员    修改说明
 *********************************************/
package com.herongtech.rc.trans.trans054;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.herongtech.console.cache.DictCache;
import com.herongtech.console.core.constant.DraftConstants;
import com.herongtech.console.core.constant.IDict;
import com.herongtech.context.Context;
import com.herongtech.context.util.ContextUtil;
import com.herongtech.db.impl.session.DBFactory;
import com.herongtech.db.interfaces.IDB;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.rc.domain.bean.RgctEcdsStatus;
import com.herongtech.rc.draft.common.vo.RequestInfo;
import com.herongtech.rc.draft.common.vo.SysMgrInfoVo;
import com.herongtech.rc.service.RcServiceFactory;
import com.herongtech.rc.service.interfaces.IRgctEcdsStatusService;
import com.herongtech.rc.trans.EcdsBaseService;
/**
 * 系统状态切换
 */
public class T054Service extends EcdsBaseService {

	/**
	 * 入口方法
	 * @param context
	 */
	protected void action(Context context) throws Exception {
		Var054 transVar = new Var054();
		
		//交易预处理
		transRequest(context);

		// 常规校验
		CheckData(context, transVar);

		// 查询处理
		ToLocal(context, transVar);
	}

	/**
	 * 交易预处理
	 * @param context
	 * @throws Exception
	 */
	protected void transRequest(Context context) throws BizAppException {
		// 父类交易请求预处理
		super.transRequest(context);
		
		//银行接口化处理
		//BankInterfaceFactory.getBankInterface().bankInterface(context);
	}

	/**
	 * 常规校验
	 * @param context
	 * @param Var054
	 * @throws BizAppException
	 */
	protected void CheckData(Context context, Var054 transVar) throws BizAppException{
	    RequestInfo req = (RequestInfo)ContextUtil.getRequestData(context);
        SysMgrInfoVo vo=(SysMgrInfoVo)req;
		
		String originalDate = vo.getOrgnlSysDt();
		String originalSequence = vo.getOrgnlSysSts();
		//从数据字典中获取
		String originalSequenceCn = DictCache.getInstance().getItemValue(IDict.K_ECDS_SYSSTATUS.NAME, originalSequence);
		String currentDate =vo.getSysDt();
		String sequence = vo.getSysSts();
		//从数据字典中获取
		String sequenceCn = DictCache.getInstance().getItemValue(IDict.K_ECDS_SYSSTATUS.NAME, sequence);
		String nextDate = vo.getNxtSysDt();
		String runTime = vo.getBizRefTm();
		
		List<RgctEcdsStatus> ecdsStatusList = new ArrayList<RgctEcdsStatus>();

		ecdsStatusList.add(new RgctEcdsStatus(DraftConstants.ECDS_ORIGINAL_DATE, originalDate, DraftConstants.ECDS_ORIGINAL_DATE_CN, originalDate));

		ecdsStatusList.add(new RgctEcdsStatus(DraftConstants.ECDS_ORIGINAL_SEQUENCE, originalSequence, DraftConstants.ECDS_ORIGINAL_SEQUENCE_CN, originalSequenceCn));

		ecdsStatusList.add(new RgctEcdsStatus(DraftConstants.ECDS_CURRENT_DATE, currentDate, DraftConstants.ECDS_CURRENT_DATE_CN, currentDate));

		ecdsStatusList.add(new RgctEcdsStatus(DraftConstants.ECDS_SEQUENCE, sequence, DraftConstants.ECDS_SEQUENCE_CN, sequenceCn));

		ecdsStatusList.add(new RgctEcdsStatus(DraftConstants.ECDS_NEXT_DATE, nextDate, DraftConstants.ECDS_NEXT_DATE_CN, nextDate));

		ecdsStatusList.add(new RgctEcdsStatus(DraftConstants.ECDS_RUN_TIME, runTime, DraftConstants.ECDS_RUN_TIME_CN, runTime));
		
		transVar.setSequence(sequence);
		transVar.setEcdsStatusList(ecdsStatusList);
	}
	
	/**
	 * 查询处理
	 * @param context
	 * @param Var054
	 * @throws BizAppException
	 */
    protected void ToLocal(Context context, Var054 transVar) throws BizAppException{
    	//IData request = ContextUtil.getRequestData(context);
    	IDB session = DBFactory.getDB();
    	IRgctEcdsStatusService ecdsStatus = RcServiceFactory.getRgctEcdsStatusService();
    	
    	try{
    		session.beginTransaction();
    		ecdsStatus.addOrUpdateRgctEcdsStatusList(transVar.getEcdsStatusList());
    		
    		session.endTransaction();
    		
    		
    		if (IDict.K_ECDS_SYSSTATUS.ECDS_SYSSTATUS_BEFORE.equals(transVar.getSequence())){
    			ecdsStatus.dealSequenceBefore();
    			/*try {
    				EcdsDayCheckFactory.getCheckInstance().sendCheck071Draft();
    			} catch (Exception e) {
    				Logger.getLogger(SystemStatusNotification054MsgProcessor.class).error("日初营业前准备,调用日核对失败。",e);
    			}*/
    		} else if (IDict.K_ECDS_SYSSTATUS.ECDS_SYSSTATUS_RUN.equals(transVar.getSequence())){
    			ecdsStatus.dealSequenceRun();
    		}
    		if (IDict.K_ECDS_SYSSTATUS.ECDS_SYSSTATUS_AFTER.equals(transVar.getSequence())){
    			ecdsStatus.dealSequenceAfter();
    			
    		}if (IDict.K_ECDS_SYSSTATUS.ECDS_SYSSTATUS_LAST.equals(transVar.getSequence())){
    			ecdsStatus.dealSequenceLast();
    		}
    		
    	} catch (BizAppException e) {
    		try {
				session.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
    		e.printStackTrace();
    	} catch (SQLException e) {
    		try {
				session.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
    		e.printStackTrace();
    	}
    }
	
	public String getTransName() {		
		return "ecds系统同步切换";
	}
	
	public String getTransVersion() {		
		return "2.0.0.1";
	}

	public String getServiceId() {
		return "Proc054";
	}
}
