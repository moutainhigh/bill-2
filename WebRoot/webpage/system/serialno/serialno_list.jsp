<%-- 
 * 文件名称: serialno_list.jsp
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
		<%-- jsp文件头和头部 --%>
		<%@ include file="../admin/top.jsp"%> 
	</head>
<body>
	<div class="clearfix">
		<div id="page-content" class="page-content">
			<%-- 查询区  --%>
			<div id="header">
				<form action="<%=basePath%>serialnoController.do?method=list" method="post" name="userForm" id="userForm" class="form-search">
					<div class="row-fluid">
						<label for="key_no">序列名称</label>
						<input class="input-medium" type="text" name="key_no" value="${mapField.key_no}" placeholder="请输入序列名称"/>
						<a class="btn-mini" id="search" onclick="searchd();">查询</a>
					</div>
					<sys:tableSort id="order_by" name="order_by" value="${mapField.order_by}" callback="page();"/>
				</form>
			</div>
			<%-- /查询区  --%>
			<%-- 按钮操作区 --%>
			<div id="center">
				<form  id="btnForm">
					<div class="row-fluid">
						<div class="span6" id="btn-left">
					    	<a class="btn-mini" onclick="add();">新增</a>
							<a class="btn-mini" onclick="edit();">修改</a>
							<a class="btn-mini" onclick="del();">删除</a>
				  	 	</div>
				  	 	<div class="span6" id="btn-right"></div>
			 	 	</div>
			 	</form>
			</div>
		    <%-- /按钮操作区 --%>
			<%-- 列表操作区 --%>
			<div id="footer">
				<table id="table_report" class="table table-striped table-bordered table-hover">
					<thead>
						<tr>
							<th class="center" onclick="selectAll('zcheckbox', 'key_nos')">
								<input type="checkbox" id="zcheckbox" />
							</th>
							<th class="center">序号</th>
							<th class='center  sort-column key_no'>序列名称</th>
							<th class='center  sort-column curr_value'>序列当前值</th>
							<th class='center  sort-column inc'>增量值</th>
							<th class='center  sort-column auto_reset'>每日初始化标志 </th>
							<th class='center  sort-column max_value'>日间最大值 </th>
							<th class='center  sort-column min_value'>日间初始值 </th>
						</tr>
					</thead>
						<c:choose>
							<c:when test="${not empty resultList}">
								<c:forEach items="${resultList}" var="serialno" varStatus="vs">
									<tr>
										<td class="center">
											<input type='checkbox' name='key_nos' value="${serialno.keyNo}" />
										</td>
										<td class="center">${vs.index+1}</td>
										<td class="center">${serialno.keyNo}</td>
										<td class="center">${serialno.currValue}</td>
										<td class="center">${serialno.inc}</td>
										<td class="center">${fns:getDictLabel('K_YORN',serialno.autoReset)}</td>
										<td class="center">${serialno.maxValue}</td>
										<td class="center">${serialno.minValue}</td>
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
				<form  action="#" name="dataCollectForm" method="post"></form>
			    <div id="page" class="pagination">
					<form action="<%=basePath%>serialnoController.do?method=list"  name="pageForm" method="post" >
						<%@ include file="/webpage/system/admin/pageFormContent.jsp"%>
						<input type="hidden" name="key_no" value="${mapField.key_no}" />
					</form>
				</div>
		  	</div>
		</div>	
<%@ include file="../admin/modalDialog.jsp"%> 
<%@ include file="../admin/footer.jsp"%> 
<script type="text/javascript">
	//检索
	function searchd(){
		modal("#layer_loading,#image");
		$("#userForm").submit();
	}
	//新增
	function add(){
		 var diag = new top.Dialog();
		 diag.Drag = true;
		 diag.Title ="新增";
		 diag.URL = "<%=basePath%>serialnoController.do?method=toAdd";
		 diag.Width = 450;
		 diag.Height = 320;
		 diag.CancelEvent = function(){ //关闭事件
              pageForm.submit();
			 diag.close();
		 };
		 diag.show(); 
	}
	//修改
	function edit(){
		 var checkNum = getCheckNum("key_nos");
	   	 if (checkNum !=1){
	   		bootbox.alert("一次只能对一条记录进行修改");
	   		return;
	   	 }
	   	 var key_no = getCheckStr("key_nos");
	   	 var diag = new top.Dialog();
		 diag.Drag = true;
		 diag.Title ="修改";
		 diag.URL = '<%=basePath%>serialnoController.do?method=toEdit&key_no='+key_no;
		 diag.Width = 450;
		 diag.Height = 320;
		 diag.CancelEvent = function(){ //关闭事件
			pageForm.submit();
			diag.close();
		 };
		 diag.show();
	}
	//删除
	function del(){
		var checkNum = getCheckNum("key_nos");
	   	if (checkNum <= 0){
	   		bootbox.alert("请先选择要删除的记录");
	   		return;
	   	}
	   	var key_nos = getCheckStr("key_nos");	
		bootbox.confirm("确定要删除选中的记录吗？", function(result) {	
		if(result) {
				$.ajax({
					type: "POST",
					url: '<%=basePath%>serialnoController.do?method=del',
			    	data: {'key_nos': key_nos},
					dataType:'json',
					cache: false,
					success: function(data){	
						if (data.success){  //处理成功
							userForm.submit();
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