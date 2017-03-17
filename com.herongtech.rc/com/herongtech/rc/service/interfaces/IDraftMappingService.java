package com.herongtech.rc.service.interfaces;

import java.util.List;

import com.herongtech.exception.impl.BizAppException;
import com.herongtech.rc.domain.bean.DraftMapping;


public interface IDraftMappingService {

    /**
     * 根据methodId获取报文映射信息。
     * @param methodId
     * @param param
     * @return
     * @throws BizAppException
     */
    public DraftMapping getDraftMapping(String methodId,String param) throws BizAppException;
    
    /**
     * 查询全部报文映射信息
     * @return
     * @throws BizAppException
     */
    public List<DraftMapping> getAllDraftMapping() throws BizAppException;
}
