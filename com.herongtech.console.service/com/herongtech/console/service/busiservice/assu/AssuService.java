/********************************************
 * 文件名称: AssuService.java
 * 系统名称: 票据管理平台V2.0
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: 2.0.0.1
 * 开发人员: dq
 * 开发时间: 2016-11-18 下午02:48:52
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期    修改人员    修改说明
 *******************************************/
package com.herongtech.console.service.busiservice.assu;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.herongtech.console.core.common.json.Page;
import com.herongtech.console.core.util.CommUtils;
import com.herongtech.console.core.util.DateTimeUtil;
import com.herongtech.console.core.util.ResourceUtil;
import com.herongtech.console.domain.assu.bean.AssuApplyInfo;
import com.herongtech.console.domain.assu.bean.AssuBillInfo;
import com.herongtech.console.domain.assu.bean.AssuSearchBean;
import com.herongtech.console.domain.assu.dao.AssuApplyInfoDao;
import com.herongtech.console.domain.assu.dao.AssuBillInfoDao;
import com.herongtech.console.domain.bean.Branch;
import com.herongtech.console.domain.bean.UserLoginfo;
import com.herongtech.console.service.ServiceFactory;
import com.herongtech.console.service.interfaces.IBranchService;
import com.herongtech.console.web.busicontroller.common.CommonConst;
import com.herongtech.console.web.busicontroller.common.GuarCodeConst;
import com.herongtech.db.impl.session.DBFactory;
import com.herongtech.db.interfaces.IDB;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.log.CommonLog;
import com.herongtech.rc.domain.bean.RgctBill;
import com.herongtech.rc.domain.bean.RgctBillHist;
import com.herongtech.rc.service.RcServiceFactory;
import com.herongtech.sysconstant.ISysErrorNo;

/**
 * 保证服务类
 * @author Administrator
 *
 */
public class AssuService implements IAssuService{
	
	public static final String HERONGTECH_VERSION="@system 票据管理平台 @version 2.0.0.1 @lastModiDate @describe ";
	
	AssuBillInfoDao billDao=new AssuBillInfoDao();
	AssuApplyInfoDao applyDao = new AssuApplyInfoDao();
	/**
	 * 插入保证清单表
	 * @param assuBillInfo 
	 * @throws BizAppException
	 */
	public void addAssuBillInfo(AssuBillInfo assuBillInfo) throws BizAppException {
		try {
			if (billDao.addAssuBillInfo(assuBillInfo) != 1) {
			      throw new BizAppException(ISysErrorNo.ERR_DBERR, "增加AssuBillInfo失败");
			}
		} catch (SQLException e) {
             throw new BizAppException(ISysErrorNo.ERR_DBERR,"");
		}
	}
	
	/**
	 * 可保证票据查询
	 * @param page
	 * @param operStatus 操作状态
	 * @param billClass 票据分类
	 * @return
	 * @throws BizAppException
	 */
	public List<AssuBillInfo> getAssuBillInfo(Page page, String operStatus, String billClass,String warteeOrgcode,String guartrBankNo) throws BizAppException {
		List<AssuBillInfo> list = new ArrayList<AssuBillInfo>();
		try {
			list = billDao.getAssuBillInfoForOperStatus(page, operStatus, billClass, warteeOrgcode,guartrBankNo);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BizAppException("可保证票据查询失败");
		}
		return list;
	}

	/**
	 * 根据id得到清单
	 * @param ids
	 * @return
	 * @throws SQLException
	 */
	public List<AssuBillInfo> getAssuBillInfoForId(String ids) throws BizAppException {
		List<AssuBillInfo> list = new ArrayList<AssuBillInfo>();
		try {
			list = billDao.getAssuBillInfoForId(ids);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BizAppException("查询清单信息失败");
		}
		return list;
	}

	/**
	 * 修改保证清单表
	 * @param assuBillInfo
	 * @throws BizAppException
	 */
	public void modifyAssuBillInfo(AssuBillInfo assuBillInfo) throws BizAppException {
		try {
			if (billDao.modifyAssuBillInfo(assuBillInfo) != 1) {
				 throw new BizAppException(ISysErrorNo.ERR_DBERR, "修改AssuBillInfo失败");
			}
		} catch (SQLException e) {
             throw new BizAppException(ISysErrorNo.ERR_DBERR,"修改AssuBillInfo失败");
		}
	}

	public void ElecRefuseBill(String ids) throws BizAppException{
		Map<String,RgctBill> rgctmap = new HashMap<String,RgctBill>();
		IDB session = DBFactory.getDB();
		String rgctids = "";
		Branch branch=ServiceFactory.getBranchService().getBranch(ResourceUtil.getSessionLoginfo().getBrchNo());
		List<AssuBillInfo> list=this.getAssuBillInfoForId(ids);
		if(list.isEmpty()){
			  throw new BizAppException(ISysErrorNo.ERR_DBERR,"票据不支持此操作");
		}
		for (int i = 0; i < list.size(); i++) {
			rgctids = rgctids + list.get(i).getRgctId()+",";
		}
		List<RgctBill> rgctlist = RcServiceFactory.getRcSaleBackService().getRgctBillList(rgctids.substring(0,rgctids.length()-1));
		for (int i = 0; i < rgctlist.size(); i++) {
			rgctmap.put(rgctlist.get(i).getHist().getRgctId(), rgctlist.get(i));
		}
		for(int i = 0 ;i<list.size();i++){
			try {
				session.beginTransaction();
				AssuBillInfo assuBillInfo = list.get(i);
				assuBillInfo.setOperStatus(GuarCodeConst.ASSU_STATUS_10);//操作状态
				//assuBillInfo.setAssuStatus(GuarCodeConst.ASSU_STATE_SIGN_NO);//保证状态  回调中写
				this.modifyAssuBillInfo(assuBillInfo);
				RgctBill rgctBill=rgctmap.get(list.get(0).getRgctId());
				RgctBillHist billHist = rgctBill.getHist();
				
				billHist.setToRole(branch.getPartnerType());
				billHist.setToOrgcode(branch.getOrgCode());
				billHist.setIsLock(CommonConst.LOCK_NO);
				billHist.setSignerSign(CommUtils.getSignerSign(billHist.getToBankNo()));
				billHist.setSignDt(DateTimeUtil.getWorkday());
				billHist.setToBankNo(ResourceUtil.getSessionLoginfo().getBrchBankNo());
				rgctBill.setHist(billHist);
				RcServiceFactory.getRcAssuranceService().registerAssuReject(rgctBill);
				session.endTransaction();
			} catch (Exception e) {
				try {
					session.rollback();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				e.printStackTrace();
				CommonLog.getCommonLogCache().errorMessage("处理失败:"+e.getMessage());
				throw new BizAppException(ISysErrorNo.ERR_DBERR, "处理失败:"+e.getMessage());
			}
		}
	}
	
	
	/**
	 * 电票保证签收
	 * @param ids
	 * @param asb
	 * @throws BizAppException 
	 */
	public void ElecAssuSign(String ids, AssuSearchBean asb) throws BizAppException{
		UserLoginfo user = ResourceUtil.getSessionLoginfo();
		IBranchService ibs = ServiceFactory.getBranchService();
		Branch branch = ibs.getBranch(user.getBranchNo());
		List<AssuBillInfo> list = this.getAssuBillInfoForId(ids);
		if(list.isEmpty()){
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "票据不支持该操作");
		}
		try {
			asb.setAssuId(ServiceFactory.getSequenceService().getASSU_APPLY_ID());
			//更新保证清单表
			for (int i = 0; i < list.size(); i++) {
				AssuBillInfo assuBillInfo = list.get(i);
				assuBillInfo.setAssuId(asb.getAssuId());
				assuBillInfo.setOperStatus(GuarCodeConst.ASSU_STATUS_12);
				this.modifyAssuBillInfo(assuBillInfo);
			}
			//插入保证批次信息
			this.addAssuApplyInfo(asb);
			//调用RC方法进行签收
			RgctBill rgctBill=RcServiceFactory.getRcRegBillService().getRgctBillById(list.get(0).getRgctId());
			rgctBill.getHist().setIsLock(CommonConst.LOCK_NO);
			rgctBill.getHist().setSignDt(DateTimeUtil.getWorkdayString());
			rgctBill.getHist().setSignerSign(CommUtils.getSignerSign(rgctBill.getHist().getToBankNo()));
			rgctBill.getHist().setToBankNo(user.getBrchBankNo());
			rgctBill.getHist().setToRole(branch.getPartnerType());
			rgctBill.getHist().setToOrgcode(branch.getOrgCode());
			RcServiceFactory.getRcAssuranceService().assuSign(rgctBill);
			} catch (BizAppException e) {
				e.printStackTrace();
				CommonLog.getCommonLogCache().errorMessage("处理失败:"+e.getMessage());
				throw new BizAppException(ISysErrorNo.ERR_DBERR, "处理失败:"+e.getMessage());
			}
		
	}
	
	
	/**
	 * 插入保证批次表
	 * @param assuApplyInfo 
	 * @throws BizAppException
	 */
	public void addAssuApplyInfo(AssuSearchBean asb) throws BizAppException {
		AssuApplyInfo assuApplyInfo = new AssuApplyInfo();
		assuApplyInfo.setBatchId(asb.getBatchId());
		assuApplyInfo.setAssuId(asb.getAssuId());
		assuApplyInfo.setCustNo(asb.getCustNo());
		assuApplyInfo.setCustName(asb.getCustName());
		assuApplyInfo.setCreateDt(asb.getCreateDt());
		assuApplyInfo.setCustManage(asb.getCustManager());
		assuApplyInfo.setCustManagerName(asb.getCustManagerName());
		assuApplyInfo.setDeptName(asb.getDeptName());
		try {
			if (applyDao.addAssuApplyInfo(assuApplyInfo) != 1) {
			      throw new BizAppException(ISysErrorNo.ERR_DBERR, "增加AssuApplyInfo失败");
			}
		} catch (SQLException e) {
             throw new BizAppException(ISysErrorNo.ERR_DBERR,"");
		}
	}

	/**
	 * 根据登记中心ID与操作状态查询保证清单信息
	 * @param rgctId 登记中心ID	
	 * @param operStatus 操作状态
	 * @return
	 * @throws BizAppException
	 */
	public AssuBillInfo getAssuBillInfoForRgctIdAndOperStatus(String rgctId, String operStatus) throws BizAppException {
		AssuBillInfo billInfo = new AssuBillInfo();
		try {
			billInfo = billDao.getAssuBillInfoByRgctIdAndOperStatus(rgctId, operStatus);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BizAppException("保证清单信息查询失败");
		}
		return billInfo;
	}
	

	/**
	 * 查询登记中心ID、保证类型查询特定 保证信息
	 * @param rgctId
	 */
	
	public List<AssuBillInfo> queryGrantInfoByRgctIdAndAssuType(String rgctId,String[] types) throws BizAppException {
		List<AssuBillInfo> grantList=new ArrayList<AssuBillInfo>();
		try {
			 grantList=billDao.getAssuInfoByRgctIdAndAssuType(rgctId, types);
		} catch (SQLException e) {

			e.printStackTrace();
		}
		return grantList;		
	}


}
