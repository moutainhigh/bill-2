/********************************************
 * 文件名称: T100003Service.java
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
package com.herongtech.online.trans.trans110103;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.herongtech.console.core.constant.IConstants;
import com.herongtech.console.core.constant.IErrorNo;
import com.herongtech.console.core.constant.IFieldName;
import com.herongtech.console.core.util.CommUtils;
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
import com.herongtech.online.trans.OnlineBaseService;
import com.herongtech.online.trans.trans101101.Var101101InfoBean;
import com.herongtech.online.trans.trans101101.Var101101Result;
import com.herongtech.rc.domain.bean.RgctBill;
import com.herongtech.rc.domain.dao.RgctBillInfoDao;
import com.herongtech.rc.service.RcServiceFactory;
import com.herongtech.xmlchannel.pkg.ProResult;
import com.herongtech.xmlchannel.pkg.TransInfo;





/**
 * 可执行出票登记的票据查询
 *
 */
public class T110103Service extends OnlineBaseService {
	/**
	 * 入口方法
	 * @param context
	 */
	protected void action(Context context) throws Exception {
		Var110103 transVar = new Var110103();
		
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
	 * @param Var110103
	 * @throws BizAppException
	 */
	protected void CheckData(Context context, Var110103 transVar) throws BizAppException{
//		IData request = (IData)ContextUtil.getRequestData(context);
//		BusiDate busiDate = (BusiDate) ContextUtil.getContextAttribute(context, IConstants.SYSBUSININFO);
		/*EcdsBillBean bean = new EcdsBillBean();
		
		try {
			BeanUtil.dataset2Bean(bean, request);
		} catch (Exception e) {
			e.printStackTrace();
		}*/
		//获取公共对象
		TransPub transPub = ServiceFactory.getTransPubService().getTransPub(context);
		transVar.setTransPub(transPub);
		
		
		RequestCheckTools.checkIsNull("RgctId", transPub.getEcdsBillBean().getRgctId());
	//	RequestCheckTools.checkIsNull("Operator", transPub.getEcdsBillBean().getOperator()); //操作员
		
		
		//票据新增校验
		//ServiceFactory.getDraftDrwrWrapper().checkOldBillDraft(transPub);
		
	}
	
	/**
	 * 查询处理
	 * @param context
	 * @param Var110103
	 * @throws BizAppException
	 */
    protected void ToLocal(Context context, Var110103 transVar) throws BizAppException {
    	
    	IDB db = DBFactory.getDB();
    	RgctBill rgctBill = transVar.getTransPub().getRgctBill();
    	
    	TransPub transPub = ServiceFactory.getTransPubService().getTransPub(context);
    	String rgids = transPub.getEcdsBillBean().getRgctId();
    	//删除
		String[] ids = CommUtils.couvertLong(rgids);
		int errnum = 0;
		int[] resultyesorno = new int[ids.length];
		for (int i = 0; i < ids.length; i++) {
			
			try {
				db.beginTransaction();
				RgctBillInfoDao dao=new RgctBillInfoDao();
				RgctBill bill = RcServiceFactory.getRcRegBillService().getRgctBillById(ids[i]);
				if(bill==null){
					throw new BizAppException(IErrorNo.ERR_RC_001,"无法找到对应的票据对象");
				}
				bill.getInfo().setDelFlag("1");
				int result = dao.modifyRgctBillInfo(bill.getInfo());
				resultyesorno[i]=result;
				db.endTransaction();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		for (int i = 0; i < resultyesorno.length; i++) {
			if(resultyesorno[i]==0){
				errnum+=1;
			}
		}
		transVar.setRequestid(ids);           //请求过来的id
		transVar.setResult(resultyesorno);   //返回的所有记录
		transVar.setErrNum(errnum);          //错误返回
		//getRbDAO().delete(new Long[]{id});
    	
    }
    
    /**
     * 应答包处理
     * @param context
     * @param Var100001
     * @throws BizAppException
     */
    protected void PackAnswer(Context context, Var110103 TransVar) throws BizAppException{
//    	IData request = (IData)ContextUtil.getRequestData(context);
//		AnswerData var110103bean = new AnswerData(request);
        TransInfo trans=(TransInfo)ContextUtil.getContextAttribute(context, IConstants.TRANS_INFO);
        ProResult proResult=new ProResult();
        proResult.setType("S");
        proResult.setExSerial(trans.getExSerial());
        proResult.setFunctionId(trans.getFunctionId());
        Var110103Result result = new Var110103Result();
        List<Var110103Bean> resultlist = new ArrayList<Var110103Bean>();
        
		int totNum = TransVar.getRequestid().length;
		int errNum = TransVar.getErrNum();
		int[] isSuccess = TransVar.getResult();
		
		Var110103ResultNum resultnum = new Var110103ResultNum();
		resultnum.setTotNum(totNum);     
		resultnum.setErrNum(errNum);
		//resultData.beforeFirst();
		for(int i = 1; i < TransVar.getRequestid().length+1; i++){
			Var110103Bean var110103bean = new Var110103Bean();
			var110103bean.setRgctId(TransVar.getRequestid()[i-1]);            
			var110103bean.setIsSuccess(isSuccess[i-1]);
			if(isSuccess[i-1]==0){
				var110103bean.setErrMsg("id为"+TransVar.getRequestid()[i-1]+"的记录删除失败");      					
			}
			resultlist.add(var110103bean);
		}
		result.setProResult(proResult);
		result.setBeanlist(resultlist);
		result.setResultnum(resultnum);
		 Map<String, Class> clazzMap = new HashMap<String, Class>();
	     clazzMap.put("Document", Var110103Result.class);
	     clazzMap.put("ResultNum", Var110103ResultNum.class);
	     clazzMap.put("ResultData", Var110103Bean.class);

	     String resp=XmlBeanUtil.bean2xml(clazzMap, result);     
	     ContextUtil.setResponseData(context, resp);
    }
	
	public String getTransName() {		
		return "删除票据";
	}
	
	public String getTransVersion() {		
		return "2.0.0.1";
	}

	public String getServiceId() {
		return "110103";
	}
}
