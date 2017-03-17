<%-- 
 * 文件名称: tradeBank_edit.jsp
 * 系统名称: 票据管理平台
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: @version2.0.0.1
 * 开发人员: dq
 * 开发时间: 2016-8-1 下午04:28:22
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
			<form  action="tradeBankController.do?method=save" name="Form" id="Form" method="post" class="form-search">
				<input type="hidden" name="isedit" value="${isedit}"/>
				<div id="zhongxin">
					<div class="row-fluid" >
						<label for="childBankno" class="text-right"><span class="star">*</span>分支行行号</label>
						<input type="text" class="input-medium" name="childBankno" id="childBankno" placeholder="请输入12位有效数字" value="${tradeBank.childBankno}" valid="required number" maxLength="12"/>
					</div>
					<div class="row-fluid">
						<label for="childBankname" class="text-right"><span class="star">*</span>分支行行名</label>
						<input type="text" class="input-medium" name="childBankname" id="childBankname" placeholder="请输入分支行行名" value="${tradeBank.childBankname}" valid="required"/>
					</div>
					<div class="row-fluid">
						<label for="parentBankno" class="text-right"><span class="star">*</span>总行行号</label>
							<input type="text" class="input-medium" name="parentBankno" id="parentBankno" placeholder="请输入12位有效数字" value="${tradeBank.parentBankno}" valid="required number" maxLength="12"/>
					</div>
					<div class="row-fluid" >
						<label for="custName" class="text-right"><span class="star">*</span>总行行名</label>
							<input type="text" class="input-medium" name="custName" id="custName" placeholder="请输入总行行名" value="${tradeBank.custName}" valid="required"/>
					</div>
			        <div class="row-fluid" >
						<label for="orgCode" class="text-right"><span class="star">*</span>组织机构代码</label>
							<input type="text" class="input-medium" name="orgCode" id="orgCode" onblur="orgcodevalidate();" placeholder="请输入组织机构代码" value="${tradeBank.orgCode}" valid="required"/>
					</div>
					<div class="row-fluid" >
						<label for="partnerType" class="text-right"><span class="star">*</span>参与者类型</label>
								<t:dictSelect  name="partnerType" other="" className="select-medium" dictGroup="K_BUSSINESS_ROLE" defaultVal="RC00" haveHead="true" valid="required" disabled="true"></t:dictSelect>
								<input type="hidden" name="partnerType" id="partnerType"  value="RC00" />
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
		document.getElementById("childBankno").readOnly = true;
		document.getElementById("parentBankno").readOnly = true;
	}
	$(document).ready(function()
	{
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
	//判断总行行号与分支行行号是否存在
	function checkExist(){
		var parentBankno = $("#parentBankno").val();
		var childBankno = $("#childBankno").val();
		var url = "tradeBankController.do?method=checkExists&parentBankno="+parentBankno+"&childBankno="+childBankno;
		$.ajax({
			url:url,
			type:"POST",
			dataType:"JSON",
			success: function(data){
  				if(data.obj=="childBankno"){
					showTips("childBankno", "分支行行号在交易对手信息表已存在，请重新输入");
					$("#childBankno").focus();
				}else if(data.obj=="parentBankno"){
				 	showTips("parentBankno", "总行行号不存在，请重新输入");
					$("#parentBankno").focus();
				}else if(data.obj=="pChildBankno"){
					showTips("childBankno", "分支行行号在联行信息表不存在，请重新输入");
					$("#childBankno").focus();
				}else{
					modal("#layer_loading,#image");
				 	$("#Form").submit(); 
					$("#zhongxin").hide();
				} 
  			 }
  		});
	} 
	//判断组织机构代码是否规范
	function orgcodevalidate(){
		var str=$("#orgCode").val();
	    if(str!=""){
 			var reg = /^[A-Z0-9]{8}-[A-Z0-9]$/;   
 			if (reg.test(str)) {  
 				return true ; 
 			}else{
 				$("#orgCode").tips({
					side : 3,
					msg :"组织机构代码输入不规范", 
					bg : '#AE81FF',
					time : 5
 				});
 			}
 		}
    }
</script>
</body>
</html>