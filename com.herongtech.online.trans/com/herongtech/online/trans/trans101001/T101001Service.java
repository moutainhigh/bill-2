/********************************************
 * 文件名称: T101001Service.java
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
package com.herongtech.online.trans.trans101001;

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
import com.herongtech.xmlchannel.pkg.ProResult;
import com.herongtech.xmlchannel.pkg.TransInfo;

/**
 *  可执行出票登记的票据查询
 */
public class T101001Service extends OnlineBaseService {

	 
	/**
	 * 入口方法
	 * @param context
	 */
	protected void action(Context context) throws Exception {
		Var101001 transVar = new Var101001();
		
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
	 * @param Var101001
	 * @throws BizAppException
	 */
	protected void CheckData(Context context, Var101001 Var101001) throws BizAppException{
		
		String request = (String)ContextUtil.getRequestData(context);
		
		Map<String, Class> clazzMap = new HashMap<String, Class>();
        clazzMap.put("Document", Var101001.class);
        Var101001 temp=(Var101001)XmlBeanUtil.xml2Bean(clazzMap, request);
		if (StringUtils.isEmpty(temp.getCustAccount())){
			throw new BizAppException(IErrorNo.ERR_CUSTACCOUNTNULL, "客户账号未上送");
		}
		org.springframework.beans.BeanUtils.copyProperties(temp, Var101001);
	}
        
		
	
	/**
	 * 查询处理
	 * @param context
	 * @param Var101001
	 * @throws BizAppException
	 */
    protected void ToLocal(Context context, Var101001 Var101001) throws BizAppException{
    	//获取记录开始值
    	try {
    	RcBaseSearchBean rcSb = new RcBaseSearchBean();
    	rcSb.setBillClass("2");
    	rcSb.setHoldAcctNo(Var101001.getCustAccount());
    	IDB session = DBFactory.getDB();
    	session.beginTransaction();
    	int startIndex = Var101001.getCurrentPage();//记录开始值
    	String sql = RcServiceFactory.getRcRegBillService().getRegBillSql(rcSb);
    	//测试 临时sql="select billinfo.*,billhist.* from trgct_bill_info billinfo, trgct_bill_hist billhist where billinfo.hist_id=billhist.hist_id and billinfo.bill_class = '1'  and billhist.hold_acct_no ='622688213855776' and ( billhist.cur_status='F_11' )  and billinfo.del_flag='0'";
    	List<RgctBill> rgctbilllist = null;
    	List<Object> rgctlist = new ArrayList<Object>();
    	List<Var101001Bean> var101001beanlist = new ArrayList<Var101001Bean>();
    	rgctbilllist = session.getBeanListByListForPage(sql, RgctBill.class, startIndex, Var101001.getPageSize(), rgctlist);
	    for (int i = 0; i < rgctbilllist.size(); i++) {
	    	Var101001Bean bean = new Var101001Bean();
	    	org.springframework.beans.BeanUtils.copyProperties(rgctbilllist.get(i).getInfo(), bean);
	    	org.springframework.beans.BeanUtils.copyProperties(rgctbilllist.get(i).getHist(), bean);
	    	bean.setAcceptDt(rgctbilllist.get(i).getInfo().getAcceptorDate());
	    	bean.setPayee(rgctbilllist.get(i).getInfo().getPayeeName());
	    	bean.setPayeeBank(rgctbilllist.get(i).getInfo().getPayeeBankName());
	    	bean.setAcceptorBank(rgctbilllist.get(i).getInfo().getAcceptorBankName());
	    	bean.setBanEndorsementMark(rgctbilllist.get(i).getInfo().getInfoForbidFlag());
	    	bean.setRemitterBank(rgctbilllist.get(i).getInfo().getRemitterBankName()); 
	    	bean.setRgctId(rgctbilllist.get(i).getHist().getRgctId());
	    	var101001beanlist.add(bean);
		}
    	//Var101001.setResultData(resultData);
	    Var101001.setVar101001beanlist(var101001beanlist);
	    Var101001.setRetNum(var101001beanlist.size());
		Var101001.setTotRecNum(
					session.account("select count(*) from ("+sql+")"));
		session.endTransaction();
		} catch (SQLException e) {
			CommonLog.getCommonLogCache().errorMessage("查询记录数失败", e);
			throw new BizAppException(IErrorNo.ERR_DBERR, "查询记录数失败");
		}
    }
    
    /**
     * 应答包处理
     * @param context
     * @param Var101001
     * @throws BizAppException
     */
    protected void PackAnswer(Context context, Var101001 Var101001) throws BizAppException{
    	 TransInfo trans=(TransInfo)ContextUtil.getContextAttribute(context, IConstants.TRANS_INFO);
    	 List<Var101001Bean> infoList=Var101001.getVar101001beanlist();

    	 
    	 Var101001Result proResult=new Var101001Result();
    	 proResult.setType("S");
    	 proResult.setExSerial(trans.getExSerial());
    	 proResult.setFunctionId(trans.getFunctionId());
    	 proResult.setCurrentPage(Var101001.getCurrentPage());//当前页
    	 proResult.setTotalRows(Var101001.getTotRecNum());//总条数
    	 proResult.setRetNum(Var101001.getRetNum());//返回条数
         proResult.setVar101001bean(infoList);
               
         Map<String, Class> clazzMap = new HashMap<String, Class>();
         clazzMap.put("Document", Var101001Result.class);
         clazzMap.put("info", Var101001Bean.class);
        
         String resp=XmlBeanUtil.bean2xml(clazzMap, proResult);     
         ContextUtil.setResponseData(context, resp);

    }
	
	public String getTransName() {		
		return "可执行出票登记的票据查询";
	}
	
	public String getTransVersion() {		
		return "2.0.0.1";
	}

	public String getServiceId() {
		return "101001";
	}
}
