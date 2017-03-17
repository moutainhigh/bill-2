/********************************************
 * 文件名称: RcSaleService.java
 * 系统名称: 基础技术平台V2.0
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: 2.0.0.1
 * 开发人员: zcz
 * 开发时间: 2016-8-24 下午01:48:52
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期    修改人员    修改说明
 *********************************************/
package com.herongtech.rc.service.rcservice;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.herongtech.console.core.common.json.Page;
import com.herongtech.console.core.constant.IDict;
import com.herongtech.console.core.constant.RcConstants;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.rc.domain.bean.RcBaseSearchBean;
import com.herongtech.rc.domain.bean.RgctBill;
import com.herongtech.rc.domain.bean.RgctBillData;
import com.herongtech.rc.domain.bean.RgctMethod;
import com.herongtech.rc.domain.dao.RgctMethodDao;

public class RcSaleService extends RcBaseService implements IRcSaleService {
	public static final String HERONGTECH_VERSION = "@system 票据管理平台 @version 2.0.0.1 @lastModiDate @describe ";
	private RgctMethodDao rgctMethodDao=new RgctMethodDao();
	
	@Override
	public List<RgctBillData> queryPresaleBill(RcBaseSearchBean sb,Page page) throws BizAppException {
		RgctMethod method;
		try {
			sb.setIsLock(IDict.K_LOCK.K_LOCK_NO);
			method = rgctMethodDao.getRgctMethod("IRcSaleService","queryPresaleBill");
			return queryRC(sb, page, method, false,sb.getBillClass());
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BizAppException(e);
		}
	}
	@Override
	public void saleEndorse(RgctBill bill) throws BizAppException {
		
		//检查前置状态
		RgctMethod method;
		try {
			method = rgctMethodDao.getRgctMethod("IRcSaleService","saleEndorse");
			if(RcConstants.REDISCOUNT_CENTER.equals(bill.getHist().getIsRediscCenter())){
				method.setParam("1");
			} else {
				method.setParam("0");
			}
			this.checkPreCondi(bill, method, null);
			String methodName = "";
			if(RcConstants.REGRESS_YES.equals(bill.getHist().getIsRegress())){
				methodName = RcConstants.COMMON_REDISCOUNT2;
			} else {
				methodName = RcConstants.COMMON_REDISCOUNT1;
			}
			if(!IDict.K_BILL_CLASS.K_ENTY_BILL.equals(bill.getInfo().getBillClass())){
				// 发送报文
				sendDraft(bill, method,RcConstants.DRAFT_APPLY,null);
			}else{
				changeStatus(bill, rgctMethodDao.getRgctMethod("commonStatus",methodName ), RcConstants.SIGN_YES);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BizAppException(e.getMessage());
		}
	}
	@Override
	public void cancelSaleEndorse(RgctBill bill) throws BizAppException {
		//检查前置状态
		RgctMethod method;
		try {
			method = rgctMethodDao.getRgctMethod("IRcSaleService","cancelSaleEndorse");
			this.checkPreStatus(bill, method, null);
			if(!IDict.K_BILL_CLASS.K_ENTY_BILL.equals(bill.getInfo().getBillClass())){
				// 发送报文
				sendDraft(bill, method,RcConstants.DRAFT_APPLY,RcConstants.TYPE_CANCEL);
			}else{
				processBill(bill, method, "0");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BizAppException(e.getMessage());
		}
	}
	
	public RgctBill regAgreeEndorse(RgctBill bill) throws BizAppException {
		bill.getHist().setIfInner("0");
		String paramRediscCenter = bill.getHist().getIsRediscCenter();
		if (StringUtils.isBlank(paramRediscCenter))
			paramRediscCenter = "0";
		String paramRegress = bill.getHist().getIsRegress().trim();
		if (StringUtils.isBlank(paramRegress))
			paramRegress = "0";
		String param= paramRegress + paramRediscCenter;
		try {
			return changeStatus(bill,rgctMethodDao.getRgctMethod("IRcSaleService","regAgreeEndorse") , param);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BizAppException(e.getMessage());
		}
	}
	public RgctBill regCancelSign(RgctBill bill) throws BizAppException{
		
		try {
			return changeStatus(bill,rgctMethodDao.getRgctMethod("IRcSaleService","regCancelSign"), null);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BizAppException(e.getMessage());
		}
	}
	
}
