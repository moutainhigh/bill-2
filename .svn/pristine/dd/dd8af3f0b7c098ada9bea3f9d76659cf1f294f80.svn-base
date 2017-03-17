
package com.herongtech.rc.draft.msgProcessor.reqInfoProcessorFor034;

import org.apache.commons.lang.math.NumberUtils;
import org.xmlbean.ecds034.Account1;
import org.xmlbean.ecds034.Account2;
import org.xmlbean.ecds034.CommercialDraftPresentationRequest1;
import org.xmlbean.ecds034.Organisation1;
import org.xmlbean.ecds034.Organisation8;
import org.xmlbean.ecds034.Presentation1;
import org.xmlbean.ecds034.RequestInformation1;

import com.herongtech.rc.domain.bean.RgctBillInfo;
import com.herongtech.rc.draft.common.MsgUtil;
import com.herongtech.rc.draft.common.vo.DraftInfoVo;
/**
 * 提示付款申请
 *
 */
public class ReqInfoProcessorCommercialDraftPresentationRequest extends ReqInfoProcessor{


	@Override
	public DraftInfoVo parseReqInfo(RequestInformation1 reqInfo,DraftInfoVo vo) {      
		RgctBillInfo info = vo.getBill();
		CommercialDraftPresentationRequest1 reqEle=reqInfo.getCommercialDraftPresentationRequest();
		String msgId=reqEle.getMsgId().getId();
        vo.setReqMsgId(msgId);  
				
		{//提示付款信息
			Presentation1  prsnttnEle = reqEle.getPrsnttn();
			String Dt = MsgUtil.convertToString(prsnttnEle.getApplDt());//提示付款申请日期
			String Amt = prsnttnEle.getAmt().getStringValue();//提示付款金额
			String SttlmMk = prsnttnEle.getSttlmMk().toString();//线上清算标记
			String PrxyPropstn=prsnttnEle.getPrxyPropstn().toString(); //代理申请标识
			//入账信息格式:#102100012345@1234567890#电话58881234,详细说明见报文格式标准附录六
			String RmrkByPropsr=prsnttnEle.getRmrkByPropsr(); //提示付款人备注
	        vo.setApplyDt(MsgUtil.converISODateTime(Dt));
	        vo.setAmt(NumberUtils.createDouble(Amt));
//	        vo.setOverdueMark(overdueMark)
	        vo.setSettleMk(SttlmMk);
//	          vo.setIsDelegate(PrxyPropstn);
	        vo.setFromRemark(RmrkByPropsr);
			
			
		}
		
		{//提示付款人信息
			
			Organisation1  drftHldrEle = reqEle.getDrftHldr();
			String Role = drftHldrEle.getRole().toString();//类别
			String Nm = drftHldrEle.getNm();//名称
			String CmonId = drftHldrEle.getCmonId();//组织机构代码
			String ElctrncSgntr = drftHldrEle.getElctrncSgntr();//电子签名
			Account1 acctEle =  drftHldrEle.getAcct();
			String acctId = acctEle.getId();//帐号
			String AcctSvcr = acctEle.getAcctSvcr();//开户行行号			
			Organisation8 agcyElement = drftHldrEle.getAgcy();
			Account2  acctEle2 = null;
			if(agcyElement != null) {
				acctEle2 = agcyElement.getAcct();
			}
			String AcctSvcr2 = null;
			if (acctEle2!=null)
				AcctSvcr2 = acctEle2.getAcctSvcr();//承接行行号
			vo.setFromName(Nm);
            vo.setFromElctrncSgntr(ElctrncSgntr);
            vo.setFromAcctNo(acctId);
            vo.setFromBankNo(AcctSvcr);
            vo.setFromRoleType(Role);
            vo.setFromBankName(Nm);
            vo.setFromOrgCode(CmonId);
            vo.setFromAgcyBankNo(AcctSvcr2);
						
		}
		

		{//承兑人信息
		    vo.setReceiveAcctNo(info.getAcceptorAcct());
            vo.setReceiveBankName(info.getAcceptorBankName());
            vo.setReceiveBankNo(info.getAcceptorBankNo());
            vo.setReceiveName(info.getAcceptorBankName());
		}
		vo.setBusiType("020");
		return vo;
	}
}
