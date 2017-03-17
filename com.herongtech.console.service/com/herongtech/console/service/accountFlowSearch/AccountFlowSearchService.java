/********************************************
 * 文件名称: AccountFlowSearchService.java
 * 名称:    基础技术平台V2.0
 * 模块名称: 账务流水查询类
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 版本:    2.0.0.1
 * 开发人员: wzc
 * 开发时间: 2016-12-15 
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期    修改人员    修改说明
 *********************************************/

package com.herongtech.console.service.accountFlowSearch;

import java.sql.SQLException;
import java.util.List;

import com.herongtech.console.core.common.json.Page;
import com.herongtech.console.core.common.search.BaseSearchBean;
import com.herongtech.console.core.common.search.OrderBean;
import com.herongtech.console.domain.acct.bean.AcctFlow;
import com.herongtech.console.domain.acct.bean.AcctFlowInfo;
import com.herongtech.console.domain.acct.bean.AcctFlowSearchBean;
import com.herongtech.console.domain.dao.AccountFlowSearchDao;
import com.herongtech.exception.impl.BizAppException;


/**
 * 机构取法
 *
 */
public class AccountFlowSearchService implements IAccountFlowSearchService{
	public static final String HUNDSUN_VERSION="@system 票据管理平台 @version 2.0.0.1 @lastModiDate @describe ";
	private AccountFlowSearchDao accountFlowSearchDao = new AccountFlowSearchDao();
	/**
	 * 功能描述：根据条件查询批次列表(账务流水使用)
	 * @param page
	 * @param query
	 * @return
	 * @throws SQLException
	 */
	public List<AcctFlow> getAccountFlowCondition(Page page,AcctFlowSearchBean query) throws SQLException{
		if("1".equals(query.getProdNo())){//贴现
			query.setProdNo("001001%");
		}else if("2".equals(query.getProdNo())){//转贴现买入
			query.setProdNo("001002%");
		}else if("3".equals(query.getProdNo())){//转贴现卖出
			query.setProdNo("001003%");
		}else if("4".equals(query.getProdNo())){//质押
			query.setProdNo("001004001");
		}else if("5".equals(query.getProdNo())){//解质押
			query.setProdNo("001005001");
		}else if("6".equals(query.getProdNo())){//收回记账
			query.setProdNo("001006001");
		}else if("7".equals(query.getProdNo())){//存票
			query.setProdNo("001007%");
		}else if("8".equals(query.getProdNo())){//取票
			query.setProdNo("001008%");
		}else if("9".equals(query.getProdNo())){//到期回购
			query.setProdNo("001009%");
		}else if("10".equals(query.getProdNo())){//到期返售
			query.setProdNo("001010%");
		}else{
			query.setProdNo(null);
		}
		query.addSpecialOpertion("prodNo", BaseSearchBean.LIKE);
	    return accountFlowSearchDao.getAccountFlowCondition(page,query);
	}
	
	/**
	 * 根据主键查询批次信息
	 * @param 
	 * @return
	 */
	public AcctFlow getAcctFlowForAfID(AcctFlowSearchBean query) throws SQLException{	
		return accountFlowSearchDao.getAcctFlowForAfID(query);
	}
	/**
	 * 功能描述：根据条件查询清单
	 * @param 
	 * @return
	 */
	public List<AcctFlowInfo> getAcctFlowInfoListForBatch(Page page,AcctFlowSearchBean query) throws Exception{
		initQueryCondition(query);
		OrderBean order=new OrderBean("afId",true);
	    query.appendOrder(order);
		return accountFlowSearchDao.getAcctFlowInfoListForBatch(page,query);
	}
	
	/**
	 * 初始化查询条件
	 * @param query
	 */
	private void initQueryCondition(AcctFlowSearchBean query){
		query.setDfaultSrchTbAliasName("bill");
	}
	
}
