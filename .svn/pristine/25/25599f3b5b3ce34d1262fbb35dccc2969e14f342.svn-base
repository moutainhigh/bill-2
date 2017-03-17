/********************************************
 * 文件名称: BankInfoService.java
 * 系统名称: 基础技术平台V2.0
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: 2.0.0.1
 * 开发人员: fqz
 * 开发时间: 2016-7-12 下午01:48:52
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期    修改人员    修改说明
 *********************************************/

package com.herongtech.console.service.bankinfo;

import java.sql.SQLException;

import com.herongtech.console.domain.bean.BankInfo;
import com.herongtech.console.domain.dao.BankInfoDao;
import com.herongtech.console.service.interfaces.IBankInfoService;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.sysconstant.ISysErrorNo;

/**
 * 系统参数取法
 *
 */
public class BankInfoService implements IBankInfoService{
	public static final String HUNDSUN_VERSION="@system 票据管理平台 @version 2.0.0.1 @lastModiDate @describe ";

	/**
	 * 获取系统参数表
	 */
	public  BankInfo getBankInfo(String bankNo)throws BizAppException{
		BankInfoDao dao=new BankInfoDao();
		try {
			return dao.getBankInfo(bankNo);
		}catch (SQLException e) {
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "");
		}
	}
	
	/**
	 * 插入系统参数表
	 */
	public void addBankInfo(BankInfo bankInfo)throws BizAppException{
		BankInfoDao dao=new BankInfoDao();
		try {
			if (dao.addBankInfo(bankInfo) != 1) {
			      throw new BizAppException(ISysErrorNo.ERR_DBERR, "增加BankInfo失败");
			}
		} catch (SQLException e) {
             throw new BizAppException(ISysErrorNo.ERR_DBERR,"");
		}
		
	}
	/**
	 * 修改系统参数表
	 */
	public void modifyBankInfo(BankInfo bankInfo)throws BizAppException{
		BankInfoDao dao=new BankInfoDao();
		try {
			if (dao.modifyBankInfo(bankInfo) != 1) {
				 throw new BizAppException(ISysErrorNo.ERR_DBERR, "修改BankInfo失败");
			}
		} catch (SQLException e) {
             throw new BizAppException(ISysErrorNo.ERR_DBERR,"");
		}
		
	}
}
