/********************************************
 * 文件名称: UserDao.java
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

import java.lang.*;
import java.math.*;
import com.herongtech.db.impl.session.DBFactory;
import com.herongtech.db.interfaces.IDB;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.log.CommonLog;
import com.herongtech.sysconstant.ISysErrorNo;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.herongtech.commons.tools.BeanFormat;
import com.herongtech.commons.tools.DateUtil;
import com.herongtech.console.core.util.PasswordUtil;
import com.herongtech.console.domain.bean.CustManage;
import com.herongtech.console.domain.bean.Role;
import com.herongtech.console.domain.bean.RoleUser;
import com.herongtech.console.domain.bean.User;

public class UserDao {

	public int addUser(User obj) throws SQLException {
		BeanFormat.format(obj);
		IDB session = DBFactory.getDB();
		return session
				.execute(
						"insert into tuser(user_id,branch_no,dep_code,user_name,user_pwd,user_type,user_status,user_email,user_phone,lock_status,create_date,modify_date,pass_modify_date,bank_no,last_logon_date,last_logon_time,last_ip_address,fail_times,fail_date,last_fail_ip,remark1,remark2,remark3)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)",
						obj.getUserId(), obj.getBranchNo(), obj.getDepCode(),
						obj.getUserName(), obj.getUserPwd(), obj.getUserType(),
						obj.getUserStatus(), obj.getUserEmail(),
						obj.getUserPhone(), obj.getLockStatus(),
						obj.getCreateDate(), obj.getModifyDate(),
						obj.getPassModifyDate(), obj.getBankNo(),
						obj.getLastLogonDate(), obj.getLastLogonTime(),
						obj.getLastIpAddress(), obj.getFailTimes(),
						obj.getFailDate(), obj.getLastFailIp(),
						obj.getRemark1(), obj.getRemark2(), obj.getRemark3());
	}

	
	public int addLoginSuccessUserInfo(User obj) throws SQLException {
		BeanFormat.format(obj);
		IDB session = DBFactory.getDB();
		return session.execute(
				//last_logon_date,last_logon_time,last_ip_address
				"update tuser set last_logon_date=?,last_logon_time=?,last_ip_address=?,FAIL_TIMES=? where user_id=?",
				obj.getLastLogonDate(),obj.getLastLogonTime(),obj.getLastIpAddress(),0,obj.getUserId());
	}
	
	public int addRoleUser(RoleUser obj) throws SQLException {
		BeanFormat.format(obj);
		IDB session = DBFactory.getDB();
		return session.execute(
				"insert into trole_user(user_id,role_code) values(?,?)",
				obj.getUserId(), obj.getRoleCode());
	}

	public int deleteUser(User obj) throws SQLException {
		IDB session = DBFactory.getDB();
		return session.execute("delete from tuser where user_id=?",
				obj.getUserId());
	}

	public int deleteUser(String userId) throws SQLException {
		IDB session = DBFactory.getDB();
		return session.execute("delete from tuser where user_id=?", userId);
	}

	
	
	public int modifyUser(User obj) throws SQLException {
		BeanFormat.format(obj);
		IDB session = DBFactory.getDB();
		return session
				.execute(
						"update tuser set user_pwd=?,branch_no=?,dep_code=?,pass_modify_date=?,modify_date=?,user_email=?,user_phone=?,remark1=?,remark2=?,remark3=? where user_id=?",
						obj.getUserPwd(),obj.getBranchNo(),obj.getDepCode(),obj.getPassModifyDate(),obj.getModifyDate(),
						obj.getUserEmail(), obj.getUserPhone(),
						obj.getRemark1(), obj.getRemark2(), obj.getRemark3(),
						obj.getUserId());
	}

	public int deleteUserRole(String userId) throws SQLException {
		IDB session = DBFactory.getDB();
		System.out.println("删除用户"
				+ userId
				+ "的角色由此开始"
				+ session.execute("delete from trole_user where user_id=?",
						userId));
		return session
				.execute("delete from trole_user where user_id=?", userId);
	}

	public int modifyRoleUser(RoleUser obj) throws SQLException {
		BeanFormat.format(obj);
		IDB session = DBFactory.getDB();
		return session.execute(
				"update trole_user set role_code=? where user_id=?",
				obj.getRoleCode(), obj.getUserId());
	}

	public int modifyUser(User obj, String userId) throws SQLException {
		BeanFormat.format(obj);
		IDB session = DBFactory.getDB();
		return session
				.execute(
						"update tuser set branch_no=?,dep_code=?,user_name=?,user_type=?,user_status=?,user_email=?,user_phone=?,lock_status=?,modify_date=?,pass_modify_date=?,bank_no=?,last_logon_date=?,last_logon_time=?,last_ip_address=?,fail_times=?,fail_date=?,last_fail_ip=?,remark1=?,remark2=?,remark3=? where user_id=?",
						obj.getBranchNo(), obj.getDepCode(), obj.getUserName(),
						obj.getUserType(), obj.getUserStatus(),
						obj.getUserEmail(), obj.getUserPhone(),
						obj.getLockStatus(), obj.getModifyDate(),
						obj.getPassModifyDate(), obj.getBankNo(),
						obj.getLastLogonDate(), obj.getLastLogonTime(),
						obj.getLastIpAddress(), obj.getFailTimes(),
						obj.getFailDate(), obj.getLastFailIp(),
						obj.getRemark1(), obj.getRemark2(), obj.getRemark3(),
						userId);
	}

	public User getUser(String userId,String sql) throws BizAppException {
		IDB session = DBFactory.getDB();
		User obj=null;
		System.out.println("sql---------->>"+sql+",userId="+userId);
		
		try {

			 obj = session.getObject(
					sql, User.class, userId);
		} catch (SQLException e) {
			// e.printStackTrace();
			CommonLog.getCommonLogCache().errorMessage("数据库错误   [" + sql + "]");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "查询数据库失败");
		}
		return obj;
	}
	
	public List<Role> getRole(String userId,String sql) throws BizAppException {
		IDB session = DBFactory.getDB();
		List<Role> obj=null;
		System.out.println("sql---------->>"+sql+",userId="+userId);
		
		try {

			 obj = session.getObjectList(sql, Role.class, userId);
		} catch (SQLException e) {
			// e.printStackTrace();
			CommonLog.getCommonLogCache().errorMessage("数据库错误   [" + sql + "]");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "查询数据库失败");
		}
		return obj;
	}


	public int addLoginFailUserInfo(User obj) throws SQLException {
		BeanFormat.format(obj);
		IDB session = DBFactory.getDB();
		int retInfo=0;
		if(obj.getUserStatus()=="0"){
			retInfo=session.execute(
					//last_logon_date,last_logon_time,last_ip_address
					"update tuser set FAIL_DATE=?,FAIL_TIMES=?,LAST_FAIL_IP=? where user_id=?",
					obj.getFailDate(),obj.getFailTimes(),obj.getLastFailIp(),obj.getUserId());
		}else{
			retInfo=session.execute(
					//last_logon_date,last_logon_time,last_ip_address
					"update tuser set FAIL_DATE=?,FAIL_TIMES=?,LAST_FAIL_IP=?,USER_STATUS=? where user_id=?",
					obj.getFailDate(),obj.getFailTimes(),obj.getLastFailIp(),obj.getUserStatus(),obj.getUserId());
		}
		return retInfo;
	}


	public int modifyUser(String userId, String psw) throws SQLException {
		IDB session = DBFactory.getDB();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar calendar = Calendar.getInstance();
		Date date = calendar.getTime();
		String nowDate = sdf.format(date);
		String password = PasswordUtil.encrypt(userId,
				psw, PasswordUtil.getStaticSalt());
		return session.execute("update tuser set user_pwd=?,pass_modify_date=? where user_id=?",password,nowDate,userId);
	}


	public User getUserByDeptNo(String deptNo)throws SQLException {
		IDB session = DBFactory.getDB();
		User obj = (User)session.getObject("select * from tuser where dep_code=?",User.class,deptNo);
		return obj;
	}
}
