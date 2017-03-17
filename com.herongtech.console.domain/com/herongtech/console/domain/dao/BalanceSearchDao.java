package com.herongtech.console.domain.dao;

import java.sql.SQLException;
import java.util.List;

import com.herongtech.console.core.common.json.Page;
import com.herongtech.console.core.common.search.QueryCondition;
import com.herongtech.console.domain.disc.bean.DiscInfo;
import com.herongtech.console.domain.rebuy.bean.RebuyInfo;
import com.herongtech.console.domain.save.bean.SaveInfo;
import com.herongtech.console.domain.subcoll.bean.SubcollInfo;
import com.herongtech.db.impl.session.DBFactory;
import com.herongtech.db.interfaces.IDB;
import com.herongtech.rc.domain.bean.BillSearchBean;

public class BalanceSearchDao {
	
	public List getBalanceBill(String baseSql,BillSearchBean bean,Page page,QueryCondition qc,Class clazz) throws SQLException{
		IDB session = DBFactory.getDB();
		//分页开始位置
		int startIndex = (page.getCurrentPage()-1)*page.getShowCount()+1;
		if(startIndex<0){
			startIndex = 0;
		}
		try {
			qc.initFromSearchBean(bean);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String allSql = qc.getAllSqlString(baseSql);
		page.setTotalResult(session.accountByList(qc.getCountSql("distinct info.bill_no"), qc.getParameterValues()));
		return session.getBeanListByListForPage(allSql, clazz, startIndex, page.getShowCount(), qc.getParameterValues());
	}
	/**
	 * 贴现库存余额
	 */
	public List<DiscInfo> getDiscBillBalanceInfo(BillSearchBean bean,Page page,QueryCondition qc) throws Exception{
		String baseSql = "select info.*,apply.* " +
				"from  tDisc_Bill_Info  info,tDisc_Apply_info  apply,trgct_bill_info bill,tacct_balance balance " +
				"where info.disc_id=apply.disc_id and bill.Id=info.rgct_Id and balance.rgct_id=bill.id ";
		return this.getBalanceBill(baseSql, bean, page, qc, DiscInfo.class);
	}
	/**
	 * 转贴现转入余额
	 */
	public List<RebuyInfo> getRebuyBillBalanceInfo(BillSearchBean bean,Page page,QueryCondition qc) throws Exception{
		String baseSql = "select info.*,apply.* " +
				"from  trebuy_Bill_Info  info,trebuy_Apply_info  apply,trgct_bill_info bill,tacct_balance balance " +
				"where info.rebuy_id=apply.rebuy_id and bill.Id=info.rgct_Id and balance.rgct_id=bill.id ";
		return this.getBalanceBill(baseSql, bean, page, qc, RebuyInfo.class);
	}
	/**
	 * 质押票据
	 */
	public List<SaveInfo> getSaveBillBalanceInfo(BillSearchBean bean,Page page,QueryCondition qc) throws Exception{
		String baseSql = "select info.*,apply.* " +
				"from  tsave_Bill_Info  info,tsave_Apply_info  apply,trgct_bill_info bill,tacct_balance balance " +
				"where info.save_id=apply.save_id and bill.Id=info.rgct_Id and balance.rgct_id=bill.id ";
		return this.getBalanceBill(baseSql, bean, page, qc, SaveInfo.class);
	}
	
	/**
	 * 托收在途
	 */
	public List<SubcollInfo> getSubCollBillBalanceInfo(BillSearchBean bean,Page page,QueryCondition qc) throws Exception{
		String baseSql = "select info.*,apply.* " +
				"from  tSubColl_Bill_Info  info,tSubColl_Apply_info  apply,trgct_bill_info bill " +
				"where info.SubColl_id=apply.SubColl_id and bill.Id=info.rgct_Id ";
		return this.getBalanceBill(baseSql, bean, page, qc, SubcollInfo.class);
	}
}
