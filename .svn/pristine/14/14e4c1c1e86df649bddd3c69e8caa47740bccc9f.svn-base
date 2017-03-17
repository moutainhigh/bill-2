/********************************************
 * 文件名称: T101002Service.java
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
package com.herongtech.online.trans.trans101002;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.herongtech.commons.tools.StringUtils;
import com.herongtech.console.core.constant.IConstants;
import com.herongtech.console.core.constant.IErrorNo;
import com.herongtech.console.core.util.XmlBeanUtil;
import com.herongtech.context.Context;
import com.herongtech.context.util.ContextUtil;
import com.herongtech.db.impl.session.DBFactory;
import com.herongtech.db.interfaces.IDB;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.log.CommonLog;
import com.herongtech.online.trans.OnlineBaseService;
import com.herongtech.rc.domain.bean.RcBaseSearchBean;
import com.herongtech.rc.domain.bean.RgctBill;
import com.herongtech.rc.service.RcServiceFactory;
import com.herongtech.xmlchannel.pkg.TransInfo;

/**
 *  可提示收票服务的票据查询
 */
public class T101002Service extends OnlineBaseService {

	 
	/**
	 * 入口方法
	 * @param context
	 */
	protected void action(Context context) throws Exception {
		Var101002 transVar = new Var101002();
		
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
	 * @param Var101002
	 * @throws BizAppException
	 */
	protected void CheckData(Context context, Var101002 Var101002) throws BizAppException{
String request = (String)ContextUtil.getRequestData(context);
		
		Map<String, Class> clazzMap = new HashMap<String, Class>();
		clazzMap.put("Document", Var101002.class);
		Var101002 temp = (Var101002) XmlBeanUtil.xml2Bean(clazzMap,request);
		if (StringUtils.isEmpty(temp.getCustAccount())){
			throw new BizAppException(IErrorNo.ERR_CUSTACCOUNTNULL, "客户账号未上送");
		}
		
		org.springframework.beans.BeanUtils.copyProperties(temp, Var101002);

	}
	
	/**
	 * 查询处理
	 * @param context
	 * @param Var101002
	 * @throws BizAppException
	 */
    protected void ToLocal(Context context, Var101002 Var101002) throws BizAppException{
    	RcBaseSearchBean rcbean = new RcBaseSearchBean();
    	rcbean.setBillClass("2");
    	rcbean.setHoldAcctNo(Var101002.getCustAccount());
    	try {
    	IDB session = DBFactory.getDB();
    	
    	int startIndex = Var101002.getCurrentPage();//记录开始值
    	String sql = RcServiceFactory.getRcBillNotifyService().queryIssuance(rcbean);
    	//测试 临时用sql="select billinfo.*,billhist.* from trgct_bill_info billinfo, trgct_bill_hist billhist where billinfo.hist_id=billhist.hist_id and billinfo.bill_class = '1'  and billhist.hold_acct_no ='622688213855776' and ( billhist.cur_status='F_11' )  and billinfo.del_flag='0'";
    	List<RgctBill> rgctbilllist = null;
    	List<Object> rgctlist = new ArrayList<Object>();
    	List<Var101002Bean> var101002beanlist = new ArrayList<Var101002Bean>();
    	rgctbilllist = session.getBeanListByListForPage(sql, RgctBill.class, startIndex, Var101002.getPageSize(), rgctlist);
    	
    	 for (int i = 0; i < rgctbilllist.size(); i++) {
    		session.beginTransaction();
 	    	Var101002Bean bean = new Var101002Bean();
 	    	org.springframework.beans.BeanUtils.copyProperties(rgctbilllist.get(i).getInfo(), bean);
 	    	org.springframework.beans.BeanUtils.copyProperties(rgctbilllist.get(i).getHist(), bean);
 	    	bean.setAcceptDt(rgctbilllist.get(i).getInfo().getAcceptorDate());
 	    	bean.setPayee(rgctbilllist.get(i).getInfo().getPayeeName());
 	    	bean.setPayeeBank(rgctbilllist.get(i).getInfo().getPayeeBankName());
 	    	bean.setAcceptorBank(rgctbilllist.get(i).getInfo().getAcceptorBankName());
 	    	bean.setBanEndorsementMark(rgctbilllist.get(i).getInfo().getInfoForbidFlag());
 	    	bean.setRemitterBank(rgctbilllist.get(i).getInfo().getRemitterBankName()); 
 	    	bean.setRgctId(rgctbilllist.get(i).getHist().getRgctId());
 	    	var101002beanlist.add(bean);
 	    	session.endTransaction();
 		}	
		
//    	if(resultData.getRowCount() <= 0){
//    		throw new BizAppException(IErrorNo.ERR_DBERR, "");
//    	}

    	Var101002.setListbean(var101002beanlist);
    	Var101002.setRetNum(var101002beanlist.size());
			Var101002.setTotRecNum(
					session.account("select count(*) from ("+sql+")"));
		} catch (SQLException e) {
			CommonLog.getCommonLogCache().errorMessage("查询记录数失败", e);
			throw new BizAppException(IErrorNo.ERR_DBERR, "查询记录数失败");
		}
    }
    
    /**
     * 应答包处理
     * @param context
     * @param Var101002
     * @throws BizAppException
     */
    protected void PackAnswer(Context context, Var101002 Var101002) throws BizAppException{
    	 TransInfo trans=(TransInfo)ContextUtil.getContextAttribute(context, IConstants.TRANS_INFO);
    	 List<Var101002Bean> infoList=Var101002.getListbean();

    	 Var101002Result proResult=new Var101002Result();
    	 proResult.setType("S");
    	 proResult.setExSerial(trans.getExSerial());
    	 proResult.setFunctionId(trans.getFunctionId());
    	 proResult.setCurrentPage(Var101002.getCurrentPage());//当前页
    	 proResult.setTotalRows(Var101002.getTotRecNum());//总条数
    	 proResult.setRetNum(Var101002.getRetNum());//返回条数
         proResult.setListbean(infoList);
               
         Map<String, Class> clazzMap = new HashMap<String, Class>();
         clazzMap.put("Document", Var101002Result.class);
         clazzMap.put("info", Var101002Bean.class);
        
         String resp=XmlBeanUtil.bean2xml(clazzMap, proResult);     
         ContextUtil.setResponseData(context, resp);

    }
	
	public String getTransName() {		
		return "可提示收票服务的票据查询";
	}
	
	public String getTransVersion() {		
		return "2.0.0.1";
	}

	public String getServiceId() {
		return "101002";
	}
}
