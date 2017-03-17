package com.herongtech.rc.draft.msgBuilder.requestBuilder;

import org.apache.commons.lang.StringUtils;
import org.apache.xmlbeans.XmlOptions;
import org.ecds018.Account1;
import org.ecds018.Collateralization1;
import org.ecds018.CommercialDraft1;
import org.ecds018.CommercialDraftCollateralizationRequestV01;
import org.ecds018.CurrencyAndAmount;
import org.ecds018.Document;
import org.ecds018.DocumentDocument;
import org.ecds018.MessageIdentification1;
import org.ecds018.Organisation1;
import org.ecds018.Organisation2;
import org.ecds018.Organisation3;
import org.ecds018.RoleCode;

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
 * 018质押申请
 * 
 */
public class CommercialDraftCollateralizationRequestDraftBuilder extends
		AbstractDraftBuilder {

	@Override
	public String buildBody(DraftSender draftSender) throws BizAppException {
		XmlOptions options = createXmlOptions(draftSender.getMesgType());

		DraftInfoVo vo = (DraftInfoVo) draftSender.getReqInfo();
		RgctBillInfo bill = vo.getBill();
		DocumentDocument document = DocumentDocument.Factory
				.newInstance(options);
		Document doc = document.addNewDocument();

		CommercialDraftCollateralizationRequestV01 commerDraft = doc
				.addNewCommercialDraftCollateralizationRequest();
		/* 报文信息 */
		MessageIdentification1 msgid = commerDraft.addNewMsgId();
		msgid.setId(vo.getReqMsgId()); // 报文标识
		msgid.setCreDtTm(MsgUtil.getCurrentTime()); // 报文时间

		/* 质押信息 */
		Collateralization1 collztn = commerDraft.addNewCollztn();
		if (StringUtils.isNotBlank(vo.getBtchNb())) {
			collztn.setBtchNb(vo.getBtchNb());
		}
		if (StringUtils.isNotBlank(vo.getFromRemark())) {
			collztn.setRmrkByPropsr(vo.getFromRemark());
		}

		collztn.setDt(MsgUtil.getConverTime(vo.getApplyDt()));

		/* 票面信息 */
		CommercialDraft1 info = commerDraft.addNewComrclDrft();
		info.setIdNb(bill.getBillNo());
		CurrencyAndAmount AMT = info.addNewIsseAmt();
		AMT.setCcy(DraftConstants.CURRENCY_TYPE);
		AMT.setStringValue(MoneyUtils.double2String(bill.getBillMoney()));
		/* 出质人信息 */
		Organisation1 from = commerDraft.addNewCollztnPropsr();
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

		/* 质权人信息 */
		Organisation2 receive = commerDraft.addNewCollztnBk();
		Account1 recAcct = receive.addNewAcct();
		recAcct.setId(vo.getReceiveAcctNo());
		recAcct.setAcctSvcr(vo.getReceiveBankNo());
		receive.setNm(vo.getReceiveName());

		String draft = document.xmlText(options);
		String signPart = commerDraft.xmlText();
		return addSign(draft, signPart, draftSender.getIsAddSign());
	}

}
