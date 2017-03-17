/********************************************
 * 文件名称: SaleBillInfoDao.java
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
package com.herongtech.console.domain.sale.dao;

import java.sql.SQLException;
import java.util.List;

import com.herongtech.commons.tools.BeanFormat;
import com.herongtech.console.core.common.json.Page;
import com.herongtech.console.core.common.search.QueryCondition;
import com.herongtech.console.domain.sale.bean.SaleBillInfo;
import com.herongtech.console.domain.sale.bean.SaleSearchBean;
import com.herongtech.db.impl.session.DBFactory;
import com.herongtech.db.interfaces.IDB;

public class SaleBillInfoDao {
	/*自动生成的方法**************************************************************************************************/
	public int addSaleBillInfo(SaleBillInfo obj) throws SQLException {
		BeanFormat.format(obj);
		IDB session = DBFactory.getDB();
		return session.execute("insert into tsale_bill_info(salemx_id,bill_type,bill_no,issue_dt,due_dt,remitter,remitter_acct,remitter_cust_no,remitter_bank_name,remitter_bank_no,bill_money,acceptor,payee_name,payee_bank_name,payee_acct,bill_before_owner,bill_owner,bill_class,bill_source,oper_status,sale_id,rgct_id,branch_no,cust_account,is_same_city,interest,delay_days,gale_date,interest_days,receive_money,remark,create_date,create_time,drawee_addr,payee_bank_no,is_accpt,rate,rate_type,buy_dept_no,sale_pract_id,buyback_money,is_online,forbid_flag,bill_source_id,is_delay_in,ex_serial,is_buyback,sale_square_id,yz_source,bidir_sale_hist_flag,pay_trade_no,buy_money,prior_src,apply_oper_no,audit_oper_no,acct_oper_no,apply_teller_dt,apply_teller_time,audit_teller_dt,audit_teller_time,acct_teller_dt,acct_teller_time,gath_mney_date,account_date,cust_manage,cust_manage_dept_no,work_branch_no,cost_rate,delay_type,bill_storage_org,is_inner,resale_due_dt,interest_buyback,paymoney_buyback,prior_src_id,buy_type,cust_manage_name,cust_manage_dept_name,work_branch_name,resale_open_dt,old_rate,old_gale_date,batch_id,old_interest,old_inst_cal_days,acceptor_bank_no,acceptor_bank_name,old_disc_dt,old_file_no,cur_status,if_recours_aviod,audit_reason)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
		obj.getSalemxId(),obj.getBillType(),obj.getBillNo(),obj.getIssueDt(),obj.getDueDt(),
		obj.getRemitter(),obj.getRemitterAcct(),obj.getRemitterCustNo(),obj.getRemitterBankName(),
		obj.getRemitterBankNo(),obj.getBillMoney(),obj.getAcceptor(),obj.getPayeeName(),
		obj.getPayeeBankName(),obj.getPayeeAcct(),obj.getBillBeforeOwner(),obj.getBillOwner(),
		obj.getBillClass(),obj.getBillSource(),obj.getOperStatus(),obj.getSaleId(),
		obj.getRgctId(),obj.getBranchNo(),obj.getCustAccount(),obj.getIsSameCity(),
		obj.getInterest(),obj.getDelayDays(),obj.getGaleDate(),obj.getInterestDays(),
		obj.getReceiveMoney(),obj.getRemark(),obj.getCreateDate(),obj.getCreateTime(),
		obj.getDraweeAddr(),obj.getPayeeBankNo(),obj.getIsAccpt(),obj.getRate(),
		obj.getRateType(),obj.getBuyDeptNo(),obj.getSalePractId(),obj.getBuybackMoney(),
		obj.getIsOnline(),obj.getForbidFlag(),obj.getBillSourceId(),obj.getIsDelayIn(),
		obj.getExSerial(),obj.getIsBuyback(),obj.getSaleSquareId(),obj.getYzSource(),
		obj.getBidirSaleHistFlag(),obj.getPayTradeNo(),obj.getBuyMoney(),obj.getPriorSrc(),
		obj.getApplyOperNo(),obj.getAuditOperNo(),obj.getAcctOperNo(),obj.getApplyTellerDt(),
		obj.getApplyTellerTime(),obj.getAuditTellerDt(),obj.getAuditTellerTime(),obj.getAcctTellerDt(),
		obj.getAcctTellerTime(),obj.getGathMneyDate(),obj.getAccountDate(),obj.getCustManage(),
		obj.getCustManageDeptNo(),obj.getWorkBranchNo(),obj.getCostRate(),obj.getDelayType(),
		obj.getBillStorageOrg(),obj.getIsInner(),obj.getResaleDueDt(),obj.getInterestBuyback(),
		obj.getPaymoneyBuyback(),obj.getPriorSrcId(),obj.getBuyType(),obj.getCustManageName(),
		obj.getCustManageDeptName(),obj.getWorkBranchName(),obj.getResaleOpenDt(),obj.getOldRate(),
		obj.getOldGaleDate(),obj.getBatchId(),obj.getOldInterest(),obj.getOldInstCalDays(),
		obj.getAcceptorBankNo(),obj.getAcceptorBankName(),obj.getOldDiscDt(),obj.getOldFileNo(),
		obj.getCurStatus(),obj.getIfRecoursAviod(),obj.getAuditReason());
	}

	public int deleteSaleBillInfo(SaleBillInfo obj) throws SQLException {
		IDB session = DBFactory.getDB();
		return session.execute("delete from tsale_bill_info where salemx_id=?",obj.getSalemxId());
	}

	public int deleteSaleBillInfo(String salemxId) throws SQLException {
		IDB session = DBFactory.getDB();
		return session.execute("delete from tsale_bill_info where salemx_id=?",salemxId);
	}

	public int modifySaleBillInfo(SaleBillInfo obj) throws SQLException {
		BeanFormat.format(obj);
		IDB session = DBFactory.getDB();
		return session.execute("update tsale_bill_info set bill_type=?,bill_no=?,issue_dt=?,due_dt=?,remitter=?,remitter_acct=?,remitter_cust_no=?,remitter_bank_name=?,remitter_bank_no=?,bill_money=?,acceptor=?,payee_name=?,payee_bank_name=?,payee_acct=?,bill_before_owner=?,bill_owner=?,bill_class=?,bill_source=?,oper_status=?,sale_id=?,rgct_id=?,branch_no=?,cust_account=?,is_same_city=?,interest=?,delay_days=?,gale_date=?,interest_days=?,receive_money=?,remark=?,create_date=?,create_time=?,drawee_addr=?,payee_bank_no=?,is_accpt=?,rate=?,rate_type=?,buy_dept_no=?,sale_pract_id=?,buyback_money=?,is_online=?,forbid_flag=?,bill_source_id=?,is_delay_in=?,ex_serial=?,is_buyback=?,sale_square_id=?,yz_source=?,bidir_sale_hist_flag=?,pay_trade_no=?,buy_money=?,prior_src=?,apply_oper_no=?,audit_oper_no=?,acct_oper_no=?,apply_teller_dt=?,apply_teller_time=?,audit_teller_dt=?,audit_teller_time=?,acct_teller_dt=?,acct_teller_time=?,gath_mney_date=?,account_date=?,cust_manage=?,cust_manage_dept_no=?,work_branch_no=?,cost_rate=?,delay_type=?,bill_storage_org=?,is_inner=?,resale_due_dt=?,interest_buyback=?,paymoney_buyback=?,prior_src_id=?,buy_type=?,cust_manage_name=?,cust_manage_dept_name=?,work_branch_name=?,resale_open_dt=?,old_rate=?,old_gale_date=?,batch_id=?,old_interest=?,old_inst_cal_days=?,acceptor_bank_no=?,acceptor_bank_name=?,old_disc_dt=?,old_file_no=?,cur_status=?,if_recours_aviod=?,audit_reason=? where salemx_id=?",
		obj.getBillType(),obj.getBillNo(),obj.getIssueDt(),obj.getDueDt(),
		obj.getRemitter(),obj.getRemitterAcct(),obj.getRemitterCustNo(),obj.getRemitterBankName(),
		obj.getRemitterBankNo(),obj.getBillMoney(),obj.getAcceptor(),obj.getPayeeName(),
		obj.getPayeeBankName(),obj.getPayeeAcct(),obj.getBillBeforeOwner(),obj.getBillOwner(),
		obj.getBillClass(),obj.getBillSource(),obj.getOperStatus(),obj.getSaleId(),
		obj.getRgctId(),obj.getBranchNo(),obj.getCustAccount(),obj.getIsSameCity(),
		obj.getInterest(),obj.getDelayDays(),obj.getGaleDate(),obj.getInterestDays(),
		obj.getReceiveMoney(),obj.getRemark(),obj.getCreateDate(),obj.getCreateTime(),
		obj.getDraweeAddr(),obj.getPayeeBankNo(),obj.getIsAccpt(),obj.getRate(),
		obj.getRateType(),obj.getBuyDeptNo(),obj.getSalePractId(),obj.getBuybackMoney(),
		obj.getIsOnline(),obj.getForbidFlag(),obj.getBillSourceId(),obj.getIsDelayIn(),
		obj.getExSerial(),obj.getIsBuyback(),obj.getSaleSquareId(),obj.getYzSource(),
		obj.getBidirSaleHistFlag(),obj.getPayTradeNo(),obj.getBuyMoney(),obj.getPriorSrc(),
		obj.getApplyOperNo(),obj.getAuditOperNo(),obj.getAcctOperNo(),obj.getApplyTellerDt(),
		obj.getApplyTellerTime(),obj.getAuditTellerDt(),obj.getAuditTellerTime(),obj.getAcctTellerDt(),
		obj.getAcctTellerTime(),obj.getGathMneyDate(),obj.getAccountDate(),obj.getCustManage(),
		obj.getCustManageDeptNo(),obj.getWorkBranchNo(),obj.getCostRate(),obj.getDelayType(),
		obj.getBillStorageOrg(),obj.getIsInner(),obj.getResaleDueDt(),obj.getInterestBuyback(),
		obj.getPaymoneyBuyback(),obj.getPriorSrcId(),obj.getBuyType(),obj.getCustManageName(),
		obj.getCustManageDeptName(),obj.getWorkBranchName(),obj.getResaleOpenDt(),obj.getOldRate(),
		obj.getOldGaleDate(),obj.getBatchId(),obj.getOldInterest(),obj.getOldInstCalDays(),
		obj.getAcceptorBankNo(),obj.getAcceptorBankName(),obj.getOldDiscDt(),obj.getOldFileNo(),
		obj.getCurStatus(),obj.getIfRecoursAviod(),obj.getAuditReason(),obj.getSalemxId());
	}

	public int modifySaleBillInfo(SaleBillInfo obj,String salemxId) throws SQLException {
		BeanFormat.format(obj);
		IDB session = DBFactory.getDB();
		return session.execute("update tsale_bill_info set bill_type=?,bill_no=?,issue_dt=?,due_dt=?,remitter=?,remitter_acct=?,remitter_cust_no=?,remitter_bank_name=?,remitter_bank_no=?,bill_money=?,acceptor=?,payee_name=?,payee_bank_name=?,payee_acct=?,bill_before_owner=?,bill_owner=?,bill_class=?,bill_source=?,oper_status=?,sale_id=?,rgct_id=?,branch_no=?,cust_account=?,is_same_city=?,interest=?,delay_days=?,gale_date=?,interest_days=?,receive_money=?,remark=?,create_date=?,create_time=?,drawee_addr=?,payee_bank_no=?,is_accpt=?,rate=?,rate_type=?,buy_dept_no=?,sale_pract_id=?,buyback_money=?,is_online=?,forbid_flag=?,bill_source_id=?,is_delay_in=?,ex_serial=?,is_buyback=?,sale_square_id=?,yz_source=?,bidir_sale_hist_flag=?,pay_trade_no=?,buy_money=?,prior_src=?,apply_oper_no=?,audit_oper_no=?,acct_oper_no=?,apply_teller_dt=?,apply_teller_time=?,audit_teller_dt=?,audit_teller_time=?,acct_teller_dt=?,acct_teller_time=?,gath_mney_date=?,account_date=?,cust_manage=?,cust_manage_dept_no=?,work_branch_no=?,cost_rate=?,delay_type=?,bill_storage_org=?,is_inner=?,resale_due_dt=?,interest_buyback=?,paymoney_buyback=?,prior_src_id=?,buy_type=?,cust_manage_name=?,cust_manage_dept_name=?,work_branch_name=?,resale_open_dt=?,old_rate=?,old_gale_date=?,batch_id=?,old_interest=?,old_inst_cal_days=?,acceptor_bank_no=?,acceptor_bank_name=?,old_disc_dt=?,old_file_no=?,cur_status=?,if_recours_aviod=?,audit_reason=? where salemx_id=?",
		obj.getBillType(),obj.getBillNo(),obj.getIssueDt(),obj.getDueDt(),
		obj.getRemitter(),obj.getRemitterAcct(),obj.getRemitterCustNo(),obj.getRemitterBankName(),
		obj.getRemitterBankNo(),obj.getBillMoney(),obj.getAcceptor(),obj.getPayeeName(),
		obj.getPayeeBankName(),obj.getPayeeAcct(),obj.getBillBeforeOwner(),obj.getBillOwner(),
		obj.getBillClass(),obj.getBillSource(),obj.getOperStatus(),obj.getSaleId(),
		obj.getRgctId(),obj.getBranchNo(),obj.getCustAccount(),obj.getIsSameCity(),
		obj.getInterest(),obj.getDelayDays(),obj.getGaleDate(),obj.getInterestDays(),
		obj.getReceiveMoney(),obj.getRemark(),obj.getCreateDate(),obj.getCreateTime(),
		obj.getDraweeAddr(),obj.getPayeeBankNo(),obj.getIsAccpt(),obj.getRate(),
		obj.getRateType(),obj.getBuyDeptNo(),obj.getSalePractId(),obj.getBuybackMoney(),
		obj.getIsOnline(),obj.getForbidFlag(),obj.getBillSourceId(),obj.getIsDelayIn(),
		obj.getExSerial(),obj.getIsBuyback(),obj.getSaleSquareId(),obj.getYzSource(),
		obj.getBidirSaleHistFlag(),obj.getPayTradeNo(),obj.getBuyMoney(),obj.getPriorSrc(),
		obj.getApplyOperNo(),obj.getAuditOperNo(),obj.getAcctOperNo(),obj.getApplyTellerDt(),
		obj.getApplyTellerTime(),obj.getAuditTellerDt(),obj.getAuditTellerTime(),obj.getAcctTellerDt(),
		obj.getAcctTellerTime(),obj.getGathMneyDate(),obj.getAccountDate(),obj.getCustManage(),
		obj.getCustManageDeptNo(),obj.getWorkBranchNo(),obj.getCostRate(),obj.getDelayType(),
		obj.getBillStorageOrg(),obj.getIsInner(),obj.getResaleDueDt(),obj.getInterestBuyback(),
		obj.getPaymoneyBuyback(),obj.getPriorSrcId(),obj.getBuyType(),obj.getCustManageName(),
		obj.getCustManageDeptName(),obj.getWorkBranchName(),obj.getResaleOpenDt(),obj.getOldRate(),
		obj.getOldGaleDate(),obj.getBatchId(),obj.getOldInterest(),obj.getOldInstCalDays(),
		obj.getAcceptorBankNo(),obj.getAcceptorBankName(),obj.getOldDiscDt(),obj.getOldFileNo(),
		obj.getCurStatus(),obj.getIfRecoursAviod(),obj.getAuditReason(),salemxId);
	}

	public SaleBillInfo getSaleBillInfo(String salemxId) throws SQLException {
		IDB session = DBFactory.getDB();
		SaleBillInfo obj = (SaleBillInfo)session.getObject("select * from tsale_bill_info where salemx_id=?",SaleBillInfo.class,salemxId);
		return obj;
	}
/*自定义方法**************************************************************************************************/
		/**
		 * 根据查询条件获取批次下票据清单
		 * @param searchBean
		 * @return
		 * @throws SQLException
		 */
		public List<SaleBillInfo> getSaleBillListBySearchBean(SaleSearchBean searchBean) throws SQLException{
			IDB session = DBFactory.getDB();
			String baseSql="select bill.* from tsale_bill_info bill";
			QueryCondition qc = new QueryCondition();
			try {
				qc.initFromSearchBean(searchBean);
			} catch (Exception e) {
				e.printStackTrace();
			}
			String sql=qc.getAllSqlString(baseSql);
		     System.out.println(sql);
		     return session.getObjectListByList(sql, SaleBillInfo.class, qc.getParameterValues());
		}
	/**
	 * 根据Page和查询条件获取批次下票据清单
	 * 
	 * @param page
	 * @param query
	 * @return
	 * @throws SQLException
	 */
	public List<SaleBillInfo> getSaleBillListBySearchBeanForPage(Page page,SaleSearchBean query) throws SQLException {
		IDB session = DBFactory.getDB();
		String baseSql="select bill.* from tsale_bill_info bill";
		QueryCondition qc = new QueryCondition();
		try {
			qc.initFromSearchBean(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String sql=qc.getAllSqlString(baseSql);
		System.out.println(sql);
		// 分页开始位置
		int startIndex = page.getCurrentResult();
		// 获得并返回本次查询的总条数
		int count = session.accountByList(qc.getCountSql("bill.salemx_Id"), qc.getParameterValues());
		page.setTotalResult(count);
		return session.getObjectListByListForPage(sql,SaleBillInfo.class, startIndex, page.getShowCount(), qc.getParameterValues());
	}
	
	

	
	/**
	 * 通过明细主键数组 查询转卖明细
	 * @param ids
	 * @return
	 * @throws SQLException
	 */
	public List<SaleBillInfo> getSaleBillInfoByIds(String[] idArr) throws SQLException {
	    IDB session = DBFactory.getDB();
	    StringBuffer sql = new StringBuffer("select * from tsale_bill_info where salemx_Id in(");
	    for(String id:idArr){
	        sql.append("?,");
	    }
	    sql.deleteCharAt(sql.length()-1);
	    sql.append(')');
	    
	    List<SaleBillInfo> obj = session.getObjectList(sql.toString(), SaleBillInfo.class, idArr);
	    return obj;
	}
	
	
	public SaleBillInfo getSaleBillInfoByRgctId(String rgctId,String status) throws SQLException{
		IDB session = DBFactory.getDB();
		SaleBillInfo obj = (SaleBillInfo)session.getObject("select * from tsale_bill_info where oper_status=? and rgct_id=? ORDER BY SALEMX_ID DESC",SaleBillInfo.class,status,rgctId);
		return obj;
	}
	
	public List<SaleBillInfo> getSaleBillInfoByRgctId(String rgctId) throws SQLException{
		IDB session = DBFactory.getDB();
		List<SaleBillInfo> obj = session.getObjectList("select * from tsale_bill_info where rgct_id=? ORDER BY SALEMX_ID DESC",SaleBillInfo.class,rgctId);
		return obj;
	}
	
	public SaleBillInfo getSaleBillInfoByRgcId(String rgctId) throws SQLException{
		IDB session = DBFactory.getDB();
		SaleBillInfo obj = (SaleBillInfo)session.getObject("select * from tsale_bill_info where rgct_id=? ORDER BY SALEMX_ID DESC",SaleBillInfo.class,rgctId);
		return obj;
	}
}
