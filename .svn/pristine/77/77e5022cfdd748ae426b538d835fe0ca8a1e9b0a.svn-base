package com.herongtech.creditchannel.client;

import java.io.UnsupportedEncodingException;

import com.herongtech.comm.socket.TcpComm;
import com.herongtech.comm.socket.TcpPackage;
import com.herongtech.commons.tools.DataUtil;
import com.herongtech.commons.tools.DateUtil;
import com.herongtech.console.core.constant.IConstants;
import com.herongtech.console.core.constant.IErrorNo;
import com.herongtech.console.core.constant.IFieldName;
import com.herongtech.corechannel.pkg.TransferData;
import com.herongtech.data.common.data.DataService;
import com.herongtech.data.interfaces.data.DataColumnType;
import com.herongtech.data.interfaces.data.IData;
import com.herongtech.xmlparser.node.XmlNode;


public class CreditClient {
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
    
    
    private String creditSwitch;
    

    
    public String getCreditSwitch() {
        return creditSwitch;
    }

    
    public void setCreditSwitch(String creditSwitch) {
        this.creditSwitch = creditSwitch;
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
    public IData callRemoteService(IData request) {
        if(IConstants.SWITCH_FLAG.CLOSE.equals(this.getCreditSwitch())){
            return successMonitor();
        }
        
        request.beforeFirst();
        request.next();
        TcpComm comm = new TcpComm();
        int ret = 0;

        ret = comm.call(this.remoteIp, this.remotePort);

        // 连接远程机器，如果连接失败返回连接失败错误码和错误信息
        if (ret < 0) {
            IData result = null;
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
        ret = sendEvent(comm, request);
        if (ret < 0) {
            IData result = null;
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
        IData result = receiveEvent(comm);
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
    private int sendEvent(TcpComm comm, IData dataSet) {
        XmlNode xml=TransferData.customXml(dataSet);
        try{
            if(xml!=null){
                byte [] content=xml.toString().getBytes(charset);
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
    private IData receiveEvent(TcpComm comm) {
        // 读5个字节的包头
        IData result = null;
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
//            result = TransferData.xml2DataSet(new String(contentPack.getContent(),charset));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }finally{
            comm.close();
        }
        return result;
    }
    
    
    private IData packAnsHead(String errorNo, String errorInfo) {
        //TODO:失败统一返回报文待确认
        IData dataSet =  DataService.getDefaultInstance().getData();
//        dataSet.addColumn(IFieldName.functionId, DataColumnType.DS_STRING);
//        dataSet.addColumn(IFieldName.exSerial, DataColumnType.DS_STRING);
        dataSet.addColumn(IFieldName.errorNo, DataColumnType.DS_STRING);
        dataSet.addColumn(IFieldName.errorInfo, DataColumnType.DS_STRING);
        dataSet.addColumn(IFieldName.sysDate, DataColumnType.DS_INT);
        dataSet.addColumn(IFieldName.sysTime, DataColumnType.DS_INT);
        dataSet.appendRow();
        dataSet.updateString(IFieldName.errorNo, errorNo);
        dataSet.updateString(IFieldName.errorInfo, errorInfo);
//        dataSet.updateString(IFieldName.functionId, functionId);
//        dataSet.updateString(IFieldName.exSerial, exserial);
        dataSet.updateString(IFieldName.sysDate, DateUtil.getDate());
        dataSet.updateString(IFieldName.sysTime, DateUtil.getTime());
        return dataSet;
    }
    
    
    private IData successMonitor() {
        IData dataSet =  DataService.getDefaultInstance().getData();
        dataSet.addColumn(IFieldName.sysDate, DataColumnType.DS_INT);
        dataSet.addColumn(IFieldName.sysTime, DataColumnType.DS_INT);
        dataSet.appendRow();
        dataSet.updateString(IFieldName.sysDate, DateUtil.getDate());
        dataSet.updateString(IFieldName.sysTime, DateUtil.getTime());
        return dataSet;
    }

    

}
