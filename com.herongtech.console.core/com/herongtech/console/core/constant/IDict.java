/******************************************** 
 * 文件名称: Dict.java 
 * 系统名称: 合融技术平台 
 * 模块名称: 
 * 软件版权: 北京合融科技有限公司 
 * 功能说明: 
 * 系统版本: 2.0.0.1 
 * 开发人员: 
 * 开发时间: 
 * 审核人员: 
 * 相关文档: 
 * 修改记录: 修改日期     修改人员     修改说明 
 *        20160627-01  yanjl  新增
 *********************************************/

package com.herongtech.console.core.constant;

import java.util.HashMap;
import java.util.Map;

public class IDict {
	public static final String HERONGTECH_VERSION="@system 合融技术平台 @version V2.0.0.1 @lastModiDate @describe ";
	
	/**
	 * 菜单记录类型
	 */
	public static interface K_MENUYPE {
		/** 字典名称 */
		public final static  String NAME = "K_MENUYPE";
		/** 0-普通菜单 */
		public final static  String MENUTYPE_MENU = "0";
		/** 1-其他 */
		public final static  String MENUTYPE_OTHER = "1";

	}
	
	/**
	 * 菜单级别
	 */
	public static interface K_MENU_LEVEL {
		/** 字典名称 */
		public final static  String NAME = "K_MENULEVEL";
		/** -1-总级别菜单 */
		public final static  String K_MENULEVEL_ZERO = "-1";
		/** 0-一级菜单， */
		public final static  String K_MENULEVEL_ONE = "0";
		/** 1-二级菜单，业务模块 */
		public final static  String K_MENULEVEL_TWO = "1";
		/** 1-三级菜单，功能操作菜单 */
		public final static  String K_MENULEVEL_THREE = "2";
	}
	/**
	 * 一级菜单编码定义
	 */
	public static final Map<String,String> K_MENU_CODE_1_NAME = new HashMap<String,String>();
	static{
		K_MENU_CODE_1_NAME.put("01", "纸");
		K_MENU_CODE_1_NAME.put("02", "电");
		K_MENU_CODE_1_NAME.put("03", "公共模块");
		K_MENU_CODE_1_NAME.put("04", "票据池");
	}
	/**
	 * 二级菜单编码定义（只包含纸票系统和电票系统的公共部分）
	 */
	public static final Map<String,String> K_MENU_CODE_2_NAME = new HashMap<String,String>();
	static{
		K_MENU_CODE_2_NAME.put("01", "承兑");
		K_MENU_CODE_2_NAME.put("02", "贴现");
		K_MENU_CODE_2_NAME.put("03", "转入");
		K_MENU_CODE_2_NAME.put("04", "转出");
		K_MENU_CODE_2_NAME.put("05", "返售");
		K_MENU_CODE_2_NAME.put("06", "回购");
		K_MENU_CODE_2_NAME.put("07", "质押");
		K_MENU_CODE_2_NAME.put("08", "解质押");
		K_MENU_CODE_2_NAME.put("09", "托收");
	}
	/**
	 * 交易模块编码
	 * @author fqz
	 *
	 */
	public static interface K_MODEL_CODE{
		/**承兑*/
		public static final String K_MODEL_CODE_ACPT="01";
		/**贴现*/
		public static final String K_MODEL_CODE_DISC="02";
		/**转入*/
		public static final String K_MODEL_CODE_REBUY="03";
		/**转出*/
		public static final String K_MODEL_CODE_SALE="04";
		/**返售*/
		public static final String K_MODEL_CODE_SALEBACK="05";
		/**回购*/
		public static final String K_MODEL_CODE_BUYBACK="06";
		/**质押*/
		public static final String K_MODEL_CODE_SAVE="07";
		/**解质押*/
		public static final String K_MODEL_CODE_GET="08";
		/**托收*/
		public static final String K_MODEL_CODE_SUBCOLL="09";
	}
	/**
	 * 票据当前状态标识
	 * @author fqz
	 *
	 */
	public static interface K_CUR_STATUS_FLAG{
		public static final String K_CUR_STATUS_FLAG_B = "B";//B-提示承兑  01-承兑
		public static final String K_CUR_STATUS_FLAG_F = "F";//F-买断式贴现  02-贴现
		public static final String K_CUR_STATUS_FLAG_G = "G";//G-回购式贴现  02-贴现
		public static final String K_CUR_STATUS_FLAG_O = "O";//O-质押  07-质押
		public static final String K_CUR_STATUS_FLAG_P = "P";//P-解质押  08-解质押
		public static final String K_CUR_STATUS_FLAG_R = "R";//R-提示付款  09-托收
		public static final String K_CUR_STATUS_FLAG_S = "S";//S-逾期提示付款 09-托收
		public static final String K_CUR_STATUS_FLAG_I = "I";//I-买断式转贴现
		public static final String K_CUR_STATUS_FLAG_J = "J";//J-回购式转贴现
		public static final String K_CUR_STATUS_FLAG_K = "K";//K-回购式转贴现赎回
		public static final String K_CUR_STATUS_FLAG_N = "N";//N-回购式再贴现赎回
		public static final String K_CUR_STATUS_FLAG_L = "L";//L-买断式再贴现
		public static final String K_CUR_STATUS_FLAG_M = "M";//M-回购式再贴现
	}
	
	/**
	 * 是否标志
	 */
	public static interface K_YORN {
		/** 字典名称 */
		public final static  String NAME = "K_YORN";
		/** 0-否*/
		public final static  String K_YORN_NO = "0";
		/** 1-是 */
		public final static  String K_YORN_YES = "1";

	}
	
	/**
	 * 是否锁定
	 */
	public static interface K_LOCK {
		/** 字典名称 */
		public final static  String NAME = "K_LOCK";
		/** 0-未锁定*/
		public final static  String K_LOCK_NO = "0";
		/** 1-已锁定 */
		public final static  String K_LOCK_YES = "1";

	}
	
	/**
	 * 票据类型
	 */
	public static interface K_BILL_CLASS {
		/** 字典名称 */
		public final static  String NAME = "K_BILL_CLASS";
		/** 1-纸票*/
		public final static  String K_ENTY_BILL = "1";
		/** 2-电票 */
		public final static  String K_ELEC_BILL = "2";

	}
	
	/**
	 * 证书绑定状态
	 */
	public static interface K_ECDS_CERT_STATUS {
		/** 字典名称 */
		public final static  String NAME = "K_ECDS_CERT_STATUS";
		/** 00-未绑定*/
		public final static  String K_ECDS_CERT_STATUS_NO = "00";
		/** 01-已绑定 */
		public final static  String K_ECDS_CERT_STATUS_YES = "01";

	}
	
	/**
	 * 票据分类
	 */
	public static interface K_BILL_TYPE {
		/** 字典名称 */
		public final static  String NAME = "K_BILL_TYPE";
		/** 1-银票*/
		public final static  String K_BANK_BILL = "1";
		/** 2-商票*/
		public final static  String K_CORP_BILL = "2";

	}
	
	/**
	 * 票据产品签约状态
	 */
	public static interface K_SIGNSTATUS {
		/** 字典名称 */
		public final static  String NAME = "K_SIGNSTATUS";
		/** 01-签约*/
		public final static  String K_SIGNSTATUS_YES = "01";
		/** 02-解约*/
		public final static  String K_SIGNSTATUS_NO = "02";

	}
	
	/**
	 * 业务参与者角色
	 */
	public static interface K_BUSSINESS_ROLE {
		/** 字典名称 */
		public final static  String NAME = "K_BUSSINESS_ROLE";
		/** RC00-接入行行*/
		public final static  String BUSSINESS_ROLE0 = "RC00";
		/** RC01-企业*/
		public final static  String BUSSINESS_ROLE1 = "RC01";
		/** RC02-人民银行*/
		public final static  String BUSSINESS_ROLE2 = "RC02";
		/** RC03-被代理行*/
		public final static  String BUSSINESS_ROLE3 = "RC03";
		/** RC04-被代理财务公司*/
		public final static  String BUSSINESS_ROLE4 = "RC04";
		/** RC05-接入财务公司*/
		public final static  String BUSSINESS_ROLE5 = "RC05";

	}
	
	/**
	 * 业务参与者类别
	 */
	public static interface K_BUSSINESS_CTG {
		/** 字典名称 */
		public final static  String NAME = "K_BUSSINESS_CTG";
		/**  01--直接参与人行*/
		public final static  String BUSSINESS_CTG01 = "01";
		/** 02--直接参与国库*/
		public final static  String BUSSINESS_CTG02 = "02";
		/** 03-- EIS转换中心*/
		public final static  String BUSSINESS_CTG03 = "03";
		/** 04--直接参与商业银行*/
		public final static  String BUSSINESS_CTG04 = "04";
		/** 05-开户特许直接参与者*/
		public final static  String BUSSINESS_CTG05 = "05";
		/** 06-开户特许间接参与者*/
		public final static  String BUSSINESS_CTG06 = "06";
		/** 07--间接参与者*/
		public final static  String BUSSINESS_CTG07 = "07";
		/** 08-无户特许直接参与者(债券)*/
		public final static  String BUSSINESS_CTG08 = "08";

	}
	
	/**
	 * 营业状态
	 */
	public static interface K_ECDS_SYSSTATUS {
		/** 字典名称 */
		public final static  String NAME = "K_ECDS_SYSSTATUS";
		/** 00-营业前准备*/
		public final static  String ECDS_SYSSTATUS_BEFORE = "00";
		/** 10-日间处理 */
		public final static  String ECDS_SYSSTATUS_RUN = "10";
		/** 20-营业截止 */
		public final static  String ECDS_SYSSTATUS_AFTER = "20";
		/** 30-日终 */
		public final static  String ECDS_SYSSTATUS_LAST = "30";

	}
	
	/**
	 * 报文日志中标识报文发送成功标识
	 */
	public static interface K_DRAFTLOG_SEND {
		/** 字典名称 */
		public final static  String NAME = "K_DRAFTLOG_SEND";
		/** 1-成功*/
		public final static  String K_DRAFTLOG_SEND_TRUE = "1";
		/** 0-失败 */
		public final static  String K_DRAFTLOG_SEND_FALSE = "0";
	}
	
	/**
	 * 报文日志中标识报文处理成功
	 */
	public static interface K_DRAFTLOG_PROCESS {
		/** 字典名称 */
		public final static  String NAME = "K_DRAFTLOG_PROCESS";
		/** 1-成功*/
		public final static  String K_DRAFTLOG_PROCESS_TRUE = "1";
		/** 0-失败 */
		public final static  String K_DRAFTLOG_PROCESS_FALSE = "0";
	}
	
	/**
	 * 报文日志中标识报文
	 */
	public static interface K_DRAFTLOG {
		/** 字典名称 */
		public final static  String NAME = "K_DRAFTLOG";
		/** 1-接收*/
		public final static  String K_DRAFTLOG_IN = "1";
		/** 0-发送 */
		public final static  String K_DRAFTLOG_OUT = "0";
	}
	
	/**
	 * 报文日志实体类型
	 */
	public static interface K_DRAFTLOG_ENTITY {
		/** 字典名称 */
		public final static  String NAME = "K_DRAFTLOG_ENTITY";
		/** 0-票据*/
		public final static  String K_DRAFTLOG_ENTITY_BILL = "0";
		/** 1-合同 */
		public final static  String K_DRAFTLOG_ENTITY_CONTRACT = "1";
	}
	
	/**
	 * 票据状态
	 */
	public static interface K_F001 {
		/** 字典名称 */
		public final static  String NAME = "K_F001";

	}
	
	/**
	 * 票据状态
	 */
	public static interface K_F002 {
		/** 字典名称 */
		public final static  String NAME = "K_F002";

	}
	
	/**
	 * 国籍
	 */
	public static interface K_NATIONALITY {
		/** 字典名称 */
		public final static  String NAME = "K_NATIONALITY"; //A041

	}
	
	/**
	 * 证件类型
	 */
	public static interface K_CARDTYPE {
		/** 字典名称 */
		public final static  String NAME = "K_CARDTYPE";  //A015
		
		public static final String CUST_CETIFICATE_TYPE = "ZC01";//电票签约需要
	}
	
	/**
	 * 学位
	 */
	public static interface K_DEGREE {
		/** 字典名称 */
		public final static  String NAME = "K_DEGREE";           //A019

	}
	
	/**
	 * 职业职称
	 */
	public static interface K_POSITION {
		/** 字典名称 */
		public final static  String NAME = "K_POSITION";          //A020

	}
	
	/**
	 * 省份
	 */
	public static interface K_PROV {
		/** 字典名称 */
		public final static  String NAME = "K_PROV";             //A040
	}
	
	/**
	 * 市
	 */
	public static interface K_CITY {
		/** 字典名称 */
		public final static  String NAME = "K_CITY"; //A043
	}
	
	/**
	 * 行分类
	 */
	public static interface K_BANKSORT {
		/** 字典名称 */
		public final static  String NAME = "K_BANKSORT"; //A044
	}
	
	/**
	 * 行大类
	 */
	public static interface K_BANKGROUP {
		/** 字典名称 */
		public final static  String NAME = "K_BANKGROUP"; //A045
	}
	
	/**
	 * 行级别
	 */
	public static interface K_BANKLEVEL {
		/** 字典名称 */
		public final static  String NAME = "K_BANKLEVEL"; //A025
	}
	
	/**
	 * 金融机构分类
	 */
	public static interface K_FITYPE {
		/** 字典名称 */
		public final static  String NAME = "K_FITYPE"; //A027
	}
	
	/**
	 * 行业
	 */
	public static interface K_TRADE {
		/** 字典名称 */
		public final static  String NAME = "K_TRADE"; //A026
	}
	
	
	/**
	 * 性质
	 */
	public static interface K_QUALITY {
		/** 字典名称 */
		public final static  String NAME = "K_QUALITY"; //A021
	}
	
	/**
	 * 规模
	 */
	public static interface K_ORGSIZE {
		/** 字典名称 */
		public final static  String NAME = "K_ORGSIZE"; //A022
	}
	
	/**
	 * 组织形式
	 */
	public static interface K_ORGTYPE {
		/** 字典名称 */
		public final static  String NAME = "K_ORGTYPE"; //A023
	}
	
	/**
	 * 信用等级
	 */
	public static interface K_CREDITCLASS {
		/** 字典名称 */
		public final static  String NAME = "K_CREDITCLASS"; //A024
	}
	
	/**
	 * 货币币种
	 */
	public static interface K_REGTCURY {
		/** 字典名称 */
		public final static  String NAME = "K_REGTCURY";   //A028
	}
	
	/**
	 * 企业类型
	 */
	public static interface K_ECOCTYPE {
		/** 字典名称 */
		public final static  String NAME = "K_ECOCTYPE";   //A029
	}
	
	/**
	 * 评级机构
	 */
	public static interface K_CREDITAGENCY {
		/** 字典名称 */
		public final static  String NAME = "K_CREDITAGENCY";  //A046
	}
	
}