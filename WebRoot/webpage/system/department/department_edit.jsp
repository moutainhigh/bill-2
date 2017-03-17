<%-- 
 * 文件名称: department_edit.jsp
 * 系统名称: 票据管理平台
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: @version2.0.0.1
 * 开发人员: cgc
 * 开发时间: 2016-7-7 上午06:28:22
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期    修改人员    修改说明
 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="t" uri="/WEB-INF/tld/poseui.tld"%>
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
			<form  action="deptController.do?method=save" name="Form" id="Form" method="post" class="form-search">
				<input type="hidden" name="isedit" value="${isedit}"/>
				<div id="zhongxin">
					<div class="row-fluid" >
						<label for="dep_code" class="text-right"><span class="star">*</span>部门编号</label>
						<input type="text" class="input-medium" name="dep_code" id="dep_code" placeholder="输入部门编号" value="${deptparam.depCode}" valid="required number"/>
					</div>
					<div class="row-fluid">
						<label for="dep_name" class="text-right"><span class="star">*</span>部门名称</label>
						<input type="text" class="input-medium" name="dep_name" id="dep_name" placeholder="请输入部门名称" value="${deptparam.depName}" valid="required"/>
					</div>
					<div class="row-fluid" >
						<label for="dep_sort" class="text-right"><span class="star">*</span>部门简称</label>
						<input type="text" class="input-medium" name="dep_sort" id="dep_sort" placeholder="请输入部门简称" value="${deptparam.shortName}" valid="required"/>
					</div>
					<div class="row-fluid">
						<label for="dep_remark" class="text-right">备注</label>
						<textarea class="form-control" name="dep_remark" id="dep_remark" placeholder="请输入备注">${deptparam.remark}</textarea>
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
    $(document).ready(function() {  
  		$("#Form").validate({
  			 onfocusout: function(element){
        		$(element).valid();
    		 }
  		});  
	}); 
	var isedit = '${isedit}';
	if(isedit == '1'){
		document.getElementById("dep_code").readOnly = true;
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
		var dep_code = $("#dep_code").val();
		var url = "deptController.do?method=checkExists&dep_code="+dep_code;
		$.ajax({
			url:url,
			type:"POST",
			dataType:"JSON",
			success: function(data){
  				if(!data.success){
					showTips("dep_code", "部门编号已存在"); 
					$("#dep_code").focus(); 
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