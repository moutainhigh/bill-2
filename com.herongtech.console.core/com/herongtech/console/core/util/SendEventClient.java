package com.herongtech.console.core.util;

import com.herongtech.beancontainer.BeanContainerFactory;
import com.herongtech.commons.tools.DatasetToString;
import com.herongtech.commons.tools.DateUtil;
import com.herongtech.console.core.constant.IErrorNo;
import com.herongtech.console.core.constant.IFieldName;
import com.herongtech.context.Context;
import com.herongtech.context.impl.ContextImpl;
import com.herongtech.context.util.ContextUtil;
import com.herongtech.data.common.data.DataService;
import com.herongtech.data.interfaces.data.DataColumnType;
import com.herongtech.data.interfaces.data.IData;
import com.herongtech.event.Event;
import com.herongtech.exception.impl.BizAppRuntimeException;
import com.herongtech.log.CommonLog;
import com.herongtech.service.IServiceClient;

public class SendEventClient {
	
	public static void send(String routerNo, String content){
		Context context = new ContextImpl();
		ContextUtil.setRequestData(context, content);
		
		Event event = Event.createEvent(routerNo, context);
		//获取本地服务处理通道
		IServiceClient serviceClient = BeanContainerFactory.getBeanContainer(
				SendEventClient.class.getClassLoader()).getBean("localServiceClient");
		
		try {
		    CommonLog.getCommonLogCache().debugMessage("call远程服务器，请求包：" + content);
		    //获取应答事件
			serviceClient.send(event);
		} catch (Exception e) {
			e.printStackTrace();
			CommonLog.getCommonLogCache().errorMessage("call远程服务器调用失败：",  e);
			throw new  BizAppRuntimeException(IErrorNo.ERR_DEFAULT, "通讯失败");			
		}
		
	}
	
	static public IData sendReceive(IData request){
		Context context = new ContextImpl();
		ContextUtil.setRequestData(context, request);
		
		Event event = Event.createEvent(request.getString(IFieldName.functionId), context);
		//获取本地服务处理通道
		IServiceClient serviceClient = BeanContainerFactory.getBeanContainer(
				SendEventClient.class.getClassLoader()).getBean("localServiceClient");
		
		Event answerEvent = null;
		IData answerData = null;
		try {
		    CommonLog.getCommonLogCache().debugMessage("call远程服务器，请求包：" + DatasetToString.datasetToLogString(request));
		    //获取应答事件
			answerEvent = serviceClient.sendReceive(event);
			
			// 在处理的过程中出现错误
			if (answerEvent.getReturnCode() == Event.I_RETRURN_SYS_ERROR) { 
				// 构造一个错误应答结果集
				answerData = packAnsHead(answerEvent.getErrorNo(), answerEvent.getErrorInfo(), 
									request.getString(IFieldName.functionId), request.getString(IFieldName.exSerial));
			} else if (answerEvent.getReturnCode() == Event.I_RETRURN_TIMEOUT){   //处理超时
				// 构造一个超时应答结果集
				answerData = packAnsHead(IErrorNo.ERR_TIMEOUT, "处理超时",
				request.getString(IFieldName.functionId), request.getString(IFieldName.exSerial));
			}
						
			else{
				//没有出现问题
				// 在这里假设第一个数据集就是我们需要的数据集
				answerData = (IData)answerEvent.getResponse();
				if (answerData == null){
					// 构造一个错误应答结果集
					answerData = packAnsHead(answerEvent.getErrorNo(), answerEvent.getErrorInfo(), 
								request.getString(IFieldName.functionId), request.getString(IFieldName.exSerial));
				}	
			}
		} catch (Exception e) {
			e.printStackTrace();
			CommonLog.getCommonLogCache().errorMessage("call远程服务器调用失败：",  e);
			answerData = packAnsHead(IErrorNo.ERR_DEFAULT, "未知错误 ["+e.getMessage() + "]",
					request.getString(IFieldName.functionId), request.getString(IFieldName.exSerial));
			//throw new  BizAppRuntimeException(IErrorNo.ERR_DEFAULT, "通讯失败");			
		}
		return answerData;
	}
	
	/**
	 * 设置错误应答包头
	 * 
	 * @param errorNo
	 *            错误码
	 * @param ErrorInfo
	 *            错误信息
	 * @param FunctionId
	 *            功能号
	 * @param Exserial
	 *            流水号
	 * @return
	 */
	private static IData packAnsHead(String errorNo, String errorInfo,
		String functionId, String exserial) {
		IData dataSet =  DataService.getDefaultInstance().getData();
		dataSet.addColumn(IFieldName.functionId, DataColumnType.DS_STRING);
		dataSet.addColumn(IFieldName.exSerial, DataColumnType.DS_STRING);
		dataSet.addColumn(IFieldName.errorNo, DataColumnType.DS_STRING);
		dataSet.addColumn(IFieldName.errorInfo, DataColumnType.DS_STRING);
		dataSet.addColumn(IFieldName.sysDate, DataColumnType.DS_INT);
		dataSet.addColumn(IFieldName.sysTime, DataColumnType.DS_INT);
		dataSet.appendRow();
		dataSet.updateString(IFieldName.errorNo, errorNo);
		dataSet.updateString(IFieldName.errorInfo, errorInfo);
		dataSet.updateString(IFieldName.functionId, functionId);
		dataSet.updateString(IFieldName.exSerial, exserial);
		dataSet.updateString(IFieldName.sysDate, DateUtil.getDate());
		dataSet.updateString(IFieldName.sysTime, DateUtil.getTime());
		return dataSet;
	}


}
