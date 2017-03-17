package com.herongtech.webservice.webBank.endorse;

import java.util.List;

import com.herongtech.rc.domain.bean.RgctBillInfo;


public interface IElecEndorseService {
    
    public List<RgctBillInfo> test(String custAcct,String bankNo);

}
