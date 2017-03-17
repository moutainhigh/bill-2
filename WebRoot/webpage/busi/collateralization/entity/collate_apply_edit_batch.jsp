<%-- 
 * 文件名称: editBatch.jsp
 * 系统名称: 票据管理平台
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: @version2.0.0.1
 * 开发人员: v
 * 开发时间: 2016-8-10
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期    修改人员    修改说明
 --%>
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
		<%@ include file="/webpage/system/admin/footer.jsp"%> 
	</head>
<body>
	<div class="clearfix">
		<div class="page-content" id="jump-content">
			<form action="collateralizationApplyController.do?method=save" method="post" name="userForm" id="userForm" class="form-search" role="form">
				<input type="hidden" name="isedit" value="${isedit}"/>
				<input type="hidden" name="saveId" value="${saveApplyInfo.saveId}"/>
		    	<input type="hidden" name="batchNo" value="${saveApplyInfo.batchNo}"/>
				<div id="zhongxin">
	    		<fieldset class="field">
					<legend>客户信息</legend>
					<div class="row-fluid">
						<label for="param_value" class="pdTop"><span class="star">*</span>客户号</label>
						<input type="text" class="input-medium" name="custNo" value="${saveApplyInfo.custNo}" placeholder="请输入客户账户" id="custNo"/>
						<label for="param_value" class="pdTop"><span class="star">*</span>客户名称</label>
						<input type="text" class="input-medium" name="custName" value="${saveApplyInfo.custName}" placeholder="请输入客户名称" id="custName" valid="required"/>
					</div>
				</fieldset>
				<fieldset class="field">
					<legend>客户经理信息</legend>
					<div class="row-fluid">
						<label for="param_value" class="pdTop"><span class="star">*</span>客户经理编号</label>
						<input type="text" class="input-medium" name="custManager" value="${saveApplyInfo.custManager}" placeholder="请输入客户经理编号" id="custManagerNo" valid="required"/>
						<label for="param_value" class="pdTop"><span class="star">*</span>客户经理名称</label>
						<input type="text" class="input-medium" readonly="true" name="custManagerName" value="${saveApplyInfo.custManagerName}" placeholder="请输入客户经理名称" id="custManagerName" valid="required"/>
					</div>
					<div class="row-fluid">
						<label for="param_value" class="pdTop"><span class="star">*</span>所属部门</label>
						<input type="text" class="input-medium" readonly="true" name="deptName" value="${saveApplyInfo.deptName}" placeholder="请输入所属部门" id="deptName" valid="required"/>
					</div>
				</fieldset>
				<fieldset class="field">
					<legend>其他信息</legend>
					<div class="row-fluid">
						<label for="param_value" class="pdTop"><span class="star">*</span>批次种类</label>
						<t:dictSelect name="batchClass" className="select-medium" other="" disabled="true" dictGroup="K_BILL_CLASS" defaultVal="${saveApplyInfo.batchClass}" haveHead="true" valid="required" >
						</t:dictSelect> 
						<label for="param_value" class="pdTop"><span class="star">*</span>批次类型</label>
						<t:dictSelect name="batchType" className="select-medium" other="" dictGroup="K_BILL_TYPE" defaultVal="${saveApplyInfo.batchType}" haveHead="true" valid="required" >
						</t:dictSelect> 
					</div>
					<div class="row-fluid">
						<label for="param_value" class="pdTop"><span class="star">*</span>产品名称</label>
						<t:select id="prodNo" name="prodNo" className="select-medium" other="" list="prodList" listKey="prodName" listValue="prodNo" defaultVal="${saveApplyInfo.prodNo}">
						</t:select>
					</div>
					</fieldset>
				<div class="row-fluid center save">
					<a class="btn-mini" href="javascript:saveSub()">保存</a>
					<a class="btn-mini" onclick="top.Dialog.close();">取消</a>
				</div>
		    </form>
    	</div>
    </div>
<%@ include file="/webpage/system/admin/modalDialog.jsp"%>
<script type="text/javascript">
	/* function close(){
		$("#mainFrame",parent.document).contents().find("#jerichotabiframe_lm010705").contents().find("#abc").val("123");
		//alert($("#mainFrame",parent.document).contents().find("#jerichotabiframe_4").contents().find("#abc").val());
		top.Dialog.close();
	} */
	rfill();
	//自动填充客户信息
	function rfill(){ 
		var custNo = $('#custNo').val();
		$.ajax({
			type: "POST",
			url: "<%=basePath%>collateralizationApplyController.do?method=custInfo",
	    	data: {'custNo': custNo},
			dataType:'json',
			cache: false,
			success: function(data){	
				if(data.success){  //处理成功
					$("#custName").val(data.attributes.custName);
				}else{
					$("#custName").val("");
					bootbox.alert(data.msg);
				}
			}
		});
	}
	//保存
	function saveSub(){
     	if($("#userForm").valid()){ 
     	    var isedit='${isedit}';
			if (isedit == '1'){
				$("#userForm").submit();
				$("#zhongxin").hide();
				//$("#zhongxin2").show();
			} else {
				modal("#layer_loading,#image");//点击票据管理按钮，弹出‘加载中’的遮罩层
		        $("#userForm").submit();
			}
 	  	}
	}	
	//根据客户经理编号查询信息并填充
	$("#custManagerNo").blur(function(){
		fill();
	})
	function fill(){ 
		var custManagerNo = $('#custManagerNo').val();
		$.ajax({
			type: "POST",
			url: "<%=basePath%>custManagerController.do?method=custMagInfo",
	    	data: {'custManagerNo': custManagerNo},
			dataType:'json',
			beforeSend:function(){
				if(custManagerNo==null||custManagerNo==""){
					showTips("custManagerNo","请输入客户经理编号");
					$("#deptName").val("");
					$("#custManagerName").val("");
			   		return false;
				}
			},
			cache: false,
			success: function(data){	
				if(data.success){  //处理成功
					$("#deptName").val(data.attributes.deptName);
					$("#custManagerName").val(data.attributes.custManagerName);
				}else{
					$("#deptName").val("");
					$("#custManagerName").val("");
					bootbox.alert(data.msg);
				}
			}
		});
	}
		document.getElementById("custNo").readOnly = true;
		document.getElementById("custName").readOnly = true;
		var totalNum = "${saveApplyInfo.totalNum}";
		if(totalNum!=0){
		document.getElementById("custManagerNo").readOnly = true;
		document.getElementById("batchType").disabled = true;
	}
</script>
</body>
</html>