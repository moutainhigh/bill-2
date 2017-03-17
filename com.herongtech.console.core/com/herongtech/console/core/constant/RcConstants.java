/********************************************
 * 文件名称: RcConstants.java
 * 系统名称: 基础技术平台V2.0
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: 2.0.0.1
 * 开发人员: zcz
 * 开发时间: 2016-8-10 下午01:48:52
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期    修改人员    修改说明
 *********************************************/
package com.herongtech.console.core.constant;




public final class RcConstants {
	
	/**
	 * 我行承兑标志：是我行承兑
	 */
	public static final String ACPT_YES = IConstants.One;

	/**
	 * 我行承兑标志：非我行承兑
	 */
	public static final String ACPT_NO = IConstants.Zero;
	/**
	 * 同意签收
	 */
	public static final String SIGNUP_MARK_CODE1 = "SU00";

	/**
	 * 拒绝签收
	 */
	public static final String SIGNUP_MARK_CODE2 = "SU01";

	/**
	 * 是否再贴现 "1":再贴现
	 */
	public static final String REDISCOUNT_CENTER = IConstants.One;
	/**
	 * "2":央行卖票
	 */
	public static final String REDISCOUNT_CENTER_OUT = IConstants.Two;

	/**
	 * 回滚
	 */
	public static final String COMMON_ROLLBACK = "rollback";
	/**
	 * 回购标志：回购
	 */
	public static final String REGRESS_YES = IConstants.One;

	/**
	 * 回购标志：不回购
	 */
	public static final String REGRESS_NO = IConstants.Zero;
	
	/**
	 * 签收标志：已签收
	 */
	public static final String SIGN_YES = IConstants.One;

	/**
	 * 签收标志：未签收
	 */
	public static final String SIGN_NO = IConstants.Zero;
	
	/** 通用报文接收配置常量* */
	/**
	 * 通用部分模块名
	 */
	public static final String CA = "CA"; //

	/**
	 * 通用转发
	 */
	public static final String TYPE_TRANS = "commonTrans"; //

	/**
	 * 通用确认
	 */
	public static final String TYPE_STATUS = "commonStatus";
	
	/**
	 * 031的通用确认
	 */
	public static final String TYPE_STATUS_for031 = "031commonStatus";
	
	/**
	 * 申请的通用确认
	 */
	public static final String TYPE_STATUS_forApply = "applycommonStatus";
	/**
	 * 撤销的通用确认032的033回调
	 */
	public static final String TYPE_STATUS_for032 = "032commonStatus";

	/**
	 * 通用签收
	 */
	public static final String TYPE_SIGNUP = "commonSignup";

	/**
	 * 通用撤回
	 */
	public static final String TYPE_CANCEL = "commonCancel";

	/**
	 * 通用业务通知
	 */
	public static final String TYPE_NOTIFY = "commonNotify";
	
	/**
	 * 040状态变更
	 */
	public static final String TYPE_SYNCHSTATUS = "synchStatus";
	
	
	
	/**
	 * 035转发
	 */
	public static final String TYPE_EXCEPTIONNOTIFY = "exceptionNotify";
	

	// 业务类别
	/**
	 * 出票登记
	 */
	public static final String COMMON_REGISTER = "register";

	/**
	 * 提示承兑
	 */
	public static final String COMMON_ACCEPTOR = "acceptor"; //

	/**
	 * 提示收票
	 */
	public static final String COMMON_ISSURANCE = "issurance"; //

	/**
	 * 撤票
	 */
	public static final String COMMON_REBACK = "reback"; //

	/**
	 * 背书
	 */
	public static final String COMMON_ENDORSE = "endorse"; //

	/**
	 * 买断式贴现
	 */
	public static final String COMMON_DISCOUNT1 = "discount1"; //

	/**
	 * 回购式贴现
	 */
	public static final String COMMON_DISCOUNT2 = "discount2"; //

	/**
	 * 贴现赎回
	 */
	public static final String COMMON_DISCOUNT_BACK = "discountBack"; //

	/**
	 * 买断式转贴现
	 */
	public static final String COMMON_REDISCOUNT1 = "rediscount1"; //

	/**
	 * 回购式转贴现
	 */
	public static final String COMMON_REDISCOUNT2 = "rediscount2"; //

	/**
	 * 转贴现赎回
	 */
	public static final String COMMON_REDISCOUNT_BACK = "rediscountBack"; //

	/**
	 * 买断式再贴现
	 */
	public static final String COMMON_REDISCOUNT_CENTER1 = "rediscountCenter1"; //

	/**
	 * 回购式再贴现
	 */
	public static final String COMMON_REDISCOUNT_CENTER2 = "rediscountCenter2"; //

	/**
	 * 再贴现赎回
	 */
	public static final String COMMON_REDISCOUNT_CENTER_BACK = "rediscountCenterBack";//

	/**
	 * 央行卖出
	 */
	public static final String COMMON_CENTER_SALE = "centerSale"; //

	/**
	 * 保证
	 */
	public static final String COMMON_GUARNT = "guarnt"; //

	/**
	 * 质押
	 */
	public static final String COMMON_COLLECT = "collect"; //
	/**
	 * 入池
	 */
	public static final String COMMON_INPOOL = "inPool"; //

	/**
	 * 质押解除
	 */
	public static final String COMMON_UNCOLLECT = "uncollect"; //

	/**
	 * 提示付款
	 */
	public static final String COMMON_PRESENTATION = "presentation"; //

	/**
	 * 逾期提示付款
	 */
	public static final String COMMON_OVERDUE = "overdue"; //

	/**
	 * 拒付追索
	 */
	public static final String COMMON_RECOURSE1 = "recourse1"; //

	/**
	 * 非拒付追索
	 */
	public static final String COMMON_RECOURSE2 = "recourse2"; //

	/**
	 * 拒付追索同意清偿
	 */
	public static final String COMMON_RECOURSEAGREEMENT1 = "recourseAgreement1"; //

	/**
	 * 非拒付追索同意清偿
	 */
	public static final String COMMON_RECOURSEAGREEMENT2 = "recourseAgreement2"; //

	/**
	 * 通用回复
	 */
	public static final String COMMON_SIGNUP = "signup"; //

	/**
	 * 撤回
	 */
	public static final String COMMON_DRAWBACK = "drawback"; //

	/**
	 * 清分失败回复
	 */
	public static final String COMMON_RESULTNOTIFY = "resultNotify"; //

	/**
	 * 其它
	 */
	public static final String COMMON_ELSE = "else";
	
	/**业务参与者类别： 
	 * 	RC00接入行行
	 * 	RC01企业
	 *  RC02人民银行
	 *  RC03被代理行
	 *  RC04被代理财务公司
	 *  RC05接入财务公司
	 */
	public static final String BUSSINESS_ROLE0 = "RC00";

	public static final String BUSSINESS_ROLE1 = "RC01";

	public static final String BUSSINESS_ROLE2 = "RC02";

	public static final String BUSSINESS_ROLE3 = "RC03";

	public static final String BUSSINESS_ROLE4 = "RC04";

	public static final String BUSSINESS_ROLE5 = "RC05";

	
   /** 业务参与者类型
	*   01--直接参与人行
	*	02--直接参与国库
	*	03-- EIS转换中心
	*	04--直接参与商业银行
	*	05-开户特许直接参与者
	*	06-开户特许间接参与者
	*	07--间接参与者
	*	08-无户特许直接参与者(债券)
	*/
	public static final String BUSSINESS_CTGY01 = "01";

	public static final String BUSSINESS_CTGY02 = "02";

	public static final String BUSSINESS_CTGY03 = "03";

	public static final String BUSSINESS_CTGY04 = "04";

	public static final String BUSSINESS_CTGY05 = "05";

	public static final String BUSSINESS_CTGY06 = "06";
	
	public static final String BUSSINESS_CTGY07 = "07";
	
	public static final String BUSSINESS_CTGY08 = "08";

	
	/**
	 * 代理签章标识 PS00开户机构代理回复签章
	 * 
	 */
	public static final String ProxySignatureCode1 = "PS00";

	/**
	 * 代理签章标识 PS01客户自己签章
	 */
	public static final String ProxySignatureCode2 = "PS01";

	/**
	 * 代理申请标识 PP00开户机构代理申请签章
	 * 
	 */
	public static final String ProxyPropositionCode1 = "PP00";

	/**
	 *  代理申请标识 PP01客户自己签章
	 */
	public static final String ProxyPropositionCode2 = "PP01";
	/**
	 * 普通确认报文，操作失败
	 */
	public static final String ECDS_OP_FAIL = IConstants.Zero;
	/**
	 * 普通拒绝签收
	 */
	public static final String ECDS_REFUSE_SIGN_COMMON = "11";
	/**
	 * 提示付款签收拒绝
	 */
	public static final String ECDS_REFUSE_SIGN_PRESENT = "12";
	/**
	 * 逾期提示付款拒绝签收
	 */
	public static final String ECDS_REFUSE_SIGN_OVERDUE = "13";
	/**
	 * 禁止线上清算开关
	 */
	public static final String SEETLE_MARK_SWITCH_FORBIDDEN = IConstants.One;
	
	/**发送方费率ECDSCOMMONDATA commonalityDataCode*/
	public static final String COMMONALITY_DATA_CODE_SEND = "CUST_CHARGE_REQUEST";
	
	/**接收方费率ECDSCOMMONDATA commonalityDataCode*/
	public static final String COMMONALITY_DATA_CODE_RECIEVE="CUST_CHARGE_RESPONSE";
	
	/**签约账号欠费状态为正常**/
	public static final String BUS_SETTLE_ACT_STATUS_ARREARAGE_NO =IConstants.Zero;
	
	/**签约账号欠费状态为欠费**/
	public static final String BUS_SETTLE_ACT_STATUS_ARREARAGE_YES =IConstants.One;
	
	/**未计费**/
	public static final String ISCHARGED_STATUS_NO =IConstants.Zero;//
	
	/**已计费**/
	public static final String ISCHARGED_STATUS_YES = IConstants.One;//
	
	/**
	 * 1 yes;
	 * 0 no
	 */
	public static final String YES = IConstants.One;
	public static final String NO = IConstants.Zero;
	
	/**
	 * 清偿标记
	 */
	public static final String RECOUSE_FLAG_YES= IConstants.One;
	/**
	 * 清偿标记数据库默认值
	 */
	public static final String RECOUSE_FLAG_DEFAULT= IConstants.Zero;
	
	/**
	 * 发起渠道 '1':网银
	 */
	public static final String COMES_FROM_WWW = IConstants.One;

	/**
	 * 发起渠道 '2'：银行
	 */
	public static final String COMES_FROM_SYS = IConstants.Two;

	/**
	 * 历史回退
	 */
	public static final String HISTORY_BACK = IConstants.One;
	/**
	 * 通用确认---处理成功
	 */
	public static final String CONFIRM_SUCCESS = IConstants.One;
	
	/**
	 * 背书登记
	 */
	public static final String IS_ADD_ENDORSE = IConstants.One;
	
	/**
	 * 检查状态
	 */
	public static final String CHECK_STATUS_YES = IConstants.One;
	/**
	 * 不检查状态
	 */
	public static final String CHECK_STATUS_NO = IConstants.One;

	/**
	 * 回退历史
	 */
	public static final String BACK_HISTORY_YES = IConstants.One;
	/**
	 * 不回退历史
	 */
	public static final String BACK_HISTORY_NO = IConstants.Zero;

	/**
	 * 增加背书
	 */
	public static final String ADD_ENDO_YES = IConstants.One;
	/**
	 * 不增加背书
	 */
	public static final String ADD_ENDO_NO = IConstants.Zero;
	
	
	/**
	 * 背书
	 */
	public static final String ENDO_ZERO = IConstants.Zero;
	/**
	 * 买断式贴现
	 */
	public static final String ENDO_One = IConstants.One;
	/**
	 * 回购式贴现
	 */
	public static final String ENDO_One_1 = "1_1";
	/**
	 *回购式贴现赎回
	 */
	public static final String ENDO_Two = IConstants.Two;
	/**
	 *  买断式转贴现
	 */
	public static final String ENDO_Three = IConstants.Three;
	/**
	 *  回购式转贴现
	 */
	public static final String ENDO_Three_1 = "3_1";
	/**
	 * 回购式转贴现赎回
	 */
	public static final String ENDO_Four = IConstants.Four;
	/**
	 *买断式再贴现
	 */
	public static final String ENDO_Five = IConstants.Five;
	/**
	 *回购式再贴现
	 */
	public static final String ENDO_Five_1 = "5_1";
	/**
	 *  回购式再贴现赎回
	 */
	public static final String ENDO_Six = IConstants.Six;
	/**
	 * 央行卖票
	 */
	public static final String ENDO_Seven = IConstants.Seven;
	/**
	 * 保证
	 */
	public static final String ENDO_Eight = IConstants.Eight;
	/**
	 * 质押
	 */
	public static final String ENDO_Nine = IConstants.Nine;
	/**
	 * 提示付款
	 */
	public static final String ENDO_Ten = IConstants.Ten;
	/**
	 * 追索
	 */
	public static final String ENDO_Eleven = IConstants.Eleven;
	
	//RC hist 买入类型
		/**
		 * 1-贴现
		 * 2-赎回式贴现
		 * 3-系统内买断
		 * 4-系统内回购式买入
		 * 5-同业间买断
		 * 6-同业间回购式买入
		 * 7-央行卖票
		 * 8-买入约定
		 * 9-卖断到期
		 * 10-质押
		 * 11-基本业务
		 * 12-衍生业务
		 * 13-电票托管
		 */
		public static final String BUY_DISC = "1"; //贴现
		public static final String BUY_DISC_REGRESS = "2"; //赎回式贴现
		public static final String BUY_INNER_NOREGRESS = "3"; //系统内买断
		public static final String BUY_INNER_REGRESS = "4"; //系统内回购式买入
		public static final String BUY_OUT_NOREGRESS = "5"; //同业间买断
		public static final String BUY_OUT_REGRESS = "6"; //同业间回购式买入
		public static final String BUY_CENTER = "7"; //央行卖票
		public static final String BUY_BIRD = "8"; //买入约定
		public static final String BUY_BIRD_DUE = "9"; //卖断到期
		public static final String BUY_COLL = "10"; //质押
		public static final String BUY_STORE = "11"; //基本业务(代保管)
		public static final String BUY_INPOOL = "12"; //衍生业务(入池)
		public static final String BUY_ELEC_DEPOSIT = "13"; //电票托管
		
		/**
		 * 报文申请、签收标识
		 */
		public static final String DRAFT_APPLY = "1"; //申请
		public static final String DRAFT_SIGN = "2"; //签收
		
		public static final String RGCT_STATUS_PRSNT_REJECT_R_22="R_22";
		public static final String RGCT_STATUS_PRSNT_REJECT_OVERDUE_S_22="S_22";
		
		/**以下6种状态为拒付（非拒付）追索的相关状态**/
		
		public static final String RECOURSE_T25 = "T_25"; //拒付追索待清偿
		public static final String RECOURSE_W25 = "W_25"; //非拒付追索待清偿
		public static final String RECOURSE_W03 = "W_03"; //非拒付追索已撤销(032单独处理)
		
		public static final String RECOURSE_U08 = "U_08"; //拒付追索同意清偿待签收
		public static final String RECOURSE_U02 = "U_02"; //拒付追索同意清偿已签收
		public static final String RECOURSE_U16 = "U_16"; //拒付追索同意清偿已结清(临时)
		
		public static final String RECOURSE_V08 = "V_08"; //非拒付追索同意清偿待签收
		public static final String RECOURSE_V02 = "V_02"; //非拒付追索同意清偿已签收
		public static final String RECOURSE_V16 = "V_16"; //非拒付追索同意清偿已结清(临时)
		
		//贴现申请
		public static final String DISCTYPE_ZERO = "0"; //买断式
		public static final String DISCTYPE_ONE = "1"; //赎回式
		
		public static final String SETTLEMENTMARK_ZERO = "0"; //线下清算
		public static final String SETTLEMENTMARK_ONE = "1"; //线上清算
		
		/**
		 * 系统内标志：系统内
		 */
		public static final String INNER_YES = IConstants.One;

		/**
		 * 系统内标志：系统外
		 */
		public static final String INNER_NO = IConstants.Zero;
		
		
		//票款收回类型
		public static final String GATH_TYPE_DEFULT = "0"; //默认值--新纪录
		public static final String GATH_TYPE_COMMON = "1"; //正常--记账完成
		public static final String GATH_TYPE_SALE = "2"; //转出卖断记账/取票出池/解质押
		public static final String GATH_TYPE_SUB = "3"; //托收收回记账
		public static final String GATH_TYPE_BUYBACK = "4"; //贴现赎回/同业返售/系统内返售(系统内回购记账)
		
		//当前状态--curStatus
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
		
		/**
		 * 0-初始值，
		 * 1，正常（默认未收回）；
		 * 2，卖断；
		 * 3，托收(结清)；
		 * 4，返售赎回 
		 * 5、到期回购 
		 * 6、借票出库 
		 * 7、发托退票 
		 * 8、发托出库
		 * 9、贴现赎回 
		 * 10、借票还票入库*/
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
		 * 票据最初来源：
		 * 1	贴现
			2	转入
			3	质押
			4	票据池

		 */
		public static final String BILL_SOURCE_DISC="1";
		public static final String BILL_SOURCE_REBUY="2";
		public static final String BILL_SOURCE_COLLATERALIZATION="3";
		
}
