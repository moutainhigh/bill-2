package com.herongtech.console.service.happensearch;

import java.sql.SQLException;
import java.util.List;

import com.herongtech.console.core.common.json.Page;
import com.herongtech.console.domain.bean.HappenSearchBean;
import com.herongtech.console.domain.disc.bean.DiscInfo;
import com.herongtech.console.domain.disc.bean.DiscSearchBean;
import com.herongtech.console.domain.rebuy.bean.RebuyInfo;
import com.herongtech.console.domain.rebuy.bean.RebuySearchBean;
import com.herongtech.console.domain.sale.bean.SaleInfo;
import com.herongtech.console.domain.sale.bean.SaleSearchBean;
import com.herongtech.console.domain.save.bean.SaveInfo;
import com.herongtech.console.domain.save.bean.SaveSearchBean;
import com.herongtech.console.domain.subcoll.bean.SubcollInfo;
import com.herongtech.console.domain.subcoll.bean.SubcollQueryCondition;

public interface IHappenSearchService {
	
	/**
	 * 功能描述：贴现发生查询
	 * @param quary
	 * @return
	 * @throws SQLException
	 */
	public List<DiscInfo> getDiscBillInfoByStatus(Page page,HappenSearchBean query) throws SQLException;
	
	/**
	 * 功能描述：转入发生查询
	 * @param quary
	 * @return
	 * @throws SQLException
	 */
	public List<RebuyInfo> getRebuyBillInfoByStatus(Page page,HappenSearchBean query) throws SQLException;
	
	
	/**
	 * 功能描述：转卖发生查询
	 * @param quary
	 * @return
	 * @throws SQLException
	 */
	public List<SaleInfo> getSaleBillInfoByStatus(Page page,HappenSearchBean query) throws SQLException;
	
	
	/**
	 * 功能描述：质押发生查询
	 * @param quary
	 * @return
	 * @throws SQLException
	 */
	public List<SaveInfo> getSaveBillInfoByStatus(Page page,HappenSearchBean query) throws SQLException;
	
	/**
	 * 功能描述：委托收款发生查询
	 * @param quary
	 * @return
	 * @throws SQLException
	 */
	public List<SubcollInfo> getSubcollBillInfoByStatus(Page page,HappenSearchBean query) throws SQLException;


}
