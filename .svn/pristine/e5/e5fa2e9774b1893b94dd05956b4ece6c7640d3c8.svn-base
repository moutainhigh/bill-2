<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/webpage/system/admin/taglib.jsp"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
	<base href="<%=basePath%>">
	<%-- jsp文件头和头部 --%>
	<%@ include file="/webpage/system/admin/top.jsp"%>
</head>
<body style="background:#f4f8fb;">
	<div class="clearfix">
		<div class="page-content" id="jump-content">
			<form action="<%=basePath%>auditRouteController.do?method=saveAuditRoute" method="post" name="Form" id="Form" class="form-search" role="form">
				<input type="hidden" name="id" value="${auditRoute.id}"/>
				<input type="hidden" name="isEdit" value="${isEdit}"/>
				<div class="row-fluid" >
				 	<label for="arName" class="pdTop"><span class="star">*</span>审批路线名称</label>
					<input type="text" class="input-medium" id="arName"  valid="required" value="${auditRoute.arName}" name="arName" placeholder="请选择审批路线名称"/>
				</div>
				<div class="row-fluid" >	
					<label for="anExecMode" class="pdTop"><span class="star">*</span>审批岗位执行模式</label>
					<t:dictSelect className="select-medium" name="anExecMode" valid="required" other="" defaultVal="${auditRoute.anExecMode}" dictGroup="K_EXEC_MODE"></t:dictSelect>
				</div>
				<div class="row-fluid">
					<label for="prodNo" class="pdTop"><span class="star">*</span>指定使用产品</label>
					<sys:treeselect className="input-medium" id="prodNo" name="prodNo" value="${auditRoute.prodNo}" module="0" labelName="auditRoute.prodName" labelValue="${auditRoute.prodNo}"
						title="产品" url="tagController.do?method=treeData" extId="${menu.menuCode}"  subnode="prod_no"  pnode = "parent_prod_no" nodename="prod_name" sourcename="tproduct"/>
				</div>
				<div class="row-fluid">
					<label for="auditStartBrchLvl" class="pdTop">审批起始机构级别</label>
					<select name="auditStartBrchLvl" id="auditStartBrchLvl" class="select2 select-medium" valid="required" value="${auditRoute.auditStartBrchLvl}">
						<option value="0" id="">请选择</option>
						<option value="1">总行</option>
						<option value="2">分行</option>
						<option value="3">支行</option>
					</select>
				</div>
				<div class="row-fluid">
				 	<label for="pubFlag" class="pdTop"><span class="star">*</span>审批路线使用模式</label>
					<select class="select2 select-medium"  id="pubFlag" name="pubFlag" valid="required" value="${auditRoute.pubFlag}" onchange="showBindTr();">
						<option value="0" id="">请选择</option>
						<option value="1">全行通用</option>
						<option value="2">指定机构使用</option>
					</select>
				</div>
				<div class="row-fluid " id="bindBranchNoTr">
					<label for="bindBranchNo" class="pdTop">指定使用机构</label>
					<sys:treeselect className="input-medium" id="bindBranchNo" name="bindBranchNo"  isedit="0" value="${auditRoute.bindBranchNo}" module="2" labelName="机构名称"  labelValue="${auditRoute.bindBranchNo}" title="机构" url="tagController.do?method=treeData"   valid="required"></sys:treeselect>
				</div>
				<div class="row-fluid">
					<label for="arRemark" class="pdTop">审批路线描述</label>
					<input type="text" class="input-medium" id="arRemark" valid="required" value="${auditRoute.arRemark}" name="arRemark" placeholder="请选择审批路线描述"/>
				</div>
				<div class="row-fluid">
					<div class="center save">
						<a class="btn-mini" onclick="save();">保存</a>
						<a class="btn-mini" onclick="top.Dialog.close();">取消</a>
					</div>
				</div>
		    </form>
		</div>
    </div>
<%@ include file="/webpage/system/admin/modalDialog.jsp"%>
<%@ include file="/webpage/system/admin/footer.jsp"%> 
<script type="text/javascript">
	$(function (){
		$('#bindBranchNoTr').hide();
	});
	function showBindTr(){
		var pubFlag = $('#pubFlag').val();
		if(pubFlag=="2"){
			$('#bindBranchNoTr').show();
		}else{
			$('#bindBranchNoTr').hide();
		}
	}
	var isEdit=${isEdit};
	function save(){
		if($("#Form").valid()){
	        Form.submit();
	    }
	}
	if(isEdit=='1'){
		var auditStartBrchLvl='${auditRoute.auditStartBrchLvl}';
		var pubFlag='${auditRoute.pubFlag}';
		if(pubFlag=="2"){
			$('#bindBranchNoTr').show();
		}else{
			$('#bindBranchNoTr').hide();
		}
		$("#auditStartBrchLvl").select2().select2("val", auditStartBrchLvl);
		$("#pubFlag").select2().select2("val", pubFlag);
	}
</script>
</body>
</html>