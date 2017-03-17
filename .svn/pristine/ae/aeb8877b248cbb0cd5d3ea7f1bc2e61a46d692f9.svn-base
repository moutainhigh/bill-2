<%-- 
 * 文件名称: notice_edit.jsp
 * 系统名称: 票据管理平台
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: @version2.0.0.1
 * 开发人员: fanqq
 * 开发时间:  
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
			<form  action="noticeController.do?method=saveNotice" name="Form" id="Form" method="post" class="form-search">
				<input type="hidden" name="isedit" value="${isedit}"/>
				<input type="hidden" name="id" value="${id}"/>
				<div id="zhongxin">
					<div class="row-fluid" >
						<label for="noticeNo" class="text-right"><span class="star">*</span>公告编号</label>
						<input type="text" class="input-medium" name="noticeNo" id="noticeNo" placeholder="请输入公告编号" value="${notice.noticeNo}" valid="required" />
					</div>
					<div class="row-fluid">
						<label for="noticeName" class="text-right"><span class="star">*</span>公告标题</label>
						<input type="text" class="input-medium" name="noticeName" id="noticeName" placeholder="请输入公告标题" value="${notice.noticeName}" valid="required" />
					</div>
					<div class="row-fluid" >
					<label class="text-right" for="custAcctNo"><span class="star">*</span>公告内容</label>
						<textarea id="notice" name="notice" placeholder="请输入公告内容"  valid="required" style="height:200px;" class="input-medium"></textarea>
					</div>
					<input type="hidden" class="input-medium" name="releaseTime" id="releaseTime" placeholder="请输入发布时间" value="${notice.releaseTime}" valid="required" />
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
		document.getElementById("noticeNo").readOnly = true;
		$("#notice").val('${notice.notice}');
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
		var noticeNo = $("#noticeNo").val();
		var url = "noticeController.do?method=checkExists&noticeNo="+noticeNo;
		$.ajax({
			url:url,
			type:"POST",
			dataType:"JSON",
			success: function(data){
  				if(!data.success){
					showTips("noticeNo", "该公告已存在");
					$("#noticeNo").focus();
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