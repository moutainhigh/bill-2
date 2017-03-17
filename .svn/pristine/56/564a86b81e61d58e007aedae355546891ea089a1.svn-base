package com.herongtech.corechannel;

import com.herongtech.application.Application;
import com.herongtech.application.ApplicationProcessor;
import com.herongtech.cepcore.CEPCore;
import com.herongtech.cepcore.EventProcessor;
import com.herongtech.exception.impl.BizBaseException;
import com.herongtech.logger.LogLevel;
import com.herongtech.logger.Logger;
import com.herongtech.logger.LoggerFactory;
import com.herongtech.xmlparser.node.XmlNode;


public class CoreProcessor implements ApplicationProcessor {
    
    private CEPCore cepcore;
    private String configNodePath = "corechannel-config";//pose配置文件中的标签
    private EventProcessor eventProcessor = new CoreEventProcessor();
    private static Logger logger = LoggerFactory.getLogger(CoreProcessor.class);
    private XmlNode applicationConfig;
    private CoreChannelService channelService = null;
    
    public CEPCore getCepcore() {
        return cepcore;
    }

    public void setCepcore(CEPCore cepcore) {
        this.cepcore = cepcore;
    }
    
    
    public String getConfigNodePath() {
        return configNodePath;
    }

    
    public void setConfigNodePath(String configNodePath) {
        this.configNodePath = configNodePath;
    }

    public CoreChannelService getChannelService() {
        return channelService;
    }

    
    public void setChannelService(CoreChannelService channelService) {
        this.channelService = channelService;
    }

    @Override
    public void config(XmlNode applicationConfig, XmlNode arg1) {
        this.applicationConfig = applicationConfig;
    }

    @Override
    public XmlNode getApplicationConfig() {
        return applicationConfig;
    }

    @Override
    public String getApplicationNodePath() {
        return "/application/"+configNodePath;
    }

    @Override
    public XmlNode getComponentConfig() {
        return null;
    }

    @Override
    public String getComponentConfigPath() {
        return null;
    }

    @Override
    public int getOrder() {
        return 0;
    }

    @Override
    public void start() throws BizBaseException {
        cepcore.registerEventProcessor(eventProcessor);
        ((CoreEventProcessor)eventProcessor).setCoreProcessor(this);
        
        logger.logMessage(LogLevel.INFO, "processor [CoreProcessor] state [started]." );

    }

    @Override
    public void init() {
        XmlNode args = applicationConfig.getSubNode("args");
        if ((args!=null) && (args.getAttribute("routetable") != null) ){
            ((CoreEventProcessor) eventProcessor).setRoutetable(args
                    .getAttribute("routetable"));
        } else {
            logger.errorMessage("[routetable] does not allow for a null!");
        }
        
        
        channelService = new CoreChannelService();
        channelService.init(applicationConfig);
    }

    @Override
    public void stop() {
        cepcore.unregisterEventProcessor(eventProcessor);
        logger.logMessage(LogLevel.INFO, "processor [CoreProcessor] state [stop]." );
    }

    @Override
    public void setApplication(Application application) {

    }

}
