package com.herongtech.rc.draft.common.vo;

import java.util.Date;
import java.util.List;

import com.herongtech.rc.domain.bean.RgctBillInfo;


/**
 * 流转类、出票类、信息类报文构造/解析对象
 * 背书历史对象
 */
public class DraftInfoVo extends RequestInfo{
    
    
    
    private String busiType;        //业务类型 (以3位报文编号作为业务类型)
    
    private String rpdMk;           //回购标记
    private String sgnUpMk;         //回复标记
    private Date applyDt;           //申请日期
    private Date signDt;            //签收日期
    private String trfRef;          //特许参与者号码（036）
    private String trfId;           //支付交易序号（036）
    
    
    private String settleMk;        //线上清算标记
    private String banEndrsmtMk;    //禁止背书标记
    private Double intrstRate;  //利率（贴现/转贴现 赎回申请）
    private Double amt;         //实付金额
    private Date rpdOpenDt;         //赎回开放日
    private Date rpdDueDt;          //赎回截止日
    private Double rpdIntrstRate;  //赎回利率
    private Double rpdAmt;         //赎回实付金额
    private String inAcct;          //入账账号
    private String inBankNo;        //入账行号
    private String txlCtrctNb;      //交易合同编号
    private String invcNb;          //发票号码
    private String BtchNb;          //批次号
    private String accptncAgrmtNb;  //承兑协议编号
    
//    private String remark;
    private String overdueReason;   //逾期原因说明
    private String overdueMark;     //逾期标记
    private String prxyPropstn;     //代理申请标示
    
    private String rejectCode;      //拒付代码
    private String rejectReason;    //拒付理由
    private String guarnteeAddr;    //保证人地址
    
    private String recourseType;    //追索类型
    private String recourseAmt;     //追索金额
    private String recourseReasonCode;    //追索理由代码
    private String drwgBckDt;	//未用退回日期
    private String dscntAgrmtNb;	//贴现协议编号
    private String discDt;	//贴现日期
    private String reDiscDt;	//转贴现日期
    private String reDiscDtCen;	//再贴现日期
    private String collDt;	//质押日期
    private String repCollDt;	//解质押日期
    private String subCollDt;	//委托收款日期
    private String endorNum;	//背书次数
    private String settleDt;	//结清日期
    private String payRefuseDt;	//拒付日期
    private String payRefuseReason;	//拒付理由
    private String suspendPayType;	//止付类型
    private String suspendPayDt;	//申请止付日期
    private String annulSuspendPayType;	//止付解除类型
    private String annulSuspendPayDt;	//止付解除日期
    
    private RgctBillInfo bill;      //票面信息
    
    private List<DraftInfoVo> endorseHist;  //背书历史
    
   
    
    public String getBusiType() {
        return busiType;
    }
    
    public void setBusiType(String busiType) {
        this.busiType = busiType;
    }
    
    public String getRpdMk() {
        return rpdMk;
    }
    
    public void setRpdMk(String rpdMk) {
        this.rpdMk = rpdMk;
    }
    
    public String getSgnUpMk() {
        return sgnUpMk;
    }
    
    public void setSgnUpMk(String sgnUpMk) {
        this.sgnUpMk = sgnUpMk;
    }
    
    public Date getApplyDt() {
        return applyDt;
    }
    
    public void setApplyDt(Date applyDt) {
        this.applyDt = applyDt;
    }
    
    public Date getSignDt() {
        return signDt;
    }
    
    public void setSignDt(Date signDt) {
        this.signDt = signDt;
    }

    public String getSettleMk() {
        return settleMk;
    }
    
    public void setSettleMk(String settleMk) {
        this.settleMk = settleMk;
    }
    
    public String getBanEndrsmtMk() {
        return banEndrsmtMk;
    }
    
    public void setBanEndrsmtMk(String banEndrsmtMk) {
        this.banEndrsmtMk = banEndrsmtMk;
    }
    
    public Double getIntrstRate() {
        return intrstRate;
    }
    
    public void setIntrstRate(Double intrstRate) {
        this.intrstRate = intrstRate;
    }
    
    public Double getAmt() {
        return amt;
    }
    
    public void setAmt(Double amt) {
        this.amt = amt;
    }
    
    public Date getRpdOpenDt() {
        return rpdOpenDt;
    }
    
    public void setRpdOpenDt(Date rpdOpenDt) {
        this.rpdOpenDt = rpdOpenDt;
    }
    
    public Date getRpdDueDt() {
        return rpdDueDt;
    }
    
    public void setRpdDueDt(Date rpdDueDt) {
        this.rpdDueDt = rpdDueDt;
    }
    
    public Double getRpdIntrstRate() {
        return rpdIntrstRate;
    }
    
    public void setRpdIntrstRate(Double rpdIntrstRate) {
        this.rpdIntrstRate = rpdIntrstRate;
    }
    
    public Double getRpdAmt() {
        return rpdAmt;
    }
    
    public void setRpdAmt(Double rpdAmt) {
        this.rpdAmt = rpdAmt;
    }
    
    public String getInAcct() {
        return inAcct;
    }
    
    public void setInAcct(String inAcct) {
        this.inAcct = inAcct;
    }
    
    public String getInBankNo() {
        return inBankNo;
    }
    
    public void setInBankNo(String inBankNo) {
        this.inBankNo = inBankNo;
    }
    
    public String getTxlCtrctNb() {
        return txlCtrctNb;
    }
    
    public void setTxlCtrctNb(String txlCtrctNb) {
        this.txlCtrctNb = txlCtrctNb;
    }
    
    public String getInvcNb() {
        return invcNb;
    }
    
    public void setInvcNb(String invcNb) {
        this.invcNb = invcNb;
    }
    
    public String getBtchNb() {
        return BtchNb;
    }
    
    public void setBtchNb(String btchNb) {
        BtchNb = btchNb;
    }
    
    public String getOverdueReason() {
        return overdueReason;
    }
    
    public void setOverdueReason(String overdueReason) {
        this.overdueReason = overdueReason;
    }
    
    public String getOverdueMark() {
        return overdueMark;
    }
    
    public void setOverdueMark(String overdueMark) {
        this.overdueMark = overdueMark;
    }
    
    public String getPrxyPropstn() {
        return prxyPropstn;
    }
    
    public void setPrxyPropstn(String prxyPropstn) {
        this.prxyPropstn = prxyPropstn;
    }
    
    public String getRejectCode() {
        return rejectCode;
    }
    
    public void setRejectCode(String rejectCode) {
        this.rejectCode = rejectCode;
    }
    
    public String getRejectReason() {
        return rejectReason;
    }
    
    public void setRejectReason(String rejectReason) {
        this.rejectReason = rejectReason;
    }
    
    public String getGuarnteeAddr() {
        return guarnteeAddr;
    }
    
    public void setGuarnteeAddr(String guarnteeAddr) {
        this.guarnteeAddr = guarnteeAddr;
    }
    
    public String getRecourseType() {
        return recourseType;
    }
    
    public void setRecourseType(String recourseType) {
        this.recourseType = recourseType;
    }
    
    public String getRecourseAmt() {
        return recourseAmt;
    }
    
    public void setRecourseAmt(String recourseAmt) {
        this.recourseAmt = recourseAmt;
    }
    
    public String getRecourseReasonCode() {
        return recourseReasonCode;
    }
    
    public void setRecourseReasonCode(String recourseReasonCode) {
        this.recourseReasonCode = recourseReasonCode;
    }

    
    public RgctBillInfo getBill() {
        return bill;
    }

    
    public void setBill(RgctBillInfo bill) {
        this.bill = bill;
    }

    
    public List<DraftInfoVo> getEndorseHist() {
        return endorseHist;
    }

    
    public void setEndorseHist(List<DraftInfoVo> endorseHist) {
        this.endorseHist = endorseHist;
    }
    
    public String getTrfRef() {
        return trfRef;
    }

    
    public void setTrfRef(String trfRef) {
        this.trfRef = trfRef;
    }

    
    public String getTrfId() {
        return trfId;
    }

    
    public void setTrfId(String trfId) {
        this.trfId = trfId;
    }

    
    public String getAccptncAgrmtNb() {
        return accptncAgrmtNb;
    }

    
    public void setAccptncAgrmtNb(String accptncAgrmtNb) {
        this.accptncAgrmtNb = accptncAgrmtNb;
    }

	public String getDrwgBckDt() {
		return drwgBckDt;
	}

	public void setDrwgBckDt(String drwgBckDt) {
		this.drwgBckDt = drwgBckDt;
	}

	public String getDscntAgrmtNb() {
		return dscntAgrmtNb;
	}

	public void setDscntAgrmtNb(String dscntAgrmtNb) {
		this.dscntAgrmtNb = dscntAgrmtNb;
	}

	public String getDiscDt() {
		return discDt;
	}

	public void setDiscDt(String discDt) {
		this.discDt = discDt;
	}

	public String getReDiscDt() {
		return reDiscDt;
	}

	public void setReDiscDt(String reDiscDt) {
		this.reDiscDt = reDiscDt;
	}

	public String getReDiscDtCen() {
		return reDiscDtCen;
	}

	public void setReDiscDtCen(String reDiscDtCen) {
		this.reDiscDtCen = reDiscDtCen;
	}

	public String getCollDt() {
		return collDt;
	}

	public void setCollDt(String collDt) {
		this.collDt = collDt;
	}

	public String getRepCollDt() {
		return repCollDt;
	}

	public void setRepCollDt(String repCollDt) {
		this.repCollDt = repCollDt;
	}

	public String getSubCollDt() {
		return subCollDt;
	}

	public void setSubCollDt(String subCollDt) {
		this.subCollDt = subCollDt;
	}

	public String getEndorNum() {
		return endorNum;
	}

	public void setEndorNum(String endorNum) {
		this.endorNum = endorNum;
	}

	public String getSettleDt() {
		return settleDt;
	}

	public void setSettleDt(String settleDt) {
		this.settleDt = settleDt;
	}

	public String getPayRefuseDt() {
		return payRefuseDt;
	}

	public void setPayRefuseDt(String payRefuseDt) {
		this.payRefuseDt = payRefuseDt;
	}

	public String getPayRefuseReason() {
		return payRefuseReason;
	}

	public void setPayRefuseReason(String payRefuseReason) {
		this.payRefuseReason = payRefuseReason;
	}

	public String getSuspendPayType() {
		return suspendPayType;
	}

	public void setSuspendPayType(String suspendPayType) {
		this.suspendPayType = suspendPayType;
	}

	public String getSuspendPayDt() {
		return suspendPayDt;
	}

	public void setSuspendPayDt(String suspendPayDt) {
		this.suspendPayDt = suspendPayDt;
	}

	public String getAnnulSuspendPayType() {
		return annulSuspendPayType;
	}

	public void setAnnulSuspendPayType(String annulSuspendPayType) {
		this.annulSuspendPayType = annulSuspendPayType;
	}

	public String getAnnulSuspendPayDt() {
		return annulSuspendPayDt;
	}

	public void setAnnulSuspendPayDt(String annulSuspendPayDt) {
		this.annulSuspendPayDt = annulSuspendPayDt;
	}
	
	

    
    
    
}
