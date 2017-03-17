/********************************************
 * 文件名称: XMLProcessor.java
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


import com.herongtech.application.Application;
import com.herongtech.application.ApplicationProcessor;
import com.herongtech.cepcore.CEPCore;
import com.herongtech.cepcore.EventProcessor;
import com.herongtech.logger.LogLevel;
import com.herongtech.logger.Logger;
import com.herongtech.logger.LoggerFactory;
import com.herongtech.xmlparser.node.XmlNode;
import com.herongtech.xmlchannel.XMLChannelService;

public class XMLProcessor implements ApplicationProcessor
{
	
	private   String configNodePath = "xmlchannel-config";
	
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
	
	private EventProcessor eventProcessor = new XMLEventProcessor();

	private static Logger logger = LoggerFactory.getLogger(XMLProcessor.class);

	private XmlNode applicationConfig;
	private XMLChannelService channelService = null;

	public XMLChannelService getChannelService() {
		return channelService;
	}

	public void setChannelService(XMLChannelService channelService) {
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

	public void start() {
		
		channelService.start();
		cepcore.registerEventProcessor(eventProcessor);
		((XMLEventProcessor)eventProcessor).setXmlProcessor(this);
		
		logger.logMessage(LogLevel.INFO, "processor [XMLProcessor] state [started]." );
	}

	public void stop() {
		if (channelService != null)	{
			channelService.stop();
		}
		cepcore.unregisterEventProcessor(eventProcessor);
		logger.logMessage(LogLevel.INFO, "processor [XMLProcessor] state [stop]." );
	}

	public void setApplication(Application application) {
		// Nothing
		
	}

	public void init() {
		XmlNode args = applicationConfig.getSubNode("args");
		if ((args!=null) && (args.getAttribute("routetable") != null) ){
			((XMLEventProcessor) eventProcessor).setRoutetable(args
					.getAttribute("routetable"));
		} else {
			logger.errorMessage("[routetable] does not allow for a null!");
		}
		
		
		channelService = new XMLChannelService();
		channelService.init(applicationConfig);
	}

	public int getOrder() {
		return 0;
	}

}
