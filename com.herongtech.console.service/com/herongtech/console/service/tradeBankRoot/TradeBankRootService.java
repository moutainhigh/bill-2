/********************************************
 * 文件名称: TradeBankRootService.java
 * 系统名称: 基础技术平台V2.0
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: 2.0.0.1
 * 开发人员: dq
 * 开发时间: 2016-8-2 下午15:45:52
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期    修改人员    修改说明
 *********************************************/
package com.herongtech.console.service.tradeBankRoot;

import java.sql.SQLException;

import com.herongtech.console.domain.bean.TradeBankRoot;
import com.herongtech.console.domain.dao.TradeBankRootDao;
import com.herongtech.console.service.interfaces.ITradeBankRootService;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.sysconstant.ISysErrorNo;

/**
 * 交易对手总行对照表方法
 *
 */
public class TradeBankRootService implements ITradeBankRootService{

	/**
	 * 获取交易对手总行对照表
	 * 
	 * @param bankNo 行号 
	 */
	public TradeBankRoot getTradeBankRoot(String bankNo) throws BizAppException {
		TradeBankRootDao dao = new TradeBankRootDao();
		try {
			return dao.getTradeBankRoot(bankNo);
		}catch (SQLException e) {
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "");
		}
	}

	/**
	 * 插入交易对手总行对照表
	 *
	 */
	public void addTradeBankRoot(TradeBankRoot tradeBankRoot)
			throws BizAppException {
		TradeBankRootDao dao = new TradeBankRootDao();
		try {
			if (dao.addTradeBankRoot(tradeBankRoot) != 1) {
			      throw new BizAppException(ISysErrorNo.ERR_DBERR, "增加TradeBankRoot失败");
			}
		} catch (SQLException e) {
             throw new BizAppException(ISysErrorNo.ERR_DBERR,"");
		}
		
		
	}

	/**
	 * 修改交易对手总行对照表
	 */
	public void modifyTradeBankRoot(TradeBankRoot tradeBankRoot)
			throws BizAppException {
		TradeBankRootDao dao = new TradeBankRootDao();
		try {
			if (dao.modifyTradeBankRoot(tradeBankRoot) != 1) {
				 throw new BizAppException(ISysErrorNo.ERR_DBERR, "修改TradeBankRoot失败");
			}
		} catch (SQLException e) {
             throw new BizAppException(ISysErrorNo.ERR_DBERR,"");
		}
		
		
		
	}
}
