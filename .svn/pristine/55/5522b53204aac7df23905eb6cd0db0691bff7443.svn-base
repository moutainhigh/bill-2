/********************************************
* 文件名称: SaveBillInfo.java
* 系统名称: 合融基础技术平台V2.0
* 模块名称:
* 软件版权: 北京合融科技有限公司
* 功能说明: 
* 系统版本: 2.0.0.1
* 开发人员: 技术平台组
* 开发时间:  
* 审核人员:
* 相关文档:
* 修改记录: 修改日期    修改人员    修改说明
*********************************************/
package com.herongtech.console.domain.save.bean;

import java.lang.*;
import java.sql.SQLException;
import java.util.*;
import java.math.*;

import com.herongtech.console.domain.common.bean.Product;
import com.herongtech.console.service.ServiceFactory;
import com.herongtech.console.service.common.product.IProductService;

public class SaveBillInfo{
	//存票明细流水信息
	private String savemxId = " ";
	public String getSavemxId(){
		return savemxId;
	}
	public void setSavemxId(String savemxId){
		this.savemxId = savemxId;
	}

	//票据种类
	private String billType = " ";
	public String getBillType(){
		return billType;
	}
	public void setBillType(String billType){
		this.billType = billType;
	}

	//票号
	private String billNo = " ";
	public String getBillNo(){
		return billNo;
	}
	public void setBillNo(String billNo){
		this.billNo = billNo;
	}

	//出票日
	private String issueDt = " ";
	public String getIssueDt(){
		return issueDt;
	}
	public void setIssueDt(String issueDt){
		this.issueDt = issueDt;
	}

	//到期日
	private String dueDt = " ";
	public String getDueDt(){
		return dueDt;
	}
	public void setDueDt(String dueDt){
		this.dueDt = dueDt;
	}

	//出票人
	private String remitter = " ";
	public String getRemitter(){
		return remitter;
	}
	public void setRemitter(String remitter){
		this.remitter = remitter;
	}

	//出票人账号
	private String remitterAcct = " ";
	public String getRemitterAcct(){
		return remitterAcct;
	}
	public void setRemitterAcct(String remitterAcct){
		this.remitterAcct = remitterAcct;
	}

	//出票人行名
	private String remitterBankName = " ";
	public String getRemitterBankName(){
		return remitterBankName;
	}
	public void setRemitterBankName(String remitterBankName){
		this.remitterBankName = remitterBankName;
	}

	//出票人行号
	private String remitterBankNo = " ";
	public String getRemitterBankNo(){
		return remitterBankNo;
	}
	public void setRemitterBankNo(String remitterBankNo){
		this.remitterBankNo = remitterBankNo;
	}

	//票面金额
	private double billMoney = 0.0;
	public double getBillMoney(){
		return billMoney;
	}
	public void setBillMoney(double billMoney){
		this.billMoney = billMoney;
	}

	//承兑人
	private String acceptor = " ";
	public String getAcceptor(){
		return acceptor;
	}
	public void setAcceptor(String acceptor){
		this.acceptor = acceptor;
	}

	//操作机构
	private String branchNo = " ";
	public String getBranchNo(){
		return branchNo;
	}
	public void setBranchNo(String branchNo){
		this.branchNo = branchNo;
	}

	//收款人名称
	private String payee = " ";
	public String getPayee(){
		return payee;
	}
	public void setPayee(String payee){
		this.payee = payee;
	}

	//收款人开户行名称
	private String payeeBankName = " ";
	public String getPayeeBankName(){
		return payeeBankName;
	}
	public void setPayeeBankName(String payeeBankName){
		this.payeeBankName = payeeBankName;
	}

	//收款人账号
	private String payeeAcct = " ";
	public String getPayeeAcct(){
		return payeeAcct;
	}
	public void setPayeeAcct(String payeeAcct){
		this.payeeAcct = payeeAcct;
	}

	//交易前手
	private String billBeforeOwner = " ";
	public String getBillBeforeOwner(){
		return billBeforeOwner;
	}
	public void setBillBeforeOwner(String billBeforeOwner){
		this.billBeforeOwner = billBeforeOwner;
	}

	//票据分类
	private String billClass = " ";
	public String getBillClass(){
		return billClass;
	}
	public void setBillClass(String billClass){
		this.billClass = billClass;
	}

	//票据来源
	private String billSource = " ";
	public String getBillSource(){
		return billSource;
	}
	public void setBillSource(String billSource){
		this.billSource = billSource;
	}

	//操作柜员
	private String operNo = " ";
	public String getOperNo(){
		return operNo;
	}
	public void setOperNo(String operNo){
		this.operNo = operNo;
	}

	//操作状态
	private String operStatus = " ";
	public String getOperStatus(){
		return operStatus;
	}
	public void setOperStatus(String operStatus){
		this.operStatus = operStatus;
	}

	//存票流水号
	private String saveId = " ";
	public String getSaveId(){
		return saveId;
	}
	public void setSaveId(String saveId){
		this.saveId = saveId;
	}

	//登记中心id
	private String rgctId = " ";
	public String getRgctId(){
		return rgctId;
	}
	public void setRgctId(String rgctId){
		this.rgctId = rgctId;
	}

	//产品编号
	private String prodNo = " ";
	public String getProdNo(){
		return prodNo;
	}
	public void setProdNo(String prodNo){
		this.prodNo = prodNo;
	}

	//额度类型
	private String eduType = " ";
	public String getEduType(){
		return eduType;
	}
	public void setEduType(String eduType){
		this.eduType = eduType;
	}

	//查复状态
	private String queryStatus = " ";
	public String getQueryStatus(){
		return queryStatus;
	}
	public void setQueryStatus(String queryStatus){
		this.queryStatus = queryStatus;
	}

	//创建日期
	private String createDt = " ";
	public String getCreateDt(){
		return createDt;
	}
	public void setCreateDt(String createDt){
		this.createDt = createDt;
	}

	//创建时间
	private String createTime = " ";
	public String getCreateTime(){
		return createTime;
	}
	public void setCreateTime(String createTime){
		this.createTime = createTime;
	}

	//是否贴查同步
	private String isTc = " ";
	public String getIsTc(){
		return isTc;
	}
	public void setIsTc(String isTc){
		this.isTc = isTc;
	}

	//签收意见
	private String signRemark = " ";
	public String getSignRemark(){
		return signRemark;
	}
	public void setSignRemark(String signRemark){
		this.signRemark = signRemark;
	}

	//是否签收
	private String ifSignFlag = " ";
	public String getIfSignFlag(){
		return ifSignFlag;
	}
	public void setIfSignFlag(String ifSignFlag){
		this.ifSignFlag = ifSignFlag;
	}

	//客户经理编号
	private String custManager = " ";
	public String getCustManager(){
		return custManager;
	}
	public void setCustManager(String custManager){
		this.custManager = custManager;
	}

	//部门号
	private String deptNo = " ";
	public String getDeptNo(){
		return deptNo;
	}
	public void setDeptNo(String deptNo){
		this.deptNo = deptNo;
	}

	//客户业务结算账号
	private String settleAcct = " ";
	public String getSettleAcct(){
		return settleAcct;
	}
	public void setSettleAcct(String settleAcct){
		this.settleAcct = settleAcct;
	}

	//出票人开户行地址
	private String remitterAddr = " ";
	public String getRemitterAddr(){
		return remitterAddr;
	}
	public void setRemitterAddr(String remitterAddr){
		this.remitterAddr = remitterAddr;
	}

	//额度扣减是否成功
	private String isAmount = " ";
	public String getIsAmount(){
		return isAmount;
	}
	public void setIsAmount(String isAmount){
		this.isAmount = isAmount;
	}

	//承兑协议号
	private String acceptProtocolNo = " ";
	public String getAcceptProtocolNo(){
		return acceptProtocolNo;
	}
	public void setAcceptProtocolNo(String acceptProtocolNo){
		this.acceptProtocolNo = acceptProtocolNo;
	}

	//审核意见
	private String auditRemark = " ";
	public String getAuditRemark(){
		return auditRemark;
	}
	public void setAuditRemark(String auditRemark){
		this.auditRemark = auditRemark;
	}

	//查询方式
	private String queryType = " ";
	public String getQueryType(){
		return queryType;
	}
	public void setQueryType(String queryType){
		this.queryType = queryType;
	}

	//查询日期
	private String queryDate = " ";
	public String getQueryDate(){
		return queryDate;
	}
	public void setQueryDate(String queryDate){
		this.queryDate = queryDate;
	}

	//查询时间
	private String queryTime = " ";
	public String getQueryTime(){
		return queryTime;
	}
	public void setQueryTime(String queryTime){
		this.queryTime = queryTime;
	}

	//查复日期
	private String queryGetDate = " ";
	public String getQueryGetDate(){
		return queryGetDate;
	}
	public void setQueryGetDate(String queryGetDate){
		this.queryGetDate = queryGetDate;
	}

	//查复时间
	private String queryGetTime = " ";
	public String getQueryGetTime(){
		return queryGetTime;
	}
	public void setQueryGetTime(String queryGetTime){
		this.queryGetTime = queryGetTime;
	}

	//禁止背书标记
	private String forbidFlag = " ";
	public String getForbidFlag(){
		return forbidFlag;
	}
	public void setForbidFlag(String forbidFlag){
		this.forbidFlag = forbidFlag;
	}

	//移植来源
	private String yzSource = " ";
	public String getYzSource(){
		return yzSource;
	}
	public void setYzSource(String yzSource){
		this.yzSource = yzSource;
	}

	//审核提交是否更改业务种类
	private String ifTj = " ";
	public String getIfTj(){
		return ifTj;
	}
	public void setIfTj(String ifTj){
		this.ifTj = ifTj;
	}

	//记账前台流水号
	private String exSerial = " ";
	public String getExSerial(){
		return exSerial;
	}
	public void setExSerial(String exSerial){
		this.exSerial = exSerial;
	}

	//产品名称
	private String prodName = " ";
	public String getProdName(){
		return prodName;
	}
	public void setProdName(String prodName){
		this.prodName = prodName;
	}

	//是否产生票据池额度
	private String isFac = " ";
	public String getIsFac(){
		return isFac;
	}
	public void setIsFac(String isFac){
		this.isFac = isFac;
	}

	//客户号
	private String custNo = " ";
	public String getCustNo(){
		return custNo;
	}
	public void setCustNo(String custNo){
		this.custNo = custNo;
	}

	//客户名称
	private String custName = " ";
	public String getCustName(){
		return custName;
	}
	public void setCustName(String custName){
		this.custName = custName;
	}

	//是否可循环
	private String isCyc = " ";
	public String getIsCyc(){
		return isCyc;
	}
	public void setIsCyc(String isCyc){
		this.isCyc = isCyc;
	}

	//额度扣减对象
	private String ownerPartyName = " ";
	public String getOwnerPartyName(){
		return ownerPartyName;
	}
	public void setOwnerPartyName(String ownerPartyName){
		this.ownerPartyName = ownerPartyName;
	}

	//组织机构代码
	private String orgCode = " ";
	public String getOrgCode(){
		return orgCode;
	}
	public void setOrgCode(String orgCode){
		this.orgCode = orgCode;
	}

	//查询内容
	private String queryContent = " ";
	public String getQueryContent(){
		return queryContent;
	}
	public void setQueryContent(String queryContent){
		this.queryContent = queryContent;
	}

	//查复内容
	private String returnContent = " ";
	public String getReturnContent(){
		return returnContent;
	}
	public void setReturnContent(String returnContent){
		this.returnContent = returnContent;
	}

	//审核内容
	private String auditContent = " ";
	public String getAuditContent(){
		return auditContent;
	}
	public void setAuditContent(String auditContent){
		this.auditContent = auditContent;
	}

	//签收内容
	private String signContent = " ";
	public String getSignContent(){
		return signContent;
	}
	public void setSignContent(String signContent){
		this.signContent = signContent;
	}

	//部门名称
	private String deptName = " ";
	public String getDeptName(){
		return deptName;
	}
	public void setDeptName(String deptName){
		this.deptName = deptName;
	}

	//客户经理名称
	private String custManagerName = " ";
	public String getCustManagerName(){
		return custManagerName;
	}
	public void setCustManagerName(String custManagerName){
		this.custManagerName = custManagerName;
	}

	//发托日
	private String collDate = " ";
	public String getCollDate(){
		return collDate;
	}
	public void setCollDate(String collDate){
		this.collDate = collDate;
	}

	//记账日期
	private String accountDate = " ";
	public String getAccountDate(){
		return accountDate;
	}
	public void setAccountDate(String accountDate){
		this.accountDate = accountDate;
	}

	//申请时产品类型
	private String applyProdNo = " ";
	public String getApplyProdNo(){
		return applyProdNo;
	}
	public void setApplyProdNo(String applyProdNo){
		this.applyProdNo = applyProdNo;
	}

	//申请时产品名称
	private String applyProdName = " ";
	public String getApplyProdName(){
		return applyProdName;
	}
	public void setApplyProdName(String applyProdName){
		this.applyProdName = applyProdName;
	}

	//申请柜员
	private String applyOperNo = " ";
	public String getApplyOperNo(){
		return applyOperNo;
	}
	public void setApplyOperNo(String applyOperNo){
		this.applyOperNo = applyOperNo;
	}

	//审核柜员
	private String checkOperNo = " ";
	public String getCheckOperNo(){
		return checkOperNo;
	}
	public void setCheckOperNo(String checkOperNo){
		this.checkOperNo = checkOperNo;
	}

	//记账柜员
	private String accountOperNo = " ";
	public String getAccountOperNo(){
		return accountOperNo;
	}
	public void setAccountOperNo(String accountOperNo){
		this.accountOperNo = accountOperNo;
	}

	//申请日期
	private String applyDate = " ";
	public String getApplyDate(){
		return applyDate;
	}
	public void setApplyDate(String applyDate){
		this.applyDate = applyDate;
	}

	//申请时间
	private String applyTime = " ";
	public String getApplyTime(){
		return applyTime;
	}
	public void setApplyTime(String applyTime){
		this.applyTime = applyTime;
	}

	//核对日期
	private String checkDate = " ";
	public String getCheckDate(){
		return checkDate;
	}
	public void setCheckDate(String checkDate){
		this.checkDate = checkDate;
	}

	//核对时间
	private String checkTime = " ";
	public String getCheckTime(){
		return checkTime;
	}
	public void setCheckTime(String checkTime){
		this.checkTime = checkTime;
	}

	//记账时间
	private String accountTime = " ";
	public String getAccountTime(){
		return accountTime;
	}
	public void setAccountTime(String accountTime){
		this.accountTime = accountTime;
	}

	//请求报文类型编号
	private String reqDraftNo = " ";
	public String getReqDraftNo(){
		return reqDraftNo;
	}
	public void setReqDraftNo(String reqDraftNo){
		this.reqDraftNo = reqDraftNo;
	}

	//申请撤销标记
	private String applyCancelFlag = " ";
	public String getApplyCancelFlag(){
		return applyCancelFlag;
	}
	public void setApplyCancelFlag(String applyCancelFlag){
		this.applyCancelFlag = applyCancelFlag;
	}

	//内部编号
	private String ebsNo = " ";
	public String getEbsNo(){
		return ebsNo;
	}
	public void setEbsNo(String ebsNo){
		this.ebsNo = ebsNo;
	}

	//承兑行行号
	private String acceptorBankNo = " ";
	public String getAcceptorBankNo(){
		return acceptorBankNo;
	}
	public void setAcceptorBankNo(String acceptorBankNo){
		this.acceptorBankNo = acceptorBankNo;
	}

	//承兑人开户行名称
	private String acceptorBankName = " ";
	public String getAcceptorBankName(){
		return acceptorBankName;
	}
	public void setAcceptorBankName(String acceptorBankName){
		this.acceptorBankName = acceptorBankName;
	}

	//流转岗位
	private String position = " ";
	public String getPosition(){
		return position;
	}
	public void setPosition(String position){
		this.position = position;
	}

	//票款收回类型
	private String gathType = " ";
	public String getGathType(){
		return gathType;
	}
	public void setGathType(String gathType){
		this.gathType = gathType;
	}

	//票款收回日期
	private String gathDate = " ";
	public String getGathDate(){
		return gathDate;
	}
	public void setGathDate(String gathDate){
		this.gathDate = gathDate;
	}

	//limit_reduce_row
	private String limitReduceRow = " ";
	public String getLimitReduceRow(){
		return limitReduceRow;
	}
	public void setLimitReduceRow(String limitReduceRow){
		this.limitReduceRow = limitReduceRow;
	}

	//所属客户
	private String ownerPartyId = " ";
	public String getOwnerPartyId(){
		return ownerPartyId;
	}
	public void setOwnerPartyId(String ownerPartyId){
		this.ownerPartyId = ownerPartyId;
	}

	//审批意见
	private String auditReason = " ";
	public String getAuditReason(){
		return auditReason;
	}
	public void setAuditReason(String auditReason){
		this.auditReason = auditReason;
	}

	//备注
	private String remark = " ";
	public String getRemark(){
		return remark;
	}
	public void setRemark(String remark){
		this.remark = remark;
	}
	
	//原申请报文id
	private String reqMsgId = " ";
	public String getReqMsgId(){
		return reqMsgId;
	}
	public void setReqMsgId(String reqMsgId){
		this.reqMsgId = reqMsgId;
	}
	
	//外加
	private String conferNo = " ";
	public void setConferNo(String conferNo) {
		this.conferNo = conferNo;
	}
	public String getConferNo() {
		return conferNo;
	}
	
	private String draweeAddr = " ";
	public void setDraweeAddr(String draweeAddr) {
		this.draweeAddr = draweeAddr;
	}
	public String getDraweeAddr() {
		return draweeAddr;
	}
	
	private String isSameCity = " ";
	public void setIsSameCity(String isSameCity) {
		this.isSameCity = isSameCity;
	}
	public String getIsSameCity() {
		return isSameCity;
	}
	
	private String protocalNo = " ";
	public void setProtocalNo(String protocalNo) {
		this.protocalNo = protocalNo;
	}
	public String getProtocalNo() {
		return protocalNo;
	}
	/**根据prod_no数值 获得对应字符串（汉字）*/
	public String getProdNoString(){
		IProductService service = ServiceFactory.getProductService();
		String productprodname = "";
		try {
			Product	product = service.getProductInfoByProdNo(this.getProdNo());
			productprodname = product.getProdName();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return productprodname;
	}

}
