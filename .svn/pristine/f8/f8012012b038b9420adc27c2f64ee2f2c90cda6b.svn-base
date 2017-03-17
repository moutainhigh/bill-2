/********************************************
 * 文件名称: AuditProcessDao.java
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
import com.herongtech.console.domain.common.audit.bean.AuditProcess;
import com.herongtech.console.domain.common.audit.bean.AuditProcessSearchBean;
import com.herongtech.console.domain.common.audit.bean.AuditStation;
import com.herongtech.console.domain.common.audit.bean.AuditTask;
import com.herongtech.db.impl.session.DBFactory;
import com.herongtech.db.interfaces.IDB;

public class AuditProcessDao {

    public int addAuditProcess(AuditProcess obj) throws SQLException {
        BeanFormat.format(obj);
        IDB session = DBFactory.getDB();
        return session
                .execute(
                        "insert into T_AUDIT_PROCESS(id,at_id,ap_commit_station_id,ap_commit_station_name,ap_commit_person_id,ap_commit_person_name,ap_commit_brch_no,ap_commit_remark,ap_exec_station_id,ap_exec_station_name,ap_exec_person_id,ap_exec_person_name,ap_exec_brch_no,ap_status,ap_exec_result,ap_exec_remark,ap_done_dt,ap_done_time,ap_commit_dt,ap_commit_time,sort_no,update_dt,update_tm)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
                        obj.getId(), obj.getAtId(), obj.getApCommitStationId(),
                        obj.getApCommitStationName(),
                        obj.getApCommitPersonId(), obj.getApCommitPersonName(),
                        obj.getApCommitBrchNo(), obj.getApCommitRemark(),
                        obj.getApExecStationId(), obj.getApExecStationName(),
                        obj.getApExecPersonId(), obj.getApExecPersonName(),
                        obj.getApExecBrchNo(), obj.getApStatus(),
                        obj.getApExecResult(), obj.getApExecRemark(),
                        obj.getApDoneDt(), obj.getApDoneTime(),
                        obj.getApCommitDt(), obj.getApCommitTime(),
                        obj.getSortNo(), obj.getUpdateDt(), obj.getUpdateTm());
    }

    public int deleteAuditProcess(AuditProcess obj) throws SQLException {
        IDB session = DBFactory.getDB();
        return session.execute("delete from T_AUDIT_PROCESS where id=?",
                obj.getId());
    }

    public int deleteAuditProcess(String id) throws SQLException {
        IDB session = DBFactory.getDB();
        return session.execute("delete from T_AUDIT_PROCESS where id=?", id);
    }

    public int modifyAuditProcess(AuditProcess obj) throws SQLException {
        BeanFormat.format(obj);
        IDB session = DBFactory.getDB();
        return session
                .execute(
                        "update T_AUDIT_PROCESS set at_id=?,ap_commit_station_id=?,ap_commit_station_name=?,ap_commit_person_id=?,ap_commit_person_name=?,ap_commit_brch_no=?,ap_commit_remark=?,ap_exec_station_id=?,ap_exec_station_name=?,ap_exec_person_id=?,ap_exec_person_name=?,ap_exec_brch_no=?,ap_status=?,ap_exec_result=?,ap_exec_remark=?,ap_done_dt=?,ap_done_time=?,ap_commit_dt=?,ap_commit_time=?,sort_no=?,update_dt=?,update_tm=? where id=?",
                        obj.getAtId(), obj.getApCommitStationId(),
                        obj.getApCommitStationName(),
                        obj.getApCommitPersonId(), obj.getApCommitPersonName(),
                        obj.getApCommitBrchNo(), obj.getApCommitRemark(),
                        obj.getApExecStationId(), obj.getApExecStationName(),
                        obj.getApExecPersonId(), obj.getApExecPersonName(),
                        obj.getApExecBrchNo(), obj.getApStatus(),
                        obj.getApExecResult(), obj.getApExecRemark(),
                        obj.getApDoneDt(), obj.getApDoneTime(),
                        obj.getApCommitDt(), obj.getApCommitTime(),
                        obj.getSortNo(), obj.getUpdateDt(), obj.getUpdateTm(),
                        obj.getId());
    }

    public int modifyAuditProcess(AuditProcess obj, String id)
            throws SQLException {
        BeanFormat.format(obj);
        IDB session = DBFactory.getDB();
        return session
                .execute(
                        "update T_AUDIT_PROCESS set at_id=?,ap_commit_station_id=?,ap_commit_station_name=?,ap_commit_person_id=?,ap_commit_person_name=?,ap_commit_brch_no=?,ap_commit_remark=?,ap_exec_station_id=?,ap_exec_station_name=?,ap_exec_person_id=?,ap_exec_person_name=?,ap_exec_brch_no=?,ap_status=?,ap_exec_result=?,ap_exec_remark=?,ap_done_dt=?,ap_done_time=?,ap_commit_dt=?,ap_commit_time=?,sort_no=?,update_dt=?,update_tm=? where id=?",
                        obj.getAtId(), obj.getApCommitStationId(),
                        obj.getApCommitStationName(),
                        obj.getApCommitPersonId(), obj.getApCommitPersonName(),
                        obj.getApCommitBrchNo(), obj.getApCommitRemark(),
                        obj.getApExecStationId(), obj.getApExecStationName(),
                        obj.getApExecPersonId(), obj.getApExecPersonName(),
                        obj.getApExecBrchNo(), obj.getApStatus(),
                        obj.getApExecResult(), obj.getApExecRemark(),
                        obj.getApDoneDt(), obj.getApDoneTime(),
                        obj.getApCommitDt(), obj.getApCommitTime(),
                        obj.getSortNo(), obj.getUpdateDt(), obj.getUpdateTm(),
                        id);
    }

    public AuditProcess getAuditProcess(String id) throws SQLException {
        IDB session = DBFactory.getDB();
        AuditProcess obj = (AuditProcess) session.getObject(
                "select * from T_AUDIT_PROCESS where id=?", AuditProcess.class,
                id);
        return obj;
    }

    public List<AuditProcess> getAuditProcessByAtId(String atId)throws Exception{
        String sql="select * from T_AUDIT_PROCESS where at_id = ? order by sort_no asc";
        IDB session = DBFactory.getDB();
        return session.getObjectList(sql, AuditProcess.class,atId);
    }
    
    public int deleteAuditProcess(List<AuditProcess> auditProcessList)throws Exception {
        IDB session = DBFactory.getDB();
        StringBuffer sb=new StringBuffer("delete from T_AUDIT_PROCESS where id in (");
        Object[] paramVal=new Object[auditProcessList.size()];
        for(int i=0;i<auditProcessList.size();i++){
            sb.append("?,");
            paramVal[i]=auditProcessList.get(i).getId();
        }
        sb.deleteCharAt(sb.lastIndexOf(","));
        sb.append(")");
        return session.execute(sb.toString(), paramVal);
    }
    
    public List<AuditProcess> getAuditProcessBySearchBean(AuditProcessSearchBean searchBean,
            Page page) throws Exception{
        IDB session = DBFactory.getDB();
        searchBean.setDfaultSrchTbAliasName("process");
        String baseSql="select process.* from T_AUDIT_PROCESS process";
        QueryCondition qc = new QueryCondition();
        try {
            qc.initFromSearchBean(searchBean);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String sql=qc.getAllSqlString(baseSql);
        
        if(page == null){
            return session.getObjectListByList(sql, AuditProcess.class, qc.getParameterValues());
        }else{
        	int count = session.accountByList(qc.getCountSql("distinct process.id"), qc.getParameterValues()); 
        	page.setTotalResult(count);
            return  session.getObjectListByListForPage(sql, AuditProcess.class, page.getCurrentResult(), page.getShowCount(), qc.getParameterValues());
        }
    }
}
