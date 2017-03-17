/********************************************
 * 文件名称: IRgctBillHistService.java
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

package com.herongtech.rc.service.rgctbillhist;

import java.sql.SQLException;

import com.herongtech.rc.domain.bean.RgctBillHist;
import com.herongtech.rc.domain.dao.RgctBillHistDao;
import com.herongtech.rc.service.interfaces.IRgctBillHistService;
import com.herongtech.sysconstant.ISysErrorNo;
import com.herongtech.exception.impl.BizAppException;

public class RgctBillHistService implements  IRgctBillHistService{
	
	public static final String HERONGTECH_VERSION="@system 票据管理平台 @version 2.0.0.1 @lastModiDate @describe ";
	/**
	 * 取RgctBillHist交易行信息参数信息
	 */
	public  RgctBillHist getRgctBillHist(String rowNumber) throws BizAppException {
		RgctBillHistDao dao=new RgctBillHistDao();
		try {
			return dao.getRgctBillHist(rowNumber);
		}catch (SQLException e) {
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "");
		}
		
	}
	
	/**
	 * 插入参数表
	 *
	 */
	public void addRgctBillHist(RgctBillHist RgctBillHist)throws BizAppException{
		RgctBillHistDao dao=new RgctBillHistDao();
		try {
			if (dao.addRgctBillHist(RgctBillHist) != 1) {
			      throw new BizAppException(ISysErrorNo.ERR_DBERR, "增加Param失败");
			}
		} catch (SQLException e) {
             throw new BizAppException(ISysErrorNo.ERR_DBERR,"");
		}
	}
	
	/**
	 * 修改参数表
	 */
	
	public void modifyRgctBillHist(RgctBillHist RgctBillHist)throws BizAppException {
		
		RgctBillHistDao dao=new RgctBillHistDao();
		try {
			if (dao.modifyRgctBillHist(RgctBillHist) != 1) {
			      throw new BizAppException(ISysErrorNo.ERR_DBERR, "增加Param失败");
			}
		} catch (SQLException e) {
             throw new BizAppException(ISysErrorNo.ERR_DBERR,"");
		}
	}
}
