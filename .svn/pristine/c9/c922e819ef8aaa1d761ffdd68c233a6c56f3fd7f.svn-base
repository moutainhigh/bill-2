package com.herongtech.console.service.prodLimitType;

import java.sql.SQLException;

import com.herongtech.console.domain.common.bean.ProdLimitType;
import com.herongtech.console.domain.common.dao.ProdLimitTypeDao;
import com.herongtech.console.service.interfaces.IProdLimitTypeService;

public class ProdLimitTypeService implements IProdLimitTypeService {

	private ProdLimitTypeDao pltDao = new ProdLimitTypeDao();
	public ProdLimitType getProdLimitTypeByProdNo(String prodNo) throws SQLException{
		return pltDao.getProdLimitTypeByProdNo(prodNo);
	}
}
