package com.herongtech.console.web.busicontroller.common;

import java.util.HashMap;
import java.util.Map;

import com.herongtech.console.core.constant.RcConstants;

/**
 * 托收代码常量类
 * @author Administrator
 *
 */
public class SubcollCodeConst {
	
	public final static Map<String,String> statusMap = new HashMap<String, String>();
	/**
	 * 
	 * //帐务库存的最近来源：(贴现)1.001001001 2.001001002   3.001001003 4.001001004  5.001001005  6.001001006
	 * 				  (转入)1.001002001 2.001002002   3.001002003 4.001002004  5.001002005  6.001002006  7.001002007  8.001002008
	 * 				  (质押)1.001004001
	 * 				 (票据池)1.001007001 2.001008001
	 * 				  (回购)1.001009001 2.001009002 3.001009003 
	 * //托收来源码表：1:贴现，2：转入,3：质押,4：票据池,5到期回购
	 * 
	 * @return
	 */
	static {
		statusMap.put("001001001", "1");
		statusMap.put("001001002", "1");
		statusMap.put("001001003", "1");	
		statusMap.put("001001004", "1");
		statusMap.put("001001005", "1");
		statusMap.put("001001006", "1");
		statusMap.put("001002001", "2");
		statusMap.put("001002002", "2");
		statusMap.put("001002003", "2");
		statusMap.put("001002004", "2");
		statusMap.put("001002005", "2");
		statusMap.put("001002006", "2");
		statusMap.put("001002007", "2");
		statusMap.put("001002008", "2");
		statusMap.put("001004001", "3");
		statusMap.put("001007001", "4");
		statusMap.put("001008001", "4");
		statusMap.put("001009001", "5");
		statusMap.put("001009002", "5");
		statusMap.put("001009003", "5");
	}
	/** 托收票据来源 */ 
	public static final String SUB_ONE = "1";//贴现
	public static final String SUB_TWO = "2";//转入
	public static final String SUB_THREE = "3";//质押
	public static final String SUB_FOUR = "4";//票据池
	public static final String SUB_FIVE = "5";//回购
	
	/**是否逾期 */ 
	public static final String IS_OVERDUE_ONE="1";//否
    public static final String IS_OVERDUE_TWO="2";//是
    
    /**SUB_COLL_LIST->STATUS状态  1，托收在途；2,结清;3,退票*/
	/**托收在途*/
	public static final String SUB_STAUTS_ONWAY ="1";
	/**结清*/
	public static final String SUB_STAUTS_SETTLED ="2";
	/**退票*/
	public static final String SUB_STAUTS_REFUSEBACK ="3";
	
	public static final String APPLY = "1";//申请
	public static final String OUTSTORE = "2";//电票发托、纸票出库
	public static final String REGISTER = "3";//收款登记(纸质、电子)、纸票退票登记(纸质)
	public static final String ACCOUNT = "4";//收款记账(纸质、电子)
	public static final String ELEC_BACK_REGISTER = "5";//退票登记(电子)
	public static final String BACKINSTORE = "6";//入库(纸质)
	public static final String ELEC_WAIT_033_FOR_APPLY = "7";//等待确认报文回复
	public static final String ELEC_WAIT_033_FOR_032 = "8";//等待确认报文回复
	public static final String ELEC_WAIT_031 = "9";//等待签收方回复
	
	public static final String ACCOUNT_OVER = "04";//结束(收款记账)
	public static final String BACKINSTORE_OVER = "05";//结束(退票)

	
	/**提示付款背书 */
	public static final String ACPT_SUB_COLL_LIST_324="BS324";
	/**发出托收出库*/
	public static final String ACPT_SUB_COLL_LIST_325="BS325";
	
	/**托收清单打印膜版id*/
	public static final String BILL_PRINT_MYSUBID = "1070401";//膜版id
	/**托收凭证打印膜版id*/
	public static final String BILL_PZ_PRINT_MYSUBID = "1070101";//凭证膜版id
	
	/**产品编号*/
	public static final String PRODUCT_ACPT_TS = "001006";//托收
	public static final String PRODUCT_ACPT_TS_QKSH = "001006001";//票款收回
	public static final String PRODUCT_ACPT_TS_DKTS = "001006002";//发出托收
	public static final String PRODUCT_ACPT_TS_DELAY_INCOME = "001006003";//延期利息收入
	public static final String PRODUCT_ACPT_TS_DELAY_OUT= "001006004";//延期利息收入
	public static final String PRODUCT_ACPT_TS_CANCEL = "001006005";//托收退票
	
	/**票据池开通标志  1 开通; 0 不开通*/
	public static final String SIGN_PROD_CMS_OPEN = RcConstants.YES;
	
	public static final String SIGN_PROD_CMS_CLOSE = RcConstants.NO;
	
	//托收判断是否是最近转入来源
	//新系统现在没有电票托管
	public static boolean  isRpdiscount(String billSource, String billTrack){
		return SubcollCodeConst.SUB_TWO.equals(billSource) && !"1".equals(billTrack);
	}
		
	//托收判断是否是最近贴现来源
	public static boolean isDiscount(String billSource, String billTrack){
		return SubcollCodeConst.SUB_ONE.equals(billSource) && "1".equals(billTrack);
	}
		
	//托收判断是否是票管或质押
	public static boolean isSaveBill(String billSource){
		return SubcollCodeConst.SUB_THREE.equals(billSource) || SubcollCodeConst.SUB_FOUR.equals(billSource);
	}
	//托收判断是否是回购
		public static boolean isBuybackBill(String billSource){
			return SubcollCodeConst.SUB_FIVE.equals(billSource);
		}
}
