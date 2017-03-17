package com.herongtech.console.domain.common.audit.bean;

import com.herongtech.console.core.common.search.BaseSearchBean;


public class AuditProcessSearchBean extends BaseSearchBean {
    
    private String atId;
    /**审批岗位集合*/
    private Object[] asIdArr;
    
    /**审批状态*/
    private String apStatus;
    
    /**上级提交日期*/
    private String apCommitDt;
    
    /**审批人机构ID*/
    private String apExecBrchNo;
    
    private String apExecPersonId;
    /**审批结果*/
    private String apExecResult;
    /**审批日期起始值*/
    private String minApDoneDt;
    /**审批日期截止值*/
    private String maxApDoneDt;
    
    
    
    
    public String getApExecResult() {
		return apExecResult;
	}


	public void setApExecResult(String apExecResult) {
		this.apExecResult = apExecResult;
	}


	public String getMinApDoneDt() {
		return minApDoneDt;
	}


	public void setMinApDoneDt(String minApDoneDt) {
		this.minApDoneDt = minApDoneDt;
	}


	public String getMaxApDoneDt() {
		return maxApDoneDt;
	}


	public void setMaxApDoneDt(String maxApDoneDt) {
		this.maxApDoneDt = maxApDoneDt;
	}


	public Object[] getAsIdArr() {
        return asIdArr;
    }

    
    public void setAsIdArr(Object[] asIdArr) {
        this.asIdArr = asIdArr;
    }

    
    public String getApStatus() {
        return apStatus;
    }

    
    public void setApStatus(String apStatus) {
        this.apStatus = apStatus;
    }

    
    public String getApCommitDt() {
        return apCommitDt;
    }

    
    public void setApCommitDt(String apCommitDt) {
        this.apCommitDt = apCommitDt;
    }

    
    public String getApExecBrchNo() {
        return apExecBrchNo;
    }

    
    public void setApExecBrchNo(String apExecBrchNo) {
        this.apExecBrchNo = apExecBrchNo;
    }


    
    public String getAtId() {
        return atId;
    }


    
    public void setAtId(String atId) {
        this.atId = atId;
    }


    
    public String getApExecPersonId() {
        return apExecPersonId;
    }


    
    public void setApExecPersonId(String apExecPersonId) {
        this.apExecPersonId = apExecPersonId;
    }
    
    
    
}
