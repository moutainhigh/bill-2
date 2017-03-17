/********************************************
 * 文件名称: AssuDratfCallback.java
 * 系统名称: 票据管理平台V2.0
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: 2.0.0.1
 * 开发人员: dq
 * 开发时间: 2016-11-18 下午02:48:52
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期    修改人员    修改说明
 *******************************************/
package com.herongtech.console.service.busiservice.assu;

import java.sql.SQLException;

import com.herongtech.console.core.constant.RcConstants;
import com.herongtech.console.core.util.DateTimeUtil;
import com.herongtech.console.domain.assu.bean.AssuBillInfo;
import com.herongtech.console.domain.assu.dao.AssuBillInfoDao;
import com.herongtech.console.service.ServiceFactory;
import com.herongtech.console.service.interfaces.ISequenceService;
import com.herongtech.console.web.busicontroller.common.GuarCodeConst;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.rc.domain.bean.RgctBill;
import com.herongtech.rc.domain.bean.RgctBillHist;
import com.herongtech.rc.domain.bean.RgctBillInfo;
import com.herongtech.rc.service.RcServiceFactory;
import com.herongtech.rc.service.rcservice.ITrigger;

/**
 * 保证报文回调处理类
 * @author Administrator
 *
 */
public class AssuDratfCallback implements ITrigger{

	@Override
	public void transFor034execute(RgctBill rgctBill) throws BizAppException {
		ISequenceService SequenceService = ServiceFactory.getSequenceService();
		IAssuService assuService = ServiceFactory.getAssuService();
		RgctBillInfo info = rgctBill.getInfo();
		RgctBillHist hist = rgctBill.getHist();
		AssuBillInfo assuBillInfo = new AssuBillInfo();
		// 保证信息
		assuBillInfo.setBillNo(info.getBillNo());
		assuBillInfo.setBillMoney(info.getBillMoney());
		assuBillInfo.setBillType(info.getBillType());
		assuBillInfo.setBillClass(info.getBillClass());
		assuBillInfo.setWarteeDt(DateTimeUtil.getWorkdayString());
		assuBillInfo.setDueDt(info.getDueDt());
		assuBillInfo.setAcceptor(info.getAcceptor());
		assuBillInfo.setAcceptorBankNo(info.getAcceptorBankNo());
		assuBillInfo.setIssueDt(info.getIssueDt());
		// 被保证人信息
		assuBillInfo.setWarteeCustName(hist.getFromName());//被保证人名称
		assuBillInfo.setWarteeSign(hist.getFromSign());// 被保证人电子签名
		assuBillInfo.setWarteeAcctNo(hist.getFromAcctNo()); // 被保证人账号
		assuBillInfo.setWarteeOrgcode(hist.getFromOrgcode()); // 被保证人组织机构代码
		assuBillInfo.setWarteeBankNo(hist.getFromBankNo()); // 被保证人开户行行号
		//rgctBillAssu.setAgcyBankNo(hist.getfrom); // 承接行行号
		assuBillInfo.setWarteePartnerType(hist.getFromRole());//被保证人参与者类型 1:型企业,2:银行
		assuBillInfo.setWarteeCustNo(hist.getFromCustNo());//被保证人客户号
		// 保证人信息
		assuBillInfo.setGuartrName(hist.getToName()); // 保证人名称
		assuBillInfo.setGuartrBankNo(hist.getToBankNo()); // 保证人开户行行号
		assuBillInfo.setGuarntrAcctNo(hist.getToAcctNo()); // 保证人账号
		//承兑人信息
		assuBillInfo.setAcceptor(info.getAcceptor());//承兑人
		assuBillInfo.setAcceptorBankNo(info.getAcceptorBankNo());//承兑人行号
		//其他信息
		assuBillInfo.setAssumxId(SequenceService.getASSU_BILL_INFO_ID());
		assuBillInfo.setRgctId(info.getId());//登记中心ID
		assuBillInfo.setAssuStatus(GuarCodeConst.ASSU_STATE_preSign);//保证状态
		assuBillInfo.setOperStatus(GuarCodeConst.ASSU_STATUS_11);//操作状态    034报文回调中无需在状态机中进行配置
		assuBillInfo.setAssuType(hist.getAssuType());//保证类别
		//保证类型   如果是系统内，034先回来，当前状态肯定是Q打头，如果是系统外，则033先回来，当前状态就是前置状态
		/*String preStatus = hist.getPreStatus();//前置状态
		String curStatus = hist.getCurStatus();//当前状态
		if("Q".equals(curStatus.substring(0, 1))){
			if(preStatus.startsWith("A")){
				assuBillInfo.setAssuType(GuarCodeConst.ASSU_TYPE_REG_1);//1出票保证
			}else if(preStatus.startsWith("B")){
				assuBillInfo.setAssuType(GuarCodeConst.ASSU_TYPE_ACPT_2);//2承兑保证
			}else{
				assuBillInfo.setAssuType(GuarCodeConst.ASSU_TYPE_ENDO_3);//3背书保证
			}
		}else{
			if(preStatus.startsWith("A")){
				assuBillInfo.setAssuType(GuarCodeConst.ASSU_TYPE_REG_1);//1出票保证
			}else if(preStatus.startsWith("B")){
				assuBillInfo.setAssuType(GuarCodeConst.ASSU_TYPE_ACPT_2);//2承兑保证
			}else{
				assuBillInfo.setAssuType(GuarCodeConst.ASSU_TYPE_ENDO_3);//3背书保证
			}
		}*/
		assuBillInfo.setRgctHistId(rgctBill.getHist().getHistId());
		assuService.addAssuBillInfo(assuBillInfo);
		//锁票
		RcServiceFactory.getRcPresentationService().lock(hist.getRgctId());
	}

	@Override
	public void applyFor033execute(RgctBill rgctBill, boolean isSuccess)
			throws BizAppException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void cancelFor033execute(RgctBill rgctBill, boolean isSuccess) throws BizAppException {
		// TODO Auto-generated method stub
	}

	@Override
	public void signFor033execute(RgctBill rgctBill, String isSign,
			boolean isSuccess) throws Exception {
		IAssuService assuService = ServiceFactory.getAssuService();
		AssuBillInfo assuBillInfo = new AssuBillInfo();
		//031签收
		if(RcConstants.SIGN_YES.equals(isSign)){
			assuBillInfo = assuService.getAssuBillInfoForRgctIdAndOperStatus(rgctBill.getInfo().getId(),GuarCodeConst.ASSU_STATUS_12);
			if(isSuccess){//033成功
				assuBillInfo.setOperStatus(GuarCodeConst.ASSU_STATUS_36);
				assuBillInfo.setAssuStatus(GuarCodeConst.ASSU_STATE_SIGN_YES);//保证状态
			}else {//033失败
				assuBillInfo.setOperStatus(GuarCodeConst.ASSU_STATUS_11);
				assuBillInfo.setAssuStatus(GuarCodeConst.ASSU_STATE_preSign);//保证状态
			}
		//031拒绝
		}else {
			assuBillInfo = assuService.getAssuBillInfoForRgctIdAndOperStatus(rgctBill.getInfo().getId(),GuarCodeConst.ASSU_STATUS_10);
			if(isSuccess){//033c成功
				assuBillInfo.setOperStatus(GuarCodeConst.ASSU_STATUS_00);
				assuBillInfo.setAssuStatus(GuarCodeConst.ASSU_STATE_SIGN_NO);//保证状态
			}else {//033失败
				assuBillInfo.setOperStatus(GuarCodeConst.ASSU_STATUS_11);
				assuBillInfo.setAssuStatus(GuarCodeConst.ASSU_STATE_preSign);//保证状态
			}
		}
		assuService.modifyAssuBillInfo(assuBillInfo);
		//解锁
		RcServiceFactory.getRcPresentationService().unLock(rgctBill.getHist().getRgctId());
	}

	@Override
	public void transFor031execute(RgctBill rgctBill, String isSign)
			throws BizAppException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void transFor032execute(RgctBill rgctBill) throws BizAppException {
		IAssuService assuService = ServiceFactory.getAssuService();
		AssuBillInfo assuBillInfo = assuService.getAssuBillInfoForRgctIdAndOperStatus(rgctBill.getInfo().getId(),GuarCodeConst.ASSU_STATUS_11);	
		assuBillInfo.setOperStatus(GuarCodeConst.ASSU_STATUS_01);
		assuBillInfo.setAssuStatus(GuarCodeConst.ASSU_STATE_CANCEL_APPLY);//保证状态
		assuService.modifyAssuBillInfo(assuBillInfo);
	}

	@Override
	public void transFor035execute(RgctBill rgctBill) throws BizAppException {
		AssuBillInfoDao dao = new AssuBillInfoDao();
		String rgctId = rgctBill.getInfo().getId();
		try {
			AssuBillInfo billInfo = dao.getAssuBillInfoByRgctId(rgctId);
			if (billInfo == null) {
				throw new BizAppException("票据不存在");
			}
			billInfo.setOperStatus(GuarCodeConst.ASSU_STATUS_00);
			dao.modifyAssuBillInfo(billInfo);
		} catch (SQLException e) {
			throw new BizAppException("查询票据失败，"+e.getMessage());
		}
		
	}

}
