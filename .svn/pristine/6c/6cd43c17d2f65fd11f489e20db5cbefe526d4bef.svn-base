package com.herongtech.rc.service.draftmapping;

import java.sql.SQLException;
import java.util.List;

import com.herongtech.console.cache.DraftMappingCache;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.rc.domain.bean.DraftMapping;
import com.herongtech.rc.domain.dao.DraftMappingDao;
import com.herongtech.rc.service.interfaces.IDraftMappingService;
import com.herongtech.sysconstant.ISysErrorNo;


public class DraftMappingService implements IDraftMappingService {

    @Override
    public DraftMapping getDraftMapping(String methodId, String param)
            throws BizAppException {
        String key;
        if("".equals(param)||null==param){
			key=methodId;
		}else{
			key=methodId+param;
		}
		return DraftMappingCache.getDraftMappingCache().getDraftMappingmapvalue(key);
    }

    @Override
    public List<DraftMapping> getAllDraftMapping() throws BizAppException {
        DraftMappingDao dao=new DraftMappingDao();
        try {
            return dao.getAllDraftMapping();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new BizAppException(ISysErrorNo.ERR_DBERR, "");
        }
    }

}
