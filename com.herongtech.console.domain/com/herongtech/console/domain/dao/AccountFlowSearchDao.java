package com.herongtech.console.domain.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.herongtech.console.core.common.json.Page;
import com.herongtech.console.core.common.search.QueryCondition;
import com.herongtech.console.core.util.MoneyUtils;
import com.herongtech.console.domain.acct.bean.AcctFlow;
import com.herongtech.console.domain.acct.bean.AcctFlowInfo;
import com.herongtech.console.domain.acct.bean.AcctFlowSearchBean;
import com.herongtech.data.interfaces.data.IData;
import com.herongtech.db.impl.session.DBFactory;
import com.herongtech.db.interfaces.IDB;


public class AccountFlowSearchDao {
	
	/**
	 * 功能描述：根据条件查询批次列表(账务流水用)
	 * @param page
	 * @param query
	 * @return
	 * @throws SQLException
	 */
	public List<AcctFlow> getAccountFlowCondition(Page page,AcctFlowSearchBean query) throws SQLException{
		IDB session = DBFactory.getDB();
		String baseSql="select distinct acct.* from tacct_flow acct,tacct_flow_info bill where  bill.af_id = acct.af_id";
		 QueryCondition qc=new QueryCondition();
	     try {
	         qc.initFromSearchBean(query);
	     } catch (Exception e) {
	         e.printStackTrace();
	     }
		String allsql=qc.getAllSqlString(baseSql);
		//分页开始位置
		int startIndex = (page.getCurrentPage()-1)*page.getShowCount()+1;
		if(startIndex<0)
			startIndex = 0;
		int count =0;
		List<AcctFlow> applyList=null;
		// 获得并返回本次查询的总条数
		if(qc.getParameterValues() ==null ||qc.getParameterValues().size()==0){//什么条件都不带的情况
		    count = session.account(qc.getCountSql("distinct acct.af_id"));
		    applyList=session.getObjectListForPage(allsql,AcctFlow.class,startIndex, page.getShowCount());
		}else{
		    count = session.accountByList(qc.getCountSql("distinct acct.af_id"), qc.getParameterValues()); 
		    applyList=session.getObjectListByListForPage(allsql,AcctFlow.class, startIndex, page.getShowCount(), qc.getParameterValues());
		}
		System.out.println(allsql);
		page.setTotalResult(count);
		//票据张数，与票据金额汇总
		sumApplyInfo(applyList, query);
		return applyList;
	}	
/**
 * 票据张数，与票据金额汇总
 * @param applyList
 * @param query
 * @throws SQLException 
 */
public void sumApplyInfo(List<AcctFlow> applyList,AcctFlowSearchBean query) throws SQLException{
    if(applyList == null || applyList.size() == 0) return;
    IDB session = DBFactory.getDB();
    //批次汇总票据张数，与票据金额sql
    StringBuilder sb = new StringBuilder("select count(bill.af_id),sum(bill.bill_amonut),sum(bill.settlement_amt),sum(bill.settlement_intrst)");
    sb.append(" from tacct_flow_info bill where bill.af_id = ? ");
    List<Object> param = new ArrayList<Object>();
    //占第一位 为第一个问号赋值
    param.add("");
    for(AcctFlow apply:applyList){
    	//删除第一位 为第一个问号赋值
    	param.remove(0);
    	// 为第一个问号赋值
    	param.add(0, apply.getAfId());
	    IData data=session.getDataByList(sb.toString(), param);
	    apply.setTotalAmount(Double.parseDouble(MoneyUtils.moneyToString(data.getDouble(2))));
	    apply.setSettlementMoney(Double.parseDouble(MoneyUtils.moneyToString(data.getDouble(3))));
	    apply.setSettlementInterest(Double.parseDouble(MoneyUtils.moneyToString(data.getDouble(4))));
    }
}
/**
 * 根据主键查询批次信息
 * @param 
 * @return
 */
public AcctFlow getAcctFlowForAfID(AcctFlowSearchBean query) throws SQLException{
	IDB session = DBFactory.getDB();
	AcctFlow obj = (AcctFlow)session.getObject("select * from tacct_flow where af_id=?",AcctFlow.class,query.getAfId());
	if(query == null) return obj;
	sumApplyInfo(obj, query);
	return obj;
}
public void sumApplyInfo(AcctFlow apply,AcctFlowSearchBean query) throws SQLException{
    if(apply == null) return;
    IDB session = DBFactory.getDB();
    //批次汇总票据张数，与票据金额sql
    StringBuilder sb = new StringBuilder("select count(bill.af_id)||'',");
    sb.append("sum(bill.bill_amonut),sum(bill.settlement_amt),sum(bill.settlement_intrst) from tacct_flow_info bill where bill.af_id = ? ");
    List<Object> param = new ArrayList<Object>();
    //占第一位 为第一个问号赋值
    param.add("");
    //删除第一位 为第一个问号赋值
    param.remove(0);
    // 为第一个问号赋值
    param.add(0, apply.getAfId());
    IData data=session.getDataByList(sb.toString(), param);
    System.out.println(sb.toString());
    apply.setTotalAmount(Double.parseDouble(MoneyUtils.moneyToString(data.getDouble(2))));
    apply.setSettlementMoney(Double.parseDouble(MoneyUtils.moneyToString(data.getDouble(3))));
    apply.setSettlementInterest(Double.parseDouble(MoneyUtils.moneyToString(data.getDouble(4))));
}
/**
 * 查询批次下清单
 * @param page
 * @param query
 * @return
 * @throws SQLException
 */
public List<AcctFlowInfo> getAcctFlowInfoListForBatch(Page page,AcctFlowSearchBean query) throws SQLException {
	IDB session = DBFactory.getDB();
	String baseSql="select bill.* from tacct_flow_info bill";
	//分页开始位置
	int startIndex = page.getCurrentResult();
	 QueryCondition qc=new QueryCondition();
	 try {
         qc.initFromSearchBean(query);
     } catch (Exception e) {
         e.printStackTrace();
     }
	 String sql=qc.getAllSqlString(baseSql);
     System.out.println(sql+qc.getParameterValues());//打印sql语句
	// 获得并返回本次查询的总条数
	int count = session.accountByList(qc.getCountSql("bill.af_Id"), qc.getParameterValues());
	page.setTotalResult(count);
	return session.getObjectListByListForPage(sql,AcctFlowInfo.class, startIndex, page.getShowCount(), qc.getParameterValues());
}



}
