package com.herongtech.rc.draft.msgProcessor.reqInfoProcessorFor034;

import org.apache.commons.lang.math.NumberUtils;
import org.xmlbean.ecds034.Account1;
import org.xmlbean.ecds034.Account2;
import org.xmlbean.ecds034.CommercialDraftDiscountRequest1;
import org.xmlbean.ecds034.Discount1;
import org.xmlbean.ecds034.Organisation1;
import org.xmlbean.ecds034.Organisation2;
import org.xmlbean.ecds034.Organisation8;
import org.xmlbean.ecds034.RequestInformation1;

import com.herongtech.console.core.constant.DraftConstants;
import com.herongtech.rc.draft.common.MsgUtil;
import com.herongtech.rc.draft.common.vo.DraftInfoVo;
/**
 * 贴现申请
 *
 */ 
public class ReqInfoProcessorCommercialDraftDiscountRequest extends ReqInfoProcessor{
	

	@Override
	public DraftInfoVo parseReqInfo(RequestInformation1 reqInfo, DraftInfoVo vo) {     
		CommercialDraftDiscountRequest1 discount=reqInfo.getCommercialDraftDiscountRequest();
		String msgId = discount.getMsgId().getId();
		vo.setReqMsgId(msgId);
		 //贴现信息
			Discount1  dscnt=discount.getDscnt();
			String RpdMk = dscnt.getRpdMk().toString();//贴现种类RM00买断式	RM01回购式
			String Dt = MsgUtil.convertToString(dscnt.getDt());//贴现申请日期
			String IntrstRate = dscnt.getIntrstRate().toString();//贴现利率
			String Amt = dscnt.getAmt().getStringValue();//贴现实付金额
			String BanEndrsmtMk = dscnt.getBanEndrsmtMk().toString();//不得转让标记
			String SttlmMk = dscnt.getSttlmMk().toString();//线上清算标记
			vo.setRpdMk(RpdMk);
            vo.setApplyDt(MsgUtil.converISODateTime(Dt));
            vo.setIntrstRate(NumberUtils.createDouble(IntrstRate));
            vo.setAmt(NumberUtils.createDouble(Amt));
            vo.setBanEndrsmtMk(BanEndrsmtMk);
            vo.setSettleMk(SttlmMk);
            
			if(DraftConstants.REPURCHASED_MARK_CODE_RM01.equals(RpdMk)){
				String RpdOpenDt = MsgUtil.convertToString(dscnt.getRpdOpenDt());//贴现赎回开放日
				String RpdDueDt = MsgUtil.convertToString(dscnt.getRpdDueDt());//贴现赎回截止日
				String RpdIntrstRate = dscnt.getRpdIntrstRate().toString();//贴现赎回利率
				String RpdAmt = dscnt.getRpdAmt().getStringValue();//贴现赎回金额
				vo.setRpdOpenDt(MsgUtil.converISODateTime(RpdOpenDt));
				vo.setRpdDueDt(MsgUtil.converISODateTime(RpdDueDt));
				vo.setRpdIntrstRate(NumberUtils.createDouble(RpdIntrstRate));
				vo.setRpdAmt(NumberUtils.createDouble(RpdAmt));
			}
			String TxlCtrctNb = dscnt.getTxlCtrctNb();//交易合同编号
			String InvcNb = dscnt.getInvcNb();//发票号码	
			String BtchNb = dscnt.getBtchNb();//批次号
			String RmrkByPropsr=dscnt.getRmrkByPropsr();
            vo.setFromRemark(RmrkByPropsr);
            vo.setBtchNb(BtchNb);
            vo.setInvcNb(InvcNb);
            vo.setTxlCtrctNb(TxlCtrctNb);
		
				
		//入账信息
			Account1 aOAccnInf=discount.getAOAccnInf();
			vo.setInAcct(aOAccnInf.getId());;//帐号
			vo.setInBankNo(aOAccnInf.getAcctSvcr());//开户行行号
			
		//贴入人信息
			Organisation2 dscntBk=discount.getDscntBk();
			String Nm = dscntBk.getNm();//名称
			Account1 acct=dscntBk.getAcct();
			String acctId = acct.getId();//帐号
			String AcctSvcr = acct.getAcctSvcr();//开户行行号
	
			vo.setReceiveAcctNo(acctId);
            vo.setReceiveBankNo(AcctSvcr);
            vo.setReceiveBankName(Nm);
            vo.setReceiveName(Nm);
			
						
		
		//贴现申请人
			Organisation1 dscntPropsr=discount.getDscntPropsr();
			String Role = dscntPropsr.getRole().toString();//类别
			String dscntPropsrNm = dscntPropsr.getNm();//名称
			String dscntPropsrCmonId =dscntPropsr.getCmonId();//组织机构代码
			String ElctrncSgntr = dscntPropsr.getElctrncSgntr();//电子签名
			Account1 dscntPropsrAcct=dscntPropsr.getAcct();
			String dscntPropsrAcctId = dscntPropsrAcct.getId();//帐号
			String dscntPropsrAcctSvcr = dscntPropsrAcct.getAcctSvcr();//开户行行号	
			Organisation8 agcyElement=dscntPropsr.getAgcy();
			Account2 acctEle2 = null;
			if(agcyElement != null) {
				
			acctEle2 = agcyElement.getAcct();
			}
			String dscntPropsrAcctSvcr2 = null;
			if (acctEle2!=null)
				dscntPropsrAcctSvcr2 = acctEle2.getAcctSvcr();//承接行行号
			vo.setFromName(dscntPropsrNm);
            vo.setFromElctrncSgntr(ElctrncSgntr);
            vo.setFromAcctNo(dscntPropsrAcctId);
            vo.setFromBankNo(dscntPropsrAcctSvcr);
            vo.setFromRoleType(Role);
            vo.setFromOrgCode(dscntPropsrCmonId);
            vo.setFromAgcyBankNo(dscntPropsrAcctSvcr2);
            vo.setBusiType("011");
		return vo;
	}
	
}
