package com.herongtech.console.service.busiservice.into;

import java.sql.SQLException;
import java.util.List;

import com.herongtech.console.core.common.json.Page;
import com.herongtech.console.domain.into.bean.IntoApplyInfo;
import com.herongtech.console.domain.into.bean.IntoBillInfo;
import com.herongtech.console.domain.into.bean.IntoSearchBean;
import com.herongtech.console.domain.save.bean.SaveApplyInfo;
import com.herongtech.console.domain.save.bean.SaveBillInfo;
import com.herongtech.exception.impl.BizAppException;

public interface IIntoService {
	
	/**
	 * 功能描述：根据条件查询批次列表(申请岗)
	 * @param page
	 * @param query
	 * @return
	 * @throws SQLException
	 * @throws Exception 
	 */
	public List<IntoApplyInfo> getIntoApplyListForApply(Page page,IntoSearchBean query) throws SQLException, Exception;

	/**
	 * 功能描述：根据条件查询批次列表(审核 入库岗)
	 * @param page
	 * @param query
	 * @return
	 * @throws SQLException
	 */
	public List<IntoApplyInfo> getIntoApplyListBySearchBean(Page page,IntoSearchBean query) throws SQLException;
	
	/**
	 * 功能描述：根据批次id查询清单(申请 审核 入库)
	 * @param intoId
	 * @return
	 * @throws SQLException
	 */
	public List<IntoBillInfo> getIntoBillListBySearchBean(Page page,IntoSearchBean query) throws Exception;
	
	/**
	 * 功能描述：批次详情
	 * @param intoId
	 * @return
	 * @throws SQLException
	 * @throws BizAppException 
	 */
	public IntoApplyInfo getIntoApplyInfo(String intoId,IntoSearchBean query) throws SQLException, BizAppException;
	
	/**
	 * 功能描述：票据详情
	 * @param intomxId
	 * @return
	 * @throws Exception 
	 */
	public List<IntoBillInfo> getIntoBillInfo(Page page,IntoSearchBean query) throws Exception;
	
	/**
	 * 功能描述：添加批次
	 * @param IntoId
	 * @return
	 * @throws BizAppException
	 */
	public void addIntoApplyInfo(IntoApplyInfo intoApplyInfo) throws BizAppException;
	
	/**
	 * 功能描述：修改批次
	 * @param IntoApplyInfo
	 * @return
	 * @throws BizAppException
	 */
	 public void modiIntoApplyInfo(IntoApplyInfo intoApplyInfo) throws BizAppException;
	
	 /**
	 * 功能描述：修改批次 批次状态修改
	 * @param IntoApplyInfo
	 * @return
	 * @throws BizAppException
	 */
	 public void modifyIntoApplyInfo(IntoApplyInfo intoApplyInfo) throws BizAppException;
	 
	 /**
	 * 功能描述：删除批次
	 * @param intoIds
	 * @throws Exception
	 */
	 public boolean delApplyInfoForIntoIds(Page page,String[] intoIds) throws Exception;
	 
	 /**
	 * 功能描述：清单详情
	 * @param intoId
	 * @return
	 * @throws SQLException
	 * @throws BizAppException
	 */
	 public IntoBillInfo getIntoBillInfo(String intoId)throws BizAppException;
	 
	 /**
	 * 功能描述：通过票据中心ID查票据清单
	 * @param rgctId
	 * @return
	 */
	 public IntoBillInfo getIntoBillInfoByRgctId(String rgctId) throws SQLException;

	 
	 /**
	 * 功能描述：添加清单
	 * @param intoId
	 * @return
	 * @throws BizAppException
	 */
	 public void addIntoBillInfo(IntoBillInfo intoBillInfo) throws BizAppException;
	 
	 /**
	 * 功能描述：修改清单
	 * @param IntoBillInfo
	 * @return
	 * @throws BizAppException
	 */
	 public void modiIntoBillInfo(IntoBillInfo intoBillInfo) throws BizAppException;
	 
	 /**
	 * 功能描述：修改清单(调rc)
	 * @param IntoBillInfo
	 * @return
	 * @throws BizAppException
	 */
	 public void modifyIntoBillInfo(IntoBillInfo intoBillInfo) throws BizAppException;
	 
	 /**
	 * 功能描述：票据删除
	 * @param ids
	 * @return
	 */
	public int delBill(String ids) throws Exception;
	
	/**
	 * 功能描述：票据申请提交
	 * @param ids
	 * @return
	 */
	 public int applySubmit(String ids) throws Exception;
	
	/**
	 * 功能描述：票据审核提交
	 * @param ids
	 * @return
	 * @throws Exception 
	 */
	 public int auditSubmit(String ids,String status,String option,IntoSearchBean query) throws BizAppException, Exception;
	
	/**
	 * 功能描述：入库
	 * @param ids
	 * @return
	 */
	 public int accountIntoBill(String intomxIds,String intoId) throws Exception;
	
	/**
	 * 功能描述：使票据状态回到上一状态
	 * @param ids
	 * @return
	 */
	 public int cancel(String conName,String methodName,String ids) throws Exception;
	 /**
		 * 功能描述：电票未签收清单列表
		 * @param IntoId
		 * @return
		 * @throws SQLException
		 */
		public List<IntoBillInfo> getElecIntoBillListForReceive(Page page,IntoSearchBean query) throws Exception;
		/**
		 * 根据id得到清单
		 * @param ids
		 * @return
		 * @throws SQLException
		 */
		public List<IntoBillInfo> getElecReceiveForId(String ids) throws SQLException;
		/**
		 * 功能描述：添加批次并签收票据
		 * @param ids
		 * @param saveApplyInfo
		 * @throws BizAppException
		 */
		public void addBatchAndSignBill(String ids, IntoApplyInfo intoApplyInfo)throws BizAppException;
		/**
		 * 功能描述：剩余票据总数量
		 * @param  bill
		 * @throws SQLException
		 */
		public int totalCount(IntoBillInfo bill) throws SQLException;
	 
}
