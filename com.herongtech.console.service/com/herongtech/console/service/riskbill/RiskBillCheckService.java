/********************************************
 * 文件名称: RiskBillCheckService.java
 * 系统名称: 基础技术平台V2.0
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: 2.0.0.1
 * 开发人员: fanmin
 * 开发时间: 2016年9月20日11:45:51
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期    修改人员    修改说明
 *********************************************/
package com.herongtech.console.service.riskbill;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.MethodUtils;
import org.apache.commons.lang.StringUtils;

import com.herongtech.appconfig.SysConfigUtil;
import com.herongtech.beancontainer.BeanContainerFactory;
import com.herongtech.console.core.constant.IConstants;
import com.herongtech.console.domain.acct.bean.AcctBalance;
import com.herongtech.console.domain.bean.BillInfoDTO;
import com.herongtech.console.domain.bean.BlackList;
import com.herongtech.console.domain.bean.RiskBill;
import com.herongtech.console.service.ServiceFactory;
import com.herongtech.console.service.interfaces.IAcctBalanceService;
import com.herongtech.console.service.interfaces.IBlackService;
import com.herongtech.console.service.interfaces.IRiskBillCheckService;
import com.herongtech.console.service.interfaces.IRiskBillService;
import com.herongtech.exception.impl.BizAppException;

public class RiskBillCheckService implements IRiskBillCheckService {

	public static String riskBillCheckSwitch = SysConfigUtil.getSysConfig().getValue("riskBillCheckSwitch");
	public static String blackListCheckSwitch = SysConfigUtil.getSysConfig().getValue("blackListCheckSwitch");
	public static String balanceCheckSwitch = SysConfigUtil.getSysConfig().getValue("balanceCheckSwitch");
	IRiskBillService riskbillService = ServiceFactory.getRiskBillService();
	IBlackService blackService = ServiceFactory.getBlackService();
	IAcctBalanceService acctBalanceService = ServiceFactory.getAcctBalanceService();

	public List<BillInfoDTO> checkRiskInfo(String methodName,String serviceName, String ids) throws BizAppException {
		List<BillInfoDTO> billList = null;
		boolean hasRisk = false;// 默认没有风险票
		boolean hasBlack = false;// 默认没有黑名单
		boolean hasBalance = false;// 默认没有库存余额的票
		// 获得票据信息
		List<BillInfoDTO> bills = this.getBillInfoDTO(methodName, serviceName,ids);

		// 黑名单检查
		if (StringUtils.equals(blackListCheckSwitch, IConstants.YES)) {
			List<BlackList> blackList = blackService.getBlackListByBills(bills);
			billMappingBlackList(blackList, bills);
			hasBlack = blackList.size() > 0;
		}

		// 风险票据检查
		if (StringUtils.equals(riskBillCheckSwitch, IConstants.YES)) {
			List<RiskBill> riskBillList = riskbillService.getRiskBillByBills(bills);
			hasRisk = billMappingRiskBill(riskBillList, bills);
		}
		// 账务余额检查
			if (StringUtils.equals(balanceCheckSwitch, IConstants.YES)) {
				List<AcctBalance> acctBalanceList = acctBalanceService.getAcctBalanceByBillNo(bills);
				billMappingAcctBalanceBill(acctBalanceList, bills);
				hasBalance = acctBalanceList.size() > 0;
			}
		// 过滤检查结果
		billList = (hasBlack || hasRisk||hasBalance) ? filertBill(bills) : billList;
		return billList;
	}

	/**
	 * 过滤出存在风险的票
	 * 
	 * @param bills	所有票据集合
	 * @return 风险票集合
	 */
	private List<BillInfoDTO> filertBill(List<BillInfoDTO> bills) {
		List<BillInfoDTO> billList = new ArrayList<BillInfoDTO>();
		for (BillInfoDTO bill : bills) {
			if ((bill.getBlackList() == null)&& (bill.getRiskBillList() == null)&& (bill.getAcctBalanceBillList()== null)) {
				continue;
			}
			billList.add(bill);
		}
		return billList;
	}

	/**
	 * 使用各个业务提供的查询转换方法获取统一的DTO对象集合
	 * 
	 * @param methodName	方法名称
	 * @param serviceName	服务名称
	 * @param ids	票据ids
	 * @return 
	 * @throws BizAppException
	 */
	@SuppressWarnings("unchecked")
	private List<BillInfoDTO> getBillInfoDTO(String methodName,String serviceName, String ids) throws BizAppException {
		Object bean = BeanContainerFactory.getBeanContainer(
				BeanContainerFactory.class.getClassLoader()).getBean(serviceName);
		Object obj;
		try {
			obj = MethodUtils.invokeExactMethod(bean, methodName,
					new Object[] { ids });
		} catch (NoSuchMethodException e) {
			throw new BizAppException("服务名对应的服务没有" + methodName + "方法。");
		} catch (IllegalAccessException e) {
			throw new BizAppException("传的参数不正确。");
		} catch (InvocationTargetException e) {
			throw new BizAppException("服务名对应的服务调用异常。");
		}
		return (List<BillInfoDTO>) obj;
	}

	/**
	 * 黑名单与票据的映射
	 * 
	 * @param blackList	黑名单
	 * @param bills	票据
	 */
	private void billMappingBlackList(List<BlackList> blackList,List<BillInfoDTO> bills) {
		if (blackList.size() == 0) {
			return;
		}
		int mapSize = (int) (blackList.size() / 0.75f + 1);
		Map<String, BlackList> blMap = new HashMap<String, BlackList>(mapSize);
		for (BlackList bl : blackList) {
			blMap.put(bl.getUnionBankno(), bl);
		}
		for (BillInfoDTO bill : bills) {
			String desc = "";
			String enterpriseName = "";
			if (bill.getCheckBankNo() == null) {
				continue;
			}
			for (String bankNo : bill.getCheckBankNo()) {
				bill.addBlackList(blMap.get(bankNo));
				if(blMap.get(bankNo)!=null){
					desc+="行号："+bankNo+"存在于黑名单中；<br>";
					enterpriseName +=blMap.get(bankNo).getEnterpriseName()+";<br>";
				}
			}
			bill.setDesc(desc);
			bill.setEnterpriseName(enterpriseName);
		}
	}

	/**
	 * 风险票校验 如果存在风险票将风险票添加到票据信息的风险票属性中
	 * 
	 * @param riskBillList	风险票集合
	 * @param bills	 待验证的票据集合
	 * @return 是否含有风险票
	 */
	private boolean billMappingRiskBill(List<RiskBill> riskBillList,
			List<BillInfoDTO> bills) {
		boolean hasRisk = false;
		if (riskBillList.size() == 0) {
			return hasRisk;
		}
		// 按票号分组
		Map<String, List<RiskBill>> billMap = riskBillGroupByBillNo(riskBillList);
		// 票据信息与风险票匹配
		List<RiskBill> tempList;
		for (BillInfoDTO bill : bills) {
			tempList = billMap.get(StringUtils.right(bill.getBillNo(), 8));
			if (tempList == null) {
				continue;
			}
			for (RiskBill riskBill : tempList) {
				if (billCompare(bill, riskBill)) {
					bill.addRiskBill(riskBill);
					hasRisk = true;
				}
			}
		}
		return hasRisk;
	}
	/**
	 * 风险票校验 如果存在风险票将风险票添加到票据信息的风险票属性中
	 * 
	 * @param riskBillList	风险票集合
	 * @param bills	 待验证的票据集合
	 * @return 是否含有风险票
	 */
	private boolean billMappingAcctBalanceBill(List<AcctBalance> acctBalanceList,List<BillInfoDTO> bills) {
		boolean hasRisk = false;
		if (acctBalanceList.size() == 0) {
			return hasRisk;
		}
		// 按票号分组
		Map<String, List<AcctBalance>> billMap = acctBalanceGroupByBillNo(acctBalanceList);
		// 票据信息与风险票匹配
		List<AcctBalance> tempList;
		for (BillInfoDTO bill : bills) {
			tempList = billMap.get(bill.getBillNo());
			if (tempList == null) {
				continue;
			}
			for (AcctBalance acctBalance : tempList) {
				if (acctBalanceCompareBillInfoDTO(bill, acctBalance)) {
					bill.addAcctBalanceBill(acctBalance);
					hasRisk = true;
				}
			}
		}
		return hasRisk;
	}

	/**
	 * 票据信息和风险票比对匹配
	 * @param bill	票据信息
	 * @param riskBill	风险票
	 * @return	是否匹配成功
	 */
	private boolean billCompare(BillInfoDTO bill, RiskBill riskBill) {
		// 票号相同
		if (bill.getBillNo().equals(riskBill.getBill1() + riskBill.getBill2())) {
			return true;
		}
		// 票面金额相同
		if (isEqual(bill.getBillMoney(), riskBill.getBillMoney())) {
			return true;
		}
		// 出票日期相同
		if (isEqual(bill.getIssueDt(), riskBill.getIssueDt())) {
			return true;
		}
		// 票面到期日期相同
		if (isEqual(bill.getDueDt(), riskBill.getDueDt())) {
			return true;
		}
		return false;
	}
	/**
	 * 票据信息和账务库存比对匹配
	 * 所有比对的票号是相同的，预留方法用于比对其他票面要素
	 * 当前版本只要票号相同就认为是匹配成功（实际纸票有可能存在票号相同的票）
	 * 可根据登记中心ID查询票面要素进行比对
	 * @param bill	票据信息
	 * @param acctBalance	账务库存票
	 * @return	是否匹配成功
	 */
	private boolean acctBalanceCompareBillInfoDTO(BillInfoDTO bill, AcctBalance acctBalance) {
		// 票面金额相同
		// 出票日相同
		// 到期日相同
		//承兑行行号相同
		return true;
	}

	/**
	 * 对象之间对比，调用对象的对比方法
	 * @param objA	对象A
	 * @param objB		对象B
	 * @return	是否一致
	 */
	@SuppressWarnings("unchecked")
	private <T> boolean isEqual(Comparable<T> objA, Comparable<T> objB) {
		if (objA == null || objB == null) {
			return false;
		}
		return objA.compareTo((T) objB) == 0;
	}

	/**
	 * 风险票按照票号（后8位）分组
	 * @param riskBillList	风险票集合
	 * @return	票号为key，风险票集合为value的map
	 */
	private Map<String, List<RiskBill>> riskBillGroupByBillNo(List<RiskBill> riskBillList) {
		int size = (int) (riskBillList.size() + 1);
		Map<String, List<RiskBill>> billMap = new HashMap<String, List<RiskBill>>(size, 1);
		List<RiskBill> tempList;
		for (RiskBill bill : riskBillList) {
			tempList = billMap.get(bill.getBill2());
			if (tempList == null) {
				tempList = new ArrayList<RiskBill>();
				billMap.put(bill.getBill2(), tempList);
			}
			tempList.add(bill);
		}
		return billMap;
	}
	/**
	 * 风险票按照票号（后8位）分组
	 * @param riskBillList	风险票集合
	 * @return	票号为key，风险票集合为value的map
	 */
	private Map<String, List<AcctBalance>> acctBalanceGroupByBillNo(List<AcctBalance> acctBalanceList) {
		int size = (int) (acctBalanceList.size() + 1);
		Map<String, List<AcctBalance>> billMap = new HashMap<String, List<AcctBalance>>(size, 1);
		List<AcctBalance> tempList;
		for (AcctBalance bill : acctBalanceList) {
			tempList = billMap.get(bill.getBillNo());
			if (tempList == null) {
				tempList = new ArrayList<AcctBalance>();
				billMap.put(bill.getBillNo(), tempList);
			}
			tempList.add(bill);
		}
		return billMap;
	}

}
