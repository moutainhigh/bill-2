
package com.herongtech.rc.draft.msgProcessor.reqInfoProcessorFor034;

import org.xmlbean.ecds034.Account1;
import org.xmlbean.ecds034.Account2;
import org.xmlbean.ecds034.CommercialDraftGuaranteeRequest1;
import org.xmlbean.ecds034.Guarantee1;
import org.xmlbean.ecds034.Organisation1;
import org.xmlbean.ecds034.Organisation2;
import org.xmlbean.ecds034.Organisation8;
import org.xmlbean.ecds034.RequestInformation1;

import com.herongtech.rc.draft.common.MsgUtil;
import com.herongtech.rc.draft.common.vo.DraftInfoVo;

/**
 * 保证申请
 * 
 */
public class ReqInfoProcessorCommercialDraftGuaranteeRequest extends
		ReqInfoProcessor {

	
	@Override
	public DraftInfoVo parseReqInfo(RequestInformation1 reqInfo,DraftInfoVo vo) {      
		CommercialDraftGuaranteeRequest1 reqEle=reqInfo.getCommercialDraftGuaranteeRequest();
		String msgId=reqEle.getMsgId().getId();
		vo.setReqMsgId(msgId);

		{// 保证信息
			Guarantee1  guarntee=reqEle.getGuarntee();
			String Dt = MsgUtil.convertToString(guarntee.getDt());// 保证申请日期
			String BtchNb=guarntee.getBtchNb();
			String Rmrk=guarntee.getRmrkByPropsr();
			vo.setApplyDt(MsgUtil.converISODateTime(Dt));
            vo.setBtchNb(BtchNb);
            vo.setFromRemark(Rmrk);
		}

		{// 被保证人信息
			Organisation1 warnteeEle=reqEle.getWarntee();
			String Role = warnteeEle.getRole().toString();// 类别
			String Nm =warnteeEle.getNm();;// 名称
			String CmonId =warnteeEle.getCmonId();// 组织机构代码
			String ElctrncSgntr =warnteeEle.getElctrncSgntr();// 电子签名
			Account1 acct=warnteeEle.getAcct();
			String acctId = acct.getId();// 帐号
			String AcctSvcr = acct.getAcctSvcr();// 开户行行号
			Organisation8 agcyElement =warnteeEle.getAgcy();
			Account2 acctEle2 = null;
			if (agcyElement != null) {
				acctEle2 =agcyElement.getAcct();
			}
			String AcctSvcr2 = null;
			if (acctEle2 != null)
				AcctSvcr2 = acctEle2.getAcctSvcr();// 承接行行号
			vo.setFromName(Nm);
            vo.setFromElctrncSgntr(ElctrncSgntr);
            vo.setFromAcctNo(acctId);
            vo.setFromBankNo(AcctSvcr);
            vo.setFromRoleType(Role);
            vo.setFromBankName(Nm);
            vo.setFromOrgCode(CmonId);
            vo.setFromAgcyBankNo(AcctSvcr2);


		}
		{// 保证人信息
			Organisation2 guarntrEle=reqEle.getGuarntr();
			Account1 acct=guarntrEle.getAcct();
			String Nm = guarntrEle.getNm();// 名称
			String acctId = acct.getId();// 帐号
			String AcctSvcr = acct.getAcctSvcr();// 开户行行号
			vo.setReceiveAcctNo(acctId);
            vo.setReceiveBankNo(AcctSvcr);
            vo.setReceiveBankName(Nm);
            vo.setReceiveName(Nm);
		}
		vo.setBusiType("017");
		return vo;
	}
		
}
