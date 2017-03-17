/********************************************
 * 文件名称: IFacCreateFlowService.java
 * 系统名称: 基础技术平台V2.0
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: 2.0.0.1
 * 开发人员: songzx
 * 开发时间: 2016-10-31 下午03:48:52
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期    修改人员    修改说明
 *********************************************/
package com.herongtech.console.service.interfaces;

import java.sql.SQLException;
import java.util.List;

import com.herongtech.console.domain.common.bean.FacCreateFlow;
import com.herongtech.console.domain.common.bean.SearchFacCreateFlowBean;

public interface IFacCreateFlowService {
	public static final String HERONGTECH_VERSION="@system 票据管理平台 @version 2.0.0.1 @lastModiDate @describe ";

	/**
	 * 功能描述：添加额度扣减流水
	 * @param fac
	 */
	public void addFacCreateFlow(FacCreateFlow fac) throws SQLException;

	/**
	 * 功能描述：更新流水表
	 * @param facCreateFlow
	 */
	public void updateFacCreateFlow(FacCreateFlow facCreateFlow) throws SQLException;
	
	/**
	 * 功能描述：根据条件查询流水
	 * @param searchBean
	 * @return
	 * @throws SQLException
	 */
	public List<FacCreateFlow> getFacCreateFlowByCondition(SearchFacCreateFlowBean searchBean) throws SQLException;
}

