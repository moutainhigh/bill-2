/********************************************
 * 文件名称: ReArProdDao.java
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
import com.herongtech.console.domain.common.audit.bean.ReArProd;
import com.herongtech.console.domain.common.audit.bean.ReArProdSearchBean;
import com.herongtech.db.impl.session.DBFactory;
import com.herongtech.db.interfaces.IDB;

public class ReArProdDao {

    public int addReArProd(ReArProd obj) throws SQLException {
        BeanFormat.format(obj);
        IDB session = DBFactory.getDB();
        return session
                .execute(
                        "insert into T_RE_BRCH_PROD(id,branch_no,prod_no,audit_flag,update_dt,update_tm)values(?,?,?,?,?,?)",
                        obj.getId(), obj.getBranchNo(), obj.getProdNo(),
                        obj.getAuditFlag(), obj.getUpdateDt(),
                        obj.getUpdateTm());
    }

    public int deleteReArProd(ReArProd obj) throws SQLException {
        IDB session = DBFactory.getDB();
        return session.execute("delete from T_RE_BRCH_PROD where id=?",
                obj.getId());
    }

    public int deleteReArProd(String id) throws SQLException {
        IDB session = DBFactory.getDB();
        return session.execute("delete from T_RE_BRCH_PROD where id=?", id);
    }

    public int modifyReArProd(ReArProd obj) throws SQLException {
        BeanFormat.format(obj);
        IDB session = DBFactory.getDB();
        return session
                .execute(
                        "update T_RE_BRCH_PROD set branch_no=?,prod_no=?,audit_flag=?,update_dt=?,update_tm=? where id=?",
                        obj.getBranchNo(), obj.getProdNo(), obj.getAuditFlag(),
                        obj.getUpdateDt(), obj.getUpdateTm(), obj.getId());
    }

    public int modifyReArProd(ReArProd obj, String id) throws SQLException {
        BeanFormat.format(obj);
        IDB session = DBFactory.getDB();
        return session
                .execute(
                        "update T_RE_BRCH_PROD set branch_no=?,prod_no=?,audit_flag=?,update_dt=?,update_tm=? where id=?",
                        obj.getBranchNo(), obj.getProdNo(), obj.getAuditFlag(),
                        obj.getUpdateDt(), obj.getUpdateTm(), id);
    }

    public ReArProd getReArProd(String id) throws SQLException {
        IDB session = DBFactory.getDB();
        ReArProd obj = (ReArProd) session.getObject(
                "select * from T_RE_BRCH_PROD where id=?", ReArProd.class, id);
        return obj;
    }
    
    public List<ReArProd> getReArProdBySeachBean(Page page,ReArProdSearchBean searchBean)throws Exception{
        IDB session = DBFactory.getDB();
        searchBean.setDfaultSrchTbAliasName("prod");
        String baseSql="select prod.* from T_RE_BRCH_PROD prod";
        QueryCondition qc = new QueryCondition();
        try {
            qc.initFromSearchBean(searchBean);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String sql=qc.getAllSqlString(baseSql);
        
        if(page == null){
            return session.getObjectListByList(sql, ReArProd.class, qc.getParameterValues());
        }else{
    		int count = session.accountByList(qc.getCountSql("distinct prod.id"), qc.getParameterValues()); 
    	  	 page.setTotalResult(count);
           return  session.getObjectListByListForPage(sql, ReArProd.class, page.getCurrentResult(), page.getShowCount(), qc.getParameterValues());
        }
    }

}
