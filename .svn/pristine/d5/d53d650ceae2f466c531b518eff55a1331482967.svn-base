package com.herongtech.rc.trans.trans033;

import java.sql.SQLException;

import org.apache.commons.lang.StringUtils;

import com.herongtech.console.core.constant.DraftConstants;
import com.herongtech.console.core.constant.RcConstants;
import com.herongtech.console.core.util.CommUtils;
import com.herongtech.console.domain.entityregister.bean.EntityRegisterInfo;
import com.herongtech.console.domain.entityregister.dao.EntityRegisterInfoDao;
import com.herongtech.console.service.ServiceFactory;
import com.herongtech.console.web.busicontroller.common.entityRegisterCodeConst;
import com.herongtech.context.Context;
import com.herongtech.context.util.ContextUtil;
import com.herongtech.db.impl.session.DBFactory;
import com.herongtech.db.interfaces.IDB;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.rc.domain.bean.RgctBill;
import com.herongtech.rc.domain.bean.RgctBillInfo;
import com.herongtech.rc.domain.bean.RgctDraftLog;
import com.herongtech.rc.domain.bean.RgctTempHist;
import com.herongtech.rc.domain.dao.RgctTempHistDao;
import com.herongtech.rc.draft.common.MsgUtil;
import com.herongtech.rc.draft.common.vo.DraftInfoVo;
import com.herongtech.rc.draft.common.vo.RequestInfo;
import com.herongtech.rc.service.RcServiceFactory;
import com.herongtech.rc.service.interfaces.IBillFlowInfoService;
import com.herongtech.rc.service.interfaces.ICustBillStorageService;
import com.herongtech.rc.service.interfaces.IRgctDraftLogService;
import com.herongtech.rc.service.rcservice.IRcBaseService;
import com.herongtech.rc.trans.EcdsBaseService;


public class T033Service extends EcdsBaseService {


    @Override
    protected void action(Context context) throws Exception {
        RequestInfo request = (RequestInfo)ContextUtil.getRequestData(context); // 请求数据集合
        DraftInfoVo vo=(DraftInfoVo)request;
        RgctBillInfo bill=vo.getBill();
        IRcBaseService baseService=RcServiceFactory.getRcBaseService();
        IRgctDraftLogService draftService = RcServiceFactory.getRgctDraftLogService();
        String prcCd = vo.getPrcCd();// 报文处理码
        String prcMsg = vo.getPrcMsg();// 处理信息
        String orgnlMsgId = vo.getOrgnlMsgId();// 原申请报文Id
        String curstatus = "0";
        if (DraftConstants.PE1I0000.equals(prcCd)){// ECDS系统正确处理
            curstatus = "1";
        }
        IDB session = DBFactory.getDB();
        try {
            session.beginTransaction();
            RgctDraftLog orgnlDraftLog = draftService.getRgctDraftLogByReqSid(vo.getOrgnlMsgId());
            if (orgnlDraftLog == null) {
                return;
            }
            String busiType=orgnlDraftLog.getDraftNoReq();
            if(StringUtils.isNotBlank(orgnlDraftLog.getRgctId())){
                RgctBill  rcgtbill = baseService.getRgctBillById(orgnlDraftLog.getRgctId());
                RgctTempHistDao tempDao=new RgctTempHistDao();
                String methodName=MsgUtil.getMethodName(busiType, rcgtbill.getHist().getIsRegress());
                if(DraftConstants.DRAFTNO_COMMON_CANCEL.equals(busiType)){
                    methodName=RcConstants.COMMON_DRAWBACK;
                }else if(DraftConstants.DRAFTNO_COMMON_SIGN.equals(busiType)){
                    methodName=RcConstants.COMMON_SIGNUP;
                }else{
                	RgctTempHist tempHist = tempDao.getRgctTempHist(rcgtbill.getInfo().getTempHistId());
                	if(tempHist!=null){
                		if(RcConstants.BUY_INPOOL.equals(tempHist.getBuyType())&& "018".equals(orgnlDraftLog.getDraftNoReq()) ){
                			methodName=RcConstants.COMMON_INPOOL;
                		}
                	}
                }
                IBillFlowInfoService service  = RcServiceFactory.getBillFlowInfoService();
                if(RcConstants.COMMON_SIGNUP.equals(methodName)){
                	System.out.println("031签收"+rcgtbill.getInfo().getBillNo());
                	baseService.commonStatus(rcgtbill, methodName, curstatus, orgnlDraftLog.getReplyFlag(), CommUtils.isSelfBank(orgnlDraftLog.getReqMsgId().substring(0, 12)));
                	if(DraftConstants.PE1I0000.equals(prcCd)){//成功的033记录以下信息
                    	addCustBillStorage(orgnlDraftLog,vo,rcgtbill);
        			}
                	rcgtbill = baseService.getRgctBillById(orgnlDraftLog.getRgctId());
                	if("031".equals(busiType)){
                    	service.executeBillFlowInfo033For031(orgnlDraftLog.getReqMsgId(),curstatus,prcMsg,true,orgnlDraftLog.getReplyFlag(),rcgtbill,orgnlDraftLog);
                	}
//                    }else if("035".equals(busiType)){
//                    	service.executeBillFlowInfo033For035(orgnlDraftLog.getReqMsgId(),curstatus,prcMsg,rcgtbill);                    	
//                    }
                }else{
                    baseService.commonStatus(rcgtbill,methodName , curstatus); 
                    rcgtbill = baseService.getRgctBillById(orgnlDraftLog.getRgctId());
                    if(DraftConstants.DRAFTNO_COMMON_CANCEL.equals(busiType)){
                    	service.executeBillFlowInfo033For032(orgnlDraftLog.getReqMsgId(),curstatus,prcMsg,rcgtbill);    //有系统内  特殊                	
                    }else if("004".equals(busiType)){
                    	service.executeBillFlowInfo033For004(orgnlMsgId,curstatus,prcMsg,rcgtbill,orgnlDraftLog);
                    	updateCustBillStorage(orgnlDraftLog,vo,rcgtbill);
                    }else{
                    	service.executeBillFlowInfo033ForCommonApply(orgnlMsgId,curstatus,prcMsg,rcgtbill,orgnlDraftLog);                    	
                    }
                }
            }else{
            	// 外部系统票据纸票登记
                if("101".equals(busiType)||"102".equals(busiType)||"103".equals(busiType)||"104".equals(busiType)||"105".equals(busiType)||"106".equals(busiType)||"107".equals(busiType)||"108".equals(busiType)||"109".equals(busiType)||"110".equals(busiType)||"111".equals(busiType)||"125".equals(busiType)){
                	EntityRegisterInfoDao registerInfoDao = new EntityRegisterInfoDao();
                	EntityRegisterInfo registInfo = registerInfoDao.getEntityRegisterInfoByMsgId(orgnlDraftLog.getReqSid());
                	if (registInfo != null) {
            			// 登记成功
            			if (DraftConstants.PE1I0000.equals(prcCd)) {
            				registInfo.setReplyFlag("1");//发送状态 0-未回复 1-登记成功 2-登记失败
            				registInfo.setOperStatus(entityRegisterCodeConst.ENTITY_REGISTER_STATUS_120);
            			} else {
            				// 登记失败
            				registInfo.setReplyFlag("2");
            				registInfo.setOperStatus(entityRegisterCodeConst.ENTITY_REGISTER_STATUS_130);
            			}
            			//处理结果
            			registInfo.setErrorReason(prcMsg);
            			registerInfoDao.modifyEntityRegisterInfo(registInfo);
            		}
                }
            }
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
	 * 他行申请我行签收的,并且是签收操作的增加库存
	 * @param draftLog
	 * @param rgctBill
	 */
	private void addCustBillStorage(RgctDraftLog orgnlDraftLog,DraftInfoVo vo, RgctBill rcgtbill) {
		try {
			ICustBillStorageService custBillStorageService = ServiceFactory.getCustBillStorageService();
			if (!CommUtils.isSelfBank(rcgtbill.getHist().getFromBankNo()) && RcConstants.SIGN_YES.equals(orgnlDraftLog.getReplyFlag())) {
				//他行申请，我行同意签收，增加背书历史记录和余额
				custBillStorageService.updateCustBillStorage(rcgtbill.getHist().getRgctId(), orgnlDraftLog.getFromDraftNo(), rcgtbill);
			}
		} catch (BizAppException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 撤票
	 * @param draftLog
	 * @param rgctBill
	 */
	private void updateCustBillStorage(RgctDraftLog orgnlDraftLog,DraftInfoVo vo, RgctBill rcgtbill) {
		try {
			ICustBillStorageService custBillStorageService = ServiceFactory.getCustBillStorageService();
				custBillStorageService.updateCustBillStorage(rcgtbill.getHist().getRgctId(), orgnlDraftLog.getDraftNoReq(), rcgtbill);
		} catch (BizAppException e) {
			e.printStackTrace();
		}
	}
    @Override
    public String getTransName() {
        return "033通用确认";
    }

    @Override
    public String getTransVersion() {
        return "2.0.0.1";
    }
    
    @Override
    public String getServiceId() {
        return "Proc033";
    }
}
