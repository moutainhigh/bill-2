package com.herongtech.rc.service.rcservice;

import com.herongtech.exception.impl.BizAppException;
import com.herongtech.rc.domain.bean.RcBaseSearchBean;
import com.herongtech.rc.domain.bean.RgctBill;

public interface IRcUnimpawnService extends IRcBaseService{

	/**
	 * 查询待解质押票据
	 * 
	 * @param sb
	 * @return 登记中心票据列表 List&lt;RgctBill&gt;或List&lt;RgctBillInfo&gt;
	 * @throws BizAppException
	 */
	String queryPreunimpawnBill(RcBaseSearchBean sb) throws BizAppException;

	/**
	 * 提交解质押申请
	 * 
	 * @param bill 登记中心票据信息
	 * @throws ServiceException
	 */
	void submitUnimpawnApply(RgctBill bill) throws BizAppException;

	/**
	 * 撤销解质押申请
	 * 
	 * @param bill 登记中心票据信息
	 * @throws ServiceException
	 */
	void cancelUnimpawnApply(RgctBill bill) throws BizAppException;

	/**
	 * 查询待解质押签收票据
	 * 
	 * @param sb SearchBean
	 * @return 登记中心票据列表 List&lt;RgctBill&gt;或List&lt;RgctBillInfo&gt;
	 * @throws ServiceException
	 */
	String queryPresignBill(RcBaseSearchBean sb) throws BizAppException;

	/**
	 * 质押解除签收
	 * 
	 * @param bill 登记中心票据信息
	 * @throws ServiceException
	 */
	void signUnimpawn(RgctBill bill) throws BizAppException;

	/**
	 * 拒绝解质押签收（对申请的拒绝）
	 * 
	 * @param bill 登记中心票据信息
	 * @throws ServiceException
	 */
	void rejectSignUnimpawn(RgctBill bill) throws BizAppException;

}
