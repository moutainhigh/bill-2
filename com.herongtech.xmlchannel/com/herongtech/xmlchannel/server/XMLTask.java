/********************************************
 * 文件名称: XMLTask.java
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
 *********************************************/
package com.herongtech.xmlchannel.server;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import com.herongtech.beancontainer.BeanContainerFactory;
import com.herongtech.comm.socket.TcpComm;
import com.herongtech.comm.socket.TcpPackage;
import com.herongtech.commons.tools.DataUtil;
import com.herongtech.console.core.constant.IConstants;
import com.herongtech.console.core.constant.IErrorNo;
import com.herongtech.console.core.util.XmlBeanUtil;
import com.herongtech.context.Context;
import com.herongtech.context.impl.ContextImpl;
import com.herongtech.context.util.ContextUtil;
import com.herongtech.event.Event;
import com.herongtech.parser.filter.PathFilter;
import com.herongtech.service.IServiceClient;
import com.herongtech.xmlchannel.XmlChannelLog;
import com.herongtech.xmlchannel.pkg.ProResult;
import com.herongtech.xmlchannel.pkg.TransInfo;
import com.herongtech.xmlparser.node.XmlNode;
import com.herongtech.xmlparser.parser.XmlStringParser;

public class XMLTask implements Runnable
{
	private Socket	socket	= null;
	private int timeout=30;
	private String charset="GBK";
    private TcpComm  comm=null;
    static final private String XML_HEAD = "<?xml version='1.0' encoding='UTF-8'?>";
	
	public TcpComm getComm() {
		return comm;
	}

	public XMLTask(Socket socket,int timeout,String charset)
	{
		this.socket = socket;
		this.timeout=timeout;
		this.comm=new TcpComm(socket);
		this.charset=charset;
	}
	
	public XMLTask(Socket socket,String charset)
	{
		this.socket = socket;
		this.comm=new TcpComm(socket);
		this.charset=charset;
	}

	/**
	 * 通道逻辑处理
	 */
	public void run()
	{
		String request = null;
		request = receiveEvent();//获取请求并转化成JRES平台事件
		if(request==null){
	        comm.close();      //暂时采用不做应答关闭方式处理，后续改用容错应答	
		    return;	
		}
		
		//同步调用 CEP平台中的一个服务
		Event answerEvent = null;
		String answerData = null;
		TransInfo transInfo = null;
		try {
			XmlChannelLog.getLogNoCache().infoMessage("请求报文:" + request);
			transInfo=getTransInfo(request);
			Context context = new ContextImpl();
			ContextUtil.setRequestData(context, request);
			ContextUtil.setContextAttribute(context,  IConstants.TRANS_INFO, transInfo);
			
			Event event = Event.createEvent(transInfo.getFunctionId(), context);
			
			//获取本地服务处理通道
			IServiceClient serviceClient = BeanContainerFactory.getBeanContainer(
					this.getClass().getClassLoader()).getBean("localServiceClient");
			//获取应答事件
			answerEvent = serviceClient.sendReceive(event, this.timeout);
			
			// 在处理的过程中出现错误
			if (answerEvent.getReturnCode() == Event.I_RETRURN_SYS_ERROR) { 
				// 构造一个错误应答结果集
				answerData = this.packAnsHead(answerEvent.getErrorNo(), answerEvent.getErrorInfo(), 
				        transInfo.getFunctionId(), "");
			} else if (answerEvent.getReturnCode() == Event.I_RETRURN_TIMEOUT){   //处理超时
				// 构造一个超时应答结果集
				answerData=this.packAnsHead(IErrorNo.ERR_TIMEOUT, "处理超时",
				        transInfo.getFunctionId(), "");
			}else{
				//没有出现问题
			    answerData=(String) ContextUtil.getResponseData(context);
			    
				if (answerData == null){
					// 构造一个错误应答结果集
					answerData = this.packAnsHead(answerEvent.getErrorNo(), answerEvent.getErrorInfo(), 
					        transInfo.getFunctionId(), "");
				}	
			}
			
		} catch (Exception e) {
			//e.printStackTrace();
			answerData=this.packAnsHead(IErrorNo.ERR_DEFAULT, "未知错误 ["+e.getMessage() + "]",
			        transInfo.getFunctionId(), "");
		} finally{
			
			XmlChannelLog.getLogNoCache().infoMessage("应答报文:" + request);
			XmlChannelLog.getLogNoCache().endTransaction();
			
			sendEvent(answerData);
			comm.close();
		}

	}

	    private TransInfo getTransInfo(String xml){
	        TransInfo trans=new TransInfo();
	        XmlStringParser parser = new XmlStringParser();
	        XmlNode xmlNode = parser.parse(xml).getRoot();
	        PathFilter<XmlNode> filter = new PathFilter<XmlNode>(xmlNode);
            XmlNode fuctionId = filter.findNode("/functionId");
            trans.setFunctionId(fuctionId.getContent());
            XmlNode exSerial = filter.findNode("/exSerial");
            trans.setExSerial(exSerial.getContent());

            XmlNode transDt = filter.findNode("/transDt");
            trans.setTransDt(transDt == null ?"":transDt.getContent());
	        return trans;
	    }
	
	   /**
	    * 
	    * @param dataSet
	    */
		private boolean sendEvent(String xml){
			
			try {
				if(xml!=null){
					byte [] content = (XML_HEAD + xml.toString()).getBytes(charset);
				    //组报文头
					byte[] pack=new byte[6+content.length];
					System.arraycopy(("X" + DataUtil.fix0BeforeString(String.valueOf(content.length),5)).getBytes(charset), 0, pack, 0, 6);
					System.arraycopy(content, 0, pack, 6, content.length);

					if(comm.sendMsg(pack)<0){
						System.out.println("报文发送失败");
					}
				}else{
					return false;
				}
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
				return false;
			}
			return true;
		}
		
		/**
		 * 接收报文并转换成JRES事件
		 * @return
		 * @throws IOException
		 * @throws DatasetException
		 */
		private String receiveEvent() 
		{
			int contentLength = 0;
			TcpPackage contentPack = null;
			// 读6个字节的包头
			String request = null;
			try {
				comm.setTimeOut(this.timeout);
				TcpPackage headPack = comm.recvPack(6);
				// 接收报文失败、即报文非法。不给应答 直接关闭socket
				if (headPack.getStatus() == TcpPackage.STATUS_TCP_TIME_OUT
						|| headPack.getStatus() == TcpPackage.STATUS_TCP_FAIL) {
					return null;
				}

				// 包体长度
				contentLength = Integer.parseInt(new String(headPack.getContent(),
						1, 5).trim());

				// 处理xml包头内容获取报文编码方式、包体长度等
				contentPack = comm.recvPack(contentLength);
				if (contentPack.getStatus() == TcpPackage.STATUS_TCP_TIME_OUT
						|| contentPack.getStatus() == TcpPackage.STATUS_TCP_FAIL) {
					return null;
				}
				// 转换成目标字符集,并转换内部数据集
				request = new String(contentPack.getContent(),charset);
				
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
			return request;
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
		private String packAnsHead(String errorNo, String errorInfo,
			String functionId, String exserial) {
			/*IData dataSet =  DataService.getDefaultInstance().getData();
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
			return dataSet;*/
		    
		    ProResult result=new ProResult();
	        result.setType("E");
	        result.setCode(errorNo);
	        result.setMessage(errorInfo);
	        result.setExSerial(exserial);
	        result.setFunctionId(functionId);
	        Map<String, Class> clazzMap = new HashMap<String, Class>();
	        clazzMap.put("Document", ProResult.class);
	        String errResp=XmlBeanUtil.bean2xml(clazzMap, result);
	        return errResp;
		}

}
