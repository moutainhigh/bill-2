package com.herongtech.console.service.busiservice.sale;

import java.sql.SQLException;
import java.util.List;

import com.herongtech.console.core.common.json.Page;
import com.herongtech.console.domain.bean.UserLoginfo;
import com.herongtech.console.domain.common.bean.InterestResultDTO;
import com.herongtech.console.domain.rebuy.bean.RebuyBillInfo;
import com.herongtech.console.domain.sale.bean.SaleApplyInfo;
import com.herongtech.console.domain.sale.bean.SaleBillInfo;
import com.herongtech.console.domain.sale.bean.SaleSearchBean;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.rc.domain.bean.RgctBillData;

public interface ISaleService {
	public SaleApplyInfo getSaleApplyInfo(String saleId) throws SQLException;
	/**
	 * 根据条件查询转卖批次列表信息
	 * @param page
	 * @param searchBean
	 * @return
	 * @throws Exception
	 */
	public List<SaleApplyInfo> getSaleApplyListBySearchBeanForApply(Page page,SaleSearchBean searchBean) throws Exception ;
/**
 * 根据条件查询转卖批次列表信息
 * @param page
 * @param query
 * @return
 * @throws SQLException
 * @throws Exception 
 */
	public List<SaleApplyInfo> getSaleApplyListForCondition(Page page,SaleSearchBean query) throws SQLException, Exception;
	/**
	 * 根据查询条件（包括saleId）查询批次信息,（能够查询批次下票据总数、票据总金额）
	 * @param saleId
	 * @return
	 */
	public SaleApplyInfo getSaleApplyInfo(SaleSearchBean searchBean) throws SQLException;
	/**
	 * 新增批次
	 * @param saleApplyInfo
	 * @throws BizAppException 
	 */
	public void addSaleApply(SaleApplyInfo saleApplyInfo) throws SQLException, BizAppException;
	/**
	 * 修改批次
	 * @param saleApplyInfo
	 * @throws SQLException
	 * @throws BizAppException 
	 */
	public void modifySaleApply(SaleApplyInfo saleApplyInfo) throws SQLException, BizAppException;
	/**
	 * 功能描述：根据批次id查询清单
	 * @param discId
	 * @return
	 * @throws SQLException
	 * @throws BizAppException 
	 */
	public List<SaleBillInfo> getSaleBillListBySearchBeanForPage(Page page,SaleSearchBean query) throws SQLException, BizAppException;
	/**
	 * 功能描述：根据批次id删除批次信息
	 * @param saleIds
	 * @throws SQLException
	 * @throws BizAppException 
	 */
	public boolean delApplyInfoForSaleIds(String[] saleIds) throws SQLException, BizAppException;
	/**
	 * 根据salemx_id获取saleBillInfo信息
	 * @param salemxId
	 * @return
	 * @throws SQLException
	 */
	public SaleBillInfo getSaleBillForSalemxId(String salemxId) throws SQLException;
	/**
	 * 检查是否进行过利息试算
	 * @param ids
	 * @return
	 * @throws SQLException 
	 */
	public boolean checkBillsHasCalcInterest(String[] ids) throws SQLException;
	/**
	 * 利息试算--单个利息试算
	 * @param saleBill
	 * @param saleApply
	 * @return
	 * @throws Exception
	 */
	public InterestResultDTO interestTrial(SaleBillInfo saleBill,SaleApplyInfo saleApply) throws Exception;
	/**
	 * 利息试算--批量利息试算
	 * @param isSameCity
	 * @param delayType
	 * @param delayDays
	 * @param salemxIds
	 * @return
	 * @throws Exception
	 */
	public void interestTrial(SaleSearchBean bean) throws Exception;
	/**
	 * 利息调整
	 * @param salemxId
	 * @param adjustMoney
	 * @return
	 * @throws Exception 
	 */
	public boolean interestAdjust(String salemxId,String adjustMoney) throws BizAppException, Exception;
	/**
	 * 修改票据清单信息
	 * @param saleBill
	 * @throws SQLException
	 */
	public void modifySaleBill(SaleBillInfo saleBill) throws SQLException;
	
	/**
	 * 功能描述：根据salemxIds删除票据清单信息
	 * @param saleIds
	 * @throws SQLException
	 * @throws BizAppException 
	 */
	public int delBillInfoForSalemxIds(String[] saleIds) throws BizAppException;
	/**
	 * 查询待转出贴现票据列表
	 * @param page
	 * @param searchBean
	 * @return
	 * @throws BizAppException
	 */
	public List<RgctBillData> getRgctBillInfo(Page page,SaleSearchBean searchBean) throws BizAppException;
	/**
	 * 将票据信息添加到tsale_bill_info表
	 * @param ids
	 * @throws BizAppException
	 */
	public void addSaleBillInfo(String ids,String saleId) throws BizAppException, SQLException;
	
	/**
	 * 转卖记账
	 * @param ids
	 * @throws Exception
	 */
	public void submitSaleAccount(String saleId,String[] ids,UserLoginfo userLogonInfo)throws Exception;
	/**
	 * 提交转卖申请
	 * @param ids
	 * @return
	 * @throws BizAppException
	 * @throws SQLException
	 * @throws Exception
	 */
	public void submitSaleApply(String saleId,String[] ids) throws BizAppException, SQLException, Exception;
	/**
	 * 提交转卖审核
	 * @param ids
	 * @param auditStatus
	 * @param option
	 * @return
	 * @throws Exception
	 */
	public void submitSaleAudit(String[] ids,String auditStatus,String option) throws Exception;
	/**
	 * 根据查询条件获取批次下票据清单
	 * @param searchBean
	 * @return
	 * @throws SQLException
	 */
	public List<SaleBillInfo> getSaleBillListBySearchBean(SaleSearchBean searchBean) throws SQLException;
	/**
	 * 撤销申请
	 * @param saleId
	 * @param salemxIds
	 * @throws BizAppException
	 */
	public void cancelApply(String saleId,String salemxIds) throws BizAppException;
	/**
	 * 撤销审核
	 * @param salemxIds
	 * @throws BizAppException
	 */
	public void cancelAudit(String salemxIds,String saleId) throws BizAppException;
	/**
	 * 撤销记账
	 * @param ids
	 * @throws BizAppException
	 */
	public void cancelAccount(String saleId,String ids) throws BizAppException;

	

	/**
	 * 转卖电票背书
	 * @param saleId
	 * @param ids
	 * @param user
	 * @throws BizAppException
	 * @throws SQLException 
	 * @throws Exception 
	 */
	public void submitSaleEndorse(String saleId,String[] ids,UserLoginfo user) throws Exception;
	/**
     * 撤销转卖电票背书
     * @param saleId
     * @param ids
	 * @throws BizAppException 
     */
    public void cancelElecSaleEndorse(String saleId,String[] ids) throws BizAppException;
    /**
     * 转卖电票背书确认签收
     * @param saleId
     * @param ids
     * @throws BizAppException 
     */
    public boolean confirmEndorse(String saleId,String[] ids) throws BizAppException;
	/**
	 * 为转入系统提供的系统内签收方法
	 * @param billList
	 * @throws Exception 
	 */
    public void agreeForRebuy(List<RebuyBillInfo> billList) throws Exception;
}
