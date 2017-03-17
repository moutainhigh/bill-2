<%-- 
 * 文件名称: user_edit.jsp
 * 系统名称: 票据管理平台
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: @version2.0.0.1
 * 开发人员: superCheng
 * 开发时间: 2016-7-12 上午08:28:22
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期    修改人员    修改说明
 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../admin/taglib.jsp"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<base href="<%=basePath%>">
		<%@ include file="../admin/top.jsp"%> 
	</head>
<body>
	<div class="clearfix">
		<div  class="page-content" id="jump-content">
			<form  name="Form" id="Form" method="post" class="form-search">
				<div id="zhongxin">
					<div class="row-fluid">
						<label for="user_id" class="text-right">用户编号</label>
						<input type="text" class="input-medium" readonly="readonly" name="user_id" id="user_id" placeholder="" value="${userView.userId}" title="参数标识"/>
						<label for="branch_no" class="text-right">所属机构</label>
						<input type="text" class="input-medium" readonly="readonly" name="branch_no" id="branch_no" placeholder="" value="${userView.branchName}" title="参数标识名称"/>
					</div>
					<div class="row-fluid">
						<label for="dep_code" class="text-right">所属部门</label>
						<input type="text" class="input-medium" readonly="readonly" name="dep_code" id="dep_code" placeholder="" value="${userView.depName}" title="参数标识值"/>
						<label for="user_name" class="text-right">用户名称</label>
						<input type="text" class="input-medium" readonly="readonly" name="user_name" id="user_name" placeholder="" value="${userView.userName}" title="参数标识值描述"/>
					</div>
					<div class="row-fluid">
						<label for="user_type" class="text-right">用户分类</label>
						<input type="text" class="input-medium" readonly="readonly" name="user_type" id="user_type" placeholder="" value="${userView.userType}" title="参数标识值描述"/>
						<label for="user_status" class="text-right">用户状态</label>
						<input type="text" class="input-medium" readonly="readonly" name="user_status" id="user_status" placeholder="请输入参数标识值描述" value="${fns:getDictLabel('K_USERSTATUS',userView.userStatus)}" title="参数标识值描述"/>
					</div>
					<div class="row-fluid">
						<label for="user_email" class="text-right">用户邮箱</label>
						<input type="text" class="input-medium" readonly="readonly" name="user_email" id="user_email" placeholder="请输入参数标识值描述" value="${userView.userEmail}" title="参数标识值描述"/>
						<label for="user_phone" class="text-right">用户电话</label>
						<input type="text" class="input-medium" readonly="readonly" name="user_phone" id="user_phone" placeholder="请输入参数标识值描述" value="${userView.userPhone}" title="参数标识值描述"/>
					</div>
					<div class="row-fluid">
						<label for="lock_status" class="text-right">登录状态</label>
						<input type="text" class="input-medium" readonly="readonly"  name="lock_status" id="lock_status" placeholder="" value="${fns:getDictLabel('K_LOGONSTATUS',userView.lockStatus)}" title="参数标识值描述"/>
						<label for="create_date" class="text-right">创建日期</label>
						<input type="text" class="input-medium" readonly="readonly"  name="create_date" id="create_date" placeholder="" value="${userView.createDate}" title="参数标识值描述"/>
					</div>
					<div class="row-fluid">
						<label for="modify_date" class="text-right">更新日期</label>
						<input type="text" class="input-medium" readonly="readonly" name="modify_date" id="modify_date" placeholder="" value="${userView.modifyDate}" title="参数标识值描述"/>
						<label for="pass_modifyDate" class="text-right">密码修改时间</label>
						<input type="text" class="input-medium" readonly="readonly" name="pass_modifyDate" id="pass_modifyDate" placeholder="" value="${userView.passModifyDate}" title="参数标识值描述"/>
					</div>
					<div class="row-fluid">
						<label for="pass_modifyDate" class="text-right">密码修改时间</label>
						<input type="text" class="input-medium" readonly="readonly" name="pass_modifyDate" id="pass_modifyDate" placeholder="" value="${userView.passModifyDate}" title="参数标识值描述"/>
						<label for="bank_no" class="text-right">法人行编号</label>
						<input type="text" class="input-medium" readonly="readonly" name="bank_no" id="bank_no" placeholder="" value="${userView.bankNo}" title="参数标识值描述"/>
					</div>
					<div class="row-fluid">
						<label for="bank_no" class="text-right">法人行编号</label>
						<input type="text" class="input-medium" readonly="readonly" name="bank_no" id="bank_no" placeholder="" value="${userView.bankNo}" title="参数标识值描述"/>
						<label for="last_logonDate" class="text-right">上次成功登录日期</label>
						<input type="text" class="input-medium" readonly="readonly" name="last_logonDate" id="last_logonDate" placeholder="" value="${userView.lastLogonDate}" title="参数标识值描述"/>
					</div>
					<div class="row-fluid">
						<label for="last_logonTime" class="text-right">上次成功登录时间</label>
						<input type="text" class="input-medium" readonly="readonly" name="last_logonTime" id="lastLogonTime" placeholder="" value="${userView.lastLogonTime}" title="参数标识值描述"/>
						<label for="last_ipAddress" class="text-right">最近登录ip</label>
						<input type="text" class="input-medium" readonly="readonly" name="last_ipAddress" id="last_ipAddress" placeholder="" value="${userView.lastIpAddress}" title="参数标识值描述"/>
					</div>
					<div class="row-fluid">
						<label for="fail_times" class="text-right">自上次登录成功失败登录次数</label>
						<input type="text" class="input-medium" readonly="readonly" name="fail_times" id="fail_times" placeholder="" value="${userView.failTimes}" title="参数标识值描述"/>
						<label for="fail_date" class="text-right">上次登录失败日期</label>
						<input type="text" class="input-medium" readonly="readonly" name="fail_date" id="fail_date" placeholder="" value="${userView.failDate}" title="参数标识值描述"/>
					</div>
					<div class="row-fluid">
						<label for="last_failIp" class="text-right">最近登录失败的ip</label>
						<input type="text" class="input-medium" readonly="readonly" name="last_failIp" id="last_failIp" placeholder="" value="${userView.lastFailIp}" title="参数标识值描述"/>
						<label for="remark1" class="text-right">备注1</label>
						<input type="text" class="input-medium" readonly="readonly" name="remark1" id="remark1" placeholder="" value="${userView.remark1}" title="参数标识值描述"/>
					</div>
					<div class="row-fluid">
						<label for="remark2" class="text-right">备注2</label>
						<input type="text" class="input-medium" readonly="readonly" name="remark2" id="remark2" placeholder="" value="${userView.remark2}" title="参数标识值描述"/>
						<label for="remark3" class="text-right">备注3</label>
						<input type="text" class="input-medium" readonly="readonly" name="remark3" id="remark3" placeholder="" value="${userView.remark3}" title="参数标识值描述"/>
					</div>
				</div>
			</form>
		  </div>
		</div>	
<%@ include file="../admin/footer.jsp"%> 		
</body>
</html>