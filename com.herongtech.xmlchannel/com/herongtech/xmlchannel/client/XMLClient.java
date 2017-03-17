/********************************************
 * 文件名称: XMLClient.java
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
package com.herongtech.xmlchannel.client;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.herongtech.comm.socket.TcpComm;
import com.herongtech.comm.socket.TcpPackage;
import com.herongtech.commons.tools.DataUtil;
import com.herongtech.console.core.constant.IErrorNo;
import com.herongtech.console.core.util.XmlBeanUtil;
import com.herongtech.xmlchannel.pkg.ProResult;

public class XMLClient {
	/**
	 * 远程端口
	 */
	private int remotePort;
	/**
	 * 远程ip地址
	 */
	private String remoteIp;
	/**
	 * 超时时间
	 */
	private int timeout;

	/**
	 * 编码方式
	 */
	private String charset;

	public int getRemotePort() {
		return remotePort;
	}

	public void setRemotePort(int remotePort) {
		this.remotePort = remotePort;
	}

	public String getCharset() {
		return charset;
	}

	public void setCharset(String charset) {
		this.charset = charset;
	}

	public String getRemoteIp() {
		return remoteIp;
	}

	public void setRemoteIp(String remoteIp) {
		this.remoteIp = remoteIp;
	}

	public int getTimeout() {
		return timeout;
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

	/**
	 * 调用远程服务 （通讯超时、发送报文失败、call远程服务器失败、解报失败 通过应用错误码、错误信息返回实现区分）
	 * 
	 * @param event
	 * @return
	 */
	public String callRemoteService(String request,String functionId) {
		TcpComm comm = new TcpComm();
		int ret = 0;

		ret = comm.call(this.remoteIp, this.remotePort);

		// 连接远程机器，如果连接失败返回连接失败错误码和错误信息
		if (ret < 0) {
			String result = packAnsHead(
                    IErrorNo.ERR_SENDHOST, "call "
                            + this.remoteIp + "," + this.remotePort + "失败",
                    functionId,"流水号");;
			
			return result;
		}

		// 发送请求报文
		ret = sendEvent(comm, request);
		if (ret < 0) {
		    String result = packAnsHead(
                    IErrorNo.ERR_SENDHOST, "call "
                            + this.remoteIp + "," + this.remotePort + "失败",
                    functionId,"流水号");;
            
            return result;
		}

		// 接收应答报文
		String result = receiveEvent(comm);
		if (result == null) {
			try {
				result = this.packAnsHead(
						IErrorNo.ERR_TIMEOUT,
						this.remoteIp + "," + this.remotePort + "通讯超时", functionId, "流水号");
			} catch (Exception e) {
				e.printStackTrace();
			}
		
			return result;
		} else {
			return result;
		}

	}

	/**
	 * 发送请求
	 * 
	 * @param comm
	 * @param dataSet
	 * @return
	 */
	private int sendEvent(TcpComm comm, String request) {
		try{
			if(StringUtils.isNotEmpty(request)){
				byte [] content=request.getBytes(charset);
			    //组报文头
				byte[] pack=new byte[6+content.length];
				System.arraycopy(("X"+ DataUtil.fix0BeforeString(String.valueOf(content.length),5)).getBytes(charset), 
						0, pack, 0, 6);
				System.arraycopy(content, 0, pack, 6, content.length);

				if(comm.sendMsg(pack)<0){
					System.out.println("报文发送失败");
				}
			}else{
				return -1;
			}
		}catch(UnsupportedEncodingException e){
			e.printStackTrace();
			return -1;
		}
		return 0;
	}

	/**
	 * 接收应答报文
	 * 
	 * @param comm
	 * @return
	 */
	private String receiveEvent(TcpComm comm) {
		// 读5个字节的包头
		String result = null;
		int contentLength = 0;
		TcpPackage contentPack = null;
		try {
			comm.setTimeOut(this.timeout);
			TcpPackage headPack = comm.recvPack(6);
			// 接收报文失败、即报文非法。不给应答 直接关闭socket
			if (headPack.getStatus() == TcpPackage.STATUS_TCP_TIME_OUT
					|| headPack.getStatus() == TcpPackage.STATUS_TCP_FAIL) {
				return null;
			}

			//包体长度
			contentLength = Integer.parseInt(new String(headPack.getContent(),1, 6));
			System.out.println("接收包体长度:["+contentLength+"]");

			//处理xml包头内容获取报文编码方式、包体长度等
			contentPack = comm.recvPack(contentLength);
			if (contentPack.getStatus() == TcpPackage.STATUS_TCP_TIME_OUT
					|| contentPack.getStatus() == TcpPackage.STATUS_TCP_FAIL) {
				return null;
			}

			//转换成目标字符集,并转换内部数据集
			result = new String(contentPack.getContent(),charset);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}finally{
			comm.close();
		}
		return result;
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
		dataSet.updateString(IFieldName.sysTime, DateUtil.getTime());*/
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
