/********************************************
 * 文件名称: MDPListener.java
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
package com.herongtech.mqchannel.server;

import java.io.UnsupportedEncodingException;
import javax.jms.BytesMessage;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;
import org.springframework.jms.listener.SessionAwareMessageListener;
import com.herongtech.mqchannel.MqChannelLog;

/**
 * 消息监听器
 * @author
 *
 */
public class MDPListener implements SessionAwareMessageListener {

	public void onMessage(Message msg, Session session) throws JMSException {
		String msgContent = "";
		if(msg instanceof BytesMessage) {
			BytesMessage bm = (BytesMessage)msg;
    		int length = Long.valueOf(bm.getBodyLength()).intValue();
    		byte[] content = new byte[length];
    		bm.readBytes(content,length);
			//正常报文处理
    		try {
				msgContent = new String(content, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
				MqChannelLog.getLogCache().errorMessage(e.getMessage());
			}
    		
    		MqChannelLog.getLogCache().infoMessage(msgContent);
		} else {
			//非规范报文,直接存储
			if(msg instanceof TextMessage) {
				msgContent = ((TextMessage)msg).getText();
				
				MqChannelLog.getLogCache().infoMessage(msgContent);
			} else {
				MqChannelLog.getLogCache().infoMessage("Others Message,");
			}
		}
		if(!"".equals(msgContent)) {
			//MsgServiceFactory.getNegativeMsgService().acceptMsg(msgContent);
			MqTask task = new MqTask();
			task.setRequestMsg(msgContent);
			
			//处理请求信息
			task.dealRequest();
		}
	}

}
