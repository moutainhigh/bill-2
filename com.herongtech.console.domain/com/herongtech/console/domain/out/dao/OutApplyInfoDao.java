/********************************************
* 文件名称: OutApplyInfoDao.java
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
package com.herongtech.console.domain.out.dao;

import java.lang.*;
import java.math.*;

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
import com.herongtech.console.domain.out.bean.OutApplyInfo;
import com.herongtech.console.domain.out.bean.OutSearchBean;
public class OutApplyInfoDao{

public int addOutApplyInfo(OutApplyInfo obj) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("insert into tout_apply_info(out_id,cust_no,cust_name,create_dt,create_time,prod_no,is_tc,batch_class,batch_type,dept_no,cust_manager,batch_no,ex_serial,dept_name,cust_manager_name,apply_status)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
	obj.getOutId(),obj.getCustNo(),obj.getCustName(),obj.getCreateDt(),obj.getCreateTime(),
	obj.getProdNo(),obj.getIsTc(),obj.getBatchClass(),obj.getBatchType(),
	obj.getDeptNo(),obj.getCustManager(),obj.getBatchNo(),obj.getExSerial(),
	obj.getDeptName(),obj.getCustManagerName(),obj.getApplyStatus());
}

public int deleteOutApplyInfo(OutApplyInfo obj,String outId) throws SQLException {
	IDB session = DBFactory.getDB();
	return session.execute("update tout_apply_info set apply_status=? where out_id=?",obj.getApplyStatus(),outId);
}

public int deleteOutApplyInfo(String outId) throws SQLException {
	IDB session = DBFactory.getDB();
	return session.execute("delete from tout_apply_info where out_id=?",outId);
}

public int modifyOutApplyInfo(OutApplyInfo obj) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("update tout_apply_info set cust_no=?,cust_name=?,create_dt=?,create_time=?,prod_no=?,is_tc=?,batch_class=?,batch_type=?,dept_no=?,cust_manager=?,batch_no=?,ex_serial=?,dept_name=?,cust_manager_name=?,apply_status=? where out_id=?",
	obj.getCustNo(),obj.getCustName(),obj.getCreateDt(),obj.getCreateTime(),
	obj.getProdNo(),obj.getIsTc(),obj.getBatchClass(),obj.getBatchType(),
	obj.getDeptNo(),obj.getCustManager(),obj.getBatchNo(),obj.getExSerial(),
	obj.getDeptName(),obj.getCustManagerName(),obj.getApplyStatus(),obj.getOutId());
}

public int modifyOutApplyInfo(OutApplyInfo obj,String outId) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("update tout_apply_info set cust_no=?,cust_name=?,create_dt=?,create_time=?,prod_no=?,is_tc=?,batch_class=?,batch_type=?,dept_no=?,cust_manager=?,batch_no=?,ex_serial=?,dept_name=?,cust_manager_name=?,apply_status=? where out_id=?",
	obj.getCustNo(),obj.getCustName(),obj.getCreateDt(),obj.getCreateTime(),
	obj.getProdNo(),obj.getIsTc(),obj.getBatchClass(),obj.getBatchType(),
	obj.getDeptNo(),obj.getCustManager(),obj.getBatchNo(),obj.getExSerial(),
	obj.getDeptName(),obj.getCustManagerName(),obj.getApplyStatus(),outId);
}

public OutApplyInfo getOutApplyInfo(String outId) throws SQLException {
	IDB session = DBFactory.getDB();
	OutApplyInfo obj = (OutApplyInfo)session.getObject("select * from tout_apply_info where out_id=?",OutApplyInfo.class,outId);
	return obj;
}

public OutApplyInfo getOutApplyInfo(String outId,OutSearchBean query) throws SQLException {
	IDB session = DBFactory.getDB();
	OutApplyInfo obj = (OutApplyInfo)session.getObject("select * from tout_apply_info where out_id=?",OutApplyInfo.class,outId);
	if(query == null) return obj;
	List<OutApplyInfo> list = new ArrayList<OutApplyInfo>(1);
	list.add(obj);
	sumApplyInfo(list, query);
	return obj;
}

/**
 * 查询批次(申请岗)
 * @param page
 * @param query
 * @return
 * @throws SQLException
 */
  public List<OutApplyInfo> getOutApplyListForApply(Page page, OutSearchBean query)
    throws Exception
  {
    StringBuilder select = new StringBuilder("select distinct ta.*");
    select.append(" from tout_apply_info ta left join tout_bill_info tb on");
    select.append(" tb.out_id = ta.out_id where 1=1 ");
    IDB session = DBFactory.getDB();
    QueryCondition qc = new QueryCondition();
    OrderBean order = new OrderBean("outId",false);
	query.appendOrder(order);
    qc.initFromSearchBean(query);
    
    String sql = qc.getAllSqlString(select.toString());
    System.out.println(sql);
	//分页开始位置
	int startIndex = (page.getCurrentPage()-1)*page.getShowCount()+1;
	if(startIndex<0)
		startIndex = 0;
	// 获得并返回本次查询的总条数
	int count = session.accountByList(qc.getCountSql("distinct ta.out_id"),qc.getParameterValues());
	page.setTotalResult(count);
	List<OutApplyInfo> applyList = session.getObjectListByListForPage(sql,OutApplyInfo.class, startIndex, page.getShowCount(), qc.getParameterValues());
	query.setOperStatus(StatusUtils.queryStatus("OutApplyController", "billManage", null)[0]);
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
    public List<OutApplyInfo> getOutApplyListBySearchBean(Page page, OutSearchBean query)
      throws SQLException
    {
      IDB session = DBFactory.getDB();
      String baseSql = "select distinct apply.* from tout_apply_info apply,tout_bill_info bill where bill.out_id = apply.out_id";
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
      List<OutApplyInfo> applyList=null;

      if ((qc.getParameterValues() == null) || (qc.getParameterValues().size() == 0)) {
        count = session.account(qc.getCountSql("distinct apply.out_id"));
        applyList = session.getObjectListForPage(allsql, OutApplyInfo.class, startIndex, page.getShowCount(), new Object[0]);
      } else {
        count = session.accountByList(qc.getCountSql("distinct apply.out_id"), qc.getParameterValues());
        applyList = session.getObjectListByListForPage(allsql, OutApplyInfo.class, startIndex, page.getShowCount(), qc.getParameterValues());
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
    public void sumApplyInfo(List<OutApplyInfo> applyList,OutSearchBean query) throws SQLException{
  	    if(applyList == null || applyList.size() == 0) return;
  	    IDB session = DBFactory.getDB();
  	    //批次汇总票据张数，与票据金额sql
  	    StringBuilder sb = new StringBuilder("select count(bill.outmx_id)||'' c_num,sum(bill.bill_money) from tout_bill_info bill where bill.out_id = ?");
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
	        if(StringUtils.isNotBlank(query.getCheckOperNo())){
	            sb.append(" and bill.check_oper_no = ? ");
	            param.add(query.getCheckOperNo().trim());
	        }
  	        if(StringUtils.isNotBlank(query.getOperStatus())){
  	            sb.append(" and bill.oper_status = ? ");
  	            param.add(query.getOperStatus().trim());
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
  	    for(OutApplyInfo apply:applyList){
  	    	//删除第一位 为第一个问号赋值
  	    	param.remove(0);
  	    	// 为第一个问号赋值
  	    	param.add(0, apply.getOutId());
  		    IData data=session.getDataByList(sb.toString(), param);
  		    apply.setTotalNum(data.getString(1));
  		    apply.setTotalMoney(MoneyUtils.moneyToString(data.getDouble(2)));
  	    }
  	}

}
