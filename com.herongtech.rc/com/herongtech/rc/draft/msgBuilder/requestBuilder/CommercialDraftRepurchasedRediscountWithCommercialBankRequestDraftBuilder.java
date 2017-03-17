package com.herongtech.rc.draft.msgBuilder.requestBuilder;

import java.math.BigDecimal;

import org.apache.commons.lang.StringUtils;
import org.apache.xmlbeans.XmlOptions;
import org.ecds014.Account1;
import org.ecds014.Account2;
import org.ecds014.BanEndorsementMarkCode;
import org.ecds014.CommercialDraft1;
import org.ecds014.CommercialDraftRepurchasedRediscountWithCommercialBankRequestV01;
import org.ecds014.CurrencyAndAmount;
import org.ecds014.Document;
import org.ecds014.DocumentDocument;
import org.ecds014.MessageIdentification1;
import org.ecds014.Organisation1;
import org.ecds014.Organisation2;
import org.ecds014.RepurchasedRediscountWithCommercialBank1;
import org.ecds014.RoleCode;
import org.ecds014.SettlementMarkCode;

import com.herongtech.console.core.constant.DraftConstants;
import com.herongtech.console.core.util.MoneyUtils;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.rc.domain.bean.RgctBillInfo;
import com.herongtech.rc.draft.common.MsgUtil;
import com.herongtech.rc.draft.common.vo.DraftInfoVo;
import com.herongtech.rc.draft.common.vo.DraftSender;
import com.herongtech.rc.draft.msgBuilder.AbstractDraftBuilder;

/**
 * 014回购式转贴现赎回申请
 * 
 */
public class CommercialDraftRepurchasedRediscountWithCommercialBankRequestDraftBuilder
		extends AbstractDraftBuilder {

	@Override
	public String buildBody(DraftSender draftSender) throws BizAppException {
		XmlOptions options = createXmlOptions(draftSender.getMesgType());

		DraftInfoVo vo = (DraftInfoVo) draftSender.getReqInfo();
		RgctBillInfo bill = vo.getBill();
		DocumentDocument document = DocumentDocument.Factory
				.newInstance(options);
		Document doc = document.addNewDocument();
		CommercialDraftRepurchasedRediscountWithCommercialBankRequestV01 commerDraft = doc
				.addNewCommercialDraftRepurchasedRediscountWithCommercialBankRequest();
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

		RepurchasedRediscountWithCommercialBank1 rpDisc = commerDraft
				.addNewRpdRdscntWthComrclBk();
		rpDisc.setDt(MsgUtil.getConverTime(vo.getApplyDt()));
		rpDisc.setIntrstRate(new BigDecimal(vo.getRpdIntrstRate().toString()));
		CurrencyAndAmount rpdAmt = rpDisc.addNewAmt();
		rpdAmt.setCcy(DraftConstants.CURRENCY_TYPE);
		rpdAmt.setStringValue(MoneyUtils.double2String(vo.getRpdAmt()));// 这里字段不要取错
		rpDisc.setBanEndrsmtMk(BanEndorsementMarkCode.Enum.forString(vo
				.getBanEndrsmtMk()));
		rpDisc.setSttlmMk(SettlementMarkCode.Enum.forString(vo.getSettleMk()));
		if (StringUtils.isNotBlank(vo.getBtchNb())) {
			rpDisc.setBtchNb(vo.getBtchNb());
		}
		if (StringUtils.isNotBlank(vo.getFromRemark())) {
			rpDisc.setRmrkByPropsr(vo.getFromRemark());
		}
		Organisation1 discBk = commerDraft
				.addNewOrgnlRcvgBkOfRdscntWthComrclBk();
		discBk.setRole(RoleCode.Enum.forString(vo.getFromRoleType()));
		discBk.setCmonId(vo.getFromOrgCode());
		discBk.setElctrncSgntr(vo.getFromElctrncSgntr());
		Account1 acct = discBk.addNewAcct();
		acct.setId(vo.getFromAcctNo());
		acct.setAcctSvcr(vo.getFromBankNo());
		if (StringUtils.isNotBlank(vo.getFromAgcyBankNo())) {
			Organisation2 agcy = discBk.addNewAgcy();
			Account2 agcyAcct = agcy.addNewAcct();
			agcyAcct.setAcctSvcr(vo.getFromAgcyBankNo());
		}

		String draft = document.xmlText(options);
		String signPart = commerDraft.xmlText();
		return addSign(draft, signPart, draftSender.getIsAddSign());
	}

}
