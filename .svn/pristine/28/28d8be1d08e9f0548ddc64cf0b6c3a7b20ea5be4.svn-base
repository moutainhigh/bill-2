<%-- 
 * 文件名称: cacheitem_edit.jsp
 * 系统名称: 票据管理平台
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: @version2.0.0.1
 * 开发人员: cgc
 * 开发时间: 2016-7-7 上午06:28:22
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
		<%@ include file="../admin/top.jsp"%>
	</head>
<body>
	<div class="clearfix">
		<div  class="page-content" id="jump-content">
			<form  action="cacheController.do?method=save" name="Form" id="Form" method="post" class="form-search">
				<input type="hidden" name="isedit" value="${isedit}"/>
					<div id="zhongxin">
						<div class="row-fluid" >
							<label for="cache_code" class="text-right"><span class="star">*</span>缓存编码</label>
							<input type="text" class="input-medium" name="cache_code" id="cache_code" placeholder="输入缓存编码" value="${cacheparam.cacheCode}" title="缓存编码" valid="required"/>
						</div>
						<div class="row-fluid">
							<label for="cache_name" class="text-right"><span class="star">*</span>缓存名称</label>
							<input type="text" class="input-medium" name="cache_name" id="cache_name" placeholder="请输入缓存名称" value="${cacheparam.cacheName}" title="缓存名称" valid="required"/>
						</div>
						<div class="row-fluid" >
							<label for="cache_class" class="text-right"><span class="star">*</span>缓存类名</label>
							<input type="text" class="input-medium" name="cache_class" id="cache_class" placeholder="请输入缓存类名" value="${cacheparam.cacheClass}" title="缓存类名" valid="required"/>
						</div>
						<div class="row-fluid">
							<label for="cache_method" class="text-right"><span class="star">*</span>缓存类方法</label>
							<input type="text" class="input-medium" name="cache_method" id="cache_method" placeholder="请输入缓存类方法" value="${cacheparam.cacheMethod}" title="缓存类方法" valid="required"/>
						</div>
						<div class="row-fluid">
							<label for="remark" class="text-right"><span class="star">*</span>缓存描述信息</label>
							<textarea class="form-control" name="remark" id="remark" placeholder="请输入缓存描述信息" title="缓存描述信息" valid="required">${cacheparam.remark}</textarea>
						</div>
						<div class="row-fluid">
							<label for="reserve1" class="text-right"><span class="star">*</span>备用1</label>
							<input type="text" class="input-medium" name="reserve1" id="reserve1" placeholder="请输入备用1" value="${cacheparam.reserve1}" title="备用1" valid="required"/>
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
     $(document).ready(function() {  
  		$("#Form").validate({
  			 onfocusout: function(element){
        		$(element).valid();
    		 }
  		});  
	}); 
	var isedit = '${isedit}';
	if(isedit == '1'){
		document.getElementById("cache_code").readOnly = true;
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
		var cache_code = $("#cache_code").val();
		var url = "cacheController.do?method=checkExists&cache_code="+cache_code;
		$.ajax({
			url:url,
			type:"POST",
			dataType:"JSON",
			success: function(data){
  				if(!data.success){
					showTips("cache_code", "缓存编码已存在"); 
					$("#cache_code").focus(); 
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