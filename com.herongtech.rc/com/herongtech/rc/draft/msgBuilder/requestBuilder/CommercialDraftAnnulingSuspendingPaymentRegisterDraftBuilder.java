package com.herongtech.rc.draft.msgBuilder.requestBuilder;

import org.apache.xmlbeans.XmlOptions;
import org.apache.commons.lang.StringUtils;
import org.ecds111.Account1;
import org.ecds111.AdditionalInformation1;
import org.ecds111.AnnulingSuspendingCode;
import org.ecds111.AnnulingSuspendingPayment1;
import org.ecds111.CommercialDraft1;
import org.ecds111.CommercialDraftAnnulingSuspendingPaymentRegisterV01;
import org.ecds111.CurrencyAndAmount;
import org.ecds111.Document;
import org.ecds111.DocumentDocument;
import org.ecds111.DraftTypeCode;
import org.ecds111.MessageIdentification1;
import org.ecds111.Organisation1;
import org.ecds111.Organisation2;

import com.herongtech.console.core.constant.DraftConstants;
import com.herongtech.console.core.util.MoneyUtils;
import com.herongtech.console.web.busicontroller.common.CommonConst;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.rc.domain.bean.RgctBillInfo;
import com.herongtech.rc.draft.common.MsgUtil;
import com.herongtech.rc.draft.common.vo.DraftInfoVo;
import com.herongtech.rc.draft.common.vo.DraftSender;
import com.herongtech.rc.draft.msgBuilder.AbstractDraftBuilder;

public class CommercialDraftAnnulingSuspendingPaymentRegisterDraftBuilder extends AbstractDraftBuilder{

	@Override
	public String buildBody(DraftSender draftSender) throws BizAppException {
		XmlOptions options = createXmlOptions(draftSender.getMesgType());

		DraftInfoVo vo = (DraftInfoVo) draftSender.getReqInfo();
		RgctBillInfo bill = vo.getBill();
		DocumentDocument document = DocumentDocument.Factory.newInstance(options);
		Document doc = document.addNewDocument();
		CommercialDraftAnnulingSuspendingPaymentRegisterV01 commerAnnulSuspend = doc.addNewCommercialDraftAnnulingSuspendingPaymentRegister();
		
		/* 报文标识 */
		MessageIdentification1 msgId = commerAnnulSuspend.addNewMsgId();
		msgId.setId(vo.getReqMsgId());
		msgId.setCreDtTm(MsgUtil.getCurrentTime());
		
		/* 票据信息 */
		CommercialDraft1 commer = commerAnnulSuspend.addNewComrclDrft();
		commer.setTp(DraftTypeCode.Enum.forString(DraftConstants.RGCT_CODE_MAPPING_MAP.get("BILL_TYPE_"+bill.getBillType())));
		commer.setIdNb(bill.getBillNo());
		CurrencyAndAmount billAmt = commer.addNewIsseAmt();
		billAmt.setCcy(DraftConstants.CURRENCY_TYPE);
		billAmt.setStringValue(MoneyUtils.double2String(bill.getBillMoney()));
		commer.setIsseDt(MsgUtil.getConverTime(MsgUtil.converISODateTime(bill.getIssueDt())));
		commer.setDueDt(MsgUtil.getConverTime(MsgUtil.converISODateTime(bill.getDueDt())));
		
		/* 止付解除信息 */
		AnnulingSuspendingPayment1 annulSuspend = commerAnnulSuspend.addNewAnlgSspdgPmt();
		annulSuspend.setTp(AnnulingSuspendingCode.Enum.forString(vo.getAnnulSuspendPayType()));
		annulSuspend.setDt(MsgUtil.getConverTime(MsgUtil.converISODateTime(vo.getAnnulSuspendPayDt())));
		
		/* 承兑人信息 */
		Organisation2 org = commerAnnulSuspend.addNewAccptr();
		org.setNm(bill.getAcceptor());
		Account1 acct = org.addNewAcct();
		if(CommonConst.BILL_TYPE_BANK.equals(bill.getBillType())){
			acct.setAcctSvcr(bill.getAcceptorBankNo());
		}else{
			acct.setAcctSvcr(bill.getDraweeBankNo());
		}
		
		/* 申请止付解除人信息 */
		Organisation1 orga = commerAnnulSuspend.addNewPropsr();
		orga.setNm(vo.getFromName());
		
		/* 止付解除人信息 */
		Organisation1 organ = commerAnnulSuspend.addNewOprtr();
		organ.setNm(vo.getReceiveName());
		
		/* 备注 */
		AdditionalInformation1 remark = commerAnnulSuspend.addNewAddtlInf();
		if(!StringUtils.isBlank(bill.getRemark())){
			remark.setRmrk(bill.getRemark());
		}else{
			remark.setRmrk("111报文登记");
		}
		String draft = document.xmlText(options);
		String signPart = commerAnnulSuspend.xmlText();
		return addSign(draft, signPart, draftSender.getIsAddSign());
	}

}
