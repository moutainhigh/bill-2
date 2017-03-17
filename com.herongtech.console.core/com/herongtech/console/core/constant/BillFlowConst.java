package com.herongtech.console.core.constant;

import java.util.HashMap;
import java.util.Map;

public class BillFlowConst {
	/**
	 * 申请方：
	 * 0、035清分失败
	 * 1、申请提交
	 * 2、申请失败
	 * 3、待签收
	 * 4、对方已签收
	 * 5、对方已拒绝
	 * 6、申请撤销成功
	 * 7、申请撤销失败
	 * 8、撤票成功
	 * 9、撤票失败
	 * 
	 * 签收方
	 * 10、待签收
	 * 11、签收成功
	 * 12、签收失败
	 * 13、拒绝成功
	 * 14、拒绝失败
	 * 15、申请已撤销
	 * 16、035清分失败
	 */
	public final static String APPLY_STATUS_035= "0";
	public final static String APPLY_STATUS_COMMIT = "1";
	public final static String APPLY_STATUS_FAIL = "2";
	public final static String APPLY_STATUS_WAIT_SIGN = "3";
	public final static String APPLY_STATUS_SIGN = "4";
	public final static String APPLY_STATUS_REFUSE = "5";
	public final static String APPLY_STATUS_CANCEL_S= "6";
	public final static String APPLY_STATUS_CANCEL_E= "7";
	public final static String APPLY_STATUS_DESTRUCTION_S= "8";
	public final static String APPLY_STATUS_DESTRUCTION_E= "9";
	
	public final static String SIGN_STATUS_WAIT= "10";
	public final static String SIGN_STATUS_S= "11";
	public final static String SIGN_STATUS_E= "12";
	public final static String SIGN_STATUS_REFUSE_S= "13";
	public final static String SIGN_STATUS_REFUSE_E= "14";
	public final static String APPLY_STATUS_CANCEL= "15";
	public final static String SIGN_STATUS_035= "16";
	
	
	/**
	 * 操作类型
	 */
	public final static String OPER_REQUEST= "0";
	public final static String OPER_SIGN= "1";
	
	/**
	 * busiType;//业务种类 1、收票2、背书3、提示付款 4、出票登记 5、提示承兑 6、贴现 7、质押 8、解质押 9、保证 10、撤票 12、回购式贴现赎回 13、转贴现 14、回购式转贴现赎回 15、再贴现申请 16、回购式再贴现赎回 
	 */
	public final static String BUSI_ISSUANCE= "1";
	public final static String BUSI_ENDORSEMENT= "2";
	public final static String BUSI_PRESENTATION= "3";
	public final static String BUSI_REGISTER= "4";
	public final static String BUSI_ACCEPTANCE= "5";
	public final static String BUSI_DISCOUNT= "6";
	public final static String BUSI_COLLATERALIZATION= "7";
	public final static String BUSI_REPURCHASEDCOLLATERALIZATION= "8";
	public final static String BUSI_GUARANTEE= "9";
	public final static String BUSI_CANCLEBILL= "10";
	public final static String BUSI_RepurchasedDiscount= "12";
	public final static String BUSI_RediscountWithCommercialBank= "13";
	public final static String BUSI_RepurchasedRediscountWithCommercialBank= "14";
	public final static String BUSI_RediscountWithCentralBank= "15";
	public final static String BUSI_RepurchasedRediscountWithCentralBank= "16";
	
	
	public static Map<String,String>  DRAFTNO_MAP = new HashMap<String,String>();
	public static Map<String, String> busiMap = new HashMap<String, String>();
	
	static{
		//	1、收票2、背书3、提示付款 4、出票登记 5、提示承兑 6、贴现 7、质押 8、解质押 9、保证 12、回购式贴现赎回 13、转贴现 14、回购式转贴现赎回 15、再贴现申请 16、回购式再贴现赎回 
		DRAFTNO_MAP.put("CommercialDraftRegisterRequest","4");
		DRAFTNO_MAP.put("CommercialDraftAcceptanceRequest","5");
		DRAFTNO_MAP.put("CommercialDraftIssuanceRequest","1");
		DRAFTNO_MAP.put("CommercialDraftEndorsementRequest","2");
		DRAFTNO_MAP.put("CommercialDraftDiscountRequest","6");
		DRAFTNO_MAP.put("CommercialDraftRepurchasedDiscountRequest","12");
		DRAFTNO_MAP.put("CommercialDraftRediscountWithCommercialBankRequest","13");
		DRAFTNO_MAP.put("CommercialDraftRepurchasedRediscountWithCommercialBankRequest","14");
		DRAFTNO_MAP.put("CommercialDraftRediscountWithCentralBankRequest","15");
		DRAFTNO_MAP.put("CommercialDraftRepurchasedRediscountWithCentralBankRequest","16");
		DRAFTNO_MAP.put("CommercialDraftGuaranteeRequest","9");
		DRAFTNO_MAP.put("CommercialDraftCollateralizationRequest","7");
		DRAFTNO_MAP.put("CommercialDraftRepurchasedCollateralizationRequest","8");
		DRAFTNO_MAP.put("CommercialDraftPresentationRequest","3");
		DRAFTNO_MAP.put("CommercialDraftOverduePresentationRequest","3");
		
		busiMap.put("1","收票");
		busiMap.put("2","背书");
		busiMap.put("3","提示付款");
		busiMap.put("4","出票登记");
		busiMap.put("5","提示承兑");
		busiMap.put("6","贴现");
		busiMap.put("7","质押");
		busiMap.put("8","解质押");
		busiMap.put("9","保证");
		busiMap.put("10","撤票");
		busiMap.put("12","回购式贴现赎回");
		busiMap.put("13","转贴现");
		busiMap.put("14","回购式转贴现赎回");
		busiMap.put("15","再贴现申请");
		busiMap.put("16","回购式再贴现赎回");
	}
	
	
	/**业务种类 
	 * 1、收票2、背书3、提示付款 4、出票登记 5、提示承兑 
	 * 6、贴现 7、质押 8、解质押 9、保证 10、撤票 12、回购式贴现赎回
	 * 13、转贴现 14、回购式转贴现赎回 15、再贴现申请 16、回购式再贴现赎回
	 * @return
	 */
	public static String getBusiTypeCN(String busiType){		
		return busiMap.get(busiType);
	}

}
