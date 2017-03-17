<%-- 
 * 文件名称: branch_edit.jsp
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
			<form  action="branchController.do?method=save" name="Form" id="Form" method="post" class="form-search">
				<input type="hidden" name="isedit" value="${isedit}"/>
				<div id="zhongxin">
					<div class="row-fluid" >
							<label for="branchNo" class="text-right"><span class="star">*</span>机构编号</label>
							<input type="text" class="input-medium" name="branchNo" id="branchNo" value="${branch.branchNo}" placeholder="请输入机构编号" valid="required"/>
							<label for="left_three_branchNo" class="text-right"><span class="star">*</span>机构名称</label>
							<input type="text" class="input-medium" name="branchName" id="branchName" value="${branch.branchName}" placeholder="请输入机构名称" valid="required"/>
					</div>
					<div class="row-fluid" >
							<label for="shortName" class="text-right"><span class="star">*</span>机构简称</label>
							<input type="text" class="input-medium" name="shortName" id="shortName" value="${branch.shortName}" placeholder="请输入机构简称" valid="required"/>
							<label for="branchPath" class="text-right"><span class="star">*</span>组织机构代码</label>
							<input type="text" class="input-medium" name="orgCode" id="orgCode" placeholder="请输入组织机构代码" value="${branch.orgCode}" valid="required"/>
					</div>
					<div class="row-fluid" >
						<div class="parentName">
							<label for="parentBrchCode" class="text-right">上级机构</label>
							<sys:treeselect className="input-medium" id="branch" name="parentBrchCode" isedit="${edit}" value="${branch.parentBrchCode}" module="2" labelName="parent.branchName" labelValue="${parentBranchName}"
								title="机构" placeholder="请输入上级机构" url="tagController.do?method=treeData" extId="${branch.branchNo}"  valid="required"/>
							<label for="payBankNo" class="text-right"><span class="star">*</span>联行行号</label>
							<input type="text" class="input-medium" name="payBankNo" id="payBankNo" placeholder="请输入联行行号" value="${branch.payBankNo}" valid="required"/>
						</div>
					</div>
					<div class="row-fluid">
							<label for="elec_auth" class="text-right"><span class="star">*</span>票据权限</label>
							<t:dictSelect className="select-medium" name="elecAuth" dictGroup="K_YORN" defaultVal="${branch.elecAuth}" haveHead="true" other="" valid="required">
							</t:dictSelect>
							<label for="if_effective" class="text-right"><span class="star">*</span>是否生效</label>
							<t:dictSelect className="select-medium" name="ifEffective" dictGroup="K_YORN" defaultVal="${branch.ifEffective}" haveHead="true" other="" valid="required">
							</t:dictSelect>
					</div>
					<div class="row-fluid" >
							<label for="remark1" class="text-right">备注1</label>
							<input type="text" class="input-medium" name="remark1" id="remark1" placeholder="情输入备注1" value="${branch.remark1}"/>
							<label for="remark2" class="text-right">备注2</label>
							<input type="text" class="input-medium" name="remark2" id="remark2" placeholder="情输入备注2" value="${branch.remark2}"/>
					</div>
					<div class="row-fluid">
							<label for="remark3" class="text-right">备注3</label>
							<input type="text" class="input-medium" name="remark3" id="remark3" placeholder="情输入备注3" value="${branch.remark3}"/>
					</div>
					<div class="row-fluid">
						<div class="center save">
							<input type="hidden" name="branchLevel" value="${branch.branchLevel}"/>
							<input type="hidden" name="parentName" id="parentName" value="${parentName}"/>
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
	//保存
	function save(){
		if($("#Form").valid()){
			if ("${isedit}" == '1'){
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
		var url = "branchController.do?method=checkExists&branchNo="+$("#branchNo").val();
		$.ajax({
			url:url,
			type:"POST",
			dataType:"JSON",
			success: function(data){
  				if(!data.success){
					showTips("branchNo", "机构编号已存在");
					$("#branchNo").focus();
				} else{
					modal("#layer_loading,#image");
					$("#Form").submit();
					$("#zhongxin").hide();
				}
  			}
  		});
	}
	//没有上级结构不显示上级机构
    var parentName = '${parentName}';
	if(parentName == "1"){
		$(".parentName").hide();
	}
	$(document).ready(function() { 
		var isedit = '${isedit}';
		if(isedit == '1'){
			document.getElementById("branchNo").readOnly = true;
		}
	});
	//校验组织机构代码
	$("#orgCode").blur(function(){
		orgcodevalidate();
	})
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