package com.herongtech.rc.trans.trans035;

import java.sql.SQLException;

import com.herongtech.context.Context;
import com.herongtech.context.util.ContextUtil;
import com.herongtech.db.impl.session.DBFactory;
import com.herongtech.db.interfaces.IDB;
import com.herongtech.rc.domain.bean.RgctBill;
import com.herongtech.rc.domain.bean.RgctDraftLog;
import com.herongtech.rc.draft.common.vo.DraftInfoVo;
import com.herongtech.rc.draft.common.vo.RequestInfo;
import com.herongtech.rc.service.RcServiceFactory;
import com.herongtech.rc.service.interfaces.IBillFlowInfoService;
import com.herongtech.rc.service.interfaces.IRgctDraftLogService;
import com.herongtech.rc.service.rcservice.IRcBaseService;
import com.herongtech.rc.trans.EcdsBaseService;


public class T035Service extends EcdsBaseService {


    @Override
    protected void action(Context context) throws Exception {
        RequestInfo request = (RequestInfo) ContextUtil.getRequestData(context); // 请求数据集合
        DraftInfoVo vo = (DraftInfoVo) request;
        IRcBaseService baseService = RcServiceFactory.getRcBaseService();
        IRgctDraftLogService draftService = RcServiceFactory.getRgctDraftLogService();
        IBillFlowInfoService billFlowService=RcServiceFactory.getBillFlowInfoService();
       
        IDB session = DBFactory.getDB();
        try {
            session.beginTransaction();
            RgctDraftLog draftLog = draftService.getDraftLogByRespSid(vo.getReqMsgId());
            String draftNoReq = draftLog.getDraftNoReq();
            RgctBill  rcgtbill = baseService.getRgctBillById(draftLog.getRgctId());
            baseService.commonExceptionNotify(rcgtbill, draftNoReq);
            //电子票据流转
            billFlowService.executeBillFlowInfo035Trans(vo.getOrgnlMsgId(), rcgtbill, vo.getPrcMsg());
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
        return "035清分失败";
    }

    @Override
    public String getTransVersion() {
        return "2.0.0.1";
    }
    
    @Override
    public String getServiceId() {
        return "Proc035";
    }

}
