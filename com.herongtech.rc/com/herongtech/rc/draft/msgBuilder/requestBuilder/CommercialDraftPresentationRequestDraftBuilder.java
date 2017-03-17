package com.herongtech.rc.draft.msgBuilder.requestBuilder;

import org.apache.commons.lang.StringUtils;
import org.apache.xmlbeans.XmlOptions;
import org.ecds020.Account1;
import org.ecds020.CommercialDraft1;
import org.ecds020.CommercialDraftPresentationRequestV01;
import org.ecds020.CurrencyAndAmount;
import org.ecds020.Document;
import org.ecds020.DocumentDocument;
import org.ecds020.MessageIdentification1;
import org.ecds020.Organisation1;
import org.ecds020.Organisation2;
import org.ecds020.Presentation1;
import org.ecds020.ProxyPropositionCode;
import org.ecds020.RoleCode;
import org.ecds020.SettlementMarkCode;

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
 * 020提示付款
 * 
 */
public class CommercialDraftPresentationRequestDraftBuilder extends
		AbstractDraftBuilder {

	@Override
	public String buildBody(DraftSender draftSender) throws BizAppException {
		XmlOptions options = createXmlOptions(draftSender.getMesgType());

		DraftInfoVo vo = (DraftInfoVo) draftSender.getReqInfo();
		RgctBillInfo bill = vo.getBill();
		DocumentDocument document = DocumentDocument.Factory
				.newInstance(options);
		Document doc = document.addNewDocument();

		CommercialDraftPresentationRequestV01 commerDraft = doc
				.addNewCommercialDraftPresentationRequest();
		/* 报文信息 */
		MessageIdentification1 msgid = commerDraft.addNewMsgId();
		msgid.setId(vo.getReqMsgId()); // 报文标识
		msgid.setCreDtTm(MsgUtil.getCurrentTime()); // 报文时间
		/* 票面信息 */
		CommercialDraft1 info = commerDraft.addNewComrclDrft();
		info.setIdNb(bill.getBillNo());
		CurrencyAndAmount AMT = info.addNewIsseAmt();
		AMT.setCcy(DraftConstants.CURRENCY_TYPE);
		AMT.setStringValue(MoneyUtils.double2String(bill.getBillMoney()));
		/* 提示付款信息 */
		Presentation1 presentInfo = commerDraft.addNewPrsnttn();
		presentInfo.setApplDt(MsgUtil.getConverTime(vo.getApplyDt()));
		CurrencyAndAmount money = presentInfo.addNewAmt();
		money.setCcy(DraftConstants.CURRENCY_TYPE);
		money.setStringValue(MoneyUtils.double2String(bill.getBillMoney()));
		presentInfo.setSttlmMk(SettlementMarkCode.Enum.forString(vo
				.getSettleMk()));
		presentInfo.setPrxyPropstn(ProxyPropositionCode.Enum
				.forString(DraftConstants.ProxyPropositionCode2));// TODO:待确定是否为固定值
		if (StringUtils.isNotBlank(vo.getFromRemark())) {
			presentInfo.setRmrkByPropsr(vo.getFromRemark());
		}
		/* 提示付款人信息 */
		Organisation1 from = commerDraft.addNewDrftHldr();
		Account1 acct = from.addNewAcct();
		acct.setId(vo.getFromAcctNo());
		acct.setAcctSvcr(vo.getFromBankNo());
		from.setCmonId(vo.getFromOrgCode());
		from.setElctrncSgntr(vo.getFromElctrncSgntr());
		from.setNm(vo.getFromName());
		from.setRole(RoleCode.Enum.forString(vo.getFromRoleType()));
		if (StringUtils.isNotBlank(vo.getFromAgcyBankNo())) {
			Organisation2 agcy = from.addNewAgcy();
			agcy.addNewAcct().setAcctSvcr(vo.getFromAgcyBankNo());
		}

		String draft = document.xmlText(options);
		String signPart = commerDraft.xmlText();
		return addSign(draft, signPart, draftSender.getIsAddSign());
	}

}
