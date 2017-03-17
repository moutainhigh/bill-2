package com.herongtech.rc.draft.msgBuilder.requestBuilder;

import org.apache.commons.lang.StringUtils;
import org.apache.xmlbeans.XmlOptions;
import org.ecds019.Account1;
import org.ecds019.CommercialDraft1;
import org.ecds019.CommercialDraftRepurchasedCollateralizationRequestV01;
import org.ecds019.CurrencyAndAmount;
import org.ecds019.Document;
import org.ecds019.DocumentDocument;
import org.ecds019.MessageIdentification1;
import org.ecds019.Organisation1;
import org.ecds019.Organisation2;
import org.ecds019.RepurchasedCollateralization1;
import org.ecds019.RoleCode;

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
 * 019解质押
 * 
 */
public class CommercialDraftRepurchasedCollateralizationRequestDraftBuilder
		extends AbstractDraftBuilder {

	@Override
	public String buildBody(DraftSender draftSender) throws BizAppException {
		XmlOptions options = createXmlOptions(draftSender.getMesgType());

		DraftInfoVo vo = (DraftInfoVo) draftSender.getReqInfo();
		RgctBillInfo bill = vo.getBill();
		DocumentDocument document = DocumentDocument.Factory
				.newInstance(options);
		Document doc = document.addNewDocument();
		CommercialDraftRepurchasedCollateralizationRequestV01 commerDraft = doc
				.addNewCommercialDraftRepurchasedCollateralizationRequest();

		CommercialDraft1 billinfo = commerDraft.addNewComrclDrft();
		billinfo.setIdNb(bill.getBillNo());
		CurrencyAndAmount AMT = billinfo.addNewIsseAmt();
		AMT.setCcy(DraftConstants.CURRENCY_TYPE);
		AMT.setStringValue(MoneyUtils.double2String(bill.getBillMoney()));

		RepurchasedCollateralization1 rpcoll = commerDraft.addNewRpdCollztn();
		rpcoll.setDt(MsgUtil.getConverTime(vo.getApplyDt()));
		if (StringUtils.isNotBlank(vo.getBtchNb())) {
			rpcoll.setBtchNb(vo.getBtchNb());
		}
		if (StringUtils.isNotBlank(vo.getFromRemark())) {
			rpcoll.setRmrkByPropsr(vo.getFromRemark());
		}
		Organisation1 from = commerDraft.addNewCollztnBk();
		Account1 acct = from.addNewAcct();
		acct.setId(vo.getFromAcctNo());
		acct.setAcctSvcr(vo.getFromBankNo());
		from.setCmonId(vo.getFromOrgCode());
		from.setElctrncSgntr(vo.getFromElctrncSgntr());
		from.setRole(RoleCode.Enum.forString(vo.getFromRoleType()));
		
		
		if (StringUtils.isNotBlank(vo.getFromAgcyBankNo())) {
			Organisation2 agcy = from.addNewAgcy();
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
