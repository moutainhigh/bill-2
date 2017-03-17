/********************************************
 * 文件名称: CustManageService.java
 * 系统名称: 基础技术平台V2.0
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: 2.0.0.1
 * 开发人员: fanqq
 * 开发时间: 2016-7-12 下午02:50:52
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期    修改人员    修改说明
 *********************************************/

package com.herongtech.console.service.custmanager;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.herongtech.console.core.util.ResourceUtil;
import com.herongtech.console.domain.bean.CustManage;
import com.herongtech.console.domain.bean.User;
import com.herongtech.console.domain.common.bean.Product;
import com.herongtech.console.domain.dao.CustManageDao;
import com.herongtech.console.service.ServiceFactory;
import com.herongtech.console.service.interfaces.ICustManageService;
import com.herongtech.console.service.interfaces.IDeptService;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.sysconstant.ISysErrorNo;

/**
 * 系统参数取法
 *
 */
public class CustManageService implements ICustManageService{
	public static final String HUNDSUN_VERSION="@system 票据管理平台 @version 2.0.0.1 @lastModiDate @describe ";

	/**
	 * 获取客户经理表
	 */
	@Override
	public CustManage getCustManage(String custManagerNo) throws BizAppException {
		CustManageDao dao=new CustManageDao();
		try {
			return dao.getCustManage(custManagerNo);
		}catch (SQLException e) {
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "");
		}
		
	}
	
	/**
	 * 获取客户经理通过部门编号
	 */
	@Override
	public CustManage getCustManageByDeptNo(String deptNo) throws BizAppException {
		CustManageDao dao=new CustManageDao();
		try {
			return dao.getCustManageByDeptNo(deptNo);
		}catch (SQLException e) {
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "");
		}
		
	}
	
	/**
	 * 插入客户经理表
	 */
	public void addCustManage(CustManage custManager)throws BizAppException{
		CustManageDao dao=new CustManageDao();
		IDeptService dept=ServiceFactory.getDeptService();
		User user = ResourceUtil.getSessionUser(); //取用户处理类
		String createOperName = user.getUserName();
		SimpleDateFormat sdf=new SimpleDateFormat("HH-mm-ss");
		String createTime=sdf.format(new Date());
		SimpleDateFormat sdf1=new SimpleDateFormat("yyyy-MM-dd");
		String createDt=sdf1.format(new Date());
		//custmanager.setId(Long.parseLong(id));
		custManager.setCreateOperName(createOperName);
		custManager.setCreateTime(createTime);
		custManager.setCreateDt(createDt);
		custManager.setDeptName(dept.getParam(custManager.getDeptNo().toString()).getDepName());
		try {
			if (dao.addCustManage(custManager)!= 1) {
			      throw new BizAppException(ISysErrorNo.ERR_DBERR, "增加custManager失败");
			}
		} catch (SQLException e) {
             throw new BizAppException(ISysErrorNo.ERR_DBERR,"");
		}
		
	}
	/**
	 * 修改客户经理表
	 */
	public void modifyCustManage(CustManage custManager)throws BizAppException{
		CustManageDao dao=new CustManageDao();
		
		try {
			if (dao.modifyCustManage(custManager)!= 1) {
				 throw new BizAppException(ISysErrorNo.ERR_DBERR, "custManager失败");
			}
		} catch (SQLException e) {
             throw new BizAppException(ISysErrorNo.ERR_DBERR,"");
		}
		
	}
	
	
	

	

	
}
