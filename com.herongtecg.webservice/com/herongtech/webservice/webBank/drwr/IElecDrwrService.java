package com.herongtech.webservice.webBank.drwr;

import javax.jws.WebService;

import com.herongtech.webservice.bean.DemoBean;
import com.herongtech.xmlchannel.pkg.ProResult;

public interface IElecDrwrService {
    
    /**
     * 出票登记
     * @param bean
     * @return
     */
    public ProResult draftRegisterEcdsRequest(DemoBean bean);
    
    public String sayHello(String name);

}
