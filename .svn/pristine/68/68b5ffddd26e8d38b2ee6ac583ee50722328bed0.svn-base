/********************************************
 * 文件名称: T101005Service.java
 * 系统名称: 票据管理平台V2.0
 * 模块名称: 可执行出票登记的票据查询
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: 2.0.0.1
 * 开发人员: dq
 * 开发时间: 2016-08-9
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期    修改人员    修改说明
 *********************************************/
package com.herongtech.online.trans.trans101005;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.herongtech.commons.tools.StringUtils;
import com.herongtech.console.core.common.pubinfo.AnswerData;
import com.herongtech.console.core.constant.IConstants;
import com.herongtech.console.core.constant.IErrorNo;
import com.herongtech.console.core.constant.IFieldName;
import com.herongtech.console.core.util.XmlBeanUtil;
import com.herongtech.console.domain.bean.BusiDate;
import com.herongtech.console.service.ServiceFactory;
import com.herongtech.context.Context;
import com.herongtech.context.util.ContextUtil;
import com.herongtech.data.interfaces.data.IData;
import com.herongtech.db.impl.session.DBFactory;
import com.herongtech.db.interfaces.IDB;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.log.CommonLog;
import com.herongtech.online.trans.OnlineBaseService;
import com.herongtech.online.trans.trans101005.Var101005;
import com.herongtech.online.trans.trans101005.Var101005Bean;
import com.herongtech.online.trans.trans101005.Var101005Result;
import com.herongtech.rc.domain.bean.RcBaseSearchBean;
import com.herongtech.rc.domain.bean.RgctBill;
import com.herongtech.rc.domain.bean.RgctMethod;
import com.herongtech.rc.service.RcServiceFactory;
import com.herongtech.xmlchannel.pkg.TransInfo;

/**
 *  查询可撤消票据服务
 */
public class T101005Service extends OnlineBaseService {

	 
	/**
	 * 入口方法
	 * @param context
	 */
	protected void action(Context context) throws Exception {
		Var101005 transVar = new Var101005();
		
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
	 * @param var101005
	 * @throws BizAppException
	 */
	protected void CheckData(Context context, Var101005 var101005) throws BizAppException{
	String request = (String)ContextUtil.getRequestData(context);
		
		Map<String, Class> clazzMap = new HashMap<String, Class>();
        clazzMap.put("Document", Var101005.class);
        Var101005 temp=(Var101005)XmlBeanUtil.xml2Bean(clazzMap, request);
		if (StringUtils.isEmpty(temp.getCustAccount())){
			throw new BizAppException(IErrorNo.ERR_CUSTACCOUNTNULL, "客户账号未上送");
		}
		org.springframework.beans.BeanUtils.copyProperties(temp, var101005);
		
	}
	
	/**
	 * 查询处理
	 * @param context
	 * @param var101005
	 * @throws BizAppException
	 */
    protected void ToLocal(Context context, Var101005 var101005) throws BizAppException{
    	try {
        	RcBaseSearchBean rcSb = new RcBaseSearchBean();
        	rcSb.setBillClass("2");
        	rcSb.setHoldAcctNo(var101005.getCustAccount());
        	IDB session = DBFactory.getDB();
        	session.beginTransaction();
        	int startIndex = var101005.getCurrentPage();//记录开始值
        	String sql = RcServiceFactory.getRcRegBillService().queryRemitterBack(rcSb);
        	//临时测试sql="select billinfo.*,billhist.* from trgct_bill_info billinfo, trgct_bill_hist billhist where billinfo.hist_id=billhist.hist_id and billinfo.bill_class = '1'  and billhist.hold_acct_no ='622688213855776' and ( billhist.cur_status='F_11' )  and billinfo.del_flag='0'";
        	List<RgctBill> rgctbilllist = null;
        	List<Object> rgctlist = new ArrayList<Object>();
        	List<Var101005Bean> var101005beanlist = new ArrayList<Var101005Bean>();
        	rgctbilllist = session.getBeanListByListForPage(sql, RgctBill.class, startIndex, var101005.getPageSize(), rgctlist);
    	    for (int i = 0; i < rgctbilllist.size(); i++) {
    	    	Var101005Bean bean = new Var101005Bean();
    	    	org.springframework.beans.BeanUtils.copyProperties(rgctbilllist.get(i).getInfo(), bean);
    	    	org.springframework.beans.BeanUtils.copyProperties(rgctbilllist.get(i).getHist(), bean);
    	    	bean.setAcceptDt(rgctbilllist.get(i).getInfo().getAcceptorDate());
    	    	bean.setPayee(rgctbilllist.get(i).getInfo().getPayeeName());
    	    	bean.setPayeeBank(rgctbilllist.get(i).getInfo().getPayeeBankName());
    	    	bean.setAcceptorBank(rgctbilllist.get(i).getInfo().getAcceptorBankName());
    	    	bean.setBanEndorsementMark(rgctbilllist.get(i).getInfo().getInfoForbidFlag());
    	    	bean.setRemitterBank(rgctbilllist.get(i).getInfo().getRemitterBankName()); 
    	    	bean.setRgctId(rgctbilllist.get(i).getHist().getRgctId());
    	    	bean.setBillBeforeOwner(rgctbilllist.get(i).getHist().getBillBeforeOwner());                
    	    	var101005beanlist.add(bean);
    		}
        	//Var101005.setResultData(resultData);
    	    var101005.setListbean(var101005beanlist);
    	    var101005.setRetNum(var101005beanlist.size());
    	    var101005.setTotRecNum(session.account("select count(*) from ("+sql+")"));
    		session.endTransaction();
    		} catch (SQLException e) {
    			CommonLog.getCommonLogCache().errorMessage("查询记录数失败", e);
    			throw new BizAppException(IErrorNo.ERR_DBERR, "查询记录数失败");
    		}
    }
    
    /**
     * 应答包处理
     * @param context
     * @param Var101005
     * @throws BizAppException
     */
    protected void PackAnswer(Context context, Var101005 Var101005) throws BizAppException{
    	 TransInfo trans=(TransInfo)ContextUtil.getContextAttribute(context, IConstants.TRANS_INFO);
    	 List<Var101005Bean> infoList=Var101005.getListbean();

    	 
    	 Var101005Result proResult=new Var101005Result();
    	 proResult.setType("S");
    	 proResult.setExSerial(trans.getExSerial());
    	 proResult.setFunctionId(trans.getFunctionId());
    	 proResult.setCurrentPage(Var101005.getCurrentPage());//当前页
    	 proResult.setTotalRows(Var101005.getTotRecNum());//总条数
    	 proResult.setRetNum(Var101005.getRetNum());//返回条数
         proResult.setListbean(infoList);
               
         Map<String, Class> clazzMap = new HashMap<String, Class>();
         clazzMap.put("Document", Var101005Result.class);
         clazzMap.put("info", Var101005Bean.class);
        
         String resp=XmlBeanUtil.bean2xml(clazzMap, proResult);     
         ContextUtil.setResponseData(context, resp);
    }
	
	public String getTransName() {		
		return "查询可撤消票据服务";
	}
	
	public String getTransVersion() {		
		return "2.0.0.1";
	}

	public String getServiceId() {
		return "101005";
	}
}
