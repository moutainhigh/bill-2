package com.herongtech.console.service.tailAfterSearch;

import java.sql.SQLException;
import java.util.List;

import com.herongtech.console.service.common.machinestatus.StatusDict;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.rc.domain.bean.RgctBill;


public interface ITailAfterSearchService {
	/**
	 * 功能描述：跟踪查询
	 * @param quary
	 * @return
	 * @throws SQLException
	 * @throws BizAppException 
	 */
	public RgctBill getTailAfterServiceCondition(String billNo) throws SQLException, BizAppException;
	/**
	 * 功能描述：获取Map<String,List<StatusDict>>
	 * 		map中key表示票据当前操作状态
	 * 		map中value表示票据流程图中所有节点集合
	 * @throws Exception 
	 */
	public List<StatusDict> getStatusDictListByRgctBillInfo(RgctBill bill,String modelCode) throws Exception;
	/**
	 * 获取票据当前操作状态
	 * 注意：在使用前先调用init方法，进行初始化
	 * @return
	 * @throws BizAppException 
	 */
	public String[] getModelCodeAndOperStatus(RgctBill bill) throws Exception ;
}
