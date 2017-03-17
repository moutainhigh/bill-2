/********************************************
* 文件名称: DictDao.java
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
package com.herongtech.console.domain.dao;

import java.sql.SQLException;
import java.util.List;

import com.herongtech.commons.tools.BeanFormat;
import com.herongtech.console.domain.bean.Dict;
import com.herongtech.db.impl.session.DBFactory;
import com.herongtech.db.interfaces.IDB;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.log.CommonLog;
import com.herongtech.sysconstant.ISysErrorNo;
public class DictDao{

public int addDictGroup(Dict obj) throws SQLException, BizAppException {
	BeanFormat.format(obj);
	List<Dict> list = getDictList(obj.getKeyNo());
	IDB session = DBFactory.getDB();
	int temp = 0;
	String sql1 = "insert into tdict_group(dict_group_code,dict_group_name,kind_code,kernal_flag,remark)values(?,?,?,?,?)";
	try{
		if(list.size()<=0){
			temp = session.execute(sql1,obj.getKeyNo(),obj.getKeyName(),obj.getBelongType(),obj.getKernalFlag(),null);
		}else{
			temp = 1;
		}
	} catch (SQLException e) {
		try {
			session.rollback();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		CommonLog.getCommonLogCache().errorMessage("数据库错误   [" + sql1 +"]");
		throw new BizAppException(ISysErrorNo.ERR_DBERR, "数据库插入失败");
	}
	return temp;
}
public int addDictItem(Dict obj) throws SQLException, BizAppException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	int temp = 0;
	String sql2 = "insert into tdict_item(dict_group_code,dict_item_code,dict_item_value)values(?,?,?)";

	try{
		temp = session.execute(sql2,obj.getKeyNo(),obj.getItemCode(),obj.getItemValue());
	} catch (SQLException e) {
		try {
			session.rollback();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		CommonLog.getCommonLogCache().errorMessage("数据库错误   [" + sql2+"]");
		throw new BizAppException(ISysErrorNo.ERR_DBERR, "数据库插入失败");
	}
	return temp;
}

public int deleteDictGroup(String keyNo) throws SQLException, BizAppException {
	IDB session = DBFactory.getDB();
	int temp = 0;
	String sql1 = "delete from tdict_group where dict_group_code=?";

	try{
		List<Dict> list = getDictList(keyNo);
		if(list.size()<=0){
			temp = session.execute(sql1,keyNo);
		}else{
			temp = 1;
		}
	} catch (SQLException e) {
		try {
			session.rollback();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		CommonLog.getCommonLogCache().errorMessage("数据库错误   [" + sql1 + "]");
		throw new BizAppException(ISysErrorNo.ERR_DBERR, "数据库删除失败");
	}
	return temp;
}
public int deleteDictItem(String keyNo,String itemCode) throws SQLException, BizAppException {
	IDB session = DBFactory.getDB();
	int temp = 0;
	String sql2 = "delete from tdict_item where dict_group_code=? and dict_item_code=?";

	try{
		temp = session.execute(sql2,keyNo,itemCode);
	} catch (SQLException e) {
		try {
			session.rollback();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		CommonLog.getCommonLogCache().errorMessage("数据库错误   ["+sql2+"]");
		throw new BizAppException(ISysErrorNo.ERR_DBERR, "数据库删除失败");
	}
	return temp;
}



public int modifyDictGroup(Dict obj) throws SQLException, BizAppException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	int temp = 0;
	String sql1 = "update tdict_group set dict_group_name=?,kind_code=?,kernal_flag=? where dict_group_code=?";
	try{
		temp = session.execute(sql1,obj.getKeyName(),obj.getBelongType(),obj.getKernalFlag(),obj.getKeyNo());
	} catch (SQLException e) {
		try {
			session.rollback();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		CommonLog.getCommonLogCache().errorMessage("数据库错误   [" + sql1 +"]");
		throw new BizAppException(ISysErrorNo.ERR_DBERR, "数据库修改失败");
	}
	return temp;
}
public int modifyDictItem(Dict obj) throws SQLException, BizAppException {
	BeanFormat.format(obj);
	IDB session = DBFactory.getDB();
	int temp = 0;
	String sql2 = "update tdict_item set dict_item_value=? where dict_group_code=? and dict_item_code=?";
	try{
		temp = session.execute(sql2,obj.getItemValue(),obj.getKeyNo(),obj.getItemCode());
	} catch (SQLException e) {
		try {
			session.rollback();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		CommonLog.getCommonLogCache().errorMessage("数据库错误   [" + sql2+"]");
		throw new BizAppException(ISysErrorNo.ERR_DBERR, "数据库修改失败");
	}
	return temp;
}

public List<Dict> getDictList(String keyNo) throws SQLException {
	IDB session = DBFactory.getDB();
	List<Dict> objs = (List<Dict>)session.getObjectList("select * from vdict where key_no=?",Dict.class,keyNo);
	return objs;
}
/**根据付息方式的数字获取对应的付息方式字符串*/
public String getPayTypeStringbypaytype(String paytype)throws SQLException {
	IDB session = DBFactory.getDB();
	Dict objs = session.getObject("select * from vdict where key_no='K_PAY_TYPE' and item_code=?",Dict.class,paytype);
	
	return objs.getItemValue();
}

/**根据billtype的数字获取对应的billtype字符串*/
public String getBillTypeStringbybilltype(String billtype)throws SQLException {
	IDB session = DBFactory.getDB();
	Dict objs = session.getObject("select * from vdict where key_no='K_BILL_TYPE' and item_code=?",Dict.class,billtype);
	
	return objs.getItemValue();
}
/**根据billclass的数字获取对应的billclass字符串*/
public String getBillClassStringbybillclass(String billclass)throws SQLException {
	IDB session = DBFactory.getDB();
	Dict objs = session.getObject("select * from vdict where key_no='K_BILL_CLASS' and item_code=?",Dict.class,billclass);
	
	return objs.getItemValue();
}

}
