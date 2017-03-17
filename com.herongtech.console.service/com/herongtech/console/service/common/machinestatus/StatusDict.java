package com.herongtech.console.service.common.machinestatus;


public class StatusDict {
	private String id;
    private String curStatus;      //当前状态
    private String curStatusDesc;  //状态描述
    private String previousStatus; //上一步状态
    private String canBack;     //能否回退到上一状态 0：否，1：是 2：回退两步
    private String param;       //例如是否启用审批流 0：否，1：是
    private String modelCode;   //交易模块编码   如：I表示转贴现
    private String billClass;   //票据品种 1-纸票  2-电票
    
    
    public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}


    
    public String getCurStatus() {
		return curStatus;
	}

	public void setCurStatus(String curStatus) {
		this.curStatus = curStatus;
	}

	public String getCurStatusDesc() {
		return curStatusDesc;
	}

	public void setCurStatusDesc(String curStatusDesc) {
		this.curStatusDesc = curStatusDesc;
	}

	public String getPreviousStatus() {
		return previousStatus;
	}

	public void setPreviousStatus(String previousStatus) {
		this.previousStatus = previousStatus;
	}

	public String getCanBack() {
        return canBack;
    }

    
    public void setCanBack(String canBack) {
        this.canBack = canBack;
    }

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}

	public String getModelCode() {
		return modelCode;
	}

	public void setModelCode(String modelCode) {
		this.modelCode = modelCode;
	}

	public String getBillClass() {
		return billClass;
	}

	public void setBillClass(String billClass) {
		this.billClass = billClass;
	}
    

//    
//    public String getLeftChildSts() {
//        return leftChildSts;
//    }
//
//    
//    public void setLeftChildSts(String leftChildSts) {
//        this.leftChildSts = leftChildSts;
//    }
//
//    
//    public String getRightSts() {
//        return rightSts;
//    }
//
//    
//    public void setRightSts(String rightSts) {
//        this.rightSts = rightSts;
//    }

    
    
   

}
