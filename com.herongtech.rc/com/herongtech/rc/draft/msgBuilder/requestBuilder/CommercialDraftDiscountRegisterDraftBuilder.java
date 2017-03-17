package com.herongtech.rc.draft.msgBuilder.requestBuilder;

import java.math.BigDecimal;

import org.apache.commons.lang.StringUtils;
import org.apache.xmlbeans.XmlOptions;
import org.ecds102.Account1;
import org.ecds102.AdditionalInformation1;
import org.ecds102.CommercialDraft1;
import org.ecds102.CommercialDraftDiscountRegisterV01;
import org.ecds102.CurrencyAndAmount;
import org.ecds102.Discount3;
import org.ecds102.Document;
import org.ecds102.DocumentDocument;
import org.ecds102.DraftTypeCode;
import org.ecds102.MessageIdentification1;
import org.ecds102.Organisation1;
import org.ecds102.Organisation2;

import com.herongtech.console.core.constant.DraftConstants;
import com.herongtech.console.core.util.MoneyUtils;
import com.herongtech.console.web.busicontroller.common.CommonConst;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.rc.domain.bean.RgctBillInfo;
import com.herongtech.rc.draft.common.MsgUtil;
import com.herongtech.rc.draft.common.vo.DraftInfoVo;
import com.herongtech.rc.draft.common.vo.DraftSender;
import com.herongtech.rc.draft.msgBuilder.AbstractDraftBuilder;

public class CommercialDraftDiscountRegisterDraftBuilder extends AbstractDraftBuilder{

	@Override
	public String buildBody(DraftSender draftSender) throws BizAppException {
		XmlOptions options = createXmlOptions(draftSender.getMesgType());

		DraftInfoVo vo = (DraftInfoVo) draftSender.getReqInfo();
		RgctBillInfo bill = vo.getBill();
		DocumentDocument document = DocumentDocument.Factory.newInstance(options);
		Document doc = document.addNewDocument();
		CommercialDraftDiscountRegisterV01 commerDisc = doc.addNewCommercialDraftDiscountRegister();
		
		/* 报文标识 */
		MessageIdentification1 msgId = commerDisc.addNewMsgId();
		msgId.setId(vo.getReqMsgId());
		msgId.setCreDtTm(MsgUtil.getCurrentTime());
		
		/* 票据信息 */
		CommercialDraft1 commer = commerDisc.addNewComrclDrft();
		commer.setTp(DraftTypeCode.Enum.forString(DraftConstants.RGCT_CODE_MAPPING_MAP.get("BILL_TYPE_"+bill.getBillType())));
		
		
		commer.setIdNb(bill.getBillNo());
		CurrencyAndAmount billAmt = commer.addNewIsseAmt();
		billAmt.setCcy(DraftConstants.CURRENCY_TYPE);
		billAmt.setStringValue(MoneyUtils.double2String(bill.getBillMoney()));
		commer.setIsseDt(MsgUtil.getConverTime(MsgUtil.converISODateTime(bill.getIssueDt())));
		commer.setDueDt(MsgUtil.getConverTime(MsgUtil.converISODateTime(bill.getDueDt())));
		
		/* 贴现信息 */
		Discount3 disc = commerDisc.addNewDscnt();
		disc.setDt(MsgUtil.getConverTime(MsgUtil.converISODateTime(vo.getDiscDt())));
		disc.setIntrstRate(new BigDecimal(vo.getIntrstRate()).setScale(6,BigDecimal.ROUND_HALF_UP));
		if (StringUtils.isNotBlank(vo.getTxlCtrctNb())) {
			disc.setTxlCtrctNb(vo.getTxlCtrctNb());
		}
		if (StringUtils.isNotBlank(vo.getInvcNb())) {
			disc.setInvcNb(vo.getInvcNb());
		}
		if (StringUtils.isNotBlank(vo.getDscntAgrmtNb())) {
			disc.setDscntAgrmtNb(vo.getDscntAgrmtNb());
		}
		
		/* 承兑人信息 */
		Organisation1 org = commerDisc.addNewAccptr();
		org.setNm(bill.getAcceptor());
		Account1 acct = org.addNewAcct();
		if(CommonConst.BILL_TYPE_BANK.equals(bill.getBillType())){
			acct.setAcctSvcr(bill.getAcceptorBankNo());
		}else{
			acct.setAcctSvcr(bill.getDraweeBankNo());
		}
		
		/* 贴出人信息 */
		Organisation2 orgPro = commerDisc.addNewDscntPropsr();
		orgPro.setNm(vo.getFromName());
		
		/* 贴入人信息 */
		Organisation1 orgBk = commerDisc.addNewDscntBk();
		orgBk.setNm(vo.getReceiveName());
		Account1 acct1 = orgBk.addNewAcct();
		acct1.setAcctSvcr(vo.getInBankNo());
		
		/* 备注 */
		AdditionalInformation1 remark = commerDisc.addNewAddtlInf();
		if(!StringUtils.isBlank(bill.getRemark())){
			remark.setRmrk(bill.getRemark());
		}else{
			remark.setRmrk("102报文登记");
		}
		String draft = document.xmlText(options);
		String signPart = commerDisc.xmlText();
		return addSign(draft, signPart, draftSender.getIsAddSign());
	}
}
