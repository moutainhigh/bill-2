<%-- 
 * 文件名称: custManager_edit.jsp
 * 系统名称: 票据管理平台
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: @version2.0.0.1
 * 开发人员: fanqq
 * 开发时间: 2016-7-12 上午09:28:22
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
			<form  action="custManagerController.do?method=save" name="Form" id="Form" method="post" class="form-search">
				<input type="hidden" name="isedit" value="${isedit}"/>
				<input type="hidden" name="branchNo1" value="${custmanage.branchNo}"/>
				<div id="zhongxin">
					<div class="row-fluid" >
						<label for="custManagerNo" class="text-right"><span class="star">*</span>经理编号</label>
						<input type="text" class="input-medium" name="custManagerNo" id="custManagerNo" placeholder="请输入经理编号" value="${custmanager.custManagerNo}" valid="required" />
					</div>
					<div class="row-fluid">
						<label for="custManagerName" class="text-right"><span class="star">*</span>经理名称</label>
						<input type="text" class="input-medium" name="custManagerName" id="custManagerName" placeholder="请输入经理名称" value="${custmanager.custManagerName}" valid="required" />
					</div>
					<div class="row-fluid">
						<label for="status" class="text-right"><span class="star">*</span>是否启用</label>
						<t:dictSelect  className="select-medium"  name="status" other="" dictGroup="K_YORN" defaultVal="${custmanager.status}" haveHead="true" valid="required">
						</t:dictSelect>
					</div>
					<div class="row-fluid">
						<label for="branchNo" class="text-right"><span class="star">*</span>机构所属</label>
						<sys:treeselect className="input-medium" id="branchNo" name="branchNo" value="${custmanager.branchNo}" isedit="0" module="2"
							labelName="custmanage.branchNo" labelValue="${branchName}" valid="required" 
							title="机构" url="tagController.do?method=treeData" extId="${menu.menuCode}" />
					</div>
					<div class="row-fluid">
						<label for="deptNo" class="text-right"><span class="star">*</span>归属部门编号</label>
						<sys:treeselect className="input-medium" id="deptNo" name="deptNo" isedit="0" value="${custmanager.deptNo}" module="0" labelName="custmanager.deptNo" labelValue="${custmanager.deptName}"
							title="部门" valid="required"  url="tagController.do?method=treeData" extId="${menu.menuCode}"  subnode="dep_code"  pnode = "parent_code" nodename="dep_name" sourcename="tdept"/>
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
		document.getElementById("custManagerNo").readOnly = true;
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
	//判断编码是否存在
	function checkExist(){
		var custManagerNo = $("#custManagerNo").val();
		var url = "custManagerController.do?method=checkExists&custManagerNo="+custManagerNo;
		$.ajax({
			url:url,
			type:"POST",
			dataType:"JSON",
			success: function(data){
  				if(!data.success){
					showTips("custManagerNo", "客户经理已存在");
					$("#custManagerNo").focus();
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