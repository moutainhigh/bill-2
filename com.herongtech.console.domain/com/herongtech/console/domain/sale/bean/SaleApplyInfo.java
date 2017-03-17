/********************************************
* 文件名称: SaleApplyInfo.java
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
package com.herongtech.console.domain.sale.bean;

import java.sql.SQLException;

import com.herongtech.console.domain.common.bean.Product;
import com.herongtech.console.service.ServiceFactory;
import com.herongtech.console.service.common.product.IProductService;


public class SaleApplyInfo{
	//入账帐号
		private String inAcctNo = " ";
		//入账账户类型
		private String inAcctType = " ";
		//入账账户名称
		private String inAcctName = " ";
		//指定保管机构
		private String billPositionBranch = " ";
		//客户经理编号
		private String custManage = " ";
		//客户经理名称
		private String custManagerName = " ";
		//部门号
		private String deptNo = " ";
		//部门名称
		private String deptName = " ";
		//经营机构编号
		private String workingadsNo = " ";
		//经营机构名称
		private String workingadsName = " ";
		//订单编号
		private String orderId = " ";
		//批次状态
		private String applyStatus = " ";
		

	//转贴出流水号
	private String saleId = " ";
	public String getSaleId(){
		return saleId;
	}
	public void setSaleId(String saleId){
		this.saleId = saleId;
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

	//产品编号
	private String prodNo = " ";
	public String getProdNo(){
		return prodNo;
	}
	public void setProdNo(String prodNo){
		this.prodNo = prodNo;
	}

	//票据类型
	private String billType = " ";
	public String getBillType(){
		return billType;
	}
	public void setBillType(String billType){
		this.billType = billType;
	}

	//票据分类
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

	//转卖日
	private String saleDt = " ";
	public String getSaleDt(){
		return saleDt;
	}
	public void setSaleDt(String saleDt){
		this.saleDt = saleDt;
	}

	//转卖时间
	private String saleTime = " ";
	public String getSaleTime(){
		return saleTime;
	}
	public void setSaleTime(String saleTime){
		this.saleTime = saleTime;
	}

	//卖出回购到期日
	private String rebuyDueDt = " ";
	public String getRebuyDueDt(){
		return rebuyDueDt;
	}
	public void setRebuyDueDt(String rebuyDueDt){
		this.rebuyDueDt = rebuyDueDt;
	}

	//利率
	private double rate = 0.0;
	public double getRate(){
		return rate;
	}
	public void setRate(double rate){
		this.rate = rate;
	}

	//利率类型
	private String rateType = " ";
	public String getRateType(){
		return rateType;
	}
	public void setRateType(String rateType){
		this.rateType = rateType;
	}

	//顺延类型
	private String delayType = " ";
	public String getDelayType(){
		return delayType;
	}
	public void setDelayType(String delayType){
		this.delayType = delayType;
	}

	//是否移票( 是否移票||转代保管（0，不移票- 不转代保管，1移票-转代保管）)
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

	//双向买断到期日
	private String bidectDueDt = " ";
	public String getBidectDueDt(){
		return bidectDueDt;
	}
	public void setBidectDueDt(String bidectDueDt){
		this.bidectDueDt = bidectDueDt;
	}

	//顺延天数
	private String delayDays = " ";
	public String getDelayDays(){
		return delayDays;
	}
	public void setDelayDays(String delayDays){
		this.delayDays = delayDays;
	}

	//机构id#
	private String branchId = " ";
	public String getBranchId(){
		return branchId;
	}
	public void setBranchId(String branchId){
		this.branchId = branchId;
	}

	//是否双卖标识
	private String ifBidirBuy = " ";
	public String getIfBidirBuy(){
		return ifBidirBuy;
	}
	public void setIfBidirBuy(String ifBidirBuy){
		this.ifBidirBuy = ifBidirBuy;
	}

	//状态
	private String status = " ";
	public String getStatus(){
		return status;
	}
	public void setStatus(String status){
		this.status = status;
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

	//操作柜员
	private String operNo = " ";
	public String getOperNo(){
		return operNo;
	}
	public void setOperNo(String operNo){
		this.operNo = operNo;
	}

	//批次类型
	private String batchType = " ";
	public String getBatchType(){
		return batchType;
	}
	public void setBatchType(String batchType){
		this.batchType = batchType;
	}

	//是否再贴现
	private String isRedisc = " ";
	public String getIsRedisc(){
		return isRedisc;
	}
	public void setIsRedisc(String isRedisc){
		this.isRedisc = isRedisc;
	}

	//转卖类型
	private String saleType = " ";
	public String getSaleType(){
		return saleType;
	}
	public void setSaleType(String saleType){
		this.saleType = saleType;
	}

	//赎回利率
	private double buybackRate = 0.0;
	public double getBuybackRate(){
		return buybackRate;
	}
	public void setBuybackRate(double buybackRate){
		this.buybackRate = buybackRate;
	}

	//赎回开放日
	private String buybackOpenDt = " ";
	public String getBuybackOpenDt(){
		return buybackOpenDt;
	}
	public void setBuybackOpenDt(String buybackOpenDt){
		this.buybackOpenDt = buybackOpenDt;
	}

	//赎回金额
	private double buybackMoney = 0.0;
	public double getBuybackMoney(){
		return buybackMoney;
	}
	public void setBuybackMoney(double buybackMoney){
		this.buybackMoney = buybackMoney;
	}

	//是否线上清算
	private String isOnline = " ";
	public String getIsOnline(){
		return isOnline;
	}
	public void setIsOnline(String isOnline){
		this.isOnline = isOnline;
	}

	//交易对手名称
	private String mockAimName = " ";
	public String getMockAimName(){
		return mockAimName;
	}
	public void setMockAimName(String mockAimName){
		this.mockAimName = mockAimName;
	}

	//产品属性名称
	private String prodAttr = " ";
	public String getProdAttr(){
		return prodAttr;
	}
	public void setProdAttr(String prodAttr){
		this.prodAttr = prodAttr;
	}

	//客户名称
	private String custName = " ";
	public String getCustName(){
		return custName;
	}
	public void setCustName(String custName){
		this.custName = custName;
	}

	//财务公司客户号
	private String financialCustNo = " ";
	public String getFinancialCustNo(){
		return financialCustNo;
	}
	public void setFinancialCustNo(String financialCustNo){
		this.financialCustNo = financialCustNo;
	}

	//客户开户行行名
	private String custBankName = " ";
	public String getCustBankName(){
		return custBankName;
	}
	public void setCustBankName(String custBankName){
		this.custBankName = custBankName;
	}

	//客户开户行行号
	private String custBankNo = " ";
	public String getCustBankNo(){
		return custBankNo;
	}
	public void setCustBankNo(String custBankNo){
		this.custBankNo = custBankNo;
	}

	//实际收款行行号
	private String factBankNo = " ";
	public String getFactBankNo(){
		return factBankNo;
	}
	public void setFactBankNo(String factBankNo){
		this.factBankNo = factBankNo;
	}

	//实际收款行行名
	private String factBankName = " ";
	public String getFactBankName(){
		return factBankName;
	}
	public void setFactBankName(String factBankName){
		this.factBankName = factBankName;
	}

	//禁止背书标志   0 否 1. 是
	private String forbidFlag = " ";
	public String getForbidFlag(){
		return forbidFlag;
	}
	public void setForbidFlag(String forbidFlag){
		this.forbidFlag = forbidFlag;
	}

	//交易机构#
	private String branchNo = " ";
	public String getBranchNo(){
		return branchNo;
	}
	public void setBranchNo(String branchNo){
		this.branchNo = branchNo;
	}

	//客户类型
	private String custType = " ";
	public String getCustType(){
		return custType;
	}
	public void setCustType(String custType){
		this.custType = custType;
	}

	//当前操作员所在机构号
	private String billStorageBrchno = " ";
	public String getBillStorageBrchno(){
		return billStorageBrchno;
	}
	public void setBillStorageBrchno(String billStorageBrchno){
		this.billStorageBrchno = billStorageBrchno;
	}

	//当前操作员所在机构名称
	private String billStorageName = " ";
	public String getBillStorageName(){
		return billStorageName;
	}
	public void setBillStorageName(String billStorageName){
		this.billStorageName = billStorageName;
	}

	//当前操作员所在机构id#
	private String billStorageBrchid = " ";
	public String getBillStorageBrchid(){
		return billStorageBrchid;
	}
	public void setBillStorageBrchid(String billStorageBrchid){
		this.billStorageBrchid = billStorageBrchid;
	}

	//客户号
	private String custNo = " ";
	public String getCustNo(){
		return custNo;
	}
	public void setCustNo(String custNo){
		this.custNo = custNo;
	}

	//产品属性编号
	private String prodAttrNo = " ";
	public String getProdAttrNo(){
		return prodAttrNo;
	}
	public void setProdAttrNo(String prodAttrNo){
		this.prodAttrNo = prodAttrNo;
	}

	public String getInAcctNo(){
		return inAcctNo;
	}
	public void setInAcctNo(String inAcctNo){
		this.inAcctNo = inAcctNo;
	}

	public String getInAcctType(){
		return inAcctType;
	}
	public void setInAcctType(String inAcctType){
		this.inAcctType = inAcctType;
	}

	public String getInAcctName(){
		return inAcctName;
	}
	public void setInAcctName(String inAcctName){
		this.inAcctName = inAcctName;
	}

	public String getBillPositionBranch(){
		return billPositionBranch;
	}
	public void setBillPositionBranch(String billPositionBranch){
		this.billPositionBranch = billPositionBranch;
	}

	public String getCustManage(){
		return custManage;
	}
	public void setCustManage(String custManage){
		this.custManage = custManage;
	}

	public String getCustManagerName(){
		return custManagerName;
	}
	public void setCustManagerName(String custManagerName){
		this.custManagerName = custManagerName;
	}

	public String getDeptNo(){
		return deptNo;
	}
	public void setDeptNo(String deptNo){
		this.deptNo = deptNo;
	}

	public String getDeptName(){
		return deptName;
	}
	public void setDeptName(String deptName){
		this.deptName = deptName;
	}

	public String getWorkingadsNo(){
		return workingadsNo;
	}
	public void setWorkingadsNo(String workingadsNo){
		this.workingadsNo = workingadsNo;
	}

	public String getWorkingadsName(){
		return workingadsName;
	}
	public void setWorkingadsName(String workingadsName){
		this.workingadsName = workingadsName;
	}

	public String getOrderId(){
		return orderId;
	}
	public void setOrderId(String orderId){
		this.orderId = orderId;
	}

	public String getApplyStatus(){
		return applyStatus;
	}
	public void setApplyStatus(String applyStatus){
		this.applyStatus = applyStatus;
	}
	// 票据总张数
			private Integer totalSize=0 ;
			// 票面总金额
			private Double sumMoney=0.00;
		public Integer getTotalSize() {
			return totalSize;
		}
		public void setTotalSize(Integer totalSize) {
			this.totalSize = totalSize;
		}
		public Double getSumMoney() {
			return sumMoney;
		}
		public void setSumMoney(Double sumMoney) {
			this.sumMoney = sumMoney;
		}
		
		//合计利息
		public Double sumInterest;
		//合计实付金额
		public Double sumReceiveMoney;
		public Double getSumInterest() {
			return sumInterest;
		}
		public void setSumInterest(Double sumInterest) {
			this.sumInterest = sumInterest;
		}
		public Double getSumReceiveMoney() {
			return sumReceiveMoney;
		}
		public void setSumReceiveMoney(Double sumReceiveMoney) {
			this.sumReceiveMoney = sumReceiveMoney;
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
