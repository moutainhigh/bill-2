/********************************************
 * 文件名称: EcdsApDataService.java
 * 系统名称: 基础技术平台V2.0
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: 2.0.0.1
 * 开发人员: yanjl
 * 开发时间: 2016-6-15 下午01:48:52
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期    修改人员    修改说明
 *********************************************/

package com.herongtech.rc.service.ecdsbankdata;

import java.sql.SQLException;

import com.herongtech.exception.impl.BizAppException;
import com.herongtech.rc.domain.bean.EcdsApData;
import com.herongtech.rc.domain.dao.EcdsApDataDao;
import com.herongtech.rc.service.interfaces.IEcdsApDataService;
import com.herongtech.sysconstant.ISysErrorNo;


public class EcdsApDataService implements IEcdsApDataService {

    @Override
    public EcdsApData getEcdsApData(String meetCode)throws BizAppException  {
        EcdsApDataDao dao=new EcdsApDataDao();
        try {
            return dao.getEcdsApData(meetCode);
        } catch (SQLException e) {
            e.printStackTrace();
            throw new BizAppException(ISysErrorNo.ERR_DBERR, "");
        }
    }

}
