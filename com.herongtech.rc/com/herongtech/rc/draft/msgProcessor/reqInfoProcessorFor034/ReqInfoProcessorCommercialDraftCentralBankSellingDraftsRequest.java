package com.herongtech.rc.draft.msgProcessor.reqInfoProcessorFor034;

import org.apache.commons.lang.math.NumberUtils;
import org.xmlbean.ecds034.Account1;
import org.xmlbean.ecds034.Account2;
import org.xmlbean.ecds034.CentralBankSellingDrafts1;
import org.xmlbean.ecds034.CommercialDraftCentralBankSellingDraftsRequestV01;
import org.xmlbean.ecds034.Organisation1;
import org.xmlbean.ecds034.Organisation2;
import org.xmlbean.ecds034.Organisation8;
import org.xmlbean.ecds034.RequestInformation1;

import com.herongtech.rc.draft.common.MsgUtil;
import com.herongtech.rc.draft.common.vo.DraftInfoVo;
/**
 * 央行卖出商业汇票申请
 *
 */
public class ReqInfoProcessorCommercialDraftCentralBankSellingDraftsRequest extends ReqInfoProcessor{


	public DraftInfoVo parseReqInfo(RequestInformation1 reqInfo, DraftInfoVo vo) {		
		
		CommercialDraftCentralBankSellingDraftsRequestV01 bankSell=reqInfo.getCommercialDraftCentralBankSellingDraftsRequest();
		String msgId=bankSell.getMsgId().getId();
        vo.setReqMsgId(msgId);
		{//央行卖出商业汇票信息
			CentralBankSellingDrafts1 sellDrfts=bankSell.getCntrlBkSellgDrfts();
			
			String Dt = MsgUtil.convertToString(sellDrfts.getDt());//申请日期
			String IntrstRate = sellDrfts.getIntrstRate().toString();//利率
			String Amt = sellDrfts.getAmt().getStringValue();//实付金额
			String BanEndrsmtMk = sellDrfts.getBanEndrsmtMk().toString();//不得转让标记
			String SttlmMk = sellDrfts.getSttlmMk().toString();//线上清算标记
			String BtchNb = sellDrfts.getBtchNb();//批次号
			String RmrkByPropsr = sellDrfts.getRmrkByPropsr();//备注
			
			vo.setApplyDt(MsgUtil.converISODateTime(Dt));
			vo.setIntrstRate(NumberUtils.createDouble(IntrstRate));
			vo.setAmt(NumberUtils.createDouble(Amt));
			vo.setBanEndrsmtMk(BanEndrsmtMk);
			vo.setSettleMk(SttlmMk);
			vo.setFromRemark(RmrkByPropsr);
			vo.setBtchNb(BtchNb);
		}
			
		{//卖出人信息
			Organisation1  sellrInfo=bankSell.getSellrInf();
			Account1 acct=sellrInfo.getAcct();
			String Role = sellrInfo.getRole().toString();//类别
			String Nm = sellrInfo.getNm();//名称
			String CmonId = sellrInfo.getCmonId();//组织机构代码
			String ElctrncSgntr = sellrInfo.getElctrncSgntr();//电子签名
			String acctId = acct.getId();//帐号
			String AcctSvcr = acct.getAcctSvcr();//开户行行号			
			
			Organisation8 agcyElement = sellrInfo.getAgcy();
			Account2 acctEle2 = null;
			if(agcyElement != null) {
				acctEle2 = agcyElement.getAcct();
			}
			String AcctSvcr2 = null;
			if (acctEle2!=null)
				AcctSvcr2 = acctEle2.getAcctSvcr();//承接行行号
			vo.setFromName(Nm);
			vo.setFromElctrncSgntr(ElctrncSgntr);
			vo.setFromAcctNo(acctId);
			vo.setFromBankNo(AcctSvcr);
			vo.setFromRoleType(Role);
			vo.setFromBankName(Nm);
			vo.setFromOrgCode(CmonId);
			vo.setFromAgcyBankNo(AcctSvcr2);
		}
		{//买入人信息
			Organisation2 buyInfo=bankSell.getBuyrInf();
			Account1 acct=buyInfo.getAcct();
			String Nm = buyInfo.getNm();//名称
			
			Account1  acctEle =  buyInfo.getAcct();
			String acctId = acctEle.getId();//帐号
			String AcctSvcr = acctEle.getAcctSvcr();//开户行行号
			
			vo.setReceiveAcctNo(acctId);
			vo.setReceiveBankNo(AcctSvcr);
			vo.setReceiveBankName(Nm);
			 vo.setReceiveName(Nm);
		}
		
		vo.setBusiType("025");
		return vo;
	}

}
