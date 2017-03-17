package com.herongtech.rc.draft.msgBuilder.requestBuilder;

import org.apache.commons.lang.StringUtils;
import org.apache.xmlbeans.XmlOptions;
import org.ecds125.Account1;
import org.ecds125.AdditionalInformation1;
import org.ecds125.CommercialDraft1;
import org.ecds125.CommercialDraftDrawingbackRegisterV01;
import org.ecds125.CurrencyAndAmount;
import org.ecds125.Document;
import org.ecds125.DocumentDocument;
import org.ecds125.DraftTypeCode;
import org.ecds125.Drawingback1;
import org.ecds125.MessageIdentification1;
import org.ecds125.Organisation1;

import com.herongtech.console.core.constant.DraftConstants;
import com.herongtech.console.core.util.MoneyUtils;
import com.herongtech.console.web.busicontroller.common.CommonConst;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.rc.domain.bean.RgctBillInfo;
import com.herongtech.rc.draft.common.MsgUtil;
import com.herongtech.rc.draft.common.vo.DraftInfoVo;
import com.herongtech.rc.draft.common.vo.DraftSender;
import com.herongtech.rc.draft.msgBuilder.AbstractDraftBuilder;

public class CommercialDraftDrawingbackRegisterDraftBuilder extends AbstractDraftBuilder{

	@Override
	public String buildBody(DraftSender draftSender) throws BizAppException {
		XmlOptions options = createXmlOptions(draftSender.getMesgType());

		DraftInfoVo vo = (DraftInfoVo) draftSender.getReqInfo();
		RgctBillInfo bill = vo.getBill();
		DocumentDocument document = DocumentDocument.Factory.newInstance(options);
		Document doc = document.addNewDocument();
		CommercialDraftDrawingbackRegisterV01 commerDrawBack = doc.addNewCommercialDraftDrawingbackRegister();
		
		/* 报文标识 */
		MessageIdentification1 msgId = commerDrawBack.addNewMsgId();
		msgId.setId(vo.getReqMsgId());
		msgId.setCreDtTm(MsgUtil.getCurrentTime());
		
		/* 票据信息 */
		CommercialDraft1 commer = commerDrawBack.addNewComrclDrft();
		commer.setTp(DraftTypeCode.Enum.forString(DraftConstants.RGCT_CODE_MAPPING_MAP.get("BILL_TYPE_"+bill.getBillType())));
		commer.setIdNb(bill.getBillNo());
		CurrencyAndAmount billAmt = commer.addNewIsseAmt();
		billAmt.setCcy(DraftConstants.CURRENCY_TYPE);
		billAmt.setStringValue(MoneyUtils.double2String(bill.getBillMoney()));
		commer.setIsseDt(MsgUtil.getConverTime(MsgUtil.converISODateTime(bill.getIssueDt())));
		commer.setDueDt(MsgUtil.getConverTime(MsgUtil.converISODateTime(bill.getDueDt())));
		
		/* 未用退回信息 */
		Drawingback1 backMess = commerDrawBack.addNewDrwgBck();
		backMess.setDrwgBckDt(MsgUtil.getConverTime(MsgUtil.converISODateTime(vo.getDrwgBckDt())));
		
		/* 承兑人信息 */
		Organisation1 org = commerDrawBack.addNewAccptr();
		org.setNm(bill.getAcceptor());
		Account1 acct = org.addNewAcct();
		if(CommonConst.BILL_TYPE_BANK.equals(bill.getBillType())){
			acct.setAcctSvcr(bill.getAcceptorBankNo());
		}else{
			acct.setAcctSvcr(bill.getDraweeBankNo());
		}
		
		/* 备注 */
		AdditionalInformation1 remark = commerDrawBack.addNewAddtlInf();
		if(!StringUtils.isBlank(bill.getRemark())){
			remark.setRmrk(bill.getRemark());
		}else{
			remark.setRmrk("125报文登记");
		}
		String draft = document.xmlText(options);
		String signPart = commerDrawBack.xmlText();
		return addSign(draft, signPart, draftSender.getIsAddSign());
	}

}
