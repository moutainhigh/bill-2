package com.herongtech.online.trans.trans101101;

import java.util.List;

import com.herongtech.xmlchannel.pkg.ProResult;
import com.thoughtworks.xstream.annotations.XStreamAlias;


public class Var101101Result {
   
    private ProResult proResult;
    
    private List<Var101101InfoBean> result;
    
    
    
    public ProResult getProResult() {
        return proResult;
    }


    
    public void setProResult(ProResult proResult) {
        this.proResult = proResult;
    }


    public List<Var101101InfoBean> getResult() {
        return result;
    }

    
    public void setResult(List<Var101101InfoBean> result) {
        this.result = result;
    }
}
