/********************************************
* 文件名称: MachineStatusDao.java
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
package com.herongtech.console.domain.common.dao;

import java.sql.SQLException;
import java.util.List;

import com.herongtech.commons.tools.StringUtils;
import com.herongtech.console.domain.common.bean.MachineStatus;
import com.herongtech.db.impl.session.DBFactory;
import com.herongtech.db.interfaces.IDB;
public class MachineStatusDao{


public MachineStatus getMachineStatus(Long id) throws SQLException {
	IDB session = DBFactory.getDB();
	MachineStatus obj = (MachineStatus)session.getObject("select * from t_machine_status where id=?",MachineStatus.class,id);
	return obj;
}

    public MachineStatus getMachineStatus(String controllerName,String methodName,String param)throws SQLException{
        IDB session = DBFactory.getDB();
        if(StringUtils.isBlank(param)){
            return (MachineStatus)session.getObject("select * from t_machine_status where controller_name=? and method_name=?",MachineStatus.class,controllerName,methodName);
        }else{
            return (MachineStatus)session.getObject("select * from t_machine_status where controller_name=? and method_name=? and param_value=?",MachineStatus.class,controllerName,methodName,param);
        }
    }
    /**获得状态机所有数据
     * @throws SQLException */
    public List<MachineStatus> getMachineStatusList() throws SQLException{
    	IDB session = DBFactory.getDB();
    	List<MachineStatus> machineStatuslist = session.getObjectList("select * from t_machine_status", MachineStatus.class);
    	return machineStatuslist;
    } 

}
