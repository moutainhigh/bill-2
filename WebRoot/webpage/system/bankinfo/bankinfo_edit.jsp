<%-- 
 * 文件名称: bankinfo_edit.jsp
 * 系统名称: 票据管理平台
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: @version2.0.0.1
 * 开发人员: fqz
 * 开发时间: 2016-7-12 上午06:28:22
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
			<form  action="bankInfoController.do?method=save" name="Form" id="Form" method="post" class="form-search">
				<input type="hidden" name="isedit" value="${isedit}"/>
				<div id="zhongxin">
					<div class="row-fluid" >
						<label for="bank_no" class="text-right">银行行号</label>
						<input type="text" class="input-medium" name="bank_no" id="bank_no" placeholder="输入行号" value="${bankinfo.bankNo}" onkeyup="document.getElementById('left_three_bank_no').value=this.value.substring(0,3)" title="行号"/>
					</div>
					<div class="row-fluid">
						<label for="left_three_bank_no" class="text-right">银行名称</label>
						<input type="text" class="input-medium" name="bank_name" id="bank_name" placeholder="请输入银行名称" value="${bankinfo.bankName}" title="银行名称"/>
					</div>
					<div class="row-fluid" >
						<label for="left_three_bank_no" class="text-right">前三位行号</label>
						<input type="text" class="input-medium" name="left_three_bank_no" id="left_three_bank_no" value="${bankinfo.leftThreeBankNo}" readOnly=true title="前三位行号"/>
					</div>
					<div class="row-fluid save">
						<div class="center">
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
		//document.getElementById("id").readOnly = true;
		document.getElementById("bank_no").readOnly = true;
		document.getElementById("left_three_bank_no").readOnly = true;
		modal("#layer_loading,#image");//点击票据管理按钮，弹出‘加载中’的遮罩层
	}
	$(top.hangge());
	//保存
	function save(){
		if($("#bank_no").val()==""){
	        showTips("bank_no", "请输入行号");
			$("#bank_no").focus();
			return false;
		}
		if($("#bank_name").val()==""){
			showTips("bank_name", "请输入银行名称");
			$("#bank_name").focus();
			return false;
		}
		if($("#left_three_bank_no").val()==""){
			showTips("left_three_bank_no", "前3位行号不能为空");
			$("#left_three_bank_no").focus();
			return false;
		}
		if (isedit == '1'){
			$("#Form").submit();
			$("#zhongxin").hide();
			$("#zhongxin2").show();
		} else {
			checkExist();
		}
	}
	//判断编码是否存在
	function checkExist(){
		var left_three_bank_no = $("#left_three_bank_no").val();
		var url = "bankInfoController.do?method=checkExists&left_three_bank_no="+left_three_bank_no;
		$.ajax({
			url:url,
			type:"POST",
			dataType:"JSON",
			success: function(data){
  				if(!data.success){
					showTips("left_three_bank_no", "前三位行号已存在");
					$("#left_three_bank_no").focus();
				} else{
					$("#Form").submit();
					$("#zhongxin").hide();
				}
  			 }
  		});
	}
</script>
</body>
</html>