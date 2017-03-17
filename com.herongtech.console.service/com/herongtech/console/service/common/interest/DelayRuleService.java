/********************************************
 * 文件名称: DelayRuleService.java
 * 系统名称: 基础技术平台V2.0
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: 2.0.0.1
 * 开发人员: vvv
 * 开发时间: 2016-7-12
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期    修改人员    修改说明
 *********************************************/

package com.herongtech.console.service.common.interest;

import java.sql.SQLException;

import com.herongtech.console.cache.DelayRuleCache;
import com.herongtech.console.domain.bean.DelayRule;
import com.herongtech.console.domain.dao.DelayRuleDao;
import com.herongtech.console.service.interfaces.IDelayRuleService;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.sysconstant.ISysErrorNo;

/**
 * 顺延规则取法
 *
 */
public class DelayRuleService implements IDelayRuleService{
	public static final String HUNDSUN_VERSION="@system 票据管理平台 @version 2.0.0.1 @lastModiDate @describe ";

	/**
	 * 获取顺延规则表
	 */
	public  DelayRule getDelayRule(Long id)throws BizAppException{
		DelayRuleDao dao=new DelayRuleDao();
		try {
			return dao.getDelayRule(id);
		}catch (SQLException e) {
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "");
		}
	}
	
	/**
	 * 插入顺延规则表
	 */
	public void addDelayRule(DelayRule delayrule)throws BizAppException{
		DelayRuleDao dao=new DelayRuleDao();
		try {
			if (dao.addDelayRule(delayrule) != 1) {
			      throw new BizAppException(ISysErrorNo.ERR_DBERR, "增加DelayRule失败");
			}
		} catch (SQLException e) {
             throw new BizAppException(ISysErrorNo.ERR_DBERR,"");
		}
		
	}
	/**
	 * 修改顺延规则表
	 */
	public void modifyDelayRule(DelayRule delayrule)throws BizAppException{
		DelayRuleDao dao=new DelayRuleDao();
		try {
			if (dao.modifyDelayRule(delayrule) != 1) {
				 throw new BizAppException(ISysErrorNo.ERR_DBERR, "修改DelayRule失败");
			}
		} catch (SQLException e) {
             throw new BizAppException(ISysErrorNo.ERR_DBERR,"");
		}
		
	}

    @Override
    public DelayRule getDelayRule(String productNo, String billClass,
            String billType) throws BizAppException {
        String key = productNo+billClass+billType;
		DelayRule delayrule = DelayRuleCache.getDelayRuleCache().getDelayRulemapvalue(key);
		return delayrule;
    }
}
