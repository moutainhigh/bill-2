package com.herongtech.corechannel;

import com.herongtech.appconfig.SysConfigUtil;
import com.herongtech.corechannel.client.CoreClient;
import com.herongtech.logger.Logger;
import com.herongtech.logger.LoggerFactory;
import com.herongtech.xmlparser.node.XmlNode;


public class CoreChannelService {

    private static Logger logger = LoggerFactory.getLogger(CoreChannelService.class);

    /**
     * 通讯超时时间
     */
    private int timeout = 3000;
    /**
     * 远程IP地址
     */
    private String remoteIP = null;

    /**
     * 远程端口
     */
    private int remotePort = 7800;

   
    /**
     * xml客户端
     */
    private CoreClient client = null;

    public CoreClient getClient() {
        return client;
    }

   
    
    private String charset;
    
    
    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }


    /**
     * 在这里做一些初始化工作
     * 
     * @param config
     *            DOM树，本插件的配置文件
     */
    public void init(XmlNode config) {
        // 设置基本参数
        XmlNode args = config.getSubNode("args");
        if (args != null) {
            timeout = Integer.parseInt(args.getAttribute("timeout", "60"));  //通讯超时时间默认60秒
            remoteIP = args.getAttribute("remoteIP");                       //远程ip地址
            remotePort = Integer.parseInt(args.getAttribute("remotePort","7800"));         //远程端口
            this.charset=args.getAttribute("charset");
        }
        
        client = new CoreClient();
        client.setCharset(this.charset);
        client.setRemoteIp(this.remoteIP);
        client.setTimeout(this.timeout);
        client.setRemotePort(this.remotePort);
        client.setCoreSwitch(SysConfigUtil.getSysConfig().getValue("coreSwitch"));
    }


    /**
     * @return the timeout
     */
    public int getTimeout() {
        return timeout;
    }

    /**
     * @return the remotePort
     */
    public int getRemotePort() {
        return remotePort;
    }

    /**
     * @return the remoteIP
     */
    public String getRemoteIP() {
        return remoteIP;
    }
    

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }


    public void setRemoteIP(String remoteIP) {
        this.remoteIP = remoteIP;
    }

    public void setRemotePort(int remotePort) {
        this.remotePort = remotePort;
    }

   
}
