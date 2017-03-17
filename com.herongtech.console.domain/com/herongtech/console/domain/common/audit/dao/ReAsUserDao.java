/********************************************
 * 文件名称: ReAsUserDao.java
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
import com.herongtech.console.domain.common.audit.bean.ReAsUser;
import com.herongtech.db.impl.session.DBFactory;
import com.herongtech.db.interfaces.IDB;

public class ReAsUserDao {

    public int addReAsUser(ReAsUser obj) throws SQLException {
        BeanFormat.format(obj);
        IDB session = DBFactory.getDB();
        return session
                .execute(
                        "insert into T_RE_AS_USER(id,as_id,bind_user_id,update_dt,update_tm)values(?,?,?,?,?)",
                        obj.getId(), obj.getAsId(), obj.getBindUserId(),
                        obj.getUpdateDt(), obj.getUpdateTm());
    }

    public int deleteReAsUser(ReAsUser obj) throws SQLException {
        IDB session = DBFactory.getDB();
        return session.execute("delete from T_RE_AS_USER where id=?",
                obj.getId());
    }

    public int deleteReAsUser(String id) throws SQLException {
        IDB session = DBFactory.getDB();
        return session.execute("delete from T_RE_AS_USER where id=?", id);
    }

    public int modifyReAsUser(ReAsUser obj) throws SQLException {
        BeanFormat.format(obj);
        IDB session = DBFactory.getDB();
        return session
                .execute(
                        "update T_RE_AS_USER set as_id=?,bind_user_id=?,update_dt=?,update_tm=? where id=?",
                        obj.getAsId(), obj.getBindUserId(), obj.getUpdateDt(),
                        obj.getUpdateTm(), obj.getId());
    }

    public int modifyReAsUser(ReAsUser obj, String id) throws SQLException {
        BeanFormat.format(obj);
        IDB session = DBFactory.getDB();
        return session
                .execute(
                        "update T_RE_AS_USER set as_id=?,bind_user_id=?,update_dt=?,update_tm=? where id=?",
                        obj.getAsId(), obj.getBindUserId(), obj.getUpdateDt(),
                        obj.getUpdateTm(), id);
    }

    public ReAsUser getReAsUser(String id) throws SQLException {
        IDB session = DBFactory.getDB();
        ReAsUser obj = (ReAsUser) session.getObject(
                "select * from T_RE_AS_USER where id=?", ReAsUser.class, id);
        return obj;
    }

    public List<ReAsUser> getReAsUserList(String asId) throws Exception{
        String sql="select * from T_RE_AS_USER where as_id=?";
        IDB session = DBFactory.getDB();
        return session.getObjectList(sql, ReAsUser.class,asId);
    }

    public int deleteReAsUser(List<ReAsUser> userList)throws Exception{
        IDB session = DBFactory.getDB();
        StringBuffer sb=new StringBuffer("delete from T_RE_AS_USER where id in (");
        Object[] paramVal=new Object[userList.size()];
        for(int i=0;i<userList.size();i++){
            sb.append("?,");
            paramVal[i]=userList.get(i).getId();
        }
        sb.deleteCharAt(sb.lastIndexOf(","));
        sb.append(")");
        return session.execute(sb.toString(), paramVal);
    }
    
    public List<ReAsUser> getReAsUserByUserId(String userId) throws Exception{
        String sql="select * from T_RE_AS_USER where bind_user_id=?";
        IDB session = DBFactory.getDB();
        return session.getObjectList(sql, ReAsUser.class,userId);
    }
}
