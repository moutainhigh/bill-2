package com.herongtech.rc.draft.msgBuilder.requestBuilder;

import java.math.BigDecimal;

import org.apache.commons.lang.StringUtils;
import org.apache.xmlbeans.XmlOptions;
import org.ecds011.Account1;
import org.ecds011.Account2;
import org.ecds011.BanEndorsementMarkCode;
import org.ecds011.CommercialDraft1;
import org.ecds011.CommercialDraftDiscountRequestV01;
import org.ecds011.CurrencyAndAmount;
import org.ecds011.Discount1;
import org.ecds011.Document;
import org.ecds011.DocumentDocument;
import org.ecds011.MessageIdentification1;
import org.ecds011.Organisation1;
import org.ecds011.Organisation2;
import org.ecds011.Organisation3;
import org.ecds011.RepurchasedMarkCode;
import org.ecds011.RoleCode;
import org.ecds011.SettlementMarkCode;

import com.herongtech.console.core.constant.DraftConstants;
import com.herongtech.console.core.util.MoneyUtils;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.rc.domain.bean.RgctBillInfo;
import com.herongtech.rc.draft.common.MsgUtil;
import com.herongtech.rc.draft.common.vo.DraftInfoVo;
import com.herongtech.rc.draft.common.vo.DraftSender;
import com.herongtech.rc.draft.msgBuilder.AbstractDraftBuilder;

public class CommercialDraftDiscountRequestDraftBuilder extends
		AbstractDraftBuilder {

	@Override
	public String buildBody(DraftSender draftSender) throws BizAppException {
		XmlOptions options = createXmlOptions(draftSender.getMesgType());

		DraftInfoVo vo = (DraftInfoVo) draftSender.getReqInfo();
		RgctBillInfo bill = vo.getBill();
		DocumentDocument document = DocumentDocument.Factory
				.newInstance(options);
		Document doc = document.addNewDocument();
		CommercialDraftDiscountRequestV01 root = doc
				.addNewCommercialDraftDiscountRequest();

		/* 报文标示 */
		MessageIdentification1 msgId = root.addNewMsgId();
		msgId.setId(vo.getReqMsgId());
		msgId.setCreDtTm(MsgUtil.getCurrentTime());

		/* 票据信息 */
		CommercialDraft1 commer = root.addNewComrclDrft();
		commer.setIdNb(bill.getBillNo());
		// 票据金额
		CurrencyAndAmount billAmt = commer.addNewIsseAmt();
		billAmt.setCcy(DraftConstants.CURRENCY_TYPE);
		billAmt.setStringValue(MoneyUtils.double2String(bill.getBillMoney()));
		/* 入账信息 */
		Account1 inAcct = root.addNewAOAccnInf();
		inAcct.setId(vo.getInAcct());
		inAcct.setAcctSvcr(vo.getInBankNo());
		/* 贴现信息 */
		Discount1 discount = root.addNewDscnt();
		CurrencyAndAmount payAmt = discount.addNewAmt();
		payAmt.setCcy(DraftConstants.CURRENCY_TYPE);
		payAmt.setStringValue(MoneyUtils.double2String(vo.getAmt()));
		discount.setRpdMk(RepurchasedMarkCode.Enum.forString(vo.getRpdMk()));
		discount.setDt(MsgUtil.getConverTime(vo.getApplyDt()));
		
		discount.setIntrstRate(new BigDecimal(vo.getIntrstRate()).setScale(6,BigDecimal.ROUND_HALF_UP));
		discount.setBanEndrsmtMk(BanEndorsementMarkCode.Enum.forString(vo
				.getBanEndrsmtMk()));
		discount.setSttlmMk(SettlementMarkCode.Enum.forString(vo.getSettleMk()));
		if (DraftConstants.REPURCHASED_MARK_CODE_RM01.equals(vo.getRpdMk())) {
			// 回购类
			discount.setRpdIntrstRate(new BigDecimal(vo.getRpdIntrstRate().toString()));
			discount.setRpdDueDt(MsgUtil.getConverTime(vo.getRpdDueDt()));
			discount.setRpdOpenDt(MsgUtil.getConverTime(vo.getRpdOpenDt()));
			CurrencyAndAmount rpdAmt = discount.addNewRpdAmt();
			rpdAmt.setCcy(DraftConstants.CURRENCY_TYPE);
			rpdAmt.setStringValue(MoneyUtils.double2String(vo.getRpdAmt()));
		}
		if (StringUtils.isNotBlank(vo.getBtchNb())) {
			discount.setBtchNb(vo.getBtchNb());
		}
		if (StringUtils.isNotBlank(vo.getInvcNb())) {
			discount.setInvcNb(vo.getInvcNb());
		}
		if (StringUtils.isNotBlank(vo.getTxlCtrctNb())) {
			discount.setTxlCtrctNb(vo.getTxlCtrctNb());
		}
		if (StringUtils.isNotBlank(vo.getFromRemark())) {
			discount.setRmrkByPropsr(vo.getFromRemark());
		}

		/* 贴现申请人信息 */
		Organisation1 dscnt = root.addNewDscntPropsr();
		dscnt.setRole(RoleCode.Enum.forString(vo.getFromRoleType()));
		dscnt.setNm(vo.getFromName());
		dscnt.setCmonId(vo.getFromOrgCode());
		dscnt.setElctrncSgntr(vo.getFromElctrncSgntr());
		Account1 acct = dscnt.addNewAcct();
		acct.setId(vo.getFromAcctNo());
		acct.setAcctSvcr(vo.getFromBankNo());
		if (StringUtils.isNotBlank(vo.getFromAgcyBankNo())) {
			Organisation3 agcy = dscnt.addNewAgcy();
			Account2 agcyAcct = agcy.addNewAcct();
			agcyAcct.setAcctSvcr(vo.getFromAgcyBankNo());
		}

		/* 贴入人信息 */
		Organisation2 dscntBk = root.addNewDscntBk();
		dscntBk.setNm(vo.getReceiveName());
		Account1 toBank = dscntBk.addNewAcct();
		toBank.setId(vo.getReceiveAcctNo());
		toBank.setAcctSvcr(vo.getReceiveBankNo());
		String draft = document.xmlText(options);
		String signPart = root.xmlText();
		return addSign(draft, signPart, draftSender.getIsAddSign());
	}
	
	
}
