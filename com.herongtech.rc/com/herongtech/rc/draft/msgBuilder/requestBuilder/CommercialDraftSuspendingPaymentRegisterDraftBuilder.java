package com.herongtech.rc.draft.msgBuilder.requestBuilder;

import org.apache.commons.lang.StringUtils;
import org.apache.xmlbeans.XmlOptions;
import org.ecds110.Account1;
import org.ecds110.AdditionalInformation1;
import org.ecds110.CommercialDraft1;
import org.ecds110.CommercialDraftSuspendingPaymentRegisterV01;
import org.ecds110.CurrencyAndAmount;
import org.ecds110.Document;
import org.ecds110.DocumentDocument;
import org.ecds110.DraftTypeCode;
import org.ecds110.MessageIdentification1;
import org.ecds110.Organisation1;
import org.ecds110.Organisation2;
import org.ecds110.SuspendingPayment1;
import org.ecds110.SuspendingPaymentCode;

import com.herongtech.console.core.constant.DraftConstants;
import com.herongtech.console.core.util.MoneyUtils;
import com.herongtech.console.web.busicontroller.common.CommonConst;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.rc.domain.bean.RgctBillInfo;
import com.herongtech.rc.draft.common.MsgUtil;
import com.herongtech.rc.draft.common.vo.DraftInfoVo;
import com.herongtech.rc.draft.common.vo.DraftSender;
import com.herongtech.rc.draft.msgBuilder.AbstractDraftBuilder;

public class CommercialDraftSuspendingPaymentRegisterDraftBuilder extends AbstractDraftBuilder{

	@Override
	public String buildBody(DraftSender draftSender) throws BizAppException {
		XmlOptions options = createXmlOptions(draftSender.getMesgType());

		DraftInfoVo vo = (DraftInfoVo) draftSender.getReqInfo();
		RgctBillInfo bill = vo.getBill();
		DocumentDocument document = DocumentDocument.Factory.newInstance(options);
		Document doc = document.addNewDocument();
		CommercialDraftSuspendingPaymentRegisterV01 commerSuspend = doc.addNewCommercialDraftSuspendingPaymentRegister();
		
		/* 报文标识 */
		MessageIdentification1 msgId = commerSuspend.addNewMsgId();
		msgId.setId(vo.getReqMsgId());
		msgId.setCreDtTm(MsgUtil.getCurrentTime());
		
		/* 票据信息 */
		CommercialDraft1 commer = commerSuspend.addNewComrclDrft();
		commer.setTp(DraftTypeCode.Enum.forString(DraftConstants.RGCT_CODE_MAPPING_MAP.get("BILL_TYPE_"+bill.getBillType())));
		commer.setIdNb(bill.getBillNo());
		CurrencyAndAmount billAmt = commer.addNewIsseAmt();
		billAmt.setCcy(DraftConstants.CURRENCY_TYPE);
		billAmt.setStringValue(MoneyUtils.double2String(bill.getBillMoney()));
		commer.setIsseDt(MsgUtil.getConverTime(MsgUtil.converISODateTime(bill.getIssueDt())));
		commer.setDueDt(MsgUtil.getConverTime(MsgUtil.converISODateTime(bill.getDueDt())));
		
		/* 止付登记信息 */
		SuspendingPayment1 susPay = commerSuspend.addNewSspdgPmt();
		susPay.setTp(SuspendingPaymentCode.Enum.forString(vo.getSuspendPayType()));
		susPay.setDt(MsgUtil.getConverTime(MsgUtil.converISODateTime(vo.getSuspendPayDt())));
		
		/* 承兑人信息 */
		Organisation2 org = commerSuspend.addNewAccptr();
		org.setNm(bill.getAcceptor());
		Account1 acct = org.addNewAcct();
		if(CommonConst.BILL_TYPE_BANK.equals(bill.getBillType())){
			acct.setAcctSvcr(bill.getAcceptorBankNo());
		}else{
			acct.setAcctSvcr(bill.getDraweeBankNo());
		}
		
		/* 申请止付人信息 */
		Organisation1 orga = commerSuspend.addNewPropsr();
		orga.setNm(vo.getFromName());
		
		/* 受理止付人信息 */
		Organisation1 organ = commerSuspend.addNewOprtr();
		organ.setNm(vo.getReceiveName());
		
		/* 备注 */
		AdditionalInformation1 remark = commerSuspend.addNewAddtlInf();
		if(!StringUtils.isBlank(bill.getRemark())){
			remark.setRmrk(bill.getRemark());
		}else{
			remark.setRmrk("110报文登记");
		}
		String draft = document.xmlText(options);
		String signPart = commerSuspend.xmlText();
		return addSign(draft, signPart, draftSender.getIsAddSign());
	}

}
