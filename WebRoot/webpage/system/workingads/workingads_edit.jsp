<%-- 
 * 文件名称: workingads_edit.jsp
 * 系统名称: 票据管理平台
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: @version2.0.0.1
 * 开发人员: vvv
 * 开发时间: 2016-7-20
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
<body style="font-family:'微软雅黑';background:#f4f8fb;">
	<div class="clearfix">
		<div  class="page-content" id="jump-content">
			<form  action="workingAdsController.do?method=save" name="Form" id="Form" method="post" class="form-search">
				<input type="hidden" name="isedit" value="${isedit}"/>
				<div id="zhongxin">
					<div class="row-fluid">
						<label for="working_ads_no" class="text-right"><span class="star">*</span>机构编号</label>
						<input type="text" class="input-medium" name="working_ads_no" id="working_ads_no" placeholder="请输入机构编号" value="${workingads.workingAdsNo}" valid="required"/>
					</div>
					<div class="row-fluid" >
						<label for="working_ads_name" class="text-right"><span class="star">*</span>机构名称</label>
						<input type="text" class="input-medium" name="working_ads_name" id="working_ads_name" placeholder="请输入机构名称" value="${workingads.workingAdsName}" valid="required"/>
					</div>
					<div class="row-fluid">
						<label for="value_name" class="text-right"><span class="star">*</span>机构状态</label>
						<t:dictSelect className="select-medium" name="status" other="" dictGroup="K_QYBZ" defaultVal="${workingads.status}" haveHead="true" valid="required">
						</t:dictSelect>
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
		document.getElementById("working_ads_no").readOnly = true;
	}
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
		var working_ads_no = $("#working_ads_no").val();
		var url = "workingAdsController.do?method=checkExists&working_ads_no="+working_ads_no;
		$.ajax({
			url:url,
			type:"POST",
			dataType:"JSON",
			success: function(data){
  				if(!data.success){
					showTips("working_ads_no", "机构编号已存在");
					$("#working_ads_no").focus();
				} else{
					modal("#layer_loading,#image");
					$("#Form").submit();
					$("#zhongxin").hide();
				}
  			 }
  		});
	}
	var isedit = '${isedit}';
	if(isedit == '1'){
		document.getElementById("working_ads_no").readOnly = true;
	}
	var isstatus='${workingads.status}';
	if(isstatus=="未启用"){
	    document.getElementById("statusno").checked=true;
	}
</script>			
</body>
</html>