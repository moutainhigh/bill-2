/********************************************
 * 文件名称: ISequenceService.java
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
package com.herongtech.console.service.interfaces;

import com.herongtech.exception.impl.BizAppException;

/**
 * 序列号接口
 * 
 * @author Administrator
 * 
 */
public interface ISequenceService {
	public static final String HERONGTECH_VERSION="@system 票据管理平台 @version 2.0.0.1 @lastModiDate @describe ";

	/**
	 * 日终日志ID
	 */
	public static final String TASK_LOG_ID = "task_log_id";
	/**
	 * 余额结转表ID
	 */
	public static final String ACCT_BALANCE_HIS_ID = "acct_balance_his_id";
	/**
	 * 额度发生流水表ID
	 */
	public static final String FAC_CREATE_FLOW_ID = "fac_create_flow_id";
	/**
	 * 日志号
	 */
	public static final String LOG_NO = "log_no";
	
	/**
	 * rc日志号
	 */
	public static final String RC_LOG_NO = "rc_log_no";
	
	/**
	 * 风险票据表id
	 */
	public static final String RISK_BILL_ID = "risk_bill_id";
	
	
	
	/**
	 * 登记中心票面信息表id
	 */
	public static final String RGCT_BILL_INFO_ID = "rgct_bill_info_id";
	
	/**
	 * 登记中心历史信息表id
	 */
	public static final String RGCT_BILL_HIST_ID = "rgct_bill_hist_id";
	/**
	 * 背书历史信息表id
	 */
	public static final String RGCT_ENDO_HIST_ID = "rgct_endo_hist_id";
	
	/**
	 * 背书历史信息表id
	 */
	public static final String RGCT_TEMP_HIST_ID = "rgct_temp_hist_id";
	
	/**
	 * 报文id
	 */
	public static final String MSGID_SEQUENCE = "msgid_sequence";
	
	
	/**
	 * 批次号
	 */
	public static final String APPLY_NO = "apply_no";
	
	/**
	 * 贴现清单表id
	 */
	public static final String DISC_BILL_INFO_ID = "disc_bill_info_id";
	
	/**
	 * 转入清单表id
	 */
	public static final String REBUY_BILL_INFO_ID = "rebuy_bill_info_id";
	
	/**
	 * 转卖清单表id
	 */
	public static final String SALE_BILL_INFO_ID = "sale_bill_info_id";
	
	/**
	 * 返售清单表id
	 */
	public static final String SALEBACK_BILL_INFO_ID = "saleback_bill_id";
	
	/**
	 * 返售批次表id
	 */
	public static final String SALEBACK_APPLY_INFO_ID = "saleback_apply_id";
	
	/**
	 * 托收清单表id
	 */
	public static final String SUBCOLL_BILL_INFO_ID = "subcoll_bill_info_id";
	
	/**
	 * 质押清单表id
	 */
	public static final String SAVE_BILL_INFO_ID = "save_bill_info_id";
	
	/**
	 * 取票清单表id
	 */
	public static final String GET_BILL_INFO_ID = "get_bill_info_id";
	
	/**
	 * 贴现批次表id
	 */
	public static final String DISC_APPLY_ID = "disc_apply_id";
	
	/**
	 * 转入批次表id
	 */
	public static final String REBUY_APPLY_ID = "rebuy_apply_id";
	
	/**
	 * 转卖批次表id
	 */
	public static final String SALE_APPLY_ID = "sale_apply_id";
	
	/**
	 * 托收批次表id
	 */
	public static final String SUBCOLL_APPLY_ID = "subcoll_apply_id";
	
	/**
	 * 质押批次表id
	 */
	public static final String SAVE_APPLY_ID = "save_apply_id";
	
	/**
	 * 取票批次表id
	 */
	public static final String GET_APPLY_ID = "get_apply_id";
	
	/**
	 * 银承批次表id
	 */
	public static final String ACPT_APPLY_ID = "acpt_apply_id";
	/**
	 * 银承清单表id
	 */
	public static final String ACPT_BILL_INFO_ID = "acpt_bill_info_id";
	
	/**
	 * 签约表id
	 */
	public static final String Sign_Prod_ID = "sign_prod_id";
	
	/**
	 * 签约协议书编号
	 */
	public static final String Sign_ID = "sign_id";
	
	/**
	 * 快捷菜单编号
	 */
	public static final String Con_Menu_ID = "con_menu_id";
	
	/**
	 * 保证批次表id
	 */
	public static final String ASSU_APPLY_ID = "assu_apply_id";

	/**
	 * 保证清单表id
	 */
	public static final String ASSU_BILL_INFO_ID = "assu_bill_info_id";
	
	/**
	 * 挂失id
	 */
	public static final String ACPT_SSPD_REG_ID = "acpt_sspd_reg_id";
	
	/**
	 * 委托收款登记薄id
	 */
	public static final String ACPT_COLLTN_REG_ID = "acpt_colltn_reg_id";
	
	
	/**
	 * 公用主键值
	 */
	public static final String PRIMARY_KEY_VALUE="primary_key_value";
	
	/**
	 * 记账流水号
	 */
	public static final String ACCT_FLOW_NO="acct_flow_no";
	
	/**
	 * 到期回购批次id
	 */
	public static final String BUYBACK_APPLY_INFO_ID = "buyback_apply_id";
	/**
	 * 到期回购清单id
	 */
	public static final String BUYBACK_BILL_INFO_ID = "buyback_bill_info_id";
	
	
	/**
	 * 主键seqName
	 */
	public static final String ID_SEQUENCE="ID_SEQUENCE";
	
	/**
	 * 纸票登记id
	 */
	public static final String ENTITY_REGISTER_ID="entity_register_id";
	
	/**
	 * 获取风险票据id号
	 * 
	 * @return
	 * @throws BizAppException
	 */
	public String getRiskBillId() throws BizAppException;
	
	/**
	 * 获取日志流水号
	 * 
	 * @return
	 * @throws BizAppException
	 */
	public String getLogSerialNo() throws BizAppException;
	
	/**
	 * 获取RC日志流水号
	 * 
	 * @return
	 * @throws BizAppException
	 */
	public String getRcLogSerialNo() throws BizAppException;
	
	/**
	 * 获取日志流水号
	 * 
	 * @return
	 * @throws BizAppException
	 */
	public String getBillInfoId() throws BizAppException;
	
	/**
	 * 获取RC日志流水号
	 * 
	 * @return
	 * @throws BizAppException
	 */
	public String getBillInfoHist() throws BizAppException;

	/**
	 * 获取批量流水号
	 * 
	 * @param keyNo
	 * @return
	 * @throws BizAppException
	 */
	public long getBatchSerialNo(String keyNo, int inc) throws BizAppException;
	
	/**
     * 获取rgctendohist的id
     */
	public  String getBillEndoHist() throws BizAppException;
	
	/**
     * 获取rgcttemphist的id
     */
	public  String getBillTempHist() throws BizAppException;
	
	/**
     * 获取msgid_sequence
     */
	public  String getMsgIdSequence() throws BizAppException;
	/**
     * 获取DISC_BILL_INFO_ID
     */
	public String getDISC_BILL_INFO_ID() throws BizAppException;
	
	/**
     * 获取DISC_APPLY_ID
     */
	public  String getDISC_APPLY_ID() throws BizAppException;
	
	/**
     * 获取REBUY_APPLY_ID
     */
	public  String getREBUY_APPLY_ID() throws BizAppException ;
	
	/**
     * 获取REBUY_BILL_INFO_ID
     */
	public  String getREBUY_BILL_INFO_ID() throws BizAppException ;
	/**
     * 获取SALE_APPLY_ID
     */
	public  String getSALE_APPLY_ID() throws BizAppException;
	
	/**
     * 获取SALE_BILL_INFO_ID
     */
	public  String getSALE_BILL_INFO_ID() throws BizAppException ;
	
	/**
     * 获取SALEBACK_BILL_INFO_ID
     */
	public  String getSALEBACK_BILL_INFO_ID() throws BizAppException ;
	
	/**
     * 获取SALEBACK_APPLY_INFO_ID
     */
	public  String getSALEBACK_APPLY_INFO_ID() throws BizAppException ;	
	
	/**
     * 获取SAVE_APPLY_ID
     */
	public  String getSAVE_APPLY_ID() throws BizAppException ;
	
	/**
     * 获取SAVE_BILL_INFO_ID
     */
	public  String getSAVE_BILL_INFO_ID() throws BizAppException ;
	
	/**
     * 获取GET_BILL_INFO_ID
     */
	public  String getGET_BILL_INFO_ID() throws BizAppException ;
	
	/**
     * 获取GET_APPLY_ID
     */
	public  String getGET_APPLY_ID() throws BizAppException;
	
	/**
     * 获取SUBCOLL_APPLY_ID
     */
	public  String getSUBCOLL_APPLY_ID() throws BizAppException ;
	
	/**
     * 获取SUBCOLL_BILL_INFO_ID
     */
	public  String getSUBCOLL_BILL_INFO_ID() throws BizAppException ;
	
	/**
     * 获取ACPT_BILL_INFO_ID
     */
	public  String getACPT_BILL_INFO_ID() throws BizAppException ;
	/**
     * 获取ACPT_APPLY_ID
     */
	public  String getACPT_APPLY_ID() throws BizAppException ;
	
	/**
     * 获取ASSU_BILL_INFO_ID
     */
	public  String getASSU_BILL_INFO_ID() throws BizAppException ;
	/**
     * 获取ASSU_APPLY_ID
     */
	public  String getASSU_APPLY_ID() throws BizAppException ;
	
	/**
	 * 生成贴现批次号
	 * @param brchNo
	 * @return
	 * @throws BizAppException
	 */
	public String getDiscountApplyNo(String brchNo) throws BizAppException;
	/**
	 * 生成转入批次号
	 * @param brchNo
	 * @return
	 * @throws BizAppException
	 */
	public String getRebuyApplyNo(String brchNo) throws BizAppException;
	
	/**
	 * 生成转出批次号
	 * @param brchNo
	 * @return
	 * @throws BizAppException
	 */
	public String getSaleApplyNo(String brchNo) throws BizAppException;
	/**
	 * 生成托收批次号
	 * @param brchNo
	 * @return
	 * @throws BizAppException
	 */
	public String getSubcollApplyNo(String brchNo) throws BizAppException;
	/**
	 * 生成质押批次号
	 * @param brchNo
	 * @return
	 * @throws BizAppException
	 */
	public String getSaveApplyNo(String brchNo) throws BizAppException;
	/**
	 * 生成解质押批次号
	 * @param brchNo
	 * @return
	 * @throws BizAppException
	 */
	public String getGetApplyNo(String brchNo) throws BizAppException;
	/**
	 * 生成银承批次号
	 * @param brchNo
	 * @return
	 * @throws BizAppException
	 */
	public String getAcptApplyNo(String brchNo) throws BizAppException;
	/**
	 * 生成存票批次号
	 * @param brchNo
	 * @return
	 * @throws BizAppException
	 */
	public String getIntoApplyNo(String brchNo) throws BizAppException;
	/**
	 * 生成取票批次号
	 * @param brchNo
	 * @return
	 * @throws BizAppException
	 */
	public String getOutApplyNo(String brchNo) throws BizAppException;
	
	/**
	 * 获取主键值
	 * @return
	 * @throws BizAppException
	 */
	public String getPrimaryKeyValue()throws BizAppException;
	/**
	 * 根据keyNo获取主键序列号
	 * @param keyNo
	 * @return
	 * @throws BizAppException
	 */
	public String getSequenceNoByKeyNo(String keyNo) throws BizAppException;
	
	/**
	 * 获取记账流水号
	 * @param branchNo
	 * @return
	 * @throws BizAppException
	 */
	public String getAcctFlowNo(String branchNo)throws BizAppException;
	/**
	 * 生成到期回购批次号
	 * @param brchNo
	 * @return
	 * @throws BizAppException
	 */
	public String getBuybackApplyNo(String brchNo) throws BizAppException;
	
	/**
	 * 生成保证批次号
	 * @param brchNo
	 * @return
	 * @throws BizAppException
	 */
	public String getAssuApplyNo(String brchNo) throws BizAppException;
	
	/**
     * 获取BUYBACK_APPLY_INFO_ID
     */
	public  String getBUYBACK_APPLY_INFO_ID() throws BizAppException ;
	
	/**
     * 获取BUYBACK_BILL_INFO_ID
     */
	public  String getBUYBACK_BILL_INFO_ID() throws BizAppException;
	
	/**
     * 获取纸票登记ID
     */
	public  String getENTITY_REGISTER_ID() throws BizAppException;
	
	/**
     * 获取Sign_Prod_ID
     */
	public String getSign_Prod_ID() throws BizAppException;
	
	/**
     * 获取Sign_ID
     */
	public String getSign_ID() throws BizAppException;
	
	/**
     * 获取ConMenu_ID
     */
	public String getConMenu_ID() throws BizAppException;

	/**
	 * 获取acct_balance_his_id
	 * @return
	 * @throws BizAppException
	 */
	public  String getAcctBalanceHisId() throws BizAppException;
	/**
     * 获取task_log_id的id
     */
	public  String getTaskLogId() throws BizAppException ;
	/**
     * 获取fac_create_flow_id的id
     */
	public  String getFacCreateFlowId() throws BizAppException;

}
