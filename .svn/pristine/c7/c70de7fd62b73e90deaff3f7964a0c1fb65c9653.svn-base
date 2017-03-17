package com.herongtech.rc.draft.msgBuilder.requestBuilder;

import org.apache.commons.lang.StringUtils;
import org.apache.xmlbeans.XmlOptions;
import org.ecds109.Account1;
import org.ecds109.AdditionalInformation1;
import org.ecds109.CommercialDraft1;
import org.ecds109.CommercialDraftPaymentRefusedRegisterV01;
import org.ecds109.CurrencyAndAmount;
import org.ecds109.Document;
import org.ecds109.DocumentDocument;
import org.ecds109.DraftTypeCode;
import org.ecds109.MessageIdentification1;
import org.ecds109.Organisation1;
import org.ecds109.Organisation2;
import org.ecds109.PaymentRefused1;

import com.herongtech.console.core.constant.DraftConstants;
import com.herongtech.console.core.util.MoneyUtils;
import com.herongtech.console.web.busicontroller.common.CommonConst;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.rc.domain.bean.RgctBillInfo;
import com.herongtech.rc.draft.common.MsgUtil;
import com.herongtech.rc.draft.common.vo.DraftInfoVo;
import com.herongtech.rc.draft.common.vo.DraftSender;
import com.herongtech.rc.draft.msgBuilder.AbstractDraftBuilder;

public class CommercialDraftPaymentRefusedRegisterDraftBuilder extends AbstractDraftBuilder{

	@Override
	public String buildBody(DraftSender draftSender) throws BizAppException {
		XmlOptions options = createXmlOptions(draftSender.getMesgType());

		DraftInfoVo vo = (DraftInfoVo) draftSender.getReqInfo();
		RgctBillInfo bill = vo.getBill();
		DocumentDocument document = DocumentDocument.Factory.newInstance(options);
		Document doc = document.addNewDocument();
		CommercialDraftPaymentRefusedRegisterV01 commerPayRefuse = doc.addNewCommercialDraftPaymentRefusedRegister();
		
		/* 报文标识 */
		MessageIdentification1 msgId = commerPayRefuse.addNewMsgId();
		msgId.setId(vo.getReqMsgId());
		msgId.setCreDtTm(MsgUtil.getCurrentTime());
		
		/* 票据信息 */
		CommercialDraft1 commer = commerPayRefuse.addNewComrclDrft();
		commer.setTp(DraftTypeCode.Enum.forString(DraftConstants.RGCT_CODE_MAPPING_MAP.get("BILL_TYPE_"+bill.getBillType())));
		commer.setIdNb(bill.getBillNo());
		CurrencyAndAmount billAmt = commer.addNewIsseAmt();
		billAmt.setCcy(DraftConstants.CURRENCY_TYPE);
		billAmt.setStringValue(MoneyUtils.double2String(bill.getBillMoney()));
		commer.setIsseDt(MsgUtil.getConverTime(MsgUtil.converISODateTime(bill.getIssueDt())));
		commer.setDueDt(MsgUtil.getConverTime(MsgUtil.converISODateTime(bill.getDueDt())));
		
		/* 拒付信息 */
		PaymentRefused1 payRefuse = commerPayRefuse.addNewPmtRfsd();
		payRefuse.setDt(MsgUtil.getConverTime(MsgUtil.converISODateTime(vo.getPayRefuseDt())));
		payRefuse.setDshnrRsn(vo.getPayRefuseReason());
		
		/* 承兑人信息 */
		Organisation2 org = commerPayRefuse.addNewAccptr();
		org.setNm(bill.getAcceptor());
		Account1 acct = org.addNewAcct();
		if(CommonConst.BILL_TYPE_BANK.equals(bill.getBillType())){
			acct.setAcctSvcr(bill.getAcceptorBankNo());
		}else{
			acct.setAcctSvcr(bill.getDraweeBankNo());
		}
		
		/* 最后一手持票人信息 */
		Organisation1 orga = commerPayRefuse.addNewPyee();
		orga.setNm(vo.getFromName());
		
		/* 备注 */
		AdditionalInformation1 remark = commerPayRefuse.addNewAddtlInf();
		if(!StringUtils.isBlank(bill.getRemark())){
			remark.setRmrk(bill.getRemark());
		}else{
			remark.setRmrk("109报文登记");
		}
		String draft = document.xmlText(options);
		String signPart = commerPayRefuse.xmlText();
		return addSign(draft, signPart, draftSender.getIsAddSign());
	}

}
