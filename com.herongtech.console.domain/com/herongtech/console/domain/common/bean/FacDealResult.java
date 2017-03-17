package com.herongtech.console.domain.common.bean;

import java.util.List;

import com.herongtech.console.web.busicontroller.common.CommonConst;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.log.CommonLog;

public class FacDealResult {
	private Boolean success;
	private int successNum;
	private int failNum;
	private int outpoolSize;//出池笔数
	private String successMsg;
	private String failMsg;
	private List successInfoList;
	private List failInfoInfoList;
	
	public FacDealResult(){}
	public FacDealResult(Boolean isSuccess,String msg,List list){
		this.success = isSuccess;
		if(isSuccess){
			this.successMsg = msg;
			this.successInfoList = list;
		}else{
			this.failMsg = msg;
			this.failInfoInfoList = list;
		}
	}
	
	public FacDealResult(String successMsg,List successInfoList,String failMsg,List failInfoInfoList){
			this.successMsg = successMsg;
			this.successInfoList = successInfoList;
			
			this.failMsg = failMsg;
			this.failInfoInfoList = failInfoInfoList;
	}
	
	public FacDealResult(List successInfoList,List failInfoInfoList){
		this.successInfoList = successInfoList;
		this.failInfoInfoList = failInfoInfoList;
	}
	
	public void appandSuccessMsg(String msg,String splitFlag){
		if(this.successMsg  == null ){
			this.successMsg = msg;
		}else{
			this.successMsg = this.successMsg + splitFlag + msg;
		}
	}
	public void appandFailMsg(String msg,String splitFlag){
		if(this.failMsg  == null ){
			this.failMsg = msg;
		}else{
			this.failMsg = this.failMsg + splitFlag + msg;
		}
	}
	public void successNumIncrease(int i){
		this.successNum = this.successNum + i;
	}
	public void successNumReduce(int i){
		this.successNum = this.successNum - i;
		if(this.successNum <0){
			this.successNum =0;
		}
	}
	public void failNumIncrease(int i){
		this.failNum = this.failNum + i;
	}
	public void failNumReduce(int i){
		this.failNum = this.failNum - i;
		if(this.failNum <0){
			this.failNum =0;
		}
	}
	
	/**合并两个RebuyFacDealResult
	 * @param anotherRC
	 * @return
	 */
	public FacDealResult add(FacDealResult anotherRC){
		if(anotherRC==null){
			return this;
		}
		
		this.successNum = this.successNum + anotherRC.successNum;
		this.failNum = this.failNum + anotherRC.failNum;
		this.outpoolSize = this.outpoolSize + anotherRC.outpoolSize;
		
		if(anotherRC.successMsg!=null){
			this.appandSuccessMsg(anotherRC.successMsg, "</br>");
		}
		
		if(anotherRC.failMsg != null){
			this.appandFailMsg(anotherRC.failMsg,  "</br>");
		}
		
		if(this.successInfoList == null ){
			this.successInfoList = anotherRC.successInfoList;
		}else if(anotherRC.successInfoList != null && !anotherRC.successInfoList.isEmpty()){
			this.successInfoList.addAll(anotherRC.successInfoList);
		}
		
		if(this.failInfoInfoList == null){
			this.failInfoInfoList = anotherRC.failInfoInfoList;
		}else if(anotherRC.failInfoInfoList != null && !anotherRC.failInfoInfoList.isEmpty()){
			this.failInfoInfoList.addAll(anotherRC.failInfoInfoList);
		}
		
		return this;
	}
	/**
	 * 检查额度操作（占用或者扣减）是否全部成功
	 */
	public void checkFacResult(String facOperType) throws BizAppException{
		String facOperName = "";
		if(CommonConst.FAC_OPER_OCCUPY.equals(facOperType)){
			facOperName="扣减";
		}else if(CommonConst.FAC_OPER_RELEASE.equals(facOperType)){
			facOperName="释放";
		}
		if(this.getFailNum()>0){
			CommonLog.getCommonLogCache().infoMessage("票据共"+(this.getSuccessNum()+this.getFailNum())+"笔，额度"+facOperName+"成功"+this.getSuccessNum()+"笔，失败"+this.getFailNum()+"笔，因存在额度"+facOperName+"失败的票据，故操作失败！");
			throw new BizAppException("票据共"+(this.getSuccessNum()+this.getFailNum())+"笔，额度"+facOperName+"成功"+this.getSuccessNum()+"笔，失败"+this.getFailNum()+"笔，因存在额度"+facOperName+"失败的票据，故操作失败！");
		}else{
			CommonLog.getCommonLogCache().infoMessage("票据共"+(this.getSuccessNum()+this.getFailNum())+"笔，额度"+facOperName+"成功"+this.getSuccessNum()+"笔");
		}
	}
	
	public Boolean getSuccess() {
		return success;
	}
	public void setSuccess(Boolean success) {
		this.success = success;
	}
	public String getSuccessMsg() {
		return successMsg;
	}
	public void setSuccessMsg(String successMsg) {
		this.successMsg = successMsg;
	}
	public String getFailMsg() {
		return failMsg == null ? "" : failMsg;
	}
	public void setFailMsg(String failMsg) {
		this.failMsg = failMsg;
	}
	public List getSuccessInfoList() {
		return successInfoList;
	}
	public void setSuccessInfoList(List successInfoList) {
		this.successInfoList = successInfoList;
	}
	public List getFailInfoInfoList() {
		return failInfoInfoList;
	}
	public void setFailInfoInfoList(List failInfoInfoList) {
		this.failInfoInfoList = failInfoInfoList;
	}
	public int getSuccessNum() {
		return successNum;
	}
	public void setSuccessNum(int successNum) {
		this.successNum = successNum;
	}
	public int getFailNum() {
		return failNum;
	}
	public void setFailNum(int failNum) {
		this.failNum = failNum;
	}
	public int getOutpoolSize() {
		return outpoolSize;
	}
	public void setOutpoolSize(int outpoolSize) {
		this.outpoolSize = outpoolSize;
	}
}
