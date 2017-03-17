/********************************************
 * 文件名称: T108006Service.java
 * 系统名称: 票据管理平台V2.0
 * 模块名称: 可质押的票据查询
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: 2.0.0.1
 * 开发人员: v
 * 开发时间: 20160808
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期    修改人员    修改说明
 *********************************************/
package com.herongtech.online.trans.trans108006;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;

import com.herongtech.commons.tools.StringUtils;
import com.herongtech.console.core.common.json.Page;
import com.herongtech.console.core.constant.BillFlowConst;
import com.herongtech.console.core.constant.IConstants;
import com.herongtech.console.core.constant.IErrorNo;
import com.herongtech.console.core.util.XmlBeanUtil;
import com.herongtech.context.Context;
import com.herongtech.context.util.ContextUtil;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.online.trans.OnlineBaseService;
import com.herongtech.rc.domain.bean.BillFlowInfoSearchBean;
import com.herongtech.rc.domain.bean.BillFlowQueryResult;
import com.herongtech.rc.service.RcServiceFactory;
import com.herongtech.xmlchannel.pkg.ProResult;
import com.herongtech.xmlchannel.pkg.TransInfo;

/**
 * 电子票据交易类查询
 *
 */
public class T108006Service extends OnlineBaseService{
	/**
	 * 入口方法
	 * @param context
	 */
	protected void action(Context context) throws Exception {
		Var108006 transVar = new Var108006();
		
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
	 * @param Var108006
	 * @throws BizAppException
	 */
	protected void CheckData(Context context, Var108006 var108006) throws BizAppException{
		String request = (String)ContextUtil.getRequestData(context);
		Map<String, Class> clazzMap = new HashMap<String, Class>();
		clazzMap.put("Document", Var108006.class);
		Var108006 temp=(Var108006)XmlBeanUtil.xml2Bean(clazzMap, request);
		if (StringUtils.isEmpty(temp.getCustAccount())){
			throw new BizAppException(IErrorNo.ERR_CUSTACCOUNTNULL, "客户账号未上送");
		}
		if (StringUtils.isEmpty(temp.getType())){
			throw new BizAppException(IErrorNo.ERR_CUSTACCOUNTNULL, "业务类型未上送");
		}
		if (StringUtils.isEmpty(temp.getBeginDate())&&StringUtils.isEmpty(temp.getEndEndt())){
			throw new BizAppException(IErrorNo.ERR_CUSTACCOUNTNULL, "开始日期和结束日期未上送");
		}
		BeanUtils.copyProperties(temp, var108006);
	}
	
	/**
	 * 查询处理
	 * @param context
	 * @param Var108006
	 * @throws BizAppException
	 */
    protected void ToLocal(Context context, Var108006 var108006) throws BizAppException{
    	Page page=new Page();
    	BillFlowInfoSearchBean query=new BillFlowInfoSearchBean();
//    	IDB session = DBFactory.getDB();
    	page.setCurrentPage(Integer.parseInt(var108006.getCurrentPage()));
    	page.setShowCount(Integer.parseInt(var108006.getPageSize()));
    	query.setFromAcctNo(var108006.getCustAccount());
    	query.setBusiType(var108006.getType());
    	query.setBeginDate(var108006.getBeginDate());
    	query.setEndEndt(var108006.getEndEndt());
        List<Var108006InfoBean> result =new ArrayList<Var108006InfoBean>();
        try{
        	List<BillFlowQueryResult> resultList = RcServiceFactory.getBillFlowInfoService().queryBillFlowResult(query,page);
    	/*List<Object> list = new ArrayList<Object>();
    	List<RgctBill> rgctBill = null;
		try {
			rgctBill = session.getBeanListByListForPage(
					sql,
					RgctBill.class,
					Integer.valueOf(var108006.getCurrentPage()),
					Integer.valueOf(var108006.getPageSize()),
					list);
			var108006.setTotalRows(Integer.toString(session.account("select count(*) from ("+sql+")")));
		} catch (SQLException e) {
			CommonLog.getCommonLogCache().errorMessage("查询数据库失败", e);
			throw new BizAppException(IErrorNo.ERR_DBERR, "查询数据库失败");
		}*/
			if(resultList.size() <= 0){
	    		throw new BizAppException(IErrorNo.ERR_NORECORD, "查询无记录");
	    	}else{
	    		for (BillFlowQueryResult billList:resultList) {
	    			Var108006InfoBean bean=new Var108006InfoBean();
	    			bean.setRgctId(billList.getRgctId());
	    			bean.setBillNo(billList.getBillNo());
	    			bean.setConferNo(billList.getConferNo());
	    			bean.setInvoiceNo(billList.getInvoiceNo());
	    			bean.setBillType(billList.getBillType());
	    			bean.setBillClass(billList.getBillClass());
	    			bean.setIssueDt(billList.getIssueDt());
	    			bean.setDueDt(billList.getDueDt());
	    			bean.setAcceptDt(billList.getAcceptorDate());
	    			bean.setBillMoney(Double.toString(billList.getBillMoney()));
	    			bean.setRemitter(billList.getRemitter());
	    			bean.setRemitterAcct(billList.getRemitterAcct());
	    			bean.setRemitterBank(billList.getRemitterBankName());
	    			bean.setRemitterBankNo(billList.getRemitterBankNo());
	    			bean.setPayee(billList.getPayeeName());
	    			bean.setPayeeAcct(billList.getPayeeAcct());
	    			bean.setPayeeBank(billList.getPayeeBankName());
	    			bean.setPayeeBankNo(billList.getPayeeBankNo());
	    			bean.setAcceptor(billList.getAcceptor());
	    			bean.setAcceptorAcct(billList.getAcceptorAcct());
	    			bean.setAcceptorBank(billList.getAcceptorBankName());
	    			bean.setAcceptorBankNo(billList.getAcceptorBankNo());
	    			bean.setBanEndorsementMark(billList.getBanEndorsementMark());
	    			bean.setSettlementMark(billList.getSettlementMark());
	    			bean.setApplicantName(billList.getFromName());
	    			bean.setApplicantAcctNo(billList.getFromAcctNo());
	    			bean.setApplicantBankNo(billList.getFromBankNo());
	    			bean.setReceiverName(billList.getToName());
	    			bean.setReceiverAcctNo(billList.getToAcctNo());
	    			bean.setReceiverBankNo(billList.getToBankNo());
	    			bean.setRemark(billList.getRemark());
	    			if(BillFlowConst.APPLY_STATUS_035.equals(billList.getStatus())||BillFlowConst.SIGN_STATUS_035.equals(billList.getStatus())){
	    				bean.setProcessResult("035清分失败");
	    			}else if(BillFlowConst.APPLY_STATUS_COMMIT.equals(billList.getStatus())){
	    				bean.setProcessResult("申请提交");
	    			}else if(BillFlowConst.APPLY_STATUS_FAIL.equals(billList.getStatus())){
	    				bean.setProcessResult("申请失败");
	    			}else if(BillFlowConst.APPLY_STATUS_WAIT_SIGN.equals(billList.getStatus())||BillFlowConst.SIGN_STATUS_WAIT.equals(billList.getStatus())){
	    				bean.setProcessResult("待签收");
	    			}else if(BillFlowConst.APPLY_STATUS_SIGN.equals(billList.getStatus())){
	    				bean.setProcessResult("对方已签收");
	    			}else if(BillFlowConst.APPLY_STATUS_REFUSE.equals(billList.getStatus())){
	    				bean.setProcessResult("对方已拒绝");
	    			}else if(BillFlowConst.APPLY_STATUS_CANCEL_S.equals(billList.getStatus())){
	    				bean.setProcessResult("申请撤销成功");
	    			}else if(BillFlowConst.APPLY_STATUS_CANCEL_E.equals(billList.getStatus())){
	    				bean.setProcessResult("申请撤销失败");
	    			}else if(BillFlowConst.APPLY_STATUS_DESTRUCTION_S.equals(billList.getStatus())){
	    				bean.setProcessResult("撤票成功");
	    			}else if(BillFlowConst.APPLY_STATUS_DESTRUCTION_E.equals(billList.getStatus())){
	    				bean.setProcessResult("撤票失败");
	    			}else if(BillFlowConst.SIGN_STATUS_S.equals(billList.getStatus())){
	    				bean.setProcessResult("签收成功");
	    			}else if(BillFlowConst.SIGN_STATUS_E.equals(billList.getStatus())){
	    				bean.setProcessResult("签收失败");
	    			}else if(BillFlowConst.SIGN_STATUS_REFUSE_S.equals(billList.getStatus())){
	    				bean.setProcessResult("拒绝成功");
	    			}else if(BillFlowConst.SIGN_STATUS_REFUSE_E.equals(billList.getStatus())){
	    				bean.setProcessResult("拒绝失败");
	    			}else if(BillFlowConst.APPLY_STATUS_CANCEL.equals(billList.getStatus())){
	    				bean.setProcessResult("申请已撤销");
	    			}
	    			result.add(bean);
	    		}
	    	}
        } catch(Exception e){
        	throw new BizAppException(IErrorNo.ERR_DBERR, "查询数据库失败");
        }
		var108006.setResult(result);
    	
    }
    
    /**
     * 应答包处理
     * @param context
     * @param Var107001
     * @throws BizAppException
     */
    protected void PackAnswer(Context context, Var108006 var108006) throws BizAppException{
    	TransInfo trans=(TransInfo)ContextUtil.getContextAttribute(context, IConstants.TRANS_INFO);
        ProResult proResult=new ProResult();
        proResult.setType("S");
        proResult.setExSerial(trans.getExSerial());
        proResult.setFunctionId(trans.getFunctionId());
        List<Var108006InfoBean> infoList=var108006.getResult();
        Var108006Result result=new Var108006Result();
        result.setProResult(proResult);
        result.setResult(infoList);
        
        Map<String, Class> clazzMap = new HashMap<String, Class>();
        clazzMap.put("Document", Var108006Result.class);
        clazzMap.put("info", Var108006InfoBean.class);
       
        String resp=XmlBeanUtil.bean2xml(clazzMap, result);     
        ContextUtil.setResponseData(context, resp);
    }
	
	public String getTransName() {		
		return "电子票据交易类查询";
	}
	
	public String getTransVersion() {		
		return "2.0.0.1";
	}

	public String getServiceId() {
		return "108006";
	}


}
