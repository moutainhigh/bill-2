/********************************************
 * 文件名称: IRgctDraftLogService.java
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


import com.herongtech.rc.domain.bean.RgctDraftLog;
import com.herongtech.exception.impl.BizAppException;

public interface IRgctDraftLogService {
	
	public static final String HERONGTECH_VERSION="@system 票据管理平台 @version 2.0.0.1 @lastModiDate @describe ";
	/**
	 * 取系统参数信息
	 * 
	 * @param logId 参数标识
	 */
	public  RgctDraftLog getRgctDraftLog(String logId) throws BizAppException;
	
	
	/**
	 * 根据请求报文编号获取报文信息
     * @param reqSid
     * @return
     * @throws DAOException
     */
    public RgctDraftLog getrespDraftByReqSid(String reqSid)throws BizAppException;
	
	/**
	 * 查询票据日志,根据请求报文标识号
	 * 
	 * @param reqSid 参数标识
	 */
	public  RgctDraftLog getRgctDraftLogByReqSid(String reqSid) throws BizAppException;
	
	/**
	 * 查询票据日志,根据原发送行报文标识号
	 * @param reqMsgId
	 * @return
	 * @throws BizAppException
	 */
	public  RgctDraftLog getDraftLogByReqMsgId(String reqMsgId) throws BizAppException;
	/**
	 * 取系统参数信息
	 * 
	 * @param reqMsgId 参数标识
	 */
	public  RgctDraftLog getSignDraftLogByReqMsgId(String reqMsgId) throws BizAppException;
	
	/**
	 * 插入参数表
	 *
	 */
	public void addRgctDraftLog(RgctDraftLog rgctDraftLog)throws BizAppException;
	
	/**
	 * 修改参数表
	 */
	
	public void modifyRgctDraftLog(RgctDraftLog rgctDraftLog)throws BizAppException;
	
	/**
     * 查询票据日志,根据响应报文标识号
     * @param respSid
     * @return
     * @throws ServiceException
     */
    public RgctDraftLog getDraftLogByRespSid(String respSid)throws BizAppException;
    
    
    public RgctDraftLog getDraftLogByReqSid(String reqSid)throws Exception;
    
}
