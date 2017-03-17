/********************************************
 * 文件名称: DateTimeUtil.java
 * 系统名称: 基础技术平台V2.0
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: 2.0.0.1
 * 开发人员: sherry
 * 开发时间: 2016-8-9 
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期    修改人员    修改说明
 *********************************************/
package com.herongtech.console.core.util;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang.time.DateUtils;

import com.herongtech.console.service.ServiceFactory;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.rc.service.RcServiceFactory;

public class DateTimeUtil {
	
	private static final String[] parsePatterns = new String[]{"yyyy-MM-dd","yyyy/MM/dd","yyyyMMdd"};
//	private static Date workDay=null;
	
	public static String parse(String s) throws BizAppException  {
		if (s == null || "".equals(s) || "null".equalsIgnoreCase(s))   
			return null;
		try {
			Date date = DateUtils.parseDate(org.springframework.util.StringUtils.trimAllWhitespace(s),
					parsePatterns);
			return get_YYYY_MM_DD_Date(date);
		} catch (Exception e) {
			throw new BizAppException(e);
		}
	}
	
	public static String getNowDateTime(){
		Calendar cd=Calendar.getInstance();
		Date date=cd.getTime();
		return get_YYYY_MM_DD_Date(date);
	}
	
	public static String toDateString(Date date) {
		if(date==null){
			return null;
		}
		return get_YYYY_MM_DD_Date(date);
	}

	public static String formatDate(Date date, String pattern) {
		if(date==null||pattern==null){
			return null;
		}
		return DateFormatUtils.format(date, pattern);
	}
	
	public static String toDateTimeString(Date date) {
		if (date == null)  return null;
		return formatDate(date, "yyyy-MM-dd HH:mm:ss");
	}
	/**
	 * 获取当前系统日期字符串     格式为 yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static String getNowDateTimeString(){
		Date date = Calendar.getInstance().getTime();
		return toDateTimeString(date);
	}
	public static String toTimeString(Date date) {
		if (date == null)  return null;
		return formatDate(date, "HH:mm:ss");
	}

	public static String convertToyyyyMMdd(String date) {
		return StringUtils.replaceEach(date, new String[]{"-", "/", " "}, new String[]{"","",""});
	}

	
	/**
	 * 获得当前日期的格式  yyyy-MM-dd
	 */
	public static String get_YYYY_MM_DD_Date(){
		return formatDate(Calendar.getInstance().getTime(), "yyyy-MM-dd");
	}
	
	/**
	 * 日期格式转换
	 * @param date
	 * @return	返回格式为"yyyy-MM-dd"的字串
	 * @throws Exception
	 */
	public static String get_YYYY_MM_DD_Date(Date date){
		if(date==null){
			return null;
		}
		return formatDate(date, "yyyy-MM-dd");
	}
	
	/**
	 * 获得制定日期的年，月，日，的数组形式
	 */
	public static String[] getYear_Month_Day_Date(Date date){
		if(date==null){
			return null;
		}
		String [] stDate = null;
		try {
			stDate = new SimpleDateFormat("yyyy-MM-dd").format(date).split("-");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return stDate;
	}
	

	
	/**
	 * 获得当前时间格式的字符串
	 */
	public static String get_hhmmss_time(){
		return formatDate(Calendar.getInstance().getTime(), "HH:mm:ss");
	}
	
	/**
	 * 获得当前时间格式的字符串
	 * <br>格式：HHmmss
	 * @return
	 */
	public static String getTime(){
        return formatDate(Calendar.getInstance().getTime(), "HHmmss");
    }
	
	/**
	 * 计算一旬的头一天
	 * @param theDate
	 * @param days
	 * @return
	 */
     public static java.sql.Date getDayOfPerMonth(String theDataStr){
     	Date theDate = java.sql.Date.valueOf(theDataStr);
     	Calendar c=  Calendar.getInstance();
     	c.setTime(theDate);
     	int day=c.get(Calendar.DAY_OF_MONTH);
     	if(day<=10){
     		c.set(Calendar.DAY_OF_MONTH,1);
     	}else if(day>10&&day<=20){
     		c.set(Calendar.DAY_OF_MONTH,11);
     	}else{
     		c.set(Calendar.DAY_OF_MONTH,21);
     	}
     	c.add(Calendar.DAY_OF_MONTH,-1);
     	return new java.sql.Date(c.getTime().getTime());
     }
     /**
      * 判断是否为该月最后一天
      * 当前日期增加一天，如果月份发生了 变化，则为最后一天
      * @param theDate
      * @param days
      * @return
      * @throws BizAppException 
      * @throws ParseException 
      */
     public static boolean isLastDayOfMonth(final String theDataStr) throws BizAppException {
     	Date theDate = parseStringToDate(theDataStr);
     	Calendar c= Calendar.getInstance();
     	c.setTime(theDate);
     	int beforeMonth = c.get(Calendar.MONTH);
     	c.add(Calendar.DAY_OF_MONTH, 1);
     	int afterMonth=c.get(Calendar.MONTH);
     	if(beforeMonth==afterMonth){//当前日期增加一天，如果月份发生了 变化，则为最后一天
     		return false;
     	}else{
     		return true;
     	}
     }

     public static Date parseStringToDate(String s) throws BizAppException  {
 		if (s == null || "".equals(s) || "null".equalsIgnoreCase(s))   
 			return null;
 		try {
 			Date date = DateUtils.parseDate(org.springframework.util.StringUtils.trimAllWhitespace(s),
 					parsePatterns);
 			return date;
 		} catch (Exception e) {
 			throw new BizAppException(e);
 		}
 	}
     /**
      * 获取指定月份的最后一天
      * @param theDataStr
      * @return
      */
     public static String lastDateOfMonth(String theDataStr){
     	Date theDate = java.sql.Date.valueOf(theDataStr);
     	Calendar c=  Calendar.getInstance();
     	c.setTime(theDate);
     	c.add(Calendar.MONTH,1);
     	c.set(Calendar.DAY_OF_MONTH,1);
     	c.add(Calendar.DAY_OF_MONTH,-1);
     	Date date=c.getTime();
     	return  get_YYYY_MM_DD_Date(date);
     }

 	/**
 	 * 计算某日期之后N天的日期
 	 * 
 	 * @param theDateStr
 	 * @param days
 	 * @return String
 	 */
 	public static String getDate(String theDateStr, int days) {
 		Date theDate = java.sql.Date.valueOf(theDateStr);
 		Calendar c = Calendar.getInstance();
 		c.setTime(theDate);
 		c.add(Calendar.DATE, days);
 		java.sql.Date d = new java.sql.Date(c.getTime().getTime());
 		return d.toString();
 	}
 	
 	/**
 	 * 计算某日期之后N天的日期
 	 * 
 	 * @param theDate
 	 * @param days
 	 * @return Date
 	 */
 	public static java.sql.Date getDate(Date theDate, int days) {
 		Calendar c = new GregorianCalendar();
 		c.setTime(theDate);
 		c.add(Calendar.DATE, days);
 		return new java.sql.Date(c.getTime().getTime());
 	}
 	
	/**
	 * 计算某日期之后N的日期
	 * 
	 * @param theDate
	 * @param field，如GregorianCalendar.DATE,GregorianCalendar.MONTH
	 * @param amount 数目
	 * @return Date
	 */
	public static java.sql.Date getDate(Date theDate, int field, int amount) {
		Calendar c = new GregorianCalendar();
		c.setTime(theDate);
		c.add(field, amount);
		
		return new java.sql.Date(c.getTime().getTime());
	}
	
	/**
	 * 计算某日期之后N的日期
	 * 
	 * @param theDateStr
	 * @param field，如GregorianCalendar.DATE,GregorianCalendar.MONTH
	 * @param amount 数目
	 * @return String
	 * @throws BizAppException 
	 */
	public static String getDate(String theDateStr, int field, int amount) throws BizAppException {
		Date theDate = parseStringToDate(theDateStr);
		Calendar c = new GregorianCalendar();
		c.setTime(theDate);
		c.add(field, amount);
		java.sql.Date date = new java.sql.Date(c.getTime().getTime());
		return date.toString();
	}
	
    /**
     * 获得两个日期(字符串)之间的天数(不算头不算尾)
     * @param begin_dt
     * @param end_dt
     * @return
     */
	public static long getDiffDay(String begin_dt,String end_dt){
		Date end = java.sql.Date.valueOf(end_dt);
		Date begin = java.sql.Date.valueOf(begin_dt);
		return DateTimeUtil.getDaysBetween(begin,end)-1;
	}
	
    /**
     * 获得两个日期(字符串)之间的天数(算头不算尾)
     * @param begin_dt
     * @param end_dt
     * @return
     */
	public static long getDiffDays(String begin_dt,String end_dt){
		Date end = java.sql.Date.valueOf(end_dt);
		Date begin = java.sql.Date.valueOf(begin_dt);
		return DateTimeUtil.getDaysBetween(begin,end);
	}	
	
	/**
	 * 获得两个日期(字符串)之间的天数(算头算尾)
	 * @param begin_dt
	 * @param end_dt
	 * @return
	 */
	public static long getDiffDaysBeginAndEnd(String begin_dt,String end_dt){
		Date end = java.sql.Date.valueOf(end_dt);
		Date begin = java.sql.Date.valueOf(begin_dt);
		return DateTimeUtil.getDaysBetween(begin,end)+1;
	}
	/**
	 * 获得两个日期(字符串)之间的天数(算头不算尾)【不取绝对值】
	 * @param begin_dt
	 * @param end_dt
	 * @return
	 */
	public static long getDiffDaysNotAbs(String begin_dt,String end_dt){
		Date end = java.sql.Date.valueOf(end_dt);
		Date begin = java.sql.Date.valueOf(begin_dt);
		return DateTimeUtil.getDaysBetweenNotAbs(begin,end);
	}

	/**
	 * 计算两日期之间的天数
	 * 
	 * @param start
	 * @param end
	 * @return int
	 */
	public static long getDaysBetween(final Date start, final Date end) {
		if(start==null || end==null)return 0;
		Date startTemp = (Date)start.clone();
		Date endTemp = (Date)end.clone();
		
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(startTemp);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		GregorianCalendar calEnd = new GregorianCalendar();
		calEnd.setTime(endTemp);
		calEnd.set(Calendar.HOUR_OF_DAY, 0);
		calEnd.set(Calendar.MINUTE, 0);
		calEnd.set(Calendar.SECOND, 0);
		calEnd.set(Calendar.MILLISECOND, 0);
		
		long days = (cal.getTimeInMillis()-calEnd.getTimeInMillis())/(24L*60L*60L*1000L);
		days = Math.abs(days);
		
		return days;
	}
	
	/**
	 * 计算两日期之间的天数（不取绝对值）
	 * 
	 * @param start
	 * @param end
	 * @return int
	 */
	public static long getDaysBetweenNotAbs(final Date start, final Date end) {
		if(start==null || end==null)return 0;
		Date startTemp = (Date)start.clone();
		Date endTemp = (Date)end.clone();
		
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(startTemp);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		GregorianCalendar calEnd = new GregorianCalendar();
		calEnd.setTime(endTemp);
		calEnd.set(Calendar.HOUR_OF_DAY, 0);
		calEnd.set(Calendar.MINUTE, 0);
		calEnd.set(Calendar.SECOND, 0);
		calEnd.set(Calendar.MILLISECOND, 0);
		
		long days = (cal.getTimeInMillis()-calEnd.getTimeInMillis())/(24L*60L*60L*1000L);
		return days;
	}

	
	/**
	 * 以指定时间格式返回指定时间
	 * 
	 * @param dt 指定时间
	 * @param format 时间格式，如yyyyMMdd
	 * @return 返回指定格式的时间
	 */
	public static String getTime(Date dt, String format) {
	    if(dt==null)
	        return "";
		SimpleDateFormat st = new SimpleDateFormat(format);
		return st.format(dt);
	}

	/**
	 * 日期解析
	 * 
	 * @param source 日期字符
	 * @param format 解析格式，如果为空，使用系统默认格式解析
	 * @return 日期
	 * @throws BizAppException 
	 */
	public static Date parse(String source, String format) throws BizAppException {
		if (format == null || format.length() == 0){
			format = "yyyy-MM-dd";
		}
		try {
			return DateUtils.parseDate(source, new String[] { format });
		} catch (ParseException e) {
			throw new BizAppException("日期格式不符" + source);
		}
	}
	/**
	 * 获得当前营业日，格式yyyy-MM-dd
	 * 
	 * @return 当前营业日
	 * @throws BizAppException 
	 */
	public static String getWorkdayString() throws BizAppException {
		return getTime(getWorkdayDate(), "yyyy-MM-dd");
	}
	
	/**
     * 获得当前营业日，格式yyyyMMdd
     * 
     * @return 当前营业日
     * @throws BizAppException 
     */
	public static String getWorkday() throws BizAppException {
        return getTime(getWorkdayDate(), "yyyyMMdd");
    }

	/**
	 * 获得当前营业日
	 * 
	 * @return 当前营业日
	 * @throws BizAppException 
	 */
	public static Date getWorkdayDate() throws BizAppException {
		String workday=ServiceFactory.getSysBusiInfoService().getDefaultBusiDate().getWorkday();
		return parseStringToDate(workday);
	}
	

	/**
	 * 获得下一个营业日
	 * 
	 * @return 下一个营业日
	 * @throws BizAppException 
	 */
	public static Date getNextWorkdayDate() throws BizAppException {
		return getDate(getWorkdayDate(), 1);
	}

	/**
	 * 获得下一个营业日，格式yyyy-MM-dd
	 * 
	 * @return 下一个营业日
	 * @throws BizAppException 
	 */
	public static String getNextWorkdayString() throws BizAppException {
		return getTime(getNextWorkdayDate(), "yyyy-MM-dd");
	}
	/**
	 * 获得上一个营业日
	 * 
	 * @return 上一个营业日
	 * @throws BizAppException 
	 */
	public static Date getLastWorkdayDate() throws BizAppException{
		return getDate(getWorkdayDate(), -1);
	}
	/**
	 * 获得上一个营业日，格式yyyy-MM-dd
	 * 
	 * @return 上一个营业日
	 * @throws BizAppException 
	 */
	public static String getLastWorkdayString() throws BizAppException {
		return getTime(getLastWorkdayDate(), "yyyy-MM-dd");
	}

	/**
	 * 更新营业日
	 * 
	 * @param dt 营业日
	 */
//	public static void updateWorkday(Date dt) {
//		BusiDate busiDate = new BusiDate();
//		busiDate.setWorkday(get_YYYY_MM_DD_Date(dt));
//		ServiceFactory.getSysBusiInfoService().updateBusiDate(busiDate);
//		BusiDateDao dao = new BusiDateDao();
//		dao.modifyBusiDate();
//	}

	/**
	 * 更新营业日
	 * 
	 * @param sdt 营业日
	 */
//	public static void updateWorkday(String sdt) {
//		updateWorkday(parse(sdt));//此处考虑sdt可能是"yyyy-MM-dd","yyyy/MM/dd","yyyyMMdd"的形式，是否必要？
//	}

	/**
	 * 更新到下一营业日
	 * @throws BizAppException 
	 */
//	public static void updateNextWorkday() throws BizAppException {
//		updateWorkday(getNextWorkdayDate());
//	}
	
	/**
	 * 比较2个日期的大小，如果d1在d2之后，则返回false，否则返回true
	 * @param d1
	 * @param d2
	 * @return
	 */
	public static boolean compartdate(Date d1, Date d2) {
		Date formatDate1 = DateUtils.round(d1, Calendar.DATE);//取到日
		Date formatDate2 = DateUtils.round(d2, Calendar.DATE);
		
		if (formatDate1.after(formatDate2)) {
			return false;
		}
		return true;
	}
	
	/**
	 * 比较2个日期的大小，如果d1在d2之后，则返回false，否则返回true
	 * @param d1
	 * @param d2
	 * @return
	 * @throws BizAppException 
	 */
	public static boolean compartdate(String d1, String d2) throws BizAppException {
		return compartdate(parseStringToDate(d1),parseStringToDate(d2));
	}
	
	/**
	 * 自定义方法
	 * 可解决不同的时区 格式转换错误的问题 
	 */
	public static Date parse3(String s) throws BizAppException {
		try {
			if (s == null)
				return null;
			SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
			TimeZone zone = TimeZone.getTimeZone("GMT+8");
			f.setTimeZone(zone);
			return f.parse(s);
		} catch (Exception e) {
			throw new BizAppException(e.getMessage());
		}
	}
	
	/***
	 * 将当前营业日整合成：当前营业日+HH:mm:ss.SSS格式
	 * @param workday:当前营业日
	 * @return
	 * @throws BizAppException 
	 */
	public static Date  workday_append_time(String workday) throws BizAppException{
		if("".equals(workday) || workday==null){
			return null;
		}
		String current_time=DateTimeUtil.getTime(new Date(),"HH:mm:ss.SSS");
		StringBuffer sbuffer=new StringBuffer(workday);
		sbuffer.append(" ");
		sbuffer.append(current_time);
		return parse(sbuffer.toString(),"yyyy-MM-dd HH:mm:ss.SSS");
	}
	
	/**
	 * 计算几个月之后的日期
	 * @param date
	 * @param month 如果是负数,那么就是计算之前几个月.
	 * @return
	 * @throws BizAppException
	 */
	public static String getDateWithMonths(String date , int month) throws BizAppException{
		Calendar c = new GregorianCalendar();
		c.setTime(parseStringToDate(date));
		c.add(Calendar.MONTH, month);//month如果是负数,那么就是计算之前几个月.
		return get_YYYY_MM_DD_Date(c.getTime());
	}
	
	
	/**
	 * 返回两个日期字符串中的最大的一个
	 * @param d1
	 * @param d2
	 * @return
	 * @throws BizAppException 
	 */
	public static String returnMaxDate(String d1Str, String d2Str) throws BizAppException {
		Date d1 = parseStringToDate(d1Str);
		Date d2 = parseStringToDate(d2Str);
		if (compartdate(d1, d2)) {
			return d2Str;
		} else {
			return d1Str;
		}
	}
	/***
	 * 获取日期的时分秒毫秒
	 * @param date
	 * @return
	 */
	public static String toDateMillTimeString(Date date) {
		if (date == null) {
			return null;
		}
		return formatDate(date, "yyyy-MM-dd HH:mm:ss.SSS");
	}
	/***
	 * 获取日期的时分秒毫秒(17位数字)
	 * @param date
	 * @return
	 */
	public static String toDateMillTimeString17(Date date) {
		if (date == null) {
			return null;
		}
		return formatDate(date, "yyyyMMddHHmmssSSS");
	}
	
	public static Date clone(Date dt){
		if(dt == null) return null;
		return (Date) dt.clone();
	}
	/***
	 * 将当前营业日整合成：当前营业日+HH:mm:ss.SSS格式
	 * @return
	 * @throws BizAppException 
	 * @throws ParseException 
	 */
	public static Date workday_append_time() throws BizAppException{
		String workday = getTime(getWorkdayDate(), "yyyy-MM-dd");
		String current_time=DateTimeUtil.getTime(new Date(),"HH:mm:ss.SSS");
		StringBuffer sbuffer=new StringBuffer(workday);
		sbuffer.append(" ");
		sbuffer.append(current_time);
		return parse(sbuffer.toString(),"yyyy-MM-dd HH:mm:ss.SSS");
	}
	
	public static String getWorktimeAsyyyyMMddHHmmss() throws BizAppException{
		String workday = getTime(getWorkdayDate(), "yyyyMMdd");
		String current_time=getTime(new Date(),"HHmmss");
		
		return workday+current_time;
	}
	
	//计算后一天
	public static String getUtilDate(String theDateStr, int days) throws BizAppException {
		Date theDate = parseStringToDate(theDateStr);
		Calendar c = new GregorianCalendar();
		c.setTime(theDate);
		c.add(Calendar.DATE, days);
		Date date = new Date(c.getTime().getTime());
		return get_YYYY_MM_DD_Date(date);
	}
	
	
	
	/**
	 * 判断日期是否有效
	 * @param dateStr
	 * @return true 有效 false 无效
	 */
	public static boolean isRightFulDate(String dateStr){
	    boolean flag=false;
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	    try {
            Date tmp = sdf.parse(dateStr);
            flag=dateStr.equals(sdf.format(tmp))?true:false;
        } catch (ParseException e) {
            flag=false;
        }
        return flag;
	}
	 /**
     * 比较提示付款回复日和到期日之间的间隔天数
     * @param dueDt
     * @return betweenDays
	 * @throws BizAppException 
     */
    public static int comparedWithDueDt(String dueDt) throws BizAppException {
    	String ecdsDateString = RcServiceFactory.getRgctEcdsStatusService().getRgctEcdsStatus("ES003").getPvalue();
    	//TODO "ES003"是否有码表或常量表
    	Date workDate = parseStringToDate(ecdsDateString);
    	Date dueDate = parseStringToDate(dueDt);
    	return (int) ((workDate.getTime() - dueDate.getTime()) / (24 * 60 * 60 * 1000));
    }
    
    /**
     * 是否超过6个月 超过则返回true
     * @param beginDt
     * @param endDt
     * @return
     * @throws BizAppException 
     */
    public static boolean isOverSixMonthInterval(String beginDtStr, String endDtStr) throws BizAppException{
    	Date beginDt = parseStringToDate(beginDtStr);
    	Date endDt = parseStringToDate(endDtStr);
    	Date two = DateTimeUtil.getDate(beginDt, Calendar.MONTH, 6);
    	//TODO 直接用 6 代替 AcptCodeConst.ACPT_SIX_MONTH？
    	if(endDt.compareTo(two)>0){
    		return true;
    	}else{
    		return false;
    	}
    	
    }
    
    /**
     * 是否超过12个月 超过则返回true
     * @param beginDt
     * @param endDt
     * @return
     * @throws BizAppException 
     */
    public static boolean isOver12MonthInterval(String beginDtStr, String endDtStr) throws BizAppException{
    	Date beginDt = parseStringToDate(beginDtStr);
    	Date endDt = parseStringToDate(endDtStr);
    	Date two = DateTimeUtil.getDate(beginDt, Calendar.MONTH, 12);
    	//TODO 直接用 12 代替 AcptCodeConst.ACPT_SIX_MONTH？
    	if(endDt.compareTo(two)>0){
    		return true;
    	}else{
    		return false;
    	}
    	
    }
    /**
	 * 获得当前日期的格式 yyyyMMdd
	 */
	public static String get_YYYYMMDD_Date() {
		return formatDate(Calendar.getInstance().getTime(), "yyyyMMdd");
	}
	
	/**
	 * 获得当前日期的格式 yyMMdd
	 */
	public static String get_YYMMDD_Date() {
		return formatDate(Calendar.getInstance().getTime(), "yyMMdd");
	}
	/**
	 * 获得当前ECDS日期的格式 yyyyMMdd
	 */
	public static String get_ECDS_YYMMDD_Date()throws BizAppException {
		String ecdsDateString = RcServiceFactory.getRgctEcdsStatusService().getRgctEcdsStatus("ES003").getPvalue();
		return formatDate(parseStringToDate(ecdsDateString), "yyyyMMdd");
	}
	
	 /*
     * 获得当前日期的格式  yyyy-MM-dd
     */
    public static String getEcds_YYYY_MM_DD_Date()throws BizAppException{
    	String ecdsDateString = RcServiceFactory.getRgctEcdsStatusService().getRgctEcdsStatus("ES003").getPvalue();
		return formatDate(parseStringToDate(ecdsDateString), "yyyy-MM-dd");
    }

}