package com.herongtech.rc.draft.msgBuilder.requestBuilder;

import org.apache.commons.lang.StringUtils;
import org.apache.xmlbeans.XmlOptions;
import org.ecds017.Account1;
import org.ecds017.CommercialDraft1;
import org.ecds017.CommercialDraftGuaranteeRequestV01;
import org.ecds017.CurrencyAndAmount;
import org.ecds017.Document;
import org.ecds017.DocumentDocument;
import org.ecds017.Guarantee1;
import org.ecds017.MessageIdentification1;
import org.ecds017.Organisation1;
import org.ecds017.Organisation2;
import org.ecds017.Organisation3;
import org.ecds017.RoleCode;

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
 * 017保证
 * 
 */
public class CommercialDraftGuaranteeRequestDraftBuilder extends
		AbstractDraftBuilder {

	@Override
	public String buildBody(DraftSender draftSender) throws BizAppException {
		XmlOptions options = createXmlOptions(draftSender.getMesgType());

		DraftInfoVo vo = (DraftInfoVo) draftSender.getReqInfo();
		RgctBillInfo bill = vo.getBill();
		DocumentDocument document = DocumentDocument.Factory
				.newInstance(options);
		Document doc = document.addNewDocument();

		CommercialDraftGuaranteeRequestV01 commerDraft = doc
				.addNewCommercialDraftGuaranteeRequest();
		/* 报文信息 */
		MessageIdentification1 msgid = commerDraft.addNewMsgId();
		msgid.setId(vo.getReqMsgId()); // 报文标识
		msgid.setCreDtTm(MsgUtil.getCurrentTime()); // 报文时间

		/* 保证信息 */
		Guarantee1 guarant = commerDraft.addNewGuarntee();
		guarant.setDt(MsgUtil.getConverTime(vo.getApplyDt()));
		if (StringUtils.isNotBlank(vo.getFromRemark())) {
			guarant.setRmrkByPropsr(vo.getFromRemark());
		}
		if (StringUtils.isNotBlank(vo.getBtchNb())) {
			guarant.setBtchNb(vo.getBtchNb());
		}
		/* 票面信息 */
		CommercialDraft1 info = commerDraft.addNewComrclDrft();
		info.setIdNb(bill.getBillNo());
		CurrencyAndAmount AMT = info.addNewIsseAmt();
		AMT.setCcy(DraftConstants.CURRENCY_TYPE);
		AMT.setStringValue(MoneyUtils.double2String(bill.getBillMoney()));
		/* 被保证人信息 */
		Organisation1 from = commerDraft.addNewWarntee();
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

		/* 保证人信息 */
		Organisation2 receive = commerDraft.addNewGuarntr();
		Account1 recAcct = receive.addNewAcct();
		recAcct.setId(vo.getReceiveAcctNo());
		recAcct.setAcctSvcr(vo.getReceiveBankNo());
		receive.setNm(vo.getReceiveName());

		String draft = document.xmlText(options);
		String signPart = commerDraft.xmlText();
		return addSign(draft, signPart, draftSender.getIsAddSign());
	}

}
