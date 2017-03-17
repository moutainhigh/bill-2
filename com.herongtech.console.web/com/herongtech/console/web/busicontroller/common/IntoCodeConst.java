package com.herongtech.console.web.busicontroller.common;

public class IntoCodeConst {
	
	public static final String INTOBILL_PROD_NO = "001007";
	public static final String BASIC_PROD_NO = "001007001";
	public static final String DERIVATIVE_PROD_NO = "001007002";
	public static final String DELETE_STATUS = "BS900";//删除状态
	public static final String FIRST_STATUS = "BS911";//初始状态
	public static final String INPUT_STATUS = "BS912";//纸票录入初始状态
	public static final String ACCOUNT_STATUS = "BS936";
	public static final String bill_sourceCust = "1";// 票据来源客户端
	public static final String bill_sourceBank = "2";// 票据来源银行端
	public static final String IF_YZ_SOURCE_NO="0";//否 
	public static final String IF_YZ_SOURCE_YES="1";//是 
	public static final String PASS_YES = "1";
	public static final String PASS_NO = "0";
	public static final String DEL_YES = "1";
	public static final String DEL_NO = "0";
	public static final String SALE_CANCEL_FLAG_YES = "1"; //转卖方撤销申请
    public static final String SALE_CANCEL_FLAG_NO = "0";  //转卖方未撤销申请
    public static final String GATH_TYPE_DEFULT = "0"; //默认值--新纪录
	public static final String GATH_TYPE_COMMON = "1"; //正常--记账完成
	public static final String GATH_TYPE_SALE = "2"; //转出卖断记账/取票出池/解质押
	public static final String GATH_TYPE_SUB = "3"; //托收收回记账
	public static final String GATH_TYPE_BUYBACK = "4"; //贴现赎回/同业返售/系统内返售(系统内回购记账)
	public static final String poolProd_no="001001003001";//入池
	public static final String poolProd_no_String="衍生业务";//入池
	public static final String outPoolProd_no="001001003002";//出池
    public static final String SAVE_APPLY ="S1";//存票申请
	public static final String SAVE_CHECK ="S2";//存票审核
	public static final String SAVE_ACCOUNT ="S3";//存票记账
	public static final String SAVE_ACCOUNT_OVER ="S4";//存票记账完成
	public static final String SAVE_WAIT_033_FOR_APPLY = "S5";//等待确认报文回复
	public static final String SAVE_TO_GET = "S6";//存票记账完成到取票申请

}
