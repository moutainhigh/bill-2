package com.herongtech.rc.service.rcservice;

import java.util.List;

import com.herongtech.console.core.common.json.Page;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.rc.domain.bean.RcBaseSearchBean;
import com.herongtech.rc.domain.bean.RgctBill;

public interface IRcSaveBillService extends IRcBaseService{
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
	 * 提交存票申请
	 * 
	 * @param bill 票据信息
	 * @return 登记中心票据
	 * @throws ServiceException
	 */
	public RgctBill submitSaveApply(RgctBill bill) throws BizAppException;
	
	/**
	 * 撤销存票申请
	 * 
	 * @param bill 票据信息
	 * @return 登记中心票据
	 * @throws ServiceException
	 */
	public RgctBill cancelSaveApply(RgctBill bill) throws BizAppException;
	
	/**
	 * 录入代保管存票
	 * 
	 * @param bill 票据信息
	 * @return 登记中心票据
	 * @throws ServiceException
	 */
	public RgctBill inputSaveBill(RgctBill bill) throws BizAppException;
	
	/**
	 * 待存票查询
	 * 
	 * @param sb SearchBean
	 * @param pg Page
	 * @return 登记中心票据列表 List&lt;RgctBill&gt;或List&lt;RgctBillInfo&gt;
	 * @throws ServiceException
	 */
	public List queryPresaveBill(RcBaseSearchBean sb, Page pg) throws BizAppException;
	
	/**
	 * 存票签收
	 * 
	 * @param bill 票据信息
	 * @return 登记中心票据
	 * @throws ServiceException
	 */
	public RgctBill signSave(RgctBill bill) throws BizAppException;
	
	/**
	 * 拒绝存票签收（对申请的拒绝）
	 * 
	 * @param bill 票据信息
	 * @return 登记中心票据
	 * @throws ServiceException
	 */
	public RgctBill rejectSignSave(RgctBill bill) throws BizAppException;
	
	/**
	 * 撤销存票签收
	 * 
	 * @param bill 票据信息
	 * @return 登记中心票据
	 * @throws ServiceException
	 */
	public RgctBill cancelSignSave(RgctBill bill) throws BizAppException;
	
	/**
	 * 代保管入库
	 * 
	 * @param bill 票据信息
	 * @return 登记中心票据
	 * @throws ServiceException
	 */
	public RgctBill saveInpool(RgctBill bill) throws BizAppException;
	
	/**
	 * 代保管入库冲正
	 * 
	 * @param bill 票据信息
	 * @return 登记中心票据
	 * @throws ServiceException
	 */
	public RgctBill reverseSaveInpool(RgctBill bill) throws BizAppException;

}
