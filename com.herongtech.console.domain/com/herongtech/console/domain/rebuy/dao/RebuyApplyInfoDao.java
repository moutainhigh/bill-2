/********************************************
* 文件名称: RebuyApplyInfoDao.java
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
package com.herongtech.console.domain.rebuy.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.herongtech.commons.tools.BeanFormat;
import com.herongtech.console.core.common.json.Page;
import com.herongtech.console.core.common.search.QueryCondition;
import com.herongtech.console.core.util.MoneyUtils;
import com.herongtech.console.domain.bean.Dict;
import com.herongtech.console.domain.rebuy.bean.RebuyApplyInfo;
import com.herongtech.console.domain.rebuy.bean.RebuySearchBean;
import com.herongtech.data.interfaces.data.IData;
import com.herongtech.db.impl.session.DBFactory;
import com.herongtech.db.interfaces.IDB;
public class RebuyApplyInfoDao{

public int addRebuyApplyInfo(RebuyApplyInfo obj) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("insert into trebuy_apply_info(rebuy_id,batch_no,prod_no,bill_type,bill_class,rebuy_dt,resale_due_dt,rate,rate_type,delay_type,is_dummy,is_inner,bidect_due_dt,delay_days,is_bidir_sale,cust_manager_name,dept_no,create_dt,create_time,dept_name,prof_owner_no,prof_owner,cust_name,disc_type,product_atts,cb_rate,file_no,bill_storage_org,resale_sta_dt,saleback_rate,saleback_money,saleback_rate_type,cust_manage,is_online,bill_storage_org_name,is_redisc_center,busi_branch_no,busi_branch_name,endorses_bank_no,busi_brch_bank_no,cust_no,cust_type,apply_oper_no,audit_oper_no,acct_oper_no,remark,cust_account,cust_branch_no,cust_branch_name,cust_bank_no,cust_bank_name,trade_acct,trade_acct_name,sale_id,prod_attr,branch_no,trade_acct_type,trade_acct_org,cust_org_code,order_id,apply_status)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
	obj.getRebuyId(),obj.getBatchNo(),obj.getProdNo(),obj.getBillType(),obj.getBillClass(),
	obj.getRebuyDt(),obj.getResaleDueDt(),obj.getRate(),obj.getRateType(),
	obj.getDelayType(),obj.getIsDummy(),obj.getIsInner(),obj.getBidectDueDt(),
	obj.getDelayDays(),obj.getIsBidirSale(),obj.getCustManagerName(),obj.getDeptNo(),
	obj.getCreateDt(),obj.getCreateTime(),obj.getDeptName(),obj.getProfOwnerNo(),
	obj.getProfOwner(),obj.getCustName(),obj.getDiscType(),obj.getProductAtts(),
	obj.getCbRate(),obj.getFileNo(),obj.getBillStorageOrg(),obj.getResaleStaDt(),
	obj.getSalebackRate(),obj.getSalebackMoney(),obj.getSalebackRateType(),obj.getCustManage(),
	obj.getIsOnline(),obj.getBillStorageOrgName(),obj.getIsRediscCenter(),obj.getBusiBranchNo(),
	obj.getBusiBranchName(),obj.getEndorsesBankNo(),obj.getBusiBrchBankNo(),obj.getCustNo(),
	obj.getCustType(),obj.getApplyOperNo(),obj.getAuditOperNo(),obj.getAcctOperNo(),
	obj.getRemark(),obj.getCustAccount(),obj.getCustBranchNo(),obj.getCustBranchName(),
	obj.getCustBankNo(),obj.getCustBankName(),obj.getTradeAcct(),obj.getTradeAcctName(),
	obj.getSaleId(),obj.getProdAttr(),obj.getBranchNo(),obj.getTradeAcctType(),
	obj.getTradeAcctOrg(),obj.getCustOrgCode(),obj.getOrderId(),obj.getApplyStatus());
}

public int deleteRebuyApplyInfo(RebuyApplyInfo obj) throws SQLException {
	IDB session = DBFactory.getDB();
	return session.execute("delete from trebuy_apply_info where rebuy_id=?",obj.getRebuyId());
}

public int deleteRebuyApplyInfo(String rebuyId) throws SQLException {
	IDB session = DBFactory.getDB();
	return session.execute("delete from trebuy_apply_info where rebuy_id=?",rebuyId);
}

public int modifyRebuyApplyInfo(RebuyApplyInfo obj) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("update trebuy_apply_info set batch_no=?,prod_no=?,bill_type=?,bill_class=?,rebuy_dt=?,resale_due_dt=?,rate=?,rate_type=?,delay_type=?,is_dummy=?,is_inner=?,bidect_due_dt=?,delay_days=?,is_bidir_sale=?,cust_manager_name=?,dept_no=?,create_dt=?,create_time=?,dept_name=?,prof_owner_no=?,prof_owner=?,cust_name=?,disc_type=?,product_atts=?,cb_rate=?,file_no=?,bill_storage_org=?,resale_sta_dt=?,saleback_rate=?,saleback_money=?,saleback_rate_type=?,cust_manage=?,is_online=?,bill_storage_org_name=?,is_redisc_center=?,busi_branch_no=?,busi_branch_name=?,endorses_bank_no=?,busi_brch_bank_no=?,cust_no=?,cust_type=?,apply_oper_no=?,audit_oper_no=?,acct_oper_no=?,remark=?,cust_account=?,cust_branch_no=?,cust_branch_name=?,cust_bank_no=?,cust_bank_name=?,trade_acct=?,trade_acct_name=?,sale_id=?,prod_attr=?,branch_no=?,trade_acct_type=?,trade_acct_org=?,cust_org_code=?,order_id=?,apply_status=? where rebuy_id=?",
	obj.getBatchNo(),obj.getProdNo(),obj.getBillType(),obj.getBillClass(),
	obj.getRebuyDt(),obj.getResaleDueDt(),obj.getRate(),obj.getRateType(),
	obj.getDelayType(),obj.getIsDummy(),obj.getIsInner(),obj.getBidectDueDt(),
	obj.getDelayDays(),obj.getIsBidirSale(),obj.getCustManagerName(),obj.getDeptNo(),
	obj.getCreateDt(),obj.getCreateTime(),obj.getDeptName(),obj.getProfOwnerNo(),
	obj.getProfOwner(),obj.getCustName(),obj.getDiscType(),obj.getProductAtts(),
	obj.getCbRate(),obj.getFileNo(),obj.getBillStorageOrg(),obj.getResaleStaDt(),
	obj.getSalebackRate(),obj.getSalebackMoney(),obj.getSalebackRateType(),obj.getCustManage(),
	obj.getIsOnline(),obj.getBillStorageOrgName(),obj.getIsRediscCenter(),obj.getBusiBranchNo(),
	obj.getBusiBranchName(),obj.getEndorsesBankNo(),obj.getBusiBrchBankNo(),obj.getCustNo(),
	obj.getCustType(),obj.getApplyOperNo(),obj.getAuditOperNo(),obj.getAcctOperNo(),
	obj.getRemark(),obj.getCustAccount(),obj.getCustBranchNo(),obj.getCustBranchName(),
	obj.getCustBankNo(),obj.getCustBankName(),obj.getTradeAcct(),obj.getTradeAcctName(),
	obj.getSaleId(),obj.getProdAttr(),obj.getBranchNo(),obj.getTradeAcctType(),
	obj.getTradeAcctOrg(),obj.getCustOrgCode(),obj.getOrderId(),obj.getApplyStatus(),
	obj.getRebuyId());
}

public int modifyRebuyApplyInfo(RebuyApplyInfo obj,String rebuyId) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("update trebuy_apply_info set batch_no=?,prod_no=?,bill_type=?,bill_class=?,rebuy_dt=?,resale_due_dt=?,rate=?,rate_type=?,delay_type=?,is_dummy=?,is_inner=?,bidect_due_dt=?,delay_days=?,is_bidir_sale=?,cust_manager_name=?,dept_no=?,create_dt=?,create_time=?,dept_name=?,prof_owner_no=?,prof_owner=?,cust_name=?,disc_type=?,product_atts=?,cb_rate=?,file_no=?,bill_storage_org=?,resale_sta_dt=?,saleback_rate=?,saleback_money=?,saleback_rate_type=?,cust_manage=?,is_online=?,bill_storage_org_name=?,is_redisc_center=?,busi_branch_no=?,busi_branch_name=?,endorses_bank_no=?,busi_brch_bank_no=?,cust_no=?,cust_type=?,apply_oper_no=?,audit_oper_no=?,acct_oper_no=?,remark=?,cust_account=?,cust_branch_no=?,cust_branch_name=?,cust_bank_no=?,cust_bank_name=?,trade_acct=?,trade_acct_name=?,sale_id=?,prod_attr=?,branch_no=?,trade_acct_type=?,trade_acct_org=?,cust_org_code=?,order_id=?,apply_status=? where rebuy_id=?",
	obj.getBatchNo(),obj.getProdNo(),obj.getBillType(),obj.getBillClass(),
	obj.getRebuyDt(),obj.getResaleDueDt(),obj.getRate(),obj.getRateType(),
	obj.getDelayType(),obj.getIsDummy(),obj.getIsInner(),obj.getBidectDueDt(),
	obj.getDelayDays(),obj.getIsBidirSale(),obj.getCustManagerName(),obj.getDeptNo(),
	obj.getCreateDt(),obj.getCreateTime(),obj.getDeptName(),obj.getProfOwnerNo(),
	obj.getProfOwner(),obj.getCustName(),obj.getDiscType(),obj.getProductAtts(),
	obj.getCbRate(),obj.getFileNo(),obj.getBillStorageOrg(),obj.getResaleStaDt(),
	obj.getSalebackRate(),obj.getSalebackMoney(),obj.getSalebackRateType(),obj.getCustManage(),
	obj.getIsOnline(),obj.getBillStorageOrgName(),obj.getIsRediscCenter(),obj.getBusiBranchNo(),
	obj.getBusiBranchName(),obj.getEndorsesBankNo(),obj.getBusiBrchBankNo(),obj.getCustNo(),
	obj.getCustType(),obj.getApplyOperNo(),obj.getAuditOperNo(),obj.getAcctOperNo(),
	obj.getRemark(),obj.getCustAccount(),obj.getCustBranchNo(),obj.getCustBranchName(),
	obj.getCustBankNo(),obj.getCustBankName(),obj.getTradeAcct(),obj.getTradeAcctName(),
	obj.getSaleId(),obj.getProdAttr(),obj.getBranchNo(),obj.getTradeAcctType(),
	obj.getTradeAcctOrg(),obj.getCustOrgCode(),obj.getOrderId(),obj.getApplyStatus(),
	rebuyId);
}

public RebuyApplyInfo getRebuyApplyInfo(String rebuyId) throws SQLException {
	IDB session = DBFactory.getDB();
	RebuyApplyInfo obj = (RebuyApplyInfo)session.getObject("select * from trebuy_apply_info where rebuy_id=?",RebuyApplyInfo.class,rebuyId);
	return obj;
}

public RebuyApplyInfo getRebuyApplyInfo(String rebuyId,	RebuySearchBean searchBean) throws SQLException {
	IDB session = DBFactory.getDB();
	RebuyApplyInfo obj = (RebuyApplyInfo)session.getObject("select * from trebuy_apply_info where rebuy_id=?",RebuyApplyInfo.class,rebuyId);
	if(searchBean == null) return obj;
	sumApplyInfo(obj, searchBean);
	return obj;
}

/**
 * 根据批次状态查询批次信息（申请）
 * @param status
 * @return
 * @throws SQLException 
 */
public List<RebuyApplyInfo> getRebuyApplyListBySearchBeanOnlyForApply(Page page, RebuySearchBean searchBean) throws SQLException{
	IDB session = DBFactory.getDB();
	StringBuilder sbc = new StringBuilder("select count(*) from trebuy_apply_info where 1=1 ");
	StringBuilder sb = new StringBuilder("select * from trebuy_apply_info where 1=1 ");
	if(searchBean!=null){
		if(StringUtils.isNotEmpty(searchBean.getApplyStatus())){
			sb.append("and apply_status='"+searchBean.getApplyStatus()+"'");
			sbc.append("and apply_status='"+searchBean.getApplyStatus()+"'");
		}
		if(StringUtils.isNotEmpty(searchBean.getBillClass())){
            sb.append("and bill_class='"+searchBean.getBillClass()+"'");
            sbc.append("and bill_class='"+searchBean.getBillClass()+"'");
        }
		if(StringUtils.isNotEmpty(searchBean.getIsInner())){
            sb.append("and is_inner='"+searchBean.getIsInner()+"'");
            sbc.append("and is_inner='"+searchBean.getIsInner()+"'");
        }
		if(StringUtils.isNotEmpty(searchBean.getBranchNo())){
            sb.append("and branch_no='"+searchBean.getBranchNo()+"'");
            sbc.append("and branch_no='"+searchBean.getBranchNo()+"'");
        }
	}
	sb.append("order by rebuy_id desc");
	String countSql = sbc.toString();
	String querySql = sb.toString();
	//分页开始位置
	int startIndex = (page.getCurrentPage()-1)*page.getShowCount()+1;
	if(startIndex<0)
		startIndex = 0;
	int count =0;
	List<RebuyApplyInfo> applyList=null;
	// 获得并返回本次查询的总条数
    count = session.account(countSql);
    System.out.println(querySql);
    applyList=session.getObjectListForPage(querySql, RebuyApplyInfo.class,startIndex, page.getShowCount());
	page.setTotalResult(count);
	
	for(RebuyApplyInfo apply:applyList){
	    searchBean.setRebuyId(apply.getRebuyId());
	    sumApplyInfo(apply, searchBean);
	}
	return applyList;
}

/**
 * 功能描述：根据条件查询批次列表
 * @param page
 * @param query
 * @return
 * @throws SQLException
 */
public List<RebuyApplyInfo> getRebuyApplyListBySearchBean(Page page,RebuySearchBean searchBean) throws SQLException{
	IDB session = DBFactory.getDB();
	String baseSql="select distinct apply.* from trebuy_apply_info apply,trebuy_bill_info bill where  bill.rebuy_id = apply.rebuy_id";
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
	List<RebuyApplyInfo> applyList=null;
	// 获得并返回本次查询的总条数
	if(qc.getParameterValues() ==null ||qc.getParameterValues().size()==0){//什么条件都不带的情况
	    count = session.account(qc.getCountSql("distinct apply.rebuy_id"));
	    applyList=session.getObjectListForPage(allsql, RebuyApplyInfo.class,startIndex, page.getShowCount());
	}else{
	    count = session.accountByList(qc.getCountSql("distinct apply.rebuy_id"), qc.getParameterValues()); 
	    applyList=session.getObjectListByListForPage(allsql,RebuyApplyInfo.class, startIndex, page.getShowCount(), qc.getParameterValues());
	}
	page.setTotalResult(count);
	
	for(RebuyApplyInfo apply:applyList){
	    searchBean.setRebuyId(apply.getRebuyId());
	    sumApplyInfo(apply, searchBean);
	}
	return applyList;
}

public void sumApplyInfo(RebuyApplyInfo apply,RebuySearchBean searchBean){
    StringBuilder sb = new StringBuilder("select count(bill.rebuymx_id)||'' c_num,sum(bill.bill_money),sum(bill.pay_money),sum(bill.interest) from trebuy_bill_info bill where 1=1 ");
    if(searchBean != null){
        if(StringUtils.isNotEmpty(searchBean.getRebuyId())){
            sb.append("and bill.rebuy_id='"+searchBean.getRebuyId()+"'");
        }
        if(StringUtils.isNotEmpty(searchBean.getBillClass())){
            sb.append("and bill.bill_class='"+searchBean.getBillClass()+"'");
        }
        if(StringUtils.isNotEmpty(searchBean.getBillType())){
            sb.append("and bill.bill_type='"+searchBean.getBillType()+"'");
        }
        if(StringUtils.isNotEmpty(searchBean.getOperStatus())){
            sb.append("and bill.oper_status='"+searchBean.getOperStatus()+"'");
        }
        if(StringUtils.isNotEmpty(searchBean.getApplyOperNo())){
            sb.append("and bill.apply_oper_no='"+searchBean.getApplyOperNo()+"'");
        }
        if(StringUtils.isNotEmpty(searchBean.getAuditOperNo())){
            sb.append("and bill.audit_oper_no='"+searchBean.getAuditOperNo()+"'");
        }
        if(StringUtils.isNotEmpty(searchBean.getAcctOperNo())){
            sb.append("and bill.acct_oper_no='"+searchBean.getAcctOperNo()+"'");
        }
        if(StringUtils.isNotEmpty(searchBean.getAccountDate())){
            sb.append("and bill.account_date='"+searchBean.getAccountDate()+"'");
        }
        if(searchBean.getOpers()!=null){
            String statuses = StringUtils.join(searchBean.getOpers(), "','");
            sb.append("and bill.oper_status in ('"+statuses+"')");
        }
    }
    IDB session = DBFactory.getDB();
    try {
        IData data=session.getData(sb.toString());
        apply.setTotalNum( data.getString(1));
        apply.setTotalMoney(MoneyUtils.moneyToString(data.getDouble(2)) );
        apply.setActualAmount(data.getString(3));
	    apply.setTotalInterest(data.getString(4));
    } catch (SQLException e) {
        e.printStackTrace();
    }
    
}


public RebuyApplyInfo sumApplyInfo(RebuyApplyInfo batch, String[] idArr) throws SQLException {
	IDB session = DBFactory.getDB();
	StringBuffer sb = new StringBuffer("select count(bill.rebuymx_id),sum(bill.bill_money),sum(bill.interest),sum(bill.pay_money) " +
					"  from trebuy_bill_info bill where bill.rebuymx_id in(");
	for(int i=0;i<idArr.length-1;i++){
		sb.append(idArr[i]+",");
	}
	sb.append(idArr[idArr.length-1]);
	sb.append(")");
    IData data=session.getData(sb.toString());
    batch.setTotalNum( data.getString(1));
    batch.setTotalMoney(MoneyUtils.moneyToString(data.getDouble(2)) );
    batch.setTotalInterest(data.getString(3));
    batch.setActualAmount(data.getString(4));
	return batch;
}


/**
 * 根据批次ids删除批次信息
 * @param ids
 * @return
 * @throws SQLException
 */
public int updateApplyStatusByIds(String[] ids,String status) throws SQLException {
	IDB session = DBFactory.getDB();
	StringBuffer sb = new StringBuffer("update trebuy_apply_info set apply_status='"+status+"' where rebuy_id in(");
	for(int i=0;i<ids.length-1;i++){
		sb.append(ids[i]+",");
	}
	sb.append(ids[ids.length-1]);
	sb.append(")");
	return session.execute(sb.toString());
}

/**
 * 获取某批次下指定状态的票据张数
 * @param id
 * @param curStatus
 * @return
 * @throws SQLException
 */
public int getInfoCountByRebuyIdAndStatus(String rebuyId, String[] curStatus) throws SQLException {
	IDB session = DBFactory.getDB();
	StringBuffer sb = new StringBuffer("select count(bill.rebuymx_id) from trebuy_bill_info bill, " +
			"trebuy_apply_info apply where bill.rebuy_id=apply.rebuy_id and apply.rebuy_id ='");
	sb.append(rebuyId);
	sb.append("' and bill.oper_status in('");
	
	for(int i=0;i<curStatus.length-1;i++){
		sb.append(curStatus[i]+"','");
	}
	sb.append(curStatus[curStatus.length-1]);
	sb.append("')");
	int count = session.account(sb.toString());
	return count;
}

/**
 * 更新批次状态为指定状态
 * @param id
 * @param status
 * @return
 * @throws SQLException
 */
public int changeApplyStatusById(String rebuyId, String status) throws SQLException {
	IDB session = DBFactory.getDB();
	String sql = "update trebuy_apply_info set apply_status='"+status+"' where rebuy_id='"+rebuyId+"'";
	return session.execute(sql);
}
/**根据billtype的数字获取对应的billtype字符串*/
public String getBillTypeStringbybilltype(String billtype)throws SQLException {
	IDB session = DBFactory.getDB();
	Dict objs = session.getObject("select * from vdict where key_no='K_BILL_TYPE' and item_code=?",Dict.class,billtype);
	
	return objs.getItemValue();
}
/**根据billclass的数字获取对应的billclass字符串*/
public String getBillClassStringbybillclass(String billclass)throws SQLException {
	IDB session = DBFactory.getDB();
	Dict objs = session.getObject("select * from vdict where key_no='K_BILL_CLASS' and item_code=?",Dict.class,billclass);
	
	return objs.getItemValue();
}

}
