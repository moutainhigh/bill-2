/********************************************
* 文件名称: DraftMappingDao.java
* 系统名称: 合融基础技术平台V2.0
* 模块名称:
* 软件版权: 北京合融科技有限公司
* 功能说明: 
* 系统版本: 2.0.0.1
* 开发人员: 技术平台组
* 开发时间:  
* 审核人员:
* 相关文档:
* 修改记录: 修改日期    修改人员    修改说明
*********************************************/
package com.herongtech.rc.domain.dao;

import java.sql.SQLException;
import java.util.List;

import com.herongtech.commons.tools.StringUtil;
import com.herongtech.db.impl.session.DBFactory;
import com.herongtech.db.interfaces.IDB;
import com.herongtech.rc.domain.bean.DraftMapping;
public class DraftMappingDao{


/**
 * 根据methodId获取报文映射信息。
 * 临时用，后续从缓存取值
 * @param methodId
 * @param param
 * @return
 * @throws SQLException
 */
public DraftMapping getDraftMapping(String methodId,String param) throws SQLException {
	IDB session = DBFactory.getDB();
	DraftMapping obj=null;
	if(StringUtil.isEmpty(param)){
	    obj= (DraftMapping)session.getObject("select * from TDRAFT_MAPPING where METHOD_ID=?",DraftMapping.class,methodId); 
	}else{
	    obj= (DraftMapping)session.getObject("select * from TDRAFT_MAPPING where METHOD_ID=? and PARAM=?",DraftMapping.class,methodId,param); 
	}
	
	return obj;
}

    public List<DraftMapping> getAllDraftMapping() throws SQLException {
        IDB session = DBFactory.getDB();
        List<DraftMapping> obj = session.getObjectList("select * from TDRAFT_MAPPING",DraftMapping.class);
        return obj;
    }


}
