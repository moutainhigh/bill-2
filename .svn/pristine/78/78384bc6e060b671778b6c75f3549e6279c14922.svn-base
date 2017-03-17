/********************************************
 * 文件名称: MenuController.java
 * 系统名称: 基础技术平台V2.0
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: 2.0.0.1
 * 开发人员: songzx
 * 开发时间: 2016-09-20 下午01:48:52
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期    修改人员    修改说明
 *********************************************/
package com.herongtech.console.web.busicontroller.common;

import java.sql.SQLException;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.herongtech.console.core.util.ImportExcel;
import com.herongtech.console.domain.importbean.ImportResultBean;
import com.herongtech.console.service.busiservice.common.IImportDataService;
import com.herongtech.db.impl.session.DBFactory;
import com.herongtech.db.interfaces.IDB;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.log.CommonLog;
import com.herongtech.sysconstant.ISysErrorNo;

/**
 * excel导入处理 Controller
 *	处理模版导入excel数据
 */
@Scope("prototype")
@Controller
@RequestMapping("/excelImportController")
public class ExcelImportController {
	
	/**
	 * 功能描述：处理导入的excel文件
	 * @param request
	 * @return
	 * @throws BizAppException
	 */
	@RequestMapping(params = "method=doImport")
	public ModelAndView doImport(@RequestParam("file") MultipartFile file,String batchId,String billType,String serviceName) throws BizAppException {
		ModelAndView mv = new ModelAndView("busi/common/import_result");
		try {
			IImportDataService service = (IImportDataService) Class.forName(serviceName).newInstance();
			ImportResultBean rs = service.handleExcelData(new ImportExcel(file, 0, 0),billType);
			mv.addObject("rs",rs);
			mv.addObject("serviceName",serviceName);
			mv.addObject("batchId",batchId);
		} catch (Exception e) {
			e.printStackTrace();
			CommonLog.getCommonLogCache().errorMessage("ExcelImportController class importResultHandle method 处理导入的excel文件失败:"+e.getMessage());
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "ExcelImportController class importResultHandle method 处理导入的excel文件失败:"+e.getMessage());
		}
		return mv;
	}
	
	
	/**
	 * 功能描述：处理导入的数据
	 * @param status 如果为0取消导入，如果为1导入成功的数据结果
	 * @return
	 * @throws BizAppException
	 */
	@RequestMapping(params = "method=importResultHandle")
	@ResponseBody
	public String importResultHandle(String serviceName,String batchId) throws BizAppException {
		String rs = null;
		IDB session = DBFactory.getDB();
		try {
			IImportDataService service = (IImportDataService) Class.forName(serviceName).newInstance();
			session.beginTransaction();
			rs = service.importResultHandle(batchId);
			if("yes".equals(rs)){
				session.endTransaction();
			}else{
				session.rollback();
			}
		} catch (Exception e) {
			rs = "no";
			try {
				session.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			CommonLog.getCommonLogCache().errorMessage("ExcelImportController class importResultHandle method 处理导入的excel数据失败:"+e.getMessage());
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "ExcelImportController class importResultHandle method 处理导入的excel数据失败:"+e.getMessage());
		}
		return rs;
	}
}
