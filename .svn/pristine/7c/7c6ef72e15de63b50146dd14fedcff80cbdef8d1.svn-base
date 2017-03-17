/********************************************
 * 文件名称: MessageListenerContainer.java
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

import org.springframework.jms.listener.DefaultMessageListenerContainer;
import com.herongtech.sysconstant.ISysDict;

public class MessageListenerContainer extends DefaultMessageListenerContainer{
	
	/**
	 * 版本号
	 */
	public static final String  HERONGTECH_VERSION="@system  合融技术平台 @version 2.0.0.1  @lastModiDate @describe ";

	private String listenSwitch;

	@Override
	public void initialize(){
		if(ISysDict.K_YORN.YORN_NO.equals(listenSwitch)){
			super.setConnectionFactory(null);
		}
		super.initialize();
	}
	
	public void setListenSwitch(String listenSwitch) {
		this.listenSwitch = listenSwitch;
	}

	public String getListenSwitch() {
		return listenSwitch;
	}
}
