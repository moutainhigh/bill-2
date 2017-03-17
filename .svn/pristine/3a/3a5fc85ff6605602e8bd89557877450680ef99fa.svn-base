package com.herongtech.console.core.util;

import com.herongtech.commons.tools.StringUtils;
import com.herongtech.console.service.ServiceFactory;
import com.herongtech.exception.impl.BizAppException;


/**
 * 状态机工具类
 *
 */
public class StatusUtils {

    
    /**
     * 查询前置状态
     * 
     * @return                前置状态
     * @throws Exception 
     */
    public static String[] queryStatus(String conName,String methodName,String pram) throws Exception {
        inParamCheck(conName,methodName);
        return ServiceFactory.getMachineStatusService().queryStatus(conName, methodName, pram);
    }


    private static void inParamCheck(String conName,String methodName) throws BizAppException {
        if(StringUtils.isBlank(conName)){
            throw new BizAppException("ControllerName cannot be null!");
        }
        if(StringUtils.isBlank(methodName)){
            throw new BizAppException("MethodName cannot be null!");
        }
    }
    
    
    /**
     * 变更方法
     * 
     * @return               后置状态
     * @throws Exception 
     */
    public static String handleStatus(String conName,String methodName,String pram,String curStatus) throws Exception {
        inParamCheck(conName,methodName);
        return ServiceFactory.getMachineStatusService().handleStatus(conName, methodName, pram, curStatus);
    }
    
    
    /**
     * 变更方法（不校验前置状态）
     * @return               后置状态
     * @throws Exception 
     */
    public static String handleStatusNoCheck(String conName,String methodName,String pram) throws Exception {
        inParamCheck(conName,methodName);
        return ServiceFactory.getMachineStatusService().handleStatus(conName, methodName, pram, null);
    }
}
