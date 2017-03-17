package com.herongtech.rc.draft.msgProcessor.histInfoProcessorFor034;

import org.xmlbean.ecds034.Account1;
import org.xmlbean.ecds034.Account2;
import org.xmlbean.ecds034.CommercialDraftBusiness1;
import org.xmlbean.ecds034.Discount4;
import org.xmlbean.ecds034.Organisation10;
import org.xmlbean.ecds034.Organisation5;
import org.xmlbean.ecds034.Organisation8;

import com.herongtech.rc.domain.bean.RgctBillInfo;
import com.herongtech.rc.draft.common.MsgUtil;
import com.herongtech.rc.draft.common.vo.DraftInfoVo;
/**
 * 历史信息处理器－贴现
 *
 */
public class HistoryInfoProcessorDscnt extends HistoryInfoProcessor{

	@Override
	public DraftInfoVo parseHistoryInfo(CommercialDraftBusiness1 comrclDrftBiz, RgctBillInfo info) {
	    DraftInfoVo vo = new DraftInfoVo();
		Discount4 dscntEle = comrclDrftBiz.getDscnt();
		{//贴现信息			
			String RpdMk = dscntEle.getRpdMk().toString();//贴现种类RM00买断式	RM01回购式
			String Dt = MsgUtil.convertToString(dscntEle.getDt());//贴现签收日期
			String IntrstRate = dscntEle.getIntrstRate().toString();//贴现利率
			String Amt =dscntEle.getAmt().getStringValue();//贴现实付金额
			String BanEndrsmtMk = dscntEle.getBanEndrsmtMk().toString();//不得转让标记
			String SttlmMk = dscntEle.getSttlmMk().toString();//线上清算标记
			String TxlCtrctNb =dscntEle.getTxlCtrctNb() ;//交易合同编号
			String InvcNb = dscntEle.getInvcNb();//发票号码
			String DscntAgrmtNb = dscntEle.getDscntAgrmtNb();//贴现协议编号
			if ("RM01".equals(RpdMk)){
				String RpdOpenDt = MsgUtil.convertToString(dscntEle.getRpdOpenDt());//贴现赎回开放日
				String RpdDueDt = MsgUtil.convertToString(dscntEle.getRpdDueDt());//贴现赎回截止日
				String RpdIntrstRate =dscntEle.getRpdIntrstRate() == null ? "0.00" : dscntEle.getRpdIntrstRate().toString();//贴现赎回利率
				String RpdAmt = dscntEle.getRpdAmt() == null ? "0.00" :  dscntEle.getRpdAmt().getStringValue();//贴现赎回金额
				vo.setBusiType("1_1");//此处业务类型表示背书类型
			}else{
			    vo.setBusiType("1");//此处业务类型表示背书类型
			}
			
			vo.setSignDt(dscntEle.getDt().getTime());
            vo.setBanEndrsmtMk(BanEndrsmtMk);
			
		}
		
		{//入账信息
			Account1  aOAccnInfEle =dscntEle.getAOAccnInf();
			String acctId = aOAccnInfEle.getId();//帐号
			String AcctSvcr =aOAccnInfEle.getAcctSvcr();//开户行行号	
		}
		
		{//贴现申请人
			
			Organisation10  dscntPropsrEle = dscntEle.getDscntPropsr();
			String Role = dscntPropsrEle.getRole().toString();//类别
			String Nm = dscntPropsrEle.getNm();//名称
			String CmonId =dscntPropsrEle.getCmonId();//组织机构代码
			Account1 acctEle =  dscntPropsrEle.getAcct();
			String acctId = acctEle.getId();//帐号
			String AcctSvcr = acctEle.getAcctSvcr();//开户行行号
			Organisation8 agcyElement = dscntPropsrEle.getAgcy();
			String AcctSvcr2 = null;
			if(agcyElement != null) {
				Account2 acctEle2 = agcyElement.getAcct();
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
			Organisation5  dscntBkEle = dscntEle.getDscntBk();
			String Role = dscntBkEle.getRole().toString();//类别
			String Nm = dscntBkEle.getNm();;//名称
			String CmonId =dscntBkEle.getCmonId();//组织机构代码
			Account1 acctEle =  dscntBkEle.getAcct();
			String acctId = acctEle.getId();//帐号
			String AcctSvcr = acctEle.getAcctSvcr();//行号
			vo.setReceiveName(Nm);
            vo.setReceiveAcctNo(acctId);
            vo.setReceiveBankNo(AcctSvcr);
            vo.setReceiveRoleType(Role);
            vo.setReceiveOrgCode(CmonId);
		}
		
		return vo;
	}
	
	
}
