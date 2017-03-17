package com.herongtech.rc.service.rcservice;

import java.util.List;

import com.herongtech.console.core.common.json.Page;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.rc.domain.bean.RcBaseSearchBean;
import com.herongtech.rc.domain.bean.RgctBill;

public interface IRcGetBillService extends IRcBaseService{
	/**
	 * 散票查询
	 * 
	 * @param sb SearchBean
	 * @param pg Page
	 * @return 登记中心票据列表 List&lt;RgctBill&gt;或List&lt;RgctBillInfo&gt;
	 * @throws ServiceException
	 */
	public List queryScatteredBill(RcBaseSearchBean sb, Page pg) throws BizAppException;
	
	
	/**
	 * 查询可取票据，可出池票据
	 * @param sb SearchBean
	 * @param pg Page
	 * @param param 1 查全部， 2 查可取票据  3 查克出池票据
	 * @return
	 * @throws ServiceException
	 */
	public List queryGetBillOutpoolBill(RcBaseSearchBean sb, Page pg, String param) throws BizAppException;
	/**
	 * 提交取票申请
	 * 
	 * @param bill 登记中心票据信息
	 * @return 登记中心票据
	 * @throws ServiceException
	 */
	public RgctBill submitGetApply(RgctBill bill) throws BizAppException;
	
	/**
	 * 撤销取票申请
	 * 
	 * @param bill 登记中心票据信息
	 * @return 登记中心票据
	 * @throws ServiceException
	 */
	public RgctBill cancelGetApply(RgctBill bill) throws BizAppException;
	
	/**
	 * 待取票查询
	 * 
	 * @param sb SearchBean
	 * @param pg Page
	 * @return 登记中心票据列表 List&lt;RgctBill&gt;或List&lt;RgctBillInfo&gt;
	 * @throws ServiceException
	 */
	public List queryPregetBill(RcBaseSearchBean sb, Page pg) throws BizAppException;
	
	/**
	 * 取票签收
	 * 
	 * @param bill 登记中心票据信息
	 * @return 登记中心票据
	 * @throws ServiceException
	 */
	public RgctBill signGet(RgctBill bill) throws BizAppException;
	
	/**
	 * 取票签收出库
	 * 
	 * @param bill 登记中心票据信息
	 * @return 登记中心票据
	 * @throws ServiceException
	 */
	public RgctBill signGetPool(RgctBill bill) throws BizAppException;
	
	/**
	 * 拒绝取票签收（对申请的拒绝）
	 * 
	 * @param bill 登记中心票据信息
	 * @return 登记中心票据
	 * @throws ServiceException
	 */
	public RgctBill rejectSignGet(RgctBill bill) throws BizAppException;
	
	/**
	 * 撤销取票签收
	 * 
	 * @param bill 登记中心票据信息
	 * @return 登记中心票据
	 * @throws ServiceException
	 */
	public RgctBill cancelSignGet(RgctBill bill) throws BizAppException;
	
	/**
	 * 取票出库
	 * 
	 * @param bill 登记中心票据信息
	 * @return 登记中心票据
	 * @throws ServiceException
	 */
	public RgctBill getOutpool(RgctBill bill) throws BizAppException;
	
	/**
	 * 普通类型
	 */
	public static final int TYPE_NORMAL = 1;
	/**
	 * 卖出类型
	 */
	public static final int TYPE_SALE = 2;
	/**
	 * 卖出回购类型
	 */
	public static final int TYPE_SALEBACK = 3;
	
	/**
	 * 取票出库
	 * 
	 * @param bill 登记中心票据信息
	 * @param type 类型
	 * @return 登记中心票据
	 * @throws ServiceException
	 */
	public RgctBill getOutpool(RgctBill bill, int type) throws BizAppException;
	
	/**
	 * 取票出库冲正
	 * 
	 * @param bill 登记中心票据信息
	 * @return 登记中心票据
	 * @throws ServiceException
	 */
	public RgctBill reverseGetOutpool(RgctBill bill) throws BizAppException;
	/**
	 * 库存票转买断
	 * @param bill 登记中心票据信息
	 * @return 登记中心票据
	 * @throws ServiceException
	 */
	public RgctBill storageChgBuy(RgctBill bill) throws BizAppException;
	/**
	 * 库存票转买入返售
	 * @param bill 登记中心票据信息
	 * @return 登记中心票据
	 * @throws ServiceException
	 */
	public RgctBill storageChgBuyBack(RgctBill bill) throws BizAppException;
	
	/**
	 * 库存票转已至赎回开放日
	 * @param bill
	 * @return
	 * @throws ServiceException
	 */
	public RgctBill storageBuyBackBefore(RgctBill bill) throws BizAppException;
	
	/**
	 * 库存票转已至赎回截止日
	 * @param bill
	 * @return
	 * @throws ServiceException
	 */
	public RgctBill storageBuyBackAfter(RgctBill bill) throws BizAppException;
	
	/**
	 * 查询被拒绝取票签收票据
	 * @param sb
	 * @param pg
	 * @return
	 * @throws ServiceException
	 */
	public List queryRejectSignGetBill(RcBaseSearchBean sb, Page pg) throws BizAppException;
	
	/**
	 * 查询未拒绝的待取票签收票据
	 * @param sb
	 * @param pg
	 * @return
	 * @throws ServiceException
	 */
	public List queryWSignGetBill(RcBaseSearchBean sb, Page pg) throws BizAppException;
	
	/**
	 * 拒绝的取票签收票据转未拒绝待取票签收票据
	 * @param bill
	 * @return
	 * @throws ServiceException
	 */
	public RgctBill reject2WSign(RgctBill bill) throws BizAppException;
	
	/**
	 * 总行拒绝入库签收（对分行出库 总行入库的拒绝）
	 * 
	 * @param bill 登记中心票据信息
	 * @return 登记中心票据
	 * @throws ServiceException
	 */
	public RgctBill rejectSignInput(RgctBill bill) throws BizAppException;

}
