/********************************************
 * 文件名称: ClientManager.java
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

package com.herongtech.console.core.system.manager;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import com.herongtech.commons.tools.StringUtils;
import com.herongtech.console.core.util.ContextHolderUtils;
import com.herongtech.console.core.util.EhcacheUtil;

/**
 * 对在线用户的管理
 * @author
 * @date 2013-9-28
 * @version 1.0
 */
public class ClientManager {
   	/**
	 * 版本号
	 */
	public static final String HERONGTECH_VERSION="@system 票据管理平台 @version 2.0.0.1 @lastModiDate 20160615 @describe ";
	
	private final String CACHENAME ="eternalCache";
	private final String OnlineClientsKey ="onLineClients";

	private static ClientManager instance = new ClientManager();
	
	private ClientManager(){
		
	}
	
	public static ClientManager getInstance(){
		return instance;
	}
	
	/**
	 * 向ehcache缓存中增加Client对象
	 * @author
	 * */
	private boolean addClientToCachedMap(String sessionId,Client client ){
		HashMap<String, Client> onLineClients ;
		if(EhcacheUtil.get(CACHENAME, OnlineClientsKey)==null){
			onLineClients = new HashMap<String, Client>();
		}
		else{
			onLineClients =(HashMap<String, Client>) EhcacheUtil.get(CACHENAME,OnlineClientsKey);
		}
		onLineClients.put(sessionId, client);
		EhcacheUtil.put(CACHENAME,OnlineClientsKey, onLineClients);
		return true;
	}
	
	/**
	 * 从缓存中的Client集合中删除 Client对象
	 * */
	private boolean removeClientFromCachedMap(String sessionId){
		HashMap<String, Client> onLineClients ;
		if(EhcacheUtil.get(CACHENAME, OnlineClientsKey)!=null){
			onLineClients =(HashMap<String, Client>) EhcacheUtil.get(CACHENAME, OnlineClientsKey);
			onLineClients.remove(sessionId);
			EhcacheUtil.put(CACHENAME, OnlineClientsKey, onLineClients);
			return true;
		}
		else{
			return false;
		}
	}
	
	/**
	 * 用户登录，向session中增加用户信息
	 * @param sessionId
	 * @param client
	 */
	public void addclient(String sessionId,Client client){
		ContextHolderUtils.getSession().setAttribute(sessionId, client);
		
		if(client !=null){
			Client ret = new Client();
			ret.setIp(client.getIp());
			ret.setLogindatetime(client.getLogindatetime());
			ret.setUserloginfor(client.getUserloginfor());
			ret.setUser(client.getUser());
			addClientToCachedMap(sessionId,ret);
		}
	}
	/**
	 * 用户退出登录 从Session中删除用户信息
	 * sessionId
	 */
	public void removeClient(String sessionId){
		ContextHolderUtils.getSession().removeAttribute(sessionId);
		removeClientFromCachedMap(sessionId);
	}
	/**
	 * 根据sessionId 得到Client 对象
	 * @param sessionId
	 */
	public Client getClient(String sessionId){
		if(!StringUtils.isEmpty(sessionId) && ContextHolderUtils.getSession().getAttribute(sessionId) != null){
			return (Client)ContextHolderUtils.getSession().getAttribute(sessionId);
		}
		else{
			return null;
		}
	}
	/**
	 * 得到Client 对象
	 */
	public Client getClient(){
		String sessionId = ContextHolderUtils.getSession().getId();
		if(!StringUtils.isEmpty(sessionId)&&ContextHolderUtils.getSession().getAttribute(sessionId)!=null){
			return (Client)ContextHolderUtils.getSession().getAttribute(sessionId);
		}
		else{
			return null;
		}
	}
	/**
	 * 得到所有在线用户
	 */
	public Collection<Client> getAllClient(){
		if(EhcacheUtil.get(CACHENAME,OnlineClientsKey)!=null){
			HashMap<String, Client> onLineClients = (HashMap<String, Client>) EhcacheUtil.get(CACHENAME,OnlineClientsKey);
			return onLineClients.values();
		}
		else
			return new ArrayList<Client>();
	}
}