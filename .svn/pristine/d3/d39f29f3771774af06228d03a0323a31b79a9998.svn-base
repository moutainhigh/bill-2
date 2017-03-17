<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
<base href="<%=basePath%>">
<meta charset="utf-8" />
<title></title>

<meta name="description" content="timeout Error Page" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<%@ include file="/webpage/system/admin/top.jsp"%>
<body>
	<div class="main-container" id="main-container">
		<div id="page-content" class="page-content">
			<div class="row">
				<div class="error-container">
					<div class="well">
						<h1 class="grey lighter smaller center">
							<span class="blue bigger-125"><i class="icon-sitemap"></i>
								Timeout</span> 会话已失效
						</h1>
						<hr />
						<h3 class="lighter smaller center">当前会话已失效,不可操作业务,请退出系统</h3>
						<div class="center">
							<div class="space"></div>
							<a class="btn" href="javascript:logout();">退出</a>
						</div>
						<hr />
						<div class="space"></div>
						<div class="row">
							<div id="zhongxin"></div>
						</div>
					</div>
				</div>
			</div>
			<%--/row--%>
		</div>
		<%--/#page-content--%>
	</div>
	<form  action="#" name="dataCollectForm" method="post"></form>
	<%--/main-container--%>
	<%@ include file="/webpage/system/admin/footer.jsp"%>
	<%-- inline scripts related to this page --%>
	<script type="text/javascript">
		$(top.hangge());
		function logout(){
			dataCollectForm.action ="<%=basePath%>loginController.do?method=logout";
			dataCollectForm.submit();
		}
	</script>
</body>
</html>