<%-- 
 * 文件名称: rebuy_batch_apply_entity_outer.jsp
 * 系统名称: 票据管理平台
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: @version2.0.0.1
 * 开发人员: sherry
 * 开发时间: 2016-9-10
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
				<form action="<%=basePath%>rebuyApplyController.do?method=searchBatch"  method="post" name="bankForm" id="bankForm" class="form-search">
					<%-- 查询区  --%>
					<div class="row-fluid">
						<label class="no-padding-right" for="custType"><span class="star">*</span>客户类型</label>
						<input type="hidden" name="isInner" id="isInner" value="${searchBean.isInner}"></input>
						<select class="select2" class="input-medium" name="custType" id="custType" onchange="changeCustType();">
								<option value="-1">请选择</option>
								<option value="1" selected="true">同业</option>
								<option value="2">系统内</option>
						</select>
						<label class="no-padding-right" for="custBankNo" id="bankNoLabel"><span class="star">*</span>联行行号</label>
						<input type="text" id="custBankNo" name="custBankNo" value="${searchBean.custBankNo}" class="input-medium" valid="required"placeholder="请输入联行行号" />
						<a class="btn-mini" id="search" onclick="fill();">填充</a>
						<label class="no-padding-right" id="bankNameLabel" for="custBankName"><span class="star">*</span>同业名称</label>
						<input readonly="true" class="input-medium" type="text" value="${searchBean.custBankName}" id="custBankName" name="custBankName" valid="required"/>
					</div>
					</form>
				</div>
				<%-- 按钮操作区 --%>
				<div id="center">
					<form  id="btnForm">
						<div class="row-fluid">
							<div class="span6" id="btn-left">
							    <a class="btn-mini" href="javascript:add();">新增</a>
								<a class="btn-mini" href="javascript:edit();">修改</a>
								<a class="btn-mini" href="javascript:del();">删除</a>
								<a class="btn-mini" href="javascript:billManage();">票据管理</a>
						   </div>
						   <div class="span6" id="btn-right">
						   </div>
					  </div>
				</form>
			</div>
	    	<%-- /按钮操作区 --%>
		    <div id="footer">
				<table class="table table-striped table-bordered table-hover" id="tab" cellpadding="0" cellspacing="0">
					<thead>
						<tr>
							<th class="center" onclick="selectAll('allcheck', 'rebuyId')">
								<input type="checkbox" id="allcheck" /><span class="lbl"></span>
							</th>
							<th class="center">批次号</th>
							<th class="center">票据类型</th>
							<th class="center">票据品种</th>
							<th class="center">产品类型</th>		
							<th class="center">合计张数</th>
							<th class="center">合计金额</th>
							<th class="center">申请创建日</th>
							<th class="center">利率</th>
							<th class="center">入账账号</th>
							<th class="center">账户名称</th>
							<th class="center">操作</th>
						</tr>
					</thead>
					<tbody id="dataBody">
						<c:choose>
							<c:when test="${not empty batchList}">
								<c:forEach items="${batchList}" var="batchList" varStatus="vs">
									<tr>
										<td class="center">
											<input type='checkbox' name='rebuyId' value="${batchList.rebuyId}" /><span class="lbl"></span>
										</td>
										<td class="center">${batchList.batchNo}</td>
										<td class="center">${fns:getDictLabel('K_BILL_TYPE',batchList.billType)}</td>
										<td class="center">${fns:getDictLabel('K_BILL_CLASS',batchList.billClass)}</td>
										<td class="center">${fns:getDictLabel('K_ISREGRESS',batchList.discType)}</td>
										<td class="center">${batchList.totalNum}</td>
										<td class="center">${fns:formateMoney(batchList.totalMoney)}</td>
										<td class="center">${batchList.createDt}</td>
										<td class="center">${batchList.rate}</td>
										<td class="center">${batchList.tradeAcct}</td>
										<td class="center">${batchList.tradeAcctName}</td>
										<td class="center">
											<a onclick="batchDetail('${batchList.rebuyId}');" style="cursor:pointer">详细</a>
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
			 <form  action="#" name="dataCollectForm" method="post"></form>
			 <%-- /列表操作区 --%>
			 <div>
				<div id="page" class="pagination">
					<form action="<%=basePath%>rebuyApplyController.do?method=searchBatch"  name="pageForm" method="post">
						<%@ include file="/webpage/system/admin/pageFormContent.jsp"%>
						<input type="hidden" name="isInner" value="${searchBean.isInner}"></input>
						<input type="hidden" name="custBankNo" value="${searchBean.custBankNo}"></input>
						<input type="hidden" name="custBankName" value="${searchBean.custBankName}"></input>
					</form>
				</div>
			</div>
		</div>
	</div>
<%@ include file="/webpage/system/admin/modalDialog.jsp"%>	
<%@ include file="/webpage/system/admin/footer.jsp"%> 
<script>
	$("#tab th,td").addClass("center");
	$(function(){
		var custBankNo = "${searchBean.custBankNo}";
		var custBankName = "${searchBean.custBankName}";
		$("#custBankNo").val(custBankNo);
		$("#custBankName").val(custBankName);
	}); 
	//客户类型下拉框修改事件
	function changeCustType(){
		var custType = $("#custType").val();
		dynamicHiddenElement('dataCollectForm','custType',custType);
		dataCollectForm.action = "<%=basePath%>rebuyApplyController.do?method=apply";
		dataCollectForm.submit();
	}
	//根据联行行号查询银行信息并填充
	function fill(){ 
		var custBankNo = $("#custBankNo").val();
		$.ajax({
			type: "POST",
			url: "<%=basePath%>rebuyApplyController.do?method=bankInfo",
	    	data: {'custBankNo': custBankNo},
			dataType:'json',
			beforeSend:function(){
				if(custBankNo==null||custBankNo==""){
					bootbox.alert("请输入联行行号");
					$("#custBankName").val("");
			   		return false;
				}
			},
			cache: false,
			success: function(data){	
				if(data.success){  //处理成功
					$("#custBankName").val(data.attributes.custBankName);
					modal("#layer_loading,#image");
					bankForm.submit();
				}else{
					$("#custBankName").val("");
					bootbox.alert(data.msg);
				}
			}
		});
	}
	//新增
	function add(){
		var bankNo=$("#custBankNo").val();
		var bankName=$("#custBankName").val();
		if (bankNo==null||bankNo==""||bankName==null||bankName==""){
	   		bootbox.alert("请输入正确的联行信息并填充");
	   		return;
	   	 }
		var diag = new top.Dialog();
		 diag.Drag = true;
		 diag.Title ="新增";
		 diag.URL = "<%=basePath%>rebuyApplyController.do?method=toAddBatch&bankNo="+bankNo;
		 diag.Width = 760;
		 diag.Height = 560;
		 diag.CancelEvent = function(){ //关闭事件
			diag.close();
			bankForm.submit();
		 };
		 diag.show(); 
	}
	//编辑
	function edit(){
		 var checkNum = getCheckNum("rebuyId");
		 if (checkNum ==0){
		   		bootbox.alert("请先选择要修改的记录");
		   		return;
		   	 }
		 if (checkNum !=1){
	   		bootbox.alert("一次只能对一个批次进行修改");
	   		return;
	   	 }
	   	 var rebuyId = getCheckStr("rebuyId");
	   	 var diag = new top.Dialog();
		 diag.Drag = true;
		 diag.Title ="修改";
		 diag.URL = "<%=basePath%>rebuyApplyController.do?method=toEditBatch&rebuyId="+rebuyId;
		 diag.Width = 760;
		 diag.Height = 560;
		 diag.CancelEvent = function(){ //关闭事件
			diag.close();
			bankForm.submit();
		 };
		 diag.show(); 
	}
	//删除 
	function del(){
		var checkNum = getCheckNum("rebuyId");
		if (checkNum == 0){
	   		bootbox.alert("请先选择要删除的记录");
	   		return;
	   	}
	   	var rebuyIds = getCheckStr("rebuyId");
	   	bootbox.confirm("确定要删除选中的记录吗?", function(result) {
	   		if(result){
	   			$.ajax({
					type: "POST",
					url: "<%=basePath%>rebuyApplyController.do?method=delBatch",
			    	data: {'rebuyIds': rebuyIds},
					dataType:'json',
					cache: false,
					success: function(data){	
						if(data.success){  //处理成功
							bankForm.submit();
						}else{
							bootbox.alert(data.msg);
						}
					}
				});
	   		}
	   	});
	}
	function billManage(){
		var checkNum = getCheckNum("rebuyId");
		if (checkNum ==0){
	   		bootbox.alert("请先选择要进行票据管理的记录");
	   		return;
	   	}
		if (checkNum !=1){
	   		bootbox.alert("一次只能对一个批次进行票据管理");
	   		return;
	   	}
	   	modal("#layer_loading,#image");
	   	var rebuyId = getCheckStr("rebuyId");	
		dynamicHiddenElement('dataCollectForm','isInner','${searchBean.isInner}');
		dynamicHiddenElement('dataCollectForm','rebuyId',rebuyId);
		dataCollectForm.action = "<%=basePath%>rebuyApplyController.do?method=billManage";
		dataCollectForm.submit();
	}
	function batchDetail(rebuyId){
		var diag = new top.Dialog();
		 diag.Drag = true;
		 diag.Title ="详情";
		 diag.URL = '<%=basePath%>rebuyApplyController.do?method=goBatchDetail&rebuyId='+rebuyId;
		 diag.Width = 800;
		 diag.Height = 400;
		 diag.CancelEvent = function(){ //关闭事件
			diag.close();
		 };
		 diag.show();
	}
</script>
</body>
</html>