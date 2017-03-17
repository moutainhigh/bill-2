/********************************************
 * 文件名称: RiskBillService.java
 * 系统名称: 基础技术平台V2.0
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: 2.0.0.1
 * 开发人员: fanmin
 * 开发时间: 2016年9月20日11:46:20
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期    修改人员    修改说明
 *********************************************/

package com.herongtech.console.service.riskbill;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.herongtech.console.core.util.DateTimeUtil;
import com.herongtech.console.domain.bean.BillInfoDTO;
import com.herongtech.console.domain.bean.RiskBill;
import com.herongtech.console.domain.dao.RiskBillDao;
import com.herongtech.console.service.interfaces.IRiskBillService;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.sysconstant.ISysErrorNo;

/**
 * 系统参数取法
 *
 */
public class RiskBillService implements IRiskBillService{
	public static final String HUNDSUN_VERSION="@system 票据管理平台 @version 2.0.0.1 @lastModiDate @describe ";

	/**
	 * 获取系统参数表
	 */
	public  RiskBill getRiskBill(String id)throws BizAppException{
		RiskBillDao dao=new RiskBillDao();
		try {
			return dao.getRiskBill(id);
		}catch (SQLException e) {
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "");
		}
	}
	
	/**
	 * 插入风险票表
	 */
	public void addRiskBill(RiskBill riskbill)throws BizAppException{
		RiskBillDao dao=new RiskBillDao();
		try {
			if (dao.addRiskBill(riskbill) != 1) {
			      throw new BizAppException(ISysErrorNo.ERR_DBERR, "增加Param失败");
			}
		} catch (SQLException e) {
             throw new BizAppException(ISysErrorNo.ERR_DBERR,"");
		}
		
	}
	/**
	 * 修改风险票表
	 */
	public void modifyRiskBill(RiskBill riskbill)throws BizAppException{
		RiskBillDao dao=new RiskBillDao();
		try {
			if (dao.modifyRiskBill(riskbill) != 1) {
				 throw new BizAppException(ISysErrorNo.ERR_DBERR, "修改RiskBill失败");
			}
		} catch (SQLException e) {
             throw new BizAppException(ISysErrorNo.ERR_DBERR,"");
		}
		
	}

	public List<RiskBill> getRiskBillByBills(List<BillInfoDTO> bills)throws BizAppException{
		RiskBillDao dao=new RiskBillDao();
		StringBuilder sb = new StringBuilder(bills.size()*(8+3) + 50);
		for (BillInfoDTO bill : bills) {
			if(!bill.isNeedCheckRiskBill()) {	
				continue;
			}
			sb.append("'").
			append(StringUtils.right(bill.getBillNo(), 8)).
			append("',");
		}
		if(sb.length() == 0) {
			return new ArrayList<RiskBill>();
		}
		sb.setLength(sb.length() - 1);
		String billNos = sb.toString();
		String workDay = DateTimeUtil.getWorkdayString();//当前营业日
		String createDay = DateTimeUtil.getDate(workDay,  -6*30);//录入日期，营业日倒退六个月
		try {
			return dao.getRiskBillByBills(billNos, workDay, createDay);
		} catch (SQLException e) {
             throw new BizAppException(ISysErrorNo.ERR_DBERR,"");
		}
	}

}
