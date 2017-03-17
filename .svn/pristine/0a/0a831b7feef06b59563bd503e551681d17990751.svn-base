package com.herongtech.console.web.syscontroller;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.herongtech.console.core.util.ResourceUtil;
import com.herongtech.console.domain.bean.UserLoginfo;
import com.herongtech.console.domain.common.bean.ExportInfoBean;
import com.herongtech.console.domain.common.bean.SearchConditionBean;
import com.herongtech.console.service.ServiceFactory;
import com.herongtech.console.service.common.export.IExportService;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.log.CommonLog;

/**
 * 功能描述：导出控制类
 * @author songzx
 *
 */
@Scope("prototype")
@Controller
@RequestMapping("/exportController")
public class ExportController {
	/**
	 * 导出时查看导出列信息
	 * @param tableName 指对应业务的表或者视图
	 * @return
	 * @throws BizAppException 
	 */
	@RequestMapping(params = "method=getExportInfo")
	public ModelAndView getExportInfo(String tableName,String action,String formName,String frameId) throws BizAppException{
		ModelAndView mv = new ModelAndView("busi/common/export_info");
		IExportService exService = ServiceFactory.getExportService();
		try {
			Map<String,List<ExportInfoBean>> map =  exService.getPlanExportInfo(tableName);
			mv.addObject("frameId",frameId);
			mv.addObject("leftList",map.get("leftList"));
			mv.addObject("rightList",map.get("rightList"));
			mv.addObject("tableName",tableName);
			mv.addObject("formName",formName);
			mv.addObject("action",action);
		} catch (Exception e) {
			e.printStackTrace();
			CommonLog.getCommonLogCache().errorMessage("查询导出信息失败");
			throw new BizAppException("查询导出信息失败，"+e.getMessage());
		}
		return mv;
	}
	
	/**
	 * 去余额详情页面
	 * @param xxxmxId 指对应业务的billInfo表的ID，例如贴现DiscBillInfo表的主键discmxId
	 * @return
	 * @throws BizAppException 
	 */
	@RequestMapping(params = "method=commonExport")
	public void commonExport(String leftList,String tableName,String rightList,SearchConditionBean bean,HttpServletResponse response,HttpServletRequest request) throws BizAppException{
		IExportService exService = ServiceFactory.getExportService();
		
		try {
			//保存导出格式信息
			updatexportInfo(tableName, leftList, rightList);
			exService.exportInfo(tableName, bean, response, request);
		} catch (Throwable e) {
			e.printStackTrace();
			CommonLog.getCommonLogCache().errorMessage("查询失败");
			throw new BizAppException("查询失败,"+e.getMessage());
		}
	}
	
	/**
	 * 保存导出的列结构
	 * @param tableName 业务名称
	 * @param leftList 不需要导出的
	 * @param rightList 导出的
	 * @throws SQLException
	 * @throws BizAppException
	 */
	public static void updatexportInfo(String tableName,String leftList,String rightList) throws SQLException,BizAppException{
		IExportService exService = ServiceFactory.getExportService();
		UserLoginfo user = ResourceUtil.getSessionLoginfo();
		if(StringUtils.isNotEmpty(leftList)){
			String[] leftArr = leftList.split(",");
			for(int i = 0 ; i < leftArr.length ; i++){
				exService.updatexportInfo("0", user.getUserId(), i+"", tableName, leftArr[i]);
			}
		}
		if(StringUtils.isNotEmpty(rightList)){
			String[] rightArr = rightList.split(",");
			for(int i = 0 ; i < rightArr.length ; i++){
				exService.updatexportInfo("1", user.getUserId(), i+"", tableName, rightArr[i]);
			}
		}else{
			throw new BizAppException("导出字段不可以为空！");
		}
	}
}
