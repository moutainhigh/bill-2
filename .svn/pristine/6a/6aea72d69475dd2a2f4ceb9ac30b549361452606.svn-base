package com.herongtech.rc.draft.msgProcessor.histInfoProcessorFor034;

import org.xmlbean.ecds034.Account1;
import org.xmlbean.ecds034.Account2;
import org.xmlbean.ecds034.CommercialDraftBusiness1;
import org.xmlbean.ecds034.Organisation10;
import org.xmlbean.ecds034.Organisation5;
import org.xmlbean.ecds034.Organisation8;
import org.xmlbean.ecds034.RediscountWithCentralBank4;

import com.herongtech.rc.domain.bean.RgctBillInfo;
import com.herongtech.rc.draft.common.MsgUtil;
import com.herongtech.rc.draft.common.vo.DraftInfoVo;
/**
 * 历史信息处理器－再贴现
 *
 */
public class HistoryInfoProcessorRdscntWthCntrlBk extends HistoryInfoProcessor{

	@Override
	public DraftInfoVo parseHistoryInfo(CommercialDraftBusiness1 comrclDrftBiz, RgctBillInfo info) {
	    DraftInfoVo vo = new DraftInfoVo();
		RediscountWithCentralBank4 rdscntWthCntrlBkEle = comrclDrftBiz.getRdscntWthCntrlBk();
		
		{//再贴现信息
			String RpdMk = rdscntWthCntrlBkEle.getRpdMk().toString();//再贴现种类RM00买断式	RM01回购式
			String Dt = MsgUtil.convertToString(rdscntWthCntrlBkEle.getDt());//再贴现签收日期
			String IntrstRate = rdscntWthCntrlBkEle.getIntrstRate().toString();//再贴现利率
			String Amt = rdscntWthCntrlBkEle.getAmt().getStringValue();//再贴现实付金额
			String BanEndrsmtMk =rdscntWthCntrlBkEle.getBanEndrsmtMk().toString();//不得转让标记
			String SttlmMk = rdscntWthCntrlBkEle.getSttlmMk().toString();//线上清算标记
			
			if ("RM01".equals(RpdMk)){
				String RpdOpenDt = MsgUtil.convertToString(rdscntWthCntrlBkEle.getRpdOpenDt());//贴现赎回开放日
				String RpdDueDt = MsgUtil.convertToString(rdscntWthCntrlBkEle.getRpdDueDt());//贴现赎回截止日
				String RpdIntrstRate = rdscntWthCntrlBkEle.getRpdIntrstRate() == null ?  "0.00" : rdscntWthCntrlBkEle.getRpdIntrstRate().toString();//贴现赎回利率
				String RpdAmt = rdscntWthCntrlBkEle.getRpdAmt()== null ?  "0.00" : rdscntWthCntrlBkEle.getRpdAmt().getStringValue();//贴现赎回金额
				vo.setBusiType("5_1");//此处业务类型表示背书类型
			}else{
			    vo.setBusiType("5");//此处业务类型表示背书类型
			}
			
			vo.setSignDt(rdscntWthCntrlBkEle.getDt().getTime());
            vo.setBanEndrsmtMk(BanEndrsmtMk);
		}
		
		{//贴出人信息
			
			Organisation10  requestingBankOfRediscountWithCentralBankEle = rdscntWthCntrlBkEle.getRqstngBkOfRpdRdscntWthCntrlBk();
			String Role = requestingBankOfRediscountWithCentralBankEle.getRole().toString();//类别
			String Nm = requestingBankOfRediscountWithCentralBankEle.getNm();//名称
			String CmonId = requestingBankOfRediscountWithCentralBankEle.getCmonId();//组织机构代码
			Account1 acctEle =  requestingBankOfRediscountWithCentralBankEle.getAcct();
			String acctId = acctEle.getId();//帐号
			String AcctSvcr = acctEle.getAcctSvcr();//开户行行号			
			Organisation8 agcy2Ele = requestingBankOfRediscountWithCentralBankEle.getAgcy();
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
		{//贴入人信息
			
			Organisation5  rediscountWithCentralBankSystemEle = rdscntWthCntrlBkEle.getRdscntWthCntrlBkSys();
			String Role =rediscountWithCentralBankSystemEle.getRole().toString();//类别
			String Nm = rediscountWithCentralBankSystemEle.getNm();//名称
			String CmonId = rediscountWithCentralBankSystemEle.getCmonId();//组织机构代码
			Account1 acctEle =  rediscountWithCentralBankSystemEle.getAcct();
			String acctId = acctEle.getId();//帐号
			String AcctSvcr = acctEle.getAcctSvcr();//开户行行号
			vo.setReceiveName(Nm);
            vo.setReceiveAcctNo(acctId);
            vo.setReceiveBankNo(AcctSvcr);
            vo.setReceiveRoleType(Role);
            vo.setReceiveOrgCode(CmonId);
		}
		
		return vo;
	}
	
	
}
