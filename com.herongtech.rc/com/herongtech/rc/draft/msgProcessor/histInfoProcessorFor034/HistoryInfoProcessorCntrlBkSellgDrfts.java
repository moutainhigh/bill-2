package com.herongtech.rc.draft.msgProcessor.histInfoProcessorFor034;

import org.xmlbean.ecds034.Account1;
import org.xmlbean.ecds034.Account2;
import org.xmlbean.ecds034.CentralBankSellingDrafts4;
import org.xmlbean.ecds034.CommercialDraftBusiness1;
import org.xmlbean.ecds034.Organisation10;
import org.xmlbean.ecds034.Organisation5;
import org.xmlbean.ecds034.Organisation8;

import com.herongtech.rc.domain.bean.RgctBillInfo;
import com.herongtech.rc.draft.common.MsgUtil;
import com.herongtech.rc.draft.common.vo.DraftInfoVo;

/**
 * 历史信息处理器－央行卖出商业汇票
 *
 */
public class HistoryInfoProcessorCntrlBkSellgDrfts extends HistoryInfoProcessor{

	@Override
	public DraftInfoVo parseHistoryInfo(CommercialDraftBusiness1 comrclDrftBiz,RgctBillInfo info) {
	    DraftInfoVo vo=new DraftInfoVo();
	    CentralBankSellingDrafts4 cntrlBkSellgDrftsEle = comrclDrftBiz.getCntrlBkSellgDrfts();
		{//央行卖出商业汇票信息
//			String Dt = MsgUtil.convertToString(cntrlBkSellgDrftsEle.getDt());//央行卖出商业汇票签收日期
			String IntrstRate = cntrlBkSellgDrftsEle.getIntrstRate().toString();//利率
			String Amt = cntrlBkSellgDrftsEle.getAmt().getStringValue();//实付金额
			String BanEndrsmtMk = cntrlBkSellgDrftsEle.getBanEndrsmtMk().toString();//不得转让标记
			String SttlmMk = cntrlBkSellgDrftsEle.getSttlmMk().toString();//线上清算标记SM00：线上清算SM01：线下清算
			String PrxySgntr = cntrlBkSellgDrftsEle.getPrxySgntr().toString();//代理回复标识
			String RmrkByPropsr = cntrlBkSellgDrftsEle.getRmrkByPropsr();//卖出人备注
			String RmrkBySgnr = cntrlBkSellgDrftsEle.getRmrkBySgnr();//回复人备注

			vo.setSignDt(cntrlBkSellgDrftsEle.getDt().getTime());
			vo.setBanEndrsmtMk(BanEndrsmtMk);
		
		}		
		{//卖出人信息
			
			Organisation10  sellrInfEle = cntrlBkSellgDrftsEle.getSellrInf();
			String Role = sellrInfEle.getRole().toString();//类别
			String Nm = sellrInfEle.getNm();//名称
			String CmonId = sellrInfEle.getCmonId();//组织机构代码
			Account1 acctEle =  sellrInfEle.getAcct();
			String acctId = acctEle.getId();//帐号
			String AcctSvcr = acctEle.getAcctSvcr();//开户行行号	
			
			Organisation8  Agcy2Ele = sellrInfEle.getAgcy();
			String AcctSvcr2 = null;
			if (Agcy2Ele!=null){
				Account2 acctEle2 = Agcy2Ele.getAcct();
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
		{//买入人信息
			
			Organisation5  buyrInfEle = cntrlBkSellgDrftsEle.getBuyrInf();
			String Role = buyrInfEle.getRole().toString();//类别
			String Nm = buyrInfEle.getNm();;//名称
			String CmonId =buyrInfEle.getCmonId();;//组织机构代码
			Account1 acctEle =  buyrInfEle.getAcct();
			String acctId = acctEle.getId();//帐号
			String AcctSvcr = acctEle.getAcctSvcr();//开户行行号
			vo.setReceiveName(Nm);
            vo.setReceiveAcctNo(acctId);
            vo.setReceiveBankNo(AcctSvcr);
            vo.setReceiveRoleType(Role);
            vo.setReceiveOrgCode(CmonId);
		}
		
		vo.setBusiType("7");//此处业务类型表示背书类型
		return vo;
	}
	
	
}
