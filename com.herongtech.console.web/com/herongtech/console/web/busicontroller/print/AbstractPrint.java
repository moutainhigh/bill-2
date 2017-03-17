package com.herongtech.console.web.busicontroller.print;

import java.util.List;

public abstract class AbstractPrint {
	private String tpl;

	//是否汇总打印 （凭证汇总打印需要） 1是 0 否
	protected String isSum="0";
	protected String ifAgree="0";//是否拒绝 0否 1 是
	
	protected String busiType;//业务类型
	
	protected String repeatIds;//需要加客户号的清单ID（用于托收）
	
	protected String isAdd;//是否 补打 （补打清单/凭证）
	
	public String moduleId;//模板 编号(凭证补打时,贴现凭证 分 第三方凭证 ,需要程序里面区分)
	
	
	public String getModuleId() {
		return moduleId;
	}

	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}

	public String getIsAdd() {
		return isAdd;
	}

	public void setIsAdd(String isAdd) {
		this.isAdd = isAdd;
	}

	public String getRepeatIds() {
		return repeatIds;
	}

	public void setRepeatIds(String repeatIds) {
		this.repeatIds = repeatIds;
	}

	public String getBusiType() {
		return busiType;
	}

	public void setBusiType(String busiType) {
		this.busiType = busiType;
	}

	public String getTpl() {
		return tpl;
	}
	
	public void setTpl(String tpl) {
		this.tpl = tpl;
	}	
	public String getIfAgree() {
		return ifAgree;
	}

	public void setIfAgree(String ifAgree) {
		this.ifAgree = ifAgree;
	}

	public String getIsSum() {
		return isSum;
	}

	public void setIsSum(String isSum) {
		this.isSum = isSum;
	}

	/**
	 * 打印
	 * @param ids
	 * @param batch_id
	 * @param handleType 
	 * @return
	 * @throws Exception
	 */
	public abstract List getPrintList(String ids,String batch_id,String handleType)  throws Exception ;
}
