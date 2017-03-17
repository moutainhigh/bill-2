package com.herongtech.rc.draft.msgProcessor.histInfoProcessorFor034;

import org.xmlbean.ecds034.Account1;
import org.xmlbean.ecds034.Account2;
import org.xmlbean.ecds034.CommercialDraftBusiness1;
import org.xmlbean.ecds034.Organisation10;
import org.xmlbean.ecds034.Organisation8;
import org.xmlbean.ecds034.Recourse5;

import com.herongtech.rc.domain.bean.RgctBillInfo;
import com.herongtech.rc.draft.common.MsgUtil;
import com.herongtech.rc.draft.common.vo.DraftInfoVo;
/**
 * 历史信息处理器－追索
 *
 */
public class HistoryInfoProcessorRcrs extends HistoryInfoProcessor{

	@Override
	public DraftInfoVo parseHistoryInfo(CommercialDraftBusiness1 comrclDrftBiz, RgctBillInfo info) {
	    DraftInfoVo vo = new DraftInfoVo();
		Recourse5 rcrsEle = comrclDrftBiz.getRcrs();
		{//追索通知信息
			String Tp = rcrsEle.getTp().toString().toString();//追索类型RT00拒付追索RT01非拒付追索
			String ApplDt = MsgUtil.convertToString(rcrsEle.getApplDt());//追索通知日期
			String Dt = MsgUtil.convertToString(rcrsEle.getDt());//清偿日期
			String ReqAmt = rcrsEle.getReqAmt().getStringValue();//追索金额
			String Amt = rcrsEle.getAmt().toString();//清偿金额
			String RcrsRsnCd = rcrsEle.getRcrsRsnCd().toString();//追索理由代码,非拒付追索时必填
		}
		
		{//追索人信息		
			
			Organisation10  rcrsrEle = rcrsEle.getRcrsr();
			String Role =rcrsrEle.getRole().toString();//类别
			String Nm = rcrsrEle.getNm();//名称
			String CmonId = rcrsrEle.getCmonId();//组织机构代码
			Account1 acctEle =  rcrsrEle.getAcct();
			String acctId = acctEle.getId();//帐号
			String AcctSvcr = acctEle.getAcctSvcr();//开户行行号			
			
			Organisation8 agcy2Ele = rcrsrEle.getAgcy();
			if (agcy2Ele != null){
				Account2 acctEle2 = agcy2Ele.getAcct();
				String AcctSvcr2 = null;
				if (acctEle2!=null)
					AcctSvcr2 = acctEle2.getAcctSvcr();//承接行行号
				
			}
			
		}
		
		{//被追索人信息
			
			Organisation10  rcvgPrsnOfRcrsEle = rcrsEle.getRcvgPrsnOfRcrs();
			String Nm = rcvgPrsnOfRcrsEle.getNm();//名称
			String CmonId = rcvgPrsnOfRcrsEle.getCmonId();//组织机构代码
			Account1 acctEle = rcvgPrsnOfRcrsEle.getAcct();
			String acctId = acctEle.getId();//帐号
			String AcctSvcr =acctEle.getAcctSvcr();//开户行行号			
			Account2 acctEle2 = rcvgPrsnOfRcrsEle.getAgcy().getAcct();
			String AcctSvcr2 = null;
			if (acctEle2!=null)
				AcctSvcr2 = acctEle2.getAcctSvcr();//承接行行号
		}
		return vo;
	}
	
	
}
