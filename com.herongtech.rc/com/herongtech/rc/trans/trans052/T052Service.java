package com.herongtech.rc.trans.trans052;

import java.util.ArrayList;
import java.util.List;

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


public class T052Service extends EcdsBaseService {


    @Override
    protected void action(Context context) throws Exception {
        RequestInfo req = (RequestInfo)ContextUtil.getRequestData(context);
        SysMgrInfoVo vo=(SysMgrInfoVo)req;
        
        String procCode =vo.getPrcCd();
        if(! DraftConstants.PE1I0000.equals(procCode)){
            return ;
        }
        
        // 系统登录状态
        String logStatus = vo.getActnTpMk();
        String logStatusCn="";
        if(logStatus.equals(DraftConstants.ECDS_LOGIN)){
            logStatusCn=DraftConstants.ECDS_LOGIN_CN;
        }else if(logStatus.equals(DraftConstants.ECDS_LOGOUT)){
            logStatusCn=DraftConstants.ECDS_LOGOUT_CN;
        }
        // 系统时序状态
        String sequence = vo.getSysSts();
        String sequenceCn = "";
        if (sequence.equals(DraftConstants.ECDS_SEQUENCE_BEFORE)) {
            sequenceCn = DraftConstants.ECDS_SEQUENCE_BEFORE_CN;
        } else if (sequence.equals(DraftConstants.ECDS_SEQUENCE_RUN)) {
            sequenceCn = DraftConstants.ECDS_SEQUENCE_RUN_CN;
        } else if (sequence.equals(DraftConstants.ECDS_SEQUENCE_AFTER)) {
            sequenceCn = DraftConstants.ECDS_SEQUENCE_AFTER_CN;
        } else if (sequence.equals(DraftConstants.ECDS_SEQUENCE_LAST)) {
            sequenceCn = DraftConstants.ECDS_SEQUENCE_LAST_CN;
        }
        // 系统当前日期
        String currentDate = vo.getSysDt();
        
        List<RgctEcdsStatus> ecdsStatusList = new ArrayList<RgctEcdsStatus>();
        ecdsStatusList.add(new RgctEcdsStatus(DraftConstants.ECDS_LOGON_STATUS, logStatus, DraftConstants.ECDS_LOGON_STATUS_CN, logStatusCn));
        ecdsStatusList.add(new RgctEcdsStatus(DraftConstants.ECDS_SEQUENCE, sequence, DraftConstants.ECDS_SEQUENCE_CN, sequenceCn));
        ecdsStatusList.add(new RgctEcdsStatus(DraftConstants.ECDS_CURRENT_DATE, currentDate, DraftConstants.ECDS_CURRENT_DATE_CN, currentDate));
        IDB session = DBFactory.getDB();
        IRgctEcdsStatusService ecdsStatus = RcServiceFactory.getRgctEcdsStatusService();
        try{
            session.beginTransaction();
            ecdsStatus.addOrUpdateRgctEcdsStatusList(ecdsStatusList);
            session.endTransaction();
        }catch (Exception e) {
            session.rollback();
        }
    }

    @Override
    public String getTransName() {
        return "052登录登出应答";
    }

    @Override
    public String getTransVersion() {
        return "2.0.0.1";
    }
    
    @Override
    public String getServiceId() {
        return "Proc052";
    }

}
