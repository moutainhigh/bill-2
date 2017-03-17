package com.herongtech.xmlchannel.test;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URL;

import com.herongtech.comm.socket.TcpComm;
import com.herongtech.comm.socket.TcpPackage;
import com.herongtech.commons.tools.DataUtil;
import com.herongtech.xmlchannel.XmlChannelLog;


public class XMLTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		
		for(int i=0;i<1;i++){
		String strRemoteIP="127.0.0.1";
		int iRemotePort=7900;
		int iTimeout=50;
		URL url = null;
		InputStream in=null;
		String StrReq=null;
		byte[] StrReqPack=null;
		byte[] StrAnsPack=null;
		int anslength=0;
		TcpComm comm=null;
		int lRet=0;
		String charset="utf-8";
		TcpPackage headPack=null;
		FileOutputStream xmlOut=null;
		
		try{
			//读文件，获取请求
			/*url = Loader.getResource("bin/trans100002.xml");
			in=url.openStream();
			
			tag=new XmlParser().parseURL(url);
			tag.getTagWithPath("");
			StrReq = tag.toString();
			
			in.close();*/

			StrReq = "<?xml version=1.0 encoding=UTF-8 ?><Document><functionId>101001</functionId><exSerial>20100625</exSerial></Document>";

			System.out.println("-----------请求包------------");
			System.out.println(StrReq);
			
			//建立连接,发送请求
			comm = new TcpComm();
			comm.setTimeOut(iTimeout);
			lRet=comm.call(strRemoteIP, iRemotePort);
			if(lRet!=0){
				System.out.println("建立连接失败！");
				return;
			}

			StrReqPack = new byte[6+StrReq.getBytes(charset).length];
			System.arraycopy(("X" + DataUtil.fix0BeforeString(String.valueOf(StrReq.getBytes(charset).length),5)).getBytes(charset), 0, StrReqPack, 0, 6);
			System.arraycopy(StrReq.getBytes(charset), 0, StrReqPack, 6, StrReq.getBytes(charset).length);
			lRet = comm.sendMsg(StrReqPack);
			if (lRet<0){
				System.out.println("发送信息失败！");
				return;
			}

			//接收返回信息
			headPack = comm.recvPack(6);
			anslength = Integer.parseInt(new String(headPack.getContent(),1,5).trim());
			headPack = comm.recvPack(anslength);
			StrAnsPack = new byte[anslength];
			StrAnsPack = headPack.getContent();

			//此处需要设置返回字符集，同后台一致
			System.out.println("------------应答("+charset+")-----------");
			System.out.println(new String(StrAnsPack,"gbk"));
			
			/*if(!(new File("1.xml").exists())){
				new File("1.xml").createNewFile();
			}

			xmlOut = new FileOutputStream("bin/1.xml");
			xmlOut.write(StrAnsPack);*/
//			System.out.println(100.12+30.67);
//			System.out.println(2*(100.12d+30.67d));
			XmlChannelLog.getLogNoCache().infoMessage("xmlchannellog1");

		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				if (comm != null) {
					comm.close();
				}
				if (in != null) {
					in.close();
				}
				if (xmlOut != null) {
					xmlOut.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}}

}
