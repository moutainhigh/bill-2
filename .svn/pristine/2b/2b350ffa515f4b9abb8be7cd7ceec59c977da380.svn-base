package com.herongtech.console.service.acct;

import java.util.Iterator;
import java.util.List;

import com.herongtech.console.core.constant.IConstants;
import com.herongtech.console.core.util.DateTimeUtil;
import com.herongtech.console.core.util.MathScaleUtil;
import com.herongtech.console.core.util.MoneyUtils;
import com.herongtech.console.domain.acct.bean.AccountRequestDTO;
import com.herongtech.console.domain.acct.bean.AccountResponseDTO;
import com.herongtech.console.domain.acct.bean.AcctBalance;
import com.herongtech.console.domain.acct.bean.AcctFlow;
import com.herongtech.console.domain.acct.bean.AcctFlowInfo;
import com.herongtech.console.domain.acct.dao.AcctBalanceDao;
import com.herongtech.console.domain.acct.dao.AcctFlowDao;
import com.herongtech.console.domain.acct.dao.AcctFlowInfoDao;
import com.herongtech.console.domain.bean.UserLoginfo;
import com.herongtech.console.domain.get.bean.GetApplyInfo;
import com.herongtech.console.domain.get.bean.GetBillInfo;
import com.herongtech.console.service.ServiceFactory;
import com.herongtech.console.service.interfaces.IAccountFacadeService;
import com.herongtech.console.service.interfaces.ISequenceService;

public class GetAccountService implements IAccountFacadeService{
    
  // private ICoreRemoteService coreRemoteService;
    

    @Override
    public AccountResponseDTO account(AccountRequestDTO accountReq)
            throws Exception {
       
        
        GetApplyInfo apply=(GetApplyInfo) accountReq.getApply();
        List<GetBillInfo> billList=accountReq.getBillList();
        UserLoginfo logonInfo=accountReq.getUserLogonInfo();
        ISequenceService sequenceService=ServiceFactory.getSequenceService();
        AcctFlowDao afDao=new AcctFlowDao();
        AcctFlowInfoDao afiDao=new AcctFlowInfoDao();
        AcctBalanceDao abDao=new AcctBalanceDao();
        
        AcctFlow af=new AcctFlow();
        af.setAfId(sequenceService.getPrimaryKeyValue());
        af.setAcctBranchNo(logonInfo.getBranchNo());
//        af.setAcctStatus(acctStatus)
        af.setAcctType(IConstants.ACCT_TYPE.ACCOUNT);
        af.setForeFlowNo(sequenceService.getAcctFlowNo(logonInfo.getBranchNo()));
        af.setProdNo(apply.getProdNo());
      
       
        af.setTransBranchName(logonInfo.getBranchName());
        af.setTransBranchNo(logonInfo.getBranchNo());
        af.setTransDt(DateTimeUtil.getWorkdayString());
//        af.setTransName(transName);//TODO:记账可能需要根据产品类型 来判断对应的交易编码
        af.setTransNo(apply.getProdNo());
        af.setTransTm(DateTimeUtil.get_hhmmss_time());
        af.setTransUserNo(logonInfo.getUserNo());
        
     //   double totPayMoney=0.0;
     //   double totInterest=0.0;
        double totAmt=0.0;
        for(Iterator<GetBillInfo> iter=billList.iterator();iter.hasNext();){
        	GetBillInfo bill=iter.next();
            AcctFlowInfo afi=new AcctFlowInfo();
            afi.setAcctBranchNo(af.getAcctBranchNo());
            afi.setAcctInfoId(sequenceService.getPrimaryKeyValue());
            afi.setAfId(af.getAfId());
            afi.setBillAmonut(bill.getBillMoney());
            afi.setBillNo(bill.getBillNo());
//            afi.setCurRemaIntrst();//买入时无需塞值
            afi.setInfoId(bill.getGetmxId());
            afi.setProdNo(af.getProdNo());
            afi.setRgctId(bill.getRgctId());
   //         afi.setSettlementAmt(bill.getDraftPayMoney());//实收（付）金额
   //         afi.setSettlementIntrst(MathScaleUtil.add(bill.getSalerInterest(), bill.getBuyerInterest()));//应收（付）利息
            afi.setTransBranchNo(af.getTransBranchNo());
            afi.setTransDt(af.getTransDt());
            afi.setTransNo(af.getTransNo());
            afi.setTransTm(af.getTransTm());
      //      totPayMoney=MathScaleUtil.add(totPayMoney, afi.getSettlementAmt());//实收（付）金额
      //     totInterest=MathScaleUtil.add(totInterest, afi.getSettlementIntrst());//应收（付）利息
            totAmt=MathScaleUtil.add(totAmt, afi.getBillAmonut());
            AcctBalance balance=new AcctBalance();
            balance.setBalanceId(sequenceService.getPrimaryKeyValue());
            balance.setBillNo(bill.getBillNo());
            balance.setCreateDt(DateTimeUtil.get_YYMMDD_Date());
            balance.setCreateTm(af.getTransTm());
            balance.setCustName(apply.getCustName());
            balance.setCustNo(apply.getCustNo());
            balance.setInfoId(bill.getGetmxId());
            balance.setProdNo(apply.getProdNo());
            balance.setRgctId(bill.getRgctId());
            balance.setStartDt(af.getTransDt());
            balance.setStatus(IConstants.BALANCE_STATUS.BALANCE);
            afiDao.addAcctFlowInfo(afi);
            abDao.addAcctBalance(balance);
            
            bill.setExSerial(af.getForeFlowNo());
            
        }
        af.setTotalAmount(totAmt);
     //   af.setSettlementInterest(totInterest);
     //   af.setSettlementMoney(totPayMoney);
        afDao.addAcctFlow(af);
        
        /*AcctRequest request=new AcctRequest();  
        request.setTransNo(af.getTransNo());
        request.setAf(af);
        request.setApply(apply);
        request.setList(billList);
        request.addProperty("af", af);
        request.addProperty("apply", apply);
        request.addProperty("list", billList);
        
        AccountResponseDTO respDto=coreRemoteService.account(request);*/
        
        AccountResponseDTO respDto=new AccountResponseDTO();
        respDto.setSuccess(true);
        return respDto;
    }

    @Override
    public AccountResponseDTO reverseAccount(AccountRequestDTO accountReq)
            throws Exception {
        AccountResponseDTO respDto=new AccountResponseDTO();
        
        GetApplyInfo apply=(GetApplyInfo) accountReq.getApply();
        List<GetBillInfo> billList=accountReq.getBillList();
        UserLoginfo logonInfo=accountReq.getUserLogonInfo();
        ISequenceService sequenceService=ServiceFactory.getSequenceService();
        AcctFlowDao afDao=new AcctFlowDao();
        AcctFlowInfoDao afiDao=new AcctFlowInfoDao();
        AcctBalanceDao abDao=new AcctBalanceDao();
        
        AcctFlow af=new AcctFlow();
        af.setAfId(sequenceService.getPrimaryKeyValue());
        af.setAcctBranchNo(logonInfo.getBranchNo());
//        af.setAcctStatus(acctStatus)
        af.setAcctType(IConstants.ACCT_TYPE.REVERSE_ACCOUNT);
        af.setForeFlowNo(sequenceService.getAcctFlowNo(logonInfo.getBranchNo()));
        af.setProdNo(apply.getProdNo());
      
        af.setTotalAmount(MoneyUtils.formate(Double.valueOf(apply.getTotalMoney())));
        af.setTransBranchName(logonInfo.getBranchName());
        af.setTransBranchNo(logonInfo.getBranchNo());
        af.setTransDt(DateTimeUtil.getWorkdayString());
//        af.setTransName(transName);//TODO:记账可能需要根据产品类型 来判断对应的交易编码
//        af.setTransNo(transNo);
        af.setTransTm(DateTimeUtil.get_hhmmss_time());
        af.setTransUserNo(logonInfo.getUserNo());
        
        double totPayMoney=0.0;
        double totInterest=0.0;
        
        for(Iterator<GetBillInfo> iter=billList.iterator();iter.hasNext();){
        	GetBillInfo bill=iter.next();
            AcctFlowInfo afi=new AcctFlowInfo();
            afi.setAcctBranchNo(af.getAcctBranchNo());
            afi.setAcctInfoId(sequenceService.getPrimaryKeyValue());
            afi.setAfId(af.getAfId());
            afi.setBillAmonut(bill.getBillMoney());
            afi.setBillNo(bill.getBillNo());
//            afi.setCurRemaIntrst();//买入时无需塞值
//            afi.setDiscrepancyInterest()
            afi.setInfoId(bill.getGetmxId());
            afi.setProdNo(af.getProdNo());
            afi.setRgctId(bill.getRgctId());
      //      afi.setSettlementAmt(bill.getDraftPayMoney());//实收（付）金额
      //      afi.setSettlementIntrst(MathScaleUtil.add(bill.getSalerInterest(), bill.getBuyerInterest()));//应收（付）利息
            afi.setTransBranchNo(af.getTransBranchNo());
            afi.setTransDt(af.getTransDt());
            afi.setTransNo(af.getTransNo());
            afi.setTransTm(af.getTransTm());
            totPayMoney=MathScaleUtil.add(totPayMoney, afi.getSettlementAmt());
            totInterest=MathScaleUtil.add(totInterest, afi.getSettlementIntrst());
            afiDao.addAcctFlowInfo(afi);
            
            AcctBalance balance=abDao.getAcctBalanceByRgctId(bill.getRgctId());
            balance.setEndDt(af.getTransDt());
            balance.setUpdateDt(af.getTransDt());
            balance.setUpdateTm(af.getTransTm());
            balance.setStatus(IConstants.BALANCE_STATUS.BALANCE);
            abDao.modifyAcctBalance(balance);


            
            bill.setExSerial(af.getForeFlowNo());
            
        }
        af.setSettlementInterest(totInterest);
        af.setSettlementMoney(totPayMoney);
        afDao.addAcctFlow(af);
        
        //TODO: 调用接口转换程序
        
        return respDto;
    }

    
    
    /*public void setCoreRemoteService(ICoreRemoteService coreRemoteService) {
        this.coreRemoteService = coreRemoteService;
    }*/

}
