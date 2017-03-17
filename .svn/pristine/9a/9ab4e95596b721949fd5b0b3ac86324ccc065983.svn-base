package com.herongtech.console.service.common.interest;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.herongtech.console.core.common.json.AjaxJson;
import com.herongtech.console.domain.bean.Holiday;
import com.herongtech.console.domain.dao.HolidayDao;
import com.herongtech.console.service.ServiceFactory;
import com.herongtech.console.service.interfaces.IHolidayService;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.log.CommonLog;
import com.herongtech.rc.draft.common.MsgUtil;
import com.herongtech.sysconstant.ISysErrorNo;
/**
 * 假期的service
 * 
 * 李江涛
 * */

public class HolidayService implements IHolidayService{

	/**查询是否有这一年的数据*/
	@Override
	public List<Holiday> searchService(String year) throws BizAppException {
		HolidayDao hd = new HolidayDao();
		List<Holiday> holidays = new ArrayList<Holiday>();
		try {
			holidays = hd.searchDataList(year);
		} catch (SQLException e) {
			CommonLog.getCommonLogCache().errorMessage("查询假期数据失败");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "查询假期数据失败");
		}
		return holidays;
	}
	
	/**查询出所有假期*/
	@Override
	public List<Holiday> searchHolidaysService(String year) throws BizAppException {
		HolidayDao hd = new HolidayDao();
		List<Holiday> holidays = new ArrayList<Holiday>();
		try {
			holidays = hd.searchHolidayDataList(year);
		} catch (SQLException e) {
			CommonLog.getCommonLogCache().errorMessage("查询假期数据失败");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "查询假期数据失败");
		}
		return holidays;
	}

	
	/**将假期保存到数据库中*/
	@Override
	public AjaxJson saveService(String startday,String holidayname,String endday) throws BizAppException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	
		AjaxJson aj = new AjaxJson();	
		Calendar calendar=Calendar.getInstance();
		HolidayDao hd = new HolidayDao();
		
		try {
			long time = sdf.parse(endday).getTime()-sdf.parse(startday).getTime();
			if(time<0){
				aj.setMsg("日期选择不能反向");
			}
			int days=(int) (time/(1000*60*60*24));
			if(days==0){
				Holiday holiday = new Holiday();
				long id = Integer.parseInt(startday.replaceAll("-", "")); 
				holiday.setId(id);
				holiday.setDt(startday);
				holiday.setHolidayName(holidayname);
				holiday.setIsHoliday("1");
				calendar.setTime(sdf.parse(startday));
				String[] weeks = {"星期日","星期一","星期二","星期三","星期四","星期五","星期六"};
				int week_index = calendar.get(Calendar.DAY_OF_WEEK) - 1;
				holiday.setWeek(weeks[week_index]);
				
				String date=holiday.getDt();
				Holiday holidays=hd.getHoliday(date);
				if(holidays==null){
					int result = hd.addHoliday(holiday);
					if(result==1){
						aj.setMsg("保存成功");
					}else{
						aj.setMsg("保存失败");
					}
					
				}
				int result = hd.modifyHoliday(holiday);
				if(result==1){
					aj.setMsg("保存成功");
				}else{
					aj.setMsg("保存失败");
				}
				return aj;
			}else{
				for (int i = 0; i <= days; i++) {
					Holiday holiday = new Holiday();
					Date startDay=sdf.parse(startday);
					calendar.setTime(startDay);
					calendar.add(Calendar.DAY_OF_MONTH, i);//加一天
					holiday.setDt(sdf.format(calendar.getTime()));
					holiday.setHolidayName(holidayname);
					holiday.setIsHoliday("1");
					String[] weeks = {"星期日","星期一","星期二","星期三","星期四","星期五","星期六"};
					int week_index = calendar.get(Calendar.DAY_OF_WEEK) - 1;
					holiday.setWeek(weeks[week_index]);
					long id = Integer.parseInt(sdf.format(calendar.getTime()).replaceAll("-", "")); 
					holiday.setId(id);
					
					String date=holiday.getDt();
					Holiday holidays=hd.getHoliday(date);
					if(holidays==null){
						int result = hd.addHoliday(holiday);
						if(result==1){
							aj.setMsg("保存成功");
						}else{
							aj.setMsg("保存失败");
						}
					}
					int result = hd.modifyHoliday(holiday);
					if(result==1){
						aj.setMsg("保存成功");
					}else{
						aj.setMsg("保存失败");
					}
				}
				
			}
			return aj;
			
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			CommonLog.getCommonLogCache().errorMessage("保存假期数据失败");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "保存假期数据失败");
		}

	return aj;	
	}


	/**删除假期的service*/
	@Override
	public String deleteService(String startDate, String endDate) throws BizAppException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		HolidayDao hd = new HolidayDao();
		Calendar calendar = Calendar.getInstance();
		try {
			Date startdate = sdf.parse(startDate);
			Date enddate = sdf.parse(endDate);
			long starttime = startdate.getTime();
			long endtime = enddate.getTime();
			long time = endtime-starttime;
			if(time<0){
				return "选择的时间不能相反";
			}
			int days=(int) (time/(1000*60*60*24));
			if(days==0){//单一假期删除()
				Holiday holiday = hd.getNowdayHoliday(startDate);
				if(holiday==null){
					return "无法删除，因为该日期不是假期";
				}
				int result = hd.deleteHoliday(startDate);
				if(result!=1){
					return "删除假期失败";
				}
				
				
			}else{
				//拖动的都是非假期
				int count=0;
				for (int i = 0; i <= days; i++) {
					Holiday holiday = new Holiday();
					Date startDay=sdf.parse(startDate);
					calendar.setTime(startDay);
					calendar.add(Calendar.DAY_OF_MONTH, i);//加一天
					Date nowday = calendar.getTime();
					String nowtime = sdf.format(nowday);
					
					holiday = hd.getNowdayHoliday(nowtime);
					if(holiday==null){
						count+=1;
						continue;
					}
					break;
				}
				if(count==(days+1)){
					return "无法删除，因为这些日期中不包含假期";
				}
				//假期和非假期同时存在的删除（前台拖动时可能出现假期和非假期都拖动在一起）
				for (int i = 0; i <= days; i++) {
					Holiday holiday = new Holiday();
					Date startDay=sdf.parse(startDate);
					calendar.setTime(startDay);
					calendar.add(Calendar.DAY_OF_MONTH, i);//加一天
					Date nowday = calendar.getTime();
					String nowtime = sdf.format(nowday);
					
					holiday = hd.getNowdayHoliday(nowtime);
					if(holiday==null){
						continue;
					}
					
					int result = hd.deleteHoliday(nowtime);
					if(result!=1){
						return "删除假期失败";
					}

				}
				
				
			}	
		} catch (ParseException e) {
			System.out.println("时间转换异常");
			return "删除假期失败";
		} catch (SQLException e) {
			CommonLog.getCommonLogCache().errorMessage("删除假期数据失败");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "删除假期数据失败");
			
		}
		
		return "删除假期成功";
	}

/**得到下一个工作日   如果当天是工作日就返回当天时间*/
    @Override
    public Date getWorkdate(Date date) throws BizAppException {
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    	HolidayDao dao = new HolidayDao();
    	Calendar cal = Calendar.getInstance();
    	String time = sdf.format(date);
    	try {
    		Holiday holiday = new Holiday();
			holiday = dao.getHoliday(time);
			if(holiday!=null&&holiday.getIsHoliday().equals("0")){//0为工作日  1为假期
				return sdf.parse(holiday.getDt());           //当天是工作日就返当天
			}else{
				Date datetime= sdf.parse(time);
				Calendar calendar = MsgUtil.getConverTime(datetime);
				calendar.setTime(datetime);   
				while(true){
					calendar.add(Calendar.DATE, 1);//增加一天  
					String nextday = MsgUtil.convertToString(calendar);
					Date nextdate = MsgUtil.convertToDate(calendar);
					if(dao.getHoliday(sdf.format(nextdate))==null){
						 throw new BizAppException(new Exception("没有下一工作日了，请维护节假日！"));
					}
					holiday = dao.getHoliday(sdf.format(nextdate));
					if(holiday!=null&&holiday.getIsHoliday().equals("0")){//0为工作日  1为假期
						return sdf.parse(holiday.getDt());          
					}
				}
		        
			}
		} catch (SQLException e) {
			 throw new BizAppException(ISysErrorNo.ERR_DBERR, "下一工作日查询异常");

		} catch (ParseException e) {
			e.printStackTrace();
		}
    	
    	
        return date;
       
    }
	
    public Date getWorkdate(Date date,int maxSize) throws BizAppException {
        try {
            HolidayDao holidayDao = new HolidayDao();
            List list=holidayDao.getSomeHolidays(date, maxSize);
            if(list.size()<1)
                throw new BizAppException(new Exception("系统日期未初始化"));
            for(int i=0;i<list.size();i++){
                Holiday h=(Holiday) list.get(i);
                if(h.getIsHoliday().equals("")){}

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

/**添加一年的假期到数据库*/
	@Override
	public int addYear(String years) throws BizAppException {
		 HolidayDao hd = new HolidayDao();
		 int year= Integer.parseInt(years);
		 int m=1;//月份计数
		 String week = "";
		 String day = "";
		 String isHoliday = "0";
		 try {
			 		HolidayDao hdao = new HolidayDao();
				    Calendar cal=Calendar.getInstance();
				    cal.setTime((new SimpleDateFormat("yyyy")).parse(year+""));
				    SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
				    while(true){
				    Holiday holiday = new Holiday();
				    
				     if(cal.get(Calendar.YEAR)>year)
				    	 break;
				    Holiday selectresult = hdao.getHoliday(sdf.format(cal.getTime()));
				    if(selectresult!=null){
				    	continue;
				    }
				     if (Calendar.MONDAY == cal.get(Calendar.DAY_OF_WEEK)){
							week="星期一";
							isHoliday = "0";
							holiday.setHolidayName("");
						}
						if (Calendar.TUESDAY == cal.get(Calendar.DAY_OF_WEEK)){
							week="星期二";
							isHoliday = "0";
							holiday.setHolidayName("");
						}
						if (Calendar.WEDNESDAY == cal.get(Calendar.DAY_OF_WEEK)){
							week="星期三";
							isHoliday = "0";
							holiday.setHolidayName("");
						}
						if (Calendar.THURSDAY == cal.get(Calendar.DAY_OF_WEEK)){
							week="星期四";
							isHoliday = "0";
							holiday.setHolidayName("");
						}
						if (Calendar.FRIDAY== cal.get(Calendar.DAY_OF_WEEK)){
							week="星期五";
							isHoliday = "0";
							holiday.setHolidayName("");
						}
						if (Calendar.SATURDAY == cal.get(Calendar.DAY_OF_WEEK)){
							week="星期六";
							holiday.setHolidayName("星期六");
							isHoliday = "1";
						}
						if (Calendar.SUNDAY == cal.get(Calendar.DAY_OF_WEEK)){
							week="星期日";
							isHoliday = "1";
							holiday.setHolidayName("星期日");
						}
				     
				    day = sdf.format(cal.getTime());
					String daystr =day.replaceAll("-","");
					Long id = Long.parseLong(daystr);
					holiday.setDt(day);
					holiday.setId(id);
					holiday.setIsHoliday(isHoliday);
					holiday.setWeek(week);
					int result = hd.addHoliday(holiday);
					cal.add(Calendar.DAY_OF_YEAR, 1);
				   }
	
		 } catch (SQLException e) {
			 CommonLog.getCommonLogCache().errorMessage("插入记录tholiday失败");
			 throw new BizAppException(ISysErrorNo.ERR_DBERR, "插入记录失败");
		 } catch (ParseException e) {
			e.printStackTrace();
		}
		 
		 
		 return 1;
		}

/**获得数据库中所有数据*/
@Override
public List<Holiday> getAllData() throws BizAppException {
	HolidayDao dao = new HolidayDao();
	List<Holiday> holidays = null;
	try {
		holidays = dao.getAllData();
		return holidays;
	} catch (SQLException e) {
	
		e.printStackTrace();
		return null;
	}
	
}

/**把controller所调用的方法都写在一个service方法中 直接返回最终controller所需数据（页面初始化时调用）*/
@Override
public List<Holiday> getControllerneedallmethodresult(String year)throws BizAppException {
	List<Holiday> holidays = new ArrayList<Holiday>();
	IHolidayService hs = ServiceFactory.getHolidayService();
	holidays=hs.searchService(year);                                //查询有无这年数据
	if(holidays.size()<=0){	              //是否有这一年的数据
		
		return null;
	}else{
		holidays = hs.searchHolidaysService(year);
	}
	return holidays;
}
		
		
	
	

}
