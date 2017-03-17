/********************************************
 * 文件名称: MessageJmsClient.java
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
package com.herongtech.mqchannel.client;

import java.io.File;
import java.io.Serializable;
import java.util.Map;

import org.springframework.jms.core.JmsTemplate;
import com.ibm.mq.jms.MQQueue;

public class MessageJmsClient {
	private JmsTemplate jmsTemplate;
	private String sendQueueName;
	private MQQueue destination;

	public synchronized void send(final String content) {
		try {
			if(getSendQueueName() != null && !"".equals(getSendQueueName())) {
				this.getJmsTemplate().convertAndSend(this.getSendQueueName(), content.getBytes("UTF-8"));
			} else {
				getJmsTemplate().convertAndSend(this.getDestination(), content.getBytes("UTF-8"));
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public synchronized void send(final Serializable obj) {
		try {
			if(getSendQueueName() != null && !"".equals(getSendQueueName())) {
				this.getJmsTemplate().convertAndSend(this.getSendQueueName(),obj);
			} else {
				getJmsTemplate().convertAndSend(this.getDestination(), obj);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public synchronized void send(final byte[] bytes) {
		try {
			if(getSendQueueName() != null && !"".equals(getSendQueueName())) {
				this.getJmsTemplate().convertAndSend(this.getSendQueueName(), bytes);
			} else {
				getJmsTemplate().convertAndSend(this.getDestination(), bytes);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}

	public synchronized void send(final Map map) {
		try {
			if(getSendQueueName() != null && !"".equals(getSendQueueName())) {
				this.getJmsTemplate().convertAndSend(this.getSendQueueName(), map);
			} else {
				getJmsTemplate().convertAndSend(this.getDestination(), map);
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public void send(File file) {
		
	}
	
	/**
	 * 调用远程服务 （通讯超时、发送报文失败、call远程服务器失败、解报失败 通过应用错误码、错误信息返回实现区分）
	 * 
	 * @param event
	 * @return
	 */
	public void callRemoteService(String request) {
		this.send(request);
	}

	public JmsTemplate getJmsTemplate() {
		return jmsTemplate;
	}

	public void setJmsTemplate(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}

	public String getSendQueueName() {
		return sendQueueName;
	}

	public void setSendQueueName(String sendQueueName) {
		this.sendQueueName = sendQueueName;
	}

	public MQQueue getDestination() {
		return destination;
	}

	public void setDestination(MQQueue destination) {
		this.destination = destination;
	}
}
