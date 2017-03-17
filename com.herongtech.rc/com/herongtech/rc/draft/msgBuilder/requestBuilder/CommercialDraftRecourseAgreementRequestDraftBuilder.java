package com.herongtech.rc.draft.msgBuilder.requestBuilder;

import com.herongtech.exception.impl.BizAppException;
import com.herongtech.rc.draft.common.vo.DraftSender;
import com.herongtech.rc.draft.msgBuilder.AbstractDraftBuilder;

/**
 * 023追索同意清偿申请
 * 
 */
public class CommercialDraftRecourseAgreementRequestDraftBuilder extends
		AbstractDraftBuilder {

	@Override
	public String buildBody(DraftSender draftSender) throws BizAppException {
		// TODO：追索清偿先不做
		return null;
	}

}
