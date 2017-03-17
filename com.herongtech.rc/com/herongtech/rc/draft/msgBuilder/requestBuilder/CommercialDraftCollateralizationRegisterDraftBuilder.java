package com.herongtech.rc.draft.msgBuilder.requestBuilder;

import org.apache.commons.lang.StringUtils;
import org.apache.xmlbeans.XmlOptions;
import org.ecds105.Account1;
import org.ecds105.AdditionalInformation1;
import org.ecds105.Collateralization1;
import org.ecds105.CommercialDraft1;
import org.ecds105.CommercialDraftCollateralizationRegisterV01;
import org.ecds105.CurrencyAndAmount;
import org.ecds105.Document;
import org.ecds105.DocumentDocument;
import org.ecds105.DraftTypeCode;
import org.ecds105.MessageIdentification1;
import org.ecds105.Organisation1;
import org.ecds105.Organisation2;

import com.herongtech.console.core.constant.DraftConstants;
import com.herongtech.console.core.util.MoneyUtils;
import com.herongtech.console.web.busicontroller.common.CommonConst;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.rc.domain.bean.RgctBillInfo;
import com.herongtech.rc.draft.common.MsgUtil;
import com.herongtech.rc.draft.common.vo.DraftInfoVo;
import com.herongtech.rc.draft.common.vo.DraftSender;
import com.herongtech.rc.draft.msgBuilder.AbstractDraftBuilder;

public class CommercialDraftCollateralizationRegisterDraftBuilder extends AbstractDraftBuilder{

	@Override
	public String buildBody(DraftSender draftSender) throws BizAppException {
		XmlOptions options = createXmlOptions(draftSender.getMesgType());

		DraftInfoVo vo = (DraftInfoVo) draftSender.getReqInfo();
		RgctBillInfo bill = vo.getBill();
		DocumentDocument document = DocumentDocument.Factory.newInstance(options);
		Document doc = document.addNewDocument();
		CommercialDraftCollateralizationRegisterV01 commerColl = doc.addNewCommercialDraftCollateralizationRegister();
		
		/* 报文标识 */
		MessageIdentification1 msgId = commerColl.addNewMsgId();
		msgId.setId(vo.getReqMsgId());
		msgId.setCreDtTm(MsgUtil.getCurrentTime());
		
		/* 票据信息 */
		CommercialDraft1 commer = commerColl.addNewComrclDrft();
		commer.setTp(DraftTypeCode.Enum.forString(DraftConstants.RGCT_CODE_MAPPING_MAP.get("BILL_TYPE_"+bill.getBillType())));
		commer.setIdNb(bill.getBillNo());
		CurrencyAndAmount billAmt = commer.addNewIsseAmt();
		billAmt.setCcy(DraftConstants.CURRENCY_TYPE);
		billAmt.setStringValue(MoneyUtils.double2String(bill.getBillMoney()));
		commer.setIsseDt(MsgUtil.getConverTime(MsgUtil.converISODateTime(bill.getIssueDt())));
		commer.setDueDt(MsgUtil.getConverTime(MsgUtil.converISODateTime(bill.getDueDt())));
		
		/* 质押信息 */
		Collateralization1 coll = commerColl.addNewCollztn();
		coll.setDt(MsgUtil.getConverTime(MsgUtil.converISODateTime(vo.getCollDt())));
		
		/* 承兑人信息 */
		Organisation2 org = commerColl.addNewAccptr();
		org.setNm(bill.getAcceptor());
		Account1 acct = org.addNewAcct();
		if(CommonConst.BILL_TYPE_BANK.equals(bill.getBillType())){
			acct.setAcctSvcr(bill.getAcceptorBankNo());
		}else{
			acct.setAcctSvcr(bill.getDraweeBankNo());
		}
		
		/* 出质人信息 */
		Organisation1 orga = commerColl.addNewCollztnPropsr();
		orga.setNm(vo.getFromName());
		
		/* 质权人信息 */
		Organisation2 organ = commerColl.addNewCollztnBk();
		organ.setNm(vo.getReceiveName());
		Account1 acct1 = organ.addNewAcct();
		acct1.setAcctSvcr(vo.getInBankNo());
		
		/* 备注 */
		AdditionalInformation1 remark = commerColl.addNewAddtlInf();
		if(!StringUtils.isBlank(bill.getRemark())){
			remark.setRmrk(bill.getRemark());
		}else{
			remark.setRmrk("105报文登记");
		}
		String draft = document.xmlText(options);
		String signPart = commerColl.xmlText();
		return addSign(draft, signPart, draftSender.getIsAddSign());
	}

}
