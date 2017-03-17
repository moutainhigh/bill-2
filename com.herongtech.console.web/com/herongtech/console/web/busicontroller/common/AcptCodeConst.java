package com.herongtech.console.web.busicontroller.common;

import com.herongtech.console.core.constant.IConstants;


public class AcptCodeConst {
	
	/**
	 * 票据状态
	 *  0-银行承兑       
	 *  1-签发         
	 *  2-未用退回       
	 *  3-扣款
	 *	4-垫款                  
	 *  5-付款        
	 *  -1-默认值
	 */
	public final static String BILL_STATUS_DEFAULT = "-1";
	public final static String BILL_STATUS_ACCPTNC = "0";
	public final static String BILL_STATUS_ISSUE = "1";
	public final static String BILL_STATUS_DRAWBACK = "2";
	public final static String BILL_STATUS_DEDUCT = "3";
	public final static String BILL_STATUS_ADVANCE = "4";
	public final static String BILL_STATUS_PAYMENT = "5";
	
	//界面用
		public static String getBillStatusString(String billStatus){
			if(billStatus!=null){
				if(BILL_STATUS_DEFAULT.equals(billStatus)){
					return "预签收";
				}else if(BILL_STATUS_ACCPTNC.equals(billStatus)){
					return "待复核";
				}else if(BILL_STATUS_ISSUE.equals(billStatus)){
					return "已签发";
				}else if(BILL_STATUS_DRAWBACK.equals(billStatus)){
					return "未用退回";
				}else if(BILL_STATUS_DEDUCT.equals(billStatus)){
					return "扣款";
				}else if(BILL_STATUS_ADVANCE.equals(billStatus)){
					return "垫款";
				}else if(BILL_STATUS_PAYMENT.equals(billStatus)){
					return "付款";
				}
			}
			return "";
		}
	/**
     * 059-新版银行承兑汇票
     * 064-新版商业承兑汇票
     * 090-电子银行承兑汇票
     * 091-电子商业承兑汇票
	 */
	public final static String VOUCHER_TYPE_EC01="059";
	public final static String VOUCHER_TYPE_EC02="064";
	public final static String VOUCHER_TYPE_AC01="090";
	public final static String VOUCHER_TYPE_AC02="091";
	
	/**
	 * 0-对公活期结算户
	 * 1-对公定期保证金
	 * 2-对公活期保证金
	 * 3-个人定期保证金
	 * 4-个人活期保证金
	 * 5-对私活期结算户
	 * 6-个人定期存款账户
	 */
	public final static String CORP_CURT = "0";
	public final static String CORP_TIME_GRANT = "1";
	public final static String CORP_CURT_GRANT = "2";
	public final static String PERN_TIME_GRANT = "3";
	public final static String PERN_CURT_GRANT = "4";
	public final static String PERN_CURT = "5";
	public final static String PERN_TIME="6";
	
	/**
	 * 是否结算账户
	 * @param acctType
	 * @return
	 */
	public static boolean isCurtAcct(String acctType){
		return AcptCodeConst.CORP_CURT.equals(acctType) || AcptCodeConst.PERN_CURT.equals(acctType);
	}
	
	
	/**
	 * 是否定期账户
	 * @param acctType
	 * @return
	 */
	public static boolean isTimeAcct(String acctType) {
		return AcptCodeConst.CORP_TIME_GRANT.equals(acctType) || AcptCodeConst.PERN_TIME_GRANT.equals(acctType) || AcptCodeConst.PERN_TIME.equals(acctType);
	}
	
	/**
	 * 银承产品
	 */
	public final static String PROD_NO_ENTITY="030401010000000000";
	public final static String PROD_NO_ELEC="030401020000000000";
	
	
	
	
	/**
	 *  银承协议状态
	 *  0-银行承兑
	 *  1-签发
     *  3-扣款
     *  4-垫款
     *  -1-默认值
	 */
	public final static String BATCH_STATUS_DEFAULT = "-1";
	public final static String BATCH_STATUS_ACCPTNC = "0";
	public final static String BATCH_STATUS_ISSUE = "1";
	public final static String BATCH_STATUS_DEDUCT = "3";
	public final static String BATCH_STATUS_ADVANCE = "4";
	
	/**
	 * 委托收款状态
	 * 0-收到
	 * 1-退票
	 * 2-注销
	 * 3-撤销
	 * 4-预签收（过度界面用）
	 * 5-预拒绝（过度界面用）
	 */
	public final static String COLTZN_STUTUS_REG = "0";
	public final static String COLTZN_STUTUS_REJECT = "1";
	public final static String COLTZN_STUTUS_LOGONOUT= "2";
	public final static String COLTZN_STUTUS_CANCEL = "3";
	public final static String COLTZN_STUTUS_PRE_SIGN = "4";
	public final static String COLTZN_STUTUS_PRE_REJECT = "5";
	
	public static String getColltnStatusString(String colltnStatus){
		if(colltnStatus!=null){
			if(COLTZN_STUTUS_REG.equals(colltnStatus)){
				return "收到";
			}else if(COLTZN_STUTUS_REJECT.equals(colltnStatus)){
				return "退票";
			}else if(COLTZN_STUTUS_LOGONOUT.equals(colltnStatus)){
				return "注销";
			}else if(COLTZN_STUTUS_CANCEL.equals(colltnStatus)){
				return "撤销";
			}else if(COLTZN_STUTUS_PRE_SIGN.equals(colltnStatus)){
				return "预签收（过度界面用）";
			}else if(COLTZN_STUTUS_PRE_REJECT.equals(colltnStatus)){
				return "预拒绝（过度界面用）";
			}
		}
		return "";
	}
	/**
	 * 委托收款登记业务类型
	 * 1－我行承兑
	 * 2－代客托收
	 */
	public final static String COLTZN_BUSY_TYPE_ACCPTNC = "1";
	public final static String COLTZN_BUSY_TYPE_AGENT = "2";
	/**
	 * 是否逾期
	 */
	public final static String COLTZN_DELAY_NO = "0";
	public final static String COLTZN_DELAY_YES = "1";
	/**
	 * 资金去向
	 * 1-转帐
	 * 2-挂帐
	 */
	public final static String PAYMENT_ORDER = "1";
	public final static String SUSPEND_ORDER = "2";
	
	/**
	 * 清算标记
	 * SM00 线上
	 * SM01 线下
	 */
	public final static String SM00="SM00";
	public final static String SM01="SM01";
	
	public final static String NOTPRINT="0";
	public final static String PRINT="1";
	/**
	 * 在线清算标识 1:线上
	 */
	public static final String ONLINE = IConstants.YES;

	/**
	 * 在线清算标识 0:线下
	 */
	public static final String ONLINE_NOT = IConstants.NO;
	
	/**0-一般  大额通道小于2万人行要检查（单笔订单垫款支付挂账） */
	public static final String URGENT_FLAG_0 = "0";
	/**1-紧急  大额通道小于2万人行要检查（单笔订单垫款支付挂账） */
	public static final String URGENT_FLAG_1 = "1";
	/**2-特急  大额通道小于2万人行要检查（单笔订单垫款支付挂账） */
	public static final String URGENT_FLAG_2 = "2";
	
	
	/**
	 * 汇路
	 * 1201-一代大额
	 * 1225-二代大额
	 * 1-本转
	 * 2-系统内汇划
	 */
	public final static String HVPS = AcptCodeConst.PE_HVPS;
	public final static String HVPS_2 = AcptCodeConst.PE_HVPS_2;
	public final static String INNER = "1";
	public final static String INNER_PATH="0000";

	/**
	 * 汇路
	 * 1201-一代大额
	 * 1225-二代大额
	 * 1-本转
	 * 2-系统内汇划
	 */
	public final static String PE_HVPS = "1201";
	public final static String PE_HVPS_2 = "1225";

	
}
