package com.herongtech.rc.draft.msgProcessor.reqInfoProcessorFor034;

import org.xmlbean.ecds034.Account1;
import org.xmlbean.ecds034.Account2;
import org.xmlbean.ecds034.CommercialDraftRepurchasedCollateralizationRequest1;
import org.xmlbean.ecds034.Organisation11;
import org.xmlbean.ecds034.Organisation8;
import org.xmlbean.ecds034.RepurchasedCollateralization1;
import org.xmlbean.ecds034.RequestInformation1;

import com.herongtech.rc.draft.common.MsgUtil;
import com.herongtech.rc.draft.common.vo.DraftInfoVo;
/**
 * 质押解除申请
 *
 */
public class ReqInfoProcessorCommercialDraftRepurchasedCollateralizationRequest extends ReqInfoProcessor{


	@Override
	public DraftInfoVo parseReqInfo(RequestInformation1 reqInfo,DraftInfoVo vo) {      		
		CommercialDraftRepurchasedCollateralizationRequest1 reqEle = reqInfo.getCommercialDraftRepurchasedCollateralizationRequest();
		String msgId=reqEle.getMsgId().getId();
        vo.setReqMsgId(msgId);
		{//质押解除信息
			RepurchasedCollateralization1 rpdCollztn= reqEle.getRpdCollztn();
			String Dt = MsgUtil.convertToString(rpdCollztn.getDt());//质押解除申请日期
			String rmrk=rpdCollztn.getRmrkByPropsr();
			String batchNo=rpdCollztn.getBtchNb();
			
			vo.setApplyDt(MsgUtil.converISODateTime(Dt));
            vo.setBtchNb(batchNo);   
            vo.setFromRemark(rmrk); 
		}
		
		{//质押解除申请人信息
			
			Organisation11 collztnBkEle = reqEle.getCollztnBk();		
			String Role = collztnBkEle.getRole().toString();//类型
			String ElctrncSgntr = collztnBkEle.getElctrncSgntr();//电子签名
			String CmonId = collztnBkEle.getCmonId();//组织机构代码
			Account1  acctEle =  collztnBkEle.getAcct();
			String acctId = acctEle.getId();//帐号
			String AcctSvcr = acctEle.getAcctSvcr();//开户行行号			
			Organisation8 agcyElement = collztnBkEle.getAgcy();
			Account2  acctEle2 = null;
			String acctSvcr2 = null;
			if(agcyElement != null) {
				acctEle2 = agcyElement.getAcct();
				if (acctEle2!=null){
					acctSvcr2 =acctEle2.getAcctSvcr();//承接行行号
					
				}
			}
//			vo.setFromName(Nm);
            vo.setFromElctrncSgntr(ElctrncSgntr);
            vo.setFromAcctNo(acctId);
            vo.setFromBankNo(AcctSvcr);
            vo.setFromRoleType(Role);
//            vo.setFromBankName(Nm);
            vo.setFromOrgCode(CmonId);
            vo.setFromAgcyBankNo(acctSvcr2);
						
		}
		vo.setBusiType("019");
		return vo;
	}

}
