package com.herongtech.rc.draft.msgProcessor.histInfoProcessorFor034;

import org.xmlbean.ecds034.Account1;
import org.xmlbean.ecds034.Account2;
import org.xmlbean.ecds034.Collateralization4;
import org.xmlbean.ecds034.CommercialDraftBusiness1;
import org.xmlbean.ecds034.Organisation10;
import org.xmlbean.ecds034.Organisation5;
import org.xmlbean.ecds034.Organisation8;

import com.herongtech.rc.domain.bean.RgctBillInfo;
import com.herongtech.rc.draft.common.vo.DraftInfoVo;
/**
 * 历史信息处理器－质押
 *
 */
public class HistoryInfoProcessorCollztn extends HistoryInfoProcessor{

	@Override
	public DraftInfoVo parseHistoryInfo(CommercialDraftBusiness1 comrclDrftBiz, RgctBillInfo info) {
	    DraftInfoVo vo=new DraftInfoVo();
		Collateralization4 collztnEle =comrclDrftBiz.getCollztn();
		{//质押信息			
			vo.setSignDt(collztnEle.getDt().getTime());
		}
		
		{//出质人信息
			Organisation10  collztnPropsrEle = collztnEle.getCollztnProPsr();
			String Role = collztnPropsrEle.getRole().toString();//类别
			String Nm =collztnPropsrEle.getNm();//名称
			String CmonCd = collztnPropsrEle.getCmonId();//组织机构代码
			Account1 acctEle =  collztnPropsrEle.getAcct();
			String acctId = acctEle.getId();//帐号
			String AcctSvcr =acctEle.getAcctSvcr();//开户行行号
			
			Organisation8  Agcy2Ele = collztnPropsrEle.getAgcy();
			String AcctSvcr2 = null;
			if (Agcy2Ele != null){
				Account2 acctEle2 = Agcy2Ele.getAcct();
				if (acctEle2!=null)
					AcctSvcr2 = acctEle2.getAcctSvcr();//承接行行号				
			}
			vo.setFromName(Nm);
            vo.setFromAcctNo(acctId);
            vo.setFromBankNo(AcctSvcr);
            vo.setFromRoleType(Role);
            vo.setFromOrgCode(CmonCd);
            vo.setFromAgcyBankNo(AcctSvcr2);
		}
		{//质权人信息
			Organisation5 collztnBkEle = collztnEle.getCollztnBk();
			String Role = collztnBkEle.getRole().toString();//类别
			String Nm = collztnBkEle.getNm();//名称
			String CmonCd = collztnBkEle.getCmonId();//组织机构代码
			Account1 acctEle =  collztnBkEle.getAcct();;
			String acctId = acctEle.getId();//帐号
			String AcctSvcr = acctEle.getAcctSvcr();//开户行行号
			
			vo.setReceiveName(Nm);
            vo.setReceiveAcctNo(acctId);
            vo.setReceiveBankNo(AcctSvcr);
            vo.setReceiveRoleType(Role);
            vo.setReceiveOrgCode(CmonCd);
		}
		vo.setBusiType("9");//此处业务类型表示背书类型
		return vo;
	}
	
	
}
