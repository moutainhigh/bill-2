/********************************************
 * 文件名称: RcRebuyService.java
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

import com.herongtech.console.core.constant.IDict;
import com.herongtech.console.core.constant.RcConstants;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.rc.domain.bean.RgctBill;
import com.herongtech.rc.domain.bean.RgctMethod;
import com.herongtech.rc.domain.dao.RgctMethodDao;

public class RcRebuyService extends RcBaseService implements IRcRebuyService{
	public static final String HERONGTECH_VERSION = "@system 票据管理平台 @version 2.0.0.1 @lastModiDate @describe ";
	
	private RgctMethodDao rgctMethodDao=new RgctMethodDao();

	@Override
	public RgctBill inputSignRebuy(RgctBill bill) throws BizAppException {
		RgctMethod method;
		try {
			method = rgctMethodDao.getRgctMethod("IRcRebuyService","inputSignRebuy");
			return addBill( bill,method,bill.getHist().getIsRegress());
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BizAppException(e.getMessage());
		}
	}

	@Override
	public void rebuyEndorseSign(RgctBill bill) throws BizAppException {
		//检查前置状态
		RgctMethod method;
		try {
			RgctValidateUtils.checkElecResponse(bill);
			method = rgctMethodDao.getRgctMethod("IRcRebuyService","rebuyEndorseSign");
			this.checkPreStatus(bill, method, null);
			String isRegress = bill.getHist().getIsRegress();
			String isInner = bill.getHist().getIfInner();
			/*记录票据痕迹*/
			if(RcConstants.REGRESS_NO.equals(isRegress)){
				if(RcConstants.INNER_YES.equals(isInner)){//3：系统内转入
					String trackOld = bill.getHist().getBillTrack();
					if (!"3".equals(trackOld.substring(trackOld.length() - 1))) {
						bill.getHist().setBillTrack(trackOld.concat("3"));//如果没有做过系统内就增加"3"
					}
				}else{
					bill.getHist().setBillTrack("2");
				}
			}else if(RcConstants.REGRESS_YES.equals(isRegress)){
				if(RcConstants.INNER_NO.equals(isInner)){//同业买入返售 置为NULL
					bill.getHist().setBillTrack(null);
				}else{
					String trackOld = bill.getHist().getBillTrack();
					if (!"3".equals(trackOld.substring(trackOld.length() - 1))) {
						bill.getHist().setBillTrack(trackOld.concat("3"));//如果没有做过系统内就增加"3"
					}
				}
			}
			if(RcConstants.REDISCOUNT_CENTER.equals(bill.getHist().getIsRediscCenter())) {
				//再贴现
				method.setParam("1");
			} else {
				method.setParam("0");
			}
			if(!IDict.K_BILL_CLASS.K_ENTY_BILL.equals(bill.getInfo().getBillClass())){
				// 发送报文
				sendDraft(bill, method,RcConstants.DRAFT_SIGN,RcConstants.SIGN_YES);
			}else{
				String param = "1".equals(isRegress) ? "1" : "2";
				changeStatus(bill, method, param);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BizAppException(e.getMessage());
		}
	}

	@Override
	public void rejectRebuyEndorse(RgctBill bill) throws BizAppException {
		//检查前置状态
		RgctMethod method;
		try {
			method = rgctMethodDao.getRgctMethod("IRcRebuyService","rejectRebuyEndorse");
			this.checkPreStatus(bill, method, null);
			if(RcConstants.REDISCOUNT_CENTER.equals(bill.getHist().getIsRediscCenter())) {
				//再贴现
				method.setParam("1");
			} else {
				method.setParam("0");
			}
			if(!IDict.K_BILL_CLASS.K_ENTY_BILL.equals(bill.getInfo().getBillClass())){
				// 发送报文
				sendDraft(bill, method,RcConstants.DRAFT_SIGN,RcConstants.SIGN_NO);
			}else{
				this.processBill(bill, method, "0");//待测试
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BizAppException(e.getMessage());
		}
	}
	
	
}
