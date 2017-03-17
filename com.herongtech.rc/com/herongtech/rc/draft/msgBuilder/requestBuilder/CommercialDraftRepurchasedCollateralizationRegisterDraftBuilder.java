package com.herongtech.rc.draft.msgBuilder.requestBuilder;

import org.apache.commons.lang.StringUtils;
import org.apache.xmlbeans.XmlOptions;
import org.ecds106.Account1;
import org.ecds106.AdditionalInformation1;
import org.ecds106.CommercialDraft1;
import org.ecds106.CommercialDraftRepurchasedCollateralizationRegisterV01;
import org.ecds106.CurrencyAndAmount;
import org.ecds106.Document;
import org.ecds106.DocumentDocument;
import org.ecds106.DraftTypeCode;
import org.ecds106.MessageIdentification1;
import org.ecds106.Organisation1;
import org.ecds106.Organisation2;
import org.ecds106.RepurchasedCollateralization1;

import com.herongtech.console.core.constant.DraftConstants;
import com.herongtech.console.core.util.MoneyUtils;
import com.herongtech.console.web.busicontroller.common.CommonConst;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.rc.domain.bean.RgctBillInfo;
import com.herongtech.rc.draft.common.MsgUtil;
import com.herongtech.rc.draft.common.vo.DraftInfoVo;
import com.herongtech.rc.draft.common.vo.DraftSender;
import com.herongtech.rc.draft.msgBuilder.AbstractDraftBuilder;

public class CommercialDraftRepurchasedCollateralizationRegisterDraftBuilder extends AbstractDraftBuilder{

	@Override
	public String buildBody(DraftSender draftSender) throws BizAppException {
		XmlOptions options = createXmlOptions(draftSender.getMesgType());

		DraftInfoVo vo = (DraftInfoVo) draftSender.getReqInfo();
		RgctBillInfo bill = vo.getBill();
		DocumentDocument document = DocumentDocument.Factory.newInstance(options);
		Document doc = document.addNewDocument();
		CommercialDraftRepurchasedCollateralizationRegisterV01 commerRepColl = doc.addNewCommercialDraftRepurchasedCollateralizationRegister();
		
		/* 报文标识 */
		MessageIdentification1 msgId = commerRepColl.addNewMsgId();
		msgId.setId(vo.getReqMsgId());
		msgId.setCreDtTm(MsgUtil.getCurrentTime());
		
		/* 票据信息*/
		CommercialDraft1 commer = commerRepColl.addNewComrclDrft();
		commer.setTp(DraftTypeCode.Enum.forString(DraftConstants.RGCT_CODE_MAPPING_MAP.get("BILL_TYPE_"+bill.getBillType())));
		commer.setIdNb(bill.getBillNo());
		CurrencyAndAmount billAmt = commer.addNewIsseAmt();
		billAmt.setCcy(DraftConstants.CURRENCY_TYPE);
		billAmt.setStringValue(MoneyUtils.double2String(bill.getBillMoney()));
		commer.setIsseDt(MsgUtil.getConverTime(MsgUtil.converISODateTime(bill.getIssueDt())));
		commer.setDueDt(MsgUtil.getConverTime(MsgUtil.converISODateTime(bill.getDueDt())));
		
		/* 质押解除信息*/
		RepurchasedCollateralization1 repColl = commerRepColl.addNewRpdCollztn();
		repColl.setDt(MsgUtil.getConverTime(MsgUtil.converISODateTime(vo.getRepCollDt())));
		
		/* 承兑人信息*/
		Organisation2 org = commerRepColl.addNewAccptr();
		org.setNm(bill.getAcceptor());
		Account1 acct = org.addNewAcct();
		if(CommonConst.BILL_TYPE_BANK.equals(bill.getBillType())){
			acct.setAcctSvcr(bill.getAcceptorBankNo());
		}else{
			acct.setAcctSvcr(bill.getDraweeBankNo());
		}
		
		/* 原质权人信息*/
		Organisation2 orga = commerRepColl.addNewOrginCollztnBk();
		orga.setNm(vo.getReceiveName());
		Account1 acct1 = orga.addNewAcct();
		acct1.setAcctSvcr(vo.getInBankNo());
		
		/* 原出质人信息*/
		Organisation1 organ = commerRepColl.addNewOrginCollztnPrpsr();
		organ.setNm(vo.getFromName());
		
		/* 备注*/
		AdditionalInformation1 remark = commerRepColl.addNewAddtlInf();
		if(!StringUtils.isBlank(bill.getRemark())){
			remark.setRmrk(bill.getRemark());
		}else{
			remark.setRmrk("106报文登记");
		}
		String draft = document.xmlText(options);
		String signPart = commerRepColl.xmlText();
		return addSign(draft, signPart, draftSender.getIsAddSign());
	}

}
