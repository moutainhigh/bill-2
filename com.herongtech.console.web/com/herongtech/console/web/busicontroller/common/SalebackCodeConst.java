package com.herongtech.console.web.busicontroller.common;

import com.herongtech.console.core.constant.IConstants;

public class SalebackCodeConst {

	
	/**
	 * 是否系统内
	 */
	public static final String IS_INNER_TRUE = IConstants.One;//系统内
	public static final String IS_INNER_FALSE = IConstants.Zero;//同业间
	
	public static final String SALEBACK_ELEC = "2";//电票
	
	public static final String SALEBACK_BACKBUY = "1";//回购式
	
	public static final String CUR_STATUS_REBUY_REDEEM_OVER = "10";//买入返售结清
	
	
	public static final String GATH_TYPE_COMMON = "1"; //正常--记账完成
	
	public static final String CUR_STATUS_REBUY_REDEEM="4";//买入返售库存
	
	
	/**产品号*/
	public static final String SALE_BACK_PROD_NO_INNER="001010001";//系统内返售
	public static final String SALE_BACK_PROD_NO_NOT_INNER="001010002";//同业间返售
	/**
	 *返售清单打印膜版
	 */
	public static final String BILL_PRINT_MYSUBID="1050601";
}
