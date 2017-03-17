/********************************************
 * 文件名称: IBuybackService.java
 * 系统名称: 基础技术平台V2.0
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: 2.0.0.1
 * 开发人员: songzx
 * 开发时间: 2016-11-04 下午10:48:52
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期    修改人员    修改说明
 *********************************************/
package com.herongtech.console.service.busiservice.buyback;

import java.sql.SQLException;
import java.util.List;

import com.herongtech.console.core.common.json.Page;
import com.herongtech.console.domain.buyback.bean.BuybackApplyInfo;
import com.herongtech.console.domain.buyback.bean.BuybackBillInfo;
import com.herongtech.console.domain.buyback.bean.BuybackSearchBean;
import com.herongtech.console.domain.sale.bean.SaleApplyInfo;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.rc.domain.bean.RgctBill;

public interface IBuybackService {
	public static final String HERONGTECH_VERSION="@system 票据管理平台 @version 2.0.0.1 @lastModiDate @describe ";

	/**
	 * 功能描述：是否进行过利息试算
	 * @param ids
	 * @return
	 * @throws SQLException
	 */
	public boolean isInterestTrial(String ids) throws SQLException;
	/**
	 * 功能描述：得到到期回购批次列表
	 * @param page 分页信息
	 * @param query 查询条件
	 * @return 批次列表
	 */
	public List<BuybackApplyInfo> getBuybackApplyListForCondition(Page page, BuybackSearchBean query) throws Exception;
	
	/**
	 * 功能描述：得到到期回购批次详细信息
	 * @param buybackId 批次id
	 * @param query 查询条件
	 * @return 批次列表
	 */
	public BuybackApplyInfo getBuybackApplyInfo(String buybackId, BuybackSearchBean query) throws SQLException;


	/**
	 * 功能描述：得到到期回购批次内的票据列表信息
	 * @param page
	 * @param query
	 * @return
	 */
	public List<BuybackBillInfo> getBuybackBillListForBatch(Page page, BuybackSearchBean query) throws SQLException;

	
	/**
	 * 功能描述：得到到期回购批次内的票据列表信息
	 * @param page
	 * @param query
	 * @return
	 */
	public void buyBackBillInit(RgctBill bill, String ifInner, String saleId)throws Exception ;
	
	/**
	 * 功能描述：清空利息等值
	 * @param query
	 * @return
	 * @throws SQLException
	 */
	public void clearInterestTrialInfo(BuybackSearchBean query) throws SQLException ;

	/**
	 * 功能描述：清空利息等值(纸票)
	 * @param query
	 * @return
	 * @throws SQLException
	 */
	public void clearInterestTrialInfoForEntity(BuybackSearchBean query) throws SQLException;
	
	/**
	 * 功能描述：利息试算
	 * @param toDiscBill
	 * @param discApply
	 * @return 结果大于0试算成功
	 */
	public int interestTrial(BuybackBillInfo buyback,String ids,String buybackRate,BuybackApplyInfo buybackApply) throws Exception;
	
	/**
	 * 功能描述：利息试算(纸票)
	 * @param buyback
	 * @param ids
	 * @return 结果大于0试算成功
	 */
	public int interestTrialForEntity(BuybackBillInfo buybackBill,String ids,BuybackApplyInfo buybackApply,String buybackDt,String buybackRate,String isSameCity) throws Exception;;
	
	/**
	 * 功能描述：调整利息
	 * @param id
	 * @return
	 * @throws Exception 
	 * @throws BizAppException 
	 */
	public int interestAdjust(String id,String adjustMoney) throws Exception;
	
	/**
	 * 功能描述：票据申请提交
	 * @param ids
	 * @return
	 * @throws Exception 
	 * @throws BizAppException 
	 */
	public int applySubmit(String ids,BuybackSearchBean query,String buybackRate) throws Exception;
	
	/**
	 * 审核提交(纸票)
	 */
	public int auditSubmitForEntity(String ids, String status,BuybackSearchBean query) throws Exception;
	
	/**
	 * 功能描述：票据申请提交(纸票)
	 * @param ids
	 * @return
	 * @throws Exception 
	 * @throws BizAppException 
	 */
	public int applySubmitForEntity(String ids,BuybackSearchBean query) throws Exception;
	
	/**
	 * 电子票据拒绝签收
	 * @param ids
	 * @return
	 * @throws Exception
	 */
	public void noReceive(String ids) throws Exception ;
	
	
	/**
	 * 功能描述:撤销票据状态到上一个操作状态
	 * @param conName 状态机控制类名称
	 * @param methodName 状态机控制类方法
	 * @param ids 要撤销的票据id
	 * @return int
	 */
	public int cancel(String conName,String methodName,String param,String ids,String buybackId) throws Exception;
	/**
	 * 审批提交
	 * @param ids
	 * @param status 是否同意
	 * @param option  审批意见
	 * @return
	 */
	public int auditSubmit(String ids, String status, String reason) throws Exception;
	/**
	 * 功能描述：票据申请记账
	 * @param ids
	 * @return
	 */
	public void applyAccount(String buybackId,String ids) throws Exception;
	
	/**
	 * 功能描述：票据确认记账
	 * @param buybackId
	 * @param ids
	 */
	public void confirmAccount(String buybackId, String ids)throws Exception;
	
	/**
	 * 记账(纸票)
	 */
	public void accountForEntity(String ids,String buybackId) throws Exception;
	
	/**
	 * 根据salemxId获取清单详情
	 * @param salemxId
	 * @return
	 * @throws SQLException
	 */
	public BuybackBillInfo getBuybackBillInfoBySalemxId(String salemxId)throws SQLException;
	
	/**
	 * 纸票批次列表(申请岗)
	 * @param page
	 * @param query
	 * @return
	 * @throws SQLException
	 */
	public List<SaleApplyInfo> getSaleApplyListBySearchBean(Page page,BuybackSearchBean query) throws SQLException;
	
	 /**
	 * 获取清单详情
	 */
	public BuybackBillInfo getBuybackBillInfo(String buybackmxId)throws BizAppException;
	
	/**
	 * 修改批次
	 */
	public void modifyBuybackApplyInfo(BuybackApplyInfo buybackApplyInfo)throws BizAppException;
	
	/**
	 * 功能描述:更新清单信息
	 * @param buyback
	 * @return
	 * @throws SQLException 
	 */
	public int updateBuybackBillInfo(BuybackBillInfo buyback) throws SQLException;
	
	/**
	 * 功能描述:撤销票据状态到上一个操作状态(纸票)
	 * @param ids
	 * @return
	 */
	public int cancel(String conName,String methodName,String ids) throws Exception;
	
	/**
	 * 功能描述：查询批次下该status状态的清单
	 * @param buybackId
	 * @param status
	 * @return
	 * @throws SQLException
	 * @throws Exception
	 */
	public List<BuybackBillInfo> getBuybackBillForBuybackIdAndStatus(String buybackId,String status) throws SQLException, Exception;
	
	/**
	 * ids
	 * @param billList
	 * @return
	 */
	public String getBillIdsString(List<BuybackBillInfo> billList);
	
}
