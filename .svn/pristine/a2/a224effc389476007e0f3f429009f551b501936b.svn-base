package com.herongtech.rc.draft.msgProcessor.histInfoProcessorFor034;

import java.util.Date;

import org.xmlbean.ecds034.Account1;
import org.xmlbean.ecds034.Account2;
import org.xmlbean.ecds034.CommercialDraft3;
import org.xmlbean.ecds034.CommercialDraftBusiness1;
import org.xmlbean.ecds034.Organisation3;
import org.xmlbean.ecds034.Organisation4;
import org.xmlbean.ecds034.Organisation8;

import com.herongtech.commons.tools.DateUtil;
import com.herongtech.rc.domain.bean.RgctBillInfo;
import com.herongtech.rc.draft.common.MsgUtil;
import com.herongtech.rc.draft.common.vo.DraftInfoVo;
/**
 * 历史信息处理器－提示承兑
 *
 */
public class HistoryInfoProcessorAccptnc extends HistoryInfoProcessor{

	@Override
	public DraftInfoVo parseHistoryInfo(CommercialDraftBusiness1 comrclDrftBiz, RgctBillInfo info ) {
	    DraftInfoVo vo=new DraftInfoVo();
		CommercialDraft3 accptncEle = comrclDrftBiz.getAccptnc();
		{//提示承兑信息
//			String Dt = MsgUtil.convertToString(accptncEle.getDt());//提示承兑签收日期
			//String UcondlConsgnmtMrk = accptncEle.getUcondlConsgnmtMrk().toString();//到期无条件支付委托
			//String UcondlPrmsMrk = accptncEle.getUcondlPrmsMrk().toString();//到期无条件支付承诺
			String TxlCtrctNb = accptncEle.getTxlCtrctNb();//交易合同编号
			String InvcNb = accptncEle.getInvcNb();//发票号码
			String AccptncAgrmtNb = accptncEle.getAccptncAgrmtNb();//承兑协议编号
			String RmrkByPropsr = accptncEle.getRmrkByPropsr();//出票人备注
			String RmrkBySgnr  = accptncEle.getRmrkBySgnr();//回复人备注
//			info.setaccept
			vo.setSignDt(accptncEle.getDt().getTime());
			vo.setTxlCtrctNb(TxlCtrctNb);
			vo.setInvcNb(InvcNb);
			vo.setFromRemark(RmrkByPropsr);
			vo.setReceiveRemark(RmrkBySgnr);
		}
		
		{//承兑人信息				
			Organisation4 accptrEle = accptncEle.getAccptr();
			String receiveRole = accptrEle.getRole().toString();//类别
			String receiveNm = accptrEle.getNm();//名称
			String receiveCmonId = accptrEle.getCmonId();//组织机构代码
			Account1  acctEle =  accptrEle.getAcct();
			String receiveacctId = acctEle.getId();//帐号
			String receiveAcctSvcr = acctEle.getAcctSvcr();//开户行行号		
			String receiveCdtRatgs = accptrEle.getCdtRatgs();//信用等级
			String receiveCdtRatgAgcy = accptrEle.getCdtRatgAgcy();//评级机构
			
			String receiveCdtRatgDueDt = null;
			if(accptrEle.getCdtRatgDueDt() !=null ){
			    receiveCdtRatgDueDt = String.valueOf(accptrEle.getCdtRatgDueDt());//评级到期日
			}
			
			vo.setReceiveName(receiveNm);
            vo.setReceiveAcctNo(receiveacctId);
            vo.setReceiveBankNo(receiveAcctSvcr);
            vo.setReceiveRoleType(receiveRole);
            vo.setReceiveOrgCode(receiveCmonId);
            info.setAcceptorOrgCode(receiveCmonId);
            info.setAcptrCreditrating(receiveCdtRatgs);
            info.setAcptrCreditratingAgency(receiveCdtRatgAgcy);
            info.setAcptrCreditratingDuedt(receiveCdtRatgDueDt);
            
		}
		
		{//出票人信息
			Organisation3 drwrEle = accptncEle.getDrwr();
			String Role = drwrEle.getRole().toString();//	
			String CmonId = drwrEle.getCmonId();//开户行行号	
			Account1  acctEle =  drwrEle.getAcct();
			String acctId = acctEle.getId();//帐号
			String AcctSvcr = acctEle.getAcctSvcr();//开户行行号	
			Organisation8  agcyElement = drwrEle.getAgcy();
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
            vo.setBusiType("002");//此处业务类型标示背书类型
            
            
		}
		
		return vo;
	}
	
	
}
