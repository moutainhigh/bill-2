package com.herongtech.console.domain.bean;

import org.apache.commons.lang.StringUtils;

import com.herongtech.console.core.util.ExcelField;

public class UserLoginfo implements java.io.Serializable{

private static final long serialVersionUID = 1L;
	
	private String userTypeDesc;//用户类型描述
	
	private String userType;//code
	
	private String userNo;//用户操作员号
	private String password;
	private String notControllByAnBao;
	
	private String userName;//用户操作员
	
	private String  lastLoginTime;//上次登录时间
	
	private int sysId;//系统编号
	
	private String branchId;//机构编号
	
	private String branchName;//机构名称
	
	private Integer sysUserId;
	
	private String brchBankNo;//登陆者行号
	
	private String loginType;// 登陆类型
	
	private String[] roleIds;// 用户角色
	
	private String[] roleCodes;// 用户角色代码
	
	private String brchNo;//机构号（机构编号）
	
	private String orderFlag = "0"; //订单/内部帐标识  0-内部帐 1-订单
	
	private String collBrchName;//发托机构名称 
	
	private String brchStatus;
	
	private String elecAuth; //电票权限开通标志 0否 1是
	
	private String userEmail; //用户邮件地址
	
	private String userPhone; //用户电话
	
	private Long loginHistoryId; //登陆日志ID
	
	//机构级别
	private String branchLevel = " ";
	/**机构级别*/
	public String getBranchLevel(){
		return branchLevel;
	}
	/**机构级别*/
	public void setBranchLevel(String branchLevel){
		this.branchLevel = branchLevel;
	}
	
	public boolean isDMBranch(){//是否上线行
		return true;
	}
	
	public String getBrchStatus() {
		return brchStatus;
	}

	public void setBrchStatus(String brchStatus) {
		this.brchStatus = brchStatus;
	}

	public String getCollBrchName() {
		return collBrchName;
	}

	public void setCollBrchName(String collBrchName) {
		this.collBrchName = collBrchName;
	}

	public boolean isOrderPattern(){
		return true;
	}
	
	public String getOrderFlag() {
		return orderFlag;
	}

	public void setOrderFlag(String orderFlag) {
		this.orderFlag = orderFlag;
	}
	/**机构号（机构编号）*/
	public void setBrchNo(String brchNo) {
	    this.brchNo = brchNo;
	}

	/**
	 * 系统状态
	 * 在系统日终时，切换BBSP_SYSTEM_STATUS=2 ，表示日终进行中，拦截器会判断是否决定登出系统
	 * 日终完成后，请切换成正常状态
	 * @return
	 */
	private static int BBSP_SYSTEM_STATUS=1;//表示正常状态
	
	public static final int STATUS_WORK_TIME=1;//表示正常状态
	
	public static final int STATUS_END_OF_DAY=2;//表示日终进行中
	
	public static int getBbspSystemStatus(){
		return BBSP_SYSTEM_STATUS;
	}
	
	/**
	 * 更新系统状态
	 * @param status
	 * STATUS_WORK_TIME=1，表示正常状态
	 * STATUS_END_OF_DAY=2 ，表示日终进行中
	 */
	public synchronized static void setBbspSystemStatus(int status){
		BBSP_SYSTEM_STATUS = status;
	}
	
	/**机构号（机构编号）*/
	public String getBrchNo(){
	    return brchNo;
	}
	
	/**登陆者行号*/
	public String getBrchBankNo() {
		return brchBankNo;
	}
	/**登陆者行号*/
	public void setBrchBankNo(String brchBankNo) {
		this.brchBankNo = brchBankNo;
	}

	public Integer getSysUserId() {
		return sysUserId;
	}

	public void setSysUserId(Integer sysUserId) {
		this.sysUserId = sysUserId;
	}

	/**机构id*/
	public String getBranchId() {
		return branchId;
	}
	/**机构id*/
	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}

	/**机构名称*/
	public String getBranchName() {
		return branchName;
	}
	/**机构名称*/
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public int getSysId() {
		return sysId;
	}

	public void setSysId(int sysId) {
		this.sysId = sysId;
	}

	public String getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(String lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public String getUserTypeDesc() {
		return userTypeDesc;
	}

	public void setUserTypeDesc(String userTypeDesc) {
		this.userTypeDesc = userTypeDesc;
	}
	/**用户操作员号*/
	public String getUserNo() {
		return userNo;
	}
	/**用户操作员号*/
	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}
	/**用户名*/
	public String getUserName() {
		return userName;
	}
	/**用户名*/
	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getLoginType() {
		return loginType;
	}

	public void setLoginType(String loginType) {
		this.loginType = loginType;
	}

	public String[] getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(String[] roleIds) {
		this.roleIds = roleIds;
	}

	public String[] getRoleCodes() {
		return roleCodes;
	}

	public void setRoleCodes(String[] roleCodes) {
		this.roleCodes = roleCodes;
	}
	/**电票权限开通标志 0否 1是*/
	public String getElecAuth() {
		return elecAuth;
	}
	/**电票权限开通标志 0否 1是*/
	public void setElecAuth(String elecAuth) {
		this.elecAuth = elecAuth;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	/**用户电话*/
	public String getUserPhone() {
		return userPhone;
	}
	/**用户电话*/
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public Long getLoginHistoryId() {
		return loginHistoryId;
	}

	public void setLoginHistoryId(Long loginHistoryId) {
		this.loginHistoryId = loginHistoryId;
	}
	/**用户密码*/
	public String getPassword() {
		return password;
	}
	/**用户密码*/
	public void setPassword(String password) {
		this.password = password;
	}

	public String getNotControllByAnBao() {
		return notControllByAnBao;
	}

	public void setNotControllByAnBao(String notControllByAnBao) {
		this.notControllByAnBao = notControllByAnBao;
	}
	
	
	
	
	
	//User
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

	//机构号（机构编号）
	private String branchNo = " ";
	/**user中的机构号（机构编号）（user也在userloginfo中）和userloginfo中的机构号一样*/
	@ExcelField(title="所属机构", align=2, sort=2)
	public String getBranchNo(){
		return branchNo;
	}
	/**user中的机构号（机构编号）（user也在userloginfo中）和userloginfo中的机构号一样*/
	public void setBranchNo(String branchNo){
		this.branchNo = branchNo;
	}

	//所属部门
	private String depCode = " ";
	/**所属部门*/
	@ExcelField(title="所属部门", align=2, sort=3)
	public String getDepCode(){
		return depCode;
	}
	/**所属部门*/
	public void setDepCode(String depCode){
		this.depCode = depCode;
	}
	
	//部门名称
		private String depName = " ";
		/**部门名称*/
		public String getDepName(){
			return depName;
		}
		/**部门名称*/
		public void setDepName(String depName){
			this.depName = depName;
		}


	//用户密码
	private String userPwd = " ";
	/**用户密码*/
	public String getUserPwd(){
		return userPwd;
	}
	/**用户密码*/
	public void setUserPwd(String userPwd){
		this.userPwd = userPwd;
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
	/**用户状态*/
	public String getUserStatus(){
		return userStatus;
	}
	/**用户状态*/
	public void setUserStatus(String userStatus){
		this.userStatus = userStatus;
	}

	


	//锁定状态
	private String lockStatus = " ";
	/**锁定状态*/
	public String getLockStatus(){
		return lockStatus;
	}
	/**锁定状态*/
	public void setLockStatus(String lockStatus){
		this.lockStatus = lockStatus;
	}

	//创建时间
	private String createDate = "0";
	/**创建时间*/
	public String getCreateDate(){
		return createDate;
	}
	/**创建时间*/
	public void setCreateDate(String createDate){
		this.createDate = createDate;
	}

	//最后修改时间
	private String modifyDate = "0";
	/**最后修改时间*/
	public String getModifyDate(){
		return modifyDate;
	}
	/**最后修改时间*/
	public void setModifyDate(String modifyDate){
		this.modifyDate = modifyDate;
	}

	//密码修改时间
	private String passModifyDate = "0";
	/**密码修改时间*/
	public String getPassModifyDate(){
		return passModifyDate;
	}
	/**密码修改时间*/
	public void setPassModifyDate(String passModifyDate){
		this.passModifyDate = passModifyDate;
	}

	//法人行编号
	private String bankNo = " ";
	/**法人行编号*/
	public String getBankNo(){
		return bankNo;
	}
	/**法人行编号*/
	public void setBankNo(String bankNo){
		this.bankNo = bankNo;
	}

	//上次成功登录日期
	private String lastLogonDate = "0";
	/**上次成功登录日期*/
	public String getLastLogonDate(){
		return lastLogonDate;
	}
	/**上次成功登录日期*/
	public void setLastLogonDate(String lastLogonDate){
		this.lastLogonDate = lastLogonDate;
	}

	//上次成功登录时间
	private String lastLogonTime = "0";
	/**上次成功登录时间*/
	public String getLastLogonTime(){
		return lastLogonTime;
	}
	/**上次成功登录时间*/
	public void setLastLogonTime(String lastLogonTime){
		this.lastLogonTime = lastLogonTime;
	}

	//最近登录ip
	private String lastIpAddress = "";
	/**最近登录ip*/
	public String getLastIpAddress(){
		return lastIpAddress;
	}
	/**最近登录ip*/
	public void setLastIpAddress(String lastIpAddress){
		this.lastIpAddress = lastIpAddress;
	}

	//自上次登录成功失败登录次数
	private int failTimes = 0;
	/**自上次登录成功失败登录次数*/
	public int getFailTimes(){
		return failTimes;
	}
	/**自上次登录成功失败登录次数*/
	public void setFailTimes(int failTimes){
		this.failTimes = failTimes;
	}

	//上次登录失败日期
	private String failDate = "";
	/**上次登录失败日期*/
	public String getFailDate(){
		return failDate;
	}
	/**上次登录失败日期*/
	public void setFailDate(String failDate){
		this.failDate = failDate;
	}

	//最近登录失败的ip
	private String lastFailIp = " ";
	/**最近登录失败的ip*/
	public String getLastFailIp(){
		return lastFailIp;
	}
	/**最近登录失败的ip*/
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

	
	
	
	
	
	
	
	
}
