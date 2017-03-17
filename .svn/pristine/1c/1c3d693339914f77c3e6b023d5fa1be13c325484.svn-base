package com.herongtech.console.service.common.audit.interfaces;


/**
 * 审批处理回调接口
 *
 */
public interface IAuditTrigger {

    /**
     * 第一岗审批通过调用此方法
     *
     * @param prodNo 产品号
     * @param batchId 批次id
     * @throws Exception
     */
    public void firstStationAdopt(String prodNo, String batchId) throws Exception;
    
    /**
     * 最后一岗审批通过调用此方法
     *
     * @param prodNo 产品号
     * @param batchId 批次id
     * @throws Exception
     */
    public void lastStationAdopt(String prodNo, String batchId) throws Exception;
    
    /**
     * 任意一岗拒绝调用此方法
     *
     * @param prodId 产品号
     * @param batchId 批次id
     * @throws Exception
     */
    public void anyStationReject(String prodNo, String batchId) throws Exception;
    
}
