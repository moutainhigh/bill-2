package com.herongtech.rc.draft.msgProcessor.histInfoProcessorFor034;

import org.xmlbean.ecds034.Account1;
import org.xmlbean.ecds034.Account2;
import org.xmlbean.ecds034.CommercialDraftBusiness1;
import org.xmlbean.ecds034.Organisation10;
import org.xmlbean.ecds034.Organisation8;
import org.xmlbean.ecds034.RepurchasedRediscountWithCentralBank4;

import com.herongtech.rc.domain.bean.RgctBillInfo;
import com.herongtech.rc.draft.common.MsgUtil;
import com.herongtech.rc.draft.common.vo.DraftInfoVo;
/**
 * 历史信息处理器－回购式再贴现赎回
 *
 */
public class HistoryInfoProcessorRpdRdscntWthCntrlBk extends HistoryInfoProcessor{

	@Override
	public DraftInfoVo parseHistoryInfo(CommercialDraftBusiness1 comrclDrftBiz, RgctBillInfo info) {
	    DraftInfoVo vo = new DraftInfoVo();
		
		RepurchasedRediscountWithCentralBank4 rpdRdscntWthCntrlBkEle = comrclDrftBiz.getRpdRdscntWthCntrlBk();

		{//再贴现赎回信息			
			String Dt =MsgUtil.convertToString(rpdRdscntWthCntrlBkEle.getDt());//再贴现赎回签收日期
			String IntrstRate = rpdRdscntWthCntrlBkEle.getIntrstRate().toString();//再贴现赎回利率
			String Amt = rpdRdscntWthCntrlBkEle.getAmt().getStringValue();//再贴现赎回实付金额
			String BanEndrsmtMk = rpdRdscntWthCntrlBkEle.getBanEndrsmtMk().toString();//不得转让标记
			String SttlmMk = rpdRdscntWthCntrlBkEle.getSttlmMk().toString();//线上清算标记
			vo.setSignDt(rpdRdscntWthCntrlBkEle.getDt().getTime());
            vo.setBanEndrsmtMk(BanEndrsmtMk);
		}
		{//再贴现赎回申请人信息（原贴入人)
			
			Organisation10  orgnlRqstngBkOfRdscntWthCntrlBkEle = rpdRdscntWthCntrlBkEle.getOrgnlRqstngBkOfRdscntWthCntrlBk();
			String Role = orgnlRqstngBkOfRdscntWthCntrlBkEle.getRole().toString();//类别
			String Nm = orgnlRqstngBkOfRdscntWthCntrlBkEle.getNm();//名称
			String CmonId = orgnlRqstngBkOfRdscntWthCntrlBkEle.getCmonId();//组织机构代码			
			Account1 acctEle =  orgnlRqstngBkOfRdscntWthCntrlBkEle.getAcct();		
			String acctId = acctEle.getId();//帐号
			String AcctSvcr = acctEle.getAcctSvcr();//开户行行号			
			
			Organisation8 agcy2Ele = orgnlRqstngBkOfRdscntWthCntrlBkEle.getAgcy();
			String AcctSvcr2 = null;
			if (agcy2Ele != null){
				Account2 acctEle2 =agcy2Ele.getAcct();
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
		
		{//再贴现赎回签收人信息（原贴出人）
			Organisation10  rdscntWthCntrlBkSysEle = rpdRdscntWthCntrlBkEle.getRdscntWthCntrlBkSys();
			String Role = rdscntWthCntrlBkSysEle.getRole().toString();//类别
			String Nm =rdscntWthCntrlBkSysEle.getNm();//名称
			String CmonId = rdscntWthCntrlBkSysEle.getCmonId();//组织机构代码			
			Account1 acctEle =  rdscntWthCntrlBkSysEle.getAcct();		
			String acctId = acctEle.getId();//帐号
			String AcctSvcr = acctEle.getAcctSvcr();//开户行行号			
			
			Organisation8 agcy2Ele = rdscntWthCntrlBkSysEle.getAgcy();
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
		vo.setBusiType("6");//此处业务类型表示背书类型
		return vo;
	}
	
	
}
