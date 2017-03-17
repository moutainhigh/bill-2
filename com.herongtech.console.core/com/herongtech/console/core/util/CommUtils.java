package com.herongtech.console.core.util;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.DecimalFormat;

import org.apache.commons.lang.StringUtils;

import com.herongtech.console.domain.bean.Branch;
import com.herongtech.console.domain.dao.BranchDao;
import com.herongtech.exception.impl.BizAppException;

public class CommUtils {
	
	public static String[] couvertLong(String values) {
		if (StringUtils.isEmpty(values))
			return new String[0];
		String[] args = StringUtils.split(values.trim(), ",");
		return args;
	}
	
	 /**
     * 是否为本行
     * @param payBankNo
     * @return
     * @throws BizAppException 
     */
    public static boolean isSelfBank(String payBankNo) throws BizAppException{
    	BranchDao dao=new BranchDao();
    	Branch brch=null;
		try {
			brch = dao.getBranch(payBankNo,null);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BizAppException(e.getMessage());
		}
    	if(brch==null){
    		return false;
    	}else{
    		return true;
    	}
    }
    
    /**
	 * 获取票号后8位
	 * @param billNo
	 * @return
	 */
	public static String getLastBillNo(String billNo){
		String lastBillNo=null;
		if(StringUtils.isNotEmpty(billNo) && billNo.length()>8){
			lastBillNo = billNo.substring(billNo.length()-8, billNo.length());
		}
		return lastBillNo;
	}
	
	public static String getSignerSign(String bank_no) {
		return "0";
	}
	
	/**
	 * 金额计算（打印）专用，先保留两位小数 再计算
	 * @param v1
	 * @param v2
	 * @return
	 */
	public static double addForMoney(double v1, double v2){
		String v1Str = NumberFormat4(v1);
		String v2Str = NumberFormat4(v2);
		BigDecimal b1 = new BigDecimal(v1Str);
		BigDecimal b2 = new BigDecimal(v2Str);
		return b1.add(b2).doubleValue();
	}
	/**
	 * 格式化为 ####0.00
	 * @param data
	 * @return
	 */
	public static String NumberFormat4(Double data) {
		if(data==null){
			return null;
		}
		DecimalFormat nf = new DecimalFormat("#####0.00");
		return nf.format(data);
	}
	
	/**
	 * 格式化为：##,##0.00
	 * @param data
	 * @return
	 */
	public static String NumberFormat3(Double data) {
			if(data==null){
				return null;
			}
			DecimalFormat nf = new DecimalFormat("###,##0.00");
			return nf.format(data);
	}
	/**
	 * 格式化为 #####.00
	 * 如 0.15 ==>>> .15
	 * @param data
	 * @return
	 */
	public static String NumberFormat5(Double data) {
		if(data==null){
			return null;
		}
		DecimalFormat nf = new DecimalFormat("######.00");
		return nf.format(data);
	}
	/**
	 * 格式化 金额到页面输出 千万
	 * 格式化为table 的td 格式的 小写金额输出格式
	 * @param moneyFomate
	 * @param width
	 * @param height
	 * @param align
	 * @return
	 */
	public static String formateNumberToTableOut1(String moneyFomate,String width,String height,String align) {
		if(moneyFomate==null){
			return "";
		}
		StringBuffer sb = new StringBuffer();
		String money =moneyFomate;
		if (money.length() < 11) {
			for (int j = 0; j < 11 - money.length(); j++)
				sb.append("<td width=\""+width+"\" height=\""+height+"\" align=\"center\">&nbsp;</td>");
		}
		for (int i = 0; i < money.length(); i++) {
			if (!".".equals(String.valueOf(money.charAt(i)))) {
				sb.append("<td width=\""+width+"\" height=\""+height+"\" align=\""+align+"\" >");
				sb.append(money.charAt(i));
				sb.append("</td>");
			}
		}
		return sb.toString();
	}
	
	/**
	 * double数据格式化处理，因为 超过3位小数的小数会自动进行 科学技术法显示
	 * 打印涉及到利率的部分使用
	 * 所有利率最小为4位小数
	 * dapeng.dong add
	 * @param v1 
	 * @return
	 */
	public static String doubleFormateForString(Double v1) {
		if (v1 == null) {
			return "";
		}
		String tmp = Double.toString(v1);
		if (tmp.indexOf("E") > -1) {
			DecimalFormat nf = new DecimalFormat("#####0.0000");
			return nf.format(v1);
		}
		return tmp;
	}
	/**
	 * 格式化为：####.##
	 * @param data
	 * @return
	 */
	public static String NumberFormat2(Double data) {
		if(data==null){
			return null;
		}
		DecimalFormat nf = new DecimalFormat("###.##");
		return nf.format(data);
	}
	
	/**
	 * 输出 亿
	 * @param moneyFomate
	 * @param width 输出格的宽度
	 * @param height 高度
	 * @param align 对齐方式
	 * @return
	 */
	public static String formateNumberToTableOut4(String moneyFomate,String width,String height,String align) {
		if(moneyFomate==null){
			return "";
		}
		StringBuffer sb = new StringBuffer();
		String money = moneyFomate;
		if (money.length() < 12) {
			for (int j = 0; j < 12 - money.length(); j++)
				sb.append("<td width=\""+width+"\" height=\""+height+"\" align=\"center\">&nbsp;</td>");
		}
		for (int i = 0; i < money.length(); i++) {
			if (!".".equals(String.valueOf(money.charAt(i)))) {
				sb.append("<td width=\""+width+"\" height=\""+height+"\" align=\""+align+"\" >");
				sb.append(money.charAt(i));
				sb.append("</td>");
			}
		}
		return sb.toString();
	}

}
