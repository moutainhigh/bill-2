/********************************************
 * 文件名称: DictController.java
 * 系统名称: 票据管理平台V2.0
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: 2.0.0.1
 * 开发人员: fqz
 * 开发时间: 2016-7-8 下午01:48:52
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.herongtech.commons.tools.HsSqlString;
import com.herongtech.commons.tools.StringUtil;
import com.herongtech.commons.tools.StringUtils;
import com.herongtech.console.core.common.json.AjaxJson;
import com.herongtech.console.core.common.json.Page;
import com.herongtech.console.core.controller.BaseController;
import com.herongtech.console.domain.bean.Dict;
import com.herongtech.console.service.ServiceFactory;
import com.herongtech.console.service.interfaces.IDictService;
import com.herongtech.db.impl.session.DBFactory;
import com.herongtech.db.interfaces.IDB;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.log.CommonLog;
import com.herongtech.sysconstant.ISysErrorNo;

/** 
 * 类名称：DictController
 * 创建人：fqz
 * 创建时间：2016年7月8日
 * @version
 */
@Scope("prototype")
@Controller
@RequestMapping("/dictController")
public class DictController extends BaseController {
	
	public static final String HERONGTECH_VERSION="@system 票据管理平台 @version 2.0.0.1 @lastModiDate @describe ";
	
	/**
	 * 列表
	 */
	@RequestMapping(params="method=list")
	public ModelAndView list(Page page, HttpServletRequest request,
			HttpServletResponse response)throws Exception{
		
		ModelAndView mv = new ModelAndView();
		
		Map<String, String> mapField = new HashMap<String,String>();
		
		String key_no = request.getParameter("key_no");
		String item_code = request.getParameter("item_code");
		String param_value = request.getParameter("param_value");
		String order_by = request.getParameter("order_by");
		page.activeCommand();
		HsSqlString dbSql = new HsSqlString("vdict");
		if (!StringUtils.isEmpty(key_no)){
			dbSql.setWhere("key_no like '%" + key_no + "%'");
		}
		
		if (!StringUtils.isEmpty(item_code)){
			dbSql.setWhere("item_code like '%" + item_code + "%'");
		}
//		
//		if (!StringUtils.isEmpty(param_value)){
//			dbSql.setWhere("param_value like '%" + param_value + "%'");
//		}
		if (!StringUtils.isEmpty(order_by)){
			dbSql.setOrder(order_by);
		}
		//分页开始位置
		int startIndex = page.getCurrentResult();
		
		List<Dict> resultList = new ArrayList<Dict>();
    	try{
    		
    		IDB session = DBFactory.getDB(); // 获取数据库连接
    		resultList = session.getObjectListByListForPage(dbSql.getSqlString(), Dict.class, startIndex, page.getShowCount(), dbSql.getParamList());
        	
        	// 获得并返回本次查询的总条数
        	int count = 0;
        	count = session.accountByList(dbSql.getTotCountSqlString(), dbSql.getParamList());
        	page.setTotalResult(count);
        	
		} catch (SQLException e){
			//e.printStackTrace();	
			CommonLog.getCommonLogCache().errorMessage("数据库错误   [" + dbSql.getSqlString() + "]");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "查询数据字典列表失败");
		}
    	
    	mapField.put("key_no", key_no);
    	mapField.put("item_code", item_code);
//    	mapField.put("param_value", param_value);
    	mapField.put("order_by", order_by);		
		mv.setViewName("system/dict/dict_list");
		mv.addObject("resultList", resultList);
		mv.addObject("page", page);
		mv.addObject("mapField", mapField);
		
		return mv;
		
	}
	
	/**
	 * 保存
	 */
	@RequestMapping(params="method=save")
	public ModelAndView save(HttpServletRequest request,
			HttpServletResponse response) throws BizAppException {
		ModelAndView mv = new ModelAndView();
		
		//取用户处理类
        IDictService dictService = ServiceFactory.getDictService();
		
		Dict dict = new Dict();
		String key_no = request.getParameter("key_no");
		String key_name = request.getParameter("key_name");
		String item_code = request.getParameter("item_code");
		String item_value = request.getParameter("item_value");
		String kernal_flag = request.getParameter("kernal_flag");
		String belong_type = request.getParameter("belong_type");
		
		String isedit = request.getParameter("isedit");
		
		dict.setKeyNo(key_no);
		dict.setKeyName(key_name);
		dict.setItemCode(item_code);
		dict.setItemValue(item_value);
		dict.setKernalFlag(kernal_flag);
		dict.setBelongType(belong_type);
		
		IDB session = DBFactory.getDB(); // 获取数据库连接
		try {
			if (isedit.equals("1")){   //编辑操作
				session.beginTransaction();
				dictService.modifyDictGroup(dict);
				dictService.modifyDictItem(dict);
				session.endTransaction();
			}else{
				session.beginTransaction();
				dictService.addDictGroup(dict);  //插入
				dictService.addDictItem(dict);  //插入
				session.endTransaction();
			}
			
		} catch (SQLException e) {
			try {
				session.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			CommonLog.getCommonLogCache().errorMessage("插入记录失败");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "插入记录失败");
		}
		
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
	
	/**
	 * 去新增页面
	 * @throws BizAppException 
	 */
	@RequestMapping(params="method=toAdd")
	public ModelAndView toAdd(Page page,String str) throws BizAppException{
		ModelAndView mv = new ModelAndView();
		if(!StringUtil.isBlank(str)){
			String[] strTemp = str.split(",");
			String key_no = strTemp[0];
			String item_code = strTemp[1];
			String sql = "select * from vdict where key_no = ? and item_code=?";
			Dict dict = new Dict();
			try{
				
				IDB session = DBFactory.getDB(); // 获取数据库连接
				dict = session.getObject(sql, Dict.class, key_no,item_code);
				dict.setItemCode(null);
				dict.setItemValue(null);
				mv.addObject("dict", dict);
			} catch (SQLException e){
				//e.printStackTrace();	
				CommonLog.getCommonLogCache().errorMessage("数据库错误   [" + sql + "]");
				throw new BizAppException(ISysErrorNo.ERR_DBERR, "查询数据库失败");
			}
		}
    	
		mv.setViewName("system/dict/dict_edit");
		mv.addObject("isedit", "0");
		return mv;
	}
	
	/**
	 * 判断编码是否存在
	 * @throws BizAppException 
	 */
	@RequestMapping(params="method=checkExists")
	@ResponseBody
	public AjaxJson checkExists(HttpServletRequest request,
			HttpServletResponse response) throws BizAppException{
		
		AjaxJson retJson = new AjaxJson();
		
		String key_no = request.getParameter("key_no");
		String item_code = request.getParameter("item_code");
		
		String sql = "select count(*) from vdict where key_no = ? and item_code=?";
		
    	try{
    		
    		IDB session = DBFactory.getDB(); // 获取数据库连接
    		int count = session.account(sql, key_no,item_code);
        	
    		if (count >= 1){
    			retJson.setSuccess(false);
    		}
        	
		} catch (SQLException e){
			//e.printStackTrace();	
			CommonLog.getCommonLogCache().errorMessage("数据库错误   [" + sql + "]");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "查询数据库失败");
		}
		
		return retJson;
	}
	
	
	
	/**
	 * 去编辑页面
	 */
	@RequestMapping(params="method=toEdit")
	public ModelAndView toEdit(HttpServletRequest request,
			HttpServletResponse response) throws BizAppException{
		ModelAndView mv = new ModelAndView();
		
		String str = request.getParameter("str");
		String[] strTemp = str.split(",");
		String key_no = strTemp[0];
		String item_code = strTemp[1];
		String sql = "select * from vdict where key_no = ? and item_code=?";
		Dict dict = new Dict();
    	try{
    		
    		IDB session = DBFactory.getDB(); // 获取数据库连接
    		dict = session.getObject(sql, Dict.class, key_no,item_code);
        	
		} catch (SQLException e){
			//e.printStackTrace();	
			CommonLog.getCommonLogCache().errorMessage("数据库错误   [" + sql + "]");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "查询数据库失败");
		}
    	
		mv.setViewName("system/dict/dict_edit");
		mv.addObject("dict", dict);
		mv.addObject("isedit", "1");
		
		return mv;
	}
	/**
	 * 将页面传过来的keyNo_itemCode的键值对，分别拆成存放keyNo和存放itemCode的数组
	 * @param strs
	 * @return
	 */
	public static Object[] splitStr(String strs){
		String[] strArr = StringUtils.split(strs);
		String[] keyArr = new String[strArr.length];
		String[] valueArr = new String[strArr.length];
		String[] strTemp = null;
		for(int i=0;i<strArr.length;i++){
			strTemp = strArr[i].split("_");
			keyArr[i] = strTemp[0];
			valueArr[i] = strTemp[1];
		}
		Object[] objArr = {keyArr,valueArr};
		return objArr;
	}
	/**
	 * 删除数据字典
	 */
	@RequestMapping(params="method=del")
	@ResponseBody
	public AjaxJson del(HttpServletRequest request,
			HttpServletResponse response) throws BizAppException{
		
		AjaxJson retJson = new AjaxJson();
		IDB session = DBFactory.getDB(); // 获取数据库连接
		
		IDictService dictService = ServiceFactory.getDictService();
		String strs = request.getParameter("strs");
		
		Object[] objArr = splitStr(strs);
		
		String[] keyArr = (String[])objArr[0];
		String[] valueArr = (String[])objArr[1];
		try{
			session.beginTransaction();
			
			for(int i=0;i<keyArr.length;i++){
//			System.out.println(keyArr[i]+":"+valueArr[i]);
				dictService.deleteDictGroup(keyArr[i]);
				dictService.deleteDictItem(keyArr[i], valueArr[i]);
				
			}
    		session.endTransaction();
    		
		} catch (SQLException e){
			//e.printStackTrace();	
			try {
				session.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			CommonLog.getCommonLogCache().errorMessage("删除数据失败");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "数据库删除失败");
		}
		
		return retJson;
	}
	public static void main(String[] args) {
		Object[] objArr = DictController.splitStr("YN_0,YN_1");
		String[] keyArr = (String[])objArr[0];
		String[] valueArr = (String[])objArr[1];
		for(int i=0;i<keyArr.length;i++){
			System.out.println(keyArr[i]+":"+valueArr[i]);
		}
		
	}
}
