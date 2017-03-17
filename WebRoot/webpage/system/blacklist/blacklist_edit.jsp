<%-- 
 * 文件名称: blacklist_edit.jsp
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
		<form  action="blackController.do?method=save" name="Form" id="Form" method="post" class="form-search">
			<input type="hidden" name="isedit" value="${isedit}"/>
				<div id="zhongxin">
					<div class="row-fluid" >
						<label for="enterprise_name" class=" text-right"><span class="star">*</span>企业名称</label>
						<input type="text" class="input-medium" name="enterprise_name" id="enterprise_name" placeholder="请输入企业名称" value="${blackparam.enterpriseName}" valid="required"/>
					</div>
					<div class="row-fluid">
						<label for="union_bankno" class=" text-right"><span class="star">*</span>关联行号</label>
						<input type="text" class="input-medium" name="union_bankno" id="union_bankno" placeholder="请输入关联行号" value="${blackparam.unionBankno}" valid="required"/>
					</div>
					<input type="hidden" name="create_dt" id="create_dt" value="${blackparam.createDt}"/>
					<input type="hidden" name="create_time" id="create_time" value="${blackparam.createTime}"/>
					<input type="hidden" name="oper_name" id="oper_name" value="${blackparam.operName}"/>
					<div class="row-fluid">
						<label for="oper_brch_no" class=" text-right"><span class="star">*</span>操作柜员机构</label>
						<input type="text" class="input-medium" name="oper_brch_no" id="oper_brch_no" placeholder="请输入操作柜员机构" value="${blackparam.operBranchNo}" valid="required"/>
					</div>
					<div class="row-fluid">
						<label for="description" class=" text-right"><span class="star">*</span>理由</label>
						<textarea class="form-control" name="description" id="description" placeholder="请输入理由" valid="required">${blackparam.description}</textarea>
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
		document.getElementById("union_bankno").readOnly = true;
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
		var id = $("#union_bankno").val();
		var url = "blackController.do?method=checkExists&id="+id;
		$.ajax({
			url:url,
			type:"POST",
			dataType:"JSON",
			success: function(data){
  				if(!data.success){
					showTips("union_bankno", "关联行号已存在");
					$("#union_bankno").focus();
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