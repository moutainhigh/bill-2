/********************************************
* 文件名称: SalebackApplyInfo.java
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
package com.herongtech.console.domain.saleback.bean;

import java.sql.SQLException;

import com.herongtech.console.core.util.MoneyUtils;
import com.herongtech.console.domain.common.bean.Product;
import com.herongtech.console.service.ServiceFactory;
import com.herongtech.console.service.common.product.IProductService;


public class SalebackApplyInfo{
	//返售流水号
	private String salebackId = " ";
	public String getSalebackId(){
		return salebackId;
	}
	public void setSalebackId(String salebackId){
		this.salebackId = salebackId;
	}

	//批次号
	private String batchNo = " ";
	public String getBatchNo(){
		return batchNo;
	}
	public void setBatchNo(String batchNo){
		this.batchNo = batchNo;
	}

	//交易对手机构
	private String aimBranchNo = " ";
	public String getAimBranchNo(){
		return aimBranchNo;
	}
	public void setAimBranchNo(String aimBranchNo){
		this.aimBranchNo = aimBranchNo;
	}

	//票据类型
	private String billType = " ";
	public String getBillType(){
		return billType;
	}
	public void setBillType(String billType){
		this.billType = billType;
	}

	//票据种类
	private String billClass = " ";
	public String getBillClass(){
		return billClass;
	}
	public void setBillClass(String billClass){
		this.billClass = billClass;
	}

	//往来账号
	private String innerAccount = " ";
	public String getInnerAccount(){
		return innerAccount;
	}
	public void setInnerAccount(String innerAccount){
		this.innerAccount = innerAccount;
	}

	//原转入日
	private String buyDt = " ";
	public String getBuyDt(){
		return buyDt;
	}
	public void setBuyDt(String buyDt){
		this.buyDt = buyDt;
	}

	//原转入时间
	private String buyTime = " ";
	public String getBuyTime(){
		return buyTime;
	}
	public void setBuyTime(String buyTime){
		this.buyTime = buyTime;
	}

	//返售到期日
	private String salebackDueDt = " ";
	public String getSalebackDueDt(){
		return salebackDueDt;
	}
	public void setSalebackDueDt(String salebackDueDt){
		this.salebackDueDt = salebackDueDt;
	}

	//是否移票
	private String isDummy = " ";
	public String getIsDummy(){
		return isDummy;
	}
	public void setIsDummy(String isDummy){
		this.isDummy = isDummy;
	}

	//是否系统内
	private String isInner = " ";
	public String getIsInner(){
		return isInner;
	}
	public void setIsInner(String isInner){
		this.isInner = isInner;
	}

	//机构
	private String branchNo = " ";
	public String getBranchNo(){
		return branchNo;
	}
	public void setBranchNo(String branchNo){
		this.branchNo = branchNo;
	}

	//产品编号
	private String prodNo = " ";
	public String getProdNo(){
		return prodNo;
	}
	public void setProdNo(String prodNo){
		this.prodNo = prodNo;
	}

	//状态
	private String status = " ";
	public String getStatus(){
		return status;
	}
	public void setStatus(String status){
		this.status = status;
	}

	//实际返售日
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

	//操作柜员
	private String operNo = " ";
	public String getOperNo(){
		return operNo;
	}
	public void setOperNo(String operNo){
		this.operNo = operNo;
	}

	//利率类型
	private String rateType = " ";
	public String getRateType(){
		return rateType;
	}
	public void setRateType(String rateType){
		this.rateType = rateType;
	}

	//是否线上清算
	private String isOnline = " ";
	public String getIsOnline(){
		return isOnline;
	}
	public void setIsOnline(String isOnline){
		this.isOnline = isOnline;
	}

	//买入返售开放日
	private String salebackOpenDt = " ";
	public String getSalebackOpenDt(){
		return salebackOpenDt;
	}
	public void setSalebackOpenDt(String salebackOpenDt){
		this.salebackOpenDt = salebackOpenDt;
	}

	//买入返售利率
	private double salebackRate = 0.0;
	public double getSalebackRate(){
		return salebackRate;
	}
	public void setSalebackRate(double salebackRate){
		this.salebackRate = salebackRate;
	}

	//买入返售金额
	private double salebackMoney = 0.0;
	public double getSalebackMoney(){
		return salebackMoney;
	}
	public void setSalebackMoney(double salebackMoney){
		this.salebackMoney = salebackMoney;
	}

	//客户名称
	private String custName = " ";
	public String getCustName(){
		return custName;
	}
	public void setCustName(String custName){
		this.custName = custName;
	}

	//利率
	private double rate = 0.0;
	public double getRate(){
		return rate;
	}
	public void setRate(double rate){
		this.rate = rate;
	}

	//顺延类型
	private String delayType = " ";
	public String getDelayType(){
		return delayType;
	}
	public void setDelayType(String delayType){
		this.delayType = delayType;
	}

	//顺延天数
	private Long delayDays = 0l;
	public Long getDelayDays(){
		return delayDays;
	}
	public void setDelayDays(Long delayDays){
		this.delayDays = delayDays;
	}

	//指定保管机构
	private String billStorageOrg = " ";
	public String getBillStorageOrg(){
		return billStorageOrg;
	}
	public void setBillStorageOrg(String billStorageOrg){
		this.billStorageOrg = billStorageOrg;
	}

	//保管机构名称
	private String billStorageOrgName = " ";
	public String getBillStorageOrgName(){
		return billStorageOrgName;
	}
	public void setBillStorageOrgName(String billStorageOrgName){
		this.billStorageOrgName = billStorageOrgName;
	}

	//入账帐号
	private String inAcctNo = " ";
	public String getInAcctNo(){
		return inAcctNo;
	}
	public void setInAcctNo(String inAcctNo){
		this.inAcctNo = inAcctNo;
	}

	//入账账号类型
	private String inAcctType = " ";
	public String getInAcctType(){
		return inAcctType;
	}
	public void setInAcctType(String inAcctType){
		this.inAcctType = inAcctType;
	}

	//入账账号名称
	private String inAcctName = " ";
	public String getInAcctName(){
		return inAcctName;
	}
	public void setInAcctName(String inAcctName){
		this.inAcctName = inAcctName;
	}

	//订单编号
	private String orderId = " ";
	public String getOrderId(){
		return orderId;
	}
	public void setOrderId(String orderId){
		this.orderId = orderId;
	}

	//批次状态
	private String applyStatus = " ";
	public String getApplyStatus(){
		return applyStatus;
	}
	public void setApplyStatus(String applyStatus){
		this.applyStatus = applyStatus;
	}
	
	//新加
	private String totalNum;
	private String totalMoney;
	private String actualAmount;
	private String totalInterest;
	private String workday;
	public String getWorkday() {
		return workday;
	}
	public void setWorkday(String workday) {
		this.workday = workday;
	}
	public String getTotalNum() {
		return totalNum;
	}
	public void setTotalNum(String totalNum) {
		this.totalNum = totalNum;
	}
	public String getTotalMoney() {
		return totalMoney;
	}
	public void setTotalMoney(String totalMoney) {
		this.totalMoney = totalMoney;
	}
	public String getActualAmount() {
		return actualAmount;
	}
	public void setActualAmount(String actualAmount) {
		this.actualAmount = actualAmount;
	}
	public String getTotalInterest() {
		return totalInterest;
	}
	public void setTotalInterest(String totalInterest) {
		this.totalInterest = totalInterest;
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
