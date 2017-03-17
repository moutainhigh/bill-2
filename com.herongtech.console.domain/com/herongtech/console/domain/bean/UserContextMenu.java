/********************************************
* 文件名称: UserContextMenu.java
* 系统名称: 合融基础技术平台V2.0
* 模块名称:
* 软件版权: 北京合融科技有限公司
* 功能说明: 
* 系统版本: 2.0.0.1
* 开发人员: 技术平台组
* 开发时间:  
* 审核人员:
* 相关文档:
* 修改记录: 修改日期    修改人员    修改说明
*********************************************/
package com.herongtech.console.domain.bean;

import java.lang.*;
import java.util.*;
import java.math.*;

public class UserContextMenu{
	private String id = " ";	//主键
	private String userId = " ";	//用户id
	private String menuName = " ";	//菜单名称
	private String menuCode = " ";	//菜单编号
	private String menuUrl = " ";	//菜单url
	private String contextMenuSrc = " ";	//图标路径
	private String createTime = " ";	//创建时间
	public String getId(){
		return id;
	}
	public void setId(String id){
		this.id = id;
	}

	public String getUserId(){
		return userId;
	}
	public void setUserId(String userId){
		this.userId = userId;
	}

	public String getMenuName(){
		return menuName;
	}
	public void setMenuName(String menuName){
		this.menuName = menuName;
	}

	public String getMenuCode(){
		return menuCode;
	}
	public void setMenuCode(String menuCode){
		this.menuCode = menuCode;
	}

	public String getMenuUrl(){
		return menuUrl;
	}
	public void setMenuUrl(String menuUrl){
		this.menuUrl = menuUrl;
	}

	public String getContextMenuSrc(){
		return contextMenuSrc;
	}
	public void setContextMenuSrc(String contextMenuSrc){
		this.contextMenuSrc = contextMenuSrc;
	}

	public String getCreateTime(){
		return createTime;
	}
	public void setCreateTime(String createTime){
		this.createTime = createTime;
	}


}
