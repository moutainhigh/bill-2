package com.herongtech.rc.trans.trans067;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

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


/**系统通用开关通知报文 ljt*/
public class T067Service extends EcdsBaseService {


    @Override
    protected void action(Context context) throws Exception {
    	 RequestInfo req = (RequestInfo)ContextUtil.getRequestData(context);
         SysMgrInfoVo vo=(SysMgrInfoVo)req;
         
         //String SystemCommonSwitchTp = vo.getSwitchTp();                 //开关种类
         String SystemCommonSwitchSwtchOnOffMrkr = vo.getSwitchOnOffMk();//开关标识
         String HvpsNxtSysDt = vo.getNxtSysDt();                         //下一系统工作日

         List<RgctEcdsStatus> ecdsStatusList = new ArrayList<RgctEcdsStatus>();

         if(StringUtils.isNotEmpty(SystemCommonSwitchSwtchOnOffMrkr)) {  //开关标识判断
 			ecdsStatusList.add(new RgctEcdsStatus(DraftConstants.ECDS_SWITCH_ST01, SystemCommonSwitchSwtchOnOffMrkr, DraftConstants.ECDS_SWITCH_ST01_CN, getSwitchMarkCnName(SystemCommonSwitchSwtchOnOffMrkr)));
 		 }
 		 if(StringUtils.isNotEmpty(HvpsNxtSysDt)) {                      //下一工作日判断
 			ecdsStatusList.add(new RgctEcdsStatus(DraftConstants.ECDS_HVPS_NEXT_DATE, HvpsNxtSysDt, DraftConstants.ECDS_HVPS_NEXT_DATE_CN, HvpsNxtSysDt));
 		 }
 		 
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

    @Override
    public String getTransName() {
        return "067";
    }

    @Override
    public String getTransVersion() {
        return "2.0.0.1";
    }
    
    @Override
    public String getServiceId() {
        return "Proc067";
    }
    /**开关判断*/
    private String getSwitchMarkCnName(String switchMark) {
		if (DraftConstants.ECDS_SWITCH_MARK_SO01.equals(switchMark)) {
			return DraftConstants.ECDS_SWITCH_MARK_ON;
		} else if (DraftConstants.ECDS_SWITCH_MARK_SO02.equals(switchMark)) {
			return DraftConstants.ECDS_SWITCH_MARK_OFF;
		} else {
			return "";
		}
	}

}
