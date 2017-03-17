/********************************************
 * 文件名称: CommonSearchService.java
 * 名称: 基础技术平台V2.0
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 版本: 2.0.0.1
 * 开发人员: fqz
 * 开发时间: 2016-7-12 下午01:48:52
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期    修改人员    修改说明
 *********************************************/

package com.herongtech.console.service.happensearch;

import java.sql.SQLException;
import java.util.List;

import com.herongtech.console.core.common.json.Page;
import com.herongtech.console.core.common.search.BaseSearchBean;
import com.herongtech.console.core.common.search.OrderBean;
import com.herongtech.console.domain.bean.HappenSearchBean;
import com.herongtech.console.domain.common.dao.HappenSearchDao;
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

/**
 * 机构取法
 *
 */
public class HappenSearchService implements IHappenSearchService{
	public static final String HUNDSUN_VERSION="@system 票据管理平台 @version 2.0.0.1 @lastModiDate @describe ";
	
	
	/**
	 * 初始化查询条件
	 * @param query
	 */
	private void initQueryCondition(HappenSearchBean query){
		query.setDfaultSrchTbAliasName("bill");
	    query.addSpecialOpertion("endDay",BaseSearchBean.LESS_AND_EQUAL);
	    query.addSqlPropretyMapping("endDay", "dueDt");
	    query.addSpecialOpertion("startDay",BaseSearchBean.MORE_AND_EQUAL);
        query.addSqlPropretyMapping("startDay", "dueDt");
		query.addSpecialOpertion("opers",BaseSearchBean.IN);
		query.addSqlPropretyMapping("opers", "operStatus");
		query.addSqlPropretyMapping("operStatus", "operStatus");
		query.addSqlPropretyMapping("acctNo", "custAccount");
		query.addSpecialOpertion("lastBillNo", BaseSearchBean.LIKE);
		query.addSqlPropretyMapping("lastBillNo", "lastBillNo");
	}
	/**
	 * 功能描述：贴现发生查询
	 * @param quary
	 * @return
	 * @throws SQLException
	 */
	public List<DiscInfo> getDiscBillInfoByStatus(Page page,HappenSearchBean query) throws SQLException {
		HappenSearchDao dbdao=new HappenSearchDao();
		initQueryCondition(query);
		OrderBean order=new OrderBean("discmxId",false);
	    query.appendOrder(order);
		return dbdao.getDiscBillListBystatus(page, query);
		
	}
	/**
	 * 功能描述：转入发生查询
	 * @param quary
	 * @return
	 * @throws SQLException
	 */
	public List<RebuyInfo> getRebuyBillInfoByStatus(Page page,HappenSearchBean query) throws SQLException {
		HappenSearchDao dbdao=new HappenSearchDao();
		initQueryCondition(query);
		OrderBean order=new OrderBean("rebuymxId",false);
	    query.appendOrder(order);
		return dbdao.getRebuyBillListBystatus(page, query);
		
	}
	
	
	/**
	 * 功能描述：转卖发生查询
	 * @param quary
	 * @return
	 * @throws SQLException
	 */
	public List<SaleInfo> getSaleBillInfoByStatus(Page page,HappenSearchBean query) throws SQLException {
		HappenSearchDao dbdao=new HappenSearchDao();
		initQueryCondition(query);
		query.addProperty2TableObj("salemxId", "bill");
		OrderBean order=new OrderBean("salemxId",false);
	    query.appendOrder(order);
		return dbdao.getSaleBillListBystatus(page, query);
		
	}
	
	
	/**
	 * 功能描述：转入发生查询
	 * @param quary
	 * @return
	 * @throws SQLException
	 */
	public List<SaveInfo> getSaveBillInfoByStatus(Page page,HappenSearchBean query) throws SQLException {
		HappenSearchDao dbdao=new HappenSearchDao();
		initQueryCondition(query);
		OrderBean order=new OrderBean("savemxId",false);
	    query.appendOrder(order);
		return dbdao.getSaveBillListBystatus(page, query);
		
	}
	
	/**
	 * 功能描述：托收发生查询
	 * @param quary
	 * @return
	 * @throws SQLException
	 */
	public List<SubcollInfo> getSubcollBillInfoByStatus(Page page,HappenSearchBean query) throws SQLException {
		HappenSearchDao dbdao=new HappenSearchDao();
		initQueryCondition(query);
		query.addProperty2TableObj("subcollmxId", "bill");
		OrderBean order=new OrderBean("subcollmxId",false);
	    query.appendOrder(order);
		return dbdao.getSubcollBillListBystatus(page, query);
		
	}
	
	
}
