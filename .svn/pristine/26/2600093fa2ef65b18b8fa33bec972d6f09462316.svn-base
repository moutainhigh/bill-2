package com.herongtech.console.service.accountFlowSearch;

import java.sql.SQLException;
import java.util.List;

import com.herongtech.console.core.common.json.Page;
import com.herongtech.console.domain.acct.bean.AcctFlow;
import com.herongtech.console.domain.acct.bean.AcctFlowInfo;
import com.herongtech.console.domain.acct.bean.AcctFlowSearchBean;
import com.herongtech.console.domain.acpt.bean.AcptApplyInfo;
import com.herongtech.console.domain.acpt.bean.AcptBillInfo;
import com.herongtech.console.domain.acpt.bean.AcptQueryCondition;


public interface IAccountFlowSearchService {
	
	
	/**
	 * 功能描述：根据条件查询批次列表(账务流水使用)
	 * @param page
	 * @param query
	 * @return
	 * @throws SQLException
	 */
	public List<AcctFlow> getAccountFlowCondition(Page page,AcctFlowSearchBean query) throws SQLException;


	/**
	 * 根据主键查询批次信息
	 * @param query
	 * @return
	 */
	public AcctFlow getAcctFlowForAfID(AcctFlowSearchBean query) throws SQLException;
	
	
	
	/**
	 * 功能描述：根据条件查询清单
	 * @param page，query
	 * @return
	 * @throws SQLException
	 */
	public List<AcctFlowInfo> getAcctFlowInfoListForBatch(Page page,AcctFlowSearchBean query) throws Exception;
	
	
	
	
}
