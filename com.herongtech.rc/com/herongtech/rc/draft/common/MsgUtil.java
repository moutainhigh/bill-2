package com.herongtech.rc.draft.common;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

import org.apache.log4j.Logger;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlError;
import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.XmlOptions;

import com.herongtech.console.core.constant.RcConstants;
import com.herongtech.console.service.ServiceFactory;
import com.herongtech.console.service.interfaces.IBranchService;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.rc.domain.bean.EcdsBankData;
import com.herongtech.rc.service.RcServiceFactory;
import com.herongtech.rc.service.interfaces.IEcdsBankDataService;

public class MsgUtil {
    
	/**
     * 报文时间
     * 
     * @param dtStr
     * @param format
     * @return
     */
    public static Calendar getCurrentTime(){
        return getConverTime(new Date());
    }
    
    public static Calendar getConverTime(Date date){
        Calendar c=Calendar.getInstance();
        c.setTime(date);
        c.clear(14);
        c.clear(15);
        return c;
    }
    
    public static Date convertToDate(Calendar cal){
        if(cal == null)
            return null;
        return cal.getTime();
    }
    
    public static String convertToString(Calendar cal){
        if(cal == null)
            return null;
        return cal.toString();
    }

    /**
     * 字符串格式时间转化为时间类型
     * 
     * @param dtStr
     * @param format
     * @return
     */
    public static Date converISODateTime(String dateStr, String formatStr) {
        if (dateStr == null)
            return null;
        String reg = dateStr;
        if (formatStr == null || "".equals(formatStr)) {
            reg = "yyyy-MM-dd'T'HH:mm:ss";
            if (dateStr.length() == 10) {
                reg = "yyyy-MM-dd";
            } else if (dateStr.length() == 8) {
                reg = "yyyyMMdd";
            }
        }
        SimpleDateFormat sdf = new SimpleDateFormat(reg);
        Date date = null;
        try {
            date = sdf.parse(dateStr);
        } catch (ParseException e) {
            Logger.getLogger(MsgUtil.class).error("转换时间出错,dateStr:" + dateStr + ",formatStr:" + formatStr, e);
        }
        return date;
    }
    
    /**
     * Date类型   转化为yyyy-MM-dd字符串
     * 
     * @param date
     * @return
     */
    public static String getISODate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String formatedDate = "";
        if (date != null) {
            formatedDate = sdf.format(date);
        }
        return formatedDate;
    }

    /**
     * DATE类型转化为yyyy-MM-dd'T'HH:mm:ss的字符串
     * 
     * @return
     */
    public static String converISODateTime(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        String creDtTm = "";
        if (date != null) {
            creDtTm = sdf.format(date);
        }
        return creDtTm;
    }

    /**
     * 时间转化
     * 
     * @param dtStr
     *            yyyy-MM-dd'T'HH:mm:ss or yyyy-MM-dd
     * @return
     */
    public static Date converISODateTime(String dtStr) {
        if (dtStr == null)
            return null;
        String reg = "yyyy-MM-dd'T'HH:mm:ss";
        if (dtStr.length() == 10)
            reg = "yyyy-MM-dd";
        SimpleDateFormat sdf = new SimpleDateFormat(reg);
        Date d = null;
        try {
            d = sdf.parse(dtStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return d;
    }

    
    
    

    /**
     * 票据金额格式(CurrencyAndAmount)的转换
     * 
     * @param money
     * @return
     */
    public static String getCurrencyAndAmount(Double money) {
        java.text.DecimalFormat decimalFormat = new java.text.DecimalFormat("#0.00");
        String formatedMoney = "";
        if (money != null) {
            formatedMoney = decimalFormat.format(money);
        }
        return formatedMoney;
    }

    /**
     * 票据利率格式(PercentageRate)的转换
     * 
     * @param money
     * @return
     */
    public static String getPercentageRate(Double rate) {
        java.text.DecimalFormat decimalFormat = new java.text.DecimalFormat("#0.000000");
        String formatedRate = "";
        if (rate != null) {
            formatedRate = decimalFormat.format(rate);
        }
        return formatedRate;
    }
    
    /**
     * String转Calendar
     * 
     * @param Calendar
     * @return
     */
    public static Calendar StringtoCalendar(String str){
    	if(str!=null && !"".equals(str) && !" ".equals(str)){
    		Date date = MsgUtil.converISODateTime(str);
    		Calendar calendar = MsgUtil.getConverTime(date);
    		return calendar;
    	}else{
    		return null;
    	}
    }
    
    

    
    public static String getMethodName(String busiType, String isRegress) {
        if (busiType == null) {
            return "";
        }
        if (busiType.equals("001"))
            return RcConstants.COMMON_REGISTER;
        if (busiType.equals("002") || busiType.equals("101"))
            return RcConstants.COMMON_ACCEPTOR;
        if (busiType.equals("003"))
            return RcConstants.COMMON_ISSURANCE;
        if (busiType.equals("004") || busiType.equals("125"))
            return RcConstants.COMMON_REBACK;
        if (busiType.equals("010"))
            return RcConstants.COMMON_ENDORSE;
        if (busiType.equals("011") || busiType.equals("102")) {
            if ("1".equals(isRegress))
                return RcConstants.COMMON_DISCOUNT2;
            else
                return RcConstants.COMMON_DISCOUNT1;
        }
        if (busiType.equals("012"))
            return RcConstants.COMMON_DISCOUNT_BACK;
        if (busiType.equals("013")) {
            if ("1".equals(isRegress) || busiType.equals("103"))
                return RcConstants.COMMON_REDISCOUNT2;
            else
                return RcConstants.COMMON_REDISCOUNT1;
        }
        if (busiType.equals("014"))
            return RcConstants.COMMON_REDISCOUNT_BACK;
        if (busiType.equals("015")) {
            if ("1".equals(isRegress) || busiType.equals("104"))
                return RcConstants.COMMON_REDISCOUNT_CENTER2;
            else
                return RcConstants.COMMON_REDISCOUNT_CENTER1;
        }
        if (busiType.equals("016"))
            return RcConstants.COMMON_REDISCOUNT_CENTER_BACK;
        if (busiType.equals("025"))
            return RcConstants.COMMON_CENTER_SALE;
        if (busiType.equals("017"))
            return RcConstants.COMMON_GUARNT;
        if (busiType.equals("018") || busiType.equals("105"))
            return RcConstants.COMMON_COLLECT;
        if (busiType.equals("019") || busiType.equals("106"))
            return RcConstants.COMMON_UNCOLLECT;
        if (busiType.equals("020"))
            return RcConstants.COMMON_PRESENTATION;
        if (busiType.equals("021"))
            return RcConstants.COMMON_OVERDUE;
        if (busiType.equals("022")) {
            if ("1".equals(isRegress))
                return RcConstants.COMMON_RECOURSE2;//
            else
                return RcConstants.COMMON_RECOURSE1;//
        }
        if (busiType.equals("023")) {
            if ("1".equals(isRegress))
                return RcConstants.COMMON_RECOURSEAGREEMENT2;
            else
                return RcConstants.COMMON_RECOURSEAGREEMENT1;
        }
        return "";

    }
    
    public static String getBusiType(String methodName,String param) {
        if (methodName == null) {
            return "";
        }
        if (methodName.equals("acptSign") || methodName.equals("acptBack"))
            return "002";
        if (methodName.equals("payeeSign") || methodName.equals("payeeReject"))
            return "003";
        
        if (methodName.equals("signEndorse") || methodName.equals("rejectEndorse"))
            return "010";
        if (methodName.equals("discountSign") || methodName.equals("rejectSignBuy")) {
            return "011";
        }
        if (methodName.equals("rebuyEndorseSign") || methodName.equals("rejectRebuyEndorse")) {
        	if("1".equals(param)){
        		return "015";
        	}else{
        		return "013";
        	}
        }
        if (methodName.equals("buyBackSign") || methodName.equals("rejectSaleBackEndorse")) {
        	if("1".equals(param)){
        		return "016";
        	}else{
        		return "014";
        	}
        }
        if (methodName.equals("assuSign") || methodName.equals("registerAssuReject")) {
            return "017";
        }
        if (methodName.equals("rejectSignImpawn") || methodName.equals("impawnSign")) {
            return "018";
        }
        if (methodName.equals("signUnimpawn") || methodName.equals("rejectSignUnimpawn")) {
            return "019";
        }
        if (methodName.equals("collectSign") || methodName.equals("rejectCollect")) {
            return "020";
        }
        if (methodName.equals("signOverdue") || methodName.equals("overduePresentationNo")) {
            return "021";
        }
        return "";

    }
    
    /**
     * 报文标识_报文标识号MsgId_Id 格式：行号（12位）+日期（8位，如20070101）+当日序号（8位，如00000001）
     * 
     * @return
     * @throws Exception 
     */
    public static String getMsgId(String draftNo, String bankNo) throws Exception {
        if("035,051,065,072,074,076,078".indexOf(draftNo)>0){
            bankNo = "00" + getMeetIncomeCode(bankNo);
        }
        String id = bankNo + ServiceFactory.getSequenceService().getMsgIdSequence();
        return id;
    }
    
    /**
     * 根据行号得到接入点号
     * 
     * @param bankNo
     * @return
     * @throws Exception 
     */
    public static String getMeetIncomeCode(String bankNo) throws Exception {
        String meetIncomeCode = "";
        IEcdsBankDataService ecdsbankService=RcServiceFactory.getEcdsBankDataService();
        EcdsBankData ecdsBankData = ecdsbankService.getEcdsBankData(bankNo);
        if (ecdsBankData == null) {
            throw new BizAppException("ECDS基础数据表EcdsBankData中没有银行号[" + bankNo + "]对应的接入点号.");
        }
        meetIncomeCode = ecdsBankData.getMeetIncomeCode();
        if (meetIncomeCode == null || "".equals(meetIncomeCode)) {
            throw new BizAppException("ECDS基础数据表EcdsBankData中银行号[" + bankNo + "]对应的接入点号为空.");
        }
        return meetIncomeCode;
    }
    
    public static void validator(XmlObject xml,XmlOptions options)throws BizAppException{
        ArrayList<XmlError> validationErrors = new ArrayList<XmlError>();
        options.setErrorListener(validationErrors);
        boolean isValid = xml.validate(options);
        String message="Schema验证不通过：";
        if (!isValid)
        {
            Iterator<XmlError> iter = validationErrors.iterator();
            while (iter.hasNext())
            {
                XmlError err=iter.next();
                XmlCursor cursor=err.getCursorLocation();
                message+=cursor.getDomNode().getLocalName()+":"+err.getMessage()+"\n";
            }
            throw new BizAppException(message);
        }
    }
    
	public static boolean isSelfBank(String bankNo) throws Exception {
		if (bankNo == null || "".equals(bankNo)) {
			return true;
		}
		IBranchService branchService = ServiceFactory.getBranchService();
		if (branchService.getBranchByBankNo(bankNo) != null) {
			return true;
		} else {
			return false;
		}
    
    
	}
}
