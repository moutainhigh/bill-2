/********************************************
 * 文件名称: IRcDiscService.java
 * 系统名称: 基础技术平台V2.0
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: 2.0.0.1
 * 开发人员: zcz
 * 开发时间: 2016-8-10 下午01:48:52
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期    修改人员    修改说明
 *********************************************/
package com.herongtech.rc.service.rcservice;
import com.herongtech.console.core.common.json.Page;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.rc.domain.bean.RcBaseSearchBean;
import com.herongtech.rc.domain.bean.RgctBill;

public interface IRcDiscService extends IRcBaseService {
	
	/**
	 * 散票查询
	 * 
	 * @param sb SearchBean
	 * @param pg Page
	 * @return 登记中心票据列表 List&lt;RgctBill&gt;或List&lt;RgctBillInfo&gt;
	 * @throws ServiceException
	 */
	public String queryScatteredBill(RcBaseSearchBean sb) throws BizAppException;
	
	
	
	/**
	 * 提交贴现申请
	 * 
	 * @param bill 登记中心票据信息
	 * @throws BizAppException
	 */
	public void submitBuyApply(RgctBill bill) throws BizAppException;
	
	/**
	 * 撤销贴现申请
	 * 
	 * @param bill 登记中心票据信息
	 * @throws BizAppException
	 */
	public void cancelBuyApply(RgctBill bill) throws BizAppException;
	
	
	/**
	 * 手动录入签收
	 * 
	 * @param bill 票据信息
	 * @return 登记中心票据
	 * @throws BizAppException
	 */
	public RgctBill inputSignBuy(RgctBill bill) throws BizAppException;
	
	/**
	 * 拒绝贴现签收（对申请的拒绝）
	 * 
	 * @param bill 登记中心票据信息
	 * @throws BizAppException
	 */
	public void rejectSignBuy(RgctBill bill) throws BizAppException;
	/**
	 * 贴现签收
	 * @param bill	bill 登记中心票据信息
	 * @throws BizAppException
	 */
	public void discountSign(RgctBill bill) throws BizAppException;
	
	/**
	 * 实票撤销签收
	 * @param bill	票据信息
	 * @return	RgctBill  票据信息
	 * @throws BizAppException
	 */
	public RgctBill cancelSign(RgctBill bill) throws BizAppException; 
	
	/**
	 * 查询可撤销票据 (包括准签收和待签收的票据)
	 * @param sb	SearchBean
	 * @param page	Page
	 * @return	登记中心票据列表 List&lt;RgctBill&gt;或List&lt;RgctBillInfo&gt;
	 * @throws BizAppException
	 */
	public String queryReversibleBill(RcBaseSearchBean sb) throws BizAppException; 

}
