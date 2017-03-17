/********************************************
* 文件名称: DiscApplyInfoDao.java
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
package com.herongtech.console.domain.disc.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.herongtech.commons.tools.BeanFormat;
import com.herongtech.console.core.common.json.Page;
import com.herongtech.console.core.common.search.OrderBean;
import com.herongtech.console.core.common.search.QueryCondition;
import com.herongtech.console.core.util.MoneyUtils;
import com.herongtech.console.core.util.StatusUtils;
import com.herongtech.console.domain.disc.bean.DiscApplyInfo;
import com.herongtech.console.domain.disc.bean.DiscSearchBean;
import com.herongtech.data.interfaces.data.IData;
import com.herongtech.db.impl.session.DBFactory;
import com.herongtech.db.interfaces.IDB;
public class DiscApplyInfoDao{

	public int addDiscApplyInfo(DiscApplyInfo obj) throws SQLException {
		BeanFormat.format(obj);
		IDB session = DBFactory.getDB();
		return session.execute("insert into tdisc_apply_info(disc_id,batch_no,rate,buy_pay_rate,cust_account,branch_no,pay_type,pay_bank_no,pay_cust_name,pay_account,prod_no,rate_type,bill_type,bill_class,disc_dt,cust_manager_name,is_tc,agent_cust_name,agent_cust_account,delay_type,redemption_dt,dept_no,create_time,dept_name,redeem_date,cust_name,trustee_name,trustee_acct,trustee_bank_name,trustee_bank_no,prof_owner_no,prof_owner,bill_owner,workingads_no1,cb_rate,bank_prod_no,workingads_no,sign_branch_no,cust_manage,cust_no,pay_account_type,order_id,cust_account_type,acct_branch_no,prod_busi_type,create_dt,apply_status,profession_name,social_credit_code,enterprise_scale,is_rural_enterprises)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
		obj.getDiscId(),obj.getBatchNo(),obj.getRate(),obj.getBuyPayRate(),obj.getCustAccount(),
		obj.getBranchNo(),obj.getPayType(),obj.getPayBankNo(),obj.getPayCustName(),
		obj.getPayAccount(),obj.getProdNo(),obj.getRateType(),obj.getBillType(),
		obj.getBillClass(),obj.getDiscDt(),obj.getCustManagerName(),obj.getIsTc(),
		obj.getAgentCustName(),obj.getAgentCustAccount(),obj.getDelayType(),obj.getRedemptionDt(),
		obj.getDeptNo(),obj.getCreateTime(),obj.getDeptName(),obj.getRedeemDate(),
		obj.getCustName(),obj.getTrusteeName(),obj.getTrusteeAcct(),obj.getTrusteeBankName(),
		obj.getTrusteeBankNo(),obj.getProfOwnerNo(),obj.getProfOwner(),obj.getBillOwner(),
		obj.getWorkingadsNo1(),obj.getCbRate(),obj.getBankProdNo(),obj.getWorkingadsNo(),
		obj.getSignBranchNo(),obj.getCustManage(),obj.getCustNo(),obj.getPayAccountType(),
		obj.getOrderId(),obj.getCustAccountType(),obj.getAcctBranchNo(),obj.getProdBusiType(),
		obj.getCreateDt(),obj.getApplyStatus(),obj.getProfessionName(),obj.getSocialCreditCode(),
		obj.getEnterpriseScale(),obj.getIsRuralEnterprises());
	}

public int deleteDiscApplyInfo(DiscApplyInfo obj) throws SQLException {
	IDB session = DBFactory.getDB();
	return session.execute("delete from tdisc_apply_info where disc_id=?",obj.getDiscId());
}

public int deleteDiscApplyInfo(String discId) throws SQLException {
	IDB session = DBFactory.getDB();
	return session.execute("delete from tdisc_apply_info where disc_id=?",discId);
}


public int deleteDiscApplyInfo(DiscApplyInfo obj,String discId) throws SQLException {
	IDB session = DBFactory.getDB();
	return session.execute("update tdisc_apply_info set apply_status=? where disc_id=?",obj.getApplyStatus(),discId);
}
public int modifyDiscApplyInfo(DiscApplyInfo obj) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("update tdisc_apply_info set batch_no=?,rate=?,buy_pay_rate=?,cust_account=?,branch_no=?,pay_type=?,pay_bank_no=?,pay_cust_name=?,pay_account=?,prod_no=?,rate_type=?,bill_type=?,bill_class=?,disc_dt=?,cust_manager_name=?,is_tc=?,agent_cust_name=?,agent_cust_account=?,delay_type=?,redemption_dt=?,dept_no=?,create_time=?,dept_name=?,redeem_date=?,cust_name=?,trustee_name=?,trustee_acct=?,trustee_bank_name=?,trustee_bank_no=?,prof_owner_no=?,prof_owner=?,bill_owner=?,workingads_no1=?,cb_rate=?,bank_prod_no=?,workingads_no=?,sign_branch_no=?,cust_manage=?,cust_no=?,pay_account_type=?,order_id=?,cust_account_type=?,acct_branch_no=?,prod_busi_type=?,create_dt=?,apply_status=?,profession_name=?,social_credit_code=?,enterprise_scale=?,is_rural_enterprises=? where disc_id=?",
	obj.getBatchNo(),obj.getRate(),obj.getBuyPayRate(),obj.getCustAccount(),
	obj.getBranchNo(),obj.getPayType(),obj.getPayBankNo(),obj.getPayCustName(),
	obj.getPayAccount(),obj.getProdNo(),obj.getRateType(),obj.getBillType(),
	obj.getBillClass(),obj.getDiscDt(),obj.getCustManagerName(),obj.getIsTc(),
	obj.getAgentCustName(),obj.getAgentCustAccount(),obj.getDelayType(),obj.getRedemptionDt(),
	obj.getDeptNo(),obj.getCreateTime(),obj.getDeptName(),obj.getRedeemDate(),
	obj.getCustName(),obj.getTrusteeName(),obj.getTrusteeAcct(),obj.getTrusteeBankName(),
	obj.getTrusteeBankNo(),obj.getProfOwnerNo(),obj.getProfOwner(),obj.getBillOwner(),
	obj.getWorkingadsNo1(),obj.getCbRate(),obj.getBankProdNo(),obj.getWorkingadsNo(),
	obj.getSignBranchNo(),obj.getCustManage(),obj.getCustNo(),obj.getPayAccountType(),
	obj.getOrderId(),obj.getCustAccountType(),obj.getAcctBranchNo(),obj.getProdBusiType(),
	obj.getCreateDt(),obj.getApplyStatus(),obj.getProfessionName(),obj.getSocialCreditCode(),
	obj.getEnterpriseScale(),obj.getIsRuralEnterprises(),obj.getDiscId());
}

public int modifyDiscApplyInfo(DiscApplyInfo obj,String discId) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("update tdisc_apply_info set batch_no=?,rate=?,buy_pay_rate=?,cust_account=?,branch_no=?,pay_type=?,pay_bank_no=?,pay_cust_name=?,pay_account=?,prod_no=?,rate_type=?,bill_type=?,bill_class=?,disc_dt=?,cust_manager_name=?,is_tc=?,agent_cust_name=?,agent_cust_account=?,delay_type=?,redemption_dt=?,dept_no=?,create_time=?,dept_name=?,redeem_date=?,cust_name=?,trustee_name=?,trustee_acct=?,trustee_bank_name=?,trustee_bank_no=?,prof_owner_no=?,prof_owner=?,bill_owner=?,workingads_no1=?,cb_rate=?,bank_prod_no=?,workingads_no=?,sign_branch_no=?,cust_manage=?,cust_no=?,pay_account_type=?,order_id=?,cust_account_type=?,acct_branch_no=?,prod_busi_type=?,create_dt=?,apply_status=?,profession_name=?,social_credit_code=?,enterprise_scale=?,is_rural_enterprises=? where disc_id=?",
	obj.getBatchNo(),obj.getRate(),obj.getBuyPayRate(),obj.getCustAccount(),
	obj.getBranchNo(),obj.getPayType(),obj.getPayBankNo(),obj.getPayCustName(),
	obj.getPayAccount(),obj.getProdNo(),obj.getRateType(),obj.getBillType(),
	obj.getBillClass(),obj.getDiscDt(),obj.getCustManagerName(),obj.getIsTc(),
	obj.getAgentCustName(),obj.getAgentCustAccount(),obj.getDelayType(),obj.getRedemptionDt(),
	obj.getDeptNo(),obj.getCreateTime(),obj.getDeptName(),obj.getRedeemDate(),
	obj.getCustName(),obj.getTrusteeName(),obj.getTrusteeAcct(),obj.getTrusteeBankName(),
	obj.getTrusteeBankNo(),obj.getProfOwnerNo(),obj.getProfOwner(),obj.getBillOwner(),
	obj.getWorkingadsNo1(),obj.getCbRate(),obj.getBankProdNo(),obj.getWorkingadsNo(),
	obj.getSignBranchNo(),obj.getCustManage(),obj.getCustNo(),obj.getPayAccountType(),
	obj.getOrderId(),obj.getCustAccountType(),obj.getAcctBranchNo(),obj.getProdBusiType(),
	obj.getCreateDt(),obj.getApplyStatus(),obj.getProfessionName(),obj.getSocialCreditCode(),
	obj.getEnterpriseScale(),obj.getIsRuralEnterprises(),discId);
}

public DiscApplyInfo getDiscApplyInfo(String discId,DiscSearchBean query) throws SQLException {
	IDB session = DBFactory.getDB();
	DiscApplyInfo obj = (DiscApplyInfo)session.getObject("select * from tdisc_apply_info where disc_id=?",DiscApplyInfo.class,discId);
	if(query == null) return obj;
	List<DiscApplyInfo> list = new ArrayList<DiscApplyInfo>(1);
	list.add(obj);
	sumApplyInfo(list, query);
	return obj;
}

/**
 * 功能描述：根据条件查询批次列表(审核 复核记账使用)
 * @param page
 * @param query
 * @return
 * @throws SQLException
 */
public List<DiscApplyInfo> getDiscApplyListForCondition(Page page,DiscSearchBean query) throws SQLException {
	IDB session = DBFactory.getDB();
	String baseSql="select distinct apply.* from tdisc_apply_info apply,tdisc_bill_info bill where  bill.disc_id = apply.disc_id";
	QueryCondition qc=new QueryCondition();
    try {
        qc.initFromSearchBean(query);
    } catch (Exception e) {
        e.printStackTrace();
    }
	String allsql=qc.getAllSqlString(baseSql);
	System.out.println(allsql);
	//分页开始位置
	int startIndex = (page.getCurrentPage()-1)*page.getShowCount()+1;
	if(startIndex<0)
		startIndex = 0;
	int count =0;
	List<DiscApplyInfo> applyList=null;
	// 获得并返回本次查询的总条数
	if(qc.getParameterValues() ==null ||qc.getParameterValues().size()==0){//什么条件都不带的情况
	    count = session.account(qc.getCountSql("distinct apply.disc_id"));
	    applyList=session.getObjectListForPage(allsql, DiscApplyInfo.class,startIndex, page.getShowCount());
	}else{
	    count = session.accountByList(qc.getCountSql("distinct apply.disc_id"), qc.getParameterValues()); 
	    applyList=session.getObjectListByListForPage(allsql,DiscApplyInfo.class, startIndex, page.getShowCount(), qc.getParameterValues());
	}
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
    public void sumApplyInfo(List<DiscApplyInfo> applyList,DiscSearchBean query) throws SQLException{
        if(applyList == null || applyList.size() == 0) return;
        IDB session = DBFactory.getDB();
        //批次汇总票据张数，与票据金额sql
        StringBuilder sb = new StringBuilder("select count(bill.discmx_id)||'' c_num,sum(bill.bill_money)");
        sb.append(",sum(bill.pay_money),sum(bill.BUYER_INTEREST+bill.SALER_INTEREST) from tdisc_bill_info bill where bill.disc_id = ? ");
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
	        if(StringUtils.isNotBlank(query.getApplyOperNo())){
	            sb.append(" and bill.apply_oper_no = ? ");
	            param.add(query.getApplyOperNo().trim());
	        }
	        if(StringUtils.isNotBlank(query.getAuditOperNo())){
	            sb.append(" and bill.audit_oper_no = ? ");
	            param.add(query.getAuditOperNo().trim());
	        }
	        if(StringUtils.isNotBlank(query.getAcctOperNo())){
	            sb.append(" and bill.acct_oper_no = ? ");
	            param.add(query.getAcctOperNo().trim());
	        }
	        if(StringUtils.isNotBlank(query.getOperStatus())){
	            sb.append(" and bill.oper_status = ? ");
	            param.add(query.getOperStatus().trim());
	        }
	        if(StringUtils.isNotBlank(query.getAccountDate())){
	            sb.append(" and bill.account_date = ? ");
	            param.add(query.getAccountDate().trim());
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
	    for(DiscApplyInfo apply:applyList){
	    	//删除第一位 为第一个问号赋值
	    	param.remove(0);
	    	// 为第一个问号赋值
	    	param.add(0, apply.getDiscId());
		    IData data=session.getDataByList(sb.toString(), param);
		    apply.setTotalNum(data.getString(1));
		    apply.setTotalMoney(MoneyUtils.moneyToString(data.getDouble(2)));
		    apply.setActualAmount(data.getString(3));
		    apply.setTotalInterest(data.getString(4));
        }
    }

/**
 * 功能描述：根据条件查询批次列表(申请岗位)
 * @param page
 * @param query
 * @return
 * @throws SQLException
 */
public List<DiscApplyInfo> getDiscApplyListForApply(Page page,DiscSearchBean query) throws Exception {
	StringBuilder select = new StringBuilder("select distinct ta.*");
	select.append(" from tdisc_apply_info ta left join tdisc_bill_info tb on");
	select.append(" tb.disc_id = ta.disc_id where ta.bill_class='1' ");
	IDB session = DBFactory.getDB();
	QueryCondition qc = new QueryCondition();
	OrderBean order = new OrderBean("discId",false);
	query.appendOrder(order);
	qc.initFromSearchBean(query);
	String sql = qc.getAllSqlString(select.toString());
	//分页开始位置
	int startIndex = (page.getCurrentPage()-1)*page.getShowCount()+1;
	if(startIndex<0)
		startIndex = 0;
	// 获得并返回本次查询的总条数
	int count = session.accountByList(qc.getCountSql("distinct ta.disc_id"),qc.getParameterValues());
	page.setTotalResult(count);
	List<DiscApplyInfo> applyList = session.getObjectListByListForPage(sql,DiscApplyInfo.class, startIndex, page.getShowCount(), qc.getParameterValues());
	//汇总申请刚票据信息
	query.setOperStatus(StatusUtils.queryStatus("DiscApplyController", "billManage", null)[0]);
	//批次汇总票据张数，与票据金额
	sumApplyInfo(applyList, query);
	return applyList;
}


}
