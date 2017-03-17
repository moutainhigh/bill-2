package com.herongtech.rc.trans.trans044;

import com.herongtech.context.Context;
import com.herongtech.rc.trans.EcdsBaseService;


public class T044Service extends EcdsBaseService {


    @Override
    protected void action(Context arg0) throws Exception {

    }

    @Override
    public String getTransName() {
        return "044票据查验详细";
    }

    @Override
    public String getTransVersion() {
        return "2.0.0.1";
    }
    
    @Override
    public String getServiceId() {
        return "Proc044";
    }

}
