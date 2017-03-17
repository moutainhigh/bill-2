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

<meta name="description" content="404 Error Page" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<%@ include file="webpage/system/admin/top.jsp"%>
<body>
	<div class="main-container" id="main-container">
		<div id="page-content" class="page-content">
			<div class="row">
				<div class="error-container">
					<div class="well">
						<h1 class="grey lighter smaller center">
							<span class="blue bigger-125"><i class="icon-sitemap"></i>
								404</span> 没有找到此页面
						</h1>
						<hr />
						<h3 class="lighter smaller center">找遍啦，可是还是没有发现哦!</h3>
						<div>
							<div class="space"></div>

							<h4 class="smaller">试试以下方式:</h4>
							<ul class="unstyled spaced inline bigger-110">
								<li><i class="icon-hand-right blue"></i> 检查路径是不是有误</li>
								<li><i class="icon-hand-right blue"></i> 检查代码是不是有误</li>
								<li><i class="icon-hand-right blue"></i> 检查环境配置是不是有误</li>
							</ul>
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
	<%--/main-container--%>
	<%@ include file="webpage/system/admin/footer.jsp"%>
	<%-- inline scripts related to this page --%>
	<script type="text/javascript">
		$(top.hangge());
	</script>
</body>
</html>