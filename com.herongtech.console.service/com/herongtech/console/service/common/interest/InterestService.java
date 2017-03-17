package com.herongtech.console.service.common.interest;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import com.herongtech.commons.tools.StringUtils;
import com.herongtech.console.core.constant.IConstants;
import com.herongtech.console.core.constant.IDict;
import com.herongtech.console.core.util.DateTimeUtil;
import com.herongtech.console.domain.bean.DelayRule;
import com.herongtech.console.domain.common.bean.InterestReqByStringDTO;
import com.herongtech.console.domain.common.bean.InterestReqDTO;
import com.herongtech.console.domain.common.bean.InterestResultDTO;
import com.herongtech.console.service.ServiceFactory;
import com.herongtech.console.service.interfaces.IHolidayService;
import com.herongtech.console.service.interfaces.IInterestService;
import com.herongtech.exception.impl.BizAppException;


public class InterestService implements IInterestService{

    public InterestResultDTO getInterest(InterestReqDTO interestDTO)throws Exception{
        InterestResultDTO result=new InterestResultDTO();
        
        Long delayDays=interestDTO.getDelayDays();
        String chargeKind=interestDTO.getChargeKind();
        if(interestDTO.getProductNo()!=null && 
                interestDTO.getBillClass()!=null &&
                    interestDTO.getBillType()!=null &&
                        interestDTO.getBeginDate()!=null &&
                            interestDTO.getEndDate()!=null &&
                                interestDTO.getRate()!=null &&
                                    interestDTO.getAmount()!=null&&
                                        interestDTO.getRateType()!=null&&
                                            interestDTO.getIfSameCity()!=null){
            DelayRule delayRule=ServiceFactory.getDelayRuleService().getDelayRule(interestDTO.getProductNo(), interestDTO.getBillClass(), interestDTO.getBillType());
            if(delayRule!=null){
                Date galedate=interestDTO.getEndDate();//计息到期日
                if(IConstants.INTEREST.DELAY_AUTO_OPERTYPE.equals(String.valueOf(delayRule.getOperType()))||StringUtils.isEmpty(chargeKind)){
                    //自动执行
                    delayDays=delayRule.getDelayDays();
                    chargeKind=delayRule.getDelayType();
                }else if(IConstants.INTEREST.DELAY_DAYS_OPERTYPE.equals(String.valueOf(delayRule.getOperType()))){
                    //手动输入顺延天数
                    delayDays=delayRule.getDelayDays();
                    galedate =addDate(interestDTO.getEndDate(), delayDays.intValue());
                }else{
                    //手动输入，输入顺延规则
                    galedate=getGaleDateByRule(interestDTO.getEndDate(),chargeKind,delayDays,interestDTO.getIfSameCity());
                   
                }
                GregorianCalendar g1=new GregorianCalendar();
                g1.setTime(galedate);
              //票据起始日
               Date date2=interestDTO.getBeginDate();
               GregorianCalendar g2=new GregorianCalendar();
               g2.setTime(date2);
               //计息天数
               long daysoff =(g1.getTimeInMillis()-g2.getTimeInMillis())/(3600*24*1000);  
               //转化日利率时分母
               int baserate=10000;
               if (interestDTO.getRateType().equals(IConstants.INTEREST.MONTH_RATE_TYPE))
                   baserate=1000;
               if (interestDTO.getRateType().equals(IConstants.INTEREST.YEAR_RATE_TYPE))
                   baserate=100;
               
               BigDecimal interest=interestDTO.getAmount().multiply(interestDTO.getRate()).multiply(BigDecimal.valueOf(daysoff))
                       .divide(BigDecimal.valueOf(Double.valueOf(interestDTO.getRateType())).multiply(BigDecimal.valueOf(baserate)),2, BigDecimal.ROUND_HALF_UP);
               result.setGaleDate(galedate);
               result.setInterest(interest);
               result.setInterestCalDays(daysoff);
               
            }else{
                throw new BizAppException("顺延规则未初始化");
            }
            
        }else{
            throw new BizAppException("利息计算输入要素不全");
        }
        
        return result;
    }
    
    
     /**
      * 获取计息到期日
     * @param date 票面到期日
     * @param delayType 顺延方式
     * @param daysoff 异地顺延天数
     * @param ifSameCity 是否同意
     * @return
     * @throws Exception 
     */
    private Date getGaleDateByRule(Date date,String delayType,Long daysoff,String ifSameCity) throws Exception{
        Date delayDate=null;
            IHolidayService holidayService=(IHolidayService) ServiceFactory.getHolidayService();
            if(IConstants.INTEREST.DELAY_NO_CHARGE.equals(delayType)){
              //不顺延
                delayDate= date;
            }else if(IConstants.INTEREST.DELAY_HOLIDAY_CHARGE.equals(delayType)){//只顺延节假日
                delayDate=holidayService.getWorkdate(date);
            }else if(IConstants.INTEREST.DELAY_PLACE_CHARGE.equals(delayType)){//只顺延节异地
                if("1".equals(ifSameCity)){//同城不顺延
                    daysoff=0L;
                }
                delayDate=addDate(date, daysoff.intValue());
            }else if(IConstants.INTEREST.DELAY_HOLIDAY_PLACE_CHARGE.equals(delayType)){//3：先节假日再异地 
                if(ifSameCity.equals(IDict.K_YORN.K_YORN_YES))
                    daysoff=0L;
                
                delayDate=addDate(holidayService.getWorkdate(date),daysoff.intValue());
            }else if(IConstants.INTEREST.DELAY_PLACE_HOLIDAY_CHARGE.equals(delayType)){//4先异地再节假日  
                if(ifSameCity.equals(IDict.K_YORN.K_YORN_YES))
                    daysoff=0L;
                delayDate=holidayService.getWorkdate(addDate(date, daysoff.intValue()));
            }else if(IConstants.INTEREST.DELAY_DAYS_CHARGE.equals(delayType)){ //电票指定顺延天数
                int delayDays=daysoff.intValue();
                delayDate=addDate(date,delayDays);
            }
          
        return delayDate;   
     }
    
    
     //在原来时间上增加daysoff后的时间
    private Date addDate(Date date,int daysoff){
        GregorianCalendar g1=new GregorianCalendar();
        g1.setTime(date);
        g1.add(Calendar.DAY_OF_MONTH,daysoff);          
        return g1.getTime();
    }
    
    /**
     * 网银计算利息
    * @param InterestReqByStringDTO 日期为String类型的利息试算类
    * @return InterestResultDTO
    * @throws Exception 
    */
    public InterestResultDTO getIns(InterestReqByStringDTO interestDTO)throws Exception{
        InterestResultDTO result=new InterestResultDTO();
        Date beginDate=DateTimeUtil.parseStringToDate(interestDTO.getBeginDate());
        Date endDate=DateTimeUtil.parseStringToDate(interestDTO.getEndDate());
        Long delayDays=interestDTO.getDelayDays();
        String chargeKind=interestDTO.getChargeKind();
        if(beginDate!=null &&
        		endDate!=null &&
                    interestDTO.getRate()!=null &&
                        interestDTO.getAmount()!=null&&
                            interestDTO.getRateType()!=null&&
                                interestDTO.getIfSameCity()!=null){
        		Date galedate=endDate;//计息到期日
               //手动输入，输入顺延规则
               galedate=getGaleDateByRule(endDate,chargeKind,delayDays,interestDTO.getIfSameCity());
               GregorianCalendar g1=new GregorianCalendar();
               g1.setTime(galedate);
              //票据起始日
               Date date2=beginDate;
               GregorianCalendar g2=new GregorianCalendar();
               g2.setTime(date2);
               //计息天数
               long daysoff =(g1.getTimeInMillis()-g2.getTimeInMillis())/(3600*24*1000);  
               //转化日利率时分秒
               int baserate=10000;
               if (interestDTO.getRateType().equals(IConstants.INTEREST.MONTH_RATE_TYPE))
                   baserate=1000;
               if (interestDTO.getRateType().equals(IConstants.INTEREST.YEAR_RATE_TYPE))
                   baserate=100;
               
               BigDecimal interest=interestDTO.getAmount().multiply(interestDTO.getRate()).multiply(BigDecimal.valueOf(daysoff))
                       .divide(BigDecimal.valueOf(Double.valueOf(interestDTO.getRateType())).multiply(BigDecimal.valueOf(baserate)),2, BigDecimal.ROUND_HALF_UP);
               result.setGaleDate(galedate);
               result.setInterest(interest);
               result.setInterestCalDays(daysoff);
        }else{
            throw new BizAppException("利息计算输入要素不全");
        }
        
        return result;
    }
}
