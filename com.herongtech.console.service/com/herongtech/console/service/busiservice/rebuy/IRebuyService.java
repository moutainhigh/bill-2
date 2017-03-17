package com.herongtech.console.service.busiservice.rebuy;

import java.sql.SQLException;
import java.util.List;

import com.herongtech.console.core.common.json.Page;
import com.herongtech.console.domain.bean.BillInfoDTO;

import com.herongtech.console.domain.rebuy.bean.RebuyApplyInfo;
import com.herongtech.console.domain.rebuy.bean.RebuyBillInfo;
import com.herongtech.console.domain.rebuy.bean.RebuySearchBean;
import com.herongtech.exception.impl.BizAppException;

public interface IRebuyService{

	/**
	 * 根据批次id查询批次信息
	 * @param rebuyId
	 * @return
	 * @throws SQLException
	 */
	public RebuyApplyInfo getApplyInfoById(String rebuyId) throws SQLException;
	
	/**
	 * 功能描述：通过票据中心ID查票据清单
	 * @param rgctId
	 * @return
	 */
	public RebuyBillInfo getRebuyBillInfoByRgctId(String rgctId) throws SQLException;
	
	/**
	 * 根据批次id和searchBean查询批次信息
	 * @param rebuyId
	 * @param searchBean
	 * @return
	 * @throws SQLException
	 */
	public RebuyApplyInfo getApplyInfoById(String rebuyId, RebuySearchBean searchBean) throws SQLException;

	/**
	 *  插入批次信息
	 * @param batch
	 * @return	返回批次id
	 * @throws SQLException
	 * @throws BizAppException 
	 * @throws Exception 
	 */
	public void addBatch(RebuyApplyInfo batch) throws Exception;

	/**
	 * 修改批次信息
	 * @param batch
	 * @throws BizAppException 
	 * @throws Exception 
	 */
	public void modifyBatch(RebuyApplyInfo batch) throws Exception;

	/**
	 * 功能描述：根据条件查询批次列表(审核 复核记账使用)
	 * @param page
	 * @param query
	 * @return
	 * @throws SQLException
	 */
	public List<RebuyApplyInfo> getRebuyApplyListBySearchBean(Page page, RebuySearchBean query) throws SQLException;

	/**
	 * 根据批次id查询该批次下票据的数量
	 * @return
	 * @throws SQLException 
	 */
	public int getBillCountByRebuyId(String rebuyId) throws SQLException;

	/**
	 * 根据ids删除批次信息
	 * @param ids
	 * @return
	 * @throws SQLException 
	 */
	public int deleteBatchesByRebuyId(String[] ids, String status) throws SQLException;

	/**
	 * 根据searchBean查询票据清单
	 * @param searchBean
	 * @param page
	 * @return
	 * @throws SQLException 
	 */
	public List<RebuyBillInfo> getRebuyBillListBySearchBean(RebuySearchBean searchBean,Page page) throws SQLException;

	/**
	 * 根据清单id查询清单信息
	 * @param rebuymxId
	 * @return
	 */
	public RebuyBillInfo getBillInfoByRebuymxId(String rebuymxId) throws SQLException;

	/**
	 * 申请提交
	 * @param ids
	 * @param curStatus
	 * @param afterStatus
	 * @param operType
	 * @param option
	 * @return
	 * @throws SQLException
	 * @throws Exception 
	 */
	public int applySubmit(String ids,String rebuyId) throws Exception;

	/**
	 * 审核提交
	 * @param ids
	 * @param curStatus
	 * @param afterStatus
	 * @param option
	 * @return
	 * @throws Exception
	 */
	public int auditSubmit(String ids,String rebuyId, String status,String reason) throws Exception;

	/**
	 * 批次查询（申请）
	 * @param page
	 * @param searchBean
	 * @return
	 * @throws SQLException 
	 * @throws Exception 
	 */
	public List<RebuyApplyInfo> getRebuyApplyListBySearchBeanOnlyForApply(
			Page page, RebuySearchBean searchBean) throws Exception;

	/**
	 * 复核提交
	 * @param ids
	 * @param curStatus
	 * @param afterStatus
	 * @param operator
	 * @param option
	 * @return
	 * @throws Exception
	 */
	int reauditSubmit(String ids,String status) throws Exception;

	/**
	 * 记账提交
	 * @param ids
	 * @param curStatus
	 * @param afterStatus
	 * @param operator
	 * @param option
	 * @return
	 * @throws Exception
	 */
	int accountSubmit(String ids,String rebuyId) throws Exception;

	/**
	 * 根据批次id，查询该批次下指定状态的票据数量
	 * @param id
	 * @param curStatus
	 * @return
	 * @throws SQLException 
	 */
	public int getInfoCountByRebuyIdAndStatus(String rebuyId, String[] curStatus) throws SQLException;

	/**
	 * 修改某批次的批次状态为指定状态
	 * @param id
	 * @throws SQLException 
	 */
	public int changeApplyStatusById(String rebuyId, String status) throws SQLException;

	/**
	 * 根据批次id查询批次信息
	 * @param rebuyId
	 * @return
	 */
	public RebuyApplyInfo getRebuyApplyInfo(String rebuyId) throws SQLException;
	
	/**
	 * 根据清单id查询票据信息
	 * @param rebuymxId
	 * @return
	 */
	public RebuyBillInfo getRebuyBillInfo(String rebuymxId) throws SQLException;
	/**
	 * 修改清单信息
	 * @param billId 目标清单id
	 * @param bill 页面传入的清单对象
	 * @throws Exception
	 */
	public void modRebuyBillInfo(String billId, RebuyBillInfo bill) throws Exception;

	/**
	 * 修改清单信息
	 * @param bill
	 * @throws Exception
	 */
	public int modifyRebuyBillInfo(RebuyBillInfo bill) throws Exception;
	/**
	 * 新增清单信息
	 * @param bill
	 * @throws BizAppException 
	 * @throws Exception 
	 */
	public void addRebuyBillInfo(RebuyBillInfo bill,String batchId) throws Exception;

	/**
	 * 入库
	 * @param ids
	 * @param curStatus
	 * @param afterStatus
	 * @param operator
	 * @return
	 * @throws SQLException 
	 * @throws Exception
	 */
	public int storageSubmit(String ids,String rebuyId) throws Exception;

	/**
	 * 利息计算
	 * @param ids
	 * @param isSameCity
	 * @param delayType
	 * @param delayDays
	 * @throws Exception 
	 */
	public void calculateInterest(String[] ids, String isSameCity, String delayType, String delayDays) throws Exception;


	/**
	 * 电票利息试算
	 * @param ids
	 * @param isSameCity
	 * @param delayType
	 * @param delayDays
	 * @throws Exception 
	 */
	public void calculateInterestElec(String[] ids, String isSameCity, String delayType, String delayDays) throws Exception;
	/**
	 * 根据ids删除票据清单信息
	 * @param split
	 * @param status
	 * @return
	 */
	public void deleteBillsByRebuymxId(String ids) throws Exception;

	/**
	 * 撤销申请
	 * @param split
	 * @param curStatus
	 * @param afterStatus
	 * @return
	 * @throws SQLException 
	 */
	public void revokeApply(String ids,String rebuyId) throws Exception;

	/**
	 * 撤销审核
	 * @param split
	 * @param curStatus
	 * @param afterStatus
	 * @return
	 * @throws SQLException 
	 */
	public void revokeAudit(String ids) throws Exception;

	/**
	 * 撤销记账
	 * @param split
	 * @param curStatus
	 * @param afterStatus
	 * @return
	 * @throws SQLException 
	 * @throws Exception 
	 */
	public void revokeAccount(String ids,String rebuyId) throws Exception;

	/**
	 * 根据searchBean查询复核条件的票据张数
	 * @param searchBean
	 * @return
	 * @throws SQLException 
	 */
	public int getRebuyBillCountBySearchBean(RebuySearchBean searchBean) throws SQLException;
	/**
	 * 根据当前业务ID获取业务对象，转换为风险检查清单对象
	 * @param ids	业务清单ID
	 * @return	风险检查对象集合
	 * @throws BizAppException
	 */
	public List<BillInfoDTO> getBillInfoDTO(String ids) throws BizAppException;
	
	
	/**根据billclass数值 获得字符串（查数据字典获得）*/
	public String getBillClassStringbybillclass(String billclass)throws BizAppException;


	/**根据billtype数值 获得字符串（查数据字典获得）*/
	public String getBillTypeStringbybilltype(String billtype)throws BizAppException;

	/**是否计算过利息*/
	public boolean israte(String ids)throws Exception;
	
	/**
	 * 根据批次id和状态获取转入票据清单列表
	 * @param rebuyId
	 * @param status
	 * @return
	 * @throws Exception
	 */
	public List<RebuyBillInfo> getRebuyBillListByRebuyIdAndStatus(String rebuyId,String[] status) throws Exception;
	
	/**
	 * 电票转入申请拒绝
	 * @param ids
	 * @throws BizAppException 
	 */
	public void rejectApplyElec(String ids) throws Exception;

/**
	 * 电票转入签收保存
	 * @param ids
	 * @param batch
	 * @return 
	 * @throws Exception 
	 */
	public RebuyApplyInfo saveElecBill(String ids, RebuyApplyInfo batch) throws Exception;

	/**
	 * 获取批次汇总信息
	 * @param batch
	 * @param ids
	 * @return
	 * @throws SQLException 
	 */
	public RebuyApplyInfo sumInfo(RebuyApplyInfo batch, String ids) throws SQLException;

	/**
	 * 检查转入申请是否已撤销
	 * @param ids
	 * @return
	 * @throws SQLException 
	 * @throws Exception 
	 */
	public  List<RebuyBillInfo> checkCancelState(String ids) throws Exception;

	/**
	 * 电票转入申请提交
	 * @param batch
	 * @param ids
	 * @throws Exception 
	 */
	public void applySubmitElec(RebuyApplyInfo batch, String ids) throws Exception;
	/**
	 * 根据票据明细id获取清单列表
	 * @param id
	 * @return
	 * @throws SQLException 
	 */
	public List<RebuyBillInfo> getRebuyBillListByIds(String ids) throws SQLException;
	
	/**根据票据discmxIds获得电票集合*/
	public List<RebuyBillInfo> getRebuyBillListBydiscmxIds(String discmxIds) throws Exception;
	
	/**电票转入审核通过*/
	public int elecauditSubmit(String ids,String rebuyId, String status,String reason) throws Exception ;

	/**电票转入撤销审核*/
	public void elecrevokeAudit(String ids) throws Exception;

	/**
	 * 电票转入撤销申请
	 * @param ids
	 * @param rebuyId
	 * @throws Exception 
	 */
	public void revokeApplyElec(String ids) throws Exception;
	
	/**
	 * 获取billList中所有billId组成的字符串，已“，”分隔 
	 * @param billList
	 * @return
	 */
	public String getBillIdsString(List<RebuyBillInfo> billList);

	/**
	 * 电票转入修改申请
	 * @param ids
	 * @param batch
	 * @return
	 * @throws SQLException 
	 */
	public RebuyApplyInfo modifyElecBill(String ids, RebuyApplyInfo batch) throws SQLException;
	
	/**
	 * 清空利息
	 * @param billList
	 * @return
	 * @throws SQLException 
	 */
	public List<RebuyBillInfo> clearInterest(List<RebuyBillInfo> billList) throws SQLException;

	/**
	 * 在批次中新增清单票据
	 * @param split
	 * @param rebuyId
	 */
	public void addElecBill(String ids, String rebuyId) throws Exception;

	/**
	 * 转入电票从批次中移除
	 * @param billList
	 * @throws Exception 
	 */
	public void removeElecBill(List<RebuyBillInfo> billList) throws Exception;

	/**
	 * 电票转入记账
	 * @param ids
	 * @param rebuyId
	 * @throws Exception 
	 */
	public List<RebuyBillInfo> elecAccountSubmit(String ids, String rebuyId) throws Exception;

	/**
	 * 电票转入记账确认
	 * @throws Exception 
	 */
	public void confirmAccount(RebuyApplyInfo batch, String ids) throws Exception;

	/**
	 * 根据rebuyId查询该批次下所有票据明细id
	 * @param rebuyId
	 * @return
	 * @throws SQLException 
	 */
	public String getBillIdsString(String rebuyId) throws SQLException;

	/**
	 * 校验批次下的票据状态是否一致
	 * @param rebuyId
	 * @return
	 * @throws SQLException 
	 */
	public boolean validStatus(String rebuyId) throws SQLException;

	/**
	 * 获取billList中所有salemxId
	 * @param billList
	 * @return
	 */
	public String[] getSalemxIds(List<RebuyBillInfo> billList);
}
