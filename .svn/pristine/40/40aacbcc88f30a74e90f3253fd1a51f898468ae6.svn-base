<%-- 
 * 文件名称: luru.jsp
 * 系统名称: 票据管理平台
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: @version2.0.0.1
 * 开发人员: dq
 * 开发时间: 2016-7-11 下午04:28:22
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
		<%@ include file="/webpage/system/admin/top.jsp"%>
	</head>
<body>
	<div class="clearfix">
		<div  class="page-content" id="jump-content">
			<form  action="subcollApplyController.do?method=billManage" name="Form" id="Form" method="post" class="form-search">
				<input type="hidden" name="isedit" value="${isedit}"/>
					<input type="hidden" id="issubmit" name="issubmit" value=""/>
				<div id="zhongxin">
					<div class="row-fluid" >
						<label for="EMS" class="text-right"><span class="star">*</span>EMS编号</label>
						<input type="text" class="input-medium" name="EMS" id="EMS" placeholder="请输入EMS" value="${EMS}" valid="required"/>
					</div>
					<div class="row-fluid" >
						<label for="inAcctNo" class="text-right">入账账号</label>
						<input type="text" class="input-medium" name="inAcctNo" id="inAcctNo" value="${query.inAcctNo}"/>
					</div>
					<div class="row-fluid" >
						<label for="inBankNo" class="text-right">入账行号</label>
						<input type="text" class="input-medium" name="inBankNo" id="inBankNo" value="${query.inBankNo}"/>
					</div>
					<div class="row-fluid">
						<label for="fromBankName" class="text-right">持票人行名</label>
						<input type="text" class="input-medium" name="fromBankName" id="fromBankName" value="${query.fromBankName}" />
					</div>
					<div class="row-fluid">
						<label for="fromBankNo" class="text-right">持票人行号</label>
						<input type="text" class="input-medium" name="fromBankNo" id="fromBankNo" value="${query.fromBankNo}" />
					</div>
					<div class="row-fluid">
						<label for="toBankName" class="text-right">托收人行名</label>
						<input type="text" class="input-medium" name="toBankName" id="toBankName" value="${query.toBankName}" />
					</div>
					<div class="row-fluid">
						<label for="toBankNo" class="text-right">托收人行号</label>
						<input type="text" class="input-medium" name="toBankNo" id="toBankNo" value="${query.toBankNo}" />
					</div>
					<div class="row-fluid">
						<label for="toBankAddress" class="text-right">托收人地址</label>
						<input type="text" class="input-medium" name="toBankAddress" id="toBankAddress" value="${query.toBankAddress}" />
					</div>
					<div class="row-fluid">
						<div class="center save">
							<a class="btn-mini" onclick="submit();">确定</a> 
							<a class="btn-mini" onclick="top.Dialog.close();">取消</a>
						</div>
					</div>
				</div>
			</form>
		<form  action="#" name="dataCollectForm" method="post"></form>
	 </div>
</div>			
<%@ include file="/webpage/system/admin/modalDialog.jsp"%>
<%@ include file="/webpage/system/admin/footer.jsp"%> 
<script type="text/javascript">
	//初始化页面控件
	var isedit = '${isedit}';
	if(isedit == '1'){
		document.getElementById("fromBankName").readOnly = true;
		document.getElementById("fromBankNo").readOnly = true;
		document.getElementById("toBankName").readOnly = true;
		document.getElementById("toBankNo").readOnly = true;
		document.getElementById("toBankAddress").readOnly = true;
		var fromBankName = document.getElementById("fromBankName");
	}
	//确定
	function submit(){
		if($("#Form").valid()){
			var ids = "${ids}";
			var billClass= "${query.billClass}";
			var billType= "${query.billType}";
			var startDay= "${query.startDay}";
			var endDay= "${query.endDay}";
			var fromBankName = "${query.fromBankName}";
			var toBankName = "${query.toBankName}";
			var toBankNo = "${query.toBankNo}";
			var EMS = $("#EMS").val();
			var inAcctNo = $("#inAcctNo").val();
			var inBankNo = $("#inBankNo").val();
			var No = /^(\w{0,20}|d*)$/;
		  	if(!No.test(EMS)){
		  		showTips("EMS","EMS最多可输入20位");
				return;
			}
		  	$.ajax({
				url:"<%=basePath%>subcollApplyController.do?method=valueToJump",
				type:"POST",
				dataType:"JSON",
				success: function(data){
	  				if(data.success){
	  					$("#mainFrame",parent.document).contents().find("#jerichotabiframe_lm010908").contents().find("#ids").val(ids);
	  					$("#mainFrame",parent.document).contents().find("#jerichotabiframe_lm010908").contents().find("#billClass").val(billClass);
	  					$("#mainFrame",parent.document).contents().find("#jerichotabiframe_lm010908").contents().find("#billType").val(billType);
	  					$("#mainFrame",parent.document).contents().find("#jerichotabiframe_lm010908").contents().find("#startDay").val(startDay);
	  					$("#mainFrame",parent.document).contents().find("#jerichotabiframe_lm010908").contents().find("#endDay").val(endDay);
	  					$("#mainFrame",parent.document).contents().find("#jerichotabiframe_lm010908").contents().find("#fromBankName").val(fromBankName);
	  					$("#mainFrame",parent.document).contents().find("#jerichotabiframe_lm010908").contents().find("#toBankName").val(toBankName);
	  					$("#mainFrame",parent.document).contents().find("#jerichotabiframe_lm010908").contents().find("#toBankNo").val(toBankNo);
	  					$("#mainFrame",parent.document).contents().find("#jerichotabiframe_lm010908").contents().find("#EMS").val(EMS);
	  					$("#mainFrame",parent.document).contents().find("#jerichotabiframe_lm010908").contents().find("#inAcctNo").val(inAcctNo);
	  					$("#mainFrame",parent.document).contents().find("#jerichotabiframe_lm010908").contents().find("#inBankNo").val(inBankNo);
	  					$("#mainFrame",parent.document).contents().find("#jerichotabiframe_lm010908").contents().find("#issubmit").val("1");
	  					/* localStorage.setItem('subcoll_ids',ids);
	  					localStorage.setItem('subcoll_billClass',billClass);
	  					localStorage.setItem('subcoll_billType',billType);
	  					localStorage.setItem('subcoll_startDay',startDay);
	  					localStorage.setItem('subcoll_endDay',endDay);
	  					localStorage.setItem('subcoll_fromBankName',fromBankName); 
	  					localStorage.setItem('subcoll_toBankName',toBankName); 
	  					localStorage.setItem('subcoll_toBankNo',toBankNo); 
	  					localStorage.setItem('subcoll_EMS',EMS);
	  					localStorage.setItem('subcoll_inAcctNo',inAcctNo);
	  					localStorage.setItem('subcoll_inBankNo',inBankNo); */
						top.Dialog.close();
					} else{
						
					}
	  			}
	  	 	});
		}
	}
</script>
</body>
</html>