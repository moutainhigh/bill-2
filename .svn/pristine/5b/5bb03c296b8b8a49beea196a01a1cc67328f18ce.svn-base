/********************************************
 * 文件名称: BbspProductService.java
 * 系统名称: 基础技术平台V2.0
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: 2.0.0.1
 * 开发人员: dq
 * 开发时间: 2016-7-21 上午11:30:52
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期    修改人员    修改说明
 *********************************************/
package com.herongtech.console.service.signProd;

import java.sql.SQLException;
import java.util.List;

import com.herongtech.console.core.common.json.Page;
import com.herongtech.console.core.constant.IDict;
import com.herongtech.console.core.util.DateTimeUtil;
import com.herongtech.console.core.util.ResourceUtil;
import com.herongtech.console.domain.bean.Branch;
import com.herongtech.console.domain.bean.SignProd;
import com.herongtech.console.domain.bean.UserLoginfo;
import com.herongtech.console.domain.dao.SignProdDao;
import com.herongtech.console.service.ServiceFactory;
import com.herongtech.console.service.interfaces.ICustInfoAcctService;
import com.herongtech.console.service.interfaces.ICustInfoService;
import com.herongtech.console.service.interfaces.ISequenceService;
import com.herongtech.console.service.interfaces.ISignProdService;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.sysconstant.ISysErrorNo;

/**
 * 产品签约信息表方法
 *
 */
public class SignProdService implements ISignProdService{
	public static final String HUNDSUN_VERSION="@system 票据管理平台 @version 2.0.0.1 @lastModiDate @describe ";

	/**
	 * 插入产品签约信息表
	 *   
	 */
	public void addSignProd(SignProd signProd) throws BizAppException {
		SignProdDao dao=new SignProdDao();
		try {
			ISequenceService sequenceService=ServiceFactory.getSequenceService();
			UserLoginfo user=ResourceUtil.getSessionLoginfo();
			signProd.setId(ServiceFactory.getSequenceService().getSign_Prod_ID());
			ICustInfoAcctService custInfoAcctService=ServiceFactory.getCustInfoAcctService();
			ICustInfoService custInfoService=ServiceFactory.getCustInfoService();
			String sBranchId=custInfoAcctService.getParam(signProd.getBusSettleAcct()).getAcctBranchNo();
			Branch branch = ServiceFactory.getBranchService().getBranch(user.getBranchNo());
			Branch brchiInfo = ServiceFactory.getBranchService().getBranch(sBranchId);
			String workday = DateTimeUtil.getWorkdayString();
			signProd.setValidfromdt(workday);
			signProd.setValidtodt(workday);
			signProd.setOperNo(user.getUserNo());
			signProd.setOpendt(workday);
			signProd.setTransDate(workday);
			signProd.setBranchNo(branch.getBranchNo());
			//组织机构代码校验
			/*String   orgCode= signProd.getIdNumber();
			if(!CommonUtil.isValidEntpCode(orgCode)) {
				throw new ServiceException("客户组织机构代码验证未通过，请到柜台核对客户信息中的组织机构代码信息。");
			}*/
			if ("1".equals(branch.getBranchLevel())) {
				throw new BizAppException("总行柜员不能操作此业务");
			}
			if(!"1".equals(brchiInfo.getElecAuth())){
				throw new BizAppException("账号所属机构"+brchiInfo.getBankNo()+"未开通电子票据权限");
			}
			/*IEcdsBankDataDAO ecdsBankDataDAO = SourceTemplate.getBean("EcdsBankDataDAO");
			EcdsBankData ecdsBankData = ecdsBankDataDAO.getEcdsBankDataOfCertBind(brchiInfo.getBankNo());
			if(null == ecdsBankData){
				throw new ServiceException("账号所属机构对应的行号未绑定证书！");
			}
			
			EcdsAuthlistData ecdsAuthListData = ecdsBankDataDAO.getEcdsAuthlistData(brchiInfo.getBankNo());
			if(null == ecdsAuthListData){
				throw new ServiceException("账号所属机构对应的行号没有发送报文的权限！");
			}*/
			
			//不能重复签约 
			//TODO: 多账号签约检查
			// ---账号级签约检查开始--------
			SignProd sign=this.getSignProdByBusAct(signProd.getProdNo(), signProd.getBusSettleAcct());
			if(sign!=null){
				throw new BizAppException("账号已在[ "+ sign.getSignOrg() +" ]机构签约成功,请勿重复签约！");
			}
			//---账号级签约检查结束--------
			
			/**
			 ---客户级签约检查开始--------
			SignProd sign=SignProdFactory.getSignProdSerivce().getSignProdInfoByCust(signProd.getProdId(), signProd.getPartner());
			if(sign!=null){
				throw new ServiceException("客户已在[ "+ sign.getOpenOrg() +" ]机构签约成功,请勿重复签约！");
			}
			if (!brchiInfo.getParentBrchId().equals(branch.getBrchId())
					&& !brchiInfo.getBrchId().equals(branch.getBrchId())) {
				throw new ServiceException("该帐号所属机构号与当前操作员所属机构号不同，请重新输入客户账号！！");
			} 
			-------客户级签约检查结束---------
			*/
			signProd.setAcceptorBankName(branch.getBranchName());// 承兑人开户行名称(支行所在的分行)
			signProd.setIdNumber(custInfoService.getParam(signProd.getCustNo()).getOrgCode());
			signProd.setAcceptorBankNo(branch.getPayBankNo());
			signProd.setSignOrg(branch.getBranchNo()); // 业务机构开户分行 (操作机构)
			signProd.setCreateOrg(brchiInfo.getBranchNo()); // 创建机构
			signProd.setRemitterBankName(brchiInfo.getBranchName());// 出票人开户行名称（所在的开户支行）																// 开户支行(账号机构)
			signProd.setRemitterBankNo(brchiInfo.getPayBankNo());
			signProd.setSignId(sequenceService.getSign_ID());//协议书编号
			signProd.setSignseq("QY"+DateTimeUtil.getWorktimeAsyyyyMMddHHmmss());//签约编号
			signProd.setChanne("1400");//创建渠道
			signProd.setIdType(IDict.K_CARDTYPE.CUST_CETIFICATE_TYPE);//证件类型
			if (dao.addSignProd(signProd) != 1) {
			      throw new BizAppException(ISysErrorNo.ERR_DBERR, "增加SignProd失败");
			}
		} catch (SQLException e) {
             throw new BizAppException(ISysErrorNo.ERR_DBERR,"");
		}
		
	}

	/**
	 * 修改产品签约信息表
	 */
	public void modifySignProd(SignProd signProd)
			throws BizAppException {
		SignProdDao dao=new SignProdDao();
		UserLoginfo user=ResourceUtil.getSessionLoginfo();
		try {
			SignProd signP=dao.getSignProdById(signProd.getId());
			Branch branch = ServiceFactory.getBranchService().getBranch(user.getBrchNo());
			signP.setBranchNo(branch.getBranchNo());
			signP.setAcceptorBankName(branch.getBranchName());// 承兑人开户行名称(支行所在的分行)
			signP.setAcceptorBankNo(branch.getPayBankNo());
			signP.setSignOrg(branch.getBranchNo()); // 业务机构开户分行 (操作机构)
			signP.setSignStatusCd(signProd.getSignStatusCd());
			signP.setCreditInfo(signProd.getCreditInfo());
			signP.setCreditAgency(signProd.getCreditAgency());
			signP.setCreditDueDt(signProd.getCreditDueDt());
			signP.setCmsFlag(signProd.getCmsFlag());
			signP.setMarginAccount(signProd.getMarginAccount());
			signP.setDiscountProportion(signProd.getDiscountProportion());
			/*if("0".equals(signProd.getCmsFlag()))
			{
				signP.setMarginAccount(" ");
			}*/
			if (dao.modifySignProd(signP) != 1) {
				 throw new BizAppException(ISysErrorNo.ERR_DBERR, "修改SignProd失败");
			}
		} catch (SQLException e) {
             throw new BizAppException(ISysErrorNo.ERR_DBERR,"");
		}
		
	}
	
	/**
	 * 获取产品签约信息表
	 *
	 */
	public SignProd getSignProdByBusAct(String prodNo, String busSettleAct) throws BizAppException {
		SignProdDao dao=new SignProdDao();
		try {
			return dao.getSignProdByBusAct(prodNo, busSettleAct);
		}catch (SQLException e) {
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "数据库查询失败");
		}
	}
	/**
	 * 根据服务产品编号和客户号查询产品签约信息(托收中使用了此方法)
	 * @param prodId
	 * @param custNo
	 * @return
	 * @throws BizAppException
	 */
	public SignProd getSignProdInfoByCust(String prodId, String custNo) throws BizAppException{
		SignProdDao dao=new SignProdDao();
		try {
			return dao.getSignProdInfoByCust(prodId, custNo);
		}catch (SQLException e) {
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "");
		}
	}
	
	/**
	 * 电票签约查询方法
	 * 
	 */
	public List<SignProd> getSignProdInfoBySignStatusCd(Page page,SignProd signProd) throws BizAppException{
		SignProdDao dao=new SignProdDao();
		try {
			return dao.getSignProdInfoBySignStatusCd(page, signProd);
		}catch (SQLException e) {
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "");
		}
		
	}
	/**
	 * 根据id查询产品签约信息
	 * @param id
	 * @return
	 * @throws BizAppException
	 */
	public SignProd getSignProdById(String id)throws BizAppException{
		SignProdDao dao=new SignProdDao();
		try {
			return dao.getSignProdById(id);
		}catch (SQLException e) {
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "");
		}
	}
	
	/**
	 * 根据服务产品编号和客户号查询产品签约信息(网银端查询签约信息)
	 * @param prodNo
	 * @param custNo
	 * @return
	 * @throws BizAppException
	 */
	public List<SignProd> getSignProdInfoByPro(String prodNo, String custNo) throws BizAppException{
		SignProdDao dao=new SignProdDao();
		try {
			return dao.getSignProdInfoByPro(prodNo, custNo);
		}catch (SQLException e) {
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "");
		}
	}
	
}
