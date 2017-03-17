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
		<div class="page-content" id="jump-content">
			<form  action="roleController.do?method=save" name="Form" id="Form" method="post" class="form-search">
				<input class="input-medium" type="hidden" name="isedit" value="${isedit}"/>
				<div id="zhongxin">
					<div class="row-fluid" >
						<label for="roleCode"><span class="star">*</span>角色编码</label>
						<input class="input-medium" type="text" name="roleCode" id="roleCode" placeholder="请输入角色编码" value="${role.roleCode}" valid="required"/>
					</div>
					<div class="row-fluid" >
						<label for="roleName"><span class="star">*</span>角色名称</label>
						<input class="input-medium" type="text" name="roleName" id="roleName" placeholder="请输入角色名称" value="${role.roleName}" valid="required"/>
					</div>
					<div class="row-fluid" >
						<label for="roleStatus"><span class="star">*</span>角色状态</label>
						<t:dictSelect className="select-medium" valid="required" other="" name="roleStatus" dictGroup="K_ROLE_STATUS" defaultVal="${role.roleStatus}" haveHead="true">
						</t:dictSelect>
					</div>
					<div class="row-fluid">
						<div class="center save">
							<input class="input-medium" type="hidden" name="menuLevel" id="menuLevel" value="${menu.menuLevel}"/>
							<input class="input-medium" type="hidden" name="treeIdx" id="treeIdx" value="${menu.treeIdx}"/>
							<a class="btn-mini" onclick="save();">保存</a>
							<a class="btn-mini" onclick="top.Dialog.close();">取消</a>
						</div>
					</div>
				</div>
				<div id="zhongxin2" class="center" style="display:none"><br/><br/><br/><img src="static/images/jzx.gif" style="width: 50px;" /><br/><h4 class="lighter block green"></h4></div>
			</form>
		</div>
	</div>
<%@ include file="/webpage/system/admin/modalDialog.jsp"%>
<%@ include file="../admin/footer.jsp"%> 
<script type="text/javascript">
	$(document).ready(function() {  
  		$("#Form").validate({
  			 onfocusout: function(element){
        		$(element).valid();
    		 }
  		});
	}); 
	//保存
	function save(){
		if($("#Form").valid()){
			if (isedit == '1'){
				$("#Form").submit();
				$("#zhongxin").hide();
				modal("#layer_loading,#image");
			} else {
				checkExist();
			}
		}
	}
	//判断编码是否存在
	function checkExist(){
		var roleCode = $("#roleCode").val();
		var url = "roleController.do?method=checkExists&roleCode="+roleCode;
		$.ajax({
			url:url,
			type:"POST",
			dataType:"JSON",
			async:false,
			success: function(data){
  				if(!data.success){
					showTips("roleCode", "角色编码已存在");
					$("#roleCode").focus();
				} else{
					modal("#layer_loading,#image");
					$("#Form").submit();
					$("#zhongxin").hide();
				}
  			}
  		});
	}
	var isedit = '${isedit}';
	if(isedit == '1'){
		document.getElementById("roleCode").readOnly = true;
	}
</script>
</body>
</html>