<%-- 
 * 文件名称: rgct_draft_log_detail.jsp
 * 系统名称: 票据管理平台
 * 模块名称: 报文日志详情页面
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: @version2.0.0.1
 * 开发人员: lijiangtao
 * 开发时间: 2016-08-18
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
<body style="font-family:'微软雅黑';background:#f4f8fb;">
<div class="page-content" id="jump-content">
	<table  id="tab2" cellpadding="0" cellspacing="0" class="table table-bordered">
		<tbody>
			<tr>
				<td>请求报文编号</td>
				<td>${rgctdraftlog.draftNoReq}</td>
			</tr>
			<tr>
				<td>请求报文标识号</td>
				<td>${rgctdraftlog.reqSid}</td>
			</tr>
			<tr>
				<td>发送时间</td>
				<td>${rgctdraftlog.reqDt}</td>
			</tr>
			<tr>
				<td >发送报文明细</td>
				<td style="background:#f4f8fb;padding:0px;margin:0px;height:400px;"><textarea style="background:#f4f8fb;margin:10px;width:90%;height:100%;">${rgctdraftlog.reqDraft}</textarea></td>
			</tr>
			
			<tr>
				<td>接收报文编号</td>
				<td>${rgctdraftlog.draftNoResp}</td>
			</tr>
			<tr>
				<td>接收报文标识号</td>
				<td>${rgctdraftlog.respSid}</td>
			</tr>
			<tr>
				<td>接收时间</td>
				<td>${rgctdraftlog.respDt}</td>
			</tr>
			
			<tr>
				<td style="width:144px;">接收报文明细</td>
				<td style="background:#f4f8fb;padding:0px;margin:0px;height:400px;"><textarea style="background:#f4f8fb;margin:10px;width:90%;height:100%;">${rgctdraftlog.respDraft}</textarea></td>
			</tr>
			<tr>
				<td colspan="6" class="center"><a class="btn btn-minier btn-info" onclick="top.Dialog.close();">关闭</a></td>
			</tr>
		</tbody>
	</table>
</div>
</body>
</html>