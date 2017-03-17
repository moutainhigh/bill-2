package com.herongtech.console.service.busiservice.acpt;

import java.sql.SQLException;
import java.util.List;

import com.herongtech.console.core.common.json.Page;
import com.herongtech.console.domain.acpt.bean.AcptApplyInfo;
import com.herongtech.console.domain.acpt.bean.AcptBillInfo;
import com.herongtech.console.domain.acpt.bean.AcptColltnReg;
import com.herongtech.console.domain.acpt.bean.AcptQueryCondition;
import com.herongtech.console.domain.acpt.bean.AcptSspdReg;
import com.herongtech.console.domain.bean.UserLoginfo;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.rc.domain.bean.RgctBill;

public interface IAcptService {
	
	/*****************************************************电票系统***************************************************************/
	/***************************报文处理**************************/
	
	
	/**********201001信贷放款*************/
	/**
	 * 信贷放款,放款处理T201001Service
	 */
	public void loanNotification(AcptApplyBean batchBean,List<AcptBillInfoBean> billList)throws BizAppException;
	/**********201001信贷放款****************/
	
	/**********031签收-033的回调*************/
	/**
	 * 电子银承记账在031签收-033的回调时调用，用以处理报文，拒绝签收时调用
	 * @param rgctBill
	 * @throws SQLException 
	 * @throws Exception 
	 * @throws ServiceException
	 */
	public void acceptanceAccountElec2(RgctBill rgctBill) throws BizAppException,Exception;
	/**
	 * 电子银承记账在031签收-033的回调时调用，用以处理报文，拒绝签收失败时调用
	 * @param rgctBill
	 * @throws SQLException 
	 * @throws Exception 
	 * @throws ServiceException
	 */
	public void acceptanceAccountElec3(RgctBill rgctBill) throws BizAppException,Exception;
	/**
	 * 电子银承记账在031签收-033的回调时调用，用以处理报文，签收成功时调用
	 * @param rgctBill
	 * @throws SQLException 
	 * @throws Exception 
	 * @throws ServiceException
	 */
	public void acceptanceAccountElec(RgctBill rgctBill) throws BizAppException,Exception;
	/**********031签收-033的回调*************/
	
	
	/**********提示付款034处理*************/
	/**
	 * 电子票据委托收款登记(提示付款034处理）
	 * @param bill
	 * @throws ServiceException
	 */
	public AcptColltnReg collectionRegisterElec(RgctBill bill)throws BizAppException  ;
	/**********提示付款034处理*************/
	
	
	
	/**********提示付款的撤销032 回调*************/
	public void paymentCancel(RgctBill bill) throws BizAppException  ;
	/**********提示付款的撤销032 回调*************/
	
	
	
	/**********提示付款签收的033 回调*************/
	/**
	 * 电子票据提示付款签收
	 * @param bill
	 * @param coltzReg
	 * @param billInfo
	 * @param transInfo
	 * @param isSign
	 * @throws ServiceException
	 */
	public void paymentSignUp(RgctBill bill, AcptColltnReg coltzReg, AcptBillInfo billInfo,  String isSign) throws BizAppException;
	/**********提示付款签收的033 回调*************/
	
	
	/***************************报文处理**************************/
	
	
	/***************************电票接收**************************/
	/**
	 * 点击确认接收时调用状态机，修改Status状态为BS415
	 * @param ids
	 * @return
	 * @throws Exception
	 */
	public void acceptBillForAcptmxId(String ids) throws Exception;
	
	/**
	 * 点击拒绝接收时调用状态机，修改status状态为BS411中间状态
	 * @param ids
	 * @return
	 * @throws Exception
	 */
	public int refuseBillAcptmxId(String ids) throws Exception;
	/***************************电票接收**************************/
	
	/***********************电票承兑记账模块***********************/
	/**
	 * 在承兑记账页面点击提交时调用状态机，修改status状态为BS436中间状态，电票签发记账（报文发送中）
	 * @param ids
	 * @return
	 * @throws Exception
	 */
	public int accountBillAcptmxId(String ids) throws Exception;
	/**
	 * 电子承兑记账
	 * @param acptId
	 * @param user
	 * @throws BizAppException
	 */
	public void acptAccountSubmitByElec(String acptId,UserLoginfo user)throws BizAppException;
	/***********************电票承兑记账模块***********************/
	
	/***********************电票承兑到期付款模块***********************/
	
	/**
	 * 电票到期付款，付款登记保存表时调用发送提示付款回复报文
	 */
	
	public void presentationPayment(String acptmx_ids) throws BizAppException;
	
	/**
	 * 电票到期付款，拒付登记保存表时调用发送提示付款回复报文
	 */
	public void collectionRefuse(String acptmx_ids,AcptQueryCondition query) throws BizAppException, SQLException;
	
	
	/**
	 * 电票到期付款，付款登记页面调用，用于根据主键来获取委托收款登记表
	 */
	
	public AcptColltnReg getAcptColltnRegForColltnId(String ColltnId) throws BizAppException;
	
	
	/**
	 * 电票到期付款，电票到期付款保存调用，用于更新委托收款登记表
	 * @throws SQLException 
	 */
	public int modifyAcptColltnRegForColltnId(AcptColltnReg obj) throws BizAppException, SQLException;
	
	/***********************电票承兑到期付款模块***********************/
	
	
	/*****************************************************电票系统***************************************************************/
	

	
	
	/*****************************************************纸票系统***************************************************************/

	/***************纸票承兑记账模块******************************/
	
	/**
	 * 功能描述：纸票承兑票据记账
	 * @param ids
	 * @return
	 */
	public int doAccount(String ids) throws Exception;
	/**
	 * 功能描述：纸票承兑记账提交
	 * @param ids
	 * @return
	 * @throws Exception 
	 */
	public int cumentsSubmitted(String ids,String billNo) throws BizAppException, Exception;
	
	/***************纸票承兑记账模块******************************/
	
	
	/***************纸票承兑未用退回模块***************************/
	/**
	 * 在纸票未用退回页面点击退回时调用状态机，修改status状态为BS437,修改bill_status票据状态为2
	 * @param ids
	 * @return
	 * @throws Exception
	 */
	public void notUseReturnForAcptmxId(String ids) throws Exception;
	
	/***************纸票承兑未用退回模块***************************/
	
	/******************纸票承兑挂失止付模块************************/
	/**
	 * 新增挂失表信息
	 * @param obj
	 * @return
	 */
	public int addAcptSspdReg(AcptSspdReg obj) throws SQLException;
	
	/**
	 * 挂失时保存表时，修改对应票的sspd_status挂失状态为1,,状态0是未挂失，状态1是已挂失
	 * @param query
	 * @return
	 * @throws BizAppException 
	 */
	public void modifySspdStatus0(AcptQueryCondition query) throws SQLException, BizAppException ;
	
	/**
	 * 解挂时保存表时，修改对应票的sspd_status挂失状态为0,,状态0是未挂失，状态1是已挂失
	 * @param query
	 * @return
	 */
	public void modifySspdStatus1(AcptQueryCondition query) throws SQLException, BizAppException ;
	/******************纸票承兑挂失止付模块************************/
	
	/*****************************************************纸票系统***************************************************************/

	/*****************************************************公共部分***************************************************************/
	/**
	 * 根据主键acptmx_id查询票据清单的方法
	 */
	public List<AcptBillInfo> refuseBillForAcptmxId(String ids) throws Exception;
	
	
	
	/**
	 * 根据主键acptmx_id查询票据清单的方法
	 */
	public AcptBillInfo getacptBillForAcptmxId(String ids) throws Exception;
	
	/**
	 * 功能描述：得到批次详情
	 * @param acptId
	 * @return
	 * @throws SQLException
	 */
	public AcptApplyInfo getAcptApplyInfo(String acptId) throws SQLException;
	
	
	/**
	 * 功能描述：根据条件查询清单
	 * @param page，query
	 * @return
	 * @throws SQLException
	 */
	public List<AcptBillInfo> getAcptBillListForBatch(Page page,AcptQueryCondition query) throws Exception;

	/**
	 * 功能描述：根据条件查询批次列表
	 * @param page，query
	 * @param query
	 * @return
	 * @throws SQLException
	 */
	public List<AcptApplyInfo> getAcptApplyListForCondition(Page page,AcptQueryCondition query) throws SQLException;

	/**
	 * 功能描述：校验票号是否为空
	 * @param ids
	 * @return
	 * @throws Exception 
	 */
	public boolean check(String ids) throws Exception;
	
	/**
	 * 根据主键查询批次信息
	 * @param query
	 * @return
	 */
	public AcptApplyInfo getAcptApplyForAcptID(AcptQueryCondition query) throws SQLException;
	
	
	
	/**
	 * 纸票电票到期扣款，把bill_Status票据状态从1改为3
	 * @param query
	 * @return
	 * @throws Exception
	 */
	public void dueChargeBillForAcptmxId(AcptQueryCondition query) throws Exception;
	
	/**
	 * 纸票电票到期转垫款，把bill_Status票据状态从1改为4
	 * @param query
	 * @return
	 * @throws Exception
	 */
	public void dueAdvanceBillForAcptmxId(AcptQueryCondition query) throws Exception;
	
	/**
	 * 纸票到期付款，添加付款或拒付信息;
	 * @param ids
	 * @return
	 * @throws Exception
	 */
	public int addAcptColltnReg(AcptColltnReg obj) throws Exception ;
	
	

	
	/**
	 * 纸票到期付款,付款登记保存表时，修改对应票清单的bill_status状态为5。
	 * @param saleId
	 * @return
	 * @throws BizAppException 
	 */
	public void modifyAcptBillInfoAboutPaymentSave(AcptBillInfo	billInfo) throws BizAppException ;
	
	
	/**
	 * 纸票到期付款，拒付登记保存表时，修改对应票清单的colltn_status委托收款状态
	 * @param query
	 * @return
	 * @throws BizAppException 
	 */
	public void modifyAcptBillInfoAboutPaymentSave1(AcptQueryCondition query) throws SQLException, BizAppException ;
	/*****************************************************公共部分***************************************************************/

}
