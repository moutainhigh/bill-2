/********************************************
 * 文件名称: DiscardPolicy.java
 * 系统名称: 合融技术平台
 * 模块名称: 总控服务
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 当服务无法受理外部任务时的拒绝策略
 * 系统版本: 2.0.0.1
 * 开发人员: 
 * 开发时间: 2010-5-15 上午08:12:37
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期    修改人员    修改说明
 *********************************************/

package com.herongtech.xmlchannel.server;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

public class DiscardPolicy implements RejectedExecutionHandler {
    /**
     * 系统核心服务忙情况下，先接收完毕报文给忙提示信息，不纳入线程池处理
     */
	public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
		//对客户端请求无法响应的做拒绝处理并提示前台 还是直接关闭客户端socket句柄，需要讨论
		XMLTask task=(XMLTask)r;
		task.getComm().close();
	}
}
