/********************************************
* 文件名称: GetApplyInfoDao.java
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
package com.herongtech.console.domain.get.dao;

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
import com.herongtech.console.domain.get.bean.GetApplyInfo;
import com.herongtech.console.domain.get.bean.GetSearchBean;
public class GetApplyInfoDao{

public int addGetApplyInfo(GetApplyInfo obj) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("insert into tget_apply_info(get_id,cust_no,cust_name,create_dt,create_time,prod_no,is_tc,batch_class,batch_type,dept_no,cust_manager,batch_no,ex_serial,dept_name,cust_manager_name,apply_status)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
	obj.getGetId(),obj.getCustNo(),obj.getCustName(),obj.getCreateDt(),obj.getCreateTime(),
	obj.getProdNo(),obj.getIsTc(),obj.getBatchClass(),obj.getBatchType(),
	obj.getDeptNo(),obj.getCustManager(),obj.getBatchNo(),obj.getExSerial(),
	obj.getDeptName(),obj.getCustManagerName(),obj.getApplyStatus());
}

public int deleteGetApplyInfo(GetApplyInfo obj,String getId) throws SQLException {
	IDB session = DBFactory.getDB();
	return session.execute("update tget_apply_info set apply_status=? where get_id=?",obj.getApplyStatus(),getId);
}

public int deleteGetApplyInfo(String getId) throws SQLException {
	IDB session = DBFactory.getDB();
	return session.execute("delete from tget_apply_info where get_id=?",getId);
}

public int modifyGetApplyInfo(GetApplyInfo obj) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("update tget_apply_info set cust_no=?,cust_name=?,create_dt=?,create_time=?,prod_no=?,is_tc=?,batch_class=?,batch_type=?,dept_no=?,cust_manager=?,batch_no=?,ex_serial=?,dept_name=?,cust_manager_name=?,apply_status=? where get_id=?",
	obj.getCustNo(),obj.getCustName(),obj.getCreateDt(),obj.getCreateTime(),
	obj.getProdNo(),obj.getIsTc(),obj.getBatchClass(),obj.getBatchType(),
	obj.getDeptNo(),obj.getCustManager(),obj.getBatchNo(),obj.getExSerial(),
	obj.getDeptName(),obj.getCustManagerName(),obj.getApplyStatus(),obj.getGetId());
}

public int modifyGetApplyInfo(GetApplyInfo obj,String getId) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("update tget_apply_info set cust_no=?,cust_name=?,create_dt=?,create_time=?,prod_no=?,is_tc=?,batch_class=?,batch_type=?,dept_no=?,cust_manager=?,batch_no=?,ex_serial=?,dept_name=?,cust_manager_name=?,apply_status=? where get_id=?",
	obj.getCustNo(),obj.getCustName(),obj.getCreateDt(),obj.getCreateTime(),
	obj.getProdNo(),obj.getIsTc(),obj.getBatchClass(),obj.getBatchType(),
	obj.getDeptNo(),obj.getCustManager(),obj.getBatchNo(),obj.getExSerial(),
	obj.getDeptName(),obj.getCustManagerName(),obj.getApplyStatus(),getId);
}

public GetApplyInfo getGetApplyInfo(String getId) throws SQLException {
	IDB session = DBFactory.getDB();
	GetApplyInfo obj = (GetApplyInfo)session.getObject("select * from tget_apply_info where get_id=?",GetApplyInfo.class,getId);
	return obj;
}

public GetApplyInfo getGetApplyInfo(String getId,GetSearchBean query) throws SQLException {
	IDB session = DBFactory.getDB();
	GetApplyInfo obj = (GetApplyInfo)session.getObject("select * from tget_apply_info where get_id=?",GetApplyInfo.class,getId);
	if(query == null) return obj;
	List<GetApplyInfo> list = new ArrayList<GetApplyInfo>(1);
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
  public List<GetApplyInfo> getGetApplyListForApply(Page page, GetSearchBean query)
    throws Exception
  {
    StringBuilder select = new StringBuilder("select distinct ta.*");
    select.append(" from tget_apply_info ta left join tget_bill_info tb on");
    select.append(" tb.get_id = ta.get_id where 1=1 ");
    IDB session = DBFactory.getDB();
    QueryCondition qc = new QueryCondition();
    OrderBean order = new OrderBean("getId",false);
	query.appendOrder(order);
    qc.initFromSearchBean(query);
    
    String sql = qc.getAllSqlString(select.toString());
	//分页开始位置
	int startIndex = (page.getCurrentPage()-1)*page.getShowCount()+1;
	if(startIndex<0)
		startIndex = 0;
	// 获得并返回本次查询的总条数
	int count = session.accountByList(qc.getCountSql("distinct ta.get_id"),qc.getParameterValues());
	page.setTotalResult(count);
	List<GetApplyInfo> applyList = session.getObjectListByListForPage(sql,GetApplyInfo.class, startIndex, page.getShowCount(), qc.getParameterValues());
	query.setOperStatus(StatusUtils.queryStatus("RepurCollateApplyController", "billManage", null)[0]);
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
    public List<GetApplyInfo> getGetApplyListBySearchBean(Page page, GetSearchBean query)
      throws SQLException
    {
      IDB session = DBFactory.getDB();
      String baseSql = "select distinct apply.* from tget_apply_info apply,tget_bill_info bill where bill.get_id = apply.get_id";
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
      List<GetApplyInfo> applyList=null;

      if ((qc.getParameterValues() == null) || (qc.getParameterValues().size() == 0)) {
        count = session.account(qc.getCountSql("distinct apply.get_id"));
        applyList = session.getObjectListForPage(allsql, GetApplyInfo.class, startIndex, page.getShowCount(), new Object[0]);
      } else {
        count = session.accountByList(qc.getCountSql("distinct apply.get_id"), qc.getParameterValues());
        applyList = session.getObjectListByListForPage(allsql, GetApplyInfo.class, startIndex, page.getShowCount(), qc.getParameterValues());
      }
      page.setTotalResult(count);
      sumApplyInfo(applyList, query);
      return applyList;
    }
  
  /**
   * 合计批次信息
   *
   * 
   */
    public void sumApplyInfo(List<GetApplyInfo> applyList,GetSearchBean query) throws SQLException{
  	    if(applyList == null || applyList.size() == 0) return;
  	    IDB session = DBFactory.getDB();
  	    //批次汇总票据张数，与票据金额sql
  	    StringBuilder sb = new StringBuilder("select count(bill.getmx_id)||'' c_num,sum(bill.bill_money) from tget_bill_info bill where bill.get_id = ?");
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
  	    System.out.println(sb.toString());
  	    for(GetApplyInfo apply:applyList){
  	    	//删除第一位 为第一个问号赋值
  	    	param.remove(0);
  	    	// 为第一个问号赋值
  	    	param.add(0, apply.getGetId());
  		    IData data=session.getDataByList(sb.toString(), param);
  		    apply.setTotalNum(data.getString(1));
  		    apply.setTotalMoney(MoneyUtils.moneyToString(data.getDouble(2)));
  	    }
  	}

}
