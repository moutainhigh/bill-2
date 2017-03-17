/********************************************
 * 文件名称: T111001Service.java
 * 系统名称: 票据管理平台V2.0
 * 模块名称: 签约信息查询
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: 2.0.0.1
 * 开发人员: fqq
 * 开发时间: 2016-12-14
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期    修改人员    修改说明
 *********************************************/
package com.herongtech.online.trans.trans111001;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;

import com.herongtech.commons.tools.StringUtils;
import com.herongtech.console.core.constant.IConstants;
import com.herongtech.console.core.constant.IErrorNo;
import com.herongtech.console.core.util.XmlBeanUtil;
import com.herongtech.console.domain.bean.SignProd;
import com.herongtech.console.service.ServiceFactory;
import com.herongtech.console.service.interfaces.ISignProdService;
import com.herongtech.context.Context;
import com.herongtech.context.util.ContextUtil;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.online.trans.OnlineBaseService;
import com.herongtech.xmlchannel.pkg.ProResult;
import com.herongtech.xmlchannel.pkg.TransInfo;

/**
 *签约信息查询
 */
public class T111001Service extends OnlineBaseService {

	 
	/**
	 * 入口方法
	 * @param context
	 */
	protected void action(Context context) throws Exception {
		Var111001 transVar = new Var111001();
		
		// 交易预处理
		transRequest(context);

		// 常规校验
		CheckData(context, transVar);

		// 查询处理
		ToLocal(context, transVar);

		// 应当包处理
		PackAnswer(context, transVar);
	}

	/**
	 * 交易预处理
	 * @param context
	 * @throws BizAppException
	 */
	protected void transRequest(Context context) throws BizAppException {
		// 父类交易请求预处理
		super.transRequest(context);
		
		//银行接口化处理
		//BankInterfaceFactory.getBankInterface().bankInterface(context);
	}

	/**
	 * 常规校验
	 * @param context
	 * @param Var111001
	 * @throws BizAppException
	 */
	protected void CheckData(Context context, Var111001 var111001) throws BizAppException{
		String request = (String)ContextUtil.getRequestData(context);
		Map<String, Class> clazzMap = new HashMap<String, Class>();
        clazzMap.put("Document", Var111001.class);
        Var111001 temp=(Var111001)XmlBeanUtil.xml2Bean(clazzMap, request);
        BeanUtils.copyProperties(temp, var111001);
        if (StringUtils.isEmpty(var111001.getCustNo())) {
			throw new BizAppException(IErrorNo.ERR_CUSTACCOUNTNULL, "客户号未上送");
		}
        if (StringUtils.isEmpty(var111001.getProductNo())) {
			throw new BizAppException(IErrorNo.ERR_CUSTACCOUNTNULL, "签约产品编号未上送");
		}
	}
	
	/**
	 * 查询处理
	 * @param context
	 * @param Var111001
	 * @throws BizAppException
	 */
    protected void ToLocal(Context context, Var111001 var111001) throws BizAppException{
    	List<SignProd> sign;
    	ISignProdService signProdService = ServiceFactory.getSignProdService();
    	//根据产品编号和客户号查询签约信息
    	sign = signProdService.getSignProdInfoByPro(var111001.getProductNo(),var111001.getCustNo());
    	if(sign.size()==0){
    		throw new BizAppException(IErrorNo.ERR_NORECORD, "查询无记录");
    	}
    	var111001.setResult(sign);
    }
    
    /**
     * 应答包处理
     * @param context
     * @param Var111001
     * @throws BizAppException
     */
    protected void PackAnswer(Context context, Var111001 var111001) throws BizAppException{
    	
    	TransInfo trans=(TransInfo)ContextUtil.getContextAttribute(context, IConstants.TRANS_INFO);
        ProResult proResult=new ProResult();
        proResult.setType("S");
        proResult.setExSerial(trans.getExSerial());
        proResult.setFunctionId(trans.getFunctionId());
        List<SignProd> SignProdInfo=var111001.getResult();
        Var111001Result result=new Var111001Result();
        result.setProResult(proResult);
        result.setResult(SignProdInfo);
        Map<String, Class> clazzMap = new HashMap<String, Class>();
        clazzMap.put("Document", Var111001Result.class);
        clazzMap.put("SignProdInfo", SignProd.class);
        String resp=XmlBeanUtil.bean2xml(clazzMap, result);     
        ContextUtil.setResponseData(context, resp);
    }
	
	public String getTransName() {		
		return "签约信息查询";
	}
	
	public String getTransVersion() {		
		return "2.0.0.1";
	}

	public String getServiceId() {
		return "111001";
	}
}
