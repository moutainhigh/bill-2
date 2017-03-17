package com.herongtech.console.service.acct;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.herongtech.console.core.util.ResourceUtil;
import com.herongtech.console.domain.acct.bean.AcctBalance;
import com.herongtech.console.domain.acct.dao.AcctBalanceDao;
import com.herongtech.console.domain.bean.BillInfoDTO;
import com.herongtech.console.service.interfaces.IAcctBalanceService;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.sysconstant.ISysErrorNo;

public class AcctBalanceService  implements IAcctBalanceService{

	@Override
	public List<AcctBalance> getAcctBalanceByBillNo(List<BillInfoDTO> bills) throws BizAppException {
		String acctBrchNo = ResourceUtil.getSessionLoginfo().getBranchNo();
		AcctBalanceDao acctBalanceDao=new AcctBalanceDao();
		List<AcctBalance> acctBalances = new ArrayList<AcctBalance>();
		StringBuilder sb = new StringBuilder(bills.size()*(8+3));
		for (BillInfoDTO bill : bills) {
			if(!bill.isNeedCheckRiskBill()) {	
				continue;
			}
			sb.append("'").
			append(bill.getBillNo()).
			append("',");
		}
		if(sb.length() == 0) {
			return new ArrayList<AcctBalance>();
		}
		sb.setLength(sb.length() - 1);
		String billNos = sb.toString();
		try {
			acctBalances = acctBalanceDao.getAcctBalanceByBillNo(billNos,acctBrchNo);
		} catch (SQLException e) {
			throw new BizAppException(ISysErrorNo.ERR_DBERR,"");
		}
		return acctBalances;
	}
	/**
	 * 通过登记中心ID查询余额信息
	 * @param rgctId
	 * @return
	 * @throws BizAppException
	 */
	public  AcctBalance getAcctBalanceByRgctId(String rgctId) throws BizAppException {
		AcctBalanceDao acctBalanceDao=new AcctBalanceDao();
		AcctBalance acctBalance = new AcctBalance();
		try {
			acctBalance = acctBalanceDao.getAcctBalanceByRgctId(rgctId);
		} catch (SQLException e) {
			throw new BizAppException(ISysErrorNo.ERR_DBERR,"获取AcctBalance实体bean失败");
		}
		return acctBalance;
	}
}
