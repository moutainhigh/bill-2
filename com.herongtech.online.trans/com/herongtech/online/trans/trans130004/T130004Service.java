/********************************************
 * 文件名称: T130004Service.java
 * 系统名称: 票据管理平台V2.0
 * 模块名称: 保证信息查询
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: 2.0.0.1
 * 开发人员: v
 * 开发时间: 20160808
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期    修改人员    修改说明
 *********************************************/
package com.herongtech.online.trans.trans130004;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;

import com.herongtech.commons.tools.StringUtils;
import com.herongtech.console.core.constant.IConstants;
import com.herongtech.console.core.constant.IDict;
import com.herongtech.console.core.constant.IErrorNo;
import com.herongtech.console.core.util.XmlBeanUtil;
import com.herongtech.console.domain.assu.bean.AssuBillInfo;
import com.herongtech.console.domain.assu.dao.AssuBillInfoDao;
import com.herongtech.context.Context;
import com.herongtech.context.util.ContextUtil;
import com.herongtech.db.impl.session.DBFactory;
import com.herongtech.db.interfaces.IDB;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.log.CommonLog;
import com.herongtech.online.trans.OnlineBaseService;
import com.herongtech.rc.domain.bean.RcBaseSearchBean;
import com.herongtech.rc.domain.bean.RgctBill;
import com.herongtech.rc.service.RcServiceFactory;
import com.herongtech.xmlchannel.pkg.ProResult;
import com.herongtech.xmlchannel.pkg.TransInfo;
/**
 * 可质押的票据查询
 */
public class T130004Service extends OnlineBaseService {

	 
	/**
	 * 入口方法
	 * @param context
	 */
	protected void action(Context context) throws Exception {
		Var130004 transVar = new Var130004();
		
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
	 * @param context
	 * @param Var130004
	 * @throws BizAppException
	 */
	protected void CheckData(Context context, Var130004 var130004) throws BizAppException{
		String request = (String)ContextUtil.getRequestData(context);
		Map<String, Class> clazzMap = new HashMap<String, Class>();
		clazzMap.put("Document", Var130004.class);
		Var130004 temp=(Var130004)XmlBeanUtil.xml2Bean(clazzMap, request);
		if (StringUtils.isEmpty(temp.getCustAccount())){
			throw new BizAppException(IErrorNo.ERR_CUSTACCOUNTNULL, "客户账号未上送");
		}
		if (StringUtils.isEmpty(temp.getRgctId())){
			throw new BizAppException(IErrorNo.ERR_CUSTACCOUNTNULL, "登记中心ID未上送");
		}
		BeanUtils.copyProperties(temp, var130004);
	}
	
	/**
	 * 查询处理
	 * @param context
	 * @param Var130004
	 * @throws BizAppException
	 */
    protected void ToLocal(Context context, Var130004 var130004) throws BizAppException{
    	AssuBillInfoDao assuBillDao=new AssuBillInfoDao();
    	RcBaseSearchBean searchBean=new RcBaseSearchBean();
        searchBean.setHoldAcctNo(var130004.getCustAccount());
        searchBean.setBillClass(IDict.K_BILL_CLASS.K_ELEC_BILL);
        List<Var130004InfoBean> result =new ArrayList<Var130004InfoBean>();
        IDB session = DBFactory.getDB();
    	Var130004InfoBean bean=new Var130004InfoBean();
    	RgctBill bill = RcServiceFactory.getRcAssuranceService().getRgctBillById(var130004.getRgctId());
		try {
			if(bill.getHist().getAssuId()!=null){
				AssuBillInfo assuBill=assuBillDao.getAssuBillInfo(bill.getHist().getAssuId());
				bean.setGuartrName(assuBill.getGuartrName());
				bean.setGuarntrAcctNo(assuBill.getGuarntrAcctNo());
				bean.setGuarntrAddr(assuBill.getGuartrAddr());
				bean.setGuarntrBankNo(assuBill.getGuartrBankNo());
				bean.setWarteeDt(assuBill.getWarteeDt());
				bean.setAssuType(assuBill.getAssuType());
				bean.setWarteeName(assuBill.getWarteeCustName());
				bean.setWarteeAcctNo(assuBill.getWarteeAcctNo());
				bean.setWarteeBankNo(assuBill.getWarteeBankNo());
				result.add(bean);
			}
			
		} catch (SQLException e) {
			CommonLog.getCommonLogCache().errorMessage("查询数据库失败", e);
			throw new BizAppException(IErrorNo.ERR_DBERR, "查询数据库失败");
		}
		var130004.setResult(result);
    	
    }
    
    /**
     * 应答包处理
     * @param context
     * @param Var130004
     * @throws BizAppException
     */
    protected void PackAnswer(Context context, Var130004 var130004) throws BizAppException{
    	TransInfo trans=(TransInfo)ContextUtil.getContextAttribute(context, IConstants.TRANS_INFO);
        ProResult proResult=new ProResult();
        proResult.setType("S");
        proResult.setExSerial(trans.getExSerial());
        proResult.setFunctionId(trans.getFunctionId());
        List<Var130004InfoBean> infoList=var130004.getResult();
        Var130004Result result=new Var130004Result();
        result.setProResult(proResult);
        result.setResult(infoList);
        
        Map<String, Class> clazzMap = new HashMap<String, Class>();
        clazzMap.put("Document", Var130004Result.class);
        clazzMap.put("info", Var130004InfoBean.class);
       
        String resp=XmlBeanUtil.bean2xml(clazzMap, result);     
        ContextUtil.setResponseData(context, resp);
    }
	
	public String getTransName() {		
		return "保证信息查询";
	}
	
	public String getTransVersion() {		
		return "2.0.0.1";
	}

	public String getServiceId() {
		return "130004";
	}
}
