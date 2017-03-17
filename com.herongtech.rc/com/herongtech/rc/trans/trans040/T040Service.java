package com.herongtech.rc.trans.trans040;

import java.sql.SQLException;
import java.util.Date;

import com.herongtech.console.core.util.DateTimeUtil;
import com.herongtech.context.Context;
import com.herongtech.context.util.ContextUtil;
import com.herongtech.db.impl.session.DBFactory;
import com.herongtech.db.interfaces.IDB;
import com.herongtech.rc.domain.bean.RgctBillInfo;
import com.herongtech.rc.draft.common.vo.DraftInfoVo;
import com.herongtech.rc.draft.common.vo.RequestInfo;
import com.herongtech.rc.service.RcServiceFactory;
import com.herongtech.rc.service.interfaces.IRgctDraftLogService;
import com.herongtech.rc.service.rcservice.IRcBaseService;
import com.herongtech.rc.trans.EcdsBaseService;


public class T040Service extends EcdsBaseService {

    @Override
    protected void action(Context context) throws Exception {
        RequestInfo request = (RequestInfo) ContextUtil.getRequestData(context); // 请求数据集合
        DraftInfoVo vo = (DraftInfoVo) request;
        RgctBillInfo bill=vo.getBill();
        IRcBaseService rcService = RcServiceFactory.getRcBaseService();
       
        IDB session = DBFactory.getDB();
        try {
            session.beginTransaction();
            rcService.synchronizeBillStatus(bill.getBillNo(), bill.getCurStatus(), DateTimeUtil.formatDate(vo.getDraftCreDtTm(), "yyyy-MM-dd"));
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
        return "040票据状态变更通知";
    }

    @Override
    public String getTransVersion() {
        return "2.0.0.1";
    }
    
    @Override
    public String getServiceId() {
        return "Proc040";
    }

}
