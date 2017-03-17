/********************************************
 * 文件名称: BlackService.java
 * 系统名称: 基础技术平台V2.0
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: 2.0.0.1
 * 开发人员: yanjl
 * 开发时间: 2016-6-15 下午01:48:52
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期    修改人员    修改说明
 *********************************************/
package com.herongtech.console.service.black;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.herongtech.console.domain.bean.BillInfoDTO;
import com.herongtech.console.domain.bean.BlackList;
import com.herongtech.console.domain.dao.BlackListDao;
import com.herongtech.console.service.interfaces.IBlackService;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.sysconstant.ISysErrorNo;

/**
 * 黑名单参数取法
 *
 */

public class BlackService implements IBlackService {

	@Override
	public BlackList getParam(String unionBankno) throws BizAppException {
		BlackListDao dao=new BlackListDao();
		try {
			return dao.getBlackList(unionBankno);
		} catch (SQLException e) {
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "");
		}
	}

	/**
	 * 插入黑名单表
	 */
	
	@Override
	public void addParam(BlackList black) throws BizAppException {
		BlackListDao dao=new BlackListDao();
		try {
			if (dao.addBlackList(black) != 1) {
			      throw new BizAppException(ISysErrorNo.ERR_DBERR, "增加黑名单失败");
			}
		} catch (SQLException e) {
             throw new BizAppException(ISysErrorNo.ERR_DBERR,"");
		}
	}

	/**
	 * 修改黑名单表
	 */
	
	@Override
	public void modifyParam(BlackList black) throws BizAppException {
		BlackListDao dao=new BlackListDao();
		try {
			if (dao.modifyBlackList(black) != 1) {
				 throw new BizAppException(ISysErrorNo.ERR_DBERR, "修改黑名单失败");
			}
		} catch (SQLException e) {
             throw new BizAppException(ISysErrorNo.ERR_DBERR,"");
		}
		
	}
	public  List<BlackList> getBlackListByBills(List<BillInfoDTO> bills) throws BizAppException {
		BlackListDao dao=new BlackListDao();
		Set<String> set = new HashSet<String>();
		for (BillInfoDTO bill:bills) {
			if(bill.getCheckBankNo() == null) {	
				continue;
			}
			set.addAll(bill.getCheckBankNo());
		}
		if(set.size() == 0) {
			return new ArrayList<BlackList>();
		}
		String[] blankNos = set.toArray(new String[set.size()]);
		
		StringBuilder sb = new StringBuilder( );
		for (String bankNo :blankNos) {
			sb.append("'"+bankNo+"',");
		}
		sb.setLength(sb.length() - 1);
		try {
			return dao.getBlackBillByBills(sb.toString());
		} catch (SQLException e) {
             throw new BizAppException(ISysErrorNo.ERR_DBERR,"");
		}
	}

}
