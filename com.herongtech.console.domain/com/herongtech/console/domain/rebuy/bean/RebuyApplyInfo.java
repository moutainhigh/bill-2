/********************************************
* 文件名称: RebuyApplyInfo.java
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
package com.herongtech.console.domain.rebuy.bean;

import java.sql.SQLException;

import com.herongtech.console.domain.common.bean.Product;
import com.herongtech.console.service.ServiceFactory;
import com.herongtech.console.service.busiservice.rebuy.IRebuyService;
import com.herongtech.console.service.common.product.IProductService;
import com.herongtech.console.web.busicontroller.common.CommonConst;
import com.herongtech.console.web.busicontroller.common.RebuyCodeConst;
import com.herongtech.exception.impl.BizAppException;

public class RebuyApplyInfo{
	private String rebuyId = " ";	//转入流水号
	private String batchNo = " ";	//批次号
	private String prodNo = " ";	//产品编号
	private String billType = " ";	//申请票据类型
	private String billClass = " ";	//申请种类
	private String rebuyDt = " ";	//买入日
	private String resaleDueDt = " ";	//买入返售到期日
	private double rate = 0.0;	//利率
	private String rateType = " ";	//利率类型
	private String delayType = " ";	//顺延方式
	private String isDummy = " ";	//是否移票
	private String isInner = " ";	//是否系统内
	private String bidectDueDt = " ";	//双向买断到期日
	private String delayDays = " ";	//顺延天数
	private String isBidirSale = " ";	//是否双向买断
	private String custManagerName = " ";	//客户经理名称
	private String deptNo = " ";	//部门号
	private String createDt = " ";	//创建日期
	private String createTime = " ";	//创建时间
	private String deptName = " ";	//部门名称
	private String profOwnerNo = " ";	//经营机构归属号
	private String profOwner = " ";	//经营机构归属
	private String custName = " ";	//客户名称
	private String discType = " ";	//转入类型
	private String productAtts = " ";	//产品属性
	private double cbRate = 0.0;	//成本利率
	private String fileNo = " ";	//档案号
	private String billStorageOrg = " ";	//代保管机构号
	private String resaleStaDt = " ";	//买入返售开放日期
	private double salebackRate = 0.0;	//返售利率
	private double salebackMoney = 0.0;	//返售金额
	private String salebackRateType = " ";	//返售利率类型
	private String custManage = " ";	//客户经理编号
	private String isOnline = " ";	//是否线上铅酸
	private String billStorageOrgName = " ";	//代保管机构名称
	private String isRediscCenter = " ";	//是否央行卖票
	private String busiBranchNo = " ";	//业务操作机构号
	private String busiBranchName = " ";	//业务操作机构名称
	private String endorsesBankNo = " ";	//票据中间背书人行号
	private String busiBrchBankNo = " ";	//业务操作机构行号
	private String custNo = " ";	//客户号
	private String custType = " ";	//客户类型
	private String applyOperNo = " ";	//申请岗
	private String auditOperNo = " ";	//审核岗
	private String acctOperNo = " ";	//记账岗柜员
	private String remark = " ";	//备注
	private String custAccount = " ";	//客户账号
	private String custBranchNo = " ";	//客户开户行机构
	private String custBranchName = " ";	//客户开户行机构名称
	private String custBankNo = " ";	//客户开户行行号
	private String custBankName = " ";	//客户开户行名称
	private String tradeAcct = " ";	//入账账号
	private String tradeAcctName = " ";	//入账账号名称
	private String saleId = " ";	//卖出流水号
	private String prodAttr = " ";	//产品属性编号
	private String branchNo = " ";	//签发机构
	private String tradeAcctType = " ";	//入账账户类型
	private String tradeAcctOrg = " ";	//入账账户所属机构
	private String custOrgCode = " ";	//客户组织机构代码
	private String orderId = " ";	//订单编号
	private String applyStatus = " ";	//批次状态
	// 票据总张数
	private String totalNum = "";
	// 票面总金额
	private String totalMoney = "";
	//总利息
	private String totalInterest = "";
	//实付金额
	private String actualAmount = "";
	
	public String getRebuyId(){
		return rebuyId;
	}
	public void setRebuyId(String rebuyId){
		this.rebuyId = rebuyId;
	}

	public String getBatchNo(){
		return batchNo;
	}
	public void setBatchNo(String batchNo){
		this.batchNo = batchNo;
	}

	public String getProdNo(){
		return prodNo;
	}
	public void setProdNo(String prodNo){
		this.prodNo = prodNo;
	}

	public String getBillType(){
		return billType;
	}
	public void setBillType(String billType){
		this.billType = billType;
	}

	public String getBillClass(){
		return billClass;
	}
	public void setBillClass(String billClass){
		this.billClass = billClass;
	}

	public String getRebuyDt(){
		return rebuyDt;
	}
	public void setRebuyDt(String rebuyDt){
		this.rebuyDt = rebuyDt;
	}

	public String getResaleDueDt(){
		return resaleDueDt;
	}
	public void setResaleDueDt(String resaleDueDt){
		this.resaleDueDt = resaleDueDt;
	}

	public double getRate(){
		return rate;
	}
	public void setRate(double rate){
		this.rate = rate;
	}

	public String getRateType(){
		return rateType;
	}
	public void setRateType(String rateType){
		this.rateType = rateType;
	}

	public String getDelayType(){
		return delayType;
	}
	public void setDelayType(String delayType){
		this.delayType = delayType;
	}

	public String getIsDummy(){
		return isDummy;
	}
	public void setIsDummy(String isDummy){
		this.isDummy = isDummy;
	}

	public String getIsInner(){
		return isInner;
	}
	public void setIsInner(String isInner){
		this.isInner = isInner;
	}

	public String getBidectDueDt(){
		return bidectDueDt;
	}
	public void setBidectDueDt(String bidectDueDt){
		this.bidectDueDt = bidectDueDt;
	}

	public String getDelayDays(){
		return delayDays;
	}
	public void setDelayDays(String delayDays){
		this.delayDays = delayDays;
	}

	public String getIsBidirSale(){
		return isBidirSale;
	}
	public void setIsBidirSale(String isBidirSale){
		this.isBidirSale = isBidirSale;
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

	public String getCreateDt(){
		return createDt;
	}
	public void setCreateDt(String createDt){
		this.createDt = createDt;
	}

	public String getCreateTime(){
		return createTime;
	}
	public void setCreateTime(String createTime){
		this.createTime = createTime;
	}

	public String getDeptName(){
		return deptName;
	}
	public void setDeptName(String deptName){
		this.deptName = deptName;
	}

	public String getProfOwnerNo(){
		return profOwnerNo;
	}
	public void setProfOwnerNo(String profOwnerNo){
		this.profOwnerNo = profOwnerNo;
	}

	public String getProfOwner(){
		return profOwner;
	}
	public void setProfOwner(String profOwner){
		this.profOwner = profOwner;
	}

	public String getCustName(){
		return custName;
	}
	public void setCustName(String custName){
		this.custName = custName;
	}

	public String getDiscType(){
		return discType;
	}
	public void setDiscType(String discType){
		this.discType = discType;
	}

	public String getProductAtts(){
		return productAtts;
	}
	public void setProductAtts(String productAtts){
		this.productAtts = productAtts;
	}

	public double getCbRate(){
		return cbRate;
	}
	public void setCbRate(double cbRate){
		this.cbRate = cbRate;
	}

	public String getFileNo(){
		return fileNo;
	}
	public void setFileNo(String fileNo){
		this.fileNo = fileNo;
	}

	public String getBillStorageOrg(){
		return billStorageOrg;
	}
	public void setBillStorageOrg(String billStorageOrg){
		this.billStorageOrg = billStorageOrg;
	}

	public String getResaleStaDt(){
		return resaleStaDt;
	}
	public void setResaleStaDt(String resaleStaDt){
		this.resaleStaDt = resaleStaDt;
	}

	public double getSalebackRate(){
		return salebackRate;
	}
	public void setSalebackRate(double salebackRate){
		this.salebackRate = salebackRate;
	}

	public double getSalebackMoney(){
		return salebackMoney;
	}
	public void setSalebackMoney(double salebackMoney){
		this.salebackMoney = salebackMoney;
	}

	public String getSalebackRateType(){
		return salebackRateType;
	}
	public void setSalebackRateType(String salebackRateType){
		this.salebackRateType = salebackRateType;
	}

	public String getCustManage(){
		return custManage;
	}
	public void setCustManage(String custManage){
		this.custManage = custManage;
	}

	public String getIsOnline(){
		return isOnline;
	}
	public void setIsOnline(String isOnline){
		this.isOnline = isOnline;
	}

	public String getBillStorageOrgName(){
		return billStorageOrgName;
	}
	public void setBillStorageOrgName(String billStorageOrgName){
		this.billStorageOrgName = billStorageOrgName;
	}

	public String getIsRediscCenter(){
		return isRediscCenter;
	}
	public void setIsRediscCenter(String isRediscCenter){
		this.isRediscCenter = isRediscCenter;
	}

	public String getBusiBranchNo(){
		return busiBranchNo;
	}
	public void setBusiBranchNo(String busiBranchNo){
		this.busiBranchNo = busiBranchNo;
	}

	public String getBusiBranchName(){
		return busiBranchName;
	}
	public void setBusiBranchName(String busiBranchName){
		this.busiBranchName = busiBranchName;
	}

	public String getEndorsesBankNo(){
		return endorsesBankNo;
	}
	public void setEndorsesBankNo(String endorsesBankNo){
		this.endorsesBankNo = endorsesBankNo;
	}

	public String getBusiBrchBankNo(){
		return busiBrchBankNo;
	}
	public void setBusiBrchBankNo(String busiBrchBankNo){
		this.busiBrchBankNo = busiBrchBankNo;
	}

	public String getCustNo(){
		return custNo;
	}
	public void setCustNo(String custNo){
		this.custNo = custNo;
	}

	public String getCustType(){
		return custType;
	}
	public void setCustType(String custType){
		this.custType = custType;
	}

	public String getApplyOperNo(){
		return applyOperNo;
	}
	public void setApplyOperNo(String applyOperNo){
		this.applyOperNo = applyOperNo;
	}

	public String getAuditOperNo(){
		return auditOperNo;
	}
	public void setAuditOperNo(String auditOperNo){
		this.auditOperNo = auditOperNo;
	}

	public String getAcctOperNo(){
		return acctOperNo;
	}
	public void setAcctOperNo(String acctOperNo){
		this.acctOperNo = acctOperNo;
	}

	public String getRemark(){
		return remark;
	}
	public void setRemark(String remark){
		this.remark = remark;
	}

	public String getCustAccount(){
		return custAccount;
	}
	public void setCustAccount(String custAccount){
		this.custAccount = custAccount;
	}

	public String getCustBranchNo(){
		return custBranchNo;
	}
	public void setCustBranchNo(String custBranchNo){
		this.custBranchNo = custBranchNo;
	}

	public String getCustBranchName(){
		return custBranchName;
	}
	public void setCustBranchName(String custBranchName){
		this.custBranchName = custBranchName;
	}

	public String getCustBankNo(){
		return custBankNo;
	}
	public void setCustBankNo(String custBankNo){
		this.custBankNo = custBankNo;
	}

	public String getCustBankName(){
		return custBankName;
	}
	public void setCustBankName(String custBankName){
		this.custBankName = custBankName;
	}

	public String getTradeAcct(){
		return tradeAcct;
	}
	public void setTradeAcct(String tradeAcct){
		this.tradeAcct = tradeAcct;
	}

	public String getTradeAcctName(){
		return tradeAcctName;
	}
	public void setTradeAcctName(String tradeAcctName){
		this.tradeAcctName = tradeAcctName;
	}

	public String getSaleId(){
		return saleId;
	}
	public void setSaleId(String saleId){
		this.saleId = saleId;
	}

	public String getProdAttr(){
		return prodAttr;
	}
	public void setProdAttr(String prodAttr){
		this.prodAttr = prodAttr;
	}

	public String getBranchNo(){
		return branchNo;
	}
	public void setBranchNo(String branchNo){
		this.branchNo = branchNo;
	}

	public String getTradeAcctType(){
		return tradeAcctType;
	}
	public void setTradeAcctType(String tradeAcctType){
		this.tradeAcctType = tradeAcctType;
	}

	public String getTradeAcctOrg(){
		return tradeAcctOrg;
	}
	public void setTradeAcctOrg(String tradeAcctOrg){
		this.tradeAcctOrg = tradeAcctOrg;
	}

	public String getCustOrgCode(){
		return custOrgCode;
	}
	public void setCustOrgCode(String custOrgCode){
		this.custOrgCode = custOrgCode;
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
	
	public String getTotalInterest() {
		return totalInterest;
	}
	public void setTotalInterest(String totalInterest) {
		this.totalInterest = totalInterest;
	}
	
	public String getActualAmount() {
		return actualAmount;
	}
	public void setActualAmount(String actualAmount) {
		this.actualAmount = actualAmount;
	}
	
	/**是否代保管
	 * @return
	 */
	public boolean isDummyBoolean(){
		return "0".equals(getIsDummy()) || RebuyCodeConst.IS_DUMMY2.equals(getIsDummy()) || RebuyCodeConst.IS_DUMMY3.equals(getIsDummy()) ? true : false;
	}
	
	/**是否回购式买入
	 * @return
	 */
	public boolean isRegress(){
		return RebuyCodeConst.SALE_REBUY_TYPE_REBUY.equals(getDiscType())?true:false;
	}
	
	/**是否系统内买入
	 * @return
	 */
	public boolean isInner(){
		return RebuyCodeConst.IS_INNER_TRUE.equals(getIsInner())?true:false;
	}
	
	/**是同业间买入
	 * @return
	 */
	public boolean isOutter(){
		return RebuyCodeConst.IS_INNER_FALSE.equals(getIsInner())?true:false;
	}
	
	/**是否实物票
	 * @return
	 */
	public boolean isPaper(){
		return CommonConst.BILL_CLASS_ENTITY.equals(getBillClass())?true:false;
	}
	
	/**是否电子票
	 * @return
	 */
	public boolean isElec(){
		return CommonConst.BILL_CLASS_ELEC.equals(getBillClass())?true:false;
	}
	
	/**是否系统内纸票
	 * @return
	 */
	public boolean isInnerPaper(){
		return CommonConst.BILL_CLASS_ENTITY.equals(getBillClass()) && RebuyCodeConst.IS_INNER_TRUE.equals(getIsInner())?true:false;
	}
	
	/**是否同业间纸票
	 * @return
	 */
	public boolean isOutterPaper(){
		return CommonConst.BILL_CLASS_ENTITY.equals(getBillClass()) && RebuyCodeConst.IS_INNER_FALSE.equals(getIsInner())?true:false;
	}
	
	/**是否同业间电票
	 * @return
	 */
	public boolean isOutterElec(){
		return CommonConst.BILL_CLASS_ELEC.equals(getBillClass()) && RebuyCodeConst.IS_INNER_FALSE.equals(getIsInner())?true:false;
	}
	
	/**根据billtype数字得到中文汉字
	 * @throws BizAppException 
	 * @throws SQLException */
	public String getBillTypeString(){
		IRebuyService service = ServiceFactory.getRebuyService();			
		
		String billtp = "";
		if(this.getBillType()==null || "".equals(this.getBillType())) {
			return "";
		}
		try {
			billtp = service.getBillTypeStringbybilltype(this.getBillType());
		} catch (BizAppException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return billtp;
	}
	
	/**根据billclass数字得到中文汉字
	 * @throws BizAppException 
	 * @throws SQLException */
	public String getBillClassString(){
		IRebuyService service = ServiceFactory.getRebuyService();			
		
		String billcs = "";
		if(this.getBillClass()==null || "".equals(this.getBillClass())) {
			return "";
		}

		try {
			billcs = service.getBillClassStringbybillclass(this.getBillClass());
			return billcs;
		} catch (BizAppException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return billcs;
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
