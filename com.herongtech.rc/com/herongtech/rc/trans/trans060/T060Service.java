package com.herongtech.rc.trans.trans060;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.herongtech.console.core.constant.DraftConstants;
import com.herongtech.context.Context;
import com.herongtech.context.util.ContextUtil;
import com.herongtech.db.impl.session.DBFactory;
import com.herongtech.db.interfaces.IDB;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.rc.domain.bean.RgctEcdsStatus;
import com.herongtech.rc.draft.common.vo.RequestInfo;
import com.herongtech.rc.draft.common.vo.SysMgrInfoVo;
import com.herongtech.rc.service.RcServiceFactory;
import com.herongtech.rc.service.interfaces.IRgctEcdsStatusService;
import com.herongtech.rc.trans.EcdsBaseService;


public class T060Service extends EcdsBaseService {


    @Override
    protected void action(Context context) throws Exception {
        RequestInfo req = (RequestInfo)ContextUtil.getRequestData(context);
        SysMgrInfoVo vo=(SysMgrInfoVo)req;
        
        
        String settlementOnline = vo.getSttlmOnlineMrk();
        String settlementOnlineCn = getSettlementOnlineCnName(settlementOnline);
        String settlementOnlineMark = vo.getSttlmRmrk();
        String hvpsNextDate = vo.getNxtSysDt();
        
        List<RgctEcdsStatus> ecdsStatusList = new ArrayList<RgctEcdsStatus>();

        ecdsStatusList.add(new RgctEcdsStatus(DraftConstants.ECDS_SETTLEMENT_ONLINE, settlementOnline, DraftConstants.ECDS_SETTLEMENT_ONLINE_CN, settlementOnlineCn));

        ecdsStatusList.add(new RgctEcdsStatus(DraftConstants.ECDS_SETTLEMENT_ONLINE_MARK, settlementOnlineMark, DraftConstants.ECDS_SETTLEMENT_ONLINE_MARK_CN, settlementOnlineMark));

        ecdsStatusList.add(new RgctEcdsStatus(DraftConstants.ECDS_HVPS_NEXT_DATE, hvpsNextDate, DraftConstants.ECDS_HVPS_NEXT_DATE_CN, hvpsNextDate));

        IDB session = DBFactory.getDB();
        IRgctEcdsStatusService ecdsStatusService = RcServiceFactory.getRgctEcdsStatusService();

        try{
            session.beginTransaction();
            ecdsStatusService.addOrUpdateRgctEcdsStatusList(ecdsStatusList);
            
            session.endTransaction();
        } catch (BizAppException e) {
            try {
                session.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        } catch (SQLException e) {
            try {
                session.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            e.printStackTrace();
        }
    }
    
    private String getSettlementOnlineCnName(String settlementOnline) {
        String settlementOnlineCn = "";
        if (settlementOnline.equals(DraftConstants.ECDS_SETTLEMENT_ON)) {
            settlementOnlineCn = DraftConstants.ECDS_SETTLEMENT_ON_CN;
        } else if (settlementOnline.equals(DraftConstants.ECDS_SETTLEMENT_OFF)) {
            settlementOnlineCn = DraftConstants.ECDS_SETTLEMENT_OFF_CN;
        }
        return settlementOnlineCn;
    }
    

    @Override
    public String getTransName() {
        return "060线上清算开关通知";
    }

    @Override
    public String getTransVersion() {
        return "2.0.0.1";
    }
    
    @Override
    public String getServiceId() {
        return "Proc060";
    }

}
