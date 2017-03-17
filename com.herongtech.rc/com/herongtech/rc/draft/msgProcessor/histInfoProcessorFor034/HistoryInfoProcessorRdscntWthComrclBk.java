package com.herongtech.rc.draft.msgProcessor.histInfoProcessorFor034;

import org.xmlbean.ecds034.Account1;
import org.xmlbean.ecds034.Account2;
import org.xmlbean.ecds034.CommercialDraftBusiness1;
import org.xmlbean.ecds034.Organisation10;
import org.xmlbean.ecds034.Organisation5;
import org.xmlbean.ecds034.Organisation8;
import org.xmlbean.ecds034.RediscountWithCommercialBank4;

import com.herongtech.rc.domain.bean.RgctBillInfo;
import com.herongtech.rc.draft.common.MsgUtil;
import com.herongtech.rc.draft.common.vo.DraftInfoVo;
/**
 * 历史信息处理器－转贴现
 *
 */
public class HistoryInfoProcessorRdscntWthComrclBk extends HistoryInfoProcessor{

	@Override
	public DraftInfoVo parseHistoryInfo(CommercialDraftBusiness1 comrclDrftBiz, RgctBillInfo info) {
	    DraftInfoVo vo = new DraftInfoVo();
		RediscountWithCommercialBank4 rdscntWthComrclBkEle = comrclDrftBiz.getRdscntWthComrclBk();		
		
		{//转贴现信息
			String RpdMk = rdscntWthComrclBkEle.getRpdMk().toString();//转贴现种类RM00买断式	RM01回购式
			String Dt = MsgUtil.convertToString(rdscntWthComrclBkEle.getDt());//转贴现签收日期
			String IntrstRate = rdscntWthComrclBkEle.getIntrstRate().toString();//转贴现利率
			String Amt = rdscntWthComrclBkEle.getAmt().getStringValue();//转贴现实付金额
			String BanEndrsmtMk = rdscntWthComrclBkEle.getBanEndrsmtMk().toString();//不得转让标记
			String SttlmMk = rdscntWthComrclBkEle.getSttlmMk().toString();//线上清算标记
			
			if ("RM01".equals(RpdMk)){
				String RpdOpenDt = MsgUtil.convertToString(rdscntWthComrclBkEle.getRpdOpenDt());//贴现赎回开放日
				String RpdDueDt = MsgUtil.convertToString(rdscntWthComrclBkEle.getRpdDueDt());//贴现赎回截止日
				String RpdIntrstRate =rdscntWthComrclBkEle.getRpdIntrstRate() == null ? "0.00" : rdscntWthComrclBkEle.getRpdIntrstRate().toString();//贴现赎回利率
				String RpdAmt = rdscntWthComrclBkEle.getRpdAmt()== null ?  "0.00" : rdscntWthComrclBkEle.getRpdAmt().getStringValue();//贴现赎回金额
				vo.setBusiType("3_1");//此处业务类型表示背书类型
			}else{
			    vo.setBusiType("3");//此处业务类型表示背书类型
			}
			
			vo.setSignDt(rdscntWthComrclBkEle.getDt().getTime());
            vo.setBanEndrsmtMk(BanEndrsmtMk);
		}
		
		{//贴出人信息
			Organisation10  rqstngBkOfRdscntWthComrclBkEle = rdscntWthComrclBkEle.getRqstngBkOfRdscntWthComrclBk();
			String Role = rqstngBkOfRdscntWthComrclBkEle.getRole().toString();//类别
			String Nm =rqstngBkOfRdscntWthComrclBkEle.getNm();//名称
			String CmonId = rqstngBkOfRdscntWthComrclBkEle.getCmonId();//组织机构代码
			Account1 acctEle = rqstngBkOfRdscntWthComrclBkEle.getAcct();
			String acctId = acctEle.getId();//帐号
			String AcctSvcr = acctEle.getAcctSvcr();//开户行行号			
			Organisation8 agcy2Ele = rqstngBkOfRdscntWthComrclBkEle.getAgcy();
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
			Organisation5  rcvgBkOfRdscntWthComrclBkEle = rdscntWthComrclBkEle.getRcvgBkOfRdscntWthComrclBk();
			String Role = rcvgBkOfRdscntWthComrclBkEle.getRole().toString();//类别
			String Nm = rcvgBkOfRdscntWthComrclBkEle.getNm();//名称
			String CmonId = rcvgBkOfRdscntWthComrclBkEle.getCmonId();//组织机构代码
			Account1 acctEle = rcvgBkOfRdscntWthComrclBkEle.getAcct();
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
