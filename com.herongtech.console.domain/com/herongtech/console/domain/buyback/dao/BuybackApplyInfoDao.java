/********************************************
* 文件名称: BuybackApplyInfoDao.java
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
package com.herongtech.console.domain.buyback.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.herongtech.commons.tools.BeanFormat;
import com.herongtech.console.core.common.json.Page;
import com.herongtech.console.core.common.search.OrderBean;
import com.herongtech.console.core.common.search.QueryCondition;
import com.herongtech.console.core.util.MoneyUtils;
import com.herongtech.console.domain.buyback.bean.BuybackApplyInfo;
import com.herongtech.console.domain.buyback.bean.BuybackSearchBean;
import com.herongtech.console.domain.sale.bean.SaleApplyInfo;
import com.herongtech.data.interfaces.data.IData;
import com.herongtech.db.impl.session.DBFactory;
import com.herongtech.db.interfaces.IDB;
public class BuybackApplyInfoDao{

public int addBuybackApplyInfo(BuybackApplyInfo obj) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("insert into tbuyback_apply_info(buyback_id,batch_no,aim_branch_no,bill_class,bill_type,inner_account,sale_dt,due_dt,is_dummy,is_inner,branch_no,prod_no,status,create_dt,create_time,oper_no,rate_type,is_online,buyback_open_dt,buyback_money,cust_name,buyback_due_dt,buyback_rate,rate,bill_storage_org,bill_storage_org_name,if_dummy,delay_type,delay_days,in_acct_no,in_acct_type,in_acct_name,prod_attr,sale_id,order_id,apply_status)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
	obj.getBuybackId(),obj.getBatchNo(),obj.getAimBranchNo(),obj.getBillClass(),obj.getBillType(),
	obj.getInnerAccount(),obj.getSaleDt(),obj.getDueDt(),obj.getIsDummy(),
	obj.getIsInner(),obj.getBranchNo(),obj.getProdNo(),obj.getStatus(),
	obj.getCreateDt(),obj.getCreateTime(),obj.getOperNo(),obj.getRateType(),
	obj.getIsOnline(),obj.getBuybackOpenDt(),obj.getBuybackMoney(),obj.getCustName(),
	obj.getBuybackDueDt(),obj.getBuybackRate(),obj.getRate(),obj.getBillStorageOrg(),
	obj.getBillStorageOrgName(),obj.getIfDummy(),obj.getDelayType(),obj.getDelayDays(),
	obj.getInAcctNo(),obj.getInAcctType(),obj.getInAcctName(),obj.getProdAttr(),
	obj.getSaleId(),obj.getOrderId(),obj.getApplyStatus());
}

public int deleteBuybackApplyInfo(BuybackApplyInfo obj) throws SQLException {
	IDB session = DBFactory.getDB();
	return session.execute("delete from tbuyback_apply_info where buyback_id=?",obj.getBuybackId());
}

public int deleteBuybackApplyInfo(String buybackId) throws SQLException {
	IDB session = DBFactory.getDB();
	return session.execute("delete from tbuyback_apply_info where buyback_id=?",buybackId);
}

public int modifyBuybackApplyInfo(BuybackApplyInfo obj) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("update tbuyback_apply_info set batch_no=?,aim_branch_no=?,bill_class=?,bill_type=?,inner_account=?,sale_dt=?,due_dt=?,is_dummy=?,is_inner=?,branch_no=?,prod_no=?,status=?,create_dt=?,create_time=?,oper_no=?,rate_type=?,is_online=?,buyback_open_dt=?,buyback_money=?,cust_name=?,buyback_due_dt=?,buyback_rate=?,rate=?,bill_storage_org=?,bill_storage_org_name=?,if_dummy=?,delay_type=?,delay_days=?,in_acct_no=?,in_acct_type=?,in_acct_name=?,prod_attr=?,sale_id=?,order_id=?,apply_status=? where buyback_id=?",
	obj.getBatchNo(),obj.getAimBranchNo(),obj.getBillClass(),obj.getBillType(),
	obj.getInnerAccount(),obj.getSaleDt(),obj.getDueDt(),obj.getIsDummy(),
	obj.getIsInner(),obj.getBranchNo(),obj.getProdNo(),obj.getStatus(),
	obj.getCreateDt(),obj.getCreateTime(),obj.getOperNo(),obj.getRateType(),
	obj.getIsOnline(),obj.getBuybackOpenDt(),obj.getBuybackMoney(),obj.getCustName(),
	obj.getBuybackDueDt(),obj.getBuybackRate(),obj.getRate(),obj.getBillStorageOrg(),
	obj.getBillStorageOrgName(),obj.getIfDummy(),obj.getDelayType(),obj.getDelayDays(),
	obj.getInAcctNo(),obj.getInAcctType(),obj.getInAcctName(),obj.getProdAttr(),
	obj.getSaleId(),obj.getOrderId(),obj.getApplyStatus(),obj.getBuybackId());
}

public int modifyBuybackApplyInfo(BuybackApplyInfo obj,String buybackId) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("update tbuyback_apply_info set batch_no=?,aim_branch_no=?,bill_class=?,bill_type=?,inner_account=?,sale_dt=?,due_dt=?,is_dummy=?,is_inner=?,branch_no=?,prod_no=?,status=?,create_dt=?,create_time=?,oper_no=?,rate_type=?,is_online=?,buyback_open_dt=?,buyback_money=?,cust_name=?,buyback_due_dt=?,buyback_rate=?,rate=?,bill_storage_org=?,bill_storage_org_name=?,if_dummy=?,delay_type=?,delay_days=?,in_acct_no=?,in_acct_type=?,in_acct_name=?,prod_attr=?,sale_id=?,order_id=?,apply_status=? where buyback_id=?",
	obj.getBatchNo(),obj.getAimBranchNo(),obj.getBillClass(),obj.getBillType(),
	obj.getInnerAccount(),obj.getSaleDt(),obj.getDueDt(),obj.getIsDummy(),
	obj.getIsInner(),obj.getBranchNo(),obj.getProdNo(),obj.getStatus(),
	obj.getCreateDt(),obj.getCreateTime(),obj.getOperNo(),obj.getRateType(),
	obj.getIsOnline(),obj.getBuybackOpenDt(),obj.getBuybackMoney(),obj.getCustName(),
	obj.getBuybackDueDt(),obj.getBuybackRate(),obj.getRate(),obj.getBillStorageOrg(),
	obj.getBillStorageOrgName(),obj.getIfDummy(),obj.getDelayType(),obj.getDelayDays(),
	obj.getInAcctNo(),obj.getInAcctType(),obj.getInAcctName(),obj.getProdAttr(),
	obj.getSaleId(),obj.getOrderId(),obj.getApplyStatus(),buybackId);
}

public BuybackApplyInfo getBuybackApplyInfo(String buybackId,BuybackSearchBean query) throws SQLException {
	IDB session = DBFactory.getDB();
	BuybackApplyInfo obj = (BuybackApplyInfo)session.getObject("select * from tbuyback_apply_info where buyback_id=?",BuybackApplyInfo.class,buybackId);
	if(query == null) return obj;
	List<BuybackApplyInfo> list = new ArrayList<BuybackApplyInfo>(1);
	list.add(obj);
	sumApplyInfo(list, query);
	return obj;
}


/**
 * 功能描述：根据条件查询批次列表(申请岗位)
 * @param page
 * @param query
 * @return
 * @throws SQLException
 */
public List<BuybackApplyInfo> getBuybackApplyListForCondition(Page page,BuybackSearchBean query) throws Exception {
	StringBuilder select = new StringBuilder("SELECT DISTINCT APPLY.*");
	select.append(" FROM TBUYBACK_APPLY_INFO APPLY LEFT JOIN TBUYBACK_BILL_INFO BILL ON");
	select.append(" APPLY.BUYBACK_ID = BILL.BUYBACK_ID WHERE 1 = 1 ");
	IDB session = DBFactory.getDB();
	QueryCondition qc = new QueryCondition();
	OrderBean order = new OrderBean("BUYBACK_ID",false);
	query.addProperty2TableObj("BUYBACK_ID", "APPLY");
	query.appendOrder(order);
	qc.initFromSearchBean(query);
	String sql = qc.getAllSqlString(select.toString());
	//分页开始位置
	int startIndex = (page.getCurrentPage()-1)*page.getShowCount()+1;
	if(startIndex<0)
		startIndex = 0;
	// 获得并返回本次查询的总条数
	int count = session.accountByList(qc.getCountSql("DISTINCT APPLY.BUYBACK_ID"),qc.getParameterValues());
	page.setTotalResult(count);
	List<BuybackApplyInfo> applyList = session.getObjectListByListForPage(sql,BuybackApplyInfo.class, startIndex, page.getShowCount(), qc.getParameterValues());
	//批次汇总票据张数，与票据金额
	sumApplyInfo(applyList, query);
	return applyList;
}

/**
 * 票据张数，与票据金额汇总
 * @param applyList
 * @param query
 * @throws SQLException 
 */
public void sumApplyInfo(List<BuybackApplyInfo> applyList,BuybackSearchBean query) throws SQLException{
    if(applyList == null || applyList.size() == 0) return;
    IDB session = DBFactory.getDB();
    //批次汇总票据张数，与票据金额sql
    StringBuilder sb = new StringBuilder("SELECT COUNT(BILL.BUYBACKMX_ID)||'' C_NUM,SUM(BILL.BILL_MONEY)");
    sb.append(", SUM(BILL.INTEREST), SUM(BILL.BUYBACK_MONEY) FROM TBUYBACK_BILL_INFO BILL WHERE BILL.BUYBACK_ID = ? ");
    List<Object> param = new ArrayList<Object>();
    //占第一位 为第一个问号赋值
    param.add("");
    if(query != null){
        if(StringUtils.isNotBlank(query.getBillClass())){
            sb.append(" AND BILL.BILL_CLASS = ? ");
            param.add(query.getBillClass().trim());
        }
        if(StringUtils.isNotBlank(query.getBillType())){
            sb.append(" AND BILL.BILL_TYPE = ? ");
            param.add(query.getBillType().trim());
        }
        if(StringUtils.isNotBlank(query.getApplyOperNo())){
            sb.append(" and bill.apply_oper_no = ? ");
            param.add(query.getApplyOperNo().trim());
        }
        if(StringUtils.isNotBlank(query.getAuditOperNo())){
            sb.append(" and bill.audit_oper_no = ? ");
            param.add(query.getAuditOperNo().trim());
        }
        if(StringUtils.isNotBlank(query.getOperStatus())){
            sb.append(" AND BILL.OPER_STATUS = ? ");
            param.add(query.getOperStatus().trim());
        }
        if(query.getOpers()!=null){
            sb.append(" AND BILL.OPER_STATUS IN (");
            for (Object status : query.getOpers()) {
            	sb.append("?,");
        		param.add(status);
        	}
            sb.deleteCharAt(sb.length()-1);
            sb.append(')');
        }
    }
    for(BuybackApplyInfo apply:applyList){
    	//删除第一位 为第一个问号赋值
    	param.remove(0);
    	// 为第一个问号赋值
    	param.add(0, apply.getBuybackId());
	    IData data=session.getDataByList(sb.toString(), param);
	    apply.setTotalNum(data.getString(1));
	    apply.setTotalMoney(MoneyUtils.moneyToString(data.getDouble(2)));
	    apply.setTotalInterest(MoneyUtils.moneyToString(data.getDouble(3)));
	    apply.setTotalDraweeMoney(MoneyUtils.moneyToString(data.getDouble(4)));//总计实付金额
    }
}


public BuybackApplyInfo getBuybackApplyInfoBySaveId(String saleId) throws SQLException {
	IDB session = DBFactory.getDB();
	BuybackApplyInfo obj = (BuybackApplyInfo)session.getObject("SELECT * FROM TBUYBACK_APPLY_INFO WHERE SALE_ID=?",BuybackApplyInfo.class,saleId);
	return obj;
}

/**
 * 纸票查询回购转出回购申请批次
 * @param page
 * @param query
 * @return
 * @throws SQLException
 */
  public List<SaleApplyInfo> getSaleApplyListBySearchBean(Page page, BuybackSearchBean query)
    throws SQLException
  {
    IDB session = DBFactory.getDB();
    String baseSql = "select distinct apply.* from tsale_apply_info apply,tbuyback_bill_info bill where bill.sale_id = apply.sale_id";
    QueryCondition qc = new QueryCondition();
    try {
      qc.initFromSearchBean(query);
    } catch (Exception e) {
      e.printStackTrace();
    }
    String allsql = qc.getAllSqlString(baseSql);
    int startIndex = (page.getCurrentPage() - 1) * page.getShowCount() + 1;
    if (startIndex < 0)
      startIndex = 0;
    int count = 0;
    List<SaleApplyInfo> applyList=null;

    if ((qc.getParameterValues() == null) || (qc.getParameterValues().size() == 0)) {
      count = session.account(qc.getCountSql("distinct apply.sale_id"));
      applyList = session.getObjectListForPage(allsql, SaleApplyInfo.class, startIndex, page.getShowCount(), new Object[0]);
    } else {
      count = session.accountByList(qc.getCountSql("distinct apply.sale_id"), qc.getParameterValues());
      applyList = session.getObjectListByListForPage(allsql, SaleApplyInfo.class, startIndex, page.getShowCount(), qc.getParameterValues());
    }
    page.setTotalResult(count);
    sumSaleApplyInfo(applyList, query);
    return applyList;
  }
  
  /**
   * 合计回购转出批次信息
   *
   */
    public void sumSaleApplyInfo(List<SaleApplyInfo> applyList,BuybackSearchBean query) throws SQLException{
  	    if(applyList == null || applyList.size() == 0) return;
  	    IDB session = DBFactory.getDB();
  	    //批次汇总票据张数，与票据金额sql
//  	    StringBuilder sb = new StringBuilder("select count(bill.buybackmx_id)||'' c_num,sum(bill.bill_money) from tbuyback_bill_info bill where bill.sale_id = ?");
  	    StringBuilder sb = new StringBuilder("select count(bill.buybackmx_id)||'',");
        sb.append("sum(bill.bill_money),sum(bill.interest),sum(bill.buyback_money) from tbuyback_bill_info bill where bill.sale_id = ? ");
  	    List<Object> param = new ArrayList<Object>();
  	    //占第一位 为第一个问号赋值
  	    param.add("");
  	    if(query != null){
  	        if(StringUtils.isNotBlank(query.getBillClass())){
  	            sb.append(" and bill.bill_class = ? ");
  	            param.add(query.getBillClass().trim());
  	        }
  	        if(StringUtils.isNotBlank(query.getBillType())){
  	            sb.append(" and bill.bill_type = ? ");
  	            param.add(query.getBillType().trim());
  	        }
  	        if(StringUtils.isNotBlank(query.getOperStatus())){
  	            sb.append(" and bill.oper_status = ? ");
  	            param.add(query.getOperStatus().trim());
  	        } 
  	        if(query.getOpers()!=null){
  	            sb.append(" and bill.oper_status in (");
  	            for (Object status : query.getOpers()) {
  	            	sb.append("?,");
  	        		param.add(status);
  	        	}
  	            sb.deleteCharAt(sb.length()-1);
  	            sb.append(')');
  	        }
  	    }
  	    for(SaleApplyInfo apply:applyList){
  	    	//删除第一位 为第一个问号赋值
  	    	param.remove(0);
  	    	// 为第一个问号赋值
  	    	param.add(0, apply.getSaleId());
  		    IData data=session.getDataByList(sb.toString(), param);
  		    apply.setTotalSize( data.getInt(1));
            apply.setSumMoney(data.getDouble(2));
            apply.setSumInterest(data.getDouble(3));
            apply.setSumReceiveMoney(data.getDouble(4));
  	    }
  	}
  
}
