package com.herongtech.mqchannel.server;

import com.herongtech.beancontainer.BeanContainerFactory;
import com.herongtech.context.Context;
import com.herongtech.context.impl.ContextImpl;
import com.herongtech.context.util.ContextUtil;
import com.herongtech.event.Event;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.rc.domain.bean.RgctExceptionDraft;
import com.herongtech.rc.draft.common.vo.RequestInfo;
import com.herongtech.rc.service.RcServiceFactory;
import com.herongtech.rc.service.interfaces.IRgctExceptionDraftService;
import com.herongtech.service.IServiceClient;

public class MqTask {
	
	String requestMsg = "";
	
	public void setRequestMsg(String requestMsg){
		this.requestMsg = requestMsg;
	}
	
	public void dealRequest(){
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		// 同步调用 CEP平台中的一个服务
		    //1、解析报文获取报文对象
		    RequestInfo request = null;
		    try {
		        request = RcServiceFactory.getDraftService().parseMessage(requestMsg);
	            if(request == null){
	                return;   
	            }
            } catch (Exception e) {
                //解析报文出错时将报文存入异常报文信息表
                IRgctExceptionDraftService exptService = RcServiceFactory.getRgctExceptionDraftService();
                String draftHead=requestMsg.substring(0, 200);
                String draftNo=draftHead.substring(87,90);
                String origSender=draftHead.substring(49, 61).trim();
                String OrigSendDate=draftHead.substring(73, 81).trim();
                String mesgID=draftHead.substring(107,127);
                RgctExceptionDraft exptDraft = new RgctExceptionDraft();
                exptDraft.setMsgSid(origSender+OrigSendDate+mesgID);
                exptDraft.setDraftNo(draftNo);
                exptDraft.setInOut("1");
                exptDraft.setStatus("0");
                exptDraft.setDraftTxt(requestMsg);
                try {
                    exptService.addRgctExceptionDraft(exptDraft);
                } catch (BizAppException e1) {
                    e1.printStackTrace();
                }
            }
		   
		    //2、业务逻辑处理
			Context context = new ContextImpl();
			ContextUtil.setRequestData(context, request);
			ContextUtil.setContextAttribute(context, "draftContent", requestMsg);
			Event event = Event.createEvent(request.getServiceId(), context);
			//获取本地服务处理通道
			IServiceClient serviceClient = BeanContainerFactory.getBeanContainer(
			this.getClass().getClassLoader()).getBean("localServiceClient");
			//获取应答事件
			serviceClient.send(event);
	}
}
