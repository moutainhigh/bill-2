package com.herongtech.rc.trans.trans058;

import com.herongtech.context.Context;
import com.herongtech.rc.trans.EcdsBaseService;


public class T058Service extends EcdsBaseService{


    @Override
    protected void action(Context arg0) throws Exception {
        
    }
    
    @Override
    public String getTransName() {
        return "058参与者承接关系通知";
    }

    @Override
    public String getTransVersion() {
        return "2.0.0.1";
    }
    
    @Override
    public String getServiceId() {
        return "Proc058";
    }

}
