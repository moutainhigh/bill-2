/********************************************
 * 文件名称: IRgctEcdsStatusService.java
 * 系统名称: 基础技术平台V2.0
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: 2.0.0.1
 * 开发人员: yanjl
 * 开发时间: 2016-6-15 下午01:48:52
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期    修改人员    修改说明
 *********************************************/
package com.herongtech.rc.service.interfaces;

import java.util.List;

import com.herongtech.exception.impl.BizAppException;
import com.herongtech.rc.domain.bean.DraftMapping;
import com.herongtech.rc.domain.bean.RgctEcdsStatus;

public interface IRgctEcdsStatusService {
	
	public static final String HERONGTECH_VERSION="@system 票据管理平台 @version 2.0.0.1 @lastModiDate @describe ";
	
	/**
     * 获取ECDS当前时序状态
     * @return  ECDS当前时序状态
     */
    public String getCurEcdsStatus();
    
    /**
     * 获取ECDS当前参数值
     * @return  参数
     */
    public String getEcdsPValueByPName(String pName);
    
    /**
     * 获取ECDS当前参数值
     * @return  参数
     */
    public String getEcdsDateYYYYMMDD(String pName);
    

    /**
     * 判断参与者是否已登录到ECDS系统
     */
    public boolean isLogon() ;
    
    /**
     * 根据某种类型报文，判断ECDS当前是否可以处理该报文
     */
    public boolean isInWorkSequence(DraftMapping draftMapping);
    
    /**
     * 根据参于者行号,判断是否拥有某种类型报文的权限
     */
    public boolean isHaveAuth(String actorRowNumber, String msgType);
    
    /**
     * 判断当前ECDS状态是否能发送相应的报文
     * 
     * @param actorRowNumber
     * @param draftTemplate
     */
    public void isCanSent(String actorRowNumber, DraftMapping draftMapping) throws BizAppException;
	
	
	/**
	 * 取系统参数信息
	 * 
	 * @param bankNo 参数标识
	 */
	public  RgctEcdsStatus getRgctEcdsStatus(String pname) throws BizAppException;
	
	/**
	 * 插入参数表
	 *
	 */
	public void addRgctEcdsStatus(RgctEcdsStatus RgctEcdsStatus)throws BizAppException;
		
	/**
	 * 修改参数表
	 */
	
	public void modifyRgctEcdsStatus(RgctEcdsStatus RgctEcdsStatus)throws BizAppException;
	
	/**
	 * 插入或者修改参数表
	 *
	 */
	public void addOrUpdateRgctEcdsStatus(RgctEcdsStatus RgctEcdsStatus)throws BizAppException;
	
	/**
	 * 批量插入或者修改参数表
	 *
	 */
	public void addOrUpdateRgctEcdsStatusList(List<RgctEcdsStatus> list)throws BizAppException;
	
	/**
	 * 
	 *
	 */
	public void dealSequenceBefore()throws BizAppException;
	/**
	 * 
	 *
	 */
	public void dealSequenceRun()throws BizAppException;
	/**
	 * 
	 *
	 */
	public void dealSequenceAfter()throws BizAppException;
	/**
	 * 
	 *
	 */
	public void dealSequenceLast()throws BizAppException;
}
