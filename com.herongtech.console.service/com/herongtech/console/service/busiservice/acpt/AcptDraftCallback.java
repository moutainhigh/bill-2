/********************************************
* 文件名称: AcptDraftCallback.java
* 系统名称: 合融基础技术平台V2.0
* 模块名称:
* 软件版权: 北京合融科技有限公司
* 功能说明: 
* 系统版本: 2.0.0.1
* 开发人员: zcz
* 开发时间: 2016/8/25 
* 审核人员:
* 相关文档:
* 修改记录: 修改日期    修改人员    修改说明
*********************************************/
package com.herongtech.console.service.busiservice.acpt;

import java.sql.SQLException;

import com.herongtech.console.core.constant.IDict;
import com.herongtech.console.core.constant.RcConstants;
import com.herongtech.console.core.util.StatusUtils;
import com.herongtech.console.domain.acpt.bean.AcptBillInfo;
import com.herongtech.console.domain.acpt.dao.AcptBillInfoDao;
import com.herongtech.console.domain.acpt.dao.AcptColltnRegDao;
import com.herongtech.console.domain.bean.Branch;
import com.herongtech.console.domain.dao.BranchDao;
import com.herongtech.console.service.ServiceFactory;
import com.herongtech.console.web.busicontroller.common.AcptCodeConst;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.rc.domain.bean.RgctBill;
import com.herongtech.rc.domain.bean.RgctBillInfo;
import com.herongtech.rc.service.rcservice.ITrigger;

public class AcptDraftCallback implements ITrigger{
	public static final String HERONGTECH_VERSION = "@system 票据管理平台 @version 2.0.0.1 @lastModiDate @describe ";
	
	IAcptService acptService= ServiceFactory.getAcptService();
	private AcptBillInfoDao acptBillInfoDao=new AcptBillInfoDao();
	private AcptColltnRegDao acptColltnRegDao=new AcptColltnRegDao();
	
	
	@Override
	public void transFor034execute(RgctBill rgctBill) throws BizAppException {//
		try{
			RgctBillInfo info=rgctBill.getInfo();
			AcptBillInfoDao dao=new AcptBillInfoDao();
			if(IDict.K_BILL_TYPE.K_CORP_BILL.equals(info.getBillType())){
				return;
			}
			AcptBillInfo oldBill = dao.getAcptBillInfoByBillNo(info.getBillNo());
			if(oldBill != null){
				dao.deleteAcptBillInfo(oldBill);
			}
			AcptBillInfo bill = new AcptBillInfo();
			String id = ServiceFactory.getSequenceService().getACPT_BILL_INFO_ID();
			bill.setAcptmxId(id);
			bill.setRgctId(info.getId());
			// 出票人名称
			bill.setRemitter(info.getRemitter());
			// 出票人账号
			bill.setRemitterAcct(info.getRemitterAcct());
			//出票人客户号
			bill.setRemitterCustNo(info.getRemitterCustNo());
			// 出票人开户行行名
			bill.setRemitterBankName(info.getRemitterBankName());
			// 出票人开户行行号
			bill.setRemitterBankNo(info.getRemitterBankNo());
			//付款日
			bill.setPaymentDt(info.getDueDt());
			//出票人开户机构
			BranchDao brchdDao=new BranchDao();
	    	Branch brch=null;
			try {
				brch = brchdDao.getBranch(info.getRemitterBankNo(),null);
			} catch (SQLException e) {
				e.printStackTrace();
				throw new BizAppException(e.getMessage());
			}
			if(brch!=null){
				bill.setRemitterBranchNo(brch.getBranchNo());
				
			}
			//签发机构
			try {
				brch = brchdDao.getBranch(info.getAcceptorBankNo(),null);
			} catch (SQLException e) {
				e.printStackTrace();
				throw new BizAppException(e.getMessage());
			}
			if(brch!=null){
				bill.setBranchNo(brch.getBranchNo());
				
			}
			// 收款人名称
			bill.setPayee(info.getPayeeName());
			// 收款人账号
			bill.setPayeeAcct(info.getPayeeAcct());
			// 收款行全称
			bill.setPayeeBankName(info.getPayeeBankName());
			bill.setPayeeBankNo(info.getPayeeBankNo());
			bill.setDraweeBankNo(info.getAcceptorBankNo());
			bill.setDraweeBankName(info.getAcceptorBankName());
			bill.setDraweeAddr(info.getDraweeAddr());
			// 出票日期
			bill.setIssueDt(info.getIssueDt());
			// 到期日期
			bill.setDueDt(info.getDueDt());
			// 票据号码
			bill.setBillNo(info.getBillNo());
			// 票据金额
			bill.setBillMoney(info.getBillMoney());
			// 承兑人名称
			bill.setAcceptor(info.getAcceptor());
			// 承兑人账号
			bill.setAcceptorAcct(info.getAcceptorAcct());
			// 承兑行行名
			bill.setAcceptorBankName(info.getAcceptorBankName());
			// 承兑行行号
			bill.setAcceptorBankNo(info.getAcceptorBankNo());
			// 是否可以转让
//			bill.setBankRemark(bankRemark)(info.getForbidFlag());
			// 票据分类
			bill.setBillClass(info.getBillClass());
			// 票据种类
			bill.setBillType(info.getBillType());
			
			bill.setRequestMsgId(info.getReqDraftId());
			
			bill.setStatus(StatusUtils.handleStatusNoCheck("AcptApplyController", "transFor034", null));//收到
			bill.setBillStatus(AcptCodeConst.BILL_STATUS_DEFAULT);
			bill.setCurrencyCategory("RMB");
			bill.setVoucherType(AcptCodeConst.VOUCHER_TYPE_AC01);
			dao.addAcptBillInfo(bill);
		}catch (Exception e) {
			//回调失败记录日志
		}
		
	}

	@Override
	public void applyFor033execute(RgctBill rgctBill, boolean isSuccess)
			throws BizAppException {
	}	
	/**
	 * 031签收-033的回调
	 * @param rgctBill
	 * @param isSign
	 * @param isSuccess
	 * @throws Exception 
	 */
	@Override
	public void signFor033execute(RgctBill rgctBill,String isSign,//签收成功的票据处理，拒绝不处理
			boolean isSuccess) throws Exception {
		RgctBillInfo info = rgctBill.getInfo();
		String signFlag =isSign;
		boolean isSign1 = RcConstants.SIGN_YES.equals(signFlag);
		boolean isSign2 = RcConstants.SIGN_NO.equals(signFlag);
		final String billNo = info.getBillNo();
		//LogTool.log_ba("票号：" + billNo + "承兑签收回调处理开始");
		if(isSuccess && isSign1){//处理签收成功的票据
			if(IDict.K_BILL_TYPE.K_BANK_BILL.equals(info.getBillType())){
				acptService.acceptanceAccountElec(rgctBill);
			//}else if(IDict.K_BILL_TYPE.K_CORP_BILL.equals(info.getBillType())){商票
//				acptService.acceptanceElecCorp(rgctBill);//商票
			}
		}else{
			//回调失败记录日志
		}
		if(isSuccess && isSign2){//处理签收拒绝的票据
			if(IDict.K_BILL_TYPE.K_BANK_BILL.equals(info.getBillType())){
				acptService.acceptanceAccountElec2(rgctBill);
			//}else if(IDict.K_BILL_TYPE.K_CORP_BILL.equals(info.getBillType())){商票
//				acptService.acceptanceElecCorp(rgctBill);//商票
			}
		}else{
			//回调失败记录日志
		}
		
		if(!isSuccess && isSign2){//处理拒绝签收失败的票据
			if(IDict.K_BILL_TYPE.K_BANK_BILL.equals(info.getBillType())){
				acptService.acceptanceAccountElec2(rgctBill);//拒绝失败时调用
			//}else if(IDict.K_BILL_TYPE.K_CORP_BILL.equals(info.getBillType())){商票
//				acptService.acceptanceElecCorp(rgctBill);//商票
			}
		}else{
			//回调失败记录日志
		}
	    //承兑签收回调处理结束
}

	@Override
	public void transFor031execute(RgctBill rgctBill, String isSign)
			throws BizAppException {
		
	}
	
	@Override
	public void transFor032execute(RgctBill rgctBill) throws BizAppException {	   
	
	}

	@Override
	public void cancelFor033execute(RgctBill rgctBill, boolean isSuccess)
			throws BizAppException {
		
	}
	
	@Override
	public void transFor035execute(RgctBill rgctBill) throws BizAppException {
		AcptBillInfoDao acptDao=new AcptBillInfoDao();
		String rgctId = rgctBill.getInfo().getId();
		try {
			AcptBillInfo acptBill = acptDao.getAcptBillInfoByRgctId(rgctId);
			if (acptBill == null) {
				throw new BizAppException("票据不存在");
			}
			acptBill.setStatus("BS400");
			acptDao.modifyAcptBillInfo(acptBill);
		} catch (SQLException e) {
			throw new BizAppException("查询票据失败，"+e.getMessage());
		}
		
	}
}
