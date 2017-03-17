/********************************************
 * 文件名称: T130101Service.java
 * 系统名称: 票据管理平台V2.0
 * 模块名称: 提示保证
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: 2.0.0.1
 * 开发人员: v
 * 开发时间: 20160808
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期    修改人员    修改说明
 *********************************************/
package com.herongtech.online.trans.trans130101;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.herongtech.commons.tools.StringUtils;
import com.herongtech.console.core.constant.IConstants;
import com.herongtech.console.core.constant.IDict;
import com.herongtech.console.core.constant.IErrorNo;
import com.herongtech.console.core.constant.RcConstants;
import com.herongtech.console.core.util.CommUtils;
import com.herongtech.console.core.util.DateTimeUtil;
import com.herongtech.console.core.util.XmlBeanUtil;
import com.herongtech.console.domain.assu.bean.AssuBillInfo;
import com.herongtech.console.domain.assu.dao.AssuBillInfoDao;
import com.herongtech.console.domain.bean.Branch;
import com.herongtech.console.domain.bean.CustInfo;
import com.herongtech.console.domain.bean.CustInfoAcct;
import com.herongtech.console.domain.bean.SignProd;
import com.herongtech.console.service.ServiceFactory;
import com.herongtech.console.service.interfaces.ICustInfoAcctService;
import com.herongtech.console.service.interfaces.ISequenceService;
import com.herongtech.console.service.interfaces.ISignProdService;
import com.herongtech.context.Context;
import com.herongtech.context.util.ContextUtil;
import com.herongtech.db.impl.session.DBFactory;
import com.herongtech.db.interfaces.IDB;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.online.trans.OnlineBaseService;
import com.herongtech.rc.domain.bean.RgctBill;
import com.herongtech.rc.domain.bean.RgctBillInfo;
import com.herongtech.rc.service.RcServiceFactory;
import com.herongtech.xmlchannel.pkg.TransInfo;
/**
 * 提示保证
 */
public class T130101Service extends OnlineBaseService {

	 
	/**
	 * 入口方法
	 * @param context
	 */
	protected void action(Context context) throws Exception {
		Var130101 transVar = new Var130101();
		
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
	 * @param Var130101
	 * @throws BizAppException
	 */
	protected void CheckData(Context context, Var130101 var130101) throws BizAppException{
		String request = (String)ContextUtil.getRequestData(context);
		Map<String, Class> clazzMap = new HashMap<String, Class>();
        clazzMap.put("Document", Var130101.class);
        Var130101 temp=(Var130101)XmlBeanUtil.xml2Bean(clazzMap, request);
		
        if (StringUtils.isEmpty(temp.getSignature())){
        	throw new BizAppException(IErrorNo.ERR_CUSTACCOUNTNULL, "电子签名未上送");
        }
		if (StringUtils.isEmpty(temp.getCustAccount())){
			throw new BizAppException(IErrorNo.ERR_CUSTACCOUNTNULL, "申请人账号未上送");
		}
		if (StringUtils.isEmpty(temp.getRgctIds())){
			throw new BizAppException(IErrorNo.ERR_CUSTACCOUNTNULL, "票据ID未上送");
		}
		if (StringUtils.isEmpty(temp.getReceiverAcctNo())){
			throw new BizAppException(IErrorNo.ERR_CUSTACCOUNTNULL, "接收人账号未上送");
		}
		if (StringUtils.isEmpty(temp.getReceiverBankNo())){
			throw new BizAppException(IErrorNo.ERR_CUSTACCOUNTNULL, "接收人开户行行号未上送");
		}
		if (StringUtils.isEmpty(temp.getReceiverName())){
			throw new BizAppException(IErrorNo.ERR_CUSTACCOUNTNULL, "接收人客户名称未上送");
		}
		org.springframework.beans.BeanUtils.copyProperties(temp, var130101);
	}
	
	/**
	 * 查询处理
	 * @param context
	 * @param Var130101
	 * @throws BizAppException
	 */
    protected void ToLocal(Context context, Var130101 var130101) throws BizAppException{
    	String [] ids =CommUtils.couvertLong(var130101.getRgctIds());
    	List<Var130101InfoBean> result =new ArrayList<Var130101InfoBean>();
    	int k = 0;
    	IDB session = DBFactory.getDB();
    	for (int i = 0; i < ids.length; i++) {
    		Var130101InfoBean bean=new Var130101InfoBean();
    		try{
    			session.beginTransaction();
    			String id = ids[i];
    			if(RcServiceFactory.getRgctBillInfoService().getRgctBillInfo(id)==null){
    				throw new BizAppException("登记中心ID检查失败");
    			}
    			RgctBill bill=RcServiceFactory.getRcAssuranceService().getRgctBillById(id);
    			SignProd applySignProd = null;
    			ISignProdService signProdService = ServiceFactory.getSignProdService();
				applySignProd = signProdService.getSignProdByBusAct(IConstants.ELECTRON_SINGPROD, var130101.getCustAccount());
    			if(applySignProd==null){
    				throw new BizAppException("客户未签约");
    			}
    			org.springframework.util.Assert.isTrue(var130101.getCustAccount().equals(bill.getHist().getHoldAcctNo()),"发起人帐号" + var130101.getCustAccount()
    						+ "与持票人帐号" + bill.getHist().getHoldAcctNo() + "不一致,请检查");
    			CustInfoAcct custInfoAcct=ServiceFactory.getCustInfoAcctService().getParam(var130101.getCustAccount());
    			CustInfo custInfo = ServiceFactory.getCustInfoService().getParam(custInfoAcct.getCustNo());
    			bill.getHist().setFromAcctNo(var130101.getCustAccount());//申请人 账号
    			bill.getHist().setFromSign(var130101.getSignature());//申请人 电子签名
    			bill.getHist().setEndorseDt(DateTimeUtil.getWorkdayString());//申请 日期
    			bill.getHist().setFromBankNo(bill.getHist().getHoldBankNo());//申请人 行号
    			bill.getHist().setFromCustNo(applySignProd.getCustNo());//申请人客户号
    			bill.getHist().setFromRole(RcConstants.BUSSINESS_ROLE1);//申请人参与者类型
    			bill.getHist().setFromOrgcode(applySignProd.getIdNumber());//申请人组织机构代码
    			bill.getHist().setFromName(applySignProd.getCustName());//申请人名称
    			bill.getHist().setToName(var130101.getReceiverName());
    			bill.getHist().setToAcctNo(var130101.getReceiverAcctNo());
    			bill.getHist().setToBankNo(var130101.getReceiverBankNo());
    			bill.getHist().setFromRemark(var130101.getRemark());
    			bill.getHist().setBatchId(var130101.getBatchNo());
//    			createAssu(var130101,bill,custInfo);
    			bill.getHist().setFromBankNo(applySignProd.getRemitterBankNo());
    			RcServiceFactory.getRcAssuranceService().assuranceApply(bill);

    			bean.setRgctId(ids[i]);
    			bean.setIsSuccess("S");
        		session.endTransaction();
    		}catch(Exception e){
    			bean.setRgctId(ids[i]);
                bean.setIsSuccess("E");
                bean.setErrMsg(e.getMessage());
    			try {
					session.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
    		}
    		result.add(bean);
    	}
    	for(int i=0; i<result.size();i++){
    		if("E".equals(result.get(i).getIsSuccess())){
    			k=k+1;
    		}
    	}
    	var130101.setErrNum(String.valueOf(k));
    	var130101.setTotNum(String.valueOf(result.size()));
    	var130101.setResult(result);
    }
    
    /**
     * 应答包处理
     * @param context
     * @param Var130101
     * @throws BizAppException
     */
    protected void PackAnswer(Context context, Var130101 var130101) throws BizAppException{
    	TransInfo trans=(TransInfo)ContextUtil.getContextAttribute(context, IConstants.TRANS_INFO);
    	Var130101ProResult proResult=new Var130101ProResult();
        proResult.setType("S");
        if(!"0".equals(var130101.getErrNum())){
  			proResult.setType("E");
  	    }
        proResult.setTotNum(var130101.getTotNum());
        proResult.setErrNum(var130101.getErrNum());
        proResult.setExSerial(trans.getExSerial());
        proResult.setFunctionId(trans.getFunctionId());
        List<Var130101InfoBean> infoList=var130101.getResult();
        Var130101Result result=new Var130101Result();
        result.setVar130101ProResult(proResult);
        result.setResult(infoList);
        
        Map<String, Class> clazzMap = new HashMap<String, Class>();
        clazzMap.put("Document", Var130101Result.class);
        clazzMap.put("info", Var130101InfoBean.class);
       
        String resp=XmlBeanUtil.bean2xml(clazzMap, result);     
        ContextUtil.setResponseData(context, resp);
			
    }
    
    /*
	 * 建立保证信息
	 */
	/*private AssuBillInfo createAssu(Var130101 var130101, RgctBill bill, CustInfo custInfo) throws BizAppException {
		AssuBillInfo assu = new AssuBillInfo();
		RgctBillInfo info = bill.getInfo();
		AssuBillInfoDao assuBillDao=new AssuBillInfoDao();
		ISequenceService seqService = ServiceFactory.getSequenceService();
		try {
			assu.setAssumxId(seqService.getPrimaryKeyValue());
			assu.setRgctId(info.getId());
			assu.setWarteeDt(DateTimeUtil.getWorkdayString());
			assu.setWarteeSign(var130101.getSignature());
			assu.setWarteeAcctNo(var130101.getCustAccount());
			assu.setGuarntrAcctNo(var130101.getReceiverAcctNo());
			assu.setGuartrBankNo(var130101.getReceiverBankNo());
			assu.setGuartrName(var130101.getReceiverName());
			assu.setWarteeCustNo(custInfo.getCustNo());
			assu.setAcceptor(info.getAcceptor());
			assu.setAcceptorBankName(info.getAcceptorBankName());
			assu.setAcceptorBankNo(info.getAcceptorBankNo());
			assu.setBillMoney(info.getBillMoney());
			assu.setBillClass(info.getBillClass());
			assu.setBillType(info.getBillType());
			assu.setBillNo(info.getBillNo());
			assu.setDueDt(info.getDueDt());
			assu.setIssueDt(info.getIssueDt());
			assu.setPayee(info.getPayeeName());
			assu.setPayeeBankName(info.getPayeeBankName());
			assu.setPayeeBankNo(info.getPayeeBankNo());
			assu.setRemitter(info.getRemitter());
			assu.setRemitterBankName(info.getRemitterBankName());
			assu.setRemitterBankNo(info.getRemitterBankNo());
			
			if(var130101.getReceiverBankNo().startsWith("305")){
				Branch branch = ServiceFactory.getBranchService().getBranchByBrchBankNo(var130101.getReceiverBankNo());
				assu.setBranchNo(branch.getBranchNo());
			}
			
			if ("A_01".equals(bill.getHist().getCurStatus())) {
				assu.setAssuType("1");
				assu.setWarteeBankNo(info.getRemitterBankNo());
				assu.setWarteeOrgcode(info.getRemitterOrgCode());
				assu.setWarteePartnerType(info.getRemitterRole());
				assu.setWarteeCustName(info.getRemitter());
			} else if ("B_02".equals(bill.getHist().getCurStatus())) {
				assu.setAssuType("2");
				assu.setWarteeBankNo(info.getAcceptorBankNo());
				assu.setWarteeOrgcode(info.getAcceptorOrgCode());
				assu.setWarteePartnerType(custInfo.getPartnerType());
				assu.setWarteeCustName(info.getAcceptor());
			} else {
				assu.setAssuType("3");
				assu.setWarteeBankNo(bill.getHist().getFromBankNo());
				assu.setWarteeOrgcode(custInfo.getOrgCode());
				assu.setWarteePartnerType(custInfo.getPartnerType());
				assu.setWarteeCustName(custInfo.getCustName());
			}
			assuBillDao.addAssuBillInfo(assu);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		bill.getHist().setAssuId(assu.getAssumxId());
		return assu;

	}*/
	
	public String getTransName() {		
		return "提示保证";
	}
	
	public String getTransVersion() {		
		return "2.0.0.1";
	}

	public String getServiceId() {
		return "130101";
	}
}
