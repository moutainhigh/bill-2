package com.herongtech.console.cache;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.util.ResourceUtils;

import com.herongtech.console.service.callremote.bean.FieldItem;
import com.herongtech.console.service.callremote.bean.Item;


public class TransCache {
    private static TransCache transCache = null;
    
    private Map<String,FieldItem> fieldItemMap = new HashMap<String,FieldItem>();
    
    private final ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
    
    private final Lock r = rwl.readLock();
    
    private TransCache(){
        init();
    }
    
    public static TransCache getInstance(){
        if(transCache == null){
            transCache= new TransCache();
        }
        return transCache;
    }
    
    public static void destroy(){
        transCache.fieldItemMap.clear();
        transCache = null;
    }
    
    public FieldItem getFieldItem(String transNo) {
        try {
            r.lock();
            FieldItem fieldItem =  fieldItemMap.get(transNo);
            return fieldItem;
        }finally {
            r.unlock();
        }
    }
    
    private void init(){
        SAXReader saxReader = new SAXReader();
        InputStream reqIn = null;
        try{
                reqIn = ResourceUtils.getURL("classpath:remotexml/coreRemote.xml").openStream();
                Document doc =  saxReader.read(reqIn);
                Element root = doc.getRootElement();
                List<Element>  eList = root.elements();
                for(Element el:eList){
                    FieldItem ai=new FieldItem();
                    
                    String transNo=el.attributeValue("tran_no");
                    String transName=el.attributeValue("tran_name");
                    String rootEle=el.attributeValue("root_element");
                    String headEle=el.attributeValue("head_element");
                    String bodyEle=el.attributeValue("body_element");
                    Element head=el.element("head");
                    Element body=el.element("body");
                    List<Element> headItem=head.elements();
                    List<Element> bodyItem=body.elements();
                    
                    ai.setTransNo(transNo);
                    ai.setTransName(transName);
                    ai.setRootElement(rootEle);
                    ai.setHeadElement(headEle);
                    ai.setBodyElement(bodyEle);
                    
                    List<Item> headList=new ArrayList<Item> (headItem.size());
                    ai.setHead(headList);
                    List<Item> bodyList=new ArrayList<Item> (bodyItem.size());
                    ai.setBody(bodyList);
                    for(Element item:headItem){
                        Item attr = setItem(item);
                        headList.add(attr);
                    }
                    
                    for(Element item:bodyItem){
                        Item attr = setItem(item);
                        bodyList.add(attr);
                    }
                    fieldItemMap.put(transNo, ai); 
                }

            
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            if(reqIn != null) {
                try {
                    reqIn.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private Item setItem(Element item) {
        Item attr=new Item();
        attr.setDefaultvalue(item.attributeValue("defaultvalue"));
        attr.setExfield(item.attributeValue("exfield"));
        attr.setField(item.attributeValue("field"));
        attr.setFieldName(item.attributeValue("field_name"));
        attr.setJavabeanAlias(item.attributeValue("javabean_alias"));
        attr.setValueType(item.attributeValue("value_type"));
        return attr;
    }
    
}
