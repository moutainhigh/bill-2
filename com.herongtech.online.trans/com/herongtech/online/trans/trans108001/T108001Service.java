/********************************************
 * 文件名称: T108001Service.java
 * 系统名称: 票据管理平台V2.0
 * 模块名称: 查询票据信息
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: 2.0.0.1
 * 开发人员: v
 * 开发时间: 20161209
 * 相关文档:
 * 修改记录: 修改日期    修改人员    修改说明
 *********************************************/
package com.herongtech.online.trans.trans108001;

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
import com.herongtech.console.domain.bean.SignProd;
import com.herongtech.console.domain.disc.bean.BillAllInfoBean;
import com.herongtech.console.domain.disc.bean.EndoBean;
import com.herongtech.console.service.ServiceFactory;
import com.herongtech.console.service.busiservice.disc.IDiscService;
import com.herongtech.console.service.interfaces.ISignProdService;
import com.herongtech.context.Context;
import com.herongtech.context.util.ContextUtil;
import com.herongtech.db.impl.session.DBFactory;
import com.herongtech.db.interfaces.IDB;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.log.CommonLog;
import com.herongtech.online.trans.OnlineBaseService;
import com.herongtech.online.trans.trans102001.Var102001;
import com.herongtech.online.trans.trans106102.Var106102InfoBean;
import com.herongtech.online.trans.trans106102.Var106102ProResult;
import com.herongtech.online.trans.trans106102.Var106102Result;
import com.herongtech.online.trans.trans130001.Var130001InfoBean;
import com.herongtech.online.trans.trans130001.Var130001Result;
import com.herongtech.online.trans.trans130103.Var130103InfoBean;
import com.herongtech.online.trans.trans130103.Var130103ProResult;
import com.herongtech.online.trans.trans130103.Var130103Result;
import com.herongtech.rc.domain.bean.RcBaseSearchBean;
import com.herongtech.rc.domain.bean.RgctBill;
import com.herongtech.rc.domain.bean.RgctBillInfo;
import com.herongtech.rc.service.RcServiceFactory;
import com.herongtech.xmlchannel.pkg.ProResult;
import com.herongtech.xmlchannel.pkg.TransInfo;
/**
 * 查询票据信息
 */
public class T108001Service extends OnlineBaseService {

	 
	/**
	 * 入口方法
	 * @param context
	 */
	protected void action(Context context) throws Exception {
		Var108001 transVar = new Var108001();
		
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
	 * @param Var108001
	 * @throws BizAppException
	 */
	protected void CheckData(Context context, Var108001 var108001) throws BizAppException{
		String request = (String)ContextUtil.getRequestData(context);

		Map<String, Class> clazzMap = new HashMap<String, Class>();
        clazzMap.put("Document", Var108001.class);
        Var108001 temp=(Var108001)XmlBeanUtil.xml2Bean(clazzMap, request);
        
        if (StringUtils.isEmpty(temp.getCustAccount())){
            throw new BizAppException(IErrorNo.ERR_CUSTACCOUNTNULL, "客户账号未上送");
        }
        if (StringUtils.isEmpty(temp.getRgctId())){
            throw new BizAppException(IErrorNo.ERR_CUSTACCOUNTNULL, "rgct_id未上送");
        }
        var108001.setCustAccount(temp.getCustAccount());
        var108001.setRgctId(temp.getRgctId());
   
		
	}
	
	/**
	 * 查询处理
	 * @param context
	 * @param Var108001
	 * @throws BizAppException
	 */
    protected void ToLocal(Context context, Var108001 var108001) throws BizAppException{
    	IDiscService discService = ServiceFactory.getDiscService();
    	BillAllInfoBean  billAll=new BillAllInfoBean();
        List<Var108001InfoBean> result =new ArrayList<Var108001InfoBean>();
    	try {
			billAll = discService.getElecBillDetail(var108001.getRgctId());
			var108001.setFrontBean(billAll.getFrontBean());
			var108001.setBackBean(billAll.getBackBean());
			var108001.setAssuBill(billAll.getGuarnteeList());
			for(EndoBean endoList :billAll.getBackBean().getEndoListBean()){//背书历史循环
				Var108001InfoBean bean=new Var108001InfoBean();
				bean.setEndoType(endoList.getEndoType());
				bean.setFromName(endoList.getFromName());
				bean.setToName(endoList.getToName());
				bean.setProtEndors(endoList.getProtEndors());
				bean.setEndoDate(endoList.getEndoDate());
				bean.setSignDate(endoList.getSignDate());
				bean.setRedeemOpenDate(endoList.getRedeemOpenDate());
				bean.setRedeemEndDate(endoList.getRedeemEndDate());
				bean.setAssuAdrr(endoList.getAssuAdrr());
				bean.setPayFlag(endoList.getPayFlag());
				bean.setPayRefuReson(endoList.getPayRefuReson());
				bean.setRecType(endoList.getRecType());
				
				result.add(bean);
				
			}
			for(AssuBillInfo assuBillList :billAll.getGuarnteeList()){//保证信息循环
				Var108001InfoBean bean1=new Var108001InfoBean();
				bean1.setGuartrName(assuBillList.getGuartrName());
				bean1.setGuarntrAddr(assuBillList.getGuartrAddr());
				bean1.setWarteeDt(assuBillList.getWarteeDt());
				bean1.setAssuType(assuBillList.getAssuType());
				result.add(bean1);
				
			}
			//正面信息添加到结果集
			Var108001InfoBean bean2=new Var108001InfoBean();
			bean2.setRgctId(billAll.getFrontBean().getRgctId());
			bean2.setBillNo(billAll.getFrontBean().getBillNo());
			bean2.setBillType(billAll.getFrontBean().getBillType());
			bean2.setBillClass(billAll.getFrontBean().getBillClass());
			bean2.setIssueDt(billAll.getFrontBean().getIssueDt());
			bean2.setAcceptDt(billAll.getFrontBean().getAcceptDt());
			bean2.setDueDt(billAll.getFrontBean().getDueDt());
			bean2.setBillMoney(billAll.getFrontBean().getBillMoney());
			bean2.setRemitter(billAll.getFrontBean().getRemitter());
			bean2.setRemitterAcct(billAll.getFrontBean().getRemitterAcct());
			bean2.setRemitterBank(billAll.getFrontBean().getRemitterBank());
			bean2.setRemitterBankNo(billAll.getFrontBean().getRemitterBankNo());
			bean2.setPayee(billAll.getFrontBean().getPayee());
			bean2.setPayeeAcct(billAll.getFrontBean().getPayeeAcct());
			bean2.setPayeeBank(billAll.getFrontBean().getPayeeBank());
			bean2.setPayeeBankNo(billAll.getFrontBean().getPayeeBankNo());
			bean2.setAcceptor(billAll.getFrontBean().getAcceptor());
			bean2.setAcceptorAcct(billAll.getFrontBean().getAcceptorAcct());
			bean2.setAcceptorBank(billAll.getFrontBean().getAcceptorBank());
			bean2.setRemitterCreditAgency(billAll.getFrontBean().getRemitterCreditAgency());
			bean2.setRemitterCreditClass(billAll.getFrontBean().getRemitterCreditClass());
			bean2.setRemitterCreditDueDt(billAll.getFrontBean().getRemitterCreditDueDt());
			bean2.setAcceptorCreditAgency(billAll.getFrontBean().getAcceptorCreditAgency());
			bean2.setAcceptorCreditClass(billAll.getFrontBean().getAcceptorCreditClass());
			bean2.setAcceptorCreditDueDt(billAll.getFrontBean().getAcceptorCreditDueDt());
			bean2.setConferNo(billAll.getFrontBean().getConferNo());
			bean2.setInvoiceNo(billAll.getFrontBean().getInvoiceNo());
			bean2.setBanEndorsementMark(billAll.getFrontBean().getBanEndorsementMark());
			bean2.setBillBeforeOwner(billAll.getFrontBean().getBillBeforeOwner());
			bean2.setCurStatusName(billAll.getFrontBean().getCurStatusName());
			result.add(bean2);

			var108001.setResult(result);
			
			
		} catch (SQLException e) {
            
		} 	
      
    }


    /**
     * 应答包处理
     * @param context
     * @param Var108001
     * @throws BizAppException
     */
    protected void PackAnswer(Context context, Var108001 var108001) throws BizAppException{
    	TransInfo trans=(TransInfo)ContextUtil.getContextAttribute(context, IConstants.TRANS_INFO);
        ProResult proResult=new ProResult();
        proResult.setType("S");
        proResult.setExSerial(trans.getExSerial());
        proResult.setFunctionId(trans.getFunctionId());
        List<Var108001InfoBean> infoList=var108001.getResult();
        Var108001Result result=new Var108001Result();
        result.setProResult(proResult);
        result.setResult(infoList);
        
        Map<String, Class> clazzMap = new HashMap<String, Class>();
        clazzMap.put("Document", Var108001Result.class);
        clazzMap.put("info", Var108001InfoBean.class);
       
        String resp=XmlBeanUtil.bean2xml(clazzMap, result);     
        ContextUtil.setResponseData(context, resp);
    }
	
	public String getTransName() {		
		return "查询票据信息";
	}
	
	public String getTransVersion() {		
		return "2.0.0.1";
	}

	public String getServiceId() {
		return "108001";
	}
}
