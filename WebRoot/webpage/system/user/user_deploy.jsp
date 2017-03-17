<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../admin/taglib.jsp"%>
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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<%@ include file="../admin/top.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title></title>
<style type="text/css">
#one {
	width: 200px;
	height: 180px;
	float: left
}
#two {
	width: 50px;
	height: 180px;
	float: left
}
#three {
	width: 200px;
	height: 180px;
	float: left
}
.btn {
	width: 45px;
}
* {
	margin: 0;
	padding: 0;
}
div.centent {
	float: left;
	text-align: center;
	margin: 10px;
}
span {
	display: block;
	margin: 2px 2px;
	padding: 4px 10px;
	cursor: pointer;
}
</style>
<%--   引入jQuery --%>
<script src="weblib/assets/js/jquery-1.3.1.js" type="text/javascript"></script>
<script type="text/javascript">
	$(function() {
		//移到右边
		$('#add').click(function() {
			//获取选中的选项，删除并追加给对方
			$('#select1 option:selected').appendTo('#select2');
		});
		//移到左边
		$('#remove').click(function() {
			$('#select2 option:selected').appendTo('#select1');
		});
		//全部移到右边
		$('#add_all').click(function() {
			//获取全部的选项,删除并追加给对方
			$('#select1 option').appendTo('#select2');
		});
		//全部移到左边
		$('#remove_all').click(function() {
			$('#select2 option').appendTo('#select1');
		});
		//双击选项
		$('#select1').dblclick(function() { //绑定双击事件
			//获取全部的选项,删除并追加给对方
			$("option:selected", this).appendTo('#select2'); //追加给对方
		});
		//双击选项
		$('#select2').dblclick(function() {
			$("option:selected", this).appendTo('#select1');
		});
	});
	$(top.hangge());
	//保存
	function save() {
		var user_id = $("#user_id").val();
		var role_code = "";
		var s = document.getElementById("select2");
		if (s.length == 0) {
			showTips("select2", "请选择角色");
			return false;
		}
		for (i = 0; i < s.length; i++) {
			role_code += s[i].value + ",";
		}
		var url = "userController.do?method=deploy&user_id=" + user_id + "&role_code="
				+ role_code;
		location.href = url;
	}
	$(function() {
		$("#filterName").keyup(function() {
			$("#select1: option").hide().filter(
					":contains('" + ($(this).val()) + "')").show();
		})
	})
</script>
</head>
<body style="background-color:#f4f8fb;font-family:'微软雅黑';">
<div class="clearfix">
	<div class="page-content" id="jump-content">
		<form action="userController.do?method=deploy" name="Form" id="Form" method="post" class="form-search">
			<div id="zhongxin">
				<div>
					<div class="row-fluid">
						<label for="user_id" class="text-right">用户编号</label>
						<input class="input-medium" type="text" name="user_id" id="user_id" readonly="readonly" placeholder="输入用户编号" value="${user_id}" title="" />
					</div>
				</div>
				<div style="margin-left:90px;">
					<table width="500" align="center" border="0" cellpadding="0" cellspacing="0" class="newContTab">
						<tr>
							<td>
								<div>
									<div class="row-fluid">
										<label for="user_id" style="margin-left:-80px;" class="text-right">搜索</label>
										<input class="input-medium" type="text" name="filterName" id="filterName" placeholder="请输入相应的角色" value="" title="请输入相应的角色" />
									</div>
									<div>
										<select multiple="multiple" id="select1" style="width:170px;height:250px; float:left; border:4px #A0A0A4 outset; padding:4px; ">
											<c:forEach items="${all_role}" var="allrole">
												<option value="${allrole.roleCode}">${allrole.roleName}</option>
											</c:forEach>
										</select>
									</div>
									<div style="float:left;margin-top: 55px;">
										<span id="add"> 
											<input type="button" class="btn-mini" value=">" />
										</span>
										<span id="add_all"> <input name="button" type="button" class="btn-mini" value=">>" /> </span><span id="remove">
											<input name="button2" type="button" class="btn-mini" value="<"/>
										</span>
										<span id="remove_all">
											<input name="button3" type="button" class="btn-mini" value="<<"/>
										</span>
						  			</div>
									<div>
										<select multiple="multiple" id="select2"
											style="width: 170px;height:250px; float:lfet;border:4px #A0A0A4 outset; padding:4px;">
											<c:forEach items="${user_role}" var="userrole">
												<option value="${userrole.roleCode}">${userrole.roleName}</option>
											</c:forEach>
										</select>
									</div>
								</div>					
							</td>
						</tr>
					</table>
				</div>
				<div class="row-fluid save">
					<div class="center">
						<a class="btn-mini" onClick="save();">保存</a>
						<a class="btn-mini" onClick="top.Dialog.close();">取消</a>
					</div>
				</div>
			</div>
		</form>
	</div>
</div>
<%@ include file="../admin/footer.jsp"%>
</body>
</html>