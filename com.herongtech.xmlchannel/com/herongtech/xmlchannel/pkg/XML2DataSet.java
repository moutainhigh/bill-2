/********************************************
 * 文件名称: XML2DataSet.java
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
package com.herongtech.xmlchannel.pkg;

import java.util.List;

import com.herongtech.xmlparser.XmlDocument;
import com.herongtech.xmlparser.node.XmlNode;
import com.herongtech.xmlparser.parser.XmlStringParser;
import com.herongtech.console.core.common.pubinfo.ChannelFieldMetaData;
import com.herongtech.data.common.data.DataService;
import com.herongtech.data.interfaces.data.DataColumnType;
import com.herongtech.data.interfaces.data.IData;


public class XML2DataSet {
    /**
     * XML转换
     * @param packBytes
     * @return
     */
	public static IData  xml2DataSet(byte[] packBytes){
		return xml2DataSet(new String(packBytes));
    }
    
	/**
	 * 
	 * @param packStr
	 * @return
	 */
	public static IData xml2DataSet(String packStr){
		System.out.println(packStr);
		
		IData dataSet=null;
		XmlNode xmlReq = null;
		String outTagName=null,value=null;
		try{
			dataSet = DataService.getDefaultInstance().getData();
			
			
			XmlDocument doc = null;
			XmlStringParser parser = new XmlStringParser();
			xmlReq = parser.parse(packStr).getRoot();
			List<XmlNode> reqList = xmlReq.getSubNodes();

			for(XmlNode t:reqList){
				outTagName=t.getNodeName();
				//value=t.getContent();
				ChannelFieldMetaData metaData= XmlChannelFieldCache.getFieldChannelCache().getOutterMetaData(outTagName);
				//在配置文件中没有配置则默认为字符串类型
				if(metaData==null){
					dataSet.addColumn(outTagName, DataColumnType.DS_STRING);
				}else{
					if( metaData.getType()==ChannelFieldMetaData.TYPE_INT ){
						dataSet.addColumn(metaData.getInnerTagName(), DataColumnType.DS_INT);
					}else if(metaData.getType()==ChannelFieldMetaData.TYPE_FLOAT){
						dataSet.addColumn(metaData.getInnerTagName(),DataColumnType.DS_DOUBLE);
					}else {
						dataSet.addColumn(metaData.getInnerTagName(), DataColumnType.DS_STRING);
       		     }
       		  }
			}
		
			dataSet.appendRow();
			for(int col=0; col<reqList.size(); col++){
				XmlNode t = reqList.get(col);
				outTagName=t.getNodeName();
				value=t.getContent();

				ChannelFieldMetaData metaData= XmlChannelFieldCache.getFieldChannelCache().getOutterMetaData(outTagName);
				//在配置文件中没有配置则默认为字符串类型
				if(metaData==null){
					dataSet.updateString(col+1, value);
				}else{
					if( metaData.getType()==ChannelFieldMetaData.TYPE_INT ){
						dataSet.updateLong(col+1, (value==null || value.trim().length()==0)?0L:Long.valueOf(value.trim()));
					}else if(metaData.getType()==ChannelFieldMetaData.TYPE_FLOAT){
						dataSet.updateDouble(col+1,(value==null || value.trim().length()==0)?0.0D:Double.valueOf(value.trim()));
					}else {
						dataSet.updateString(col+1, value);
       		     }
       		  }
			}
			
			//xml请求包只支持单记录模式,有需要再扩展
			dataSet.beforeFirst();
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		return dataSet;
    }
	public static void main(String[] args){
		String str = "<?xml  version=1.0 encoding=UTF-8 ?><lcpt><ErrorNo>0000</ErrorNo><ErrorInfo></ErrorInfo><FunctionId>100001</FunctionId><Exserial>2010010100001</Exserial><SysDate>20100624</SysDate><SysTime>152807</SysTime><Result>123.456</Result><Amt></Amt></lcpt>";
		try{
			IData com = XML2DataSet.xml2DataSet(str);
			com.beforeFirst();
			com.next();
			for(int i=1;i<=com.getColumnCount();i++){
				if (com.getColumnType(i)==DataColumnType.DS_LONG)
					System.out.println(com.getColumnName(i)+"=["+com.getLong(com.getColumnName(i))+"]");
				else if (com.getColumnType(i)==DataColumnType.DS_DOUBLE)
					System.out.println(com.getColumnName(i)+"=["+com.getDouble(com.getColumnName(i))+"]");
				else
					System.out.println(com.getColumnName(i)+"=["+com.getString(com.getColumnName(i))+"]");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
