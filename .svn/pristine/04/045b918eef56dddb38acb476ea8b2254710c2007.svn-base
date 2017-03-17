package com.herongtech.rc.draft.msgBuilder.requestBuilder;

import org.apache.commons.lang.StringUtils;
import org.apache.xmlbeans.XmlOptions;
import org.ecds010.Account1;
import org.ecds010.BanEndorsementMarkCode;
import org.ecds010.CommercialDraft1;
import org.ecds010.CommercialDraftEndorsementRequestV01;
import org.ecds010.CurrencyAndAmount;
import org.ecds010.Document;
import org.ecds010.DocumentDocument;
import org.ecds010.Endorsement1;
import org.ecds010.MessageIdentification1;
import org.ecds010.Organisation1;
import org.ecds010.Organisation2;
import org.ecds010.Organisation3;
import org.ecds010.RoleCode;

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
 * 010背书申请
 * 
 */
public class CommercialDraftEndorsementRequestDraftBuilder extends
		AbstractDraftBuilder {

	@Override
	public String buildBody(DraftSender draftSender) throws BizAppException {
		XmlOptions options = createXmlOptions(draftSender.getMesgType());

		DraftInfoVo vo = (DraftInfoVo) draftSender.getReqInfo();
		RgctBillInfo bill = vo.getBill();
		DocumentDocument document = DocumentDocument.Factory
				.newInstance(options);
		Document doc = document.addNewDocument();

		CommercialDraftEndorsementRequestV01 commerDraft = doc
				.addNewCommercialDraftEndorsementRequest();
		/* 报文信息 */
		MessageIdentification1 msgid = commerDraft.addNewMsgId();
		msgid.setId(vo.getReqMsgId()); // 报文标识
		msgid.setCreDtTm(MsgUtil.getCurrentTime()); // 报文时间

		/* 背书信息 */
		Endorsement1 endorse = commerDraft.addNewEndrsmt();
		endorse.setDt(MsgUtil.getConverTime(vo.getApplyDt()));
		if (StringUtils.isNotBlank(vo.getFromRemark())) {
			endorse.setRmrkByPropsr(vo.getFromRemark());
		}

		endorse.setBanEndrsmtMk(BanEndorsementMarkCode.Enum.forString(vo
				.getBanEndrsmtMk()));
		/* 票面信息 */
		CommercialDraft1 info = commerDraft.addNewComrclDrft();
		info.setIdNb(bill.getBillNo());
		CurrencyAndAmount AMT = info.addNewIsseAmt();
		AMT.setCcy(DraftConstants.CURRENCY_TYPE);
		AMT.setStringValue(MoneyUtils.double2String(bill.getBillMoney()));
		/* 背书人信息 */
		Organisation1 from = commerDraft.addNewEndrsr();
		Account1 acct = from.addNewAcct();
		acct.setId(vo.getFromAcctNo());
		acct.setAcctSvcr(vo.getFromBankNo());
		from.setCmonId(vo.getFromOrgCode());
		from.setElctrncSgntr(vo.getFromElctrncSgntr());
		from.setNm(vo.getFromName());
		from.setRole(RoleCode.Enum.forString(vo.getFromRoleType()));
		if (StringUtils.isNotBlank(vo.getFromAgcyBankNo())) {
			Organisation3 agcy = from.addNewAgcy();
			agcy.addNewAcct().setAcctSvcr(vo.getFromAgcyBankNo());
		}

		/* 被背书人信息 */
		Organisation2 receive = commerDraft.addNewEndrsee();
		Account1 recAcct = receive.addNewAcct();
		recAcct.setId(vo.getReceiveAcctNo());
		recAcct.setAcctSvcr(vo.getReceiveBankNo());
		receive.setNm(vo.getReceiveName());

		String draft = document.xmlText(options);
		String signPart = commerDraft.xmlText();
		return addSign(draft, signPart, draftSender.getIsAddSign());
	}

}
