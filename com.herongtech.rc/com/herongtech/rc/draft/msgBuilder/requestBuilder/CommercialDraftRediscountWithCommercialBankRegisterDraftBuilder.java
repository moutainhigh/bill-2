package com.herongtech.rc.draft.msgBuilder.requestBuilder;

import java.math.BigDecimal;

import org.apache.commons.lang.StringUtils;
import org.apache.xmlbeans.XmlOptions;
import org.ecds103.Account1;
import org.ecds103.AdditionalInformation1;
import org.ecds103.CommercialDraft1;
import org.ecds103.CommercialDraftRediscountWithCommercialBankRegisterV01;
import org.ecds103.CurrencyAndAmount;
import org.ecds103.Document;
import org.ecds103.DocumentDocument;
import org.ecds103.DraftTypeCode;
import org.ecds103.MessageIdentification1;
import org.ecds103.Organisation1;
import org.ecds103.Organisation2;
import org.ecds103.RediscountWithCommercialBank3;

import com.herongtech.console.core.constant.DraftConstants;
import com.herongtech.console.core.util.MoneyUtils;
import com.herongtech.console.web.busicontroller.common.CommonConst;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.rc.domain.bean.RgctBillInfo;
import com.herongtech.rc.draft.common.MsgUtil;
import com.herongtech.rc.draft.common.vo.DraftInfoVo;
import com.herongtech.rc.draft.common.vo.DraftSender;
import com.herongtech.rc.draft.msgBuilder.AbstractDraftBuilder;

public class CommercialDraftRediscountWithCommercialBankRegisterDraftBuilder extends AbstractDraftBuilder{

	@Override
	public String buildBody(DraftSender draftSender) throws BizAppException {
		XmlOptions options = createXmlOptions(draftSender.getMesgType());

		DraftInfoVo vo = (DraftInfoVo) draftSender.getReqInfo();
		RgctBillInfo bill = vo.getBill();
		DocumentDocument document = DocumentDocument.Factory.newInstance(options);
		Document doc = document.addNewDocument();
		CommercialDraftRediscountWithCommercialBankRegisterV01 commerReDisc = doc.addNewCommercialDraftRediscountWithCommercialBankRegister();
		
		/* 报文标识 */
		MessageIdentification1 msgId = commerReDisc.addNewMsgId();
		msgId.setId(vo.getReqMsgId());
		msgId.setCreDtTm(MsgUtil.getCurrentTime());
		
		/* 票据信息 */
		CommercialDraft1 commer = commerReDisc.addNewComrclDrft();
		commer.setTp(DraftTypeCode.Enum.forString(DraftConstants.RGCT_CODE_MAPPING_MAP.get("BILL_TYPE_"+bill.getBillType())));
		commer.setIdNb(bill.getBillNo());
		CurrencyAndAmount billAmt = commer.addNewIsseAmt();
		billAmt.setCcy(DraftConstants.CURRENCY_TYPE);
		billAmt.setStringValue(MoneyUtils.double2String(bill.getBillMoney()));
		commer.setIsseDt(MsgUtil.getConverTime(MsgUtil.converISODateTime(bill.getIssueDt())));
		commer.setDueDt(MsgUtil.getConverTime(MsgUtil.converISODateTime(bill.getDueDt())));
		
		/* 转贴现信息 */
		RediscountWithCommercialBank3 reDisc = commerReDisc.addNewRdscntWthComrclBk();
		reDisc.setDt(MsgUtil.getConverTime(MsgUtil.converISODateTime(vo.getReDiscDt())));
		reDisc.setIntrstRate(new BigDecimal(vo.getIntrstRate().toString()));
		
		/* 承兑人信息 */
		Organisation2 org = commerReDisc.addNewAccptr();
		org.setNm(bill.getAcceptor());
		Account1 acct = org.addNewAcct();
		if(CommonConst.BILL_TYPE_BANK.equals(bill.getBillType())){
			acct.setAcctSvcr(bill.getAcceptorBankNo());
		}else{
			acct.setAcctSvcr(bill.getDraweeBankNo());
		}
		
		/* 贴出人信息 */
		Organisation1 orga = commerReDisc.addNewRqstngBkOfRdscntWthComrclBk();
		orga.setNm(vo.getFromName());
		
		/* 贴入人信息 */
		Organisation2 orgn = commerReDisc.addNewRcvgBkOfRdscntWthComrclBk();
		orgn.setNm(vo.getReceiveName());
		Account1 acct1 = orgn.addNewAcct();
		acct1.setAcctSvcr(vo.getInBankNo());
		
		/* 备注*/
		AdditionalInformation1 remark = commerReDisc.addNewAddtlInf();
		if(!StringUtils.isBlank(bill.getRemark())){
			remark.setRmrk(bill.getRemark());
		}else{
			remark.setRmrk("103报文登记");
		}
		String draft = document.xmlText(options);
		String signPart = commerReDisc.xmlText();
		return addSign(draft, signPart, draftSender.getIsAddSign());
	}

}
