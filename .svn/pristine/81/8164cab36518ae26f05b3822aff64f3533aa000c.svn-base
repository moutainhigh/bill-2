/********************************************
 * 文件名称: MqEventProcessor.java
 * 系统名称: 合融技术平台
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: 2.0.0.1
 * 开发人员: 
 * 开发时间: 
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期    修改人员    修改说明
 *********************************************/
package com.herongtech.mqchannel;

import java.util.ArrayList;
import java.util.List;

import com.herongtech.cepcore.CEPCore;
import com.herongtech.cepcore.impl.AbstractEventProcessor;
import com.herongtech.commons.tools.RegexUtil;
import com.herongtech.context.Context;
import com.herongtech.event.Event;
import com.herongtech.event.ServiceInfo;
import com.herongtech.logger.Logger;
import com.herongtech.logger.LoggerFactory;
import com.herongtech.mqchannel.client.MessageJmsClient;
import com.herongtech.data.interfaces.data.IData;

public class MqEventProcessor extends AbstractEventProcessor{
	
	private static Logger logger = LoggerFactory.getLogger(MqEventProcessor.class);
	
	private MqProcessor mqProcessor;
	public MqProcessor getMqProcessor() {
		return mqProcessor;
	}


	public void setMqProcessor(MqProcessor mqProcessor) {
		this.mqProcessor = mqProcessor;
	}

	private String routetable;
	
	public void setRoutetable(String routetable) {
		this.routetable = routetable;
	}
	
	
	public String getId() {
		return "mq-channel";
		
	}

	public List<String> getRegex() {
		List  list = new ArrayList();
		String[] routetables = routetable.split(";");
		for (String route:routetables){
			list.add(RegexUtil.getRegexString(route));		
		}
		return list;
	}

	public List<ServiceInfo> getServiceInfos() {
		return new ArrayList();
	}

	public int getType() {
		return TYPE_LOCAL;
	}

	public int getWeight() {
		return 0;
	}

	public void process(Event event) {
		//同步调用远程服务器上的一个服务，并得到应答
		String draftContent = event.getServiceRequest().getContext().get(
							Context.REQUEST_PARAM);
		
		MessageJmsClient client = null;
		//非实时mq
		if (event.getServiceRequest().getServiceId().startsWith("ECDS0")){
			client = mqProcessor.getChannelService().getClient();
		} else{    //否则发送到实时mq
			client = mqProcessor.getChannelService().getRealTimeClient();
		}
		
		client.callRemoteService(draftContent);
	}

	public void setCepCore(CEPCore arg0) {
		
	}
	
	public boolean isRead() {
		return true;
	}

	public void setRead(boolean read) {
	}

}
