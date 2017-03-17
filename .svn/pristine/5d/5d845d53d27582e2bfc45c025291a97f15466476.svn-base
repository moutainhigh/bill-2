<%-- 
 * 文件名称: high_search.jsp
 * 系统名称: 票据管理平台
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 高级查询页面
 * 系统版本: @version2.0.0.1
 * 开发人员: 
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
		<title>高级查询</title>
		<base href="<%=basePath%>">
		<%-- jsp文件头和头部 --%>
		<%@ include file="/webpage/system/admin/top.jsp"%>
	</head>
<body style="font-family:'微软雅黑';" style="padding-top:20px">
<div class="clearfix">
		<div class="page-content" id="jump-content">
			<form action="<%=basePath%>saleApplyController.do?method=toAddBill" name="Form" id="Form"
				method="post" class="form-search">
				<div class="row-fluid">
					<label for="billNo" class="text-right">票号</label>
					<input type="text" class="input-medium" valid="required" name="billNo" id="billNo" placeholder="票号" value="" />
				</div>
				<div class="row-fluid">
					<label for="dueDt" class="text-right">
						票面到期日
					</label>
					<input type="text" class="input-medium" date-date-format="yyyy-mm-dd"  redonly="redonly" valid="required"
							name="startDay" id="startDay" placeholder=""
							value="${query.startDay}" />
					<span style="margin:7px 0 0 0">~</span>
					<input type="text" redonly="redonly" class="input-medium" valid="required"
							name="endDay" id="endDay" placeholder=""
							value="${query.endDay}" />
				</div>
				<div class="row-fluid">
					<label for="billMoney" class="text-right">
						票面金额
					</label>
					<input type="text" class="input-medium" valid="required"
							name="startMoney" id="startMoney" placeholder=""
							value="${query.startMoney}" />
					<span style="margin:7px 0 0 0">~</span>
					<input type="text" class="input-medium" valid="required"
							name="endMoney" id="endMoney" placeholder=""
							value="${query.endMoney}" />
				</div>
				<div class="row-fluid save">
					<div class="center">
						<a class="btn-mini" onclick="submit();">查询</a>
						<a class="btn-mini" onclick="top.Dialog.close();">取消</a>
					</div>
				</div>
			</form>
		</div>
	</div>
	<script type="text/javascript">
	function submit() {
		$("#Form").submit();
		$("#Form").hide();
	}
	</script>
</body>
</html>