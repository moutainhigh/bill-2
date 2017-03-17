package com.herongtech.console.service.interfaces;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.herongtech.exception.impl.BizAppException;


/**ecds数据导入接口*/
public interface IEcdsDataImportService {

	/**
	 * 取得相应的实体类
	 * 
	 * @param ecdsDataList
	 * @param className
	 * @return
	 * @throws BizAppException 
	 * @throws SQLException 
	 */
	public void createEcdsData(List ecdsDataList, String className) throws BizAppException, SQLException;

	/**
	 * 将所有属性set到实体类存入一个list中 行号数据文件
	 * @throws BizAppException 
	 * @throws SQLException 
	 */
	public void createEcdsBankData(List ecdsDataList) throws BizAppException, SQLException;

	/**
	 * 将所有属性set到实体类存入一个list中 行号数据辅文件
	 * 
	 * @param ecdsDataList
	 * @return
	 * @throws SQLException 
	 */
	public void createEcdsBankAssData(List ecdsDataList) throws SQLException;

	/**
	 * 将所有属性set到实体类存入一个list中 业务权限数据文件
	 * 
	 * @param ecdsDataList
	 * @return
	 * @throws SQLException 
	 */
	public void createEcdsAuthListData(List ecdsDataList) throws SQLException;

	/**
	 * 将所有属性set到实体类存入一个list中 公共数据数据文件
	 * 
	 * @param ecdsDataList
	 * @return
	 * @throws SQLException 
	 */
	public void createEcdsCommonData(List ecdsDataList) throws SQLException;

	/**
	 * 将所有属性set到实体类存入一个list中 接入点信息数据文件
	 * 
	 * @param ecdsDataList
	 * @return
	 * @throws SQLException 
	 */
	public void createEcdsApData(List ecdsDataList) throws SQLException;

	/**
	 * 将所有属性set到实体类存入一个list中 参与者承接关系数据辅文件
	 * 
	 * @param ecdsDataList
	 * @return
	 * @throws SQLException 
	 */
	public void createEcdsPsAssData(List ecdsDataList) throws SQLException;

	/**
	 * 将所有属性set到实体类存入一个list中 参与者与接入点关系
	 * 
	 * @param ecdsDataList
	 * @return
	 * @throws SQLException 
	 */
	public void createEcdsPsapAssData(List ecdsDataList) throws SQLException;
	
}
