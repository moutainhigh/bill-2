package com.herongtech.rc.draft.msgBuilder.requestBuilder;

import org.apache.commons.lang.StringUtils;
import org.apache.xmlbeans.XmlOptions;
import org.ecds107.Account1;
import org.ecds107.AdditionalInformation1;
import org.ecds107.Collection1;
import org.ecds107.CommercialDraft1;
import org.ecds107.CommercialDraftCollectionRegisterV01;
import org.ecds107.CurrencyAndAmount;
import org.ecds107.Document;
import org.ecds107.DocumentDocument;
import org.ecds107.DraftTypeCode;
import org.ecds107.MessageIdentification1;
import org.ecds107.Organisation1;
import org.ecds107.Organisation2;

import com.herongtech.console.core.constant.DraftConstants;
import com.herongtech.console.core.util.MoneyUtils;
import com.herongtech.console.web.busicontroller.common.CommonConst;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.rc.domain.bean.RgctBillInfo;
import com.herongtech.rc.draft.common.MsgUtil;
import com.herongtech.rc.draft.common.vo.DraftInfoVo;
import com.herongtech.rc.draft.common.vo.DraftSender;
import com.herongtech.rc.draft.msgBuilder.AbstractDraftBuilder;

public class CommercialDraftCollectionRegisterDraftBuilder extends AbstractDraftBuilder{

	@Override
	public String buildBody(DraftSender draftSender) throws BizAppException {
		XmlOptions options = createXmlOptions(draftSender.getMesgType());

		DraftInfoVo vo = (DraftInfoVo) draftSender.getReqInfo();
		RgctBillInfo bill = vo.getBill();
		DocumentDocument document = DocumentDocument.Factory.newInstance(options);
		Document doc = document.addNewDocument();
		CommercialDraftCollectionRegisterV01 commerCollection = doc.addNewCommercialDraftCollectionRegister();
		
		/* 报文标识 */
		MessageIdentification1 msgId = commerCollection.addNewMsgId();
		msgId.setId(vo.getReqMsgId());
		msgId.setCreDtTm(MsgUtil.getCurrentTime());
		
		/* 票据信息 */
		CommercialDraft1 commer = commerCollection.addNewComrclDrft();
		commer.setTp(DraftTypeCode.Enum.forString(DraftConstants.RGCT_CODE_MAPPING_MAP.get("BILL_TYPE_"+bill.getBillType())));
		commer.setIdNb(bill.getBillNo());
		CurrencyAndAmount billAmt = commer.addNewIsseAmt();
		billAmt.setCcy(DraftConstants.CURRENCY_TYPE);
		billAmt.setStringValue(MoneyUtils.double2String(bill.getBillMoney()));
		commer.setIsseDt(MsgUtil.getConverTime(MsgUtil.converISODateTime(bill.getIssueDt())));
		commer.setDueDt(MsgUtil.getConverTime(MsgUtil.converISODateTime(bill.getDueDt())));
		
		/* 委托收款登记信息 */
		Collection1 coll = commerCollection.addNewColltn();
		coll.setDt(MsgUtil.getConverTime(MsgUtil.converISODateTime(vo.getSubCollDt())));
		coll.setEndrsmtCnt(vo.getEndorNum());
		
		/* 承兑人信息 */
		Organisation2 org = commerCollection.addNewAccptr();
		org.setNm(bill.getAcceptor());
		Account1 acct = org.addNewAcct();
		if(CommonConst.BILL_TYPE_BANK.equals(bill.getBillType())){
			acct.setAcctSvcr(bill.getAcceptorBankNo());
		}else{
			acct.setAcctSvcr(bill.getDraweeBankNo());
		}
		
		/* 委托收款银行名称 */
		Organisation2 orga = commerCollection.addNewColltgBk();
		orga.setNm(vo.getReceiveName());
		Account1 acct1 = orga.addNewAcct();
		acct1.setAcctSvcr(vo.getInBankNo());
		
		/* 最后一手持票人信息 */
		Organisation1 organ = commerCollection.addNewPrncpl();
		organ.setNm(vo.getFromName());
		
		/* 备注 */
		AdditionalInformation1 remark = commerCollection.addNewAddtlInf();
		if(!StringUtils.isBlank(bill.getRemark())){
			remark.setRmrk(bill.getRemark());
		}else{
			remark.setRmrk("107报文登记");
		}
		String draft = document.xmlText(options);
		String signPart = commerCollection.xmlText();
		return addSign(draft, signPart, draftSender.getIsAddSign());
	}

}
