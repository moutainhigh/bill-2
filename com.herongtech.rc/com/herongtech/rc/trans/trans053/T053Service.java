package com.herongtech.rc.trans.trans053;

import com.herongtech.console.core.constant.DraftConstants;
import com.herongtech.context.Context;
import com.herongtech.context.util.ContextUtil;
import com.herongtech.db.impl.session.DBFactory;
import com.herongtech.db.interfaces.IDB;
import com.herongtech.rc.domain.bean.RgctEcdsStatus;
import com.herongtech.rc.draft.common.vo.RequestInfo;
import com.herongtech.rc.draft.common.vo.SysMgrInfoVo;
import com.herongtech.rc.service.RcServiceFactory;
import com.herongtech.rc.service.interfaces.IRgctEcdsStatusService;
import com.herongtech.rc.trans.EcdsBaseService;


public class T053Service extends EcdsBaseService {


    @Override
    protected void action(Context context) throws Exception {
        RequestInfo req = (RequestInfo)ContextUtil.getRequestData(context);
        SysMgrInfoVo vo=(SysMgrInfoVo)req;
        String msgCntCnts = vo.getMsgCnts();
        RgctEcdsStatus ecdsStatus = new RgctEcdsStatus(DraftConstants.ECDS_LOGON_STATUS, DraftConstants.ECDS_LOGOUT,DraftConstants.ECDS_LOGON_STATUS_CN,DraftConstants.ECDS_LOGOUT_CN);
        IDB session = DBFactory.getDB();
        IRgctEcdsStatusService ecdsStatusService = RcServiceFactory.getRgctEcdsStatusService();
        try{
            session.beginTransaction();
            ecdsStatusService.addOrUpdateRgctEcdsStatus(ecdsStatus);
            session.endTransaction();
        }catch (Exception e) {
            session.rollback();
        }
    }

    @Override
    public String getTransName() {
        return "053强制退出登录";
    }

    @Override
    public String getTransVersion() {
        return "2.0.0.1";
    }
    
    @Override
    public String getServiceId() {
        return "Proc053";
    }

}
