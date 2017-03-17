/********************************************
 * 文件名称: DictService.java
 * 系统名称: 基础技术平台V2.0
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: 2.0.0.1
 * 开发人员: fqz
 * 开发时间: 2016-7-8 下午01:48:52
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期    修改人员    修改说明
 *********************************************/

package com.herongtech.console.service.dict;

import java.sql.SQLException;
import java.util.List;

import com.herongtech.console.domain.bean.Dict;
import com.herongtech.console.domain.dao.DictDao;
import com.herongtech.console.service.interfaces.IDictService;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.sysconstant.ISysErrorNo;

/**
 * 数据字典取法
 *
 */
public class DictService implements IDictService{
	public static final String HUNDSUN_VERSION="@system 票据管理平台 @version 2.0.0.1 @lastModiDate @describe ";

	/**
	 * 获取数据字典表
	 */
	public  List<Dict> getDictList(String keyNo)throws BizAppException{
		DictDao dao=new DictDao();
		try {
			return dao.getDictList( keyNo);
		}catch (SQLException e) {
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "");
		}
	}
	
	/**
	 * 插入数据字典表
	 */
	public void addDictGroup(Dict dict)throws BizAppException{
		DictDao dao=new DictDao();
		try {
			if (dao.addDictGroup(dict) != 1) {
			      throw new BizAppException(ISysErrorNo.ERR_DBERR, "增加Dict失败");
			}
		} catch (SQLException e) {
             throw new BizAppException(ISysErrorNo.ERR_DBERR,"");
		}
		
	}
	/**
	 * 插入数据字典表
	 */
	public void addDictItem(Dict dict)throws BizAppException{
		DictDao dao=new DictDao();
		try {
			if (dao.addDictItem(dict) != 1) {
			      throw new BizAppException(ISysErrorNo.ERR_DBERR, "增加Dict失败");
			}
		} catch (SQLException e) {
             throw new BizAppException(ISysErrorNo.ERR_DBERR,"");
		}
		
	}
	/**
	 * 修改数据字典表
	 */
	public void modifyDictGroup(Dict dict)throws BizAppException{
		DictDao dao=new DictDao();
		try {
			if (dao.modifyDictGroup(dict) != 1) {
				 throw new BizAppException(ISysErrorNo.ERR_DBERR, "修改Dict失败");
			}
		} catch (SQLException e) {
             throw new BizAppException(ISysErrorNo.ERR_DBERR,"");
		}
		
	}
	/**
	 * 修改数据字典表
	 */
	public void modifyDictItem(Dict dict)throws BizAppException{
		DictDao dao=new DictDao();
		try {
			if (dao.modifyDictItem(dict) != 1) {
				 throw new BizAppException(ISysErrorNo.ERR_DBERR, "修改Dict失败");
			}
		} catch (SQLException e) {
             throw new BizAppException(ISysErrorNo.ERR_DBERR,"");
		}
		
	}
	/**
	 * 删除数据字典
	 */
	public void deleteDictGroup(String keyNo)throws BizAppException{
		DictDao dao=new DictDao();
		try {
			if (dao.deleteDictGroup(keyNo) != 1) {
				 throw new BizAppException(ISysErrorNo.ERR_DBERR, "删除Dict失败");
			}
		} catch (SQLException e) {
             throw new BizAppException(ISysErrorNo.ERR_DBERR,"");
		}
		
	}
	/**
	 * 删除数据字典
	 */
	public void deleteDictItem(String keyNo,String itemCode)throws BizAppException{
		DictDao dao=new DictDao();
		try {
			if (dao.deleteDictItem(keyNo,itemCode) != 1) {
				 throw new BizAppException(ISysErrorNo.ERR_DBERR, "删除Dict失败");
			}
		} catch (SQLException e) {
             throw new BizAppException(ISysErrorNo.ERR_DBERR,"");
		}
		
	}
}
