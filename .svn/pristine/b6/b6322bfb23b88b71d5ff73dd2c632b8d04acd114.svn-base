package com.herongtech.console.web.busicontroller.common;

import com.herongtech.console.core.constant.IConstants;
public class CommonConst {
	/**
	 * 票据类型
	 */
	public static final String BILL_CLASS_ENTITY = IConstants.One;//实物
	public static final String BILL_CLASS_ELEC = IConstants.Two;//电子
	
	/**
	 * 票据种类
	 */
	public static final String BILL_TYPE_BANK = IConstants.One;//银承
	public static final String BILL_TYPE_CORP = IConstants.Two;//商承
	
	/**
	 * 批次状态
	 */
	public static final String APPLY_STATUS_DELETE = IConstants.Zero; //删除批次
	public static final String APPLY_STATUS_NEW = IConstants.One; //新建批次|只要有一张票还在申请岗，则整个批次的状态即为新建状态
	public static final String APPLY_STATUS_SUBMIT = IConstants.Two; //提交批次
	
	/**
	 * 结算账户类型
	 */
	public static final String TRADE_ACCT_TYPE_SETTLE = "7";
	
	/**
	 * 年利率
	 */
	public static final String  RATE_TYPE_YEAR = "360";
	
	/**
	 * 买入标志
	 */
	public static final String BUY_YES = IConstants.One; //买入
	public static final String BUY_NO = IConstants.Zero; //卖出
	
	/**
	 * 是否锁票
	 */
	public static final String LOCK_NO = IConstants.NO; //否
	public static final String LOCK_YES = IConstants.YES; //是
	
	/**
	 * 是否线上清算
	 */
	public static final String ONLINE_NOT = IConstants.NO;//线下
	public static final String ONLINE_YES = IConstants.YES;//线上
	
	/**
	 * 禁止背书标志
	 */
	public static final String ENDORSE_FORBID_NO = IConstants.NO;//不禁止
	public static final String ENDORSE_FORBID_YES = IConstants.YES;//禁止

	/**
	 * 票据流转类型: 0-初始值，1，正常（默认未收回）；2，卖断；3，托收(结清)；4，返售赎回 5、到期回购 6、借票出库 7、发托退票 8、发托出库 9、贴现赎回 10、借票还票入库
	 */
	public static final String DEFAULT="0";
	public static final String NORMAL="1";
	public static final String SALE = "2";
	public static final String SETTLEMENT = "3";
	public static final String REDEEM = "4";
	public static final String BUYBACK = "5";
	public static final String BORROW = "6";
	public static final String RETURN_SUB = "7";
	public static final String SUB_ON_WAY = "8";
	public static final String DISC_REDEEM = "9";
	public static final String SALE_INSTORE= "10";
	
	/**
	 * 签收标志：已签收
	 */
	public static final String SIGN_YES = IConstants.YES;

	/**
	 * 签收标志：未签收
	 */
	public static final String SIGN_NO = IConstants.NO;
	
	/**
	 *	客户额度品种-直贴 
	 */
	public static final String CUST_LIMIT_CLASS_DISC = IConstants.One;
	/**
	 *	客户额度品种-转贴现 
	 */
	public static final String CUST_LIMIT_CLASS_TRANSFER  = IConstants.Two;
	/**
	 *	客户额度品种-质押
	 */
	public static final String CUST_LIMIT_CLASS_SAVE  = IConstants.Three;
	
	/** 票据来源 1,贴现 */
	public static final String FAC_SOURCE_DISC = "1";
	/** 票据来源 2,转贴现 */
	public static final String FAC_SOURCE_REBUY = "2";
	/** 票据来源 3,质押 */
	public static final String FAC_SOURCE_SAVE = "3";
	/** 票据来源 4,票据池 */
	public static final String FAC_SOURCE_SAVE_TWO = "4";
	/** 票据来源 5,承兑 */
	public static final String FAC_SOURCE_ACPT = "5";
	/** 票据来源 6,承兑保证金 */
	public static final String FAC_SOURCE_ACPT_02 = "6";
	
	/** 额度操作状态 0,释放 */
	public static final String FAC_STATUS_ZERO = "0";
	/** 额度操作状态 1，额度扣减直接占用成功 */
	public static final String FAC_STATUS_ONE = "1";
	/** 额度操作状态 3,直接占用不成功 */
	public static final String FAC_STATUS_THREE = "3";
	
	//额度管理外部支持开关
	public final static String SWITCH_FLAG_OUTER = IConstants.Two;
	public final static String SWITCH_FLAG_INNER = IConstants.One;
	public final static String SWITCH_FLAG_NO = IConstants.Zero;
	
	/** 额度扣减操作类型 */
	public static final String FAC_OPER_NO = "0";// 不扣减
	public static final String FAC_OPER_OCCUPY = "1";// 扣减
	public static final String FAC_OPER_RELEASE = "2";// 释放
		
    //客户类型:4系统内机构，10财务公司或代理行，12系统外同业机构
    public static final String CUST_INNER = "4";
    public static final String CUST_BANK = "12";
    public static final String CUST_OTHER = "10";
    
    /**
	 * 网银已撤销
	 */
	public final static String IS_CANCEL_TRUE=IConstants.One;
	
	//票据当前状态
	public static final String CUR_STATUS_DISC="1";//贴现库存
	public static final String CUR_STATUS_DISC_REDEEM_OVER="2";//贴现赎回结清
	public static final String CUR_STATUS_REBUY="3";//买断库存
	public static final String CUR_STATUS_REBUY_REDEEM="4";//买入返售库存
	public static final String CUR_STATUS_SALE="5";//卖断
	public static final String CUR_STATUS_SALE_WAIT_BACK="6";//卖出回购待赎回
	public static final String CUR_STATUS_SUB_ON_PASSAGE="7";//托收在途
	public static final String CUR_STATUS_COllZTN="8";//质押库存
	public static final String CUR_STATUS_RECOLLZTN_OVER="9";//解质押结清
	public static final String CUR_STATUS_REBUY_REDEEM_OVER="10";//买入返售结清
	public static final String CUR_STATUS_SUB_COLL_BACK="11";//收回结清
	public static final String CUR_STATUS_SAVE="12";//代保管存票库存
	public static final String CUR_STATUS_INPOOL="13";//票据池入池库存
	public static final String CUR_STATUS_GET="14";//代保管取票结清
	public static final String CUR_STATUS_OUTPOOL="15";//票据池出池结清
	public static final String CUR_STATUS_SALE_OVER="16";//回购到期结清
	public static final String CUR_STATUS_SALE_OUTSTORE="17";//借票出库
	public static final String CUR_STATUS_SALE_INSTORE="18";//借票还票入库
}
