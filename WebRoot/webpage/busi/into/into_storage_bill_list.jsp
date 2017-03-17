<%-- 
 * 文件名称: storage_bill_list.jsp.jsp
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
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/webpage/system/admin/taglib.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
  <head>
    <base href="<%=basePath%>">
    <meta charset="UTF-8">
	<title>票据管理页面</title>
	<%@ include file="/webpage/system/admin/top.jsp"%>
</head>
<body>
	<div class="clearfix">
		<div id="page-content" class="page-content">
			<div id="header">
				<form action=""	method="post" name="userForm" id="userForm" class="form-search detail-form">
				<input type="hidden" name="intoId" value="${intoId}"/>
				<%-- 查询区  --%>
					<div class="row-fluid">
						<label for="batchNo" class="pull-left control-label">批次号</label>
						<div class="pull-left batch">${batch.batchNo}</div>
						<label for="batchType" >批次类型</label>
						<input type="text" class="input-medium" name="batchType" value="${fns:getDictLabel('K_BILL_TYPE',batch.batchType)}" readonly />
						<label for="custManagerName" >批次种类</label>
						<input type="text" class="input-medium" name="batchClass" value="${fns:getDictLabel('K_BILL_CLASS',batch.batchClass)}" readonly />
						<label for="prodName" >产品名称</label>
						<input type="text" class="input-medium" name="prodName" value="${prodName}" readonly />
				    </div>
				    <div class="row-fluid">
						<label for="custNo" >客户号</label>
						<input type="text" class="input-medium" name="custNo" readonly value="${batch.custNo}" />
						<label for="custName" >客户名称</label>
						<input type="text" class="input-medium" name="custName" value="${batch.custName}" readonly id="custName"/>
						<label for="totalNum" >票据张数</label>
						<input type="text" class="input-medium" name="totalNum" readonly value="${batch.totalNum}" />
						<label for="totalMoney" >总金额</label>
						<input type="text" class="input-medium" name="totalMoney" readonly value="${fns:formateMoney(batch.totalMoney)}" />
					</div>
					<div class="row-fluid">
						<label for="custManager" >客户经理编号</label>
						<input type="text" class="input-medium" name="custManager" value="${batch.custManager}" readonly />
						<label for="custManagerName" >客户经理名称</label>
						<input type="text" class="input-medium" name="custManagerName" value="${batch.custManagerName}" readonly />
						<label for="deptName" >所属部门</label>
						<input type="text" class="input-medium" readonly name="deptName" value="${batch.deptName}" />
						<label for="isTc" >是否贴查同步</label>
						<input type="text" class="input-medium" name="isTc" value="${fns:getDictLabel('K_IS_TC',batch.isTc)}" readonly />
				    </div>
					</form>
				</div>
				<%-- 按钮操作区 --%>
				<div id="center">
					<form  id="btnForm">
						<div class="row-fluid">
							<div class="span6" id="btn-left">
						   </div>
						   <div class="span6" id="btn-right">
								<a class="btn-mini pull-right" href="javascript:doStorage();">入库</a>
								<a class="btn-mini pull-right" onclick="goHistory('${query.custNo}');">返回</a>
						   </div>
					  	</div>
					</form>
				</div>
				<div id="footer" class="footer">
					<table class="table table-striped table-bordered table-hover" id="tab" cellpadding="0" cellspacing="0">
						<thead>
							<tr>
								<th class="center" onclick="selectAll('zcheckbox', 'ids')">
										<input type="checkbox" id="zcheckbox" /><span class="lbl"></span>
									</th>
								<th class="center">票号</th>
								<th class="center">票据类型</th>
								<th class="center">出票日</th>
								<th class="center">票面到期日</th>
								<th class="center">票面金额</th>
								<th class="center">是否贴查同步</th>
								<th class="center">承兑人</th>
								<th class="center">出票人</th>
								<th class="center">详情</th>
							</tr>
						</thead>
						<tbody>
							<c:choose>
								<c:when test="${not empty resultList}">
									<c:forEach items="${resultList}" var="into" varStatus="vs">
										<tr>
											<td class="center"><input type="checkbox" name="ids" value="${into.intomxId}"/></td>
											<td class="center">${into.billNo}</td>
											<td class="center">${fns:getDictLabel('K_BILL_TYPE',into.billType)}</td>
											<td class="center">${into.issueDt}</td>
											<td class="center">${into.dueDt}</td>
											<td class="center">${fns:formateMoney(into.billMoney)}</td>
											<td class="center">${fns:getDictLabel('K_IS_TC',into.isTc)}</td>
											<td class="center">${into.acceptor}</td>
											<td class="center">${into.remitter}</td>
											<td class="center"><a href="javascript:goBillDetail('${into.intomxId}')">查看</a></td>
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
					<div id="page" class="pagination">
							<form action="<%=basePath%>intoStorageController.do?method=storageBillList"  name="pageForm" method="post">
							<%@ include file="/webpage/system/admin/pageFormContent.jsp"%>
							<input type="hidden" name="intoId" value="${batch.intoId}"/>
						</form>
					</div>
				</div>
			</div>
		</div>
<%@ include file="/webpage/system/admin/modalDialog.jsp"%> 
<%@ include file="/webpage/system/admin/footer.jsp"%>
<script>
	//返回上一页
	function goHistory(){
		modal("#layer_loading,#image");//点击票据管理按钮，弹出‘加载中’的遮罩层
        dataCollectForm.action="<%=basePath%>intoStorageController.do?method=storageBatchList";
        dataCollectForm.submit();
	}
	//入库
	function doStorage(){
		var checkNum = getCheckNum("ids");
	   	if (checkNum <= 0){
	   		bootbox.alert("请先选择要入库的记录!");
	   		return;
	   	}
	   	var intomx_ids = getCheckStr("ids");
	   	var intoId = "${intoId}";
	   	bootbox.confirm("确定要入库选中的记录吗?", function(result) {
			if(result) {
				top.jzts();
				modal("#layer_loading,#image");//点击票据管理按钮，弹出‘加载中’的遮罩层
				$.ajax({
					type: "POST",
					url: '<%=basePath%>intoStorageController.do?method=storage',
			    	data: {'intomx_ids': intomx_ids,'intoId': intoId},
					dataType:'json',
					cache: false,
					success: function(data){	
						if (data.success){  //入库成功
	                        dataCollectForm.action = "<%=basePath%>intoStorageController.do?method=storageBatchList"
	                        dataCollectForm.submit();
						} else {
							top.hangge();
							bootbox.alert("入库失败!"); 
						}
					}
			  });
		  	}
	   	});
	}
	//详情页面
	function goBillDetail(intomxId){
	   	 var diag = new top.Dialog();
		 diag.Drag = true;
		 diag.Title ="详情";
		 diag.URL = "<%=basePath%>intoApplyController.do?method=goBillDetail&intomxId="+intomxId;
		 diag.Width = 900;
		 diag.Height = 260;
		 diag.CancelEvent = function(){ //关闭事件
			diag.close();
		 };
		 diag.show();
	}
</script>
</body>
</html>