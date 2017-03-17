/********************************************
 * 文件名称: T201003Service.java
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
package com.herongtech.online.trans.acpt.trans201003;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import com.herongtech.commons.tools.StringUtils;
import com.herongtech.console.core.constant.IConstants;
import com.herongtech.console.core.constant.IErrorNo;
import com.herongtech.console.core.util.StatusUtils;
import com.herongtech.console.core.util.XmlBeanUtil;
import com.herongtech.console.domain.acpt.bean.AcptApplyInfo;
import com.herongtech.console.domain.acpt.bean.AcptBillInfo;
import com.herongtech.console.domain.acpt.dao.AcptApplyInfoDao;
import com.herongtech.console.domain.acpt.dao.AcptBillInfoDao;
import com.herongtech.console.service.busiservice.acpt.AcptApplyBean;
import com.herongtech.console.service.busiservice.acpt.AcptBillInfoBean;
import com.herongtech.context.Context;
import com.herongtech.context.util.ContextUtil;
import com.herongtech.db.impl.session.DBFactory;
import com.herongtech.db.interfaces.IDB;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.online.trans.OnlineBaseService;
import com.herongtech.online.trans.trans102001.Var102001;
import com.herongtech.rc.domain.bean.RgctBill;
import com.herongtech.rc.service.RcServiceFactory;
import com.herongtech.xmlchannel.pkg.ProResult;
import com.herongtech.xmlchannel.pkg.TransInfo;

/**
 * 电票审批查询
 */

public class T201003Service extends OnlineBaseService{


	/**
	 * 入口方法
	 * @param context
	 */
	@Override
	protected void action(Context context) throws Exception {
		Var201003 transVar = new Var201003();
		
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
	protected void CheckData(Context context, Var201003 var201003) throws BizAppException {
		String request = (String)ContextUtil.getRequestData(context);
//		BusiDate busiDate = (BusiDate) ContextUtil.getContextAttribute(context, IConstants.SYSBUSININFO);
		
		Map<String, Class> clazzMap = new HashMap<String, Class>();
        clazzMap.put("Document", Var201003.class);
        Var201003 temp=(Var201003)XmlBeanUtil.xml2Bean(clazzMap, request);
		
		
		if (StringUtils.isEmpty(temp.getBatchNo())){
			throw new BizAppException(IErrorNo.ERR_CUSTACCOUNTNULL, "批次号未上送");
		}
		var201003.setBatchNo(temp.getBatchNo());
	}

	/**
	 * 电票审批查询 
	 * @param context
	 * @param var106101
	 * @throws BizAppException
	 */
	protected void ToLocal(Context context, Var201003 var201003) throws BizAppException{
		String request = (String)ContextUtil.getRequestData(context);
		
		IDB session = DBFactory.getDB();
		try {
			session.beginTransaction();
			AcptApplyInfoDao applyDao=new AcptApplyInfoDao();
			AcptApplyInfo apply=applyDao.getAcptApplyInfoByBatchNo(var201003.getBatchNo());
			AcptBillInfoDao infoDao=new AcptBillInfoDao();
			List<AcptBillInfo> infoList=infoDao.getBillListByAcptId(apply.getAcptId(),StatusUtils.queryStatus("AcptAuditController", "extendLoanNotification", null)[0]);
	    	session.endTransaction();
	    	AcptApplyBean bean=new AcptApplyBean();
	    	BeanUtils.copyProperties(bean, apply);
	    	List<Var201003InfoBean> beanList = new ArrayList<Var201003InfoBean>();
			for (Iterator<AcptBillInfo> iterator = infoList.iterator(); iterator.hasNext();) {
				Var201003InfoBean infobean = new Var201003InfoBean();
				AcptBillInfo billInfo = iterator.next();
				BeanUtils.copyProperties(infobean, billInfo);
				beanList.add(infobean);
			}
	    	var201003.setApply(bean);
	    	var201003.setInfoList(beanList);
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
     * @param Var201003
     * @throws BizAppException
     */
	protected void PackAnswer(Context context, Var201003 var201003) throws BizAppException{
		Var201003Result result = new Var201003Result();
		  //批次
		  result.setAcptId(var201003.getApply().getAcptId());
		  result.setBatchNo(var201003.getApply().getBatchNo());
		  result.setBranchNo(var201003.getApply().getBranchNo());
		  result.setProtocalNo(var201003.getApply().getProtocalNo());
		  result.setFacNo(var201003.getApply().getFacNo());
		  result.setFkjjh(var201003.getApply().getFkjjh());
		  result.setProdNo(var201003.getApply().getProdNo());
		      
		  result.setRemitter(var201003.getApply().getRemitter());
		  result.setRemitterCustNo(var201003.getApply().getRemitterCustNo());
		  result.setRemitterAcct(var201003.getApply().getRemitterAcct());
		  result.setRemitterBankNo(var201003.getApply().getRemitterBankName());
		  result.setIssueBranchNo(var201003.getApply().getIssueBranchNo());
		
		 //清单
		result.setInfoList(var201003.getInfoList());	
    	TransInfo trans=(TransInfo)ContextUtil.getContextAttribute(context, IConstants.TRANS_INFO);
        ProResult proResult=new ProResult();
        proResult.setType("S");
        proResult.setExSerial(trans.getExSerial());
        proResult.setFunctionId(trans.getFunctionId());
        List<Var201003InfoBean> infoList=var201003.getResult();
       // Var201003Result resul=new Var201003Result();
        result.setProResult(proResult);
        result.setResult(infoList);
        
        Map<String, Class> clazzMap = new HashMap<String, Class>();
        clazzMap.put("Document", Var201003Result.class);
        clazzMap.put("info", Var201003InfoBean.class);
       
        String resp=XmlBeanUtil.bean2xml(clazzMap, result);     
        ContextUtil.setResponseData(context, resp);
		
		
	}

	
	@Override
	public String getServiceId() {
		return "201003";
	}
	
	@Override
	public String getTransName() {
		return "电票审批查询 ";
	}

	@Override
	public String getTransVersion() {
		return "2.0.0.1";
	}

}
