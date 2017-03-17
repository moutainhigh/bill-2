package com.herongtech.console.service.common.audit.util;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.herongtech.beancontainer.BeanContainerFactory;
import com.herongtech.console.cache.AuditCache;
import com.herongtech.console.cache.TransCache;
import com.herongtech.console.service.common.audit.interfaces.IAuditTrigger;
import com.herongtech.logger.Logger;
import com.herongtech.logger.LoggerFactory;


public class AuditTriggerUtil {

    private static Logger logger = LoggerFactory.getLogger(AuditTriggerUtil.class);
    private static List<AuditTriggers> triggers = new ArrayList<AuditTriggers>();
    
    protected static void addTriggerMapping(AuditTriggers mappings) {
        triggers.add(mappings);
    }
    
    /**
     * 第一岗审批通过调用此方法
     *
     * @param prodNo 产品号
     * @param batchId 批次id
     */
    public static void firstStationAdopt(String prodNo, String batchId) throws Exception {
    	String triggerBeanName = AuditCache.getInstance().getAuditTriggerBeanName(prodNo);
        if (StringUtils.isNotBlank(triggerBeanName)) {
            Object tmp =BeanContainerFactory.getBeanContainer(BeanContainerFactory.class.getClassLoader()).
                    getBean(triggerBeanName);
            if (null!=tmp) {
                try {
                    IAuditTrigger tger = (IAuditTrigger)tmp;
                    tger.firstStationAdopt(prodNo,batchId);
                } catch (Exception e) {
                    logger.error("程序回调失败：产品号："+prodNo+",批次号"+batchId);
                }
            }
        }
    }
    
    /**
     * 最后一岗审批通过调用此方法
     *
     * @param prodNo 产品号
     * @param batchId 批次id
     */
    public static void lastStationAdopt(String prodNo, String batchId) throws Exception {
    	String triggerBeanName = AuditCache.getInstance().getAuditTriggerBeanName(prodNo);
        if (StringUtils.isNotBlank(triggerBeanName)) {
            Object tmp = BeanContainerFactory.getBeanContainer(BeanContainerFactory.class.getClassLoader()).
                    getBean(triggerBeanName);
            if (null!=tmp) {
                try {
                    IAuditTrigger tger = (IAuditTrigger)tmp;
                    tger.lastStationAdopt(prodNo,batchId);
                } catch (Exception e) {
                    logger.error("程序回调失败：产品号："+prodNo+",批次号"+batchId);
                }
            }
        }
    }
    
    /**
     * 任意一岗拒绝调用此方法
     *
     * @param prodNo 产品号
     * @param batchId 批次id
     */
    public static void anyStationReject(String prodNo, String batchId) throws Exception {
    	String triggerBeanName = AuditCache.getInstance().getAuditTriggerBeanName(prodNo);
        if (StringUtils.isNotBlank(triggerBeanName)) {
            Object tmp = BeanContainerFactory.getBeanContainer(BeanContainerFactory.class.getClassLoader()).
                    getBean(triggerBeanName);
            if (null!=tmp) {
                try {
                    IAuditTrigger tger = (IAuditTrigger)tmp;
                    tger.anyStationReject(prodNo,batchId);
                } catch (Exception e) {
                    logger.error("程序回调失败：产品号："+prodNo+",批次号"+batchId);
                }
            }
        }
    }
    
}
