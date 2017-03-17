package com.herongtech.console.core.util;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;

import com.herongtech.console.cache.RgctStatusMappingCache;
import com.herongtech.console.domain.assu.bean.AssuBillInfo;
import com.herongtech.console.domain.bean.EcdsBillBean;
import com.herongtech.console.domain.disc.bean.EndoBean;
import com.herongtech.rc.domain.bean.RgctBill;
import com.herongtech.rc.domain.bean.RgctBillInfo;
import com.herongtech.rc.domain.bean.RgctEndoHist;
import com.herongtech.rc.domain.dao.EcdsBankDataDao;


public class WebBankUtil {
	
	public static EcdsBillBean createEcdsBillBean(RgctBill bill)throws Exception{
		EcdsBillBean bean=new EcdsBillBean();
		EcdsBankDataDao ecdsBankDao=new EcdsBankDataDao();
		BeanUtils.copyProperties(bean, bill.getInfo());
		final RgctBillInfo info=bill.getInfo();
		bean.setRgctId(info.getId());
		bean.setPayee(info.getPayeeName());
		bean.setPayeeBank(ecdsBankDao.getEcdsBankData(info.getPayeeBankNo()).getActorFullCall());
		bean.setIssueDt(info.getIssueDt());
		bean.setAcceptDt(info.getAcceptorDate());
		bean.setAcceptorBank(info.getAcceptor());
		bean.setAcceptorAcct(info.getAcceptorAcct());
		bean.setAcceptorCreditAgency(info.getAcptrCreditratingAgency());
		bean.setAcceptorCreditClass(info.getAcptrCreditrating());
		bean.setAcceptorCreditDueDt(info.getAcptrCreditratingDuedt()==null?"":info.getAcptrCreditratingDuedt());
		bean.setRemitterBank(ecdsBankDao.getEcdsBankData(info.getRemitterBankNo()).getActorFullCall());
		bean.setRemitterCreditAgency(info.getDrwrCreditratingAgency());
		bean.setRemitterCreditClass(info.getDrwrCreditrating());
		bean.setRemitterCreditDueDt(info.getDrwrCreditratingDuedt()==null?"":info.getDrwrCreditratingDuedt());
		bean.setCurStatus(bill.getHist().getCurStatus());
		bean.setBanEndorsementMark(bill.getInfo().getInfoForbidFlag());
		
		String cureStatus = bill.getHist().getCurStatus();
		//cureStatus = cureStatus.replaceAll("11", "08");
		bean.setCurStatusName(RgctStatusMappingCache.getRgctStatusMappingCache().getRgctStatusMapping(cureStatus).getStatusName());    
		bean.setBillBeforeOwner(bill.getHist().getBillBeforeOwner());
		DecimalFormat df = new DecimalFormat("0.00");
		bean.setBillMoney(df.format(info.getBillMoney()));
		bean.setRemark(bill.getHist().getFromRemark());
		return bean;
	} 

	public static List<EndoBean> createEndoHist(List<RgctEndoHist> list){
		List<EndoBean> endos=new ArrayList<EndoBean>();
		for (int i = 0; i < list.size(); i++) {
			RgctEndoHist endoHist=list.get(i);
			EndoBean endoBean=new EndoBean();
			endoBean.setAssuAdrr(endoHist.getGuarnteeAdr());
			endoBean.setEndoDate(endoHist.getEndorseDt());
			endoBean.setEndoType(endoHist.getEndoType());
			endoBean.setFromAcct(endoHist.getFromAcctNo());
			endoBean.setFromBankNo(endoHist.getFromBankNo());
			endoBean.setFromName(endoHist.getFromName());
			endoBean.setPayFlag(endoHist.getIsRejectPrsnt());
			endoBean.setPayRefuReson(endoHist.getRejectPrsntRsn());
			endoBean.setProtEndors(endoHist.getForbidFlag());
			endoBean.setRecType(endoHist.getRecourseType());
			endoBean.setRedeemEndDate(endoHist.getRpdDueDate());
			endoBean.setRedeemOpenDate(endoHist.getRpdOpenDate());
			endoBean.setSignDate(endoHist.getSignDate());
			endoBean.setToAcct(endoHist.getToAcctNo());
			endoBean.setToBankNo(endoHist.getToBankNo());
			endoBean.setToName(endoHist.getToName());
			endos.add(endoBean);
		}
		return endos;
	}
	
	
	
	public static List<AssuBillInfo> createAssu(List<AssuBillInfo> assus){
		List<AssuBillInfo> list=new ArrayList<AssuBillInfo>();
		for(AssuBillInfo assu:assus){
			AssuBillInfo bean=new AssuBillInfo();
			bean.setAssuType(assu.getAssuType());
			bean.setGuarntrAcctNo(assu.getGuarntrAcctNo());
			bean.setGuartrAddr(assu.getGuartrAddr());
			bean.setGuartrBankNo(assu.getGuartrBankNo());
			bean.setGuartrName(assu.getGuartrName());
			bean.setGuartrOrgcode(assu.getGuartrOrgcode());
			bean.setWarteeAcctNo(assu.getWarteeAcctNo());
			bean.setWarteeBankNo(assu.getWarteeBankNo());
			bean.setWarteeDt(assu.getWarteeDt());
			bean.setWarteeCustName(assu.getWarteeCustName());
			list.add(bean);
		}
		return list;
	}

}
