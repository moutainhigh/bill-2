package com.herongtech.rc.draft.msgBuilder.requestBuilder;

import org.apache.commons.lang.StringUtils;
import org.apache.xmlbeans.XmlOptions;


import org.ecds101.Acceptance1;
import org.ecds101.Account1;
import org.ecds101.AdditionalInformation1;
import org.ecds101.CommercialDraft1;
import org.ecds101.CommercialDraftAcceptanceRegisterV01;
import org.ecds101.CurrencyAndAmount;
import org.ecds101.Document;
import org.ecds101.DocumentDocument;
import org.ecds101.DraftTypeCode;
import org.ecds101.MessageIdentification1;
import org.ecds101.Organisation1;
import org.ecds101.Organisation2;

import com.herongtech.console.core.constant.DraftConstants;
import com.herongtech.console.core.util.MoneyUtils;
import com.herongtech.console.web.busicontroller.common.CommonConst;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.rc.domain.bean.RgctBillInfo;
import com.herongtech.rc.draft.common.MsgUtil;
import com.herongtech.rc.draft.common.vo.DraftInfoVo;
import com.herongtech.rc.draft.common.vo.DraftSender;
import com.herongtech.rc.draft.msgBuilder.AbstractDraftBuilder;

public class CommercialDraftAcceptanceRegisterDraftBuilder extends AbstractDraftBuilder{

	@Override
	public String buildBody(DraftSender draftSender) throws BizAppException {
		XmlOptions options = createXmlOptions(draftSender.getMesgType());

		DraftInfoVo vo = (DraftInfoVo) draftSender.getReqInfo();
		RgctBillInfo bill = vo.getBill();
		DocumentDocument document = DocumentDocument.Factory.newInstance(options);
		Document doc = document.addNewDocument();
		CommercialDraftAcceptanceRegisterV01 commerDraft = doc.addNewCommercialDraftAcceptanceRegister();
		
		/* 报文标识 */
		MessageIdentification1 msgId = commerDraft.addNewMsgId();
		msgId.setId(vo.getReqMsgId());
		msgId.setCreDtTm(MsgUtil.getCurrentTime());
		
		/* 票据信息 */
		CommercialDraft1 commer = commerDraft.addNewComrclDrft();
		commer.setTp(DraftTypeCode.Enum.forString(DraftConstants.RGCT_CODE_MAPPING_MAP.get("BILL_TYPE_"+bill.getBillType())));
		commer.setIdNb(bill.getBillNo());
		CurrencyAndAmount billAmt = commer.addNewIsseAmt();
		billAmt.setCcy(DraftConstants.CURRENCY_TYPE);
		billAmt.setStringValue(MoneyUtils.double2String(bill.getBillMoney()));
		commer.setIsseDt(MsgUtil.getConverTime(MsgUtil.converISODateTime(bill.getIssueDt())));
		commer.setDueDt(MsgUtil.getConverTime(MsgUtil.converISODateTime(bill.getDueDt())));
		
		/* 承兑信息 */
		Acceptance1 accpt = commerDraft.addNewAccptnc();
		accpt.setDt(MsgUtil.getConverTime(MsgUtil.converISODateTime(bill.getAcceptorDate())));
		if(StringUtils.isNotBlank(vo.getTxlCtrctNb())){
			accpt.setTxlCtrctNb(vo.getTxlCtrctNb());
		}
		if(StringUtils.isNotBlank(vo.getInvcNb())){
			accpt.setInvcNb(vo.getInvcNb());
		}
		if(StringUtils.isNotBlank(vo.getAccptncAgrmtNb())){
			accpt.setAccptncAgrmtNb(vo.getAccptncAgrmtNb());
		}
		
		/* 承兑人信息 */
		Organisation2 org = commerDraft.addNewAccptr();
		org.setNm(bill.getAcceptor());
		Account1 acct = org.addNewAcct();
		if(CommonConst.BILL_TYPE_BANK.equals(bill.getBillType())){
			acct.setAcctSvcr(bill.getAcceptorBankNo());
		}else{
			acct.setAcctSvcr(bill.getDraweeBankNo());
		}
		
		/* 出票人信息 */
		Organisation1 orga = commerDraft.addNewDrwr();
		orga.setNm(bill.getRemitter());
		
		/* 收款人信息 */
		Organisation1 payee = commerDraft.addNewPyee();
		payee.setNm(bill.getPayeeName());
		
		/* 备注 */
		AdditionalInformation1 remark = commerDraft.addNewAddtlInf();
		if(!StringUtils.isBlank(bill.getRemark())){
			remark.setRmrk(bill.getRemark());
		}else{
			remark.setRmrk("101报文登记");
		}
		String draft = document.xmlText(options);
		String signPart = commerDraft.xmlText();
		return addSign(draft, signPart, draftSender.getIsAddSign());
	}

}
