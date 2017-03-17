package com.herongtech.rc.service.rcservice;

import java.util.List;

import com.herongtech.console.core.common.json.Page;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.rc.domain.bean.RcBaseSearchBean;
import com.herongtech.rc.domain.bean.RgctBill;
import com.herongtech.rc.domain.bean.RgctBillData;


public interface IRcSaleService extends IRcBaseService{
	
	/**
	 * 查询待转出贴现票据
	 * 
	 * @param sb RcBaseSearchBean
	 * @param pg Page
	 * @return 登记中心票据列表 
	 * @throws BizAppException
	 */
	public List<RgctBillData> queryPresaleBill(RcBaseSearchBean sb,Page page) throws BizAppException;
	
	/**
	 * 转出贴现背书登记
	 * 
	 * @param bill 登记中心票据
	 * @throws BizAppException
	 */
	public void saleEndorse(RgctBill bill) throws BizAppException;
	
	
	/**
	 * 转出方撤销转出贴现背书登记
	 * 
	 * @param bill 登记中心票据信息
	 * @throws BizAppException
	 */
	public void cancelSaleEndorse(RgctBill bill) throws BizAppException;
	
	/**
	 * 转入方同意签收
	 * @param bill
	 * @return
	 * @throws BizAppException
	 */
	public RgctBill regAgreeEndorse(RgctBill bill) throws BizAppException;
	/**
	 * 转入方撤销签收结果登记
	 * 
	 * @param bill 登记中心票据信息
	 * @return 登记中心票据
	 * @throws BizAppException
	 */
	public RgctBill regCancelSign(RgctBill bill) throws BizAppException;
}
