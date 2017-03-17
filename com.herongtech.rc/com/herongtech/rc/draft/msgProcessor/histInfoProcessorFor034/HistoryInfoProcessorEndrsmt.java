package com.herongtech.rc.draft.msgProcessor.histInfoProcessorFor034;

import org.xmlbean.ecds034.Account1;
import org.xmlbean.ecds034.Account2;
import org.xmlbean.ecds034.CommercialDraftBusiness1;
import org.xmlbean.ecds034.Endorsement4;
import org.xmlbean.ecds034.Organisation10;
import org.xmlbean.ecds034.Organisation5;
import org.xmlbean.ecds034.Organisation8;

import com.herongtech.rc.domain.bean.RgctBillInfo;
import com.herongtech.rc.draft.common.MsgUtil;
import com.herongtech.rc.draft.common.vo.DraftInfoVo;
/**
 * 历史信息处理器－背书
 *
 */
public class HistoryInfoProcessorEndrsmt extends HistoryInfoProcessor{

	@Override
	public DraftInfoVo parseHistoryInfo(CommercialDraftBusiness1 comrclDrftBiz, RgctBillInfo info) {
	    DraftInfoVo vo = new DraftInfoVo();
		
		Endorsement4 endrsmtEle = comrclDrftBiz.getEndrsmt();		
		{//背书信息
//			String Dt = MsgUtil.convertToString(endrsmtEle.getDt());//背书申请日期
			String BanEndrsmtMk = endrsmtEle.getBanEndrsmtMk().toString();//不得转让标记			//
			vo.setSignDt(endrsmtEle.getDt().getTime());
            vo.setBanEndrsmtMk(BanEndrsmtMk);
		}
		
		{//背书人信息
			Organisation10 endrsrEle = endrsmtEle.getEndrsr();
			String Role = endrsrEle.getRole().toString();//类别
			String Nm = endrsrEle.getNm();//名称
			String CmonId = endrsrEle.getCmonId();//组织机构代码
//			String ElctrncSgntr = Dom4jUtil.getFirstChildContext(EndrsrEle, "ElctrncSgntr");//电子签名
			Account1 acctEle =  endrsrEle.getAcct();
			String acctId = acctEle.getId();//帐号
			String AcctSvcr = acctEle.getAcctSvcr();//开户行行号	
			
			Organisation8 agcy2Ele = endrsrEle.getAgcy();
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
		
		{//被背书人信息
			
			Organisation5  endrseeEle = endrsmtEle.getEndrsee();
			String Role =endrseeEle.getRole().toString();//类别
			String Nm = endrseeEle.getNm();//名称
			String CmonId = endrseeEle.getCmonId();//组织机构代码
			Account1 acctEle =  endrseeEle.getAcct();
			String acctId = acctEle.getId();//帐号
			String AcctSvcr = acctEle.getAcctSvcr();//开户行行号
			vo.setReceiveName(Nm);
            vo.setReceiveAcctNo(acctId);
            vo.setReceiveBankNo(AcctSvcr);
            vo.setReceiveRoleType(Role);
            vo.setReceiveOrgCode(CmonId);
		}
		vo.setBusiType("0");//此处业务类型表示背书类型
		return vo;
	}
	
	
}
