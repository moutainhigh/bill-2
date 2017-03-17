/********************************************
 * 文件名称: WorkingAdsService.java
 * 系统名称: 基础技术平台V2.0
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: 2.0.0.1
 * 开发人员: vvv
 * 开发时间: 2016-7-28
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期    修改人员    修改说明
 *********************************************/

package com.herongtech.console.service.workingads;

import java.sql.SQLException;

import com.herongtech.console.domain.bean.WorkingAds;
import com.herongtech.console.domain.dao.WorkingAdsDao;
import com.herongtech.console.service.interfaces.IWorkingAdsService;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.sysconstant.ISysErrorNo;

/**
 * 经营机构维护取法
 *
 */
public class WorkingAdsService implements IWorkingAdsService{
	public static final String HUNDSUN_VERSION="@system 票据管理平台 @version 2.0.0.1 @lastModiDate @describe ";

	/**
	 * 获取经营机构维护表
	 */
	public  WorkingAds getWorkingAds(String workingAdsNo)throws BizAppException{
		WorkingAdsDao dao=new WorkingAdsDao();
		try {
			return dao.getWorkingAds(workingAdsNo);
		}catch (SQLException e) {
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "");
		}
	}
	
	/**
	 * 插入经营机构维护表
	 */
	public void addWorkingAds(WorkingAds workingads)throws BizAppException{
		WorkingAdsDao dao=new WorkingAdsDao();
		try {
			if (dao.addWorkingAds(workingads) != 1) {
			      throw new BizAppException(ISysErrorNo.ERR_DBERR, "增加WorkingAds失败");
			}
		} catch (SQLException e) {
             throw new BizAppException(ISysErrorNo.ERR_DBERR,"");
		}
		
	}
	/**
	 * 修改经营机构维护表
	 */
	public void modifyWorkingAds(WorkingAds workingads)throws BizAppException{
		WorkingAdsDao dao=new WorkingAdsDao();
		try {
			if (dao.modifyWorkingAds(workingads) != 1) {
				 throw new BizAppException(ISysErrorNo.ERR_DBERR, "修改WorkingAds失败");
			}
		} catch (SQLException e) {
             throw new BizAppException(ISysErrorNo.ERR_DBERR,"");
		}
		
	}
}
