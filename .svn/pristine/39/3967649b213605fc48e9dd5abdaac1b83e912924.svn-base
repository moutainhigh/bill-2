/********************************************
 * 文件名称: T111101Service.java
 * 系统名称: 票据管理平台V2.0
 * 模块名称: 利息计算
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: 2.0.0.1
 * 开发人员: fqq
 * 开发时间: 20161209
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期    修改人员    修改说明
 *********************************************/
package com.herongtech.online.trans.trans111101;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.BeanUtils;

import com.herongtech.commons.tools.StringUtils;
import com.herongtech.console.core.constant.IConstants;
import com.herongtech.console.core.constant.IErrorNo;
import com.herongtech.console.core.util.XmlBeanUtil;
import com.herongtech.console.domain.common.bean.InterestReqByStringDTO;
import com.herongtech.console.domain.common.bean.InterestResultDTO;
import com.herongtech.console.service.ServiceFactory;
import com.herongtech.console.service.interfaces.IInterestService;
import com.herongtech.context.Context;
import com.herongtech.context.util.ContextUtil;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.online.trans.OnlineBaseService;
import com.herongtech.online.trans.trans111101.Var111101;
import com.herongtech.xmlchannel.pkg.ProResult;
import com.herongtech.xmlchannel.pkg.TransInfo;


public class T111101Service extends OnlineBaseService{
	/*
	 * 入口方法
	 */
	protected void action(Context context) throws Exception {
		Var111101 transVar = new Var111101();
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
	 * @throws Exception
	 */
	protected void transRequest(Context context) throws BizAppException {
		// 父类交易请求预处理
		super.transRequest(context);
		
		//银行接口化处理
		//BankInterfaceFactory.getBankInterface().bankInterface(context);
	}
	/**
	 * 常规校验
	 * @param var111101
	 * @param context
	 * @throws BizAppException
	 */
	protected void CheckData(Context context,Var111101 var111101) throws BizAppException{
		String request=(String)ContextUtil.getRequestData(context);
		Map<String, Class> clazzMap = new HashMap<String, Class>();
        clazzMap.put("Document", Var111101.class);
        clazzMap.put("bean", InterestReqByStringDTO.class);
        Var111101 temp=(Var111101)XmlBeanUtil.xml2Bean(clazzMap, request);
        List<InterestReqByStringDTO> insList = temp.getInsList();
        for(int i = 0; i < insList.size(); i++){
        	InterestReqByStringDTO insDto=insList.get(i);
        	if (StringUtils.isEmpty(insDto.getBeginDate().toString())){
        		throw new BizAppException(IErrorNo.ERR_CUSTACCOUNTNULL, "起息日未上送");
        	}
        	if (StringUtils.isEmpty(insDto.getEndDate().toString())){
        		throw new BizAppException(IErrorNo.ERR_CUSTACCOUNTNULL, "票面到期日未上送");
        	}
        	if (StringUtils.isEmpty(insDto.getAmount().toString())){
        		throw new BizAppException(IErrorNo.ERR_CUSTACCOUNTNULL, "票面金额未上送");
        	}
        	if (StringUtils.isEmpty(insDto.getRate().toString())){
        		throw new BizAppException(IErrorNo.ERR_CUSTACCOUNTNULL, "票面利率未上送");
        	}
        	if (StringUtils.isEmpty(insDto.getDelayDays().toString())){
        		throw new BizAppException(IErrorNo.ERR_CUSTACCOUNTNULL, "异地顺延天数未上送");
        	}
        	if (StringUtils.isEmpty(insDto.getChargeKind())){
				throw new BizAppException(IErrorNo.ERR_CUSTACCOUNTNULL, "顺延规则未上送");
        	}
        	if (StringUtils.isEmpty(insDto.getRateType())){
        		throw new BizAppException(IErrorNo.ERR_CUSTACCOUNTNULL, "利率类型未上送");
        	}
        	if (StringUtils.isEmpty(insDto.getIfSameCity())){
        		throw new BizAppException(IErrorNo.ERR_CUSTACCOUNTNULL, "同城未上送");
        	}
        }
        BeanUtils.copyProperties(temp, var111101);
		
	}
	/**
	 * 查询处理
	 * @param context
	 * @param Var111101
	 * @throws BizAppException
	 */
    protected void ToLocal(Context context, Var111101 var111101) throws BizAppException{
    	int errNum = 0;
    	List<InterestReqByStringDTO> insList = var111101.getInsList();
    	IInterestService interestService = ServiceFactory.getInterestService();
    	List<Var111101InfoBean> result =new ArrayList<Var111101InfoBean>();
    	List<InterestResultDTO> insDto=new ArrayList<InterestResultDTO>();
    	for (int i = 0; i < insList.size(); i++) {
    		Var111101InfoBean bean=new Var111101InfoBean();
    		//进行利息试算
        	try {
        		/*if(insList.get(i).getDelayDays()!=0)
        		{
        			throw new BizAppException(IErrorNo.ERR_CUSTACCOUNTNULL, "电子票据异地顺延天数固定为0");
        		}*/
				InterestResultDTO interestResult = interestService.getIns(insList.get(i));
				insDto.add(interestResult);
				bean.setInterest(interestResult.getInterest().toString());
				bean.setIsSuccess("S");
				bean.setErrMsg("利息计算成功");
        	} catch (Exception e) {
        		bean.setInterest(null);
				bean.setIsSuccess("E");
				bean.setErrMsg(e.getMessage());
			}
        	result.add(bean);
			
		}
    	for(int i=0; i<result.size();i++){
    		if("E".equals(result.get(i).getIsSuccess())){
    			errNum=errNum+1;
    		}
    	}
    	var111101.setErrNum(String.valueOf(errNum));
    	var111101.setTotNum(String.valueOf(result.size()));
    	var111101.setResult(result);
    }
    /**
     * 应答包处理
     * @param context
     * @param Var111101
     * @throws BizAppException
     */
    protected void PackAnswer(Context context, Var111101 var111101) throws BizAppException{
    	TransInfo trans=(TransInfo)ContextUtil.getContextAttribute(context, IConstants.TRANS_INFO);
    	ProResult proResult=new ProResult();
    	List<Var111101InfoBean> infoList = var111101.getResult();
        proResult.setType("S");
        proResult.setExSerial(trans.getExSerial());
        proResult.setFunctionId(trans.getFunctionId());
        Var111101Result result = new Var111101Result();
        result.setProResult(proResult);
        result.setResult(infoList);
        Map<String, Class> clazzMap = new HashMap<String, Class>();
        clazzMap.put("Document", Var111101Result.class);
        clazzMap.put("info", Var111101InfoBean.class);
        String resp=XmlBeanUtil.bean2xml(clazzMap, result);     
        ContextUtil.setResponseData(context, resp);
        
        
    }
    public String getTransName() {		
		return "计算利息";
	}
	
	public String getTransVersion() {		
		return "2.0.0.1";
	}

	public String getServiceId() {
		return "111101";
	}
	

}
