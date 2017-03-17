<%-- 
 * 文件名称: disc_apply_batch_list.jsp
 * 系统名称: 票据管理平台
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
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
			<div id="header">
				<form action="<%=basePath%>discApplyController.do?method=searchBatch"  method="post" name="searchForm" id="searchForm" class="form-search">
					<%-- 查询区  --%>
					<div class="row-fluid">
						<label class="no-padding-right" for="acctNo"><span class="star">*</span>客户账号</label>
						<input type="text" id="custAcctNo" name="acctNo" class="input-medium" valid="required" value="${query.acctNo}" placeholder="请输入客户账号"/>
						<a class="btn-mini"  id="search" onclick="fill();">填充</a>
						<label class="no-padding-right" for="custNo">客户号</label>
						<input readonly="true" type="text" class="input-medium" id="custNo" name="custNo" valid="required" value="${query.custNo}"/>
						<label class="no-padding-right" for="custName">客户名称</label>
						<input readonly="true" class="input-medium" type="text" id="custName" name="custName" valid="required" value="${custName}"/>
						<input readonly="true" class="hide" type="text" id="custAcctType" name="custAcctType"/>
					</div>
				</form>
			</div>
			<%-- 按钮操作区 --%>
			<div id="center">
				<form  id="btnForm">
					<div class="row-fluid">
						<div class="span6" id="btn-left" >
						    <a class="btn-mini" onclick="addBatch();">新增</a>
							<a class="btn-mini" href="javascript:toEditBatchCheck();">修改</a>
							<a class="btn-mini" href="javascript:delBatch();">删除</a>
							<a class="btn-mini" href="javascript:updateBillInfo();">票据管理</a>
					   </div>
					   <div class="span6" id="btn-right"></div>
				  	</div>
				 </form>
			 </div>
   		 	 <%-- /按钮操作区 --%>
		     <div id="footer">
				<table class="table table-striped table-bordered table-hover" id="tab" cellpadding="0" cellspacing="0" style="min-width:100%;max-width:1600px;width:1600px;">
					<thead>
						<tr>
							<th>
								<input type="checkbox" name="allcheck" id="allcheck" onclick="selectAll('allcheck','discId')"/>
							</th>
							<th>批次号</th>
							<th>客户名称</th>
							<th>票据类型</th>
							<th>票据品种</th>
							<th>付息方式</th>
							<th>贴现利率</th>
							<th>贴现日</th>
							<th>买方付息比例</th>
							<th>合计张数</th>
							<th>合计金额</th>
							<th>总利息</th>
							<th>实付金额</th>
							<th>申请生成日</th>
							<th>客户经理</th>
							<th>部门归属</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody id="dataBody">
						<c:choose>
							<c:when test="${not empty resultList}">
								<c:forEach items="${resultList}" var="batch" varStatus="vs">
									<tr>
										<td class="center">
											<input type='checkbox' name='discId' value="${batch.discId}" /><span class="lbl"></span>
										</td>
										<td class="center">${batch.batchNo}</td>
										<td class="center">${batch.custName}</td>
										<td class="center">${fns:getDictLabel('K_BILL_TYPE',batch.billType)}</td>
										<td class="center">${fns:getDictLabel('K_BILL_CLASS',batch.billClass)}</td>
										<td class="center">${fns:getDictLabel('K_PAY_TYPE',batch.payType)}</td>
										<td class="center">${batch.rate}</td>
										<td class="center">${batch.discDt}</td>
										<c:if test="${batch.payType=='1'}">
											<td class="center">100.0</td>
										</c:if>
										<c:if test="${batch.payType!='1'}">
											<td class="center">${batch.buyPayRate}</td>
										</c:if>		
										<td class="center">${batch.totalNum}</td>
										<td class="center">${fns:formateMoney(batch.totalMoney)}</td>
										<td class="center">${fns:formateMoney(batch.totalInterest)}</td>
										<td class="center">${fns:formateMoney(batch.actualAmount)}</td>
										<td class="center">${batch.createDt}</td>
										<td class="center">${batch.custManagerName}</td>
										<td class="center">${batch.deptName}</td>
										<td class="center"><a class="" href="javascript:goBatchInfo('${batch.discId}');">查看</a></td>
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
			<%-- /列表操作区 --%>
			<div>
				<div id="page" class="pagination">
					<form action="<%=basePath%>discApplyController.do?method=searchBatch" method="post" name="pageForm">
						<%@ include file="/webpage/system/admin/pageFormContent.jsp"%> 
						<input type="hidden" name="acctNo" value="${query.acctNo}"/>
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
	$("#tab th,td").addClass("center");
	$(function(){
		var acctNo = "${acctNo}";
		if(acctNo!=null && acctNo != ""){
			$.ajax({
				type: "POST",
				url: "<%=basePath%>custInfoController.do?method=custInfo",
		    	data: {'custAcctNo': acctNo},
				dataType:'json',
				success: function(data){	
					if(data.success){  //处理成功
						$("#custNo").val(data.attributes.custNo);
						$("#custName").val(data.attributes.custName);
					}
				}
			});
		 }
	});
	//新增
	function addBatch(){
		if($("#custAcctNo").val() == ""){
			bootbox.alert("请先输入客户号！");
			return false;
		}else{
			var custAcctNo=$("#custAcctNo").val();
			var diag = new top.Dialog();
			diag.Drag = true;
		 	diag.Title ="新增";
		 	diag.URL = "<%=basePath%>discApplyController.do?method=toAddBatch&custAcctNo="+custAcctNo;
		 	diag.Width = 750;
		 	diag.Height = 600;
		 	diag.CancelEvent = function(){ //关闭事件
			 	searchForm.submit();
			   	diag.close();
		 	};
		 	diag.show(); 
		 }
	}
	//编辑
	function toEditBatchCheck(){
		 var checkNum = getCheckNum("discId");
	   	 if (checkNum ==0){
	   		bootbox.alert("请先选择要修改的记录");
	   		return;
	   	 }
	   	  if (checkNum !=1){
	   		bootbox.alert("一次只能对一个批次进行修改");
	   		return;
	   	 }
	   	 var discId = getCheckStr("discId");
	   	 $.ajax({
			type: "POST",
			url: "discApplyController.do?method=toEditBatchCheck",
	    	data:{'discId': discId},
	    	cache:false,
			dataType:'json',
			success:function(data){
				if(data.success) {  //处理成功
					var discId = getCheckStr("discId");
					var custAcctNo=$("#custAcctNo").val();
 	  				$("#_ButtonClose_0").css({'width':'27px','top':'4px','background-position': '-1px 0px'});
				 	var diag = new top.Dialog();
					diag.Drag = true;
					diag.Title ="修改";
					diag.URL = "<%=basePath%>discApplyController.do?method=toEditBatch&discId="+discId+"&custAcctNo="+custAcctNo;
					diag.Width = 750;
					diag.Height = 600;
					diag.CancelEvent = function(){ //关闭事件
						searchForm.submit();
						diag.close();
					};
					diag.show(); 
					}else{
						bootbox.alert("该批次还有票据没有处理，不能修改");
						return;
					}
				}
		  });
	}
	function discManage(){
	    var checkNum = getCheckNum("discId");
	   	 if (checkNum !=1){
	   		bootbox.alert("一次只能对一个批次进行票据管理");
	   		return;
	   	 }
	   	modal("#layer_loading,#image");//点击票据管理按钮，弹出‘加载中’的遮罩层
	   	var discId = getCheckStr("discId");	
	   	dynamicHiddenElement('searchForm','acctNo',$('#custAcctNo').val());
		dynamicHiddenElement('searchForm','discId',discId);
		dynamicHiddenElement('searchForm','custName',$('#custName').val());
		searchForm.action = "<%=basePath%>discApplyController.do?method=billManage";
		searchForm.submit();
	}    
	function fill(){ //根据客户账号查询客户信息并填充
		var custAcctNo = $('#custAcctNo').val();
		$.ajax({
			type: "POST",
			url: "<%=basePath%>custInfoController.do?method=custInfo",
	    	data: {'custAcctNo': custAcctNo},
			dataType:'json',
			beforeSend:function(){
				if(custAcctNo==null||custAcctNo==""){
					bootbox.alert("请先输入客户账号");
					$("#custNo").val("");
					$("#custName").val("");
			   		return false;
				}
			},
			cache: false,
			success: function(data){	
				if(data.success){  //处理成功
					$("#custNo").val(data.attributes.custNo);
					$("#custName").val(data.attributes.custName);
					modal("#layer_loading,#image");//点击票据管理按钮，弹出‘加载中’的遮罩层
					searchForm.submit();
				}else{
					$("#custNo").val("");
					$("#custName").val("");
					bootbox.alert(data.msg);
					var trHtml = "<tr><td colspan=\"100\" class=\"center\">没有相关数据</td></tr>";
					$("#dataBody").html(trHtml);
				}
			}
		});
	}
	//详情页面
	function goBatchInfo(discId){
	   	 var diag = new top.Dialog();
		 diag.Drag = true;
		 diag.Title ="详情";
		 diag.URL = '<%=basePath%>discApplyController.do?method=goBatchInfo&discId='+discId;
		 diag.Width = 800;
		 diag.Height = 585;
		 diag.CancelEvent = function(){ //关闭事件
			diag.close();
		 };
		 diag.show();
	}
	//删除批次 
	function delBatch(){
		var checkNum = getCheckNum("discId");
	   	if (checkNum <= 0){
	   		bootbox.alert("请先选择要删除的记录");
	   		return;
	   	}
	   	var discId = getCheckStr("discId");	
	   	bootbox.confirm("确定要删除选中的记录吗?", function(result) {
			if(result) {
				$.ajax({
					type: "POST",
					url: "<%=basePath%>discApplyController.do?method=delBatch",
			    	data:{'discId': discId},
			    	cache:false,
					dataType:'json',
					success:function(data){
						bootbox.alert(data.msg);
						if(data.success) {  //处理成功
							searchForm.submit();
						}
					}
				});
			}
		});
	}
	//更新利息的信息
	function updateBillInfo(){
		var checkNum = getCheckNum("discId");
	   	if (checkNum <= 0){
	   		bootbox.alert("请先选择要管理的记录");
	   		return;
	   	}
	   	var discId = getCheckStr("discId");	
		$.ajax({
			type: "POST",
			url: "<%=basePath%>discApplyController.do?method=updateBillInfo",
	    	data:{'discId': discId},
	    	cache:false,
			dataType:'json',
			success:function(data){
				if(data.success) {  //处理成功
					discManage();
				}
			}
	 	});
	}
</script>
</body>
</html>