/********************************************
 * 文件名称: IRgctBillInfoService.java
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

package com.herongtech.rc.service.rgctbillinfo;

import java.sql.SQLException;

import com.herongtech.rc.domain.bean.RgctBillInfo;
import com.herongtech.rc.domain.dao.RgctBillInfoDao;
import com.herongtech.rc.service.interfaces.IRgctBillInfoService;
import com.herongtech.sysconstant.ISysErrorNo;
import com.herongtech.exception.impl.BizAppException;

public class RgctBillInfoService implements IRgctBillInfoService{
	
	public static final String HERONGTECH_VERSION="@system 票据管理平台 @version 2.0.0.1 @lastModiDate @describe ";
	/**
	 * 取RgctBillInfo交易行信息参数信息
	 */
	public  RgctBillInfo getRgctBillInfo(String id) throws BizAppException {
		RgctBillInfoDao dao=new RgctBillInfoDao();
		try {
			return dao.getRgctBillInfo(id);
		}catch (SQLException e) {
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "");
		}
		
	}
	
	/**
	 * 取RgctBillInfo
	 */
	public  RgctBillInfo getRgctBillInfoByBillNo(String billNo) throws BizAppException {
		RgctBillInfoDao dao=new RgctBillInfoDao();
		try {
			return dao.getRgctBillInfoByBillNo(billNo);
		}catch (SQLException e) {
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "");
		}
		
	}
	
	/**
	 * 取RgctBillInfo
	 */
	public  RgctBillInfo getRgctBillInfoByReqDraftId(String reqDraftId) throws BizAppException {
		RgctBillInfoDao dao=new RgctBillInfoDao();
		try {
			return dao.getRgctBillInfoByReqDraftId(reqDraftId);
		}catch (SQLException e) {
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "");
		}
		
	}
	
	/**
	 * 插入参数表
	 *
	 */
	public void addRgctBillInfo(RgctBillInfo RgctBillInfo)throws BizAppException{
		RgctBillInfoDao dao=new RgctBillInfoDao();
		try {
			if (dao.addRgctBillInfo(RgctBillInfo) != 1) {
			      throw new BizAppException(ISysErrorNo.ERR_DBERR, "增加RgctBillInfo失败");
			}
		} catch (SQLException e) {
             throw new BizAppException(ISysErrorNo.ERR_DBERR,"");
		}
	}
	
	/**
	 * 修改参数表
	 */
	
	public void modifyRgctBillInfo(RgctBillInfo RgctBillInfo)throws BizAppException {
		
		RgctBillInfoDao dao=new RgctBillInfoDao();
		try {
			if (dao.modifyRgctBillInfo(RgctBillInfo) != 1) {
			      throw new BizAppException(ISysErrorNo.ERR_DBERR, "增加RgctBillInfo失败");
			}
		} catch (SQLException e) {
             throw new BizAppException(ISysErrorNo.ERR_DBERR,"");
		}
	}
}
