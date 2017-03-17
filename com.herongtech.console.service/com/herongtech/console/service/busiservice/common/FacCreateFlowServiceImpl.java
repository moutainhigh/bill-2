package com.herongtech.console.service.busiservice.common;

import java.sql.SQLException;
import java.util.List;

import com.herongtech.console.domain.common.bean.FacCreateFlow;
import com.herongtech.console.domain.common.bean.SearchFacCreateFlowBean;
import com.herongtech.console.domain.common.dao.FacCreateFlowDao;
import com.herongtech.console.service.interfaces.IFacCreateFlowService;

public class FacCreateFlowServiceImpl implements IFacCreateFlowService{
	private FacCreateFlowDao facDao = new FacCreateFlowDao();

	/**
	 * 功能描述：添加额度扣减流水
	 * @param fac
	 * @throws SQLException 
	 */
	public void addFacCreateFlow(FacCreateFlow fac) throws SQLException{
		facDao.addFacCreateFlow(fac);
	}

	/**
	 * 功能描述：更新流水表
	 * @param facCreateFlow
	 */
	public void updateFacCreateFlow(FacCreateFlow fac) throws SQLException{
		this.facDao.modifyFacCreateFlow(fac);
	}

	/**
	 * 功能描述：根据条件查询流水
	 * @param searchBean
	 * @return
	 * @throws SQLException
	 */
	public List<FacCreateFlow> getFacCreateFlowByCondition(
			SearchFacCreateFlowBean searchBean) throws SQLException {
		return facDao.getFacCreateFlowByCondition(searchBean);
	}
	
}
