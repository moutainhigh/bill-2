/********************************************
 * 文件名称: IDiscService.java
 * 系统名称: 基础技术平台V2.0
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: 2.0.0.1
 * 开发人员: songzx
 * 开发时间: 2016-8-11
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期    修改人员    修改说明
 *********************************************/
package com.herongtech.console.service.busiservice.disc;

import java.sql.SQLException;
import java.util.List;

import com.herongtech.console.core.common.json.AjaxJson;
import com.herongtech.console.core.common.json.Page;
import com.herongtech.console.domain.disc.bean.BillAllInfoBean;
import com.herongtech.console.domain.disc.bean.DiscApplyInfo;
import com.herongtech.console.domain.disc.bean.DiscBillInfo;
import com.herongtech.console.domain.disc.bean.DiscSearchBean;
import com.herongtech.exception.impl.BizAppException;

public interface IDiscService {
	public static final String HERONGTECH_VERSION="@system 票据管理平台 @version 2.0.0.1 @lastModiDate @describe ";
	
	/**
	 * 功能描述：查询清单明细
	 * @param String
	 * @return DiscBillInfo
	 * @throws BizAppException
	 */
	public DiscBillInfo getDiscBillInfo(String discmxId) throws BizAppException;
	/**
	 * 功能描述：修改清单
	 * @param DiscBillInfo
	 * @return
	 * @throws BizAppException
	 */
	public void modDiscBillInfo(DiscBillInfo bill) throws BizAppException;
	/**
	 * 功能描述：添加清单
	 * @param discId
	 * @return
	 * @throws BizAppException
	 */
	public void addDiscBillInfo(DiscBillInfo bill) throws BizAppException;
	/**
	 * 功能描述：根据批次id查询清单
	 * @param discId
	 * @return
	 * @throws SQLException
	 */
	public List<DiscBillInfo> getDiscBillListForBatch(Page page,DiscSearchBean query) throws Exception;
	
	/**
	 * 功能描述：根据批次id查询清单(电票)
	 * @param discId
	 * @return
	 * @throws SQLException
	 */
	public List<DiscBillInfo> getDiscElecBillListForBatch(Page page,DiscSearchBean query) throws Exception;
	
	
	/**
	 * 功能描述：根据批次id和状态查询清单
	 * @param discId
	 * @return
	 * @throws SQLException
	 */
	public List<DiscBillInfo> getDiscBillListForBatchStatus(Page page,DiscSearchBean query) throws Exception;

	
	
	
	/**
	 * 功能描述：根据条件查询批次列表(审核 复核记账使用)
	 * @param page
	 * @param query
	 * @return
	 * @throws SQLException
	 */
	public List<DiscApplyInfo> getDiscApplyListForCondition(Page page,DiscSearchBean query) throws SQLException;

	/**
	 * 功能描述：根据条件查询批次列表(申请岗位)
	 * @param page
	 * @param query
	 * @return
	 * @throws Exception
	 */
	public List<DiscApplyInfo> getDiscApplyListForApply(Page page,DiscSearchBean query) throws Exception;
	
	
	/**
	 * 功能描述：得到批次详情
	 * @param discId
	 * @return
	 * @throws SQLException
	 */
	public DiscApplyInfo getDiscApplyInfo(String discId,DiscSearchBean query) throws SQLException;

	
	
	
	/**
	 * 功能描述：添加批次
	 * @param 
	 * @return
	 * @throws SQLException
	 */
	public void addDiscApplyInfo(DiscApplyInfo discApplyInfo)throws BizAppException;
	
	/**
	 * 功能描述：修改批次
	 * @param 
	 * @return
	 * @throws SQLException
	 */
	
	public void modifyDiscApplyInfo(DiscApplyInfo discApplyInfo)throws BizAppException;
     

	
	/**
	 * 功能描述：得到待接受電票
	 * @return
	 * @throws SQLException
	 */
	public List<DiscBillInfo> getElectricReceive(Page page,DiscSearchBean query) throws SQLException;
	/**
	 * 功能描述：得到待接受電票根据清单id
	 * @return
	 * @throws SQLException
	 */
	public List<DiscBillInfo> getElectricReceiveForId(String ids) throws SQLException;

	/**
	 * 功能描述：电票贴现复核记账
	 * @return
	 * @throws Exception
	 */
	public void discElecReviewBillListDoAccount(String ids,String discId) throws  Exception;
	
	
	/**
	 * 功能描述：电票贴现复核记账计算剩余票据总数量
	 * @return
	 * @throws Exception
	 */
	public int discElecReviewBillForTotalCount(DiscBillInfo query) throws  Exception;
	
	
	/**
	 * 功能描述：根据id进行实物入库
	 * @param String ids
	 * @return int
	 * @throws Exception
	 */
	public int discStorageForDiscmxId(String ids) throws SQLException, Exception;

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
	 * 功能描述：票据状态值更改
	 * @param ids,queryStatus,afterStatus,dateTime,option
	 * @return
	 */
	public int getUpdateDiscBillInfoStatus(String ids, String[] queryStatus,
			String afterStatus,String dateTime,String option) throws SQLException,BizAppException;
	
	/**
	 * 功能描述：通过票据中心ID查票据清单
	 * @param rgctId
	 * @return
	 */
	public DiscBillInfo getDiscBillInfoByRgctId(String rgctId) throws SQLException;
	
	/**
	 * 功能描述：通过票据清单修改状态值
	 * @param bill
	 * @return
	 */
	public void modifyOperStatusForBill(DiscBillInfo bill) throws SQLException;
	
	/**
	 * 审批提交
	 * @param ids
	 * @param status 是否同意
	 * @param option  审批意见
	 * @return
	 */
	public int auditSubmit(String ids, String status, String option) throws Exception;
	/**
	 * 功能描述：票据记账
	 * @param ids
	 * @return
	 */
	public int doAccount(String discId, String ids) throws Exception;
	
	
	/**
	 * 功能描述：根据批次id删除批次信息
	 * @param discIds
	 * @throws Exception
	 */
	public boolean delApplyInfoForDiscIds(Page page,String[] discIds) throws Exception;
	
	/**
	 * 功能描述：剩余票据总数量
	 * @param  bill
	 * @throws SQLException
	 */
	public int totalCount(DiscBillInfo bill) throws SQLException;
	
	
	/**
	 * 功能描述：利息试算
	 * @param toDiscBill
	 * @param discApply
	 * @return 结果大于0试算成功
	 */
	public int interestTrial(DiscBillInfo discBill,String ids) throws Exception;

	/**
	 * 功能描述:更新清单信息
	 * @param discBill
	 * @return
	 * @throws SQLException 
	 */
	public int updateDiscBillInfo(DiscBillInfo discBill) throws SQLException;
	/**
	 * 功能描述:撤销申请 撤销票据状态到上一个操作状态
	 * @param ids
	 * @return
	 */
	public int cancel(String conName,String methodName,String ids) throws Exception;
	
	/**
	 * 功能描述：点击保存时添加批次并修改该批次下的票据信息
	 * @param 
	 * @return
	 * @throws SQLException
	 */
	public void saveConditionForAddDiscApplyInfoAndModifyDiscBillInfo(String ids,DiscApplyInfo discApplyInfo)throws BizAppException;

	/**
	 * 功能描述：点击提交时添加批次并修改该批次下的票据信息到审核岗的状态
	 * @param 
	 * @return
	 * @throws SQLException
	 */
	public void submitConditionForAddDiscApplyInfoAndModifyDiscBillInfo(String ids,DiscApplyInfo discApplyInfo)throws BizAppException;
	
	/**
	 * 功能描述：修改电票批次
	 * @param 
	 * @return
	 * @throws SQLException
	 */
	public void modifyElecDiscApplyInfo(DiscApplyInfo discApplyInfo)throws BizAppException;
	
	/**
	 * 电票审批提交
	 * @param ids
	 * @param status 是否同意
	 * @param option  审批意见
	 * @return
	 */
	public int auditElecSubmit(String ids, String status, String option) throws Exception;
	
	/**
	 * 根据清单id查询DiscBillInfo集合（ids是清单id以，的拼接）
	 * @param ids
	 * @return
	 */
	public List<DiscBillInfo> getDiscBillInfolistbyids(String ids) throws Exception;
	
	/**
	 * 根据批次id（discid）查询DiscApplyInfo
	 * @param discid
	 * @return
	 */
	public DiscApplyInfo getDiscApplyInfoBydiscid(String discid) throws Exception;
	
	/**
	 * 功能描述：电票利息试算
	 * @param toDiscBill
	 * @param discApply
	 * @return 结果大于0试算成功
	 */
	public int elecInterestTrial(DiscBillInfo discBill) throws Exception;
	
	/**
	 * 功能描述：申请岗直接提交时电票利息试算
	 * @param toDiscBill
	 * @param discApply
	 * @return 结果大于0试算成功
	 */
	public int elecSubmitInterestTrial(DiscBillInfo discBill) throws Exception;
	
	public void modifyBill(DiscSearchBean query) throws SQLException;
	

	/**
	 * 功能描述：查询批次下该status状态的清单
	 * @param discId
	 * @param status
	 * @return
	 * @throws SQLException
	 * @throws Exception
	 */
	public List<DiscBillInfo> getDiscBillForDiscIdAndStatus(String discId,String status) throws SQLException, Exception;
	
	/**
	 * 功能描述：获取票据详情信息
	 * @param discmxId
	 * @return   billAllInfoBean
	 * @throws SQLException
	 */
	public BillAllInfoBean getElecBillDetail(String rgctId) throws SQLException;
	
	/**
	 * 功能描述：电票审批线路管理
	 * @param ids,auditAmt
	 * @return   
	 * @throws BizAppException
	 */
	public void getCheckApprove(String ids,double auditAmt) throws BizAppException;
	
	/**
	 * 功能描述：电票添加批次
	 * @param discApplyInfo
	 * @return   billAllInfoBean
	 * @throws SQLException
	 */
	public void addElecDiscApplyInfo(DiscApplyInfo discApplyInfo) throws SQLException;
	
	
	/**
	 * 电票贴现拒绝
	 * @param ids
	 * @throws BizAppException 
	 * @throws Exception 
	 */
	public void ElecNoReceive(String ids) throws BizAppException;
}