package com.herongtech.console.core.constant;

import java.util.HashMap;
import java.util.Map;


public final class DraftConstants {

    /**
     * 报文头固定开头{@value}
     */
    public static final String DRAFT_HEAD_PREFIX = "{H:";
    
    /**
     * 编码方式{@value}
     */
    public static final String ENCODING= "UTF-8";
    
    /**
     * 币种{@value}
     */
    public static final String CURRENCY_TYPE="CNY";
    
    
    /**
     * 报文编号{@value}
     */
    public static final String DRAFTNO_COMMON_TRANS="034";
    /**
     * 报文编号{@value}
     */
    public static final String DRAFTNO_COMMON_STATUS="033";
    /**
     * 报文编号{@value}
     */
    public static final String DRAFTNO_COMMON_CANCEL="032";
    /**
     * 报文编号{@value}
     */
    public static final String DRAFTNO_COMMON_SIGN="031";
    
    

    /**
     * 拒付理由代码 DC00、与自己有直接债权债务关系的持票人未履行约定义务； DC01、持票人以欺诈、偷盗或者胁迫等手段取得票据；
     * DC02、持票人明知有欺诈、偷盗或者胁迫等情形，出于恶意取得票据； DC03、持票人明知债务人与出票人或者持票人的前手之间存在抗辩事由而取得票据；
     * DC04、持票人因重大过失取得不符合《票据法》规定的票据； DC05、超过提示付款期； DC06、被法院冻结或收到法院止付通知书；
     * DC07、票据未到期； DC08、商业承兑汇票承兑人账户余额不足。 DC09、其他（必须注明）。
     * 
     */
    public static final Map<String, String> REFUSE_REASON_MAP = new HashMap<String, String>();
    public static final Map<String, String> BusiTypeToSignUpBuilderMap = new HashMap<String, String>();
    
    public static final Map<String, String>  RGCT_CODE_MAPPING_MAP = new HashMap<String, String>();
    public static final Map<String, String>  SIGNUPTYPE_TO_BUSITYPE = new HashMap<String, String>();
    static{
        REFUSE_REASON_MAP.put("DC00", "与自己有直接债权债务关系的持票人未履行约定义务");
        REFUSE_REASON_MAP.put("DC01", "持票人以欺诈、偷盗或者胁迫等手段取得票据");
        REFUSE_REASON_MAP.put("DC02", "持票人明知有欺诈、偷盗或者胁迫等情形，出于恶意取得票据");
        REFUSE_REASON_MAP.put("DC03", "持票人明知债务人与出票人或者持票人的前手之间存在抗辩事由而取得票据");
        REFUSE_REASON_MAP.put("DC04", "持票人因重大过失取得不符合《票据法》规定的票据");
        REFUSE_REASON_MAP.put("DC05", "超过提示付款期");
        REFUSE_REASON_MAP.put("DC06", "被法院冻结或收到法院止付通知书");
        REFUSE_REASON_MAP.put("DC07", "票据未到期");
        REFUSE_REASON_MAP.put("DC08", "商业承兑汇票承兑人账户余额不足");
        
        
        BusiTypeToSignUpBuilderMap.put("002", "AccptncSignUpBuilder");
        BusiTypeToSignUpBuilderMap.put("018", "CollztnSignUpBuilder");
        BusiTypeToSignUpBuilderMap.put("011", "DscntSignUpBuilder");
        BusiTypeToSignUpBuilderMap.put("010", "EndrsmtSignUpBuilder");
        BusiTypeToSignUpBuilderMap.put("017", "GuarnteeSignUpBuilder");
        BusiTypeToSignUpBuilderMap.put("003", "IssncSignUpBuilder");
        BusiTypeToSignUpBuilderMap.put("021", "OvrduePrsnttnSignUpBuilder");
        BusiTypeToSignUpBuilderMap.put("020", "PrsnttnSignUpBuilder");
        BusiTypeToSignUpBuilderMap.put("013", "RdscntWthComrclBkSignUpBuilder");
        BusiTypeToSignUpBuilderMap.put("019", "RpdCollztnSignUpBuilder");
        BusiTypeToSignUpBuilderMap.put("016", "RpdRdscntWthCntrlBkSignUpBuilder");
        BusiTypeToSignUpBuilderMap.put("014", "RpdRdscntWthComrclBkSignUpBuilder");
        BusiTypeToSignUpBuilderMap.put("025", "CntrlBkSellgDrftsSignUpBuilder");
        
        SIGNUPTYPE_TO_BUSITYPE.put("Accptnc", "002");
        SIGNUPTYPE_TO_BUSITYPE.put("Issnc", "003");
        SIGNUPTYPE_TO_BUSITYPE.put("Endrsmt", "010");
        SIGNUPTYPE_TO_BUSITYPE.put("Dscnt", "011");
        SIGNUPTYPE_TO_BUSITYPE.put("RpdDscnt", "012");
        SIGNUPTYPE_TO_BUSITYPE.put("RdscntWthComrclBk", "013");
        SIGNUPTYPE_TO_BUSITYPE.put("RpdRdscntWthComrclBk", "014");
        SIGNUPTYPE_TO_BUSITYPE.put("RdscntWthCntrlBk", "015");
        SIGNUPTYPE_TO_BUSITYPE.put("RpdRdscntWthCntrlBk", "016");
        SIGNUPTYPE_TO_BUSITYPE.put("Guarntee", "017");
        SIGNUPTYPE_TO_BUSITYPE.put("Collztn", "018");
        SIGNUPTYPE_TO_BUSITYPE.put("RpdCollztn", "019");
        SIGNUPTYPE_TO_BUSITYPE.put("Prsnttn", "020");
        SIGNUPTYPE_TO_BUSITYPE.put("OvrduePrsnttn", "021");
        SIGNUPTYPE_TO_BUSITYPE.put("Rcrs", "023");
        
        RGCT_CODE_MAPPING_MAP.put("BILL_TYPE_1", "AC01");
        RGCT_CODE_MAPPING_MAP.put("BILL_TYPE_2", "AC02");
        RGCT_CODE_MAPPING_MAP.put("AC01", "1");
        RGCT_CODE_MAPPING_MAP.put("AC02", "2");
        
        RGCT_CODE_MAPPING_MAP.put("FORBID_FLAG_0", "EM00");//背面禁止背书
        RGCT_CODE_MAPPING_MAP.put("FORBID_FLAG_1", "EM01");//背面禁止背书
        RGCT_CODE_MAPPING_MAP.put("EM00", "0");//背面禁止背书
        RGCT_CODE_MAPPING_MAP.put("EM01", "1");//背面禁止背书
        
        RGCT_CODE_MAPPING_MAP.put("IS_REGRESS_0", "RM00");//是否回购
        RGCT_CODE_MAPPING_MAP.put("IS_REGRESS_1", "RM01");//是否回购
        RGCT_CODE_MAPPING_MAP.put("RM00", "0");//是否回购
        RGCT_CODE_MAPPING_MAP.put("RM01", "1");//是否回购
        
        RGCT_CODE_MAPPING_MAP.put("ONLINE_MARK_1", "SM00");// 线上清算
        RGCT_CODE_MAPPING_MAP.put("ONLINE_MARK_0", "SM01");// 线下清算
        RGCT_CODE_MAPPING_MAP.put("SM00", "1");// 线上清算
        RGCT_CODE_MAPPING_MAP.put("SM01", "0");// 线下清算
        
        RGCT_CODE_MAPPING_MAP.put("CONTRACT_STATUS_0", "PP01");// 合同状态
        RGCT_CODE_MAPPING_MAP.put("CONTRACT_STATUS_1", "PP00");// 合同状态
        RGCT_CODE_MAPPING_MAP.put("PP01", "0");// 合同状态
        RGCT_CODE_MAPPING_MAP.put("PP00", "1");// 合同状态
       
        RGCT_CODE_MAPPING_MAP.put("SIGN_FLAG_1", "SU00");// 签收标记
        RGCT_CODE_MAPPING_MAP.put("SIGN_FLAG_0", "SU01");// 拒绝签收标记
        RGCT_CODE_MAPPING_MAP.put("SU00", "1");// 签收标记
        RGCT_CODE_MAPPING_MAP.put("SU01", "0");// 签收标记
        
    }
    
    public static final String REFUSE_REASON_CODE = "C010";
    public static final String REFUSE_REASON_CODE_DC09 = "DC09";
    public static final String REFUSE_REASON_CODE_DC08 = "DC08";
    
    
    /**
     * 新增
     */
    public static final String ECDS_CERTIFICATEBINDING_ADD = "AB00";

    /**
     * 删除
     */
    public static final String ECDS_CERTIFICATEBINDING_DEL = "AB01";
    
    
    /**
     * NPC 处理码-处理成功
     */
    public static final String PE1I0000 = "PE1I0000";

    /**
     * MBFE 处理码-处理成功
     */
    public static final String PE3I0001 = "PE3I0001";
    
    public static final String UCONDL_CONSGNMT_MRK="CC00";
    
    /**解除方式标识-强制解除**/
    public static final String CONTRACT_RESCISSION_MARKER_ENFORCE="RM02";
    /**解除方式标识-协商解除**/
    public static final String CONTRACT_RESCISSION_MARKER_CONSULT="RM01";
    
    /**电子合同签收标识-同意**/
    public static final String CONTRACT_RESPONSE_MARKER_AGREE="RM01";
    /**电子合同签收标识-拒绝**/
    public static final String CONTRACT_RESPONSE_MARKER_REJECT="RM02";
    
    /**
     * 贴现信息_贴现种类   RM00买断式 
     */
    public static final String REPURCHASED_MARK_CODE_RM00="RM00";
    
    /**
     * 贴现信息_贴现种类 RM01回购式
     */
    public static final String REPURCHASED_MARK_CODE_RM01="RM01";
    
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
     * 同意签收
     */
    public static final String SIGNUP_MARK_CODE1 = "SU00";

    /**
     * 拒绝签收
     */
    public static final String SIGNUP_MARK_CODE2 = "SU01";
    
    /////////////////////////////////////////////////////
    /////////                                 ///////////
    /////////          系统管理类                                                        ///////////
    /////////                                 //////////
    ////////////////////////////////////////////////////
    /**
     * 登陆 TypeMarkCode
     */
    public static final String ECDS_LOGIN = "TM00";

    public static final String ECDS_LOGIN_CN = "登陆";

    /**
     * 退出 TypeMarkCode
     */
    public static final String ECDS_LOGOUT = "TM01";

    public static final String ECDS_LOGOUT_CN = "退出";
    
    /**
     * 营业前准备
     */
    public static final String ECDS_SEQUENCE_BEFORE = "00";

    public static final String ECDS_SEQUENCE_BEFORE_CN = "营业前准备";

    /**
     * 日间处理
     */
    public static final String ECDS_SEQUENCE_RUN = "10";

    public static final String ECDS_SEQUENCE_RUN_CN = "日间处理";

    /**
     * 营业截止
     */
    public static final String ECDS_SEQUENCE_AFTER = "20";

    public static final String ECDS_SEQUENCE_AFTER_CN = "营业截止";

    /**
     * 日终
     */
    public static final String ECDS_SEQUENCE_LAST = "30";

    public static final String ECDS_SEQUENCE_LAST_CN = "日终";
    
    /**
     * 登录状态
     */
    public static final String ECDS_LOGON_STATUS = "ES001";

    public static final String ECDS_LOGON_STATUS_CN = "登录状态";

    /**
     * 运行时序状态 00：营业前准备； 10：日间处理；20：业务截止；30：日终处理
     */
    public static final String ECDS_SEQUENCE = "ES002";
    //日终状态
    public static final String ECDS_DAYEND = "ES020";

    public static final String ECDS_SEQUENCE_CN = "运行时序状态";

    /**
     * 系统当前日期
     */
    public static final String ECDS_CURRENT_DATE = "ES003";

    public static final String ECDS_CURRENT_DATE_CN = "系统当前日期";

    /**
     * 下一系统工作日期
     */
    public static final String ECDS_NEXT_DATE = "ES004";

    public static final String ECDS_NEXT_DATE_CN = "下一系统工作日期";

    /**
     * 营业参考时间
     */
    public static final String ECDS_RUN_TIME = "ES005";

    public static final String ECDS_RUN_TIME_CN = "营业参考时间";

    /**
     * 系统状态信息_原系统日期
     */
    public static final String ECDS_ORIGINAL_DATE = "ES007";

    public static final String ECDS_ORIGINAL_DATE_CN = "原系统日期";

    /**
     * 系统状态信息_原系统状态
     */
    public static final String ECDS_ORIGINAL_SEQUENCE = "ES008";

    public static final String ECDS_ORIGINAL_SEQUENCE_CN = "原系统状态";

    /**
     * 线上清算标识_线上清算标识
     */
    public static final String ECDS_SETTLEMENT_ONLINE = "ES009";

    public static final String ECDS_SETTLEMENT_ONLINE_CN = "线上清算标识";

    /**
     * 线上清算
     */
    public static final String ECDS_SETTLEMENT_ON = "SM00";

    public static final String ECDS_SETTLEMENT_ON_CN = "线上清算";

    /**
     * 线下清算
     */
    public static final String ECDS_SETTLEMENT_OFF = "SM01";

    public static final String ECDS_SETTLEMENT_OFF_CN = "线下清算";

    /**
     * 线上清算标识_附言
     */
    public static final String ECDS_SETTLEMENT_ONLINE_MARK = "ES010";

    public static final String ECDS_SETTLEMENT_ONLINE_MARK_CN = "线上清算标识附言";

    /**
     * 大额系统工作日信息_下一系统工作日期
     */
    public static final String ECDS_HVPS_NEXT_DATE = "ES011";

    public static final String ECDS_HVPS_NEXT_DATE_CN = "大额系统下一工作日期";

    /**
     * ECDS接入点新密码
     */
    public static final String ECDS_NEW_SIGN = "ES012";
    
    /**
     * 营业前准备批处理开关
     */
    public static final String ECDS_SWITCH_ST01 = "ES013";
    public static final String ECDS_SWITCH_ST01_CN = "营业前准备批处理开关";
    public static final String ECDS_SWITCH_MARK_SO01 = "SO01";  //开
    public static final String ECDS_SWITCH_MARK_SO02 = "SO02";  //关
    public static final String ECDS_SWITCH_MARK_ON = "开";   //开
    public static final String ECDS_SWITCH_MARK_OFF = "关";  //关

}
