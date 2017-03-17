/********************************************
* 文件名称: BranchDao.java
* 系统名称: 合融基础技术平台V3.0
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

import org.apache.commons.lang.StringUtils;

import com.herongtech.commons.tools.BeanFormat;
import com.herongtech.console.domain.bean.Branch;
import com.herongtech.db.impl.session.DBFactory;
import com.herongtech.db.interfaces.IDB;

public class BranchDao{

public int addBranch(Branch obj) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("insert into tbranch(branch_no,branch_id,branch_level,branch_name,short_name,branch_path,org_code,parent_brch_code,parent_brch_id,pay_bank_no,elec_auth,if_effective,bank_no,remark1,remark2,remark3,partner_type,address)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
	obj.getBranchNo().trim(),obj.getBranchId(),obj.getBranchLevel().trim(),obj.getBranchName().trim(),obj.getShortName().trim(),
	obj.getBranchPath().trim(),obj.getOrgCode(),obj.getParentBrchCode().trim(),obj.getParentBrchId(),
	obj.getPayBankNo().trim(),obj.getElecAuth().trim(),obj.getIfEffective().trim(),obj.getBankNo(),
	obj.getRemark1(),obj.getRemark2(),obj.getRemark3(),obj.getPartnerType(),obj.getAddress());
}

public int deleteBranch(Branch obj) throws SQLException {
	IDB session = DBFactory.getDB();
	return session.execute("delete from tbranch where branch_no=?",obj.getBranchNo());
}

public int deleteBranch(String branchNo) throws SQLException {
	IDB session = DBFactory.getDB();
	return session.execute("delete from tbranch where branch_no=?",branchNo);
}

public int modifyBranch(Branch obj) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("update tbranch set branch_id=?,branch_level=?,branch_name=?,short_name=?,branch_path=?,org_code=?,parent_brch_code=?,parent_brch_id=?,pay_bank_no=?,elec_auth=?,if_effective=?,bank_no=?,remark1=?,remark2=?,remark3=?,partner_type=?,address=? where branch_no=?",
	obj.getBranchId(),obj.getBranchLevel().trim(),obj.getBranchName().trim(),obj.getShortName().trim(),
	obj.getBranchPath().trim(),obj.getOrgCode(),obj.getParentBrchCode().trim(),obj.getParentBrchId(),
	obj.getPayBankNo().trim(),obj.getElecAuth().trim(),obj.getIfEffective().trim(),obj.getBankNo(),
	obj.getRemark1(),obj.getRemark2(),obj.getRemark3(),obj.getPartnerType(),obj.getAddress(),obj.getBranchNo().trim());
}

public int modifyBranch(Branch obj,String branchNo) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("update tbranch set branch_id=?,branch_level=?,branch_name=?,short_name=?,branch_path=?,org_code=?,parent_brch_code=?,parent_brch_id=?,pay_bank_no=?,elec_auth=?,if_effective=?,bank_no=?,remark1=?,remark2=?,remark3=?,partner_type=?,address=? where branch_no=?",
	obj.getBranchId(),obj.getBranchLevel(),obj.getBranchName(),obj.getShortName(),
	obj.getBranchPath(),obj.getOrgCode(),obj.getParentBrchCode(),obj.getParentBrchId(),
	obj.getPayBankNo(),obj.getElecAuth(),obj.getIfEffective(),obj.getBankNo(),
	obj.getRemark1(),obj.getRemark2(),obj.getRemark3(),obj.getPartnerType(),obj.getAddress(),branchNo);
}

public Branch getBranch(String branchNo) throws SQLException {
	IDB session = DBFactory.getDB();
	Branch obj = (Branch)session.getObject("select * from tbranch where branch_no=?",Branch.class,branchNo);
	return obj;
}
/**
 * 根据机构编号获取其子级机构
 * @param parentBrchCode
 * @return
 * @throws SQLException
 */
public List<Branch> getBranchByParentBrchCode(String parentBrchCode) throws SQLException {
	IDB session = DBFactory.getDB();
	String sql = "select * from tbranch t";
	if(StringUtils.isNotBlank(parentBrchCode)){
		sql += " where t.parent_brch_code = ?";
		return (List<Branch>)session.getObjectList(sql+" order by t.branch_level",Branch.class,parentBrchCode);
	}
	return (List<Branch>)session.getObjectList(sql+" order by t.branch_level",Branch.class);
}

/**根据法人行编号获得所有机构*/
public List<Branch> getBranchBybankno(String bankno) throws SQLException {
	IDB session = DBFactory.getDB();
	String sql = "select * from tbranch t";
	if(StringUtils.isNotBlank(bankno)){
		sql += " where t.bank_no = ?";
		return (List<Branch>)session.getObjectList(sql+" order by t.branch_level",Branch.class,bankno);
	}
	return null;
}

/**根据登陆者的行号查询机构表中对应的法人行编号（登陆者行号对应机构表中的联行行号）*/
public Branch getBranchByBrchBankNo(String brchBankNo) throws SQLException {
	IDB session = DBFactory.getDB();
	Branch obj = (Branch)session.getObject("select * from tbranch where pay_bank_no=?",Branch.class,brchBankNo);
	return obj;
}

public Branch getBranchByBranchId(String branchId) throws SQLException {
	IDB session = DBFactory.getDB();
	Branch obj = (Branch)session.getObject("select * from tbranch where branch_id=?",Branch.class,branchId);
	return obj;
}


/**
 * 根据机构编号获取其包括子级和孙子级在内的所有机构
 * @param parentBrchCode
 * @return
 * @throws SQLException
 */
public List<Branch> getAllBranchByParentBrchCode(String parentBrchCode) throws SQLException {
	List<Branch> list = new ArrayList<Branch>();
	list = getBranchByParentBrchCode(parentBrchCode);
	if(StringUtils.isBlank(parentBrchCode)) return list;
	for(int i=0;i<list.size();i++){
		List<Branch> objList = getBranchByParentBrchCode(list.get(i).getBranchNo());
		if(objList!=null && objList.size()>0){
			list.addAll(objList);
		}
	}
	return list;
}
/**
 * 查询是否存在子类
 */
public int isExists(String parentBrchCode) throws SQLException {
	IDB session = DBFactory.getDB();
	String sql = "select count(0) from tbranch t where t.parent_brch_code = ?";
	return session.account(sql, parentBrchCode);
}

/**
 * 根据行号查找机构信息(多机构共用行号的情况，仅用来判断是我行)
 * @param payBankNo 联行号
 * @param bankNo 法人行标识
 * @return
 * @throws SQLException
 */
public Branch getBranch(String payBankNo,String bankNo) throws SQLException {
	IDB session = DBFactory.getDB();
	String sql = "select * from tbranch t where t.pay_bank_no = ?";
	Branch obj =null;
	if(bankNo!=null && !"".equals(bankNo)){
		sql=sql+" and t.bank_no=? ";
		obj = (Branch)session.getObject(sql,Branch.class,payBankNo,bankNo);
	}else{
		obj = (Branch)session.getObject(sql,Branch.class,payBankNo);
	}
	return obj;
}


/**
 * 
 * 根据联行行号查找机构信息
 * 
 * @param bankNo
 * @return
 * @throws ServiceException
 */

public Branch getBranchByBankNo(String bankNo) throws SQLException {
		IDB session = DBFactory.getDB();
		Branch obj = (Branch)session.getObject("select * from tbranch where pay_bank_no=?",Branch.class,bankNo);
		return obj;
	}

}
