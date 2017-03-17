package com.herongtech.rc.draft.msgProcessor.reqInfoProcessorFor034;

import org.apache.commons.lang.math.NumberUtils;
import org.xmlbean.ecds034.Account1;
import org.xmlbean.ecds034.Account2;
import org.xmlbean.ecds034.CommercialDraft1;
import org.xmlbean.ecds034.CommercialDraft5;
import org.xmlbean.ecds034.CommercialDraft6;
import org.xmlbean.ecds034.CommercialDraftAcceptanceRequest1;
import org.xmlbean.ecds034.Organisation11;
import org.xmlbean.ecds034.Organisation2;
import org.xmlbean.ecds034.Organisation8;
import org.xmlbean.ecds034.RequestInformation1;

import com.herongtech.rc.domain.bean.RgctBillInfo;
import com.herongtech.rc.draft.common.vo.DraftInfoVo;
/**
 * 提示承兑
 *
 */
public class ReqInfoProcessorCommercialDraftAcceptanceRequest extends ReqInfoProcessor{

	
	public DraftInfoVo parseReqInfo(RequestInformation1 reqInfo, DraftInfoVo vo) {		
		RgctBillInfo info=vo.getBill();
	    CommercialDraftAcceptanceRequest1 acceptanceRequest=reqInfo.getCommercialDraftAcceptanceRequest();
	    String msgId=acceptanceRequest.getMsgId().getId();
	    vo.setReqMsgId(msgId);
	    {//票据信息
			CommercialDraft5 comrclDrft=acceptanceRequest.getComrclDrft();
			CommercialDraft6 accpt=acceptanceRequest.getAccptnc();
			String IdNb =comrclDrft.getIdNb();//电子票据号码
			String IsseAmt =comrclDrft.getIsseAmt().getStringValue();//票据金额
			String UcondlConsgnmtMrk = accpt.getUcondlConsgnmtMrk().toString();//到期无条件支付委托
			String TxlCtrctNb = accpt.getTxlCtrctNb();//交易合同编号
			String InvcNb = accpt.getInvcNb();//发票号码	
			String BtchNb = accpt.getBtchNb();//批次号
			//
			info.setBillNo(IdNb);
			info.setBillMoney(NumberUtils.createDouble(IsseAmt));	
			info.setConferNo(TxlCtrctNb);	
			vo.setBtchNb(BtchNb);
			vo.setInvcNb(InvcNb);
			vo.setTxlCtrctNb(TxlCtrctNb);
		}
		
		{//出票人信息
			Organisation11 drwr=acceptanceRequest.getDrwr();
			String role = drwr.getRole().toString();//类别
			String ElctrncSgntr =drwr.getElctrncSgntr();//电子签名
			String cmonId = drwr.getCmonId();//组织机构代码
			Account1 acct=drwr.getAcct();
			String acctId = acct.getId();//帐号
			String AcctSvcr =acct.getAcctSvcr();//开户行行号	
			Organisation8 agcy=drwr.getAgcy();
			Account2 acctEle2 = null;
			if(agcy != null) {
				acctEle2 =agcy.getAcct();
			}
			String AcctSvcr2 = null;
			if (acctEle2!=null)
				AcctSvcr2 = acctEle2.getAcctSvcr();//承接行行号
			
			vo.setFromRoleType(role);
			vo.setFromOrgCode(cmonId);
			vo.setFromElctrncSgntr(ElctrncSgntr);
			vo.setFromAcctNo(acctId);
			vo.setFromBankNo(AcctSvcr);
			vo.setFromAgcyBankNo(AcctSvcr2);
		}

		{//承兑人信息
		    vo.setReceiveAcctNo(info.getAcceptorAcct());
		    vo.setReceiveBankName(info.getAcceptorBankName());
		    vo.setReceiveBankNo(info.getAcceptorBankNo());
		    vo.setReceiveName(info.getAcceptor());
		}
		vo.setBusiType("002");
		return vo;
	}
    


}
