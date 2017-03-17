/********************************************
* 文件名称: SignProdDao.java
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
package com.herongtech.console.domain.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.herongtech.commons.tools.BeanFormat;
import com.herongtech.commons.tools.HsSqlString;
import com.herongtech.commons.tools.StringUtils;
import com.herongtech.console.core.common.json.Page;
import com.herongtech.console.core.constant.IDict;
import com.herongtech.console.domain.bean.SignProd;
import com.herongtech.db.impl.session.DBFactory;
import com.herongtech.db.interfaces.IDB;
import com.herongtech.log.CommonLog;
public class SignProdDao{

public int addSignProd(SignProd obj) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("insert into tsign_prod(id,cust_no,cust_name,id_type,id_number,prod_no,bus_settle_acct,sign_id,sign_status_cd,signseq,branch_no,oper_no,ex_serial,trans_date,trans_time,validfromdt,validtodt,opendt,channe,sign_org,create_org,remitter_bank_name,remitter_bank_no,acceptor_bank_name,acceptor_bank_no,credit_info,credit_agency,credit_due_dt,cms_flag,margin_account,fac_number,cms_number,discount_proportion,loop_no,update_date,relieve_sign_user_no,relieve_sign_date)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
	obj.getId(),obj.getCustNo(),obj.getCustName(),obj.getIdType(),obj.getIdNumber(),
	obj.getProdNo(),obj.getBusSettleAcct(),obj.getSignId(),obj.getSignStatusCd(),
	obj.getSignseq(),obj.getBranchNo(),obj.getOperNo(),obj.getExSerial(),
	obj.getTransDate(),obj.getTransTime(),obj.getValidfromdt(),obj.getValidtodt(),
	obj.getOpendt(),obj.getChanne(),obj.getSignOrg(),obj.getCreateOrg(),
	obj.getRemitterBankName(),obj.getRemitterBankNo(),obj.getAcceptorBankName(),obj.getAcceptorBankNo(),
	obj.getCreditInfo(),obj.getCreditAgency(),obj.getCreditDueDt(),obj.getCmsFlag(),
	obj.getMarginAccount(),obj.getFacNumber(),obj.getCmsNumber(),obj.getDiscountProportion(),
	obj.getLoopNo(),obj.getUpdateDate(),obj.getRelieveSignUserNo(),obj.getRelieveSignDate());
}

public int deleteSignProd(SignProd obj) throws SQLException {
	IDB session = DBFactory.getDB();
	return session.execute("delete from tsign_prod where id=?",obj.getId());
}

public int deleteSignProd(String id) throws SQLException {
	IDB session = DBFactory.getDB();
	return session.execute("delete from tsign_prod where id=?",id);
}

public int modifySignProd(SignProd obj) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("update tsign_prod set cust_no=?,cust_name=?,id_type=?,id_number=?,prod_no=?,bus_settle_acct=?,sign_id=?,sign_status_cd=?,signseq=?,branch_no=?,oper_no=?,ex_serial=?,trans_date=?,trans_time=?,validfromdt=?,validtodt=?,opendt=?,channe=?,sign_org=?,create_org=?,remitter_bank_name=?,remitter_bank_no=?,acceptor_bank_name=?,acceptor_bank_no=?,credit_info=?,credit_agency=?,credit_due_dt=?,cms_flag=?,margin_account=?,fac_number=?,cms_number=?,discount_proportion=?,loop_no=?,update_date=?,relieve_sign_user_no=?,relieve_sign_date=? where id=?",
	obj.getCustNo(),obj.getCustName(),obj.getIdType(),obj.getIdNumber(),
	obj.getProdNo(),obj.getBusSettleAcct(),obj.getSignId(),obj.getSignStatusCd(),
	obj.getSignseq(),obj.getBranchNo(),obj.getOperNo(),obj.getExSerial(),
	obj.getTransDate(),obj.getTransTime(),obj.getValidfromdt(),obj.getValidtodt(),
	obj.getOpendt(),obj.getChanne(),obj.getSignOrg(),obj.getCreateOrg(),
	obj.getRemitterBankName(),obj.getRemitterBankNo(),obj.getAcceptorBankName(),obj.getAcceptorBankNo(),
	obj.getCreditInfo(),obj.getCreditAgency(),obj.getCreditDueDt(),obj.getCmsFlag(),
	obj.getMarginAccount(),obj.getFacNumber(),obj.getCmsNumber(),obj.getDiscountProportion(),
	obj.getLoopNo(),obj.getUpdateDate(),obj.getRelieveSignUserNo(),obj.getRelieveSignDate(),
	obj.getId());
}

public int modifySignProd(SignProd obj,String id) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("update tsign_prod set cust_no=?,cust_name=?,id_type=?,id_number=?,prod_no=?,bus_settle_acct=?,sign_id=?,sign_status_cd=?,signseq=?,branch_no=?,oper_no=?,ex_serial=?,trans_date=?,trans_time=?,validfromdt=?,validtodt=?,opendt=?,channe=?,sign_org=?,create_org=?,remitter_bank_name=?,remitter_bank_no=?,acceptor_bank_name=?,acceptor_bank_no=?,credit_info=?,credit_agency=?,credit_due_dt=?,cms_flag=?,margin_account=?,fac_number=?,cms_number=?,discount_proportion=?,loop_no=?,update_date=?,relieve_sign_user_no=?,relieve_sign_date=? where id=?",
	obj.getCustNo(),obj.getCustName(),obj.getIdType(),obj.getIdNumber(),
	obj.getProdNo(),obj.getBusSettleAcct(),obj.getSignId(),obj.getSignStatusCd(),
	obj.getSignseq(),obj.getBranchNo(),obj.getOperNo(),obj.getExSerial(),
	obj.getTransDate(),obj.getTransTime(),obj.getValidfromdt(),obj.getValidtodt(),
	obj.getOpendt(),obj.getChanne(),obj.getSignOrg(),obj.getCreateOrg(),
	obj.getRemitterBankName(),obj.getRemitterBankNo(),obj.getAcceptorBankName(),obj.getAcceptorBankNo(),
	obj.getCreditInfo(),obj.getCreditAgency(),obj.getCreditDueDt(),obj.getCmsFlag(),
	obj.getMarginAccount(),obj.getFacNumber(),obj.getCmsNumber(),obj.getDiscountProportion(),
	obj.getLoopNo(),obj.getUpdateDate(),obj.getRelieveSignUserNo(),obj.getRelieveSignDate(),
	id);
}

public SignProd getSignProdByBusAct(String prodNo, String busSettleAct) throws SQLException {
	IDB session = DBFactory.getDB();
	SignProd obj = (SignProd)session.getObject("select * from tSIGN_PROD where prod_no=? and bus_settle_acct=? and sign_status_cd=?  " , SignProd.class, prodNo, busSettleAct, IDict.K_SIGNSTATUS.K_SIGNSTATUS_YES);
	return obj;
}

public SignProd getSignProdById(String id) throws SQLException {
	IDB session = DBFactory.getDB();
	SignProd obj = (SignProd)session.getObject("select * from tSIGN_PROD where id=? " , SignProd.class, id);
	return obj;
}

/**
 * 根据服务产品编号和客户号查询产品签约信息(托收中使用了此方法)
 * @param prodId
 * @param custNo
 * @return
 * @throws SQLException
 */
public SignProd getSignProdInfoByCust(String prodId, String custNo) throws SQLException {
	IDB session = DBFactory.getDB();
	SignProd obj = (SignProd)session.getObject("select * from tSIGN_PROD where prod_no=? and cust_no=? and sign_status_cd=? " , SignProd.class, prodId, custNo, IDict.K_SIGNSTATUS.K_SIGNSTATUS_YES);
	return obj;
}

/**
 * 根据服务产品编号和客户号和签约状态查询产品签约信息
 * @param signProd
 * @return
 * @throws SQLException
 */
public List<SignProd> getSignProdInfoBySignStatusCd(Page page,SignProd signProd) throws SQLException {
	HsSqlString dbSql = new HsSqlString("tSIGN_PROD");
	if (!StringUtils.isBlank(signProd.getProdNo())){
		dbSql.setWhere("prod_no like '%" + signProd.getProdNo() + "%'");
	}
	if (!StringUtils.isBlank(signProd.getBusSettleAcct())){
		dbSql.setWhere("bus_settle_acct like '%" + signProd.getBusSettleAcct() + "%'");
	}
	if (!StringUtils.isBlank(signProd.getSignStatusCd())){
		dbSql.setWhere("sign_status_cd like '%" + signProd.getSignStatusCd() + "%'");
	}
	//分页开始位置
	int startIndex = page.getCurrentResult();
	List<SignProd> resultList = new ArrayList<SignProd>();
	try{
		IDB session = DBFactory.getDB(); // 获取数据库连接
		resultList = session.getObjectListByListForPage(dbSql.getSqlString(), SignProd.class, startIndex, page.getShowCount(), dbSql.getParamList());
    	// 获得并返回本次查询的总条数
    	int count = 0;
    	count = session.accountByList(dbSql.getTotCountSqlString(), dbSql.getParamList());
    	page.setTotalResult(count);
	} catch (SQLException e){
		//e.printStackTrace();	
		CommonLog.getCommonLogCache().errorMessage("数据库错误   [" + dbSql.getSqlString() + "]");
	}
	return resultList;
}
/**
 * 根据服务产品编号和客户号查询产品签约信息(网银端查询签约信息)
 * @param prodNo
 * @param custNo
 * @return
 * @throws SQLException
 */
public List<SignProd> getSignProdInfoByPro(String prodNo, String custNo) throws SQLException {
	IDB session = DBFactory.getDB();
	List<SignProd> obj = session.getObjectList("select * from tSIGN_PROD where prod_no=? and cust_no=? and sign_status_cd=? " , SignProd.class, prodNo, custNo, IDict.K_SIGNSTATUS.K_SIGNSTATUS_YES);
	return obj;
}

}
