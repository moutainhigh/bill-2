
package com.herongtech.rc.draft.msgProcessor.histInfoProcessorFor034;

import org.xmlbean.ecds034.Account1;
import org.xmlbean.ecds034.Account2;
import org.xmlbean.ecds034.CommercialDraftBusiness1;
import org.xmlbean.ecds034.Organisation10;
import org.xmlbean.ecds034.Organisation8;
import org.xmlbean.ecds034.OverduePresentation3;

import com.herongtech.rc.domain.bean.RgctBillInfo;
import com.herongtech.rc.draft.common.MsgUtil;
import com.herongtech.rc.draft.common.vo.DraftInfoVo;
/**
 * 历史信息处理器－逾期提示付款
 *
 */
public class HistoryInfoProcessorOvrduePrsnttn extends HistoryInfoProcessor{

	@Override
	public DraftInfoVo parseHistoryInfo(CommercialDraftBusiness1 comrclDrftBiz, RgctBillInfo info) {
	    DraftInfoVo vo = new DraftInfoVo();
		OverduePresentation3 ovrduePrsnttnEle = comrclDrftBiz.getOvrduePrsnttn();				
		{//逾期提示付款信息
//			String ApplDt = MsgUtil.convertToString(ovrduePrsnttnEle.getApplDt());//逾期提示付款申请日期
//			String Dt = MsgUtil.convertToString(ovrduePrsnttnEle.getDt());//逾期提示付款签收日期
			String Amt = ovrduePrsnttnEle.getAmt().getStringValue();//逾期提示付款金额
			String Rsn = ovrduePrsnttnEle.getRsn().toString();//逾期原因
			String SttlmMk = ovrduePrsnttnEle.getSttlmMk().toString();//线上清算标记
			String PrxyPropstn=ovrduePrsnttnEle.getPrxyPropstn().toString(); //代理申请标识
//			String DptyMk = Dom4jUtil.getFirstChildContext(OvrduePrsnttnEle, "DptyMk");//是否代客户回复-DC00代理客户回复,DC01非代理客户回复
			String DshnrCd = ovrduePrsnttnEle.getDshnrCd().toString();//拒付代码
			String DshnrRsn =ovrduePrsnttnEle.getDshnrRsn();//拒付备注
			
			vo.setApplyDt(ovrduePrsnttnEle.getApplDt().getTime());
			vo.setSignDt(ovrduePrsnttnEle.getDt().getTime());
			
			
		}
		{//逾期提示付款人信息
			
			Organisation10  drftHldrEle = ovrduePrsnttnEle.getDrftHldr();
			String Role = drftHldrEle.getRole().toString();//类别
			String Nm = drftHldrEle.getNm();//名称
			String CmonId = drftHldrEle.getCmonId();//组织机构代码
			Account1 acctEle = drftHldrEle.getAcct();
			String acctId =acctEle.getId();//帐号
			String AcctSvcr = acctEle.getAcctSvcr();//开户行行号			
			Organisation8 agcy2Ele = drftHldrEle.getAgcy();
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
		{//付款人信息
			
			Organisation10  payBkEle =ovrduePrsnttnEle.getPayBk();
			String Role = payBkEle.getRole().toString();//类别
			String Nm = payBkEle.getNm();//名称
			String CmonId = payBkEle.getCmonId();//组织机构代码
			Account1 acctEle =  payBkEle.getAcct();
			String acctId = acctEle.getId();//帐号
			String AcctSvcr = acctEle.getAcctSvcr();//开户行行号			
			Organisation8 agcy2Ele = payBkEle.getAgcy();
			if (agcy2Ele != null){
				Account2 acctEle2 = agcy2Ele.getAcct();
				String AcctSvcr2 = null;
				if (acctEle2!=null)
					AcctSvcr2 =acctEle2.getAcctSvcr();//承接行行号
				
			}	
			
			vo.setReceiveName(Nm);
            vo.setReceiveAcctNo(acctId);
            vo.setReceiveBankNo(AcctSvcr);
            vo.setReceiveRoleType(Role);
            vo.setReceiveOrgCode(CmonId);
		}	
		vo.setBusiType("10");//此处业务类型表示背书类型
		return vo;
	}
	
	
}
