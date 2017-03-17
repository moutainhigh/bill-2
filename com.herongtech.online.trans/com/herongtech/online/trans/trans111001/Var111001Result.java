package com.herongtech.online.trans.trans111001;

import java.util.List;

import com.herongtech.console.domain.bean.SignProd;
import com.herongtech.xmlchannel.pkg.ProResult;


public class Var111001Result extends ProResult{
   
    private ProResult proResult;//输出基本信息
    private List<SignProd> result;//查询签约信息结果
    
    public ProResult getProResult() {
        return proResult;
    }
    
    public void setProResult(ProResult proResult) {
        this.proResult = proResult;
    }

	public List<SignProd> getResult() {
		return result;
	}

	public void setResult(List<SignProd> result) {
		this.result = result;
	}

	
}
