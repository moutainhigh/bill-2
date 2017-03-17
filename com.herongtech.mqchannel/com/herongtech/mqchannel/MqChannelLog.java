/********************************************
 * 文件名称: MqChannelLog.java
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

import com.herongtech.logger.Logger;
import com.herongtech.logger.LoggerFactory;

/**
 * 日志句柄获取调用类
 * 
 * @author Administrator
 */
public class MqChannelLog {
	/**
	 * 版本号
	 */
	public static final String  HERONGTECH_VERSION="@system  合融技术平台 @version 2.0.0.1  @lastModiDate @describe ";
	
    /**
     * 带缓存交易日志句柄
     * 一个线程一个句柄
     */
	private static  ThreadLocal<Logger>  logCache = new ThreadLocal<Logger>();
	/**
	 * 不带缓存交易日志句柄
	 * 一个线程一个句柄
	 */
	private static  ThreadLocal<Logger>  logNoCache = new ThreadLocal<Logger>();
	
	/**
	 * 带缓存日志句柄
	 */
	public static Logger getLogCache() {
		Logger  logger=(Logger)logCache.get();  
		if(logger == null){
			logger = LoggerFactory.getLogger("mqchannel.log");
			logger.startTransaction();
			logCache.set(logger);
		}
		return logger;
	}

	/**
	 * 实时写的日志句柄
	 */
	public static Logger getLogNoCache() {
		Logger logger=(Logger)logNoCache.get();  
		if(logger==null){
			logger = LoggerFactory.getLogger("mqchannel.log");
			logNoCache.set(logger);
		}
		return logger; 
	}
	
}
