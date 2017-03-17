/********************************************
 * 文件名称: RcServiceFactory.java
 * 系统名称: 基础技术平台V2.0
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: 2.0.0.1
 * 开发人员: yanjl
 * 开发时间: 2016-6-15 下午01:48:52
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期    修改人员    修改说明
 *********************************************/

package com.herongtech.rc.service.sendecds;

import java.util.Date;

import javax.jms.Destination;

import com.herongtech.commons.tools.StringUtils;
import com.herongtech.console.core.util.SendEventClient;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.rc.domain.bean.DraftMapping;
import com.herongtech.rc.domain.bean.EcdsApData;
import com.herongtech.rc.domain.bean.EcdsBankData;
import com.herongtech.rc.domain.bean.RgctBillInfo;
import com.herongtech.rc.draft.common.vo.DraftInfoVo;
import com.herongtech.rc.draft.common.vo.DraftSender;
import com.herongtech.rc.draft.common.vo.RequestInfo;
import com.herongtech.rc.draft.msgBuilder.AbstractDraftBuilder;
import com.herongtech.rc.draft.msgBuilder.requestBuilder.CommercialDraftRegisterRequestDraftBuilder;
import com.herongtech.rc.service.RcServiceFactory;
import com.herongtech.rc.service.interfaces.IEcdsApDataService;
import com.herongtech.rc.service.interfaces.IEcdsBankDataService;
import com.herongtech.rc.service.interfaces.ISendEcdsService;


public class SendEcdsService implements ISendEcdsService{
    
	public void pack001(RequestInfo reqInfo, String methodName) throws Exception{
//        DraftMapping draftMapping = getDraftMapping(methodName);
//        DraftSender draftSender = createDaraftSender(reqInfo, draftMapping);
//        AbstractDraftBuilder builder = RcServiceFactory.getDraftBuilderService(draftSender.getDraftBuilder());
//        String draft = builder.assembleDraft(draftSender);
//        Destination dest = null;//draftSender.getQueueName();
//        
//        SendEventClient.send("ECDS054", draft);
    }
	
	
	public void send001(RequestInfo reqInfo, String methodName) throws Exception{
//        DraftMapping draftMapping = getDraftMapping(methodName);
//        DraftSender draftSender = createDaraftSender(reqInfo, draftMapping);
//        AbstractDraftBuilder builder = RcServiceFactory.getDraftBuilderService(draftSender.getDraftBuilder());
//        String draft = builder.assembleDraft(draftSender);
//        Destination dest = null;//draftSender.getQueueName();
//        
//        SendEventClient.send("054", draft);
    }

    private DraftMapping getDraftMapping(String methodName){
        //从缓存取报文映射信息
        return null;
    }
    
    private DraftSender createDaraftSender(RequestInfo reqInfo, DraftMapping draftMapping) throws Exception{
        IEcdsBankDataService ecdsbankService = RcServiceFactory.getEcdsBankDataService();
        IEcdsApDataService ecdsapService = RcServiceFactory.getEcdsApDataService();
        
        DraftSender sender = new DraftSender();
        sender.setMesgType(draftMapping.getDraftNo());
        sender.setDraftBuilder(draftMapping.getDraftBuilder());
        sender.setIsAddSign(draftMapping.getIsAddSign());
        DraftInfoVo vo = (DraftInfoVo)reqInfo;
        String fromBankNo = vo.getFromBankNo();
        String receiveBankNo = vo.getReceiveBankNo();
        EcdsBankData fromBank = ecdsbankService.getEcdsBankData(fromBankNo);
        if(fromBank == null){
            throw new BizAppException("报文发起方行号不存在");
        }
        String origSender=fromBank.getMeetIncomeCode();
        if(StringUtils.isEmpty(origSender)){
            throw new BizAppException("报文发起方接入点号不存在");
        }
        String senderId = origSender;
        EcdsApData ecdsApData = ecdsapService.getEcdsApData(origSender);
        if(ecdsApData == null){
            throw new BizAppException("ECDS基础数据表中找不到本行的接入点信息");
        }
        String receiveID = ecdsApData.getNonceCcpc();
        sender.setSenderId(senderId);
        sender.setReceiverId(receiveID);
        sender.setOrigSender(origSender);
        
        
        EcdsBankData receiveBank = ecdsbankService.getEcdsBankData(receiveBankNo);
        if (receiveBank == null){
            throw new BizAppException("报文接收方行号不存在");
        }
        String origReceiver=receiveBank.getMeetIncomeCode();
        if (StringUtils.isEmpty(origReceiver)){
            throw new BizAppException("报文接收方接入点号不存在");
        }
        sender.setOrigReceiver(origReceiver);
        sender.setMesgRefId(null);
        
        return sender;
    }
    
}
