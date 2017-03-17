/********************************************
 * 文件名称: BbspProductService.java
 * 系统名称: 基础技术平台V2.0
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: 2.0.0.1
 * 开发人员: dq
 * 开发时间: 2016-7-12 上午11:30:52
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期    修改人员    修改说明
 *********************************************/
package com.herongtech.console.service.bbspProduct;

import java.sql.SQLException;

import com.herongtech.console.domain.bean.BbspProduct;
import com.herongtech.console.domain.dao.BbspProductDao;
import com.herongtech.console.service.interfaces.IBbspProductService;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.sysconstant.ISysErrorNo;

/**
 * 产品维护表方法
 *
 */
public class BbspProductService implements IBbspProductService {
	public static final String HUNDSUN_VERSION="@system 票据管理平台 @version 2.0.0.1 @lastModiDate @describe ";

	/**
	 * 获取产品维护表
	 *
	 */
	public BbspProduct getBbspProduct(String prodNo) throws BizAppException {
		BbspProductDao dao=new BbspProductDao();
		try {
			return dao.getBbspProduct(prodNo);
		}catch (SQLException e) {
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "");
		}
	}

	/**
	 * 插入产品维护表
	 *
	 */
	public void addBbspProduct(BbspProduct bbspProduct) throws BizAppException {
		BbspProductDao dao=new BbspProductDao();
		try {
			if (dao.addBbspProduct(bbspProduct) != 1) {
			      throw new BizAppException(ISysErrorNo.ERR_DBERR, "增加BbspProduct失败");
			}
		} catch (SQLException e) {
             throw new BizAppException(ISysErrorNo.ERR_DBERR,"");
		}
		
	}

	/**
	 * 修改产品维护表
	 */
	public void modifyBbspProduct(BbspProduct bbspProduct)
			throws BizAppException {
		BbspProductDao dao=new BbspProductDao();
		try {
			if (dao.modifyBbspProduct(bbspProduct) != 1) {
				 throw new BizAppException(ISysErrorNo.ERR_DBERR, "修改BbspProduct失败");
			}
		} catch (SQLException e) {
             throw new BizAppException(ISysErrorNo.ERR_DBERR,"");
		}
		
	}
}
