<%-- 
 * 文件名称: cacheitem_manage.jsp
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
<%@ taglib prefix="sys" tagdir="/WEB-INF/tags/sys" %>
<%@ taglib prefix="t" uri="/WEB-INF/tld/poseui.tld"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<base href="<%=basePath%>">
		<%-- jsp文件头和头部 --%>
		<%@ include file="../admin/top.jsp"%> 
	</head>

<body style="font-family:'微软雅黑';">
	<div class="clearfix">
		<div id="page-content" class="page-content">
			<form action="<%=basePath%>/cacheManageController.do?method=listCacheManage" method="post" name="cacheItemForm" id="cacheItemForm" class="form-search">
				<div class="row-fluid">
					<table class="table table-striped table-bordered table-hover" border="0" cellspacing="0" cellpadding="0">
						<tr>
                        	<td style="width:20px;">
                        		<input type='checkbox' onclick="selectCheck(this)"/>
                        	</td>
                        	<td><span>全选</span></td>
                        </tr>    
						<c:choose>
							<c:when test="${not empty resultList}">
								<c:forEach items="${resultList}" var="cacheiparam" >
									<tr>
										<td style="width:20px;" >
											<input type='checkbox' style="margin-right:5px" class="memflash" name='ids' value="${cacheiparam.cacheCode}" />
										</td>
										<td><span>${cacheiparam.cacheName}</span></td>
									</tr>
								</c:forEach>
						</c:when>
						<c:otherwise>
							<tr>
								<td colspan="100" class="center">没有相关数据</td>
							</tr>
						</c:otherwise>
					</c:choose>
				</table>
			</div>
			</form>
			<div id="center">
				<div class="row-fluid">
					<div class="span6" id="btn-left">
						<a class="btn-mini"  id="submit" onclick="del();">提交</a>
			   		</div>
			   		<div class="span6" id="btn-right">
			   		</div>
		  		</div>
	  		</div>
  		</div>
	</div>	
<%@ include file="../admin/footer.jsp"%> 
<script type="text/javascript">
	function del(){
		var checkNum = getCheckNum("ids");
	   	if (checkNum <= 0){
	   		bootbox.alert('请选择要处理的项');
	   		return;
	   	}
	   	var cachei_ids = getCheckStr("ids");	
	   	bootbox.confirm("是确定要选择的项吗?", function(result) {
		if(result){
				$.ajax({
					type: "POST",
					url: '<%=basePath%>cacheManageController.do?method=refre',
			    	data: {'cachei_ids': cachei_ids},
					dataType:'json',
					cache: false,
					success: function(data){	
						if (data.success){  //处理成功
							bootbox.alert('处理成功!');
						} else {
							bootbox.alert('处理失败!');
						}
					}
			});
			}
	    });
	}
    function selectCheck(v) {
	$.each($(".memflash"),function(){
		if (v.checked) {
			this.checked = true;
		} else {
			this.checked = false;
		}
	});
}
</script>
		
</body>
</html>