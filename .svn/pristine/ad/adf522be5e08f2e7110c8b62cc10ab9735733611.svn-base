/********************************************
 * 文件名称: T201002Service.java
 * 系统名称: 票据管理平台V2.0
 * 模块名称: 贴现申请
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: 2.0.0.1
 * 开发人员: yanjl
 * 开发时间: 2016-07-16
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期    修改人员    修改说明
 *********************************************/
package com.herongtech.online.trans.acpt.trans201002;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.herongtech.commons.tools.StringUtils;
import com.herongtech.console.core.common.pubinfo.AnswerData;
import com.herongtech.console.core.constant.IConstants;
import com.herongtech.console.core.constant.IErrorNo;
import com.herongtech.console.core.constant.IFieldName;
import com.herongtech.console.core.util.CommUtils;
import com.herongtech.console.core.util.StatusUtils;
import com.herongtech.console.core.util.XmlBeanUtil;
import com.herongtech.console.domain.acpt.bean.AcptBillInfo;
import com.herongtech.console.domain.acpt.dao.AcptBillInfoDao;
import com.herongtech.console.service.busiservice.acpt.AcptBillInfoBean;
import com.herongtech.context.Context;
import com.herongtech.context.util.ContextUtil;
import com.herongtech.data.interfaces.data.IData;
import com.herongtech.db.impl.session.DBFactory;
import com.herongtech.db.interfaces.IDB;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.online.trans.OnlineBaseService;
import com.herongtech.online.trans.acpt.trans201001.Var201001;
import com.herongtech.rc.service.RcServiceFactory;
import com.herongtech.rc.service.rcservice.IRcAcptBillService;
import com.herongtech.xmlchannel.pkg.ProResult;
import com.herongtech.xmlchannel.pkg.TransInfo;

/**
 * 电票审批拒绝
 */

public class T201002Service extends OnlineBaseService{


	/**
	 * 入口方法
	 * @param context
	 */
	@Override
	protected void action(Context context) throws Exception {
		Var201002 transVar = new Var201002();
		
		// 交易预处理
		transRequest(context);

		// 常规校验
		CheckData(context, transVar);

		// 查询处理
		ToLocal(context, transVar);

		// 应当包处理
		PackAnswer(context, transVar);
		
		
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
	 * @param var106101
	 * @throws BizAppException
	 */
	protected void CheckData(Context context, Var201002 var201002) throws BizAppException {
		String request = (String)ContextUtil.getRequestData(context);
//		BusiDate busiDate = (BusiDate) ContextUtil.getContextAttribute(context, IConstants.SYSBUSININFO);
		
		Map<String, Class> clazzMap = new HashMap<String, Class>();
        clazzMap.put("Document", Var201002.class);
        Var201002 temp=(Var201002)XmlBeanUtil.xml2Bean(clazzMap, request);
		
		
		
		if (StringUtils.isEmpty(temp.getAcptmxIds())){
			throw new BizAppException(IErrorNo.ERR_CUSTACCOUNTNULL, "清单ID未上送");
		}
		var201002.setAcptmxIds(temp.getAcptmxIds());
	}

	/**
	 * 电票审批拒绝
	 * @param context
	 * @param var106101
	 * @throws BizAppException
	 */
	protected void ToLocal(Context context, Var201002 var201002) throws BizAppException{
		String request = (String)ContextUtil.getRequestData(context);
		String[] ids=CommUtils.couvertLong(var201002.getAcptmxIds());
		IDB session = DBFactory.getDB();
		try {
			IRcAcptBillService  service=RcServiceFactory.getRcAcptBillService();
			session.beginTransaction();
			AcptBillInfoDao infoDao=new AcptBillInfoDao();
			for (int i = 0; i < ids.length; i++) {
				AcptBillInfo info=infoDao.getAcptBillInfo(ids[i]);
//				info.setBillStatus("-1");
				info.setStatus(StatusUtils.handleStatus("AcptAuditController", "rejectLoanNotification", null, info.getStatus()));
				infoDao.modifyAcptBillInfo(info);
				service.unLock(info.getRgctId());
			}
	    	session.endTransaction();
	    	
		} catch (Exception e) {
			try {
				session.rollback();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			e.printStackTrace();
		}
    	
	}

	 /**
     * 应答包处理
     * @param context
     * @param Var201002
     * @throws BizAppException
     */
	protected void PackAnswer(Context context, Var201002 var201002) throws BizAppException{
//		IData request = (IData)ContextUtil.getRequestData(context);
//		AnswerData response = new AnswerData(request);
//		ContextUtil.setResponseData(context, response.getAnswerData());
        TransInfo trans=(TransInfo)ContextUtil.getContextAttribute(context, IConstants.TRANS_INFO);
        ProResult result=new ProResult();
        result.setType("S");
       
        result.setExSerial(trans.getExSerial());
        result.setFunctionId(trans.getFunctionId());
        Map<String, Class> clazzMap = new HashMap<String, Class>();
        clazzMap.put("Document", ProResult.class);
        String resp=XmlBeanUtil.bean2xml(clazzMap, result);		
		ContextUtil.setResponseData(context, resp);
		
		
	}

	
	@Override
	public String getServiceId() {
		return "201002";
	}
	
	@Override
	public String getTransName() {
		return "电票审批拒绝 ";
	}

	@Override
	public String getTransVersion() {
		return "2.0.0.1";
	}

}
