<%-- 
 * 文件名称: disc_review.jsp
 * 系统名称: 票据管理平台
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: @version2.0.0.1
 * 开发人员: songzx
 * 开发时间: 2016-8-10
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期    修改人员    修改说明
 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/webpage/system/admin/taglib.jsp"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<base href="<%=basePath%>">
		<%-- jsp文件头和头部 --%>
		<%@ include file="/webpage/system/admin/top.jsp"%> 
	</head>
</head>
<body style="font-family:'微软雅黑';" style="padding-top:20px;">
	<form action="" method="" name="userForm" id="userForm" class="form-search" role="form" >
		<div class="row-fluid" >
			<label for="param_id" class="pdTop"><span class="star">*</span>贴现人我行开户账号</label>
			<input class="input-medium" type="text" name="param_id" value="" placeholder="请输入账号"/>
			<label for="param_id" class="pdTop"><span class="star">*</span>票号</label>
			<input class="input-medium" type="text" name="param_id" value="" placeholder="请输入票号"/>
		</div>
		<div class="row-fluid" >
			<label for="param_id" class="pdTop"><span class="star">*</span>金额</label>
			<input class="input-medium" type="text" name="param_id" value="" placeholder="请输入金额"/>
			<label for="param_id" class="pdTop"><span class="star">*</span>票据类型</label>
			<select class="input-medium">
				<option value="1">银承</option>
			</select>
		</div>
		<div class="row-fluid" >
			<label for="param_id" class="pdTop"><span class="star">*</span>票据品种</label>
			<select class="input-medium">
				<option value="1">纸质</option>
			</select>
			<label for="param_id" class="pdTop"><span class="star">*</span>承兑人行号</label>
			<input class="input-medium" type="text" name="param_id" value="" placeholder="请输入行号"/>
		</div>
		<div class="row-fluid" >
			<label for="param_id" class="pdTop"><span class="star">*</span>利率</label>
			<input class="input-medium" type="text" name="param_id" value="" placeholder="请输入利率"/>
			<label for="param_id" class="pdTop"><span class="star">*</span>贴现日</label>
			<input size="16" class="input-medium" type="text" value="12-02-2012" readonly>
		</div>
		<div class="row-fluid" >
			<label for="param_id" class="pdTop"><span class="star">*</span>票面到期日</label>
			<input type="text" value="" readonly class="input-medium">
			<label for="param_id" class="pdTop"><span class="star">*</span>付息方>式</label>
			<select class="input-medium">
				<option value="1">请选择</option>
				<option value="2">银票卖方付息方式贴现</option>
				<option value="3">银票买方付息方式贴现</option>
			</select>
		</div>
		<div class="row-fluid" >
			<div class="center save">
				<a class="btn-mini">保存</a>
				<a class="btn-mini">返回</a>
			</div>
		</div>
	</form>
</body>
</html>