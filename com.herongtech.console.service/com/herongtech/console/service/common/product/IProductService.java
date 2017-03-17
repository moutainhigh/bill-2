package com.herongtech.console.service.common.product;

import java.sql.SQLException;
import java.util.List;

import com.herongtech.console.domain.common.bean.ProdLimitType;
import com.herongtech.console.domain.common.bean.Product;



public interface IProductService {
	/**
	 * 功能描述：为申请批次提供产品名称与产品号码(后续提供缓存功能)
	 * @param pId
	 * @param billType
	 * @return
	 * @throws SQLException
	 */
	public List<Product> getProductListForApplyBatch(String pId,String billType) throws SQLException;
	/**
	 * 功能描述：为申请批次提供产品名称与产品号码(后续提供缓存功能)
	 * @param pId
	 * @param billType
	 * @return
	 * @throws SQLException
	 */
	public List<Product> getProductListForApplyBatch(String pId) throws SQLException ;
	public List<Product> getProductListForPidAndIsInner(String pId,String isInner) throws SQLException ;
	public Product getProductListForApplyBatch(String pId,String billType,String isInner,String buyType) throws SQLException;
	/**
	 * 功能描述：为申请批次提供产品名称与产品号码(后续提供缓存功能)
	 * @param pId
	 * @param billType
	 * @return
	 * @throws SQLException
	 */
	public List<Product> getProductListForApplyBatch(String pId,String billType,String isInner) throws SQLException;

	/**
	 * 根据产品编号获取产品对象
	 * @param prodNo
	 * @return
	 * @throws SQLException 
	 */
	public Product getProductInfoByProdNo(String prodNo) throws SQLException;

	/**
	 * 根据prodNo获取ProdLimitType对象
	 * @param prodNo
	 * @return
	 * @throws SQLException
	 */
	ProdLimitType getProdLimitTypeByProdNo(String prodNo) throws SQLException;
	/**
	 * 获取所有的产品信息
	 * @return
	 * @throws SQLException
	 */
	public List<Product> getAllProduct() throws SQLException;

}
