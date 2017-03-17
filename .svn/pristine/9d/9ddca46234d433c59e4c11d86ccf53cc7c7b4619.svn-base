/********************************************
 * 文件名称: ICollateralizationService.java
 * 系统名称: 基础技术平台V2.0
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: 2.0.0.1
 * 开发人员: v
 * 开发时间: 2016-8-24
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期    修改人员    修改说明
 *********************************************/

package com.herongtech.console.service.busiservice.collateralization;

import java.sql.SQLException;
import java.util.List;

import com.herongtech.console.core.common.json.Page;
import com.herongtech.console.domain.save.bean.SaveApplyInfo;
import com.herongtech.console.domain.save.bean.SaveBillInfo;
import com.herongtech.console.domain.save.bean.SaveSearchBean;
import com.herongtech.exception.impl.BizAppException;


public interface ICollateralizationService {
	
	public static final String HUNDSUN_VERSION="@system 票据管理平台 @version 2.0.0.1 @lastModiDate @describe ";
	/**
	 * 功能描述：电票未签收清单列表
	 * @param saveId
	 * @return
	 * @throws SQLException
	 */
	public List<SaveBillInfo> getElecSaveBillListForReceive(Page page,SaveSearchBean query) throws Exception;
	
	/**
	 * 功能描述：添加批次并签收票据
	 * @param ids
	 * @param saveApplyInfo
	 * @throws BizAppException
	 */
	public void addBatchAndSignBill(String ids, SaveApplyInfo saveApplyInfo)throws BizAppException;
	
	/**
	 * 根据id得到清单
	 * @param ids
	 * @return
	 * @throws SQLException
	 */
	public List<SaveBillInfo> getElecReceiveForId(String ids) throws SQLException;
	
	/**
	 * 功能描述：根据批次id查询清单(申请 审核 入库)
	 * @param saveId
	 * @return
	 * @throws SQLException
	 */
	public List<SaveBillInfo> getSaveBillListBySearchBean(Page page,SaveSearchBean query) throws Exception;
	
	/**
	 * 功能描述：根据条件查询批次列表(申请岗)
	 * @param page
	 * @param query
	 * @return
	 * @throws SQLException
	 * @throws Exception 
	 */
	public List<SaveApplyInfo> getSaveApplyListForApply(Page page,SaveSearchBean query) throws SQLException, Exception;
	
	/**
	 * 功能描述：根据条件查询批次列表(审核 入库岗)
	 * @param page
	 * @param query
	 * @return
	 * @throws SQLException
	 */
	public List<SaveApplyInfo> getSaveApplyListBySearchBean(Page page,SaveSearchBean query) throws SQLException;

	/**
	 * 功能描述：批次详情
	 * @param saveId
	 * @return
	 * @throws SQLException
	 * @throws BizAppException 
	 */
	public SaveApplyInfo getSaveApplyInfo(String saveId,SaveSearchBean query) throws SQLException, BizAppException;
	
	/**
	 * 功能描述：票据详情
	 * @param savemxId
	 * @return
	 * @throws Exception 
	 */
	public List<SaveBillInfo> getSaveBillInfo(Page page,SaveSearchBean query) throws Exception;
	
	/**
	 * 功能描述：添加批次
	 * @param SaveId
	 * @return
	 * @throws BizAppException
	 */
	public void addSaveApplyInfo(SaveApplyInfo saveApplyInfo) throws BizAppException;
	
	/**
	 * 功能描述：修改批次
	 * @param SaveApplyInfo
	 * @return
	 * @throws BizAppException
	 */
	 public void modiSaveApplyInfo(SaveApplyInfo saveApplyInfo) throws BizAppException;
	
	/**
	 * 功能描述：修改批次 批次状态修改
	 * @param SaveApplyInfo
	 * @return
	 * @throws BizAppException
	 */
	public void modifySaveApplyInfo(SaveApplyInfo saveApplyInfo) throws BizAppException;
	
	/**
	 * 功能描述：删除批次
	 * @param saveIds
	 * @throws Exception
	 */
	public boolean delApplyInfoForSaveIds(Page page,String[] saveIds) throws Exception;
	
	/**
	 * 功能描述：清单详情
	 * @param saveId
	 * @return
	 * @throws SQLException
	 * @throws BizAppException
	 */
	public SaveBillInfo getSaveBillInfo(String saveId)throws BizAppException;
	
	/**
	 * 功能描述：添加清单
	 * @param SaveId
	 * @return
	 * @throws BizAppException
	 */
	public void addSaveBillInfo(SaveBillInfo saveBillInfo) throws BizAppException;
	
	/**
	 * 功能描述：修改清单
	 * @param SaveBillInfo
	 * @return
	 * @throws BizAppException
	 */
	public void modiSaveBillInfo(SaveBillInfo saveBillInfo) throws BizAppException;
	
	/**
	 * 功能描述：修改清单(调rc)
	 * @param SaveBillInfo
	 * @return
	 * @throws BizAppException
	 */
	public void modifySaveBillInfo(SaveBillInfo saveBillInfo) throws BizAppException;

	/**
	 * 功能描述：票据删除
	 * @param ids
	 * @return
	 */
	public int delBill(String ids) throws Exception;
	
	/**
	 * 功能描述：票据申请提交
	 * @param ids
	 * @return
	 */
	public int applySubmit(String ids) throws Exception;
	
	/**
	 * 功能描述：票据审核提交
	 * @param ids
	 * @return
	 * @throws Exception 
	 */
	public int auditSubmit(String ids,String status,String option,SaveSearchBean query) throws BizAppException, Exception;
	
	/**
	 * 功能描述：质押入库
	 * @param ids
	 * @return
	 */
//	public int saveStorageForSavemxId(String ids) throws SQLException, Exception;
	public void accountCollateralizationBill(String savemxIds,String saveId) throws Exception;
	
	/**
	 * 功能描述：使票据状态回到上一状态
	 * @param ids
	 * @return
	 */
	public int cancel(String conName,String methodName,String ids) throws Exception;
	/**
	 * 功能描述：查询批次下该status状态的清单
	 * @param saveId
	 * @param status
	 * @return
	 * @throws SQLException
	 * @throws Exception
	 */
	public List<SaveBillInfo> getSaveBillForSaveIdAndStatus(String saveId,String status) throws SQLException, Exception;

	/**
	 * 功能描述：剩余票据总数量
	 * @param  bill
	 * @throws SQLException
	 */
	public int totalCount(SaveBillInfo bill) throws SQLException;
	
	/**
	 * 功能描述：通过票据中心ID查票据清单
	 * @param rgctId
	 * @return
	 */
	public SaveBillInfo getSaveBillInfoByRgctId(String rgctId) throws SQLException;

	public List<SaveBillInfo> getSaveBillListByMxids(String[] ids) throws SQLException;
}
