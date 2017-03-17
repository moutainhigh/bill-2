/********************************************
 * 文件名称: OnlineBaseService.java
 * 系统名称: 票据管理平台V2.0
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: 2.0.0.1
 * 开发人员: yanjl
 * 开发时间: 20160716
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期    修改人员    修改说明
 *********************************************/

package com.herongtech.online.trans;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.herongtech.baseservice.BaseService;
import com.herongtech.console.core.constant.IConstants;
import com.herongtech.console.core.constant.IErrorNo;
import com.herongtech.console.core.util.XmlBeanUtil;
import com.herongtech.console.domain.bean.BusiDate;
import com.herongtech.context.Context;
import com.herongtech.context.util.ContextUtil;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.exception.impl.BizAppRuntimeException;
import com.herongtech.log.CommonLog;
import com.herongtech.xmlchannel.pkg.ProResult;
import com.herongtech.xmlchannel.pkg.TransInfo;

abstract public class OnlineBaseService extends BaseService {

	public static final String  HERONGTECH_VERSION="@system  票据管理平台 @version 2.0.0.1  @lastModiDate @describe ";

	protected void beforeAction(Context context) throws Exception {

	}

	protected void finallyAction(Context context) throws Exception {
		
		String response = (String)ContextUtil.getResponseData(context);
		
		if (response != null) {
			CommonLog.getCommonLogCache().infoMessage(
					"应答信息：" + response);
		}
		
		CommonLog.getCommonLogCache().endTransaction();
        //ServiceFactory.getMonitorService().monitor(context);  //添加监控信息
	}

	protected void initAction(Context context) throws Exception {
		String request = (String)ContextUtil.getRequestData(context);
		// 取交易信息
		// 校验交易是允许
		/*Trans trans = TransCache.getInstance().getTrans(this.getServiceId()); // 从内存中获取交易配置信息
		//联机交易若交易未配置则抛出异常
		if (trans == null) {
			throw new BizAppException(IErrorNo.ERR_NOTRANS, "交易未配置");
		}*/

		// 设置交易日志号
		/*ISequenceService sequence = ServiceFactory.getSequenceService();
		String logSerial = sequence.getLogSerialNo();
		CommonLog.setLogId(logSerial); // 注入交易日志号
        this.setContextAttribute(context, IConstant.LOGID_KEY, logSerial);
        
		CommonLog.putThreadVariable(IFieldName.FunctionId, request.getString(IFieldName.FunctionId));   
		CommonLog.putThreadVariable(IConstant.THREAD_ID,String.valueOf(Thread.currentThread().getId()));  */
		
		// 设置日志级别
		//CommonLog.setLogLevel(trans.getLogLevel());
		
		CommonLog.getCommonLogCache().infoMessage("请求信息：" + request);

		// 判断交易是否允许
		/*if (!IDict.K_YXBZ.YXBZ_YES.equals(trans.getEnableFlag())) {
			throw new BizBussinessException(IErrMsg.ERR_NOTRANS, "交易禁止");
		}

		TransService transService = new TransService();
		if (!transService.isChannelEnable(trans, this
				.getRequestDataset(context).getString(IFieldName.Channel))) {
			throw new BizBussinessException(IErrMsg.ERR_CHNNOSUPPORT, "渠道不允许["
					+ DictCache.getInstance().getPrompt("K_JYQD",
							getRequestDataset(context).getString(IFieldName.Channel))
					+ "-" + getRequestDataset(context).getString(IFieldName.Channel)
					+ "]");
		}
		this.setContextAttribute(context, IConstant.TRANS_KEY, trans);
		*/

	}

	@Override
	protected void packErrorAnswer(Context context, Exception e){
		BusiDate busiInfo = (BusiDate) ContextUtil.getContextAttribute(context, IConstants.SYSBUSININFO);
		TransInfo trans=(TransInfo)ContextUtil.getContextAttribute(context, IConstants.TRANS_INFO);
		if (busiInfo == null){
			//取发起方法人行编号
		}  
		 ProResult result=new ProResult();
         result.setType("E");
        
         result.setExSerial(trans.getExSerial());
         result.setFunctionId(trans.getFunctionId());
         Map<String, Class> clazzMap = new HashMap<String, Class>();
         clazzMap.put("Document", ProResult.class);
		if (e instanceof BizAppRuntimeException) {
		    result.setCode(((BizAppRuntimeException) e).getErrorNo());
	        result.setMessage(((BizAppRuntimeException) e).getErrorMsg());
		} else if (e instanceof BizAppException) {
		    result.setCode(((BizAppException) e).getErrorNo());
            result.setMessage(((BizAppException) e).getErrorMsg());
		} else if (e instanceof SQLException) {
		    result.setCode(IErrorNo.ERR_DBERR);
            result.setMessage("数据库操作失败");
		}else {
		    result.setCode(IErrorNo.ERR_DEFAULT);
            result.setMessage("未知错误");
		}
		String errResp=XmlBeanUtil.bean2xml(clazzMap, result);
		// 服务的结果key值是固定的，结果需要放结果集
		ContextUtil.setResponseData(context, errResp); // 设置应答包
		
	    CommonLog.getCommonLogCache().errorMessage("抛出异常", e);		
	  	
	}
	
	/**
	 * 交易请求预处理
	 * @param context transVar
	 * @throws Exception
	 */
	protected void transRequest(Context context) throws BizAppException {
	    
	    
	}
	
	
}
