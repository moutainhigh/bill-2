package com.herongtech.rc.service.ecdsbankdata;

import java.sql.SQLException;

import com.herongtech.exception.impl.BizAppException;
import com.herongtech.rc.domain.bean.EcdsCommonData;
import com.herongtech.rc.domain.dao.EcdsCommonDataDao;
import com.herongtech.rc.service.interfaces.IEcdsCommonDataService;
import com.herongtech.sysconstant.ISysErrorNo;


public class EcdsCommonDataService implements IEcdsCommonDataService {

    @Override
    public EcdsCommonData getEcdsCommonData(String commonalityDataCode)throws BizAppException {
        EcdsCommonDataDao dao=new EcdsCommonDataDao();
        try {
            return dao.getEcdsCommonData(commonalityDataCode);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new BizAppException(ISysErrorNo.ERR_DBERR, "");
        }
    }
    
    public void AddOrUpdate(EcdsCommonData ecdscommonData) throws BizAppException{
    	 EcdsCommonDataDao dao=new EcdsCommonDataDao();
    	 try {
    		 EcdsCommonData ecdscommondataresult = dao.getEcdsCommonData(ecdscommonData.getCommonalityDataCode());
    		 if(ecdscommondataresult==null){
    			 dao.addEcdsCommonData(ecdscommonData);    			 
    		 }else{
    			 dao.modifyEcdsCommonData(ecdscommonData);
    		 }
    		 
		} catch (SQLException e) {
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "");
		}
    	 
    }
    

}
