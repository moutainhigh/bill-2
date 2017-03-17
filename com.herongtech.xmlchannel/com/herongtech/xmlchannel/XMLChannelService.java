/********************************************
 * 文件名称: XMLChannelService.java
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
 *        20160627-01  yanjl 新增
 *********************************************/
package com.herongtech.xmlchannel;

import com.herongtech.logger.Logger;
import com.herongtech.logger.LoggerFactory;
import com.herongtech.xmlparser.node.XmlNode;
import com.herongtech.xmlchannel.client.XMLClient;
import com.herongtech.xmlchannel.pkg.XmlChannelFieldCache;
import com.herongtech.xmlchannel.server.XMLServer;


/**
 * 通道插件的一个简单示例 
 */
public class XMLChannelService {
	private static Logger logger = LoggerFactory.getLogger(XMLChannelService.class);

	/**
	 * 通讯超时时间
	 */
	private int timeout = 3000;
	/**
	 * 服务端侦听端口
	 */
	private int serverPort = 7810;
	/**
	 * 远程IP地址
	 */
	private String remoteIP = null;

	/**
	 * 远程端口
	 */
	private int remotePort = 7800;

	/**
	 * xml服务端
	 */
	private XMLServer serverThread = null;
	/**
	 * xml客户端
	 */
	private XMLClient client = null;

	public XMLClient getClient() {
		return client;
	}

	/**
	 *线程池大小
	 */
	private int corePoolSize = 3;//30
	/**
	 * 线程池最大线程数
	 */
	private int maxNumPoolSize = 5;//50
	/**
	 * 线程生命周期
	 */
	private long keepAliveTime = 300;
	/**
	 * 任务等待队列
	 */
	private int waitTaskQueeSize = 10;//30

	/**
	 * socket缓存队列
	 */
	private int backlog = 10;//30

	/**
	 * 本地侦听绑定ip地址
	 */
	private String localIp;
	
	private String charset;
	
	
	public String getCharset() {
		return charset;
	}

	public void setCharset(String charset) {
		this.charset = charset;
	}

	public String getLocalIp() {
		return localIp;
	}

	public void setLocalIp(String localIp) {
		this.localIp = localIp;
	}

	/**
	 * 在这里做一些初始化工作
	 * 
	 * @param config
	 *            DOM树，本插件的配置文件
	 */
	public void init(XmlNode config) {
		// 从配置文件中读取配置信息。假设配置结构如下:
		// <xmlchannel-config> <args timeout="3000"> </xmlchannel-config>
		// 设置基本参数
		XmlNode args = config.getSubNode("args");
		if (args != null) {
			//通道为双向通道 每个通道 1对1
			timeout = Integer.parseInt(args.getAttribute("timeout", "60"));  //通讯超时时间默认60秒
			serverPort = Integer.parseInt(args.getAttribute("serverPort","7800"));         //侦听端口
			remoteIP = args.getAttribute("remoteIP");                       //远程ip地址
			remotePort = Integer.parseInt(args.getAttribute("remotePort","7800"));         //远程端口
			
			this.corePoolSize=Integer.parseInt(args.getAttribute("corePoolSize","30"));                 //线程池大小
			
			this.maxNumPoolSize=Integer.parseInt(args.getAttribute("maxNumPoolSize", "50"));               //线程池线程最大个数

            this.backlog=Integer.parseInt(args.getAttribute("backlog","30"));                      //socket缓存个数
			
            this.waitTaskQueeSize=Integer.parseInt(args.getAttribute("waitTaskQueeSize", "30"));                      //socket缓存个数
			
			this.keepAliveTime=Integer.parseInt(args.getAttribute("keepAliveTime", "300"));                  //线程生命周期
			
            this.charset=args.getAttribute("charset");
		}

		// 实例化
		serverThread = new XMLServer();
		serverThread.setBacklog(this.backlog);
		serverThread.setCorePoolSize(this.corePoolSize);
		serverThread.setKeepAliveTime(this.keepAliveTime);
		serverThread.setLocalIp(this.localIp);
		serverThread.setMaxNumPoolSize(this.maxNumPoolSize);
		serverThread.setServerPort(this.serverPort);
		serverThread.setTimeout(this.timeout);
		serverThread.setWaitTaskQueeSize(this.waitTaskQueeSize);
		serverThread.setCharset(this.charset);
		
		serverThread.startServer();
		
		client = new XMLClient();
		client.setCharset(this.charset);
		client.setRemoteIp(this.remoteIP);
		client.setTimeout(this.timeout);
		client.setRemotePort(this.remotePort);
		
		//标签描述文件装载
		XmlChannelFieldCache.initFieldConfig();
	}

	/**
	 * 开启线程等
	 */
	public void start() {
		serverThread.start();
	}

	/**
	 * 停止线程等
	 */
	public void stop() {
		serverThread.stopServer();
	}

	/**
	 * 销毁操作等
	 */
	public void destroy() {

	}
	/**
	 * @return the timeout
	 */
	public int getTimeout() {
		return timeout;
	}

	/**
	 * @return the serverPort
	 */
	public int getServerPort() {
		return serverPort;
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
	
	public int getCorePoolSize() {
		return corePoolSize;
	}

	public void setCorePoolSize(int corePoolSize) {
		this.corePoolSize = corePoolSize;
	}

	public int getMaxNumPoolSize() {
		return maxNumPoolSize;
	}

	public void setMaxNumPoolSize(int maxNumPoolSize) {
		this.maxNumPoolSize = maxNumPoolSize;
	}

	public long getKeepAliveTime() {
		return keepAliveTime;
	}

	public void setKeepAliveTime(long keepAliveTime) {
		this.keepAliveTime = keepAliveTime;
	}

	public int getWaitTaskQueeSize() {
		return waitTaskQueeSize;
	}

	public void setWaitTaskQueeSize(int waitTaskQueeSize) {
		this.waitTaskQueeSize = waitTaskQueeSize;
	}

	public int getBacklog() {
		return backlog;
	}

	public void setBacklog(int backlog) {
		this.backlog = backlog;
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

	public void setServerPort(int serverPort) {
		this.serverPort = serverPort;
	}

	public void setRemoteIP(String remoteIP) {
		this.remoteIP = remoteIP;
	}

	public void setRemotePort(int remotePort) {
		this.remotePort = remotePort;
	}

	public static void main(String[] args){
		//ChannelFieldCache.getInstance();
		return;
	}

}
