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


public class AuditCache {
    private static AuditCache transCache = null;
    
    private Map<String,String> auditBeanMap = new HashMap<String,String>();
    
    private final ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
    
    private final Lock r = rwl.readLock();
    
    private AuditCache(){
        init();
    }
    
    public static AuditCache getInstance(){
        if(transCache == null){
            transCache= new AuditCache();
        }
        return transCache;
    }
    
    public static void destroy(){
        transCache.auditBeanMap.clear();
        transCache = null;
    }
    
    public String getAuditTriggerBeanName(String prodNo){
	    try{	
    		r.lock();
	    	String triggerBeanName = auditBeanMap.get(prodNo);
	    	return triggerBeanName;
	    }finally{
	    	r.unlock();
	    }
    }

    private void init(){
        SAXReader saxReader = new SAXReader();
        InputStream reqIn = null;
        try{
                reqIn = ResourceUtils.getURL("classpath:audit.auditcallback.xml").openStream();
                Document doc =  saxReader.read(reqIn);
                Element root = doc.getRootElement();
                List<Element>  eList = root.elements();
                for(Element el:eList){
                    String prodNo=el.attributeValue("prodNo");
                    String beanId=el.attributeValue("beanId");
                    auditBeanMap.put(prodNo, beanId); 
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
}
