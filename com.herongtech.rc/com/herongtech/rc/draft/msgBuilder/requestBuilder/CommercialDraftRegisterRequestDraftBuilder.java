package com.herongtech.rc.draft.msgBuilder.requestBuilder;

import org.apache.commons.lang.StringUtils;
import org.apache.xmlbeans.XmlOptions;
import org.ecds001.Account1;
import org.ecds001.BanEndorsementMarkCode;
import org.ecds001.CommercialDraft1;
import org.ecds001.CommercialDraftRegisterRequestV01;
import org.ecds001.CurrencyAndAmount;
import org.ecds001.Document;
import org.ecds001.DocumentDocument;
import org.ecds001.DraftTypeCode;
import org.ecds001.MessageIdentification1;
import org.ecds001.Organisation1;
import org.ecds001.Organisation2;
import org.ecds001.RoleCode;

import com.herongtech.console.core.constant.DraftConstants;
import com.herongtech.console.core.util.MoneyUtils;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.rc.domain.bean.RgctBillInfo;
import com.herongtech.rc.draft.common.MsgUtil;
import com.herongtech.rc.draft.common.vo.DraftInfoVo;
import com.herongtech.rc.draft.common.vo.DraftSender;
import com.herongtech.rc.draft.msgBuilder.AbstractDraftBuilder;

/**
 * 出票登记
 * 
 * @author litao
 * 
 */
public class CommercialDraftRegisterRequestDraftBuilder extends
		AbstractDraftBuilder {

	@Override
	public String buildBody(DraftSender draftSender) throws BizAppException {
		XmlOptions options = createXmlOptions(draftSender.getMesgType());

		DraftInfoVo vo = (DraftInfoVo) draftSender.getReqInfo();
		RgctBillInfo bill = vo.getBill();
		DocumentDocument document = DocumentDocument.Factory
				.newInstance(options);
		Document doc = document.addNewDocument();
		CommercialDraftRegisterRequestV01 commDraft = doc
				.addNewCommercialDraftRegisterRequest();
		// 报文标识
		MessageIdentification1 msgid = commDraft.addNewMsgId();
		msgid.setId(vo.getReqMsgId()); // 报文标识
		msgid.setCreDtTm(MsgUtil.getCurrentTime()); // 报文时间
		// 承兑人
		Organisation2 acceptor = commDraft.addNewAccptr();
		acceptor.setNm(bill.getAcceptor());
		Account1 acct = acceptor.addNewAcct();
		acct.setId(bill.getAcceptorAcct());
		acct.setAcctSvcr(bill.getAcceptorBankNo());

		// 票据信息
		CommercialDraft1 comrcldrft = commDraft.addNewComrclDrft();
		CurrencyAndAmount billAmt = (CurrencyAndAmount) comrcldrft
				.addNewIsseAmt();
		billAmt.setCcy(DraftConstants.CURRENCY_TYPE);
		billAmt.setStringValue(MoneyUtils.double2String(bill.getBillMoney()));
		comrcldrft.setIsseAmt(billAmt);
		comrcldrft.setDueDt(MsgUtil.StringtoCalendar(bill.getDueDt())); // 到期日
		comrcldrft.setIsseDt(MsgUtil.StringtoCalendar(bill.getIssueDt())); // 出票日期
		comrcldrft.setTp(DraftTypeCode.Enum.forString(DraftConstants.RGCT_CODE_MAPPING_MAP.get("BILL_TYPE_"+bill.getBillType())));

		comrcldrft.setBanEndrsmtMk(BanEndorsementMarkCode.Enum.forString(vo
				.getBanEndrsmtMk()));
		if (StringUtils.isNotBlank(bill.getRemark())) {
			comrcldrft.setRmrk(bill.getRemark());
		}
		// 出票人信息
		Organisation1 drwr = commDraft.addNewDrwr();
		drwr.setRole(RoleCode.Enum.forString(bill.getRemitterRole())); // 出票人类别
		drwr.setNm(bill.getRemitter()); // 出票人姓名
		drwr.setCmonId(bill.getRemitterOrgCode()); // 组织机构
		drwr.setElctrncSgntr(bill.getRemitterSign()); // 出票人电子签名
		Account1 Remitteracct = drwr.addNewAcct();
		Remitteracct.setId(bill.getRemitterAcct());
		Remitteracct.setAcctSvcr(bill.getRemitterBankNo());
		if (StringUtils.isNotBlank(bill.getDrwrCreditrating())) {
			drwr.setCdtRatgs(bill.getDrwrCreditrating()); // 信用等级
		}
		if (StringUtils.isNotBlank(bill.getDrwrCreditratingAgency())) {
			drwr.setCdtRatgAgcy(bill.getDrwrCreditratingAgency()); // 评级机构
		}
		if (StringUtils.isNotBlank(bill.getDrwrCreditratingDuedt())) {
			drwr.setCdtRatgDueDt(MsgUtil.StringtoCalendar(bill
					.getDrwrCreditratingDuedt())); // 评级到期日
		}

		comrcldrft.setBanEndrsmtMk(BanEndorsementMarkCode.Enum.forString(vo.getBanEndrsmtMk()));
        if(StringUtils.isNotBlank(bill.getRemark())){
            comrcldrft.setRmrk(bill.getRemark());
        }

        
        
        //收款人信息
        Organisation2 pyee = commDraft.addNewPyee();
        pyee.setNm(bill.getPayeeName());
        Account1 pyeeacc = pyee.addNewAcct();
        pyeeacc.setId(bill.getPayeeAcct());
        pyeeacc.setAcctSvcr(bill.getPayeeBankNo());
        
        
        
        String draft=document.xmlText(options);
        String signPart=commDraft.xmlText();
        return addSign(draft, signPart,draftSender.getIsAddSign());
    }


	
}
