/********************************************
 * 文件名称: GlobalExceptionResolver.java
 * 系统名称: 基础技术平台
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 多语言缓存类
 * 系统版本: 2.0.0.1
 * 开发人员: yanjl
 * 开发时间: 2016-6-15 上午08:39:00
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期     修改人员        修改说明
 * 20160615-01   yanjl  创建  
 *********************************************/

package com.herongtech.console.core.common.exception;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.herongtech.commons.tools.JSONHelper;
import com.herongtech.commons.tools.StringUtils;
import com.herongtech.console.core.common.json.AjaxJson;
import com.herongtech.console.service.ServiceFactory;
import com.herongtech.console.service.interfaces.ISystemService;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.exception.impl.BizAppRuntimeException;
import com.herongtech.log.CommonLog;
import com.herongtech.sysconstant.ISysErrorNo;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

/**
 * spring mvc 全局处理异常捕获 根据请求区分ajax和普通请求，分别进行响应.
 * 第一、异常信息输出到日志中。
 * 第二、截取异常详细信息的前50个字符，写入日志表中t_s_log。
 */
@Component
public class GlobalExceptionResolver implements HandlerExceptionResolver {
	/**
	 * 版本号
	 */
	public static final String HERONGTECH_VERSION="@system 票据管理平台 @version 2.0.0.1 @lastModiDate 20160615 @describe ";
	
	//记录数据库最大字符长度
	private static final int WIRTE_DB_MAX_LENGTH = 1500;
	//记录数据库最大字符长度
	private static final short LOG_LEVEL = 6;
	//记录数据库最大字符长度
	private static final short LOG_OPT = 3;
	
	/**
	 * 对异常信息进行统一处理，区分异步和同步请求，分别处理
	 */
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
        boolean isajax = isAjax(request,response);
        Throwable deepestException = deepestException(ex);
        return processException(request, response, handler, deepestException, isajax);
	}
	/**
	 * 判断当前请求是否为异步请求.
	 */
	private boolean isAjax(HttpServletRequest request, HttpServletResponse response){
		return !StringUtils.isEmpty(request.getHeader("X-Requested-With"));
	}
	/**
	 * 获取最原始的异常出处，即最初抛出异常的地方
	 */
    private Throwable deepestException(Throwable e){
        
    	if (e instanceof BizAppException) {
			return e;
		} else if (e instanceof BizAppRuntimeException) {
			return new BizAppException(((BizAppRuntimeException) e).getErrorNo(), ((BizAppRuntimeException) e).getErrorMsg());
		} else if (e instanceof SQLException) {
			return new BizAppException(ISysErrorNo.ERR_DBERR, "数据库操作失败");

		}
		else {
			Throwable tmp = e;
	        int breakPoint = 0;
	        while(tmp.getCause()!=null){
	            if(tmp.equals(tmp.getCause())){
	                break;
	            }
	            tmp=tmp.getCause();
	            breakPoint++;
	            if(breakPoint>1000){
	                break;
	            }
	        } 
	        return tmp;
		}
    	
    }
	/**
	 * 处理异常.
	 * @param request
	 * @param response
	 * @param handler
	 * @param deepestException
	 * @param isajax
	 * @return
	 */
	private ModelAndView processException(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			Throwable ex, boolean isajax) {
		
		//步骤一、异常信息记录到日志文件中.
		CommonLog.getCommonLogCache().errorMessage("全局处理异常捕获:", ex);
		//步骤二、异常信息记录截取前50字符写入数据库中.
		logDb(ex);
		//步骤三、分普通请求和ajax请求分别处理.
		if(isajax){
			return processAjax(request,response,handler,ex);
		}else{
			return processNotAjax(request, response, handler, ex);
		}
	}
	/**
	 * 异常信息记录截取前50字符写入数据库中
	 * @param ex
	 */
	private void logDb(Throwable ex) {
		
		String exceptionMessage = "错误异常: " + ex.getClass().getSimpleName() + ",错误描述：" + ex.getMessage();
		
		if (!StringUtils.isEmpty(exceptionMessage)){
			if(exceptionMessage.length() > WIRTE_DB_MAX_LENGTH){
				exceptionMessage = exceptionMessage.substring(0, WIRTE_DB_MAX_LENGTH);
			}
		}
		
		ISystemService systemService = ServiceFactory.getSystemService();
		systemService.addLog(exceptionMessage, LOG_LEVEL, LOG_OPT);
	}
	/**
	 * ajax异常处理并返回.
	 * @param request
	 * @param response
	 * @param handler
	 * @param deepestException
	 * @return
	 */
	private ModelAndView processAjax(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			Throwable deepestException){
		
		ModelAndView empty = new ModelAndView();
		//response.setContentType("application/json");
		response.setHeader("Cache-Control", "no-store");
		AjaxJson json = new AjaxJson();
		json.setSuccess(false);
		
		String retStr = getRetMessage(deepestException);
		json.setMsg(retStr);
		
		try {
			PrintWriter pw = response.getWriter();
			pw.write(JSONHelper.bean2json(json));
			pw.flush();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		empty.clear();
		
		return empty;
	}
	/**
	 * 普通页面异常处理并返回.
	 * @param request
	 * @param response
	 * @param handler
	 * @param deepestException
	 * @return
	 */
	private ModelAndView processNotAjax(HttpServletRequest request,
			HttpServletResponse response, Object handler, Throwable ex) {
		
		String exceptionMessage =ex.getMessage();// ;
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("exceptionMessage", exceptionMessage);
		model.put("exceptionStackTrace", getThrowableMessage(ex));
		model.put("ex", ex);
		return new ModelAndView("error", model);
	}
	
	/**
	 * 返回错误信息字符串
	 * 
	 * @param ex
	 *            Exception
	 * @return 错误信息字符串
	 */
	public String getThrowableMessage(Throwable ex) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		ex.printStackTrace(pw);
		return sw.toString();
	}
	
	/**
	 * 返回错误信息字符串
	 * 
	 * @param ex
	 *            Exception
	 * @return 错误信息字符串
	 */
	public String getRetMessage(Throwable ex) {
		if (ex instanceof BizAppException) {
			return ((BizAppException) ex).getErrorMsg();
		} else {
			return ex.getMessage();
		} 
	}
}
