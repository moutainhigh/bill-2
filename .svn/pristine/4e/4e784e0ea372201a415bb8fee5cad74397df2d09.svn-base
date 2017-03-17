package com.herongtech.rc.draft.msgBuilder.requestBuilder;

import org.apache.commons.lang.StringUtils;
import org.apache.xmlbeans.XmlOptions;
import org.ecds104.Account1;
import org.ecds104.AdditionalInformation1;
import org.ecds104.CommercialDraft1;
import org.ecds104.CommercialDraftRediscountWithCentralBankRegisterV01;
import org.ecds104.CurrencyAndAmount;
import org.ecds104.Document;
import org.ecds104.DocumentDocument;
import org.ecds104.DraftTypeCode;
import org.ecds104.MessageIdentification1;
import org.ecds104.Organisation1;
import org.ecds104.Organisation2;
import org.ecds104.RediscountWithCentralBank3;

import com.herongtech.console.core.constant.DraftConstants;
import com.herongtech.console.core.util.MoneyUtils;
import com.herongtech.console.web.busicontroller.common.CommonConst;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.rc.domain.bean.RgctBillInfo;
import com.herongtech.rc.draft.common.MsgUtil;
import com.herongtech.rc.draft.common.vo.DraftInfoVo;
import com.herongtech.rc.draft.common.vo.DraftSender;
import com.herongtech.rc.draft.msgBuilder.AbstractDraftBuilder;

public class CommercialDraftRediscountWithCentralBankRegisterDraftBuilder extends AbstractDraftBuilder{

	@Override
	public String buildBody(DraftSender draftSender) throws BizAppException {
		XmlOptions options = createXmlOptions(draftSender.getMesgType());

		DraftInfoVo vo = (DraftInfoVo) draftSender.getReqInfo();
		RgctBillInfo bill = vo.getBill();
		DocumentDocument document = DocumentDocument.Factory.newInstance(options);
		Document doc = document.addNewDocument();
		CommercialDraftRediscountWithCentralBankRegisterV01 commerReDiscCen = doc.addNewCommercialDraftRediscountWithCentralBankRegister();
		
		/* 报文标识 */
		MessageIdentification1 msgId = commerReDiscCen.addNewMsgId();
		msgId.setId(vo.getReqMsgId());
		msgId.setCreDtTm(MsgUtil.getCurrentTime());
		
		/* 票据信息 */
		CommercialDraft1 commer = commerReDiscCen.addNewComrclDrft();
		commer.setTp(DraftTypeCode.Enum.forString(DraftConstants.RGCT_CODE_MAPPING_MAP.get("BILL_TYPE_"+bill.getBillType())));
		commer.setIdNb(bill.getBillNo());
		CurrencyAndAmount billAmt = commer.addNewIsseAmt();
		billAmt.setCcy(DraftConstants.CURRENCY_TYPE);
		billAmt.setStringValue(MoneyUtils.double2String(bill.getBillMoney()));
		commer.setIsseDt(MsgUtil.getConverTime(MsgUtil.converISODateTime(bill.getIssueDt())));
		commer.setDueDt(MsgUtil.getConverTime(MsgUtil.converISODateTime(bill.getDueDt())));
		
		/* 再贴现信息 */
		RediscountWithCentralBank3 reDiscCen = commerReDiscCen.addNewRdscntWthCntrlBk();
		reDiscCen.setDt(MsgUtil.getConverTime(MsgUtil.converISODateTime(vo.getReDiscDtCen())));
		
		/* 承兑人信息 */
		Organisation2 org = commerReDiscCen.addNewAccptr();
		org.setNm(bill.getAcceptor());
		Account1 acct = org.addNewAcct();
		if(CommonConst.BILL_TYPE_BANK.equals(bill.getBillType())){
			acct.setAcctSvcr(bill.getAcceptorBankNo());
		}else{
			acct.setAcctSvcr(bill.getDraweeBankNo());
		}
		
		/* 贴出人信息 */
		Organisation2 orga = commerReDiscCen.addNewRqstngBkOfRpdRdscntWthCntrlBk();
		orga.setNm(vo.getFromName());
		Account1 acct1 = orga.addNewAcct();
		acct1.setAcctSvcr(vo.getFromBankNo());
		
		/* 贴入人信息 */
		Organisation1 orgSys = commerReDiscCen.addNewRdscntWthCntrlBkSys();
		orgSys.setNm(vo.getReceiveName());
		
		/* 备注*/
		AdditionalInformation1 remark = commerReDiscCen.addNewAddtlInf();
		if(!StringUtils.isBlank(bill.getRemark())){
			remark.setRmrk(bill.getRemark());
		}else{
			remark.setRmrk("104报文登记");
		}
		String draft = document.xmlText(options);
		String signPart = commerReDiscCen.xmlText();
		return addSign(draft, signPart, draftSender.getIsAddSign());
	}

}
