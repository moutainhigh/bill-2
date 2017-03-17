package com.herongtech.console.service.interfaces;

import java.sql.SQLException;
import java.util.List;

import com.herongtech.console.core.common.json.Page;
import com.herongtech.console.domain.bean.UserLoginfo;
import com.herongtech.console.domain.common.bean.InterestResultDTO;
import com.herongtech.console.domain.rebuy.bean.RebuyApplyInfo;
import com.herongtech.console.domain.rebuy.bean.RebuyBillInfo;
import com.herongtech.console.domain.saleback.bean.SaleBackSearchBean;
import com.herongtech.console.domain.saleback.bean.SalebackApplyInfo;
import com.herongtech.console.domain.saleback.bean.SalebackBillInfo;
import com.herongtech.console.domain.subcoll.bean.SubcollBillInfo;

import com.herongtech.exception.impl.BizAppException;

/**
 * @author 李江涛
 * 返售接口
 */
public interface ISaleBackService {


	/**
	 * 返售申请批次页面显示
	 * @return
	 * @throws Exception 
	 */
	public List<RebuyApplyInfo> getRebuyApplyInfoBydisctypeandnowdate(Page page,
			SaleBackSearchBean query) throws Exception;

	
	//
	/**
	 * 生成返售的批次
	 * @param ids
	 * @param discApplyInfo
	 * @throws BizAppException
	 */
	public SalebackApplyInfo saveConditionForAddSalebackApplyInfoAndModifySalebackBillInfo(String rebuyId,String newsalebackId,String iscancel) throws BizAppException;
	
	/**获得反售清单集合
	 * @throws BizAppException 
	 * @throws Exception */
	public List<SalebackBillInfo> getSalebackBillInfolist(Page page,SaleBackSearchBean bean,String iscancel,String rateids) throws BizAppException, Exception;
	
	/**
	 * 批量利息试算
	 * @param searchBean
	 * @return
	 * @throws Exception
	 */
	public void interestTrial(SaleBackSearchBean bean) throws Exception;
	
	
	/**
	 * 单个利息试算
	 */
	public InterestResultDTO interestTrial(SalebackBillInfo salebackBill,SalebackApplyInfo salebackApply,String isSameCity) throws Exception;
	
	/**返售申请提交
	 * @throws SQLException 
	 * @throws Exception */
	public boolean salebackapplysubmit(String salebackmxIds) throws SQLException, Exception;
	
	/**是否利息试算
	 * @throws BizAppException */
	public boolean getSalebackBillInfolistbysalebackmxIds(String salebackmxids,String salebackId) throws SQLException, BizAppException;
	
/**
	 * 功能描述：根据条件查询批次列表
	 * @param page
	 * @param query
	 * @return
	 * @throws SQLException
 * @throws Exception 
	 */
	public List<SalebackApplyInfo> getSaleBackApplyForCondition(Page page,SaleBackSearchBean query) throws SQLException, Exception;
	
	/**
	 * 功能描述：得到批次详情
	 * @param salebackId
	 * @param query
	 * @return
	 * @throws SQLException
	 */
	public SalebackApplyInfo getSalebackApplyInfo(String salebackId,SaleBackSearchBean query) throws SQLException;
	
	/**
	 * 功能描述：根据批次id查询清单
	 * @param page
	 * @param query
	 * @return
	 * @throws SQLException
	 */
	public List<SalebackBillInfo> getSaleBackBillListForBatch(Page page,SaleBackSearchBean query) throws SQLException;
	
	/**
	 * 功能描述：得到返售确认電票根据清单id
	 * @param ids
	 * @return
	 * @throws SQLException
	 */
	public List<SalebackBillInfo> getConfirmReceiveBillForId(String ids) throws SQLException;
	
	/**
     * 返售确认提交
     * @param saleId
     * @param ids
     * @throws BizAppException 
	 * @throws Exception 
     */
	public boolean confirmSubmit(String salebackId,String ids) throws BizAppException, Exception;
	
	/**
     * 确认记账
     * @param ids
     * @throws SQLException 
	 * @throws Exception 
     */
	public void accouontSubmit(String ids) throws SQLException, Exception;
	
	
	
		
	
	
	
	/**
	 * 撤销申请批次查询
	 * @return
	 * @throws SQLException 
	 * @throws Exception 
	 */
	public List<SalebackApplyInfo> getSalebackApplyInfolist() throws SQLException, Exception;
	
	/**
	 * 根据searchbean撤销申请批次查询
	 * @return
	 * @throws BizAppException 
	 * @throws Exception 
	 * @throws Exception 
	 * @throws SQLException 
	 */
	public List<SalebackApplyInfo> getSalebackApplyInfolistbysearchbean(SaleBackSearchBean bean,Page page) throws BizAppException, Exception;
	
	/**根据searchbean撤销申请清单查询
	 * @throws Exception */
	public List<SalebackBillInfo> getSalebackBillInfolistbysearchbean(SaleBackSearchBean query, Page page) throws SQLException, Exception;
	
	
	
	/**根据清单ids(清单主键字符串)和状态更改清单状态   
	 * @throws SQLException 
	 * @throws BizAppException */
	public void editsalebackbillstatus(String ids,String status,String salebackId) throws SQLException, BizAppException;

	
	
	
	
	

	/**根据searchbean背书批次查询
	 * @throws Exception */
	List<SalebackApplyInfo> getSalebackendorseApplyInfolistbysearchbean(
			SaleBackSearchBean query, Page page) throws BizAppException, Exception;
	
	/**根据searchbean撤销申请背书清单查询
	 * @throws Exception */
	public List<SalebackBillInfo> getSalebackcxBillInfolistbysearchbean(SaleBackSearchBean query, Page page) throws SQLException, Exception;
	
	/**根据searchbean撤销背书批次查询
	 * @throws Exception */
	public List<SalebackApplyInfo> getSalebackendorsecxInfolistbysearchbean(
			SaleBackSearchBean query, Page page) throws BizAppException, Exception;
	
	/**
	 * 根据rgctId获取票据信息(单张票)
	 * @param rgctId 登记中心id
	 * @return
	 * @throws BizAppException
	 */
	public SalebackBillInfo getSalebackBillInfoForRgctId(String rgctId) throws BizAppException;
	
	/**
	 * 更新实体SalebackBillInfo信息
	 * @param billInfo 要修改的字段值(可以多个)
	 * @throws BizAppException
	 */
	public void modifySalebackBillInfo(SalebackBillInfo billInfo) throws BizAppException ;
	
	
	/**
     * 电票返售背书 1,对选中的票据进行背书申请报文
	 * 2,工作流往下走,到等待节点等待033的确认报文,然后进入回调操作
     * @throws BizAppException 
	 * @throws SQLException 
	 * @throws Exception 
     */
    public void submitSalebackEndorse(String saleId,String ids,UserLoginfo userInfo) throws Exception;
    
    	/**
	 * 撤销到期反售电票背书
	 * @param ids
	 * @throws BizAppException
	 */
	public void cancelElecSaleBackEndorse(String ids) throws BizAppException;
	
	public void modifySalebackApplyInfo(SalebackApplyInfo obj) throws SQLException;
	
	
	public List<SalebackBillInfo> getSaleBackBillListForBatchtwo(Page page,
			String salebackId,String rateids,String rebuyId) throws SQLException, Exception ;
	
	/**根据批次主键获得批次*/
	public SalebackApplyInfo getSalebackApplyInfo(String salebackId) throws SQLException;
	
	/**根据批次主键获得批次*/
	public SalebackApplyInfo getSalebackApplyInfo(Page page,String salebackId) throws SQLException;
	
	/**把rebuy的汇总信息添加到salebackapply
	 * @throws BizAppException */
	public SalebackApplyInfo copyrebuytosalebackapply(SalebackApplyInfo batch,RebuyApplyInfo apply) throws BizAppException;
	
	/**
	 * 把转入下的所有清单关联到新返售批次
	 * @param salebackId
	 * @param salebackIdss
	 * @throws BizAppException 
	 */
	public void salebackaplyandsalebackbill(SaleBackSearchBean bean,String salebackIdss) throws BizAppException;
	
	/**
	 * 根据清单集合得到所有id主键
	 * @param billlist
	 * @return
	 */
	public String getallids(List<SalebackBillInfo> billlist);
	
	/**
	 * 040转卖断处理
	 * @param rgctId
	 * @throws SQLException 
	 * @throws ServiceException
	 */
	public void overdueBill(String rgctId) throws SQLException;
	
	
	/**
	 * 初始化返售清单表（纸票）
	 * @throws SQLException 
	 * @throws BizAppException 
	 * @throws Exception 
	 */
	public void initsalebackbill(List<RebuyBillInfo> billlist,RebuyApplyInfo rebuyApply) throws SQLException, BizAppException, Exception;
	
	
	/**
	 * 初始化票据管理searchbean
	 * @param bean
	 * @return
	 */
	public SaleBackSearchBean initsalebackSearchbean(SaleBackSearchBean bean);
	
	/**
	 * 纸票转入撤销记账后对反手清单的处理（删除）
	 * @param ids
	 * @throws Exception
	 */
	public void rebuycancelaccountaftersalebackupdatestatus(List<RebuyBillInfo> rebuylist) throws Exception;
	
	
	/**
	 * 审批会掉变更状体
	 * @param batchId
	 * @param status
	 * @return
	 */
	public List<SalebackBillInfo> getSaveBillForSaveIdAndStatus(String batchId,String status);


	/**
	 * 审核批次页面获得批次
	 * @param page
	 * @param query
	 * @return
	 * @throws Exception
	 */
	List<SalebackApplyInfo> getSaleBackAuditForCondition(Page page,
			SaleBackSearchBean query) throws Exception;
	/**
	 * 获得审核清单
	 * @param query
	 * @param page
	 * @return
	 * @throws Exception 
	 */
	public List<SalebackBillInfo> getauditbilllist(SaleBackSearchBean query,Page page) throws Exception;
	
	
	/**
	 * 撤销审核差批次
	 * @param query
	 * @param page
	 * @return
	 * @throws Exception
	 */
	public List<SalebackApplyInfo> getcancelauditbatchlist(SaleBackSearchBean query,Page page) throws Exception;

	/**
	 * 撤销审核差批次
	 * @param query
	 * @param page
	 * @return
	 * @throws Exception
	 */
	List<SalebackBillInfo> getcancelauditbilllist(SaleBackSearchBean query,
			Page page) throws Exception;
	
	/**
	 * 返售审核
	 * @param ids
	 * @param auditStatus
	 * @param option
	 * @throws Exception
	 */
	public void submitSaleAudit(String[] ids,String auditStatus,String option) throws Exception;
	
	
	/**
	 * 撤销审核
	 * @param query
	 * @param page
	 * @throws Exception 
	 */
	public void cancelaudit(SaleBackSearchBean query,Page page,String salebackmxId) throws Exception;
	
	
	/**
	 * 审核记账批次查询
	 * @param query
	 * @param page
	 * @return
	 * @throws Exception
	 */
	public List<SalebackApplyInfo> getaccountbatchlist(SaleBackSearchBean query,Page page) throws Exception;
	
	
	/**
	 * 记账清单查询
	 * @param query
	 * @param page
	 * @return
	 * @throws SQLException
	 */
	public List<SalebackBillInfo> getaccountbilllist(SaleBackSearchBean query,Page page) throws SQLException;
	
	
	/**
	 * 纸票返售记账提交
	 * @param query
	 * @param salebackmxIds
	 * @throws SQLException 
	 * @throws Exception 
	 */
	public void entityaccountapply(SaleBackSearchBean query,String salebackmxIds) throws SQLException, Exception;
	
	
	/**
	 * 纸票撤销记账
	 * @param query
	 * @param salebackmxIds
	 * @throws SQLException 
	 * @throws Exception 
	 */
	public void entitycancelaccount(SaleBackSearchBean query,String salebackmxIds) throws SQLException, Exception;
	
	/**返售记账批次查询
	 * @param page
	 * @param query
	 * @return
	 * @throws SQLException
	 */
	public List<SalebackApplyInfo> getSaleBackaccountbatch(Page page,
			SaleBackSearchBean query) throws SQLException;
	 /** 
     *纸票申请提交 
     */
    public void submitSalebackapplyentity(String salebackId,String ids,UserLoginfo userInfo) throws Exception;

/**
	 * 是否为新建的批次
	 * @param rebuyId
	 * @param newsalebackId
	 * @param iscancel
	 * @return
	 * @throws BizAppException
	 */
	public String isnewcreatebatch(String rebuyId, String newsalebackId,
			String iscancel) throws BizAppException;


/**
 * 得到合计完成的批次
 * @param page
 * @param query
 * @return
 * @throws Exception
 */
List<RebuyApplyInfo> getRebuyApplyInfoBydisctypeandnowdatetwo(Page page,
		SaleBackSearchBean query) throws Exception;
}
