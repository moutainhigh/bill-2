package com.herongtech.webservice.core;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import org.apache.cxf.Bus;
import org.apache.cxf.BusFactory;
import org.apache.cxf.frontend.ServerFactoryBean;
import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.transport.servlet.CXFNonSpringServlet;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.util.ResourceUtils;

import com.herongtech.beancontainer.BeanContainerFactory;

public class WebServiceServlet extends CXFNonSpringServlet {

    public static final String CONFIG_LOCATION_PARAM = "contextConfigLocation";

    @Override
    public void loadBus(ServletConfig sc)throws ServletException {
        super.loadBus(sc);
        String configLocation = sc.getInitParameter(
                CONFIG_LOCATION_PARAM);
        if (configLocation != null) {
            InputStream is=null;
            try {
                SAXReader saxReader = new SAXReader();
                is = ResourceUtils.getURL(configLocation).openStream();
                Document doc = saxReader.read(is);
                Element root = doc.getRootElement();
                List<Element> eList = root.elements();
                // 全局配置
                Bus bus = getBus();
                // 使用全局配置
                BusFactory.setDefaultBus(bus);
                LoggingInInterceptor in= new LoggingInInterceptor();
                LoggingOutInterceptor out=new LoggingOutInterceptor();
                for (Element el : eList) {
                    ServerFactoryBean serverFactoryBean = new ServerFactoryBean(); // server工厂
                    serverFactoryBean.setAddress(el.attributeValue("address")); // 服务请求路径
                    String implementor=el.attributeValue("implementor");
                    Object serviceBean=null;
                    if(implementor.startsWith("#")){
                        serviceBean=BeanContainerFactory.getBeanContainer(BeanContainerFactory.class.getClassLoader()).
                                getBean(implementor.substring(1));
                        
                    }else{
                        serviceBean=Class.forName(implementor).newInstance();
                    }
                    serverFactoryBean.setServiceBean(serviceBean);
                   
                   Class<?> c=serviceBean.getClass();
                   Class<?> interfaces[] =c.getInterfaces();//要求发布webservice的接口只能有1个
                   serverFactoryBean.setServiceClass(interfaces[0]);// 接口类
                   serverFactoryBean.getInInterceptors().add(in);
                   serverFactoryBean.getOutInterceptors().add(out);
                   serverFactoryBean.create();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }finally{
                if(is != null) {
                    try {
                        is.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

    }

}
