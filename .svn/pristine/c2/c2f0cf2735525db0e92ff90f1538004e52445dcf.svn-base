/********************************************
 * 文件名称: IDictService.java
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

package com.herongtech.console.service.interfaces;

import java.util.List;

import com.herongtech.console.domain.bean.Dict;
import com.herongtech.exception.impl.BizAppException;


public interface IDictService {
	
	public static final String HUNDSUN_VERSION="@system 票据管理平台 @version 2.0.0.1 @lastModiDate @describe ";
	/**
	 * 获取数据字典
	 * 
	 * @dict keyNo 序列标识
	 */
	public  List<Dict> getDictList(String keyNo) throws BizAppException;
	
	/**
	 * 插入数据字典表
	 *
	 */
	public void addDictGroup(Dict dict)throws BizAppException;
	public void addDictItem(Dict dict)throws BizAppException;
	
	/**
	 * 修改数据字典表
	 */
	
	public void modifyDictGroup(Dict dict)throws BizAppException;
	public void modifyDictItem(Dict dict)throws BizAppException;
	/**
	 * 删除数据字典
	 */
	public void deleteDictGroup(String keyNo)throws BizAppException;
	public void deleteDictItem(String keyNo,String itemCode)throws BizAppException;
}
