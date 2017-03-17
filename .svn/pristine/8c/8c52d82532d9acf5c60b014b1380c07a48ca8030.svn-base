/********************************************
 * 文件名称: MqChannelService.java
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
package com.herongtech.mqchannel;

import javax.jms.JMSException;
import org.springframework.jms.JmsException;
import org.springframework.jms.connection.UserCredentialsConnectionFactoryAdapter;
import org.springframework.jms.core.JmsTemplate;

import com.herongtech.console.cache.ChannelFieldCache;
import com.herongtech.exception.impl.BizBaseException;
import com.herongtech.exception.impl.BizBaseRuntimeException;
import com.herongtech.logger.Logger;
import com.herongtech.logger.LoggerFactory;
import com.herongtech.mqchannel.client.MessageJmsClient;
import com.herongtech.mqchannel.pkg.MqChannelFieldCache;
import com.herongtech.mqchannel.server.MDPListener;
import com.herongtech.mqchannel.server.MessageListenerContainer;
import com.herongtech.xmlparser.node.XmlNode;
import com.ibm.mq.jms.MQQueue;
import com.ibm.mq.jms.MQQueueConnectionFactory;

/**
 * 通道插件的一个简单示例 
 */
public class MqChannelService {
	private static Logger logger = LoggerFactory.getLogger(MqChannelService.class);
	
	/**
	 * 队列个数
	 */
	private int concurrentConsumers = 1;
	/**
	 * 最大队列个数
	 */
	private int maxConcurrentConsumers = 1;
	/**
	 * 用户名
	 */
	private String mqUsername = null;

	/**
	 * 用户密码
	 */
	private String mqUserpwd = null;
	
	/**
	 * mq异步读队列
	 */
	private String ccpcInQueueName = null;

	/**
	 * mq同步读队列
	 */
	private String ccpcInRealtimeQueueName = null;
	
	/**
	 * mq异步写队列
	 */
	private String mbOutQueueName = null;
	
	/**
	 * mq同步写队列
	 */
	private String mbOutRealtimeQueueName = null;
	
	/**
	 * mqip
	 */
	private String mqHostname = null;
	
	/**
	 * mq端口
	 */
	private int mqPort = 0;
	
	/**
	 * mq id号
	 */
	private int mqCcsid = 0;

	/**
	 * mq通道名
	 */
	private String mqChannel = null;
	
	/**
	 * mq队列名称
	 */
	private String mqQueueManagerName = null;
	
	/**
	 * 监听是否打开: 0-关闭  1-打开
	 */
	private String listenSwitch = "1";

	/**
	 * mq监听容器
	 */
	MessageListenerContainer mlContainer = null;
	
	/**
	 * mq监听容器
	 */
	MessageListenerContainer realMlContainer = null;
	
	/**
	 * mq监听处理函数
	 */
	MDPListener mqListener = new MDPListener();
	
	/**
	 * mq监听处理函数
	 */
	MDPListener realMqListener = new MDPListener();
	
	/**
	 * mq客户端
	 */
	private MessageJmsClient client = null;

	public MessageJmsClient getClient() {
		return client;
	}
	
	/**
	 * mq实时客户端
	 */
	private MessageJmsClient realTimeClient = null;

	public MessageJmsClient getRealTimeClient() {
		return realTimeClient;
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
			this.concurrentConsumers = Integer.parseInt(args.getAttribute("concurrentConsumers", "1"));                //队列个数
			this.maxConcurrentConsumers = Integer.parseInt(args.getAttribute("maxConcurrentConsumers", "1"));                //最大队列个数
			
			this.mqUsername = args.getAttribute("mqUsername","hr");         //用户名
			this.mqUserpwd = args.getAttribute("mqUserpwd","hr");           //用户密码
			
			this.ccpcInQueueName = args.getAttribute("ccpcInQueueName","3051000001_6");         //用户名
			this.ccpcInRealtimeQueueName = args.getAttribute("ccpcInRealtimeQueueName","3051000001_5");           //用户密码
			
			this.mbOutQueueName = args.getAttribute("mbOutQueueName","3051000001_4");         //用户名
			this.mbOutRealtimeQueueName = args.getAttribute("mbOutRealtimeQueueName","3051000001_3");           //用户密码
			
			this.mqHostname = args.getAttribute("mqHostname","127.0.0.1");         //用户名
			this.mqPort = Integer.parseInt(args.getAttribute("mqPort","1417"));           //用户密码
			this.mqCcsid =  Integer.parseInt(args.getAttribute("mqCcsid", "819"));         //用户名
			this.mqChannel = args.getAttribute("mqChannel", "SYSTEM.DEF.SVRCONN");           //用户密码
			this.mqQueueManagerName = args.getAttribute("mqQueueManagerName","QMEMBFE");         //用户名
			this.listenSwitch = args.getAttribute("listenSwitch","1");           //是否开启
			
			
		} else{
			logger.errorMessage("mq通道参数未配置 !");
			throw new BizBaseRuntimeException("mq参数未配置");
		}
	}

	/**
	 * 开启线程等
	 * @throws BizBaseException 
	 */
	public void start() throws BizBaseException {
		MQQueueConnectionFactory mcf = new MQQueueConnectionFactory();  
		//
		MQQueue mqIn = new MQQueue();
		MQQueue mqRealTimeIn = new MQQueue();
		
		MQQueue mqOut = new MQQueue();
		MQQueue mqRealTimeOut = new MQQueue();
		
		UserCredentialsConnectionFactoryAdapter ucf = new UserCredentialsConnectionFactoryAdapter();
		
	    try {
	    	mcf.setHostName(this.mqHostname); 
	    	mcf.setPort(this.mqPort);
			mcf.setQueueManager(this.mqQueueManagerName);
			mcf.setTransportType(1);
			mcf.setCCSID(this.mqCcsid);
			mcf.setChannel(this.mqChannel);
			
			ucf.setUsername(this.mqUsername);
			ucf.setPassword(this.mqUserpwd);
			ucf.setTargetConnectionFactory(mcf);
			
			mqIn.setBaseQueueName(this.ccpcInQueueName);
			mqIn.setTargetClient(1);
			
			mqRealTimeIn.setBaseQueueName(this.ccpcInRealtimeQueueName);
			mqRealTimeIn.setTargetClient(1);
			
			mqOut.setBaseQueueName(this.mbOutQueueName);
			mqOut.setTargetClient(1);
			
			mqRealTimeOut.setBaseQueueName(this.mbOutRealtimeQueueName);
			mqRealTimeOut.setTargetClient(1);
			
		} catch (JMSException e) {
			e.printStackTrace();
			throw new BizBaseException("启动mq监听失败");
		}
	    
	    //如果不监听，不启动mq监听处理
	    if (this.listenSwitch.equals("1")){
	    	//启动mq监听，接收mq报文处理
	    	mlContainer = new MessageListenerContainer();
	    	mlContainer.setListenSwitch(this.listenSwitch);
	    	mlContainer.setConcurrentConsumers(this.concurrentConsumers);
	    	mlContainer.setMaxConcurrentConsumers(this.maxConcurrentConsumers);
	    	mlContainer.setConnectionFactory(ucf);
	    	mlContainer.setDestination(mqIn);
	    	mlContainer.setMessageListener(mqListener);
	    	mlContainer.setSessionTransacted(true);
	    	mlContainer.setMaxMessagesPerTask(1);
		
	    	mlContainer.initialize();
		
	    	//启动mq监听，接收mq报文处理
	    	realMlContainer = new MessageListenerContainer();
	    	realMlContainer.setListenSwitch(this.listenSwitch);
	    	realMlContainer.setConcurrentConsumers(this.concurrentConsumers);
	    	realMlContainer.setMaxConcurrentConsumers(this.maxConcurrentConsumers);
	    	realMlContainer.setConnectionFactory(ucf);
	    	realMlContainer.setDestination(mqRealTimeIn);
	    	realMlContainer.setMessageListener(realMqListener);
	    	realMlContainer.setSessionTransacted(true);
	    	realMlContainer.setMaxMessagesPerTask(1);
		
	    	realMlContainer.initialize();
	    }
		
		//mq客户端发送信息
		JmsTemplate jmsTemplate = new JmsTemplate();
		jmsTemplate.setConnectionFactory(ucf);
		jmsTemplate.setSessionTransacted(true);
		
		client = new MessageJmsClient();
		client.setJmsTemplate(jmsTemplate);
		client.setDestination(mqOut);
		
		realTimeClient = new MessageJmsClient();
		realTimeClient.setJmsTemplate(jmsTemplate);
		realTimeClient.setDestination(mqRealTimeOut);
		//标签描述文件装载
		MqChannelFieldCache.initFieldConfig();
	}

	/**
	 * 停止线程等
	 */
	public void stop() {
		if(mlContainer != null && mlContainer.getMessageListener() != null ) {
			try{
				logger.errorMessage("开始销毁实时接收队列容器");
				mlContainer.stop();
			} catch( JmsException ex ) {
				logger.errorMessage("");
			} finally {
				mlContainer.shutdown();
				logger.errorMessage("实时接收队列容器已销毁");
			}
		}
		if(realMlContainer != null && realMlContainer.getMessageListener() != null ) {
			try{
				logger.errorMessage("开始销毁实时接收队列容器");
				realMlContainer.stop();
			} catch( JmsException ex ) {
				logger.errorMessage("");
			} finally {
				realMlContainer.shutdown();
				logger.errorMessage("实时接收队列容器已销毁");
			}
		}
	}

	public static void main(String[] args){
		return;
	}

}
