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
package com.herongtech.console.service.busiservice.disc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.poi.ss.usermodel.Row;

import com.google.common.collect.Lists;
import com.herongtech.commons.tools.StringUtils;
import com.herongtech.console.core.util.ContextHolderUtils;
import com.herongtech.console.core.util.DateTimeUtil;
import com.herongtech.console.core.util.ImportExcel;
import com.herongtech.console.domain.disc.bean.DiscApplyInfo;
import com.herongtech.console.domain.disc.bean.DiscBillInfo;
import com.herongtech.console.domain.importbean.ImportResultBean;
import com.herongtech.console.service.ServiceFactory;
import com.herongtech.console.service.busiservice.common.IImportDataService;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.log.CommonLog;
import com.herongtech.sysconstant.ISysErrorNo;
/**
 * 处理贴现导入的excel数据
 * @author songzx
 *
 */
public class DiscImportDataService extends IImportDataService {

	
	/**
	 * 处理导入的excel的成功或者失败的数据，并返回处理结果
	 * @param status
	 * @return
	 */
	public String importResultHandle(String batchId) throws Exception{
		HttpSession session = ContextHolderUtils.getSession();
		String rs = insertBillInfo(session,batchId);//数据持久化
		//取消导入，释放内存
		session.removeAttribute("successData");
		return rs;
	}
	/**
	 * 功能描述：批量导入清单持久化
	 * @param session
	 * @param batchId
	 * @return
	 * @throws BizAppException
	 */
	private String insertBillInfo(HttpSession session,String batchId) throws BizAppException {
		String rs = "yes";
		@SuppressWarnings("unchecked")
		List<DiscBillInfo> billList = (List<DiscBillInfo>)session.getAttribute("successData");
		if(billList==null || billList.size() == 0 ) return rs;
		
		IDiscService discService = ServiceFactory.getDiscService();
		try {
			DiscApplyInfo apply = discService.getDiscApplyInfoBydiscid(batchId);
			for(DiscBillInfo bill : billList){
				bill.setDiscId(batchId);
				bill.setCustNo(apply.getCustNo());
				bill.setCustAccount(apply.getCustAccount());
				discService.addDiscBillInfo(bill);
			}
		} catch (Exception e) {
			rs = "no";
			e.printStackTrace();
			CommonLog.getCommonLogCache().errorMessage("票据导入失败");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "票据导入失败");
		}
		return rs;
	}

	/**
	 * 处理导入的excel的成功或者失败的数据，并返回处理结果
	 * @param status
	 * @return
	 */
	public ImportResultBean handleExcelData(ImportExcel ie,String billType) throws Exception {
		String workDate = DateTimeUtil.getWorkday();
		//重复票验证
		Map<String,String> billNoMap = new HashMap<String,String>();
		//成功后保存的内存，在用户点击确认导入后从内存中读取数据插入数据库
		List<DiscBillInfo> billList = Lists.newArrayList();
		//处理成功的数据行
		List<List<Object>> successDataList = Lists.newArrayList();
		//处理错误的的数据行
		List<List<Object>> failDataList = Lists.newArrayList();
		//开始行
		int start = ie.getDataRowNum();
		int end = ie.getLastDataRowNum();//结束行
		int colNum = ie.getLastCellNum();//结束列
		for (int i = start; i <= end; i++) {
			Row row = ie.getRow(i);
			List<Object> successList = Lists.newArrayList();//每一行的成功数据列表
			List<Object> failList = Lists.newArrayList();//每一行的失败数据列表
			DiscBillInfo bill = new DiscBillInfo();
			for (int j = 0 ; j < colNum; j++){
				String val = ie.getCellValueDeleteNaNDecimalPoint(row, j);
				//验证每一列数据规则 成功返回原值，失败返回错误信息
				String value = checkValue(bill,val, j, billType,workDate);
				if( val.equals(value) ){
					if( j == 0 ){
						if(StringUtils.isBlank(billNoMap.get(val))){
							billNoMap.put(val, val);
							//成功数据添加到成功数据列表
							successList.add(val);
						}else{
							value = "票号：["+val+"]已存在";
						}
					}else{
						if( j == 1 ){
							value = "1".equals(billType) ? "银票" : "商票";
						}else if( j == 14 ){
							value = "0".equals(val) ? "异地":"同城";
						};
						//成功数据添加到成功数据列表
						successList.add(value);
					}
				}
				failList.add(value);
			}
			//最后该行成功的数量与成功数据列表中的数据个数相同，表明改行没有失败数据，反之改行数据不正确
			if( failList.size() == successList.size() ){
				successDataList.add(successList);
				failList = null;//成功后把失败列表置空，有利于垃圾回收
				billList.add(bill);
			}else{
				String one = "第"+(i+1)+"行错误   "+failList.get(0);
				failList.remove(0);failList.add(0, one);
				failDataList.add(failList);
				successList = null;//失败后把成功列表置空，有利于垃圾回收
			}
		}
		HttpSession session = ContextHolderUtils.getSession();
		session.setAttribute("successData", billList);
		//session.setAttribute("failDataList", failDataList);
		return new ImportResultBean(getTitleList(ie),successDataList,failDataList);
	}
	
	/**
	 * 验证地i列的值 如果通过返回值 如果失败返回原因
	 * @param val
	 * @param i
	 * @return
	 * @throws BizAppException 
	 */
	@Override
	protected String checkValue(Object obj,String val,int i,String billType,String workDate) throws BizAppException{
		DiscBillInfo bill = (DiscBillInfo)obj;
		//第一列验证票号
		if( i == 0 ){
			bill.setBillNo(val);
			return checkBillNo(val,billType);
		}
		//第2列验证票类型
		if( i == 1 ){
			bill.setBillType(billType);
			if(billType.equals(val) && ("1".equals(billType) || "2".equals(billType))){
				return val;
			}else{
				return "票号类型：["+val+"]错误";
			}
		}
		//第3列验证出票日期
		if( i == 2 ){
			bill.setIssueDt(val);
			if(checkDateFormat(val)){
				String rsCheck = checkDate(val, "",workDate);
				if("yes".equals(rsCheck)){
					return val;
				}
			}else{
				return "出票日期格式不正确:"+val;	
			}
			
		}
		//第4列验证票面到期日期
		if( i == 3 ){
			bill.setDueDt(val);
			if(checkDateFormat(val)){
				if( -1 == DateTimeUtil.parseStringToDate(val).compareTo(DateTimeUtil.getWorkdayDate()) ) {
					return "票面到期日:"+val+" 不能小于当前营业日";
				}
				return val;
			}else{
				return "票面到期日格式不正确:"+val;	
			}
		}
		
		//第5列验证出票金额
		if( i == 4 ){
			bill.setBillMoney(Double.valueOf(val));
			return checkMoney(val);
		}
		
		//第6列验证承兑人名称
		if( i == 5 ){
			bill.setAcceptor(val);
			if(StringUtils.isEmpty(val)){
				return "承兑人名称不可以为空";	
			}
			return val;
		}
		//第7列验证出票人
		if( i == 6 ){
			bill.setRemitter(val);
			if(StringUtils.isEmpty(val)){
				return "出票人不可以为空";	
			}
			return val;
		}
		//第8列验证出票人账号
		if( i == 7 ){
			bill.setRemitterAcct(val);
			if(!StringUtils.isEmpty(val)){
				if(!checkNumber(val)){
					return "出票人账号格式不正确:"+val;	
				}
			}
			return val;
		}
		//第9列验证出票人开户行行号
		if( i == 8 ){
			bill.setRemitterBankNo(val);
			if(!checkNumber(val)){
				return "出票人开户行行号不正:"+val;	
			}
			return val;
		}		
		
		//第10列验出票人开户行名称
		if( i == 9 ){
			bill.setRemitterBankName(val);
			bill.setAcceptorBankName(val);
			if(StringUtils.isEmpty(val)){
				return "出票人开户行名称不可以为空";	
			}
			return val;
		}
		//第11列验证收款人
		if( i == 10 ){
			bill.setPayee(val);
			if(StringUtils.isEmpty(val)){
				return "收款人不可以为空";	
			}
			return val;
		}
		
		//第12列验证收款人账号
		if( i == 11 ){
			bill.setPayeeAcct(val);
			if(!StringUtils.isEmpty(val)){
				if(!checkNumber(val)){
					return "收款人账号格式不正确:"+val;	
				}
			}
			return val;
		}
		//第13列验收款人开户行
		if( i == 12 ){
			bill.setPayeeBankName(val);
			return val;
		}
		
		//第14列验证直接前手
		if( i == 13 ){
			bill.setBillBeforeOwner(val);
			return val;
		}
		
		//第15列验证是否同城
		if( i == 14 ){
			bill.setIsSameCity(val);
			if("1".equals(val) || "0".equals(val)){
				return val;
			}else{
				return "是否同城数值：["+billType+"]错误";
			}
		}
		
		return val;
	}
}
