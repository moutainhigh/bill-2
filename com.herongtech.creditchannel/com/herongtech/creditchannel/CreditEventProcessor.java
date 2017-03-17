package com.herongtech.creditchannel;

import java.util.ArrayList;
import java.util.List;

import com.herongtech.cepcore.CEPCore;
import com.herongtech.cepcore.impl.AbstractEventProcessor;
import com.herongtech.commons.tools.RegexUtil;
import com.herongtech.context.Context;
import com.herongtech.creditchannel.client.CreditClient;
import com.herongtech.data.interfaces.data.IData;
import com.herongtech.event.Event;
import com.herongtech.event.ServiceInfo;
import com.herongtech.exception.impl.BizBaseException;


public class CreditEventProcessor extends AbstractEventProcessor{
    
    private CreditProcessor creditProcessor;
    private String routetable;
    
    
    public CreditProcessor getCreditProcessor() {
        return creditProcessor;
    }


    
    public void setCreditProcessor(CreditProcessor creditProcessor) {
        this.creditProcessor = creditProcessor;
    }


    public String getRoutetable() {
        return routetable;
    }

    
    public void setRoutetable(String routetable) {
        this.routetable = routetable;
    }

    @Override
    public List<String> getRegex() {
        List  list = new ArrayList();
        String[] routetables = routetable.split(";");
        for (String route:routetables){
            list.add(RegexUtil.getRegexString(route));      
        }
        return list;
    }

    @Override
    public List<ServiceInfo> getServiceInfos() {
        return new ArrayList();
    }

    @Override
    public int getWeight() {
        return 0;
    }

    @Override
    public boolean isRead() {
        return true;
    }

    @Override
    public void process(Event event) throws BizBaseException {
     // 同步调用远程服务器上的一个服务，并得到应答
        IData dataset = event.getServiceRequest().getContext().get(
                            Context.REQUEST_PARAM);
        CreditClient client = creditProcessor.getChannelService().getClient();
        IData result = client.callRemoteService(dataset);
        event.getServiceRequest().getContext().put(Context.RESPONSE_PARAM, result);
    }

    @Override
    public void setCepCore(CEPCore arg0) {
        
    }

    @Override
    public void setRead(boolean arg0) {
        
    }

}
