/********************************************
 * 文件名称: DiscService.java
 * 系统名称: 基础技术平台V2.0
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: 2.0.0.1
 * 开发人员: songzx
 * 开发时间: 2016-09-21
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期    修改人员    修改说明
 *********************************************/
package com.herongtech.console.service.busiservice.common;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Row;

import com.google.common.collect.Lists;
import com.herongtech.console.core.util.ImportExcel;
import com.herongtech.console.domain.importbean.ImportResultBean;
import com.herongtech.exception.impl.BizAppException;
/**
 * 处理导入excel数据
 * @author songzx
 *
 */
public abstract class IImportDataService {

	/**
	 * 处理导入的excel，并返回成功失败记录，成功直接插入数据库
	 * @param ie String billType
	 * @return
	 */
	public abstract ImportResultBean handleExcelData(ImportExcel ie,String billType) throws Exception;
	
	/**
	 * 处理导入的excel的成功或者失败的数据，并返回处理结果
	 * @param status
	 * @return
	 */
	public abstract String importResultHandle(String batchId) throws Exception;
	
	/**
	 * 得到模板标题行
	 * @param ImportExcel ie
	 * @return
	 * @throws Exception 
	 */
	public final List<Object> getTitleList(ImportExcel ie) throws Exception{
		List<Object> titleList = Lists.newArrayList();
		Row row = ie.getRow(0);
		int colNum = ie.getLastCellNum();
		for (int j = 0 ; j < colNum; j++){
			Object val = ie.getCellValue(row, j);
			if (val != null){
				titleList.add(val);
			}
		}
		if( titleList.size() == 0 ) throw new Exception("导入数据模板错误，模板标题不能为空");
		return titleList;
	}
	
	/**
	 * 验证地i列的值 如果通过返回值 如果失败返回原因
	 * @param val
	 * @param i
	 * @return
	 */
	protected abstract String checkValue(Object bill,String val,int i,String billType,String workDate) throws BizAppException;

	/**
	 * 验证票号规则
	 * @param val
	 * @return
	 */
	protected String checkBillNo(String billNo,String billType) {
		if(billNo.length() != 16) return "票号:"+billNo+"格式不正确";
		if("1".equals(billType)){
			if("5".equals(billNo.substring(6, 7))){
				return billNo;
			}
		}
		if("2".equals(billType)){
			if("6".equals(billNo.substring(6, 7))){
				return billNo;
			}
		}
		return "票号:"+billNo+"格式不正确";
	}
	
	
	/**
	 * 验证日期格式是否正确
	 * @param date
	 * @return
	 */
	protected boolean checkDateFormat(String date){
		String regex = "^[1-9]\\d{3}\\-(0?[1-9]|1[0-2])\\-(0?[1-9]|[12]\\d|3[01])$";
		return date.matches(regex);
	}
	
	
	/**
	 * 验证出票日期是否日期格式是否正确
	 * @param date
	 * @return
	 */
	protected String checkDate(String issueDt,String dueDt,String workDate){
		long temp = Long.valueOf(workDate)-Long.valueOf(issueDt.replaceAll("-",""));
		if(temp<0){
			return "出票日必须小于当前营业日";
		}
		if(StringUtils.isNotEmpty(dueDt)){
			//票面到期日与出票日期差值
			long tem = Long.valueOf(dueDt.replaceAll("-",""))-Long.valueOf(issueDt.replaceAll("-",""));
			if(tem<0){
				return "票面到期日必须大于出票日";
			}
			if(tem>180){ //此算法：平均每月30天，6个月180天，实际6个月最大相差184天
				return "票面到期日,出票日相差不能超过6个月";
			}
		}
		return "yes";
	}
	
	
	
	/**
	 * 验证出票金额是否正确
	 * @param billMoney
	 * @return 成功返回值 失败返回错误信息
	 */
	protected String checkMoney(String billMoney){
		double money = Double.valueOf(billMoney);
		if((Math.round(money)+"").length() > 9 ){
		   	  return "票面金额不能达到十亿："+billMoney;
		}
		if(Math.round(money)==0){
			return "出票金额不能为：0";
		}	
		return billMoney;
	}
	
	/**
	 * 验证是否是数字
	 * @param value
	 * @return boolean
	 */
	protected boolean checkNumber(String value){
		String regex = "^[0-9]*";
		return value.matches(regex);
	}
	
}
