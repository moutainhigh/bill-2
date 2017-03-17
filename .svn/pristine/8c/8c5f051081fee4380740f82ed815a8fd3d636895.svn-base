package com.herongtech.console.service.balanceSearch;

import java.util.List;

import com.herongtech.console.core.common.json.Page;
import com.herongtech.console.core.common.search.BaseSearchBean;
import com.herongtech.console.core.common.search.QueryCondition;
import com.herongtech.console.core.constant.IConstants;
import com.herongtech.console.core.constant.RcConstants;
import com.herongtech.console.core.util.StatusUtils;
import com.herongtech.console.domain.dao.BalanceSearchDao;
import com.herongtech.console.web.busicontroller.common.CollateCodeConst;
import com.herongtech.console.web.busicontroller.common.SubcollCodeConst;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.rc.domain.bean.BillSearchBean;


public class BalanceSearchService implements IBalanceSearchService {
	BalanceSearchDao balanceSearchDao = new BalanceSearchDao();
/**
 * 余额查询 type :1:贴现库存余额,2:转入余额,3:卖出回购余额,4:借票出库余额,5:质押票据,6:托收在途
 */
	public List getBalanceByType(String type,BillSearchBean bean, Page page) throws Exception{
		bean.setStatus(IConstants.BALANCE_STATUS.BALANCE);
		bean.addProperty2TableObj("status", "balance");
		bean.addSqlPropretyMapping("statusArray", "operStatus");
		bean.addSpecialOpertion("statusArray", BaseSearchBean.IN);
		bean.addProperty2TableObj("statusArray", "info");
		bean.addProperty2TableObj("prodNo", "info");
		bean.addProperty2TableObj("billNo", "bill");
		bean.addProperty2TableObj("branchNo", "info");
		bean.addProperty2TableObj("billSource", "bill");
		QueryCondition qc = new QueryCondition();
		if("1".equals(type)){//贴现余额
			//入库、记账成功
			String[] opers = {StatusUtils.handleStatusNoCheck("DiscAccountController", "doAccount", null),
					StatusUtils.handleStatusNoCheck("DiscStorageController", "doStorage", null)};
			bean.setStatusArray(opers);
			qc.add("info.gath_Mney_Type=:gathMneyType", RcConstants.GATH_TYPE_COMMON);
			return balanceSearchDao.getDiscBillBalanceInfo(bean, page, qc);
		}else if("2".equals(type)){//转贴现转入余额
			//记账通过、入库、返售逾期
			String[] opers = {StatusUtils.handleStatusNoCheck("RebuyAccountController", "accountSubmit", null),
					StatusUtils.handleStatusNoCheck("RebuyStorageController", "storageSubmit", null)};
			bean.setStatusArray(opers);
			qc.add("info.gath_Type=:gathType", RcConstants.GATH_TYPE_COMMON);
			return balanceSearchDao.getRebuyBillBalanceInfo(bean, page, qc);
		}else if("5".equals(type)){//质押票据
//			bean.setBillSource(RcConstants.BILL_SOURCE_COLLATERALIZATION);
			bean.setProdNo(CollateCodeConst.impawnBillProd_no);
			return balanceSearchDao.getSaveBillBalanceInfo(bean, page, qc);
		}else if("6".equals(type)){//托收在途
			bean.setStatus(null);
			bean.addProperty2TableObj("branchNo", "apply");
			qc.add("info.status=:status", SubcollCodeConst.SUB_STAUTS_ONWAY);
			return balanceSearchDao.getSubCollBillBalanceInfo(bean, page, qc);
		}else{
			throw new BizAppException("业务类型选择错误！");
		}
	}
	/**
	 * 余额明细查看
	 * @param type
	 * @param xxxmxId
	 * @return
	 * @throws Exception
	 */
	public Object getBalanceDetailByXxxmxId(String type,String xxxmxId) throws Exception{
		QueryCondition qc = new QueryCondition();
		if("1".equals(type)){//贴现余额
			qc.add("info.discmx_id=:discmxId", xxxmxId);
			return balanceSearchDao.getDiscBillBalanceInfo(new BillSearchBean(), new Page(), qc).get(0);
		}else if("2".equals(type)){//转入余额
			qc.add("info.rebuymx_id=:rebuymxId", xxxmxId);
			return balanceSearchDao.getRebuyBillBalanceInfo(new BillSearchBean(), new Page(), qc).get(0);
		}else if("5".equals(type)){//质押票据
			qc.add("info.savemx_id=:savemxId", xxxmxId);
			return balanceSearchDao.getSaveBillBalanceInfo(new BillSearchBean(), new Page(), qc).get(0);
		}else if("6".equals(type)){//托收在途
			qc.add("info.subcollmx_id=:subcollmxId", xxxmxId);
			return balanceSearchDao.getSubCollBillBalanceInfo(new BillSearchBean(), new Page(), qc).get(0);
		}else{
			throw new BizAppException("业务类型选择错误！");
		}
	}

}
