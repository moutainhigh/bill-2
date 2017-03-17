/********************************************
* 文件名称: SaveApplyInfoDao.java
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
package com.herongtech.console.domain.save.dao;

import java.lang.*;
import java.math.*;

import com.herongtech.data.interfaces.data.IData;
import com.herongtech.db.impl.session.DBFactory;
import com.herongtech.db.interfaces.IDB;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.herongtech.commons.tools.BeanFormat;
import com.herongtech.console.core.util.MoneyUtils;
import com.herongtech.console.domain.save.bean.SaveApplyInfo;
import com.herongtech.console.domain.save.bean.SaveBillInfo;
import com.herongtech.console.domain.save.bean.SaveSearchBean;
public class SaveApplyInfoDao{

public int addSaveApplyInfo(SaveApplyInfo obj) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("insert into tsave_apply_info(save_id,cust_no,cust_name,create_date,create_time,prod_no,cust_manager,is_tc,dept_no,batch_type,batch_class,oper_no,batch_no,dept_name,cust_manager_name,branch_no,impawn_bail_account,impawn_bail_name,apply_status)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
	obj.getSaveId(),obj.getCustNo(),obj.getCustName(),obj.getCreateDate(),obj.getCreateTime(),
	obj.getProdNo(),obj.getCustManager(),obj.getIsTc(),obj.getDeptNo(),
	obj.getBatchType(),obj.getBatchClass(),obj.getOperNo(),obj.getBatchNo(),
	obj.getDeptName(),obj.getCustManagerName(),obj.getBranchNo(),obj.getImpawnBailAccount(),
	obj.getImpawnBailName(),obj.getApplyStatus());
}

public int deleteSaveApplyInfo(SaveApplyInfo obj,String saveId) throws SQLException {
	IDB session = DBFactory.getDB();
	return session.execute("update tsave_apply_info set apply_status=? where save_id=?",obj.getApplyStatus(),saveId);
}

public int deleteSaveApplyInfo(String saveId) throws SQLException {
	IDB session = DBFactory.getDB();
	return session.execute("delete from tsave_apply_info where save_id=?",saveId);
}

public int modifySaveApplyInfo(SaveApplyInfo obj) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("update tsave_apply_info set cust_no=?,cust_name=?,create_date=?,create_time=?,prod_no=?,cust_manager=?,is_tc=?,dept_no=?,batch_type=?,batch_class=?,oper_no=?,batch_no=?,dept_name=?,cust_manager_name=?,branch_no=?,impawn_bail_account=?,impawn_bail_name=?,apply_status=? where save_id=?",
	obj.getCustNo(),obj.getCustName(),obj.getCreateDate(),obj.getCreateTime(),
	obj.getProdNo(),obj.getCustManager(),obj.getIsTc(),obj.getDeptNo(),
	obj.getBatchType(),obj.getBatchClass(),obj.getOperNo(),obj.getBatchNo(),
	obj.getDeptName(),obj.getCustManagerName(),obj.getBranchNo(),obj.getImpawnBailAccount(),
	obj.getImpawnBailName(),obj.getApplyStatus(),obj.getSaveId());
}

public int modifySaveApplyInfo(SaveApplyInfo obj,String saveId) throws SQLException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	return session.execute("update tsave_apply_info set cust_no=?,cust_name=?,create_date=?,create_time=?,prod_no=?,cust_manager=?,is_tc=?,dept_no=?,batch_type=?,batch_class=?,oper_no=?,batch_no=?,dept_name=?,cust_manager_name=?,branch_no=?,impawn_bail_account=?,impawn_bail_name=?,apply_status=? where save_id=?",
	obj.getCustNo(),obj.getCustName(),obj.getCreateDate(),obj.getCreateTime(),
	obj.getProdNo(),obj.getCustManager(),obj.getIsTc(),obj.getDeptNo(),
	obj.getBatchType(),obj.getBatchClass(),obj.getOperNo(),obj.getBatchNo(),
	obj.getDeptName(),obj.getCustManagerName(),obj.getBranchNo(),obj.getImpawnBailAccount(),
	obj.getImpawnBailName(),obj.getApplyStatus(),saveId);
}

public SaveApplyInfo getSaveApplyInfo(String saveId,SaveSearchBean query) throws SQLException {
	IDB session = DBFactory.getDB();
	SaveApplyInfo obj = (SaveApplyInfo)session.getObject("select * from tsave_apply_info where save_id=?",SaveApplyInfo.class,saveId);
	if(query == null) return obj;
	List<SaveApplyInfo> list = new ArrayList<SaveApplyInfo>(1);
	list.add(obj);
	sumApplyInfo(list, query);
	return obj;
}

public SaveApplyInfo getSaveApplyInf(String saveId) throws SQLException {
    IDB session = DBFactory.getDB();
    SaveApplyInfo obj = (SaveApplyInfo)session.getObject("select * from tsave_apply_info where save_id=?",SaveApplyInfo.class,saveId);
    return obj;
  }

/**
 * 票据张数，与票据金额汇总
 * @param applyList
 * @param query
 * @throws SQLException 
 */
public void sumApplyInfo(List<SaveApplyInfo> applyList,SaveSearchBean query) throws SQLException{
    if(applyList == null || applyList.size() == 0) return;
    IDB session = DBFactory.getDB();
    //批次汇总票据张数，与票据金额sql
    StringBuilder sb = new StringBuilder("select count(bill.savemx_id)||'' c_num,sum(bill.bill_money) from tsave_bill_info bill where bill.save_id = ?");
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
    for(SaveApplyInfo apply:applyList){
    	//删除第一位 为第一个问号赋值
    	param.remove(0);
    	// 为第一个问号赋值
    	param.add(0, apply.getSaveId());
	    IData data=session.getDataByList(sb.toString(), param);
	    apply.setTotalNum(data.getString(1));
	    apply.setTotalMoney(MoneyUtils.moneyToString(data.getDouble(2)));
    }
}

}
