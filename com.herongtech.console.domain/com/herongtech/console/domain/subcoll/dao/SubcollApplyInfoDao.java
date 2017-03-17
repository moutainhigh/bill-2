/********************************************
* 文件名称: SubcollApplyInfoDao.java
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
package com.herongtech.console.domain.subcoll.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.herongtech.commons.tools.BeanFormat;
import com.herongtech.console.core.common.json.Page;
import com.herongtech.console.core.common.search.QueryCondition;
import com.herongtech.console.core.util.MoneyUtils;
import com.herongtech.console.domain.subcoll.bean.SubcollApplyInfo;
import com.herongtech.console.domain.subcoll.bean.SubcollQueryCondition;
import com.herongtech.data.interfaces.data.IData;
import com.herongtech.db.impl.session.DBFactory;
import com.herongtech.db.interfaces.IDB;
import com.herongtech.exception.impl.BizAppException;

/**
 * 托收批次 DAO
 * @author Administrator
 *
 */
public class SubcollApplyInfoDao{

/**
 * 添加SubcollApplyInfo(托收批次)信息
 * @param obj
 * @return
 * @throws SQLException
 */
public int addSubcollApplyInfo(SubcollApplyInfo obj) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("insert into tsubcoll_apply_info(subcoll_id,coll_dt,coll_time,sub_type,branch_no,prod_no,overduereason,old_id,bill_class,bill_type,batch_id,remark,from_bank_no,from_bank_name,to_bank_no,to_bank_name,to_bank_address,ems,in_acct_no,in_bank_no)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
	obj.getSubcollId(),obj.getCollDt(),obj.getCollTime(),obj.getSubType(),obj.getBranchNo(),
	obj.getProdNo(),obj.getOverduereason(),obj.getOldId(),obj.getBillClass(),
	obj.getBillType(),obj.getBatchId(),obj.getRemark(),obj.getFromBankNo(),obj.getFromBankName(),obj.getToBankNo(),obj.getToBankName(),obj.getToBankAddress(),obj.getEms(),obj.getInAcctNo(),obj.getInBankNo());
}

public int deleteSubcollApplyInfo(SubcollApplyInfo obj) throws SQLException {
	IDB session = DBFactory.getDB();
	return session.execute("delete from tsubcoll_apply_info where subcoll_id=?",obj.getSubcollId());
}

public int deleteSubcollApplyInfo(String subcollId) throws SQLException {
	IDB session = DBFactory.getDB();
	return session.execute("delete from tsubcoll_apply_info where subcoll_id=?",subcollId);
}

public int modifySubcollApplyInfo(SubcollApplyInfo obj) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("update tsubcoll_apply_info set coll_dt=?,coll_time=?,sub_type=?,branch_no=?,prod_no=?,overduereason=?,old_id=?,bill_class=?,bill_type=?,batch_id=?,remark=?,from_bank_no=?,from_bank_name=?,to_bank_no=?,to_bank_name=?,to_bank_address=?,ems=?,in_acct_no=?,in_bank_no=? where subcoll_id=?",
	obj.getCollDt(),obj.getCollTime(),obj.getSubType(),obj.getBranchNo(),
	obj.getProdNo(),obj.getOverduereason(),obj.getOldId(),obj.getBillClass(),
	obj.getBillType(),obj.getBatchId(),obj.getRemark(),obj.getFromBankNo(),
	obj.getFromBankName(),obj.getToBankNo(),obj.getToBankName(),obj.getToBankAddress(),
	obj.getEms(),obj.getInAcctNo(),obj.getInBankNo(),obj.getSubcollId());
}

public int modifySubcollApplyInfo(SubcollApplyInfo obj,String subcollId) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("update tsubcoll_apply_info set coll_dt=?,coll_time=?,sub_type=?,branch_no=?,prod_no=?,overduereason=?,old_id=?,bill_class=?,bill_type=?,batch_id=?,remark=?,from_bank_no=?,from_bank_name=?,to_bank_no=?,to_bank_name=?,to_bank_address=?,ems=?,,in_acct_no=?,in_bank_no=? where subcoll_id=?",
	obj.getCollDt(),obj.getCollTime(),obj.getSubType(),obj.getBranchNo(),
	obj.getProdNo(),obj.getOverduereason(),obj.getOldId(),obj.getBillClass(),
	obj.getBillType(),obj.getBatchId(),obj.getRemark(),obj.getFromBankNo(),
	obj.getFromBankName(),obj.getToBankNo(),obj.getToBankName(),obj.getToBankAddress(),
	obj.getEms(),obj.getInAcctNo(),obj.getInBankNo(),subcollId);
}

public SubcollApplyInfo getSubcollApplyInfo(SubcollQueryCondition query) throws SQLException {
	IDB session = DBFactory.getDB();
	if(query == null){
        query=new SubcollQueryCondition();
    }
	SubcollApplyInfo obj = (SubcollApplyInfo)session.getObject("select * from tsubcoll_apply_info where subcoll_id=?",SubcollApplyInfo.class,query.getSubcollId());
	sumApplyInfo(obj, query);
	return obj;
}
/**根据批次id查询该批次*/
public SubcollApplyInfo getSubcollApplyInfobyid(String id)throws SQLException{
	IDB session = DBFactory.getDB();
	SubcollApplyInfo obj = (SubcollApplyInfo)session.getObject("select * from tsubcoll_apply_info where subcoll_id=?",SubcollApplyInfo.class,id);
	return obj;
}


/**
 * 根据条件查询批次信息
 * @param page
 * @param query
 * @return
 * @throws SQLException
 */
public List<SubcollApplyInfo> getSubcollApplyInfoListForCondition(Page page,
		SubcollQueryCondition query) throws SQLException {

	IDB session = DBFactory.getDB();
	String baseSql = "select distinct apply.* from tsubcoll_apply_info apply, tsubcoll_bill_info bill where bill.subcoll_id = apply.subcoll_id";
	QueryCondition qc=new QueryCondition();
	try {
		qc.initFromSearchBean(query);
	} catch (Exception e) {
		e.printStackTrace();
	}
	String allSql = qc.getAllSqlString(baseSql);
	//分页开始位置
	int startIndex = (page.getCurrentPage()-1)*page.getShowCount()+1;
	if(startIndex<0)
		startIndex = 0;
	
	List<SubcollApplyInfo> applyList=null;
	int count = 0;
	// 获得并返回本次查询的总条数
	if(qc.getParameterValues() ==null ||qc.getParameterValues().size()==0){//什么条件都不带的情况
	    count = session.account(qc.getCountSql("distinct apply.subcoll_id"));
	    applyList=session.getObjectListForPage(allSql, SubcollApplyInfo.class,startIndex, page.getShowCount());
	}else{
	    count = session.accountByList(qc.getCountSql("distinct apply.subcoll_id"), qc.getParameterValues()); 
	    applyList=session.getObjectListByListForPage(allSql,SubcollApplyInfo.class, startIndex, page.getShowCount(), qc.getParameterValues());
	}
	System.out.println(allSql);
	//List<Object> list = qc.getParameterValues();
//	System.out.println(list.get(0)+","+list.get(1)+","+list.get(2));
	page.setTotalResult(count);
	for(SubcollApplyInfo apply:applyList){
		query.setSubcollId(apply.getSubcollId());
	    sumApplyInfo(apply, query);
	}
	return applyList;

}

/**
 * 票据张数，与票据金额汇总
 * @param applyList
 * @param query
 * @throws SQLException 
 */
public void sumApplyInfo(SubcollApplyInfo apply,SubcollQueryCondition query) throws SQLException{
    if(apply == null) return;
    IDB session = DBFactory.getDB();
    //批次汇总票据张数，与票据金额sql
    StringBuilder sb = new StringBuilder("select count(bill.subcollmx_id)||'',");
    sb.append("sum(bill.bill_money) from tsubcoll_bill_info bill where bill.subcoll_id = ? ");
    List<Object> param = new ArrayList<Object>();
    //占第一位 为第一个问号赋值
    param.add("");
    if(query != null){
        if(StringUtils.isNotBlank(query.getBillClass())){
            sb.append(" and bill.bill_class = ? ");
            param.add(query.getBillClass().trim());
        }
        if(StringUtils.isNotBlank(query.getBillType())){
            sb.append(" and bill.bill_type = ? ");
            param.add(query.getBillType().trim());
        }
        if(StringUtils.isNotBlank(query.getApplyOperNo())){
            sb.append(" and bill.apply_oper_no = ? ");
            param.add(query.getApplyOperNo().trim());
        }
        if(StringUtils.isNotBlank(query.getOperStatus())){
            sb.append(" and bill.oper_status = ? ");
            param.add(query.getOperStatus().trim());
        }
        if(query.getStatusArray()!=null){
            sb.append(" and bill.oper_status in (");
            for (Object status : query.getStatusArray()) {
            	sb.append("?,");
        		param.add(status);
        	}
            sb.deleteCharAt(sb.length()-1);
            sb.append(')');
        }
    }
    //删除第一位 为第一个问号赋值
    param.remove(0);
    // 为第一个问号赋值
    param.add(0, apply.getSubcollId());
    IData data=session.getDataByList(sb.toString(), param);
    System.out.println(sb.toString());
    apply.setTotalNum( data.getString(1));
    apply.setTotalMoney(MoneyUtils.moneyToString(data.getDouble(2)) );
}
	/**根据批次号查询批次id*/
	public String getpiciidbypicihao(String id)throws BizAppException{
		IDB session = DBFactory.getDB();
		try {
			SubcollApplyInfo obj = (SubcollApplyInfo)session.getObject("select * from tsubcoll_apply_info where batch_id=?",SubcollApplyInfo.class,id);
			return obj.getSubcollId();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	


}
