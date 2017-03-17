/********************************************
 * 文件名称: TradeBankService.java
 * 系统名称: 基础技术平台V2.0
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: 2.0.0.1
 * 开发人员: dq
 * 开发时间: 2016-8-1 下午15:45:52
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期    修改人员    修改说明
 *********************************************/
package com.herongtech.console.service.tradeBank;

import java.sql.SQLException;

import com.herongtech.console.domain.bean.TradeBank;
import com.herongtech.console.domain.dao.TradeBankDao;
import com.herongtech.console.service.interfaces.ITradeBankService;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.sysconstant.ISysErrorNo;
/**
 * 交易对手信息维护表方法
 *
 */
public class TradeBankService implements ITradeBankService{

	/**
	 * 获取交易对手信息维护表
	 * 
	 * @param childBankno 分支行行号 
	 */
	public TradeBank getTradeBank(String childBankno) throws BizAppException {
		TradeBankDao dao = new TradeBankDao();
		try {
			return dao.getTradeBank(childBankno);
		}catch (SQLException e) {
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "");
		}
	}

	/**
	 * 插入交易对手信息维护表
	 *
	 */
	public void addTradeBank(TradeBank tradeBank) throws BizAppException {
		TradeBankDao dao = new TradeBankDao();
		try {
			if (dao.addTradeBank(tradeBank) != 1) {
			      throw new BizAppException(ISysErrorNo.ERR_DBERR, "增加TradeBank失败");
			}
		} catch (SQLException e) {
             throw new BizAppException(ISysErrorNo.ERR_DBERR,"");
		}
		
	}

	/**
	 * 修改交易对手信息维护表
	 */
	public void modifyTradeBank(TradeBank tradeBank) throws BizAppException {
		TradeBankDao dao = new TradeBankDao();
		try {
			if (dao.modifyTradeBank(tradeBank) != 1) {
				 throw new BizAppException(ISysErrorNo.ERR_DBERR, "修改TradeBank失败");
			}
		} catch (SQLException e) {
             throw new BizAppException(ISysErrorNo.ERR_DBERR,"");
		}
		
		
	}
	
}
