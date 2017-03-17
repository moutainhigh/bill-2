/********************************************
 * 文件名称: XMLEventProcessor.java
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
package com.herongtech.xmlchannel;

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
import com.herongtech.xmlchannel.client.XMLClient;

public class XMLEventProcessor extends AbstractEventProcessor{
	private static Logger logger = LoggerFactory.getLogger(XMLEventProcessor.class);
	
	private XMLProcessor xmlProcessor;
	public XMLProcessor getXmlProcessor() {
		return xmlProcessor;
	}


	public void setXmlProcessor(XMLProcessor xmlProcessor) {
		this.xmlProcessor = xmlProcessor;
	}

	private String routetable;
	
	public void setRoutetable(String routetable) {
		this.routetable = routetable;
	}
	
	
	public String getId() {
		return "xml-host-channel";
		
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
		// 同步调用远程服务器上的一个服务，并得到应答
		String requestXml = event.getServiceRequest().getContext().get(
							Context.REQUEST_PARAM);
		String functionId = event.getServiceRequest().getContext().get("functionId");
		XMLClient client = xmlProcessor.getChannelService().getClient();
		String respXml = client.callRemoteService(requestXml,functionId);
		event.getServiceRequest().getContext().put(Context.RESPONSE_PARAM, respXml);
	}
	
	public void setCepCore(CEPCore arg0) {
		
	}
	
	public boolean isRead() {
		return true;
	}

	public void setRead(boolean read) {
	}

}
