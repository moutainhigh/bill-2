/********************************************
* 文件名称: FacCreateFlowDao.java
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
package com.herongtech.console.domain.common.dao;

import java.sql.SQLException;
import java.util.List;

import com.herongtech.commons.tools.BeanFormat;
import com.herongtech.console.core.common.search.QueryCondition;
import com.herongtech.console.domain.common.bean.FacCreateFlow;
import com.herongtech.console.domain.common.bean.SearchFacCreateFlowBean;
import com.herongtech.db.impl.session.DBFactory;
import com.herongtech.db.interfaces.IDB;
public class FacCreateFlowDao{

public int addFacCreateFlow(FacCreateFlow obj) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("insert into tfac_create_flow(id,bill_no,rgct_id,source,remitter_bank_no,loan_requisition,bill_money,bill_type,bill_class,aim_branch_no,create_date,create_time,status,bill_id,fac_type,facility_code,cust_no,acceptor,due_dt,product_id,owner_party_id,is_cyc,branch_no,release_date,release_time)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
	obj.getId(),obj.getBillNo(),obj.getRgctId(),obj.getSource(),obj.getRemitterBankNo(),
	obj.getLoanRequisition(),obj.getBillMoney(),obj.getBillType(),obj.getBillClass(),
	obj.getAimBranchNo(),obj.getCreateDate(),obj.getCreateTime(),obj.getStatus(),
	obj.getBillId(),obj.getFacType(),obj.getFacilityCode(),obj.getCustNo(),
	obj.getAcceptor(),obj.getDueDt(),obj.getProductId(),obj.getOwnerPartyId(),
	obj.getIsCyc(),obj.getBranchNo(),obj.getReleaseDate(),obj.getReleaseTime());
}

public int deleteFacCreateFlow(FacCreateFlow obj) throws SQLException {
	IDB session = DBFactory.getDB();
	return session.execute("delete from tfac_create_flow where id=?",obj.getId());
}

public int deleteFacCreateFlow(Long id) throws SQLException {
	IDB session = DBFactory.getDB();
	return session.execute("delete from tfac_create_flow where id=?",id);
}

public int modifyFacCreateFlow(FacCreateFlow obj) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("update tfac_create_flow set bill_no=?,rgct_id=?,source=?,remitter_bank_no=?,loan_requisition=?,bill_money=?,bill_type=?,bill_class=?,aim_branch_no=?,create_date=?,create_time=?,status=?,bill_id=?,fac_type=?,facility_code=?,cust_no=?,acceptor=?,due_dt=?,product_id=?,owner_party_id=?,is_cyc=?,branch_no=?,release_date=?,release_time=? where id=?",
	obj.getBillNo(),obj.getRgctId(),obj.getSource(),obj.getRemitterBankNo(),
	obj.getLoanRequisition(),obj.getBillMoney(),obj.getBillType(),obj.getBillClass(),
	obj.getAimBranchNo(),obj.getCreateDate(),obj.getCreateTime(),obj.getStatus(),
	obj.getBillId(),obj.getFacType(),obj.getFacilityCode(),obj.getCustNo(),
	obj.getAcceptor(),obj.getDueDt(),obj.getProductId(),obj.getOwnerPartyId(),
	obj.getIsCyc(),obj.getBranchNo(),obj.getReleaseDate(),obj.getReleaseTime(),
	obj.getId());
}

public int modifyFacCreateFlow(FacCreateFlow obj,Long id) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("update tfac_create_flow set bill_no=?,rgct_id=?,source=?,remitter_bank_no=?,loan_requisition=?,bill_money=?,bill_type=?,bill_class=?,aim_branch_no=?,create_date=?,create_time=?,status=?,bill_id=?,fac_type=?,facility_code=?,cust_no=?,acceptor=?,due_dt=?,product_id=?,owner_party_id=?,is_cyc=?,branch_no=?,release_date=?,release_time=? where id=?",
	obj.getBillNo(),obj.getRgctId(),obj.getSource(),obj.getRemitterBankNo(),
	obj.getLoanRequisition(),obj.getBillMoney(),obj.getBillType(),obj.getBillClass(),
	obj.getAimBranchNo(),obj.getCreateDate(),obj.getCreateTime(),obj.getStatus(),
	obj.getBillId(),obj.getFacType(),obj.getFacilityCode(),obj.getCustNo(),
	obj.getAcceptor(),obj.getDueDt(),obj.getProductId(),obj.getOwnerPartyId(),
	obj.getIsCyc(),obj.getBranchNo(),obj.getReleaseDate(),obj.getReleaseTime(),
	id);
}

public FacCreateFlow getFacCreateFlow(Long id) throws SQLException {
	IDB session = DBFactory.getDB();
	FacCreateFlow obj = (FacCreateFlow)session.getObject("select * from tfac_create_flow where id=?",FacCreateFlow.class,id);
	return obj;
}


public List<FacCreateFlow> getFacCreateFlowByCondition(SearchFacCreateFlowBean searchBean) throws SQLException {
	QueryCondition qc=new QueryCondition();
	IDB session = DBFactory.getDB();
	searchBean.setDfaultSrchTbAliasName("FAC");
	String baseSql = "SELECT FAC.* FROM TFAC_CREATE_FLOW FAC WHERE 1 = 1 ";
	try {
		qc.initFromSearchBean(searchBean);
	} catch (Exception e1) {
		e1.printStackTrace();
	}
	String allSql = qc.getAllSqlString(baseSql);
	return (List<FacCreateFlow>)session.getObjectListByList(allSql,FacCreateFlow.class,qc.getParameterValues());
}
}
