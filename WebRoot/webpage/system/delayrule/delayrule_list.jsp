<%-- 
 * 文件名称: delayrule_list.jsp
 * 系统名称: 票据管理平台
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: @version2.0.0.1
 * 开发人员: vvv
 * 开发时间: 2016-7-12
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
				<form action="<%=basePath%>/delayRuleController.do?method=list" method="post" name="userForm" id="userForm" class="form-search">
					<div class="row-fluid">
			    		<label for="prod_no" class="text-right">产品编号</label>
						<t:dictSelect className="select-medium" name="prod_no" other="" dictGroup="K_PRDTYPE" defaultVal="${mapField.prod_no}" haveHead="true">
						</t:dictSelect>
						<label for="bill_type" class="control-label text-right">票据种类</label>
						<t:dictSelect className="select-medium" name="bill_type" other="" dictGroup="K_BILL_TYPE" defaultVal="${mapField.bill_type}" haveHead="true">
						</t:dictSelect>
					</div>
					<div class="row-fluid">
						<label for="delay_type" class="text-right">顺延规则</label>
						<t:dictSelect className="select-medium" name="delay_type" other="" dictGroup="K_SYFS" defaultVal="${mapField.delay_type}" haveHead="true">
						</t:dictSelect>
						<label for="oper_type" class="control-label text-right">执行方式</label>
						<t:dictSelect className="select-medium" name="oper_type" other="" dictGroup="K_DELAYCZFS" defaultVal="${mapField.oper_type}" haveHead="true">
						</t:dictSelect>
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
				    		<a class="btn-mini"  onclick="add();">新增</a>
							<a class="btn-mini" onclick="edit();">修改</a>
							<a class="btn-mini"  onclick="del();">删除</a>
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
							<th class="center" onclick="selectAll('zcheckbox', 'ids')">
								<input type="checkbox" id="zcheckbox" />
							</th>
							<th class="center">序号</th>
							<th class='center sort-column id'>编号</th>
							<th class='center sort-column prod_no'>产品编号</th>
							<th class='center sort-column bill_class'>票据分类</th>
							<th class='center sort-column bill_type'>票据种类</th>
							<th class='center sort-column delay_type'>顺延规则</th>
							<th class='center sort-column delay_days'>顺延天数</th>
							<th class='center sort-column oper_type'>执行方式</th>
						</tr>
					</thead>
					<c:choose>
						<c:when test="${not empty resultList}">
							<c:forEach items="${resultList}" var="delayrule" varStatus="vs">
								<tr>
									<td class="center">
										<input type='checkbox' name='ids' value="${delayrule.id}" />
									</td>
									<td class="center">${vs.index+1}</td>
									<td class="center">${delayrule.id}</td>
									<c:if test="${delayrule.prodNo=='S511001001'}">
										<td class="center">票据池</td>
									</c:if>
									<c:if test="${delayrule.prodNo=='S511002001'}">
										<td class="center">电子票据</td>
									</c:if>
									<c:if test="${delayrule.prodNo=='S511003001'}">
										<td class="center">银行承兑汇票</td>
									</c:if>
									<c:if test="${delayrule.prodNo=='S511004001'}">
										<td class="center">银行承兑汇票(商票)</td>
									</c:if>
										<td class="center">${delayrule.billClass}</td>
									<c:if test="${delayrule.billType=='1'}">
										<td class="center">银票</td>
									</c:if>
									<c:if test="${delayrule.billType=='2'}">
										<td class="center">商票</td>
									</c:if>
									<c:if test="${delayrule.delayType=='0'}">
										<td class="center">不顺延</td>
									</c:if>
									<c:if test="${delayrule.delayType=='1'}">
										<td class="center">只顺延节假日</td>
									</c:if>
									<c:if test="${delayrule.delayType=='2'}">
										<td class="center">只顺延异地</td>
									</c:if>
									<c:if test="${delayrule.delayType=='3'}">
										<td class="center">先节假日再异地</td>
									</c:if>
									<c:if test="${delayrule.delayType=='4'}">
										<td class="center">先异地再节假日</td>
									</c:if>
									<c:if test="${delayrule.delayType=='5'}">
										<td class="center">指定顺延天数</td>
									</c:if>
									<td class="center">${delayrule.delayDays}</td>
									<c:if test="${delayrule.operType=='0'}">
										<td class="center">自动</td>
									</c:if>
									<c:if test="${delayrule.operType=='1'}">
										<td class="center">手动选择</td>
									</c:if>
									<c:if test="${delayrule.operType=='2'}">
										<td class="center">只选择天数</td>
									</c:if>
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
				<form action="<%=basePath%>delayRuleController.do?method=list"  name="pageForm" method="post">
					<%@ include file="/webpage/system/admin/pageFormContent.jsp"%> 
					<input type="hidden" name="prod_no" value="${mapField.prod_no}" />
					<input type="hidden" name="bill_type" value="${mapField.bill_type}" />
					<input type="hidden" name="delay_type" value="${mapField.delay_type}" />
					<input type="hidden" name="oper_type" value="${mapField.oper_type}" />
				</form>
			</div>
	  	</div>
	</div>	
<%@ include file="../admin/modalDialog.jsp"%>
<%@ include file="../admin/footer.jsp"%>
<script type="text/javascript">
	var isedit = '${isedit}';
	if(isedit == '1'){
		document.getElementById("id").readOnly = true;
	}
	$(top.hangge());
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
		 diag.URL = "<%=basePath%>delayRuleController.do?method=toAdd";
		 diag.Width = 470;
		 diag.Height = 370;
		 diag.CancelEvent = function(){ //关闭事件
			pageForm.submit();
			 diag.close();
		 };
		 diag.show(); 
	}
	//修改
	function edit(){
		 var checkNum = getCheckNum("ids");
	   	 if (checkNum !=1){
	   		bootbox.alert("请选择一条记录编辑");
	   		return;
	   	 }
	   	 var id = getCheckStr("ids");
	   	 var diag = new top.Dialog();
		 diag.Drag = true;
		 diag.Title ="修改";
		 diag.URL = '<%=basePath%>delayRuleController.do?method=toEdit&id='+id;
		 diag.Width = 470;
		 diag.Height = 380;
		 diag.CancelEvent = function(){ //关闭事件
			pageForm.submit();
			diag.close();
		 };
		 diag.show();
	}
	//删除
	function del(){
		var checkNum = getCheckNum("ids");
	   	if (checkNum <= 0){
	   		bootbox.alert("请选择删除记录");
	   		return;
	   	}
	   	var ids = getCheckStr("ids");	
	   	bootbox.confirm("是否确定要删除选择的记录吗?", function(result) {
			if(result) {
				$.ajax({
					type: "POST",
					url: '<%=basePath%>delayRuleController.do?method=del',
			    	data: {'ids': ids},
					dataType:'json',
					//beforeSend: validateData,
					cache: false,
					success: function(data){	
						if (data.success){  //处理成功
							userForm.submit();
						} else {
							top.hangge();
							alert("删除失败!"); 
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