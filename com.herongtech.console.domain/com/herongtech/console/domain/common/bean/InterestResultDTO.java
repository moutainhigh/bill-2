package com.herongtech.console.domain.common.bean;

import java.math.BigDecimal;
import java.util.Date;


/**
 * 利息计算结果
 *
 */
public class InterestResultDTO {
    /**
     * 顺延到期日
     */
    private Date galeDate;
        
    /**
     * 计息天数
     */
    private long interestCalDays;
        
    /**
     * 利息
     */
    private BigDecimal interest;

    
    public Date getGaleDate() {
        return galeDate;
    }

    
    public void setGaleDate(Date galeDate) {
        this.galeDate = galeDate;
    }

    
    public long getInterestCalDays() {
        return interestCalDays;
    }

    
    public void setInterestCalDays(long interestCalDays) {
        this.interestCalDays = interestCalDays;
    }

    
    public BigDecimal getInterest() {
        return interest;
    }

    
    public void setInterest(BigDecimal interest) {
        this.interest = interest;
    }
    
    
    
    
}
