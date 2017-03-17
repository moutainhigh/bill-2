<%-- 
 * 文件名称: notice_list.jsp
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
		<%-- jsp文件头和头部 --%>
		<%@ include file="../admin/top.jsp"%>	
	</head>
<body>
	<div class="clearfix">
		<div id="page-content" class="page-content">
			<form action="<%=basePath%>noticeController.do?method=list" method="post" name="Form">
			<%-- 按钮操作区 --%>
			<div id="center">
				<form  id="btnForm">
					<div class="row-fluid">
						<div class="span6" id="btn-left">
				    		<a class="btn-mini"  onclick="add();">新增</a>
				    		<a class="btn-mini" onclick="edit();">修改</a>
				    		<a class="btn-mini" onclick="notice();">公告内容</a>
							<a class="btn-mini"  onclick="del();">删除</a>
			  		 	</div>
			  		 	<div class="span6" id="btn-right"></div>
		  			</div>
		  		</form>
		  </div>
		  </form>
	      <%-- /按钮操作区 --%>
		  <%-- 列表操作区 --%>
		  <div id="footer">
			 <table id="table_report" class="table table-striped table-bordered table-hover">
				<thead>
				<tr>
					<th class="center" onclick="selectAll('zcheckbox', 'noticeNos')">
						<input type="checkbox" id="zcheckbox" />
					</th>
					<th class="center">序号</th>
					<th class='center sort-column notice_no'>公告编号</th>
					<th class='center sort-column notice_name'>公告标题</th>
					<th class='center sort-column release_time'>公告发布时间</th>
				</tr>
				</thead>
				<c:choose>
					<c:when test="${not empty resultList}">
						<c:forEach items="${resultList}" var="notice" varStatus="vs">
							<tr>
								<td class="center">
									<input type='checkbox' name='noticeNos' value="${notice.noticeNo}" />
								</td>
								<td class="center">${vs.index+1}</td>
								<td class="center">${notice.noticeNo}</td>
								<td class="center">${notice.noticeName}</td>
								<td class="center">${notice.releaseTime}</td>
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
	   <%-- /列表操作区 --%>
	   <div id="page" class="pagination">
			<form action="<%=basePath%>noticeController.do?method=list" name="pageForm" method="post">
				<%@ include file="/webpage/system/admin/pageFormContent.jsp"%>
				<input type="hidden" name="noticeNo" value="${mapField.noticeNo}" />
			</form>
		</div>
  	</div>
</div>	
<%@ include file="../admin/footer.jsp"%>
<script type="text/javascript">
	//检索
	function search(){
		$("#userForm").submit();
	}
	//新增
	function add(){
		 var diag = new top.Dialog();
		 diag.Drag = true;
		 diag.Title ="新增";
		 diag.URL = "<%=basePath%>noticeController.do?method=toAdd";
		 diag.Width = 450;
		 diag.Height = 390;
		 diag.CancelEvent = function(){ //关闭事件
			pageForm.submit();
			 diag.close();
		 };
		 diag.show(); 
	}
	//修改
	function edit(){
		 var checkNum = getCheckNum("noticeNos");
	   	 if (checkNum !=1){
	   		bootbox.alert("请选择一条记录编辑");
	   		return;
	   	 }
	   	 var noticeNo = getCheckStr("noticeNos");
	   	 var diag = new top.Dialog();
		 diag.Drag = true;
		 diag.Title ="修改";
		 diag.URL = '<%=basePath%>noticeController.do?method=toEdit&noticeNo='+noticeNo;
		 diag.Width = 450;
		 diag.Height = 390;
		 diag.CancelEvent = function(){ //关闭事件
				diag.close();
				pageForm.submit();
		 };
		 diag.show();
	}
	//公告内容
	function notice(){
		 var checkNum = getCheckNum("noticeNos");
	   	 if (checkNum !=1){
	   		bootbox.alert("请选择一条要查看的公告内容");
	   		return;
	   	 }
	   	 var noticeNo = getCheckStr("noticeNos");
	   	 var url="<%=basePath%>noticeController.do?method=toReadNotice&&noticeNo="+noticeNo;
	     top.mainFrame.jericho.buildTree("notice","notice","公告明细",url);
	   	 /*var diag = new top.Dialog();
		 diag.Drag = true;
		 diag.Title ="查看";
		 diag.URL = '<%=basePath%>noticeController.do?method=toReadNotice&noticeNo='+noticeNo;
		 diag.Width = 500;
		 diag.Height = 360;
		 diag.CancelEvent = function(){ //关闭事件
				diag.close();
				pageForm.submit();
		 };
		 diag.show();*/
	}
	//删除
	function del(){
		var checkNum = getCheckNum("noticeNos");
	   	if (checkNum <= 0){
	   		bootbox.alert("请选择删除记录");
	   		return;
	   	}
	   	var noticeNos = getCheckStr("noticeNos");
	   	bootbox.confirm("是否确定要删除选择的记录吗?", function(result) {
			if(result) {
				$.ajax({
					type: "POST",
					url: '<%=basePath%>noticeController.do?method=del',
			    	data: {'noticeNos': noticeNos},
					dataType:'json',
					cache: false,
					success: function(data){	
						if (data.success){  //处理成功
							Form.submit();
						} else {
							bootbox.alert("删除失败!"); 
							}
					}
				});
		  	}
	   	});
	}
	   	function page(){
		$("#userForm").submit();
		return false;
	}
</script>
</body>
</html>