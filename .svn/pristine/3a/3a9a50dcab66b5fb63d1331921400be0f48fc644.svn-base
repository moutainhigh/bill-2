
package com.herongtech.rc.draft.msgProcessor.histInfoProcessorFor034;

import java.util.Date;

import org.xmlbean.ecds034.Account1;
import org.xmlbean.ecds034.Account2;
import org.xmlbean.ecds034.CommercialDraftBusiness1;
import org.xmlbean.ecds034.Guarantee4;
import org.xmlbean.ecds034.Organisation10;
import org.xmlbean.ecds034.Organisation6;
import org.xmlbean.ecds034.Organisation8;

import com.herongtech.rc.domain.bean.RgctBillInfo;
import com.herongtech.rc.draft.common.vo.DraftInfoVo;
/**
 * 历史信息处理器－保证
 *
 */
public class HistoryInfoProcessorGuarntee extends HistoryInfoProcessor{

	@Override
	public DraftInfoVo parseHistoryInfo(CommercialDraftBusiness1 comrclDrftBiz, RgctBillInfo info) {
	    DraftInfoVo vo = new DraftInfoVo();
		Guarantee4 guarnteeEle = comrclDrftBiz.getGuarntee();
		
		String fromAcctNo = null;
		String fromBankNo = null;
		String fromName = null;
		String fromOrgCode = null;
		String fromRole = null;
		
		String toAcctNo = null;
		String toBankNo = null;
		String toName = null;
		String toOrgCode = null;
		String toRole = null;
		String toAddress = null;
		
		Date signDate = null;
		String signRemark = null;
		
		 
		{//保证信息
//			signDate = guarnteeEle.getDt();//保证回复日期	
			signRemark = guarnteeEle.getRmrkBySgnr();//回复人备注	
			vo.setSignDt(guarnteeEle.getDt().getTime());
		}
		
		{//被保证人信息
			Organisation10 warnteeEle =  guarnteeEle.getWarntee();
			fromRole = warnteeEle.getRole().toString();//类别
			fromName = warnteeEle.getNm();//名称
			fromOrgCode = warnteeEle.getCmonId();//组织机构代码
			Account1 acctEle = warnteeEle.getAcct();
			fromAcctNo = acctEle.getId();//帐号
			fromBankNo = acctEle.getAcctSvcr();//开户行行号			
			String AcctSvcr2 = null;
			Organisation8 agcy2Ele = warnteeEle.getAgcy();
			if (agcy2Ele != null){
				Account2 acctEle2 = agcy2Ele.getAcct();
				if (acctEle2!=null)
					AcctSvcr2 = acctEle2.getAcctSvcr();//承接行行号
			}
			vo.setFromName(fromName);
            vo.setFromAcctNo(fromAcctNo);
            vo.setFromBankNo(fromBankNo);
            vo.setFromRoleType(fromRole);
            vo.setFromOrgCode(fromOrgCode);
            vo.setFromAgcyBankNo(AcctSvcr2);
			
		}
		{//保证人信息
			
			Organisation6  guarntrEle =guarnteeEle.getGuarntr();
			toRole = guarntrEle.getRole().toString();//类别
			toName = guarntrEle.getNm();//名称
			toOrgCode = guarntrEle.getCmonId();//组织机构代码
			Account1 acctEle =  guarntrEle.getAcct();
			toAcctNo = acctEle.getId();//帐号
			toBankNo = acctEle.getAcctSvcr();//开户行行号
			toAddress = guarntrEle.getAdr();//保证人地址	
			
			vo.setReceiveName(toName);
            vo.setReceiveAcctNo(toAcctNo);
            vo.setReceiveBankNo(toBankNo);
            vo.setReceiveRoleType(toRole);
            vo.setReceiveOrgCode(toOrgCode);
		}
		vo.setBusiType("8");//此处业务类型表示背书类型
		return vo;
	}
}
