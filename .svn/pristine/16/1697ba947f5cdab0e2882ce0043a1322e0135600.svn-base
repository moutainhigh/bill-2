/********************************************
 * 文件名称: AuditRouteDao.java
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
import com.herongtech.commons.tools.StringUtils;
import com.herongtech.console.core.common.json.Page;
import com.herongtech.console.core.common.search.QueryCondition;
import com.herongtech.console.domain.common.audit.bean.AuditRoute;
import com.herongtech.console.domain.common.audit.bean.AuditRouteSearchBean;
import com.herongtech.db.impl.session.DBFactory;
import com.herongtech.db.interfaces.IDB;

public class AuditRouteDao {

    public int addAuditRoute(AuditRoute obj) throws SQLException {
        BeanFormat.format(obj);
        IDB session = DBFactory.getDB();
        return session
                .execute(
                        "insert into T_AUDIT_ROUTE(id,ar_name,an_exec_mode,ar_remark,bind_branch_no,prod_no,pub_flag,effect_flag,audit_start_brch_lvl,create_brch_no,create_dt,create_time,update_dt,update_tm)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
                        obj.getId(), obj.getArName(), obj.getAnExecMode(),
                        obj.getArRemark(), obj.getBindBranchNo(),
                        obj.getProdNo(), obj.getPubFlag(), obj.getEffectFlag(),
                        obj.getAuditStartBrchLvl(), obj.getCreateBrchNo(),
                        obj.getCreateDt(), obj.getCreateTime(),
                        obj.getUpdateDt(), obj.getUpdateTm());
    }

    public int deleteAuditRoute(AuditRoute obj) throws SQLException {
        IDB session = DBFactory.getDB();
        return session.execute("delete from T_AUDIT_ROUTE where id=?",
                obj.getId());
    }

    public int deleteAuditRoute(String id) throws SQLException {
        IDB session = DBFactory.getDB();
        return session.execute("delete from T_AUDIT_ROUTE where id=?", id);
    }
    
    public int delAuditRoute(String ids) throws SQLException {
        IDB session = DBFactory.getDB();
        String sqlInParts = StringUtils.fillStringInPart(StringUtils.split(ids));
        return session.execute("delete from T_AUDIT_ROUTE where id in(" + sqlInParts+  ")");
    }

    public int modifyAuditRoute(AuditRoute obj) throws SQLException {
        BeanFormat.format(obj);
        IDB session = DBFactory.getDB();
        return session
                .execute(
                        "update T_AUDIT_ROUTE set ar_name=?,an_exec_mode=?,ar_remark=?,bind_branch_no=?,prod_no=?,pub_flag=?,effect_flag=?,audit_start_brch_lvl=?,create_brch_no=?,create_dt=?,create_time=?,update_dt=?,update_tm=? where id=?",
                        obj.getArName(), obj.getAnExecMode(),
                        obj.getArRemark(), obj.getBindBranchNo(),
                        obj.getProdNo(), obj.getPubFlag(), obj.getEffectFlag(),
                        obj.getAuditStartBrchLvl(), obj.getCreateBrchNo(),
                        obj.getCreateDt(), obj.getCreateTime(),
                        obj.getUpdateDt(), obj.getUpdateTm(), obj.getId());
    }

    public int modifyAuditRoute(AuditRoute obj, String id) throws SQLException {
        BeanFormat.format(obj);
        IDB session = DBFactory.getDB();
        return session
                .execute(
                        "update T_AUDIT_ROUTE set ar_name=?,an_exec_mode=?,ar_remark=?,bind_branch_no=?,prod_no=?,pub_flag=?,effect_flag=?,audit_start_brch_lvl=?,create_brch_no=?,create_dt=?,create_time=?,update_dt=?,update_tm=? where id=?",
                        obj.getArName(), obj.getAnExecMode(),
                        obj.getArRemark(), obj.getBindBranchNo(),
                        obj.getProdNo(), obj.getPubFlag(), obj.getEffectFlag(),
                        obj.getAuditStartBrchLvl(), obj.getCreateBrchNo(),
                        obj.getCreateDt(), obj.getCreateTime(),
                        obj.getUpdateDt(), obj.getUpdateTm(), id);
    }

    public AuditRoute getAuditRoute(String id) throws SQLException {
        IDB session = DBFactory.getDB();
        AuditRoute obj = (AuditRoute) session.getObject(
                "select * from T_AUDIT_ROUTE where id=?", AuditRoute.class, id);
        return obj;
    }
    
    
    public List<AuditRoute> searchAuditRoute(Page page,AuditRouteSearchBean searchBean)throws Exception{
        IDB session = DBFactory.getDB();
        searchBean.setDfaultSrchTbAliasName("route");
        String baseSql="select route.* from T_AUDIT_ROUTE route";
        QueryCondition qc = new QueryCondition();
        try {
            qc.initFromSearchBean(searchBean);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String sql=qc.getAllSqlString(baseSql);
        
        if(page == null){
            return session.getObjectListByList(sql, AuditRoute.class, qc.getParameterValues());
        }else{
        	int count = session.accountByList(qc.getCountSql("distinct route.id"), qc.getParameterValues()); 
        	page.setTotalResult(count);
           return  session.getObjectListByListForPage(sql, AuditRoute.class, page.getCurrentResult(), page.getShowCount(), qc.getParameterValues());
        }
        
    }
    
    

}
