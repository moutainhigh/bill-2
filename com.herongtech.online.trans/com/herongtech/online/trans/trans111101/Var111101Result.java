package com.herongtech.online.trans.trans111101;

import java.util.List;

import com.herongtech.xmlchannel.pkg.ProResult;


public class Var111101Result {
   
    private ProResult proResult;
    private String totNum;//总记录数
    private String errNum;//错误记录数
    private List<Var111101InfoBean> result;
    
    
    
    public ProResult getProResult() {
        return proResult;
    }


    
    public void setProResult(ProResult proResult) {
        this.proResult = proResult;
    }


    public List<Var111101InfoBean> getResult() {
        return result;
    }

    
    public void setResult(List<Var111101InfoBean> result) {
        this.result = result;
    }



	public String getTotNum() {
		return totNum;
	}



	public void setTotNum(String totNum) {
		this.totNum = totNum;
	}



	public String getErrNum() {
		return errNum;
	}



	public void setErrNum(String errNum) {
		this.errNum = errNum;
	}
    
}
