package com.herongtech.rc.draft;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.xmlbeans.XmlCursor;
import org.apache.xmlbeans.XmlObject;

import com.herongtech.commons.tools.StringUtils;
import com.herongtech.console.core.constant.DraftConstants;
import com.herongtech.console.core.constant.IDict;
import com.herongtech.console.core.util.SendEventClient;
import com.herongtech.console.service.ServiceFactory;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.rc.domain.bean.DraftMapping;
import com.herongtech.rc.domain.bean.EcdsApData;
import com.herongtech.rc.domain.bean.EcdsBankData;
import com.herongtech.rc.domain.bean.RgctBillInfo;
import com.herongtech.rc.domain.bean.RgctDraftLog;
import com.herongtech.rc.domain.bean.RgctMethod;
import com.herongtech.rc.draft.common.vo.DraftInfoVo;
import com.herongtech.rc.draft.common.vo.DraftSender;
import com.herongtech.rc.draft.common.vo.RequestInfo;
import com.herongtech.rc.draft.msgBuilder.AbstractDraftBuilder;
import com.herongtech.rc.draft.msgProcessor.AbstractDraftMsgProcessor;
import com.herongtech.rc.draft.msgProcessor.DraftMessage;
import com.herongtech.rc.service.RcServiceFactory;
import com.herongtech.rc.service.interfaces.IEcdsApDataService;
import com.herongtech.rc.service.interfaces.IEcdsBankDataService;
import com.herongtech.rc.service.interfaces.IRgctDraftLogService;


public class DraftService {
    
    /**
     * 报文构造入口
     * @param reqInfo
     * @param methodName
     * @throws Exception
     */
    public String buildAndSend(RequestInfo reqInfo,RgctMethod method) throws Exception{
        DraftMapping draftMapping=getDraftMapping(method);
        DraftSender draftSender=createDaraftSender(reqInfo,draftMapping);
        
        AbstractDraftBuilder builder=(AbstractDraftBuilder)Class.forName(
                "com.herongtech.rc.draft.msgBuilder."+ draftSender.getDraftBuilder()).newInstance();
        String draft = builder.assembleDraft(draftSender);
        //记录报文日志
        RgctBillInfo bill=null;
        String fromBankNo="";
        if(reqInfo instanceof DraftInfoVo){
            DraftInfoVo vo=(DraftInfoVo)reqInfo;
            fromBankNo=vo.getFromBankNo();
            bill=vo.getBill();
        }
       String logId = saveDraftLog(reqInfo,bill,draftMapping.getDraftNo(),draft,fromBankNo);
       System.out.println("报文==="+draft);
        //发送报文
        SendEventClient.send("ECDS"+draftMapping.getIsRealTime()+draftMapping.getDraftNo(), draft);
        return logId;
    }

    private DraftMapping getDraftMapping(RgctMethod method) throws BizAppException{
        //从缓存取报文映射信息
        return RcServiceFactory.getDraftMappingService().getDraftMapping(method.getId()+"", method.getParam());
    }
    
    private DraftSender createDaraftSender(RequestInfo reqInfo,DraftMapping draftMapping) throws Exception{
        String fromBankNo=reqInfo.getFromBankNo();
        String receiveBankNo=reqInfo.getReceiveBankNo();
        
        RcServiceFactory.getRgctEcdsStatusService().isCanSent(fromBankNo, draftMapping);
        
        IEcdsBankDataService ecdsbankService=RcServiceFactory.getEcdsBankDataService();
        IEcdsApDataService ecdsapService=RcServiceFactory.getEcdsApDataService();
        DraftSender sender=new DraftSender();
        sender.setMesgType(draftMapping.getDraftNo());
        sender.setDraftBuilder(draftMapping.getDraftBuilder());
        sender.setIsAddSign(draftMapping.getIsAddSign());
        sender.setReqInfo(reqInfo);
        sender.setMesgId(reqInfo.getReqMsgId());
        sender.setMesgRefId(null);
        
        
        EcdsBankData fromBank=ecdsbankService.getEcdsBankData(fromBankNo);
        if(fromBank == null){
            throw new BizAppException("报文发起方行号不存在");
        }
        String origSender=fromBank.getMeetIncomeCode(); 
        if(StringUtils.isEmpty(origSender)){
            throw new BizAppException("报文发起方接入点号不存在");
        }
        String senderId=origSender;
        EcdsApData ecdsApData=ecdsapService.getEcdsApData(origSender);
        if(ecdsApData == null){
            throw new BizAppException("ECDS基础数据表中找不到本行的接入点信息");
        }
        String receiveID=ecdsApData.getNonceCcpc();
        sender.setSenderId(senderId);
        sender.setReceiverId(receiveID);
        sender.setOrigSender(origSender);
        
        if(reqInfo instanceof DraftInfoVo){
            if(!"9968".equals(receiveBankNo)){//纸票登记（DraftInfoVo）特殊处理
             EcdsBankData receiveBank=ecdsbankService.getEcdsBankData(receiveBankNo);
             if(receiveBank == null){
              throw new BizAppException("报文接收方行号不存在");
             }
             String origReceiver=receiveBank.getMeetIncomeCode();
             if(StringUtils.isEmpty(origReceiver)){
              throw new BizAppException("报文接收方接入点号不存在");
             }
             sender.setOrigReceiver(origReceiver);
            }
           }else{//系统管理类 接收方默认为9968
//               SysMgrInfoVo sysVo=(SysMgrInfoVo)reqInfo;
           }
           return sender;
       }
//        if(reqInfo instanceof DraftInfoVo){
//            EcdsBankData receiveBank=ecdsbankService.getEcdsBankData(receiveBankNo);
//            if(receiveBank == null){
//                throw new BizAppException("报文接收方行号不存在");
//            }
//            String origReceiver=receiveBank.getMeetIncomeCode();
//            if(StringUtils.isEmpty(origReceiver)){
//                throw new BizAppException("报文接收方接入点号不存在");
//            }
//            sender.setOrigReceiver(origReceiver);
//        }else{//系统管理类 接收方默认为9968
////            SysMgrInfoVo sysVo=(SysMgrInfoVo)reqInfo;
//        }
//        return sender;
//    }
    
    private String saveDraftLog(RequestInfo reqInfo,RgctBillInfo bill,String msgType,String msg,String origSender) throws BizAppException{
        IRgctDraftLogService draftLogService = RcServiceFactory.getRgctDraftLogService();
        //新的报文日志
        RgctDraftLog draftLog = new RgctDraftLog();
        //023,034的原申请报文日志
        RgctDraftLog originaDraftLog = null;
        // 如果是针对023发送031通用回得报文，那么就将上次收到023报文的信息作为此回日志的接收报文部分信息;
        // 如果是针对034发送031通用回得报文，那么就将上次收到034报文的信息作为此回日志的接收报文部分信息;
        // 如果是针对034发送035通用回得报文，那么就将上次收到034报文的信息作为此回日志的接收报文部分信息;
        // 其它报文为申请报文,只存发送那部分信息;
        if (msgType.equals("031")||msgType.equals("035")) {
            //上次接收的原报文ID
            String originalId = reqInfo.getOrgnlMsgId();
            //针对034的原报文ID
            originaDraftLog = draftLogService.getDraftLogByReqMsgId(originalId);
            if (originaDraftLog == null) {
                //针对023的原报文ID
                originaDraftLog = draftLogService.getDraftLogByRespSid(originalId);
            } 
            if (originaDraftLog == null) {
                
            }
        }
        
        if("032".equals(msgType)){//通用撤回，匹配原申请报文
            String originalId = reqInfo.getOrgnlMsgId();
            draftLog.setReqMsgId(originalId);
        }
        
        //如果有上次接收的原报文
        if(originaDraftLog != null){
            try {
                PropertyUtils.copyProperties(draftLog, originaDraftLog);
            } catch (Exception e) {
                throw new BizAppException("原报文信息保存在此回日志中失败");
            }
            draftLog.setLogId(null);
        }

        if (bill != null) {
            // 登记中心ID
            draftLog.setRgctId(bill.getId());
            // 登记中心历史ID
            draftLog.setRgctHistId(bill.getHistId());
            // 票据号码
            draftLog.setBillNo(bill.getBillNo());
        }

        String reqMsg = msg;

        draftLog.setReqDraft(reqMsg);

        // 请求报文编号
        draftLog.setDraftNoReq(msgType);
        if(!"032".equals(msgType) && !"031".equals(msgType)  ){//针对背书流转类的请求 FromDraftNo特殊处理
        	draftLog.setFromDraftNo(msgType);
        }
        // 请求报文标识号
        String reqSid = reqInfo.getReqMsgId();
        draftLog.setReqSid(reqSid);
        
        // 请求报文发送时间
        String reqDatetime = reqSid.substring(12, 20);
//        Date date = MsgUtil.converISODateTime(reqDatetime,null);
        draftLog.setReqDt(reqDatetime);

        // 请求行行号
        draftLog.setReqBankNo(origSender);

        // 报文发送标记
        draftLog.setInOut(IDict.K_DRAFTLOG.K_DRAFTLOG_OUT);
        String isAgree="";
        if(reqInfo instanceof DraftInfoVo){
            isAgree=DraftConstants.RGCT_CODE_MAPPING_MAP.get(((DraftInfoVo)reqInfo).getSgnUpMk());
        }
        // 是否同意
        draftLog.setReplyFlag(isAgree);

        // 是否需要核对
//        draftLog.setIsCheckDraft(isCheckFlag);
        
        // 报文发送失败标记
        draftLog.setSendFlag(IDict.K_DRAFTLOG_SEND.K_DRAFTLOG_SEND_FALSE);
        
        // 报文处理失败
        draftLog.setProcessFlag(IDict.K_DRAFTLOG_PROCESS.K_DRAFTLOG_PROCESS_FALSE);
        String logId = ServiceFactory.getSequenceService().getRcLogSerialNo();
        draftLog.setLogId(logId);
        draftLogService.addRgctDraftLog(draftLog);
        
        
        return logId;
    }
    
    /**
     * 报文解析入口
     * @param draft
     * @return
     * @throws Exception
     */
    public RequestInfo parseMessage(String draft)throws Exception{
        DraftMessage message=createDraftMessage(draft);
        
        AbstractDraftMsgProcessor processor=(AbstractDraftMsgProcessor) Class.forName("com.herongtech.rc.draft.msgProcessor."+message.getMsgName()+"Processor").newInstance();
        
        return processor.processMsg(message);
    }
    
    private DraftMessage createDraftMessage(String draft){
        DraftMessage message=new DraftMessage();
        String content = draft.trim();   
        if (content.startsWith(DraftConstants.DRAFT_HEAD_PREFIX)) {
            message.setMsgHead(content.substring(0, 200));
            message.setMsgBody(content.substring(200));
            message.setMsgNo(content.substring(87,90));
        } else {
            message.setMsgBody(content);
        }
        
        InputStream input=null;
        try {
             input = new ByteArrayInputStream(
                    message.getMsgBody().getBytes(DraftConstants.ENCODING));
            XmlObject a = XmlObject.Factory.parse(input);
            XmlCursor cursor = a.newCursor();
            cursor.toFirstChild();
            cursor.toFirstChild();
            String msgName = cursor.getName().toString();
            message.setMsgName(msgName);
            
         } catch (Exception e) {
             e.printStackTrace();
         }finally{
             org.apache.commons.io.IOUtils.closeQuietly(input);
         }
        
        return message;
    }
    
}
