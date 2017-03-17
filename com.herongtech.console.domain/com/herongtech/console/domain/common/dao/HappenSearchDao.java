/********************************************
* 文件名称: HappenSearchDao.java
* 系统名称: 合融基础技术平台V2.0
* 模块名称:
* 软件版权: 北京合融科技有限公司
* 功能说明: 
* 系统版本: 2.0.0.1
* 开发人员: FQQ
* 开发时间:  
* 审核人员:
* 相关文档:
* 修改记录: 修改日期    修改人员    修改说明
*********************************************/
package com.herongtech.console.domain.common.dao;

import java.sql.SQLException;
import java.util.List;

import com.herongtech.commons.tools.BeanFormat;
import com.herongtech.console.core.common.json.Page;
import com.herongtech.console.core.common.search.QueryCondition;
import com.herongtech.console.domain.bean.HappenSearchBean;
import com.herongtech.console.domain.common.bean.Product;
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
import com.herongtech.db.impl.session.DBFactory;
import com.herongtech.db.interfaces.IDB;
public class HappenSearchDao{
	
	//贴现发生查询（根据界面的查询条件，查询批次和清单）
	public List<DiscInfo>  getDiscBillListBystatus(Page page,HappenSearchBean query)throws SQLException{
		QueryCondition qc=new QueryCondition();
		IDB session = DBFactory.getDB();
		String baseSql = "select bill.*,apply.* from tdisc_bill_info bill,tdisc_apply_info apply where bill.disc_id=apply.disc_id";
		try {
			qc.initFromSearchBean(query);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		String allSql = qc.getAllSqlString(baseSql);
		int startIndex = (page.getCurrentPage()-1)*page.getShowCount()+1;
		if(startIndex<0)
			startIndex = 0;
		int count = session.accountByList(qc.getCountSql("distinct bill.bill_no"), qc.getParameterValues()); 
	  	 page.setTotalResult(count);
		return session.getBeanListByListForPage(allSql,DiscInfo.class, startIndex, page.getShowCount(), qc.getParameterValues());
	}
	
	
	
	//转入发生查询（根据界面的查询条件，查询批次和清单信息）
	public List<RebuyInfo>  getRebuyBillListBystatus(Page page,HappenSearchBean query)throws SQLException{
		QueryCondition qc=new QueryCondition();
		IDB session = DBFactory.getDB();
		String baseSql = "select bill.*,apply.* from trebuy_bill_info bill,trebuy_apply_info apply where bill.rebuy_id=apply.rebuy_id";
		try {
			qc.initFromSearchBean(query);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		String allSql = qc.getAllSqlString(baseSql);
		int startIndex = (page.getCurrentPage()-1)*page.getShowCount()+1;
		if(startIndex<0)
			startIndex = 0;
		int count = session.accountByList(qc.getCountSql("distinct bill.bill_no"), qc.getParameterValues()); 
		 page.setTotalResult(count);
		return session.getBeanListByListForPage(allSql,RebuyInfo.class, startIndex, page.getShowCount(), qc.getParameterValues());
	}
	
	
	//转卖发生查询（根据界面的查询条件，查询批次和清单信息）
		public List<SaleInfo>  getSaleBillListBystatus(Page page,HappenSearchBean query)throws SQLException{
			QueryCondition qc=new QueryCondition();
			IDB session = DBFactory.getDB();
			String baseSql = "select bill.*,apply.* from tsale_bill_info bill,tsale_apply_info apply where bill.sale_id=apply.sale_id";
			try {
				qc.initFromSearchBean(query);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			String allSql = qc.getAllSqlString(baseSql);
			int startIndex = (page.getCurrentPage()-1)*page.getShowCount()+1;
			if(startIndex<0)
				startIndex = 0;
			int count = session.accountByList(qc.getCountSql("distinct bill.bill_no"), qc.getParameterValues()); 
			 page.setTotalResult(count);
			return session.getBeanListByListForPage(allSql,SaleInfo.class, startIndex, page.getShowCount(), qc.getParameterValues());
		}
		
		
		//质押发生查询（根据界面的查询条件，查询批次和清单信息）
		public List<SaveInfo>  getSaveBillListBystatus(Page page,HappenSearchBean query)throws SQLException{
			QueryCondition qc=new QueryCondition();
			IDB session = DBFactory.getDB();
			String baseSql = "select bill.*,apply.* from tsave_bill_info bill,tsave_apply_info apply where bill.save_id=apply.save_id";
			try {
				qc.initFromSearchBean(query);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			String allSql = qc.getAllSqlString(baseSql);
			int startIndex = (page.getCurrentPage()-1)*page.getShowCount()+1;
			if(startIndex<0)
				startIndex = 0;
			int count = session.accountByList(qc.getCountSql("distinct bill.bill_no"), qc.getParameterValues()); 
			 page.setTotalResult(count);
			return session.getBeanListByListForPage(allSql,SaveInfo.class, startIndex, page.getShowCount(), qc.getParameterValues());
		}
		
		//托收发生查询（根据界面的查询条件，查询批次和清单信息）
		public List<SubcollInfo>  getSubcollBillListBystatus(Page page,HappenSearchBean query)throws SQLException{
			QueryCondition qc=new QueryCondition();
			IDB session = DBFactory.getDB();
			String baseSql = "select bill.*,apply.* from tsubcoll_bill_info bill,tsubcoll_apply_info apply where bill.subcoll_id=apply.subcoll_id and bill.status in ('1','2','3')";
			try {
				qc.initFromSearchBean(query);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			String allSql = qc.getAllSqlString(baseSql);
			int startIndex = (page.getCurrentPage()-1)*page.getShowCount()+1;
			if(startIndex<0)
				startIndex = 0;
			int count = session.accountByList(qc.getCountSql("distinct bill.bill_no"), qc.getParameterValues()); 
			 page.setTotalResult(count);
			 return session.getBeanListByListForPage(allSql,SubcollInfo.class, startIndex, page.getShowCount(), qc.getParameterValues());
			}
		
		
		



}
