package com.herongtech.rc.draft.msgProcessor.reqInfoProcessorFor034;

import org.apache.commons.lang.math.NumberUtils;
import org.xmlbean.ecds034.Account1;
import org.xmlbean.ecds034.Account2;
import org.xmlbean.ecds034.CommercialDraftRediscountWithCentralBankRequest1;
import org.xmlbean.ecds034.Organisation1;
import org.xmlbean.ecds034.Organisation2;
import org.xmlbean.ecds034.Organisation8;
import org.xmlbean.ecds034.RediscountWithCentralBank1;
import org.xmlbean.ecds034.RequestInformation1;

import com.herongtech.console.core.constant.DraftConstants;
import com.herongtech.rc.draft.common.MsgUtil;
import com.herongtech.rc.draft.common.vo.DraftInfoVo;
/**
 * 再贴现申请
 * 该类不会用到
 */
@Deprecated
public class ReqInfoProcessorCommercialDraftRediscountWithCentralBankRequest extends ReqInfoProcessor{

	@Override
	public DraftInfoVo parseReqInfo(RequestInformation1 reqInfo,DraftInfoVo vo) {      	
		CommercialDraftRediscountWithCentralBankRequest1 reqEle = reqInfo.getCommercialDraftRediscountWithCentralBankRequest();
		String msgId=reqEle.getMsgId().getId();
        vo.setReqMsgId(msgId);  
		/*{//票据信息
			CommercialDraft5 comrclDrftEle = reqEle.getComrclDrft();
			String IdNb = comrclDrftEle.getIdNb();// 电子票据号码
			String IsseAmt = comrclDrftEle.getIsseAmt().getStringValue();// 票据金额
		}*/
				
		{//再贴现信息
			RediscountWithCentralBank1  rdscntWthCntrlBkEle = reqEle.getRdscntWthCntrlBk();
			String RpdMk = rdscntWthCntrlBkEle.getRpdMk().toString();//再贴现种类RM00买断式	RM01回购式
			String Dt = MsgUtil.convertToString(rdscntWthCntrlBkEle.getDt());//再贴现申请日期
			String IntrstRate = rdscntWthCntrlBkEle.getIntrstRate().toString();//再贴现利率
			String Amt = rdscntWthCntrlBkEle.getAmt().getStringValue();//再贴现实付金额
			String BanEndrsmtMk = rdscntWthCntrlBkEle.getBanEndrsmtMk().toString();//不得转让标记
			String SttlmMk = rdscntWthCntrlBkEle.getSttlmMk().toString();//线上清算标记
			String BtchNb = rdscntWthCntrlBkEle.getBtchNb();//批次号
			
			vo.setRpdMk(RpdMk);
            vo.setApplyDt(MsgUtil.converISODateTime(Dt));
            vo.setIntrstRate(NumberUtils.createDouble(IntrstRate));
            vo.setAmt(NumberUtils.createDouble(Amt));
            vo.setBanEndrsmtMk(BanEndrsmtMk);
            vo.setSettleMk(SttlmMk);
            vo.setBtchNb(BtchNb);
			if(DraftConstants.REPURCHASED_MARK_CODE_RM01.equals(RpdMk)){
				String RpdOpenDt = MsgUtil.convertToString(rdscntWthCntrlBkEle.getRpdOpenDt());//再贴现赎回开放日
				String RpdDueDt = MsgUtil.convertToString(rdscntWthCntrlBkEle.getRpdDueDt());//再贴现赎回截止日
				String RpdIntrstRate = rdscntWthCntrlBkEle.getRpdIntrstRate().toString();//再贴现赎回利率
				String RpdAmt = rdscntWthCntrlBkEle.getRpdAmt().getStringValue();//再贴现赎回金额
				vo.setRpdOpenDt(MsgUtil.converISODateTime(RpdOpenDt));
                vo.setRpdDueDt(MsgUtil.converISODateTime(RpdDueDt));
                vo.setRpdIntrstRate(NumberUtils.createDouble(RpdIntrstRate));
                vo.setRpdAmt(NumberUtils.createDouble(RpdAmt));
			}
			
		}
			
		{//贴出人信息
			
			Organisation1  rqstngBkOfRdscntWthCntrlBkEle = reqEle.getRqstngBkOfRdscntWthCntrlBk();
			String Role = rqstngBkOfRdscntWthCntrlBkEle.getRole().toString();//类别
			String Nm = rqstngBkOfRdscntWthCntrlBkEle.getNm();//名称
			String CmonId = rqstngBkOfRdscntWthCntrlBkEle.getCmonId();//组织机构代码
			String ElctrncSgntr =rqstngBkOfRdscntWthCntrlBkEle.getElctrncSgntr();//电子签名
			Account1  acctEle =  rqstngBkOfRdscntWthCntrlBkEle.getAcct();
			String acctId = acctEle.getId();//帐号
			String AcctSvcr = acctEle.getAcctSvcr();//开户行行号			
			//Element acctEle2 = Dom4jUtil.getFirstChild(Dom4jUtil.getFirstChild(RqstngBkOfRdscntWthCntrlBkEle, "Agcy"),"Acct");
			Organisation8 agcyElement = rqstngBkOfRdscntWthCntrlBkEle.getAgcy();
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
		{//贴入人信息
			
			Organisation2  rdscntWthCntrlBkSysEle = reqEle.getRdscntWthCntrlBkSys();
			
			
			String Nm = rdscntWthCntrlBkSysEle.getNm();;//名称
			Account1  acctEle =  rdscntWthCntrlBkSysEle.getAcct();
			String acctId = acctEle.getId();//帐号
			String AcctSvcr = acctEle.getAcctSvcr();//开户行行号
			vo.setReceiveAcctNo(acctId);
            vo.setReceiveBankNo(AcctSvcr);
            vo.setReceiveBankName(Nm);
		}
		
		return vo;
	}

	
}
