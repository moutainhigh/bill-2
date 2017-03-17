<%-- 
 * 文件名称: user_list.jsp
 * 系统名称: 票据管理平台
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: @version2.0.0.1
 * 开发人员: Super Cheng
 * 开发时间: 2016-7-11 上午19:28:22
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期    修改人员    修改说明
 --%>
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
	<%-- jsp文件头和头部 --%>
	<%@ include file="../admin/top.jsp"%>
</head>
<body>
	<div class="clearfix">
		<div id="page-content" class="page-content">
		<%-- 查询区  --%>
			<div id="header">
				<form action="<%=basePath%>/userController.do?method=listUsers" method="post" name="userForm" id="userForm" class="form-search">
					<div class="row-fluid">
						<label for="user_id">用户编号</label>
						<input class="input-medium" type="text" name="userId" id="userId" value="${mapField.userId}" placeholder="请输入用户编号" />
						<label for="user_tatus">用户状态</label>
						<t:dictSelect name="userStatus" dictGroup="K_USERSTATUS" defaultVal="${mapField.userStatus}" haveHead="true" other=""></t:dictSelect>
						<sys:tableSort id="order_by" name="order_by"
							value="${mapField.order_by}" callback="page();" />
						<a class="btn-mini" onclick="searchd();" id="search">查询</a>
					</div>
				</form>
			</div>
			<%-- 按钮操作区 --%>
			<div id="center">
				<form  id="btnForm">
					<div class="row-fluid">
						<div class="span6" id="btn-left">
							<a class="btn-mini" onclick="add();">新增</a> 
							<a class="btn-mini" onclick="edit();">修改</a> 
							<a class="btn-mini" onclick="del();">删除</a> 
							<a class="btn-mini" onclick="distribute();">分配角色</a>
							<a class="btn-mini" id="resetpwd" onclick="resetpwd();">重置密码</a> 
						</div>
						<div class="span6" id="btn-right">
							<a id="btnExport" class="btn-mini pull-right" type="button">导出</a>
							<a id="btnImport" class="btn-mini pull-right" type="button">导入</a>
					</div>
					</div>
				</form>
			</div>
			<%-- /按钮操作区 --%>
			<%-- 列表操作区 --%>
			<div id="footer">
				<table id="table_report" class="table table-striped table-bordered table-hover">
					<thead>
						<tr>
							<th class="center" onclick="selectAll('zcheckbox', 'ids')"><label><input type="checkbox" id="zcheckbox" /></label></th>
							<th class="center">序号</th>
							<th class="center">用户编号</th>
							<th class="center">所属机构</th>
							<th class="center">所属部门</th>
							<th class="center">用户名称</th>
							<th class="center">用户状态</th>
							<th class="center">用户邮箱</th>
							<th class="center">用户电话</th>
							<th class="center">登录状态</th>
							<th class="center">操作</th>
						</tr>
					</thead>
					<c:choose>
						<c:when test="${not empty userList}">
							<c:forEach items="${userList}" var="sysparam" varStatus="vs">
								<tr>
									<td class='center'>
										<input type='checkbox' name='ids' value="${sysparam.userId}" />
									</td>
									<td class='center'>${vs.index+1}</td>
									<td class='center'>${sysparam.userId}</td>
									<td class='center'>${sysparam.branchName}</td>
									<td class='center'>${sysparam.depName}</td>
									<td class='center'>${sysparam.userName}</td>
									<td class='center' id='userStatus'>${fns:getDictLabel('K_USERSTATUS',sysparam.userStatus)}
									<input type='hidden' id='userStatus1' value="${sysparam.userStatus}" />
									</td>		
									<td class="center">${sysparam.userEmail}</td>
									<td class="center">${sysparam.userPhone}</td>
									<td class="center">${fns:getDictLabel('K_LOGONSTATUS',sysparam.lockStatus)}</td>
									<td class="center"><a class="" style="cursor:pointer;"
										id="viewUser" onclick="qwe('${sysparam.userId}');">查看</a>
									</td>
								</tr>
							</c:forEach>
						</c:when>
					<c:otherwise>
						<tr>
							<td colspan="100" class="center">没有相关数据</td>
						</tr>
					</c:otherwise>
				</c:choose>
			</table>
		</div>
		<%-- /列表操作区 --%>
		<div id="page" class="pagination">
			<form action="<%=basePath%>/userController.do?method=listUsers" name="pageForm" method="post">
				<%@ include file="/webpage/system/admin/pageFormContent.jsp"%>
				<input class="input-medium" type="hidden" name="userId" value="${mapField.userId}" />
				<input class="input-medium" type="hidden" name="userStatus" defaultVal="${mapField.userStatus}" />
			</form>
		</div>
	</div>
</div>
<div id="importBox" class="hide">
	<form id="importForm" action="<%=basePath%>/userController.do?method=import"
		method="post" enctype="multipart/form-data" class="form-search"
		style="padding-left:20px;text-align:center;"
		onsubmit="loading('正在导入，请稍等...');">
	<br/> <input id="uploadFile" name="uploadFile" type="file" style="width:330px" /><br />
	<br/> 
		 <input id="btnImportSubmit" class="btn btn-minier btn-yellow" type="submit" value="导入" />  
			 <a href="<%=basePath%>/userController.do?method=template">下载模板</a>
	</form>
</div>
<%@ include file="/webpage/system/admin/modalDialog.jsp"%> 
<%@ include file="../admin/footer.jsp"%>	
<script type="text/javascript">
	$(document).ready(function() {
		var URL = "<%=basePath%>userController.do?method=exportuser";
			$("#btnExport").click(function(){
				var frameId = window.frameElement && window.frameElement.id || '';
				var diag = new top.Dialog();
		 		diag.Drag = true;
		 		diag.Title ="详情";
				diag.URL = "<%=basePath%>exportController.do?method=getExportInfo&tableName=TUSER&action=exportController.do?method=commonExport&formName=userForm&frameId="+frameId;
				diag.Width = 800;
		 		diag.Height = 575;
		 		diag.CancelEvent = function(){ //关闭事件
					diag.close();
		 		};
		 		diag.show();
			});
			$("#btnImport").click(function(){
				$.jBox($("#importBox").html(), {title:"导入数据", buttons:{"关闭":true}, 
					bottomText:"导入文件不能超过5M，仅允许导入“xls”或“xlsx”格式文件！"});
			});
		});
	//检索
	function searchd(){
		if($("#userForm").validate()){
			modal("#layer_loading,#image");
			$("#userForm").submit();
		}else{
			return false;
		}
	}
	//新增
	function add(){
		 var diag = new top.Dialog();
		 diag.Drag = true;
		 diag.Title ="新增";
		 diag.URL = "<%=basePath%>userController.do?method=toAdd";
		 diag.Width = 700;
		 diag.Height = 250;
		 diag.CancelEvent = function(){ //关闭事件
			// if (diag.innerFrame.contentWindow.document.getElementById('zhongxin').style.display == 'none'){
				pageForm.submit();
			//}
			 diag.close();
		 };
		 diag.show(); 
	}
	//查看
	function qwe(name){
		 var diag = new top.Dialog();
		 diag.Drag = true;
		 diag.Title ="查看";
		 diag.URL = "<%=basePath%>userController.do?method=viewUser&user_id="+name;
		 diag.Width = 800;
		 diag.Height = 520;
		 diag.CancelEvent = function(){ //关闭事件
			 //if (diag.innerFrame.contentWindow.document.getElementById('zhongxin').style.display == 'none'){
					pageForm.submit();
			//}
			 diag.close();
		 };
		 diag.show(); 
	}
	//修改
	function edit(){
		 var checkNum = getCheckNum("ids");
	   	 if (checkNum !=1){
	   	 	$("#")
	   		bootbox.alert("请选择一条记录编辑");
	   		return;
	   	 }
	   	 var user_id = getCheckStr("ids");
	   	 var diag = new top.Dialog();
		 diag.Drag = true;
		 diag.Title ="修改";
		 diag.URL = '<%=basePath%>userController.do?method=toEdit&user_id='+user_id;
		 diag.Width = 700;
		 diag.Height = 250;
		 diag.CancelEvent = function(){ //关闭事件
			//if(diag.innerFrame.contentWindow.document.getElementById('zhongxin').style.display == 'none'){				
				pageForm.submit();
			//}
			diag.close();
		 };
		 diag.show();
	}
	//删除
	function del(){
		var checkNum = getCheckNum("ids");
	   	if (checkNum <= 0){
	   		bootbox.alert("请选择删除记录");
	   		return;
	   	}
	   	var user_ids = getCheckStr("ids");
		bootbox.confirm("是否确定要删除选择的记录?将会删除与该用户相关的角色权限信息",function(result){
			if(result){
				$.ajax({
					type: "POST",
					url: '<%=basePath%>userController.do?method=del',
			    	data: {'user_ids': user_ids},
					dataType:'json',
					//beforeSend: validateData,
					cache: false,
					success: function(data){	
						if (data.success){  //处理成功
							pageForm.submit();
						} else {
							//top.hangge();
							bootbox.alert("删除失败!"); 
						}
					}
				});
			}
		})
	}
	function distribute(){
		 var checkNum = getCheckNum("ids");
	   	 if (checkNum <=0){
	   		bootbox.alert("请选择一条记录分配角色");
	   		return;
	   	 }
	   	 var user_id = getCheckStr("ids");
	   	 var diag = new top.Dialog();
		 diag.Drag = true;
		 diag.Title ="分配角色";
		 diag.URL = '<%=basePath%>userController.do?method=toDepoly&user_id='+user_id;
		 diag.Width = 630;
		 diag.Height = 400;
		 diag.CancelEvent = function(){ //关闭事件
			//if(diag.innerFrame.contentWindow.document.getElementById('zhongxin').style.display == 'none'){				
				pageForm.submit();
			//}
			diag.close();
		 };
		 diag.show();
	}
	//重置密码
	function resetpwd(){
		 var checkNum = getCheckNum("ids");
	   	 if (checkNum <=0){
	   		bootbox.alert("请选择一条记录重置密码");
	   		return;
	   	 }
		var user_id = getCheckStr("ids");
		var url = "userController.do?method=resetPwd&user_id="+user_id;
		$.ajax({
			url:url,
			type:"POST",
			dataType:"JSON",
			success: function(data){
  				if(data.success){
  					bootbox.alert({
	  					message:"重置密码成功！！！",
	  					callback:function(){
	  						pageForm.submit();
	  					}
  					});
				} else{
					bootbox.alert("重置密码失败！！！");
				
				}
  			 }
  		});
	}
	//用户解锁
	function unlockUser(){
	 	var userStatus = $("#userStatus1").val();
		 var checkNum = getCheckNum("ids");
	   	 if (checkNum <= 0){
	   		bootbox.alert("请选择一条记录进行用户解锁");
	   		return;
	   	 }
	   	 /* if(userStatus==0){
	   	 	bootbox.alert("该用户处于已解锁状态");
	   	 	return;
	   	 } */
		var user_id = getCheckStr("ids");
		var url = "userController.do?method=unclockUser&user_id="+user_id;
		$.ajax({
			url:url,
			type:"POST",
			dataType:"JSON",
			success: function(data){
  				if(data.success){
  						bootbox.alert({
	  					message:"用户解锁成功！！！",
	  					callback:function(){
	  						pageForm.submit();
	  					}
  					});
				} else{
					bootbox.alert("用户解锁失败！！！");
				}
  			}
  		});
	}
	//用户解锁
	function lockUser(){
	   	 var userStatus = $("#userStatus1").val();
		 var checkNum = getCheckNum("ids");
	   	 if (checkNum <0){
	   		bootbox.alert("请选择一条记录进行用户状态锁定");
	   		return;
	   	 }
	   	  if(userStatus==2){
	   	 	bootbox.alert("该用户已处于锁定状态");
	   	 	return;
	   	 }
		var user_id = getCheckStr("ids");
		var url = "userController.do?method=lockUser&user_id="+user_id;
		$.ajax({
			url:url,
			type:"POST",
			dataType:"JSON",
			success: function(data){
  				if(data.success){
  					bootbox.alert({
	  					message:"用户锁定成功！！！",
	  					callback:function(){
	  						pageForm.submit();
	  					}
  					});
				} else{
					bootbox.alert("用户锁定失败！！！");
				}
  			}
  		});
	}
	function page(){
		$("#userForm").submit();
			return false;
    }
</script>	
</body>
</html>