/********************************************
 * 文件名称: Var106101.java
 * 系统名称: 票据管理平台V2.0
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: 2.0.0.1
 * 开发人员: yanjl
 * 开发时间: 20160716
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期    修改人员    修改说明
 *********************************************/
package com.herongtech.online.trans.trans106101;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.herongtech.console.domain.bean.TransPub;
import com.herongtech.console.service.busiservice.disc.DiscBillInfoBean;
import com.herongtech.data.interfaces.data.IData;
import com.herongtech.rc.domain.bean.RcBaseSearchBean;

/**
 * 客户信息查询
 * @author Administrator
 *
 */

public class Var106101 {

	public static final String  HUNDSUN_VERSION="@system  票据管理平台  @version 2.0.0.1  @lastModiDate @describe ";
	
	private String totNum;
	
	private String errNum;
	
	public String getTotNum() {
		return totNum;
	}

	public void setTotNum(String totNum) {
		this.totNum = totNum;
	}

	public String getErrNum() {
		return errNum;
	}

	public void setErrNum(String errNum) {
		this.errNum = errNum;
	}


	private List<DiscBillInfoBean> billList;
	
	
	
	public List<DiscBillInfoBean> getBillList() {
		return billList;
	}

	public void setBillList(List<DiscBillInfoBean> billList) {
		this.billList = billList;
	}


	private List<Var106101InfoBean> result;
	
	
	
	public List<Var106101InfoBean> getResult() {
		return result;
	}

	public void setResult(List<Var106101InfoBean> result) {
		this.result = result;
	}


		//公共对象
		TransPub transPub = null;
		
		public void setTransPub(TransPub transPub){
			this.transPub = transPub;
		}
		
		public TransPub getTransPub(){
			return transPub;
		}
		
	
	private ArrayList<Map<String, String>> resultList;
		
	public ArrayList<Map<String, String>> getResultList() {
		return resultList;
	}

	public void setResultList(ArrayList<Map<String, String>> resultList) {
		this.resultList = resultList;
	}

	/**
	 * 查询结果
	 */
	private IData resultData = null;
	
	/**
	 * 总记录数
	 */
	private int totRecNum = 0;
	

	/**
	 * 请求查询对象
	 */
	private RcBaseSearchBean rcSb = null;

	/**
	 * 
	 * @return IDataset
	 */
	public IData getResultData() {
		return resultData;
	}

	/**
	 * 
	 * @param resultDataSet
	 */
	public void setResultData(IData resultData) {
		this.resultData = resultData;
	}

	public int getTotRecNum() {
		return totRecNum;
	}

	public void setTotRecNum(int totRecNum) {
		this.totRecNum = totRecNum;
	}
	
	public RcBaseSearchBean getRcBaseSearchBean() {
		return rcSb;
	}

	public void setRcBaseSearchBean(RcBaseSearchBean rcSb) {
		this.rcSb = rcSb;
	}
	
	/**
	 * 入账帐号
	 */
	private String inAcctNo;
	
	/**
	 * 入账行号
	 */
	private String inBankNo;
	
	/**
	 * 申请人帐号
	 */
	private String applicantAcctNo;
	
	/**
	 * 电子签名
	 */
	private String signature;
	
	/**
	 * 票据ID
	 */
	private String rgctId;
	
	/**
	 * 贴入人账号
	 */
	private String receiverAcctNo;
	
	/**
	 * 贴入人行号
	 */
	private String receiverBankNo;
	
	/**
	 * 贴入人名称
	 */
	private String receiverName;
	
	/**
	 * 贴现方式
	 */
	private String discType;
	
	/**
	 * 贴现利率
	 */
	private String discRate;
	
	/**
	 * 应付金额
	 */
	private String discMoney;
	
	/**
	 * 赎回开放日
	 */
	private String rpdOpenDt;
	
	/**
	 * 赎回截止日
	 */
	private String rpdDueDt;
	
	/**
	 * 赎回利率
	 */
	private String rpdDiscRate;
	
	/**
	 * 赎回金额
	 */
	private String rpdDiscAmt;
	
	/**
	 * 线上清算
	 */
	private String settlementMark;
	
	/**
	 * 贴现申请日
	 */
	private String applyDate;
	
	/**
	 * 不得转让标记
	 */
	private String banEndorsementMark;
	
	private String batchNo;
	
	private String conferNo;
	
	private String invoiceNo;
	
	public String getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}

	public String getConferNo() {
		return conferNo;
	}

	public void setConferNo(String conferNo) {
		this.conferNo = conferNo;
	}

	public String getInvoiceNo() {
		return invoiceNo;
	}

	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	private String remark;

	public String getDiscType() {
		return discType;
	}

	public void setDiscType(String discType) {
		this.discType = discType;
	}

	public String getDiscRate() {
		return discRate;
	}

	public void setDiscRate(String discRate) {
		this.discRate = discRate;
	}

	public String getDiscMoney() {
		return discMoney;
	}

	public void setDiscMoney(String discMoney) {
		this.discMoney = discMoney;
	}

	public String getRpdOpenDt() {
		return rpdOpenDt;
	}

	public void setRpdOpenDt(String rpdOpenDt) {
		this.rpdOpenDt = rpdOpenDt;
	}

	public String getRpdDueDt() {
		return rpdDueDt;
	}

	public void setRpdDueDt(String rpdDueDt) {
		this.rpdDueDt = rpdDueDt;
	}

	public String getRpdDiscRate() {
		return rpdDiscRate;
	}

	public void setRpdDiscRate(String rpdDiscRate) {
		this.rpdDiscRate = rpdDiscRate;
	}

	public String getRpdDiscAmt() {
		return rpdDiscAmt;
	}

	public void setRpdDiscAmt(String rpdDiscAmt) {
		this.rpdDiscAmt = rpdDiscAmt;
	}

	public String getSettlementMark() {
		return settlementMark;
	}

	public void setSettlementMark(String settlementMark) {
		this.settlementMark = settlementMark;
	}

	public String getApplyDate() {
		return applyDate;
	}

	public void setApplyDate(String applyDate) {
		this.applyDate = applyDate;
	}

	public String getBanEndorsementMark() {
		return banEndorsementMark;
	}

	public void setBanEndorsementMark(String banEndorsementMark) {
		this.banEndorsementMark = banEndorsementMark;
	}

	public String getInAcctNo() {
		return inAcctNo;
	}

	public void setInAcctNo(String inAcctNo) {
		this.inAcctNo = inAcctNo;
	}

	public String getInBankNo() {
		return inBankNo;
	}

	public void setInBankNo(String inBankNo) {
		this.inBankNo = inBankNo;
	}

	public String getApplicantAcctNo() {
		return applicantAcctNo;
	}

	public void setApplicantAcctNo(String applicantAcctNo) {
		this.applicantAcctNo = applicantAcctNo;
	}

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getRgctId() {
		return rgctId;
	}

	public void setRgctId(String rgctId) {
		this.rgctId = rgctId;
	}

	public String getReceiverAcctNo() {
		return receiverAcctNo;
	}

	public void setReceiverAcctNo(String receiverAcctNo) {
		this.receiverAcctNo = receiverAcctNo;
	}

	public String getReceiverBankNo() {
		return receiverBankNo;
	}

	public void setReceiverBankNo(String receiverBankNo) {
		this.receiverBankNo = receiverBankNo;
	}

	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}
	
}
