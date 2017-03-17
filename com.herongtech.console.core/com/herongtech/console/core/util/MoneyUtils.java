/********************************************
 * 文件名称: MoneyUtils.java
 * 系统名称: 票据管理平台V2.0
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: 2.0.0.1
 * 开发人员: yy
 * 开发时间: 2016-7-15 下午13:14:52
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期    修改人员    修改说明
 *******************************************/
package com.herongtech.console.core.util;

import java.text.DecimalFormat;

import com.herongtech.commons.tools.BigDecimalUtil;
import com.herongtech.commons.tools.DoubleValue;
import com.herongtech.commons.tools.StringUtils;

public class MoneyUtils {
	private static String ls_number[] = {
        "零", "壹", "贰", "叁", "肆", "伍", "陆", "柒", "捌", "玖"};
    private static String ls_flag[] = {
        "仟", "佰", "拾", "亿", "仟", "佰", "拾", "万", "仟", "佰", "拾", "元", "角", "分"};
    private static String lz_flag[] = {
        "仟", "佰", "拾", "亿", "仟", "佰", "拾", "万", "仟", "佰", "拾", "张"};

    private MoneyUtils() {
    }
    
    /**
     * 将金额转化成大写
     * @param aje
     * @return
     */
    public static String toUpper(double aje) {
        String ls_money = "";
        String ls_chinese = "";
        if (aje < 0.001)return "零元整";
        DecimalFormat df = (DecimalFormat) DecimalFormat.getInstance();
        df.applyPattern("#.00");
        ls_money = df.format(formate(aje));
        ls_money = StringUtils.replace(ls_money, ".", "");

        int li_len = ls_money.length();
        int offsize = 14 - li_len;
        if (li_len > 14 || li_len == 0) {
            return "";
        }
        else {
            for (int i = 0; i < li_len; i++) {
                int x = Integer.parseInt(ls_money.substring(i, i + 1));
                ls_chinese = ls_chinese + ls_number[x] + ls_flag[offsize + i];
            }
            ls_chinese = StringUtils.replace(ls_chinese, "零仟", "零");
            ls_chinese = StringUtils.replace(ls_chinese, "零零", "零");
            ls_chinese = StringUtils.replace(ls_chinese, "零佰", "零");
            ls_chinese = StringUtils.replace(ls_chinese, "零零", "零");
            ls_chinese = StringUtils.replace(ls_chinese, "零拾", "零");
            ls_chinese = StringUtils.replace(ls_chinese, "零零", "零");
            ls_chinese = StringUtils.replace(ls_chinese, "零亿", "亿");
            ls_chinese = StringUtils.replace(ls_chinese, "零万", "万");
            ls_chinese = StringUtils.replace(ls_chinese, "亿万", "亿零");
            ls_chinese = StringUtils.replace(ls_chinese, "零零", "零");
            ls_chinese = StringUtils.replace(ls_chinese, "零元", "元");
            if (ls_chinese.indexOf("零角") > -1) {
                ls_chinese = StringUtils.replace(ls_chinese, "零角", "零");
            }
            ls_chinese = StringUtils.replace(ls_chinese, "零零", "零");
            if (ls_chinese.indexOf("零分") > -1) {
                ls_chinese = StringUtils.replace(ls_chinese, "零分", "整");
            }
            return ls_chinese;
        }
    }
    
    /**
     * 将整数数字转化成大写
     * @param aje
     * @return
     */
    public static String toUpperTool(String aje) {
        String ls_chinese = "";
        int li_len = aje.length();
        int offsize = 12 - li_len;
        if (li_len > 9 || li_len == 0) {
            return "";
        }
        int intvalue = Integer.parseInt(aje);
        if ( intvalue==0) return "零张";
        else {
            for (int i = 0; i < li_len; i++) {
                int x = Integer.parseInt(aje.substring(i, i + 1));
                ls_chinese = ls_chinese + ls_number[x] + lz_flag[offsize + i];
            }
            ls_chinese = StringUtils.replace(ls_chinese, "零仟", "零");
            ls_chinese = StringUtils.replace(ls_chinese, "零零", "零");
            ls_chinese = StringUtils.replace(ls_chinese, "零佰", "零");
            ls_chinese = StringUtils.replace(ls_chinese, "零零", "零");
            ls_chinese = StringUtils.replace(ls_chinese, "零拾", "零");
            ls_chinese = StringUtils.replace(ls_chinese, "零零", "零");
            ls_chinese = StringUtils.replace(ls_chinese, "零亿", "亿");
            ls_chinese = StringUtils.replace(ls_chinese, "零万", "万");
            ls_chinese = StringUtils.replace(ls_chinese, "亿万", "亿零");
            ls_chinese = StringUtils.replace(ls_chinese, "零零", "零");
            ls_chinese = StringUtils.replace(ls_chinese, "零张", "张");
            return ls_chinese;
        }
    }
       
    
    /**
     * 票号格式化处理
     * 16位。 8位+空格+8位
     * 30为。 13位+空格+9位+空格+。。
     * @param billNo
     * @return
     */
    public static String billNoSubString(String billNo){
    	if(billNo==null||billNo.equals("")){
    		return null;
    	}
    	if(billNo.length()<8){
    		return billNo;
    	}
    	
    	StringBuffer buffer= new StringBuffer();
    	if(billNo.length()==16){
        	buffer.append(billNo.substring(0, 8))
        	.append(" ")
        	.append(billNo.substring(8,billNo.length()));    		
    	}else if(billNo.length()==30){
    		buffer.append(billNo.substring(0, 13))
        	.append(" ")
        	.append(billNo.subSequence(13,21))
        	.append(" ")
        	.append(billNo.substring(21,billNo.length()));  
    	}else{
    		return billNo;
    	}
    	return  buffer.toString();
    }
    
    /**
     * 金额转换为￥123450，这样的样式，并去掉小数点
     */
    public static String toChinaSign(String money){
    	String jine =money;
		int dd111 = jine.indexOf(".");
		int h11 = jine.length() - 1;
		int length11 = jine.length();
		int u11 = h11 - dd111;
		String finalMoney11 = null;
		if ("1".equalsIgnoreCase(String.valueOf(u11))) {
			finalMoney11 = "￥" + jine.substring(0, dd111)
					+ jine.substring(dd111 + 1, dd111 + 2) + "0";
		} else {
			finalMoney11 = "￥" + jine.substring(0, dd111)
					+ jine.substring(dd111 + 1, dd111 + 3);
		}
		return finalMoney11;
    }
    
    /**
     * 如果金额或者利息为0.0时，调用此方法可以防止输出结果为.00的情况
     * @param args
     * add rpf 2009-12-08
     */
    public static String double2String(double args){
    	DecimalFormat df = (DecimalFormat) DecimalFormat.getInstance();
        df.applyPattern("#0.00");
        return df.format(formate(args));
    }
    
    public static String rate2String(double rate) { 
        DecimalFormat df = (DecimalFormat) DecimalFormat.getInstance();
        df.applyPattern("#0.000000");
        return df.format(formateRate(rate));
    }
    
    public static String doubleObjToString(Double aje) { 
    	if(null!=aje){
    		DecimalFormat df = (DecimalFormat) DecimalFormat.getInstance();
    		df.applyPattern("#.00");
    		return df.format(formate(aje));
    	}else{
    		return "0.00";
    	}
    }
    
    //money太大，转化成string会变成科学计数法
	public static String moneyToString(Double aje) {
		if(aje == null){
			return "0.00";
		}else{
			DecimalFormat df = (DecimalFormat) DecimalFormat.getInstance();
			df.applyPattern("0.00");
			return df.format(formate(aje));
		}
	}
    
    /**格式化金额格式
     * @param money 金额
     * @param scale 精度，小数点后位数
     * @return
     */
    public static Double formate(Double money,int scale){
    	if(money == null){
    		return null;
    	}else{
    		return BigDecimalUtil.round(money, scale);
    	}
    } 
    
    /**格式化金额格式
     * @param money 金额
     * @param scale 精度，小数点后位数
     * @return
     */
    public static Double formateByDefaultValue(Double money){
    	if(money == null){
    		return 0.0;
    	}else{
    		return BigDecimalUtil.round(money, 2);
    	}
    }
    
    /**格式化金额格式（金额为字符串格式）
     * @param money 金额
     * @param scale 精度，小数点后位数
     * @return
     */
    public static String formateByStringValue(String money){
    	if(StringUtils.isEmpty(money)){
    		return "0.0";
    	}else{
    		Double dmoney = Double.parseDouble(money);
    		return String.valueOf(BigDecimalUtil.round(dmoney, 2));
    	}
    }
   
    /**格式化金额格式，默认小数点后2位
     * @param money
     * @return
     */
    public static Double formate(Double money){
    	return formate(money, 2);
    } 
    
    /**格式化利率，默认小数点后6位
     * @param rate
     * @return
     */
    public static Double formateRate(Double rate){
    	return formate(rate, 6);
    } 
    /**将金额格式化为千分位格式
     * @param money
     * @return
     */
    public static String doubleToFormatStr(Double money) { 
    	if(null!=money){
    		DecimalFormat df = (DecimalFormat) DecimalFormat.getInstance();
    		df.applyPattern("###,##0.00");
    		return df.format(formate(money));
    	}else{
    		return "0.00";
    	}
    }
    
}
