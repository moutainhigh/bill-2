/********************************************
 * 文件名称: ISubcollService.java
 * 系统名称: 票据管理平台V2.0
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: 2.0.0.1
 * 开发人员: dq
 * 开发时间: 2016-8-25 下午02:48:52
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期    修改人员    修改说明
 *******************************************/
package com.herongtech.console.service.busiservice.subcoll;

import java.sql.SQLException;
import java.util.List;

import com.herongtech.console.core.common.json.Page;
import com.herongtech.console.domain.bean.Branch;
import com.herongtech.console.domain.subcoll.bean.SubcollApplyInfo;
import com.herongtech.console.domain.subcoll.bean.SubcollBillInfo;
import com.herongtech.console.domain.subcoll.bean.SubcollQueryCondition;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.rc.domain.bean.RgctBill;
import com.herongtech.rc.domain.bean.RgctBillData;

/**
 * 托收服务接口 interface
 * @author Administrator
 *
 */
public interface ISubcollService {

	/**
	 * 功能描述：根据前台输入获取可托收票据信息
	 * @param subcollmxId
	 * @return
	 * @throws SQLException
	 */
	public List<RgctBillData> getSubcollBillForPrint(Page page,SubcollQueryCondition query) throws BizAppException;
	
	/**
	 *  票据查看
	 * @param id
	 * @return
	 * @throws BizAppException
	 */
	public RgctBill goDetail(String id) throws BizAppException;
	
	/**
	 * 获得List<SubcollBillInfo>  通过subcollId
	 * @param subcollId
	 * @return
	 * @throws BizAppException
	 */
	public List<SubcollBillInfo> getSubcollBillForSubcollId(String subcollId) throws BizAppException;
	
	/**
	 * 获得List<SubcollBillInfo>  通过rgctId
	 * @param rgctId
	 * @return
	 * @throws BizAppException
	 */
	public SubcollBillInfo getSubcollBillForRgctid(String rgctId) throws BizAppException;
	
	/**
	 * 获得SubcollBillInfo bean 通过rgctId 和 operstatus
	 * @param rgctId
	 * @param operstatus
	 * @return
	 * @throws BizAppException
	 */
	public SubcollBillInfo getSubcollBillForRgctIdAndOperStatus(String rgctId,String operstatus) throws BizAppException;
	
	/**
	 * 获得SubcollBillInfo bean 通过rgctId
	 * @param rgctId
	 * @return
	 * @throws BizAppException
	 */
	public SubcollBillInfo getSubcollBillForRgctId(String rgctId) throws BizAppException;
	
	/**
	 * 功能描述：添加票据往清单表中
	 * @param subcollApplyInfo
	 * @return
	 * @throws SQLException
	 */
	public void addSubcollBillInfo(String ids,String subcollId,SubcollQueryCondition query) throws BizAppException;
	
	/**
	 * 功能描述：添加票据和批次信息
	 * @param subcollApplyInfo
	 * @return
	 * @throws SQLException
	 */
	
	public void addBillInfoAndApplyInfo(String ids,SubcollQueryCondition query) throws BizAppException;
	/**
	 * 申请岗先保存后提交
	 * @param subcollmxIds
	 * @return
	 * @throws BizAppException
	 */
	public void lastApply(String[]subcollmxIds)throws BizAppException;
	
	
	/**
	 * 更新实体subcollBillInfo(清单)信息
	 * @param bill
	 * @throws BizAppException
	 */
	public void modifySubcollBillInfo(SubcollBillInfo bill) throws BizAppException;
	
	/**
	 * 更新实体SubcollApplyInfo(批次)信息
	 * @param applyInfo
	 * @throws BizAppException
	 */
	public void modifySubcollApplyInfo(SubcollApplyInfo applyInfo) throws BizAppException;
	
	/**
	 * 根据条件查询批次信息
	 * @param page
	 * @param query
	 * @return
	 * @throws BizAppException
	 */
	public List<SubcollApplyInfo> getSubcollApplyInfoListForCondition(Page page,SubcollQueryCondition query) throws BizAppException;
	
	/**
	 * 功能描述：根据批次id查询清单
	 * @param batchId
	 * @return
	 * @throws SQLException
	 */
	public List<SubcollBillInfo> getSubcollBillsBySearchBean(Page page,SubcollQueryCondition query) throws BizAppException;
	
	
	/**
	 * 功能描述：得到批次详情
	 * @param subcollId
	 * @return
	 * @throws SQLException
	 */
	public SubcollApplyInfo getSubcollApplyInfo(SubcollQueryCondition query) throws BizAppException;
	
	
	/**
	 * 提交收款记账
	 * @param ids 托收明细主键
	 * @throws Exception
	 */
	public void submitReceiveMoneyAcct(String ids)throws BizAppException;
	
	/**
	 * 提交撤销记账
	 * @param ids 托收明细主键
	 * @throws Exception
	 */
	public void submitRevokeMoneyAcct(String ids)throws BizAppException;
	
	/**
	 * 提交收款登记
	 * @param ids 托收明细主键
	 * @throws Exception
	 */
	public void submitReceiveMoneyAcctRegister(String ids)throws BizAppException;
	
	/**
	 * 提交退票登记
	 * @param ids 托收明细主键
	 * @throws Exception
	 */
	public void submitRevokeBillRegister(String ids)throws BizAppException;
	
	/**
	 * 删除票据信息
	 * @param subcollIds 托收明细主键
	 * @throws Exception
	 */
	public void delBillForApply(String subcollmxIds)throws BizAppException;
	
	/**
	 * 纸票提交修改岗票据
	 * @param subcollIds 托收明细主键
	 * @throws Exception
	 */
	public void applyBill(String subcollIds)throws BizAppException;
	
	/**
	 * 电票提交修改岗票据
	 * @param subcollmxId 托收明细主键
	 * @param query 
	 * @throws Exception
	 */
	public void elecApplyBill(String subcollmxId, SubcollQueryCondition query)throws BizAppException;
	
	/**
	 * 撤销发托
	 * @param subcollIds 托收明细主键
	 * @throws Exception
	 */
	public void RevokeBillRegister(String subcollIds)throws BizAppException;
	
	/**
	 * 提交出库
	 * @param subcollIds 托收明细主键
	 * @throws Exception
	 */
	public void applyWarehouse(String subcollmxIds,SubcollQueryCondition query)throws BizAppException;
	
	/**
	 * 初始化报文数据
	 * @param searchBean
	 * @param brch
	 * @param sub
	 * @return
	 */
	public RgctBill initRgctHist(RgctBill rgctBill, Branch banch,SubcollBillInfo bill,
			SubcollQueryCondition query) throws BizAppException;
	
	/**
	 * 撤销出库
	 * @param subcollIds 托收明细主键
	 * @throws Exception
	 */
	public void revokeWarehouse(String subcollIds,String billClass)throws BizAppException;
	
	/**
	 * 通过主键查询票据详细信息（单张票）
	 * @param subcollmxId
	 * @return
	 * @throws BizAppException
	 */
	public SubcollBillInfo getSubcollBillInfo(String subcollmxId) throws BizAppException;

	/**根据清单id查询发拖请单*/
	public SubcollBillInfo getSubcollBillInfobyid(String id)throws BizAppException;
	
	/**根据批次id查询批次*/
	public SubcollApplyInfo getSubcollApplyInfobyid(String id)throws BizAppException;
	
	
	/**根据票据类型的标识获得字符串*/
	public String getBillClassStringbybillclass(String billclass)throws BizAppException;
	
	/**根据票据品种的标识获得字符串*/
	public String getBillTypeStringbybilltype(String billclass)throws BizAppException;
	
	/**票据来源字符串  1:代保管，2票据池,3质押,4贴现；5转入，6理财，7分行带总行代保管 */
	public String getBillSourceStringbybillsource(String billsource)throws BizAppException;
	
//	/**根据清单ids(逗号分隔开的清单id)和OPER_STATUS=BS310查出清单list  （该list集合是托收增加保存完（OPER_STATUS=BS310）之后的清单）*/
//	public List<SubcollBillInfo> getSubcollBillInfolistbyqingdanids(String ids)throws BizAppException;
	
	/**根据批次号查询出批次id*/
	public String getpiciidbypicihao(String picihao)throws BizAppException ;
	
	/**根据清单rgct_id查询发拖请单*/
	public SubcollBillInfo getSubcollBillInfobyrgctid(String rgctid)throws BizAppException;
	/**
	 * 1.托收打印时判断 票据是否保存所用
	 * 2.根据ids字符串和前置状态（保存状态BS310）查询SubcollBillInfo集合（集合不为空说明这批ids中有保存完的票）*/
	public List<SubcollBillInfo> getIssaveListbyidsandoperstatus(String ids,String operstatus)throws BizAppException;

	/**
	 * 1.托收打印时判断 票据是否保存所用
	 * 2.根据主键subcollmx_id字符串和前置状态（保存状态BS310）查询SubcollBillInfo集合（集合不为空说明这批subcollmxid中有保存完的票）*/
	public List<SubcollBillInfo> getIssaveListbysubcollmxidandoperstatus(String subcollmxid,String operstatus)
	throws BizAppException;
}
