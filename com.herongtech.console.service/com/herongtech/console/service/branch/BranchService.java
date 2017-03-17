/********************************************
 * 文件名称: BranchService.java
 * 名称: 基础技术平台V2.0
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 版本: 2.0.0.1
 * 开发人员: fqz
 * 开发时间: 2016-7-12 下午01:48:52
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期    修改人员    修改说明
 *********************************************/

package com.herongtech.console.service.branch;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.herongtech.console.domain.bean.Branch;
import com.herongtech.console.domain.dao.BranchDao;
import com.herongtech.console.service.interfaces.IBranchService;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.sysconstant.ISysErrorNo;

/**
 * 机构取法
 *
 */
public class BranchService implements IBranchService{
	public static final String HUNDSUN_VERSION="@system 票据管理平台 @version 2.0.0.1 @lastModiDate @describe ";

	/**
	 * 获取机构表
	 */
	public  Branch getBranch(String branchNo)throws BizAppException{
		BranchDao dao=new BranchDao();
		try {
			return dao.getBranch(branchNo);
		}catch (SQLException e) {
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "");
		}
	}
	
	/**
	 * 插入机构表
	 */
	public void addBranch(Branch branch)throws BizAppException{
		BranchDao dao=new BranchDao();
		try {
			if (dao.addBranch(branch) != 1) {
			      throw new BizAppException(ISysErrorNo.ERR_DBERR, "增加Branch失败");
			}
		} catch (SQLException e) {
             throw new BizAppException(ISysErrorNo.ERR_DBERR,"");
		}
		
	}
	/**
	 * 修改机构表
	 */
	public void modifyBranch(Branch branch)throws BizAppException{
		BranchDao dao=new BranchDao();
		Branch bran=this.getBranch(branch.getBranchNo());
		bran.setBranchName(branch.getBranchName());
		bran.setShortName(branch.getShortName());
		bran.setOrgCode(branch.getOrgCode());
		bran.setPayBankNo(branch.getPayBankNo());
		bran.setElecAuth(branch.getElecAuth());
		bran.setIfEffective(branch.getIfEffective());
		bran.setRemark1(branch.getRemark1());
		bran.setRemark2(branch.getRemark2());
		bran.setRemark3(branch.getRemark3());
		try {
			if (dao.modifyBranch(bran) != 1) {
				 throw new BizAppException(ISysErrorNo.ERR_DBERR, "修改Branch失败");
			}
		} catch (SQLException e) {
             throw new BizAppException(ISysErrorNo.ERR_DBERR,"");
		}
		
	}
	/**
	 * 根据机构编号获取其下级机构
	 * @param parentBrchCode
	 * @return
	 * @throws SQLException
	 */
	public List<Branch> getAllBranchByParentBrchCode(String parentBrchCode) throws SQLException{
		BranchDao dao=new BranchDao();
		List<Branch> list = new ArrayList<Branch>();
		list = dao.getAllBranchByParentBrchCode(parentBrchCode);
		return list;
	}
	
	/**
	 * 查询是否存在子类
	 */
	public int isExists(String parentBrchCode) throws SQLException{
		BranchDao dao=new BranchDao();
		return dao.isExists(parentBrchCode);
	}
	
	/**
	 * 删除机构
	 */
	public int deleteBranch(String branchNo) throws SQLException{
		BranchDao dao=new BranchDao();
		return dao.deleteBranch(branchNo);
	}

	/**
	 * 根据登陆者的行号查询机构表中对应的法人行编号（登陆者行号对应机构表中的联行行号）
	 */
	public Branch getBranchByBrchBankNo(String brchBankNo) throws SQLException {
		BranchDao dao=new BranchDao();
		return dao.getBranchByBrchBankNo(brchBankNo);
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
		   BranchDao dao=new BranchDao();
		   return dao.getBranchByBankNo(bankNo);

		}
	
}
