/********************************************
* 文件名称: SaleApplyInfoDao.java
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
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.herongtech.commons.tools.BeanFormat;
import com.herongtech.console.core.common.json.Page;
import com.herongtech.console.core.common.search.QueryCondition;
import com.herongtech.console.core.util.StatusUtils;
import com.herongtech.console.domain.sale.bean.SaleApplyInfo;
import com.herongtech.console.domain.sale.bean.SaleSearchBean;
import com.herongtech.data.interfaces.data.IData;
import com.herongtech.db.impl.session.DBFactory;
import com.herongtech.db.interfaces.IDB;
public class SaleApplyInfoDao{
	
/*自动生成的增、删、改、查方法*******************************************************************************/
	public int addSaleApplyInfo(SaleApplyInfo obj) throws SQLException {
		BeanFormat.format(obj);
		IDB session = DBFactory.getDB();
		return session.execute("insert into tsale_apply_info(sale_id,batch_no,aim_branch_no,prod_no,bill_type,bill_class,inner_account,sale_dt,sale_time,rebuy_due_dt,rate,rate_type,delay_type,is_dummy,is_inner,bidect_due_dt,delay_days,branch_id,if_bidir_buy,status,create_dt,create_time,oper_no,batch_type,is_redisc,sale_type,buyback_rate,buyback_open_dt,buyback_money,is_online,mock_aim_name,prod_attr,cust_name,financial_cust_no,cust_bank_name,cust_bank_no,fact_bank_no,fact_bank_name,forbid_flag,branch_no,cust_type,bill_storage_brchno,bill_storage_name,bill_storage_brchid,cust_no,prod_attr_no,in_acct_no,in_acct_type,in_acct_name,bill_position_branch,cust_manage,cust_manager_name,dept_no,dept_name,workingads_no,workingads_name,order_id,apply_status)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
		obj.getSaleId(),obj.getBatchNo(),obj.getAimBranchNo(),obj.getProdNo(),obj.getBillType(),
		obj.getBillClass(),obj.getInnerAccount(),obj.getSaleDt(),obj.getSaleTime(),
		obj.getRebuyDueDt(),obj.getRate(),obj.getRateType(),obj.getDelayType(),
		obj.getIsDummy(),obj.getIsInner(),obj.getBidectDueDt(),obj.getDelayDays(),
		obj.getBranchId(),obj.getIfBidirBuy(),obj.getStatus(),obj.getCreateDt(),
		obj.getCreateTime(),obj.getOperNo(),obj.getBatchType(),obj.getIsRedisc(),
		obj.getSaleType(),obj.getBuybackRate(),obj.getBuybackOpenDt(),obj.getBuybackMoney(),
		obj.getIsOnline(),obj.getMockAimName(),obj.getProdAttr(),obj.getCustName(),
		obj.getFinancialCustNo(),obj.getCustBankName(),obj.getCustBankNo(),obj.getFactBankNo(),
		obj.getFactBankName(),obj.getForbidFlag(),obj.getBranchNo(),obj.getCustType(),
		obj.getBillStorageBrchno(),obj.getBillStorageName(),obj.getBillStorageBrchid(),obj.getCustNo(),
		obj.getProdAttrNo(),obj.getInAcctNo(),obj.getInAcctType(),obj.getInAcctName(),
		obj.getBillPositionBranch(),obj.getCustManage(),obj.getCustManagerName(),obj.getDeptNo(),
		obj.getDeptName(),obj.getWorkingadsNo(),obj.getWorkingadsName(),obj.getOrderId(),
		obj.getApplyStatus());
	}

	public int deleteSaleApplyInfo(SaleApplyInfo obj) throws SQLException {
		IDB session = DBFactory.getDB();
		return session.execute("delete from tsale_apply_info where sale_id=?",obj.getSaleId());
	}

	public int deleteSaleApplyInfo(String saleId) throws SQLException {
		IDB session = DBFactory.getDB();
		return session.execute("delete from tsale_apply_info where sale_id=?",saleId);
	}

	public int modifySaleApplyInfo(SaleApplyInfo obj) throws SQLException {
		BeanFormat.format(obj);
		IDB session = DBFactory.getDB();
		return session.execute("update tsale_apply_info set batch_no=?,aim_branch_no=?,prod_no=?,bill_type=?,bill_class=?,inner_account=?,sale_dt=?,sale_time=?,rebuy_due_dt=?,rate=?,rate_type=?,delay_type=?,is_dummy=?,is_inner=?,bidect_due_dt=?,delay_days=?,branch_id=?,if_bidir_buy=?,status=?,create_dt=?,create_time=?,oper_no=?,batch_type=?,is_redisc=?,sale_type=?,buyback_rate=?,buyback_open_dt=?,buyback_money=?,is_online=?,mock_aim_name=?,prod_attr=?,cust_name=?,financial_cust_no=?,cust_bank_name=?,cust_bank_no=?,fact_bank_no=?,fact_bank_name=?,forbid_flag=?,branch_no=?,cust_type=?,bill_storage_brchno=?,bill_storage_name=?,bill_storage_brchid=?,cust_no=?,prod_attr_no=?,in_acct_no=?,in_acct_type=?,in_acct_name=?,bill_position_branch=?,cust_manage=?,cust_manager_name=?,dept_no=?,dept_name=?,workingads_no=?,workingads_name=?,order_id=?,apply_status=? where sale_id=?",
		obj.getBatchNo(),obj.getAimBranchNo(),obj.getProdNo(),obj.getBillType(),
		obj.getBillClass(),obj.getInnerAccount(),obj.getSaleDt(),obj.getSaleTime(),
		obj.getRebuyDueDt(),obj.getRate(),obj.getRateType(),obj.getDelayType(),
		obj.getIsDummy(),obj.getIsInner(),obj.getBidectDueDt(),obj.getDelayDays(),
		obj.getBranchId(),obj.getIfBidirBuy(),obj.getStatus(),obj.getCreateDt(),
		obj.getCreateTime(),obj.getOperNo(),obj.getBatchType(),obj.getIsRedisc(),
		obj.getSaleType(),obj.getBuybackRate(),obj.getBuybackOpenDt(),obj.getBuybackMoney(),
		obj.getIsOnline(),obj.getMockAimName(),obj.getProdAttr(),obj.getCustName(),
		obj.getFinancialCustNo(),obj.getCustBankName(),obj.getCustBankNo(),obj.getFactBankNo(),
		obj.getFactBankName(),obj.getForbidFlag(),obj.getBranchNo(),obj.getCustType(),
		obj.getBillStorageBrchno(),obj.getBillStorageName(),obj.getBillStorageBrchid(),obj.getCustNo(),
		obj.getProdAttrNo(),obj.getInAcctNo(),obj.getInAcctType(),obj.getInAcctName(),
		obj.getBillPositionBranch(),obj.getCustManage(),obj.getCustManagerName(),obj.getDeptNo(),
		obj.getDeptName(),obj.getWorkingadsNo(),obj.getWorkingadsName(),obj.getOrderId(),
		obj.getApplyStatus(),obj.getSaleId());
	}

	public int modifySaleApplyInfo(SaleApplyInfo obj,String saleId) throws SQLException {
		BeanFormat.format(obj);
		IDB session = DBFactory.getDB();
		return session.execute("update tsale_apply_info set batch_no=?,aim_branch_no=?,prod_no=?,bill_type=?,bill_class=?,inner_account=?,sale_dt=?,sale_time=?,rebuy_due_dt=?,rate=?,rate_type=?,delay_type=?,is_dummy=?,is_inner=?,bidect_due_dt=?,delay_days=?,branch_id=?,if_bidir_buy=?,status=?,create_dt=?,create_time=?,oper_no=?,batch_type=?,is_redisc=?,sale_type=?,buyback_rate=?,buyback_open_dt=?,buyback_money=?,is_online=?,mock_aim_name=?,prod_attr=?,cust_name=?,financial_cust_no=?,cust_bank_name=?,cust_bank_no=?,fact_bank_no=?,fact_bank_name=?,forbid_flag=?,branch_no=?,cust_type=?,bill_storage_brchno=?,bill_storage_name=?,bill_storage_brchid=?,cust_no=?,prod_attr_no=?,in_acct_no=?,in_acct_type=?,in_acct_name=?,bill_position_branch=?,cust_manage=?,cust_manager_name=?,dept_no=?,dept_name=?,workingads_no=?,workingads_name=?,order_id=?,apply_status=? where sale_id=?",
		obj.getBatchNo(),obj.getAimBranchNo(),obj.getProdNo(),obj.getBillType(),
		obj.getBillClass(),obj.getInnerAccount(),obj.getSaleDt(),obj.getSaleTime(),
		obj.getRebuyDueDt(),obj.getRate(),obj.getRateType(),obj.getDelayType(),
		obj.getIsDummy(),obj.getIsInner(),obj.getBidectDueDt(),obj.getDelayDays(),
		obj.getBranchId(),obj.getIfBidirBuy(),obj.getStatus(),obj.getCreateDt(),
		obj.getCreateTime(),obj.getOperNo(),obj.getBatchType(),obj.getIsRedisc(),
		obj.getSaleType(),obj.getBuybackRate(),obj.getBuybackOpenDt(),obj.getBuybackMoney(),
		obj.getIsOnline(),obj.getMockAimName(),obj.getProdAttr(),obj.getCustName(),
		obj.getFinancialCustNo(),obj.getCustBankName(),obj.getCustBankNo(),obj.getFactBankNo(),
		obj.getFactBankName(),obj.getForbidFlag(),obj.getBranchNo(),obj.getCustType(),
		obj.getBillStorageBrchno(),obj.getBillStorageName(),obj.getBillStorageBrchid(),obj.getCustNo(),
		obj.getProdAttrNo(),obj.getInAcctNo(),obj.getInAcctType(),obj.getInAcctName(),
		obj.getBillPositionBranch(),obj.getCustManage(),obj.getCustManagerName(),obj.getDeptNo(),
		obj.getDeptName(),obj.getWorkingadsNo(),obj.getWorkingadsName(),obj.getOrderId(),
		obj.getApplyStatus(),saleId);
	}

	public SaleApplyInfo getSaleApplyInfo(String saleId) throws SQLException {
		IDB session = DBFactory.getDB();
		SaleApplyInfo obj = (SaleApplyInfo)session.getObject("select * from tsale_apply_info where sale_id=?",SaleApplyInfo.class,saleId);
		return obj;
	}
/*自定义方法*******************************************************************************/
	/**
	 * 修改批次状态
	 * @param applyStatus
	 * @param saleId
	 * @return
	 * @throws SQLException
	 */
	public int updateSaleApplyStatus(String applyStatus,String saleId) throws SQLException{
		IDB session = DBFactory.getDB();
		return session.execute("update tsale_apply_info set apply_status=? where sale_id=?",applyStatus,saleId);
	}
	/**
	 * 根据查询条件获取批次信息（能够查询批次下票据总数、票据总金额）
	 * @param searchBean
	 * @return
	 * @throws SQLException
	 */
	public SaleApplyInfo getSaleApplyInfo(SaleSearchBean searchBean) throws SQLException{
		IDB session = DBFactory.getDB();
		SaleApplyInfo obj = (SaleApplyInfo)session.getObject("select * from tsale_apply_info where sale_id=?",SaleApplyInfo.class,searchBean.getSaleId());
		sumApplyInfo(obj, searchBean);
		return obj;
	}
/**
 * 根据条件查询批次列表：转卖申请使用
 * @param page
 * @param searchBean
 * @return
 * @throws Exception 
 */
	public List<SaleApplyInfo> getSaleApplyListBySearchBeanForApply(Page page,SaleSearchBean searchBean) throws Exception{
		IDB session = DBFactory.getDB();
		String baseSql = "select distinct apply.* from tsale_apply_info apply left join tsale_bill_info bill on apply.sale_id=bill.sale_id where 1=1 ";
		QueryCondition qc=new QueryCondition();
		try {
			qc.initFromSearchBean(searchBean);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String allSql = qc.getAllSqlString(baseSql);
		//分页开始位置
		int startIndex = (page.getCurrentPage()-1)*page.getShowCount()+1;
		if(startIndex<0)
			startIndex = 0;
		
		List<SaleApplyInfo> applyList=null;
		int count = 0;
		count = session.accountByList(qc.getCountSql("distinct apply.sale_id"), qc.getParameterValues()); 
		applyList=session.getObjectListByListForPage(allSql,SaleApplyInfo.class, startIndex, page.getShowCount(), qc.getParameterValues());
		page.setTotalResult(count);
		searchBean.setStatusArray(StatusUtils.queryStatus("SaleApplyController", "batchManege", null));//把这个条件放在这里，避免了当删除批次的最后一张票时，批次就查询不到了(即批次下有票，但是票的状态不是BS211)
		for(SaleApplyInfo apply:applyList){
			sumApplyInfo(apply, searchBean);
		}
		System.out.println(allSql);
		return applyList;
	}
/**
 * 根据条件查询批次列表:关联表tsale_bill_info 查询，用于查询待审核批次列表、查询待记账批次列表
 * @param page
 * @param searchBean
 * @return
 * @throws SQLException
 */
	public List<SaleApplyInfo> getSaleApplyListForCondition(Page page,SaleSearchBean searchBean) throws SQLException{
		IDB session = DBFactory.getDB();
		String baseSql = "select distinct apply.* from tsale_apply_info apply, tsale_bill_info bill where bill.sale_id = apply.sale_id";
		QueryCondition qc=new QueryCondition();
		try {
			qc.initFromSearchBean(searchBean);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String allSql = qc.getAllSqlString(baseSql);
		//分页开始位置
		int startIndex = (page.getCurrentPage()-1)*page.getShowCount()+1;
		if(startIndex<0)
			startIndex = 0;
		
		List<SaleApplyInfo> applyList=null;
		int count = 0;
		// 获得并返回本次查询的总条数
		if(qc.getParameterValues() ==null ||qc.getParameterValues().size()==0){//什么条件都不带的情况
		    count = session.account(qc.getCountSql("distinct apply.sale_id"));
		    applyList=session.getObjectListForPage(allSql, SaleApplyInfo.class,startIndex, page.getShowCount());
		}else{
		    count = session.accountByList(qc.getCountSql("distinct apply.sale_id"), qc.getParameterValues()); 
		    applyList=session.getObjectListByListForPage(allSql,SaleApplyInfo.class, startIndex, page.getShowCount(), qc.getParameterValues());
		}
		page.setTotalResult(count);
		for(SaleApplyInfo apply:applyList){
		    searchBean.setSaleId(apply.getSaleId());
		    sumApplyInfo(apply, searchBean);
		}
		return applyList;
	}
	 public void sumApplyInfo(SaleApplyInfo apply,SaleSearchBean searchBean,QueryCondition qc){
	        try {
				qc.initFromSearchBean(searchBean);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
	        String sql = qc.getCountSql2("bill.salemx_id", "bill.bill_money");
	        
	        IDB session = DBFactory.getDB();
	        try {
	        	System.out.println(sql);
	            IData data=session.getDataByList(sql, qc.getParameterValues());
	            apply.setTotalSize( data.getInt(1));
	            apply.setSumMoney(data.getDouble(2));
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        
	    }
	 /**
		 * 票据张数，与票据金额汇总
		 * @param applyList
		 * @param query
		 * @throws SQLException 
		 */
	    public void sumApplyInfo(SaleApplyInfo apply,SaleSearchBean query) throws SQLException{
	        if(apply == null) return;
	        IDB session = DBFactory.getDB();
	        //批次汇总票据张数，与票据金额sql
	        StringBuilder sb = new StringBuilder("select count(bill.salemx_id)||'',");
	        sb.append("sum(bill.bill_money),sum(bill.interest),sum(bill.receive_money) from tsale_bill_info bill where bill.sale_id = ? ");
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
		        if(query.getStatusArray()!=null){
		            sb.append(" and bill.oper_status in (");
		            for (Object status : query.getStatusArray()) {
		            	sb.append("?,");
		        		param.add(status);
		        	}
		            sb.deleteCharAt(sb.length()-1);
		            sb.append(')');
		        }
		    }
		    //删除第一位 为第一个问号赋值
		    param.remove(0);
		    // 为第一个问号赋值
		    param.add(0, apply.getSaleId());
		    System.out.println(sb.toString());
		    IData data=session.getDataByList(sb.toString(), param);
//		    System.out.println(sb.toString());
		    apply.setTotalSize( data.getInt(1));
            apply.setSumMoney(data.getDouble(2));
            apply.setSumInterest(data.getDouble(3));
            apply.setSumReceiveMoney(data.getDouble(4));
	    }

}
