/********************************************
* 文件名称: BillFlowInfoDao.java
* 系统名称: 合融基础技术平台V2.0
* 模块名称:
* 软件版权: 北京合融科技有限公司
* 功能说明: 
* 系统版本: 2.0.0.1
* 开发人员: 技术平台组
* 开发时间:  
* 审核人员:
* 相关文档:
* 修改记录: 修改日期    修改人员    修改说明
*********************************************/
package com.herongtech.rc.domain.dao;

import java.lang.*;
import java.math.*;
import com.herongtech.db.impl.session.DBFactory;
import com.herongtech.db.interfaces.IDB;

import java.sql.SQLException;
import java.util.List;

import com.herongtech.commons.tools.BeanFormat;
import com.herongtech.console.core.common.json.Page;
import com.herongtech.console.core.common.search.QueryCondition;
import com.herongtech.rc.domain.bean.BillFlowInfo;
import com.herongtech.rc.domain.bean.BillFlowInfoSearchBean;
import com.herongtech.rc.domain.bean.BillFlowQueryResult;
import com.herongtech.rc.domain.bean.RgctBill;
public class BillFlowInfoDao{

public int addBillFlowInfo(BillFlowInfo obj) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("insert into tbill_flow_info(id,bill_no,rgct_id,hist_id,from_name,from_cust_no,from_acct_no,from_bank_no,to_name,to_cust_no,to_acct_no,to_bank_no,bill_status,hold_cust_no,hold_cust_name,hold_acct_no,hold_bank_no,create_dt,create_time,oper_result,draft_no_req,signup_date,signup_time,signup_mark,signup_info,result_msg,is_online,in_acct_no,in_bank_no,remark,oper_type,remitter,apply_date,apply_time,busi_type,req_sid,trans_date,trans_time,bill_type,bill_money,issue_dt,due_dt,acceptor,acceptor_acct,acceptor_bank_name,ban_endorsement_mark,print_staus,status,transfor,cur_status_string)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
	obj.getId(),obj.getBillNo(),obj.getRgctId(),obj.getHistId(),obj.getFromName(),
	obj.getFromCustNo(),obj.getFromAcctNo(),obj.getFromBankNo(),obj.getToName(),
	obj.getToCustNo(),obj.getToAcctNo(),obj.getToBankNo(),obj.getBillStatus(),
	obj.getHoldCustNo(),obj.getHoldCustName(),obj.getHoldAcctNo(),obj.getHoldBankNo(),
	obj.getCreateDt(),obj.getCreateTime(),obj.getOperResult(),obj.getDraftNoReq(),
	obj.getSignupDate(),obj.getSignupTime(),obj.getSignupMark(),obj.getSignupInfo(),
	obj.getResultMsg(),obj.getIsOnline(),obj.getInAcctNo(),obj.getInBankNo(),
	obj.getRemark(),obj.getOperType(),obj.getRemitter(),obj.getApplyDate(),
	obj.getApplyTime(),obj.getBusiType(),obj.getReqSid(),obj.getTransDate(),
	obj.getTransTime(),obj.getBillType(),obj.getBillMoney(),obj.getIssueDt(),
	obj.getDueDt(),obj.getAcceptor(),obj.getAcceptorAcct(),obj.getAcceptorBankName(),
	obj.getBanEndorsementMark(),obj.getPrintStaus(),obj.getStatus(),obj.getTransfor(),
	obj.getCurStatusString());
}

public int deleteBillFlowInfo(BillFlowInfo obj) throws SQLException {
	IDB session = DBFactory.getDB();
	return session.execute("delete from tbill_flow_info where id=?",obj.getId());
}

public int deleteBillFlowInfo(String id) throws SQLException {
	IDB session = DBFactory.getDB();
	return session.execute("delete from tbill_flow_info where id=?",id);
}

public int modifyBillFlowInfo(BillFlowInfo obj) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("update tbill_flow_info set bill_no=?,rgct_id=?,hist_id=?,from_name=?,from_cust_no=?,from_acct_no=?,from_bank_no=?,to_name=?,to_cust_no=?,to_acct_no=?,to_bank_no=?,bill_status=?,hold_cust_no=?,hold_cust_name=?,hold_acct_no=?,hold_bank_no=?,create_dt=?,create_time=?,oper_result=?,draft_no_req=?,signup_date=?,signup_time=?,signup_mark=?,signup_info=?,result_msg=?,is_online=?,in_acct_no=?,in_bank_no=?,remark=?,oper_type=?,remitter=?,apply_date=?,apply_time=?,busi_type=?,req_sid=?,trans_date=?,trans_time=?,bill_type=?,bill_money=?,issue_dt=?,due_dt=?,acceptor=?,acceptor_acct=?,acceptor_bank_name=?,ban_endorsement_mark=?,print_staus=?,status=?,transfor=?,cur_status_string=? where id=?",
	obj.getBillNo(),obj.getRgctId(),obj.getHistId(),obj.getFromName(),
	obj.getFromCustNo(),obj.getFromAcctNo(),obj.getFromBankNo(),obj.getToName(),
	obj.getToCustNo(),obj.getToAcctNo(),obj.getToBankNo(),obj.getBillStatus(),
	obj.getHoldCustNo(),obj.getHoldCustName(),obj.getHoldAcctNo(),obj.getHoldBankNo(),
	obj.getCreateDt(),obj.getCreateTime(),obj.getOperResult(),obj.getDraftNoReq(),
	obj.getSignupDate(),obj.getSignupTime(),obj.getSignupMark(),obj.getSignupInfo(),
	obj.getResultMsg(),obj.getIsOnline(),obj.getInAcctNo(),obj.getInBankNo(),
	obj.getRemark(),obj.getOperType(),obj.getRemitter(),obj.getApplyDate(),
	obj.getApplyTime(),obj.getBusiType(),obj.getReqSid(),obj.getTransDate(),
	obj.getTransTime(),obj.getBillType(),obj.getBillMoney(),obj.getIssueDt(),
	obj.getDueDt(),obj.getAcceptor(),obj.getAcceptorAcct(),obj.getAcceptorBankName(),
	obj.getBanEndorsementMark(),obj.getPrintStaus(),obj.getStatus(),obj.getTransfor(),
	obj.getCurStatusString(),obj.getId());
}

public int modifyBillFlowInfo(BillFlowInfo obj,String id) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("update tbill_flow_info set bill_no=?,rgct_id=?,hist_id=?,from_name=?,from_cust_no=?,from_acct_no=?,from_bank_no=?,to_name=?,to_cust_no=?,to_acct_no=?,to_bank_no=?,bill_status=?,hold_cust_no=?,hold_cust_name=?,hold_acct_no=?,hold_bank_no=?,create_dt=?,create_time=?,oper_result=?,draft_no_req=?,signup_date=?,signup_time=?,signup_mark=?,signup_info=?,result_msg=?,is_online=?,in_acct_no=?,in_bank_no=?,remark=?,oper_type=?,remitter=?,apply_date=?,apply_time=?,busi_type=?,req_sid=?,trans_date=?,trans_time=?,bill_type=?,bill_money=?,issue_dt=?,due_dt=?,acceptor=?,acceptor_acct=?,acceptor_bank_name=?,ban_endorsement_mark=?,print_staus=?,status=?,transfor=?,cur_status_string=? where id=?",
	obj.getBillNo(),obj.getRgctId(),obj.getHistId(),obj.getFromName(),
	obj.getFromCustNo(),obj.getFromAcctNo(),obj.getFromBankNo(),obj.getToName(),
	obj.getToCustNo(),obj.getToAcctNo(),obj.getToBankNo(),obj.getBillStatus(),
	obj.getHoldCustNo(),obj.getHoldCustName(),obj.getHoldAcctNo(),obj.getHoldBankNo(),
	obj.getCreateDt(),obj.getCreateTime(),obj.getOperResult(),obj.getDraftNoReq(),
	obj.getSignupDate(),obj.getSignupTime(),obj.getSignupMark(),obj.getSignupInfo(),
	obj.getResultMsg(),obj.getIsOnline(),obj.getInAcctNo(),obj.getInBankNo(),
	obj.getRemark(),obj.getOperType(),obj.getRemitter(),obj.getApplyDate(),
	obj.getApplyTime(),obj.getBusiType(),obj.getReqSid(),obj.getTransDate(),
	obj.getTransTime(),obj.getBillType(),obj.getBillMoney(),obj.getIssueDt(),
	obj.getDueDt(),obj.getAcceptor(),obj.getAcceptorAcct(),obj.getAcceptorBankName(),
	obj.getBanEndorsementMark(),obj.getPrintStaus(),obj.getStatus(),obj.getTransfor(),
	obj.getCurStatusString(),id);
}

public BillFlowInfo getBillFlowInfo(String id) throws SQLException {
	IDB session = DBFactory.getDB();
	BillFlowInfo obj = (BillFlowInfo)session.getObject("select * from tbill_flow_info where id=?",BillFlowInfo.class,id);
	return obj;
}
public List<BillFlowInfo> getBillFlow(String reqSid, RgctBill bill ,String operType) throws SQLException {
	IDB session = DBFactory.getDB();
	String sql="select * from tbill_flow_info where req_sid='"+reqSid+"' and rgct_id='"+bill.getInfo().getId()+"'and oper_type='"+operType+"' " ;
	List<BillFlowInfo> list = session.getObjectList(sql, BillFlowInfo.class);
	return list;
}


//public List<EcdsQueryResult> queryRgctBillInfoByBillFlowInfoSearchBean(BillFlowInfoSearchBean billFlowInfoSearchBean,
//		Page page) throws DAOException {
//	String queryString = "SELECT rgctBillInfo, billFlowInfo FROM RgctBillInfo rgctBillInfo, BillFlowInfo billFlowInfo WHERE rgctBillInfo.id = billFlowInfo.rgctId ";
//    billFlowInfoSearchBean.appendOrder(new OrderBean("id", Boolean.FALSE));
//    if(billFlowInfoSearchBean.getEndDate() != null) {
//    	Date date  = this.getDate(billFlowInfoSearchBean.getEndDate(), 1);
//    	billFlowInfoSearchBean.setEndDate(date);
//    }
//    billFlowInfoSearchBean.addHibPropretyMapping("beginDate", "createTime"); 
//    billFlowInfoSearchBean.addSpecialOpertion("beginDate", BaseSearchBean.MORE_AND_EQUAL);
//    billFlowInfoSearchBean.addHibPropretyMapping("endDate", "createTime");
//    billFlowInfoSearchBean.addSpecialOpertion("endDate", BaseSearchBean.LESS_AND_EQUAL);
//	QueryCondition queryCondition = new QueryCondition();
//	try {
//		billFlowInfoSearchBean.setDfaultSrchTbAliasName("billFlowInfo");
//		queryCondition.initFromSearchBean(billFlowInfoSearchBean);
//		page.setKeyQueryId("rgctBillInfo.id");
//		page.setKeyQueryMoney("rgctBillInfo.billMoney");
//		List list =  this.getListByPage2(page, queryCondition, queryString);
//		List<EcdsQueryResult> billList = new ArrayList<EcdsQueryResult>();
//		for (int i = 0; i < list.size(); i++) {
//			Object[] obj = (Object[])list.get(i);
//			RgctBillInfo orig = (RgctBillInfo) obj[0];
//			EcdsQueryResult result = WebBankUtil.createEcdsQueryResultByInfo(orig);
//			BillFlowInfo flowInfo = (BillFlowInfo) obj[1];
//			result.setApplyDate(flowInfo.getCreateTime());
//			if(flowInfo.getDraftNoReq() != null && (flowInfo.getDraftNoReq()).startsWith("031")) {
//				if("1".equals(flowInfo.getOperResult())) {
//					result.setProcessResult("已签收");
//				}else{
//					result.setProcessResult("已拒绝");
//				}
//			} else{
//				if("1".equals(flowInfo.getOperResult())) {
//					result.setProcessResult("成功");
//				}else{
//					result.setProcessResult("失败");
//				}
//			}
//			result.setRemark(flowInfo.getRemark());
//			result.setSettlementMark(flowInfo.getSettlementMark());
//			result.setApplicantAcctNo(flowInfo.getFromAcctNo());
//			result.setApplicantBankNo(flowInfo.getFromBankNo());
//			result.setApplicantName(flowInfo.getFromName());
//			result.setReceiverAcctNo(flowInfo.getToAcctNo());
//			result.setReceiverBankNo(flowInfo.getToBankNo());
//			result.setReceiverName(flowInfo.getToName());
//			result.setInAcctNo(flowInfo.getInAcctNo());
//			result.setInBankNo(flowInfo.getInBankNo());
//			billList.add(result);
//		}
//		
//		return billList;
//	} catch (Exception e) {
//		throw new DAOException(e);
//	}
//}

/*public List<BillFlowInfo> getBillFlowListBySearchBean(BillFlowSearchBean query)throws SQLException{
	IDB session = DBFactory.getDB();
	String baseSql = "select distinct flow.* from tbill_flow_info flow";
	QueryCondition qc = new QueryCondition();
	try {
	  qc.initFromSearchBean(query);
	} catch (Exception e) {
	  e.printStackTrace();
	}
	String allsql = qc.getAllSqlString(baseSql);
	List<BillFlowInfo> list=null;
	list = session.getObjectListByList(allsql, BillFlowInfo.class, qc.getParameterValues());
	return list;
	}*/

public List<BillFlowQueryResult> queryBillFlowResult(BillFlowInfoSearchBean query,Page page) throws Exception{
	/*StringBuilder builder = new StringBuilder("select distinct rbi.*,rbh.*,bfi.* from trgct_bill_info as rbi,trgct_bill_hist as rbh,tbill_flow_info as bfi where rbi.id = rbh.rgct_id and rbh.rgct_id = bfi.rgct_id");
	if (StringUtils.isNotBlank(query.getFromAcctNo())){
		builder.append(" and bfi.from_acct_no = '" + query.getFromAcctNo() + "' ");
	}
	if (StringUtils.isNotBlank(query.getBusiType())){
		builder.append(" and bfi.busi_type = '" + query.getBusiType() + "' ");
	}
	if (StringUtils.isNotBlank(query.getBeginDate())){
		builder.append(" and bfi.create_Time >= '" + query.getBeginDate() + "' ");
	}
	if (StringUtils.isNotBlank(query.getEndEndt())){
		builder.append(" and bfi.create_Time <= '" + query.getEndEndt() + "' ");
	}
	System.out.println(builder.toString());
	return builder.toString();*/
	String baseSql = "select distinct rbi.*,bfi.* from trgct_bill_info as rbi,tbill_flow_info as bfi where rbi.id = bfi.rgct_id";
	IDB session = DBFactory.getDB();
	QueryCondition qc = new QueryCondition();
	try {
	  qc.initFromSearchBean(query);
	  qc.getParameterValues();
	} catch (Exception e) {
	  e.printStackTrace();
	}
	String allsql = qc.getAllSqlString(baseSql);
	System.out.println(allsql);
	int startIndex = (page.getCurrentPage()-1)*page.getShowCount()+1;
	if(startIndex<0){
		startIndex = 0;
	}
	int count = session.accountByList(qc.getCountSql("distinct rbi.id"),qc.getParameterValues());
	page.setTotalResult(count);
	List<BillFlowQueryResult> resultList = session.getObjectListByListForPage(allsql,BillFlowQueryResult.class, startIndex, page.getShowCount(), qc.getParameterValues());
	return resultList;
	
}

}
