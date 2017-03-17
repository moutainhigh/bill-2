package com.herongtech.rc.service.custBillStorage;

import java.sql.SQLException;

import com.herongtech.commons.tools.DateUtil;
import com.herongtech.console.core.util.DateTimeUtil;
import com.herongtech.console.service.ServiceFactory;
import com.herongtech.console.service.interfaces.ISequenceService;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.rc.domain.bean.CustBillStorage;
import com.herongtech.rc.domain.bean.RcBaseSearchBean;
import com.herongtech.rc.domain.bean.RgctBill;
import com.herongtech.rc.domain.dao.CustBillStorageDao;
import com.herongtech.rc.service.interfaces.ICustBillStorageService;

public class CustBillStorageService implements ICustBillStorageService {
	
	private CustBillStorageDao custBillStorageDao = new CustBillStorageDao();
	
	/**
	 * 出票
	 */
	@Override
	public void saveCustBillStorage(CustBillStorage custBillStorage) throws BizAppException {
		try {
			ISequenceService sequenceService = ServiceFactory.getSequenceService();
			CustBillStorage billStorageOld;
			billStorageOld = custBillStorageDao.serchCustBillStorage(custBillStorage.getRgctId());
			if (billStorageOld != null) {
				throw new BizAppException("该票已存在");
			}
			custBillStorage.setIsRegister("1");
			custBillStorage.setId(sequenceService.getPrimaryKeyValue());
			custBillStorage.setUpdateDt(DateTimeUtil.getWorkdayString());
			custBillStorage.setUpdateTime(DateUtil.getDateTime("yyyy-MM-dd hh:mm:ss"));
			custBillStorageDao.addCustBillStorage(custBillStorage);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void saveCustBillStorage034(CustBillStorage billStorage)
			throws BizAppException {
		try {
		ISequenceService sequenceService = ServiceFactory.getSequenceService();
			CustBillStorage billStorageOld;
			billStorageOld = custBillStorageDao.serchCustBillStorage(billStorage.getRgctId());
			if (billStorageOld == null) {
			billStorage.setUpdateDt(DateTimeUtil.getWorkdayString());
				billStorage.setUpdateTime(DateUtil.getDateTime("yyyy-MM-dd hh:mm:ss"));
				billStorage.setId(sequenceService.getPrimaryKeyValue());
				custBillStorageDao.addCustBillStorage(billStorage);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateCustBillStorage(String rgctId, String busiType,
			RgctBill rgctBill) throws BizAppException {
		try {
			if(busiType.equals(null)){
				return ;
			}
			CustBillStorage	billStorage = custBillStorageDao.serchCustBillStorage(rgctId);
			if (billStorage == null) {
				throw new BizAppException("该票不存在");
			}
			billStorage.setHoldAcctNo(rgctBill.getHist().getHoldAcctNo());
			billStorage.setHoldBankNo(rgctBill.getHist().getHoldBankNo());
			billStorage.setHoldCustName(rgctBill.getHist().getHoldCustName());
			if ("002".equals(busiType)) { // 承兑
				billStorage.setIsAccept("1");
			} else if ("018".equals(busiType)) { // 质押
				billStorage.setIsBalance("0");
				billStorage.setIsImpawn("1");
				billStorage.setHoldAcctNo(rgctBill.getHist().getFromAcctNo()); // 网银端通过该字段查询质押余额
			} else if ("019".equals(busiType)) { // 解质押
				billStorage.setIsImpawn("0");
				billStorage.setIsBalance("1");
			}  else if ("011".equals(busiType)||"013".equals(busiType)
					||"015".equals(busiType)||"025".equals(busiType)||"012".equals(busiType)
					||"014".equals(busiType)||"016".equals(busiType)
					||"010".equals(busiType)
					||"003".equals(busiType)) { // 贴现、转入、再贴现、背书或收票
				billStorage.setIsBalance("1");
			} else if ("004".equals(busiType)) { // 撤票
					billStorage.setIsBalance("0");
					billStorage.setIsImpawn("0");
					billStorage.setIsRegister("0");
					billStorage.setIsAccept("0");
			}else {
				throw new BizAppException("类型不存在");
			}
			billStorage.setUpdateDt(DateTimeUtil.getWorkdayString());
			billStorage.setUpdateTime(DateUtil.getDateTime("yyyy-MM-dd hh:mm:ss"));
			custBillStorageDao.modifyCustBillStorage(billStorage);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
		
	}

	@Override
	public String  serchCustBillStorageBalance(RcBaseSearchBean searchBean)
			throws BizAppException {
		String sql ="";
		try {			
			if ("1".equals(searchBean.getBalaceType())) {// 出票余额
				sql = custBillStorageDao.serchBalanceRegister(searchBean);
			} else if ("2".equals(searchBean.getBalaceType())) {// 承兑余额（网银端）
				sql = custBillStorageDao.serchBalanceAcceptNet(searchBean);
			} else if ("3".equals(searchBean.getBalaceType())) {// 库存余额（网银端）
				sql = custBillStorageDao.serchBalanceInventoryNet(searchBean);
			} else if ("4".equals(searchBean.getBalaceType())) {// 质押余额（网银端）
				sql = custBillStorageDao.serchBalanceImpawnNet(searchBean);
			} else if ("5".equals(searchBean.getBalaceType())) {// 托收在途余额（网银端）
				sql = custBillStorageDao.serchBalanceCollectionNet(searchBean);
			} else if ("6".equals(searchBean.getBalaceType())) {// 到期未收回余额
				sql = custBillStorageDao.serchBalanceDueBackNot(searchBean);
			} /*else if ("7".equals(searchBean.getBalaceType())) {与质押余额相对重复暂时不用// 质权票据余额
				sql = custBillStorageDao.serchBalancePledge(searchBean);
			}*/ else {
				throw new BizAppException("余额类型不存在");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BizAppException("BillStorageServiceImpl_serchBillStorageBalance", e);
		}
		return sql;
	}

	
}
