 <%-- 
 * 文件名称: serialno_edit.jsp
 * 系统名称: 票据管理平台
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: @version2.0.0.1
 * 开发人员: wuzhichen
 * 开发时间: 2016-7-13 下午14:32:27
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
			<form  action="serialnoController.do?method=save" name="Form" id="Form" method="post" class="form-search">
				<input type="hidden" name="isedit" value="${isedit}"/>
					<div id="zhongxin">
						<div class="row-fluid" >
							<label for="key_no" class=" text-right"><span class="star">*</span>序列名称</label>
							<input type="text" class="input-medium" name="key_no" id="key_no" placeholder="输入序列名称" value="${serialno.keyNo}" valid="required"/>
						</div>
						<div class="row-fluid">
							<label for="curr_value" class=" text-right"><span class="star">*</span>序列当前值</label>
							<input type="text" class="input-medium" name="curr_value" id="curr_value" placeholder="请输入序列当前值" value="${serialno.currValue}" valid="required"/>
						</div>
						<div class="row-fluid" >
							<label for="inc" class=" text-right"><span class="star">*</span>增量值</label>
							<input type="text" class="input-medium" name="inc" id="inc" placeholder="请输入增量值" value="${serialno.inc}" valid="required"/>
						</div>
						<div class="row-fluid">
							<label for="auto_reset" class=" text-right"><span class="star">*</span>日初始化标志</label>
							<t:dictSelect className="select-medium" name="auto_reset" other="" dictGroup="K_YORN" defaultVal="${serialno.autoReset}" haveHead="true"  valid="required">
							</t:dictSelect>
						</div>
						<div class="row-fluid">
							<label for="max_value" class=" text-right"><span class="star">*</span>日间最大值</label>
							<input type="text" class="input-medium" name="max_value" id="max_value" placeholder="请输入日间最大值" value="${serialno.maxValue}" valid="required"/>
						</div>
						<div class="row-fluid">
							<label for="min_value" class=" text-right"><span class="star">*</span>日间初始值</label>
							<input type="text" class="input-medium" name="min_value" id="min_value" placeholder="请输入日间初始值" value="${serialno.minValue}" valid="required"/>
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
		document.getElementById("key_no").readOnly = true;
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
				modal("#layer_loading,#image");
			}
	 	 }
	}
	//判断编码是否存在
	function checkExist(){
		var key_no = $("#key_no").val();
		var url = "serialnoController.do?method=checkExists&key_no="+key_no;
		$.ajax({
			url:url,
			type:"POST",
			dataType:"JSON",
			success: function(data){
  				if(!data.success){
					showTips("key_no", "序列名称已存在");
					$("#key_no").focus();
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