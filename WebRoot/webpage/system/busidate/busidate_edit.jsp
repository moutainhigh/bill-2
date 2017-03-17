<%-- 
 * 文件名称: busidate_edit.jsp
 * 系统名称: 票据管理平台
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: @version2.0.0.1
 * 开发人员: yanjl
 * 开发时间: 2016-7-7 上午06:28:22
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
			<form  action="busiDateController.do?method=save" name="Form" id="Form" method="post" class="form-search">
				<input type="hidden" name="isedit" value="${isedit}"/>
				<div id="zhongxin">
					<div class="row-fluid">
						<label for="workday" class="text-right">系统日期</label>
						<input class="input-medium input-date" name="workday" id="workday" value="${busidate.workday}" type="text" placeholder="系统日期" valid="required dateISO" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"/>
					</div>
					<div class="row-fluid">
						<label for="host_check_date" class="text-right">核心对账日期</label>
						<input class="input-medium input-date" name="host_check_date" id="host_check_date" value="${busidate.hostCheckDate}" type="text" placeholder="核心对账日期" valid="required dateISO" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"/>
					</div>
					<div class="row-fluid">
					    <label for="sys_status" class="text-right">系统状态</label>
			            <t:dictSelect className="select-medium" name="sys_status" other="" dictGroup="K_SYSSTATUS" defaultVal="${busidate.sysStatus}" haveHead="true">
						</t:dictSelect>     
					</div>
					<div class="row-fluid" >
						<input type="hidden" class="input-medium" name="sys_bank_no" id="sys_bank_no" placeholder="请输入法人行编号" value="${busidate.sysBankNo}"/>		 
					</div> 
					<div class="row-fluid center">
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
		document.getElementById("sys_bank_no").readOnly = true;
	}
	//保存
	function save(){
		if($("#sys_bank_no").val()==""){
	        showTips("sys_bank_no", "输入法人行编号");
			$("#sys_bank_no").focus();
			return false;
		}
		if($("#workday").val()==""){
	        showTips("workday", "输入系统时间");
			$("#workday").focus();
			return false;
		}
		if($("#host_check_date").val()==""){
			showTips("select2-host_check_date-container", "请输入核心对账时间");
			$("#host_check_date").focus();
			return false;
		}
		if($("#sys_status").val()==""){
			showTips("select2-sys_status-container", "请输入系统标识");
			$("#sys_status").focus();
			return false;
		}
		else{
			if (isedit == '1'){
				Form.submit();
				$("#zhongxin").hide();
				modal("#layer_loading,#image");
			} else {
				checkExist();
			}
	  }	
	}
</script>
</body>
</html>