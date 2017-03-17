package com.herongtech.console.cache;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.util.ResourceUtils;


public class TokenCache {
    private static TokenCache tokenCache = null;
    
    private List<String> actionUrlsList = new ArrayList<String>();
    
    private final ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
    
    private final Lock r = rwl.readLock();
    
    private TokenCache(){
        init();
    }
    
    public static TokenCache getInstance(){
        if(tokenCache == null){
        	tokenCache= new TokenCache();
        }
        return tokenCache;
    }

	public List<String> getActionUrlsList() {
		return actionUrlsList;
	}

	public void setActionUrlsList(List<String> actionUrlsList) {
		this.actionUrlsList = actionUrlsList;
	}

	public static void destroy(){
    	tokenCache.actionUrlsList.clear();
    	tokenCache = null;
    }
    
    private void init(){
        SAXReader saxReader = new SAXReader();
        InputStream reqIn = null;
        try{
                reqIn = ResourceUtils.getURL("classpath:tokenMethod.xml").openStream();
                Document doc =  saxReader.read(reqIn);
                Element root = doc.getRootElement();
                List<Element>  eList = root.elements();
                for(Element el:eList){
                    String url=el.attributeValue("url");
                    actionUrlsList.add(url); 
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
