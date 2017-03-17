package com.herongtech.rc.service.rcservice;

import java.sql.SQLException;
import java.util.List;

import com.herongtech.console.core.common.json.Page;
import com.herongtech.console.core.constant.IDict;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.rc.domain.bean.RcBaseSearchBean;
import com.herongtech.rc.domain.bean.RgctBill;
import com.herongtech.rc.domain.bean.RgctBillData;
import com.herongtech.rc.domain.bean.RgctBillHist;
import com.herongtech.rc.domain.bean.RgctBillInfo;
import com.herongtech.rc.domain.bean.RgctEndoHist;
import com.herongtech.rc.domain.bean.RgctMethod;

public interface IRcBaseService {
	
	/**获取票据登记信息
	 */
	public RgctBill getRgctBillById(String rgctId)throws BizAppException ;
	
	/**
	 * 批量获取RC集合
	 * @param ids rgctId逗号分隔
	 * @return
	 * @throws BizAppException
	 */
	public List<RgctBill> getRgctBillList(String ids)throws BizAppException ;
	
	/**
	 * 高级查询返回info、hist结果集
	 * @param sb
	 * @param page
	 * @param method
	 * @param bDelFlag
	 * @return
	 * @throws BizAppException
	 */
	public List<RgctBillData> queryRC(RcBaseSearchBean sb, Page page, RgctMethod method, boolean bDelFlag,String param) throws BizAppException;
	
	public RgctBill getRgctBillByBillNo(String billNo)throws BizAppException ;
	
	/**
	 * 高级查询rc返回info信息
	 * @param sb
	 * @param page
	 * @param method
	 * @param bDelFlag
	 * @return
	 * @throws BizAppException
	 */
	public List<RgctBillInfo> query(RcBaseSearchBean sb, Page page, RgctMethod method, boolean bDelFlag) throws BizAppException;
	/**
	 * rc锁票
	 * @param rgctId
	 * @throws BizAppException
	 */
	public void lock(String  rgctId) throws BizAppException ;
	/**
	 * rc解锁
	 * @param rgctId
	 * @throws BizAppException
	 */
	public void unLock(String  rgctId) throws BizAppException ;
	/**
	 * 根据reqDraftId获取rcBill
	 * @param reqDraftId
	 * @return
	 * @throws BizAppException
	 */
	public RgctBill getRgctBillByReqDraftId(String reqDraftId)throws BizAppException ;
	
	/**
	 * 修改hist信息
	 * @param hist
	 * @throws BizAppException
	 */
	public void updateRgctBillHist(RgctBillHist hist)throws BizAppException ;
	
	/**
	 * 修改info信息
	 * @param info
	 * @throws BizAppException
	 */
	public void updateRgctBillInfo(RgctBillInfo info)throws BizAppException ;
	
	/**
	 * 网银新增票据;质押、贴现、转入专用
	 * @param newBill
	 * @param method
	 * @param param
	 * @return
	 * @throws ServiceException
	 */
	public RgctBill addBill(RgctBill newBill, RgctMethod method, String param) throws BizAppException;
	
	/**
	 * 通用转发（031、036）处理
	 * @param bill
	 * @param methodName
	 * @param param
	 * @throws SQLException 
	 * @throws ServiceException
	 */
	public void commonSignUp(RgctBill bill, String methodName, String param) throws BizAppException ;
	
	
	/**
	 * 034处理
	 * @param bill
	 * @param billHist
	 * @param methodName
	 * @throws ServiceException
	 */
	public void commonTransmission(RgctBill bill, List<RgctEndoHist> billHist, String methodName) throws BizAppException;
	
	/**
	 * 032通用撤回 -- 根据通用撤回报文处理流程
	 * 
	 * @param bill 登记中心票据信息
	 * @throws ServiceException
	 */
	public void commonCancel(RgctBill bill, String param) throws BizAppException;
	
	/**
	 * 收到033通用处理
	 * @param bill
	 * @param methodName
	 * @param param
	 * @throws BizAppException
	 */
	public void commonStatus(RgctBill bill, String methodName, String param) throws BizAppException;
	
	
	/**
	 * 031的033回复处理
	 * @throws SQLException 
	 */
	public void commonStatus(RgctBill bill, String methodName, String param, String signFlag, boolean isInner) throws BizAppException;
	
	/**
	 * 通用业务通知服务 --- 根据通用业务通知报文处理流程
	 * 
	 * @param bill 登记中心票据信息
	 * @param methodName
	 *            登记中心处理服务
	 *            注:系统参与者在收到电子商业汇票系统NPC发来的“通用业务通知报文(041)”后，若报文中所带通知信息为撤票信息，则需修改票据状态为“票据已作废”，
	 *            若为其他通知信息，则不需修改票据状态
	 * @throws ServiceException
	 */
	public void commonBusinessNotification(RgctBill bill, String methodName)
			throws BizAppException ;
	
	/**
	 * 035清分失败恢复报文处理服务
	 * 
	 * @param bill
	 *            登记中心票据信息
	 * @throws ServiceException
	 */
	public void commonExceptionNotify(RgctBill bill,String orgDraftNoReq) throws BizAppException;
	
	/**
	 * 040状态变更
	 * @param billNo
	 * @param statusCode
	 * @param draftDate
	 * @throws BizAppException
	 */
	public void synchronizeBillStatus(String billNo,String statusCode,String draftDate) throws BizAppException;
	
	
}
