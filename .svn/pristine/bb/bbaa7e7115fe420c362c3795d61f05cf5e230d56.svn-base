package com.herongtech.rc.draft.msgBuilder.requestBuilder;

import org.apache.commons.lang.StringUtils;
import org.apache.xmlbeans.XmlOptions;
import org.ecds003.Account1;
import org.ecds003.CommercialDraft1;
import org.ecds003.CommercialDraft2;
import org.ecds003.CommercialDraftIssuanceRequestV01;
import org.ecds003.CurrencyAndAmount;
import org.ecds003.Document;
import org.ecds003.DocumentDocument;
import org.ecds003.MessageIdentification1;
import org.ecds003.Organisation1;
import org.ecds003.Organisation2;
import org.ecds003.RoleCode;

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
 * 003提示收票
 * 
 */
public class CommercialDraftIssuanceRequestDraftBuilder extends
		AbstractDraftBuilder {

	@Override
	public String buildBody(DraftSender draftSender) throws BizAppException {
		XmlOptions options = createXmlOptions(draftSender.getMesgType());

		DraftInfoVo vo = (DraftInfoVo) draftSender.getReqInfo();
		RgctBillInfo bill = vo.getBill();
		DocumentDocument document = DocumentDocument.Factory
				.newInstance(options);
		Document doc = document.addNewDocument();
		CommercialDraftIssuanceRequestV01 commerDraft = doc
				.addNewCommercialDraftIssuanceRequest();

		if (StringUtils.isNotBlank(vo.getFromRemark())) {
			CommercialDraft2 issnc = commerDraft.addNewIssnc();
			issnc.setRmrkByPropsr(vo.getFromRemark());
		}
		CommercialDraft1 billinfo = commerDraft.addNewComrclDrft();
		billinfo.setIdNb(bill.getBillNo());
		CurrencyAndAmount AMT = billinfo.addNewIsseAmt();
		AMT.setCcy(DraftConstants.CURRENCY_TYPE);
		AMT.setStringValue(MoneyUtils.double2String(bill.getBillMoney()));

		Organisation1 drwr = commerDraft.addNewDrwr();
		Account1 account = drwr.addNewAcct();
		account.setId(bill.getRemitterAcct());
		account.setAcctSvcr(bill.getRemitterBankNo());
		drwr.setCmonId(bill.getRemitterOrgCode());
		drwr.setRole(RoleCode.Enum.forString(bill.getRemitterRole()));
		drwr.setElctrncSgntr(vo.getFromElctrncSgntr());
		if (StringUtils.isNotBlank(vo.getFromAgcyBankNo())) {
			Organisation2 agcy = drwr.addNewAgcy();
			agcy.addNewAcct().setAcctSvcr(vo.getFromAgcyBankNo());
		}

		MessageIdentification1 msgid = commerDraft.addNewMsgId();
		msgid.setId(vo.getReqMsgId()); // 报文标识
		msgid.setCreDtTm(MsgUtil.getCurrentTime()); // 报文时间

		String draft = document.xmlText(options);
		String signPart = commerDraft.xmlText();
		return addSign(draft, signPart, draftSender.getIsAddSign());
	}

}
