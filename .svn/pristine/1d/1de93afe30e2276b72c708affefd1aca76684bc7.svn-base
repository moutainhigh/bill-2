package com.herongtech.console.service.interfaces;
/**
 * 非工作日接口
 * 李江涛
 * */
import java.util.Date;
import java.util.List;

import com.herongtech.console.core.common.json.AjaxJson;
import com.herongtech.console.domain.bean.Holiday;
import com.herongtech.exception.impl.BizAppException;

public interface IHolidayService {

	/**根据年份查询节假日*/
	public List<Holiday> searchService(String year) throws BizAppException;
	
	/**根据所传的假期进行插入或者更新节假日数据*/
	public AjaxJson saveService(String startday,String holidayname,String endday) throws BizAppException;
	
	/**删除节假日*/
	public String deleteService(String startDate,String endDate) throws BizAppException;
	
	
	/**
     * 根据date取出数据库中下一个工作日，如果date当天本身是工作日，则得到当前日期
     */
    public Date getWorkdate(Date date)throws BizAppException;
    /**数据库中添加一年的天数*/
    public int addYear(String year)throws BizAppException;

    /**查询出某年的假期列表*/
	List<Holiday> searchHolidaysService(String year) throws BizAppException;
	
	/**获得数据库中的所有数据*/
	public List<Holiday> getAllData()throws BizAppException;
	
	/**把controller所调用的方法都写在一个service方法中  直接返回最终controller所需数据*/
	public List<Holiday> getControllerneedallmethodresult(String year)throws BizAppException;

}
