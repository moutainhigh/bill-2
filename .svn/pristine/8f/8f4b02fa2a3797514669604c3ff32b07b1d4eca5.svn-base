package com.herongtech.console.service.busiservice.out;

import java.sql.SQLException;
import java.util.List;

import com.herongtech.console.core.common.json.Page;
import com.herongtech.console.domain.out.bean.OutApplyInfo;
import com.herongtech.console.domain.out.bean.OutBillInfo;
import com.herongtech.console.domain.out.bean.OutSearchBean;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.rc.domain.bean.RgctBillInfo;

public interface IOutService {
	
	/**
	 * 功能描述：根据条件查询批次列表(申请岗)
	 * @param page
	 * @param query
	 * @return
	 * @throws SQLException
	 * @throws Exception 
	 */
	public List<OutApplyInfo> getOutApplyListForApply(Page page,OutSearchBean query) throws SQLException, Exception;

	/**
	 * 功能描述：根据条件查询批次列表(审核 入库岗)
	 * @param page
	 * @param query
	 * @return
	 * @throws SQLException
	 */
	public List<OutApplyInfo> getOutApplyListBySearchBean(Page page,OutSearchBean query) throws SQLException;
	
	/**
	 * 功能描述：根据批次id查询清单(申请 审核 入库)
	 * @param outId
	 * @return
	 * @throws SQLException
	 */
	public List<OutBillInfo> getOutBillListBySearchBean(Page page,OutSearchBean query) throws Exception;
	
	/**
	 * 功能描述：批次详情
	 * @param outId
	 * @return
	 * @throws SQLException
	 * @throws BizAppException 
	 */
	public OutApplyInfo getOutApplyInfo(String outId,OutSearchBean query) throws SQLException, BizAppException;
	
	/**
	 * 功能描述：票据详情
	 * @param outmxId
	 * @return
	 * @throws Exception 
	 */
	public List<OutBillInfo> getOutBillInfo(Page page,OutSearchBean query) throws Exception;
	
	/**
	 * 功能描述：添加批次
	 * @param OutId
	 * @return
	 * @throws BizAppException
	 */
	public void addOutApplyInfo(OutApplyInfo outApplyInfo) throws BizAppException;
	
	/**
	 * 功能描述：修改批次
	 * @param OutApplyInfo
	 * @return
	 * @throws BizAppException
	 */
	 public void modiOutApplyInfo(OutApplyInfo outApplyInfo) throws BizAppException;
	
	 /**
	 * 功能描述：修改批次 批次状态修改
	 * @param OutApplyInfo
	 * @return
	 * @throws BizAppException
	 */
	 public void modifyOutApplyInfo(OutApplyInfo outApplyInfo) throws BizAppException;
	 
	 /**
	 * 功能描述：删除批次
	 * @param outIds
	 * @throws Exception
	 */
	 public boolean delApplyInfoForOutIds(Page page,String[] outIds) throws Exception;
	 
	 /**
	 * 功能描述：清单详情
	 * @param outId
	 * @return
	 * @throws SQLException
	 * @throws BizAppException
	 */
	 public OutBillInfo getOutBillInfo(String outId)throws BizAppException;
	 
	 /**
	 * 功能描述：修改清单
	 * @param OutBillInfo
	 * @return
	 * @throws BizAppException
	 */
	 public void modifyOutBillInfo(OutBillInfo outBillInfo) throws BizAppException;
	 
	 /**
	 * 功能描述：票据申请提交
	 * @param ids
	 * @return
	 */
	 public int applySubmit(String ids,OutApplyInfo outApplyInfo) throws Exception;
	 
	 /**
	 * 功能描述：票据审核提交
	 * @param ids
	 * @return
	 * @throws Exception 
	 */
	 public int auditSubmit(String ids,String status,String option,OutSearchBean query) throws BizAppException, Exception;
	 
	 /**
	 * 出库
	 * 
	 * @return
	 */
	 public int accountOutBill(String ids, String outId) throws Exception;
	 
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
	 public int totalCount(OutBillInfo bill) throws SQLException;

	 

}
