/********************************************
* 文件名称: AssuBillInfoDao.java
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
package com.herongtech.console.domain.assu.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.herongtech.commons.tools.BeanFormat;
import com.herongtech.console.core.common.json.Page;
import com.herongtech.console.domain.assu.bean.AssuBillInfo;
import com.herongtech.console.web.busicontroller.common.GuarCodeConst;
import com.herongtech.db.impl.session.DBFactory;
import com.herongtech.db.interfaces.IDB;
public class AssuBillInfoDao{

public int addAssuBillInfo(AssuBillInfo obj) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("insert into tassu_bill_info(assumx_id,assu_id,rgct_id,rgct_hist_id,wartee_dt,wartee_time,wartee_cust_no,wartee_sign,wartee_acct_no,wartee_bank_no,guartr_orgcode,wartee_acpt_bank_no,guartr_name,guarntr_cust_no,guarntr_acct_no,guartr_bank_no,wartee_orgcode,assu_status,is_accpt,assu_type,guartr_addr,wartee_cust_name,guartr_partner_type,wartee_partner_type,account_date,account_time,acct_status,acct_oper_no,acceptor_bank_name,acceptor_bank_no,acceptor,adjust_flag,audit_date,audit_time,audit_oper_no,bill_money,bill_class,bill_no,bill_type,branch_no,check_oper_no,due_dt,fee,final_fee,ex_serial,impawn_money,loan_no,issue_dt,payee,payee_bank_name,payee_bank_no,rate,remitter,remitter_bank_name,remitter_bank_no,valid_amount,valid_quarter,oper_status,remitter_acct,payee_acct,drawee_addr,apply_oper_no,deadline_oper_no,if_fee_charging,dead_apply_id,has_freeze,dead_apply_date,deadacct_oper_no,deadacct_date,dead_flow_no)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
	obj.getAssumxId(),obj.getAssuId(),obj.getRgctId(),obj.getRgctHistId(),obj.getWarteeDt(),
	obj.getWarteeTime(),obj.getWarteeCustNo(),obj.getWarteeSign(),obj.getWarteeAcctNo(),
	obj.getWarteeBankNo(),obj.getGuartrOrgcode(),obj.getWarteeAcptBankNo(),obj.getGuartrName(),
	obj.getGuarntrCustNo(),obj.getGuarntrAcctNo(),obj.getGuartrBankNo(),obj.getWarteeOrgcode(),
	obj.getAssuStatus(),obj.getIsAccpt(),obj.getAssuType(),obj.getGuartrAddr(),
	obj.getWarteeCustName(),obj.getGuartrPartnerType(),obj.getWarteePartnerType(),obj.getAccountDate(),
	obj.getAccountTime(),obj.getAcctStatus(),obj.getAcctOperNo(),obj.getAcceptorBankName(),
	obj.getAcceptorBankNo(),obj.getAcceptor(),obj.getAdjustFlag(),obj.getAuditDate(),
	obj.getAuditTime(),obj.getAuditOperNo(),obj.getBillMoney(),obj.getBillClass(),
	obj.getBillNo(),obj.getBillType(),obj.getBranchNo(),obj.getCheckOperNo(),
	obj.getDueDt(),obj.getFee(),obj.getFinalFee(),obj.getExSerial(),
	obj.getImpawnMoney(),obj.getLoanNo(),obj.getIssueDt(),obj.getPayee(),
	obj.getPayeeBankName(),obj.getPayeeBankNo(),obj.getRate(),obj.getRemitter(),
	obj.getRemitterBankName(),obj.getRemitterBankNo(),obj.getValidAmount(),obj.getValidQuarter(),
	obj.getOperStatus(),obj.getRemitterAcct(),obj.getPayeeAcct(),obj.getDraweeAddr(),
	obj.getApplyOperNo(),obj.getDeadlineOperNo(),obj.getIfFeeCharging(),obj.getDeadApplyId(),
	obj.getHasFreeze(),obj.getDeadApplyDate(),obj.getDeadacctOperNo(),obj.getDeadacctDate(),
	obj.getDeadFlowNo());
}

public int deleteAssuBillInfo(AssuBillInfo obj) throws SQLException {
	IDB session = DBFactory.getDB();
	return session.execute("delete from tassu_bill_info where assumx_id=?",obj.getAssumxId());
}

public int deleteAssuBillInfo(String assumxId) throws SQLException {
	IDB session = DBFactory.getDB();
	return session.execute("delete from tassu_bill_info where assumx_id=?",assumxId);
}

public int modifyAssuBillInfo(AssuBillInfo obj) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("update tassu_bill_info set assu_id=?,rgct_id=?,rgct_hist_id=?,wartee_dt=?,wartee_time=?,wartee_cust_no=?,wartee_sign=?,wartee_acct_no=?,wartee_bank_no=?,guartr_orgcode=?,wartee_acpt_bank_no=?,guartr_name=?,guarntr_cust_no=?,guarntr_acct_no=?,guartr_bank_no=?,wartee_orgcode=?,assu_status=?,is_accpt=?,assu_type=?,guartr_addr=?,wartee_cust_name=?,guartr_partner_type=?,wartee_partner_type=?,account_date=?,account_time=?,acct_status=?,acct_oper_no=?,acceptor_bank_name=?,acceptor_bank_no=?,acceptor=?,adjust_flag=?,audit_date=?,audit_time=?,audit_oper_no=?,bill_money=?,bill_class=?,bill_no=?,bill_type=?,branch_no=?,check_oper_no=?,due_dt=?,fee=?,final_fee=?,ex_serial=?,impawn_money=?,loan_no=?,issue_dt=?,payee=?,payee_bank_name=?,payee_bank_no=?,rate=?,remitter=?,remitter_bank_name=?,remitter_bank_no=?,valid_amount=?,valid_quarter=?,oper_status=?,remitter_acct=?,payee_acct=?,drawee_addr=?,apply_oper_no=?,deadline_oper_no=?,if_fee_charging=?,dead_apply_id=?,has_freeze=?,dead_apply_date=?,deadacct_oper_no=?,deadacct_date=?,dead_flow_no=? where assumx_id=?",
	obj.getAssuId(),obj.getRgctId(),obj.getRgctHistId(),obj.getWarteeDt(),
	obj.getWarteeTime(),obj.getWarteeCustNo(),obj.getWarteeSign(),obj.getWarteeAcctNo(),
	obj.getWarteeBankNo(),obj.getGuartrOrgcode(),obj.getWarteeAcptBankNo(),obj.getGuartrName(),
	obj.getGuarntrCustNo(),obj.getGuarntrAcctNo(),obj.getGuartrBankNo(),obj.getWarteeOrgcode(),
	obj.getAssuStatus(),obj.getIsAccpt(),obj.getAssuType(),obj.getGuartrAddr(),
	obj.getWarteeCustName(),obj.getGuartrPartnerType(),obj.getWarteePartnerType(),obj.getAccountDate(),
	obj.getAccountTime(),obj.getAcctStatus(),obj.getAcctOperNo(),obj.getAcceptorBankName(),
	obj.getAcceptorBankNo(),obj.getAcceptor(),obj.getAdjustFlag(),obj.getAuditDate(),
	obj.getAuditTime(),obj.getAuditOperNo(),obj.getBillMoney(),obj.getBillClass(),
	obj.getBillNo(),obj.getBillType(),obj.getBranchNo(),obj.getCheckOperNo(),
	obj.getDueDt(),obj.getFee(),obj.getFinalFee(),obj.getExSerial(),
	obj.getImpawnMoney(),obj.getLoanNo(),obj.getIssueDt(),obj.getPayee(),
	obj.getPayeeBankName(),obj.getPayeeBankNo(),obj.getRate(),obj.getRemitter(),
	obj.getRemitterBankName(),obj.getRemitterBankNo(),obj.getValidAmount(),obj.getValidQuarter(),
	obj.getOperStatus(),obj.getRemitterAcct(),obj.getPayeeAcct(),obj.getDraweeAddr(),
	obj.getApplyOperNo(),obj.getDeadlineOperNo(),obj.getIfFeeCharging(),obj.getDeadApplyId(),
	obj.getHasFreeze(),obj.getDeadApplyDate(),obj.getDeadacctOperNo(),obj.getDeadacctDate(),
	obj.getDeadFlowNo(),obj.getAssumxId());
}

public int modifyAssuBillInfo(AssuBillInfo obj,String assumxId) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("update tassu_bill_info set assu_id=?,rgct_id=?,rgct_hist_id=?,wartee_dt=?,wartee_time=?,wartee_cust_no=?,wartee_sign=?,wartee_acct_no=?,wartee_bank_no=?,guartr_orgcode=?,wartee_acpt_bank_no=?,guartr_name=?,guarntr_cust_no=?,guarntr_acct_no=?,guartr_bank_no=?,wartee_orgcode=?,assu_status=?,is_accpt=?,assu_type=?,guartr_addr=?,wartee_cust_name=?,guartr_partner_type=?,wartee_partner_type=?,account_date=?,account_time=?,acct_status=?,acct_oper_no=?,acceptor_bank_name=?,acceptor_bank_no=?,acceptor=?,adjust_flag=?,audit_date=?,audit_time=?,audit_oper_no=?,bill_money=?,bill_class=?,bill_no=?,bill_type=?,branch_no=?,check_oper_no=?,due_dt=?,fee=?,final_fee=?,ex_serial=?,impawn_money=?,loan_no=?,issue_dt=?,payee=?,payee_bank_name=?,payee_bank_no=?,rate=?,remitter=?,remitter_bank_name=?,remitter_bank_no=?,valid_amount=?,valid_quarter=?,oper_status=?,remitter_acct=?,payee_acct=?,drawee_addr=?,apply_oper_no=?,deadline_oper_no=?,if_fee_charging=?,dead_apply_id=?,has_freeze=?,dead_apply_date=?,deadacct_oper_no=?,deadacct_date=?,dead_flow_no=? where assumx_id=?",
	obj.getAssuId(),obj.getRgctId(),obj.getRgctHistId(),obj.getWarteeDt(),
	obj.getWarteeTime(),obj.getWarteeCustNo(),obj.getWarteeSign(),obj.getWarteeAcctNo(),
	obj.getWarteeBankNo(),obj.getGuartrOrgcode(),obj.getWarteeAcptBankNo(),obj.getGuartrName(),
	obj.getGuarntrCustNo(),obj.getGuarntrAcctNo(),obj.getGuartrBankNo(),obj.getWarteeOrgcode(),
	obj.getAssuStatus(),obj.getIsAccpt(),obj.getAssuType(),obj.getGuartrAddr(),
	obj.getWarteeCustName(),obj.getGuartrPartnerType(),obj.getWarteePartnerType(),obj.getAccountDate(),
	obj.getAccountTime(),obj.getAcctStatus(),obj.getAcctOperNo(),obj.getAcceptorBankName(),
	obj.getAcceptorBankNo(),obj.getAcceptor(),obj.getAdjustFlag(),obj.getAuditDate(),
	obj.getAuditTime(),obj.getAuditOperNo(),obj.getBillMoney(),obj.getBillClass(),
	obj.getBillNo(),obj.getBillType(),obj.getBranchNo(),obj.getCheckOperNo(),
	obj.getDueDt(),obj.getFee(),obj.getFinalFee(),obj.getExSerial(),
	obj.getImpawnMoney(),obj.getLoanNo(),obj.getIssueDt(),obj.getPayee(),
	obj.getPayeeBankName(),obj.getPayeeBankNo(),obj.getRate(),obj.getRemitter(),
	obj.getRemitterBankName(),obj.getRemitterBankNo(),obj.getValidAmount(),obj.getValidQuarter(),
	obj.getOperStatus(),obj.getRemitterAcct(),obj.getPayeeAcct(),obj.getDraweeAddr(),
	obj.getApplyOperNo(),obj.getDeadlineOperNo(),obj.getIfFeeCharging(),obj.getDeadApplyId(),
	obj.getHasFreeze(),obj.getDeadApplyDate(),obj.getDeadacctOperNo(),obj.getDeadacctDate(),
	obj.getDeadFlowNo(),assumxId);
}

public AssuBillInfo getAssuBillInfo(String assumxId) throws SQLException {
	IDB session = DBFactory.getDB();
	AssuBillInfo obj = (AssuBillInfo)session.getObject("select * from tassu_bill_info where assumx_id=?",AssuBillInfo.class,assumxId);
	return obj;
}
/*******************************************************自定义方法*******************************************************************/

/**
 * 可保证票据查询
 * @param page
 * @param operStatus 操作状态
 * @param billClass 票据分类
 * @return
 * @throws SQLException
 */
public List<AssuBillInfo> getAssuBillInfoForOperStatus(Page page,String operStatus,String billClass,String warteeOrgcode,String guartrBankNo) throws SQLException {
	List<AssuBillInfo> list = null;
	IDB session = DBFactory.getDB(); // 获取数据库连接
	String sqls = "select * from tassu_bill_info where oper_status=? and bill_class=? and wartee_orgcode=? and guartr_bank_no=? order by assumx_id desc";
	list = session.getObjectList(sqls, AssuBillInfo.class, operStatus, billClass, warteeOrgcode, guartrBankNo);
	page.setTotalResult(list.size());
	return list;
}

/**
 * 功能描述：通过票据中心ID查票据清单
 * @param rgctId
 * @return
 * @throws SQLException
 */
public AssuBillInfo getAssuBillInfoByRgctId(String rgctId) throws SQLException {
	IDB session = DBFactory.getDB();
	AssuBillInfo obj = (AssuBillInfo)session.getObject("select * from tassu_bill_info where rgct_id=? order by assumx_id desc",AssuBillInfo.class,rgctId);
	return obj;
}

/**
 * 根据登记中心ID与操作状态查询保证清单信息
 * @param rgctId 登记中心ID	
 * @param operStatus 操作状态
 * @return
 * @throws SQLException
 */
public AssuBillInfo getAssuBillInfoByRgctIdAndOperStatus(String rgctId, String operStatus) throws SQLException {
	IDB session = DBFactory.getDB();
	AssuBillInfo obj = (AssuBillInfo)session.getObject("select * from tassu_bill_info where rgct_id=? and oper_status=?",AssuBillInfo.class,rgctId,operStatus);
	return obj;
}

/**
 * 根据id得到清单
 * @param ids
 * @return
 * @throws SQLException
 */
public List<AssuBillInfo> getAssuBillInfoForId(String ids) throws SQLException{
	StringBuffer sql = new StringBuffer("select t.* from tassu_bill_info t where assumx_id in (");
	String idArr[] = ids.split(",");
	List<Object> param = new ArrayList<Object>(idArr.length);
	for (String id : idArr) {
		sql.append("?,");
		param.add(id);
	}
	sql.deleteCharAt(sql.length()-1);
	sql.append(')');
	IDB session = DBFactory.getDB();
	return session.getObjectListByList(sql.toString(), AssuBillInfo.class, param);
}



/**
 * 查询登记中心ID、保证类型查询特定 保证信息
 * @param rgctId
 */
public List<AssuBillInfo> getAssuInfoByRgctIdAndAssuType(String rgctId, String[] types) throws SQLException {
	StringBuffer sql = new StringBuffer("select t.* from tassu_bill_info t where rgct_id="+rgctId+" and assu_status ='"+GuarCodeConst.ASSU_STATE_SIGN_YES+"' and assu_type in (");
	List<Object> param = new ArrayList<Object>(types.length);
	for (String type : types) {
		sql.append("?,");
		param.add(type);
	}
	sql.deleteCharAt(sql.length()-1);
	sql.append(')');
		IDB session = DBFactory.getDB();
		return session.getObjectListByList(sql.toString(), AssuBillInfo.class, param);

}



}
