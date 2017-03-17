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
package com.herongtech.xmlchannel.pkg;

import java.util.ArrayList;
import java.util.Vector;

import com.herongtech.xmlparser.node.XmlNode;
import com.herongtech.console.core.common.pubinfo.ChannelFieldMetaData;
import com.herongtech.console.core.constant.IFieldName;
import com.herongtech.data.common.data.DataService;
import com.herongtech.data.interfaces.data.DataColumnType;
import com.herongtech.data.interfaces.data.IData;


public class DataSet2XML {

	/**
	 * 
	 * @param dataSet
	 * @return
	 */
	public static XmlNode DataSet2xml(IData dataSet) {
		Vector<String> xmlHead=new Vector<String>();
		XmlNode xmlContent = null;
		String value = null;
		String targName=null;
		String innerTagName=null;
		
		// 将DataSet转换成xml
		try {
			//xml = new XmlNode().addContent("<?xml version='1.0' encoding='UTF-8'?>");
			xmlContent = new XmlNode("Document");
			XmlNode tagTmp = null;
			//设置报文头
			xmlContent.addAll(packAnsHead2XML(dataSet, xmlHead));
			
			dataSet.beforeFirst();
			if(dataSet.getRowCount() > 1){
				//多记录
				XmlNode xmlList = new XmlNode("list");
				for (int row = 1; row <= dataSet.getRowCount(); row++) {
					dataSet.next();
					XmlNode xmlRecord = new XmlNode("record");

					for (int col = 1; col <= dataSet.getColumnCount(); col++) {
						innerTagName = dataSet.getColumnName(col);
						ChannelFieldMetaData metaData = XmlChannelFieldCache.getFieldChannelCache().getInnerMetaData(innerTagName);
						if (metaData != null) {
							targName = metaData.getOuterTagName();
						} else {
							targName = innerTagName;
						}
						if(xmlHead.contains(innerTagName))
							continue;

						if (dataSet.getColumnType(col) == DataColumnType.DS_STRING) {
							value = dataSet.getString(innerTagName);
						} else if (dataSet.getColumnType(col) == DataColumnType.DS_INT) {
							value = String.valueOf(dataSet.getInt(innerTagName));
						} else if (dataSet.getColumnType(col) == DataColumnType.DS_DOUBLE) {
							value = String.valueOf(dataSet.getDouble(innerTagName));
						} else if (dataSet.getColumnType(col) == DataColumnType.DS_LONG) {
							value = String.valueOf(dataSet.getLong(innerTagName));
						} else {
							value = dataSet.getString(innerTagName);
						}

						tagTmp =  new XmlNode(targName);
						tagTmp.setContent(value);
						xmlRecord.addNode(tagTmp);
					}
					xmlList.addNode(xmlRecord);
				}
				xmlContent.addNode(xmlList);
			}else if(dataSet.getRowCount() == 1){
				//单记录
				dataSet.next();
				for (int col = 1; col <= dataSet.getColumnCount(); col++) {
					innerTagName = dataSet.getColumnName(col);
					ChannelFieldMetaData metaData =XmlChannelFieldCache.getFieldChannelCache().getInnerMetaData(innerTagName);
					if (metaData != null) {
						targName = metaData.getOuterTagName();
					} else {
						targName = innerTagName;
					}
					
					if(xmlHead.contains(innerTagName))
						continue;

					if (dataSet.getColumnType(col) == DataColumnType.DS_STRING) {
						value = dataSet.getString(innerTagName);
					} else if (dataSet.getColumnType(col) == DataColumnType.DS_INT) {
						value = String.valueOf(dataSet.getInt(innerTagName));
					} else if (dataSet.getColumnType(col) == DataColumnType.DS_DOUBLE) {
						value = String.valueOf(dataSet.getDouble(innerTagName));
					} else if (dataSet.getColumnType(col) == DataColumnType.DS_LONG) {
						value = String.valueOf(dataSet.getLong(innerTagName));
					} else {
						value = dataSet.getString(innerTagName);
					}

					tagTmp =  new XmlNode(targName);
					tagTmp.setContent(value);
					xmlContent.addNode(tagTmp);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return xmlContent;
	}
	/**
	 * 设置应答头
	 * @param dataset
	 * @param xmlHead
	 * @return
	 */
	private static ArrayList<XmlNode> packAnsHead2XML(IData dataset, Vector<String> xmlHead) {
		ArrayList<XmlNode> arrayTag = new ArrayList<XmlNode>();
		XmlNode xmlTag=null;
		xmlHead.clear();
		//对外部显示名称
		String[] packNeedHeadName={IFieldName.errorNo, IFieldName.errorInfo, IFieldName.functionId, 
					IFieldName.exSerial, IFieldName.sysDate, IFieldName.sysTime};
		String[] packNoNeedHeadName={IFieldName.totalRows, IFieldName.currentPage, 
				IFieldName.retNum, IFieldName.totNum,IFieldName.errNum}; 
		
		try{
			if(dataset.getRowCount()>0){
				dataset.beforeFirst();
				dataset.next();
				for(int i=0;i<packNeedHeadName.length;i++){
					String dataColName = packNeedHeadName[i];
					ChannelFieldMetaData metaData = XmlChannelFieldCache.getFieldChannelCache().getInnerMetaData(packNeedHeadName[i]);

					if (metaData != null) {
						dataColName = metaData.getOuterTagName();
					}
					xmlTag = new XmlNode(dataColName);
					xmlTag.setContent(dataset.getString(packNeedHeadName[i]));
					arrayTag.add(xmlTag);
					xmlHead.add(dataColName);
				}
				
				for(int i=0;i<packNoNeedHeadName.length;i++){
					String dataColName = packNoNeedHeadName[i];
					
					//字段存在
					if (dataset.getString(packNoNeedHeadName[i]) != null &&
							!dataset.getString(packNoNeedHeadName[i]).equals("")){
						ChannelFieldMetaData metaData = XmlChannelFieldCache.getFieldChannelCache().getInnerMetaData(packNoNeedHeadName[i]);
					
						if (metaData != null) {
							dataColName = metaData.getOuterTagName();
						}
						xmlTag = new XmlNode(dataColName);
						xmlTag.setContent(dataset.getString(packNoNeedHeadName[i]));
						arrayTag.add(xmlTag);
						xmlHead.add(dataColName);
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return arrayTag;
	}

	public static void main(String[] args){
		IData cd = DataService.getDefaultInstance().getData();
		cd.addColumn("FunctionId", DataColumnType.DS_STRING);
		cd.addColumn("ExSerial", DataColumnType.DS_STRING);
		cd.addColumn("SysDate", DataColumnType.DS_STRING);
		cd.addColumn("SysTime", DataColumnType.DS_STRING);
		cd.addColumn("ErrorNo", DataColumnType.DS_STRING);
		cd.addColumn("ErrorInfo", DataColumnType.DS_STRING);
		cd.addColumn("Result", DataColumnType.DS_STRING);
		cd.appendRow();
		cd.updateString(1, "100001");
		cd.updateString(2, "2010010100001");
		cd.updateString(3, "20100101");
		cd.updateString(4, "121259");
		cd.updateString(5, "0000");
		cd.updateString(6, "");
		cd.updateString(7, "123.456");
	
		System.out.println("------------单记录------------------");
		System.out.println("CommonDataset count=["+ cd.getRowCount() +"]");
//		System.out.println(DataSet2xml(cd).format());
		System.out.println(DataSet2xml(cd).toString());

		System.out.println("------------多记录------------------");
		
		try{
			cd.updateString("Result", "999.999");
		}catch(Exception e){
			e.printStackTrace();
		}
		cd.appendRow();
		System.out.println("CommonDataset count=["+ cd.getRowCount() +"]");
		DataService.printData(cd);
//		System.out.println(DataSet2xml(cd).format());
		System.out.println(DataSet2xml(cd).toString());
	}
}
