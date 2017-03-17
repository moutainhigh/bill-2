package com.herongtech.console.web.syscontroller;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.herongtech.appconfig.SysConfigUtil;
import com.herongtech.console.core.common.json.AjaxJson;
import com.herongtech.console.core.controller.BaseController;
import com.herongtech.console.service.ServiceFactory;
import com.herongtech.db.impl.session.DBFactory;
import com.herongtech.db.interfaces.IDB;
import com.herongtech.exception.impl.BizAppException;

/**Ecds基础数据导入*/
@Scope("prototype")
@Controller
@RequestMapping("/ecdsDataImportController")
public class EcdsDataImportController extends BaseController{

	public static final String HERONGTECH_VERSION="@system 票据管理平台 @version 2.0.0.1 @lastModiDate @describe ";

	/**跳转到ecds导入的主页面*/
	@RequestMapping(params="method=mainview")
	public ModelAndView mainview(){
		ModelAndView mv = new ModelAndView();
		List<String> list = new ArrayList<String>();
		list.add("ECDS_AP_DATA.dat");
		list.add("ECDS_AUTHLIST_DATA.dat");
		list.add("ECDS_BANK_ASS_DATA.dat");
		list.add("ECDS_BANK_DATA.dat");
		list.add("ECDS_COMMON_DATA.dat");
		list.add("ECDS_PS_ASS_DATA.dat");
		list.add("ECDS_PSAP_ASS_DATA.dat");
	
		mv.addObject("ecdspathList", list);
		mv.setViewName("system/ecdsimport/ecdsimport");
		return mv;
	}
	
	/**导入ecds数据*/
	@RequestMapping(params="method=importecdsdata")
	@ResponseBody
	public AjaxJson importecdsdata(String filenames){
		AjaxJson aj = new AjaxJson();
		String filename=filenames.split(".dat")[0];
		ResourceLoader resourceLoader = new DefaultResourceLoader();
		String modelPath = "";
  	  try {
  		  if("ECDS_AP_DATA".equals(filename)){
  			  modelPath = URLDecoder.decode(resourceLoader.getResource(SysConfigUtil.getSysConfig().getValue("ECDS_AP_DATA")).getURL().getFile(), "UTF-8");  			  
  		  } if("ECDS_AUTHLIST_DATA".equals(filename)){
  			  modelPath = URLDecoder.decode(resourceLoader.getResource(SysConfigUtil.getSysConfig().getValue("ECDS_AUTHLIST_DATA")).getURL().getFile(), "UTF-8");  			  
  		  } if("ECDS_BANK_ASS_DATA".equals(filename)){
  			  modelPath = URLDecoder.decode(resourceLoader.getResource(SysConfigUtil.getSysConfig().getValue("ECDS_BANK_ASS_DATA")).getURL().getFile(), "UTF-8");  			  
  		  } if("ECDS_BANK_DATA".equals(filename)){
  			  modelPath = URLDecoder.decode(resourceLoader.getResource(SysConfigUtil.getSysConfig().getValue("ECDS_BANK_DATA")).getURL().getFile(), "UTF-8");  			  
  		  } if("ECDS_COMMON_DATA".equals(filename)){
  			  modelPath = URLDecoder.decode(resourceLoader.getResource(SysConfigUtil.getSysConfig().getValue("ECDS_COMMON_DATA")).getURL().getFile(), "UTF-8");  			  
  		  } if("ECDS_PS_ASS_DATA".equals(filename)){
  			  modelPath = URLDecoder.decode(resourceLoader.getResource(SysConfigUtil.getSysConfig().getValue("ECDS_PS_ASS_DATA")).getURL().getFile(), "UTF-8");  			  
  		  } if("ECDS_PSAP_ASS_DATA".equals(filename)){
  			  modelPath = URLDecoder.decode(resourceLoader.getResource(SysConfigUtil.getSysConfig().getValue("ECDS_PSAP_ASS_DATA")).getURL().getFile(), "UTF-8");  			  
  		  }
  		boolean result= convert(filename, modelPath);
  		if(result){
  			aj.setMsg("导入成功");
  			aj.setSuccess(true);  			
  		}else{
  			aj.setMsg("导入失败");
  			aj.setSuccess(false);  
  		}
	} catch (UnsupportedEncodingException e) {
		e.printStackTrace();
		aj.setMsg("导入失败");
		aj.setSuccess(false);
		return aj;
	} catch (IOException e) {
		e.printStackTrace();
		e.printStackTrace();
		aj.setMsg("导入失败");
		aj.setSuccess(false);
		return aj;
	}	
		return aj;
	}
	/**
	 * 读取一个文本行,处理转化成一个数组
	 * 
	 * @param line
	 * @return
	 */
	public String[] convertLine(String line) {

		String[] ecdsData = line.split("┠\t┠");// 跳过字符。

		if (ecdsData.length > 2) {
			int firstPos = ecdsData[0].indexOf("┠");
			ecdsData[0] = ecdsData[0].substring(firstPos + 1, ecdsData[0].length());
			int lastPos = ecdsData[ecdsData.length - 1].indexOf("┠");
			ecdsData[ecdsData.length - 1] = ecdsData[ecdsData.length - 1].substring(0, lastPos);

		}

		for (int i = 0; i < ecdsData.length; i++) {
			ecdsData[i] = ecdsData[i].trim();
			if (ecdsData[i].equals("NULL")) {
				ecdsData[i] = null;
			}
		}

		return ecdsData;

	}

	/**
	 * 将转换后的数组文件再转换成bo类文件。
	 */
	public boolean convert(String className, String fileName) {
		FileInputStream fis = null;
		InputStreamReader isr = null;
		BufferedReader read = null;
		IDB session = DBFactory.getDB();
		try {
			fis = new FileInputStream(fileName);
			isr = new InputStreamReader(fis, "utf-8");
			read = new BufferedReader(isr);
			String line = "";
			ArrayList ecdsDataList = new ArrayList();
			ArrayList forList = new ArrayList();
			forList.add(ecdsDataList);
			while ((line = read.readLine()) != null) {
				ecdsDataList.add(convertLine(line));
				if(ecdsDataList.size() % 1000 == 0){
					ecdsDataList.clear();
					ecdsDataList = null;
					ecdsDataList = new ArrayList();
					forList.add(ecdsDataList);
				}
			}
			session.beginTransaction();
			for(Object dataList: forList) {
				ServiceFactory.getEcdsDataImportService().createEcdsData((List)dataList, className);
			}
			session.endTransaction();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		} catch (SQLException e) {
			try {
				session.rollback();
				return false;
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		} catch (BizAppException e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				if(fis != null)fis.close();
				if(isr != null)isr.close();
				if(read != null)read.close();
            } catch (IOException e) {
	            e.printStackTrace();
            }
		}
		return true;
	}

	
}
