package com.herongtech.rc.draft.msgProcessor.histInfoProcessorFor034;

import org.xmlbean.ecds034.Account1;
import org.xmlbean.ecds034.Account2;
import org.xmlbean.ecds034.CommercialDraftBusiness1;
import org.xmlbean.ecds034.Organisation10;
import org.xmlbean.ecds034.Organisation8;
import org.xmlbean.ecds034.RepurchasedRediscountWithCommercialBank4;

import com.herongtech.rc.domain.bean.RgctBillInfo;
import com.herongtech.rc.draft.common.MsgUtil;
import com.herongtech.rc.draft.common.vo.DraftInfoVo;
/**
 * 历史信息处理器－回购式转贴现赎回
 *
 */
public class HistoryInfoProcessorRpdRdscntWthComrclBk extends HistoryInfoProcessor{

	@Override
	public DraftInfoVo parseHistoryInfo(CommercialDraftBusiness1 comrclDrftBiz, RgctBillInfo info) {
	    DraftInfoVo vo = new DraftInfoVo();
		
		RepurchasedRediscountWithCommercialBank4 rpdRdscntWthComrclBkEle = comrclDrftBiz.getRpdRdscntWthComrclBk();
		
		{//转贴现赎回信息		
//			String Dt = MsgUtil.convertToString(rpdRdscntWthComrclBkEle.getDt());//转贴现赎回签收日期
			String IntrstRate = rpdRdscntWthComrclBkEle.getIntrstRate().toString();//转贴现赎回利率
			String Amt = rpdRdscntWthComrclBkEle.getAmt().getStringValue();//转贴现赎回实付金额
			String BanEndrsmtMk = rpdRdscntWthComrclBkEle.getBanEndrsmtMk().toString();//不得转让标记
			String SttlmMk = rpdRdscntWthComrclBkEle.getSttlmMk().toString();//线上清算标记
			vo.setSignDt(rpdRdscntWthComrclBkEle.getDt().getTime());
            vo.setBanEndrsmtMk(BanEndrsmtMk);
		}
		{//转贴现赎回申请人信息（原贴入人)
			
			Organisation10  orgnlRqstngBkOfRdscntWthComrclBkEle = rpdRdscntWthComrclBkEle.getOrgnlRqstngBkOfRdscntWthComrclBk();
			String Role = orgnlRqstngBkOfRdscntWthComrclBkEle.getRole().toString();//类别
			String Nm = orgnlRqstngBkOfRdscntWthComrclBkEle.getNm();//名称
			String CmonId = orgnlRqstngBkOfRdscntWthComrclBkEle.getCmonId();//组织机构代码			
			Account1 acctEle =  orgnlRqstngBkOfRdscntWthComrclBkEle.getAcct();		
			String acctId = acctEle.getId();//帐号
			String AcctSvcr = acctEle.getAcctSvcr();//开户行行号			
			
			Organisation8 agcy2Ele = orgnlRqstngBkOfRdscntWthComrclBkEle.getAgcy();
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
		
		{//转贴现赎回签收人信息（原贴出人）
			
			Organisation10  orgnlRcvgBkOfRdscntWthComrclBkEle =rpdRdscntWthComrclBkEle.getOrgnlRcvgBkOfRdscntWthComrclBk();
			String Role = orgnlRcvgBkOfRdscntWthComrclBkEle.getRole().toString();//类别
			String Nm = orgnlRcvgBkOfRdscntWthComrclBkEle.getNm();//名称
			String CmonId =orgnlRcvgBkOfRdscntWthComrclBkEle.getCmonId();//组织机构代码			
			Account1 acctEle =  orgnlRcvgBkOfRdscntWthComrclBkEle.getAcct();		
			String acctId =acctEle.getId();//帐号
			String AcctSvcr =acctEle.getAcctSvcr();//开户行行号			
			
			Organisation8 agcy2Ele = orgnlRcvgBkOfRdscntWthComrclBkEle.getAgcy();
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
		vo.setBusiType("4");//此处业务类型表示背书类型
		return vo;
	}
	
	
}
