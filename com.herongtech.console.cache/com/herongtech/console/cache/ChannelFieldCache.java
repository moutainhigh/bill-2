/********************************************
 * 文件名称: ChannelFieldCache.java
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
package com.herongtech.console.cache;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import com.herongtech.commons.file.IOUtils;
import com.herongtech.commons.tools.Loader;
import com.herongtech.console.core.common.pubinfo.ChannelFieldMetaData;
import com.herongtech.parser.filter.PathFilter;
import com.herongtech.vfs.FileObject;
import com.herongtech.vfs.VFS;
import com.herongtech.xmlparser.XmlDocument;
import com.herongtech.xmlparser.node.XmlNode;
import com.herongtech.xmlparser.parser.XmlStringParser;

public class ChannelFieldCache {

    private static Map<String, ChannelFieldCache> cacheMap  = new HashMap<String, ChannelFieldCache>();
    private Vector  fieldMetaDataList = new Vector();
    private Map    innerMap=new HashMap();
    private Map    outterMap=new HashMap();
    
    protected ChannelFieldCache(){
    	
    }
    
    private void init(String fileName){
        //从xml文件中装载报文字段描述信息到内存
		URL url = null;
		XmlNode tag =null;
		
		try {
			url = Loader.getResource(fileName + ".xml");
			XmlStringParser parser = new XmlStringParser();
			FileObject file = VFS.resolveFile("file:" + url.getPath());
			XmlDocument doc = parser.parse(IOUtils.readFromInputStream(file.getInputStream(), "UTF-8"));
			tag = doc.getRoot();
			
			PathFilter filter = new PathFilter(tag);
			List <XmlNode> fieldNodeList= filter.findNodeList("/xml/list/field");
			for(int i=0;i<fieldNodeList.size();i++){
		 		ChannelFieldMetaData  fieldMetaData=new ChannelFieldMetaData();
		        fieldMetaData.setInnerTagName(fieldNodeList.get(i).getAttribute("innerTagName", ""));
		        fieldMetaData.setOuterTagName(fieldNodeList.get(i).getAttribute("outerTagName", ""));
		        fieldMetaData.setLength(Integer.parseInt(fieldNodeList.get(i).getAttribute("length", "0")));
		        fieldMetaData.setScale(Integer.parseInt(fieldNodeList.get(i).getAttribute("scale", "0")));
		        fieldMetaData.setType(fieldNodeList.get(i).getAttribute("type", "S").charAt(0));
		        Integer index=(Integer)innerMap.get(fieldMetaData.getInnerTagName());
		        if(index!=null && index>=0){ //同名标签以最后一次的为准
		        	fieldMetaDataList.set(index,fieldMetaData);
		        }else{
		        	innerMap.put(fieldMetaData.getInnerTagName(), fieldMetaDataList.size());
		        	outterMap.put(fieldMetaData.getOuterTagName(), fieldMetaDataList.size());
				    fieldMetaDataList.add(fieldMetaData);
		        }
		    }

		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    /**
     * 初始化,在插件启动的时候加载; 参数为启动加载的配置文件名
     * @return
     */
    synchronized public static ChannelFieldCache getInstance(String cfgName){
    	ChannelFieldCache instance = (ChannelFieldCache)cacheMap.get(cfgName);
		if (instance== null) {
			instance = new ChannelFieldCache();
			instance.init(cfgName);
			
			cacheMap.put(cfgName, instance);
        }
		return instance;
    }
    
    /*
	 * 更新实例。
	 */
     synchronized public static void refresh(String cfgName) {
    	  ChannelFieldCache  instance = new ChannelFieldCache();
    	  instance.init(cfgName);
    	  
    	  cacheMap.put(cfgName, instance);
	}
    
    /**
     * 
     * @param outterTagName
     * @return
     */
    public ChannelFieldMetaData getOutterMetaData(String outterTagName){
    	  Integer index= (Integer)outterMap.get(outterTagName);
    	  if(index==null){
    		  return null;
    	  }else{
    		  return (ChannelFieldMetaData)this.fieldMetaDataList.get(index);
    	  }
    }
    
    /**
     * 
     * @param innerTagName
     * @return
     */
    public ChannelFieldMetaData  getInnerMetaData(String innerTagName){
  	  Integer index= (Integer)innerMap.get(innerTagName);
  	  if(index==null){
  		  return null;
  	  }else{
  		  return (ChannelFieldMetaData)this.fieldMetaDataList.get(index);
  	  }
   }
 
}
