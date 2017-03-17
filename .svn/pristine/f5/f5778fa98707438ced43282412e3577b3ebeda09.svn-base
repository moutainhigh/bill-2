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
package com.herongtech.online.trans.trans110101;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.herongtech.console.core.constant.IConstants;
import com.herongtech.console.core.constant.IErrorNo;
import com.herongtech.console.core.util.RequestCheckTools;
import com.herongtech.console.core.util.XmlBeanUtil;
import com.herongtech.console.domain.bean.TransPub;
import com.herongtech.console.service.ServiceFactory;
import com.herongtech.context.Context;
import com.herongtech.context.util.ContextUtil;
import com.herongtech.db.impl.session.DBFactory;
import com.herongtech.db.interfaces.IDB;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.log.CommonLog;
import com.herongtech.online.trans.OnlineBaseService;
import com.herongtech.xmlchannel.pkg.ProResult;
import com.herongtech.xmlchannel.pkg.TransInfo;

/**
 * 可执行出票登记的票据查询
 *
 */
public class T110101Service extends OnlineBaseService {

	 
	/**
	 * 入口方法
	 * @param context
	 */
	protected void action(Context context) throws Exception {
		Var110101 transVar = new Var110101();
		
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
	 * @param Var110101
	 * @throws BizAppException
	 */
	protected void CheckData(Context context, Var110101 transVar) throws BizAppException{

		
		
		//获取公共对象	
		TransPub transPub = ServiceFactory.getTransPubService().getTransPub(context);
		transVar.setTransPub(transPub);

		RequestCheckTools.checkIsNull("AcceptorBankNo", transPub.getEcdsBillBean().getAcceptorBankNo());
		RequestCheckTools.checkIsNull("BanEndorsementMark", transPub.getEcdsBillBean().getBanEndorsementMark());
		RequestCheckTools.checkIsNull("BillClass", transPub.getEcdsBillBean().getBillClass());
		RequestCheckTools.checkIsNull("BillMoney", transPub.getEcdsBillBean().getBillMoney());
		RequestCheckTools.checkIsNull("BillType",transPub.getEcdsBillBean().getBillType());
		RequestCheckTools.checkIsNull("CustAccount",transPub.getEcdsBillBean().getCustAccount());
		RequestCheckTools.checkIsNull("DueDt", transPub.getEcdsBillBean().getDueDt());
		RequestCheckTools.checkIsNull("Remitter", transPub.getEcdsBillBean().getRemitter());
		RequestCheckTools.checkIsNull("RemitterAcct", transPub.getEcdsBillBean().getRemitterAcct());
		RequestCheckTools.checkIsNull("RemitterBank", transPub.getEcdsBillBean().getRemitterBank());
		RequestCheckTools.checkIsNull("RemitterBankNo", transPub.getEcdsBillBean().getRemitterBankNo());
		RequestCheckTools.checkIsNull("Payee", transPub.getEcdsBillBean().getPayee());
		RequestCheckTools.checkIsNull("PayeeAcct", transPub.getEcdsBillBean().getPayeeAcct());
		RequestCheckTools.checkIsNull("PayeeBankNo", transPub.getEcdsBillBean().getPayeeBankNo());
		RequestCheckTools.checkIsNull("AcceptorAcct", transPub.getEcdsBillBean().getAcceptorAcct());
		RequestCheckTools.checkIsNull("AcceptorBank",transPub.getEcdsBillBean().getAcceptorBank());
		RequestCheckTools.checkIsNull("AcceptorBankNo",transPub.getEcdsBillBean().getAcceptorBankNo());
		RequestCheckTools.checkIsNull("PayeeBank", transPub.getEcdsBillBean().getPayeeBank());
		RequestCheckTools.checkIsNull("IssueDt",  transPub.getEcdsBillBean().getIssueDt());//出票日
		RequestCheckTools.checkIsNull("Acceptor", transPub.getEcdsBillBean().getAcceptor());
		//RequestCheckTools.checkIsNull("acptDt", transPub.getEcdsBillBean().getIssueDt()); 
		
		
		//票据新增校验
		ServiceFactory.getDraftDrwrWrapper().checkNewBillDraft(transPub);
		
	}
	
	/**
	 * 增加处理
	 * @param context
	 * @param Var110101
	 * @throws BizAppException
	 */
    protected void ToLocal(Context context, Var110101 transVar) throws BizAppException {

    	IDB db = DBFactory.getDB();
    	try {
			db.beginTransaction();
			ServiceFactory.getDraftDrwrWrapper().addNewBillDraft(transVar.getTransPub());  //把 info和hist添加到add..方法中
			
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
     * @param Var110101
     * @throws BizAppException
     */
    protected void PackAnswer(Context context, Var110101 TransVar) throws BizAppException{
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
		return "新增票据信息服务";
	}
	
	public String getTransVersion() {		
		return "2.0.0.1";
	}

	public String getServiceId() {
		return "110101";
	}
}
