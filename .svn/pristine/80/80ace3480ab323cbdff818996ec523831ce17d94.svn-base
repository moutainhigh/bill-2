package com.herongtech.console.core.tag;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.herongtech.commons.tools.StringUtils;
import com.herongtech.console.cache.DictCache;
import com.herongtech.console.core.util.ReflectionUtils;
import com.herongtech.console.domain.bean.Dict;


public class SelectTag extends TagSupport {
   	/**
	 * 版本号
	 */
	public static final String HERONGTECH_VERSION="@system 票据管理平台 @version 2.0.0.1 @lastModiDate 20160615 @describe ";

	
	private static final long serialVersionUID = 1;
	
	private String id;
	private String name;         
	private String disabled = "";//是否可编辑
	private String className = "";//class
	
	private String defaultVal;
	private String headerKey;
	private String headerValue;
	
	private String list;
	private String listKey;
	private String listValue;
	
	private String onchange = "";//值改变事件
	private String onclick = "";//点击事件
	private String multiple = "";//点击事件
	private String other = "";
	private String valid = "";//校验规则
	private String tips = "";//校验提示信息
	
	private String ulHeight = "";//ul下拉列表的高度
	
    public String getUlHeight() {
		return ulHeight;
	}
	public void setUlHeight(String ulHeight) {
		this.ulHeight = ulHeight;
	}

	public String getMultiple() {
		return multiple;
	}
	public void setMultiple(String multiple) {
		this.multiple = multiple;
	}

	public String getValid() {
		return valid;
	}


	public void setValid(String valid) {
		this.valid = valid;
	}


	public String getTips() {
		return tips;
	}


	public void setTips(String tips) {
		this.tips = tips;
	}


	public String getOther() {
		return other;
	}


	public void setOther(String other) {
		this.other = other;
	}


	public String getId() {
        return id;
    }

    
    public void setId(String id) {
        this.id = id;
    }

    
    public String getName() {
        return name;
    }

    
    public void setName(String name) {
        this.name = name;
    }

    
    public String getDisabled() {
        return disabled;
    }

    
    public void setDisabled(String disabled) {
        this.disabled = disabled;
    }

    
    public String getHeaderKey() {
        return headerKey;
    }

    
    public void setHeaderKey(String headerKey) {
        this.headerKey = headerKey;
    }

    
    public String getHeaderValue() {
        return headerValue;
    }

    
    public void setHeaderValue(String headerValue) {
        this.headerValue = headerValue;
    }

    
    public String getList() {
        return list;
    }

    
    public void setList(String list) {
        this.list = list;
    }

    
    public String getListKey() {
        return listKey;
    }

    
    public void setListKey(String listKey) {
        this.listKey = listKey;
    }

    
    public String getListValue() {
        return listValue;
    }

    
    public void setListValue(String listValue) {
        this.listValue = listValue;
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
    
    

    
    public String getDefaultVal() {
        return defaultVal;
    }


    
    public void setDefaultVal(String defaultVal) {
        this.defaultVal = defaultVal;
    }


    public String getClassName() {
		return className;
	}


	public void setClassName(String className) {
		this.className = className;
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
		List valueList =(List) pageContext.getRequest().getAttribute(list);
		String validInfo = "";
		if(!StringUtils.isBlank(valid)) validInfo = " valid='"+valid+"'";
		String tipInfo = "";
		if(!StringUtils.isBlank(tips)) tipInfo = " tips='"+tips+"'";
		String classInfo = " class='select2";
		if(!StringUtils.isBlank(className)) classInfo = classInfo+" "+className;
		classInfo = classInfo+"'";
		sb.append("<select ").append(classInfo).append(validInfo).append(tipInfo).append("name='").append(name);
		sb.append("' id='").append(id).append("' style='vertical-align:top;");
		sb.append(StringUtils.isEmpty(other) ? "" : other).append('\'');
		if(!StringUtils.isBlank(multiple)){
			sb.append(" multiple='").append(multiple).append('\'');
		}
		if(!StringUtils.isBlank(disabled)){
			sb.append(" disabled='").append(disabled).append('\'');
		}
		if(!StringUtils.isBlank(onchange)){
			sb.append(" onchange=\"").append(onchange).append('"');
		}
		if(!StringUtils.isBlank(onclick)){
			sb.append(" onclick=\"").append(onclick).append('"');
		}
		if(!StringUtils.isBlank(ulHeight)){
			sb.append(" ulHeight=\"").append(ulHeight).append("\"");
		}
		sb.append("'> ");
		if (!StringUtils.isEmpty(headerKey)) {
			sb.append( "<option value='"+headerValue+"'>请选择"+headerKey+"</option>");
		}
		if (valueList != null && valueList.size() > 0) {
			for (int i = 0; i < valueList.size(); i++) {
				Object aValue =  valueList.get(i);
			    Object value = ReflectionUtils.getFieldValue(aValue, listValue).toString();
			    String disPlay = ReflectionUtils.getFieldValue(aValue, listKey).toString();
			    if(!StringUtils.isBlank(defaultVal)) {
			    	String[] dVal = defaultVal.split(",");
			    	for (String string : dVal) {
			    		if (string.equals(value)) { 
			    			sb.append("<option value='"+ value + "' selected>" + value + "--" + disPlay + "</option>");
			    		}else {
			    			sb.append("<option value='"+ value + "'>" + value + "--" + disPlay + "</option>");// modified
						}
					}
			    }else {
			    	sb.append("<option value='"+ value + "'>" + value + "--" + disPlay + "</option>");// modified
			    }
			}
		}
		sb.append("</select>");
		return sb.toString();
	}
}
