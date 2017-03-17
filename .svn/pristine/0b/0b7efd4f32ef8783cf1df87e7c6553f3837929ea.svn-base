package com.herongtech.rc.service.billflowinfo;

import java.sql.SQLException;
import java.util.ArrayList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.herongtech.console.cache.RgctStatusMappingCache;
import com.herongtech.console.core.common.json.Page;
import com.herongtech.console.core.common.search.BaseSearchBean;
import com.herongtech.console.core.constant.BillFlowConst;
import com.herongtech.console.core.constant.RcConstants;
import com.herongtech.console.core.util.CommUtils;
import com.herongtech.console.core.util.DateTimeUtil;
import com.herongtech.console.service.ServiceFactory;
import com.herongtech.console.service.interfaces.ISequenceService;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.rc.domain.bean.BillFlowInfo;
import com.herongtech.rc.domain.bean.BillFlowInfoSearchBean;

import com.herongtech.rc.domain.bean.BillFlowQueryResult;
import com.herongtech.rc.domain.bean.RgctBill;
import com.herongtech.rc.domain.bean.RgctBillHist;
import com.herongtech.rc.domain.bean.RgctBillInfo;
import com.herongtech.rc.domain.bean.RgctDraftLog;
import com.herongtech.rc.domain.dao.BillFlowInfoDao;
import com.herongtech.rc.draft.common.MsgUtil;
import com.herongtech.rc.service.RcServiceFactory;
import com.herongtech.rc.service.interfaces.IBillFlowInfoService;
import com.herongtech.rc.service.interfaces.IRgctDraftLogService;


import com.herongtech.sysconstant.ISysErrorNo;





public class BillFlowInfoService implements IBillFlowInfoService{
	private BillFlowInfoDao billFlowInfoDao = new BillFlowInfoDao();
	private static Map<String, String> busiMap = new HashMap<String, String>();
	private static Map<String, String> map = new HashMap<String, String>();
	static {
		/* 出票登记 */
		map.put("001", "0");
		/* 提示承兑 */
		map.put("002", "1");
		/* 承兑签收 */
		map.put("031002", "2");
		/* 提示收票 */
		map.put("003", "3");
		/* 收票签收--统一修改为转让签收 */
		map.put("031003", "8");
		/* 未用退回申请(网银端)*/
		map.put("004", "5");
		/* 未用退回（银行端）*/
		map.put("041004", "7");
		/* 背书转让申请 */
		map.put("010", "6");
		/* 背书签收--统一修改为转让签收 */
		map.put("031010", "8");
		/* 贴现申请 */
		map.put("011", "9");
		/* 贴现签收 */
		map.put("031011", "10");
		/* 质押申请 */
		map.put("018", "11");
		/* 质押签收 */
		map.put("031018", "12");
		/* 解质押申请 */
		map.put("019", "13");
		/* 解质押签收 */
		map.put("031019", "14");
		/* 收到解质押申请 */
		map.put("034019", "16");
		/* 发出托收 */
		map.put("020", "15");
		map.put("021", "15");
		/* 收到托收 */
		map.put("034020", "17");
		map.put("034021", "17");
		/* 托收付款 */
		map.put("031020", "18");
		map.put("031021", "18");
		/* 转入签收 */
		map.put("031013", "19");
		map.put("031025", "19");
		/* 转出申请 */
		map.put("013", "20");
		/* 再贴现申请 */
		map.put("015", "20");
		/* 收到承兑 */
		map.put("034002", "21");
		
		
		//接受方根据规则，用034+申请报文去关联原码值   003/010/019需要在031时进行覆盖
		/* 收票签收--统一修改为转让签收 */
		map.put("034003", "8");
		// 背书签收--统一修改为转让签收 
		map.put("034010", "8");
		// 贴现签收 
		map.put("034011", "10");
		// 质押签收 
		map.put("034018", "12");
		// 解质押签收 
		map.put("034019", "14");
		// 转入签收 
		map.put("034013", "19");
		map.put("034025", "19");
		 //承兑签收 
		map.put("034002", "2");
		
		
		
		
//		 busiType;//业务种类 1、收票2、背书3、提示付款 4、出票登记 5、提示承兑 6、贴现 7、质押 8、解质押 9、保证 10、撤票 
//		12、回购式贴现赎回 13、转贴现 14、回购式转贴现赎回 15、再贴现申请 16、回购式再贴现赎回
		busiMap.put("001", "1");//出票登记
		busiMap.put("002", "2");//提示承兑
		busiMap.put("003", "3");//收票
		busiMap.put("004", "4");//撤票
		busiMap.put("010", "5");//背书
		busiMap.put("011", "6");//贴现
		busiMap.put("012", "7");//回购式贴现赎回
		busiMap.put("013", "8");//转贴现
		busiMap.put("014", "9");//回购式转贴现赎回
		busiMap.put("015", "10");//再贴现
		busiMap.put("016", "11");//回购式再贴现赎回
		busiMap.put("017", "12");//保证
		busiMap.put("018", "13");//质押
		busiMap.put("019", "14");//解质押
		busiMap.put("020", "15");//提示付款
		busiMap.put("021", "15");//提示付款
		
		/*busiMap.put("003", "1");
		busiMap.put("010", "2");
		busiMap.put("020", "3");
		busiMap.put("021", "3");
		busiMap.put("001", "4");
		busiMap.put("002", "5");
		busiMap.put("011", "6");
		busiMap.put("018", "7");
		busiMap.put("019", "8");
		busiMap.put("017", "9");
		busiMap.put("013", "13");
		busiMap.put("014", "14");
		busiMap.put("015", "15");
		busiMap.put("016", "16");*/
	}
	/**ljt-------------------------------------start*/
	/**031-033处理 （只处理同业的票）
	 * orgnlMsgId 原申请报文id
	 * curstatus 是否成功
	 * prcMsg 处理信息
	 * 
	 * @throws BizAppException */
	@Override
	public void executeBillFlowInfo033For031(String orgnlMsgId, String curstatus,
			String prcMsg, boolean isInner, String signFlag, RgctBill bill,
			RgctDraftLog orgnlDraftLog) throws BizAppException {
		try {
			bill=RcServiceFactory.getRcBaseService().getRgctBillById(bill.getInfo().getId());
			List<BillFlowInfo> flowList = getBillFlow(orgnlMsgId, bill,BillFlowConst.OPER_SIGN);
			if(!flowList.isEmpty()){
				BillFlowInfo flowInfo=flowList.get(0);
				String workday = DateTimeUtil.getWorkday();
				flowInfo.setSignupDate(workday);
				flowInfo.setSignupInfo(RcConstants.SIGN_YES.equals(signFlag)?"签收":"拒绝");
				flowInfo.setSignupMark(signFlag);
				String status="";
				if("1".equals(curstatus)){
					if(RcConstants.SIGN_YES.equals(signFlag)){
						status=BillFlowConst.SIGN_STATUS_S;//11签收成功
					}else{
						status=BillFlowConst.SIGN_STATUS_REFUSE_S;//13拒绝成功
					}
				}else{
					if(RcConstants.SIGN_YES.equals(signFlag)){
						status=BillFlowConst.SIGN_STATUS_E;//12签收失败
					}else{
						status=BillFlowConst.SIGN_STATUS_REFUSE_E;//14拒绝失败
					}
				}
				flowInfo.setTransDate(workday);
				flowInfo.setStatus(status);
				flowInfo.setResultMsg(prcMsg);
				flowInfo.setOperResult(curstatus);
				String draftNoReq = flowInfo.getDraftNoReq();
				//接受方根据规则，用034+申请报文去关联原码值   
				//因 网银接口中定义的003/010/019这三种查询类型是签收，需要在031时进行替换
				if("003".equals(draftNoReq) || "010".equals(draftNoReq) || "019".equals(draftNoReq)){
					flowInfo.setBillStatus(map.get("031"+draftNoReq));
				}
				
//			flowInfo.setDraftNoReq(orgnlDraftLog.getDraftNoReq());
				String cureStatus =bill.getHist().getCurStatus();
				cureStatus = cureStatus.replaceAll("08", "02");
				flowInfo.setCurStatusString(RgctStatusMappingCache.getRgctStatusMappingCache().getRgctStatusMapping(cureStatus).getStatusName());
				if ("A_27".equals(cureStatus)) {
					flowInfo.setCurStatusString("票据被拒绝");
				}
				billFlowInfoDao.modifyBillFlowInfo(flowInfo);
			}
		} catch (SQLException e) {
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "031的回调033处理失败");
		}
		
	
	}	
	
	
	/**申请033处理
	 * orgnlMsgId 原申请报文id
	 * curstatus 是否成功
	 * prcMsg 处理信息
	 * 
	 * @throws BizAppException */
	@Override
	public void executeBillFlowInfo033ForCommonApply(String orgnlMsgId,
			String curstatus, String prcMsg, RgctBill bill,
			RgctDraftLog orgnlDraftLog) throws BizAppException {
		BillFlowInfo flowInfo;
		try {
			String draftNoReq = orgnlDraftLog.getDraftNoReq();//请求报文类型编号
			flowInfo = transFormBillFlowInfo(bill,busiMap.get(draftNoReq),BillFlowConst.OPER_REQUEST,orgnlMsgId);
			String date=DateTimeUtil.getWorkday();
			flowInfo.setApplyDate(date);
			flowInfo.setStatus("1".equals(curstatus) 
					? BillFlowConst.APPLY_STATUS_WAIT_SIGN
					: BillFlowConst.APPLY_STATUS_FAIL);
			flowInfo.setResultMsg(prcMsg);
			flowInfo.setOperResult(curstatus);
			flowInfo.setBillNo(bill.getInfo().getBillNo());
			flowInfo.setTransDate(date);
			String status = map.get(draftNoReq);
			flowInfo.setBillStatus(status);//票据状态
			flowInfo.setDraftNoReq(draftNoReq);//请求报文类型编号
			String cureStatus =bill.getHist().getCurStatus();
			cureStatus = cureStatus.replaceAll(cureStatus.substring(2, 4), "08");
			flowInfo.setCurStatusString(RgctStatusMappingCache.getRgctStatusMappingCache().getRgctStatusMapping(cureStatus).getStatusName());
			
			
			billFlowInfoDao.addBillFlowInfo(flowInfo);
		} catch (Exception e) {
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "申请的回调033处理失败");
		}
		
		
	}
	/**保存billflowinfo
	 * @throws BizAppException */
	@Override
	public void addBillFlowInfo(BillFlowInfo billFlowInfo) throws BizAppException {
		try {
			billFlowInfoDao.addBillFlowInfo(billFlowInfo);
		} catch (SQLException e) {
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "保存数据失败");
		}
		
	}
	/**
	 * 申请方收到转发的031
	 * @param orgnlMsgId
	 * @param signFlag
	 * @param bill
	 * @throws BizAppException 
	 * @throws ServiceException
	 */
	@Override
	public void executeBillFlowInfo031Trans(String orgnlMsgId, String signFlag,
			RgctBill bill) throws BizAppException {
		List<BillFlowInfo> list;
		try {
			bill= RcServiceFactory.getRcBaseService().getRgctBillById(bill.getInfo().getId());
			list = getBillFlow(orgnlMsgId, bill,BillFlowConst.OPER_REQUEST);
			if(!list.isEmpty()){
				BillFlowInfo flowInfo=list.get(0);
				flowInfo.setSignupDate(bill.getHist().getSignDt());
				flowInfo.setSignupInfo(RcConstants.SIGN_YES.equals(signFlag)?"签收":"拒绝");
				flowInfo.setSignupMark(signFlag);
				String status="";
				if(RcConstants.SIGN_YES.equals(signFlag)){
					status=BillFlowConst.APPLY_STATUS_SIGN;
				}else{
					status=BillFlowConst.APPLY_STATUS_REFUSE;
				}
				flowInfo.setStatus(status);
				String cureStatus =bill.getHist().getCurStatus();
				cureStatus = cureStatus.replaceAll("08", "02");
				flowInfo.setCurStatusString(RgctStatusMappingCache.getRgctStatusMappingCache().getRgctStatusMapping(cureStatus).getStatusName());
				billFlowInfoDao.modifyBillFlowInfo(flowInfo);
				if(CommUtils.isSelfBank(orgnlMsgId.substring(0,12))){
					//系统内更改签收方的flow当前票据状态（处理系统内先收到033，后回031的情况）
					List<BillFlowInfo> flowList = getBillFlow(orgnlMsgId, bill,BillFlowConst.OPER_SIGN);
					if(!flowList.isEmpty()){
						BillFlowInfo flow=flowList.get(0);
						flow.setCurStatusString(RgctStatusMappingCache.getRgctStatusMappingCache().getRgctStatusMapping(cureStatus).getStatusName());
						billFlowInfoDao.modifyBillFlowInfo(flow);
					}
				}
			}
		} catch (SQLException e) {
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "申请方收到转发的031处理失败");
		}
			
			
		
	}
	
	/**申请方032的033
	 * orgnlMsgId 原申请报文id
	 * curstatus 是否成功
	 * prcMsg 处理信息
	 * 
	 * @throws BizAppException */
	@Override
	public void executeBillFlowInfo033For032(String orgnlMsgId, String curstatus,
			String prcMsg, RgctBill bill) throws BizAppException {	
		List<BillFlowInfo> list;
		try {
			bill= RcServiceFactory.getRcBaseService().getRgctBillById(bill.getInfo().getId());
			list = getBillFlow(orgnlMsgId, bill,BillFlowConst.OPER_REQUEST);
			if(!list.isEmpty()){
				BillFlowInfo flowInfo=list.get(0);
				flowInfo.setStatus("1".equals(curstatus)?BillFlowConst.APPLY_STATUS_CANCEL_S:BillFlowConst.APPLY_STATUS_CANCEL_E);
				flowInfo.setResultMsg(prcMsg);
				flowInfo.setOperResult(curstatus);
				String cureStatus =bill.getHist().getCurStatus();
				cureStatus = cureStatus.replaceAll("11", "08");
				flowInfo.setCurStatusString(RgctStatusMappingCache.getRgctStatusMappingCache().getRgctStatusMapping(cureStatus).getStatusName());
				billFlowInfoDao.modifyBillFlowInfo(flowInfo);
				if(CommUtils.isSelfBank(orgnlMsgId.substring(0,12))){//申请方是本行
					//系统内更改签收方的flow当前票据状态（处理系统内先收到032，后收到转发的033）
					List<BillFlowInfo> flowList = getBillFlow(orgnlMsgId, bill,BillFlowConst.OPER_SIGN);
					if(!flowList.isEmpty()){
						BillFlowInfo flow=flowList.get(0);
						flow.setStatus(BillFlowConst.APPLY_STATUS_CANCEL);
						flow.setCurStatusString(RgctStatusMappingCache.getRgctStatusMappingCache().getRgctStatusMapping(cureStatus).getStatusName());
						billFlowInfoDao.modifyBillFlowInfo(flow);
					}
				}
			}
			
		} catch (SQLException e) {
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "申请方收到032的033处理失败");
		}
	}
	
	/**ljt-----------------------------------end*/
	
	
	/**sys-------------------------------------start*/
	/**
	 * 签收方收到转发的032
	 * @param orgnlMsgId 原申请报文ID
	 * @param bill
	 * @throws ServiceException
	 */
	public void executeBillFlowInfo032Trans(String orgnlMsgId,RgctBill bill)  throws BizAppException {
		//申请方处理
		try{
			BillFlowInfoDao billFlowDao=new BillFlowInfoDao();
			bill=RcServiceFactory.getRcBaseService().getRgctBillById(bill.getInfo().getId());//获得登记中心
			List<BillFlowInfo> flowList = getBillFlow(orgnlMsgId, bill,BillFlowConst.OPER_SIGN);
			if(!flowList.isEmpty()){
				BillFlowInfo flowInfo=flowList.get(0);
				flowInfo.setStatus(BillFlowConst.APPLY_STATUS_CANCEL);
				String preStatus=bill.getHist().getPreStatus();
//				String cureStatus =bill.getHist().getCurStatus();
//				cureStatus = cureStatus.replaceAll("11", "08");
				flowInfo.setCurStatusString(RgctStatusMappingCache.getRgctStatusMappingCache().getRgctStatusMapping(preStatus).getStatusName());
				billFlowDao.modifyBillFlowInfo(flowInfo);
			}
		}catch (Exception e) {
			throw new BizAppException("032-033回复，票号"+bill.getInfo().getBillNo()+"，错误信息："+e.getMessage());
		}
	}
	
	/**
	 *  035-033处理-- 因为034处理时 直接拒绝，不会保存申请流水，所以在此处进行处理
	 * reqSid 原申请报文id
	 * curstatus 是否成功
	 * prcMsg 处理信息
	 * 
	 * @throws ServiceException
	 */
	public void executeBillFlowInfo033For035(String reqSid,String curstatus,String prcMsg,RgctBill bill)  throws BizAppException {
		
		if("1".equals(curstatus)){
			try{
				BillFlowInfoDao billFlowDao=new BillFlowInfoDao();
				bill=RcServiceFactory.getRcBaseService().getRgctBillById(bill.getInfo().getId());//获得登记中心
				IRgctDraftLogService service = RcServiceFactory.getRgctDraftLogService();
				RgctDraftLog draft = service.getDraftLogByReqSid(reqSid);
				BillFlowInfo flow=this.transFormBillFlowInfo(bill, busiMap.get(draft.getDraftNoReq()),BillFlowConst.OPER_SIGN,reqSid);
				flow.setStatus(BillFlowConst.SIGN_STATUS_035);
				flow.setCurStatusString(RgctStatusMappingCache.getRgctStatusMappingCache().getRgctStatusMapping(bill.getHist().getCurStatus()).getStatusName());
				billFlowDao.addBillFlowInfo(flow);
			}catch (Exception e) {
				throw new BizAppException("035的033回复，票号"+bill.getInfo().getBillNo()+"，错误信息："+e.getMessage());
			}
		}
	}
	
	/**
	 * 收到转发的035
	 * @param orgnlMsgId
	 * @param bill
	 * @throws BizAppException
	 */
	public void executeBillFlowInfo035Trans(String orgnlMsgId,RgctBill bill,String exceptionCN)  throws BizAppException {
		//申请方处理
		try{
			BillFlowInfoDao billFlowDao=new BillFlowInfoDao();
			bill=RcServiceFactory.getRcBaseService().getRgctBillById(bill.getInfo().getId());
			List<BillFlowInfo> list = getBillFlow(orgnlMsgId, bill,BillFlowConst.OPER_REQUEST);
			if(!list.isEmpty()){
				BillFlowInfo flowInfo=list.get(0);
				flowInfo.setStatus(BillFlowConst.APPLY_STATUS_035);
				String cureStatus =bill.getHist().getCurStatus();
				cureStatus = cureStatus.replaceAll("11", "08");
				flowInfo.setCurStatusString(RgctStatusMappingCache.getRgctStatusMappingCache().getRgctStatusMapping(cureStatus).getStatusName());
				flowInfo.setResultMsg(exceptionCN);
				billFlowDao.modifyBillFlowInfo(flowInfo);
				if(CommUtils.isSelfBank(orgnlMsgId.substring(0,12))){//申请方是本行
					//系统内更改签收方的flow当前票据状态（处理系统内先收到035-033，后收到转发的035）
					List<BillFlowInfo> flowList = getBillFlow(orgnlMsgId, bill,BillFlowConst.OPER_SIGN);
					if(!flowList.isEmpty()){
						BillFlowInfo flow=flowList.get(0);
						flow.setStatus(BillFlowConst.SIGN_STATUS_035);
						flow.setCurStatusString(RgctStatusMappingCache.getRgctStatusMappingCache().getRgctStatusMapping(cureStatus).getStatusName());
						billFlowDao.modifyBillFlowInfo(flow);
					}
				}
			}
		}catch (Exception e) {
			throw new BizAppException("收到转发的035，票号"+bill.getInfo().getBillNo()+"，错误信息："+e.getMessage());
		}
		
	}
	
	/**
	 * 004-033处理
	 * orgnlMsgId 原申请报文id
	 * curstatus 是否成功
	 * prcMsg 处理信息
	 * 
	 * @throws BizAppException
	 */
	public void executeBillFlowInfo033For004(String orgnlMsgId,String curstatus,String prcMsg,RgctBill bill,RgctDraftLog orgnlDraftLog)  throws BizAppException {
		//申请方处理
		try{
			BillFlowInfoDao billFlowDao=new BillFlowInfoDao();
			String draftNoReq = orgnlDraftLog.getDraftNoReq();
			BillFlowInfo flowInfo = transFormBillFlowInfo(bill,busiMap.get(draftNoReq),BillFlowConst.OPER_REQUEST,orgnlMsgId);
			
			flowInfo.setStatus("1".equals(curstatus)?BillFlowConst.APPLY_STATUS_DESTRUCTION_S:BillFlowConst.APPLY_STATUS_DESTRUCTION_E);
			flowInfo.setResultMsg(prcMsg);
			flowInfo.setOperResult(curstatus);
			
			String status = map.get(orgnlDraftLog.getDraftNoReq());
			flowInfo.setBillStatus(status);
			flowInfo.setDraftNoReq(orgnlDraftLog.getDraftNoReq());
			String cureStatus =bill.getHist().getCurStatus();
			//cureStatus = cureStatus.replaceAll("11", "08");
			flowInfo.setCurStatusString(RgctStatusMappingCache.getRgctStatusMappingCache().getRgctStatusMapping(cureStatus).getStatusName());
			billFlowDao.addBillFlowInfo(flowInfo);
		}catch (Exception e) {
			throw new BizAppException("004-033回复，票号"+bill.getInfo().getBillNo()+"，错误信息："+e.getMessage());
		}
		
	}
	
	/**
	 * 034BillFlowInfo
	 * @param busiType 业务种类
	 * @param rgctBill
	 * @return
	 * @throws ServiceException
	 */
	public void executeBillFlowInfo034Trans(RgctBill rgctBill,String busiType, String reqSid)throws Exception {
		
		BillFlowInfo billFlowInfo =transFormBillFlowInfo(rgctBill, busiMap.get(busiType), BillFlowConst.OPER_SIGN, reqSid);
		String key="034"+busiType;//不影响普通网银增加此操作
//		String key="034"+rdl.getFromDraftNo();//不影响普通网银增加此操作
		
		String status = map.get(key);
		billFlowInfo.setBillStatus(status);
		billFlowInfo.setDraftNoReq(busiType);
//		billFlowInfo.setDraftNoReq(rdl.getFromDraftNo());
		billFlowInfo.setStatus(BillFlowConst.SIGN_STATUS_WAIT);
		billFlowInfoDao.addBillFlowInfo(billFlowInfo);
		
	}
	/**sys-----------------------------------end*/
	
	
/**----------------------------公用start---------------------------------------------------------*/

	/**
	 * 构建BillFlowInfo
	 * @param busiType 业务种类
	 * @param rgctBill
	 * @return
	 * @throws Exception 
	 * @throws ServiceException
	 */
	public BillFlowInfo transFormBillFlowInfo(RgctBill rgctBill,String busiType,String operType,String reqSid)throws Exception {
		ISequenceService sequenceService=ServiceFactory.getSequenceService();
		if(BillFlowConst.OPER_SIGN.equals(operType) && !MsgUtil.isSelfBank(rgctBill.getHist().getFromBankNo()) ){
			rgctBill=RcServiceFactory.getRcBaseService().getRgctBillById(rgctBill.getInfo().getId());
		}
		BillFlowInfo billFlowInfo = new BillFlowInfo();
		// 处理信息
		billFlowInfo.setId(sequenceService.getPrimaryKeyValue());
		billFlowInfo.setOperResult("1");
		billFlowInfo.setResultMsg("操作成功");//结果信息
		RgctBillInfo rgctBillInfo = rgctBill.getInfo();
		RgctBillHist rgctBillHist = rgctBill.getHist();
		billFlowInfo.setBillNo(rgctBillInfo.getBillNo());
		billFlowInfo.setRgctId(rgctBillInfo.getId());
//		billFlowInfo.setHistId(rgctBillHist.getId());
		
		billFlowInfo.setFromName(rgctBillHist.getFromName());
		billFlowInfo.setFromAcctNo(rgctBillHist.getFromAcctNo());
		billFlowInfo.setFromBankNo(rgctBillHist.getFromBankNo());
		billFlowInfo.setFromCustNo(rgctBillHist.getFromCustNo());
		billFlowInfo.setToName(rgctBillHist.getToName());
		billFlowInfo.setToAcctNo(rgctBillHist.getToAcctNo());
		billFlowInfo.setToBankNo(rgctBillHist.getToBankNo());
		billFlowInfo.setToCustNo(rgctBillHist.getToCustNo());
		billFlowInfo.setHoldCustNo(rgctBillHist.getHoldCustNo());
		billFlowInfo.setHoldCustName(rgctBillHist.getHoldCustName());
		billFlowInfo.setHoldAcctNo(rgctBillHist.getHoldAcctNo());
		billFlowInfo.setHoldBankNo(rgctBillHist.getHoldBankNo());
		
		billFlowInfo.setCreateTime(DateTimeUtil.getWorkdayString());
		
//		billFlowInfo.setDraftNoReq("010");
		billFlowInfo.setIsOnline(rgctBillHist.getIsOnline());

		billFlowInfo.setInAcctNo(rgctBillHist.getInAcctNo());
		billFlowInfo.setInBankNo(rgctBillHist.getInAcctNo());
		billFlowInfo.setRemark(rgctBillHist.getFromRemark());
		
		billFlowInfo.setRemitter(rgctBillInfo.getRemitter());
		billFlowInfo.setApplyDate(rgctBillHist.getEndorseDt());
		billFlowInfo.setBusiType(busiType);
		billFlowInfo.setBillType(rgctBillInfo.getBillType());
		billFlowInfo.setBillMoney(rgctBillInfo.getBillMoney());
		billFlowInfo.setIssueDt(rgctBillInfo.getIssueDt());
		billFlowInfo.setDueDt(rgctBillInfo.getDueDt());
		billFlowInfo.setAcceptor(rgctBillInfo.getAcceptor());
		billFlowInfo.setAcceptorAcct(rgctBillInfo.getAcceptorAcct());
		billFlowInfo.setAcceptorBankName(rgctBillInfo.getAcceptorBankName());
		billFlowInfo.setBanEndorsementMark(rgctBillInfo.getInfoForbidFlag());
//		billFlowInfo.setPrintStaus("0");
//		billFlowInfo.setStatus("1");
		billFlowInfo.setOperType(operType);
		billFlowInfo.setReqSid(reqSid);
		billFlowInfo.setTransDate(DateTimeUtil.getWorkday());
//		billFlowInfo.setTransfor(transfor)
		String cureStatus =rgctBillHist.getCurStatus();
		cureStatus = cureStatus.replaceAll("11", "08");
//		IRgctStatusMappingDAO dao = SourceTemplate.getBean("RgctStatusMappingDao");
		billFlowInfo.setCurStatusString(RgctStatusMappingCache.getRgctStatusMappingCache().getRgctStatusMapping(cureStatus).getStatusName());
		return billFlowInfo;
	}
	/**
	 * @param reqSid
	 * @param bill
	 * @return
	 * @throws BizAppException 
	 */
	private List<BillFlowInfo> getBillFlow(String reqSid, RgctBill bill ,String operType) throws BizAppException {
		List<BillFlowInfo> list = new ArrayList<BillFlowInfo>();
		try {
			list = billFlowInfoDao.getBillFlow(reqSid,bill ,operType);
		} catch (SQLException e) {
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "查询数据失败");
		}
		return list;
	}
	@Override
	public Page searchRgctBillInfoByBillFlowInfoSearchBean(BillFlowInfoSearchBean billFlowInfoSearchBean, Page page) {
//			List<EcdsQueryResult> list = billFlowInfoDao.queryRgctBillInfoByBillFlowInfoSearchBean(billFlowInfoSearchBean, page);
//			page.setList(list);
		
		return null;
	}
	/**----------------------------公用end---------------------------------------------------------*/
	/**
	 * 提供接口查询list
	 * @throws Exception 
	 */
	public List<BillFlowQueryResult> queryBillFlowResult(BillFlowInfoSearchBean query,Page page) throws Exception{
		query.setDfaultSrchTbAliasName("bfi");
//		query.addProperty2TableObj("endEndt", "bfi");
		query.addSpecialOpertion("endEndt", BaseSearchBean.LESS_AND_EQUAL);
		query.addSqlPropretyMapping("endEndt", "createTime");
		query.addSpecialOpertion("beginDate", BaseSearchBean.LESS_AND_EQUAL);
		query.addSqlPropretyMapping("beginDate", "createTime");
		query.addSqlPropretyMapping("type", "busiType");
		query.addSqlPropretyMapping("custAccount", "fromAcctNo");
		return billFlowInfoDao.queryBillFlowResult(query,page);
	}

}
