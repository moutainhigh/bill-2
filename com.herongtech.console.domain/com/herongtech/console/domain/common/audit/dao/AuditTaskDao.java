/********************************************
 * 文件名称: AuditTaskDao.java
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
import com.herongtech.console.core.common.json.Page;
import com.herongtech.console.core.common.search.QueryCondition;
import com.herongtech.console.domain.common.audit.bean.AuditTask;
import com.herongtech.console.domain.common.audit.bean.AuditTaskSearchBean;
import com.herongtech.db.impl.session.DBFactory;
import com.herongtech.db.interfaces.IDB;

public class AuditTaskDao {

    public int addAuditTask(AuditTask obj) throws SQLException {
        BeanFormat.format(obj);
        IDB session = DBFactory.getDB();
        return session
                .execute(
                        "insert into T_AUDIT_TASK(id,ar_id,at_status,batch_id,entity_name,entity_service,batch_no,wait_audit_amt,at_author_id,at_author_name,at_create_dt,at_create_time,at_done_dt,at_done_time,brch_no,prod_no,audit_remark,update_dt,update_tm)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
                        obj.getId(), obj.getArId(), obj.getAtStatus(),
                        obj.getBatchId(), obj.getEntityName(),
                        obj.getEntityService(), obj.getBatchNo(),
                        obj.getWaitAuditAmt(), obj.getAtAuthorId(),
                        obj.getAtAuthorName(), obj.getAtCreateDt(),
                        obj.getAtCreateTime(), obj.getAtDoneDt(),
                        obj.getAtDoneTime(), obj.getBrchNo(), obj.getProdNo(),
                        obj.getAuditRemark(), obj.getUpdateDt(),
                        obj.getUpdateTm());
    }

    public int deleteAuditTask(AuditTask obj) throws SQLException {
        IDB session = DBFactory.getDB();
        return session.execute("delete from T_AUDIT_TASK where id=?",
                obj.getId());
    }

    public int deleteAuditTask(String id) throws SQLException {
        IDB session = DBFactory.getDB();
        return session.execute("delete from T_AUDIT_TASK where id=?", id);
    }

    public int modifyAuditTask(AuditTask obj) throws SQLException {
        BeanFormat.format(obj);
        IDB session = DBFactory.getDB();
        return session
                .execute(
                        "update T_AUDIT_TASK set ar_id=?,at_status=?,batch_id=?,entity_name=?,entity_service=?,batch_no=?,wait_audit_amt=?,at_author_id=?,at_author_name=?,at_create_dt=?,at_create_time=?,at_done_dt=?,at_done_time=?,brch_no=?,prod_no=?,audit_remark=?,update_dt=?,update_tm=? where id=?",
                        obj.getArId(), obj.getAtStatus(), obj.getBatchId(),
                        obj.getEntityName(), obj.getEntityService(),
                        obj.getBatchNo(), obj.getWaitAuditAmt(),
                        obj.getAtAuthorId(), obj.getAtAuthorName(),
                        obj.getAtCreateDt(), obj.getAtCreateTime(),
                        obj.getAtDoneDt(), obj.getAtDoneTime(),
                        obj.getBrchNo(), obj.getProdNo(), obj.getAuditRemark(),
                        obj.getUpdateDt(), obj.getUpdateTm(), obj.getId());
    }

    public int modifyAuditTask(AuditTask obj, String id) throws SQLException {
        BeanFormat.format(obj);
        IDB session = DBFactory.getDB();
        return session
                .execute(
                        "update T_AUDIT_TASK set ar_id=?,at_status=?,batch_id=?,entity_name=?,entity_service=?,batch_no=?,wait_audit_amt=?,at_author_id=?,at_author_name=?,at_create_dt=?,at_create_time=?,at_done_dt=?,at_done_time=?,brch_no=?,prod_no=?,audit_remark=?,update_dt=?,update_tm=? where id=?",
                        obj.getArId(), obj.getAtStatus(), obj.getBatchId(),
                        obj.getEntityName(), obj.getEntityService(),
                        obj.getBatchNo(), obj.getWaitAuditAmt(),
                        obj.getAtAuthorId(), obj.getAtAuthorName(),
                        obj.getAtCreateDt(), obj.getAtCreateTime(),
                        obj.getAtDoneDt(), obj.getAtDoneTime(),
                        obj.getBrchNo(), obj.getProdNo(), obj.getAuditRemark(),
                        obj.getUpdateDt(), obj.getUpdateTm(), id);
    }

    public AuditTask getAuditTask(String id) throws SQLException {
        IDB session = DBFactory.getDB();
        AuditTask obj = (AuditTask) session.getObject(
                "select * from T_AUDIT_TASK where id=?", AuditTask.class, id);
        return obj;
    }
    
    
    public List<AuditTask> getAuditTaskListBySearchBean(Page page,AuditTaskSearchBean searchBean)throws Exception{
        IDB session = DBFactory.getDB();
        searchBean.setDfaultSrchTbAliasName("task");
        String baseSql="select task.* from T_AUDIT_TASK task";
        QueryCondition qc = new QueryCondition();
        try {
            qc.initFromSearchBean(searchBean);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String sql=qc.getAllSqlString(baseSql);
        
        if(page == null){
            return session.getObjectListByList(sql, AuditTask.class, qc.getParameterValues());
        }else{
           return  session.getObjectListByListForPage(sql, AuditTask.class, page.getCurrentResult(), page.getShowCount(), qc.getParameterValues());
        }
    }

}
