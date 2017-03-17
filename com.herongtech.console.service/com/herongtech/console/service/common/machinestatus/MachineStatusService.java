package com.herongtech.console.service.common.machinestatus;

import org.apache.commons.lang.StringUtils;

import com.herongtech.console.cache.MachineStatusCache;
import com.herongtech.console.domain.common.bean.MachineStatus;
import com.herongtech.exception.impl.BizAppException;


public class MachineStatusService {

    public String[] queryStatus(String conName,String methodName,String pram)throws BizAppException{
        
        MachineStatus ms=null;
        String key;
        if(null==pram){
        	key =conName+methodName;
        }else{
        	key =conName+methodName+pram;        	
        }
		ms = MachineStatusCache.getInstance().getmachinemapvalue(key);
        if(ms != null){
            return ms.getBeforeStatus().split(",");
        }
        return null;
    }
    
    /**
     * @param conName
     * @param methodName
     * @param pram
     * @param curStauts 当前状态，前置状态检查用（为空则不检查前置状态）
     * @return
     * @throws BizAppException
     */
    public String handleStatus(String conName,String methodName,String pram,String curStauts)throws BizAppException{
        MachineStatus ms=null;
        String key;
        if(null==pram){
        	key =conName+methodName;
        }else{
        	key =conName+methodName+pram;        	
        }
		ms = MachineStatusCache.getInstance().getmachinemapvalue(key);
        if(ms != null){
            //
            if(StringUtils.isNotEmpty(curStauts)){
                if(!StringUtils.contains(ms.getBeforeStatus(), curStauts)){
                    throw new BizAppException("前置状态检查不符! 正确状态：【"+ms.getBeforeStatus()+"】,当前状态：【"+curStauts+"】");
                }
            } 
            return ms.getAfterStatus();
        }
        
        return null;
    }
}
