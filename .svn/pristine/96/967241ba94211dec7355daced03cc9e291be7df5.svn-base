/********************************************
 * 文件名称: ICustLimitFlowService.java
 * 系统名称: 基础技术平台V2.0
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: 2.0.0.1
 * 开发人员: songzx
 * 开发时间: 2016-08-28 11:48:52
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期    修改人员    修改说明
 *********************************************/
package com.herongtech.console.web.busicontroller.common;

import java.util.HashMap;
import java.util.Map;

import com.herongtech.console.core.constant.IConstants;


public class DiscCodeConst {
	
	public final static Map<String,String> PROD_PAY_TYPE = new HashMap<String, String>();//产品编码与付息方式对应关系
	static{
		//产品编码与付息方式对应关系初始化
		PROD_PAY_TYPE.put("001001001", "3");
		PROD_PAY_TYPE.put("001001002", "1");
		PROD_PAY_TYPE.put("001001003", "2");
		PROD_PAY_TYPE.put("001001004", "3");
		PROD_PAY_TYPE.put("001001005", "1");
		PROD_PAY_TYPE.put("001001006", "2");
	}
	
	/**
	 * 网银已撤销
	 */
	public final static String IS_CANCEL_TRUE=IConstants.One;
	
	/**
	 * 网银未撤销
	 */
	public final static String IS_CANCEL_FALSE=IConstants.Zero;
	
	public static final String IS_SAME_CITY=IConstants.One;//是同城
	public static final String NOT_SAME_CITY=IConstants.Zero;//是异地
	
	/**
	 * 票据类型
	 */
	public static final String BILL_CLASS_ENTITY = IConstants.One;//实物
	public static final String BILL_CLASS_ELEC = IConstants.Two;//电子
	/**
	 * 票据来源
	 */
	public static final String BILL_SOURCE_1 = IConstants.One;//代保管
	
	public static final String BILL_SOURCE_ENTER = IConstants.Two;//录入
	
	public static final String BILL_SOURCE_3 = IConstants.Three;//网银
	
	
	public static final String BILL_TYPE_BANK =  IConstants.One;//1银票
	/**
	 * 是否我行承兌
	 */
	public static final String ACPT_NO = IConstants.Zero;
	public static final String ACPT_YES = IConstants.One;
	/**
	 * 审核状态
	 */
	public static final String AUDIT_NO = IConstants.Zero;
	public static final String AUDIT_YES = IConstants.One;
	
	/**
	 * 记账状态
	 */
	public static final String CUR_STATUS_NO = "0";
	public static final String CUR_STATUS_YES = "1";//记账为1，撤销为0
	/**
	 * 买断式贴现
	 */
	public static final String DISC_TYPE = IConstants.Zero;
	/**
	 * 父节点编号
	 */
	public static final String PARENT_PROD_NO = "001001";
	/**
	 * 买方付息
	 */
	public static final String PAY_TYPE_BUYER = IConstants.One;
	
	/**
	 * 卖方付息
	 */
	public static final String PAY_TYPE_SALER = IConstants.Two;
	
	/**
	 * 协议付息
	 */
	public static final String PAY_TYPE_AGREEMENT = IConstants.Three;
	/**
	 * 票款收回类型
	 */
	public static final String GATH_TYPE_DEFULT = IConstants.Zero; //默认值--新纪录
	public static final String GATH_TYPE_COMMON = IConstants.One; //正常--记账完成
	
	//0-初始值，1，正常（默认未收回）；2，卖断；3，托收(结清)；4，返售赎回 5、到期回购 6、借票出库 7、发托退票 8、发托出库 9、贴现赎回 10、借票还票入库
	public static final String DEFAULT = IConstants.Zero;
	public static final String NORMAL = IConstants.One;
	
	public static final String CUR_STATUS_DISC = IConstants.One;//贴现库存
}
