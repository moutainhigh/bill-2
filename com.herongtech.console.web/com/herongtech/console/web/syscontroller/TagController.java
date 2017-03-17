/********************************************
 * 文件名称: TagController.java
 * 系统名称: 票据管理平台V2.0
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: 2.0.0.1
 * 开发人员: yanjl
 * 开发时间: 2016-6-15 下午01:48:52
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期    修改人员    修改说明
 *******************************************/
package com.herongtech.console.web.syscontroller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.herongtech.commons.tools.StringUtils;
import com.herongtech.console.core.controller.BaseController;
import com.herongtech.db.impl.session.DBFactory;
import com.herongtech.db.interfaces.IDB;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.sysconstant.ISysErrorNo;

/**
 * 类名称：TagController 创建人：yy 创建时间：2016年7月19日
 * 
 * @version
 */
@Scope("prototype")
@Controller
@RequestMapping("/tagController")
public class TagController extends BaseController {

	public static final String HERONGTECH_VERSION = "@system 票据管理平台 @version 2.0.0.1 @lastModiDate @describe ";

	/**
	 * 转到树页面
	 */
	@RequestMapping(params = "method=treeselect")
	public String treeselect(HttpServletRequest request, Model model) {
		model.addAttribute("url", request.getParameter("url")); // 树结构数据URL
		model.addAttribute("extId", request.getParameter("extId")); // 排除的编号ID
		model.addAttribute("checked", request.getParameter("checked")); // 是否可复选
		model.addAttribute("selectIds", request.getParameter("selectIds")); // 指定默认选中的ID
		model.addAttribute("isAll", request.getParameter("isAll")); // 是否读取全部数据，不进行权限过滤
		model.addAttribute("module", request.getParameter("module")); // 模型类型（0:自定义 1、菜单 2、机构)
		model.addAttribute("subnode", request.getParameter("subnode")); // 子节点CODE-自定义时有效
		model.addAttribute("pnode", request.getParameter("pnode")); // 父节点CODE-自定义时有效
		model.addAttribute("nodename", request.getParameter("nodename")); // 节点名称-自定义时有效
		model.addAttribute("sourcename", request.getParameter("sourcename")); // 表名-自定义时有效
		return "system/admin/tagtreeselect";

	}

	/**
	 * 获取树形层级的JSON数据。
	 * 
	 * @param request
	 *            module 类型（1：菜单；2：机构：3：用户  0：公共）
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(params = "method=treeData")
	public List<Map> treeData(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		// 获取类型
		String module = request.getParameter("module");
		// 获取树形节点
		String subnode= request.getParameter("subnode");
		// 获取树形父节点
		String pnode= request.getParameter("pnode");
		// 获取树形节点名称
		String nodename= request.getParameter("nodename");
		// 获取树形数据表名(目前是单表)
		String sourcename= request.getParameter("sourcename");
		
		// 若类型未传，则抛出异常
		if (StringUtils.isEmpty(module)) {
			throw new BizAppException(
					ISysErrorNo.PARAM_VALIDATE_EXCEPTION_CODE, "不支持的树module类型");
		}

		// 根据类型组织数据库查询语句
		StringBuffer sb = new StringBuffer();
		Map<String, Object> parameters = new HashMap();
		if ("1".equalsIgnoreCase(module)) {// 菜单
			sb.append("select menu_code as id, parent_menu_code as pid, menu_name as name from tmenu");
		}
		
		if ("2".equalsIgnoreCase(module)) {// 机构
			sb.append("select branch_no as id, parent_brch_code as pid, branch_name as name from tbranch");
		}

		if ("0".equalsIgnoreCase(module)) {//公共树
			if (StringUtils.isEmpty(subnode) ||StringUtils.isEmpty(pnode) ||StringUtils.isEmpty(nodename) ||StringUtils.isEmpty(sourcename)) {
				throw new BizAppException(
						ISysErrorNo.PARAM_VALIDATE_EXCEPTION_CODE, "公共树参数不正确");
			}
			sb.append("select ");
		    sb.append(subnode);
		    sb.append(" as id,");
		    sb.append(pnode);
		    sb.append(" as pid,");
		    sb.append(nodename);
		    sb.append(" as name from ");
		    sb.append(sourcename);
		}

		List<Map> mapList = new ArrayList();
		List<Map> retList = new ArrayList();

		IDB dbsession = DBFactory.getDB();

		try {
			mapList = dbsession
					.getResultMapListByMap(sb.toString(), parameters);

			for (int i = 0; i < mapList.size(); i++) {

				Map<String, Object> map = new HashMap();
				map.put("id", mapList.get(i).get("id"));

				map.put("pId", mapList.get(i).get("pid"));

				map.put("name", mapList.get(i).get("name"));

				retList.add(map);
			}

		} catch (SQLException e) {
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "数据库错误 ["
					+ sb.toString() + "]");
		}
		return retList;
	}
	
	/**
	 * 图标选择标签（iconselect.tag）
	 */
	@RequestMapping(params = "method=iconselect")
	public String iconselect(HttpServletRequest request, Model model) {
		model.addAttribute("value", request.getParameter("value"));
		return "system/admin/tagIconselect";
	}
}
