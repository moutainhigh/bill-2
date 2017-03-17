<%-- 
 * 文件名称: sale_apply_batch_list.jsp
 * 系统名称: 票据管理平台
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 批次列表页面
 * 系统版本: @version2.0.0.1
 * 开发人员: sherry
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
			<%-- 按钮操作区 --%>
			<div id="center">
				<form id="btnForm">
					<div class="row-fluid">
						<div class="span6" id="btn-left">
							<a class="btn-mini" onclick="add();">新增</a> 
							<a class="btn-mini" href="javascript:checkBillsStatusForEditApply();">修改</a> 
							<a class="btn-mini" href="javascript:del();">删除</a>
							<a class="btn-mini" href="javascript:billManage();">票据管理</a> 
						</div>
					</div>
				</form>
			</div>
			<%-- 列表操作区 --%>
			<div id="footer">
				<table class="table table-striped table-bordered table-hover" id="tab" cellpadding="0" cellspacing="0">
					<thead>
						<tr>
							<th class="center" onclick="selectAll('zcheckbox', 'saleId')">
								<input type="checkbox" id="zcheckbox" /><span class="lbl"></span>
							</th>
							<th class="center">批次号</th>
							<th class="center">交易对手名称</th>
							<th class="center">票据类型</th>
							<th class="center">票据品种</th>
							<th class="center">合计张数</th>
							<th class="center">合计金额</th>
							<th class="center">申请生成日</th>
							<th class="center">客户经理</th>
							<th class="center">部门归属</th>
							<th class="center">详情</th>
						</tr>
					</thead>
					<tbody id="dataBody">
						<c:choose>
							<c:when test="${not empty batchList}">
								<c:forEach items="${batchList}" var="saleApplyInfo"
									varStatus="vs">
									<tr>
										<td class="center">
											<input type='checkbox' name='saleId' value="${saleApplyInfo.saleId}" /><span class="lbl"></span>
										</td>
										<td class="center">${saleApplyInfo.batchNo}</td>
										<td class="center">${saleApplyInfo.custName}</td>
										<td class="center">${fns:getDictLabel('K_BILL_TYPE',saleApplyInfo.billType)}</td>
										<td class="center">${fns:getDictLabel('K_BILL_CLASS',saleApplyInfo.billClass)}</td>
										<td class="center">${saleApplyInfo.totalSize}</td>
										<td class="center">${fns:formateMoney(saleApplyInfo.sumMoney)}</td>
										<td class="center">${saleApplyInfo.createDt}</td>
										<td class="center">${saleApplyInfo.custManagerName}</td>
										<td class="center">${saleApplyInfo.deptName}</td>
										<td class="center">
											<a href="javascript:goApplyInfo('${saleApplyInfo.saleId}')">查看</a>
										</td>
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
			<%-- 用于页面跳转 --%>
			<form  action="#" name="dataCollectForm" method="post"></form>
			<%-- 分页 --%>
			<div id="page" class="pagination">
				<form action="<%=basePath%>saleApplyController.do?method=batch"
					name="pageForm" method="post">
					<%@ include file="/webpage/system/admin/pageFormContent.jsp"%>
				</form>
			</div>
		</div>
	</div>
<%@ include file="/webpage/system/admin/modalDialog.jsp"%>
<%@ include file="/webpage/system/admin/footer.jsp"%>
<script>
	//新增
	function add(){
		var diag = new top.Dialog();
		diag.Drag = true;
	 	diag.Title ="新增";
	 	diag.URL = "<%=basePath%>saleApplyController.do?method=toAddBatch";
	 	diag.Width = 800;
	 	diag.Height = 590;
	 	diag.CancelEvent = function(){ //关闭事件
		 	location.href = location.href;
			diag.close();
	 	};
	 	diag.show(); 
	}
	//检查选择的批次是否允许修改
	function checkBillsStatusForEditApply(){
		var checkNum = getCheckNum("saleId");
	   	 if (checkNum !=1){
	   		bootbox.alert("请选择一条记录编辑");
	   		return;
	   	 }
	   	 var saleId = getCheckStr("saleId");
		$.ajax({
			url:"<%=basePath%>saleApplyController.do?method=checkBillsStatusForEditApply",
			data: {'saleId': saleId},
			type:"post",
			dataType:"JSON",
			success: function(data){
  				if(!data.success){
  					top.hangge();
					bootbox.alert(data.msg); 
				}else{
					edit(saleId);
				}
  			}
  		});
	}
	//编辑
	function edit(saleId){
	   	 var diag = new top.Dialog();
		 diag.Drag = true;
		 diag.Title ="修改";
		 diag.URL = "<%=basePath%>saleApplyController.do?method=toEditBatch&saleId="+saleId;
		 diag.Width = 800;
		 diag.Height = 590;
		 diag.CancelEvent = function(){ //关闭事件
		 	location.href = location.href;
			diag.close();
		 };
		 diag.show(); 
	}
	//删除
	function del(){
		 var checkNum = getCheckNum("saleId");
	   	 if (checkNum <=0){
	   		bootbox.alert("请选择删除记录");
	   		return;
	   	 }
	   	 var saleId = getCheckStr("saleId");
		 bootbox.confirm("是否确定要删除选择的记录吗?", function(result) {
			if(result) {
				$.ajax({
					type: "POST",
					url: '<%=basePath%>saleApplyController.do?method=del',
					data: {'saleId': saleId},
					dataType:'json',
					cache: false,
					success: function(data){	
						if (data.success){  //处理成功
							bootbox.alert(data.msg); 
						} else {
							top.hangge();
							bootbox.alert(data.msg); 
						}
						dataCollectForm.action = "<%=basePath%>saleApplyController.do?method=batch";
						dataCollectForm.submit();
					}
			  });
		   }
	   });
	}
	//票据管理
	function billManage(){
		var checkNum = getCheckNum("saleId");
	   	if (checkNum !=1){
	   		bootbox.alert("请选择一条记录");
	   		return;
	   	}
	   	var saleId = getCheckStr("saleId");
	   	dynamicHiddenElement('dataCollectForm','saleId',saleId);
	   	dynamicHiddenElement('dataCollectForm','flag','1');
	   	modal("#layer_loading,#image");//点击票据管理按钮，弹出‘加载中’的遮罩层
		dataCollectForm.action = "<%=basePath%>saleApplyController.do?method=billManage";
		dataCollectForm.submit();
	}
	//批次详情页面
	function goApplyInfo(saleId){
	   	 var diag = new top.Dialog();
		 diag.Drag = true;
		 diag.Title ="详情";
		 diag.URL = '<%=basePath%>saleApplyController.do?method=goApplyInfo&saleId='+saleId+'&flag=apply';
		 diag.Width = 990;
		 diag.Height = 510;
		 diag.CancelEvent = function(){ //关闭事件
			diag.close();
		 };
		 diag.show();
	}	
</script>
</body>
</html>