package com.herongtech.rc.draft.msgBuilder.requestBuilder;

import org.apache.commons.lang.StringUtils;
import org.apache.xmlbeans.XmlOptions;
import org.ecds002.Account1;
import org.ecds002.CommercialDraft1;
import org.ecds002.CommercialDraft2;
import org.ecds002.CommercialDraftAcceptanceRequestV01;
import org.ecds002.ConsignmentCode;
import org.ecds002.CurrencyAndAmount;
import org.ecds002.Document;
import org.ecds002.DocumentDocument;
import org.ecds002.MessageIdentification1;
import org.ecds002.Organisation2;
import org.ecds002.Organisation3;
import org.ecds002.RoleCode;

import com.herongtech.commons.tools.StringUtil;
import com.herongtech.console.core.constant.DraftConstants;
import com.herongtech.console.core.util.MoneyUtils;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.rc.domain.bean.RgctBillInfo;
import com.herongtech.rc.draft.common.MsgUtil;
import com.herongtech.rc.draft.common.vo.DraftInfoVo;
import com.herongtech.rc.draft.common.vo.DraftSender;
import com.herongtech.rc.draft.msgBuilder.AbstractDraftBuilder;

/**
 * 002提示承兑
 * 
 */
public class CommercialDraftAcceptanceRequestDraftBuilder extends
		AbstractDraftBuilder {

	@Override
	public String buildBody(DraftSender draftSender) throws BizAppException {
		XmlOptions options = createXmlOptions(draftSender.getMesgType());

		DraftInfoVo vo = (DraftInfoVo) draftSender.getReqInfo();
		RgctBillInfo bill = vo.getBill();
		DocumentDocument document = DocumentDocument.Factory
				.newInstance(options);
		Document doc = document.addNewDocument();
		CommercialDraftAcceptanceRequestV01 commeracpt = doc
				.addNewCommercialDraftAcceptanceRequest();
		CommercialDraft2 accpt = commeracpt.addNewAccptnc();
		if (StringUtils.isNotBlank(vo.getBtchNb())) {
			accpt.setBtchNb(vo.getBtchNb());
		}
		if (StringUtils.isNotBlank(vo.getInvcNb())) {
			accpt.setInvcNb(vo.getInvcNb());
		}
		if (StringUtils.isNotBlank(vo.getFromRemark())) {
			accpt.setRmrkByPropsr(vo.getFromRemark());
		}

		if (StringUtils.isNotBlank(vo.getTxlCtrctNb())) {
			accpt.setTxlCtrctNb(vo.getTxlCtrctNb());
		}

		accpt.setUcondlConsgnmtMrk(ConsignmentCode.Enum
				.forString(DraftConstants.UCONDL_CONSGNMT_MRK));

		CommercialDraft1 billinfo = commeracpt.addNewComrclDrft();
		billinfo.setIdNb(bill.getBillNo());
		CurrencyAndAmount AMT = billinfo.addNewIsseAmt();
		AMT.setCcy(DraftConstants.CURRENCY_TYPE);
		AMT.setStringValue(MoneyUtils.double2String(bill.getBillMoney()));

		Organisation2 drwr = commeracpt.addNewDrwr();
		Account1 account = drwr.addNewAcct();
		account.setId(bill.getRemitterAcct());
		account.setAcctSvcr(bill.getRemitterBankNo());
		drwr.setCmonId(bill.getRemitterOrgCode());
		drwr.setRole(RoleCode.Enum.forString(bill.getRemitterRole()));
		drwr.setElctrncSgntr(vo.getFromElctrncSgntr());
		if (StringUtils.isNotBlank(vo.getFromAgcyBankNo())) {
			Organisation3 agcy = drwr.addNewAgcy();
			agcy.addNewAcct().setAcctSvcr(vo.getFromAgcyBankNo());
		}

		MessageIdentification1 msgid = commeracpt.addNewMsgId();
		msgid.setId(vo.getReqMsgId()); // 报文标识
		msgid.setCreDtTm(MsgUtil.getCurrentTime()); // 报文时间

		String draft = document.xmlText(options);
		String signPart = commeracpt.xmlText();
		return addSign(draft, signPart, draftSender.getIsAddSign());
	}

}
