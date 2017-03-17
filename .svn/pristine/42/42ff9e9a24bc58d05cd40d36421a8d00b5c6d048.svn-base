/********************************************
 * 文件名称: UserService.java
 * 系统名称: 基础技术平台V2.0
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: 2.0.0.1
 * 开发人员: yanjl
 * 开发时间: 2016-6-15 下午01:48:52
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期    修改人员    修改说明
 *********************************************/
package com.herongtech.console.service.user;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.herongtech.commons.tools.DateUtil;
import com.herongtech.commons.tools.StringUtils;
import com.herongtech.console.core.common.json.Page;
import com.herongtech.console.core.util.PasswordUtil;
import com.herongtech.console.domain.bean.CustManage;
import com.herongtech.console.domain.bean.Role;
import com.herongtech.console.domain.bean.RoleUser;
import com.herongtech.console.domain.bean.User;
import com.herongtech.console.domain.dao.CustManageDao;
import com.herongtech.console.domain.dao.UserDao;
import com.herongtech.console.service.interfaces.IUserService;
import com.herongtech.db.impl.session.DBFactory;
import com.herongtech.db.interfaces.IDB;
import com.herongtech.exception.impl.BizAppException;
import com.herongtech.log.CommonLog;
import com.herongtech.sysconstant.ISysErrorNo;

public class UserService implements IUserService {

	public static final String HERONGTECH_VERSION = "@system 票据管理平台 @version 2.0.0.1 @lastModiDate 20160615 @describe ";
	public static final String NOW = DateUtil.getNow();
	public static final String USERSTATUS = "0";
	public static final String PASSWORD = "password";
	public static final String BANKNO = "00000";

	
	public boolean checkUserPsw(User user,String psw) throws BizAppException {
		String password = PasswordUtil.encrypt(user.getUserId(),//7f7e630e3460c18ab626df67cf6e74d7
				psw, PasswordUtil.getStaticSalt());
		if(password.equals(user.getUserPwd())){
			return true;
		}
		return false;
	}
	
	public User checkUserExits(User user) throws BizAppException {

		String password = PasswordUtil.encrypt(user.getUserId(),
				user.getUserPwd(), PasswordUtil.getStaticSalt());

		//String query = "select * from tuser u where u.user_id = ? and u.user_pwd= ?";
		String query = "select a.*,b.dep_name,c.branch_name  from tuser a left join tdept b "
				+ "on a.dep_code=b.dep_code left join tbranch c on a.branch_no=c.branch_no "
				+ "where 1=1  and a.user_id = ? and a.user_pwd= ?";
		IDB dbsession = DBFactory.getDB();
		List<User> users = null;

		try {
			users = dbsession.getObjectList(query, User.class,
					user.getUserId(), password);
			if (users != null && users.size() > 0) {
				return users.get(0);
			} /*else {
				users = dbsession.getObjectList(query, User.class,
						user.getUserId(), user.getUserPwd());
				if (users != null && users.size() > 0) {
					return users.get(0);
				}
			}*/
		} catch (SQLException e) {
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "数据库操作错误");
		}

		return null;
	}
	
	public int checkUserExitsById(User user) throws BizAppException {


		String query = "select count(*) from tuser u where u.user_id = ?";
		IDB dbsession = DBFactory.getDB();

		int allCounts = 0;
		try {
			allCounts = dbsession.account(query, user.getUserId());
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "数据库操作错误");
		}
		return allCounts;
	}

	public String getUserRoleByUserId(String userId) throws BizAppException {
		String userRole = "";
		IDB dbsession = DBFactory.getDB();

		String query = "select * from trole_user u where u.user_id = ? ";

		List<RoleUser> sRoleUser = null;
		try {
			sRoleUser = dbsession.getObjectList(query, RoleUser.class,
			        userId);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "数据库操作错误");
		}

		for (RoleUser tsRoleUser : sRoleUser) {
			userRole += tsRoleUser.getRoleCode() + ",";
		}
		return userRole;
	}

	public void pwdInit(User user, String newPwd) {

	}

	/**
	 * 通过用户ID获取用户信息
	 */
	public User getUserByUserId(String user_id) throws SQLException, BizAppException{
		String sql = "select a.*,b.dep_name,c.branch_name  from tuser a left join tdept b "
				+ "on a.dep_code=b.dep_code left join tbranch c on a.branch_no=c.branch_no "
				+ "where 1=1  and user_id = ?";
		
		return new UserDao().getUser(user_id, sql);
		
	}
	/**
	 * 
	 * 文件名称: bbsp_platform com.herongtech.console.service.userUserService.java
	 * 系统名称: 票据管理平台
	 * 模块名称: 获取用户角色信息
	 * 软件版权: 北京合融科技有限公司
	 * 功能说明: 
	 * 系统版本: @version2.0.0.1
	 * 开发人员: superCheng
	 * 开发时间: 2016-7-28 下午5:25:20
	 * 返回值:   List<Role>
	 * 相关文档:
	 * 修改记录: 修改日期    修改人员    修改说明
	 */
	public List<Role> getRoleByUserId(String user_id,String sql) throws SQLException, BizAppException{
		
		return new UserDao().getRole(user_id, sql);
		
	}
	
	public int getUsersOfThisRole(String roleCode) throws BizAppException {

		String query = "select count(*) from trole_user u where u.role_code = ? ";
		IDB dbsession = DBFactory.getDB();

		int allCounts = 0;
		try {
			allCounts = dbsession.account(query, roleCode);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "数据库操作错误");
		}
		return allCounts;
	}

	public void delUserRole(String userId) throws BizAppException {
		UserDao userDao = new UserDao();
		try {
			int i = userDao.deleteUserRole(userId);

		} catch (SQLException e) {
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "");
		}

	}

	public void addUserRole(RoleUser roleuser) throws BizAppException {
		UserDao userDao = new UserDao();
		try {
			if (userDao.addRoleUser(roleuser) != 1) {
				throw new BizAppException(ISysErrorNo.ERR_DBERR, "增加用户角色失败");
			}
		} catch (SQLException e) {
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "");
		}

	}

	/**
	 * 
	 * 文件名称: bbsp_platform com.herongtech.console.service.userUserService.java
	 * 系统名称: 票据管理平台
	 * 模块名称: 修改用户信息
	 * 软件版权: 北京合融科技有限公司
	 * 功能说明: 
	 * 系统版本: @version2.0.0.1
	 * 开发人员: superCheng
	 * 开发时间: 20162016-7-28 下午3:55:57
	 * 审核人员:
	 * 相关文档:
	 * 修改记录: 修改日期    修改人员    修改说明
	 */
	@Override
	public void modifyUser(User user) throws BizAppException {
		UserDao userDao = new UserDao();
		//修改用户密码
		
	
		user.setModifyDate(NOW);
		
		try {
			if (userDao.modifyUser(user) != 1) {
				throw new BizAppException(ISysErrorNo.ERR_DBERR, "修改用户信息失败");
			}
		} catch (SQLException e) {
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "修改用户信息失败");
		}

	}

	
	public void modifyUser(String userId,String Psw) throws BizAppException {
		UserDao userDao = new UserDao();
		
		try {
			if (userDao.modifyUser(userId,Psw) != 1) {
				throw new BizAppException(ISysErrorNo.ERR_DBERR, "修改用户密码失败");
			}
		} catch (SQLException e) {
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "");
		}

	}
	
	public int checkPassModifyDate(String date) throws ParseException{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		long passModifyDate=sdf.parse(date).getTime();
		Calendar calendar = Calendar.getInstance();
		long nowDateFormile = calendar.getTime().getTime(); // Date.getTime() 获得毫秒型日期
		long betweenDate = (nowDateFormile - passModifyDate) / (1000 * 60 * 60*24); // 计算间隔多少天，则除以毫秒到天的转换公式
		
		System.out.println(betweenDate);
		return (int) betweenDate;
		/*if(betweenDate>=30){
			return 1;//超过30天期限，强制修改
		}else if(betweenDate>=27&&betweenDate<30){
			return 2;//大于27天且小于30天，距离密码修改还剩余3天，提示修改，正常登录
		}else{
		return 3;//密码在正常范围期限内，可不修改
		}*/
	}
	public static void main(String[] args) {
		UserService us= new UserService();
		try {
			us.checkPassModifyDate("2016-07-03 11:27:40");
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 
	 * 文件名称: bbsp_platform com.herongtech.console.service.userUserService.java
	 * 系统名称: 票据管理平台
	 * 模块名称: 新增用户
	 * 软件版权: 北京合融科技有限公司
	 * 功能说明: 
	 * 系统版本: @version2.0.0.1
	 * 开发人员: superCheng
	 * 开发时间: 2016-7-28 下午3:51:03
	 * 审核人员:
	 * 相关文档:
	 * 修改记录: 修改日期    修改人员    修改说明
	 */
	@Override
	public void addUser(User user) throws BizAppException {
		UserDao userDao = new UserDao();
		user.setUserPwd(PasswordUtil.encrypt(user.getUserId(), "password",
				PasswordUtil.getStaticSalt()));
		user.setCreateDate(NOW);
		user.setUserStatus(USERSTATUS);
		user.setBankNo(BANKNO);
		System.out.println("user------------------------->>>>>"+user);
		try {
			if (userDao.addUser(user) != 1) {
				throw new BizAppException(ISysErrorNo.ERR_DBERR, "增加用户失败");
			}
		} catch (SQLException e) {
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "");
		}

	}
	
	/**
	 * 
	 * 文件名称: bbsp_platform com.herongtech.console.service.userUserService.java
	 * 系统名称: 票据管理平台
	 * 模块名称: 保存登录成功用户的登录时间、IP等信息
	 * 软件版权: 北京合融科技有限公司
	 * 功能说明: 
	 * 系统版本: @version2.0.0.1
	 * 开发人员: superCheng
	 * 开发时间: 2016-7-29 下午2:45:26
	 * 返回值:   void
	 * 相关文档:
	 * 修改记录: 修改日期    修改人员    修改说明
	 * @throws BizAppException 
	 */
	public void addLoginSuccess(User user) throws BizAppException{
		UserDao dao = new UserDao();
		try {
			if (dao.addLoginSuccessUserInfo(user) != 1) {
				throw new BizAppException(ISysErrorNo.ERR_DBERR, "更新用户信息失败");
			}
		} catch (SQLException e) {
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "更新用户信息失败");
		}
	}
	
	/**
	 * 获取客户经理通过部门编号
	 */
	public User getUserByDeptNo(String deptNo)throws BizAppException{
		UserDao dao=new UserDao();
		try {
			return dao.getUserByDeptNo(deptNo);
		}catch (SQLException e) {
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "");
		}
		
	}

	/**
	 * 
	 * 文件名称: bbsp_platform com.herongtech.console.service.userUserService.java
	 * 系统名称: 票据管理平台
	 * 模块名称: 登录失败用户信息更新
	 * 软件版权: 北京合融科技有限公司
	 * 功能说明: 
	 * 系统版本: @version2.0.0.1
	 * 开发人员: superCheng
	 * 开发时间: 20162016-7-29 下午4:25:00
	 * 审核人员:
	 * 相关文档:
	 * 修改记录: 修改日期    修改人员    修改说明
	 * @throws BizAppException 
	 */
	@Override
	public void addLoginFail(User params) throws BizAppException {
		UserDao dao = new UserDao();
		try {
			if (dao.addLoginFailUserInfo(params) != 1) {
				throw new BizAppException(ISysErrorNo.ERR_DBERR, "更新新用户登录信息失败");
			}
		} catch (SQLException e) {
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "");
		}
	}
	
	public void getUserList(User user,Page page) throws BizAppException{
		List<Object> param = new ArrayList<Object>();
		StringBuffer sql = new StringBuffer(
				"select a.*,b.dep_name,c.branch_name  from tuser a left join tdept b on a.dep_code=b.dep_code left join tbranch c on a.branch_no=c.branch_no  ");
		StringBuffer sql_count = new StringBuffer(
				"select count(*) from tuser a left join tdept b on a.dep_code=b.dep_code left join tbranch c on a.branch_no=c.branch_no  ");
		String userId = user.getUserId();
		String userStatus = user.getUserStatus();
		System.out.println("userId===============>>>>" + userId);
		System.out.println("userStatus===============>>>>" + userStatus);


		// 角色
		String userRole = user.getUserRoleCode();
		System.out.println("用户角色---------------〉〉〉〉〉〉" + userRole);
		if (!StringUtils.isEmpty(userRole)) {
			sql.append(" left join trole_user d on a.user_id=d.user_id where 1=1 and a.user_status !='1'");
			sql_count
					.append(" left join trole_user d on a.user_id=d.user_id where 1=1 and a.user_status !='1'");
			sql.append(" and d.role_code like '%" + userRole.trim() + "%'");
			sql_count.append(" and d.role_code like '%" + userRole.trim()
					+ "%'");
		} else {
			sql.append("  where 1=1 and a.user_status !='1'");
			sql_count.append(" where 1=1 and a.user_status !='1'");
		}

		// HsSqlString dbSql = new HsSqlString("tuser");
		if (!StringUtils.isEmpty(userId)) {
			sql.append(" and a.user_id like '%" + userId + "%'");
			sql_count.append(" and a.user_id like '%" + userId + "%'");
		}

		if (!StringUtils.isEmpty(userStatus)) {
			sql.append(" and a.user_status like '%" + userStatus + "%'");
			sql_count.append(" and a.user_status like '%" + userStatus + "%'");
		}
		// 所属机构
		String branchNo = user.getBankNo();
		System.out.println("用户所属机构---------------〉〉〉〉〉〉" + branchNo);
		if (!StringUtils.isEmpty(branchNo)) {
			sql.append(" and a.branch_no = '" + branchNo.trim() + "'");
			sql_count.append(" and a.branch_no = '" + branchNo.trim() + "'");
		}

		// 所属部门
		String depCode = user.getDepCode();
		System.out.println("用户所属部门---------------〉〉〉〉〉〉" + depCode);
		if (!StringUtils.isEmpty(depCode)) {
			sql.append(" and a.dep_code = '" + depCode.trim() + "'");
			sql_count.append(" and a.dep_code = '" + depCode.trim() + "'");
		}

		// 姓名
		String userName = user.getUserName();
		System.out.println("用户姓名---------------〉〉〉〉〉〉" + userName);
		if (!StringUtils.isEmpty(userName)) {
			sql.append(" and a.user_name like '%" + userName.trim() + "%'");
			sql_count.append(" and a.user_name like '%" + userName.trim()
					+ "%'");
		}

	
		int startIndex = page.getCurrentResult();
		List<User> userList = new ArrayList<User>();
		System.out.println("sql==============>>>>>" + sql);
		System.out.println("参数列表：" + param.toString());
		try {

			IDB session = DBFactory.getDB(); // 获取数据库连接
			userList = session.getObjectListByListForPage(sql.toString(),
					User.class, startIndex, page.getShowCount(), param);

			// 获得并返回本次查询的总条数
			int count = 0;
			count = session.account(sql_count.toString());
			System.out.println("count----------->>>" + count);
			page.setTotalResult(count);

		} catch (SQLException e) {
			CommonLog.getCommonLogCache().errorMessage(
					"数据库错误   [" + sql.toString() + "]");
			throw new BizAppException(ISysErrorNo.ERR_DBERR, "查询用户列表失败");
		}
	}

}
