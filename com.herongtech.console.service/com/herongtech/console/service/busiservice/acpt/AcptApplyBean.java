package com.herongtech.console.service.busiservice.acpt;    //


//银承批次
public class AcptApplyBean {

	//承兑流水号
		private String acptId ;
		private String batchNo;
		private String branchNo;
		//银承协议编号
			private String protocalNo;
			
		public String getAcptId(){
			return acptId;
		}
		public void setAcptId(String acptId){
			this.acptId = acptId;
		}

		//批次号
		public String getBatchNo(){
			return batchNo;
		}
		public void setBatchNo(String batchNo){
			this.batchNo = batchNo;
		}

		//签发机构
		public String getBranchNo(){
			return branchNo;
		}
		public void setBranchNo(String branchNo){
			this.branchNo = branchNo;
		}

		
		public String getProtocalNo(){
			return protocalNo;
		}
		public void setProtocalNo(String protocalNo){
			this.protocalNo = protocalNo;
		}

		//授信额度编号
		private String facNo;
		public String getFacNo(){
			return facNo;
		}
		public void setFacNo(String facNo){
			this.facNo = facNo;
		}

		//放款借据号
		private String fkjjh;
		public String getFkjjh(){
			return fkjjh;
		}
		public void setFkjjh(String fkjjh){
			this.fkjjh = fkjjh;
		}

		//产品编号
		private String prodNo;
		public String getProdNo(){
			return prodNo;
		}
		public void setProdNo(String prodNo){
			this.prodNo = prodNo;
		}

		//出票人客户名称
		private String remitter;
		public String getRemitter(){
			return remitter;
		}
		public void setRemitter(String remitter){
			this.remitter = remitter;
		}

		//出票人客户号
		private String remitterCustNo;
		public String getRemitterCustNo(){
			return remitterCustNo;
		}
		public void setRemitterCustNo(String remitterCustNo){
			this.remitterCustNo = remitterCustNo;
		}

		//出票人客户账号
		private String remitterAcct;
		public String getRemitterAcct(){
			return remitterAcct;
		}
		public void setRemitterAcct(String remitterAcct){
			this.remitterAcct = remitterAcct;
		}

		//出票人开户行行号
		private String remitterBankNo;
		public String getRemitterBankNo(){
			return remitterBankNo;
		}
		public void setRemitterBankNo(String remitterBankNo){
			this.remitterBankNo = remitterBankNo;
		}

		//出票人开户行名称
		private String remitterBankName;
		public String getRemitterBankName(){
			return remitterBankName;
		}
		public void setRemitterBankName(String remitterBankName){
			this.remitterBankName = remitterBankName;
		}

		//出票人开户机构
		private String issueBranchNo;
		public String getIssueBranchNo(){
			return issueBranchNo;
		}
		public void setIssueBranchNo(String issueBranchNo){
			this.issueBranchNo = issueBranchNo;
		}

		//付款行行号
		private String draweeBankNo;
		public String getDraweeBankNo(){
			return draweeBankNo;
		}
		public void setDraweeBankNo(String draweeBankNo){
			this.draweeBankNo = draweeBankNo;
		}

		//付款行行名
		private String draweeBankName;
		public String getDraweeBankName(){
			return draweeBankName;
		}
		public void setDraweeBankName(String draweeBankName){
			this.draweeBankName = draweeBankName;
		}

		//付款行地址
		private String draweeAddr;
		public String getDraweeAddr(){
			return draweeAddr;
		}
		public void setDraweeAddr(String draweeAddr){
			this.draweeAddr = draweeAddr;
		}

		//出票日
		private String issueDt;
		public String getIssueDt(){
			return issueDt;
		}
		public void setIssueDt(String issueDt){
			this.issueDt = issueDt;
		}

		//到期日
		private String dueDt;
		public String getDueDt(){
			return dueDt;
		}
		public void setDueDt(String dueDt){
			this.dueDt = dueDt;
		}

		//票据分类
		private String billClass;
		public String getBillClass(){
			return billClass;
		}
		public void setBillClass(String billClass){
			this.billClass = billClass;
		}

		//票据种类
		private String billType;
		public String getBillType(){
			return billType;
		}
		public void setBillType(String billType){
			this.billType = billType;
		}

		//总笔数
		private Long totalCount = 0l;
		public Long getTotalCount(){
			return totalCount;
		}
		public void setTotalCount(Long totalCount){
			this.totalCount = totalCount;
		}

		//汇总金额
		private Double totalAmt = 0.0;
		public Double getTotalAmt(){
			return totalAmt;
		}
		public void setTotalAmt(Double totalAmt){
			this.totalAmt = totalAmt;
		}

		//到期应扣款汇总金额
		private Double totalAmtForDeduct = 0.0;
		public Double getTotalAmtForDeduct(){
			return totalAmtForDeduct;
		}
		public void setTotalAmtForDeduct(Double totalAmtForDeduct){
			this.totalAmtForDeduct = totalAmtForDeduct;
		}

		//保证金汇总限额
		private Double totalGrantAmt = 0.0;
		public Double getTotalGrantAmt(){
			return totalGrantAmt;
		}
		public void setTotalGrantAmt(Double totalGrantAmt){
			this.totalGrantAmt = totalGrantAmt;
		}

		//保证金圈存金额汇总
		private Double freezeTotalGrantAmt = 0.0;
		public Double getFreezeTotalGrantAmt(){
			return freezeTotalGrantAmt;
		}
		public void setFreezeTotalGrantAmt(Double freezeTotalGrantAmt){
			this.freezeTotalGrantAmt = freezeTotalGrantAmt;
		}

		//第一还款账号
		private String accountNo1;
		public String getAccountNo1(){
			return accountNo1;
		}
		public void setAccountNo1(String accountNo1){
			this.accountNo1 = accountNo1;
		}

		//第一还款账号户名
		private String accountName1;
		public String getAccountName1(){
			return accountName1;
		}
		public void setAccountName1(String accountName1){
			this.accountName1 = accountName1;
		}

		//第一还款账号开户机构
		private String accountBranchNo1;
		public String getAccountBranchNo1(){
			return accountBranchNo1;
		}
		public void setAccountBranchNo1(String accountBranchNo1){
			this.accountBranchNo1 = accountBranchNo1;
		}

		//第一还款账号开户机构名
		private String accountBranchName1;
		public String getAccountBranchName1(){
			return accountBranchName1;
		}
		public void setAccountBranchName1(String accountBranchName1){
			this.accountBranchName1 = accountBranchName1;
		}

		//第一保证金限额
		private Double grantAmt1 = 0.0;
		public Double getGrantAmt1(){
			return grantAmt1;
		}
		public void setGrantAmt1(Double grantAmt1){
			this.grantAmt1 = grantAmt1;
		}

		//第一还款账号类型
		private String accountType1;
		public String getAccountType1(){
			return accountType1;
		}
		public void setAccountType1(String accountType1){
			this.accountType1 = accountType1;
		}

		//第一圈存编号
		private String grantNo1;
		public String getGrantNo1(){
			return grantNo1;
		}
		public void setGrantNo1(String grantNo1){
			this.grantNo1 = grantNo1;
		}

		//第一保证金圈存金额
		private Double freezeGrantAmt1 = 0.0;
		public Double getFreezeGrantAmt1(){
			return freezeGrantAmt1;
		}
		public void setFreezeGrantAmt1(Double freezeGrantAmt1){
			this.freezeGrantAmt1 = freezeGrantAmt1;
		}

		//第二还款账号
		private String accountNo2;
		public String getAccountNo2(){
			return accountNo2;
		}
		public void setAccountNo2(String accountNo2){
			this.accountNo2 = accountNo2;
		}

		//第二还款账号户名
		private String accountName2;
		public String getAccountName2(){
			return accountName2;
		}
		public void setAccountName2(String accountName2){
			this.accountName2 = accountName2;
		}

		//第二还款账号开户机构
		private String accountBranchNo2;
		public String getAccountBranchNo2(){
			return accountBranchNo2;
		}
		public void setAccountBranchNo2(String accountBranchNo2){
			this.accountBranchNo2 = accountBranchNo2;
		}

		//第二还款账号开户机构名
		private String accountBranchName2;
		public String getAccountBranchName2(){
			return accountBranchName2;
		}
		public void setAccountBranchName2(String accountBranchName2){
			this.accountBranchName2 = accountBranchName2;
		}

		//第二保证金限额
		private Double grantAmt2 = 0.0;
		public Double getGrantAmt2(){
			return grantAmt2;
		}
		public void setGrantAmt2(Double grantAmt2){
			this.grantAmt2 = grantAmt2;
		}

		//第二还款账号类型
		private String accountType2;
		public String getAccountType2(){
			return accountType2;
		}
		public void setAccountType2(String accountType2){
			this.accountType2 = accountType2;
		}

		//第二圈存编号
		private String grantNo2;
		public String getGrantNo2(){
			return grantNo2;
		}
		public void setGrantNo2(String grantNo2){
			this.grantNo2 = grantNo2;
		}

		//第二保证金圈存金额
		private Double freezeGrantAmt2 = 0.0;
		public Double getFreezeGrantAmt2(){
			return freezeGrantAmt2;
		}
		public void setFreezeGrantAmt2(Double freezeGrantAmt2){
			this.freezeGrantAmt2 = freezeGrantAmt2;
		}

		//第三还款账号
		private String accountNo3;
		public String getAccountNo3(){
			return accountNo3;
		}
		public void setAccountNo3(String accountNo3){
			this.accountNo3 = accountNo3;
		}

		//第三还款账号户名
		private String accountName3;
		public String getAccountName3(){
			return accountName3;
		}
		public void setAccountName3(String accountName3){
			this.accountName3 = accountName3;
		}

		//第三还款账号开户机构
		private String accountBranchNo3;
		public String getAccountBranchNo3(){
			return accountBranchNo3;
		}
		public void setAccountBranchNo3(String accountBranchNo3){
			this.accountBranchNo3 = accountBranchNo3;
		}

		//第三还款账号开户机构名
		private String accountBranchName3;
		public String getAccountBranchName3(){
			return accountBranchName3;
		}
		public void setAccountBranchName3(String accountBranchName3){
			this.accountBranchName3 = accountBranchName3;
		}

		//第三保证金限额
		private Double grantAmt3 = 0.0;
		public Double getGrantAmt3(){
			return grantAmt3;
		}
		public void setGrantAmt3(Double grantAmt3){
			this.grantAmt3 = grantAmt3;
		}

		//第三还款账号类型
		private String accountType3;
		public String getAccountType3(){
			return accountType3;
		}
		public void setAccountType3(String accountType3){
			this.accountType3 = accountType3;
		}

		//第三圈存编号
		private String grantNo3;
		public String getGrantNo3(){
			return grantNo3;
		}
		public void setGrantNo3(String grantNo3){
			this.grantNo3 = grantNo3;
		}

		//第三保证金圈存金额
		private Double freezeGrantAmt3 = 0.0;
		public Double getFreezeGrantAmt3(){
			return freezeGrantAmt3;
		}
		public void setFreezeGrantAmt3(Double freezeGrantAmt3){
			this.freezeGrantAmt3 = freezeGrantAmt3;
		}

		//第四还款账号
		private String accountNo4;
		public String getAccountNo4(){
			return accountNo4;
		}
		public void setAccountNo4(String accountNo4){
			this.accountNo4 = accountNo4;
		}

		//第四还款账号户名
		private String accountName4;
		public String getAccountName4(){
			return accountName4;
		}
		public void setAccountName4(String accountName4){
			this.accountName4 = accountName4;
		}

		//第四还款账号开户机构
		private String accountBranchNo4;
		public String getAccountBranchNo4(){
			return accountBranchNo4;
		}
		public void setAccountBranchNo4(String accountBranchNo4){
			this.accountBranchNo4 = accountBranchNo4;
		}

		//第四还款账号开户机构名
		private String accountBranchName4;
		public String getAccountBranchName4(){
			return accountBranchName4;
		}
		public void setAccountBranchName4(String accountBranchName4){
			this.accountBranchName4 = accountBranchName4;
		}

		//第四保证金限额
		private Double grantAmt4 = 0.0;
		public Double getGrantAmt4(){
			return grantAmt4;
		}
		public void setGrantAmt4(Double grantAmt4){
			this.grantAmt4 = grantAmt4;
		}

		//第四还款账号类型
		private String accountType4;
		public String getAccountType4(){
			return accountType4;
		}
		public void setAccountType4(String accountType4){
			this.accountType4 = accountType4;
		}

		//第四圈存编号
		private String grantNo4;
		public String getGrantNo4(){
			return grantNo4;
		}
		public void setGrantNo4(String grantNo4){
			this.grantNo4 = grantNo4;
		}

		//第四保证金圈存金额
		private Double freezeGrantAmt4 = 0.0;
		public Double getFreezeGrantAmt4(){
			return freezeGrantAmt4;
		}
		public void setFreezeGrantAmt4(Double freezeGrantAmt4){
			this.freezeGrantAmt4 = freezeGrantAmt4;
		}

		//第五还款账号
		private String accountNo5;
		public String getAccountNo5(){
			return accountNo5;
		}
		public void setAccountNo5(String accountNo5){
			this.accountNo5 = accountNo5;
		}

		//第五还款账号户名
		private String accountName5;
		public String getAccountName5(){
			return accountName5;
		}
		public void setAccountName5(String accountName5){
			this.accountName5 = accountName5;
		}

		//第五还款账号开户机构
		private String accountBranchNo5;
		public String getAccountBranchNo5(){
			return accountBranchNo5;
		}
		public void setAccountBranchNo5(String accountBranchNo5){
			this.accountBranchNo5 = accountBranchNo5;
		}

		//第五还款账号开户机构名
		private String accountBranchName5;
		public String getAccountBranchName5(){
			return accountBranchName5;
		}
		public void setAccountBranchName5(String accountBranchName5){
			this.accountBranchName5 = accountBranchName5;
		}

		//第五保证金限额
		private Double grantAmt5 = 0.0;
		public Double getGrantAmt5(){
			return grantAmt5;
		}
		public void setGrantAmt5(Double grantAmt5){
			this.grantAmt5 = grantAmt5;
		}

		//第五还款账号类型
		private String accountType5;
		public String getAccountType5(){
			return accountType5;
		}
		public void setAccountType5(String accountType5){
			this.accountType5 = accountType5;
		}

		//第五圈存编号
		private String grantNo5;
		public String getGrantNo5(){
			return grantNo5;
		}
		public void setGrantNo5(String grantNo5){
			this.grantNo5 = grantNo5;
		}

		//第五保证金圈存金额
		private Double freezeGrantAmt5 = 0.0;
		public Double getFreezeGrantAmt5(){
			return freezeGrantAmt5;
		}
		public void setFreezeGrantAmt5(Double freezeGrantAmt5){
			this.freezeGrantAmt5 = freezeGrantAmt5;
		}

		//事业部
		private String busiDivision;
		public String getBusiDivision(){
			return busiDivision;
		}
		public void setBusiDivision(String busiDivision){
			this.busiDivision = busiDivision;
		}

		//部门编号
		private String deptNo;
		public String getDeptNo(){
			return deptNo;
		}
		public void setDeptNo(String deptNo){
			this.deptNo = deptNo;
		}

		//客户经理
		private String custManager;
		public String getCustManager(){
			return custManager;
		}
		public void setCustManager(String custManager){
			this.custManager = custManager;
		}

		//审核渠道
		private String auditChannel;
		public String getAuditChannel(){
			return auditChannel;
		}
		public void setAuditChannel(String auditChannel){
			this.auditChannel = auditChannel;
		}

		//贷款系统
		private String loanSystem;
		public String getLoanSystem(){
			return loanSystem;
		}
		public void setLoanSystem(String loanSystem){
			this.loanSystem = loanSystem;
		}

		//批次状态
		private String batchStatus;
		public String getBatchStatus(){
			return batchStatus;
		}
		public void setBatchStatus(String batchStatus){
			this.batchStatus = batchStatus;
		}

		//已扣金额
		private String deductAmt;
		public String getDeductAmt(){
			return deductAmt;
		}
		public void setDeductAmt(String deductAmt){
			this.deductAmt = deductAmt;
		}

		//贷款金额
		private String loanAmt;
		public String getLoanAmt(){
			return loanAmt;
		}
		public void setLoanAmt(String loanAmt){
			this.loanAmt = loanAmt;
		}

		//贷款日期
		private String loanDt;
		public String getLoanDt(){
			return loanDt;
		}
		public void setLoanDt(String loanDt){
			this.loanDt = loanDt;
		}

		//贷款时间
		private String loanTime;
		public String getLoanTime(){
			return loanTime;
		}
		public void setLoanTime(String loanTime){
			this.loanTime = loanTime;
		}

		//起息垫款日期
		private String loanStartDt;
		public String getLoanStartDt(){
			return loanStartDt;
		}
		public void setLoanStartDt(String loanStartDt){
			this.loanStartDt = loanStartDt;
		}

		//订单编号
		private String orderId;
		public String getOrderId(){
			return orderId;
		}
		public void setOrderId(String orderId){
			this.orderId = orderId;
		}

		//处理柜员
		private String processOperNo;
		public String getProcessOperNo(){
			return processOperNo;
		}
		public void setProcessOperNo(String processOperNo){
			this.processOperNo = processOperNo;
		}

		//处理日期
		private String processDt;
		public String getProcessDt(){
			return processDt;
		}
		public void setProcessDt(String processDt){
			this.processDt = processDt;
		}

		//处理时间
		private String processTime;
		public String getProcessTime(){
			return processTime;
		}
		public void setProcessTime(String processTime){
			this.processTime = processTime;
		}

		//复核柜员
		private String auditOperNo;
		public String getAuditOperNo(){
			return auditOperNo;
		}
		public void setAuditOperNo(String auditOperNo){
			this.auditOperNo = auditOperNo;
		}

		//复核日期
		private String auditDt;
		public String getAuditDt(){
			return auditDt;
		}
		public void setAuditDt(String auditDt){
			this.auditDt = auditDt;
		}

		//复核时间
		private String auditTime;
		public String getAuditTime(){
			return auditTime;
		}
		public void setAuditTime(String auditTime){
			this.auditTime = auditTime;
		}

		//记账柜员
		private String acctOperNo;
		public String getAcctOperNo(){
			return acctOperNo;
		}
		public void setAcctOperNo(String acctOperNo){
			this.acctOperNo = acctOperNo;
		}

		//记账日期
		private String accountDt;
		public String getAccountDt(){
			return accountDt;
		}
		public void setAccountDt(String accountDt){
			this.accountDt = accountDt;
		}

		//记账时间
		private String accountTime;
		public String getAccountTime(){
			return accountTime;
		}
		public void setAccountTime(String accountTime){
			this.accountTime = accountTime;
		}

		//记账前台流水号
		private String acctFlowNo;
		public String getAcctFlowNo(){
			return acctFlowNo;
		}
		public void setAcctFlowNo(String acctFlowNo){
			this.acctFlowNo = acctFlowNo;
		}

		//到期扣款柜员
		private String deductOperNo;
		public String getDeductOperNo(){
			return deductOperNo;
		}
		public void setDeductOperNo(String deductOperNo){
			this.deductOperNo = deductOperNo;
		}

		//到期扣款日期
		private String deductDt;
		public String getDeductDt(){
			return deductDt;
		}
		public void setDeductDt(String deductDt){
			this.deductDt = deductDt;
		}

		//到期扣款时间
		private String deductTime;
		public String getDeductTime(){
			return deductTime;
		}
		public void setDeductTime(String deductTime){
			this.deductTime = deductTime;
		}

		//维护柜员
		private String operNo;
		public String getOperNo(){
			return operNo;
		}
		public void setOperNo(String operNo){
			this.operNo = operNo;
		}

		//维护日期
		private String operDt;
		public String getOperDt(){
			return operDt;
		}
		public void setOperDt(String operDt){
			this.operDt = operDt;
		}

		//维护时间
		private String operTime;
		public String getOperTime(){
			return operTime;
		}
		public void setOperTime(String operTime){
			this.operTime = operTime;
		}

		//保证金比例
		private Double grantRatio = 0.0;
		public Double getGrantRatio(){
			return grantRatio;
		}
		public void setGrantRatio(Double grantRatio){
			this.grantRatio = grantRatio;
		}

		//贷款合同号
		private String loanNo;
		public String getLoanNo(){
			return loanNo;
		}
		public void setLoanNo(String loanNo){
			this.loanNo = loanNo;
		}

		//抵质押物编号
		private String pledgeNo;
		public String getPledgeNo(){
			return pledgeNo;
		}
		public void setPledgeNo(String pledgeNo){
			this.pledgeNo = pledgeNo;
		}

		//挂账机构名称
		private String suspdBranchName;
		public String getSuspdBranchName(){
			return suspdBranchName;
		}
		public void setSuspdBranchName(String suspdBranchName){
			this.suspdBranchName = suspdBranchName;
		}

		//挂账机构
		private String suspdBranchNo;
		public String getSuspdBranchNo(){
			return suspdBranchNo;
		}
		public void setSuspdBranchNo(String suspdBranchNo){
			this.suspdBranchNo = suspdBranchNo;
		}

		//移植来源
		private String yzSource;
		public String getYzSource(){
			return yzSource;
		}
		public void setYzSource(String yzSource){
			this.yzSource = yzSource;
		}

		//修改日期
		private String updateDt;
		public String getUpdateDt(){
			return updateDt;
		}
		public void setUpdateDt(String updateDt){
			this.updateDt = updateDt;
		}

		//修改时间
		private String updateTime;
		public String getUpdateTime(){
			return updateTime;
		}
		public void setUpdateTime(String updateTime){
			this.updateTime = updateTime;
		}

		//第一还款账号订单编号
		private String acctOrderId1;
		public String getAcctOrderId1(){
			return acctOrderId1;
		}
		public void setAcctOrderId1(String acctOrderId1){
			this.acctOrderId1 = acctOrderId1;
		}

		//第二还款账号订单编号
		private String acctOrderId2;
		public String getAcctOrderId2(){
			return acctOrderId2;
		}
		public void setAcctOrderId2(String acctOrderId2){
			this.acctOrderId2 = acctOrderId2;
		}

		//第三还款账号订单编号
		private String acctOrderId3;
		public String getAcctOrderId3(){
			return acctOrderId3;
		}
		public void setAcctOrderId3(String acctOrderId3){
			this.acctOrderId3 = acctOrderId3;
		}

		//第四还款账号订单编号
		private String acctOrderId4;
		public String getAcctOrderId4(){
			return acctOrderId4;
		}
		public void setAcctOrderId4(String acctOrderId4){
			this.acctOrderId4 = acctOrderId4;
		}

		//第五还款账号订单编号
		private String acctOrderId5;
		public String getAcctOrderId5(){
			return acctOrderId5;
		}
		public void setAcctOrderId5(String acctOrderId5){
			this.acctOrderId5 = acctOrderId5;
		}

		//还款订单编号
		private String refundOrderId;
		public String getRefundOrderId(){
			return refundOrderId;
		}
		public void setRefundOrderId(String refundOrderId){
			this.refundOrderId = refundOrderId;
		}

		//定期账户退款标志位
		private String refundFlag;
		public String getRefundFlag(){
			return refundFlag;
		}
		public void setRefundFlag(String refundFlag){
			this.refundFlag = refundFlag;
		}

		//交易合同编号
		private String conferNo;
		public String getConferNo(){
			return conferNo;
		}
		public void setConferNo(String conferNo){
			this.conferNo = conferNo;
		}
		
}
