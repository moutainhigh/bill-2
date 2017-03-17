package com.herongtech.rc.trans.trans005;

import java.sql.SQLException;

import com.herongtech.console.core.constant.IDict;
import com.herongtech.console.core.util.BillUtils;
import com.herongtech.console.core.util.DateTimeUtil;
import com.herongtech.console.service.ServiceFactory;
import com.herongtech.context.Context;
import com.herongtech.context.util.ContextUtil;
import com.herongtech.db.impl.session.DBFactory;
import com.herongtech.db.interfaces.IDB;
import com.herongtech.rc.domain.bean.CustBillStorage;
import com.herongtech.rc.domain.bean.RgctBill;
import com.herongtech.rc.domain.bean.RgctBillInfo;
import com.herongtech.rc.draft.common.vo.DraftInfoVo;
import com.herongtech.rc.draft.common.vo.RequestInfo;
import com.herongtech.rc.service.RcServiceFactory;
import com.herongtech.rc.service.interfaces.ICustBillStorageService;
import com.herongtech.rc.service.interfaces.IRcRegBillService;
import com.herongtech.rc.trans.EcdsBaseService;


public class T005Service extends EcdsBaseService {

    

    @Override
    protected void action(Context context) throws Exception {
        
        RequestInfo request = (RequestInfo)ContextUtil.getRequestData(context); // 请求数据集合
        DraftInfoVo vo=(DraftInfoVo)request;
        RgctBillInfo draftBill=vo.getBill();
        IRcRegBillService rcregService=RcServiceFactory.getRcRegBillService();
        IDB session = DBFactory.getDB();
        try {
            session.beginTransaction();
            RgctBill rgct=rcregService.getRgctBillByReqDraftId(vo.getOrgnlMsgId());
            RgctBillInfo bill=rgct.getInfo();
            bill.setBillNo(draftBill.getBillNo());
            bill.setEbsNo(generateEBSNO(bill));
            rcregService.registerYes(rgct);
            addCustBillStorage(rgct);
            session.endTransaction();
        } catch (Exception e) {
            try {
                session.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
        
        
    }
    
    private String generateEBSNO(RgctBillInfo newBillInfo) {
        String  ebsNo = BillUtils.generateEbsNo(newBillInfo.getBillNo(),DateTimeUtil.convertToyyyyMMdd(newBillInfo.getIssueDt()), newBillInfo
                    .getDraweeBankNo(), IDict.K_BILL_CLASS.K_ELEC_BILL.equals(newBillInfo
                    .getBillClass()), IDict.K_BILL_TYPE.K_BANK_BILL.equals(newBillInfo
                    .getBillType()));
        return ebsNo;
    }
    private void addCustBillStorage(RgctBill rgct) {
		/**
		 * 签收确认,交易发生统计
		 */
		try {
			ICustBillStorageService custBillStorageService = ServiceFactory.getCustBillStorageService();
			// 余额类：出票
			CustBillStorage custBillStorage = new CustBillStorage();
			custBillStorage.setBillNo(rgct.getInfo().getBillNo());
			custBillStorage.setHoldAcctNo(rgct.getHist().getHoldAcctNo());
			custBillStorage.setHoldBankNo(rgct.getHist().getHoldBankNo());
			custBillStorage.setHoldCustName(rgct.getHist().getHoldCustName());
			custBillStorage.setRgctId(rgct.getInfo().getId());
			custBillStorageService.saveCustBillStorage(custBillStorage);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
    

    @Override
    public String getTransName() {
        return "005出票信息登记确认";
    }

    @Override
    public String getTransVersion() {
        return "2.0.0.1";
    }
    
    @Override
    public String getServiceId() {
        return "Proc005";
    }

}
