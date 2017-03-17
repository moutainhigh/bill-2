package com.herongtech.console.web.syscontroller;
/**假期controller
 * 李江涛
 * 
 * */
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


import com.herongtech.commons.tools.HsSqlString;
import com.herongtech.commons.tools.StringUtils;
import com.herongtech.console.core.common.json.AjaxJson;
import com.herongtech.console.core.common.json.Page;
import com.herongtech.console.core.controller.BaseController;
import com.herongtech.console.domain.bean.Holiday;
import com.herongtech.console.domain.bean.OtherHoliday;

import com.herongtech.console.service.ServiceFactory;
import com.herongtech.console.service.interfaces.IHolidayService;
import com.herongtech.db.impl.session.DBFactory;
import com.herongtech.db.interfaces.IDB;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.log.CommonLog;
import com.herongtech.rc.draft.DraftService;
import com.herongtech.rc.service.RcServiceFactory;
import com.herongtech.sysconstant.ISysErrorNo;
@Scope("prototype")
@Controller
@RequestMapping("/sysHolidayController")
public class HolidayController  extends BaseController{

	public static final String HERONGTECH_VERSION="@system 票据管理平台 @version 2.0.0.1 @lastModiDate @describe ";
	/**页面跳转*/
	@RequestMapping(params="method=list")
	public ModelAndView list(Page page, HttpServletRequest request,
			HttpServletResponse response)throws Exception{
		ModelAndView mv = new ModelAndView();
	
		mv.setViewName("system/holiday/holidaymain");
			return mv;
	}
	
	/**保存假期*/
	@RequestMapping(params="method=save")
	@ResponseBody
	public AjaxJson save(HttpServletRequest request,
			HttpServletResponse response) throws BizAppException{
			IDB session = DBFactory.getDB();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date systime = new Date(); 
			AjaxJson aj = new AjaxJson();	
			IHolidayService hs =  ServiceFactory.getHolidayService();
			Calendar calendar=Calendar.getInstance();
			String holidayname = "非工作日";
			String startday = request.getParameter("startday");
			String endday = request.getParameter("endday");
			String sysdatestr = sdf.format(systime);
			try {
				session.beginTransaction();
				systime = sdf.parse(sysdatestr);
				Date starttime = sdf.parse(startday);
				int result = starttime.compareTo(systime);
				if(result<0){
					aj.setMsg("0");
					return aj;
				}
				
			aj = hs.saveService(startday, holidayname, endday);
				session.endTransaction();
			} catch (ParseException e) {
				try {
					session.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				CommonLog.getCommonLogCache().errorMessage("插入记录tholiday失败");
				 throw new BizAppException(ISysErrorNo.ERR_DBERR, "插入记录失败");
			}catch (Exception e) {
				try {
					session.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				CommonLog.getCommonLogCache().errorMessage("插入记录tholiday失败");
				 throw new BizAppException(ISysErrorNo.ERR_DBERR, "插入记录失败");
			}

		return aj;
		
		
	}
	
	/**页面刚加载时调用     显示日历*/
	@RequestMapping(params="method=search")
	@ResponseBody
	public Map<String,Object> search(HttpServletRequest request,
			HttpServletResponse response) throws BizAppException{
		IHolidayService hs = ServiceFactory.getHolidayService();
		Map<String,Object> map = new HashMap<String, Object>(2);
		String nowyear = request.getParameter("nowyear");                  //获得前台传过来的年份
		//System.out.println("当前年=="+nowyear);
		List<OtherHoliday> deliverholidays = new ArrayList<OtherHoliday>();//需要传到前台页面的holiday相关数据  ： 月    天  假期名
	
		 IDB session = DBFactory.getDB();
		try {
			session.beginTransaction();
			List<Holiday> holidays = hs.getControllerneedallmethodresult(nowyear);
			if(holidays==null){
				map.put("deliverholidays", "");
				return map;
			}
			String[] shuzu = new String[2];
			for (int i = 0; i < holidays.size(); i++) {                        //将holiday中的月和日还有假期名取出储存到deliverholidays中传到前台
				OtherHoliday deliverholiday = new OtherHoliday();
				String year = holidays.get(i).getDt().substring(0,4);
				String month = holidays.get(i).getDt().substring(5,7);
				String day = holidays.get(i).getDt().substring(8,10);
				String name = holidays.get(i).getHolidayName();
				deliverholiday.setId(i);
				deliverholiday.setName("'"+name+"'");
				String date = "new Date("+year+","+(Integer.parseInt(month)-1)+","+day+")";//前台需要的开始和结束时间
				deliverholiday.setStartDate(date);
				deliverholiday.setEndDate(date);
				deliverholidays.add(deliverholiday);
			}
			session.endTransaction();
		} catch (Exception e) {
			try {
				session.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			CommonLog.getCommonLogCache().errorMessage("查询记录tholiday失败");
			 throw new BizAppException(ISysErrorNo.ERR_DBERR, "查询记录失败");
		}
		
		
		map.put("deliverholidays",deliverholidays);
		return map;
	
	
	}
	
	/**删除假期控制事件（不需要的方法，节假日没有这个功能）*/
	@RequestMapping(params="method=delete")
	@ResponseBody
	public String DeleteHoliday(HttpServletRequest request,HttpServletResponse response) throws BizAppException{
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		IHolidayService hs = ServiceFactory.getHolidayService();
		IDB session = DBFactory.getDB();
		String deleteresult;
		try {
			session.beginTransaction();
			deleteresult = hs.deleteService(startDate, endDate);
			if(deleteresult!="删除假期成功"){
				return deleteresult;
			}
			session.endTransaction();
		} catch (Exception e) {
			try {
				session.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			CommonLog.getCommonLogCache().errorMessage("删除记录tholiday失败");
			 throw new BizAppException(ISysErrorNo.ERR_DBERR, "删除记录失败");
		}
		
		return "删除假期成功";
	}
	/**
	 * 新增一年
	 *
	 */
	@RequestMapping(params="method=addOneYear")
	@ResponseBody
	public String addOneYear(HttpServletRequest request,HttpServletResponse response){
		String nowyear = request.getParameter("nowyear");
		int willaddyear =0;
		IDB session = DBFactory.getDB();
		List<Holiday> holidays = new ArrayList<Holiday>();
		IHolidayService hs = ServiceFactory.getHolidayService();

		try {
			session.beginTransaction();
			holidays=hs.searchService(nowyear);
			if(holidays.size()<365){	              //是否这一年的数据全
				hs.addYear(nowyear);	         //如果没数据就先添加一年的数据
				//holidays=hs.searchHolidaysService(nowyear); 
			}else{
				
				List<Holiday> holidayall = hs.getAllData();
				for (int i = 0; i < holidayall.size(); i++) {//取出数据库中最大年份
					if(i==0){
						willaddyear=Integer.parseInt(holidayall.get(0).getDt().substring(0, 4));
					}
					if(willaddyear<=Integer.parseInt(holidayall.get(i).getDt().substring(0, 4))){
						willaddyear=Integer.parseInt(holidayall.get(i).getDt().substring(0, 4));
						
					}
				}
			
				holidays=hs.searchService(String.valueOf(willaddyear));	
				if(holidays.size()<365){
					hs.addYear(String.valueOf(willaddyear));
				}else{
					String addyear = String.valueOf(willaddyear+1);
					hs.addYear(addyear);		
				}
				
			}
			session.endTransaction();
		} catch (BizAppException e) {
			try {
				session.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}catch (SQLException e) {
			   try {
				session.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		
		return "成功";
	}
	
}
