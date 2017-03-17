/******************************************** 
 * 文件名称: IConstants.java 
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



/**
 * 
 *
 */
public final class IConstants {
	public static final String HERONGTECH_VERSION="@system 合融技术平台 @version V2.0.0.1 @lastModiDate @describe ";

	/**
	 * 系统信息存放名称
	 */
	public final static  String SYSBUSININFO = "SYSBUSININFO";
	
	/**页面显示最大行数*/
	public final static  int MAXPAGESIZE = 20;
	
	/**
	 * 默认参数
	 */
	public final static  String DEFAULT_PARAM_CODE = "000000";
	
	/**
	 * 默认法人行编号
	 */
	public final static  String DEFAULT_BANK_NO = "000";
	
	//逻辑顺序常量
	public final static String Zero="0";
	public final static String One="1";
	public final static String Two="2";
	public final static String Three="3";
	public final static String Four="4";
	public final static String Five="5";
	public final static String Six="6";
	public final static String Seven="7";
	public final static String Eight="8";
	public final static String Nine="9";
	public final static String Ten="10";
	public final static String Eleven="11";
	public final static String Twelve="12";
	public final static String Thirteen="13";
	
	
	public final static String YES = IConstants.One;
	public final static String NO = IConstants.Zero;
	

	
	public static final String BILLPOOL_SINGPROD = "S511001001";    //票据池服务产品编号：S511001001
	public static final String ELECTRON_SINGPROD = "S511002001";        //电子票据服务产品编号：S511002001
	public static final String ACPT_SINGPROD  = "S511003001";          //银行承兑汇票:S511003001(账户级签约)
	public static final String ACPT_CORP_SINGPROD = "S511004001";  //银行承兑汇票--商票:S511004001(账户级签约)
	
	
	
	
	/* 日志表公共标识名称  */
	public static final String DRAFT_LOG = "DRAFT_LOG";
	
	public final static  String TRANS_INFO = "TRANS_INFO";

	/**
	 * BA
	 */
	public static interface BA {
		/** 字典名称 */
		public static final String BA = "BA";
		public static final String IRcAcptSignService = "IRcAcptSignService";
		public static final String IRcRegBillService = "IRcRegBillService";
		public static final String IRcAcptBillService = "IRcAcptBillService";
		public static final String IRcBillNotifyService = "IRcBillNotifyService";
		public static final String IRcCancelBillService = "IRcCancelBillService";
		
		public static final String IRcEndorseService = "IRcEndorseService";
		public static final String IRcCollectService = "IRcCollectService";
		public static final String IRcFreeService = "IRcFreeService";
		public static final String IRcAccountService = "IRcAccountService";
		public static final String IRcStopPayService = "IRcStopPayService";
		public static final String IRcOverdueService = "IRcOverdueService";
		
		public static final String RC_BA_ACPTSIGN = "RC_BA_AcptSign";
		public static final String RC_BA_ENDORSE = "RC_BA_Endorse";
		public static final String RC_BA_COLLECT = "RC_BA_Collect";
		public static final String RC_BA_FREE = "RC_BA_Free";
		public static final String RC_BA_ACCOUNT = "RC_BA_Account";
		public static final String RC_BA_STOPPAY = "RC_BA_StopPay";
		public static final String RC_BA_OVERDUE = "RC_BA_Overdue";
	}
	 
	
	/**
	 * 利息相关常量
	 *
	 */
	public static interface INTEREST{
	    /**
	     * 年利率
	     */
	    public static final String YEAR_RATE_TYPE = "360";

	    /**
	     * 月利率
	     */
	    public static final String MONTH_RATE_TYPE = "30";

	    /**
	     * 日利率
	     */
	    public static final String DAY_RATE_TYPE = "1";
	    
	    
	    /**
	     * 顺延方式
	     * <br>0:不顺延
	     */
	    public static final String DELAY_NO_CHARGE = "0";

	    /**
	     * 顺延方式
	     * <br>1:只顺延节假日
	     */
	    public static final String DELAY_HOLIDAY_CHARGE = "1";

	    /**
	     * 顺延方式
	     * <br>2:只顺延异地
	     */
	    public static final String DELAY_PLACE_CHARGE = "2";

	    /**
	     * 顺延方式
	     * <br>3:先节假日后异地
	     */
	    public static final String DELAY_HOLIDAY_PLACE_CHARGE = "3";

	    /**
	     * 顺延方式
	     * <br>4:先异地后节假日
	     */
	    public static final String DELAY_PLACE_HOLIDAY_CHARGE = "4";
	    
	    /**
         * 顺延方式
         * <br>5:指定顺延天数
         */
        public static final String DELAY_DAYS_CHARGE = "5";

	    /**
	     * 顺延执行方式 
	     * <br>0:自动执行
	     */
	    public static final String DELAY_AUTO_OPERTYPE = "0";

	    /**
	     * 顺延执行方式
	     * <br>1:手动选择
	     */
	    public static final String DELAY_HAND_OPERTYPE = "1";

	    /**
	     * 顺延执行方式
	     * <br>2:只选择天数
	     */
	    public static final String DELAY_DAYS_OPERTYPE = "2";
	    
	}
	
	
	/**
	 * 记账类型
	 *
	 */
	public static interface ACCT_TYPE{
	    
	    /**
	     *记账
	     *{@value} 
	     */
	    public static final String ACCOUNT="1";
	    
	    /**
	     * 冲正
	     * {@value}
	     */
	    public static final String REVERSE_ACCOUNT="2";
	    
	}
	
	
	/**
     * 余额状态
     *
     */
    public static interface BALANCE_STATUS{
        
        /**
         *余额
         *{@value} 
         */
        public static final String BALANCE="1";
        
        /**
         * 非余额
         * {@value}
         */
        public static final String NON_BALANCE="2";
        
    }
    
    
    /**
     * 外部系统编码
     *
     */
    public static interface SYSTEM_CODE{
        
        /**
         *核心
         *{@value} 
         */
        public static final String CORE="CORE";
        
        /**
         * 信贷
         * {@value}
         */
        public static final String CREDIT="CREDIT";
        
    }
	
    /**
     * 数据类型
     *
     */
    public static interface DATA_TYPE{
        
        /**
         *字符串
         *{@value} 
         */
        public static final String STRING="string";
        
        /**
         *整形
         *{@value} 
         */
        public static final String INTEGER="int";
        
        /**
         *浮点型
         *{@value} 
         */
        public static final String DOUBLE="double";
    }
    
    /**
     * 系统通用开关   统一0否 1是
     *
     */
    public static interface SWITCH_FLAG{
        
        /**
         * 开启
         */
        public static String OPEN="1";
        
        /**
         * 关闭
         */
        public static String CLOSE="0";
    }
    
    /**
     * 系统参数常量类
     *
     */
    public static interface SysParamConstant{
        //在缓存中的参数code
    	
        /**
         * 提醒天数标志
         */
        public static String REMIND_DAYS="REMIND_DAYS";
    	
    	
        /**
         * 日终开启标志
         */
        public static String BATCH_START_FLAG="BATCH_START_FLAG";
        
        /**
         * 是否自动清算
         */
        public static String BATCH_AUTO_FLAG="BATCH_AUTO_FLAG";
        
        /**
         * 日终开启定时时间 格式:HHMMSS
         */
        public static String BATCH_AUTO_TM="BATCH_AUTO_TM";
        
        /**
         * 日终清算日期
         */
        public static String BATCH_BUSIDATE="BATCH_BUSIDATE";
        /**
         * 外部代理开关标识（"1":有外部代理；"0":无外部代理)--额度相关
         */
        public static String LIMIT_USED_SWITCH="LIMIT_USED_SWITCH";
    }
    
    public static interface DAY_END{
        /** 
         * 日终初始化.... 
         * */
        public static final String BATCH_INIT="0";
        /**
         *  日终状态中....
         *   */
        public static final String BATCH_PROC="1";
        /** 
         * 日终完成
         *  */
        public static final String BATCH_FINISH="2";
        
        /**作业类型 - 清算前处理*/
        public static final String TASK_TYPE_PRE = "1";
        /**作业类型 - 清算*/  
        public static final String TASK_TYPE_CLEAR = "2";
        /**作业类型 - 初始化*/
        public static final String TASK_TYPE_INIT = "3";
        
        
        /**
         * 未激活
         * */
        public static final String DEAL_STATUS_NOTACTIVE="0";
        /**
         * 已激活
         * */
        public static final String DEAL_STATUS_ACTIVE="1";
        /**
         * 作业暂停
         * */
        public static final String DEAL_STATUS_STOP="2";
        /**
         * 作业完成
         * */
        public static final String DEAL_STATUS_FINISH="3";
        /**
         * 作业失败
         * */
        public static final String DEAL_STATUS_FAIL="4";
        /**
         * 作业中断
         * */
        public static final String DEAL_STATUS_INTERRUPT="5";
        /**
         * 作业正在处理
         * */
        public static final String DEAL_STATUS_ING="Z";
        
        /**日终作业生效 */
        public static final String TASK_EFFECTIVE="1";
        
        /**延迟处理*/
        public static final String DELAY_FLAG_YES="1";
        
    }
    
    public static interface AuditConstant{
        
        public static final String SWITCH_NAME="auditSwitch";
        /**审批路线生效标志：1-生效*/
        public static final String EFFECT_STATUS_YES = "1";
        
        /**审批路线生效标志：0-失效*/
        public static final String EFFECT_STATUS_NO = "0";
        
        /**审批任务状态：1-审批中*/
        public static final String AUDIT_PROCESSING = "1";
        
        /**树节点类型：根节点*/
        public static final String TREE_NODE_TYPE_ROOT = "0";
        
        /**树节点类型：子节点*/
        public static final String TREE_NODE_TYPE_CHILD = "1";
        
        /**树节点类型：叶子节点*/
        public static final String TREE_NODE_TYPE_LEAF = "2";
        
        /**机构级别：总行*/
        public static final String BRCH_LEVEL_HEAD = "1";
        
        /**机构级别：分行*/
        public static final String BRCH_LEVEL_BRANCH = "2";
        
        /**机构级别：支行*/
        public static final String BRCH_LEVEL_SUB_BRANCH = "3";
        
        /**机构级别：网点*/
        public static final String BRCH_LEVEL_ATTICE_POINT = "4";
        
       

        
        /**审批路线使用模式：全行通用*/
        public static final String PUB_FLAG_ALL = "1";
        
        /**审批路线使用模式：指定机构使用*/
        public static final String PUB_FLAG_ASSIGN_BRCH = "2";
        
      
        
        /**审批岗位执行模式：岗位审批权限控制执行*/
        public static final String AN_EXEC_MODE_POST = "1";
        
        /**审批岗位执行模式：全部执行*/
        public static final String AN_EXEC_MODE_ALL = "2";
        
        
        /**上移*/
        public static final int MOVE_UP = 0;
        
        /**下移*/
        public static final int MOVE_DOWN = 1;
        
        /**审批任务状态：1-审批中*/
        public static final String AT_STATUS_PROCESS = "1";
        
        /**审批任务状态：2-审批通过*/
        public static final String AT_STATUS_SUCCESS = "2";
        
        /**审批任务状态：3-审批不通过*/
        public static final String AT_STATUS_FAIL = "3";
        
        /** 审批状态：1-未受理*/
        public static final String AP_STATUS_UNTREATED = "1";
        
        /** 审批状态：2-受理中*/
        public static final String AP_STATUS_PROCESS = "2";
        
        /** 审批状态：3-受理完毕*/
        public static final String AP_STATUS_SUCCESS = "3";
        
        /** 审批结果：1-同意 */
        public static final String AP_EXEC_RESULT_AGREE = "1";
        
        /** 审批结果：2-拒绝*/
        public static final String AP_EXEC_RESULT_REFUSE = "2";
        
        /** 审批结果：3-驳回 */
        public static final String AP_EXEC_RESULT_REJECT = "3";
        
        /** 序号：1 */
        public static final int ONE = 1;
        
        /**空格" "*/
        public static final String BLANK_STRING = " ";
        
        /**0*/
        public static final String ZERO = "0";
       
    }
    
}
