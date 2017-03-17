/********************************************
* 文件名称: User.java
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
package com.herongtech.console.domain.bean;

import java.io.Serializable;
import java.lang.*;
import java.math.*;

import com.herongtech.console.core.util.ExcelField;

public class User implements Serializable {
	/**
	 * 序列号
	 */
	private static final long serialVersionUID = 1L;
	
	//用户编号
	private String userId = "";
	@Override
	public String toString() {
		return "User [userId=" + userId + ", branchNo=" + branchNo
				+ ", branchName=" + branchName + ", depCode=" + depCode
				+ ", depName=" + depName + ", userName=" + userName
				+ ", userPwd=" + userPwd + ", userType=" + userType
				+ ", userRoleCode=" + userRoleCode + ", userStatus="
				+ userStatus + ", userEmail=" + userEmail + ", userPhone="
				+ userPhone + ", lockStatus=" + lockStatus + ", createDate="
				+ createDate + ", modifyDate=" + modifyDate
				+ ", passModifyDate=" + passModifyDate + ", bankNo=" + bankNo
				+ ", lastLogonDate=" + lastLogonDate + ", lastLogonTime="
				+ lastLogonTime + ", lastIpAddress=" + lastIpAddress
				+ ", failTimes=" + failTimes + ", failDate=" + failDate
				+ ", lastFailIp=" + lastFailIp + ", remark1=" + remark1
				+ ", remark2=" + remark2 + ", remark3=" + remark3 + "]";
	}
	@ExcelField(title="员工号", align=2, sort=1)
	public String getUserId(){
		return userId;
	}
	public void setUserId(String userId){
		this.userId = userId;
	}

	//所属机构
	private String branchNo = " ";
	@ExcelField(title="所属机构", align=2, sort=2)
	/**机构号*/
	public String getBranchNo(){
		return branchNo;
	}
	public void setBranchNo(String branchNo){
		this.branchNo = branchNo;
	}

	//机构名称
		private String branchName = " ";
		public String getBranchName(){
			return branchName;
		}
		public void setBranchName(String branchName){
			this.branchName = branchName;
		}

	//所属部门
	private String depCode = " ";
	@ExcelField(title="所属部门", align=2, sort=3)
	public String getDepCode(){
		return depCode;
	}
	public void setDepCode(String depCode){
		this.depCode = depCode;
	}
	
	//部门名称
		private String depName = " ";
		public String getDepName(){
			return depName;
		}
		public void setDepName(String depName){
			this.depName = depName;
		}


	//用户名称
	private String userName = " ";
	@ExcelField(title="用户姓名", align=2, sort=4)
	public String getUserName(){
		return userName;
	}
	public void setUserName(String userName){
		this.userName = userName;
	}

	//用户密码
	private String userPwd = " ";
	public String getUserPwd(){
		return userPwd;
	}
	public void setUserPwd(String userPwd){
		this.userPwd = userPwd;
	}

	//用户分类
	private String userType = " ";
	public String getUserType(){
		return userType;
	}
	public void setUserType(String userType){
		this.userType = userType;
	}

	private String userRoleCode;
	
	
	public String getUserRoleCode() {
		return userRoleCode;
	}
	public void setUserRoleCode(String userRoleCode) {
		this.userRoleCode = userRoleCode;
	}

	//用户状态
	private String userStatus = " ";
	public String getUserStatus(){
		return userStatus;
	}
	public void setUserStatus(String userStatus){
		this.userStatus = userStatus;
	}

	//用户邮箱
	private String userEmail = " ";
	@ExcelField(title="用户邮箱", align=2, sort=5)
	public String getUserEmail(){
		return userEmail;
	}
	public void setUserEmail(String userEmail){
		this.userEmail = userEmail;
	}

	//用户电话
	private String userPhone = " ";
	@ExcelField(title="用户电话", align=2, sort=6)
	public String getUserPhone(){
		return userPhone;
	}
	public void setUserPhone(String userPhone){
		this.userPhone = userPhone;
	}

	//锁定状态
	private String lockStatus = " ";
	public String getLockStatus(){
		return lockStatus;
	}
	public void setLockStatus(String lockStatus){
		this.lockStatus = lockStatus;
	}
	public User(String userId, String branchNo, String branchName,
			String depCode, String depName, String userName, String userPwd,
			String userType, String userRoleCode, String userStatus,
			String userEmail, String userPhone, String lockStatus,
			String createDate, String modifyDate, String passModifyDate,
			String bankNo, String lastLogonDate, String lastLogonTime,
			String lastIpAddress, int failTimes, String failDate,
			String lastFailIp, String remark1, String remark2, String remark3) {
		super();
		this.userId = userId;
		this.branchNo = branchNo;
		this.branchName = branchName;
		this.depCode = depCode;
		this.depName = depName;
		this.userName = userName;
		this.userPwd = userPwd;
		this.userType = userType;
		this.userRoleCode = userRoleCode;
		this.userStatus = userStatus;
		this.userEmail = userEmail;
		this.userPhone = userPhone;
		this.lockStatus = lockStatus;
		this.createDate = createDate;
		this.modifyDate = modifyDate;
		this.passModifyDate = passModifyDate;
		this.bankNo = bankNo;
		this.lastLogonDate = lastLogonDate;
		this.lastLogonTime = lastLogonTime;
		this.lastIpAddress = lastIpAddress;
		this.failTimes = failTimes;
		this.failDate = failDate;
		this.lastFailIp = lastFailIp;
		this.remark1 = remark1;
		this.remark2 = remark2;
		this.remark3 = remark3;
	}
	

	//创建时间
	private String createDate = "0";
	public String getCreateDate(){
		return createDate;
	}
	public void setCreateDate(String createDate){
		this.createDate = createDate;
	}

	//最后修改时间
	private String modifyDate = "0";
	public String getModifyDate(){
		return modifyDate;
	}
	public void setModifyDate(String modifyDate){
		this.modifyDate = modifyDate;
	}

	//密码修改时间
	private String passModifyDate = "0";
	public String getPassModifyDate(){
		return passModifyDate;
	}
	public void setPassModifyDate(String passModifyDate){
		this.passModifyDate = passModifyDate;
	}

	//法人行编号
	private String bankNo = " ";
	public String getBankNo(){
		return bankNo;
	}
	public void setBankNo(String bankNo){
		this.bankNo = bankNo;
	}

	//上次成功登录日期
	private String lastLogonDate = "0";
	public String getLastLogonDate(){
		return lastLogonDate;
	}
	public void setLastLogonDate(String lastLogonDate){
		this.lastLogonDate = lastLogonDate;
	}

	//上次成功登录时间
	private String lastLogonTime = "0";
	public String getLastLogonTime(){
		return lastLogonTime;
	}
	public void setLastLogonTime(String lastLogonTime){
		this.lastLogonTime = lastLogonTime;
	}

	//最近登录ip
	private String lastIpAddress = "";
	public String getLastIpAddress(){
		return lastIpAddress;
	}
	public void setLastIpAddress(String lastIpAddress){
		this.lastIpAddress = lastIpAddress;
	}

	//自上次登录成功失败登录次数
	private int failTimes = 0;
	public int getFailTimes(){
		return failTimes;
	}
	public void setFailTimes(int failTimes){
		this.failTimes = failTimes;
	}

	//上次登录失败日期
	private String failDate = "";
	public String getFailDate(){
		return failDate;
	}
	public void setFailDate(String failDate){
		this.failDate = failDate;
	}

	//最近登录失败的ip
	private String lastFailIp = " ";
	public String getLastFailIp(){
		return lastFailIp;
	}
	public void setLastFailIp(String lastFailIp){
		this.lastFailIp = lastFailIp;
	}

	//备注1
	private String remark1 = " ";
	@ExcelField(title="备注1", align=2, sort=7)
	public String getRemark1(){
		return remark1;
	}
	public void setRemark1(String remark1){
		this.remark1 = remark1;
	}

	//备注2
	private String remark2 = " ";
	@ExcelField(title="备注2", align=2, sort=8)
	public String getRemark2(){
		return remark2;
	}
	public void setRemark2(String remark2){
		this.remark2 = remark2;
	}

	//备注3
	private String remark3 = " ";
	@ExcelField(title="备注3", align=2, sort=9)
	public String getRemark3(){
		return remark3;
	}
	public void setRemark3(String remark3){
		this.remark3 = remark3;
	}
	public User(String userId, String branchNo, String branchName,
			String depCode, String depName, String userName, String userPwd,
			String userType, String userStatus, String userEmail,
			String userPhone, String lockStatus, String createDate,
			String modifyDate, String passModifyDate, String bankNo,
			String lastLogonDate, String lastLogonTime, String lastIpAddress,
			int failTimes, String failDate, String lastFailIp, String remark1,
			String remark2, String remark3) {
		super();
		this.userId = userId;
		this.branchNo = branchNo;
		this.branchName = branchName;
		this.depCode = depCode;
		this.depName = depName;
		this.userName = userName;
		this.userPwd = userPwd;
		this.userType = userType;
		this.userStatus = userStatus;
		this.userEmail = userEmail;
		this.userPhone = userPhone;
		this.lockStatus = lockStatus;
		this.createDate = createDate;
		this.modifyDate = modifyDate;
		this.passModifyDate = passModifyDate;
		this.bankNo = bankNo;
		this.lastLogonDate = lastLogonDate;
		this.lastLogonTime = lastLogonTime;
		this.lastIpAddress = lastIpAddress;
		this.failTimes = failTimes;
		this.failDate = failDate;
		this.lastFailIp = lastFailIp;
		this.remark1 = remark1;
		this.remark2 = remark2;
		this.remark3 = remark3;
	}
	public User(String userId, String branchNo, String depCode,
			String userName, String userPwd, String userType,
			String userStatus, String userEmail, String userPhone,
			String lockStatus, String createDate, String modifyDate,
			String passModifyDate, String bankNo, String lastLogonDate,
			String lastLogonTime, String lastIpAddress, int failTimes,
			String failDate, String lastFailIp, String remark1, String remark2,
			String remark3) {
		super();
		this.userId = userId;
		this.branchNo = branchNo;
		this.depCode = depCode;
		this.userName = userName;
		this.userPwd = userPwd;
		this.userType = userType;
		this.userStatus = userStatus;
		this.userEmail = userEmail;
		this.userPhone = userPhone;
		this.lockStatus = lockStatus;
		this.createDate = createDate;
		this.modifyDate = modifyDate;
		this.passModifyDate = passModifyDate;
		this.bankNo = bankNo;
		this.lastLogonDate = lastLogonDate;
		this.lastLogonTime = lastLogonTime;
		this.lastIpAddress = lastIpAddress;
		this.failTimes = failTimes;
		this.failDate = failDate;
		this.lastFailIp = lastFailIp;
		this.remark1 = remark1;
		this.remark2 = remark2;
		this.remark3 = remark3;
	}
	public User() {
		super();
	}


}
