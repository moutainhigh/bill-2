package com.herongtech.rc.draft.msgBuilder.requestBuilder;

import org.apache.commons.lang.StringUtils;
import org.apache.xmlbeans.XmlOptions;
import org.ecds021.Account1;
import org.ecds021.CommercialDraft1;
import org.ecds021.CommercialDraftOverduePresentationRequestV01;
import org.ecds021.CurrencyAndAmount;
import org.ecds021.Document;
import org.ecds021.DocumentDocument;
import org.ecds021.MessageIdentification1;
import org.ecds021.Organisation1;
import org.ecds021.Organisation2;
import org.ecds021.OverduePresentation1;
import org.ecds021.ProxyPropositionCode;
import org.ecds021.RoleCode;
import org.ecds021.SettlementMarkCode;

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
 * 021逾期提示付款
 * 
 */
public class CommercialDraftOverduePresentationRequestDraftBuilder extends
		AbstractDraftBuilder {

	@Override
	public String buildBody(DraftSender draftSender) throws BizAppException {
		XmlOptions options = createXmlOptions(draftSender.getMesgType());

		DraftInfoVo vo = (DraftInfoVo) draftSender.getReqInfo();
		RgctBillInfo bill = vo.getBill();
		DocumentDocument document = DocumentDocument.Factory
				.newInstance(options);
		Document doc = document.addNewDocument();

		CommercialDraftOverduePresentationRequestV01 commerDraft = doc
				.addNewCommercialDraftOverduePresentationRequest();
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
		/* 逾期提示付款信息 */
		OverduePresentation1 presentInfo = commerDraft.addNewOvrduePrsnttn();
		presentInfo.setApplDt(MsgUtil.getConverTime(vo.getApplyDt()));
		CurrencyAndAmount money = presentInfo.addNewAmt();
		money.setCcy(DraftConstants.CURRENCY_TYPE);
		money.setStringValue(MoneyUtils.double2String(bill.getBillMoney()));
		presentInfo.setRsn(vo.getOverdueReason());
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
