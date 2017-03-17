<%-- 
 * 文件名称: dict_edit.jsp
 * 系统名称: 票据管理平台
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: @version2.0.0.1
 * 开发人员: fqz
 * 开发时间: 2016-7-8 上午06:28:22
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
			<form  action="dictController.do?method=save" name="Form" id="Form" method="post" class="form-search">
				<input type="hidden" name="isedit" value="${isedit}"/>
				<div id="zhongxin">
					<div class="row-fluid" >
						<label for="key_no" class="text-right"><span class="star">*</span>分组编号</label>
						<input type="text" class="input-medium" name="key_no" id="key_no" placeholder="输入分组编号" value="${dict.keyNo}"/>
					</div>
					<div class="row-fluid">
						<label for="key_name" class="text-right"><span class="star">*</span>分组名称</label>
						<input type="text" class="input-medium" name="key_name" id="key_name" placeholder="请输入分组名称" value="${dict.keyName}"/>
					</div>
					<div class="row-fluid" >
						<label for="item_code" class="text-right"><span class="star">*</span>分组key</label>
						<input type="text" class="input-medium" name="item_code" id="item_code" placeholder="请输入分组key" value="${dict.itemCode}"/>
					</div>
					<div class="row-fluid">
						<label for="item_value" class="text-right"><span class="star">*</span>分组value</label>
						<input type="text" class="input-medium" name="item_value" id="item_value" placeholder="请输入分组value" value="${dict.itemValue}"/>
					</div>
					<div class="row-fluid" >
						<label for="kernal_flag" class="text-right">核心接口标志</label>
						<input type="text" class="input-medium" name="kernal_flag" id="kernal_flag" placeholder="请输入核心接口标志" value="${dict.kernalFlag}"/>
					</div>
					<div class="row-fluid">
						<label for="belong_type" class="text-right">归属标志</label>
						<input type="text" class="input-medium" name="belong_type" id="belong_type" placeholder="请输入归属标志" value="${dict.belongType}"/>
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
	//保存
	function save(){
		if($("#key_no").val()==""){
	        showTips("key_no", "请输入分组编号");
			$("#key_no").focus();
			return false;
		}
		if($("#key_name").val()==""){
			showTips("key_name", "请输入分组名称");
			$("#key_name").focus();
			return false;
		}
		if($("#item_code").val()==""){
			showTips("item_code", "请输入分组key");
			$("#item_code").focus();
			return false;
		}
		if($("#item_value").val()==""){
			showTips("item_value", "请输入分组value");
			$("#item_value").focus();
			return false;
		}
		if (isedit == '1'){
			$("#Form").submit();
			$("#zhongxin").hide();
			modal("#layer_loading,#image");
		} else {
			checkExist();
		}
	}
	//判断编码是否存在
	function checkExist(){
		var key_no = $("#key_no").val();
		var item_code = $("#item_code").val();
		var url = "dictController.do?method=checkExists&key_no="+key_no+"&item_code="+item_code;
		$.ajax({
			url:url,
			type:"POST",
			dataType:"JSON",
			success: function(data){
  				if(!data.success){
					showTips("key_no", "分组编号已存在");
					showTips("item_code", "分组key已存在");
					$("#key_no").focus();
					$("#item_code").focus();
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
		document.getElementById("key_no").readOnly = true;
		document.getElementById("item_code").readOnly = true;
	}
</script>
</body>
</html>