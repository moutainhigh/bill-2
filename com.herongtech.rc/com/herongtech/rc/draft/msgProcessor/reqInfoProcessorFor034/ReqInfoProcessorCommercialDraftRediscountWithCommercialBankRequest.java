package com.herongtech.rc.draft.msgProcessor.reqInfoProcessorFor034;

import java.math.BigDecimal;

import org.apache.commons.lang.math.NumberUtils;
import org.xmlbean.ecds034.Account1;
import org.xmlbean.ecds034.Account2;
import org.xmlbean.ecds034.CommercialDraftRediscountWithCommercialBankRequest1;
import org.xmlbean.ecds034.CurrencyAndAmount;
import org.xmlbean.ecds034.Organisation1;
import org.xmlbean.ecds034.Organisation2;
import org.xmlbean.ecds034.Organisation8;
import org.xmlbean.ecds034.RediscountWithCommercialBank1;
import org.xmlbean.ecds034.RequestInformation1;

import com.herongtech.console.core.constant.DraftConstants;
import com.herongtech.rc.draft.common.MsgUtil;
import com.herongtech.rc.draft.common.vo.DraftInfoVo;
/**
 * 转贴现申请
 *
 */							 
public class ReqInfoProcessorCommercialDraftRediscountWithCommercialBankRequest extends ReqInfoProcessor{

	@Override
	public DraftInfoVo parseReqInfo(RequestInformation1 reqInfo, DraftInfoVo vo) {     		
		CommercialDraftRediscountWithCommercialBankRequest1 reqEle = reqInfo.getCommercialDraftRediscountWithCommercialBankRequest();
		String msgId=reqEle.getMsgId().getId();
        vo.setReqMsgId(msgId);
				
		{//转贴现信息
			
			RediscountWithCommercialBank1  rdscntWthComrclBkEle = reqEle.getRdscntWthComrclBk();
			String RpdMk = rdscntWthComrclBkEle.getRpdMk().toString();//转贴现种类RM00买断式	RM01回购式
			String Dt = MsgUtil.convertToString(rdscntWthComrclBkEle.getDt());//转贴现申请日期
			String IntrstRate = rdscntWthComrclBkEle.getIntrstRate().toString();//转贴现利率
			String Amt = rdscntWthComrclBkEle.getAmt().getStringValue();//转贴现实付金额
			String BanEndrsmtMk = rdscntWthComrclBkEle.getBanEndrsmtMk().toString();//不得转让标记
			String SttlmMk = rdscntWthComrclBkEle.getSttlmMk().toString();//线上清算标记
			String BtchNb = rdscntWthComrclBkEle.getBtchNb();//批次号
			vo.setRpdMk(RpdMk);
            vo.setApplyDt(MsgUtil.converISODateTime(Dt));
            vo.setIntrstRate(NumberUtils.createDouble(IntrstRate));
            vo.setAmt(NumberUtils.createDouble(Amt));
            vo.setBanEndrsmtMk(BanEndrsmtMk);
            vo.setSettleMk(SttlmMk);
            vo.setBtchNb(BtchNb);
			
            if(DraftConstants.REPURCHASED_MARK_CODE_RM01.equals(RpdMk)){
				String RpdOpenDt = String.valueOf(rdscntWthComrclBkEle.getRpdOpenDt());//贴现赎回开放日
				String RpdDueDt = String.valueOf(rdscntWthComrclBkEle.getRpdDueDt());//贴现赎回截止日
				
				BigDecimal rpdIntrstRate = rdscntWthComrclBkEle.getRpdIntrstRate();
				String RpdIntrstRate = rpdIntrstRate==null ? "0" : String.valueOf( rpdIntrstRate);//贴现赎回利率
				
				CurrencyAndAmount rpdAmt = rdscntWthComrclBkEle.getRpdAmt();
				String RpdAmt =   rpdAmt == null ? "0" : rpdAmt.getStringValue();//贴现赎回金额
				vo.setRpdOpenDt(MsgUtil.converISODateTime(RpdOpenDt));
                vo.setRpdDueDt(MsgUtil.converISODateTime(RpdDueDt));
                vo.setRpdIntrstRate(NumberUtils.createDouble(RpdIntrstRate));
                vo.setRpdAmt(NumberUtils.createDouble(RpdAmt));
			}
		}
            
		{//贴出人信息

			Organisation1  rqstngBkOfRdscntWthComrclBkEle = reqEle.getRqstngBkOfRdscntWthComrclBk();		
			String Role = rqstngBkOfRdscntWthComrclBkEle.getRole().toString();//类别
			String Nm = rqstngBkOfRdscntWthComrclBkEle.getNm();//名称
			String CmonId = rqstngBkOfRdscntWthComrclBkEle.getCmonId();//组织机构代码
			String ElctrncSgntr = rqstngBkOfRdscntWthComrclBkEle.getElctrncSgntr();//电子签名
			Account1  acctEle =  rqstngBkOfRdscntWthComrclBkEle.getAcct();
			String acctId = acctEle.getId();//帐号
			String AcctSvcr = acctEle.getAcctSvcr();//开户行行号			
			Organisation8 agcyElement = rqstngBkOfRdscntWthComrclBkEle.getAgcy();
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
			
			Organisation2  rcvgBkOfRdscntWthComrclBkEle = reqEle.getRcvgBkOfRdscntWthComrclBk();
			
			String Nm = rcvgBkOfRdscntWthComrclBkEle.getNm();//名称
			Account1  acctEle =  rcvgBkOfRdscntWthComrclBkEle.getAcct();
			String acctId =acctEle.getId();//帐号
			String AcctSvcr = acctEle.getAcctSvcr();//开户行行号
			vo.setReceiveAcctNo(acctId);
            vo.setReceiveBankNo(AcctSvcr);
            vo.setReceiveBankName(Nm);
            vo.setReceiveName(Nm);
		}

		vo.setBusiType("013");
		return vo;
	}
	
}
