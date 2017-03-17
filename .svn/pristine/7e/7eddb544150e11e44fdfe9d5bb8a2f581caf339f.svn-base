/********************************************
 * 文件名称: RcBaseService.java
 * 系统名称: 基础技术平台V2.0
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: 2.0.0.1
 * 开发人员: yanjl
 * 开发时间: 2016-6-15 下午01:48:52
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期    修改人员    修改说明
 *********************************************/

package com.herongtech.rc.service.rcservice;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;

import com.herongtech.console.cache.RgctStatusMappingCache;
import com.herongtech.console.core.common.json.Page;
import com.herongtech.console.core.common.search.BaseSearchBean;
import com.herongtech.console.core.common.search.QueryCondition;
import com.herongtech.console.core.constant.DraftConstants;
import com.herongtech.console.core.constant.IDict;
import com.herongtech.console.core.constant.IErrorNo;
import com.herongtech.console.core.constant.RcConstants;
import com.herongtech.console.core.util.CommUtils;
import com.herongtech.console.core.util.DateTimeUtil;
import com.herongtech.console.service.ServiceFactory;
import com.herongtech.console.web.busicontroller.common.GuarCodeConst;
import com.herongtech.db.impl.session.DBFactory;
import com.herongtech.db.interfaces.IDB;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.rc.domain.bean.EcdsBankData;
import com.herongtech.rc.domain.bean.RcBaseSearchBean;
import com.herongtech.rc.domain.bean.RgctBill;
import com.herongtech.rc.domain.bean.RgctBillData;
import com.herongtech.rc.domain.bean.RgctBillHist;
import com.herongtech.rc.domain.bean.RgctBillInfo;
import com.herongtech.rc.domain.bean.RgctDraftLog;
import com.herongtech.rc.domain.bean.RgctEndoHist;
import com.herongtech.rc.domain.bean.RgctMethod;
import com.herongtech.rc.domain.bean.RgctStatus;
import com.herongtech.rc.domain.bean.RgctTempHist;
import com.herongtech.rc.domain.bean.RgctTrigger;
import com.herongtech.rc.domain.dao.RgctBillHistDao;
import com.herongtech.rc.domain.dao.RgctBillInfoDao;
import com.herongtech.rc.domain.dao.RgctEndoHistDao;
import com.herongtech.rc.domain.dao.RgctMethodDao;
import com.herongtech.rc.domain.dao.RgctStatusDao;
import com.herongtech.rc.domain.dao.RgctTempHistDao;
import com.herongtech.rc.domain.dao.RgctTriggerDao;
import com.herongtech.rc.draft.common.MsgUtil;
import com.herongtech.rc.draft.common.vo.DraftInfoVo;
import com.herongtech.rc.service.RcServiceFactory;
import com.herongtech.rc.service.interfaces.IRgctDraftLogService;
import com.herongtech.sysconstant.ISysErrorNo;

public class RcBaseService implements IRcBaseService{
	
	public static final String HERONGTECH_VERSION="@system 票据管理平台 @version 2.0.0.1 @lastModiDate @describe ";
	private RgctStatusDao statusDao=new RgctStatusDao();
	private RgctBillHistDao histDao=new RgctBillHistDao();
	private RgctBillInfoDao infoDao=new RgctBillInfoDao();
	private RgctTempHistDao tempDao=new RgctTempHistDao();
	
	public void lock(String  rgctId) throws BizAppException {
		RgctBillInfo info;
		try {
			info = infoDao.getRgctBillInfo(rgctId);
			RgctBillHist hist = histDao.getRgctBillHist(info.getHistId());
			hist.setIsLock(IDict.K_LOCK.K_LOCK_YES);
			histDao.modifyRgctBillHist(hist);
		} catch (SQLException e) {
			throw new BizAppException(ISysErrorNo.ERR_DBERR, e.getMessage());
		}
		
	}
	
	public void unLock(String  rgctId) throws BizAppException {
		try {
			RgctBillInfo info=infoDao.getRgctBillInfo(rgctId);
			RgctBillHist hist = histDao.getRgctBillHist(info.getHistId());
			hist.setIsLock(IDict.K_LOCK.K_LOCK_NO);
			histDao.modifyRgctBillHist(hist);
		}catch (SQLException e) {
			throw new BizAppException(ISysErrorNo.ERR_DBERR, e.getMessage());
		}
	}
	
	/**获取票据登记信息
	 */
	public RgctBill getRgctBillById(String rgctId)throws BizAppException {
		RgctBill rgctbill = new RgctBill();
		rgctbill.setInfo(RcServiceFactory.getRgctBillInfoService().getRgctBillInfo(rgctId));
		rgctbill.setHist(RcServiceFactory.getRgctBillHistService().getRgctBillHist(rgctbill.getInfo().getHistId()));
		return rgctbill;
	}
	
	
	
	public RgctBill getRgctBillByBillNo(String billNo)throws BizAppException {
		RgctBill rgctbill = new RgctBill();
		rgctbill.setInfo(RcServiceFactory.getRgctBillInfoService().getRgctBillInfoByBillNo(billNo));
		if(rgctbill.getInfo() != null){
		    rgctbill.setHist(RcServiceFactory.getRgctBillHistService().getRgctBillHist(rgctbill.getInfo().getHistId()));
		}
		return rgctbill;
	}
	
	public RgctBill getRgctBillByReqDraftId(String reqDraftId)throws BizAppException {
		RgctBill rgctbill = new RgctBill();
		rgctbill.setInfo(RcServiceFactory.getRgctBillInfoService().getRgctBillInfoByReqDraftId(reqDraftId));
		rgctbill.setHist(RcServiceFactory.getRgctBillHistService().getRgctBillHist(rgctbill.getInfo().getHistId()));
		return rgctbill;
	}
	
	public List<RgctBill> getRgctBillList(String ids)throws BizAppException {
		String histIds="";
		List<RgctBill> rcList=new ArrayList<RgctBill>();
		Map<String, RgctBillInfo> billMap=new HashMap<String, RgctBillInfo>();
		try {
			List<RgctBillInfo> infoList=infoDao.getRcInfoList(ids);
			for (int i = 0; i < infoList.size(); i++) {
				histIds=histIds+infoList.get(i).getHistId()+",";
				billMap.put(infoList.get(i).getId(), infoList.get(i));
			}
			List<RgctBillHist> histList=histDao.getRcHistList(histIds.substring(0, histIds.length()-1));
			for (int i = 0; i <histList.size(); i++) {
				RgctBill bill=new RgctBill();
				RgctBillHist hist=histList.get(i);
				bill.setHist(hist);
				bill.setInfo(billMap.get(hist.getRgctId()));
				rcList.add(bill);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BizAppException("批量获取RgctBill失败"+e.getMessage());
		}
		return rcList;
	}
	public void updateRgctBillHist(RgctBillHist hist)throws BizAppException {
		try {
			histDao.modifyRgctBillHist(hist);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BizAppException(e.getMessage());
		}
	}
	public void updateRgctBillInfo(RgctBillInfo info)throws BizAppException {
		try {
			infoDao.modifyRgctBillInfo(info);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BizAppException(e.getMessage());
		}
	}
	
	public List<RgctBillData> queryRC(RcBaseSearchBean sb, Page page, RgctMethod method, boolean bDelFlag,String param) throws BizAppException {
		try {
			IDB session = DBFactory.getDB();
			String baseSql="select billinfo.*,billhist.* from trgct_bill_info billinfo, trgct_bill_hist billhist where billinfo.hist_id=billhist.hist_id";
			baseSql=baseSql+getBillhistStatusSql(method, bDelFlag,param);
			//分页开始位置
			int startIndex = page.getCurrentResult();
			 QueryCondition qc=new QueryCondition();
			 try {
				 sb.setDfaultSrchTbAliasName("billinfo");
				 sb.addSqlPropretyMapping("minDueDt", "dueDt");
				 sb.addProperty2TableObj("minDueDt", "billinfo");
				 sb.addSpecialOpertion("minDueDt", BaseSearchBean.MORE_AND_EQUAL);
				 sb.addSqlPropretyMapping("maxDueDt", "dueDt");
				 sb.addProperty2TableObj("maxDueDt", "billinfo");
				 sb.addSpecialOpertion("maxDueDt", BaseSearchBean.LESS_AND_EQUAL);
				 sb.addSqlPropretyMapping("minBillMoney", "billMoney");
				 sb.addProperty2TableObj("minBillMoney", "billinfo");
				 sb.addSpecialOpertion("minBillMoney", BaseSearchBean.MORE_AND_EQUAL);
				 sb.addSqlPropretyMapping("maxBillMoney", "billMoney");
				 sb.addProperty2TableObj("maxBillMoney", "billinfo");
				 sb.addSpecialOpertion("maxBillMoney", BaseSearchBean.LESS_AND_EQUAL);
				 sb.addProperty2TableObj("billNo", "billinfo");	
				 sb.addProperty2TableObj("holdAcctNo", "billhist");
				 sb.addProperty2TableObj("holdBankNo", "billhist");
				 sb.addProperty2TableObj("toAcctNo", "billhist");
				 sb.addProperty2TableObj("toBankNo", "billhist");
				 sb.addProperty2TableObj("isLock", "billhist");
				 sb.addProperty2TableObj("acctBranchNo", "billhist");
				 sb.addProperty2TableObj("storageBranchNo", "billhist");
		         qc.initFromSearchBean(sb);
		     } catch (Exception e) {
		         e.printStackTrace();
		     }
			 String sql=qc.getAllSqlString(baseSql);
			 System.out.println(sql);
			// 获得并返回本次查询的总条数
			int count = session.accountByList(qc.getCountSql("billinfo.id"), qc.getParameterValues());
			page.setTotalResult(count);
			return session.getObjectListByListForPage(sql,RgctBillData.class, startIndex, page.getShowCount(), qc.getParameterValues());
			
		} catch (Exception ex) {
			throw new BizAppException(ex);
		}
	}
	
	public List<RgctBillInfo> query(RcBaseSearchBean sb, Page page, RgctMethod method, boolean bDelFlag) throws BizAppException {
		try {
			IDB session = DBFactory.getDB();
			String baseSql="select billinfo.* from trgct_bill_info billinfo, trgct_bill_hist billhist where billinfo.hist_id=billhist.hist_id";
			//分页开始位置
			int startIndex = page.getCurrentResult();
			 QueryCondition qc=new QueryCondition();
			 try {
				 sb.addProperty2TableObj("holdAcctNo", "billhist");
				 sb.addProperty2TableObj("toAcctNo", "billhist");
				 sb.addProperty2TableObj("toBankNo", "billhist");
				 sb.addProperty2TableObj("isLock", "billhist");
				 sb.addProperty2TableObj("acctBranchNo", "billhist");
				 sb.addProperty2TableObj("storageBranchNo", "billhist");
		         qc.initFromSearchBean(sb);
		     } catch (Exception e) {
		         e.printStackTrace();
		     }
			 String sql=qc.getAllSqlString(baseSql)+ getBillhistStatusSql(method, bDelFlag,null);
//			 String sql=qc.getAllSqlString(baseSql);//因数据库中没有匹配的票数据，此处先将查询条件注释掉
			 System.out.println(sql);
			// 获得并返回本次查询的总条数
			int count = session.accountByList(qc.getCountSql("billinfo.id"), qc.getParameterValues());
			page.setTotalResult(count);
//			String ss="select billinfo.*,billhist.* from trgct_bill_info billinfo, trgct_bill_hist billhist where billinfo.hist_id=billhist.hist_id";
//			List aa=session.getObject(ss,RgctBillInfo.class, startIndex, page.getShowCount(), qc.getParameterValues());
			return session.getObjectListByListForPage(sql,RgctBillInfo.class, startIndex, page.getShowCount(), qc.getParameterValues());
			
		} catch (Exception ex) {
			throw new BizAppException(ex);
		}
	}
	
	protected String queryBillSql(RcBaseSearchBean sb, RgctMethod method, boolean bDelFlag)
			throws BizAppException{
    	StringBuilder builder = new StringBuilder("select billinfo.*,billhist.* from trgct_bill_info billinfo, trgct_bill_hist billhist where billinfo.hist_id=billhist.hist_id");
    	//生成票据状态查询语句
    	if (StringUtils.isNotBlank(sb.getBillClass())){
    		builder.append(" and billinfo.bill_class = '" + sb.getBillClass() + "' ");
    	}
    	if (StringUtils.isNotBlank(sb.getBillType())){
    		builder.append(" and billinfo.bill_type = '" + sb.getBillType() + "' ");
    	}
    	//生成票据状态查询语句
    	if (StringUtils.isNotBlank(sb.getHoldAcctNo())){
    		builder.append(" and billhist.hold_acct_no ='" + sb.getHoldAcctNo() + "'");
    	}
		
    	if (StringUtils.isNotBlank(sb.getToAcctNo())){
    		builder.append(" and billhist.to_acct_no ='" + sb.getToAcctNo() + "'");
    	}
    	if (StringUtils.isNotBlank(sb.getToBankNo())){
    		builder.append(" and billhist.to_bank_no ='" + sb.getToBankNo() + "'");
    	}
    	if (StringUtils.isNotBlank(sb.getStorageBranchNo())){
    		builder.append(" and billhist.storage_branch_no ='" + sb.getStorageBranchNo() + "'");
    	}
    	if (StringUtils.isNotBlank(sb.getAcctBranchNo())){
    		builder.append(" and billhist.acct_branch_no ='" + sb.getAcctBranchNo() + "'");
    	}
    	String statusWhereSql = getBillhistStatusSql(method, bDelFlag,null);
    	
    	builder.append(statusWhereSql);
    	
    	return builder.toString();
	}
	
	/**
	 * 041通用业务通知服务 --- 根据通用业务通知报文处理流程
	 * 
	 * @param bill 登记中心票据信息
	 * @param methodName
	 *            登记中心处理服务
	 *            注:系统参与者在收到电子商业汇票系统NPC发来的“通用业务通知报文(041)”后，若报文中所带通知信息为撤票信息，则需修改票据状态为“票据已作废”，
	 *            若为其他通知信息，则不需修改票据状态
	 * @throws ServiceException
	 */
	public void commonBusinessNotification(RgctBill bill, String methodName)
			throws BizAppException {
		try {
			if(!isSelfBank(bill.getHist().getHoldBankNo())) {
				//当持有人非系统内时接收通知
				RgctMethodDao methodDao=new RgctMethodDao();
				
				changeStatus(bill, methodDao.getRgctMethod(RcConstants.TYPE_NOTIFY, methodName), null);
			}
//			trigger(bill,"cancelBillNotify","","",true);//回调待处理
		} catch (Exception e) {
			throw new BizAppException(e);
		}
	}
	
	/**
	 * 035清分失败恢复报文处理服务
	 * 
	 * @param bill
	 *            登记中心票据信息
	 * @throws ServiceException
	 */
	public void commonExceptionNotify(RgctBill bill,String orgDraftNoReq) throws BizAppException {
		String callBackParam = bill.getHist().getCurStatus().substring(0,1);
		RgctMethodDao methodDao=new RgctMethodDao();
		RgctMethod method;
		try {
			method = methodDao.getRgctMethod("commonCancel", "rollback");
			this.changeStatus(bill, method, null);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BizAppException(e);
		}
		if("013".equals(orgDraftNoReq) || "015".equals(orgDraftNoReq)){
			callBackParam="013";
		}else if("014".equals(orgDraftNoReq) || "016".equals(orgDraftNoReq)){
			callBackParam="014";
		}
		trigger(bill,RcConstants.TYPE_EXCEPTIONNOTIFY,callBackParam,"",false);
	}
	
	/**
	 * 通用转发（031、036）处理
	 * @param bill
	 * @param methodName
	 * @param param
	 * @throws SQLException 
	 * @throws ServiceException
	 */
	public void commonSignUp(RgctBill bill, String methodName, String param) throws BizAppException {
		/**
		 * 原申请报文为“提示付款申请报文(020)”，当前票据状态为“提示付款待签收”：若报文中“提示付款签收标记”中内容为“同意签收”，则设置票据当前状态为“结束已结清” ；
		 * 若报文中“提示付款签收标记”中内容为“拒绝签收”， 则根据当前的时间修改票据状态： 
		 * a)若当前日期在提示付款期前（签收日期<到期日），设置票据当前状态为“提示付款已拒付（不可进行拒付追索）”。
		 * b)若当前日期在提示付款期内（到期日<=签收日期<=到期日+10），设置票据当前状态为“提示付款已拒绝（可拒付追索，可以追所有人）”。
		 * c)若当前日期已逾期（到期日+10<签收日期），若提示付款是在提示付款期内发起的，修改当前票据状态为“提示付款已拒绝（可拒付追索，只能追出票人，承兑人及其保证人）”；
		 * 原申请报文为“逾期提示付款申请报文(021)”，当前票据状态为“逾期提示付款待签收”：若报文中“逾期提示付款签收标记”中内容为“同意签收”，则设置票据当前状态为“结束已结清” ；
		 * 若报文中“逾期提示付款签收标记”中内容为“拒绝签收”，则根据当前的时间修改票据状态： 
		 * a)若当前日期在提示付款期内（到期日<=签收日期<=到期日+10），设置票据当前状态为“逾期提示付款已拒绝（可拒付追索，可以追所有人）”。
		 * b)若当前日期已逾期（到期日+10<签收日期），若逾期提示付款是在提示付款期内发起的，修改当前票据状态为“逾期提示付款已拒绝（可拒付追索，可以追所有人）”；
		 * 否则修改当前票据状态为“逾期提示付款已拒绝（可拒付追索，只能追出票人，承兑人及其保证人）”
		 */
		RgctBillHist hist = bill.getHist();
		RgctMethodDao methodDao=new RgctMethodDao();
		//报文回复日期- 最好不要去workday,避免报文补发的时候日期与实际报文日期不符。
		String signUpDate = bill.getHist().getSignDt();
//		final String orgnlMsgId = hist.getReqDraftId();
//		final String msgId = hist.getRespDraftId();
//		final String receiveBankNo=hist.getReceiveBankNo();
		boolean iFlag = false;
		
		if ((RcConstants.SIGN_NO.equals(param))
				&& (RcConstants.COMMON_PRESENTATION.equals(methodName) || RcConstants.COMMON_OVERDUE.equals(methodName))) {
			param = changePresentParam(methodName,param, bill);
		} else if ((RcConstants.SIGN_YES.equals(param))) {
			param = "1";
			iFlag = true;
		}
//		RgctMethod method = getRgctMethod(RcConstants.CA, RcConstants.TYPE_SIGNUP, methodName);
		RgctMethod method;
		RgctBill newBill = null;
		try {
			method = methodDao.getRgctMethod("commonSignup", methodName);
			RgctMethod virtualMethod = new RgctMethod();
			virtualMethod.setId(method.getId());
			virtualMethod.setInterfaceName(method.getInterfaceName());
			virtualMethod.setMethodCnName(method.getMethodCnName());
			virtualMethod.setMethodName(method.getMethodName());
			virtualMethod.setSubSystem(method.getSubSystem());
			virtualMethod.setIsAddEndorse(method.getIsAddEndorse());
			virtualMethod.setIsBackHistory(method.getIsBackHistory());
			virtualMethod.setIsCheckDel(method.getIsCheckDel());
			virtualMethod.setIsCheckStatus(method.getIsCheckStatus());
			virtualMethod.setIsAddObligee(method.getIsAddObligee());
			if (!RcConstants.SIGN_YES.equals(param)){// 拒绝不背书
				virtualMethod.setIsAddEndorse("0");
				if(!RcConstants.COMMON_PRESENTATION.equals(methodName) && !RcConstants.COMMON_OVERDUE.equals(methodName)){
					virtualMethod.setIsBackHistory("1");
				}
			}
			
			if(isSelfBank(bill.getHist().getFromBankNo()) && IDict.K_BILL_CLASS.K_ELEC_BILL.equals(bill.getInfo().getBillClass())){//系统内转发的031
				RgctTempHist rth = tempDao.getRgctTempHist(bill.getInfo().getTempHistId().toString());
				copyProperties(hist, rth);
				hist.setSignDt(signUpDate);
			}
			
			// 接收行接收
			hist.setIsLock(IDict.K_LOCK.K_LOCK_NO);
//			hist.setIsOurSign("1");
			hist.setIsAccpt("1");
			
			if (RcConstants.COMMON_GUARNT.equals(methodName)) {
				virtualMethod.setIsBackHistory("1");
			}
			
			
			if(RcConstants.COMMON_PRESENTATION.equals(methodName) || RcConstants.COMMON_OVERDUE.equals(methodName)){
				virtualMethod.setIsAddEndorse("1");
			}
			
			
			if(RcConstants.COMMON_PRESENTATION.equals(methodName) || RcConstants.COMMON_OVERDUE.equals(methodName)){
				newBill = changeStatus4ElecPresentation(bill, virtualMethod, param, param);
			}else{
				newBill = changeStatus(bill, virtualMethod, param);
			} 
			
			// 签收后回调
			if (RcConstants.COMMON_GUARNT.equals(methodName)) { // 设置保证状态
				RgctStatus tempStatus = getRgctStatus(bill, virtualMethod, param);
				if (RcConstants.SIGN_NO.equals(param)) {
					newBill.getHist().setRunStatus(tempStatus.getRunStatus());
				} else if (RcConstants.SIGN_YES.equals(param)) {
					newBill.getHist().setRunStatus(tempStatus.getAfterStatus());
				} 
//				assuService.changeAssuStatus031For017(bill, param);待实现20160813
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BizAppException(e);
		}
		
		// 签收后回调
		if (IDict.K_BILL_CLASS.K_ELEC_BILL.equals(newBill.getInfo().getBillClass())) {
//			newBill.getHist().setReqDraftId(orgnlMsgId);
//			newBill.getHist().setRespDraftId(msgId);
//			newBill.getHist().setReceiveBankNo(receiveBankNo);
			trigger(newBill,RcConstants.TYPE_SIGNUP,methodName,param,iFlag);
		}
	}
	
	/**
	 * 040状态变更
	 * @param billNo
	 * @param statusCode
	 * @param draftDate
	 * @throws BizAppException
	 */
	public void synchronizeBillStatus(String billNo,String statusCode,String draftDate) throws BizAppException {
		final String curStatus =RgctStatusMappingCache.getRgctStatusMappingCache().getBbspStatusCodebyEcdsStatusCode(statusCode);
		RgctBill bill = changeRcStatus(billNo, curStatus,draftDate);
		trigger(bill,RcConstants.TYPE_SYNCHSTATUS,curStatus,"",false);
	}

	private RgctBill changeRcStatus(final String billNo, final String curStatus,String draftDate)throws BizAppException{
		RgctBill bill;
		try {
			bill = this.getRgctBillByBillNo(billNo);
			RgctBillHist oldHist = bill.getHist();
			final String oldStatus = bill.getHist().getCurStatus();
			if(curStatus.equals(oldStatus)){//系统内双方都会收到人行下发的040，如果已经更改过状态，则不处理登记中心状态
				return bill;
			}else if (curStatus.equals(oldHist.getPreStatus())){
				RgctMethod method = new RgctMethodDao().getRgctMethod(RcConstants.TYPE_CANCEL, RcConstants.COMMON_ROLLBACK);
				processBill(bill, method, RcConstants.SIGN_NO);
				return bill;
			}
			// 增加历史状态
			RgctBillHist newHist = new RgctBillHist();
			BeanUtils.copyProperties(newHist, oldHist);
			newHist.setPreStatus(oldHist.getCurStatus());
			newHist.setCurStatus(curStatus.trim());
			if(draftDate != null){
				newHist.setSignDt(draftDate);
			}
			newHist.setIsLock(IDict.K_LOCK.K_LOCK_NO);
			newHist.setIsRegress(RcConstants.REGRESS_NO);//回购式转买断需要更改回购标记，否则转出背书状态切换会有误
			if(("J_20".equals(curStatus) || "M_20".equals(curStatus)) && newHist.getBillTrack() == null){
				newHist.setBillTrack("2");
				newHist.setObligeeAcctNo(oldHist.getToAcctNo());
				newHist.setObligeeBankNo(oldHist.getToBankNo());
				newHist.setObligeeCustNo(oldHist.getToName());
			}
			String histId = ServiceFactory.getSequenceService().getBillInfoHist();
			newHist.setHistId(histId);
			histDao.addRgctBillHist(newHist);
			RgctBillInfo info = bill.getInfo();
			info.setHistId(histId);
			info.setCurStatus(newHist.getCurStatus());
			info.setStorageBranchNo(newHist.getStorageBranchNo());
			info.setAcctBranchNo(newHist.getAcctBranchNo());
			infoDao.modifyRgctBillInfo(info);
			bill.setHist(newHist);
		} catch (Exception e) {
			e.printStackTrace();
			throw new BizAppException(e.getMessage());
		}
		
		return bill;
	}
	
	/**
	 * 034处理
	 * @param bill
	 * @param billHist
	 * @param methodName
	 * @throws ServiceException
	 */
	public void commonTransmission(RgctBill bill, List<RgctEndoHist> endoHist, String methodName) throws BizAppException {
		RgctBill newBill ;
//		String msgId = bill.getHist().getRespDraftId();
		try {
			RgctEndoHistDao endoDao=new RgctEndoHistDao();
			endoDao.deleteRgctEndoHist(bill.getInfo().getId());
			for (int i = 0; i < endoHist.size(); i++) {
				RgctEndoHist endo=(RgctEndoHist) endoHist.get(i);
				endoDao.addRgctEndoHist(endo);
			}
			if (isSelfBank(bill.getHist().getFromBankNo())) {
				//return;
	//			this.evictBillHist(bill.getHist());
				RgctTempHist temp =tempDao.getRgctTempHist(bill.getInfo().getTempHistId().toString());
	//			RgctTempHist temp = getRgctTempHistById(bill.getInfo().getTempHistId());
				BeanUtils.copyProperties(bill.getHist(), temp);
				//部分字段为拷贝待手动set
	//			BeanCoper.copyProperties(temp, bill.getHist(), null, null);
				//为了避免报文乱序造成登记中心hist的值还没有更新就传递到回调方法，将temp复制到hist
				//此处只有状态不正确
				newBill = bill;
			}else{
				//补充完善票据历史(补最后一条历史 20120104)
				//RgctBillHist curHist = bill.getHist();
				/*RgctBill preBill = complementationBillHist(bill.getInfo(),billHist);
				bill.setHist(curHist);
				updateDraftLogWithNoLock(bill, curHist.getDraftLogId());
				
				//复制上一条记录的持票人信息
				this.copyHoldMessage(preBill.getHist(), bill.getHist());*/
				//保存本次流程记录
	//			updateDraftLogWithNoLock(bill, bill.getHist().getDraftLogId());
				RgctTempHist th = new RgctTempHist();
				BeanUtils.copyProperties(th, bill.getHist());
				String histId = ServiceFactory.getSequenceService().getBillTempHist();
				th.setHistId(histId);
				
	//				th.setOverdueRs(bill.getHist().getOverDueRs());//字段名称不相同 add by wintersun
	//				th.setDraftLogId(draftId);//待处理
				tempDao.addRgctTempHist(th);
				//更新票据最新行为信息ID
				RgctBillInfo info = bill.getInfo();
				info.setTempHistId(th.getHistId());
				infoDao.modifyRgctBillInfo(info);
				RgctMethodDao methodDao=new RgctMethodDao();
				RgctMethod method = methodDao.getRgctMethod("commonTrans", methodName);	
				changeStatus(bill, method, null);
				newBill = getRgctBillByBillNo(bill.getInfo().getBillNo());
				String param = newBill.getHist().getCurStatus();
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new BizAppException(e.getMessage());
		}
		// 回调部分
		if (IDict.K_BILL_CLASS.K_ELEC_BILL.equals(newBill.getInfo().getBillClass())) {
			newBill.getHist().setInvcNb(bill.getHist().getInvcNb());
			newBill.getHist().setConferNo(bill.getHist().getConferNo());
			//根据endoHist最后一条判断保证类型
			if(endoHist.size()==0){
				newBill.getHist().setAssuType(GuarCodeConst.ASSU_TYPE_REG_1);
			}else if("002".equals(endoHist.get(endoHist.size()-1).getEndoType())){
				newBill.getHist().setAssuType(GuarCodeConst.ASSU_TYPE_ACPT_2);
			}else{
				newBill.getHist().setAssuType(GuarCodeConst.ASSU_TYPE_ENDO_3);
			}
			trigger(newBill,RcConstants.TYPE_TRANS,methodName,"",true);
		}
		
	}
	
	/**
	 * 032通用撤回 -- 根据通用撤回报文处理流程
	 * 转发032
	 * @param bill 登记中心票据信息
	 * param 通过原申请报文ID查询 传methodname
	 * @throws ServiceException
	 */
	public void commonCancel(RgctBill bill, String param) throws BizAppException {
//		final String orgnlMsgId  = bill.getHist().getReqDraftId();
		//boolean iFlag = RcConstants.ECDS_OP_FAIL.equals(param) ? false : true;
		String tempParam = bill.getHist().getCurStatus();
		String billClass = bill.getInfo().getBillClass();
		try{
			if (isSelfBank(bill.getHist().getFromBankNo())  || (IDict.K_BILL_CLASS.K_ENTY_BILL.equals(billClass))) {
				
			}else {
				RgctMethodDao methodDao=new RgctMethodDao();
				RgctMethod method = methodDao.getRgctMethod(RcConstants.TYPE_CANCEL, RcConstants.COMMON_ROLLBACK);	
				String curStatus = bill.getHist().getCurStatus();
				if ("11".equals(curStatus.substring(curStatus.indexOf("_") + 1, curStatus.length()))) {
					processBill(bill, method, "0");
					RgctBill newBill = this.getRgctBillById(bill.getInfo().getId());
					//第二次回退需基于第一回退后的最新hist
					changeStatus(newBill, method, null);
				}else{
					changeStatus(bill, method, null);
				}
				
			}
			if (IDict.K_BILL_CLASS.K_ELEC_BILL.equals(bill.getInfo().getBillClass())) {
				RgctBill newBill = this.getRgctBillById(bill.getInfo().getId());
				trigger(newBill,RcConstants.TYPE_CANCEL,param,"",true);
			}
			/*if (!RcConstants.ENTY_BILL.equals(billClass)) {
				bill.getHist().setReqDraftId(orgnlMsgId);
				trigger(bill,RcConstants.TYPE_CANCEL, RcConstants.COMMON_ROLLBACK, param, true);
			}*/
		}catch (Exception e) {
			e.printStackTrace();
			throw new BizAppException(e);
		}
		
		
	}	
	/**
	 * 031的033回复处理
	 * @throws SQLException 
	 */
	public void commonStatus(RgctBill bill, String methodName, String isSuccess, String signFlag, boolean isInner) throws BizAppException{
		boolean iFlag = false;		//操作是否成功标记
		String param=bill.getHist().getCurStatus();
		if(!"1".endsWith(isSuccess)){
		    param="0";
		}else{
		    if("0".equals(signFlag)){
		        param="11";  
		        if (bill.getHist().getCurStatus().startsWith("S")) {
		        	param = "12";
				}
		        if (bill.getHist().getCurStatus().startsWith("R")) {
					param = "13";
				}
		    }
		    
		}
		String callBackParam = param;
		RgctMethodDao methodDao=new RgctMethodDao();
		RgctMethod method;
		try {
			method = methodDao.getRgctMethod("commonStatus", methodName);
			RgctMethod virtualMethod = new RgctMethod();
			virtualMethod.setId(method.getId());
			virtualMethod.setInterfaceName(method.getInterfaceName());
			virtualMethod.setMethodCnName(method.getMethodCnName());
			virtualMethod.setMethodName(method.getMethodName());
			virtualMethod.setSubSystem(method.getSubSystem());
			virtualMethod.setIsAddEndorse(method.getIsAddEndorse());
			virtualMethod.setIsBackHistory(method.getIsBackHistory());
			virtualMethod.setIsCheckDel(method.getIsCheckDel());
			virtualMethod.setIsCheckStatus(method.getIsCheckStatus());
			virtualMethod.setIsAddObligee(method.getIsAddObligee());
			// 通用确认，直接置当前行为状态为空
			RgctBillHist hist = bill.getHist();
//			final String orgnlMsgId = hist.getReqDraftId();
//			final String msgId = hist.getRespDraftId();
//			final String receiveBankNo=hist.getReceiveBankNo();
			hist.setRunStatus("");
			hist.setIsLock(IDict.K_LOCK.K_LOCK_NO);
			hist.setSignDt(DateTimeUtil.getWorkdayString());
			if (RcConstants.COMMON_SIGNUP.equals(methodName)) {// 对于通用回复的确认			
				callBackParam = hist.getCurStatus().substring(0,1);	//发起方接收回复回调参数
				if(hist.getCurStatus().indexOf("BPC")>-1){//电子票据池特殊处理
					callBackParam = "BPC";
				}
				if(RcConstants.ECDS_OP_FAIL.equals(param)){
					if(RcConstants.COMES_FROM_WWW.equals(hist.getChannel()) && hist.getCurStatus().endsWith("11")) {
						// 如为网银端,则回退
						virtualMethod.setIsBackHistory(RcConstants.HISTORY_BACK);
						processBill(bill, virtualMethod, "0");
					} else {
						// 如为银行端,更新行为状态
						histDao.modifyRgctBillHist(hist);
//						updateRgctBillHist(hist);
					}
					tempHistBack(bill.getInfo().getId());
				} else {
					//回复报文确认成功
					//hist.setSignDt(DateTimeUtil.getWorkday());
					String method1 = "";
					iFlag = true;
					if (RcConstants.ECDS_REFUSE_SIGN_COMMON.equals(param)) {// 拒绝签收
						//callFlag = false;	//拒绝不回调
						virtualMethod.setIsBackHistory(RcConstants.HISTORY_BACK); // 拒绝签收,历史回退
						String curStatus = hist.getCurStatus();
						if (curStatus != null && !"".equals(curStatus)) {
							hist.setRunStatus(curStatus.substring(0, 1) + "_26");// 设置拒绝状态
						}
						/*if(!isInner && ("Q_08".equals(curStatus))){
							assuService.changeAssuStatus033For031(bill, signFlag );
						}*/
					} else if (RcConstants.ECDS_REFUSE_SIGN_PRESENT.equals(param) || RcConstants.ECDS_REFUSE_SIGN_OVERDUE.equals(param)) {
						// TODO: 提示付款及逾期提示付款 , 判断有误 GAOHENG
						method1 = RcConstants.COMMON_PRESENTATION;
						if(RcConstants.ECDS_REFUSE_SIGN_OVERDUE.equals(param)){
							method1 = RcConstants.COMMON_OVERDUE;
						}
						param = changePresentParam(method1, param, bill);
					} else {
						if ("Q_08".equals(param)) {
							virtualMethod.setIsBackHistory(RcConstants.HISTORY_BACK); // 保证签收,历史回退
							hist.setRunStatus("Q_02");
							
							if(!isInner){
//								assuService.changeAssuStatus033For031(bill, signFlag);先不处理待完成
							}
							
						} else {// 签收
							//iFlag = true;
							addEndorseFlag(callBackParam, virtualMethod); // 设置背书标记
						}
					}
					/**
					 * 由于通过rgctBillHist的fromBankNo/toBankNo来判断是否系统内,
			         * 在报文乱序的情况下是不对的,故改为通过原申请报文编号来判断申请方是行内还是行外.
					 * 如前一手为行外买入的电票, 报文乱序的情况下，hist会造成回退2次，此处加判断给以限制
					 */
					if (!isInner) {
						RgctTempHist rth = tempDao.getRgctTempHist(bill.getInfo().getTempHistId().toString());
						copyProperties(hist, rth);
						hist.setIsLock(IDict.K_LOCK.K_LOCK_NO);
						hist.setSignDt(DateTimeUtil.getWorkdayString());
						if(RcConstants.COMMON_PRESENTATION.equals(method.getMethodName()) || RcConstants.COMMON_OVERDUE.equals(method.getMethodName())){
							changeStatus4ElecPresentation(bill, virtualMethod, param, signFlag);
						}else{
							changeStatus(bill, virtualMethod, param);
						}
					}
				}
			}
			
			if (IDict.K_BILL_CLASS.K_ELEC_BILL.equals(bill.getInfo().getBillClass())) {
				String draftType=RcConstants.TYPE_STATUS_for031;        
				IRgctDraftLogService draftService = RcServiceFactory.getRgctDraftLogService();
				RgctDraftLog orgnlDraftLog = draftService.getRgctDraftLogByReqSid(bill.getInfo().getReqDraftId());
	            if (orgnlDraftLog != null) {
	            	String busiType=orgnlDraftLog.getFromDraftNo();
	            	 methodName=MsgUtil.getMethodName(busiType, bill.getHist().getIsRegress());
	            }
				RgctBill newBill = this.getRgctBillById(bill.getInfo().getId());
				//TODO:日志记录待完成
				System.out.println("回调开始======"+draftType+"===="+methodName);
				trigger(newBill,draftType,methodName,signFlag,iFlag);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BizAppException(e.getMessage());
		}
	} 
	
	//设置登记中心
	protected String getBillhistStatusSql(RgctMethod method, boolean bDelFlag,String param) throws BizAppException {
		IDB db = DBFactory.getDB();
		
		try {
			List<RgctStatus> list;
			if(param!=null){
				list = db.getObjectList("select * from trgct_status where  method_id= ? and param= ? ", RgctStatus.class, method.getId(),param);
			}else{
				list = db.getObjectList("select * from trgct_status where  method_id= ?", RgctStatus.class, method.getId());
			}
			List<String> sList = new ArrayList<String>();
			
			for (int i = 0; i < list.size(); i++) {
				RgctStatus status = (RgctStatus) list.get(i);
				String qStatus = status.getBeforeStatus();
				String runStatus = status.getRunStatus();
				if (qStatus != null && qStatus.length() > 0) {
					StringBuffer sb = new StringBuffer();
					sb.append("billhist.cur_status='");
					sb.append(qStatus);
					sb.append("'");

					sList.add(sb.toString());
				}
				if (runStatus != null && runStatus.length() > 0) {
					StringBuffer sb = new StringBuffer();
					sb.append("billhist.run_status='");
					sb.append(runStatus);
					sb.append("'");

					sList.add(sb.toString());
				}
			}

			StringBuffer sb = new StringBuffer();
			if (sList.size() > 0) {
				sb.append(" and ( ");
				for (int i = 0; i < sList.size(); i++) {
					if (i > 0) {
						sb.append(" or ");
					}
					sb.append(sList.get(i));
				}
				sb.append(" ) ");
			}

			if (!bDelFlag) {
				sb.append(" and ");
				sb.append("billinfo.del_flag='");
				sb.append(IDict.K_YORN.K_YORN_NO);
				sb.append("' ");
			}

			return sb.toString();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return "";
	}
	
	/**
	 * 检查前置条件
	 * 
	 * @param bill
	 * @param method
	 * @param param
	 * @param call
	 * @throws SQLException 
	 */
	public void checkPreStatus(RgctBill bill , RgctMethod method , String param ) throws BizAppException, SQLException {
		if (IDict.K_LOCK.K_LOCK_YES.equals(bill.getHist().getIsLock())) {
			throw new BizAppException("该票已锁定");
		}
		RgctStatus status = null;
//		evictBillHist(bill.getHist());
//		RgctStatusDao statusDao=new RgctStatusDao();
		if (!"1".equals(method.getIsCheckStatus()))
			return;
		
		if (param != null) {
			status = statusDao.getRgctStatus(method.getId(), bill.getHist().getCurStatus(), param);
		} else {
			status = statusDao.getRgctStatusList(method.getId(),null).get(0);
		}
		if (status == null) {
			throw new BizAppException("该票状态不支持此操作");
			/*List list = null;
			if (param != null) {
				list = statusDao.getStatusList(method.getId(), param);
			} else {
				list = statusDao.getStatusList(method.getId());
			}
			String validStatus = getRsDAO().getStatusAll(list);

			String curStatus = getRsDAO().getStatusAll(
					bill.getHist().getCurStatus());
			return new BizAppException(ErrorConst.ERR_RC_002, null, new String[] {
					bill.getInfo().getId().toString(), curStatus,
					method.getMethodCnName(), validStatus });*/
		}
	}
	
	/**
	 * 检查前置条件
	 * @param bill
	 * @param method
	 * @param param
	 * @param call
	 */
	public void checkPreCondi(RgctBill bill, RgctMethod method, String param) throws BizAppException {
		if (IDict.K_LOCK.K_LOCK_YES.equals(bill.getHist().getIsLock())) {
			throw new BizAppException("该票已锁定");
		}
		// 禁止背书 true:禁止背书/false:不禁止背书
		RgctBillHist hist=RcServiceFactory.getRgctBillHistService().getRgctBillHist(bill.getInfo().getHistId());
		boolean forbidFlag ="1".equals(hist.getForbidFlag())?true:false;
		if (forbidFlag) {
			throw new BizAppException("该票禁止背书.");
		}
		RgctStatus status = null;
		try {
			if ("1".equals(method.getIsCheckStatus())) {
				if (param != null) {
						status = statusDao.getRgctStatus(method.getId(), bill.getHist().getCurStatus(), param);
				} else {
					status = statusDao.getRgctStatusList(method.getId(), null).get(0);
				}
				if (status == null) {
					throw new BizAppException("该票状态不支持此操作");
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BizAppException(e.getMessage());
		}
	}
	
	/**
	 * 网银新增票据;质押、贴现、转入专用
	 * @param newBill
	 * @param method
	 * @param param
	 * @return
	 * @throws ServiceException
	 */
	public RgctBill addBill(RgctBill newBill, RgctMethod method, String param) throws BizAppException {

		try {
			String infoId = ServiceFactory.getSequenceService().getBillInfoId();
			String histId = ServiceFactory.getSequenceService().getBillInfoHist();
			
			RgctBillInfo newBillInfo = newBill.getInfo();
			newBillInfo.setId(infoId);
			newBillInfo.setIsAccpt(CommUtils.isSelfBank(newBillInfo.getDraweeBankNo())?"1":"0");
			String ebsNo = ServiceFactory.getTransPubService().generateEBSNO(newBillInfo);
			
			newBillInfo.setEbsNo(ebsNo);
			newBillInfo.setCreateTime(DateTimeUtil.getWorkdayString());
			
			newBillInfo.setDelFlag(IDict.K_YORN.K_YORN_NO);
			newBillInfo.setHistId(histId);
			
			RgctBillHist newHist = newBill.getHist();
			newHist.setHistId(histId);
			newHist.setRgctId(infoId);
			RgctStatusDao statusDao=new RgctStatusDao();
			RgctStatus rgctStatus = statusDao.getRgctStatusList(method.getId(), param).get(0);
			String curStatus = rgctStatus.getAfterStatus();
			newHist.setCurStatus(curStatus);
			newHist.setIsLock(IDict.K_LOCK.K_LOCK_NO);
			newHist.setStepName(method.getMethodCnName());
			newBillInfo.setCurStatus(curStatus);
			newBillInfo.setStorageBranchNo(newHist.getStorageBranchNo());
			newBillInfo.setAcctBranchNo(newHist.getAcctBranchNo());
//			RgctBillInfoDao infoDao=new RgctBillInfoDao();
			try {
				if (infoDao.addRgctBillInfo(newBillInfo) != 1) {
				      throw new BizAppException(ISysErrorNo.ERR_DBERR, "增加RgctBillInfo失败");
				}
			} catch (SQLException e) {
	             throw new BizAppException(ISysErrorNo.ERR_DBERR,"");
			}
//			RgctBillHistDao histDao=new RgctBillHistDao();
			try {
				if (histDao.addRgctBillHist(newHist) != 1) {
				      throw new BizAppException(ISysErrorNo.ERR_DBERR, "增加Param失败");
				}
			} catch (SQLException e) {
	             throw new BizAppException(ISysErrorNo.ERR_DBERR,"");
			}
			newBill.setInfo(newBillInfo);
			newBill.setHist(newHist);
			
			return newBill;
	       
		} catch (Exception ex) {
			throw new BizAppException(ex);
		}
	}
	
	/**
	 * 申请、撤销的033回调
	 */
	public void commonStatus(RgctBill bill, String methodName, String param) throws BizAppException {
		try {
			boolean iFlag = false;		//操作是否成功标记
			RgctMethodDao methodDao=new RgctMethodDao();
			RgctMethod method1 = methodDao.getRgctMethod("commonStatus", methodName);
			RgctMethod virtualMethod = new RgctMethod();
			virtualMethod.setId(method1.getId());
			virtualMethod.setInterfaceName(method1.getInterfaceName());
			virtualMethod.setMethodCnName(method1.getMethodCnName());
			virtualMethod.setMethodName(method1.getMethodName());
			virtualMethod.setSubSystem(method1.getSubSystem());
			virtualMethod.setIsAddEndorse(method1.getIsAddEndorse());
			virtualMethod.setIsBackHistory(method1.getIsBackHistory());
			virtualMethod.setIsCheckDel(method1.getIsCheckDel());
			virtualMethod.setIsCheckStatus(method1.getIsCheckStatus());
			virtualMethod.setIsAddObligee(method1.getIsAddObligee());
			// 通用确认，直接置当前行为状态为空
			RgctBillHist hist = bill.getHist();
//			final String orgnlMsgId = hist.getReqDraftId();
//			final String msgId = hist.getRespDraftId();
			hist.setRunStatus("");
			hist.setIsLock(IDict.K_LOCK.K_LOCK_NO);
			hist.setSignDt(DateTimeUtil.getWorkdayString());
			RgctBillHistDao histDao=new RgctBillHistDao();
			if (RcConstants.COMMON_DRAWBACK.equals(methodName)) {// 通用撤回的确认
//				callBackParam = hist.getCurStatus().substring(0,1);
				iFlag = true;
				if(RcConstants.ECDS_OP_FAIL.equals(param)){
					iFlag = false;
//					updateRgctBillHist(hist);
					histDao.modifyRgctBillHist(hist);
					tempHistBack(bill.getInfo().getId());
				} else {
					hist.setSignDt(DateTimeUtil.getWorkdayString());
					// 设置历史回退
					virtualMethod.setIsBackHistory(RcConstants.HISTORY_BACK);
					if("11".equals(hist.getCurStatus().substring(2,hist.getCurStatus().length()))  && (! "I_11".equals(hist.getCurStatus()))) //I_11缺少中间状态I_08 所有少回退一步
						processBill(bill, virtualMethod, "0");
					
					//撤销保证需要更新保证清单状态
					if("Q_08".equals(hist.getCurStatus())){
//						assuService.changeAssuStatus033For032(bill);//保证模块待实现
					} 
					
					changeStatus(bill, virtualMethod, param);
				}
			} else {// 对于请求的确认
//				callBackParam = "";
				/*if("rediscount1".equals(methodName)){
					bill.getHist().setSignDt(DateTimeUtil.getWorkday());
					bill.getInfo().setRemark("");
					updateRgctBillInfo(bill.getInfo());
				}*/
				if (RcConstants.CONFIRM_SUCCESS.equals(param) || "".equals(param) || param == null) {
					iFlag = true;
					hist.setSignDt(DateTimeUtil.getWorkdayString());
					if(isSelfBank(hist.getFromBankNo()) 
							&& (RcConstants.COMMON_RECOURSEAGREEMENT1.equals(methodName) 
									|| RcConstants.COMMON_RECOURSEAGREEMENT2.equals(methodName))) {
						histDao.modifyRgctBillHist(hist);
//						updateRgctBillHist(hist);
					} else {
						if(!IDict.K_BILL_CLASS.K_ENTY_BILL.equals(bill.getInfo().getBillClass())){
							RgctTempHist rth = tempDao.getRgctTempHist(bill.getInfo().getTempHistId().toString());
							copyProperties(hist, rth);
							hist.setIfInner(rth.getIsInner());
						}
						bill = changeStatus(bill, virtualMethod, param);
						//人行回复保证后需要反向更新保证表关联的hist_id
						if("Q_08".equals(bill.getHist().getCurStatus())){
//							assuService.changeAssuStatus033For017(bill);//保证模块待实现
						}
					}
				} else {
					// 通用确认失败
//					updateRgctBillHist(hist);
					histDao.modifyRgctBillHist(hist);
					
					tempHistBack(bill.getInfo().getId());
				}
				
				
			}
			if (IDict.K_BILL_CLASS.K_ELEC_BILL.equals(bill.getInfo().getBillClass())) {
				String draftType=RcConstants.TYPE_STATUS_forApply;        
				if(RcConstants.COMMON_DRAWBACK.equals(methodName)){//032撤销
					IRgctDraftLogService draftService = RcServiceFactory.getRgctDraftLogService();
					RgctDraftLog orgnlDraftLog = draftService.getRgctDraftLogByReqSid(bill.getInfo().getReqDraftId());
		            if (orgnlDraftLog != null) {
		            	String busiType=orgnlDraftLog.getDraftNoReq();
		            	 methodName=MsgUtil.getMethodName(busiType, bill.getHist().getIsRegress());
		            }
		            draftType=RcConstants.TYPE_STATUS_for032;
                }
				RgctBill newBill = this.getRgctBillById(bill.getInfo().getId());
				trigger(newBill,draftType,methodName,"",iFlag);
			}
		} catch (Exception e) {
//			log(e.getMessage());
			throw new BizAppException(e);
		}
	}
	
	
	/**
	 * 检查是否改变票据状态
	 * @param bill			登记中心票据
	 * @param switchFlag	开关设置
	 * @param methodName	票据阶段代码
	 * @return				boolean (是/否)
	 * @throws BizAppException 
	 */
	private boolean checkIfChangeStatus(RgctBill bill, String stageCode) throws BizAppException {
		boolean flag = false;
			if(!IDict.K_BILL_CLASS.K_ENTY_BILL.equals(bill.getInfo().getBillClass())){
				if("V".equals(stageCode) || "U".equals(stageCode)){
					if(!isSelfBank(bill.getHist().getToBankNo())) {
						flag = true;
					}
				}else if("Q".equals(stageCode)){
					flag = true;
				}else {
					if(!isSelfBank(bill.getHist().getFromBankNo())) {
						flag = true;
					}
				}
			} 
		return flag;
	}
	
	/**
	 * 票据临时信息回退
	 * 
	 * @param rgctId
	 * @throws  BizAppException  
	 */
	public void tempHistBack(String rgctId) throws BizAppException  {
		try {
			tempDao.deleteRgctTempHistByRgctId(rgctId);
		
			String currentTh = tempDao.getMaxTempHistId(rgctId);
			// 更新票据最新行为信息ID
	//		RgctBillInfo info = this.getRbDAO().getRgctBillInfoById(rgctId);
			RgctBillInfo info = infoDao.getRgctBillInfo(rgctId);
			info.setTempHistId(currentTh);
	//		this.updateRgctBillInfo(info);
			infoDao.modifyRgctBillInfo(info);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BizAppException(e);
		}
	}
	
	/**
	 * 根据拒绝参数设置提示付款/逾期提示付款参数
	 * 
	 * @param param
	 * @param bill
	 * @return param
	 * @throws ServiceException
	 */
	private String changePresentParam(String methodName, String param, RgctBill bill)
			throws BizAppException {
		try {
			Calendar cSign = Calendar.getInstance();
			Calendar cDue = (Calendar) cSign.clone();
			Calendar cEndorse = (Calendar) cSign.clone();
			Calendar cDueAdd10 = (Calendar) cSign.clone();
			
			cEndorse.setTime(DateTimeUtil.parseStringToDate(bill.getHist().getEndorseDt()));//提示付款日期
			cSign.setTime(DateTimeUtil.parseStringToDate(DateTimeUtil.getWorkdayString()));//签收日期
			cDue.setTime(DateTimeUtil.parseStringToDate(bill.getInfo().getDueDt()));//票面到期日
			cDueAdd10.setTime(DateTimeUtil.parseStringToDate(DateTimeUtil.getDate(bill.getInfo().getDueDt(), 10)));//逾期日+10天顺延节假日
			
			if (RcConstants.COMMON_PRESENTATION.equals(methodName)) {// 提示付款
				if (cSign.before(cDue)) {
					param = "4"; // 已拒付3 --- 提示付款回复日在到期日之前,不可进行拒付追索
					
				} else if ((cSign.after(cDue) && cSign.before(cDueAdd10))
						|| cSign.compareTo(cDue) == 0 || cSign.compareTo(cDueAdd10) == 0) {
					
					param = "3"; // 已拒付2 --- 回复日在提示付款期内,可以追所有人
				
				} else if (cSign.after(cDueAdd10)) {//回复日在逾期后
					
					if(hasPresentInTime(cDue,bill.getInfo().getBillNo())){//发起过申请
						
						param = "3"; // 已拒付2 --- 回复日在逾期后,但提示付款期内发起过申请,可以追所有人
					
					} else {//提示付款期内未发起过申请
						
						param = "2"; // 已拒付1 --- 只能追出票人，承兑人及其保证人
					}
				}
			} else if (RcConstants.COMMON_OVERDUE.equals(methodName)) {
				// 逾期提示付款
				//如果在提示付款期内曾经发起过提示付款或者被拒付过,可以追索所有人
				if (hasPresentInTime(cSign,bill.getInfo().getBillNo())) {
					param = "23"; // 已拒付2 --- 可以追所有人
				} else{
					param = "22"; // 已拒付1 --- 只能追出票人，承兑人及其保证人
				}
			}
		} catch (Exception e) {
			throw new BizAppException(e);
		}
		return param;
	}
	
	private boolean hasPresentInTime(Calendar dueDt,String billNo) throws BizAppException {
		boolean flag= false;
//		RcBaseSearchBean sb = new RcBaseSearchBean();
//		List<RgctStatus> statusList = new ArrayList<RgctStatus>();
//		
//		
//		RgctStatus status1 = new RgctStatus();
//		RgctStatus status2 = new RgctStatus();
//		status1.setBeforeStatus("R_08");
//		status2.setBeforeStatus("R_22");
//		statusList.add(status1);
//		statusList.add(status2);
		
//		sb.setBillNo(billNo);
//		sb.setEndorseDt(dueDt.getTime().toString());
//		sb.addSpecialOpertion("endorseDt", BaseSearchBean.MORE_AND_EQUAL);
		StringBuilder builder = new StringBuilder("select billinfo.* from trgct_bill_info billinfo, trgct_bill_hist billhist where billinfo.hist_id=billhist.id ");
		builder.append(" and billinfo.bill_no ='"+billNo+"'");
		builder.append(" and billinfo.endorse_dt >='"+dueDt.getTime().toString()+"'");
		builder.append(" and billhist.cur_status= ='R_08'");
		builder.append(" and billhist.cur_status= ='R_22'");
		IDB db = DBFactory.getDB();
		List billList=null;
		try {
			billList = db.getObjectList(builder.toString(), RgctBillInfo.class);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BizAppException(e.getMessage());
		}
		if(billList != null && !billList.isEmpty()){
			flag = true;
		}
		return flag;
	}
	
	/**
	 * 流程回退方法
	 * @param bill
	 * @param method
	 * @param param
	 * @throws SQLException 
	 */
	public void processBill(RgctBill bill, RgctMethod method, String param) throws BizAppException, SQLException {
		/*if (RcConstants.SWITCH_FLAG_INNER.equals(this.getSwitchFlag())) {
			// 设置当前持有人行号为本机构行号
			bill.getHist().setHoldBankNo(bankService.getSelfBankNo());
		}*/
		// param="0":表示拒绝签收,回退
		if ("0".equals(param)) {
			String rgctId = bill.getInfo().getId();
			RgctBillHist hist = bill.getHist();
			// 下料机回退
			//getRbDAO().reverseRgctCtrl(rgctId, hist.getId());
			// 历史回退
			String vHistId = hist.getValidHistId().toString();
			RgctBillHist newHist = new RgctBillHist();
			RgctBillHistDao histDao=new RgctBillHistDao();
			if (vHistId != null) {
//				RgctBillHist oldHist = histDao.getRgctBillHist(vHistId);
				RgctBillHist oldHist =histDao.getRgctBillHist(vHistId);
//				BeanCoper.copyProperties(oldHist, newHist, null, new String[]{"id"});
				try {
					BeanUtils.copyProperties(newHist,oldHist);
				} catch (Exception e) {
					e.printStackTrace();
					 throw new BizAppException(e.getMessage());
				}
			}
			newHist.setOperTime(DateTimeUtil.getWorkdayString());
			newHist.setStepName(method.getMethodCnName());
			newHist.setLastHistId(hist.getHistId());
//			histDao.addRgctBillHist(newHist);
			String histId = ServiceFactory.getSequenceService().getBillInfoHist();
			newHist.setHistId(histId);
			newHist.setIsLock("0");
			histDao.addRgctBillHist(newHist);
			// 更新RgctBillInfo的histId
			RgctBillInfoDao infoDao=new RgctBillInfoDao();
			RgctBillInfo info=infoDao.getRgctBillInfo(rgctId);
			info.setCurStatus(newHist.getCurStatus());
			info.setStorageBranchNo(newHist.getStorageBranchNo());
			info.setAcctBranchNo(newHist.getAcctBranchNo());
			info.setHistId(histId);
			infoDao.modifyRgctBillInfo(info);
//			getRbDAO().updateRgctBillHistId(rgctId, newHist.getId());
		}
	}
	
	public RgctBill changeStatus(RgctBill bill, RgctMethod method, String param) throws BizAppException {
		boolean notReg = !StringUtils.contains(bill.getHist().getCurStatus(), "A_");
		boolean notAcpt = !StringUtils.contains(bill.getHist().getCurStatus(), "B_");
		boolean notIsse = !StringUtils.contains(bill.getHist().getCurStatus(), "C_");
		boolean isAfterIsse = notReg && notAcpt && notIsse;
	    if (isAfterIsse && (bill.getHist().getFromName() == null
				|| bill.getHist().getToName() == null)) {
			 throw new BizAppException(IErrorNo.ERR_RC_008);
		}

		try {
			RgctStatus status = getRgctStatus(bill, method, param);
			RgctBillInfo info = bill.getInfo();
			String rgctId = info.getId();
			String histId = ServiceFactory.getSequenceService().getBillInfoHist();
			RgctBillHist newHist = new RgctBillHist();
			if (RcConstants.BACK_HISTORY_YES.equals(method.getIsBackHistory())) {
				// 历史回退
				String vHistId = bill.getHist().getValidHistId();
				if (vHistId != null && !"".equals(vHistId)) {
					RgctBillHist oldHist =histDao.getRgctBillHist(vHistId.toString());
//					RgctBillHist oldHist = getRbDAO().getBillHistById(vHistId);
//					BeanCoper.copyProperties(oldHist, newHist, null, null);
					BeanUtils.copyProperties(newHist, oldHist);
					// 清空锁票
					newHist.setIsLock(IDict.K_LOCK.K_LOCK_NO);
					newHist.setHistId(histId);
					newHist.setRgctId(rgctId);
					newHist.setOperTime(DateTimeUtil.getWorkdayString());
					newHist.setStepName(method.getMethodCnName());
					newHist.setLastHistId(bill.getHist().getHistId());
					newHist.setAssuId(bill.getHist().getAssuId());//对应的保证历史id
					// 添加当前行为状态
					if (status.getRunStatus() != null && !"".equals(status.getRunStatus())) {
						newHist.setRunStatus(status.getRunStatus());
					}
					histDao.addRgctBillHist(newHist);
//					getRbDAO().addRgctHistByGivenId(newHist);
					// 更新RgctBillInfo的histId
					info.setHistId(newHist.getHistId());
					info.setCurStatus(newHist.getCurStatus());
					info.setStorageBranchNo(newHist.getStorageBranchNo());
					info.setAcctBranchNo(newHist.getAcctBranchNo());
					infoDao.modifyRgctBillInfo(info);
//					getRbDAO().updateRgctBillInfo(info);
				} else {
					//如没有有效的历史ID，则直接删除该票
					info.setDelFlag(IDict.K_YORN.K_YORN_YES);
					infoDao.modifyRgctBillInfo(info);
//					getRbDAO().updateRgctBillInfo(info);
				}
			} else {
				BeanUtils.copyProperties(newHist, bill.getHist());
				newHist.setHistId(histId);
				newHist.setCurStatus(status.getAfterStatus());
				newHist.setRunStatus(status.getRunStatus());
				// 添加当前行为状态
				// 注：在转贴现申请的候出现这样的情况：在接收到对方转贴现申请034报文后commonTransmission()
				//     方法会跟据票号获取最近的历史信息，并且当这时是一个辙消历史时，HIST中RUNSTATUS有值并且为B_97，这时会把该值写到新历史信息的
				//     RunStatus里，经过检查发现这里将状态切换配置表rgct_status下的RunStatus里配置 的值为RunStatus=""时过虑了。也就是rgct_status表中配置的
				//     RunStauts为""时，这儿不更新Hist中的RunStatus，而去除该过虑的理由是：所有的状态切换（除非拒绝或辙消）都应该以rgct_status表中的配置为依据
				
				//newHist.setId(IdGenerator.getNextId());
				newHist.setRgctId(rgctId);
				// hist.setOperTime(new Date()); 使用当前营业日
				newHist.setOperTime(DateTimeUtil.getWorkdayString());
				newHist.setStepName(method.getMethodCnName());
				newHist.setPreStatus(bill.getHist().getCurStatus());
				newHist.setValidHistId(bill.getHist().getHistId());
				newHist.setLastHistId(bill.getHist().getHistId());
				
				// 设置当前持有人信息(拒付时增加背书历史，但持票人信息不变更)
				if (RcConstants.IS_ADD_ENDORSE.equals(method.getIsAddEndorse())){
					/*if((RcConstants.COMMON_PRESENTATION.equals(method.getMethodName())
							||RcConstants.COMMON_OVERDUE.equals(method.getMethodName()))&&!"1".equals(param)){
						//不变更持票人031
					}else{*/
						newHist.setHoldCustNo(bill.getHist().getToCustNo());						
						newHist.setHoldCustName(newHist.getToName());
						newHist.setHoldAcctNo(bill.getHist().getToAcctNo());
						newHist.setHoldBankNo(bill.getHist().getToBankNo());
						newHist.setEndoStatus(newHist.getCurStatus());
						newHist.setEndoHistId(newHist.getHistId());
					//}
				}
				if ("1".equals(method.getIsAddObligee())) {
					newHist.setObligeeCustNo(bill.getHist().getToName());
					newHist.setObligeeBankNo(bill.getHist().getToBankNo());
					newHist.setObligeeAcctNo(bill.getHist().getToAcctNo());
				}
				// 清空锁票
				newHist.setIsLock(IDict.K_LOCK.K_LOCK_NO);
//				newHist.setLockBrchId("");
				newHist.setLockFlowName("");
//				getRbDAO().addRgctHistByGivenId(newHist);
				histDao.addRgctBillHist(newHist);
				// 更新RgctBillInfo的histId
				info.setHistId(newHist.getHistId());
				//
				info.setCurStatus(newHist.getCurStatus());
				info.setStorageBranchNo(newHist.getStorageBranchNo());
				info.setAcctBranchNo(newHist.getAcctBranchNo());
				infoDao.modifyRgctBillInfo(info);
//				getRbDAO().updateRgctBillInfo(info);				
				// 增加背书				
				if (RcConstants.ADD_ENDO_YES.equals(method.getIsAddEndorse()) && IDict.K_BILL_CLASS.K_ELEC_BILL.equals(info.getBillClass())) {
					new RgctEndoHistDao().addRgctEndo(newHist);
				}
			}

			return new RgctBill(info, newHist);
		} catch (Exception ex) {
			throw new BizAppException(ex);
		}
	}
	
	private RgctStatus getRgctStatus(RgctBill bill, RgctMethod method, String param) throws SQLException {
		RgctStatus status = null;
		List list = null;
		if (param != null) {
			list = statusDao.getRgctStatusList(method.getId(), param);
		} else {
			list = statusDao.getRgctStatusList(method.getId(),null);
		}
		if (list == null || list.isEmpty()) {
			new BizAppException(IErrorNo.ERR_RC_003+method.getId());
		}
		status = (RgctStatus) list.get(0);
		return status;
	}
	
	private void addEndorseFlag(String param, RgctMethod method) {
		if ("C".equals(param)) {
			// 提示收票,增加背书
			method.setIsAddEndorse(RcConstants.IS_ADD_ENDORSE);
		} else if ("E".equals(param)) {
			// 背书
			method.setIsAddEndorse(RcConstants.IS_ADD_ENDORSE);
		} else if ("F".equals(param)) {
			// 买断式贴现
			method.setIsAddEndorse(RcConstants.IS_ADD_ENDORSE);
		} else if ("G".equals(param)) {
			// 回购式贴现
			method.setIsAddEndorse(RcConstants.IS_ADD_ENDORSE);
		} else if ("H".equals(param)) {
			// 回购式贴现赎回
			method.setIsAddEndorse(RcConstants.IS_ADD_ENDORSE);
		} else if ("I".equals(param)) {
			// 买断式转贴现
			method.setIsAddEndorse(RcConstants.IS_ADD_ENDORSE);
		} else if ("J".equals(param)) {
			// 回购式转贴现
			method.setIsAddEndorse(RcConstants.IS_ADD_ENDORSE);
		} else if ("K".equals(param)) {
			// 回购式转贴现赎回
			method.setIsAddEndorse(RcConstants.IS_ADD_ENDORSE);
		} else if ("L".equals(param)) {
			// 买断式再贴现
			method.setIsAddEndorse(RcConstants.IS_ADD_ENDORSE);
		} else if ("M".equals(param)) {
			// 回购式再贴现
			method.setIsAddEndorse(RcConstants.IS_ADD_ENDORSE);
		} else if ("N".equals(param)) {
			// 回购式再贴现赎回
			method.setIsAddEndorse(RcConstants.IS_ADD_ENDORSE);
		} else if ("X".equals(param)) {
			// 央行卖票
			method.setIsAddEndorse(RcConstants.IS_ADD_ENDORSE);
		} else if ("O".equals(param)) {
			// 质押
			method.setIsAddEndorse(RcConstants.IS_ADD_ENDORSE);
		} else if ("P".equals(param)) {
			// 质押解除
			method.setIsAddEndorse(RcConstants.IS_ADD_ENDORSE);
		} else if ("R".equals(param)) {
			// 提示付款
			method.setIsAddEndorse(RcConstants.IS_ADD_ENDORSE);
		} else if ("S".equals(param)) {
			// 逾期提示付款
			method.setIsAddEndorse(RcConstants.IS_ADD_ENDORSE);
		} else if ("U".equals(param)) {
			// 拒付追索同意清偿
			method.setIsAddEndorse(RcConstants.IS_ADD_ENDORSE);
		} else if ("V".equals(param)) {
			// 非拒付追索同意清偿
			method.setIsAddEndorse(RcConstants.IS_ADD_ENDORSE);
		} else if ("B".equals(param)) {
			// 提示承兑,增加背书
			method.setIsAddEndorse(RcConstants.IS_ADD_ENDORSE);
		}
	}
	
	/**
	 * 登记中心票据备份
	 * @param destBillHist 目标票据行为信息
	 * @param srcBillHist  源票据行为信息
	 */
	private void copyProperties(RgctBillHist destBillHist, RgctTempHist srcBillHist) {
		destBillHist.setBatchId(srcBillHist.getBatchId());
		destBillHist.setFromRole(srcBillHist.getFromRole());
		destBillHist.setFromAcctNo(srcBillHist.getFromAcctNo());
		destBillHist.setFromBankNo(srcBillHist.getFromBankNo());
		destBillHist.setFromBranchNo(srcBillHist.getFromBrchId());
		destBillHist.setFromCustNo(srcBillHist.getFromCustNo());
		destBillHist.setFromName(srcBillHist.getFromName());
		destBillHist.setFromSign(srcBillHist.getFromSign());
		destBillHist.setFromRemark(srcBillHist.getFromRemark());
		destBillHist.setFromOrgcode(srcBillHist.getFromOrgcode());
		destBillHist.setToAcctNo(srcBillHist.getToAcctNo());
		destBillHist.setToBankNo(srcBillHist.getToBankNo());
		destBillHist.setToCustNo(srcBillHist.getToCustNo());
		destBillHist.setToBranchNo(srcBillHist.getToBrchId());
		destBillHist.setToName(srcBillHist.getToName());
		destBillHist.setToRemark(srcBillHist.getToRemark());
		destBillHist.setToOrgcode(srcBillHist.getToOrgcode());
		destBillHist.setToRole(srcBillHist.getToRole());
		destBillHist.setEndorseDt(srcBillHist.getEndorseDt());
		destBillHist.setSignDt(srcBillHist.getSignDt());
		destBillHist.setInterestRate(srcBillHist.getInterestRate());
		destBillHist.setInterest(srcBillHist.getInterest());
		destBillHist.setDealMoney(srcBillHist.getDealMoney());
		destBillHist.setIsRegress(srcBillHist.getIsRegress());
		destBillHist.setRegressDt(srcBillHist.getRegressDt());
		destBillHist.setRegressPrice(srcBillHist.getRegressPrice());
		destBillHist.setIsRediscCenter(srcBillHist.getIsRediscCenter());
		destBillHist.setBackOpenDt(srcBillHist.getBackOpenDt());
		destBillHist.setBackEndDt(srcBillHist.getBackEndDt());
		destBillHist.setBackAmount(srcBillHist.getBackAmount());
		destBillHist.setBackRate(srcBillHist.getBackRate());
		destBillHist.setIfInner(srcBillHist.getIsInner());
		destBillHist.setSigner(srcBillHist.getSigner());
		destBillHist.setSignerSign(srcBillHist.getSignerSign());
		destBillHist.setSignFlag(srcBillHist.getSignFlag());
		destBillHist.setStepName(srcBillHist.getStepName());
		// destBillHist.setHoldAcctNo(srcBillHist.getHoldAcctNo());
		// destBillHist.setHoldBankNo(srcBillHist.getHoldBankNo());
		// destBillHist.setHoldCustName(srcBillHist.getHoldCustName());
		// destBillHist.setHoldCustNo(srcBillHist.getHoldCustNo());
		destBillHist.setChannel(srcBillHist.getComesFrom());
		destBillHist.setIsOnline(srcBillHist.getIsOnline());
		destBillHist.setOverdueRs(srcBillHist.getOverdueRs());
		destBillHist.setRejectCode(srcBillHist.getRejectCode());
		destBillHist.setRejectReason(srcBillHist.getRejectReason());
		destBillHist.setIsRediscCenter(srcBillHist.getIsRediscCenter());
		destBillHist.setIsDelegate(srcBillHist.getIsDelegate());
		destBillHist.setInAcctNo(srcBillHist.getInAcctNo());
		destBillHist.setInBankNo(srcBillHist.getInBankNo());
//		destBillHist.setPartnerCode(srcBillHist.getPartnerCode());
		destBillHist.setAssuId(srcBillHist.getAssuId());
		destBillHist.setForbidFlag(srcBillHist.getForbidFlag());
		destBillHist.setDraftLogId(srcBillHist.getDraftLogId());
		destBillHist.setRecourseId(srcBillHist.getRecourseId());
		destBillHist.setOldInterestRate(srcBillHist.getOldInterestRate());
		destBillHist.setOldDisDt(srcBillHist.getOldDisDt());
		destBillHist.setBillTrack(srcBillHist.getBillTrack());
		destBillHist.setAcctBranchNo(srcBillHist.getAcctBrchNo());
		destBillHist.setStorageBranchNo(srcBillHist.getStorageBrchNo());
		destBillHist.setBuyType(srcBillHist.getBuyType());
		destBillHist.setWorkingadsNo(srcBillHist.getWorkingadsNo());
		destBillHist.setInterestDueDt(srcBillHist.getInterestDueDt());
		destBillHist.setBillBeforeOwner(srcBillHist.getBillBeforeOwner());
		destBillHist.setWorkingadsName(srcBillHist.getWorkingadsName());
		destBillHist.setInterestDays(srcBillHist.getInterestDays());
		destBillHist.setDelayDays(srcBillHist.getDelayDays());
	}

	// 判断是否为本行
	public boolean isSelfBank(String bankNo) throws BizAppException {
		return CommUtils.isSelfBank(bankNo);
	}
	
	/**
	 * 发送报文
	 * 
	 * @param bill
	 * @param method
	 * @throws ServiceException
	 */
	public void sendDraft(RgctBill bill, RgctMethod method,String sendFlag,String signFlag) throws BizAppException {
		RgctTempHist th = new RgctTempHist();
		try {
			BeanUtils.copyProperties(th, bill.getHist());
			th.setIsInner(bill.getHist().getIfInner());
			String msgId = "";
//			th.setOverdueRs(bill.getHist().getOverDueRs());//字段名称不相同 add by wintersun
			String histId = ServiceFactory.getSequenceService().getBillTempHist();
			th.setHistId(histId);
//			tempDao.addRgctTempHist(th);
			//更新票据最新行为信息ID
			RgctBillInfo info = bill.getInfo();
			String orgnlMsgId=info.getReqDraftId();
			info.setTempHistId(th.getHistId());
			//锁票
			
			RgctBillHist oldHist=histDao.getRgctBillHist(info.getHistId());
			oldHist.setIsLock(IDict.K_LOCK.K_LOCK_YES);
			histDao.modifyRgctBillHist(oldHist);
			
			RgctBillHist hist =bill.getHist();
			DraftInfoVo draftInfo=new DraftInfoVo();
			
			draftInfo.setOrgnlMsgId(orgnlMsgId);
			draftInfo.setAmt(hist.getDealMoney());
			draftInfo.setRpdAmt(hist.getBackAmount());
			if(!StringUtils.isBlank(bill.getHist().getEndorseDt()))
			draftInfo.setApplyDt(DateTimeUtil.parseStringToDate(bill.getHist().getEndorseDt()));
			if("registerRequest".equals(method.getMethodName())){
				String forbidFlag = DraftConstants.RGCT_CODE_MAPPING_MAP.get("FORBID_FLAG_"+info.getInfoForbidFlag());
				draftInfo.setBanEndrsmtMk(forbidFlag);
			}else{
				String forbidFlag = DraftConstants.RGCT_CODE_MAPPING_MAP.get("FORBID_FLAG_"+hist.getForbidFlag());
				draftInfo.setBanEndrsmtMk(forbidFlag);
			}
			draftInfo.setBill(bill.getInfo());
//			draftInfo.setBtchNb(hist.getBatchId());//系统批次号大于10位，所有报文都不传该值
//			draftInfo.setBusiType();
//			draftInfo.setDraftCreDtTm();
//			draftInfo.setGuarnteeAddr();
			draftInfo.setInAcct(hist.getInAcctNo());
			draftInfo.setInBankNo(hist.getInBankNo());
			draftInfo.setInvcNb(info.getInvoiceNo());
			draftInfo.setIntrstRate(hist.getInterestRate());
			
//			draftInfo.setOverdueMark();//废字段
			draftInfo.setOverdueReason(hist.getOverdueRs());
			
//			draftInfo.setPrcCd();
//			draftInfo.setPrcMsg();
			
//			draftInfo.setPrxyPropstn();//代理 待处理
//			draftInfo.setRecourseAmt();
//			draftInfo.setRecourseReasonCode();
//			draftInfo.setRecourseType();
			draftInfo.setRejectCode(hist.getRejectCode());
			draftInfo.setRejectReason(hist.getRejectReason());
			draftInfo.setRpdAmt(hist.getBackAmount());
			if(!StringUtils.isBlank(bill.getHist().getBackEndDt()))
			draftInfo.setRpdDueDt(DateTimeUtil.parseStringToDate(hist.getBackEndDt()));
			draftInfo.setRpdIntrstRate(hist.getBackRate());
			
			draftInfo.setRpdMk(DraftConstants.RGCT_CODE_MAPPING_MAP.get("IS_REGRESS_"+bill.getHist().getIsRegress()));
			if(!StringUtils.isBlank(bill.getHist().getBackOpenDt()))
			draftInfo.setRpdOpenDt(DateTimeUtil.parseStringToDate(hist.getBackOpenDt()));
			
			draftInfo.setSettleMk(DraftConstants.RGCT_CODE_MAPPING_MAP.get("ONLINE_MARK_"+bill.getHist().getIsOnline()));
			draftInfo.setSgnUpMk(DraftConstants.RGCT_CODE_MAPPING_MAP.get("SIGN_FLAG_"+signFlag));
			draftInfo.setAccptncAgrmtNb(hist.getBatchId());
			if(!StringUtils.isBlank(bill.getHist().getSignDt()))
			draftInfo.setSignDt(DateTimeUtil.parseStringToDate(hist.getSignDt()));
			draftInfo.setBusiType(MsgUtil.getBusiType(method.getMethodName(),method.getParam()));
			
//			draftInfo.setTrfRef();
			draftInfo.setTxlCtrctNb(info.getConferNo());
			if(!StringUtils.isBlank(bill.getHist().getEndorseDt()))
				draftInfo.setOrgnlDraftCreDtTM(DateTimeUtil.parseStringToDate(hist.getEndorseDt()));
			if(RcConstants.DRAFT_APPLY.equals(sendFlag)){
			    msgId = MsgUtil.getMsgId("", bill.getHist().getFromBankNo());
				draftInfo.setFromAcctNo(hist.getFromAcctNo());
				EcdsBankData bankData = RcServiceFactory.getEcdsBankDataService().getEcdsBankData(hist.getFromBankNo());
				if(bankData!=null&&!StringUtils.isEmpty(bankData.getContinueRowNum())){
					draftInfo.setFromAgcyBankNo(bankData.getContinueRowNum());
				}
				draftInfo.setFromBankName(hist.getFromBankNo());
				draftInfo.setFromBankNo(hist.getFromBankNo());
				draftInfo.setFromElctrncSgntr(hist.getFromSign());
				draftInfo.setFromName(hist.getFromName());
				draftInfo.setFromOrgCode(hist.getFromOrgcode());
				draftInfo.setFromRemark(hist.getFromRemark());
				draftInfo.setFromRoleType(hist.getFromRole());
				draftInfo.setReceiveAcctNo(hist.getToAcctNo());
//				draftInfo.setReceiveBankName(hist.getToBankNo());
				draftInfo.setReceiveBankNo(hist.getToBankNo());
				draftInfo.setReceiveElctrncSgntr(hist.getSignerSign());
				draftInfo.setReceiveName(hist.getToName());
				draftInfo.setReceiveOrgCode(hist.getToOrgcode());
				draftInfo.setReceiveRemark(hist.getToRemark());
				draftInfo.setReceiveRoleType(hist.getToRole());
				if(!RcConstants.TYPE_CANCEL.equals(signFlag)){
					info.setReqDraftId(msgId);//只有申请类报文更新
				}
			}else{
			    msgId = MsgUtil.getMsgId("", bill.getHist().getToBankNo());
				draftInfo.setFromAcctNo(hist.getToAcctNo());
				EcdsBankData bankData = RcServiceFactory.getEcdsBankDataService().getEcdsBankData(hist.getToBankNo());
				if(!StringUtils.isEmpty(bankData.getContinueRowNum())){
					draftInfo.setFromAgcyBankNo(bankData.getContinueRowNum());
				}
				draftInfo.setFromBankName(hist.getToBankNo());
				draftInfo.setFromBankNo(hist.getToBankNo());
				draftInfo.setFromElctrncSgntr(hist.getSignerSign());
				draftInfo.setFromName(hist.getToName());
				draftInfo.setFromOrgCode(hist.getToOrgcode());
				draftInfo.setFromRemark(hist.getToRemark());
				draftInfo.setFromRoleType(hist.getToRole());
				
				draftInfo.setReceiveAcctNo(hist.getFromAcctNo());
//				draftInfo.setReceiveBankName(hist.getToBankNo());
				draftInfo.setReceiveBankNo(hist.getFromBankNo());
				draftInfo.setReceiveElctrncSgntr(hist.getFromSign());
				draftInfo.setReceiveName(hist.getFromName());
				draftInfo.setReceiveOrgCode(hist.getFromOrgcode());
				draftInfo.setReceiveRemark(hist.getFromRemark());
				draftInfo.setReceiveRoleType(hist.getFromRole());
			}
			draftInfo.setReqMsgId(msgId);
			infoDao.modifyRgctBillInfo(info);
			String draftLogId=RcServiceFactory.getDraftService().buildAndSend(draftInfo, method);
			th.setDraftLogId(draftLogId);
			tempDao.addRgctTempHist(th);
		} catch (Exception e) {
			e.printStackTrace();
			throw new BizAppException(e.getMessage());
		} 
		
	}
	
	/**
	 * 电票提示付款登记中心跳转比较特殊，单独抽出此方法
	 * 1、即使拒付也许要配置 背书历史
	 * 2、拒付后,from\to 应为转入时的信息（否则托收挑票时无法 确定原始票据来源）
	 * @param bill
	 * @param method
	 * @param param
	 * @param signFlag
	 * @return
	 * @throws ServiceException
	 */
	public RgctBill changeStatus4ElecPresentation(RgctBill bill, RgctMethod method, String param, String signFlag) throws BizAppException {
		boolean notReg = !StringUtils.contains(bill.getHist().getCurStatus(), "A_");
		boolean notAcpt = !StringUtils.contains(bill.getHist().getCurStatus(), "B_");
		boolean notIsse = !StringUtils.contains(bill.getHist().getCurStatus(), "C_");
		boolean isAfterIsse = notReg && notAcpt && notIsse;
	    if (isAfterIsse && (bill.getHist().getFromName() == null
				|| bill.getHist().getToName() == null)) {
			 throw new BizAppException(IErrorNo.ERR_RC_008);
		}

		try {
			RgctStatus status = getRgctStatus(bill, method, param);
			RgctBillInfo info = bill.getInfo();
			String rgctId = info.getId();
			RgctBillHist newHist = new RgctBillHist();
			String workDate = DateTimeUtil.getWorkdayString();
			//如果提示付款签收
			if(RcConstants.SIGN_YES.equals(signFlag)){
//				BeanCoper.copyProperties(bill.getHist(), newHist, null, null);
				BeanUtils.copyProperties(newHist, bill.getHist());
				newHist.setCurStatus(status.getAfterStatus());
				newHist.setRunStatus(status.getRunStatus());
				
				newHist.setRgctId(rgctId); 
				newHist.setOperTime(workDate);
				newHist.setStepName(method.getMethodCnName());
				newHist.setPreStatus(bill.getHist().getCurStatus());
				newHist.setValidHistId(bill.getHist().getHistId());
				newHist.setLastHistId(bill.getHist().getHistId());
				
				//设置当前持有人信息
			    newHist.setHoldCustNo(bill.getHist().getToCustNo());						
				newHist.setHoldCustName(newHist.getToName());
				newHist.setHoldAcctNo(bill.getHist().getToAcctNo());
				newHist.setHoldBankNo(bill.getHist().getToBankNo());
				newHist.setEndoStatus(newHist.getCurStatus());	 
				
				//设置权利人信息
				newHist.setObligeeCustNo(bill.getHist().getToName());
				newHist.setObligeeBankNo(bill.getHist().getToBankNo());
				newHist.setObligeeAcctNo(bill.getHist().getToAcctNo());
				
				// 清空锁票
				newHist.setIsLock(IDict.K_LOCK.K_LOCK_NO);
//				newHist.setLockBrchId("");
				newHist.setLockFlowName("");
				String histId = ServiceFactory.getSequenceService().getBillInfoHist();
				newHist.setHistId(histId);
				histDao.addRgctBillHist(newHist);
//				getRbDAO().addRgctHistAndBillBrch(newHist);

				// 更新RgctBillInfo的histId
				info.setHistId(newHist.getHistId());
				info.setCurStatus(newHist.getCurStatus());
				info.setAcctBranchNo(newHist.getAcctBranchNo());
				info.setStorageBranchNo(newHist.getStorageBranchNo());
				infoDao.modifyRgctBillInfo(info);
//				getRbDAO().updateRgctBillInfo(info);
				
			}else{//其他 为拒付(历史回退并且状态 2,3,4,22,23)
				String vHistId = bill.getHist().getValidHistId();
				String rejectCode = bill.getHist().getRejectCode();
				String rejectReason = bill.getHist().getRejectReason();
				if (vHistId != null && !"".equals(vHistId)) {
					RgctBillHist oldHist = histDao.getRgctBillHist(vHistId.toString());
//					BeanCoper.copyProperties(oldHist, newHist, null, null);
					BeanUtils.copyProperties(newHist, oldHist);
	                // 清空锁票
					newHist.setIsLock(IDict.K_LOCK.K_LOCK_NO);
					newHist.setRgctId(rgctId);
					newHist.setOperTime(DateTimeUtil.getWorkdayString());
					newHist.setStepName(method.getMethodCnName());
					newHist.setLastHistId(bill.getHist().getHistId());
					
					//更新状态
					newHist.setCurStatus(status.getAfterStatus());
					if (status.getRunStatus() != null && !"".equals(status.getRunStatus())) {
						newHist.setRunStatus(status.getRunStatus());
					}
					newHist.setRejectCode(rejectCode);
					newHist.setRejectReason(rejectReason);
					String histId = ServiceFactory.getSequenceService().getBillInfoHist();
					newHist.setHistId(histId);
					histDao.addRgctBillHist(newHist);
					// 更新RgctBillInfo的histId
					info.setHistId(newHist.getHistId());
					info.setCurStatus(newHist.getCurStatus());
					info.setAcctBranchNo(newHist.getAcctBranchNo());
					info.setStorageBranchNo(newHist.getStorageBranchNo());
					infoDao.modifyRgctBillInfo(info);
					
				} else {
					//如没有有效的历史ID，则直接删除该票
					info.setDelFlag("1");
					infoDao.modifyRgctBillInfo(info);
					//this.delete(new Long[]{rgctId});
				}
			}
			//设置背书信息
			addEndoHist(bill, status);
			return new RgctBill(info, newHist);
		} catch (Exception ex) {
			throw new BizAppException(ex);
		}
	}
	
	private void addEndoHist(RgctBill bill, RgctStatus status) {
		try{
			RgctBillHist newHist4Endo = new RgctBillHist();
			BeanUtils.copyProperties(newHist4Endo, bill.getHist());
			newHist4Endo.setCurStatus(status.getAfterStatus());
			new RgctEndoHistDao().addRgctEndo(newHist4Endo);
		}catch (Exception e){
//			LogTool.log_ecds("票号"+bill.getInfo().getBillNo()+"添加背书历史失败："+e.getMessage());
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 电票回调处理
	 * @param newBill
	 * @param draftType 报文类型
	 * @param methodName 报文名称
	 * @param isSign  签收、拒绝
	 * @param isSuccess 报文是否处理成功
	 * @throws BizAppException
	 */
	private  void trigger(RgctBill rgctBill, String draftType, String methodName,String isSign,boolean isSuccess) throws BizAppException {
		RgctTriggerDao triDao=new RgctTriggerDao();
		RgctTrigger rctrigger;
		try {
			rctrigger = triDao.getRgctTrigger(draftType, methodName);
			if(rctrigger!=null){
				if(RcConstants.TYPE_EXCEPTIONNOTIFY.equals(draftType)){
					ITrigger trigger = RcServiceFactory.getRcTriggerService(rctrigger.getSpringId());
					trigger.transFor035execute(rgctBill);
				}else if(RcConstants.TYPE_SYNCHSTATUS.equals(draftType)){
					ISysTrigger trigger =RcServiceFactory.getRcSysTriggerService(rctrigger.getSpringId());
					trigger.execute(rgctBill);
				}else{
					ITrigger trigger =RcServiceFactory.getRcTriggerService(rctrigger.getSpringId());
					if(trigger!=null){
						if(RcConstants.TYPE_TRANS.equals(draftType)){
							trigger.transFor034execute(rgctBill);
						}else if(RcConstants.TYPE_STATUS_for031.equals(draftType)){
							trigger.signFor033execute(rgctBill, isSign, isSuccess);
						}else if(RcConstants.TYPE_STATUS_forApply.equals(draftType)){
							trigger.applyFor033execute(rgctBill, isSuccess);
						}else if(RcConstants.TYPE_STATUS_for032.equals(draftType)){
							trigger.cancelFor033execute(rgctBill, isSuccess);
						}else if(RcConstants.TYPE_CANCEL.equals(draftType)){
							trigger.transFor032execute(rgctBill);
						}else if(RcConstants.TYPE_SIGNUP.equals(draftType)){
							trigger.transFor031execute(rgctBill, isSign);
						}
					}
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			//回调处理失败只记录日志
		}
		
	}
	
	//取票、岀池Rc中使用
	public void convertEndorsorInfo(RgctBill bill) {
		RgctBillHist hist = bill.getHist();
		final String fromAcctNo = hist.getFromAcctNo();
		final String fromCustNo = hist.getFromCustNo();
		final String fromName = hist.getFromName();
		final String toAcctNo = hist.getToAcctNo();
		final String toCustNo = hist.getToCustNo();
		final String toName = hist.getToName();
		hist.setToAcctNo(fromAcctNo);
		hist.setToCustNo(fromCustNo);
		hist.setToName(fromName);
		hist.setFromAcctNo(toAcctNo);
		hist.setFromCustNo(toCustNo);
		hist.setFromName(toName);
		bill.setHist(hist);
	}
	
	/**
	 * 通用转发
	 */
	public static final String TYPE_TRANS = "commonTrans"; //

	/**
	 * 通用确认
	 */
	public static final String TYPE_STATUS = "commonStatus";

	/**
	 * 通用签收
	 */
	public static final String TYPE_SIGNUP = "commonSignup";

	/**
	 * 通用撤回
	 */
	public static final String TYPE_CANCEL = "commonCancel";

	/**
	 * 通用业务通知
	 */
	public static final String TYPE_NOTIFY = "commonNotify";

	
}
