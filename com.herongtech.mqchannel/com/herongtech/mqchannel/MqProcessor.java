/********************************************
 * 文件名称: MqProcessor.java
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

import com.herongtech.application.Application;
import com.herongtech.application.ApplicationProcessor;
import com.herongtech.cepcore.CEPCore;
import com.herongtech.cepcore.EventProcessor;
import com.herongtech.exception.impl.BizBaseException;
import com.herongtech.logger.LogLevel;
import com.herongtech.logger.Logger;
import com.herongtech.logger.LoggerFactory;
import com.herongtech.xmlparser.node.XmlNode;

public class MqProcessor implements ApplicationProcessor
{
	private static Logger logger = LoggerFactory.getLogger(MqProcessor.class);
	
	private EventProcessor eventProcessor = new MqEventProcessor();
	
	private  String configNodePath = "mqchannel-config";
	public String getConfigNodePath() {
		return configNodePath;
	}

	public void setConfigNodePath(String configNodePath) {
		this.configNodePath = configNodePath;
	}
	
	private CEPCore cepcore;
	
	public CEPCore getCepcore() {
		return cepcore;
	}

	public void setCepcore(CEPCore cepcore) {
		this.cepcore = cepcore;
	}

	private XmlNode applicationConfig;
	private MqChannelService channelService = null;

	public MqChannelService getChannelService() {
		return channelService;
	}

	public void setChannelService(MqChannelService channelService) {
		this.channelService = channelService;
	}

	public String getApplicationNodePath() {
		return "/application/"+configNodePath;
	}

	public String getComponentConfigPath() {
		return null;
	}

	public void config(XmlNode applicationConfig, XmlNode componentConfig) {
		this.applicationConfig = applicationConfig;
	}

	public XmlNode getComponentConfig() {
		return null;
	}

	public XmlNode getApplicationConfig() {
		return applicationConfig;
	}

	public void start() throws BizBaseException{
		
		channelService.start();
		cepcore.registerEventProcessor(eventProcessor);
		((MqEventProcessor)eventProcessor).setMqProcessor(this);
		
		logger.logMessage(LogLevel.INFO, "processor [MqProcessor] state [started]." );
	}

	public void stop() {
		if (channelService != null)	{
			channelService.stop();
		}
		cepcore.unregisterEventProcessor(eventProcessor);
		
		logger.logMessage(LogLevel.INFO, "processor [MqProcessor] state [stop]." );
	}

	public void setApplication(Application application) {
		// Nothing
		
	}

	public void init(){
		
		XmlNode args = applicationConfig.getSubNode("args");
		
		if ((args!=null) && (args.getAttribute("routetable") != null) ){
			((MqEventProcessor) eventProcessor).setRoutetable(args
					.getAttribute("routetable"));
		} else {
			logger.errorMessage("[routetable] 路由参数不允许配置为空 !");
		}
		
		channelService = new MqChannelService();
		channelService.init(applicationConfig);
	}

	public int getOrder() {
		return 0;
	}

}
