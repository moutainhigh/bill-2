/********************************************
 * 文件名称: CustLimitService.java
 * 系统名称: 基础技术平台V2.0
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: 2.0.0.1
 * 开发人员: songzx
 * 开发时间: 2016-10-28 11:48:52
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期    修改人员    修改说明
 *********************************************/
package com.herongtech.console.service.custlimit;

import java.sql.SQLException;

import com.herongtech.console.domain.bean.CustLimit;
import com.herongtech.console.domain.bean.CustLimitFlow;
import com.herongtech.console.domain.dao.CustLimitDao;
import com.herongtech.console.domain.dao.CustLimitFlowDao;
import com.herongtech.console.service.interfaces.ICustLimitFlowService;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.sysconstant.ISysErrorNo;

public class CustLimitFlowService  implements ICustLimitFlowService{
	
	private CustLimitFlowDao flowDao = new CustLimitFlowDao();
	private CustLimitDao balanceDao = new CustLimitDao();
	
	public int addCustLimitFlow(CustLimitFlow clFlow) throws SQLException {
		flowDao.addCustLimitFlow(clFlow);
		CustLimit cl = balanceDao.getCustLimit(clFlow.getOperCustNo());
		cl.getTotalBalance();
		return 1;
	}
	
	/**
	 * 功能描述:客户占用额度操作流水
	 * @param clFlow
	 * @return
	 * @throws SQLException
	 */
	public int usedCustLimitFlow(CustLimitFlow clFlow) throws BizAppException{
		clFlow.setOperType("1");
		try {
			CustLimit cl = balanceDao.getCustLimit(clFlow.getOperCustNo());
			if( cl == null ) throw new BizAppException(ISysErrorNo.ERR_DBERR, "客户不是授信客户，请先进行授信");
			//判断操作后的占用额度
			double newUsed = cl.getUsedBalance() + clFlow.getOperMoney();
			//判断操作后的已用额度是否大于总额度 或者 操作额度是否大于可用额度
			if(newUsed>cl.getTotalBalance() || clFlow.getOperMoney() > cl.getAbleBalance()) throw new BizAppException(ISysErrorNo.ERR_DBERR, "余额不足");
			cl.setUsedBalance(newUsed);
			//可用额度
			cl.setAbleBalance(cl.getAbleBalance() - clFlow.getOperMoney());
			//添加操作流水
			flowDao.addCustLimitFlow(clFlow);
			balanceDao.modifyCustLimit(cl);
		} catch (SQLException e) {
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "占用额度操作失败");
		}
		return 1;
	}
	
	/**
	 * 功能描述:释放客户占用额度并添加操作流水
	 * @param clFlow
	 * @return
	 * @throws SQLException
	 */
	public int releaseCustLimitFlow(CustLimitFlow clFlow) throws BizAppException{
		clFlow.setOperType("0");
		try {
			CustLimit cl = balanceDao.getCustLimit(clFlow.getOperCustNo());
			if(cl.getUsedBalance() < clFlow.getOperMoney()) {
				throw new BizAppException(ISysErrorNo.ERR_DBERR, "释放占用额度大于已占用额度");
			}
			if("1".equals(cl.getIsCircle())){
				//增加释放的额度到可用额度
				cl.setAbleBalance(cl.getAbleBalance() + clFlow.getOperMoney());
			}
			//判断操作后的占用额度
			double newUsed = cl.getUsedBalance() - clFlow.getOperMoney();
			cl.setUsedBalance(newUsed);
			//添加操作流水
			flowDao.addCustLimitFlow(clFlow);
			balanceDao.modifyCustLimit(cl);
		} catch (SQLException e) {
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "释放占用额度操作失败");
		}
		return 1;
	}
}
