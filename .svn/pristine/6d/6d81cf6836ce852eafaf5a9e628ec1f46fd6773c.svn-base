/********************************************
* 文件名称: SalebackBillInfoDao.java
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
package com.herongtech.console.domain.saleback.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.herongtech.commons.tools.BeanFormat;
import com.herongtech.console.core.common.json.Page;
import com.herongtech.console.core.common.search.QueryCondition;
import com.herongtech.console.core.util.ResourceUtil;
import com.herongtech.console.domain.bean.UserLoginfo;
import com.herongtech.console.domain.rebuy.bean.RebuyBillInfo;
import com.herongtech.console.domain.saleback.bean.SaleBackSearchBean;
import com.herongtech.console.domain.saleback.bean.SalebackBillInfo;
import com.herongtech.db.impl.session.DBFactory;
import com.herongtech.db.interfaces.IDB;
public class SalebackBillInfoDao{

public int addSalebackBillInfo(SalebackBillInfo obj) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("insert into tsaleback_bill_info(salebackmx_id,bill_type,bill_no,issue_dt,due_dt,remitter,remitter_acct,remitter_bank_name,remitter_bank_no,bill_money,acceptor,payee_name,payee_bank_name,payee_acct,bill_before_owner,bill_owner,bill_class,bill_source,oper_no,oper_status,saleback_id,rgct_id,branch_no,cust_account,create_dt,create_time,is_audited,rebuymx_id,drawee_addr,payee_bank_no,confer_no,is_accpt,interest,limit_bill_id,limit_prod_no,buy_dept_no,is_inner,remitter_cust_no,saleback_money,is_online,ex_serial,interest_days,pay_trade_no,account_dt,account_time,apply_oper_no,audit_oper_no,acct_oper_no,delay_type,delay_days,saleback_due_dt,forbid_flag,endorse_dt,endorse_time,acceptor_bank_no,acceptor_bank_name,is_dummy,bill_storage_org,bill_storage_org_name,cur_status,audit_reason,buy_dt,rebuy_id)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
	obj.getSalebackmxId(),obj.getBillType(),obj.getBillNo(),obj.getIssueDt(),obj.getDueDt(),
	obj.getRemitter(),obj.getRemitterAcct(),obj.getRemitterBankName(),obj.getRemitterBankNo(),
	obj.getBillMoney(),obj.getAcceptor(),obj.getPayeeName(),obj.getPayeeBankName(),
	obj.getPayeeAcct(),obj.getBillBeforeOwner(),obj.getBillOwner(),obj.getBillClass(),
	obj.getBillSource(),obj.getOperNo(),obj.getOperStatus(),obj.getSalebackId(),
	obj.getRgctId(),obj.getBranchNo(),obj.getCustAccount(),obj.getCreateDt(),
	obj.getCreateTime(),obj.getIsAudited(),obj.getRebuymxId(),obj.getDraweeAddr(),
	obj.getPayeeBankNo(),obj.getConferNo(),obj.getIsAccpt(),obj.getInterest(),
	obj.getLimitBillId(),obj.getLimitProdNo(),obj.getBuyDeptNo(),obj.getIsInner(),
	obj.getRemitterCustNo(),obj.getSalebackMoney(),obj.getIsOnline(),obj.getExSerial(),
	obj.getInterestDays(),obj.getPayTradeNo(),obj.getAccountDt(),obj.getAccountTime(),
	obj.getApplyOperNo(),obj.getAuditOperNo(),obj.getAcctOperNo(),obj.getDelayType(),
	obj.getDelayDays(),obj.getSalebackDueDt(),obj.getForbidFlag(),obj.getEndorseDt(),
	obj.getEndorseTime(),obj.getAcceptorBankNo(),obj.getAcceptorBankName(),obj.getIsDummy(),
	obj.getBillStorageOrg(),obj.getBillStorageOrgName(),obj.getCurStatus(),obj.getAuditReason(),
	obj.getBuyDt(),obj.getRebuyId());
}

public int modifySalebackBillInfo(SalebackBillInfo obj) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("update tsaleback_bill_info set bill_type=?,bill_no=?,issue_dt=?,due_dt=?,remitter=?,remitter_acct=?,remitter_bank_name=?,remitter_bank_no=?,bill_money=?,acceptor=?,payee_name=?,payee_bank_name=?,payee_acct=?,bill_before_owner=?,bill_owner=?,bill_class=?,bill_source=?,oper_no=?,oper_status=?,saleback_id=?,rgct_id=?,branch_no=?,cust_account=?,create_dt=?,create_time=?,is_audited=?,rebuymx_id=?,drawee_addr=?,payee_bank_no=?,confer_no=?,is_accpt=?,interest=?,limit_bill_id=?,limit_prod_no=?,buy_dept_no=?,is_inner=?,remitter_cust_no=?,saleback_money=?,is_online=?,ex_serial=?,interest_days=?,pay_trade_no=?,account_dt=?,account_time=?,apply_oper_no=?,audit_oper_no=?,acct_oper_no=?,delay_type=?,delay_days=?,saleback_due_dt=?,forbid_flag=?,endorse_dt=?,endorse_time=?,acceptor_bank_no=?,acceptor_bank_name=?,is_dummy=?,bill_storage_org=?,bill_storage_org_name=?,cur_status=?,audit_reason=?,buy_dt=?,rebuy_id=? where salebackmx_id=?",
	obj.getBillType(),obj.getBillNo(),obj.getIssueDt(),obj.getDueDt(),
	obj.getRemitter(),obj.getRemitterAcct(),obj.getRemitterBankName(),obj.getRemitterBankNo(),
	obj.getBillMoney(),obj.getAcceptor(),obj.getPayeeName(),obj.getPayeeBankName(),
	obj.getPayeeAcct(),obj.getBillBeforeOwner(),obj.getBillOwner(),obj.getBillClass(),
	obj.getBillSource(),obj.getOperNo(),obj.getOperStatus(),obj.getSalebackId(),
	obj.getRgctId(),obj.getBranchNo(),obj.getCustAccount(),obj.getCreateDt(),
	obj.getCreateTime(),obj.getIsAudited(),obj.getRebuymxId(),obj.getDraweeAddr(),
	obj.getPayeeBankNo(),obj.getConferNo(),obj.getIsAccpt(),obj.getInterest(),
	obj.getLimitBillId(),obj.getLimitProdNo(),obj.getBuyDeptNo(),obj.getIsInner(),
	obj.getRemitterCustNo(),obj.getSalebackMoney(),obj.getIsOnline(),obj.getExSerial(),
	obj.getInterestDays(),obj.getPayTradeNo(),obj.getAccountDt(),obj.getAccountTime(),
	obj.getApplyOperNo(),obj.getAuditOperNo(),obj.getAcctOperNo(),obj.getDelayType(),
	obj.getDelayDays(),obj.getSalebackDueDt(),obj.getForbidFlag(),obj.getEndorseDt(),
	obj.getEndorseTime(),obj.getAcceptorBankNo(),obj.getAcceptorBankName(),obj.getIsDummy(),
	obj.getBillStorageOrg(),obj.getBillStorageOrgName(),obj.getCurStatus(),obj.getAuditReason(),
	obj.getBuyDt(),obj.getRebuyId(),obj.getSalebackmxId());
}

public int modifySalebackBillInfo(SalebackBillInfo obj,String salebackmxId) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("update tsaleback_bill_info set bill_type=?,bill_no=?,issue_dt=?,due_dt=?,remitter=?,remitter_acct=?,remitter_bank_name=?,remitter_bank_no=?,bill_money=?,acceptor=?,payee_name=?,payee_bank_name=?,payee_acct=?,bill_before_owner=?,bill_owner=?,bill_class=?,bill_source=?,oper_no=?,oper_status=?,saleback_id=?,rgct_id=?,branch_no=?,cust_account=?,create_dt=?,create_time=?,is_audited=?,rebuymx_id=?,drawee_addr=?,payee_bank_no=?,confer_no=?,is_accpt=?,interest=?,limit_bill_id=?,limit_prod_no=?,buy_dept_no=?,is_inner=?,remitter_cust_no=?,saleback_money=?,is_online=?,ex_serial=?,interest_days=?,pay_trade_no=?,account_dt=?,account_time=?,apply_oper_no=?,audit_oper_no=?,acct_oper_no=?,delay_type=?,delay_days=?,saleback_due_dt=?,forbid_flag=?,endorse_dt=?,endorse_time=?,acceptor_bank_no=?,acceptor_bank_name=?,is_dummy=?,bill_storage_org=?,bill_storage_org_name=?,cur_status=?,audit_reason=?,buy_dt=?,rebuy_id=? where salebackmx_id=?",
	obj.getBillType(),obj.getBillNo(),obj.getIssueDt(),obj.getDueDt(),
	obj.getRemitter(),obj.getRemitterAcct(),obj.getRemitterBankName(),obj.getRemitterBankNo(),
	obj.getBillMoney(),obj.getAcceptor(),obj.getPayeeName(),obj.getPayeeBankName(),
	obj.getPayeeAcct(),obj.getBillBeforeOwner(),obj.getBillOwner(),obj.getBillClass(),
	obj.getBillSource(),obj.getOperNo(),obj.getOperStatus(),obj.getSalebackId(),
	obj.getRgctId(),obj.getBranchNo(),obj.getCustAccount(),obj.getCreateDt(),
	obj.getCreateTime(),obj.getIsAudited(),obj.getRebuymxId(),obj.getDraweeAddr(),
	obj.getPayeeBankNo(),obj.getConferNo(),obj.getIsAccpt(),obj.getInterest(),
	obj.getLimitBillId(),obj.getLimitProdNo(),obj.getBuyDeptNo(),obj.getIsInner(),
	obj.getRemitterCustNo(),obj.getSalebackMoney(),obj.getIsOnline(),obj.getExSerial(),
	obj.getInterestDays(),obj.getPayTradeNo(),obj.getAccountDt(),obj.getAccountTime(),
	obj.getApplyOperNo(),obj.getAuditOperNo(),obj.getAcctOperNo(),obj.getDelayType(),
	obj.getDelayDays(),obj.getSalebackDueDt(),obj.getForbidFlag(),obj.getEndorseDt(),
	obj.getEndorseTime(),obj.getAcceptorBankNo(),obj.getAcceptorBankName(),obj.getIsDummy(),
	obj.getBillStorageOrg(),obj.getBillStorageOrgName(),obj.getCurStatus(),obj.getAuditReason(),
	obj.getBuyDt(),obj.getRebuyId(),salebackmxId);
}

public SalebackBillInfo getSalebackBillInfo(String salebackmxId) throws SQLException {
	IDB session = DBFactory.getDB();
	SalebackBillInfo obj = (SalebackBillInfo)session.getObject("select * from tsaleback_bill_info where salebackmx_id=?",SalebackBillInfo.class,salebackmxId);
	return obj;
}
/**
 * 根据rgctId获取票据信息(多张票)
 * @param rgctId 登记中心id
 * @return
 * @throws SQLException
 */
public List<SalebackBillInfo> getSalebackBillInfolistForRgctId(String rgctIds) throws SQLException {
	IDB session = DBFactory.getDB();
	String sql = "select * from tsaleback_bill_info where rgct_id in ("+rgctIds+") order by salebackmx_id desc";
	List<SalebackBillInfo> obj = session.getObjectList(sql,SalebackBillInfo.class);
	return obj;
}

/**查询出可反售的清单*/
public List<SalebackBillInfo> getSalebackBillInfolistentity(Page page,SaleBackSearchBean query) throws SQLException {
	IDB session = DBFactory.getDB();
	String baseSql="select  bill.* from trebuy_apply_info apply, tsaleback_bill_info bill where apply.rebuy_id=bill.rebuy_id";
	//分页开始位置
	int startIndex = page.getCurrentResult();
	 QueryCondition qc=new QueryCondition();
	 try {
         qc.initFromSearchBean(query);
     } catch (Exception e) {
         e.printStackTrace();
     }
	 String sql=qc.getAllSqlString(baseSql);
     System.out.println(sql);
	// 获得并返回本次查询的总条数
	int count = session.accountByList(qc.getCountSql("bill.salebackmx_Id"), qc.getParameterValues());
	page.setTotalResult(count);
	return session.getObjectListByListForPage(sql,SalebackBillInfo.class, startIndex, page.getShowCount(), qc.getParameterValues());
}

/**
 * 根据searchBean查询清单信息列表（分页）
 * @param searchBean
 * @param page
 * @return
 * @throws SQLException
 */
public List<SalebackBillInfo> getRebuyBillListBySearchBean(SaleBackSearchBean searchBean, Page page) throws SQLException {
	IDB session = DBFactory.getDB();
	String baseSql="select bill.* from tsaleback_bill_info bill where 1=1";
	 QueryCondition qc=new QueryCondition();
     try {
         qc.initFromSearchBean(searchBean);
     } catch (Exception e) {
         e.printStackTrace();
     }
	String allsql=qc.getAllSqlString(baseSql);
	//分页开始位置
	int startIndex = (page.getCurrentPage()-1)*page.getShowCount()+1;
	if(startIndex<0)
		startIndex = 0;
	int count =0;
	List<SalebackBillInfo> billList=null;
	// 获得并返回本次查询的总条数
	if(qc.getParameterValues() ==null ||qc.getParameterValues().size()==0){//什么条件都不带的情况
	    count = session.account(qc.getCountSql("distinct bill.salebackmx_id"));
	    billList=session.getObjectListForPage(allsql, SalebackBillInfo.class,startIndex, page.getShowCount());
	}else{
	    count = session.accountByList(qc.getCountSql("distinct bill.salebackmx_id"), qc.getParameterValues()); 
	    billList=session.getObjectListByListForPage(allsql,SalebackBillInfo.class, startIndex, page.getShowCount(), qc.getParameterValues());
	}
	page.setTotalResult(count);

	return billList;
}

/**
 * 功能描述：得到返售确认電票根据清单id
 * @return
 * @throws SQLException
 */
public List<SalebackBillInfo> getConfirmReceiveBillForId(String ids) throws SQLException{
	StringBuffer sql = new StringBuffer("select * from tsaleback_bill_info  where salebackmx_id in (");
	String idArr[] = ids.split(",");
	List<Object> param = new ArrayList<Object>(idArr.length);
	for (String id : idArr) {
		sql.append("?,");
		param.add(id);
	}
	sql.deleteCharAt(sql.length()-1);
	sql.append(')');
	IDB session = DBFactory.getDB();
	return session.getObjectListByList(sql.toString(), SalebackBillInfo.class, param);
}

/**审批变更状态*/
public List<SalebackBillInfo> getSaveBillForSaveIdAndStatus(String batchId,String status) throws SQLException{
	IDB session = DBFactory.getDB();
	List<SalebackBillInfo> objs = new ArrayList<SalebackBillInfo>();
	
	objs= session.getObjectList("select * from tsaleback_bill_info where saleback_Id = ? and oper_status =?",SalebackBillInfo.class,batchId,status);
	
	return objs;
}


/**
 * 根据searchBean查询清单信息列表（返售清单和批次关联   分页）
 * @param searchBean
 * @param page
 * @return
 * @throws SQLException
 */
public List<SalebackBillInfo> getsalebackBillListBySearchBean(SaleBackSearchBean searchBean, Page page) throws SQLException {
	IDB session = DBFactory.getDB();
	String baseSql="select bill.* from tsaleback_apply_info apply, tsaleback_bill_info bill where apply.saleback_id=bill.saleback_id";
	 QueryCondition qc=new QueryCondition();
     try {
         qc.initFromSearchBean(searchBean);
     } catch (Exception e) {
         e.printStackTrace();
     }
	String allsql=qc.getAllSqlString(baseSql);
	//分页开始位置
	int startIndex = (page.getCurrentPage()-1)*page.getShowCount()+1;
	if(startIndex<0)
		startIndex = 0;
	int count =0;
	List<SalebackBillInfo> billList=null;
	// 获得并返回本次查询的总条数
	if(qc.getParameterValues() ==null ||qc.getParameterValues().size()==0){//什么条件都不带的情况
	    count = session.account(qc.getCountSql("distinct bill.salebackmx_id"));
	    billList=session.getObjectListForPage(allsql, SalebackBillInfo.class,startIndex, page.getShowCount());
	}else{
	    count = session.accountByList(qc.getCountSql("distinct bill.salebackmx_id"), qc.getParameterValues()); 
	    billList=session.getObjectListByListForPage(allsql,SalebackBillInfo.class, startIndex, page.getShowCount(), qc.getParameterValues());
	}
	page.setTotalResult(count);

	return billList;
}

	/**
	 * 转入撤销记账后删除返售清单表中数据
	 * @param salebackmxId
	 * @param status
	 * @throws SQLException 
	 */
	public void rebuycancelaccountupdatesalebackstatus(String salebackmxId,String status) throws SQLException{
		IDB session = DBFactory.getDB();
		String sql = "update tsaleback_bill_info set oper_status=? where salebackmx_id = ?";
		session.execute(sql,status,salebackmxId);
	}
	
	public SalebackBillInfo getsalebackbillinfobyrgctidandstatus(String rgctid,String operstatus) throws SQLException{
		IDB session = DBFactory.getDB();
		String sql = "select * from tsaleback_bill_info where rgct_id = ? and oper_status = ? order by salebackmx_id desc";
		
		SalebackBillInfo saleback =(SalebackBillInfo)session.getObject(sql, SalebackBillInfo.class, rgctid,operstatus);
		
		return saleback;
	}
}

