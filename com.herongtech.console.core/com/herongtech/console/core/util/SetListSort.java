package com.herongtech.console.core.util;

import java.util.Comparator;

import com.herongtech.console.domain.bean.Menu;


/**
* @ClassName: SetListSort 
* @Description: TODO(int比较器) 
*
 */
public class SetListSort implements Comparator {
	
   	/**
	 * 版本号
	 */
	public static final String HERONGTECH_VERSION="@system 票据管理平台 @version 2.0.0.1 @lastModiDate 20160615 @describe ";
	
	/**
	 * 菜单排序比较器
	 */
	public int compare(Object o1, Object o2) {
		Menu c1 = (Menu) o1;
		Menu c2 = (Menu) o2;
		if (c1.getOrderNo() != 0 && c2.getOrderNo() != 0) {
			long c1order = c1.getOrderNo();
			long c2order = c2.getOrderNo();
			if (c1order > c2order) {
				return 1;
			} else {
				return -1;
			}
		} else {
			return 1;
		}

	}
}