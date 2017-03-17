package com.herongtech.rc.trans.trans034;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.herongtech.console.core.constant.DraftConstants;
import com.herongtech.console.core.constant.IConstants;
import com.herongtech.console.core.constant.IDict;
import com.herongtech.console.core.constant.RcConstants;
import com.herongtech.console.core.util.CommUtils;
import com.herongtech.console.core.util.DateTimeUtil;
import com.herongtech.console.core.util.MathScaleUtil;
import com.herongtech.console.domain.bean.Branch;
import com.herongtech.console.domain.bean.CustInfo;
import com.herongtech.console.domain.bean.CustInfoAcct;
import com.herongtech.console.domain.bean.SignProd;
import com.herongtech.console.service.ServiceFactory;
import com.herongtech.console.service.interfaces.IBranchService;
import com.herongtech.console.service.interfaces.ICustInfoAcctService;
import com.herongtech.console.service.interfaces.ICustInfoService;
import com.herongtech.console.service.interfaces.ISignProdService;
import com.herongtech.console.web.busicontroller.common.CommonConst;
import com.herongtech.context.Context;
import com.herongtech.context.util.ContextUtil;
import com.herongtech.db.impl.session.DBFactory;
import com.herongtech.db.interfaces.IDB;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.log.CommonLog;
import com.herongtech.rc.domain.bean.CustBillStorage;
import com.herongtech.rc.domain.bean.EcdsBankData;
import com.herongtech.rc.domain.bean.RgctBill;
import com.herongtech.rc.domain.bean.RgctBillHist;
import com.herongtech.rc.domain.bean.RgctBillInfo;
import com.herongtech.rc.domain.bean.RgctEndoHist;
import com.herongtech.rc.domain.bean.RgctMethod;
import com.herongtech.rc.domain.dao.RgctMethodDao;
import com.herongtech.rc.draft.common.MsgUtil;
import com.herongtech.rc.draft.common.vo.DraftInfoVo;
import com.herongtech.rc.draft.common.vo.RequestInfo;
import com.herongtech.rc.service.RcServiceFactory;
import com.herongtech.rc.service.interfaces.IBillFlowInfoService;
import com.herongtech.rc.service.interfaces.ICustBillStorageService;
import com.herongtech.rc.service.interfaces.IRgctDraftLogService;
import com.herongtech.rc.service.rcservice.IRcBaseService;
import com.herongtech.rc.trans.EcdsBaseService;
import com.herongtech.sysconstant.ISysErrorNo;


public class T034Service extends EcdsBaseService {


    @Override
    protected void beforeAction(Context context) throws Exception {
        super.beforeAction(context);
        RequestInfo request = (RequestInfo) ContextUtil.getRequestData(context); // 请求数据集合
        DraftInfoVo vo = (DraftInfoVo) request;
        RgctBillInfo bill = vo.getBill();
        DraftInfoVo ExceVo = new DraftInfoVo();
        ExceVo.setBusiType(vo.getBusiType());
        ExceVo.setOrgnlMsgId(vo.getReqMsgId());
        ExceVo.setOrgnlDraftCreDtTM(vo.getDraftCreDtTm());
        ExceVo.setBill(bill);
        ExceVo.setFromBankNo(vo.getReceiveBankNo());
        ExceVo.setReceiveBankNo(vo.getFromBankNo());
        if("018".equals(vo.getBusiType())){
        	ICustInfoService custService = ServiceFactory.getCustInfoService();
        	String orgCode = "";
        	String custName = "";
        	try {
        		CustInfo custInfo = custService.getCustInfoByOrgNoAndName(vo.getFromOrgCode(), vo.getFromName());
            	orgCode = custInfo.getOrgCode();
            	custName = custInfo.getCustName();
			} catch (Exception e) {
				CommonLog.getCommonLogCache().errorMessage("组织机构代码"+":"+vo.getFromOrgCode()+",名称"+vo.getFromName());
			}
        	if(!vo.getFromOrgCode().equals(orgCode)||!vo.getFromName().equals(custName)){
        		this.rejectRequest(vo.getFromAcctNo(), vo.getFromBankNo(), ExceVo);
        	}
        	this.checkAcctInfo(vo.getReceiveAcctNo(), vo.getReceiveBankNo(), vo.getReceiveName(), ExceVo);
        }else if("025".equals(vo.getBusiType())){
        	this.checkAcctInfo(vo.getReceiveAcctNo(), vo.getReceiveBankNo(), vo.getReceiveName(), ExceVo);
        }else if("002".equals(vo.getBusiType())){
        	if(CommonConst.BILL_TYPE_CORP.equals(DraftConstants.RGCT_CODE_MAPPING_MAP.get(bill.getBillType()))){
        		this.checkAcctInfo(vo.getReceiveAcctNo(), vo.getReceiveBankNo(), vo.getReceiveName(), ExceVo);
        	}
        }else if("011".equals(vo.getBusiType())){
        	boolean isSuccess = this.checkCustInfo(vo.getFromAcctNo(), vo.getFromBankNo(), vo.getFromOrgCode(), ExceVo);
        	if(isSuccess){
        		this.checkReceiverAcctInfo(vo.getReceiveAcctNo(), vo.getReceiveBankNo(), vo.getReceiveName(), ExceVo);
        	}
        }else if("010".equals(vo.getBusiType())){
        	this.checkAcctInfo(vo, ExceVo);
        }else if("017".equals(vo.getBusiType())){
        	if(!"0".equals(vo.getReceiveAcctNo())){
        		this.checkReqAcctInfo(vo.getReceiveAcctNo(), vo.getReceiveBankNo(), vo.getReceiveName(), ExceVo);
        	}
        	if(!MsgUtil.isSelfBank(vo.getFromBankNo())&&"0".equals(vo.getReceiveAcctNo())){
        		ICustInfoService custService = ServiceFactory.getCustInfoService();
            	String orgCode = "";
            	String custName = "";
            	try {
            		CustInfo custInfo = custService.getCustInfoByOrgNoAndName(vo.getFromOrgCode(), vo.getFromName());
                	orgCode = custInfo.getOrgCode();
                	custName = custInfo.getCustName();
    			} catch (Exception e) {
    				CommonLog.getCommonLogCache().errorMessage("被保证人组织机构代码"+":"+vo.getFromOrgCode()+",名称"+vo.getFromName());
    			}
            	if(!vo.getFromOrgCode().equals(orgCode)||!vo.getFromName().equals(custName)){
            		this.rejectRequest(vo.getFromAcctNo(), vo.getFromBankNo(), ExceVo);
            	}
        	}
        }else if("003".equals(vo.getBusiType())){
        	if(!MsgUtil.isSelfBank(vo.getFromBankNo())){
        		this.checkReqAcctInfo(vo.getReceiveAcctNo(), vo.getReceiveBankNo(), vo.getReceiveName(), ExceVo);
        	}
        }else if("013".equals(vo.getBusiType())){
        	ICustInfoService custService = ServiceFactory.getCustInfoService();
        	if(RcConstants.BUSSINESS_ROLE3.equals(vo.getFromRoleType())||RcConstants.BUSSINESS_ROLE4.equals(vo.getFromRoleType())){
        		
        		String orgCode = "";
        		String custName = "";
        		try {
        			CustInfo custInfo = custService.getCustInfoByOrgNoAndName(vo.getFromOrgCode(), vo.getFromName());
        			orgCode = custInfo.getOrgCode();
        			custName = custInfo.getCustName();
        		} catch (Exception e) {
        			CommonLog.getCommonLogCache().errorMessage("组织机构代码"+":"+vo.getFromOrgCode()+",名称"+vo.getFromName());
        		}
        		if(!vo.getFromOrgCode().equals(orgCode)||!vo.getFromName().equals(custName)){
        			this.rejectRequest(vo.getFromAcctNo(), vo.getFromBankNo(), ExceVo);
        		}
        	}
        	this.checkReceiverAcctInfo(vo.getReceiveAcctNo(), vo.getReceiveBankNo(), vo.getReceiveName(), ExceVo);
        }
        
        
        
    }
    @Override
    protected void action(Context context) throws Exception {
        RequestInfo request = (RequestInfo) ContextUtil.getRequestData(context); // 请求数据集合
        DraftInfoVo vo = (DraftInfoVo) request;
        RgctBillInfo bill = vo.getBill();
        List<DraftInfoVo> endorHist=vo.getEndorseHist();
        List<RgctEndoHist> list=new ArrayList<RgctEndoHist>(endorHist.size());
        
        System.out.println("034Start");
        IRcBaseService baseService = RcServiceFactory.getRcBaseService();
        IBillFlowInfoService billFlowService=RcServiceFactory.getBillFlowInfoService();
        IRgctDraftLogService draftService = RcServiceFactory.getRgctDraftLogService();
        IDB session = DBFactory.getDB();
        try {
            session.beginTransaction(); 
            RgctBill rgctbill=baseService.getRgctBillByBillNo(bill.getBillNo());
            if(rgctbill.getInfo() == null ){
//                String id=ServiceFactory.getSequenceService().getBillInfoId();
//                bill.setId(id);
                RgctMethod method= new RgctMethodDao().getRgctMethod("IRcRegBillService","regBill");
                RgctBill rcbill= new RgctBill();
        		RgctBillHist hist = new RgctBillHist();
        		hist.setFromBankNo(bill.getAcceptorBankNo());
        		bill.setBillClass(IDict.K_BILL_CLASS.K_ELEC_BILL);
        		bill.setBillType(DraftConstants.RGCT_CODE_MAPPING_MAP.get(bill.getBillType()));
        		bill.setInfoForbidFlag(DraftConstants.RGCT_CODE_MAPPING_MAP.get(bill.getInfoForbidFlag()));
        		//添加付款行 - 如付款行为空,则添加承兑行为付款行
        		if(StringUtils.isBlank(bill.getDraweeBankNo())) {
        			bill.setDraweeBankNo(bill.getAcceptorBankNo());
        			bill.setDraweeBankName(bill.getAcceptor());
        		}
        		if(CommonConst.BILL_TYPE_BANK.equals(bill.getBillType())){
        			bill.setAcceptorBankName(bill.getAcceptor());
        		}else{
        			EcdsBankData bankdata=RcServiceFactory.getEcdsBankDataService().getEcdsBankData(bill.getAcceptorBankNo());
        			if(bankdata!=null){
        				bill.setAcceptorBankName(bankdata.getActorFullCall());
        			}
        		}
        		rcbill.setHist(hist);
        		rcbill.setInfo(bill);
                rgctbill=baseService.addBill(rcbill, method, null);
                // 模拟出票登记
//        		bill = rcService.changeStatus(saveBill, rcService.getRgctMethod(BaConst.BA, BaConst.IRcRegBillService, "registerYes"), null);
            }
            System.out.println("034:"+rgctbill.getInfo().getBillNo());
            rgctbill.getInfo().setReqDraftId(vo.getReqMsgId());
            for(DraftInfoVo info:endorHist){
                RgctEndoHist eh=new RgctEndoHist();
                String id = ServiceFactory.getSequenceService().getBillEndoHist();
                eh.setId(id);
                eh.setRgctId(rgctbill.getInfo().getId());
                eh.setEndorseDt(DateTimeUtil.get_YYYY_MM_DD_Date(info.getSignDt()));
                eh.setEndoType(info.getBusiType());
                eh.setForbidFlag(DraftConstants.RGCT_CODE_MAPPING_MAP.get(info.getBanEndrsmtMk()));
                eh.setFromName(info.getFromName());
                eh.setFromAcctNo(info.getFromAcctNo());
                eh.setFromBankNo(info.getFromBankNo());
                eh.setFromOrgCode(info.getFromOrgCode());
                eh.setToName(info.getReceiveName());
                eh.setToAcctNo(info.getReceiveAcctNo());
                eh.setToBankNo(info.getReceiveBankNo());
                eh.setToOrgCode(info.getReceiveOrgCode());
                list.add(eh);
            }
            rgctbill.getHist().setToAcctNo(vo.getReceiveAcctNo());
            rgctbill.getHist().setToBankNo(vo.getReceiveBankNo());
            rgctbill.getHist().setToName(vo.getReceiveName());
            rgctbill.getHist().setToOrgcode(vo.getReceiveOrgCode());
            rgctbill.getHist().setToRemark(vo.getReceiveRemark());
            rgctbill.getHist().setToRole(vo.getReceiveRoleType());
//            rgctbill.getHist().setToBranchNo(vo.getre);
//            rgctbill.getHist().setToCustNo();
            if("025".equals(vo.getBusiType())){
            	rgctbill.getHist().setIsRediscCenter(RcConstants.REDISCOUNT_CENTER);
            }else{
            	rgctbill.getHist().setIsRediscCenter("0");
            }
            if("014".equals(vo.getBusiType()) || "016".equals(vo.getBusiType())){//014/016申请报文没有接收行信息，默认取回购式买出的信息
            	rgctbill.getHist().setToBankNo(rgctbill.getHist().getFromBankNo());
            	rgctbill.getHist().setToAcctNo(rgctbill.getHist().getFromAcctNo());
            	rgctbill.getHist().setToBranchNo(rgctbill.getHist().getFromBranchNo());
            	rgctbill.getHist().setToCustNo(rgctbill.getHist().getFromCustNo());
            	rgctbill.getHist().setToName(rgctbill.getHist().getFromName());
            	rgctbill.getHist().setToRole(rgctbill.getHist().getFromRole());
            	rgctbill.getHist().setFromName(rgctbill.getHist().getToName());
            }else{
            	rgctbill.getHist().setFromName(vo.getFromName());
            }
            rgctbill.getHist().setFromAcctNo(vo.getFromAcctNo());
            rgctbill.getHist().setFromBankNo(vo.getFromBankNo());
//            rgctbill.getHist().setFromBranchNo(vo.getfrom);
//            rgctbill.getHist().setFromCustNo(vo.getfrom);
            rgctbill.getHist().setFromOrgcode(vo.getFromOrgCode());
            rgctbill.getHist().setFromRemark(vo.getFromRemark());
            rgctbill.getHist().setFromRole(vo.getFromRoleType());
            rgctbill.getHist().setFromSign(vo.getFromElctrncSgntr());
            //跨行承兑申请 无申请日期，默认为当前日期
            rgctbill.getHist().setEndorseDt(vo.getApplyDt()==null?DateTimeUtil.getEcds_YYYY_MM_DD_Date():DateTimeUtil.get_YYYY_MM_DD_Date(vo.getApplyDt()));
            rgctbill.getHist().setInterestRate(vo.getIntrstRate()!=null?vo.getIntrstRate():0.00);			
            rgctbill.getHist().setDealMoney(vo.getAmt()!=null?vo.getAmt():0.00);
            if(vo.getAmt() !=null){
                rgctbill.getHist().setInterest(MathScaleUtil.subtract(vo.getBill().getBillMoney(), vo.getAmt()));//利息＝票面－实付 
            }
            rgctbill.getHist().setForbidFlag(DraftConstants.RGCT_CODE_MAPPING_MAP.get(vo.getBanEndrsmtMk()));
            rgctbill.getHist().setIsOnline(DraftConstants.RGCT_CODE_MAPPING_MAP.get(vo.getSettleMk()));
            rgctbill.getHist().setBatchId(vo.getBtchNb());
            rgctbill.getHist().setIsRegress(DraftConstants.RGCT_CODE_MAPPING_MAP.get(vo.getRpdMk()));
            rgctbill.getHist().setRegressDt(DateTimeUtil.get_YYYY_MM_DD_Date(vo.getApplyDt()));
            rgctbill.getHist().setBackRate(vo.getRpdIntrstRate()!=null ?vo.getRpdIntrstRate():0.00);
            rgctbill.getHist().setBackAmount(vo.getRpdAmt()!=null?vo.getRpdAmt():0.00);	
            rgctbill.getHist().setBackOpenDt(DateTimeUtil.get_YYYY_MM_DD_Date(vo.getRpdOpenDt()));
            rgctbill.getHist().setBackEndDt(DateTimeUtil.get_YYYY_MM_DD_Date(vo.getRpdDueDt()));
            //入账信息
            rgctbill.getHist().setInAcctNo(vo.getInAcct());;//帐号
            rgctbill.getHist().setInBankNo(vo.getInBankNo());//开户行行号
            rgctbill.getHist().setInvcNb(vo.getInvcNb());
            rgctbill.getHist().setConferNo(vo.getTxlCtrctNb());
            baseService.commonTransmission(rgctbill, list, MsgUtil.getMethodName(vo.getBusiType(), vo.getRpdMk()));
            //电子票据流转
//            RgctDraftLog orgnlDraftLog = draftService.getRgctDraftLogByReqSid(vo.getOrgnlMsgId());
            billFlowService.executeBillFlowInfo034Trans(rgctbill, vo.getBusiType(), vo.getReqMsgId());
        	addCustBillStorage(rgctbill);	
            session.endTransaction();
        } catch (Exception e) {
        	e.printStackTrace();
            try {
                session.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }
    
    /**
     * 检查帐户信息，如果信息不正确，会发送清分失败报文。
     * @param acctNo
     * @param bankNo
     * @param name
     * @param vo
     */
    public void checkAcctInfo(String acctNo, String bankNo, String name,DraftInfoVo vo) throws Exception{
     /* EC00－无此帐号
        EC01－无此行号
        EC02－名称不符
        EC03－贴现入账信息账号错误
     */
    	IBranchService branchService = ServiceFactory.getBranchService();
        String errorCode="";
        if(StringUtils.isEmpty(acctNo)){
            errorCode="EC00";
        }else if(StringUtils.isEmpty(bankNo)){
            errorCode="EC01";
        }else{
        	Branch brchInfo = branchService.getBranchByBankNo(bankNo);
        	if(brchInfo == null){
        		errorCode="EC01";
        	}
        	if(!"0".equals(acctNo)){
        		String brchId = brchInfo.getBranchId();
        		ICustInfoAcctService custService = ServiceFactory.getCustInfoAcctService();
        		ICustInfoService cusService = ServiceFactory.getCustInfoService();
        		CustInfoAcct acct = custService.getParam(acctNo);
        		if(acct == null){
        			errorCode="EC00";
        		}else{
        			String acctBrchId = branchService.getBranch(acct.getAcctBranchNo()).getParentBrchId();
        			if(!(brchId.equals(acct.getAcctBranchNo())||brchId.equals(acctBrchId))){
        				errorCode="EC00";
        			}else if(name!=null && name.equals(cusService.getParam(acct.getCustNo()).getCustName())){
        				errorCode="EC02";
        			}
        		}
        	}
        }
        if("EC00".equals(errorCode)||"EC01".equals(errorCode)||"EC02".equals(errorCode)||"EC03".equals(errorCode)){
        	BizAppException e = new BizAppException(ISysErrorNo.ERR_DBERR, "由于帐号信息不正确产生了一个清分失败报文，帐号信息：" + acctNo + " 行号:" + bankNo + " 名称:" + name);
        	vo.setExceptionCode(errorCode);
        	vo.setReqMsgId(MsgUtil.getMsgId("035", vo.getFromBankNo()));
        	this.packErrorAnswer(vo, e);
        }
    }   
    
    /**
     * 检查入账帐户信息，如果信息不正确，会发送清分失败报文。
     * @param acctNo
     * @param bankNo
     * @param vo
     */
    public void rejectRequest(String acctNo, String bankNo,DraftInfoVo vo) throws Exception{
    	String errorCode="EC02";
    	if("EC00".equals(errorCode)||"EC01".equals(errorCode)||"EC02".equals(errorCode)||"EC03".equals(errorCode)){
    		BizAppException e = new BizAppException(ISysErrorNo.ERR_DBERR, "由于入账帐号信息不正确产生了一个清分失败报文，帐号信息：" + acctNo + " 行号:" + bankNo);
        	vo.setExceptionCode(errorCode);
        	vo.setReqMsgId(MsgUtil.getMsgId("035", vo.getFromBankNo()));
        	this.packErrorAnswer(vo, e);
        }
    }
    
    /**
     * 检查接收方为银行时的信息，如果信息不正确，会发送清分失败报文。
     * @param acctNo
     * @param bankNo
     * @param name
     * @param vo
     */
    public void checkReceiverAcctInfo(String acctNo, String bankNo, String name,DraftInfoVo vo) throws Exception{
    	IBranchService branchService = ServiceFactory.getBranchService();
        String errorCode="";
        if(StringUtils.isEmpty(acctNo)){
            errorCode="EC00";
        }else if(StringUtils.isEmpty(bankNo)){
            errorCode="EC01";
        }else{
        	Branch brchInfo = branchService.getBranchByBankNo(bankNo);
        	if(brchInfo == null){
        		errorCode="EC01";
        	}
        	if(!"0".equals(acctNo)){
        		errorCode="EC00";
        	}
        }
        if("EC00".equals(errorCode)||"EC01".equals(errorCode)||"EC02".equals(errorCode)||"EC03".equals(errorCode)){
        	BizAppException e = new BizAppException(ISysErrorNo.ERR_DBERR, "由于帐号信息不正确产生了一个清分失败报文，帐号信息：" + acctNo + " 行号:" + bankNo + " 名称:" + name);
        	vo.setExceptionCode(errorCode);
        	vo.setReqMsgId(MsgUtil.getMsgId("035", vo.getFromBankNo()));
        	this.packErrorAnswer(vo, e);
        }
    }
    
    /**
     * 检查入账帐户信息，如果信息不正确，会发送清分失败报文。
     * @param acctNo
     * @param bankNo
     * @param orgNo
     * @param vo
     */
    public boolean checkCustInfo(String acctNo, String bankNo,String orgNo,DraftInfoVo vo) throws Exception{
    	boolean isSuccess = true;
    	ICustInfoService custService = ServiceFactory.getCustInfoService();
    	if(!custService.validateCustInfoByOrgNo(orgNo)){
    		String errorCode="EC02";
    		isSuccess=false;
    		if("EC00".equals(errorCode)||"EC01".equals(errorCode)||"EC02".equals(errorCode)||"EC03".equals(errorCode)){
    			BizAppException e = new BizAppException(ISysErrorNo.ERR_DBERR, "由于入账帐号信息不正确产生了一个清分失败报文，帐号信息：" + acctNo + " 行号:" + bankNo);
            	vo.setExceptionCode(errorCode);
            	vo.setReqMsgId(MsgUtil.getMsgId("035", vo.getFromBankNo()));
            	this.packErrorAnswer(vo, e);
            }
    	}
    	return isSuccess;
    }
    
    /**
	 * 检查帐户信息，如果信息不正确，会发送清分失败报文。
	 * @param vo
	 * @param rgctBill
	 */
    public void checkAcctInfo(DraftInfoVo vos,DraftInfoVo vo) throws Exception{
    	String errorCode="";
    	final String bankNo = vos.getReceiveBankNo();
		final String acctNo = vos.getReceiveAcctNo();
		final String name = vos.getReceiveName();
		ICustInfoAcctService custService = ServiceFactory.getCustInfoAcctService();
		ICustInfoService cusService = ServiceFactory.getCustInfoService();
		CustInfoAcct cust = custService.getParam(acctNo);
		if(cust == null){
			errorCode = "EC00";
		}else if(!bankNo.equals(cust.getAcctBankNo())){
			errorCode = "EC01";
		}else if(!name.equals(cusService.getParam(cust.getCustNo()).getCustName())){
			errorCode = "EC02";
		}
		if("EC00".equals(errorCode)||"EC01".equals(errorCode)||"EC02".equals(errorCode)||"EC03".equals(errorCode)){
			BizAppException e = new BizAppException(ISysErrorNo.ERR_DBERR, "由于帐号信息不正确产生了一个清分失败报文，帐号信息：" + acctNo + " 行号:" + bankNo + " 名称:" + name);
        	vo.setExceptionCode(errorCode);
        	vo.setReqMsgId(MsgUtil.getMsgId("035", vo.getFromBankNo()));
        	this.packErrorAnswer(vo, e);
        }
    }
    
    /**
	 * 检查帐户信息，如果信息不正确，会发送清分失败报文。
	 * @param acctNo
	 * @param bankNo
	 * @param name
	 * @param vo
	 */
    public void checkReqAcctInfo(String acctNo, String bankNo, String name,DraftInfoVo vo) throws Exception{
    	IBranchService branchService = ServiceFactory.getBranchService();
        String errorCode="";
        if(StringUtils.isEmpty(acctNo)){
            errorCode="EC00";
        }else if(StringUtils.isEmpty(bankNo)){
            errorCode="EC01";
        }else{
        	Branch brchInfo = branchService.getBranchByBankNo(bankNo);
        	if(brchInfo == null){
        		errorCode="EC01";
        	}
        	if(!"0".equals(acctNo)){
        		ISignProdService signProdService = ServiceFactory.getSignProdService();
        		SignProd signProd = signProdService.getSignProdByBusAct(IConstants.ELECTRON_SINGPROD,acctNo);
        		if(signProd == null){
        			errorCode="EC00";
        		}else{
        			if(!bankNo.equals(signProd.getRemitterBankNo())){
        				errorCode="EC00";
        			}else if(!name.equals(signProd.getCustName())){
        				errorCode="EC02";
        			}
        		}
        	}
        }
        if("EC00".equals(errorCode)||"EC01".equals(errorCode)||"EC02".equals(errorCode)||"EC03".equals(errorCode)){
        	BizAppException e = new BizAppException(ISysErrorNo.ERR_DBERR, "由于帐号信息不正确产生了一个清分失败报文，帐号信息：" + acctNo + " 行号:" + bankNo + " 名称:" + name);
        	vo.setExceptionCode(errorCode);
        	vo.setReqMsgId(MsgUtil.getMsgId("035", vo.getFromBankNo()));
        	this.packErrorAnswer(vo, e);
        }
    }
    private void addCustBillStorage(RgctBill rgctBill){
  		/**
  		 * 余额类034报文处理
  		 * 如果是我行转发 则不处理
  		 * 如果是他行转发 如果源系统中billStorage存在数据 则不处理 
  		 *             如果该票据是第一次进系统,则新增一条初始化数据
  		 */
  			try {
  				ICustBillStorageService custBillStorageService = ServiceFactory.getCustBillStorageService();
  				if(!CommUtils.isSelfBank(rgctBill.getInfo().getRemitterBankNo()) && !CommUtils.isSelfBank(rgctBill.getHist().getFromBankNo())) {
  					CustBillStorage billStorage = new CustBillStorage();
  					billStorage.setBillNo(rgctBill.getInfo().getBillNo());
  					billStorage.setHoldAcctNo(rgctBill.getHist().getHoldAcctNo());
  					billStorage.setHoldBankNo(rgctBill.getHist().getHoldBankNo());
  					billStorage.setHoldCustName(rgctBill.getHist().getHoldCustName());
  					billStorage.setRgctId(rgctBill.getInfo().getId());
  				custBillStorageService.saveCustBillStorage034(billStorage);
  				}
  			} catch (BizAppException e) {
  				e.printStackTrace();
  			}
  		
  	}
    @Override
    public String getTransName() {
        return "034通用转发";
    }

    @Override
    public String getTransVersion() {
        return "2.0.0.1";
    }
    
    @Override
    public String getServiceId() {
        return "Proc034";
    }

}
