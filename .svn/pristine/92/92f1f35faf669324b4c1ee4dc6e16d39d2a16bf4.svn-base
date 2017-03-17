package com.herongtech.rc.draft.msgBuilder.requestBuilder;

import org.apache.commons.lang.StringUtils;
import org.apache.xmlbeans.XmlOptions;
import org.ecds108.Account1;
import org.ecds108.AdditionalInformation1;
import org.ecds108.CommercialDraft1;
import org.ecds108.CommercialDraftSettlementRegisterV01;
import org.ecds108.CurrencyAndAmount;
import org.ecds108.Document;
import org.ecds108.DocumentDocument;
import org.ecds108.DraftTypeCode;
import org.ecds108.MessageIdentification1;
import org.ecds108.Organisation1;
import org.ecds108.Organisation2;
import org.ecds108.Settlement1;

import com.herongtech.console.core.constant.DraftConstants;
import com.herongtech.console.core.util.MoneyUtils;
import com.herongtech.console.web.busicontroller.common.CommonConst;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.rc.domain.bean.RgctBillInfo;
import com.herongtech.rc.draft.common.MsgUtil;
import com.herongtech.rc.draft.common.vo.DraftInfoVo;
import com.herongtech.rc.draft.common.vo.DraftSender;
import com.herongtech.rc.draft.msgBuilder.AbstractDraftBuilder;

public class CommercialDraftSettlementRegisterDraftBuilder extends AbstractDraftBuilder{

	@Override
	public String buildBody(DraftSender draftSender) throws BizAppException {
		XmlOptions options = createXmlOptions(draftSender.getMesgType());

		DraftInfoVo vo = (DraftInfoVo) draftSender.getReqInfo();
		RgctBillInfo bill = vo.getBill();
		DocumentDocument document = DocumentDocument.Factory.newInstance(options);
		Document doc = document.addNewDocument();
		CommercialDraftSettlementRegisterV01 commerSettle = doc.addNewCommercialDraftSettlementRegister();
		
		/* 报文标识 */
		MessageIdentification1 msgId = commerSettle.addNewMsgId();
		msgId.setId(vo.getReqMsgId());
		msgId.setCreDtTm(MsgUtil.getCurrentTime());
		
		/* 票据信息 */
		CommercialDraft1 commer = commerSettle.addNewComrclDrft();
		commer.setTp(DraftTypeCode.Enum.forString(DraftConstants.RGCT_CODE_MAPPING_MAP.get("BILL_TYPE_"+bill.getBillType())));
		commer.setIdNb(bill.getBillNo());
		CurrencyAndAmount billAmt = commer.addNewIsseAmt();
		billAmt.setCcy(DraftConstants.CURRENCY_TYPE);
		billAmt.setStringValue(MoneyUtils.double2String(bill.getBillMoney()));
		commer.setIsseDt(MsgUtil.getConverTime(MsgUtil.converISODateTime(bill.getIssueDt())));
		commer.setDueDt(MsgUtil.getConverTime(MsgUtil.converISODateTime(bill.getDueDt())));
		
		/* 结清信息 */
		Settlement1 settle = commerSettle.addNewSttlm();
		settle.setPayDt(MsgUtil.getConverTime(MsgUtil.converISODateTime(vo.getSettleDt())));
		
		/* 承兑人信息 */
		Organisation2 org = commerSettle.addNewAccptr();
		org.setNm(bill.getAcceptor());
		Account1 acct = org.addNewAcct();
		if(CommonConst.BILL_TYPE_BANK.equals(bill.getBillType())){
			acct.setAcctSvcr(bill.getAcceptorBankNo());
		}else{
			acct.setAcctSvcr(bill.getDraweeBankNo());
		}
		
		/* 最后一手持票人信息 */
		Organisation1 orga = commerSettle.addNewPyee();
		orga.setNm(vo.getFromName());
		
		/* 备注 */
		AdditionalInformation1 remark = commerSettle.addNewAddtlInf();
		if(!StringUtils.isBlank(bill.getRemark())){
			remark.setRmrk(bill.getRemark());
		}else{
			remark.setRmrk("108报文登记");
		}
		String draft = document.xmlText(options);
		String signPart = commerSettle.xmlText();
		return addSign(draft, signPart, draftSender.getIsAddSign());
	}

}
