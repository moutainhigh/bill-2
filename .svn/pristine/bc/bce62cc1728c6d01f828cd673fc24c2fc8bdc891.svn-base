/********************************************
 * 文件名称: T100001Service.java
 * 系统名称: 票据管理平台V2.0
 * 模块名称: 可执行出票登记的票据查询
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: 2.0.0.1
 * 开发人员: yanjl
 * 开发时间: 2016-07-16
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期    修改人员    修改说明
 *********************************************/
package com.herongtech.online.trans.trans110102;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.herongtech.console.core.common.pubinfo.AnswerData;
import com.herongtech.console.core.constant.IConstants;
import com.herongtech.console.core.constant.IErrorNo;
import com.herongtech.console.core.util.RequestCheckTools;
import com.herongtech.console.core.util.XmlBeanUtil;
import com.herongtech.console.domain.bean.BusiDate;
import com.herongtech.console.domain.bean.TransPub;
import com.herongtech.console.service.ServiceFactory;
import com.herongtech.context.Context;
import com.herongtech.context.util.ContextUtil;
import com.herongtech.data.interfaces.data.IData;
import com.herongtech.db.impl.session.DBFactory;
import com.herongtech.db.interfaces.IDB;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.log.CommonLog;
import com.herongtech.online.trans.OnlineBaseService;
import com.herongtech.rc.domain.bean.RgctBill;
import com.herongtech.rc.service.RcServiceFactory;
import com.herongtech.xmlchannel.pkg.ProResult;
import com.herongtech.xmlchannel.pkg.TransInfo;

/**
 * 可执行出票登记的票据查询
 *
 */
public class T110102Service extends OnlineBaseService {
	 
	/**
	 * 入口方法
	 * @param context
	 */
	protected void action(Context context) throws Exception {
		Var110102 transVar = new Var110102();
		
		// 交易预处理
		transRequest(context);

		// 常规校验
		CheckData(context, transVar);

		// 查询处理
		ToLocal(context, transVar);

		// 应达包处理
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
	 * @param Var110102
	 * @throws BizAppException
	 */
	protected void CheckData(Context context, Var110102 transVar) throws BizAppException{
		//获取公共对象
		TransPub transPub = ServiceFactory.getTransPubService().getTransPub(context);
		transVar.setTransPub(transPub);
		
		RequestCheckTools.checkIsNull("BanEndorsementMark", transPub.getEcdsBillBean().getBanEndorsementMark());
		RequestCheckTools.checkIsNull("BillClass", transPub.getEcdsBillBean().getBillClass());
		RequestCheckTools.checkIsNull("BillMoney", transPub.getEcdsBillBean().getBillMoney());
		RequestCheckTools.checkIsNull("BillType",transPub.getEcdsBillBean().getBillType());
		RequestCheckTools.checkIsNull("CustAccount",transPub.getEcdsBillBean().getCustAccount());
		RequestCheckTools.checkIsNull("DueDt", transPub.getEcdsBillBean().getDueDt());
		RequestCheckTools.checkIsNull("RgctId", transPub.getEcdsBillBean().getRgctId());
		RequestCheckTools.checkIsNull("Remitter", transPub.getEcdsBillBean().getRemitter());
		RequestCheckTools.checkIsNull("RemitterAcct", transPub.getEcdsBillBean().getRemitterAcct());
		RequestCheckTools.checkIsNull("RemitterBank", transPub.getEcdsBillBean().getRemitterBank());
		RequestCheckTools.checkIsNull("RemitterBankNo", transPub.getEcdsBillBean().getRemitterBankNo());
		RequestCheckTools.checkIsNull("Payee", transPub.getEcdsBillBean().getPayee());
		RequestCheckTools.checkIsNull("PayeeAcct", transPub.getEcdsBillBean().getPayeeAcct());
		RequestCheckTools.checkIsNull("PayeeBankNo", transPub.getEcdsBillBean().getPayeeBankNo());
		RequestCheckTools.checkIsNull("AcceptorAcct", transPub.getEcdsBillBean().getAcceptorAcct());//承兑人账号
		RequestCheckTools.checkIsNull("AcceptorBank",transPub.getEcdsBillBean().getAcceptorBank());
		RequestCheckTools.checkIsNull("PayeeBank", transPub.getEcdsBillBean().getPayeeBank());
		RequestCheckTools.checkIsNull("IssueDt",  transPub.getEcdsBillBean().getIssueDt());
		RequestCheckTools.checkIsNull("Acceptor", transPub.getEcdsBillBean().getAcceptor());
		RequestCheckTools.checkIsNull("AcceptorBankNo", transPub.getEcdsBillBean().getAcceptorBankNo());
		RequestCheckTools.checkIsNull("Operator", transPub.getEcdsBillBean().getOperator());
		
		//票据新增校验
		ServiceFactory.getDraftDrwrWrapper().checkOldBillDraft(transPub);
		
	}
	
	/**
	 * 修改处理
	 * @param context
	 * @param Var110102
	 * @throws BizAppException
	 */
    protected void ToLocal(Context context, Var110102 transVar) throws BizAppException {
    	
    	IDB db = DBFactory.getDB();
    	RgctBill rgctBill = transVar.getTransPub().getRgctBill();
    	
    	try {
			db.beginTransaction();
			RcServiceFactory.getRgctBillInfoService().modifyRgctBillInfo(rgctBill.getInfo());
			
			RcServiceFactory.getRgctBillHistService().modifyRgctBillHist(rgctBill.getHist());
			
			db.endTransaction();
			
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				db.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			CommonLog.getCommonLogCache().errorMessage("添加rc票据登记信息失败");
			throw new BizAppException(IErrorNo.ERR_DBERR, "数据库错误");
			
		} catch (BizAppException e) {
			e.printStackTrace();
			try {
				db.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			CommonLog.getCommonLogCache().errorMessage("添加rc票据登记信息失败");
			throw e;
		}
    }
    
    /**
     * 应答包处理
     * @param context
     * @param Var100001
     * @throws BizAppException
     */
    protected void PackAnswer(Context context, Var110102 TransVar) throws BizAppException{
		
		TransInfo trans=(TransInfo)ContextUtil.getContextAttribute(context, IConstants.TRANS_INFO);
	    ProResult result=new ProResult();
	    result.setType("S");  
	    result.setExSerial(trans.getExSerial());
	    result.setFunctionId(trans.getFunctionId());
	    Map<String, Class> clazzMap = new HashMap<String, Class>();
	    clazzMap.put("Document", ProResult.class);
	    String resp=XmlBeanUtil.bean2xml(clazzMap, result);		
	    ContextUtil.setResponseData(context, resp);
		
    }
	
	public String getTransName() {		
		return "修改票据信息服务";
	}
	
	public String getTransVersion() {		
		return "2.0.0.1";
	}

	public String getServiceId() {
		return "110102";
	}
}
