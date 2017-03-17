/********************************************
 * 文件名称: AuditNodeDao.java
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
import com.herongtech.console.domain.common.audit.bean.AuditNode;
import com.herongtech.db.impl.session.DBFactory;
import com.herongtech.db.interfaces.IDB;

public class AuditNodeDao {

    public int addAuditNode(AuditNode obj) throws SQLException {
        BeanFormat.format(obj);
        IDB session = DBFactory.getDB();
        return session
                .execute(
                        "insert into T_AUDIT_NODE(id,ar_id,an_name,an_brch_lvl,sort_no,update_dt,update_tm)values(?,?,?,?,?,?,?)",
                        obj.getId(), obj.getArId(), obj.getAnName(),
                        obj.getAnBrchLvl(), obj.getSortNo(), obj.getUpdateDt(),
                        obj.getUpdateTm());
    }

    public int deleteAuditNode(AuditNode obj) throws SQLException {
        IDB session = DBFactory.getDB();
        return session.execute("delete from T_AUDIT_NODE where id=?",
                obj.getId());
    }

    public int deleteAuditNode(String id) throws SQLException {
        IDB session = DBFactory.getDB();
        return session.execute("delete from T_AUDIT_NODE where id=?", id);
    }

    public int modifyAuditNode(AuditNode obj) throws SQLException {
        BeanFormat.format(obj);
        IDB session = DBFactory.getDB();
        return session
                .execute(
                        "update T_AUDIT_NODE set ar_id=?,an_name=?,an_brch_lvl=?,sort_no=?,update_dt=?,update_tm=? where id=?",
                        obj.getArId(), obj.getAnName(), obj.getAnBrchLvl(),
                        obj.getSortNo(), obj.getUpdateDt(), obj.getUpdateTm(),
                        obj.getId());
    }

    public int modifyAuditNode(AuditNode obj, String id) throws SQLException {
        BeanFormat.format(obj);
        IDB session = DBFactory.getDB();
        return session
                .execute(
                        "update T_AUDIT_NODE set ar_id=?,an_name=?,an_brch_lvl=?,sort_no=?,update_dt=?,update_tm=? where id=?",
                        obj.getArId(), obj.getAnName(), obj.getAnBrchLvl(),
                        obj.getSortNo(), obj.getUpdateDt(), obj.getUpdateTm(),
                        id);
    }

    public AuditNode getAuditNode(String id) throws SQLException {
        IDB session = DBFactory.getDB();
        AuditNode obj = (AuditNode) session.getObject(
                "select * from T_AUDIT_NODE where id=?", AuditNode.class, id);
        return obj;
    }

    
    public List<AuditNode> getAuditNodeByArId(String arId)throws Exception{
        String sql="select * from T_AUDIT_NODE where ar_id=? order by an_brch_lvl asc";
        IDB session = DBFactory.getDB();
        return session.getObjectList(sql, AuditNode.class,arId);
    }
    
    /**
     * 根据审批路线id和机构级别获取审批节点
     * @param arId
     * @param brchLvl
     * @return
     * @throws Exception
     */
    public List<AuditNode> getAuditNodeByArIdAndBrchLevel(String arId, String brchLvl) throws Exception{
        String sql="select * from T_AUDIT_NODE where ar_id=? and an_brch_lvl=?";
        IDB session = DBFactory.getDB();
        return session.getObjectList(sql, AuditNode.class,arId,brchLvl);
    }
}
