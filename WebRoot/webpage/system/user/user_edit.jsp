<%-- 
 * 文件名称: user_edit.jsp
 * 系统名称: 票据管理平台
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: @version2.0.0.1
 * 开发人员: superCheng
 * 开发时间: 2016-7-12 上午08:28:22
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期    修改人员    修改说明
 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
</head>
<body>
	<div class="clearfix">
		<div class="page-content" id="jump-content">
			<form action="userController.do?method=save" name="Form" id="Form" method="post" class="form-search">
				<input type="hidden" name="isedit" value="${isedit}" />
				<div id="zhongxin">
					<div class="row-fluid">
							<label for="user_id" class="text-right"><span class="star">*</span>用户编号</label>
							<input type="text" class="input-medium" valid="required" name="userId" id="userId" placeholder="输入用户编号" value="${sysparam.userId}" />
							<label for="branch_no" class="text-right"><span class="star">*</span>所属机构</label>
							<sys:treeselect className="input-medium" id="branchNo" name="branchNo"
								value="${sysparam.branchNo}" module="2"
								isedit="0" labelName="branchName" labelValue="${sysparam.branchName}"
								title="机构" url="tagController.do?method=treeData"
								extId="${sysparam.branchNo}" 
								valid="required"/>
					</div>
					<div class="row-fluid">
							<label for="dep_code" class="text-right"><span class="star">*</span>所属部门</label>
							<sys:treeselect className="input-medium" id="depCode" name="depCode" value="${sysparam.depCode}" 
								isedit="0" module="0" 
								labelName="depName" labelValue="${sysparam.depCode}"
					            title="部门" url="tagController.do?method=treeData" extId="${menu.menuCode}"  
					            subnode="dep_code"  pnode = "parent_code" nodename="dep_name" sourcename="tdept" valid="required"/>
							<label for="user_name" class="text-right"><span class="star">*</span>用户名称</label>
							<input type="text" class="input-medium" valid="required" name="userName" id="userName" placeholder="请输入用户名称" value="${sysparam.userName}" />
						</div>
					</div>
					<div class="row-fluid">
							<label for="user_email" class="text-right"><span class="star">*</span>用户邮箱</label>
							<input class="input-medium" type="email" name="userEmail" valid="email required maxlength:'64' " id="userEmail" placeholder="请输入用户邮箱" value="${sysparam.userEmail}" />
							<label for="user_phone" class="text-right"><span class="star">*</span>用户电话</label>
							<input type="text" class="input-medium" name="userPhone" id="userPhone" placeholder="请输入用户电话" value="${sysparam.userPhone}" valid="phone required" />
					</div>
					<div class="row-fluid">
							<label for="remark1" class="text-right">备注1</label>
							<input type="text" class="input-medium" name="remark1" id="remark1" placeholder="请输入备注1" value="${sysparam.remark1}" />
							<label for="remark2" class="text-right">备注2</label>
							<input type="text" class="input-medium" name="remark2" id="remark2" placeholder="请输入备注2" value="${sysparam.remark2}" />
					</div>
					<div class="row-fluid">
							<label for="remark3" class="text-right">备注3</label>
							<input type="text" class="input-medium" name="remark3" id="remark3" placeholder="请输入备注3" value="${sysparam.remark3}" />
					</div>
					<div class="row-fluid save">
						<div class="center">
							<a class="btn-mini" onclick="save();">保存</a> <a
								class="btn-mini" onclick="top.Dialog.close();">取消</a>
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
	if (isedit == '1') {
		document.getElementById("userId").readOnly = true;
	}
	//保存
	function save() {
		var useremail= $("#userEmail").val();
		if(useremail.toString().length>64){
			showTips("userEmail", "输入的邮箱长度太长,请输入正确的邮箱");
			$("#userEmail").focus();
			return false;
		}
		if ($("#Form").valid()) {
			if (isedit == '1') {
				$("#Form").submit();
				$("#zhongxin").hide();
				modal("#layer_loading,#image");
			} else {
				checkExist();
			}
		}
	}
	//判断员工号是否存在
	function checkExist() {
		var user_id = $("#userId").val();
		var url = "userController.do?method=checkExists&user_id=" + user_id;
		$.ajax({
			url : url,
			type : "GET",
			dataType : "JSON",
			success : function(data) {
				if (!data.success) {
					showTips("user_id", "员工编号已存在");
					alter("员工编号已存在");
					$("#user_id").focus();

				} else {
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