/********************************************
 * 文件名称: AuditStationDao.java
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
import com.herongtech.console.domain.common.audit.bean.AuditStation;
import com.herongtech.console.domain.common.audit.bean.ReAsRole;
import com.herongtech.db.impl.session.DBFactory;
import com.herongtech.db.interfaces.IDB;

public class AuditStationDao {

    public int addAuditStation(AuditStation obj) throws SQLException {
        BeanFormat.format(obj);
        IDB session = DBFactory.getDB();
        return session
                .execute(
                        "insert into T_AUDIT_STATION(id,an_id,ar_id,as_name,as_privilege,create_brch_no,as_remark,sort_no,update_dt,update_tm)values(?,?,?,?,?,?,?,?,?,?)",
                        obj.getId(), obj.getAnId(), obj.getArId(),
                        obj.getAsName(), obj.getAsPrivilege(),
                        obj.getCreateBrchNo(), obj.getAsRemark(),
                        obj.getSortNo(), obj.getUpdateDt(), obj.getUpdateTm());
    }

    public int deleteAuditStation(AuditStation obj) throws SQLException {
        IDB session = DBFactory.getDB();
        return session.execute("delete from T_AUDIT_STATION where id=?",
                obj.getId());
    }

    public int deleteAuditStation(String id) throws SQLException {
        IDB session = DBFactory.getDB();
        return session.execute("delete from T_AUDIT_STATION where id=?", id);
    }

    public int modifyAuditStation(AuditStation obj) throws SQLException {
        BeanFormat.format(obj);
        IDB session = DBFactory.getDB();
        return session
                .execute(
                        "update T_AUDIT_STATION set an_id=?,ar_id=?,as_name=?,as_privilege=?,create_brch_no=?,as_remark=?,sort_no=?,update_dt=?,update_tm=? where id=?",
                        obj.getAnId(), obj.getArId(), obj.getAsName(),
                        obj.getAsPrivilege(), obj.getCreateBrchNo(),
                        obj.getAsRemark(), obj.getSortNo(), obj.getUpdateDt(),
                        obj.getUpdateTm(), obj.getId());
    }

    public int modifyAuditStation(AuditStation obj, String id)
            throws SQLException {
        BeanFormat.format(obj);
        IDB session = DBFactory.getDB();
        return session
                .execute(
                        "update T_AUDIT_STATION set an_id=?,ar_id=?,as_name=?,as_privilege=?,create_brch_no=?,as_remark=?,sort_no=?,update_dt=?,update_tm=? where id=?",
                        obj.getAnId(), obj.getArId(), obj.getAsName(),
                        obj.getAsPrivilege(), obj.getCreateBrchNo(),
                        obj.getAsRemark(), obj.getSortNo(), obj.getUpdateDt(),
                        obj.getUpdateTm(), id);
    }

    public AuditStation getAuditStation(String id) throws SQLException {
        IDB session = DBFactory.getDB();
        AuditStation obj = (AuditStation) session.getObject(
                "select * from T_AUDIT_STATION where id=?", AuditStation.class,
                id);
        return obj;
    }

    
    public List<AuditStation> getAuditStationByArId(String arId)throws Exception{
        String sql="select * from T_AUDIT_STATION where ar_id=? order by sort_no asc";
        IDB session = DBFactory.getDB();
        return session.getObjectList(sql, AuditStation.class,arId);
    }
    
    public List<AuditStation> getAuditStationByAnId(String anId)throws Exception{
        String sql="select * from T_AUDIT_STATION where an_id=? order by sort_no asc";
        IDB session = DBFactory.getDB();
        return session.getObjectList(sql, AuditStation.class,anId);
    }
    
    public int deleteAuditStation(List<AuditStation> stationList)throws Exception{
        IDB session = DBFactory.getDB();
        StringBuffer sb=new StringBuffer("delete from T_AUDIT_STATION where id in (");
        Object[] paramVal=new Object[stationList.size()];
        for(int i=0;i<stationList.size();i++){
            sb.append("?,");
            paramVal[i]=stationList.get(i).getId();
        }
        sb.deleteCharAt(sb.lastIndexOf(","));
        sb.append(")");
        return session.execute(sb.toString(), paramVal);
    }
}
