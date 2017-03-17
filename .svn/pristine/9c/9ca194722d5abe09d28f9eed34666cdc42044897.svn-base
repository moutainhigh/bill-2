package com.herongtech.console.service.common.product;

import java.sql.SQLException;
import java.util.List;

import com.herongtech.console.domain.common.bean.ProdLimitType;
import com.herongtech.console.domain.common.bean.Product;
import com.herongtech.console.domain.common.dao.ProdLimitTypeDao;
import com.herongtech.console.domain.common.dao.ProductDao;


public class ProductService implements IProductService {

	ProductDao proDao = new ProductDao();
	ProdLimitTypeDao limitDao = new ProdLimitTypeDao();
	
	/**
	 * 功能描述：为申请批次提供产品名称与产品号码(后续提供缓存功能)
	 * @param pId
	 * @param billType
	 * @return
	 * @throws SQLException
	 */
	public List<Product> getProductListForApplyBatch(String pId,String billType) throws SQLException {
		return proDao.getProductForPidAndBillType(pId, billType);
	}
	/**
	 * 功能描述：为申请批次提供产品名称与产品号码(后续提供缓存功能)
	 * @param pId
	 * @param billType
	 * @return
	 * @throws SQLException
	 */
	public List<Product> getProductListForApplyBatch(String pId) throws SQLException {
		return proDao.getProductForPid(pId);
	}
	
	/**
	 * 功能描述：为申请批次提供产品名称与产品号码(后续提供缓存功能)
	 * @param pId
	 * @param billType
	 * @return
	 * @throws SQLException
	 */
	public List<Product> getProductListForPidAndIsInner(String pId,String isInner) throws SQLException {
		return proDao.getProductForPidAndIsInner(pId, isInner);
	}
	
	/**
	 * 功能描述：为申请批次提供产品名称与产品号码(后续提供缓存功能)
	 * @param pId
	 * @param billType
	 * @return
	 * @throws SQLException
	 */
	public List<Product> getProductListForApplyBatch(String pId,String billType,String isInner) throws SQLException {
		return proDao.getProductForPidAndBillType(pId, billType, isInner);
	}

	public Product getProductListForApplyBatch(String pId,String billType,String isInner,String buyType) throws SQLException {
		return proDao.getProductForPidAndBillTypeAndBuyType(pId, billType, isInner,buyType);
	}
	
	/**
	 * 根据产品编号获取产品对象
	 * @param prodNo
	 * @return
	 * @throws SQLException 
	 */
	@Override
	public Product getProductInfoByProdNo(String prodNo) throws SQLException {
		return proDao.getProductByProdNo(prodNo);
	}
	
	/**
	 * 根据prodNo获取ProdLimitType对象
	 * @param prodNo
	 * @return
	 * @throws SQLException
	 */
	@Override
	public ProdLimitType getProdLimitTypeByProdNo(String prodNo) throws SQLException{
		return limitDao.getProdLimitTypeByProdNo(prodNo);
	}
	
	public List<Product> getAllProduct() throws SQLException{
		return proDao.getAllProduct();
	}
}
