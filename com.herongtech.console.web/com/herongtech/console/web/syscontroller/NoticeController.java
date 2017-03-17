/********************************************
 * 文件名称: NoticeController.java
 * 系统名称: 票据管理平台V2.0
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: 2.0.0.1
 * 开发人员: fqq
 * 开发时间: 2016-9-22 下午01:48:52
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期    修改人员    修改说明
 *******************************************/
package com.herongtech.console.web.syscontroller;

import java.sql.SQLException;
import java.text.ParseException;
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
import com.herongtech.commons.tools.StringUtils;
import com.herongtech.console.core.common.json.AjaxJson;
import com.herongtech.console.core.common.json.Page;
import com.herongtech.console.core.controller.BaseController;
import com.herongtech.console.domain.bean.Branch;
import com.herongtech.console.domain.bean.CustInfo;
import com.herongtech.console.domain.bean.CustManage;
import com.herongtech.console.domain.bean.Notice;
import com.herongtech.console.service.ServiceFactory;
import com.herongtech.console.service.interfaces.IBranchService;
import com.herongtech.console.service.interfaces.ICustInfoService;
import com.herongtech.console.service.interfaces.ICustManageService;
import com.herongtech.console.service.interfaces.INoticeService;
import com.herongtech.db.impl.session.DBFactory;
import com.herongtech.db.interfaces.IDB;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.log.CommonLog;
import com.herongtech.sysconstant.ISysErrorNo;

/** 
 * 类名称：NoticeController
 * 创建人：fqq
 * 创建时间：2016年9月22日
 * @version
 */
@Scope("prototype")
@Controller
@RequestMapping("/noticeController")
public class NoticeController extends BaseController {
	
	public static final String HERONGTECH_VERSION="@system 票据管理平台 @version 2.0.0.1 @lastModiDate @describe ";
	/**
	 * 列表
	 */
	@RequestMapping(params="method=list")
	public ModelAndView list(Page page, Notice notice)throws Exception{
		page.activeCommand();
		ModelAndView mv = new ModelAndView();
		
		Map<String, String> mapField = new HashMap<String,String>();
		INoticeService noticeService=ServiceFactory.getNoticeService();
		List<Notice> resultList = new ArrayList<Notice>();
    	try{
    		resultList=noticeService.getNotice(page, notice);
		} catch (SQLException e){
			//e.printStackTrace();	
			CommonLog.getCommonLogCache().errorMessage("数据库错误");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "查询机构信息失败");
		}
		mv.setViewName("system/notice/notice_list");
		mv.addObject("resultList", resultList);
		mv.addObject("page", page);
		mv.addObject("mapField", mapField);
		
		return mv;
	}
	/**
	 * 保存
	 */
	@RequestMapping(params="method=saveNotice")
	public ModelAndView save(Notice notice,String isedit) throws BizAppException {
		ModelAndView mv = new ModelAndView();
		
		//取公告信息处理类
		INoticeService noticeService=ServiceFactory.getNoticeService();
		IDB session = DBFactory.getDB(); // 获取数据库连接
		try {
			session.beginTransaction();
			if (isedit.equals("1")){   //编辑操作
				noticeService.modifyNotice(notice);
			}else{
				noticeService.addNotice(notice);  //插入
			}
			session.endTransaction();
		} catch (SQLException e) {
			try {
				session.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			CommonLog.getCommonLogCache().errorMessage("操作数据库失败");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "编辑记录失败");
		}
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
	
	/**
	 * 去新增页面
	 */
	@RequestMapping(params="method=toAdd")
	public ModelAndView toAdd(Page page){
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("system/notice/notice_edit");
		mv.addObject("isedit", "0");
		return mv;
	}
	
	/**
	 * 查看公告内容
	 * @param page
	 * @return
	 */
	@RequestMapping(params="method=toReadNotice")
	public ModelAndView toReadNotice(String noticeNo)throws BizAppException{
		ModelAndView mv = new ModelAndView();
		INoticeService noticeService=ServiceFactory.getNoticeService();
		Notice notice=new Notice();
    	notice=noticeService.getNotice(noticeNo);
		mv.addObject("notice", notice);
		mv.setViewName("system/notice/notice_read");
		return mv;
	}
	/**
	 * 判断编码是否存在
	 * @throws BizAppException 
	 */
	@RequestMapping(params="method=checkExists")
	@ResponseBody
	public AjaxJson checkExists(String noticeNo) throws BizAppException{
		
		AjaxJson retJson = new AjaxJson();
		String sql = "select count(*) from tnotice where notice_no = ?";
    	try{
    		
    		IDB session = DBFactory.getDB(); // 获取数据库连接
    		int count = session.account(sql, noticeNo);
        	
    		if (count >= 1){
    			retJson.setSuccess(false);
    		}
		} catch (SQLException e){
			CommonLog.getCommonLogCache().errorMessage("数据库错误   [" + sql + "]");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "查询数据库失败");
		}
		
		return retJson;
	}
	
	
	
	/**
	 * 去编辑页面
	 */
	@RequestMapping(params="method=toEdit")
	public ModelAndView toEdit(String noticeNo) throws BizAppException, SQLException{
		ModelAndView mv = new ModelAndView();
		INoticeService noticeService=ServiceFactory.getNoticeService();
		Notice notice=new Notice();
    	notice = noticeService.getNotice(noticeNo);
		mv.setViewName("system/notice/notice_edit");
		mv.addObject("notice", notice);
		mv.addObject("isedit", "1");
		
		return mv;
	}
	
	/**
	 * 删除用户
	 */
	@RequestMapping(params="method=del")
	@ResponseBody
	public AjaxJson del(String noticeNos) throws BizAppException{
		INoticeService noticeService=ServiceFactory.getNoticeService();
		AjaxJson retJson = new AjaxJson();
		IDB session = DBFactory.getDB(); // 获取数据库连接
		try{
			session.beginTransaction();
			noticeService.delNotice(noticeNos);
    		session.endTransaction();
    		
		} catch (SQLException e){
			try {
				session.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			CommonLog.getCommonLogCache().errorMessage("数据库错误");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "数据库删除失败");
		}
		return retJson;
	}
}
