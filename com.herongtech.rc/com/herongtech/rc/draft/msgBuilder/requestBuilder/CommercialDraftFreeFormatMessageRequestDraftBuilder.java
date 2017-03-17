package com.herongtech.rc.draft.msgBuilder.requestBuilder;

import org.apache.commons.lang.StringUtils;
import org.apache.xmlbeans.XmlOptions;
import org.ecds039.BroadcastTypeCode;
import org.ecds039.CommercialDraftFreeFormatMessageRequestV01;
import org.ecds039.Document;
import org.ecds039.DocumentDocument;
import org.ecds039.MessageContents2;
import org.ecds039.MessageIdentification1;
import org.ecds039.Organisation1;

import com.herongtech.exception.impl.BizAppException;
import com.herongtech.rc.draft.common.MsgUtil;
import com.herongtech.rc.draft.common.vo.DraftInfoVo;
import com.herongtech.rc.draft.common.vo.DraftSender;
import com.herongtech.rc.draft.common.vo.SysMgrInfoVo;
import com.herongtech.rc.draft.msgBuilder.AbstractDraftBuilder;

/**
 * 039自由格式报文
 * 
 */
public class CommercialDraftFreeFormatMessageRequestDraftBuilder extends
		AbstractDraftBuilder {

	@Override
	public String buildBody(DraftSender draftSender) throws BizAppException {
		XmlOptions options = createXmlOptions(draftSender.getMesgType());

		SysMgrInfoVo vo = (SysMgrInfoVo) draftSender.getReqInfo();
		DocumentDocument document = DocumentDocument.Factory
				.newInstance(options);
		Document doc = document.addNewDocument();

		CommercialDraftFreeFormatMessageRequestV01 commerDraft = doc
				.addNewCommercialDraftFreeFormatMessageRequest();

		/* 报文信息 */
		MessageIdentification1 msgid = commerDraft.addNewMsgId();
		msgid.setId(vo.getReqMsgId()); // 报文标识
		msgid.setCreDtTm(MsgUtil.getCurrentTime()); // 报文时间
		MessageContents2 content = commerDraft.addNewMsgCnts();
		content.setCnts(vo.getContents());
		content.setBrdcstLvl(BroadcastTypeCode.Enum.forString(vo.getBorderLevel()));

		Organisation1 send = commerDraft.addNewSndr();
		send.addNewAcct().setAcctSvcr(vo.getFromBankNo());

		if (StringUtils.isNotBlank(vo.getReceiveBankNo())) {
			Organisation1 receive = commerDraft.addNewRcvr();
			receive.addNewAcct().setAcctSvcr(vo.getReceiveBankNo());
		}

		String draft = document.xmlText(options);
		String signPart = commerDraft.xmlText();
		return addSign(draft, signPart, draftSender.getIsAddSign());

	}

}
