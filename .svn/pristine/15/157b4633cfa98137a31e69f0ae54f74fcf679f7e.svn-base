package com.herongtech.rc.trans.trans031;

import java.sql.SQLException;

import com.herongtech.console.core.constant.DraftConstants;
import com.herongtech.console.core.constant.RcConstants;
import com.herongtech.console.core.util.CommUtils;
import com.herongtech.console.core.util.DateTimeUtil;
import com.herongtech.console.service.ServiceFactory;
import com.herongtech.context.Context;
import com.herongtech.context.util.ContextUtil;
import com.herongtech.db.impl.session.DBFactory;
import com.herongtech.db.interfaces.IDB;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.rc.domain.bean.RgctBill;
import com.herongtech.rc.domain.bean.RgctBillHist;
import com.herongtech.rc.domain.bean.RgctBillInfo;
import com.herongtech.rc.draft.common.MsgUtil;
import com.herongtech.rc.draft.common.vo.DraftInfoVo;
import com.herongtech.rc.draft.common.vo.RequestInfo;
import com.herongtech.rc.service.RcServiceFactory;
import com.herongtech.rc.service.interfaces.IBillFlowInfoService;
import com.herongtech.rc.service.interfaces.ICustBillStorageService;
import com.herongtech.rc.service.rcservice.IRcBaseService;
import com.herongtech.rc.trans.EcdsBaseService;

public class T031Service extends EcdsBaseService {

    @Override
    protected void action(Context context) throws Exception {
        RequestInfo request = (RequestInfo) ContextUtil.getRequestData(context); // 请求数据集合
        DraftInfoVo vo = (DraftInfoVo) request;
        RgctBillInfo bill = vo.getBill();
        String SgnUpMk = "0";
        if(RcConstants.SIGNUP_MARK_CODE1.equals(vo.getSgnUpMk())){
        	SgnUpMk="1";
        }
        IRcBaseService baseService = RcServiceFactory.getRcBaseService();
        IBillFlowInfoService billFlowService=RcServiceFactory.getBillFlowInfoService();
        IDB session = DBFactory.getDB();
        try {
            session.beginTransaction();
            RgctBill rgctBill = baseService.getRgctBillByBillNo(bill
                    .getBillNo());
            RgctBillHist hist = rgctBill.getHist();
            hist.setToRemark(vo.getFromRemark());
            hist.setToAcctNo(vo.getFromAcctNo());
            hist.setToBankNo(vo.getFromBankNo());
            
            hist.setSignDt(DateTimeUtil.get_YYYY_MM_DD_Date(vo.getSignDt()));
            // 提示承兑、提示收票签收时，需要更新rgct_bill_info中收票人、承兑人的组织机构编码，追索时使用
            if ("002".equals(vo.getBusiType())) {// 提示承兑签收
                rgctBill.getInfo().setAcceptorOrgCode(vo.getFromOrgCode());
            }
            if ("003".equals(vo.getBusiType())) {// 提示收票签收
                rgctBill.getInfo().setPayeeOrgCode(vo.getFromOrgCode());
            }
            String methodName= MsgUtil.getMethodName(
                    vo.getBusiType(), hist.getIsRegress());
            if("12".equals(hist.getBuyType()) && "018".equals(vo.getBusiType())){
            	methodName=RcConstants.COMMON_INPOOL;
			}
            
            baseService.commonSignUp(rgctBill,methodName,DraftConstants.SIGNUP_MARK_CODE1.equals(vo.getSgnUpMk()) ? "1":"0");
            //电子票据流转
            billFlowService.executeBillFlowInfo031Trans(vo.getOrgnlMsgId(), SgnUpMk, rgctBill);
            addCustBillStorage(rgctBill,vo);
            session.endTransaction();
        } catch (Exception e) {
        	e.printStackTrace();
            try {
                session.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }

    }
    private void addCustBillStorage(RgctBill rgctBill, DraftInfoVo vo) {
		/**
		 * 余额类查询
		 * 处理规则－－申请方是我行，我行会收到033和031,登记中心在收到031时进行状态变更，所以余额类处理转发的031报文
		 *		     申请方是他行，则余额类处理031的033回复报文 
		 */
		try {
			ICustBillStorageService custBillStorageService = ServiceFactory.getCustBillStorageService();
			if(CommUtils.isSelfBank(rgctBill.getHist().getFromBankNo()) && DraftConstants.SIGNUP_MARK_CODE1.equals(vo.getSgnUpMk())) { //我行申请，我行或他行同意签收
				custBillStorageService.updateCustBillStorage(rgctBill.getHist().getRgctId(), vo.getBusiType(), rgctBill);
			}
		} catch (BizAppException e) {
			e.printStackTrace();
		}
	}
    @Override
    public String getTransName() {
        return "031通用回复";
    }

    @Override
    public String getTransVersion() {
        return "2.0.0.1";
    }

    @Override
    public String getServiceId() {
        return "Proc031";
    }

}
