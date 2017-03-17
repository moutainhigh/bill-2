/********************************************
* 文件名称: SubcollBillInfo.java
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
package com.herongtech.console.domain.subcoll.bean;

import java.sql.SQLException;

import com.herongtech.console.service.ServiceFactory;
import com.herongtech.console.service.busiservice.subcoll.ISubcollService;
import com.herongtech.exception.impl.BizAppException;

/**
 * 托收清单 bean
 * @author Administrator
 *
 */
public class SubcollBillInfo{
	//主键
	private String subcollmxId = " ";
	public String getSubcollmxId(){
		return subcollmxId;
	}
	public void setSubcollmxId(String subcollmxId){
		this.subcollmxId = subcollmxId;
	}

	//登记中心id
	private String rgctId = " ";
	public String getRgctId(){
		return rgctId;
	}
	public void setRgctId(String rgctId){
		this.rgctId = rgctId;
	}

	//托收流水号(批次)
	private String subcollId = " ";
	public String getSubcollId(){
		return subcollId;
	}
	public void setSubcollId(String subcollId){
		this.subcollId = subcollId;
	}

	//票号
	private String billNo = " ";
	public String getBillNo(){
		return billNo;
	}
	public void setBillNo(String billNo){
		this.billNo = billNo;
	}

	//票据种类
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

	//票据来源
	private String billSource = " ";
	public String getBillSource(){
		return billSource;
	}
	public void setBillSource(String billSource){
		this.billSource = billSource;
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

	//收款人名称
	private String payee = " ";
	public String getPayee(){
		return payee;
	}
	public void setPayee(String payee){
		this.payee = payee;
	}

	//收款人账号
	private String payeeAcct = " ";
	public String getPayeeAcct(){
		return payeeAcct;
	}
	public void setPayeeAcct(String payeeAcct){
		this.payeeAcct = payeeAcct;
	}

	//省份
	private String province = " ";
	public String getProvince(){
		return province;
	}
	public void setProvince(String province){
		this.province = province;
	}

	//收款人行名
	private String payeeBankName = " ";
	public String getPayeeBankName(){
		return payeeBankName;
	}
	public void setPayeeBankName(String payeeBankName){
		this.payeeBankName = payeeBankName;
	}

	//收款人开户行行号
	private String payeeBankNo = " ";
	public String getPayeeBankNo(){
		return payeeBankNo;
	}
	public void setPayeeBankNo(String payeeBankNo){
		this.payeeBankNo = payeeBankNo;
	}

	//操作状态
	private String operStatus = " ";
	public String getOperStatus(){
		return operStatus;
	}
	public void setOperStatus(String operStatus){
		this.operStatus = operStatus;
	}

	//付款行行号
	private String draweeBankNo = " ";
	public String getDraweeBankNo(){
		return draweeBankNo;
	}
	public void setDraweeBankNo(String draweeBankNo){
		this.draweeBankNo = draweeBankNo;
	}

	//付款人行名
	private String draweeBankName = " ";
	public String getDraweeBankName(){
		return draweeBankName;
	}
	public void setDraweeBankName(String draweeBankName){
		this.draweeBankName = draweeBankName;
	}

	//付款行地址
	private String draweeAddr = " ";
	public String getDraweeAddr(){
		return draweeAddr;
	}
	public void setDraweeAddr(String draweeAddr){
		this.draweeAddr = draweeAddr;
	}

	//禁止背书标记
	private String forbidFlag = " ";
	public String getForbidFlag(){
		return forbidFlag;
	}
	public void setForbidFlag(String forbidFlag){
		this.forbidFlag = forbidFlag;
	}

	//出票人名称
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

	//交易前手
	private String billBeforeOwner = " ";
	public String getBillBeforeOwner(){
		return billBeforeOwner;
	}
	public void setBillBeforeOwner(String billBeforeOwner){
		this.billBeforeOwner = billBeforeOwner;
	}

	//所属客户号
	private String belongCustNo = " ";
	public String getBelongCustNo(){
		return belongCustNo;
	}
	public void setBelongCustNo(String belongCustNo){
		this.belongCustNo = belongCustNo;
	}

	//所属客户账号
	private String belongCustAcct = " ";
	public String getBelongCustAcct(){
		return belongCustAcct;
	}
	public void setBelongCustAcct(String belongCustAcct){
		this.belongCustAcct = belongCustAcct;
	}

	//操作机构
	private String branchNo = " ";
	

	public String getBranchNo() {
		return branchNo;
	}
	public void setBranchNo(String branchNo) {
		this.branchNo = branchNo;
	}

	//计息到期日
	private String galeDate = " ";
	public String getGaleDate(){
		return galeDate;
	}
	public void setGaleDate(String galeDate){
		this.galeDate = galeDate;
	}

	//客户名称
	private String custName = " ";
	public String getCustName(){
		return custName;
	}
	public void setCustName(String custName){
		this.custName = custName;
	}

	//是否逾期:1-否,2-是
	private String isOverdue = " ";
	public String getIsOverdue(){
		return isOverdue;
	}
	public void setIsOverdue(String isOverdue){
		this.isOverdue = isOverdue;
	}

	//承兑人帐号
	private String acceptorAcct = " ";
	public String getAcceptorAcct(){
		return acceptorAcct;
	}
	public void setAcceptorAcct(String acceptorAcct){
		this.acceptorAcct = acceptorAcct;
	}

	//承兑行行号
	private String acceptorBankNo = " ";
	public String getAcceptorBankNo(){
		return acceptorBankNo;
	}
	public void setAcceptorBankNo(String acceptorBankNo){
		this.acceptorBankNo = acceptorBankNo;
	}

	//委托收款编号
	private String subcollno = " ";
	public String getSubcollno(){
		return subcollno;
	}
	public void setSubcollno(String subcollno){
		this.subcollno = subcollno;
	}

	//移植来源
	private String yzSource = " ";
	public String getYzSource(){
		return yzSource;
	}
	public void setYzSource(String yzSource){
		this.yzSource = yzSource;
	}

	//操作柜员
	private String operNo = " ";
	public String getOperNo(){
		return operNo;
	}
	public void setOperNo(String operNo){
		this.operNo = operNo;
	}

	//背书次数
	private Long endorsnum = 0l;
	public Long getEndorsnum(){
		return endorsnum;
	}
	public void setEndorsnum(Long endorsnum){
		this.endorsnum = endorsnum;
	}

	//入账账号
	private String inAccount = " ";
	public String getInAccount(){
		return inAccount;
	}
	public void setInAccount(String inAccount){
		this.inAccount = inAccount;
	}

	//前台流水号1
	private String exSerial = " ";
	public String getExSerial(){
		return exSerial;
	}
	public void setExSerial(String exSerial){
		this.exSerial = exSerial;
	}

	//线上清算标识
	private String isOnline = " ";
	public String getIsOnline(){
		return isOnline;
	}
	public void setIsOnline(String isOnline){
		this.isOnline = isOnline;
	}

	//对应最近票据来源的清单id
	private String billId = " ";
	public String getBillId(){
		return billId;
	}
	public void setBillId(String billId){
		this.billId = billId;
	}

	//质押保证金账号
	private String impawnBailAccount = " ";
	public String getImpawnBailAccount(){
		return impawnBailAccount;
	}
	public void setImpawnBailAccount(String impawnBailAccount){
		this.impawnBailAccount = impawnBailAccount;
	}

	//生成日期
	private String createDt = " ";
	public String getCreateDt(){
		return createDt;
	}
	public void setCreateDt(String createDt){
		this.createDt = createDt;
	}

	//生成时间
	private String createTime = " ";
	public String getCreateTime(){
		return createTime;
	}
	public void setCreateTime(String createTime){
		this.createTime = createTime;
	}

	//状态
	private String status = " ";
	public String getStatus(){
		return status;
	}
	public void setStatus(String status){
		this.status = status;
	}

	//业务来源产品编码
	private String sourceProjectNo = " ";
	public String getSourceProjectNo(){
		return sourceProjectNo;
	}
	public void setSourceProjectNo(String sourceProjectNo){
		this.sourceProjectNo = sourceProjectNo;
	}

	//付款金额
	private double collMoney = 0.0;
	public double getCollMoney(){
		return collMoney;
	}
	public void setCollMoney(double collMoney){
		this.collMoney = collMoney;
	}

	//赔偿金额
	private double reparationMoney = 0.0;
	public double getReparationMoney(){
		return reparationMoney;
	}
	public void setReparationMoney(double reparationMoney){
		this.reparationMoney = reparationMoney;
	}

	//交易机构
	private String dealBranchNo = " ";
	public String getDealBranchNo(){
		return dealBranchNo;
	}
	public void setDealBranchNo(String dealBranchNo){
		this.dealBranchNo = dealBranchNo;
	}

	//销记柜员
	private String gathOperNo = " ";
	public String getGathOperNo(){
		return gathOperNo;
	}
	public void setGathOperNo(String gathOperNo){
		this.gathOperNo = gathOperNo;
	}

	//销记审核柜员
	private String gathAuditOperNo = " ";
	public String getGathAuditOperNo(){
		return gathAuditOperNo;
	}
	public void setGathAuditOperNo(String gathAuditOperNo){
		this.gathAuditOperNo = gathAuditOperNo;
	}

	//申请岗
	private String applyOperNo = " ";
	public String getApplyOperNo(){
		return applyOperNo;
	}
	public void setApplyOperNo(String applyOperNo){
		this.applyOperNo = applyOperNo;
	}

	//审核岗
	private String auditOperNo = " ";
	public String getAuditOperNo(){
		return auditOperNo;
	}
	public void setAuditOperNo(String auditOperNo){
		this.auditOperNo = auditOperNo;
	}

	//销记日期1
	private String gathDate = " ";//记账日期
	public String getGathDate(){
		return gathDate;
	}
	public void setGathDate(String gathDate){
		this.gathDate = gathDate;
	}

	//备注
	private String remark = " ";
	public String getRemark(){
		return remark;
	}
	public void setRemark(String remark){
		this.remark = remark;
	}

	//传给pes的产品编码
	private String pesProdNo = " ";
	public String getPesProdNo(){
		return pesProdNo;
	}
	public void setPesProdNo(String pesProdNo){
		this.pesProdNo = pesProdNo;
	}

	//传给pes的交易行为编码
	private String pesBusiNo = " ";
	public String getPesBusiNo(){
		return pesBusiNo;
	}
	public void setPesBusiNo(String pesBusiNo){
		this.pesBusiNo = pesBusiNo;
	}

	//传给pes的流水号
	private String pesFlowNo = " ";
	public String getPesFlowNo(){
		return pesFlowNo;
	}
	public void setPesFlowNo(String pesFlowNo){
		this.pesFlowNo = pesFlowNo;
	}

	//发托日
	private String collDate = " ";
	public String getCollDate(){
		return collDate;
	}
	public void setCollDate(String collDate){
		this.collDate = collDate;
	}

	//托收收款人账号
	private String collPayeeAcct = " ";
	public String getCollPayeeAcct(){
		return collPayeeAcct;
	}
	public void setCollPayeeAcct(String collPayeeAcct){
		this.collPayeeAcct = collPayeeAcct;
	}

	//托收收款人
	private String collPayee = " ";
	public String getCollPayee(){
		return collPayee;
	}
	public void setCollPayee(String collPayee){
		this.collPayee = collPayee;
	}

	//托收收款人行号
	private String collPayeeBankNo = " ";
	public String getCollPayeeBankNo(){
		return collPayeeBankNo;
	}
	public void setCollPayeeBankNo(String collPayeeBankNo){
		this.collPayeeBankNo = collPayeeBankNo;
	}

	//托收收款人行名
	private String collPayeeBank = " ";
	public String getCollPayeeBank(){
		return collPayeeBank;
	}
	public void setCollPayeeBank(String collPayeeBank){
		this.collPayeeBank = collPayeeBank;
	}

	//记账前台流水号
	private String acctFlowNo = " ";
	public String getAcctFlowNo(){
		return acctFlowNo;
	}
	public void setAcctFlowNo(String acctFlowNo){
		this.acctFlowNo = acctFlowNo;
	}

	//订单编号
	private String orderId = " ";
	public String getOrderId(){
		return orderId;
	}
	public void setOrderId(String orderId){
		this.orderId = orderId;
	}

	//是否关联订单
	private String relationFlag = " ";
	public String getRelationFlag(){
		return relationFlag;
	}
	public void setRelationFlag(String relationFlag){
		this.relationFlag = relationFlag;
	}

	//流转岗位
	private String position = " ";
	public String getPosition(){
		return position;
	}
	public void setPosition(String position){
		this.position = position;
	}

	//买入类型
	private String buyType = " ";
	public String getBuyType(){
		return buyType;
	}
	public void setBuyType(String buyType){
		this.buyType = buyType;
	}

	//贴现日
	private String discDt = " ";
	public String getDiscDt(){
		return discDt;
	}
	public void setDiscDt(String discDt){
		this.discDt = discDt;
	}

	//赎回开放日
	private String redeemOpenDt = " ";
	public String getRedeemOpenDt(){
		return redeemOpenDt;
	}
	public void setRedeemOpenDt(String redeemOpenDt){
		this.redeemOpenDt = redeemOpenDt;
	}

	//赎回截止日
	private String redeemEndDt = " ";
	public String getRedeemEndDt(){
		return redeemEndDt;
	}
	public void setRedeemEndDt(String redeemEndDt){
		this.redeemEndDt = redeemEndDt;
	}

	//计息天数
	private Long interestDays = 0l;
	public Long getInterestDays(){
		return interestDays;
	}
	public void setInterestDays(Long interestDays){
		this.interestDays = interestDays;
	}

	//利率
	private double rate = 0.0;
	public double getRate(){
		return rate;
	}
	public void setRate(double rate){
		this.rate = rate;
	}

	//实付金额
	private double payMoney = 0.0;
	public double getPayMoney(){
		return payMoney;
	}
	public void setPayMoney(double payMoney){
		this.payMoney = payMoney;
	}

	//总利息
	private double interest = 0.0;
	public double getInterest(){
		return interest;
	}
	public void setInterest(double interest){
		this.interest = interest;
	}

	//承兑人开户行名称
	private String acceptorBankName = " ";
	public String getAcceptorBankName(){
		return acceptorBankName;
	}
	public void setAcceptorBankName(String acceptorBankName){
		this.acceptorBankName = acceptorBankName;
	}

	//账务机构号
	private String acctBranchNo = " ";
	public String getAcctBranchNo(){
		return acctBranchNo;
	}
	public void setAcctBranchNo(String acctBranchNo){
		this.acctBranchNo = acctBranchNo;
	}

	//档案编号
	private String fileNo = " ";
	public String getFileNo(){
		return fileNo;
	}
	public void setFileNo(String fileNo){
		this.fileNo = fileNo;
	}

	//票据当前状态
	private String curStatus = " ";
	public String getCurStatus(){
		return curStatus;
	}
	public void setCurStatus(String curStatus){
		this.curStatus = curStatus;
	}

	//产品属性编号
	private String prodAttr = " ";
	public String getProdAttr(){
		return prodAttr;
	}
	public void setProdAttr(String prodAttr){
		this.prodAttr = prodAttr;
	}

	//强制收款挂应收应付订单号
	private String suspdOrderId = " ";
	public String getSuspdOrderId(){
		return suspdOrderId;
	}
	public void setSuspdOrderId(String suspdOrderId){
		this.suspdOrderId = suspdOrderId;
	}

	//强制收款扣款标志
	private String forceGatheringFlag = " ";
	public String getForceGatheringFlag(){
		return forceGatheringFlag;
	}
	public void setForceGatheringFlag(String forceGatheringFlag){
		this.forceGatheringFlag = forceGatheringFlag;
	}
	/**根据票据类型的标识获得字符串
	 * @throws BizAppException */
	public String getBillClassString(){
		ISubcollService service =ServiceFactory.getSubcollService();
		String billcs = null;
		if(this.getBillClass()==null || "".equals(this.getBillClass())) {
			return "";
		}
		try {
			billcs = service.getBillClassStringbybillclass(this.getBillClass());
		} catch (BizAppException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return billcs;
	}

	/**根据billtype数字得到中文汉字
	 * @throws SQLException */
	public String getBillTypeString(){
		ISubcollService service =ServiceFactory.getSubcollService();
		
		String billtp = null;
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
	/**票据来源字符串  1:代保管，2票据池,3质押,4贴现；5转入，6理财，7分行带总行代保管 */
	public String getBillSourceString(){
		ISubcollService service =ServiceFactory.getSubcollService();
		
		String billsource = null;
		if(this.getBillSource()==null || "".equals(this.getBillSource())) {
			return "";
		}
		try {
			billsource = service.getBillSourceStringbybillsource(this.getBillSource());
		} catch (BizAppException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return billsource;
	}
}
