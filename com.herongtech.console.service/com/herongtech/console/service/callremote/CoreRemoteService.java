package com.herongtech.console.service.callremote;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.herongtech.beancontainer.BeanContainerFactory;
import com.herongtech.console.cache.TransCache;
import com.herongtech.console.core.constant.IConstants;
import com.herongtech.console.core.util.ReflectionUtils;
import com.herongtech.console.domain.acct.bean.AccountResponseDTO;
import com.herongtech.console.domain.acct.bean.AcctFlow;
import com.herongtech.console.service.callremote.bean.AcctRequest;
import com.herongtech.console.service.callremote.bean.FieldItem;
import com.herongtech.console.service.callremote.bean.Item;
import com.herongtech.console.service.callremote.interfaces.ICoreRemoteService;
import com.herongtech.context.Context;
import com.herongtech.context.impl.ContextImpl;
import com.herongtech.context.util.ContextUtil;
import com.herongtech.corechannel.pkg.TransferData;
import com.herongtech.data.common.data.DataService;
import com.herongtech.data.interfaces.data.DataColumnType;
import com.herongtech.data.interfaces.data.IData;
import com.herongtech.event.Event;
import com.herongtech.service.IServiceClient;

public class CoreRemoteService implements ICoreRemoteService{

    @Override
    public AccountResponseDTO account(AcctRequest request) throws Exception {
        AccountResponseDTO respDto=new AccountResponseDTO();
        IData data = DataService.getDefaultInstance().getData();
        data.setDataName(request.getTransNo());
        FieldItem columns= TransCache.getInstance().getFieldItem(request.getTransNo());
        if(columns == null)
            throw new Exception("交易未配置");
        List<Item>  heads= columns.getHead();
        for(Item it:heads){
            setValue(1,request.getPropertyValue(it.getJavabeanAlias()), data, it);
        }

        data.setHeadColumnCount(heads.size());
        data.appendRow();
        List<Item>  bodys= columns.getBody();
        for(int i=0;i<request.getList().size();i++){
            for(Item it:bodys){
               Object obj= request.getPropertyValue(it.getJavabeanAlias());
               if(obj instanceof java.util.List){
                   ArrayList list=(ArrayList)obj;
                   setValue(i+2,list.get(i), data, it);
               }else{
                   setValue(i+2,obj, data, it);
               }
               
            } 
        }
        
        System.out.println(TransferData.customXml(data).toString());
        
        Context context = new ContextImpl();
        ContextUtil.setRequestData(context, TransferData.customXml(data).toString());
        Event event = Event.createEvent(IConstants.SYSTEM_CODE.CORE+request.getTransNo(), context);
        //获取本地服务处理通道
        IServiceClient serviceClient = BeanContainerFactory.getBeanContainer(
        this.getClass().getClassLoader()).getBean("localServiceClient");
        //获取应答事件
        Object response=serviceClient.sendReceive(event).getResponse();
        respDto.setSuccess(true);
        return respDto;
    }

    private void setValue(int row,Object dataSource, IData data, Item it) {
        if (row > data.getRowCount()) {
            data.appendRow();
        }
        data.locateLine(row);
        
        if(data.findColumn(it.getExfield())>0){
            if(it.getValueType().equals(IConstants.DATA_TYPE.STRING)){
                data.updateString(it.getExfield(), getFiedlValue(dataSource, it).toString() );
            }else if(it.getValueType().equals(IConstants.DATA_TYPE.DOUBLE)){
                data.updateDouble(it.getExfield(), Double.valueOf(getFiedlValue(dataSource, it).toString()));
            }else{
                data.updateInt(it.getExfield(), Integer.valueOf(getFiedlValue(dataSource, it).toString()));
            }
        }else{
            if(it.getValueType().equals(IConstants.DATA_TYPE.STRING)){
                data.addColumn(it.getExfield(), DataColumnType.DS_STRING);
                data.updateString(it.getExfield(), getFiedlValue(dataSource, it).toString() );
            }else if(it.getValueType().equals(IConstants.DATA_TYPE.DOUBLE)){
                data.addColumn(it.getExfield(),DataColumnType.DS_DOUBLE);
                data.updateDouble(it.getExfield(), Double.valueOf(getFiedlValue(dataSource, it).toString()));
            }else{
                data.addColumn(it.getExfield(),DataColumnType.DS_INT);
                data.updateInt(it.getExfield(), Integer.valueOf(getFiedlValue(dataSource, it).toString()));
            } 
        }
        
        
    }
    
    private Object getFiedlValue(Object dataSource,Item it){
        if(StringUtils.isEmpty(it.getDefaultvalue())){
            Object obj=ReflectionUtils.getFieldValue(dataSource, it.getField());
            if(obj ==null){
                if(it.getValueType().equals(IConstants.DATA_TYPE.STRING)){
                    obj="";
                }else if(it.getValueType().equals(IConstants.DATA_TYPE.DOUBLE)){
                    obj=0D;
                }else{
                    obj=0;
                }  
            }
            return obj;
        }else{
            return it.getDefaultvalue();
        }
    }
    

    @Override
    public AccountResponseDTO reverseAccount(AcctFlow af) {
        return null;
    }
    

    
}
