/********************************************
* 文件名称: IntoApplyInfoDao.java
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
package com.herongtech.console.domain.into.dao;

import com.herongtech.data.interfaces.data.IData;
import com.herongtech.db.impl.session.DBFactory;
import com.herongtech.db.interfaces.IDB;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.herongtech.commons.tools.BeanFormat;
import com.herongtech.console.core.common.json.Page;
import com.herongtech.console.core.common.search.OrderBean;
import com.herongtech.console.core.common.search.QueryCondition;
import com.herongtech.console.core.util.MoneyUtils;
import com.herongtech.console.core.util.StatusUtils;
import com.herongtech.console.domain.into.bean.IntoApplyInfo;
import com.herongtech.console.domain.into.bean.IntoSearchBean;

public class IntoApplyInfoDao{

public int addIntoApplyInfo(IntoApplyInfo obj) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("insert into tinto_apply_info(into_id,cust_no,cust_name,create_date,create_time,prod_no,cust_manager,is_tc,dept_no,batch_type,batch_class,oper_no,batch_no,dept_name,cust_manager_name,branch_no,impawn_bail_account,impawn_bail_name,apply_status)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
	obj.getIntoId(),obj.getCustNo(),obj.getCustName(),obj.getCreateDate(),obj.getCreateTime(),
	obj.getProdNo(),obj.getCustManager(),obj.getIsTc(),obj.getDeptNo(),
	obj.getBatchType(),obj.getBatchClass(),obj.getOperNo(),obj.getBatchNo(),
	obj.getDeptName(),obj.getCustManagerName(),obj.getBranchNo(),obj.getImpawnBailAccount(),
	obj.getImpawnBailName(),obj.getApplyStatus());
}

public int deleteIntoApplyInfo(IntoApplyInfo obj,String intoId) throws SQLException {
	IDB session = DBFactory.getDB();
	return session.execute("update tinto_apply_info set apply_status=? where into_id=?",obj.getApplyStatus(),intoId);
}

public int deleteIntoApplyInfo(String intoId) throws SQLException {
	IDB session = DBFactory.getDB();
	return session.execute("delete from tinto_apply_info where into_id=?",intoId);
}

public int modifyIntoApplyInfo(IntoApplyInfo obj) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("update tinto_apply_info set cust_no=?,cust_name=?,create_date=?,create_time=?,prod_no=?,cust_manager=?,is_tc=?,dept_no=?,batch_type=?,batch_class=?,oper_no=?,batch_no=?,dept_name=?,cust_manager_name=?,branch_no=?,impawn_bail_account=?,impawn_bail_name=?,apply_status=? where into_id=?",
	obj.getCustNo(),obj.getCustName(),obj.getCreateDate(),obj.getCreateTime(),
	obj.getProdNo(),obj.getCustManager(),obj.getIsTc(),obj.getDeptNo(),
	obj.getBatchType(),obj.getBatchClass(),obj.getOperNo(),obj.getBatchNo(),
	obj.getDeptName(),obj.getCustManagerName(),obj.getBranchNo(),obj.getImpawnBailAccount(),
	obj.getImpawnBailName(),obj.getApplyStatus(),obj.getIntoId());
}

public int modifyIntoApplyInfo(IntoApplyInfo obj,String intoId) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("update tinto_apply_info set cust_no=?,cust_name=?,create_date=?,create_time=?,prod_no=?,cust_manager=?,is_tc=?,dept_no=?,batch_type=?,batch_class=?,oper_no=?,batch_no=?,dept_name=?,cust_manager_name=?,branch_no=?,impawn_bail_account=?,impawn_bail_name=?,apply_status=? where into_id=?",
	obj.getCustNo(),obj.getCustName(),obj.getCreateDate(),obj.getCreateTime(),
	obj.getProdNo(),obj.getCustManager(),obj.getIsTc(),obj.getDeptNo(),
	obj.getBatchType(),obj.getBatchClass(),obj.getOperNo(),obj.getBatchNo(),
	obj.getDeptName(),obj.getCustManagerName(),obj.getBranchNo(),obj.getImpawnBailAccount(),
	obj.getImpawnBailName(),obj.getApplyStatus(),intoId);
}

public IntoApplyInfo getIntoApplyInfo(String intoId,IntoSearchBean query) throws SQLException {
	IDB session = DBFactory.getDB();
	IntoApplyInfo obj = (IntoApplyInfo)session.getObject("select * from tinto_apply_info where into_id=?",IntoApplyInfo.class,intoId);
	if(query == null) return obj;
	List<IntoApplyInfo> list = new ArrayList<IntoApplyInfo>(1);
	list.add(obj);
	sumApplyInfo(list, query);
	return obj;
}

public IntoApplyInfo getIntoApplyInf(String intoId) throws SQLException {
    IDB session = DBFactory.getDB();
    IntoApplyInfo obj = (IntoApplyInfo)session.getObject("select * from tinto_apply_info where into_id=?",IntoApplyInfo.class,intoId);
    return obj;
  }

/**
 * 查询批次(申请岗)
 * @param page
 * @param query
 * @return
 * @throws SQLException
 */
  public List<IntoApplyInfo> getIntoApplyListForApply(Page page, IntoSearchBean query)
    throws Exception
  {
    StringBuilder select = new StringBuilder("select distinct ta.*");
    select.append(" from tinto_apply_info ta left join tinto_bill_info tb on");
    select.append(" tb.into_id = ta.into_id where 1=1 ");
    IDB session = DBFactory.getDB();
    QueryCondition qc = new QueryCondition();
    OrderBean order = new OrderBean("intoId",false);
	query.appendOrder(order);
    qc.initFromSearchBean(query);
    
    String sql = qc.getAllSqlString(select.toString());
    System.out.println(sql);
	//分页开始位置
	int startIndex = (page.getCurrentPage()-1)*page.getShowCount()+1;
	if(startIndex<0)
		startIndex = 0;
	// 获得并返回本次查询的总条数
	int count = session.accountByList(qc.getCountSql("distinct ta.into_id"),qc.getParameterValues());
	page.setTotalResult(count);
	List<IntoApplyInfo> applyList = session.getObjectListByListForPage(sql,IntoApplyInfo.class, startIndex, page.getShowCount(), qc.getParameterValues());
	query.setOperStatus(StatusUtils.queryStatus("IntoApplyController", "billManage", null)[0]);
	sumApplyInfo(applyList, query);
	return applyList;
    
  }
  
  /**
   * 查询批次(审核 入库岗)
   * @param page
   * @param query
   * @return
   * @throws SQLException
   */
    public List<IntoApplyInfo> getIntoApplyListBySearchBean(Page page, IntoSearchBean query)
      throws SQLException
    {
      IDB session = DBFactory.getDB();
      String baseSql = "select distinct apply.* from tinto_apply_info apply,tinto_bill_info bill where bill.into_id = apply.into_id";
      QueryCondition qc = new QueryCondition();
      try {
        qc.initFromSearchBean(query);
      } catch (Exception e) {
        e.printStackTrace();
      }
      String allsql = qc.getAllSqlString(baseSql);
      System.out.println(allsql);
      int startIndex = (page.getCurrentPage() - 1) * page.getShowCount() + 1;
      if (startIndex < 0)
        startIndex = 0;
      int count = 0;
      List<IntoApplyInfo> applyList=null;

      if ((qc.getParameterValues() == null) || (qc.getParameterValues().size() == 0)) {
        count = session.account(qc.getCountSql("distinct apply.into_id"));
        applyList = session.getObjectListForPage(allsql, IntoApplyInfo.class, startIndex, page.getShowCount(), new Object[0]);
      } else {
        count = session.accountByList(qc.getCountSql("distinct apply.into_id"), qc.getParameterValues());
        applyList = session.getObjectListByListForPage(allsql, IntoApplyInfo.class, startIndex, page.getShowCount(), qc.getParameterValues());
      }
      page.setTotalResult(count);
      sumApplyInfo(applyList, query);
      /*for (IntoApplyInfo apply : applyList) {
        query.setIntoId(apply.getIntoId());
        sumApplyInfo(applyList, query);
      }*/
      return applyList;
    }
  
  /**
   * 合计批次信息
   *
   * 
   */
    public void sumApplyInfo(List<IntoApplyInfo> applyList,IntoSearchBean query) throws SQLException{
  	    if(applyList == null || applyList.size() == 0) return;
  	    IDB session = DBFactory.getDB();
  	    //批次汇总票据张数，与票据金额sql
  	    StringBuilder sb = new StringBuilder("select count(bill.intomx_id)||'' c_num,sum(bill.bill_money) from tinto_bill_info bill where bill.into_id = ?");
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
  	        if(StringUtils.isNotBlank(query.getOperStatus())){
  	            sb.append(" and bill.oper_status = ? ");
  	            param.add(query.getOperStatus().trim());
  	        } 
  	        if(StringUtils.isNotBlank(query.getApplyOperNo())){
	            sb.append(" and bill.apply_oper_no = ? ");
	            param.add(query.getApplyOperNo().trim());
	        }
	        if(StringUtils.isNotBlank(query.getCheckOperNo())){
	            sb.append(" and bill.check_oper_no = ? ");
	            param.add(query.getCheckOperNo().trim());
	        }
  	        if(StringUtils.isNotBlank(query.getPosition())){
  	            sb.append(" and bill.position = ? ");
  	            param.add(query.getPosition().trim());
  	        }
  	        if(query.getOpers()!=null){
  	            sb.append(" and bill.oper_status in (");
  	            for (Object status : query.getOpers()) {
  	            	sb.append("?,");
  	        		param.add(status);
  	        	}
  	            sb.deleteCharAt(sb.length()-1);
  	            sb.append(')');
  	        }
  	    }
  	    System.out.println(sb.toString());
  	    for(IntoApplyInfo apply:applyList){
  	    	//删除第一位 为第一个问号赋值
  	    	param.remove(0);
  	    	// 为第一个问号赋值
  	    	param.add(0, apply.getIntoId());
  		    IData data=session.getDataByList(sb.toString(), param);
  		    apply.setTotalNum(data.getString(1));
  		    apply.setTotalMoney(MoneyUtils.moneyToString(data.getDouble(2)));
  	    }
  	}

}
