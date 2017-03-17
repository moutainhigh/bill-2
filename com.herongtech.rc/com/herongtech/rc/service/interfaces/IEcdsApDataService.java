package com.herongtech.rc.service.interfaces;

import com.herongtech.exception.impl.BizAppException;
import com.herongtech.rc.domain.bean.EcdsApData;


/**
 *接入点信息数据service
 *
 */
public interface IEcdsApDataService {

    /**
     * 通过接入点号获取接入点信息
     * @param meetCode
     * @return
     */
    public EcdsApData getEcdsApData(String meetCode)throws BizAppException ;
}
