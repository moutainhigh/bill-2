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
package com.herongtech.mqchannel.pkg;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.herongtech.xmlparser.node.XmlNode;
import com.herongtech.xmlparser.parser.XmlStringParser;
import com.herongtech.console.core.common.pubinfo.ChannelFieldMetaData;
import com.herongtech.console.core.constant.IFieldName;
import com.herongtech.data.common.data.DataService;
import com.herongtech.data.interfaces.data.DataColumnType;
import com.herongtech.data.interfaces.data.IData;

public class XML2DataSet {
    /**
     * XML转换成内部数据总线   
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
		String outTagName=null, value=null;
		String msgHead = null, msgBody = null;
		try{
			dataSet=DataService.getDefaultInstance().getData();
			
			if (packStr.startsWith("{H:")) {
				msgHead = packStr.substring(0, 200);
				msgBody = packStr.substring(200);
			} else {
				msgBody = packStr;
			}
			
			XmlStringParser parser = new XmlStringParser();
			xmlReq = parser.parse(msgBody).getRoot();
			//取报文msgid, 从msgid取报文内容
			XmlNode msgBodyNode = xmlReq.getSubNodes().get(0);
			String functionId = msgBodyNode.getNodeName();
			
			List<XmlNode> reqList = msgBodyNode.getSubNodes();
			Map<String, XmlNode> leafNodes = new HashMap<String, XmlNode>();
			getAllLeafNodes("", reqList, leafNodes);
			
			dataSet.addColumn(IFieldName.ECDS_HEAD, DataColumnType.DS_STRING);
			dataSet.addColumn(IFieldName.ECDS_BODY, DataColumnType.DS_STRING);
			dataSet.addColumn(IFieldName.functionId, DataColumnType.DS_STRING);
			dataSet.addColumn(IFieldName.ECDS_BODYXML, DataColumnType.DS_UNKNOWN);
			
			for(Map.Entry<String, XmlNode> entry : leafNodes.entrySet()){
				outTagName = entry.getKey();
				
				ChannelFieldMetaData metaData = MqChannelFieldCache.getFieldChannelCache().getOutterMetaData(outTagName);
				//在配置文件中没有配置则默认为字符串类型
				if (metaData == null){
					dataSet.addColumn(outTagName, DataColumnType.DS_STRING);
				} else {
					if( metaData.getType()==ChannelFieldMetaData.TYPE_INT ){
						dataSet.addColumn(metaData.getInnerTagName(), DataColumnType.DS_INT);
					}else if(metaData.getType()==ChannelFieldMetaData.TYPE_FLOAT){
						dataSet.addColumn(metaData.getInnerTagName(), DataColumnType.DS_DOUBLE);
					}else {
						dataSet.addColumn(metaData.getInnerTagName(), DataColumnType.DS_STRING);
       		     	}
       		  	}
			}
		
			dataSet.appendRow();
			dataSet.updateString(IFieldName.ECDS_HEAD, msgHead);
			dataSet.updateString(IFieldName.ECDS_BODY, msgBody);
			dataSet.updateString(IFieldName.functionId, functionId);
			//特殊对象, 用于多条处理记录
			dataSet.updateValue(IFieldName.ECDS_BODYXML, msgBodyNode);
			
			for(Map.Entry<String, XmlNode> entry : leafNodes.entrySet()){
				XmlNode t = entry.getValue();
				outTagName = entry.getKey();
				value = t.getContent();

				ChannelFieldMetaData metaData= MqChannelFieldCache.getFieldChannelCache().getOutterMetaData(outTagName);
				//在配置文件中没有配置则默认为字符串类型
				if (metaData == null){
					dataSet.updateString(outTagName, value);
				} else{
					if( metaData.getType()==ChannelFieldMetaData.TYPE_INT ){
						dataSet.updateLong(metaData.getInnerTagName(),  (value==null || value.trim().length()==0)?0L:Long.valueOf(value.trim()));
					}else if(metaData.getType()==ChannelFieldMetaData.TYPE_FLOAT){
						dataSet.updateDouble(metaData.getInnerTagName(), (value==null || value.trim().length()==0)?0.0D:Double.valueOf(value.trim()));
					}else {
						dataSet.updateString(metaData.getInnerTagName(), value);
       		     	}
       		  	}
			}
			dataSet.beforeFirst();
			
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		return dataSet;
    }
	
    /**
     * 获取所有的叶子节点
     * @param packBytes
     * @return
     */
	private static void  getAllLeafNodes(String parentNodeName, List<XmlNode> nodes, Map<String, XmlNode> leafNodes){
		for(XmlNode t:nodes){
			String nodeName = t.getNodeName();
			
			//如果有子节点表示不是叶子节点
			if (t.getSubNodes().size() > 0){
				getAllLeafNodes(parentNodeName + nodeName+".", t.getSubNodes(), leafNodes);
			} else{
				//节点key为上级节点+"."+下级节点
				leafNodes.put(parentNodeName.substring(0, parentNodeName.length()-1), t);
			}
		}
    }
	
	public static void main(String[] args){
		String str = "{H:019968        CD        3051000001  CD        9968        3051000001  20160622020329054                 10000001                                3N00000477dba65a05bf62cdb754c1a1512d529917        }" + 
				"<?xml version=\"1.0\" encoding=\"UTF-8\"?><Document><CommercialDraftSystemStatusNotification><MsgId><Id>0000000099682016062110000001</Id>" + 
				"<CreDtTm>2016-06-22T14:03:29</CreDtTm>" + 
				"</MsgId><SysSts><OrgnlSysDt>2016-06-20</OrgnlSysDt><OrgnlSysSts>00</OrgnlSysSts><SysDt>2016-06-21</SysDt><SysSts>10</SysSts><NxtSysDt>2016-06-22</NxtSysDt><BizRefTm>18:00:00</BizRefTm></SysSts></CommercialDraftSystemStatusNotification></Document>";
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
