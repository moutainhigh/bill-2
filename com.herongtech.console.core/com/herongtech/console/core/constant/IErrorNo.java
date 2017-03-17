/************************************************************
 * 文件名称: IErrorNo.java
 * 系统名称: 合融技术平台
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: 2.0.0.1
 * 开发人员: 
 * 开发时间: 
 * 审核人员:
 * 相关文档: 
 * 修改记录: 修改日期    修改人员    修改说明
 *        20160627-01  yanjl  新增
 ************************************************************/
package com.herongtech.console.core.constant;

public class IErrorNo {
	public static final String  HERONGTECH_VERSION="@system  合融技术平台 @version 2.0.0.1  @lastModiDate @describe ";
	
	
	/* 程序运行相关错误 ,系统类(0XXX)*/
	/**成功*/
	public final static   String ERR_SUCCESS                = "0000";
	/**部分成功*/
	public final static   String ERR_PARTSUCC               = "BBSP1001";
	/**全部失败*/
	public final static   String ERR_ALLFAIL                = "BBSP1002";
	
	/**发送主机失败*/
	public final static   String ERR_SENDHOST               = "BBSP1003";
	/**接收主机应答失败*/
	public  final static  String ERR_RECVHOST               = "BBSP1004";
	/**文件不存在*/
	public final static   String ERR_FILENOTEXIST           = "BBSP1005";
	
	/**前台请求解包失败*/
	public final static   String ERR_UNPACKQT               = "BBSP1006";
	/**返回前台打包失败*/
	public final static   String ERR_PACKQT                 = "BBSP1007";
	/**接收后台解包失败*/
	public final static   String ERR_UNPACKHT               = "BBSP1008";
	/**发送后台打包失败*/
	public final static   String ERR_PACKHT                 = "BBSP1009";
	/**发送后台接口失败*/
	public final static   String ERR_SENDPKG                = "BBSP1008";
	/**通讯超时*/
	public final static   String ERR_TIMEOUT                = "BBSP1009";
	/**接收后台接口失败*/
	public final static   String ERR_RECVPKG                = "BBSP1010";
	/**取系统参数失败*/
	public final static   String ERR_GETSYSARG              = "BBSP1011";
	/**生成交易流水号失败*/
	public final static   String ERR_GENSERIAL              = "BBSP1012";
	/**打开文件失败*/
	public final static   String ERR_FILEOPEN               = "BBSP1013";
	/**创建文件失败*/
	public final static   String ERR_FILECREATE             = "BBSP1014";
	/**文件校验失败*/
	public final static   String ERR_FILECHECK              = "BBSP1015";
	/**取配置参数错*/
	public final static   String ERR_GETCFGITEM             = "BBSP1017";
	/**修改配置参数错*/
	public final static   String ERR_MODIFYCFGITEM          = "BBSP1018";
	/**报文非法*/
	public final static   String ERR_PACKAGE                = "BBSP1019";
	/**参数错*/
	public final static   String ERR_PARAMETER              = "BBSP1020";
	/**转换成请求包出错*/
	public final static   String ERR_TRANSTOREPORT          = "BBSP1021";
	
	/**数据库错误,详看错误日志*/
	public  final static  String ERR_DBERR                  = "BBSP1022";
	/**查询数据库失败*/
	public  final static  String ERR_DBSELECT               = "BBSP1023";
	/**插入数据库失败*/
	public  final static  String ERR_DBINSERT               = "BBSP1024";
	/**更新数据库失败*/
	public  final static  String ERR_DBUPDATE               = "BBSP1025";
	/**删除记录失败*/
	public  final static  String ERR_DBDELETE               = "BBSP1026";
	
	/**查询无记录*/
	public  final static  String ERR_NORECORD               = "BBSP1027";
	
	/**主机成功*/
	public final static   String ERR_HOSTSUCC               = "AAAAAAA";
	/**未知错误*/
	public final static   String ERR_DEFAULT                = "BBSP9999";
	
	
	/**业务错误信息*/
	public final static   String ERR_CUSTACCOUNTNULL        = "BBSP2001";
	
	/**
	 * RC_ERR_001：无法找到对应的票据对象：id={0}
	 */
	public static final String ERR_RC_001 = "RC_ERR_001";
	/**
	 * RC_ERR_002：当前票据（id={0}）状态是{1}，”{2}“需要的状态是{3}
	 */
	public static final String ERR_RC_002 = "RC_ERR_002";
	/**
	 * RC_ERR_003：无法找到”{0}“对应的状态切换记录
	 */
	public static final String ERR_RC_003 = "RC_ERR_003";
	/**
	 * RC_ERR_004：票据号、出票日、付款行行号必须输入
	 */
	public static final String ERR_RC_004 = "RC_ERR_004";
	/**
	 * RC_ERR_005：票据{0}已经被机构{1}的{2}流程锁定，无法解锁
	 */
	public static final String ERR_RC_005 = "RC_ERR_005";
	/**
	 * RC_ERR_006：登记中心ID或者历史ID不能为空
	 */
	public static final String ERR_RC_006 = "RC_ERR_006";
	/**
	 * RC_ERR_007：无法找到对应的方法：subSystem={0}, interfaceName={1}, methodName={2}
	 */
	public static final String ERR_RC_007 = "RC_ERR_007";
	
	/**
	 *  RC_ERR_008：背书人、被背书人名不能为空
	 */
	public static final String ERR_RC_008 = "RC_ERR_008";
	
	/**
	 * BBSP0001： 同一签发机构项下银承协议编号必须唯一
	 */
	public static final String BBSP0001 = "BBSP0001";
	
	/**
	 * BBSP0002： 该批次已经承兑记账，无法撤销
	 */
	public static final String BBSP0002 = "BBSP0002";
	
	/**
	 * BBSP0003： 根据批次号[{0}]未能找到对应的银承协议信息
	 */
	public static final String BBSP0003 = "BBSP0003";
	
	/**
	 * 账户[{0}]对应的开户机构[{1}]不存在
	 */
	public static final String BBSP0004 = "BBSP0004";
	
	/**
	 * BBSP0005：还款账号[{0}]类型非法,账号产品编码[{1}]
	 */
	public static final String BBSP0005 = "BBSP0005";
	
	
	/**
	 * BBSP0006："该银承协议项下已经有承兑签收的票据"
	 */
	public static final String BBSP0006 = "BBSP0006";
	
	
	/**
	 * BBSP0007：银承协议项下票据明细不能超过100
	 */
	public static final String BBSP0007 = "BBSP0007";
	
	
	/**
	 * BBSP0008：必输项不能为空
	 */
	public static final String BBSP0008 = "BBSP0008";
	
	/**
	 * BBSP0008：参数非法
	 */
	public static final String BBSP0112 = "BBSP0112";
	
	/**
	 * BBSP0009：出票人客户号与出票人帐号不属于同一客户，请检查!  出票人账户类型非法
	 */
	public static final String BBSP0009 = "BBSP0009";
	
	/**
	 * BBSP0010：出票人账户类型非法  
	 */
	public static final String BBSP0010 = "BBSP0010";
	
	/**
	 * BBSP0011：出票人账户锁控制  
	 */
	public static final String BBSP0011 = "BBSP0011";
	
	
	/**
	 * BBSP0012：通用校验检查失败  
	 */
	public static final String BBSP0012 = "BBSP0012";
	
	/**
	 * BBSP0013：票据种类检查失败  
	 */
	public static final String BBSP0013 = "BBSP0013";
	/**
	 * BBSP0014：根据签发机构[{0}]和银承协议编号[{1}]未能找到对应的批次号信息
	 */
	public static final String BBSP0014 = "BBSP0014";
	
	
	/**
	 * BBSP0015：结算帐号的保证金限额必须为0
	 */
	public static final String BBSP0015 = "BBSP0015";
	/**
	 * BBSP0016：银承协议项下还款账号必须至少一个对公活期结算户!
	 */
	public static final String BBSP0016 = "BBSP0016";
	
	/**
	 * BBSP0017：贷款处理系统必须明确指定!
	 */
	public static final String BBSP0017 = "BBSP0017";
	
	
	/**
	 * BBSP0020：当前交易[{0}]已执行成功，不可重复执行
	 */
	public static final String BBSP0020 = "BBSP0020";
	/**
	 * BBSP0021：原交易[{0}]已冲正成功,不可重复冲正
	 */
	public static final String BBSP0021 = "BBSP0021";
	/**
	 * BBSP0022：纸质票据批次号不能输入
	 */
	public static final String BBSP0022 = "BBSP0022";
	/**
	 * BBSP0023：当前输入的出票人账号[{1}]与对应批次电票的出票人账号[{0}]不一致
	 */
	public static final String BBSP0023 = "BBSP0023";	
	/**
	 * BBSP0024：当前输入的出票人客户号[{1}]与对应批次电票的出票人客户号[{0}]不一致
	 */
	public static final String BBSP0024 = "BBSP0024";
	/**
	 * BBSP0030: 票号[{0}]:该票据当前状态不支持此项处理!
	 */
	public static final String BBSP0030 = "BBSP0030";
	public static final String BBSP0031 = "BBSP0031";
	
	/**
	 * 票号[{0}] , 当前在[{1}]机构库存, 出入库业务为[{2}], 操作状态为[{3}],请退回业务后重新进行操作!
	 */
	public static final String BBSP0032="BBSP0032";
	
	/**
	 * 票号[{0}] , 当前在[{1}]机构库存, 出入库业务为[{2}],请核实后重新进行操作!
	 */
	public static final String BBSP0033="BBSP0033";
	
	/**
	 * 票据形态：[billClass]字段必须为 "1" 或 "2"!
	 */
	public static final String BBSP0034="BBSP0034";
	/**
	 * 票据种类：[billType]字段必须为 "1" 或  "2"!
	 */
	public static final String BBSP0035="BBSP0035";
	
	/**
	 * 协议圈存编号不能输入
	 */
	public static final String BBSP0036="BBSP0036";
	
	/**
	 * BBSP0037：交易日期格式不正确!
	 */
	public static final String BBSP0037 = "BBSP0037";
	
	/**
	 * BBSP0038：账号[{0}]类型非法
	 */
	public static final String BBSP0038 = "BBSP0038";
	
	/**
	 * BBSP0039：贷款系统必须指定1-对公或2-对私
	 */
	public static final String BBSP0039 = "BBSP0039";
	
	/**
	 * BBSP0040：纸票单张票据金额不能超过十亿！
	 */
	public static final String BBSP0040 = "BBSP0040";
	
	
	/**
	 * BBSP0041：纸票票据清单必须有一条记录
	 */
	public static final String BBSP0041 = "BBSP0041";
	
	
	/**
	 * BBSP0042：账号[{0}]状态非法,该账号审批处理中,已创建!
	 */
	public static final String BBSP0042 = "BBSP0042";
	
	/**
	 * BBSP0043：账号[{0}]状态非法,该账号正在终止!
	 */
	public static final String BBSP0043 = "BBSP0043";
	/**
	 * BBSP0044：账号[{0}]状态非法,该账号已终止!
	 */
	public static final String BBSP0044 = "BBSP0044";
	
	/**
	 * BBSP0045：协议编号长度必须为1-16位
	 */
	public static final String BBSP0045 = "BBSP0045";
	
	/**
	 * BBSP0046：找不到挡板账号[{0}]的开户信息
	 */
	public static final String BBSP0046 = "BBSP0046";
	/**
	 * BBSP0047：解析extendData字段[{0}]域 [{1}]时异常，或者请在BPM进行转产品处理
	 */
	public static final String BBSP0047 = "BBSP0047";
	/**
	 * BBSP0048：不支持线下借记类型业务!
	 */
	public static final String BBSP0048 = "BBSP0048";
	/**
	 * BBSP0049：根据支付交易序号[{0}]\\日期[{1}]\\发起行[{2}]无法定位线上清算记录!
	 */
	public static final String BBSP0049 = "BBSP0049";
	/**
	 * BBSP0050：不支持[{0}]业务的线上[{1}]来帐通知
	 */
	public static final String BBSP0050 = "BBSP0050";
	/**
	 * BBSP0051：找不到业务接收行[{0}]机构信息!
	 */
	public static final String BBSP0051 = "BBSP0051";
	/**
	 * BBSP0052：承兑业务的接收行[{0}]必须为上线机构!
	 */
	public static final String BBSP0052 = "BBSP0052";
	/**
	 * BBSP0053：线上来帐金额和票面金额不一致
	 */
	public static final String BBSP0053 = "BBSP0053";
	/**
	 * BBSP0054：当前票据[{0}]为线上清算,需等待PE来帐通知后自动匹配订单!
	 */
	public static final String BBSP0054 = "BBSP0054";
	/**
	 * BBSP0055：当前票据[{0}]已关联订单,不可重复匹配!
	 */
	public static final String BBSP0055 = "BBSP0055";
	/**
	 * BBSP0056：当前占用总额度[{0}]大于订单可用额度[{1}]
	 */
	public static final String BBSP0056 = "BBSP0056";
	/**
	 * BBSP0057：未找到原渠道流水[{0}]对应的来帐登记记录!
	 */
	public static final String BBSP0057 = "BBSP0057";
	/**
	 * BBSP0058：线上清算业务不允许撤销来帐通知!
	 */
	public static final String BBSP0058 = "BBSP0058";
	/**
	 * BBSP0059：订单号[{0}]状态为[{1}]不可以冲正
	 */
	public static final String BBSP0059 = "BBSP0059";
	/**
	 * BBSP0060：来帐通知挂账订单已被部分核销!
	 */
	public static final String BBSP0060 = "BBSP0060";
	/**
	 * BBSP0061：输入的[{0}]值不正确,[{1}]正确的值为[{2}]!
	 */
	public static final String BBSP0061 = "BBSP0061";
	
	/**
	 * BBSP0062：当前交易流水号[{0}]已存在
	 */
	public static final String BBSP0062 = "BBSP0062";
	/**
	 * BBSP0063： 非上线行不允许进行此操作!
	 */
	public static final String BBSP0063 = "BBSP0063";
	/**
	 * BBSP0064： 协议到期日[{0}]不能小于当前系统营业日[{1}]!
	 */
	public static final String BBSP0064 = "BBSP0064";
	/**
	 * BBSP0065：协议出票日[{0}]不能大于到期日[{1}]!
	 */
	public static final String BBSP0065 = "BBSP0065";
	/**
	 * BBSP0066：原交易已冲正,不允许补记账!
	 */
	public static final String BBSP0066 = "BBSP0066";
	/**
	 * BBSP0067：原交易失败,不必冲正!
	 */
	public static final String BBSP0067 = "BBSP0067";	
	/**
	 * BBSP0068：原交易不存在!
	 */
	public static final String BBSP0068 = "BBSP0068";	
	/**
	 * BBSP0069：原交易正在处理中，不允许冲正!
	 */
	public static final String BBSP0069 = "BBSP0069";	
	/**
	 * BBSP0070：原交易已成功，但不允许冲正（暂不支持,预留暂不对PE公布）
	 */
	public static final String BBSP0070 = "BBSP0070";
	/**
	 * BBSP0071：原交易失败，不允许补记账!
	 */
	public static final String BBSP0071 = "BBSP0071";
	/**
	 * BBSP0072：原交易正在处理中,不允许补记账!
	 */
	public static final String BBSP0072 = "BBSP0072";
	
	/**
	 * BBSP0073：票据状态检查失败, 当前票据状态为[{0}]
	 */
	public static final String BBSP0073 = "BBSP0073";
	
	/**
	 * BBSP0074: 行内交易不允许线上清算
	 */
	public static final String BBSP0074 = "BC_SETTLEMENTONLINEFORBIDDEN_ERROR";
	
	/**
	 * BBSP0075：票号[{0}]:票据状态检查失败, 当前票据状态为[{1}],不允许该操作!
	 */
	public static final String BBSP0075 = "BBSP0075";
	
	/**
	 * BBSP0076：当前纸质银承票据已进行了委托收款登记,委托收款状态为[收到],不允许重复登记!
	 */
	public static final String BBSP0076 = "BBSP0076";
	
	/**
	 * BBSP0077：线上清算报文接收方机构号[{0}]和BPM入账账号[{1}]的开户机构号[{2}]不一致!
	 */
	public static final String BBSP0077 = "BBSP0077";
	
	/**
	 * BBSP0078：票据出票日[{0}]不能大于当前系统营业日[{1}]!
	 */
	public static final String BBSP0078 = "BBSP0078";
	
	/**
	 * BBSP0079：字段[{0}]长度不能大于[{1}]
	 */
	public static final String BBSP0079 = "BBSP0079";
	
	/**
	 * BBSP0080：票据种类检查失败,电子票据不支持此操作!
	 */
	public static final String BBSP0080 = "BBSP0080";
	
	/**
	 * BBSP0081：票据委托收款状态检查失败,当前委托收款状态为[{0}], 不允许重复登记!
	 */
	public static final String BBSP0081 = "BBSP0081";
	
	/**
	 * BBSP0082：票面收款人帐号、名称必输
	 */
	public static final String BBSP082 = "BBSP0082";
	
	
	/**
	 * BBSP0083：不存在的票号
	 */
	public static final String BBSP083 = "BBSP0083";
	
	/**
	 * BBSP0100：挡板账号[{0}]不支持[{1}]记的来帐业务!
	 */
	public static final String BBSP0100 = "BBSP0100";
	/**
	 * BBSP0101：线上清算业务类型和BPM入账账号类型校验不通过!
	 */
	public static final String BBSP0101 = "BBSP0101";
	
	/**
	 * BBSP0102: 协议已经发起扣款交易，等交易完成后再试!
	 */
	public static final String BBSP0102 = "BBSP0102";
	
	/**
	 * BBSP0103: 票据[{0}]已做过收到委托登记或注销不能进行票号互换!
	 */
	public static final String BBSP0103 = "BBSP0103";
	/**
	 * BBSP0104: 互换的票号必须为同一协议!
	 */
	public static final String BBSP0104 = "BBSP0104";
	
	/**
	 * BBSP0105: 票据[{0}]已挂失!
	 */
	public static final String BBSP0105 = "BBSP0105";
	/**
	 * BBSP0106: 票号[{0}]:状态必须为签发、扣款、垫款, 当前票据状态为[{1}],不允许该操作!
	 */
	public static final String BBSP0106 = "BBSP0106";
	
	/**
	 * BBSP0107:票号[{0}]:已被加入其他批次,不允许该操作!
	 */
	public static final String BBSP0107 = "BBSP0107";
	
	/**
	 * BBSP0108:纸票登记类型不能为空!
	 */
	public static final String BBSP0108 = "BBSP0108";
	
	/**
	 * BBSP0109指定的机构号{0}不存在
	 */
	public static final String BBSP0109 = "BBSP0109";
	
	/**
	 * BBSP0110 收款人账号[{0}]不能超过32位,纸质票据请确认收款人账号,电子票据请联系对方行撤回该申请!
	 */
	public static final String BBSP0110 = "BBSP0110";
	
	/**
	 * 当前营业日下存在正在处理的作业
	 */
	public static final String ERR_EOD_001="ERR_EOD_001";
	
	/**
	 * 当前作业正在处理
	 */
	public static final String ERR_EOD_002="ERR_EOD_002";
	
	/**
	 * BBSP9999：系统内部错误
	 */
	public static final String BBSP9999 ="BBSP9999";
	
	public static final String SERVICE_ERR_CODE_DEFAULT="SRV_ERR_001";//服务层默认异常
	/**系统管理异常***/
	public static final String SYS_USERNO_EXIST_ERR="SYS_USERNO_EXIST";//用户已经存在
	
	public static final String DATA_REFERED = "DATA_REFERED"; //数据已经被使用
	
	public static final String USER_FIRST_LOGIN="USER_FIRST_LOGIN";//用户第一次登录
	
	/**BA异常*/
	public static final String BA_BILL_NOT_FOUND="BA_SRV_ERR_BILL_001";//找不到指定票据	
	
	public static final String BA_BILL_SIGN_NOT_FOUND="BA_BILL_SIGN_NOT_FOUND";//查询未用退回信息失败
	
	/**BA业务层异常*/
	public static final String BD_BILL_INFO_ERROR0010="BD_BILL_INFO_ERROR0010";//票据类型复核不通过
	
	public static final String BD_BILL_INFO_ERROR0009="BD_BILL_INFO_ERROR0009";//利率复核不通过
	
	public static final String BD_BILL_INFO_ERROR0008="BD_BILL_INFO_ERROR0008";//利率类型复核不通过
	
	public static final String BD_BILL_INFO_ERROR0007="BD_BILL_INFO_ERROR0007";//利率不能为空
	
	public static final String BD_BILL_INFO_ERROR0006="BD_BILL_INFO_ERROR0006";//承兑行行号复核不通过
	
	public static final String BD_BILL_INFO_IFSAMECITY="BD_BILL_INFO_IFSAMECITY";//是否同城异地
	
	public static final String BD_BILL_INFO_ERROR0005="BD_BILL_INFO_ERROR0005";//票据种类复核不通过
	
	public static final String BD_BILL_INFO_ERROR0004="BD_BILL_INFO_ERROR0004";//票据类型复核不通过
	
	public static final String BD_BILL_INFO_ERROR0003="BD_BILL_INFO_ERROR0003";//票号复核不通过
	
	public static final String BD_DiscAccrualType_Error="BD_DiscAccrualType_Error";//顺延方式选择不正确
	
	public static final String BD_PostponeDays_Error="BD_PostponeDays_Error";//顺延天数选择不正确
	
	public static final String BD_BILL_INFO_ERROR0002="BD_BILL_INFO_ERROR0002";//输入的承兑行行号为空
	
	public static final String BD_BILL_INFO_ERROR0001="BD_BILL_INFO_ERROR0001";//复核通过的票不能退回
	
	public static final String BD_BILL_INFO_ERROR0019="BD_BILL_INFO_ERROR0019";//选中票据已记账复核通过，请先撤销复核
	
	public static final String BD_BILL_INFO_ERROR0011="BD_BILL_INFO_ERROR0011";//贴现人我行开户账号必填
	
	public static final String BD_BILL_INFO_ERROR0012="BD_BILL_INFO_ERROR0012";//赎回截止日不能为空
	
	public static final String BD_BILL_INFO_ERROR0013="BD_BILL_INFO_ERROR0013";//贴现人我行开户账号复核不过
	
	public static final String BD_BILL_INFO_ERROR0014="BD_BILL_INFO_ERROR0014";//赎回截止日复核不过
	
	public static final String BA_DIFFERENT_BILLDATE_ERROR="BA_DIFFERENT_BILLDATE_ERROR";//清单出票日小于票面到期日
	
	public static final String BA_DIFFERENT_BILLMONTH_ERROR="BA_DIFFERENT_BILLMONTH_ERROR";//票面到期日超过六个月
	
	public static final String BA_DIFFERENT_ELEC_BILLMONTH_ERROR="BA_DIFFERENT_ELEC_BILLMONTH_ERROR";//票面到期日超过一年
	
	public static final String BA_DIFFERENT_SUBCOLLLIST_DEL_ERROR="BA_DIFFERENT_SUBCOLLLIST_DEL_ERROR";//发出托收清单已使用，签收删除无法执行
	
	public static final String BA_DIFFERENT_BILLINFO_REPEAT_ERROR="BA_DIFFERENT_BILLINFO_REPEAT_ERROR";//所选票据已经发出托收，不允许重复发出
	
	public static final String BA_FENLU_ERROR = "BA_FENLU_ERROR";//查询分录信息失败
	
	public static final String BA_CHECK_ERROR = "BA_CHECK_ERROR";//校验失败
	
	public static final String BA_COPY_FLOW_ERROR = "BA_COPY_FLOW_ERROR";//查询流水信息失败
	
	public static final String BD_BILL_INFO_ERROR0015="BD_BILL_INFO_ERROR0015";//实付金额不能为空
	
	public static final String BD_BILL_INFO_ERROR0016="BD_BILL_INFO_ERROR0016";//计息天数不能为空
	
	public static final String BD_BILL_INFO_ERROR0017="BD_BILL_INFO_ERROR0017";//实付金额不能为空
	
	public static final String BD_BILL_INFO_ERROR0018="BD_BILL_INFO_ERROR0018";//计息天数不能为空
	
	//** SubCollBatchService 异常*//*
	public static final String BA_saveSubCollBatch_One_ERROR = "BA_saveSubCollBatch_One_ERROR";//新增托收申请失败
	public static final String BA_saveSubCollBatch_Two_ERROR = "BA_saveSubCollBatch_Two_ERROR";//修改托收清单失败
	public static final String BA_createSubCollBatch_Two_ERROR = "BA_createSubCollBatch_Two_ERROR";//新增托收清单失败
	public static final String BA_searchSubCollBatch_ERROR = "BA_searchSubCollBatch_ERROR";//查询托收申请失败
	public static final String BA_updateSubCollBatch_ERROR = "BA_updateSubCollBatch_ERROR";//修改托收申请失败
	public static final String BA_SearchAllSub_ERROR = "BA_SearchAllSub_ERROR";//查询托收申请及申请下明细失败
	public static final String BA_searchSubList_ERROR = "BA_searchSubList_ERROR";//查询发出托收申请下清单失败
	public static final String BA_workSubmitSubCollBatch_ERROR = "BA_workSubmitSubCollBatch_ERROR";//提交托收申请失败
	public static final String BA_QueryWorkSubCollBatch_ERROR = "BA_QueryWorkSubCollBatch_ERROR";//查询工作流中申请信息失败
	public static final String BA_subcollbatch_searchAllWork_ERROR = "BA_subcollbatch_searchAllWork_ERROR";//查询工作流中申请下清单信息失败
	
	//** SignCancelService 异常*//*
	public static final String BA_updateSignCancel_ERROR = "BA_updateSignCancel_ERROR";//修改未用退回失败
	public static final String BA_QueryAcptBill_ERROR = "BA_QueryAcptBill_ERROR";//查询承兑清单状态失败
	public static final String BA_querySignCancel_ERROR = "BA_querySignCancel_ERROR";//查询未用退回失败

	//** SubCollAccountService 异常*//*
	public static final String BA_queryWorkSubCollBatch_ERROR = "BA_queryWorkSubCollBatch_ERROR";//查询工作流中申请信息失败
	public static final String BA_SearchAllWork_ERROR = "BA_SearchAllWork_ERROR";//查询工作流中申请下清单信息失败
	public static final String BA_workConsignRgctReturn_ERROR = "BA_workConsignRgctReturn_ERROR";//托收回执失败
	public static final String BA_QueryInstanceId_ERROR = "BA_QueryInstanceId_ERROR";//查询流程ID失败

	//** SubCollClientService 异常*//*
	public static final String BA_searchSubCollClient_ERROR = "BA_searchSubCollClient_ERROR";//查询待托收票据失败
	public static final String BA_loadSubCollInfo_ERROR = "BA_loadSubCollInfo_ERROR";//查询托收信息失败
	public static final String BA_searchSubCollClientSign_ERROR = "BA_searchSubCollClientSign_ERROR";//查询选中的托收票据失败
	
	public static final String BA_elecBillPrompt_ERROR = "BA_elecBillPrompt_ERROR";//电子票提示付款失败
	public static final String BA_elecBillPromptByID_ERROR = "BA_elecBillPromptByID_ERROR";//查询提示付款的电子票据失败
	public static final String BA_receiveReturnReceipt_ERROR = "BA_receiveReturnReceipt_ERROR";//收到托收回执查询失败

	public static final String BA_withdrawDelayPaySignSubMitByID_ERROR = "BA_withdrawDelayPaySignSubMitByID_ERROR";//撤销延迟收款登记失败
	
	//** SubCollInOutService 异常*//*
	public static final String BA_subcollinout_workPromptBillOut_ERROR = "BA_subcollinout_workPromptBillOut_ERROR";//票据提交出库失败
	public static final String BA_subcollinout_queryInstanceId_ERROR = "BA_subcollinout_queryInstanceId_ERROR";//查询流程失败

	/** SubCollListService 异常*/
	public static final String BA_subcolllist_deleteSubCollList_ERROR = "BA_subcolllist_deleteSubCollList_ERROR";// 删除签收意见失败
	public static final String BA_subcolllist_searchSubCollList_ERROR = "BA_subcolllist_searchSubCollList_ERROR";//查询发出托收清单失败
	public static final String BA_subcolllist_queryWaitAccountSubCollList_ERROR = "BA_subcolllist_queryWaitAccountSubCollList_ERROR";//查询待收款记账的发出托收清单失败
	public static final String BA_subcolllist_workSubCollInputAccount_ERROR = "BA_subcolllist_workSubCollInputAccount_ERROR";//托收入库失败
	
	public static final String BA_subcolllist_searchSubCollBack_ERROR = "BA_subcolllist_searchSubCollBack_ERROR";//托收退票查询失败
	public static final String BA_subcolllist_queryWaitReceiveMoneyAccountCounteractSubCollList_ERROR = "BA_subcolllist_queryWaitReceiveMoneyAccountCounteractSubCollList_ERROR";//查询待托收收款记账冲正的清单失败
	public static final String BA_subcolllist_workReceiveCollections_ERROR = "BA_subcolllist_workReceiveCollections_ERROR";//托收收款登记失败
	public static final String BA_subcolllist_queryWaitInputSubCollList_ERROR = "BA_subcolllist_queryWaitInputSubCollList_ERROR";//查询待托收退票入库的票据失败
	public static final String BA_subcolllist_queryOneSubCollList_ERROR = "BA_subcolllist_queryOneSubCollList_ERROR";//查询发出托收清单失败
	public static final String BA_subcolllist_listLostToApply_ERROR = "BA_subcolllist_listLostToApply_ERROR";//托收清单离开申请失败
	public static final String BA_subcolllist_loadSubCollBatch_ERROR = "BA_subcolllist_loadSubCollBatch_ERROR";//查询托收申请失败
	public static final String BA_subcolllist_searchSubCollNotify_ERROR = "BA_subcolllist_searchSubCollNotify_ERROR";//到期托收提醒失败
	public static final String BA_subcolllist_loadSubCollList_ERROR = "BA_subcolllist_loadSubCollList_ERROR";//获得发出托收清单对象失败
	public static final String BA_subcolllist_queryCollSignInfo_ERROR = "BA_subcolllist_queryCollSignInfo_ERROR";//查询托收签收信息失败
	public static final String BA_subcolllist_updateSubColl_ERROR = "BA_subcolllist_updateSubColl_ERROR";//修改托收清单失败
	public static final String BA_subcolllist_saveInputBillInfo_ERROR = "BA_subcolllist_saveInputBillInfo_ERROR";//保存票据失败
	public static final String BA_subcolllist_subCollListInfoToBillInfo_ERROR = "BA_subcolllist_subCollListInfoToBillInfo_ERROR";//复制录入失败
	/**AcptMatuPayyService 异常*/
	public static final String BA_CollPayyAcptBill_ERROR = "BA_CollPayyAcptBill_ERROR";//付款清单查询失败失败
	public static final String BA_ACCTEXCEPTION_ERROR = "BA_AcctException_ERROR";//账务异常查询失败
		
	public static final String DAO_ERR_CODE_DEFAULT="DAO_ERR_001";//DAO层默认异常
	/**DAO层查询异常*/
	public static final String DAO_ERR_CODE_NOT_FOUND = "DAO_ERR_003";	//没有找到数据
	
	/**BD模块异常*/
	public static final String BD_BATCH_PRODUCT_ERROR="BD_BATCH_PRODUCT_ERROR";//所选票据产品类型不同
	public static final String BD_BATCH_RATE_ERROR="BD_BATCH_RATE_ERROR";//所选票据利率不同
	public static final String BD_IFDUMMY_ERROR="BD_IFDUMMY_ERROR";//是否转代保管选择与转出不一致
	public static final String BD_IFSAMECITY_ERROR="BD_IFSAMECITY_ERROR";//是否同城异地选择与转出不一致
	public static final String BD_RESALEDUEDt_ISHOLIDAY_ERROR="BD_ResaleDueDt_Isholiday_ERROR";//返售到期日不能为节假日
	public static final String BD_REBUYDUEDt_ISHOLIDAY_ERROR="BD_RebuyDueDt_Isholiday_ERROR";//回购到期日不能为节假日
	public static final String BD_OutBillDt_Empty_ERROR="BD_OutBillDt_Empty_ERROR";//出票日不能为空
	public static final String BD_EndBillDt_Empty_ERROR="BD_EndBillDt_Empty_ERROR";//票面到期日不能为空
	public static final String BD_RebuyDt_Empty_ERROR="BD_RebuyDt_Empty_ERROR";//转入日不能为空
	public static final String BD_ResaleDueDt_Empty_ERROR="BD_ResaleDueDt_Empty_ERROR";//返售到期日不能为空
	public static final String BD_OutBillDt_ERROR="BD_OutBillDt_ERROR";//出票日复核不通过
	public static final String BD_ResaleDueDt_ERROR="BD_ResaleDueDt_ERROR";//返售到期日复核不通过
	public static final String BD_RebuyDt_ERROR="BD_RebuyDt_ERROR";//转入日复核不通过
	public static final String BD_BILLAMOUNT_ERROR="BD_BILLAMOUNT_ISNULL";//票面金额不能为空
	public static final String BD_BILLAMOUNT_NOTEQUAL_ERROR="BD_BILLAMOUNT_NOTEQUAL_ERROR";//票面金额不相等
	public static final String BD_DiscDt_ERROR="BD_DiscDt_ERROR";//贴现日期不相等
	public static final String BD_ENDBILLDATE_ERROR="BD_ENDBILLDATE_ERROR";//票面到期日不相等
	public static final String BD_CREATE_REDEEM_ERROR_REDEMPRIONDT="REDEMPRIONDT";//约定赎回日大于营业日
	public static final String BD_DISCOUNT_ERROR_DISCDT="DISCDT_MSUTEQUAL_WORKDAY";//贴现日必须等于营业日
	public static final String BD_BILL_NOT_FOUND="BD_BILL_NOT_FOUND";
	public static final String BD_BILL_NOT_FOUND2="BD_BILL_NOT_FOUND2";
	public static final String BD_BILL_NOT_FOUND_FORBUYBACK="BD_BILL_NOT_FOUND_FORBUYBACK";
	public static final String BD_BILL_NOT_FOUND_FORSALEBACK="BD_BILL_NOT_FOUND_FORSALEBACK";
	public static final String BD_APPLY_NOT_FOUND="BD_APPLY_NOT_FOUND";
	public static final String BD_APPLY_NOT_FOUND2="BD_APPLY_NOT_FOUND2";
	public static final String BD_APPLY_NOT_FOUND_FORBUYBACK="BD_APPLY_NOT_FOUND_FORBUYBACK";
	public static final String BD_ACCTINFO_NOT_FOUND="BD_ACCTINFO_NOT_FOUND";
	public static final String BD_DIFFERENT_BILLCLASS_ERROR="BD_DIFFERENT_BILLCLASS_ERROR";
	public static final String BD_DIFFERENT_BILLTYPE_ERROR="BD_DIFFERENT_BILLTYPE_ERROR";
	public static final String BD_OPERSTATUS_ISNOTREQUIRED_ERROR="BD_OPERSTATUS_ISNOTREQUIRED_ERROR";
	public static final String BD_OPERSTATUS_BILL_ERROR="BD_OPERSTATUS_BILL_ERROR";//查无此票
	public static final String BD_OPERSTATUS_TYPEORCLASS_ERROR="BD_OPERSTATUS_TYPEORCLASS_ERROR";//票据种类或类型不一致
	public static final String BD_OPERSTATUS_NOBILL_ERROR="BD_OPERSTATUS_NOBILL_ERROR";//请选择票据
	public static final String BD_OPERSTATUS_DUSUBMIT_ERROR="BD_OPERSTATUS_DUSUBMIT_ERROR";//请勿重复提交
	public static final String BD_ERROR_NOT_CACULATE_INTEREST = "BD_ERROR_NOT_CACULATE_INTEREST";//所有的票要都要计算利息
	//saleacpt
	public static final String BD_SALEACPT_DATE_ERROR="BD_SALEACPT_DATE_ERROR";//回购到期日或转卖日与票据出票日或票面到期日冲突
	public static final String BD_SALEACPT_PRODUCT_ERROR="BD_SALEACPT_PRODUCT_ERROR";//请选择产品
	//rebuyaccept
	public static final String BD_RebuyAccept_BILLNO_ERROR="BD_RebuyAccept_BILLNO_ERROR";//录入票号是空,或小于12位
	//rebuyapply
	public static final String BD_RebuyApply_CustId_ERROR="BD_RebuyApply_CustId_ERROR";//对方行不能为空
	public static final String BD_RebuyApply_BillTypeNoSame_ERROR="BD_RebuyApply_BillTypeNoSame_ERROR";//产生批次信息的票不是同一个类型
	
	public static final String BD_RebuyApply_BillSaleApply_ERROR="BD_RebuyApply_BillSaleApply_ERROR";//系统内的票不是同一个买入批次
	public static final String BD_RebuyApply_BillClassNoSame_ERROR="BD_RebuyApply_BillClassNoSame_ERROR";//产生批次信息的票不是同一个种类
	public static final String BD_RebuyApply_CustIdNoSame_ERROR="BD_RebuyApply_CustIdNoSame_ERROR";//票据客户不同
	public static final String BD_RebuyApply_BillType_ERROR="BD_RebuyApply_BillType_ERROR";//票据类型与选择批次类型不同
	public static final String BD_RebuyApply_CheckIfInner_ERROR="BD_RebuyApply_CheckIfInner_ERROR";//票据是系统类型与选择批次类型不同
	public static final String BD_RebuyApply_MaxAndMinDate_ERROR="BD_RebuyApply_MaxAndMinDate_ERROR";//转入日不能大于最小票面到期日也不能小于最大出票日
	public static final String BD_RebuyApply_rebuyDateAndMinDate_ERROR="BD_RebuyApply_rebuyDateAndMinDate_ERROR";//回购日小于转买日或大于最小票面到期日
	public static final String BD_RebuyApply_WorkDate_ERROR="BD_RebuyApply_WorkDate_ERROR";//转入日不能小于营业日
	//RebuyFirstRetril
	public static final String BD_RebuyFirstRetril_FirstRetrilRate_ERROR="BD_RebuyFirstRetril_FirstRetrilRate_ERROR";//只有计算利息后的的票能进行初审提交
	//rebuyAccount
	public static final String BD_RebuyAccount_Status_ERROR="BD_RebuyAccount_Status_ERROR";//只有入库后的票才能记账
	/**调用外部额度中心异常*/
	public static final String BD_ERROR_BILL_TYPE = "BD_ERROR_BILL_TYPE";
	public static final String BD_ERROR_CANCEL_APPLY = "BD_ERROR_CANCEL_APPLY";
	public static final String BD_ERROR_BILL_OPERSTATUS="BD_ERROR_BILL_OPERSTATUS";//必须初审通过，才可进行下一步操作
	
	/**BC异常*/
	public static final String BC_INTEREST_PARAM_NULL_ERROR="BC_INTEREST_PARAM_NULL_ERROR";//服务调用传入参数有为空值
	public static final String BC_DELAYRULE_NONINIT_ERROR="BC_DELAYRULE_NONINIT_ERROR";//系统未初始化顺延规则
	public static final String BC_PROCESSDEFINITION_ERROR="BC_PROCESSDEFINITION_ERROR";//流程发布异常
	public static final String BC_PROCESSDEFINITION_NOT_FOUND="BC_PROCESSDEFINITION_NOT_FOUND";//流程发布异常
	
	/** 客户数据异常 **/
	public static final String CUST_INFO_NOT_EXIST = "CUST_INFO_NOT_EXIST"; //客户不存在
	/** 风险票据异常 **/
	public static final String RISKBILL_createRiskBill_ERROR = "RISKBILL_createRiskBill_ERROR";//新增风险票据失败
	
	/**RGSTBUYBILL异常*/
	public static final String RGSTBUYBILL_EXCEL_ERROR_ENDBILLDATEEERROR="票面到期日应大于营业日";
	public static final String RGSTBUYBILL_EXCEL_ERROR_OUTBILLDATEEERROR="票面出票日应小于等于营业日";
	
	/** 额度管理异常信息  **/
	public static final String CREDIT_ERR_CODE_GRANT_INFO = "CREDIT_ERR_FIND_GRANT_INFO";
	public static final String CREDIT_ERR_CODE_FREEZEN = "CREDIT_ERR_FREEZEN";
	public static final String CREDIT_ERR_CODE_UNFREEZEN = "CREDIT_ERR_UNFREEZEN";
	public static final String CREDIT_ERR_CODE_FREEZEN_TO_USE = "CREDIT_ERR_FREEZEN_TO_USE";
	public static final String CREDIT_ERR_CODE_USED_TO_FREEZEN = "CREDIT_ERR_USED_TO_FREEZE";

	//代理工厂异常
	public static final String AGENT_ERR_CODE_NOT_FOUND_SERVICE = "AGENT_ERR_NOT_FOUND_SERVICE";
	//** 同业交换代理异常 **//*
	public static final String FINA_ERR_CODE_REG_ACCOUNT = "FINA_ERR_REG_ACCOUNT";
	public static final String FINA_ERR_CODE_REG_ACCOUNT_CREDIT = "FINA_ERR_REG_ACCOUNT_CREDIT";
	public static final String FINA_ERR_CODE_QUERY = "FINA_ERR_QUERY";
	public static final String FINA_ERR_CODE_PAY = "FINA_ERR_PAY";
	public static final String FINA_ERR_CODE_QUERY_RESULT = "FINA_ERR_CODE_QUERY_RESULT";
	public static final String FINA_ERR_CODE_SEND_ACCEPT = "FINA_ERR_SEND_ACCEPT";
	public static final String FINA_ERR_CODE_PAY_CREDIT = "FINA_ERR_PAY_CREDIT";
	//高风险审核
	public static final String RISK_AUDIT_ERR_CODE_AUDIT = "RISK_AUDIT_ERR_CODE_AUDIT";
	public static final String RISK_AUDIT_ERR_CODE_RESULT = "RISK_AUDIT_ERR_CODE_RESULT";
	public static final String RISK_AUDIT_ERR_CODE_RESELL = "RISK_AUDIT_ERR_CODE_RESELL";
	public static final String RISK_AUDIT_ERR_CODE_NOTEXIST = "RISK_AUDIT_ERR_CODE_NOTEXIST";
	
	
	
	//登记中心票据提醒统计异常
	public static final String RC_ERR_CODE_STATISTIC = "RC_ERR_STATISTIC";
	public static final String REMINDER_addReminderInfo_ERROR = "REMINDER_addReminderInfo_ERROR";//增加提醒信息失败
	public static final String REMINDER_deleteReminderInfo_ERROR = "REMINDER_deleteReminderInfo_ERROR";//删除提醒信息失败
	public static final String REMINDER_findReminderInfo_ERROR = "REMINDER_findReminderInfo_ERROR";//查询提醒信息失败
	public static final String REMINDER_getReminderInfoById_ERROR = "REMINDER_getReminderInfoById_ERROR";//查询提醒信息失败
	public static final String REMINDER_updateReminderInfo_ERROR = "REMINDER_updateReminderInfo_ERROR";//修改提醒信息失败

	
	//记帐异常信息
	public static final String ACCOUNT_ERR_CODE_REC_REPEAT = "ACCOUNT_ERR_REC_REPEAT";
	public static final String ACCOUNT_ERR_CODE_CREDIT_REPEAT = "ACCOUNT_ERR_CREDIT_REPEAT";
	public static final String ACCOUNT_ERR_CODE_REC_CHECK = "ACCOUNT_ERR_REC_CHECK";

	/** BankService */
	public static final String COMMON_BANKINFO_addBank_ERROR = "COMMON_BANKINFO_addBank_ERROR";//增加银行信息失败
	public static final String COMMON_BANKINFO_deleteBank_ERROR = "COMMON_BANKINFO_deleteBank_ERROR";//删除银行信息失败
	public static final String COMMON_BANKINFO_editBank_ERROR = "COMMON_BANKINFO_editBank_ERROR";//修改银行信息失败
	public static final String COMMON_BANKINFO_getBankByBankNo_ERROR = "COMMON_BANKINFO_getBankByBankNo_ERROR";//查询银行信息失败
	public static final String COMMON_BANKINFO_getBankById_ERROR = "COMMON_BANKINFO_getBankById_ERROR";//查询银行信息失败
	public static final String COMMON_BANKINFO_getBankByName_ERROR = "COMMON_BANKINFO_getBankByName_ERROR";//查询银行信息失败
	public static final String COMMON_BANKINFO_getBankBySimpleName_ERROR = "COMMON_BANKINFO_getBankBySimpleName_ERROR";//查询银行信息失败
	public static final String COMMON_BANKINFO_getBanks_ERROR = "COMMON_BANKINFO_getBanks_ERROR";//查询银行信息失败
	public static final String COMMON_BANKINFO_findBankBySearchBean_ERROR = "COMMON_BANKINFO_findBankBySearchBean_ERROR";//查询银行信息失败

	public static final String COMMON_BANKINFO_BANKNO_ERROR = "COMMON_BANKINFO_BANKNO_ERROR";//银行号相同
	
	/** BankCatgService*/
	public static final String COMMON_BANKCATG_deleteBankCatg_ERROR = "COMMON_BANKCATG_deleteBankCatg_ERROR";//删除银行分类信息失败
	public static final String COMMON_BANKCATG_getBankCatg_ERROR = "COMMON_BANKCATG_getBankCatg_ERROR";//查询银行分类信息失败
	public static final String COMMON_BANKCATG_getBankCatgs_ERROR = "COMMON_BANKCATG_getBankCatgs_ERROR";//查询银行分类信息失败
	public static final String COMMON_BANKCATG_saveBankCatg_ERROR = "COMMON_BANKCATG_saveBankCatg_ERROR";//增加银行分类信息失败
	public static final String COMMON_BANKCATG_updateBankCatg_ERROR = "COMMON_BANKCATG_updateBankCatg_ERROR";//修改银行分类信息失败
	public static final String COMMON_BANKCATG_getPages_ERROR = "COMMON_BANKCATG_getPages_ERROR";//查询银行分类信息失败
	public static final String COMMON_BANKCATG_getBankByBankSort_ERROR = "COMMON_BANKCATG_getBankByBankSort_ERROR";//查询银行分类信息失败

	/** CustomerService*/
	public static final String COMMON_CUSTOMER_addCustomers_ERROR = "COMMON_CUSTOMER_addCustomers_ERROR";//增加客户信息失败
	public static final String COMMON_CUSTOMER_delCustomerInfo_ERROR = "COMMON_CUSTOMER_delCustomerInfo_ERROR";//查询客户信息失败
	public static final String COMMON_CUSTOMER_editCustomer_ERROR = "COMMON_CUSTOMER_editCustomer_ERROR";//修改客户信息失败
	public static final String COMMON_CUSTOMER_addCustomer_ERROR = "COMMON_CUSTOMER_addCustomer_ERROR";//增加客户信息失败
	public static final String COMMON_CUSTOMER_delCustomer_ERROR = "COMMON_CUSTOMER_delCustomer_ERROR";//删除客户信息失败
	public static final String COMMON_CUSTOMER_getCustomer_ERROR = "COMMON_CUSTOMER_getCustomer_ERROR";//查询客户信息失败
	public static final String COMMON_CUSTOMER_findCustomerByCustNo_ERROR = "COMMON_CUSTOMER_findCustomerByCustNo_ERROR";//查询客户信息失败
	public static final String COMMON_CUSTOMER_findCustomersByCustName_ERROR = "COMMON_CUSTOMER_findCustomersByCustName_ERROR";//查询客户信息失败
	public static final String COMMON_CUSTOMER_getCustomerBySearchBean_ERROR = "COMMON_CUSTOMER_getCustomerBySearchBean_ERROR";//查询客户信息失败
	public static final String COMMON_CUSTOMER_getCustomerById_ERROR = "COMMON_CUSTOMER_getCustomerById_ERROR";//查询客户信息失败
	public static final String COMMON_CUSTOMER_getCustomers_ERROR = "COMMON_CUSTOMER_getCustomers_ERROR";//查询客户信息失败
	public static final String COMMON_CUSTOMER_findCustAddrByCustId_ERROR = "COMMON_CUSTOMER_findCustAddrByCustId_ERROR";//查询客户地址信息失
	public static final String COMMON_CUSTOMER_findCustAddrByCustNo_ERROR = "COMMON_CUSTOMER_findCustAddrByCustNo_ERROR";//查询客户地址信息失
	public static final String COMMON_CUSTOMER_getCustAttrByCustId_ERROR = "COMMON_CUSTOMER_getCustAttrByCustId_ERROR";//查询客户性质信息失败
	public static final String COMMON_CUSTOMER_findCustAttrByCustNo_ERROR = "COMMON_CUSTOMER_findCustAttrByCustNo_ERROR";//查询客户性质信息失败
	public static final String COMMON_CUSTOMER_findCustMgtByCustNo_ERROR = "COMMON_CUSTOMER_findCustMgtByCustNo_ERROR";//查询客户管理信息失败
	public static final String COMMON_CUSTOMER_getCorpCustomers_ERROR = "COMMON_CUSTOMER_getCorpCustomers_ERROR";//查询客户信息失败
	public static final String COMMON_CUSTOMER_getFinaCustomers_ERROR = "COMMON_CUSTOMER_getFinaCustomers_ERROR";//查询客户信息失败
	public static final String COMMON_CUSTOMER_findCustAcctByAcctNo_ERROR = "COMMON_CUSTOMER_findCustAcctByAcctNo_ERROR";//查询客户帐号相关信息失败
	public static final String COMMON_CUSTOMER_findCustAcctByCustNo_ERROR = "COMMON_CUSTOMER_findCustAcctByCustNo_ERROR";//查询客户帐号信息失败
	public static final String COMMON_CUSTOMER_getCustInfoByCustNo_ERROR = "COMMON_CUSTOMER_getCustInfoByCustNo_ERROR";//查询客户信息失败
	public static final String COMMON_CUSTOMER_addCustAcct_ERROR = "COMMON_CUSTOMER_addCustAcct_ERROR";//增加客户开户信息失败
	public static final String COMMON_CUSTOMER_deleteCustAcct_ERROR = "COMMON_CUSTOMER_deleteCustAcct_ERROR";//删除客户开户信息失败
	public static final String COMMON_CUSTOMER_getCustAcctById_ERROR = "COMMON_CUSTOMER_getCustAcctById_ERROR";//查询客户开户信息失败
	
	//打印异常
	public static final String PRINT_ERR_CODE_TEMPLATE_NOT_FOUND = "PRINT_ERR_TEMPLATE_NOT_FOUND";	//未找到打印模板
	//登记中心查询
	public static final String ESB_RGCTSEARCH_getRgctSearchBeans_ERROR = "ESB_RGCTSEARCH_getRgctSearchBeans_ERROR";	//登记中心查询失败
	
	//提醒
	public static final String REMINDER_ERR_CONFIG = "REMINDER_ERR_CONFIG";	//提醒配置错误
	//Excel导入
	public static final String SERVICE_ERR_CODE_JIO_BEAN_PROPERTIES = "SERVICE_CODE_JIO_BEAN_PROPERTIES";	//导入对象属性配置错误
	public static final String SERVICE_ERR_CODE_JIO_EXCEL_TITLE = "SERVICE_CODE_JIO_EXCEL_TITLE";		//Excel标题读取错误
	public static final String SERVICE_ERR_CODE_JIO_EXCEL2BEAN = "SERVICE_CODE_JIO_EXCEL2BEAN";	//Excel数据映射错误
	
	//调用外部系统通信异常
	public static final String SERVICE_ERR_CODE_SEND_COMMUNICATIONS = "SERVICE_ERR_CODE_SEND_COMMUNICATIONS";
	/**
	 * 外部系统数据不一致
	 */
	public static final String SERVICE_ERR_CODE_SEND_R = "SERVICE_ERR_CODE_SEND_R";
	//重复冲正异常
	public static final String SERVICE_ERR_CODE_REPEAT_RVS = "SERVICE_ERR_CODE_REPEAT_RVS";
	//找不到原流水号(被冲正流水号)
	public static final String SERVICE_ERR_CODE_FLOW_NOT_EXIST = "SERVICE_ERR_CODE_FLOW_NOT_EXIST";
	
	public static final String BA_CLIENT_createAcptMateBill_ERROR = "BA_CLIENT_createAcptMateBill_ERROR";//新增签收失败
	
	public static final String BD_queryBankClassInfo_ERROR = "BD_queryBankClassInfo_ERROR";//调用外部系统时：根据联行行号查询行分类失败

	//纸票登记异常
	public static final String SYS_REGISTER_TYPE_NULL="SYS_REGISTER_TYPE_NULL";//纸票登记类型为空

	//sunying add 2010-5-27
	//网银操作异常queryStayRegisterBill
	//清单编辑
	public static final String NETBANK_BILLEDITSERVICE_QUERYSTAYREGISTERBILL="NETBANK_BILLEDITSERVICE_QUERYSTAYREGISTERBILL_ERROR";//查询待出票登记票据服务异常
	public static final String NETBANK_BILLEDITSERVICE_INSERTPREPAREBILL="NETBANK_BILLEDITSERVICE_INSERTPREPAREBILL_ERROR";//新增票据信息服务异常
	public static final String NETBANK_BILLEDITSERVICE_UPDATEPREPAREBILL="NETBANK_BILLEDITSERVICE_UPDATEPREPAREBILL_ERROR";//修改票据信息服务异常
	public static final String NETBANK_BILLEDITSERVICE_BATCHDELETEPREPAREBILLS="NETBANK_BILLEDITSERVICE_BATCHDELETEPREPAREBILLS_ERROR";//删除票据信息服务异常
	public static final String NETBANK_BILLEDITSERVICE_QUERYPREPAREBILLBYRGCTID="NETBANK_BILLEDITSERVICE_QUERYPREPAREBILLBYRGCTID_ERROR";//根据Id查询票据对象服务异常
	public static final String NETBANK_NWENSURESERVICE_FINDNWENSUREBYCOND="NETBANK_NWENSURESERVICE_FINDNWENSUREBYCOND_ERROR";//待保证票据查询服务异常
	public static final String NETBANK_NWENSURESERVICE_FINDNWENSUREINFOBYBILLID="NETBANK_NWENSURESERVICE_FINDNWENSUREINFOBYBILLID_ERROR";//根据保证ID查询保证信息服务异常
	public static final String NETBANK_NWENSURESERVICE_BATCHINSERTNWENSURE="NETBANK_NWENSURESERVICE_BATCHINSERTNWENSURE_ERROR";//添加保证信息服务异常
	public static final String NETBANK_NWENSURESERVICE_LOADNWENSURE="NETBANK_NWENSURESERVICE_LOADNWENSURE_ERROR";//根据登记中心保证id预加载保证信息对象服务异常
	public static final String NETBANK_NWENSURESERVICE_UPDATENWENSURE="NETBANK_NWENSURESERVICE_UPDATENWENSURE_ERROR";//修改保证信息服务异常
	public static final String NETBANK_NWENSURESERVICE_BATCHREMOVENWENSURE="NETBANK_NWENSURESERVICE_BATCHREMOVENWENSURE_ERROR";//删除保证信息服务异常
	public static final String NETBANK_NWENSURESERVICE_FINDNWENSUREBYRGCTID="NETBANK_NWENSURESERVICE_BATCHREMOVENWENSURE_ERROR";//根据登记中心ID查询保证信息服务异常
	public static final String NETBANK_NWENSURESERVICE_BATCHREGISTERENSURE="NETBANK_NWENSURESERVICE_BATCHREGISTERENSURE_ERROR";//提示保证服务异常
	public static final String NETBANK_NWENSURESERVICE_FINDNWENSURENOTAFFIRM="NETBANK_NWENSURESERVICE_FINDNWENSURENOTAFFIRM_ERROR";//提示保证待确认票据查询服务异常
	public static final String NETBANK_NWENSURESERVICE_FINDNWENSURENOTSIGN="NETBANK_NWENSURESERVICE_FINDNWENSURENOTSIGN_ERROR";//可撤销保证票据查询服务异常（保证待签收,我行发起，他行待签收）
	public static final String NETBANK_NWENSURESERVICE_BATCHBACKENSURE="NETBANK_NWENSURESERVICE_BATCHBACKENSURE_ERROR";//撤销提示保证服务异常
	public static final String NETBANK_NWENSURESERVICE_FINDNWENSURESIGN="NETBANK_NWENSURESERVICE_FINDNWENSURESIGN_ERROR";//查询提示保证已签收票据查询（我行发起，他行已签收）
	public static final String NETBANK_NWENSURESERVICE_FINDNWENSURESIGNOFOTHER="NETBANK_NWENSURESERVICE_FINDNWENSURESIGNOFOTHER_ERROR";//查询提示保证已签收票据查询（他行发起，我行已签收）
	public static final String NETBANK_NWENSURESERVICE_FINDNWENSUREREFUSE="NETBANK_NWENSURESERVICE_FINDNWENSUREREFUSE_ERROR";//查询提示保证已拒绝查询(我方发起，他方已拒绝)
	public static final String NETBANK_NWENSURESERVICE_FINDNWENSUREREFUSEOFOTHER="NETBANK_NWENSURESERVICE_FINDNWENSUREREFUSEOFOTHER_ERROR";//查询提示保证已拒绝查询（他方发起，我方已拒绝）
	public static final String NETBANK_NWENSURESERVICE_FINDNWENSURENOTSIGNOFOTHER="NETBANK_NWENSURESERVICE_FINDNWENSURENOTSIGNOFOTHER_ERROR";//保证待签收票据查询（他行发起，我行待签收）
	public static final String NETBANK_NWENSURESERVICE_BATCHENSURESIGN="NETBANK_NWENSURESERVICE_BATCHENSURESIGN_ERROR";//保证签收服务异常
	public static final String NETBANK_NWENSURESERVICE_BATCHENSUREREFUSE="NETBANK_NWENSURESERVICE_BATCHENSUREREFUSE_ERROR";//保证拒绝服务异常
	
	//提示付款
	public static final String NETBANK_NWPROMPTPAYMENTSERVICE_QUERYPROMPTABLEPAYMENTBILL="NETBANK_NWPROMPTPAYMENTSERVICE_QUERYPROMPTABLEPAYMENTBILL_ERROR";//查询可提示付款票据服务异常
	public static final String NETBANK_NWPROMPTPAYMENTSERVICE_BATCHPROMPTPAYMENT="NETBANK_NWPROMPTPAYMENTSERVICE_BATCHPROMPTPAYMENT_ERROR";//提示付款申请服务异常
	public static final String NETBANK_NWPROMPTPAYMENTSERVICE_QUERYPROMPTPAYMENTRESULT="NETBANK_NWPROMPTPAYMENTSERVICE_QUERYPROMPTPAYMENTRESULT_ERROR";//提示付款结果查询服务异常
	public static final String NETBANK_NWPROMPTPAYMENTSERVICE_QUERYRECALLABLEPROMPTPAYMENTBILL="NETBANK_NWPROMPTPAYMENTSERVICE_QUERYRECALLABLEPROMPTPAYMENTBILL_ERROR";//查询可提示付款撤回票据服务异常
	public static final String NETBANK_NWPROMPTPAYMENTSERVICE_BATCHRECALLPROMPTPAYMENT="NETBANK_NWPROMPTPAYMENTSERVICE_BATCHRECALLPROMPTPAYMENT_ERROR";//提示付款撤回服务异常
	//贴现
	public static final String NETBANK_CUSTDISCSERVICE_SEARCHDISCOUNTABLEBILL="NETBANK_CUSTDISCSERVICE_SEARCHDISCOUNTABLEBILL_ERROR";//查询可贴现的票据服务异常
	public static final String NETBANK_CUSTDISCSERVICE_BATCHSUBMITDISCAPPLY="NETBANK_CUSTDISCSERVICE_BATCHSUBMITDISCAPPLY_ERROR";//贴现申请服务异常
	public static final String NETBANK_CUSTDISCSERVICE_SEARCHDISCOUNTBILL="NETBANK_CUSTDISCSERVICE_SEARCHDISCOUNTBILL_ERROR";//贴现结果查询服务异常
	public static final String NETBANK_CUSTDISCSERVICE_SEARCHSUBMITEDBILL="NETBANK_CUSTDISCSERVICE_SEARCHSUBMITEDBILL_ERROR";//查询可贴现撤回票据服务异常
	public static final String NETBANK_CUSTDISCSERVICE_BATCHCANCELDISCAPPLY="NETBANK_CUSTDISCSERVICE_BATCHCANCELDISCAPPLY_ERROR";//贴现撤回服务异常
	public static final String NETBANK_CUSTDISCSERVICE_SEARCHREDEEMABLEBILL="NETBANK_CUSTDISCSERVICE_SEARCHREDEEMABLEBILL_ERROR";//查询待赎回签收票据服务异常
	public static final String NETBANK_CUSTDISCSERVICE_BATCHSUBMITREDEEMSIGN="NETBANK_CUSTDISCSERVICE_BATCHSUBMITREDEEMSIGN_ERROR";//赎回签收服务异常
	
	//质押
	public static final String NETBANK_CUSTIMPAWNCLIENTSERVICE_SEARCHRGCTBILL="NETBANK_CUSTIMPAWNCLIENTSERVICE_SEARCHRGCTBILL_ERROR";//可质押票据查询服务异常
	public static final String NETBANK_CUSTIMPAWNCLIENTSERVICE_BATCHSUBMITIMPAWNAPPLY="NETBANK_CUSTIMPAWNCLIENTSERVICE_BATCHSUBMITIMPAWNAPPLY_ERROR";//票据质押服务异常
	public static final String NETBANK_CUSTIMPAWNCLIENTSERVICE_SEARCHPOOLBILL="NETBANK_CUSTIMPAWNCLIENTSERVICE_SEARCHPOOLBILL_ERROR";//可撤消质押票据查询服务异常
	public static final String NETBANK_CUSTIMPAWNCLIENTSERVICE_BATCHRESETIMPAWNAPPLY="NETBANK_CUSTIMPAWNCLIENTSERVICE_BATCHRESETIMPAWNAPPLY_ERROR";//票据质押撤消服务异常
	public static final String NETBANK_CUSTIMPAWNCLIENTSERVICE_SEARCHUNCOILIMPAWNBILL="NETBANK_CUSTIMPAWNCLIENTSERVICE_SEARCHUNCOILIMPAWNBILL_ERROR";//待解质签收票据查询
	public static final String NETBANK_CUSTIMPAWNCLIENTSERVICE_BATCHAGREEUNCOILIMPAWN="NETBANK_CUSTIMPAWNCLIENTSERVICE_BATCHAGREEUNCOILIMPAWN_ERROR";//解质签收服务异常
	public static final String NETBANK_CUSTIMPAWNCLIENTSERVICE_BATCHREFUSEUNCOILIMPAWN="NETBANK_CUSTIMPAWNCLIENTSERVICE_BATCHREFUSEUNCOILIMPAWN_ERROR";//解质拒绝签收服务异常
	//追索
	public static final String NETBANK_NWRECOURSESERVICE_QUERYRECOURSEABLEBILL="NETBANK_NWRECOURSESERVICE_QUERYRECOURSEABLEBILL_ERROR";//查询可追索票据服务异常
	public static final String NETBANK_NWRECOURSESERVICE_QUERYCANRECOURSE="NETBANK_NWRECOURSESERVICE_QUERYCANRECOURSE_ERROR";//查询可被追索对象服务异常
	public static final String NETBANK_NWRECOURSESERVICE_BATCHAPPLYRECOURSE="NETBANK_NWRECOURSESERVICE_BATCHAPPLYRECOURSE_ERROR";//追索申请服务异常
	public static final String NETBANK_NWRECOURSESERVICE_QUERYAGREEPAYMENTRESPONSE="NETBANK_NWRECOURSESERVICE_QUERYAGREEPAYMENTRESPONSE_ERROR";//待清偿回复查询服务异常
	public static final String NETBANK_NWRECOURSESERVICE_BATCHAGREEPAYMENTRESPONSE="NETBANK_NWRECOURSESERVICE_BATCHAGREEPAYMENTRESPONSE_ERROR";//同意清偿回复服务异常
	public static final String NETBANK_NWRECOURSESERVICE_QUERYRECOURSEREVERSIBLEBILL="NETBANK_NWRECOURSESERVICE_QUERYRECOURSEREVERSIBLEBILL_ERROR";//查询可撤回追索票据服务异常
	public static final String NETBANK_NWRECOURSESERVICE_BATCHAPPLYRECOURSEREVERSE="NETBANK_NWRECOURSESERVICE_BATCHAPPLYRECOURSEREVERSE_ERROR";//追索申请撤回服务异常
	public static final String NETBANK_NWRECOURSESERVICE_QUERYAGREEPAYMENTBILL="NETBANK_NWRECOURSESERVICE_QUERYAGREEPAYMENTBILL_ERROR";//待清偿查询（被追索人）
	public static final String NETBANK_NWRECOURSESERVICE_BATCHAPPLYAGREEPAYMENT="NETBANK_NWRECOURSESERVICE_BATCHAPPLYAGREEPAYMENT_ERROR";//同意清偿申请（被追索人）
	public static final String NETBANK_NWRECOURSESERVICE_QUERYAGREEPAYMENTREVERSIBLEBILL="NETBANK_NWRECOURSESERVICE_QUERYAGREEPAYMENTREVERSIBLEBILL_ERROR";//查询可撤销的清偿申请（被追索人）
	public static final String NETBANK_NWRECOURSESERVICE_BATCHAPPLYAGREEPAYMENTREVERSE="NETBANK_NWRECOURSESERVICE_BATCHAPPLYAGREEPAYMENTREVERSE_ERROR";//清偿申请撤回（被追索人）
	public static final String NETBANK_NWRECOURSESERVICE_QUERYAGREEPAYMENTRESPONSERESULT="NETBANK_NWRECOURSESERVICE_QUERYAGREEPAYMENTRESPONSERESULT_ERROR";//清偿申请结果查询（被追索人）
	//利息试算
	public static final String NETBANK_INTERESTDELAYRULESERVICE_BATCHINTERESTBYCUSTOM="NETBANK_INTERESTDELAYRULESERVICE_BATCHINTERESTBYCUSTOM_ERROR";//利息试算服务异常

}

