/********************************************
 * 文件名称: ReAsRoleDao.java
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
package com.herongtech.console.domain.common.audit.dao;

import java.sql.SQLException;
import java.util.List;

import com.herongtech.commons.tools.BeanFormat;
import com.herongtech.console.domain.common.audit.bean.ReAsRole;
import com.herongtech.db.impl.session.DBFactory;
import com.herongtech.db.interfaces.IDB;

public class ReAsRoleDao {

    public int addReAsRole(ReAsRole obj) throws SQLException {
        BeanFormat.format(obj);
        IDB session = DBFactory.getDB();
        return session
                .execute(
                        "insert into T_RE_AS_ROLE(id,as_id,bind_role_id,update_dt,update_tm)values(?,?,?,?,?)",
                        obj.getId(), obj.getAsId(), obj.getBindRoleId(),
                        obj.getUpdateDt(), obj.getUpdateTm());
    }

    public int deleteReAsRole(ReAsRole obj) throws SQLException {
        IDB session = DBFactory.getDB();
        return session.execute("delete from T_RE_AS_ROLE where id=?",
                obj.getId());
    }

    public int deleteReAsRole(String id) throws SQLException {
        IDB session = DBFactory.getDB();
        return session.execute("delete from T_RE_AS_ROLE where id=?", id);
    }

    public int modifyReAsRole(ReAsRole obj) throws SQLException {
        BeanFormat.format(obj);
        IDB session = DBFactory.getDB();
        return session
                .execute(
                        "update T_RE_AS_ROLE set as_id=?,bind_role_id=?,update_dt=?,update_tm=? where id=?",
                        obj.getAsId(), obj.getBindRoleId(), obj.getUpdateDt(),
                        obj.getUpdateTm(), obj.getId());
    }

    public int modifyReAsRole(ReAsRole obj, String id) throws SQLException {
        BeanFormat.format(obj);
        IDB session = DBFactory.getDB();
        return session
                .execute(
                        "update T_RE_AS_ROLE set as_id=?,bind_role_id=?,update_dt=?,update_tm=? where id=?",
                        obj.getAsId(), obj.getBindRoleId(), obj.getUpdateDt(),
                        obj.getUpdateTm(), id);
    }

    public ReAsRole getReAsRole(String id) throws SQLException {
        IDB session = DBFactory.getDB();
        ReAsRole obj = (ReAsRole) session.getObject(
                "select * from T_RE_AS_ROLE where id=?", ReAsRole.class, id);
        return obj;
    }
    
    public List<ReAsRole> getReAuditStationRoleList(String asId) throws Exception{
        String sql="select * from T_RE_AS_ROLE where as_id=?";
        IDB session = DBFactory.getDB();
        return session.getObjectList(sql, ReAsRole.class,asId);
    }

    public int deleteReAsRole(List<ReAsRole> roleList)throws Exception{
        IDB session = DBFactory.getDB();
        StringBuffer sb=new StringBuffer("delete from T_RE_AS_ROLE where id in (");
        Object[] paramVal=new Object[roleList.size()];
        for(int i=0;i<roleList.size();i++){
            sb.append("?,");
            paramVal[i]=roleList.get(i).getId();
        }
        sb.deleteCharAt(sb.lastIndexOf(","));
        sb.append(")");
        return session.execute(sb.toString(), paramVal);
    }
    
    
    public List<ReAsRole> getReAsRoleByRoleId(String roleId) throws Exception{
        String sql="select * from T_RE_AS_ROLE where bind_role_id=?";
        IDB session = DBFactory.getDB();
        return session.getObjectList(sql, ReAsRole.class,roleId);
    }
}
