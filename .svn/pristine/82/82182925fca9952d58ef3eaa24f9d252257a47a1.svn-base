package com.herongtech.webservice.webBank.drwr;

import java.util.HashMap;
import java.util.Map;

import com.herongtech.beancontainer.BeanContainerFactory;
import com.herongtech.console.core.util.XmlBeanUtil;
import com.herongtech.context.Context;
import com.herongtech.context.impl.ContextImpl;
import com.herongtech.context.util.ContextUtil;
import com.herongtech.event.Event;
import com.herongtech.service.IServiceClient;
import com.herongtech.webservice.bean.DemoBean;
import com.herongtech.xmlchannel.pkg.ProResult;


public class ElecDrwrService implements IElecDrwrService {

    @Override
    public ProResult draftRegisterEcdsRequest(DemoBean bean) {

        Map<String, Class> clazzMap = new HashMap<String, Class>();
        clazzMap.put("Document", DemoBean.class);
        String reqXml=XmlBeanUtil.bean2xml(clazzMap, bean);
        Context context = new ContextImpl();
        ContextUtil.setRequestData(context,reqXml );
        Event event = Event.createEvent(bean.getTrans().getFunctionId(), context);
        //获取本地服务处理通道
        IServiceClient serviceClient = BeanContainerFactory.getBeanContainer(
        this.getClass().getClassLoader()).getBean("localServiceClient");
        //获取应答事件
        Object response=serviceClient.sendReceive(event).getResponse();
        
        
        return null;
    }

    @Override
    public String sayHello(String name) {
        return "hello "+name;
    }

}
