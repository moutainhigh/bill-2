package com.herongtech.console.domain.subcoll.bean;

import com.herongtech.console.core.common.search.BaseSearchBean;

/**
 * 托收中间类
 * @author Administrator
 *
 */
public class SubcollQueryCondition extends BaseSearchBean {
        private Object[] ids;//托收明细ID
        
		//托收流水号（批次id）
		private String subcollId;
		//客户账号
		private String belongCustAcct;
		//客户号
		private String belongCustNo;
		//客户名
		private String custName;
		//当前状态
		private String operStatus;
		
		private Object [] statusArray;
		
		//查询起始日期
	  	private String startDay;
	  	//排序
	  	private String orderBy;
	  	//票面到期日
	  	private String dueDt;
	  	//操作机构
	  	private String branchNo;
	  	
	  	//申请提交柜员
	  	private String applyOperNo;
	  	
	  	private String gathDate;//记账日期
		public String getGathDate(){
			return gathDate;
		}
		public void setGathDate(String gathDate){
			this.gathDate = gathDate;
		}
		public String getApplyOperNo() {
			return applyOperNo;
		}
		public void setApplyOperNo(String applyOperNo) {
			this.applyOperNo = applyOperNo;
		}
		public String getDueDt() {
			return dueDt;
		}
		public void setDueDt(String dueDt) {
			this.dueDt = dueDt;
		}
		

		//EMS
		private String EMS;
		public String getEMS() {
			return EMS;
		}
		public void setEMS(String eMS) {
			EMS = eMS;
		}
		
		//入账账号
		private String inAcctNo;
		public String getInAcctNo() {
			return inAcctNo;
		}
		public void setInAcctNo(String inAcctNo) {
			this.inAcctNo = inAcctNo;
		}

		//入账行号
		private String inBankNo;
		public String getInBankNo() {
			return inBankNo;
		}
		public void setInBankNo(String inBankNo) {
			this.inBankNo = inBankNo;
		}


		//持票人行名
		private String fromBankName;
		public String getFromBankName() {
			return fromBankName;
		}
		public void setFromBankName(String fromBankName) {
			this.fromBankName = fromBankName;
		}
		
		//持票人行号
		private String fromBankNo;
		public String getFromBankNo() {
			return fromBankNo;
		}
		public void setFromBankNo(String fromBankNo) {
			this.fromBankNo = fromBankNo;
		}

		//托收人行名
		private String toBankName;
		public String getToBankName() {
			return toBankName;
		}
		public void setToBankName(String toBankName) {
			this.toBankName = toBankName;
		}

		//托收人行号
		private String toBankNo;
		public String getToBankNo() {
			return toBankNo;
		}
		public void setToBankNo(String toBankNo) {
			this.toBankNo = toBankNo;
		}
		
		//托收人地址
		private String toBankAddress;
		public String getToBankAddress() {
			return toBankAddress;
		}
		public void setToBankAddress(String toBankAddress) {
			this.toBankAddress = toBankAddress;
		}

		//查询结束日期
	  	private String endDay;
	  	//票据种类
	  	private String billType;
	  	//票据类型
	  	private String billClass;
	  	
		//票号
		private String billNo;
		
		//最小票据金额
		private Double smallMoney;
		//最大票据金额
		private Double bigMoney;
		//票据金额
		private Double billMoney;
		//承兑行行号
		private String acceptorBankNo;
		//承兑人
		private String acceptor;
		//备注
		private String remark;
		//逾期说明
		private String overdueReason;
		public String getIsOverdue() {
			return isOverdue;
		}
		public void setIsOverdue(String isOverdue) {
			this.isOverdue = isOverdue;
		}

		//是否逾期:1-否,2-是
		private String isOverdue;
		//操作柜员
		private String operNo;
		public String getOperNo() {
			return operNo;
		}
		public void setOperNo(String operNo) {
			this.operNo = operNo;
		}
		//线上清算标识 :0-线下，1-线上
		private String isOnline;
		public String getIsOnline(){
			return isOnline;
		}
		public void setIsOnline(String isOnline){
			this.isOnline = isOnline;
		}
		//登陆者行号
		private String BrchBankNo;

		public String getBrchBankNo() {
			return BrchBankNo;
		}
		public void setBrchBankNo(String brchBankNo) {
			BrchBankNo = brchBankNo;
		}

		//状态
		private String status;
		public String getStatus(){
			return status;
		}
		public void setStatus(String status){
			this.status = status;
		}
		//承兑人开户行行名
		private String acceptorBankName;
		public String getAcceptorBankName() {
			return acceptorBankName;
		}
		public void setAcceptorBankName(String acceptorBankName) {
			this.acceptorBankName = acceptorBankName;
		}
		public String getOverdueReason() {
			return overdueReason;
		}
		public void setOverdueReason(String overdueReason) {
			this.overdueReason = overdueReason;
		}
		public String getRemark() {
			return remark;
		}
		public void setRemark(String remark) {
			this.remark = remark;
		}
		public String getAcceptor() {
			return acceptor;
		}
		public void setAcceptor(String acceptor) {
			this.acceptor = acceptor;
		}
		public String getAcceptorBankNo(){
			return acceptorBankNo;
		}
		public void setAcceptorBankNo(String acceptorBankNo){
			this.acceptorBankNo = acceptorBankNo;
		}
		

		public Double getSmallMoney() {
			return smallMoney;
		}
		public void setSmallMoney(Double smallMoney) {
			this.smallMoney = smallMoney;
		}
		public Double getBigMoney() {
			return bigMoney;
		}
		public void setBigMoney(Double bigMoney) {
			this.bigMoney = bigMoney;
		}
		public Double getBillMoney() {
			return billMoney;
		}
		public void setBillMoney(Double billMoney) {
			this.billMoney = billMoney;
		}
		public String getBillNo() {
			return billNo;
		}
		public void setBillNo(String billNo) {
			this.billNo = billNo;
		}
	  	
        public Object [] getStatusArray() {
            return statusArray;
        }
        
        public void setStatusArray(Object [] statusArray) {
            this.statusArray = statusArray;
        }
        public String getOrderBy() {
			return orderBy;
		}
		public void setOrderBy(String orderBy) {
			this.orderBy = orderBy;
		}
		public String getStartDay() {
			return startDay;
		}
		public void setStartDay(String startDay) {
			this.startDay = startDay;
		}
		public String getEndDay() {
			return endDay;
		}
		public void setEndDay(String endDay) {
			this.endDay = endDay;
		}
		public String getBillType() {
			return billType;
		}
		public void setBillType(String billType) {
			this.billType = billType;
		}
		public String getBillClass() {
			return billClass;
		}
		public void setBillClass(String billClass) {
			this.billClass = billClass;
		}
		
		public String getSubcollId() {
			return subcollId;
		}
		public void setSubcollId(String subcollId) {
			this.subcollId = subcollId;
		}
		public String getBelongCustAcct() {
			return belongCustAcct;
		}
		public void setBelongCustAcct(String belongCustAcct) {
			this.belongCustAcct = belongCustAcct;
		}
		public String getBelongCustNo() {
			return belongCustNo;
		}
		public void setBelongCustNo(String belongCustNo) {
			this.belongCustNo = belongCustNo;
		}
		public String getCustName() {
			return custName;
		}
		public void setCustName(String custName) {
			this.custName = custName;
		}
		public String getOperStatus() {
			return operStatus;
		}
		public void setOperStatus(String operStatus) {
			this.operStatus = operStatus;
		}
        
        public Object[] getIds() {
            return ids;
        }
        
        public void setIds(Object[] ids) {
            this.ids = ids;
        }
		public String getBranchNo() {
			return branchNo;
		}
		public void setBranchNo(String branchNo) {
			this.branchNo = branchNo;
		}
		
}
