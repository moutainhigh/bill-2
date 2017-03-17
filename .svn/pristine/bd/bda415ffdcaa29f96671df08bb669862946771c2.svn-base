package com.herongtech.rc.draft.msgProcessor.histInfoProcessorFor034;

import org.xmlbean.ecds034.Account1;
import org.xmlbean.ecds034.Account2;
import org.xmlbean.ecds034.CommercialDraft4;
import org.xmlbean.ecds034.CommercialDraftBusiness1;
import org.xmlbean.ecds034.Organisation3;
import org.xmlbean.ecds034.Organisation5;
import org.xmlbean.ecds034.Organisation8;

import com.herongtech.rc.domain.bean.RgctBillInfo;
import com.herongtech.rc.draft.common.MsgUtil;
import com.herongtech.rc.draft.common.vo.DraftInfoVo;
/**
 * 历史信息处理器－提示收票
 *
 */
public class HistoryInfoProcessorIssnc extends HistoryInfoProcessor{
	
	@Override
	public DraftInfoVo parseHistoryInfo(CommercialDraftBusiness1 comrclDrftBiz, RgctBillInfo info) {
	    DraftInfoVo vo = new DraftInfoVo();
			
		CommercialDraft4 issncEle = comrclDrftBiz.getIssnc();
		{//信息
			String Dt = MsgUtil.convertToString(issncEle.getDt());//提示收票签收日期	
			String PrxySgntr = issncEle.getPrxySgntr().toString();//代理回复标识
			String RmrkByPropsr = issncEle.getRmrkByPropsr();//出票人备注
			String RmrkBySgnr = issncEle.getRmrkBySgnr();//回复人备注	
		}
		
		{//收款人信息			
			Organisation5  pyeeEle =issncEle.getPyee();	
			String Role = pyeeEle.getRole().toString();//类别
			String Nm = pyeeEle.getNm();//名称
			String CmonId = pyeeEle.getCmonId();//组织机构代码
			Account1 acctEle = pyeeEle.getAcct();
			String acctId =acctEle.getId();//帐号
			String AcctSvcr = acctEle.getAcctSvcr();//开户行行号
			
			vo.setReceiveName(Nm);
            vo.setReceiveAcctNo(acctId);
            vo.setReceiveBankNo(AcctSvcr);
            vo.setReceiveRoleType(Role);
            vo.setReceiveOrgCode(CmonId);
					
		}
		
		{//出票人信息
			
			Organisation3  drwrEle = issncEle.getDrwr();
			String Role = drwrEle.getRole().toString();
			String CmonId = drwrEle.getCmonId();
			Account1  acctEle =  drwrEle.getAcct();
			String acctId =  acctEle.getId();//帐号
			String AcctSvcr =  acctEle.getAcctSvcr();//开户行行号			
			Organisation8 agcyElement = drwrEle.getAgcy();
			String AcctSvcr2 = null;
			if(agcyElement != null){
				Account2 acctEle2 = agcyElement.getAcct();
				if (acctEle2!=null)
					AcctSvcr2 = acctEle2.getAcctSvcr();//承接行行号
			}
            vo.setFromAcctNo(acctId);
            vo.setFromBankNo(AcctSvcr);
            vo.setFromRoleType(Role);
            vo.setFromOrgCode(CmonId);
            vo.setFromAgcyBankNo(AcctSvcr2);
			
		}
//		vo.setBusiType("");//此处业务类型表示背书类型
		return vo;
	}
	
	
}
