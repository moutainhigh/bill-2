package com.herongtech.console.core.tag;

import java.io.IOException;
import java.util.List;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.herongtech.commons.tools.StringUtils;
import com.herongtech.console.cache.DictCache;
import com.herongtech.console.core.util.ReflectionUtils;
import com.herongtech.console.domain.bean.Dict;


public class TokenTag extends TagSupport{
	/**
	 * 版本号
	 */
	public static final String HERONGTECH_VERSION="@system 票据管理平台 @version 2.0.0.1 @lastModiDate 20160615 @describe ";

	
	private static final long serialVersionUID = 1;
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
    public int doEndTag() throws JspException {
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
    	name = StringUtils.isEmpty(name) ? TokenHelper.DEFAULT_TOKEN_NAME : name;
		
    	sb.append("<input type='hidden' name='");
		sb.append(name);
		sb.append("' value='");
		sb.append(TokenHelper.setToken(name));
		sb.append("'/>");

    	sb.append("<input type='hidden' name='");
    	sb.append(TokenHelper.TOKEN_NAME_FIELD);
    	sb.append("' value='");
    	sb.append(name);
    	sb.append("'/>");
    	
		return sb.toString();
	}
}
