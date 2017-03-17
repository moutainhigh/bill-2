package com.herongtech.rc.trans.trans055;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.herongtech.context.Context;
import com.herongtech.context.util.ContextUtil;
import com.herongtech.db.impl.session.DBFactory;
import com.herongtech.db.interfaces.IDB;
import com.herongtech.rc.domain.bean.EcdsCommonData;
import com.herongtech.rc.domain.desc.EcdsCommonDataDesc;
import com.herongtech.rc.draft.common.vo.DraftCommonData;
import com.herongtech.rc.draft.common.vo.RequestInfo;
import com.herongtech.rc.draft.common.vo.SysMgrInfoVo;
import com.herongtech.rc.service.RcServiceFactory;
import com.herongtech.rc.service.ecdsbankdata.EcdsCommonDataService;
import com.herongtech.rc.service.interfaces.IEcdsCommonDataService;
import com.herongtech.rc.service.interfaces.IRgctEcdsStatusService;
import com.herongtech.rc.trans.EcdsBaseService;

/**公共数据变更*/
public class T055Service extends EcdsBaseService {

    

    @Override
    protected void action(Context context) throws Exception {
        RequestInfo req = (RequestInfo)ContextUtil.getRequestData(context);
        SysMgrInfoVo vo=(SysMgrInfoVo)req;
        List<EcdsCommonData> ecdscommondatalist = new ArrayList<EcdsCommonData>();
        List<DraftCommonData> cmonDt = vo.getCmonDt();
        String ALTRN_TP_ADD = "01";
        String ALTRN_TP_DEL = "02";
        String ALTRN_TP_MOD = "03";
        
        String FCTV_TP_NOW = "1";
        String FCTV_TP_DATE = "2";
        
        
        IDB session = DBFactory.getDB();
        IEcdsCommonDataService dataService=RcServiceFactory.getEcdsCommonDataService();
        for(int i=0;i<cmonDt.size();i++){
        	Object[] beans = new Object[2];
            DraftCommonData data=cmonDt.get(i);
            // 变更类型
            String altrnTp = data.getAltrnTp();
            
            // 生效类型
            String fctvTp = data.getFctvTp();
            EcdsCommonData ecdsCommondata = dataService.getEcdsCommonData(data.getCmonDtCd());
            if(ecdsCommondata == null){
                ecdsCommondata=new EcdsCommonData();
                ecdsCommondata.setCommonalityDataCode(data.getCmonDtCd());
            }
            
            // 公共数据名称
            ecdsCommondata.setCommonalityDataName(data.getCmonDtNm());
            // 公共数据类型
            ecdsCommondata.setCommonalityDataType(data.getCmonDtTp());
            // 生效类型
            ecdsCommondata.setUpdateStyle(data.getFctvTp());
            // 生效日期
            ecdsCommondata.setInureDate(data.getFctvDt());
            if (altrnTp.equals(ALTRN_TP_ADD)){
                // 公共数据更新值
                ecdsCommondata.setCommonalityDataUpdata(data.getCmonDtVal());
                // 公共数据值
                ecdsCommondata.setCommonalityDataValue(data.getCmonDtVal());
            }else if(altrnTp.equals(ALTRN_TP_MOD)){
                if(fctvTp.equals(FCTV_TP_NOW)){
                    // 公共数据更新值
                    ecdsCommondata.setCommonalityDataUpdata(data.getCmonDtVal());
//                    // 公共数据值
                    ecdsCommondata.setCommonalityDataValue(data.getCmonDtVal());
                }else if(fctvTp.equals(FCTV_TP_DATE)){
                    // 公共数据更新值
                    if(ecdsCommondata.getCommonalityDataUpdata()!=null&&!ecdsCommondata.getCommonalityDataUpdata().equals("")){
                        ecdsCommondata.setCommonalityDataValue(ecdsCommondata.getCommonalityDataUpdata());
                    }
                    // 公共数据值
                    ecdsCommondata.setCommonalityDataUpdata(data.getCmonDtVal());
                }
            }else if(altrnTp.equals(ALTRN_TP_DEL)){
                if(fctvTp.equals(FCTV_TP_NOW)){
                    // 公共数据更新值
                    ecdsCommondata.setCommonalityDataUpdata("");
                    // 公共数据值
                    ecdsCommondata.setCommonalityDataValue("");
                }else if(fctvTp.equals(FCTV_TP_DATE)){
                    // 公共数据更新值
                    ecdsCommondata.setCommonalityDataValue(ecdsCommondata.getCommonalityDataUpdata());
                    // 公共数据值
                    ecdsCommondata.setCommonalityDataUpdata("");
                }
            }
            ecdscommondatalist.add(ecdsCommondata);
        }
        
        Iterator iter = ecdscommondatalist.iterator();
        EcdsCommonDataService ecdscommonservice = new EcdsCommonDataService();
		while (iter.hasNext()) {
			EcdsCommonData ecdscommonData =(EcdsCommonData) iter.next();
			session.beginTransaction();
			ecdscommonservice.AddOrUpdate(ecdscommonData);
	             
	        session.endTransaction();
			
		}
      
        
        
        
        
        
    }

    @Override
    public String getTransName() {
        return "055公共数据变更";
    }

    @Override
    public String getTransVersion() {
        return "2.0.0.1";
    }
    
    @Override
    public String getServiceId() {
        return "Proc055";
    }

}
