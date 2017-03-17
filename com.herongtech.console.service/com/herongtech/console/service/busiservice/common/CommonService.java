package com.herongtech.console.service.busiservice.common;

import java.sql.SQLException;
import java.util.List;

import com.herongtech.console.domain.common.bean.ProfessionInvestDirection;
import com.herongtech.console.domain.common.dao.ProfessionInvestDirectionDao;
import com.herongtech.console.domain.dao.DictDao;


public class CommonService implements ICommonService {
	ProfessionInvestDirectionDao pfidDao = new ProfessionInvestDirectionDao();
	
	/**
	 * 功能描述：为申请批次提供行业投向
	 * @return
	 * @throws SQLException
	 */
	public List<ProfessionInvestDirection> getAllProfessionInvestDirection() throws SQLException {
		return pfidDao.getAllProfessionInvestDirection();
	}

	
	@Override
	public String getPayTypeString(String paytype) throws SQLException {
		DictDao dao = new DictDao();
		String payty = dao.getPayTypeStringbypaytype(paytype);
		return payty;
	}


	@Override
	public String getBillClassStringbybillclass(String billclass)
			throws SQLException {
		DictDao dao = new DictDao();
		String billcs = dao.getBillClassStringbybillclass(billclass);
		return billcs;
	}


	@Override
	public String getBillTypeStringbybilltype(String billtype)
			throws SQLException {
		DictDao dao = new DictDao();
		String billtp = dao.getBillTypeStringbybilltype(billtype);
		return billtp;
	}
	
	
}
