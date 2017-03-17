<%-- 
 * 文件名称: collate_elec_remark.jsp
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
	</head>
<body>
<div class="clearfix">
	<div id="jump-content" class="page-content">
		<form action="<%=basePath%>collateralizationApplyController.do?method=elecRefuseBill" method="post" name="Form" id="Form" class="form-search">
			<input type="hidden" name="ids" id="ids" value="${ids}"/>
			<div class="row-fluid">
				<label class="text-right" for="option">拒收意见<br>(最多20位)</label>
				<textarea id="option" name="option" class="input-medium"></textarea>
			</div>
			<div class="row-fluid save">
				<div class="center">
					<a class="btn-mini" onclick="refuse();">拒收</a>
					<a class="btn-mini" onclick="top.Dialog.close();">取消</a>
				</div>
			</div>
		</form>
	</div>
</div>
<%@ include file="/webpage/system/admin/modalDialog.jsp"%> 
<%@ include file="/webpage/system/admin/footer.jsp"%>
<script>
	function refuse(){
		var option = $("#option").val();
	  	var No = /^([\w\s\S\u4e00-\u9fa5]{0,20}|d*)$/;
		if($("#Form").valid()){
			if($("#option").val()==""){
				showTips("option","请输入拒收意见!");
				return;
				} else if(!No.test(option)){
					showTips("option","拒绝意见最多可输入20位");
					return;
				}else{	
				modal("#layer_loading,#image");//点击票据管理按钮，弹出‘加载中’的遮罩层
				$.ajax({
					type:"POST",
					url:'<%=basePath%>collateralizationApplyController.do?method=elecRefuseBill',
					data:$("#Form").serialize(),
					dataType:"json",
					cache: false,
					success:function(data){
						if(data.success){
							top.Dialog.close();
						}else{
							top.hangge();
							bootbox.alert("拒绝失败!"); 
						}
					}
				});
			}
		}
	}
</script>
</body>
</html>