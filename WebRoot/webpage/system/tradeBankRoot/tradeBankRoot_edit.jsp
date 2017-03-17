<%-- 
 * 文件名称: tradeBankRoot_edit.jsp
 * 系统名称: 票据管理平台
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: @version2.0.0.1
 * 开发人员: dq
 * 开发时间: 2016-8-2 下午04:28:22
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
			<form  action="tradeBankRootController.do?method=save" name="Form" id="Form" method="post" class="form-search">
				<input type="hidden" name="isedit" value="${isedit}"/>
				<div id="zhongxin">
					<div class="row-fluid">
						<label for="bankName" class="text-right"><span class="star">*</span>行名</label>
						<input type="text" class="input-medium" name="bankName" id="bankName" placeholder="请输入行名" value="${tradeBankRoot.bankName}" valid="required"/>
					</div>
					<div class="row-fluid">
						<label for="bankNo" class="text-right"><span class="star">*</span>行号</label>
						<input type="text" class="input-medium" name="bankNo" id="bankNo" placeholder="请输入12位有效数字" value="${tradeBankRoot.bankNo}" valid="required number"/>
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
		document.getElementById("bankNo").readOnly = true;
	}
	$(document).ready(function(){
		$("#Form").validate({
			onfocusout:
			function(element){
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
	//判断行号是否存在
	function checkExist(){
		var bankNo = $("#bankNo").val();
		var url = "tradeBankRootController.do?method=checkExists&bankNo="+bankNo;
		$.ajax({
			url:url,
			type:"POST",
			dataType:"JSON",
			success: function(data){
  				if(data.obj=="pBankNo"){
					showTips("bankNo", "行号在联行信息表中不存在，请从新输入");
					$("#bankNo").focus();
				}else if(data.obj=="bankNo"){
					showTips("bankNo", "行号在交易对手总行对照表中已存在，请从新输入");
					$("#bankNo").focus();
				}else{
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