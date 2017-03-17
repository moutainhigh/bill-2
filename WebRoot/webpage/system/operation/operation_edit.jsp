<%-- 
 * 文件名称: operation_edit.jsp
 * 系统名称: 票据管理平台
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: @version2.0.0.1
 * 开发人员: fanqq
 * 开发时间: 2016-7-21 上午09:28:22
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
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<%@ include file="../admin/top.jsp"%> 
	</head>
<body>
	<div class="clearfix">
		<div  class="page-content" id="jump-content">
			<form  action="operationController.do?method=save" name="Form" id="Form" method="post" class="form-search">
				<input type="hidden" name="isedit" value="${isedit}"/>
				<div id="zhongxin">
					<div class="row-fluid" >
						<label for="operationCode" class="text-right"><span class="star">*</span>功能编号</label>
						<input type="text"  class="input-medium" name="operationCode" id="operationCode" placeholder="请输入功能编号" valid="required" value="${operation.operationCode}" />
					</div>
					<div class="row-fluid">
						<label for="operationName" class="text-right"><span class="star">*</span>功能名称</label>
						<input type="text"  class="input-medium" name="operationName" id="operationName" placeholder="请输入功能名称" valid="required" value="${operation.operationName}" />
					</div>
				    <div class="row-fluid">
						<label for="menuCode" class="text-right"><span class="star">*</span>菜单编号</label>
						<sys:treeselect className="input-medium" id="menuCode" name="menuCode"  value="${operation.menuCode}" module="1" labelName="operation.menuCode" labelValue="${operation.menuCode}"
							title="菜单" url="tagController.do?method=treeData" extId="${menu.menuCode}"  valid="required"/>
					</div>
					<div class="row-fluid">
						<div class="center save">
							<a class="btn-mini" onclick="save();">保存</a>
							<a class="btn-mini" onclick="top.Dialog.close();">取消</a>
						</div>
					</div>
				</div>
			</form>
		 </div>
	</div>	
<%@ include file="/webpage/system/admin/modalDialog.jsp"%>
<%@ include file="../admin/footer.jsp"%> 
<script type="text/javascript">
	var isedit = '${isedit}';
	if(isedit == '1'){
		document.getElementById("operationCode").readOnly = true;
		document.getElementById("menuCode").isedit = "1";
	}
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
		var operationCode = $("#operationCode").val();
		var url = "operationController.do?method=checkExists&operationCode="+operationCode ;
		$.ajax({
			url:url,
			type:"POST",
			dataType:"JSON",
			success: function(data){
  				if(!data.success){
					showTips("operationCode", "功能菜单已存在");
					$("#operationCode").focus();
				} else{
					modal("#layer_loading,#image");
					$("#Form").submit();
					$("#zhongxin").hide();
				}
  			}
  		});
	}
</script>		
</body>
</html>