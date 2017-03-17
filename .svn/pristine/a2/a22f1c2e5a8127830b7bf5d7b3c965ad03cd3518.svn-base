package com.herongtech.rc.draft.msgProcessor.histInfoProcessorFor034;

import org.xmlbean.ecds034.Account1;
import org.xmlbean.ecds034.Account2;
import org.xmlbean.ecds034.CommercialDraftBusiness1;
import org.xmlbean.ecds034.Organisation10;
import org.xmlbean.ecds034.Organisation8;
import org.xmlbean.ecds034.RepurchasedCollateralization4;

import com.herongtech.rc.domain.bean.RgctBillInfo;
import com.herongtech.rc.draft.common.MsgUtil;
import com.herongtech.rc.draft.common.vo.DraftInfoVo;
/**
 * 历史信息处理器－质押解除
 *
 */
public class HistoryInfoProcessorRpdCollztn extends HistoryInfoProcessor{

	@Override
	public DraftInfoVo parseHistoryInfo(CommercialDraftBusiness1 comrclDrftBiz, RgctBillInfo info) {
	    DraftInfoVo vo = new DraftInfoVo();
		
		RepurchasedCollateralization4 rpdCollztnEle = comrclDrftBiz.getRpdCollztn();
		{//质押解除信息
			String Dt = MsgUtil.convertToString(rpdCollztnEle.getDt());//质押解除申请日期
			vo.setSignDt(rpdCollztnEle.getDt().getTime());
		}
		
		{//质押解除申请人信息
			
			Organisation10  collztnBkEle = rpdCollztnEle.getCollztnBk();
			String Role = collztnBkEle.getRole().toString();//类别
			String Nm = collztnBkEle.getNm();//名称
			String CmonId =collztnBkEle.getCmonId();//组织机构代码
			Account1 acctEle =collztnBkEle.getAcct();
			String acctId = acctEle.getId();//帐号
			String AcctSvcr =acctEle.getAcctSvcr();//开户行行号			
			
			Organisation8  agcy2Ele = collztnBkEle.getAgcy();
			String AcctSvcr2 = null;
			if (agcy2Ele != null){
				Account2 acctEle2 = agcy2Ele.getAcct();
				
				if (acctEle2!=null)
					AcctSvcr2 = acctEle2.getAcctSvcr();//承接行行号
				
			}	
			 vo.setFromName(Nm);
             vo.setFromAcctNo(acctId);
             vo.setFromBankNo(AcctSvcr);
             vo.setFromRoleType(Role);
             vo.setFromOrgCode(CmonId);
             vo.setFromAgcyBankNo(AcctSvcr2);
		}
		
		{//质押解除签收人信息（出质人）
			
			Organisation10  orgnlCollztnProPsrEle = rpdCollztnEle.getOrgnlCollztnProPsr();
			orgnlCollztnProPsrEle.getAgcy();
			String Role = orgnlCollztnProPsrEle.getRole().toString();//类别
			String Nm = orgnlCollztnProPsrEle.getNm();//名称
			String CmonId = orgnlCollztnProPsrEle.getCmonId();//组织机构代码
			Account1 acctEle =  orgnlCollztnProPsrEle.getAcct();
			String acctId = acctEle.getId();//帐号
			String AcctSvcr = acctEle.getAcctSvcr();//开户行行号
			Organisation8 agcy2Ele = orgnlCollztnProPsrEle.getAgcy();
			if (agcy2Ele != null){
				Account2 acctEle2 = agcy2Ele.getAcct();
				String AcctSvcr2 = null;
				if (acctEle2!=null)
					AcctSvcr2 = acctEle2.getAcctSvcr();//承接行行号
			}
			vo.setReceiveName(Nm);
            vo.setReceiveAcctNo(acctId);
            vo.setReceiveBankNo(AcctSvcr);
            vo.setReceiveRoleType(Role);
            vo.setReceiveOrgCode(CmonId);
		}
		vo.setBusiType("");//此处业务类型表示背书类型
		return vo;
	}
	
	
}
