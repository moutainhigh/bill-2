/********************************************
* 文件名称: AssuApplyInfoDao.java
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

import java.lang.*;
import java.math.*;
import com.herongtech.db.impl.session.DBFactory;
import com.herongtech.db.interfaces.IDB;
import java.sql.SQLException;
import com.herongtech.commons.tools.BeanFormat;
import com.herongtech.console.domain.assu.bean.AssuApplyInfo;
public class AssuApplyInfoDao{

public int addAssuApplyInfo(AssuApplyInfo obj) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("insert into tassu_apply_info(assu_id,batch_id,cust_account,cust_name,dept_no,dept_name,is_sign,sign_remark,create_dt,create_time,cust_no,assu_type,assu_account,coll_prod_no,cust_manager_name,guarant_pact_no,pledge_pact_no,mort_pact_no,back_type,impawn_bail_acct,impawn_bail_serial,order_id,is_calcfee,branch_no,bill_class,assu_protocal_no,in_acct_type,in_acct_no,is_getfee,cust_manage,rate,impawn_bail_money,valid_money,total_fee,bill_type)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
	obj.getAssuId(),obj.getBatchId(),obj.getCustAccount(),obj.getCustName(),obj.getDeptNo(),
	obj.getDeptName(),obj.getIsSign(),obj.getSignRemark(),obj.getCreateDt(),
	obj.getCreateTime(),obj.getCustNo(),obj.getAssuType(),obj.getAssuAccount(),
	obj.getCollProdNo(),obj.getCustManagerName(),obj.getGuarantPactNo(),obj.getPledgePactNo(),
	obj.getMortPactNo(),obj.getBackType(),obj.getImpawnBailAcct(),obj.getImpawnBailSerial(),
	obj.getOrderId(),obj.getIsCalcfee(),obj.getBranchNo(),obj.getBillClass(),
	obj.getAssuProtocalNo(),obj.getInAcctType(),obj.getInAcctNo(),obj.getIsGetfee(),
	obj.getCustManage(),obj.getRate(),obj.getImpawnBailMoney(),obj.getValidMoney(),
	obj.getTotalFee(),obj.getBillType());
}

public int deleteAssuApplyInfo(AssuApplyInfo obj) throws SQLException {
	IDB session = DBFactory.getDB();
	return session.execute("delete from tassu_apply_info where assu_id=?",obj.getAssuId());
}

public int deleteAssuApplyInfo(String assuId) throws SQLException {
	IDB session = DBFactory.getDB();
	return session.execute("delete from tassu_apply_info where assu_id=?",assuId);
}

public int modifyAssuApplyInfo(AssuApplyInfo obj) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("update tassu_apply_info set batch_id=?,cust_account=?,cust_name=?,dept_no=?,dept_name=?,is_sign=?,sign_remark=?,create_dt=?,create_time=?,cust_no=?,assu_type=?,assu_account=?,coll_prod_no=?,cust_manager_name=?,guarant_pact_no=?,pledge_pact_no=?,mort_pact_no=?,back_type=?,impawn_bail_acct=?,impawn_bail_serial=?,order_id=?,is_calcfee=?,branch_no=?,bill_class=?,assu_protocal_no=?,in_acct_type=?,in_acct_no=?,is_getfee=?,cust_manage=?,rate=?,impawn_bail_money=?,valid_money=?,total_fee=?,bill_type=? where assu_id=?",
	obj.getBatchId(),obj.getCustAccount(),obj.getCustName(),obj.getDeptNo(),
	obj.getDeptName(),obj.getIsSign(),obj.getSignRemark(),obj.getCreateDt(),
	obj.getCreateTime(),obj.getCustNo(),obj.getAssuType(),obj.getAssuAccount(),
	obj.getCollProdNo(),obj.getCustManagerName(),obj.getGuarantPactNo(),obj.getPledgePactNo(),
	obj.getMortPactNo(),obj.getBackType(),obj.getImpawnBailAcct(),obj.getImpawnBailSerial(),
	obj.getOrderId(),obj.getIsCalcfee(),obj.getBranchNo(),obj.getBillClass(),
	obj.getAssuProtocalNo(),obj.getInAcctType(),obj.getInAcctNo(),obj.getIsGetfee(),
	obj.getCustManage(),obj.getRate(),obj.getImpawnBailMoney(),obj.getValidMoney(),
	obj.getTotalFee(),obj.getBillType(),obj.getAssuId());
}

public int modifyAssuApplyInfo(AssuApplyInfo obj,String assuId) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("update tassu_apply_info set batch_id=?,cust_account=?,cust_name=?,dept_no=?,dept_name=?,is_sign=?,sign_remark=?,create_dt=?,create_time=?,cust_no=?,assu_type=?,assu_account=?,coll_prod_no=?,cust_manager_name=?,guarant_pact_no=?,pledge_pact_no=?,mort_pact_no=?,back_type=?,impawn_bail_acct=?,impawn_bail_serial=?,order_id=?,is_calcfee=?,branch_no=?,bill_class=?,assu_protocal_no=?,in_acct_type=?,in_acct_no=?,is_getfee=?,cust_manage=?,rate=?,impawn_bail_money=?,valid_money=?,total_fee=?,bill_type=? where assu_id=?",
	obj.getBatchId(),obj.getCustAccount(),obj.getCustName(),obj.getDeptNo(),
	obj.getDeptName(),obj.getIsSign(),obj.getSignRemark(),obj.getCreateDt(),
	obj.getCreateTime(),obj.getCustNo(),obj.getAssuType(),obj.getAssuAccount(),
	obj.getCollProdNo(),obj.getCustManagerName(),obj.getGuarantPactNo(),obj.getPledgePactNo(),
	obj.getMortPactNo(),obj.getBackType(),obj.getImpawnBailAcct(),obj.getImpawnBailSerial(),
	obj.getOrderId(),obj.getIsCalcfee(),obj.getBranchNo(),obj.getBillClass(),
	obj.getAssuProtocalNo(),obj.getInAcctType(),obj.getInAcctNo(),obj.getIsGetfee(),
	obj.getCustManage(),obj.getRate(),obj.getImpawnBailMoney(),obj.getValidMoney(),
	obj.getTotalFee(),obj.getBillType(),assuId);
}

public AssuApplyInfo getAssuApplyInfo(String assuId) throws SQLException {
	IDB session = DBFactory.getDB();
	AssuApplyInfo obj = (AssuApplyInfo)session.getObject("select * from tassu_apply_info where assu_id=?",AssuApplyInfo.class,assuId);
	return obj;
}

}
