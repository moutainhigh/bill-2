package com.herongtech.console.service.busiservice.common;

import java.sql.SQLException;
import java.util.List;

import com.herongtech.console.domain.common.bean.ProfessionInvestDirection;

public interface ICommonService {
	
	/**
	 * 功能描述：为申请批次提供行业投向
	 * @return
	 * @throws SQLException
	 */
	public List<ProfessionInvestDirection> getAllProfessionInvestDirection()throws SQLException; 

	/**获取付息方式的字符串*/
	public String getPayTypeString(String paytype)throws SQLException;
	
	/**根据billtype的数字获取对应的billtype字符串*/
	public String getBillTypeStringbybilltype(String billtype)throws SQLException ;
	
	/**根据billclass的数字获取对应的billclass字符串*/
	public String getBillClassStringbybillclass(String billclass)throws SQLException;
}
