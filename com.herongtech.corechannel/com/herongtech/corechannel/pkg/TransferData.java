/********************************************
 * 文件名称: DataSet2XML.java
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
package com.herongtech.corechannel.pkg;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.herongtech.console.cache.TransCache;
import com.herongtech.console.service.callremote.bean.FieldItem;
import com.herongtech.data.common.data.DataService;
import com.herongtech.data.interfaces.data.DataColumnType;
import com.herongtech.data.interfaces.data.IData;
import com.herongtech.xmlparser.node.XmlNode;
import com.herongtech.xmlparser.parser.XmlStringParser;


public class TransferData {
	
	/**
	 * 定制xml
	 * 要求IData 第一行为树干节点
	 * 
	 * @param dataSet
	 * @return
	 */
	public static XmlNode customXml(IData dataSet) {
	    XmlNode rootEle = null;
	    XmlNode headEle = null;
	    XmlNode bodyEle = null;
	    String value = null;
	    String transNo= dataSet.getDataName();
	    
	    FieldItem xmlStruct=TransCache.getInstance().getFieldItem(transNo);
	    try {
	        rootEle=new XmlNode(xmlStruct.getRootElement());
	        XmlNode tagTmp = null;
	        //
	        dataSet.beforeFirst();
	        dataSet.next();
	        
	        //head部分
	        if(StringUtils.isNotEmpty(xmlStruct.getHeadElement())){
	            headEle=new XmlNode(xmlStruct.getHeadElement());
	        }
	        for(int col=1;col<=dataSet.getHeadColumnCount();col++){//dataSet.getColumnCount()
	            
	            String exfield=dataSet.getColumnName(col);
	            tagTmp =  new XmlNode(exfield);
	            if (dataSet.getColumnType(col) == DataColumnType.DS_STRING) {
                    value = dataSet.getString(exfield);
                } else if (dataSet.getColumnType(col) == DataColumnType.DS_INT) {
                    value = String.valueOf(dataSet.getInt(exfield));
                } else if (dataSet.getColumnType(col) == DataColumnType.DS_DOUBLE) {
                    value = String.valueOf(dataSet.getDouble(exfield));
                } else if (dataSet.getColumnType(col) == DataColumnType.DS_LONG) {
                    value = String.valueOf(dataSet.getLong(exfield));
                } else {
                    value = dataSet.getString(exfield);
                }
                tagTmp.setContent(value);
                if(headEle == null){
                    rootEle.addNode(tagTmp);
                }else{
                    headEle.addNode(tagTmp);
                    rootEle.addNode(headEle);
                }
                
	        }
	        //body部分
	        if(dataSet.getColumnCount()>dataSet.getHeadColumnCount()){
	           bodyEle=new XmlNode(xmlStruct.getBodyElement()); 
	            for(int i=1;i<dataSet.getRowCount();i++){
	                dataSet.next();
	                for(int col=dataSet.getHeadColumnCount()+1;col<=dataSet.getColumnCount();col++){
	                    String exfield=dataSet.getColumnName(col);
	                    tagTmp =  new XmlNode(exfield);
	                    if (dataSet.getColumnType(col) == DataColumnType.DS_STRING) {
	                        value = dataSet.getString(exfield);
	                    } else if (dataSet.getColumnType(col) == DataColumnType.DS_INT) {
	                        value = String.valueOf(dataSet.getInt(exfield));
	                    } else if (dataSet.getColumnType(col) == DataColumnType.DS_DOUBLE) {
	                        value = String.valueOf(dataSet.getDouble(exfield));
	                    } else if (dataSet.getColumnType(col) == DataColumnType.DS_LONG) {
	                        value = String.valueOf(dataSet.getLong(exfield));
	                    } else {
	                        value = dataSet.getString(exfield);
	                    }
	                    tagTmp.setContent(value);
	                    bodyEle.addNode(tagTmp);
	                }
	            }
	        }
	        if(bodyEle !=null){
	            rootEle.addNode(bodyEle);
	        }
	       
        } catch (Exception e) {
        }
	    return rootEle;
	}
	  
	
	public static IData xml2DataSet(String packStr){
        System.out.println(packStr);
        
        IData dataSet=null;
        XmlNode xmlReq = null;
        String outTagName=null,value=null;
        try{
            dataSet = DataService.getDefaultInstance().getData();
            
            
            XmlStringParser parser = new XmlStringParser();
            xmlReq = parser.parse(packStr).getRoot();
            List<XmlNode> reqList = xmlReq.getSubNodes();

            for(XmlNode t:reqList){
                outTagName=t.getNodeName();
                //统一放String
                dataSet.addColumn(outTagName, DataColumnType.DS_STRING);
                
            }
        
            dataSet.appendRow();
            for(int col=0; col<reqList.size(); col++){
                XmlNode t = reqList.get(col);
                outTagName=t.getNodeName();
                value=t.getContent();

                dataSet.updateString(col+1, value);
            }
            
            //xml请求包只支持单记录模式,有需要再扩展
            dataSet.beforeFirst();
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
        return dataSet;
    }
}
