/********************************************
 * 文件名称: IAssuService.java
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
import java.util.List;

import com.herongtech.console.core.common.json.Page;
import com.herongtech.console.domain.assu.bean.AssuBillInfo;
import com.herongtech.console.domain.assu.bean.AssuSearchBean;
import com.herongtech.exception.impl.BizAppException;

/**
 * 保证接口interface
 * @author Administrator
 *
 */
public interface IAssuService {
	
	public static final String HERONGTECH_VERSION="@system 票据管理平台 @version 2.0.0.1 @lastModiDate @describe ";
	/**
	 * 插入保证清单表
	 * @param assuBillInfo 
	 * @throws BizAppException
	 */
	public void addAssuBillInfo(AssuBillInfo assuBillInfo) throws BizAppException;
	
	/**
	 * 可保证票据查询
	 * @param page
	 * @param operStatus 操作状态
	 * @param billClass 票据分类
	 * @return
	 * @throws BizAppException
	 */
	public List<AssuBillInfo> getAssuBillInfo(Page page, String operStatus, String billClass, String warteeOrgcode, String guartrBankNo) throws BizAppException;
	
	/**
	 * 根据id得到清单
	 * @param ids
	 * @return
	 * @throws SQLException
	 */
	public List<AssuBillInfo> getAssuBillInfoForId(String ids) throws BizAppException;
	
	/**
	 * 根据登记中心ID与操作状态查询保证清单信息
	 * @param rgctId 登记中心ID	
	 * @param operStatus 操作状态
	 * @return
	 * @throws BizAppException
	 */
	public AssuBillInfo getAssuBillInfoForRgctIdAndOperStatus(String rgctId, String operStatus) throws BizAppException;
	
	/**
	 * 修改保证清单表
	 * @param assuBillInfo
	 * @throws BizAppException
	 */
	public void modifyAssuBillInfo(AssuBillInfo assuBillInfo) throws BizAppException; 
	
	/**
	 * 插入保证批次表
	 * @param asb 
	 * @throws BizAppException
	 */
	public void addAssuApplyInfo(AssuSearchBean asb) throws BizAppException;
	

	/**
	 * 查询登记中心ID、保证类型查询特定 保证信息
	 * @param rgctId
	 */
	public List<AssuBillInfo> queryGrantInfoByRgctIdAndAssuType(String rgctId,String[] types) throws BizAppException ;
	
	
	/**
	 * 电票保证拒绝
	 * @param ids
	 * @throws BizAppException
	 */
	public void ElecRefuseBill(String ids) throws BizAppException;
	
	/**
	 * 电票保证签收
	 * @param ids
	 * @param asb
	 * @throws BizAppException 
	 */
	public void ElecAssuSign(String ids, AssuSearchBean asb) throws BizAppException;
}
