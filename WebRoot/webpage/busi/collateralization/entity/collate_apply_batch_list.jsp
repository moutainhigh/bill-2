<%-- 
 * 文件名称: batch_manage.jsp
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
		<div id="page-content" class="page-content">
			<div id="header">
				<form action="<%=basePath%>collateralizationApplyController.do?method=searchBatch"  method="post" name="custForm" id="custForm" class="form-search">
					<%-- 查询区  --%>
					<div class="row-fluid">
						<label class="no-padding-right" for="custNo"><span class="star">*</span>客户号</label>
						<input type="text" class="input-medium" id="custNo" name="custNo" valid="required" value="${query.custNo}" placeholder="请输入客户号"/>
						<a class="btn-mini" id="search"  onclick="fill();">填充</a>
						<label class="no-padding-right" for="custName"><span class="star">*</span>客户名称</label>
						<input readonly="true" class="input-medium" type="text" id="custName" name="custName" valid="required" value="${custName}"/>
					</div>
					</form>
				</div>
				<%-- 按钮操作区 --%>
				<div id="center">
					<form  id="btnForm">
						<div class="row-fluid">
							<div class="span6" id="btn-left" >
							    <a class="btn-mini" onclick="addBatch();">新增</a>
								<a class="btn-mini" href="javascript:editBatch();">修改</a>
								<a class="btn-mini" href="javascript:delBatch();">删除</a>
								<a class="btn-mini" href="javascript:saveManage();">票据管理</a>
						   </div>
						   <div class="span6" id="btn-right"></div>
				  		</div>
		 			</form>
		 		</div>
			    <div id="footer">
					<table class="table table-striped table-bordered table-hover" id="tab" cellpadding="0" cellspacing="0">
						<thead>
							<tr>
								<th class="center" onclick="selectAll('zcheckbox', 'saveId')">
									<input type="checkbox" id="zcheckbox" /><span class="lbl"></span>
								</th>
								<th>批次号</th>
								<th>批次类型</th>
								<th>批次种类</th>
								<th>产品编号</th>
								<th>合计张数</th>
								<th>合计金额</th>
								<th>客户经理编号</th>
								<th>客户经理名称</th>
								<th>所属部门</th>
								<th>详情</th>
							</tr>
						</thead>
						<tbody id="dataBody">
							<c:choose>
									<c:when test="${not empty resultList}">
										<c:forEach items="${resultList}" var="batchList" varStatus="vs">
											<tr>
												<td class="center">
													<input type='checkbox' name='saveId' value="${batchList.saveId}" /><span class="lbl"></span>
												</td>
												<td class="center">${batchList.batchNo}</td>
												<td class="center">${fns:getDictLabel('K_BILL_TYPE',batchList.batchType)}</td>
												<td class="center">${fns:getDictLabel('K_BILL_CLASS',batchList.batchClass)}</td>
												<td class="center">${batchList.prodNo}</td>
												<td class="center">${batchList.totalNum}</td>
												<td class="center">${fns:formateMoney(batchList.totalMoney)}</td>
												<td class="center">${batchList.custManager}</td>
												<td class="center">${batchList.custManagerName}</td>
												<td class="center">${batchList.deptName}</td>
												<td class="center"><a href="javascript:goBatchDetail('${batchList.saveId}')">查看</a></td>
											</tr>
										</c:forEach>
									</c:when>
									<c:otherwise>
										<tr>
											<td colspan="100" class="center">没有相关数据</td>
										</tr>
									</c:otherwise>
								</c:choose>
							</tbody>
						</table>
					</div>
					<form action="#" name="dataCollectForm" method="post"></form>
					<%-- /列表操作区 --%>
					<div>
						<div id="page" class="pagination hid">
							<form action="<%=basePath%>collateralizationApplyController.do?method=searchBatch" name="pageForm" method="post">
								<%@ include file="/webpage/system/admin/pageFormContent.jsp"%>
								<input type="hidden" name="custNo" value="${query.custNo}"/>
								<input type="hidden" name="custName" value="${custName}"/>
							</form>
						</div>
					</div>
				</div>
			</div>
<%@ include file="/webpage/system/admin/modalDialog.jsp"%> 
<%@ include file="/webpage/system/admin/footer.jsp"%> 
<script>
    var custNo = $("#custNo").val();
    if(custNo!=""){
    $(".hid").show();
    }else{
    $(".hid").hide();
    }
	$("#tab th,td").addClass("center");
	$(function(){
		var custNo = "${custNo}";
		if(""!=custNo && custNo!=null){
			$.ajax({
				type: "POST",
				url: "<%=basePath%>collateralizationApplyController.do?method=custInfo",
		    	data: {'custNo':custNo},
				dataType:'json',
				success: function(data){
					if(data.success){  //处理成功
						$("#custNo").val(custNo);
						$("#custName").val(data.attributes.custName);
						fillBatch(1,5);
					}else{
						$("#custName").val("");
						bootbox.alert(data.msg); 
					}
				}
			});	
		 }
	});
	//新增
	function addBatch(){
		if($("#custNo").val() == ""){
			bootbox.alert("请输入客户号！");
			return false;
		}else{
			var custNo=$("#custNo").val();
			var diag = new top.Dialog();
			diag.Drag = true;
		 	diag.Title ="新增";
		 	diag.URL = "<%=basePath%>collateralizationApplyController.do?method=toAddBatch&custNo="+custNo;
		 	diag.Width = 750;
		 	diag.Height = 450;
		 	diag.CancelEvent = function(){ //关闭事件
		 	custForm.submit();
				diag.close();
		 	};
		 	diag.show(); 
		 }
	}
	//编辑
	function editBatch(){
		 var checkNum = getCheckNum("saveId");
	   	 if (checkNum !=1){
	   		bootbox.alert("请先选择要编辑的记录!");
	   		return;
	   	 }
	   	 var saveId = getCheckStr("saveId");
	   	 var totalNum = "${batchList.totalNum}";
	   	 var diag = new top.Dialog();
		 diag.Drag = true;
		 diag.Title ="修改";
		 diag.URL = "<%=basePath%>collateralizationApplyController.do?method=toEditBatch&saveId="+saveId+"&totalNum="+totalNum;
		 diag.Width = 700;
		 diag.Height = 425;
		 diag.CancelEvent = function(){ //关闭事件
		 custForm.submit();
		 diag.close();
		 };
		 diag.show(); 
	}
	// 删除批次 
	function delBatch(){
	    var checkNum = getCheckNum("saveId");
	    if (checkNum <= 0){
	   		bootbox.alert("请先选择要删除的记录!");
	   		return;
	   	}
	   	var saveId = getCheckStr("saveId");	
	   	bootbox.confirm("确定要删除选中的记录吗?", function(result) {
			if(result) {
				$.ajax({
					type: "POST",
					url: "<%=basePath%>collateralizationApplyController.do?method=delBatch",
			    	data:{'saveId': saveId},
			    	cache:false,
					dataType:'json',
					success:function(data){
					    bootbox.alert(data.msg);
						if(data.success) {  //处理成功
						pageForm.submit();
						} 
					}
				});
		 	 }
	   	});
	}
	//批次详情
	function goBatchDetail(saveId){
	   	 var diag = new top.Dialog();
		 diag.Drag = true;
		 diag.Title ="详情";
		 diag.URL = "<%=basePath%>collateralizationApplyController.do?method=goBatchDetail&saveId="+saveId;
		 diag.Width = 990;
		 diag.Height = 190;
		 diag.CancelEvent = function(){ //关闭事件
			diag.close();
		 };
		 diag.show();
	}
	//票据管理
	function saveManage(){
		 var checkNum = getCheckNum("saveId");
	   	 if (checkNum !=1){
	   		bootbox.alert("请先选择要编辑的记录!");
	   		return;
	   	 }
	   	 var saveId = getCheckStr("saveId");
	   	 dynamicHiddenElement('dataCollectForm','saveId',saveId);
	   	 modal("#layer_loading,#image");//点击票据管理按钮，弹出‘加载中’的遮罩层
	     dataCollectForm.action = "<%=basePath%>collateralizationApplyController.do?method=billManage"
	     dataCollectForm.submit();
	}
	function fill(){ //根据客户号查询客户信息并填充
			var custNo = $('#custNo').val();
			$.ajax({
				type: "POST",
				url: "<%=basePath%>collateralizationApplyController.do?method=custInfo",
		    	data: {'custNo': custNo},
				dataType:'json',
				beforeSend:function(){
					if(custNo==null||custNo==""){
						bootbox.alert("请输入客户号!");
						$("#custName").val("");
				   		return false;
					}
				},
				cache: false,
				success: function(data){	
					if(data.success){  //处理成功
						$("#custName").val(data.attributes.custName);
						$("#custNo").val(data.attributes.custNo);
					custForm.submit();
					}else{
						$("#custName").val("");
						bootbox.alert(data.msg);
						var trHtml = "<tr><td colspan=\"100\" class=\"center\">没有相关数据</td></tr>";
						$("#dataBody").html(trHtml);
					}
				}
			});
		}
		function nextPage(page){
			fillBatch(page,5);
		}
		function changeCount(value){
			fillBatch(1,value);
		}
</script>
</body>
</html>