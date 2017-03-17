/********************************************
* 文件名称: AcptApplyInfoDao.java
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
package com.herongtech.console.domain.acpt.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.herongtech.commons.tools.BeanFormat;
import com.herongtech.console.core.common.json.Page;
import com.herongtech.console.core.common.search.QueryCondition;
import com.herongtech.console.domain.acpt.bean.AcptApplyInfo;
import com.herongtech.console.domain.acpt.bean.AcptQueryCondition;
import com.herongtech.data.interfaces.data.IData;
import com.herongtech.db.impl.session.DBFactory;
import com.herongtech.db.interfaces.IDB;
public class AcptApplyInfoDao{

public int addAcptApplyInfo(AcptApplyInfo obj) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("insert into tacpt_apply_info(acpt_id,batch_no,branch_no,protocal_no,fac_no,fkjjh,prod_no,remitter,remitter_cust_no,remitter_acct,remitter_bank_no,remitter_bank_name,issue_branch_no,drawee_bank_no,drawee_bank_name,drawee_addr,issue_dt,due_dt,bill_class,bill_type,total_count,total_amt,total_amt_for_deduct,total_grant_amt,freeze_total_grant_amt,account_no1,account_name1,account_branch_no1,account_branch_name1,grant_amt1,account_type1,grant_no1,freeze_grant_amt1,account_no2,account_name2,account_branch_no2,account_branch_name2,grant_amt2,account_type2,grant_no2,freeze_grant_amt2,account_no3,account_name3,account_branch_no3,account_branch_name3,grant_amt3,account_type3,grant_no3,freeze_grant_amt3,account_no4,account_name4,account_branch_no4,account_branch_name4,grant_amt4,account_type4,grant_no4,freeze_grant_amt4,account_no5,account_name5,account_branch_no5,account_branch_name5,grant_amt5,account_type5,grant_no5,freeze_grant_amt5,busi_division,dept_no,cust_manager,audit_channel,loan_system,batch_status,deduct_amt,loan_amt,loan_dt,loan_time,loan_start_dt,order_id,process_oper_no,process_dt,process_time,audit_oper_no,audit_dt,audit_time,acct_oper_no,account_dt,account_time,acct_flow_no,deduct_oper_no,deduct_dt,deduct_time,oper_no,oper_dt,oper_time,grant_ratio,loan_no,pledge_no,suspd_branch_name,suspd_branch_no,yz_source,update_dt,update_time,acct_order_id1,acct_order_id2,acct_order_id3,acct_order_id4,acct_order_id5,refund_order_id,refund_flag,confer_no)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
	obj.getAcptId(),obj.getBatchNo(),obj.getBranchNo(),obj.getProtocalNo(),obj.getFacNo(),
	obj.getFkjjh(),obj.getProdNo(),obj.getRemitter(),obj.getRemitterCustNo(),
	obj.getRemitterAcct(),obj.getRemitterBankNo(),obj.getRemitterBankName(),obj.getIssueBranchNo(),
	obj.getDraweeBankNo(),obj.getDraweeBankName(),obj.getDraweeAddr(),obj.getIssueDt(),
	obj.getDueDt(),obj.getBillClass(),obj.getBillType(),obj.getTotalCount(),
	obj.getTotalAmt(),obj.getTotalAmtForDeduct(),obj.getTotalGrantAmt(),obj.getFreezeTotalGrantAmt(),
	obj.getAccountNo1(),obj.getAccountName1(),obj.getAccountBranchNo1(),obj.getAccountBranchName1(),
	obj.getGrantAmt1(),obj.getAccountType1(),obj.getGrantNo1(),obj.getFreezeGrantAmt1(),
	obj.getAccountNo2(),obj.getAccountName2(),obj.getAccountBranchNo2(),obj.getAccountBranchName2(),
	obj.getGrantAmt2(),obj.getAccountType2(),obj.getGrantNo2(),obj.getFreezeGrantAmt2(),
	obj.getAccountNo3(),obj.getAccountName3(),obj.getAccountBranchNo3(),obj.getAccountBranchName3(),
	obj.getGrantAmt3(),obj.getAccountType3(),obj.getGrantNo3(),obj.getFreezeGrantAmt3(),
	obj.getAccountNo4(),obj.getAccountName4(),obj.getAccountBranchNo4(),obj.getAccountBranchName4(),
	obj.getGrantAmt4(),obj.getAccountType4(),obj.getGrantNo4(),obj.getFreezeGrantAmt4(),
	obj.getAccountNo5(),obj.getAccountName5(),obj.getAccountBranchNo5(),obj.getAccountBranchName5(),
	obj.getGrantAmt5(),obj.getAccountType5(),obj.getGrantNo5(),obj.getFreezeGrantAmt5(),
	obj.getBusiDivision(),obj.getDeptNo(),obj.getCustManager(),obj.getAuditChannel(),
	obj.getLoanSystem(),obj.getBatchStatus(),obj.getDeductAmt(),obj.getLoanAmt(),
	obj.getLoanDt(),obj.getLoanTime(),obj.getLoanStartDt(),obj.getOrderId(),
	obj.getProcessOperNo(),obj.getProcessDt(),obj.getProcessTime(),obj.getAuditOperNo(),
	obj.getAuditDt(),obj.getAuditTime(),obj.getAcctOperNo(),obj.getAccountDt(),
	obj.getAccountTime(),obj.getAcctFlowNo(),obj.getDeductOperNo(),obj.getDeductDt(),
	obj.getDeductTime(),obj.getOperNo(),obj.getOperDt(),obj.getOperTime(),
	obj.getGrantRatio(),obj.getLoanNo(),obj.getPledgeNo(),obj.getSuspdBranchName(),
	obj.getSuspdBranchNo(),obj.getYzSource(),obj.getUpdateDt(),obj.getUpdateTime(),
	obj.getAcctOrderId1(),obj.getAcctOrderId2(),obj.getAcctOrderId3(),obj.getAcctOrderId4(),
	obj.getAcctOrderId5(),obj.getRefundOrderId(),obj.getRefundFlag(),obj.getConferNo());
}

public int deleteAcptApplyInfo(AcptApplyInfo obj) throws SQLException {
	IDB session = DBFactory.getDB();
	return session.execute("delete from tacpt_apply_info where acpt_id=?",obj.getAcptId());
}

public int deleteAcptApplyInfo(String acptId) throws SQLException {
	IDB session = DBFactory.getDB();
	return session.execute("delete from tacpt_apply_info where acpt_id=?",acptId);
}

public int modifyAcptApplyInfo(AcptApplyInfo obj) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("update tacpt_apply_info set batch_no=?,branch_no=?,protocal_no=?,fac_no=?,fkjjh=?,prod_no=?,remitter=?,remitter_cust_no=?,remitter_acct=?,remitter_bank_no=?,remitter_bank_name=?,issue_branch_no=?,drawee_bank_no=?,drawee_bank_name=?,drawee_addr=?,issue_dt=?,due_dt=?,bill_class=?,bill_type=?,total_count=?,total_amt=?,total_amt_for_deduct=?,total_grant_amt=?,freeze_total_grant_amt=?,account_no1=?,account_name1=?,account_branch_no1=?,account_branch_name1=?,grant_amt1=?,account_type1=?,grant_no1=?,freeze_grant_amt1=?,account_no2=?,account_name2=?,account_branch_no2=?,account_branch_name2=?,grant_amt2=?,account_type2=?,grant_no2=?,freeze_grant_amt2=?,account_no3=?,account_name3=?,account_branch_no3=?,account_branch_name3=?,grant_amt3=?,account_type3=?,grant_no3=?,freeze_grant_amt3=?,account_no4=?,account_name4=?,account_branch_no4=?,account_branch_name4=?,grant_amt4=?,account_type4=?,grant_no4=?,freeze_grant_amt4=?,account_no5=?,account_name5=?,account_branch_no5=?,account_branch_name5=?,grant_amt5=?,account_type5=?,grant_no5=?,freeze_grant_amt5=?,busi_division=?,dept_no=?,cust_manager=?,audit_channel=?,loan_system=?,batch_status=?,deduct_amt=?,loan_amt=?,loan_dt=?,loan_time=?,loan_start_dt=?,order_id=?,process_oper_no=?,process_dt=?,process_time=?,audit_oper_no=?,audit_dt=?,audit_time=?,acct_oper_no=?,account_dt=?,account_time=?,acct_flow_no=?,deduct_oper_no=?,deduct_dt=?,deduct_time=?,oper_no=?,oper_dt=?,oper_time=?,grant_ratio=?,loan_no=?,pledge_no=?,suspd_branch_name=?,suspd_branch_no=?,yz_source=?,update_dt=?,update_time=?,acct_order_id1=?,acct_order_id2=?,acct_order_id3=?,acct_order_id4=?,acct_order_id5=?,refund_order_id=?,refund_flag=?,confer_no=? where acpt_id=?",
	obj.getBatchNo(),obj.getBranchNo(),obj.getProtocalNo(),obj.getFacNo(),
	obj.getFkjjh(),obj.getProdNo(),obj.getRemitter(),obj.getRemitterCustNo(),
	obj.getRemitterAcct(),obj.getRemitterBankNo(),obj.getRemitterBankName(),obj.getIssueBranchNo(),
	obj.getDraweeBankNo(),obj.getDraweeBankName(),obj.getDraweeAddr(),obj.getIssueDt(),
	obj.getDueDt(),obj.getBillClass(),obj.getBillType(),obj.getTotalCount(),
	obj.getTotalAmt(),obj.getTotalAmtForDeduct(),obj.getTotalGrantAmt(),obj.getFreezeTotalGrantAmt(),
	obj.getAccountNo1(),obj.getAccountName1(),obj.getAccountBranchNo1(),obj.getAccountBranchName1(),
	obj.getGrantAmt1(),obj.getAccountType1(),obj.getGrantNo1(),obj.getFreezeGrantAmt1(),
	obj.getAccountNo2(),obj.getAccountName2(),obj.getAccountBranchNo2(),obj.getAccountBranchName2(),
	obj.getGrantAmt2(),obj.getAccountType2(),obj.getGrantNo2(),obj.getFreezeGrantAmt2(),
	obj.getAccountNo3(),obj.getAccountName3(),obj.getAccountBranchNo3(),obj.getAccountBranchName3(),
	obj.getGrantAmt3(),obj.getAccountType3(),obj.getGrantNo3(),obj.getFreezeGrantAmt3(),
	obj.getAccountNo4(),obj.getAccountName4(),obj.getAccountBranchNo4(),obj.getAccountBranchName4(),
	obj.getGrantAmt4(),obj.getAccountType4(),obj.getGrantNo4(),obj.getFreezeGrantAmt4(),
	obj.getAccountNo5(),obj.getAccountName5(),obj.getAccountBranchNo5(),obj.getAccountBranchName5(),
	obj.getGrantAmt5(),obj.getAccountType5(),obj.getGrantNo5(),obj.getFreezeGrantAmt5(),
	obj.getBusiDivision(),obj.getDeptNo(),obj.getCustManager(),obj.getAuditChannel(),
	obj.getLoanSystem(),obj.getBatchStatus(),obj.getDeductAmt(),obj.getLoanAmt(),
	obj.getLoanDt(),obj.getLoanTime(),obj.getLoanStartDt(),obj.getOrderId(),
	obj.getProcessOperNo(),obj.getProcessDt(),obj.getProcessTime(),obj.getAuditOperNo(),
	obj.getAuditDt(),obj.getAuditTime(),obj.getAcctOperNo(),obj.getAccountDt(),
	obj.getAccountTime(),obj.getAcctFlowNo(),obj.getDeductOperNo(),obj.getDeductDt(),
	obj.getDeductTime(),obj.getOperNo(),obj.getOperDt(),obj.getOperTime(),
	obj.getGrantRatio(),obj.getLoanNo(),obj.getPledgeNo(),obj.getSuspdBranchName(),
	obj.getSuspdBranchNo(),obj.getYzSource(),obj.getUpdateDt(),obj.getUpdateTime(),
	obj.getAcctOrderId1(),obj.getAcctOrderId2(),obj.getAcctOrderId3(),obj.getAcctOrderId4(),
	obj.getAcctOrderId5(),obj.getRefundOrderId(),obj.getRefundFlag(),obj.getConferNo(),
	obj.getAcptId());
}

public int modifyAcptApplyInfo(AcptApplyInfo obj,String acptId) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("update tacpt_apply_info set batch_no=?,branch_no=?,protocal_no=?,fac_no=?,fkjjh=?,prod_no=?,remitter=?,remitter_cust_no=?,remitter_acct=?,remitter_bank_no=?,remitter_bank_name=?,issue_branch_no=?,drawee_bank_no=?,drawee_bank_name=?,drawee_addr=?,issue_dt=?,due_dt=?,bill_class=?,bill_type=?,total_count=?,total_amt=?,total_amt_for_deduct=?,total_grant_amt=?,freeze_total_grant_amt=?,account_no1=?,account_name1=?,account_branch_no1=?,account_branch_name1=?,grant_amt1=?,account_type1=?,grant_no1=?,freeze_grant_amt1=?,account_no2=?,account_name2=?,account_branch_no2=?,account_branch_name2=?,grant_amt2=?,account_type2=?,grant_no2=?,freeze_grant_amt2=?,account_no3=?,account_name3=?,account_branch_no3=?,account_branch_name3=?,grant_amt3=?,account_type3=?,grant_no3=?,freeze_grant_amt3=?,account_no4=?,account_name4=?,account_branch_no4=?,account_branch_name4=?,grant_amt4=?,account_type4=?,grant_no4=?,freeze_grant_amt4=?,account_no5=?,account_name5=?,account_branch_no5=?,account_branch_name5=?,grant_amt5=?,account_type5=?,grant_no5=?,freeze_grant_amt5=?,busi_division=?,dept_no=?,cust_manager=?,audit_channel=?,loan_system=?,batch_status=?,deduct_amt=?,loan_amt=?,loan_dt=?,loan_time=?,loan_start_dt=?,order_id=?,process_oper_no=?,process_dt=?,process_time=?,audit_oper_no=?,audit_dt=?,audit_time=?,acct_oper_no=?,account_dt=?,account_time=?,acct_flow_no=?,deduct_oper_no=?,deduct_dt=?,deduct_time=?,oper_no=?,oper_dt=?,oper_time=?,grant_ratio=?,loan_no=?,pledge_no=?,suspd_branch_name=?,suspd_branch_no=?,yz_source=?,update_dt=?,update_time=?,acct_order_id1=?,acct_order_id2=?,acct_order_id3=?,acct_order_id4=?,acct_order_id5=?,refund_order_id=?,refund_flag=?,confer_no=? where acpt_id=?",
	obj.getBatchNo(),obj.getBranchNo(),obj.getProtocalNo(),obj.getFacNo(),
	obj.getFkjjh(),obj.getProdNo(),obj.getRemitter(),obj.getRemitterCustNo(),
	obj.getRemitterAcct(),obj.getRemitterBankNo(),obj.getRemitterBankName(),obj.getIssueBranchNo(),
	obj.getDraweeBankNo(),obj.getDraweeBankName(),obj.getDraweeAddr(),obj.getIssueDt(),
	obj.getDueDt(),obj.getBillClass(),obj.getBillType(),obj.getTotalCount(),
	obj.getTotalAmt(),obj.getTotalAmtForDeduct(),obj.getTotalGrantAmt(),obj.getFreezeTotalGrantAmt(),
	obj.getAccountNo1(),obj.getAccountName1(),obj.getAccountBranchNo1(),obj.getAccountBranchName1(),
	obj.getGrantAmt1(),obj.getAccountType1(),obj.getGrantNo1(),obj.getFreezeGrantAmt1(),
	obj.getAccountNo2(),obj.getAccountName2(),obj.getAccountBranchNo2(),obj.getAccountBranchName2(),
	obj.getGrantAmt2(),obj.getAccountType2(),obj.getGrantNo2(),obj.getFreezeGrantAmt2(),
	obj.getAccountNo3(),obj.getAccountName3(),obj.getAccountBranchNo3(),obj.getAccountBranchName3(),
	obj.getGrantAmt3(),obj.getAccountType3(),obj.getGrantNo3(),obj.getFreezeGrantAmt3(),
	obj.getAccountNo4(),obj.getAccountName4(),obj.getAccountBranchNo4(),obj.getAccountBranchName4(),
	obj.getGrantAmt4(),obj.getAccountType4(),obj.getGrantNo4(),obj.getFreezeGrantAmt4(),
	obj.getAccountNo5(),obj.getAccountName5(),obj.getAccountBranchNo5(),obj.getAccountBranchName5(),
	obj.getGrantAmt5(),obj.getAccountType5(),obj.getGrantNo5(),obj.getFreezeGrantAmt5(),
	obj.getBusiDivision(),obj.getDeptNo(),obj.getCustManager(),obj.getAuditChannel(),
	obj.getLoanSystem(),obj.getBatchStatus(),obj.getDeductAmt(),obj.getLoanAmt(),
	obj.getLoanDt(),obj.getLoanTime(),obj.getLoanStartDt(),obj.getOrderId(),
	obj.getProcessOperNo(),obj.getProcessDt(),obj.getProcessTime(),obj.getAuditOperNo(),
	obj.getAuditDt(),obj.getAuditTime(),obj.getAcctOperNo(),obj.getAccountDt(),
	obj.getAccountTime(),obj.getAcctFlowNo(),obj.getDeductOperNo(),obj.getDeductDt(),
	obj.getDeductTime(),obj.getOperNo(),obj.getOperDt(),obj.getOperTime(),
	obj.getGrantRatio(),obj.getLoanNo(),obj.getPledgeNo(),obj.getSuspdBranchName(),
	obj.getSuspdBranchNo(),obj.getYzSource(),obj.getUpdateDt(),obj.getUpdateTime(),
	obj.getAcctOrderId1(),obj.getAcctOrderId2(),obj.getAcctOrderId3(),obj.getAcctOrderId4(),
	obj.getAcctOrderId5(),obj.getRefundOrderId(),obj.getRefundFlag(),obj.getConferNo(),
	acptId);
}

public AcptApplyInfo getAcptApplyInfo(String acptId) throws SQLException {
	IDB session = DBFactory.getDB();
	AcptApplyInfo obj = (AcptApplyInfo)session.getObject("select * from tacpt_apply_info where acpt_id=?",AcptApplyInfo.class,acptId);
	return obj;
}


public AcptApplyInfo getAcptApplyInfoByProtocalNoAndBrchNo(String protocalNo,String branchNo) throws SQLException {
	IDB session = DBFactory.getDB();
	AcptApplyInfo obj = (AcptApplyInfo)session.getObject("select * from tacpt_apply_info where protocal_no =? and  BRANCH_NO=?",AcptApplyInfo.class,protocalNo,branchNo);
	return obj;
}

public AcptApplyInfo getAcptApplyInfoByBatchNo(String batchNo) throws SQLException {
	IDB session = DBFactory.getDB();
	AcptApplyInfo obj = (AcptApplyInfo)session.getObject("select * from tacpt_apply_info where batch_status ='-1' and  batch_no=?",AcptApplyInfo.class,batchNo);
	return obj;
}

/*自定义方法*******************************************************************************/
public AcptApplyInfo getAcptApplyInfo(AcptQueryCondition query) throws SQLException{
	IDB session = DBFactory.getDB();
	AcptApplyInfo obj = (AcptApplyInfo)session.getObject("select * from tacpt_apply_info where acpt_id=?",AcptApplyInfo.class,query.getAcptId());
	if(query == null) return obj;
	sumApplyInfo(obj, query);
	return obj;
}

/**
 * 功能描述：根据条件查询批次列表
 * @param page
 * @param query
 * @return
 * @throws SQLException
 */
public List<AcptApplyInfo> getAcptApplyListForCondition(Page page,AcptQueryCondition query) throws SQLException {
	IDB session = DBFactory.getDB();
	String baseSql="select distinct apply.* from tacpt_apply_info apply,tacpt_bill_info bill where  bill.acpt_id = apply.acpt_id";
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
	List<AcptApplyInfo> applyList=null;
	// 获得并返回本次查询的总条数
	if(qc.getParameterValues() ==null ||qc.getParameterValues().size()==0){//什么条件都不带的情况
	    count = session.account(qc.getCountSql("distinct apply.acpt_id"));
	    applyList=session.getObjectListForPage(allsql,AcptApplyInfo.class,startIndex, page.getShowCount());
	}else{
	    count = session.accountByList(qc.getCountSql("distinct apply.acpt_id"), qc.getParameterValues()); 
	    applyList=session.getObjectListByListForPage(allsql,AcptApplyInfo.class, startIndex, page.getShowCount(), qc.getParameterValues());
	}
	System.out.println(allsql);
	page.setTotalResult(count);
	//票据张数，与票据金额汇总
	sumApplyInfo(applyList, query);
	
	return applyList;
}

public void sumApplyInfo(List<AcptApplyInfo> applyList,AcptQueryCondition query) throws SQLException{
    if(applyList == null || applyList.size() == 0) return;
    IDB session = DBFactory.getDB();
    //批次汇总票据张数，与票据金额sql
    StringBuilder sb = new StringBuilder("select count(bill.acptmx_id)||'',");
    sb.append("sum(bill.bill_money) from tacpt_bill_info bill where bill.acpt_id = ?");
    List<Object> param = new ArrayList<Object>();
    //占第一位 为第一个问号赋值
    param.add("");
    if(query != null){
        if(StringUtils.isNotBlank(query.getBillClass())){
            sb.append(" and bill.bill_class = ? ");
            param.add(query.getBillClass().trim());
        }
        if(StringUtils.isNotBlank(query.getStatus())){
            sb.append(" and bill.status = ? ");
            param.add(query.getStatus().trim());
        }
        if(StringUtils.isNotBlank(query.getBillStatus())){
            sb.append(" and bill.bill_status = ? ");
            param.add(query.getBillStatus().trim());
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
    
    
    for(AcptApplyInfo apply:applyList){
    	//删除第一位 为第一个问号赋值
    	param.remove(0);
    	// 为第一个问号赋值
    	param.add(0, apply.getAcptId());
	    IData data=session.getDataByList(sb.toString(), param);
	    apply.setTotalCount(data.getLong(1));
	    apply.setTotalAmt(data.getDouble(2));

    }
    
}
public IData sumApplyInfo(AcptQueryCondition query) throws SQLException{
	 String baseSql = "select distinct apply.* from tacpt_apply_info apply, tacpt_bill_info bill where bill.acpt_id = apply.acpt_id";
	 QueryCondition qc = new QueryCondition();
       try {
			qc.initFromSearchBean(query);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
       qc.getAllSqlString(baseSql);//这里只是用于初始化basicSql_low和basicSql
       String sql = qc.getCountSql2("bill.acptmx_id", "bill.bill_money");
       
       IDB session = DBFactory.getDB();
       IData data=session.getDataByList(sql, qc.getParameterValues());
		return data;
       
   }


public void sumApplyInfo(AcptApplyInfo apply,AcptQueryCondition query) throws SQLException{
    if(apply == null) return;
    IDB session = DBFactory.getDB();
    //批次汇总票据张数，与票据金额sql
    StringBuilder sb = new StringBuilder("select count(bill.acptmx_id)||'',");
    sb.append("sum(bill.bill_money) from tacpt_bill_info bill where bill.acpt_id = ? ");
    List<Object> param = new ArrayList<Object>();
    //占第一位 为第一个问号赋值
    param.add("");
    if(query != null){
      if(StringUtils.isNotBlank(query.getAcptId())){
          sb.append("and bill.acpt_id='"+query.getAcptId()+"'");
      }
      if(StringUtils.isNotBlank(query.getBillClass())){
          sb.append("and bill.bill_class='"+query.getBillClass()+"'");
      }
      if(StringUtils.isNotBlank(query.getStatus())){
          sb.append("and bill.status='"+query.getStatus()+"'");
      }
      if(StringUtils.isNotBlank(query.getBillStatus())){
          sb.append("and bill.bill_status='"+query.getBillStatus()+"'");
      }
  }
      
    
    //删除第一位 为第一个问号赋值
    param.remove(0);
    // 为第一个问号赋值
    param.add(0, apply.getAcptId());
    IData data=session.getDataByList(sb.toString(), param);
//  System.out.println(sb.toString());
    apply.setTotalCount(data.getLong(1));
    apply.setTotalAmt(data.getDouble(2));
}


/**
 * 功能描述：根据acptId查询批次信息
 * @param page
 * @param query
 * @return
 * @throws SQLException
 */
public AcptApplyInfo queryApplyByAcptId(String acptId)throws SQLException{
	IDB session = DBFactory.getDB();
	return session.getObject("select * from tacpt_apply_info where acpt_id=?",AcptApplyInfo.class,acptId);
   }


}

