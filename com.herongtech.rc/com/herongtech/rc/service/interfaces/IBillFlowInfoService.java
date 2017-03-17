package com.herongtech.rc.service.interfaces;

import java.util.List;

import com.herongtech.console.core.common.json.Page;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.rc.domain.bean.BillFlowInfo;
import com.herongtech.rc.domain.bean.BillFlowInfoSearchBean;
import com.herongtech.rc.domain.bean.BillFlowQueryResult;
import com.herongtech.rc.domain.bean.RgctBill;
import com.herongtech.rc.domain.bean.RgctDraftLog;

public interface IBillFlowInfoService {

	/**
	 * @Description: 根据searchBean查询
	 * 
	 * @param BillFlowInfoSearchBean
	 * @return Page
	 * @throws ServiceException
	 */
	public Page searchRgctBillInfoByBillFlowInfoSearchBean(BillFlowInfoSearchBean billFlowInfoSearchBean,
			Page page);
	
	//public void addBillFlowInfoForCancel(RgctBill rgctBill, String draftNoReq, String operResult) throws ServiceException;
	
	/**
	 * 提供接口查询list
	 * @throws Exception 
	 */
	public List<BillFlowQueryResult> queryBillFlowResult(BillFlowInfoSearchBean query,Page page) throws Exception;
	
	/**
	 * 申请033处理
	 * @param orgnlMsgId 申请报文ID
	 * @param curstatus 0：失败，1：成功
	 * @param prcMsg 
	 * @param bill
	 * @throws BizAppException 
	 * @throws ServiceException
	 */
	public void executeBillFlowInfo033ForCommonApply(String orgnlMsgId,String curstatus,String prcMsg,RgctBill bill,RgctDraftLog orgnlDraftLog) throws BizAppException;
	
	/**
	 * 031-033处理 （只处理同业的票）
	 * @param orgnlMsgId
	 * @param curstatus
	 * @param prcMsg
	 * @param isInner
	 * @param signFlag
	 * @param bill
	 * @throws BizAppException 
	 * @throws ServiceException
	 */
	public void executeBillFlowInfo033For031(String orgnlMsgId,String curstatus,String prcMsg,boolean isInner,String signFlag,RgctBill bill,RgctDraftLog orgnlDraftLog) throws BizAppException;
	
	/**
	 * 构建BillFlowInfo
	 * @param busiType 业务种类
	 * @param rgctBill
	 * @return
	 * @throws Exception 
	 * @throws ServiceException
	 */
	public BillFlowInfo transFormBillFlowInfo(RgctBill rgctBill,String busiType,String operType,String reqSid) throws Exception;
	
	/**
	 * 保存flow
	 * @param billFlowInfo
	 * @return
	 * @throws BizAppException 
	 * @throws ServiceException
	 */
	public void addBillFlowInfo(BillFlowInfo billFlowInfo) throws BizAppException;
	
	/**
	 * 申请方收到转发的031
	 * @param orgnlMsgId
	 * @param signFlag
	 * @param bill
	 * @throws BizAppException 
	 * @throws ServiceException
	 */
	public void executeBillFlowInfo031Trans(String orgnlMsgId,String signFlag,RgctBill bill) throws BizAppException;
	
	/**
	 * 申请方 032-033
	 * @param orgnlMsgId
	 * @param curstatus
	 * @param prcMsg
	 * @param bill
	 * @throws BizAppException 
	 * @throws ServiceException
	 */
	public void executeBillFlowInfo033For032(String orgnlMsgId,String curstatus,String prcMsg,RgctBill bill) throws BizAppException;
	
	/**
	 * 签收方收到转发的032
	 * @param orgnlMsgId
	 * @param bill
	 * @throws BizAppException 
	 * @throws ServiceException
	 */
	public void executeBillFlowInfo032Trans(String orgnlMsgId,RgctBill bill) throws BizAppException;
	
	/**
	 * 034BillFlowInfo
	 * @param busiType 业务种类
	 * @param rgctBill
	 * @return
	 * @throws ServiceException
	 */
	public void executeBillFlowInfo034Trans(RgctBill rgctBill,String busiType, String reqSid)throws Exception ;
	
	/**
	 *  035-033处理-- 因为034处理时 直接拒绝，不会保存申请流水，所以在此处进行处理
	 * @param reqSid
	 * @param curstatus
	 * @param prcMsg
	 * @param bill
	 * @throws ServiceException
	 */
	public void executeBillFlowInfo033For035(String reqSid,String curstatus,String prcMsg,RgctBill bill)  throws BizAppException;
	
	/**
	 * 收到转发的035
	 * @param orgnlMsgId
	 * @param bill
	 * @throws BizAppException
	 */
	public void executeBillFlowInfo035Trans(String orgnlMsgId,RgctBill bill,String exceptionCN)  throws BizAppException;
	
	/**
	 * 004-033处理
	 * @param orgnlMsgId
	 * @param curstatus
	 * @param prcMsg
	 * @param bill
	 * @throws BizAppException
	 */
	public void executeBillFlowInfo033For004(String orgnlMsgId,String curstatus,String prcMsg,RgctBill bill,RgctDraftLog orgnlDraftLog)  throws BizAppException;
	
}
