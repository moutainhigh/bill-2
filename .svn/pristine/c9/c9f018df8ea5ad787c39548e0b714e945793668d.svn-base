/********************************************
 * 文件名称: DictCheckBoxTag.java
 * 系统名称: 基础技术平台V2.0
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: 2.0.0.1
 * 开发人员: yanjl
 * 开发时间: 2016-6-15 下午01:48:52
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期    修改人员    修改说明
 *********************************************/

package com.herongtech.console.core.tag;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.lang.StringUtils;

import com.herongtech.console.cache.DictCache;
import com.herongtech.console.domain.bean.Dict;

/**
 * 
 * 选择下拉框
 * 
 * @author:
 * @date： 日期：2013-04-18
 * @version 1.0
 */
public class DictCheckBoxTag extends TagSupport {
	
   	/**
	 * 版本号
	 */
	public static final String HERONGTECH_VERSION="@system 票据管理平台 @version 2.0.0.1 @lastModiDate 20160615 @describe ";

	
	private static final long serialVersionUID = 1;
	private String dictGroup;   // 数据字典类型
	private String name;         // 选择表单ID EAMPLE:<select name="selectName" id = "" />
	private String defaultVal = ""; // 默认值
	private String valid = "";//校验规则
	private String tips = "";//校验提示信息
	public String getTips() {
		return tips;
	}
	public void setTips(String tips) {
		this.tips = tips;
	}
	public String getValid() {
		return valid;
	}
	public void setValid(String valid) {
		this.valid = valid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
    public String getDictGroup() {
		return dictGroup;
	}
	public void setDictGroup(String dictGroup) {
		this.dictGroup = dictGroup;
	}
	
	public String getDefaultVal() {
		return defaultVal;
	}
	public void setDefaultVal(String defaultVal) {
		this.defaultVal = defaultVal;
	}


	public int doEndTag() throws JspTagException {
		try {
			JspWriter out = this.pageContext.getOut();
			out.print(end().toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return EVAL_PAGE;
	}

	public String end() {
		StringBuffer sb = new StringBuffer();
		
		// 取相应的字典
		List<Dict> valueList = DictCache.getInstance().getDictList(dictGroup);
		// 取相应的字典
		if (valueList == null) {
			return "";
		}
		String[] delValues = defaultVal.split(",");

		for (int i = 0; i < valueList.size(); i++) {
			Dict aValue = (Dict) valueList.get(i);
			String itemCode = aValue.getItemCode().trim();
					
			if (!itemCode.equals("!") && !itemCode.equals("-1")) {
				boolean flag=false;
				if (delValues != null && delValues.length>0  ) {
					for(int m=0; m< delValues.length;m++){
						if(itemCode.equals(delValues[m])){
							flag=true;
							break;
						}
					}
				}
				String tipInfo = "";
				if(StringUtils.isNotBlank(tips)) tipInfo = "tips='"+tips+"'";
				String validInfo = "";
				if(StringUtils.isNotBlank(valid)) validInfo = "valid='"+valid+"'";
				if(flag){
					sb.append("<label ").append(tipInfo).append(validInfo);
					sb.append("><input type='checkbox' class='ace' name='").append(name);
					sb.append("'  id='").append(name).append("' value='").append(itemCode);
					sb.append("' checked/><span class='lbl'> ").append(aValue.getItemValue()).append(" </span></lable>");
				}else {
					sb.append("<label ").append(tipInfo).append(validInfo);
					sb.append("><input type='checkbox' class='ace' name='").append(name);
					sb.append("'  id='").append(name).append("' value='").append(itemCode);
					sb.append("'/><span class='lbl'> ").append(aValue.getItemValue()).append(" </span> </label>");
				}
				
				
				/*if(flag){
					sb.append("<label ><input type='checkbox' class='ace' name='" + name 
						+ "'  id='"+name+ "' value='" + itemCode + "' checked/><span class='lbl'> "
						+ aValue.getItemValue() + " </span></lable>");
				}else {
					sb.append("<label><input type='checkbox' class='ace' name='" + name
									+ "'  id='"+name+"' value='" + itemCode + "'/><span class='lbl'> " + aValue.getItemValue() + " </span> </label>");
				}*/
					
			}
		}
	
		return sb.toString();
	}
}
