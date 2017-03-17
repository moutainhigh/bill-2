package com.herongtech.console.service.busiservice.repurchasedcollateralization;

import java.sql.SQLException;
import java.util.List;

import com.herongtech.console.core.common.json.Page;
import com.herongtech.console.domain.get.bean.GetApplyInfo;
import com.herongtech.console.domain.get.bean.GetBillInfo;
import com.herongtech.console.domain.get.bean.GetSearchBean;
import com.herongtech.exception.impl.BizAppException;

public interface IRepurCollateService {
	
	/**
	 * 功能描述：根据条件查询批次列表(申请岗)
	 * @param page
	 * @param query
	 * @return
	 * @throws SQLException
	 * @throws Exception 
	 */
	public List<GetApplyInfo> getGetApplyListForApply(Page page,GetSearchBean query) throws SQLException, Exception;

	/**
	 * 功能描述：根据条件查询批次列表(审核 入库岗)
	 * @param page
	 * @param query
	 * @return
	 * @throws SQLException
	 */
	public List<GetApplyInfo> getGetApplyListBySearchBean(Page page,GetSearchBean query) throws SQLException;
	
	/**
	 * 功能描述：根据批次id查询清单(申请 审核 入库)
	 * @param getId
	 * @return
	 * @throws SQLException
	 */
	public List<GetBillInfo> getGetBillListBySearchBean(Page page,GetSearchBean query) throws Exception;
	
	/**
	 * 功能描述：批次详情
	 * @param getId
	 * @return
	 * @throws SQLException
	 * @throws BizAppException 
	 */
	public GetApplyInfo getGetApplyInfo(String getId,GetSearchBean query) throws SQLException, BizAppException;
	
	/**
	 * 功能描述：票据详情
	 * @param getmxId
	 * @return
	 * @throws Exception 
	 */
	public List<GetBillInfo> getGetBillInfo(Page page,GetSearchBean query) throws Exception;
	
	/**
	 * 功能描述：添加批次
	 * @param getId
	 * @return
	 * @throws BizAppException
	 */
	public void addGetApplyInfo(GetApplyInfo getApplyInfo) throws BizAppException;
	
	/**
	 * 功能描述：修改批次
	 * @param GetApplyInfo
	 * @return
	 * @throws BizAppException
	 */
	 public void modiGetApplyInfo(GetApplyInfo getApplyInfo) throws BizAppException;
	
	 /**
	 * 功能描述：修改批次 批次状态修改
	 * @param GetApplyInfo
	 * @return
	 * @throws BizAppException
	 */
	 public void modifyGetApplyInfo(GetApplyInfo getApplyInfo) throws BizAppException;
	 
	 /**
	 * 功能描述：删除批次
	 * @param getIds
	 * @throws Exception
	 */
	 public boolean delApplyInfoForGetIds(Page page,String[] getIds) throws Exception;
	 
	 /**
	 * 功能描述：清单详情
	 * @param getId
	 * @return
	 * @throws SQLException
	 * @throws BizAppException
	 */
	 public GetBillInfo getGetBillInfo(String getId)throws BizAppException;
	 
	 /**
	 * 功能描述：修改清单
	 * @param GetBillInfo
	 * @return
	 * @throws BizAppException
	 */
	 public void modifyGetBillInfo(GetBillInfo getBillInfo) throws BizAppException;
	 
	 /**
	 * 功能描述：票据申请提交
	 * @param ids
	 * @return
	 */
	 public int applySubmit(String ids,GetApplyInfo getApplyInfo) throws Exception;
	 
	 /**
	 * 出库
	 * 
	 * @return
	 */
	 public void accountRepurCollate(String ids, String getId) throws Exception;
	 
	 /**
	 * 功能描述:撤销票据状态到上一个操作状态
	 * 
	 * @param ids
	 * @return
	 */
	 public int cancel(String conName, String methodName, String ids)throws Exception;
	 
	 /**
	 * 功能描述：剩余票据总数量
	 * @param bill
	 * @return
	 * @throws SQLException
	 */
	 public int totalCount(GetBillInfo bill) throws SQLException;
	 public List<GetBillInfo> getGetBillListByMxids (String[] ids) throws SQLException;

}
