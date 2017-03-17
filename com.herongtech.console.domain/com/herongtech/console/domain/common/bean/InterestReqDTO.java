package com.herongtech.console.domain.common.bean;

import java.math.BigDecimal;
import java.util.Date;


public class InterestReqDTO {

  //起息日
    private Date beginDate;
    //票面到期日
    private Date endDate;
    //票面金额
    private BigDecimal amount;
    //利率
    private BigDecimal rate;
    //1承兑 2贴现 3转入贴现
    private String productNo;
    //1：实物,2：电子
    private String billClass;
    //1：银票，2：商票
    private String billType;
    //如果使用数据库中的异地顺延天数，不用设置该值，否则给该变量赋值
    private Long delayDays;
    //顺延规则 0:不顺延 1：只顺延节假日 2：只顺延异地 3：先节假日再异地 4先异地再节假日    
    private String chargeKind;
    //利率类型 1：日 30：月 360：年
    private String rateType;
    //0异地 1同城
    private String ifSameCity;
    //调整金额 1-10分
    private Integer adjustMoney;
    
    public Date getBeginDate() {
        return beginDate;
    }
    
    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }
    
    public Date getEndDate() {
        return endDate;
    }
    
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    
    
    public BigDecimal getAmount() {
        return amount;
    }

    
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    
    public BigDecimal getRate() {
        return rate;
    }

    
    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public String getProductNo() {
        return productNo;
    }
    
    public void setProductNo(String productNo) {
        this.productNo = productNo;
    }
    
    public String getBillClass() {
        return billClass;
    }
    
    public void setBillClass(String billClass) {
        this.billClass = billClass;
    }
    
    public String getBillType() {
        return billType;
    }
    
    public void setBillType(String billType) {
        this.billType = billType;
    }
    

    
    public Long getDelayDays() {
        return delayDays;
    }

    
    public void setDelayDays(Long delayDays) {
        this.delayDays = delayDays;
    }

    
    public String getChargeKind() {
        return chargeKind;
    }

    
    public void setChargeKind(String chargeKind) {
        this.chargeKind = chargeKind;
    }

    public String getRateType() {
        return rateType;
    }
    
    public void setRateType(String rateType) {
        this.rateType = rateType;
    }
    
    public String getIfSameCity() {
        return ifSameCity;
    }
    
    public void setIfSameCity(String ifSameCity) {
        this.ifSameCity = ifSameCity;
    }
    
    public Integer getAdjustMoney() {
        return adjustMoney;
    }
    
    public void setAdjustMoney(Integer adjustMoney) {
        this.adjustMoney = adjustMoney;
    }
    
}
