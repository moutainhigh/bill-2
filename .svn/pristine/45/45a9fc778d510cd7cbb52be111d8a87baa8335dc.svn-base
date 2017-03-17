package com.herongtech.console.web.syscontroller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.herongtech.appconfig.SysConfigUtil;
import com.herongtech.commons.tools.StringUtils;
import com.herongtech.console.service.ServiceFactory;
import com.herongtech.console.web.busicontroller.common.BuybackCodeConst;
import com.herongtech.console.web.busicontroller.common.CollateCodeConst;
import com.herongtech.console.web.busicontroller.common.RebuyCodeConst;
import com.herongtech.console.web.busicontroller.common.SaleCodeConst;
import com.herongtech.console.web.busicontroller.common.SalebackCodeConst;
import com.herongtech.console.web.busicontroller.common.SubcollCodeConst;
import com.herongtech.console.web.busicontroller.print.AbstractPrint;
import com.herongtech.console.web.busicontroller.print.PublicItemBean;
import com.herongtech.console.web.busicontroller.print.VelocityUtils;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.factory.BeanFactory;


/**打印所用Controller*/
@Scope("prototype")
@Controller
@RequestMapping("/addPrintController")
public class AddPrintController {

	private String moduleId;//打印模板id
	private String ids;//清单id
	private String tpl;
	private String baid;//批次Id
	private String type;  //膜版类型
	private String isSum;//是否汇总
	private String ifAgree;//是否拒绝
	private String busiType;//业务类型
	private String repeatIds;//与其它业务类型承兑行重复的票管\票据池清单id
	private String isAdd;//是否补打(补打清单/凭证) 1 ：是
	private String handleType;//操作类型  申请/审核/记账等
	/**
	 * 显要打印的票据列表
	 */
	@RequestMapping(params="method=doPrint")
	public ModelAndView doPrint(String ids,String moduleId,String baid,String type,String handleType)throws Exception{
		try{ 
			AbstractPrint printAbstract = ServiceFactory.getPrint(StringUtils.trim(type));
			//AbstractPrint printAbstract=BeanContainerFactory.getBeanContainer(BeanContainerFactory.class.getClassLoader()).getBean(StringUtils.trim(type));
			

			printAbstract.setIsSum(isSum);
			printAbstract.setIfAgree(ifAgree);
			printAbstract.setBusiType(busiType);
			printAbstract.setRepeatIds(repeatIds);
			printAbstract.setIsAdd(isAdd);
			printAbstract.setModuleId(moduleId);
			String batch_id=baid;
			List list = printAbstract.getPrintList(ids, batch_id,handleType);
			if("1".equals(isAdd) && moduleId !=null && moduleId.indexOf(",")>0){//贴现凭证 补打时,会有第三方凭证 ,需要在程序里面进行区分
				moduleId = printAbstract.getModuleId();
			}
			tpl=this.setDataIntoTemplate(moduleId, list);
		} catch (Exception e) {
			e.printStackTrace();
			throw new BizAppException("您的打印数据有异常"+e.getMessage(),e);
		}
		ModelAndView mv = new ModelAndView();
		//mv.setViewName("views/print");
		
		mv.setViewName("system/addPrint/print");
		mv.addObject("tpl", tpl);
		return mv;
	}
	
	
	 /**
     * 获取公共打印模板的方法
     * @param moduleId
     * @param list
     * @return
     */
	@SuppressWarnings("unchecked")
	public String setDataIntoTemplate(String moduleId, List list)throws Exception {

		StringBuffer allScript = null;
		ResourceLoader resourceLoader = new DefaultResourceLoader();
		String modelPath="";//moan路径
		try {
		      StringBuffer strBuffer = new StringBuffer();
		      if("1030601".equals(moduleId)){//清单
		    	  modelPath = URLDecoder.decode(resourceLoader.getResource(SysConfigUtil.getSysConfig().getValue("DiscAuditPrintPath")).getURL().getFile(), "UTF-8");	    	  
		      }else if("1030102".equals(moduleId)){//凭证膜版 付息方式为  2-卖方付息
		    	  modelPath = URLDecoder.decode(resourceLoader.getResource(SysConfigUtil.getSysConfig().getValue("DiscPrintsellerPath")).getURL().getFile(), "UTF-8");
		      }else if("1030301".equals(moduleId)){//凭证膜版 付息方式为  卖方付息 以外的方式 
		    	  modelPath = URLDecoder.decode(resourceLoader.getResource(SysConfigUtil.getSysConfig().getValue("DiscPrintotherPath")).getURL().getFile(), "UTF-8");
		      } else if(SubcollCodeConst.BILL_PRINT_MYSUBID.equals(moduleId)){//托收清单
		    	  modelPath = URLDecoder.decode(resourceLoader.getResource(SysConfigUtil.getSysConfig().getValue("SubcollbillprintPath")).getURL().getFile(), "UTF-8");
		      }else if(SubcollCodeConst.BILL_PZ_PRINT_MYSUBID.equals(moduleId)){//托收凭证
		    	  modelPath = URLDecoder.decode(resourceLoader.getResource(SysConfigUtil.getSysConfig().getValue("SubcollbillpzprintPath")).getURL().getFile(), "UTF-8");
		      }else if(RebuyCodeConst.BILL_PRINT_MYSUBID.equals(moduleId)){//转入清单
		    	  modelPath = URLDecoder.decode(resourceLoader.getResource(SysConfigUtil.getSysConfig().getValue("RebuyListprintPath")).getURL().getFile(), "UTF-8");
		      }else if(RebuyCodeConst.BILL_PZ_PRINT_MYSUBID.equals(moduleId)){//转入凭证
		    	  modelPath = URLDecoder.decode(resourceLoader.getResource(SysConfigUtil.getSysConfig().getValue("RebuyListpzprintPath")).getURL().getFile(), "UTF-8");
		      }else if(SaleCodeConst.BILL_PRINT_MYSUBID.equals(moduleId)){//转卖
		    	  modelPath = URLDecoder.decode(resourceLoader.getResource(SysConfigUtil.getSysConfig().getValue("SaleListPrintPath")).getURL().getFile(), "UTF-8");
		      }else if(SalebackCodeConst.BILL_PRINT_MYSUBID.equals(moduleId)){//返售
		    	  modelPath = URLDecoder.decode(resourceLoader.getResource(SysConfigUtil.getSysConfig().getValue("SaleBackListPrintPath")).getURL().getFile(), "UTF-8");
		      }else if(BuybackCodeConst.BILL_PRINT_MYSUBID.equals(moduleId)){//回购
		    	  modelPath = URLDecoder.decode(resourceLoader.getResource(SysConfigUtil.getSysConfig().getValue("BuyBackListPrintPath")).getURL().getFile(), "UTF-8");
		      }else if(CollateCodeConst.BILL_PRINT_MYSUBID.equals(moduleId)){//回购
		    	  modelPath = URLDecoder.decode(resourceLoader.getResource(SysConfigUtil.getSysConfig().getValue("CollateralizationListPrintPath")).getURL().getFile(), "UTF-8");
		      }
		      
		      BufferedReader bufReader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(modelPath)),"UTF-8"));//数据流读取文件
		      for (String temp = null; (temp = bufReader.readLine()) != null; temp = null) {
		        strBuffer.append(temp);
		      }
		      bufReader.close();
			
			// 取数据映射
			allScript = new StringBuffer();
			int i = 0;
//			String tmplScript = tmpl.getTpl(); 
			String tmplScript=strBuffer.toString();
			if(StringUtils.isEmpty(tmplScript)){
				throw new Exception("打印模板未设置或者不存在！");
			}
			Map tmplMap = new HashMap();
			for (int j = 0; j < list.size(); j++) {
				tmplMap.put("td", (PublicItemBean)list.get(j));
				if (i != 0) {
					tmplScript = makeBatchScript(tmplScript, i++);
				} else {
					i++;
				}
				String result = VelocityUtils.renderContent(tmplScript, tmplMap);
				allScript.append(result);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("【打印模板异常"+moduleId+"】:"+e);
		}
		return allScript==null ? "" : allScript.toString();
	}
	

	/**
	 * 取打印模板,并按模板框架高度增加各个标签的top值,新的top值应为: top + 框架高度*row
	 * 
	 * @param script
	 * @param row
	 *            数据行
	 * @return
	 */
	public static String makeBatchScript(String script, int row) {
		int invoiceHeight = 0;
		String regHeightEx = "height:\\s*\\d+px"; // 匹配height: 100px
		String regTopEx = "TOP:\\s*\\d+px"; // 匹配Top: 100px
		String regEx = "\\d+"; // 数值模式
		Pattern p = Pattern.compile(regHeightEx, Pattern.CASE_INSENSITIVE);
		Matcher m = p.matcher(script);
		boolean flag = m.find();
		if (flag) {
			String divFrameHeight = m.group(0);
			p = Pattern.compile(regEx);
			m = p.matcher(divFrameHeight);
			flag = m.find();
			invoiceHeight = Integer.parseInt(m.group(0)) * row;
			// 替换各div对象的top属性
			Pattern pTop = Pattern.compile(regTopEx, Pattern.CASE_INSENSITIVE);
			Matcher mTop = pTop.matcher(script);
			while (mTop.find()) {
				String subScript = mTop.group();
				Pattern patter = Pattern.compile(regEx);
				Matcher matcher = patter.matcher(subScript);
				flag = matcher.find();
				if (flag) {
					int topValue = Integer.parseInt(matcher.group(0));
					int newTopValue = topValue + invoiceHeight;
					String newTop = "top:" + newTopValue + "px";
					script = script.replaceAll(subScript, newTop);
				}
			}
		}
		return script;
	}
}
