package com.herongtech.rc.trans.trans036;

import java.sql.SQLException;

import com.herongtech.console.core.constant.DraftConstants;
import com.herongtech.console.core.constant.RcConstants;
import com.herongtech.console.core.util.CommUtils;
import com.herongtech.console.service.ServiceFactory;
import com.herongtech.context.Context;
import com.herongtech.context.util.ContextUtil;
import com.herongtech.db.impl.session.DBFactory;
import com.herongtech.db.interfaces.IDB;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.rc.domain.bean.RgctBill;
import com.herongtech.rc.domain.bean.RgctDraftLog;
import com.herongtech.rc.draft.common.MsgUtil;
import com.herongtech.rc.draft.common.vo.DraftInfoVo;
import com.herongtech.rc.draft.common.vo.RequestInfo;
import com.herongtech.rc.service.RcServiceFactory;
import com.herongtech.rc.service.interfaces.IBillFlowInfoService;
import com.herongtech.rc.service.interfaces.ICustBillStorageService;
import com.herongtech.rc.service.interfaces.IRgctDraftLogService;
import com.herongtech.rc.service.rcservice.IRcBaseService;
import com.herongtech.rc.trans.EcdsBaseService;

public class T036Service extends EcdsBaseService {


    @Override
    protected void action(Context context) throws Exception {
    	 IBillFlowInfoService billFlowService=RcServiceFactory.getBillFlowInfoService();
        RequestInfo request = (RequestInfo) ContextUtil.getRequestData(context); // 请求数据集合
        DraftInfoVo vo = (DraftInfoVo) request;
        IRcBaseService rcService = RcServiceFactory.getRcBaseService();
        IRgctDraftLogService draftService = RcServiceFactory.getRgctDraftLogService();
        String SgnUpMk = "0";
        String ecdsresult = "0";
        if(RcConstants.SIGNUP_MARK_CODE1.equals(vo.getSgnUpMk())){
        	SgnUpMk="1";
        }
        if (DraftConstants.PE1I0000.equals(vo.getPrcCd())){// ECDS系统正确处理
        	ecdsresult = "1";
        }
        IDB session = DBFactory.getDB();
        try {
            session.beginTransaction();


            RgctBill rgctBill = rcService.getRgctBillByBillNo(vo.getBill().getBillNo());
//            rgctBill.getHist().setRespDraftId(vo.getReqMsgId());
//            rgctBill.getHist().setReqDraftId(vo.getOrgnlMsgId());
            
            rgctBill.getHist().setPartnerCode(vo.getTrfRef());
            rgctBill.getHist().setPayTradeNo(vo.getTrfId());
//            rgctBill.getHist().setReceiveBankNo(vo.getReceiveBankNo());
            boolean fromIsInner = this.isInner(vo.getFromBankNo());//看报文解析类（CommonSettlementResultNotification036MsgProcessor），对应填入的值
            boolean toIsInner = this.isInner(vo.getReceiveBankNo());
            if (!fromIsInner){//申请方为系统外
                // 签收方收到线上清算 调用与033类似
                String curstatus = rgctBill.getHist().getCurStatus();
                RgctDraftLog draftLog = draftService.getSignDraftLogByReqMsgId(vo.getOrgnlMsgId());
                //添加确认报文结果信息至billHist
                if(draftLog != null) {
                    rgctBill.getHist().setDraftInfo(draftLog.getDraftInfo());
                    MsgUtil.getMethodName(draftLog.getFromDraftNo(), rgctBill.getHist().getIsRegress());
                }
    
                if (!DraftConstants.PE1I0000.equals(vo.getPrcCd())) {// 失败
                    curstatus = "0";
    
                } else {
                    if (RcConstants.SIGN_NO.equals(draftLog.getReplyFlag())) {
                        curstatus = "11";
                        if ("020".equals(draftLog.getFromDraftNo())) {
                            curstatus = "12";
                        }
                        if ("021".equals(draftLog.getFromDraftNo())) {
                            curstatus = "13";
                        }
                    }
                }
                rcService.commonStatus(rgctBill, RcConstants.COMMON_SIGNUP, curstatus,draftLog.getReplyFlag(),fromIsInner);
                RgctBill bill = rcService.getRgctBillById(rgctBill.getInfo().getId());
                bill.getHist().setPartnerCode(vo.getTrfRef());
                bill.getHist().setPayTradeNo(vo.getTrfId());
                rcService.updateRgctBillHist(bill.getHist());
                RgctBill  rcgtbill = rcService.getRgctBillById(rgctBill.getInfo().getId());
                billFlowService.executeBillFlowInfo033For031(draftLog.getReqMsgId(),ecdsresult, vo.getPrcMsg(),true,draftLog.getReplyFlag(),rcgtbill,draftLog);
            }else if (!toIsInner){//签收方为系统外
                //申请方收到线上清算， 调用与031类似
                RgctDraftLog draftLog = draftService.getDraftLogByReqSid(vo.getOrgnlMsgId());
                String bustType = MsgUtil.getMethodName(draftLog.getDraftNoReq(), rgctBill
                        .getHist().getIsRegress());
                if (DraftConstants.PE1I0000.equals(vo.getPrcCd())) {
                    // 同意签收
                    rcService.commonSignUp(rgctBill, bustType, RcConstants.SIGN_YES);
                } else {
                    // 拒绝签收
                    rcService.commonSignUp(rgctBill, bustType, RcConstants.SIGN_NO);
                }
                billFlowService.executeBillFlowInfo031Trans(vo.getOrgnlMsgId(), SgnUpMk, rgctBill);
            }
            
            addCustBillStorage(rgctBill,vo.getPrcCd(),vo);
            session.endTransaction();
        } catch (Exception e) {
            try {
                session.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }
    private void addCustBillStorage(RgctBill rgctBill,String prcCd,DraftInfoVo vo) {
			  try {
				  IRgctDraftLogService draftService = RcServiceFactory.getRgctDraftLogService();
				  RgctDraftLog draftLog = null;
				  if (vo.getToBankNo().equals(vo.getFromBankNo())) { // 查询原申请报文
						draftLog = draftService.getDraftLogByReqSid(vo.getOrgnlMsgId());
						if (draftLog == null || draftLog.getRgctId() == null) {
							return;
						} else {
							processOrginReqDraft(rgctBill,prcCd, draftLog);
						}
				  } else if (vo.getToBankNo().equals(vo.getReceiveBankNo())) { // 查询031签收报文
					  draftLog = draftService.getSignDraftLogByReqMsgId(vo.getOrgnlMsgId());
					  if (draftLog == null || draftLog.getRgctId() == null) {
						  return;
					  } else {
						  process031Draft(rgctBill,prcCd, draftLog);
					  }
				  }
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
    
    private void processOrginReqDraft(RgctBill rgctBill,String prcCd, RgctDraftLog draftLog) {
				try {
					if("011".equals(draftLog.getDraftNoReq()) || "013".equals(draftLog.getDraftNoReq())
							|| "020".equals(draftLog.getDraftNoReq()) || "021".equals(draftLog.getDraftNoReq())) {
						// 申请方为我行 签收方收到线上清算 调用与031类似
						if (!CommUtils.isSelfBank(rgctBill.getHist().getFromBankNo())) {
							return;
						}
						if(!DraftConstants.PE1I0000.equals(prcCd)) { //如果失败
							return;
						}
						/**
						 * 余额类查询
						 * 处理规则－－申请方是我行，我行会收到033和031,登记中心在收到031时进行状态变更，所以余额类处理转发的031报文
						 *		     申请方是他行，则余额类处理031的033回复报文 
						 */
						ICustBillStorageService custBillStorageService = ServiceFactory.getCustBillStorageService();
						if(CommUtils.isSelfBank(rgctBill.getHist().getFromBankNo()) && DraftConstants.PE1I0000.equals(prcCd)) { //我行申请，我行或他行同意签收
					custBillStorageService.updateCustBillStorage(rgctBill.getHist().getRgctId(), draftLog.getDraftNoReq(), rgctBill);
						}
					  }
					} catch (BizAppException e) {
					e.printStackTrace();
				}
	}
    private void process031Draft(RgctBill rgctBill,String prcCd, RgctDraftLog draftLog) {
			try {
				if("031".equals(draftLog.getDraftNoReq())) {
					// 申请方为系统外 签收方我行收到线上清算 调用与033类似（由于我行系统不支持线上清算，所以这种情况实际不会发生）
					// 由于贴现回复CommonSignUpDscnt类中，getMethodName=COMMON_DISCOUNT2，无法区分是否为回购式；以后考虑检查登记中心
					// 同033处理
					if(!DraftConstants.PE1I0000.equals(prcCd)) { //他行申请，我行同意签收，增加背书历史记录和余额
						return;
					}
				if (MsgUtil.isSelfBank(rgctBill.getHist().getFromBankNo())) {
					return;
				}
				ICustBillStorageService custBillStorageService = ServiceFactory.getCustBillStorageService();
				custBillStorageService.updateCustBillStorage(rgctBill.getHist().getRgctId(), draftLog.getFromDraftNo(), rgctBill);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
    @Override
    public String getTransName() {
        return "036线上清算结果通知";
    }

    @Override
    public String getTransVersion() {
        return "2.0.0.1";
    }
    
    @Override
    public String getServiceId() {
        return "Proc036";
    }
    
    private boolean isInner(String bankNo)throws Exception {
        return MsgUtil.isSelfBank(bankNo);
    }

}
