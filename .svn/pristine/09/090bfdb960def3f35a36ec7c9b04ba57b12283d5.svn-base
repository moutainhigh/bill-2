package com.herongtech.rc.service.rcservice;

import com.herongtech.console.core.common.json.Page;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.rc.domain.bean.RcBaseSearchBean;
import com.herongtech.rc.domain.bean.RgctBill;

public interface IRcImpawnService  extends IRcBaseService{
	
	/**
	 * 清单录入
	 * @param bill
	 * @return
	 * @throws BizAppException
	 */
	public RgctBill inputImpawnBill(RgctBill bill) throws BizAppException;
	/**
	 * 查询可质押票据
	 * 
	 * @param sb SearchBean
	 * @param pg Page
	 * @return 登记中心票据列表 String&lt;RgctBill&gt;或String&lt;RgctBillInfo&gt;
	 * @throws BizAppException
	 */
	public String queryScatterBill(RcBaseSearchBean sb) throws BizAppException;
	
	/**
	 * 提交质押申请
	 * 
	 * @param bill 登记中心票据信息
	 * @throws BizAppException
	 */
	public void submitImpawnApply(RgctBill bill) throws BizAppException;
	
	/**
	 * 撤销质押申请
	 * 
	 * @param bill 登记中心票据信息
	 * @throws BizAppException
	 */
	public void cancelImpawnApply(RgctBill bill) throws BizAppException;
	
	/**
	 * 查询待质押签收票据
	 * 
	 * @param sb SearchBean
	 * @param pg Page
	 * @return 登记中心票据列表 String&lt;RgctBill&gt;或String&lt;RgctBillInfo&gt;
	 * @throws BizAppException
	 */
	public String queryPreimpawnBill(RcBaseSearchBean sb) throws BizAppException;
	
	/**
	 * 拒绝质押签收（对申请的拒绝）
	 * 
	 * @param bill 登记中心票据信息
	 * @throws BizAppException
	 */
	public void rejectSignImpawn(RgctBill bill) throws BizAppException;
	
	/**
	 * 质押签收
	 * @param bill
	 * @throws BizAppException
	 */
	public void impawnSign(RgctBill bill) throws BizAppException;
	
	/**
	 * 查询可撤销票据 (包括准签收和待签收的票据)
	 * @param sb	SearchBean
	 * @param page	Page
	 * @return	登记中心票据列表 String&lt;RgctBill&gt;或String&lt;RgctBillInfo&gt;
	 * @throws BizAppException
	 */
	public String queryReversibleBill(RcBaseSearchBean sb, Page page) throws BizAppException; 
	
}
