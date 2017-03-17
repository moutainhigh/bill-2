/********************************************
 * 文件名称: EcdsBaseService.java
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

package com.herongtech.rc.trans;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.sql.SQLException;
import java.util.HashSet;

import org.apache.commons.logging.LogFactory;

import com.herongtech.appconfig.SysConfigUtil;
import com.herongtech.baseservice.BaseService;
import com.herongtech.commons.tools.DateUtil;
import com.herongtech.commons.tools.StringUtils;
import com.herongtech.console.core.constant.DraftConstants;
import com.herongtech.console.core.constant.IConstants;
import com.herongtech.console.core.constant.IDict;
import com.herongtech.console.core.constant.IErrorNo;
import com.herongtech.console.core.constant.IFieldName;
import com.herongtech.console.domain.bean.BusiDate;
import com.herongtech.console.service.ServiceFactory;
import com.herongtech.context.Context;
import com.herongtech.context.util.ContextUtil;
import com.herongtech.data.interfaces.data.IData;
import com.herongtech.db.impl.session.DBFactory;
import com.herongtech.db.interfaces.IDB;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.log.CommonLog;
import com.herongtech.parser.filter.PathFilter;
import com.herongtech.rc.domain.bean.RgctDraftLog;
import com.herongtech.rc.domain.bean.RgctDraftTemplate;
import com.herongtech.rc.domain.bean.RgctExceptionDraft;
import com.herongtech.rc.domain.bean.RgctMethod;
import com.herongtech.rc.draft.DraftService;
import com.herongtech.rc.draft.common.vo.DraftInfoVo;
import com.herongtech.rc.draft.common.vo.RequestInfo;
import com.herongtech.rc.service.RcServiceFactory;
import com.herongtech.rc.service.interfaces.IRgctDraftLogService;
import com.herongtech.rc.service.interfaces.IRgctDraftTemplateService;
import com.herongtech.rc.service.interfaces.IRgctExceptionDraftService;
import com.herongtech.xmlparser.node.XmlNode;

abstract public class EcdsBaseService extends BaseService {

	public static final String  HERONGTECH_VERSION="@system  票据管理平台 @version 2.0.0.1  @lastModiDate @describe ";
	
	private static HashSet<String> contractDraftNoSet = new HashSet<String>();
	static{
		contractDraftNoSet.add("Proc202");
		contractDraftNoSet.add("Proc203");
		contractDraftNoSet.add("Proc204");
		contractDraftNoSet.add("Proc205");
		contractDraftNoSet.add("Proc206");
		contractDraftNoSet.add("Proc207");
	}

	
	/**
	 * 校验
	 * 需要对来报进行校验的在子类实现（账号校验/行号/行名校验）
	 */
	protected void beforeAction(Context context) throws Exception {

	}

	protected void finallyAction(Context context) throws Exception {
		
		CommonLog.getCommonLogCache().endTransaction();
        //ServiceFactory.getMonitorService().monitor(context);  //添加监控信息
	}
	
	/**
	 * 完善日志信息
	 * 
	 */
	protected void initAction(Context context) throws Exception {
	    RgctDraftLog draftLog =(RgctDraftLog)ContextUtil.getContextAttribute(context, IConstants.DRAFT_LOG);
		// 保存日志到文件
//		String logFileName = saveDraftLogToFile(context);
		//保存日志到数据库
		IDB dbsession = DBFactory.getDB();
		
		try{
			dbsession.beginTransaction();
			
			saveDraftLog(context, draftLog);
			
			dbsession.endTransaction();
		} catch (Exception e){
			dbsession.rollback();
			throw e;
		}
		
	}

	protected void packErrorAnswer(DraftInfoVo vo, Exception e) throws Exception{
		if(e instanceof BizAppException){
			RgctMethod method = new RgctMethod();
			method.setId(Long.parseLong("1035"));
			if("EC01".equals(vo.getExceptionCode())){
			    vo.setFromBankNo(SysConfigUtil.getSysConfig().getValue("rootBankNo"));
			}
			DraftService ds = RcServiceFactory.getDraftService();
			ds.buildAndSend(vo, method);
		}

		/*RgctDraftLog draftLog = (RgctDraftLog)ContextUtil.getContextAttribute(context, IConstants.DRAFT_LOG);
		if (draftLog == null){
			draftLog.setRespSid(msgId);
		}
		
		IRgctExceptionDraftService exptService = RcServiceFactory.getRgctExceptionDraftService();
		
		RgctExceptionDraft exptDraft = new RgctExceptionDraft();
		exptDraft.setMsgSid(draftLog.getRespSid());
		exptDraft.setDraftNo(draftLog.getDraftNoResp());
		exptDraft.setDraftDatetime(draftLog.getRespDt());
		exptDraft.setBillNo(draftLog.getBillNo());
		exptDraft.setInOut("1");
		exptDraft.setStatus("0");
		
		if (draftLog.getReqMsgId() != null){
			exptDraft.setOrgnlMsgSid(draftLog.getReqMsgId());
			exptDraft.setOrgnlDraftNo(draftLog.getFromDraftNo());
		}else if (draftLog.getReqSid() != null){
			exptDraft.setOrgnlMsgSid(draftLog.getReqSid());
			exptDraft.setOrgnlDraftNo(draftLog.getDraftNoReq());
			exptDraft.setOrgnlDraftDatetime(draftLog.getReqDt());		
		}	
		//保存日志到数据库
		IDB dbsession = DBFactory.getDB();
				
		try{
			dbsession.beginTransaction();
			exptService.addRgctExceptionDraft(exptDraft);
			dbsession.endTransaction();
		} catch (Exception ex){
			try {
				dbsession.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			ex.printStackTrace();
		}
		*/
	    CommonLog.getCommonLogCache().errorMessage("抛出异常", e);		
	    throw e ;
	}
	
	/**
	 * 交易请求预处理
	 * @param context transVar
	 * @throws Exception
	 */
	protected void transRequest(Context context) throws BizAppException {
	    RequestInfo request = (RequestInfo)ContextUtil.getRequestData(context); // 请求数据集合
		
		if (request == null){
			throw new BizAppException(IErrorNo.ERR_UNPACKQT, "解请求包失败!");
		}
		
		//取发起方法人行编号
		/*String bankNo = request.getString(IFieldName.bankNo);
		if (StringUtils.isEmpty(bankNo)){
			bankNo = IConstants.DEFAULT_BANK_NO;
		}*/
		
		//取系统参数
		//TODO:多法人情况下，应根据接收报文的MQ的队列名称来判断所属法人
		BusiDate busiInfo = ServiceFactory.getSysBusiInfoService().getBusiDate(IConstants.DEFAULT_BANK_NO);
		
		ContextUtil.setContextAttribute(context, IConstants.SYSBUSININFO, busiInfo);
	}
	
	//保存日志到文件，返回报文标识号
	@Deprecated
	private String saveDraftLogToFile(Context context) throws BizAppException {
		IData request = (IData)ContextUtil.getRequestData(context);
		
		// 接收报文标识号
		String msgId = request.getString("MsgId.Id");
		if (StringUtils.isEmpty(msgId)) {
			throw new BizAppException(IErrorNo.ERR_PACKAGE, "接收的报文没有报文标识号");
		}

		// 创建文件夹
		String msgDate = msgId.substring(12, 20);
		String basePath = SysConfigUtil.getSysConfig().getValue("DIR.HOME");
		
		String fileDirPath =  basePath + SysConfigUtil.getSysConfig().getValue("DIR.LOCAL.MSG") + "/" + msgDate + "/in/";
		File fileDir = new File(fileDirPath);
		boolean fileOperFlag = true;
		if (!(fileDir.isDirectory())) {
			fileOperFlag = fileDir.mkdirs();
		}
		if (!fileOperFlag){
			throw new BizAppException(IErrorNo.ERR_DEFAULT, "创建文件夹:" + fileDir.getAbsolutePath() + "失败");
		}
		String msgHead = request.getString(IFieldName.ECDS_HEAD);
		String msgBody = request.getString(IFieldName.ECDS_BODY);
		
		// 定义文件名
		String fileName = fileDirPath + msgId + ".xml";
		BufferedWriter fileWriter = null;
		try {
			fileWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName), "utf-8"));
			fileWriter.write(msgHead + msgBody);
			fileWriter.close();
		} catch (Exception e) {
			LogFactory.getLog(getClass()).error("报文写入文件失败:");
			LogFactory.getLog(getClass()).error(msgHead + msgBody);
		} finally {
			try {
				if (fileWriter != null){
					fileWriter.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return fileName;
	}
	
	// 保存日志到数据库
	private void saveDraftLog(Context context, RgctDraftLog draftLog) throws BizAppException {
	    RequestInfo request = (RequestInfo)ContextUtil.getRequestData(context);
		String draftContent = (String)ContextUtil.getContextAttribute(context, "draftContent");
	    //记合同报文信息
		if (this.isContractDraft(request)){
		    //TODO:电子合同类业务报文暂时不做
//			this.saveContractDraftLog(context, logFileName);
			return;
		}
		String functionId = request.getServiceId();
		
		IRgctDraftLogService draftLogService = RcServiceFactory.getRgctDraftLogService();
		
		// 如果有原报文ID，那么就将原日志的发送部分作为此回日志的发送部分；
		String originalId = request.getOrgnlMsgId();
		if (!StringUtils.isEmpty(originalId)) {
			//原报文ID为申请报文ID
			draftLog = draftLogService.getRgctDraftLogByReqSid(originalId);
			if (draftLog == null) {
				//此处针对线上清算签收返回的036报文
				draftLog = draftLogService.getSignDraftLogByReqMsgId(originalId);
			}
			if (draftLog != null) {
				//如果此报文是上次某个报文的确认,那么就将上次的报文的发送标志设为成功
				draftLog.setSendFlag(IDict.K_DRAFTLOG_SEND.K_DRAFTLOG_SEND_TRUE);
				if (functionId.equals("Proc033")) {
					//如果此报文是成功回复,那么就设置上次的发送报文的处理状态为成功,反之,失败
					String processFlag = request.getPrcCd();
					if (DraftConstants.PE1I0000.equals(processFlag)){
						draftLog.setProcessFlag(IDict.K_DRAFTLOG_PROCESS.K_DRAFTLOG_PROCESS_TRUE);
					}else{
						draftLog.setProcessFlag(IDict.K_DRAFTLOG_PROCESS.K_DRAFTLOG_PROCESS_FALSE);
					}
				}else if (functionId.equals("Proc005")) {
					draftLog.setProcessFlag(IDict.K_DRAFTLOG_PROCESS.K_DRAFTLOG_PROCESS_TRUE);
				}else if (functionId.equals("Proc036")) {
					//如果此报文是成功回复,那么就设置上次的发送报文的处理状态为成功,反之,失败
					String processFlag = request.getPrcCd();
					if (DraftConstants.PE1I0000.equals(processFlag)){
						draftLog.setProcessFlag(IDict.K_DRAFTLOG_PROCESS.K_DRAFTLOG_PROCESS_TRUE);
					}else{
						draftLog.setProcessFlag(IDict.K_DRAFTLOG_PROCESS.K_DRAFTLOG_PROCESS_FALSE);
					}
				}else if (functionId.equals("Proc122")) {
					draftLog.setProcessFlag(IDict.K_DRAFTLOG_PROCESS.K_DRAFTLOG_PROCESS_TRUE);
				}
				//记录上次发送报文人行处理的结果
				String processMsg = request.getPrcMsg();
				if(!StringUtils.isEmpty(processMsg)){
				    draftLog.setDraftInfo(processMsg);
				}
				draftLog.setRespDraft(draftContent);
				draftLogService.modifyRgctDraftLog(draftLog);
				//为了统计收到的报文,设置报文的接收行,主要针对本行的005,031,032,033,036
				draftLog.setAcceptBankNo(originalId.substring(0, 12));
			}
		} 
		//如果不是本接入点发出的报文,那么就生成一个日志记录,否则就根据上条日志记录新建
        if(draftLog==null){
            draftLog = new RgctDraftLog();
        }
        draftLog.setLogId(null);
		
		// 如果接收的报文是034通用转发报文，那么就将他行的报文标识号与他行的报文类型存入本次日志
		if (functionId.equals("Proc034")) {
			DraftInfoVo vo=(DraftInfoVo)request;
			String reqMsgId = request.getReqMsgId();
			// 他行的报文标识号
			draftLog.setReqMsgId(reqMsgId);
			draftLog.setReqSid(reqMsgId);
			// 他行的报文类型
			draftLog.setFromDraftNo(vo.getBusiType());
		} 
		// 接收行行号
		String acceptBankNo = "";
		if(request instanceof DraftInfoVo){
		    DraftInfoVo vo=(DraftInfoVo)request;
		    acceptBankNo=vo.getReceiveBankNo();
		    if(!StringUtils.isBlank(vo.getBill().getBillNo())){
		        draftLog.setBillNo(vo.getBill().getBillNo());
		    }
		}
		
		if (!StringUtils.isEmpty(acceptBankNo)){
			draftLog.setAcceptBankNo(acceptBankNo);
		}

		// 接收报文类型
		// 接收报文标识号
		String msgId = request.getReqMsgId();
		draftLog.setRespSid(msgId);
		
		// 接收报文时间	
		String respDatetime = msgId.substring(12, 20);
		/*Date date = MsgUtil.converISODateTime(respDatetime,null);
		draftLog.setRespDatetime(date);*/
		String formatDate = DateUtil.dateTo10(respDatetime);
		draftLog.setRespDt(formatDate);


		// 响应行行号
		String RespBankNo = "";
		RespBankNo = msgId.substring(0, 12);
		draftLog.setRespBankNo(RespBankNo);
		

		// 报文发送标记
		draftLog.setInOut(IDict.K_DRAFTLOG.K_DRAFTLOG_IN);
		draftLog.setRespDraft(draftContent);
		draftLog.setDraftNoResp(functionId.substring(4));
		if(StringUtils.isBlank(draftLog.getLogId())){
		    String logId = ServiceFactory.getSequenceService().getRcLogSerialNo();
            draftLog.setLogId(logId);
            draftLogService.addRgctDraftLog(draftLog);
		}else{
		    draftLogService.modifyRgctDraftLog(draftLog);
		}
		
		
		ContextUtil.setContextAttribute(context, IConstants.DRAFT_LOG, draftLog);
	}
	
	private boolean isContractDraft(RequestInfo request) throws BizAppException{
		// 接收报文msgName
		String functionId = request.getServiceId();
		
		if (contractDraftNoSet.contains(functionId)){
			return true;
		}
		if ("Proc033".equals(functionId)){
			String originalId = request.getOrgnlMsgId();
			if (!StringUtils.isEmpty(originalId)) {
				//原报文ID为申请报文ID
				IRgctDraftLogService draftLogService = RcServiceFactory.getRgctDraftLogService();
				
				RgctDraftLog orgnlDraftLog = draftLogService.getRgctDraftLogByReqSid(originalId);
				if (orgnlDraftLog != null) {
					return IDict.K_DRAFTLOG_ENTITY.K_DRAFTLOG_ENTITY_CONTRACT.equals(orgnlDraftLog.getEntityType());
				}
			}
		}
		return false;
	}
	
	/**
	 * 保存合同相关报文
	 * @return
	 * @throws BizAppException 
	 */
	private void saveContractDraftLog(Context context, String logFileName) throws BizAppException {
		IData request = (IData)ContextUtil.getRequestData(context);
		
		RgctDraftLog draftLog = null;
		IRgctDraftTemplateService draftTemplateService = RcServiceFactory.getRgctDraftTemplateService();
		IRgctDraftLogService draftLogService = RcServiceFactory.getRgctDraftLogService();
		
		// 接收报文msgName
		String functionId = request.getString(IFieldName.functionId);
		// 如果有原报文ID，那么就将原日志的发送部分作为此回日志的发送部分；
		String originalId = request.getString("OrgnlMsgId.Id");
		if (!StringUtils.isEmpty(originalId)) {
			//原报文ID为申请报文ID
			draftLog = draftLogService.getRgctDraftLogByReqSid(originalId);		
			if (draftLog != null ) {
				//如果此报文是上次某个报文的确认,那么就将上次的报文的发送标志设为成功
				draftLog.setSendFlag(IDict.K_DRAFTLOG_SEND.K_DRAFTLOG_SEND_TRUE);
				if (functionId.equals("CommercialDraftCommonStatus")) {
					//如果此报文是成功回复,那么就设置上次的发送报文的处理状态为成功,反之,失败
					//Element prcCdElement = (Element) document.selectSingleNode("//Document/" + msgName + "/BizCtrlInf/PrcCd");
					String processFlag = request.getString("BizCtrlInf.PrcCd");
					if (DraftConstants.PE1I0000.equals(processFlag)){
						draftLog.setProcessFlag(IDict.K_DRAFTLOG_PROCESS.K_DRAFTLOG_PROCESS_TRUE);
					}else{
						draftLog.setProcessFlag(IDict.K_DRAFTLOG_PROCESS.K_DRAFTLOG_PROCESS_FALSE);
					}
				}else if (functionId.equals("ContractOfferConfirm")) {
					draftLog.setProcessFlag(IDict.K_DRAFTLOG_PROCESS.K_DRAFTLOG_PROCESS_TRUE);
				}
				//记录上次发送报文人行处理的结果
				//Element prcMsgElement = (Element) document.selectSingleNode("//Document/" + msgName + "/BizCtrlInf/PrcMsg");
				
				String processMsg = request.getString("BizCtrlInf.PrcMsg");
				if(!StringUtils.isEmpty(processMsg)){
				    draftLog.setDraftInfo(processMsg);
				}
				//更新数据库
				draftLogService.modifyRgctDraftLog(draftLog);
				
				//为了统计收到的报文,设置报文的接收行,主要针对本行的005,031,032,033,036
				draftLog.setAcceptBankNo(originalId.substring(0, 12));
			}
		} 
		//如果不是本接入点发出的报文,那么就生成一个日志记录,否则就根据上条日志记录新建
		if(draftLog==null){
			draftLog = new RgctDraftLog();
		}
		String logId = ServiceFactory.getSequenceService().getRcLogSerialNo();
		draftLog.setLogId(logId);

		// 如果接收的报文是203转发报文，那么就将他行的报文标识号与他行的报文类型存入本次日志
		if (functionId.equals("ContractOfferTransfer")) {
			XmlNode bodyNode = (XmlNode)request.getValue(IFieldName.ECDS_BODYXML);
			
			PathFilter<XmlNode> filter = new PathFilter<XmlNode>(bodyNode);
			XmlNode reqInfoConfig = filter.findNode("/"+ functionId +"/ReqInf");
			//String xpath = "//Document/" + msgName + "/ReqInf";
			//Element element = (Element) document.selectSingleNode(xpath);
			//List elements = element.elements();
			//element = (Element) elements.get(0);
			//String transName = element.getName();
			String transName = reqInfoConfig.getNodeName();
			//String xpath = "//Document/" + msgName + "/ReqInf/" + transName + "/MsgId/Id";
			//element = (Element) document.selectSingleNode(xpath);
			// 他行的报文标识号
			String reqMsgId = request.getString("ReqInf." + transName + ".MsgId.Id");
			draftLog.setReqMsgId(reqMsgId);
			//他行的报文类型
			String fromDraftNo = draftTemplateService.getRgctDraftTemplateByName(transName).getDraftNo();
			draftLog.setFromDraftNo(fromDraftNo);
		} 
		
		// 接收行行号
		/*String acptBankNoPath = "//Document/" + msgName + "/Rcvr/Acct/AcctSvcr";
		Element acptBankNoElement = (Element) document.selectSingleNode(acptBankNoPath);
		if (acptBankNoElement != null) {
			String acptBankNo = acptBankNoElement.getTextTrim();
			draftLog.setAcptBankNo(acptBankNo);
		}*/
		String acptBankNo = request.getString("Rcvr.Acct.AcctSvcr");
		if (!StringUtils.isEmpty(acptBankNo)){
			draftLog.setAcceptBankNo(acptBankNo);
		}

		// 设置接收报文,当CLOB的字符串长度处于[1000，2000]时，插入会报错
		/*String respMsg = "";
		if (msgBody.length() >= 1000 && msgBody.length() <= 2000) {
			respMsg = msgBody + new String(new char[2001 - msgBody.length()]);
		} else {
			respMsg = msgBody;
		}*/
		//Clob respDraft = Hibernate.createClob(respMsg);
		//draftLog.setRespDraft(msgHead + msgBody);
		draftLog.setRespDraft(logFileName);
		
		RgctDraftTemplate draftTemplate = draftTemplateService.getRgctDraftTemplateByName(functionId);
		// 接收报文类型
		if (draftTemplate != null) {
			draftLog.setDraftNoResp(draftTemplate.getDraftNo());
		}

		// 接收报文标识号
		String msgId = "";
		/*Element idElement = (Element) document.selectSingleNode("//Document/" + msgName + "/MsgId/Id");
		if (idElement != null) {
			msgId = idElement.getTextTrim();
		}*/
		String tempStr = request.getString("MsgId.Id");
		if (!StringUtils.isEmpty(tempStr)){
			msgId = tempStr;
		}
		draftLog.setRespSid(msgId);
		
		//接收报文时间	
		String respDatetime = msgId.substring(12, 20);
		//Date date = MsgUtil.converISODateTime(respDatetime, null);
		String formatDate = DateUtil.dateTo10(respDatetime);
		draftLog.setRespDt(formatDate);

		// 响应行行号
		String RespBankNo = "";
		RespBankNo = msgId.substring(0, 12);
		draftLog.setRespBankNo(RespBankNo);

		// 设置合同号
		/*String CtrctNbXpath = "//Document/" + msgName + "/Ctrct/CtrctNb";
		Element CtrctNbElement = (Element) document.selectSingleNode(CtrctNbXpath);
		if (CtrctNbElement != null) {
			String contractNo = CtrctNbElement.getTextTrim();
			draftLog.setEntityNo(contractNo);			
		}*/
		tempStr = request.getString("Ctrct.CtrctNb");
		if (!StringUtils.isEmpty(tempStr)){
			draftLog.setEntityNo(tempStr);	
		}
		draftLog.setEntityType(IDict.K_DRAFTLOG_ENTITY.K_DRAFTLOG_ENTITY_CONTRACT);

		// 报文发送标记
		draftLog.setInOut(IDict.K_DRAFTLOG.K_DRAFTLOG_IN);

		//是否需要核对
		if (draftTemplate != null) {
			draftLog.setIsCheckDraft(draftTemplate.getIsCheckDraft());
		}
		draftLogService.addRgctDraftLog(draftLog);
		
		ContextUtil.setContextAttribute(context, IConstants.DRAFT_LOG, draftLog);
		return;
	}
}
