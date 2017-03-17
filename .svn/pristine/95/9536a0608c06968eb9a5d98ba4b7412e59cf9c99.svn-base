/********************************************
 * 文件名称: DictSelectTag.java
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

import com.herongtech.commons.tools.StringUtils;
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
public class DictSelectTag extends TagSupport {
   	/**
	 * 版本号
	 */
	public static final String HERONGTECH_VERSION="@system 票据管理平台 @version 2.0.0.1 @lastModiDate 20160615 @describe ";

	
	private static final long serialVersionUID = 1;
	private String dictGroup;   // 数据字典类型
	private String name;         // 选择表单ID EAMPLE:<select name="selectName" id = "" />
	private String defaultVal = ""; // 默认值
	
	private boolean haveHead = true;
	private String 	other = "";
	private String className = "";//class
	private String ulHeight="";//ul下拉列表的高度
	private String 	title = ""; //默认空值显示值
	private String valid = "";//校验规则
	private String tips = "";//校验提示信息
	private String disabled = "";//是否可编辑
	private String onchange = "";//值改变事件
	private String onclick = "";//点击事件
	private String multiple;
	public String getMultiple() {
		return multiple;
	}
	public void setMultiple(String multiple) {
		this.multiple = multiple;
	}
	
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	
	public String getUlHeight() {
		return ulHeight;
	}
	public void setUlHeight(String ulHeight) {
		this.ulHeight = ulHeight;
	}
	public String getDisabled() {
		return disabled;
	}
	public void setDisabled(String disabled) {
		this.disabled = disabled;
	}
	public String getOnchange() {
		return onchange;
	}
	public void setOnchange(String onchange) {
		this.onchange = onchange;
	}
	public String getOnclick() {
		return onclick;
	}
	public void setOnclick(String onclick) {
		this.onclick = onclick;
	}
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
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
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

	public boolean isHaveHead() {
		return haveHead;
	}

	public void setHaveHead(boolean haveHead) {
		this.haveHead = haveHead;
	}
	
	public String isOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
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
		defaultVal = StringUtils.isEmpty(defaultVal) ? "" : defaultVal;
		if (valueList == null || valueList.size() == 0) {
		    return "";
		}
		String tipInfo = "";
		if(!StringUtils.isBlank(tips)) tipInfo = "tips='"+tips+"'";
		String validInfo = "";
		if(!StringUtils.isBlank(valid)) validInfo = "valid='"+valid+"'";
		String classInfo ="class='select2";// " select2";
		if(!StringUtils.isBlank(className)) classInfo = classInfo+" "+className;
		classInfo = classInfo+"'";
		sb.append("<select ").append(classInfo).append(tipInfo).append(validInfo).append(" name='").append(name);
		sb.append("' id='").append(name).append("' style='vertical-align:top;");
		sb.append(StringUtils.isEmpty(other) ? "" : other).append('\'');
		if(!StringUtils.isBlank(disabled)){
			sb.append(" disabled='").append(disabled).append('\'');
		}
		if(!StringUtils.isBlank(onchange)){
			sb.append(" onchange=\"").append(onchange).append('"');
		}
		if(!StringUtils.isBlank(onclick)){
			sb.append(" onclick=\"").append(onclick).append('"');
		}
		if(!StringUtils.isBlank(multiple)){
			sb.append(" multiple=\"").append(multiple).append('"');
		}
		if(!StringUtils.isBlank(ulHeight)){
			sb.append(" ulHeight=\"").append(ulHeight).append("\"");
		}
		sb.append("'> ");
		/*sb.append("<select valid='required' class='select2' name='" + name + 
				"' id='" + name + "' style='vertical-align:top;" + (StringUtils.isEmpty(other) ? "" : other) +   "'> ");
		*/
		if (haveHead) {
			sb.append( "<option value=''>请选择"+title+"</option>");
		}
		for (int i = 0; i < valueList.size(); i++) {
			Dict aValue = (Dict) valueList.get(i);
		    String itemCode = aValue.getItemCode().trim();
		    if (defaultVal.equals(itemCode)) { 
		    	sb.append("<option value='"+ itemCode + "' selected>"
		    					+ ((itemCode.equals("-1") || itemCode.equals("!")) ? aValue.getItemValue()
		    							: (itemCode + "--" + aValue.getItemValue())) + "</option>");
		    } else {
		    	sb.append("<option value='" + itemCode + "'>"
				+ ((itemCode.equals("-1") || itemCode.equals("!")) ? aValue.getItemValue()
					: (itemCode + "--" + aValue.getItemValue())) + "</option>"); // modified
		    }
		}
		sb.append("</select>");
		return sb.toString();
	}
}
