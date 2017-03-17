package com.herongtech.rc.trans.trans032;

import java.sql.SQLException;

import com.herongtech.context.Context;
import com.herongtech.context.util.ContextUtil;
import com.herongtech.db.impl.session.DBFactory;
import com.herongtech.db.interfaces.IDB;
import com.herongtech.rc.domain.bean.RgctBill;
import com.herongtech.rc.domain.bean.RgctBillHist;
import com.herongtech.rc.domain.bean.RgctBillInfo;
import com.herongtech.rc.domain.bean.RgctDraftLog;
import com.herongtech.rc.draft.common.MsgUtil;
import com.herongtech.rc.draft.common.vo.DraftInfoVo;
import com.herongtech.rc.draft.common.vo.RequestInfo;
import com.herongtech.rc.service.RcServiceFactory;
import com.herongtech.rc.service.interfaces.IBillFlowInfoService;
import com.herongtech.rc.service.interfaces.IRgctDraftLogService;
import com.herongtech.rc.service.rcservice.IRcBaseService;
import com.herongtech.rc.trans.EcdsBaseService;

public class T032Service extends EcdsBaseService{

   

    @Override
    protected void action(Context context) throws Exception {
        RequestInfo request = (RequestInfo)ContextUtil.getRequestData(context); // 请求数据集合
        DraftInfoVo vo=(DraftInfoVo)request;
        RgctBillInfo bill=vo.getBill();
        IRcBaseService baseService=RcServiceFactory.getRcBaseService();
        IRgctDraftLogService draftService = RcServiceFactory.getRgctDraftLogService();
        IBillFlowInfoService billFlowService=RcServiceFactory.getBillFlowInfoService();
        
        IDB session = DBFactory.getDB();
        try {
            session.beginTransaction();
            RgctBill rgctBill=baseService.getRgctBillByBillNo(bill.getBillNo());
            RgctBillHist hist=rgctBill.getHist(); 
            RgctDraftLog rdl =  draftService.getDraftLogByRespSid(vo.getReqMsgId());
            
            RgctDraftLog orgDraft = draftService.getDraftLogByRespSid(vo.getOrgnlMsgId());
            String busiType = MsgUtil.getMethodName(orgDraft.getFromDraftNo(), rgctBill.getHist().getIsRegress());
            //为了统计收到的撤销报文
            rdl.setAcceptBankNo(orgDraft.getAcceptBankNo());
            draftService.modifyRgctDraftLog(rdl);
            baseService.commonCancel(rgctBill, busiType);
            //电子票据流转
            billFlowService.executeBillFlowInfo032Trans(vo.getOrgnlMsgId(), rgctBill);
            session.endTransaction();
        } catch (Exception e) {
            try {
                session.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
       
        
    }
    @Override
    public String getTransName() {
        return "032通用撤销";
    }

    @Override
    public String getTransVersion() {
        return "2.0.0.1";
    }
    
    @Override
    public String getServiceId() {
        return "Proc032";
    }
}
