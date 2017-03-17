package com.herongtech.console.web.busicontroller.utils;

import org.apache.commons.lang.StringUtils;

public class BillUtils {
	/**
	 * 获取票号后8位
	 * @param billNo
	 * @return
	 */
	public static String getLastEightBillNo(String billNo){
		String lastEight=null;
		if(StringUtils.isNotEmpty(billNo) && billNo.length()>8){
			lastEight = billNo.substring(billNo.length()-8, billNo.length());
		}
		return lastEight;
	}
}
