package com.herongtech.rc.trans.trans059;

import com.herongtech.context.Context;
import com.herongtech.rc.trans.EcdsBaseService;


public class T059Service extends EcdsBaseService {


    @Override
    protected void action(Context arg0) throws Exception {

    }

    @Override
    public String getTransName() {
        return "059行名行号数据变更";
    }

    @Override
    public String getTransVersion() {
        return "2.0.0.1";
    }
    
    @Override
    public String getServiceId() {
        return "Proc059";
    }

}
