package com.herongtech.corechannel.client;

import java.io.UnsupportedEncodingException;

import org.apache.commons.lang.StringUtils;

import com.herongtech.comm.socket.TcpComm;
import com.herongtech.comm.socket.TcpPackage;
import com.herongtech.commons.tools.DataUtil;
import com.herongtech.console.core.constant.IConstants;
import com.herongtech.console.core.constant.IErrorNo;


public class CoreClient {
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
    
    
    private String coreSwitch;
    
    
    public String getCoreSwitch() {
        return coreSwitch;
    }

    
    public void setCoreSwitch(String coreSwitch) {
        this.coreSwitch = coreSwitch;
    }

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
    public String callRemoteService(String requestXml) {
        if(IConstants.SWITCH_FLAG.CLOSE.equals(this.getCoreSwitch())){
            return successMonitor();
        }
        
        TcpComm comm = new TcpComm();
        int ret = 0;

        ret = comm.call(this.remoteIp, this.remotePort);

        // 连接远程机器，如果连接失败返回连接失败错误码和错误信息
        if (ret < 0) {
            String result = null;
            try {
                result = this.packAnsHead(
                        IErrorNo.ERR_SENDHOST, "call "
                                + this.remoteIp + "," + this.remotePort + "失败");
            } catch (Exception e) {
                e.printStackTrace();
            }
            
            return result;
        }

        // 发送请求报文
        ret = sendEvent(comm, requestXml);
        if (ret < 0) {
            String result = null;
            try {
                result = this.packAnsHead(
                        IErrorNo.ERR_SENDHOST, "call "
                                + this.remoteIp + "," + this.remotePort + "失败");
            } catch (Exception e) {
                e.printStackTrace();
            }
            return result;
        }

        // 接收应答报文
        String result = receiveEvent(comm);
        if (result == null) {
            try {
                result = this.packAnsHead(
                        IErrorNo.ERR_TIMEOUT,
                        this.remoteIp + "," + this.remotePort + "通讯超时");
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
    private int sendEvent(TcpComm comm, String requestXml) {
        try{
            if(StringUtils.isNotEmpty(requestXml)){
                byte [] content=requestXml.getBytes(charset);
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
    
    
    private String packAnsHead(String errorNo, String errorInfo) {


        return "";
    }
    
    
    private String successMonitor() {


        return "";
    }

    

}
