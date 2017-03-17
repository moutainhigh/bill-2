package com.herongtech.rc.draft.msgProcessor.reqInfoProcessorFor034;

import org.xmlbean.ecds034.Account1;
import org.xmlbean.ecds034.Account2;
import org.xmlbean.ecds034.Collateralization1;
import org.xmlbean.ecds034.CommercialDraftCollateralizationRequest1;
import org.xmlbean.ecds034.Organisation1;
import org.xmlbean.ecds034.Organisation2;
import org.xmlbean.ecds034.Organisation8;
import org.xmlbean.ecds034.RequestInformation1;

import com.herongtech.rc.draft.common.MsgUtil;
import com.herongtech.rc.draft.common.vo.DraftInfoVo;
/**
 * 质押申请
 *
 */
public class ReqInfoProcessorCommercialDraftCollateralizationRequest extends ReqInfoProcessor{
	
	@Override
	public DraftInfoVo parseReqInfo(RequestInformation1 reqInfo, DraftInfoVo vo) {     
		CommercialDraftCollateralizationRequest1 Collateralization=
		        reqInfo.getCommercialDraftCollateralizationRequest();
		String msgId=Collateralization.getMsgId().getId();
        vo.setReqMsgId(msgId);		
		{//质押信息
			Collateralization1 collztn=Collateralization.getCollztn();
			String Dt = MsgUtil.convertToString(collztn.getDt());//质押申请日期	
			String BtchNb = collztn.getBtchNb();//批次号
			String Rmrk=collztn.getRmrkByPropsr();//备注
			vo.setApplyDt(MsgUtil.converISODateTime(Dt));
			vo.setBtchNb(BtchNb);
			vo.setFromRemark(Rmrk);
		}
		
		{//出质人信息
			Organisation1 collztnPropsr=Collateralization.getCollztnPropsr();
			String Role = collztnPropsr.getRole().toString();//类别
			String Nm = collztnPropsr.getNm();//名称
			String CmonId = collztnPropsr.getCmonId();//组织机构代码
			String ElctrncSgntr = collztnPropsr.getElctrncSgntr();//电子签名
			Account1 acct=collztnPropsr.getAcct();
			String acctId = acct.getId();;//帐号
			String AcctSvcr = acct.getAcctSvcr();//开户行行号			
			Organisation8 agcyElement= collztnPropsr.getAgcy();
			
			Account2 acctEle2 = null;
			if(agcyElement != null) {
				acctEle2 = agcyElement.getAcct();
			}
			String AcctSvcr2 = null;
			if (acctEle2!=null)
				AcctSvcr2 =acctEle2.getAcctSvcr();//承接行行号
			vo.setFromName(Nm);
            vo.setFromElctrncSgntr(ElctrncSgntr);
            vo.setFromAcctNo(acctId);
            vo.setFromBankNo(AcctSvcr);
            vo.setFromRoleType(Role);
            vo.setFromBankName(Nm);
            vo.setFromOrgCode(CmonId);
            vo.setFromAgcyBankNo(AcctSvcr2);
		}
		{//质权人信息
			Organisation2 collztnBkEle=Collateralization.getCollztnBk();
			String Nm = collztnBkEle.getNm();//名称
			Account1 acct= collztnBkEle.getAcct();
			String acctId = acct.getId();//帐号
			String acctSvcr = acct.getAcctSvcr();//开户行行号
			vo.setReceiveAcctNo(acctId);
            vo.setReceiveBankNo(acctSvcr);
            vo.setReceiveBankName(Nm);
            vo.setReceiveName(Nm);
		}
		vo.setBusiType("018");
		return vo;
	}

}
