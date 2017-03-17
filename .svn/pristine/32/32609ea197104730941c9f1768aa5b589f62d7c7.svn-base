/********************************************
 * 文件名称: MenuController.java
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
package com.herongtech.console.web.syscontroller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.herongtech.console.core.common.json.AjaxJson;
import com.herongtech.console.core.common.json.Page;
import com.herongtech.console.core.constant.IDict;
import com.herongtech.console.core.controller.BaseController;
import com.herongtech.console.core.util.ResourceUtil;
import com.herongtech.console.domain.bean.Menu;
import com.herongtech.console.domain.bean.User;
import com.herongtech.console.domain.bean.UserLoginfo;
import com.herongtech.console.service.ServiceFactory;
import com.herongtech.console.service.interfaces.IMenuService;
import com.herongtech.db.impl.session.DBFactory;
import com.herongtech.db.interfaces.IDB;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.log.CommonLog;
import com.herongtech.sysconstant.ISysErrorNo;
/** 
 * 类名称：MenuController
 * 创建时间：2014年7月1日
 * @version
 */
@Scope("prototype")
@Controller
@RequestMapping("/menuController")
public class MenuController extends BaseController {
	
	public static final String HERONGTECH_VERSION="@system 票据管理平台 @version 2.0.0.1 @lastModiDate @describe ";
	//取菜单处理类
	private IMenuService menuService = ServiceFactory.getMenuService();
	/**
	 * 获取当前菜单的所有子菜单
	 * @param menuId
	 * @param response
	 */
	@RequestMapping(params="method=getSubMenu")
	@ResponseBody
	public AjaxJson getSubMenu(HttpServletRequest request,
			HttpServletResponse response) throws BizAppException{
		
		AjaxJson retJson = new AjaxJson();
		
		User user = ResourceUtil.getSessionUser();
		
        String menuCode = request.getParameter("menuCode");
		
        //生成父菜单下的json字符串
        String menuJson = menuService.getMenuJson(user, menuCode);
        
        retJson.setObj(menuJson); 
        
        return retJson;
			
	}
	/**
	 * 列表
	 */
	@RequestMapping(params = "method=list")
	public ModelAndView list(Menu menu,Page page) throws BizAppException {

		ModelAndView mv = new ModelAndView();
		List<Menu> resultList = new ArrayList<Menu>();
		List<Menu> list = null;
		try {
			page.setShowCount(1000);
			list = menuService.getMenuList(menu,page);
			String parentMenuCode = "root";
			if( list!=null && list.size() > 0){
				parentMenuCode = list.get(0).getParentMenuCode();
				mv.addObject("level", list.get(0).getMenuLevel());
			}
			sortList(resultList, list,parentMenuCode,true);
		} catch (BizAppException e){
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "查询菜单列表失败");
		}
		mv.setViewName("system/menu/menu_list");
		mv.addObject("resultList", resultList);
		mv.addObject("menu", menu);
		return mv;
	}
	public static void sortList(List<Menu> list, List<Menu> sourcelist, String parentId, boolean cascade){
		for (int i=0; i<sourcelist.size(); i++){
			Menu e = sourcelist.get(i);
			if (e.getParentMenuCode()!=null && e.getParentMenuCode().equals(parentId)){
				list.add(e);
				if (cascade){
					// 判断是否还有子节点, 有则继续获取子节点
					for (int j=0; j<sourcelist.size(); j++){
						Menu child = sourcelist.get(j);
						if (child.getParentMenuCode()!=null && child.getParentMenuCode().equals(e.getMenuCode())){
							sortList(list, sourcelist, e.getMenuCode(), true);
							break;
						}
					}
				}
			}
		}
	}

	/**
	 * 保存
	 */
	@RequestMapping(params = "method=save")
	public ModelAndView save(Menu menu, String isedit) throws BizAppException {
		ModelAndView mv = new ModelAndView();
		IDB session = DBFactory.getDB(); // 获取数据库连接
		try {
			if(StringUtils.isNotBlank(menu.getParentMenuCode())){
				session.beginTransaction();
				if (isedit.equals("1")) { // 编辑操作
					menu.setMenuClass("icon-"+menu.getMenuIcon());
					menuService.modifyMenu(menu);
				} else {
					if(StringUtils.isBlank(menu.getTreeIdx())){
						menu.setTreeIdx(getMaxTreeIdx(menu.getParentMenuCode()));
						Menu parent = menuService.getMenu(menu.getParentMenuCode());
						menu.setMenuLevel((Integer.valueOf(parent.getMenuLevel())+1)+"");
					}
					List<Menu> childMenuList=menuService.getMenuListByParentMenuCode(menu.getParentMenuCode());
					if(childMenuList.isEmpty()){
						if(IDict.K_MENU_LEVEL.K_MENULEVEL_ONE.equals(menu.getMenuLevel())){
							menu.setMenuCode("01");					
						}else{
							menu.setMenuCode(menu.getParentMenuCode()+"01");
						}
					}else{
						menu.setMenuCode("0"+(Integer.parseInt(childMenuList.get(0).getMenuCode())+1));
					}
					menu.setMenuClass("icon-"+menu.getMenuIcon());
					menuService.addMenu(menu); // 插入
				}
				session.endTransaction();
				mv.addObject("msg", "success");
				mv.setViewName("save_result");
			}
		} catch (SQLException e) {
			try {
				session.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			CommonLog.getCommonLogCache().errorMessage("插入记录tMenu失败");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "插入记录失败");
		}
		return mv;
	}

	/**
	 * 去新增页面
	 */
	@RequestMapping(params = "method=toAdd")
	public ModelAndView toAdd(String parentMenuCode) throws BizAppException {
		ModelAndView mv = new ModelAndView();
		IDB session = DBFactory.getDB(); // 获取数据库连接
		try {
			session.beginTransaction();
			Menu pMenu = menuService.getMenu(parentMenuCode);
			mv.addObject("edit", "0");
			if( pMenu != null ){
				Menu menu = new Menu();
				menu.setParentMenuCode(parentMenuCode);
				menu.setParentName(pMenu.getMenuName());
				menu.setTreeIdx(getMaxTreeIdx(pMenu.getMenuCode()));
				menu.setMenuLevel((Integer.valueOf(pMenu.getMenuLevel())+1)+"");
				mv.addObject("menu", menu);
				mv.addObject("edit", "1");
			}
			session.endTransaction();
		} catch (Exception e) {
			try {
				session.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			CommonLog.getCommonLogCache().errorMessage("插入记录tMenu失败");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "插入记录失败");
		}
		mv.setViewName("system/menu/menu_edit");
		mv.addObject("isedit", "0");
		return mv;
	}

	/**
	 * 判断编码是否存在
	 * 
	 * @throws BizAppException
	 */
	@RequestMapping(params = "method=checkExists")
	@ResponseBody
	public AjaxJson checkExists(String menuCode) throws BizAppException {
		AjaxJson retJson = new AjaxJson();
		try {
			int count = menuService.checkExists(menuCode);
			if (count >= 1) {
				retJson.setSuccess(false);
			}
		} catch (BizAppException e) {
			CommonLog.getCommonLogCache().errorMessage("数据库错误   [验证角色编码失败]");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "查询数据库失败");
		}
		return retJson;
	}

	/**
	 * 去编辑页面
	 */
	@RequestMapping(params = "method=toEdit")
	public ModelAndView toEdit(String menuCode) throws BizAppException {
		ModelAndView mv = new ModelAndView();
		Menu menu = null;
		try {
			menu = menuService.getMenu(menuCode);
			mv.addObject("parentName", "1");
			if(menuService.getMenu(menu.getParentMenuCode())!=null){
				menu.setParentName(menuService.getMenu(menu.getParentMenuCode()).getMenuName());
				mv.addObject("parentName", "0");
			}
		} catch (Exception e) {
			CommonLog.getCommonLogCache().errorMessage("数据库错误   [查询角色失败]");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "查询数据库失败");
		}
		mv.setViewName("system/menu/menu_edit");
		mv.addObject("menu", menu);
		mv.addObject("edit", "1");
		mv.addObject("isedit", "1");
		return mv;
	}

	/**
	 * 删除用户
	 */
	@RequestMapping(params = "method=del")
	@ResponseBody
	public AjaxJson del(String menuCodes) throws BizAppException {
		AjaxJson retJson = new AjaxJson();

		IDB session = DBFactory.getDB(); // 获取数据库连接

		try {
			session.beginTransaction();

			menuService.deleteMenu(menuCodes);

			session.endTransaction();

		} catch (Exception e) {
			try {
				session.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			retJson.setSuccess(false);
			retJson.setMsg(e.getMessage());
		}
		return retJson;
	}
	private static final char[] SIGNS;		//做为标记的字符集
	private static final int SIGN_LENGTH = 2;		//默认标记的长度为2
	//private static final int FIELD_LENGTH = 10;     //默认标记字段的长度
	static{
		int j=0;
		SIGNS = new char[62];					//62个,应该够用了
		for (int i=48;i<58;i++)					//从 0 到 9
			SIGNS[j++]=(char)i;
		for (int i=65;i<91;i++)					//从 A 到 Z
			SIGNS[j++]=(char)i;
		for (int i=97;i<123;i++)				//从 a 到 z
			SIGNS[j++]=(char)i;
	}
	private String getMaxTreeIdx(String menuCode) throws BizAppException{
		String rs = null;
		Menu menu =	menuService.getMenu(menuCode);
		if (menu == null)
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "父菜单不存在！");
		String treeIdx = menu.getTreeIdx().trim();
		String addBit = "";
		for (int i=0;i<SIGN_LENGTH;i++)
			addBit += "_";						//"_"在SQL语句代表单个任意字符(与"%"类似,但范围仅限一个字符)
		//该父类菜单下子类菜单的最大索引
		String maxIdx = menuService.getMaxTreeIdxForParentIdx(treeIdx+addBit);
		char[] nextIdx = null;
		if (StringUtils.isNotEmpty(maxIdx) && maxIdx.length() == treeIdx.length()+addBit.length()){
			nextIdx = maxIdx.substring(treeIdx.length()).toCharArray();
			int carry = 1;		//进位
			for (int i=nextIdx.length-1;i>=0;i--){		//从后向前推
				for (int j=0;j<SIGNS.length;j++){
					if (nextIdx[i] == SIGNS[j]){
						if (j == SIGNS.length-1){			//如果是最后一位则需要进位
							if (i == 0)						//不过如果i=0就说明部门应该达到上限了
								throw new BizAppException(ISysErrorNo.ERR_DBERR,"该父类下的菜单数量已达上限!!!");
							nextIdx[i] = SIGNS[0];
							carry = 1;
						}else{								//一般情况只要进一位就可以了
							nextIdx[i] = SIGNS[j+carry];
							carry = 0;
						}
						break;
					}
				}
			}
		}else{
			nextIdx = new char[SIGN_LENGTH];
			for (int i=0;i<SIGN_LENGTH;i++)				//生成指定长度的编号
				nextIdx[i] = SIGNS[0];				//每一位置为0
		}
		rs = treeIdx+new String(nextIdx);
		return rs;
	}
	
	/**
	 * 列表
	 */
	@RequestMapping(params = "method=toMenuTree")
	public ModelAndView toMenuTree(Menu menu,Page page) throws BizAppException {

		ModelAndView mv = new ModelAndView();
		List<Menu> resultList = new ArrayList<Menu>();
		List<Menu> list = null;
		try {
			page.setShowCount(30);
			list = menuService.getMenuList(menu,page);
			sortList(resultList, list, "root",true);
		} catch (BizAppException e){
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "查询菜单列表失败");
		}
		mv.setViewName("system/tools/treeselect");
		mv.addObject("resultList", JSONArray.fromObject(resultList).toString());
		return mv;
	}
	
	
	/**
	 * 根据前台所传条件查询对应菜单集合
	 * @param likestr
	 * @return电
	 * @throws BizAppException
	 */
	@RequestMapping(params="method=selectmenu")
	@ResponseBody
	public AjaxJson getMenuList(String likestr) throws BizAppException{
		AjaxJson  aj = new AjaxJson();
		if("%".equals(likestr)){
			aj.setSuccess(false);
			aj.setMsg("查询条件错误");
		}
//		UserLoginfo user = ResourceUtil.getSessionLoginfo();
//		String rolecode = user.getUserRoleCode();
//		String idArr[]=rolecode.split(",");
//		String rolecodes =null;
//		for(int i=0; i<idArr.length ; i++){
//			rolecodes+="'"+idArr[i]+"'"+",";
//		}
//		rolecodes= rolecodes.substring(0,rolecodes.length()-1);
		UserLoginfo user = ResourceUtil.getSessionLoginfo();
		String rolecode = user.getUserRoleCode();
		String rolecodes=rolecode.substring(0,rolecode.length()-1);
		List<Menu> menulist = new ArrayList<Menu>();
		try {
			menulist = menuService.getMenuList(rolecodes,likestr);
			aj.setObj(menulist);
			aj.setSuccess(true);
			aj.setMsg("aaaa");
		} catch (BizAppException e) {
			CommonLog.getCommonLogCache().errorMessage("查询数据库失败");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "查询数据库失败");
		}
		return aj;
	}
	
	
	
}


