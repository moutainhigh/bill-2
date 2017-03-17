<%-- 
 * 文件名称: collate_elec_sign_bill.jsp
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
				<form action="<%=basePath%>intoApplyController.do?method=elecAfterSaveBatch"	method="post" name="batchForm" id="batchForm" class="form-search">
				<input type="hidden" name="ids" id="ids" value="${ids}"/>
				<input type="hidden" name="intoId" id="intoId" value="${intoId}"/>
				<input type="hidden" name="batchNo" id="batchNo" value="${batchNo}"/>
				<input type="hidden" name="batchType" id="batchType" value="${batchType}"/>
				<input type="hidden" name="isedit" id="isedit" value="${isedit}"/>
				<div class="row-fluid">
						<label for="batchNo" class="pull-left">批次号</label>
						<div class="pull-left batch">${batch.batchNo}</div>
						<label for="tradeAcct" class="pull-left">批次类型</label>
							<t:dictSelect name="batchType" other=" width:159px;" dictGroup="K_BILL_TYPE" defaultVal="${batch.batchType}" haveHead="true" valid="required" disabled="disabled">
				            </t:dictSelect> 
						<label for="custNo" class="pull-left">客户号</label>
							<input type="text" class="input-medium" name="custNo" id="custNo" readonly value="${batch.custNo}" />
						<label for="custName" class="pull-left">客户名称</label>
							<input type="text" class="input-medium" name="custName" id="custName" value="${batch.custName}" readonly />
					</div>
					<div class="row-fluid">
							<label for="custManager" class="pull-left">客户经理编号</label>
								<input type="text" class="input-medium" name="custManager" value="${batch.custManager}" placeholder="请输入客户经理编号" id="custManagerNo" valid="required" onblur="fill();"/>
							<label for="param_value" class="pull-left">客户经理名称</label>
								<input type="text" class="input-medium" name="custManagerName" id="custManagerName" value="${batch.custManagerName}" readonly />
							<label for="param_value" class="pull-left">所属部门</label>
								<input type="text" class="input-medium" name="deptName" id="deptName" value="${batch.deptName}" readonly/>
							<label for="param_value" class="pull-left pdTop">产品名称</label>
					         <%--  <t:select id="prodNo" other="width:159px;" name="prodNo" list="prodList" listKey="prodName" listValue="prodNo" defaultVal="${batch.prodNo}">
					        </t:select> --%>
					        <input type="text" class="input-medium" value="存票" readonly="true"/>
					</div>
				</form>
			</div>
			<%-- 按钮操作区 --%>
			<div id="center">
				<form  id="btnForm">
					<div class="row-fluid" style="margin-bottom:0px;">
						<div class="span6" id="btn-left">
					   	</div> 
					   	<div class="span6" id="btn-right">
							<a class="btn-mini pull-right" href="javascript:elecApply();">提交</a>
							<a class="btn-mini pull-right" href="javascript:intoBatch();">保存</a>
							<a class="btn-mini pull-right" onclick="goHistory();">返回</a>
					   </div>
				  </div>
				</form>
			</div>
			<div id="footer" class="footer">
				<table class="table table-striped table-bordered table-hover" id="tab" cellpadding="0" cellspacing="0">
					<thead>
						<tr>
							<th class="center">票号</th>
							<th class="center">票据类型</th>
							<th class="center">出票日</th>
							<th class="center">票面到期日</th>
							<th class="center">票面金额</th>
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
										<input type="hidden" name="ids" value="${into.intomxId}"/>
										<td class="center">${into.billNo}</td>
										<td class="center">${fns:getDictLabel('K_BILL_TYPE',into.billType)}</td>
										<td class="center">${into.issueDt}</td>
										<td class="center">${into.dueDt}</td>
										<td class="center">${fns:formateMoney(into.billMoney)}</td>
										<td class="center">${into.acceptor}</td>
										<td class="center">${into.remitter}</td>
										<td class="center"><a href="javascript:goBillDetail('${into.rgctId}')">查看</a></td>
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
						<form action="<%=basePath%>intoApplyController.do?method=searchElecBill"  name="pageForm" method="post">
						<%@ include file="/webpage/system/admin/pageFormContent.jsp"%>
						
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
		modal("#layer_loading,#image");
		var custNo =$("#custNo").val();
		var custName =$("#custName").val();
		dynamicHiddenElement('dataCollectForm','custNo',custNo);
        dynamicHiddenElement('dataCollectForm','custName',custName);
        dataCollectForm.action="<%=basePath%>intoApplyController.do?method=searchElecBill";
        dataCollectForm.submit();
	}
    //批次保存
		function intoBatch(){
		 	if($("#batchForm").valid()){
	 		modal("#layer_loading,#image");
		 	$.ajax({
					type: "POST",
					url: '<%=basePath%>intoApplyController.do?method=elecSaveBatch',
			    	data: $('#batchForm').serialize(),
					dataType:'json',
					cache: false,
					success: function(data){	
						if(data.success){  //处理成功
							batchForm.submit();
						    /*bootbox.alert({
							    message:data.msg,
							    callback : function(){
							    	modal("#layer_loading,#image");
								    batchForm.submit();
						    	}
						    });*/
						} else {
							bootbox.alert(data.msg); 
						}
					}
				});
		 	}
		}
    //电票申请
	function elecApply(){
	   	if($("#batchForm").valid()){
			$.ajax({
				url:"<%=basePath%>intoApplyController.do?method=elecApply",
				type:"POST",
				data:$('#batchForm').serialize(),
				dataType:"json",
				cache: false,
				success:function(data){
					if(data.success){
						modal("#layer_loading,#image");
						var custNo =$("#custNo").val();
						var custName =$("#custName").val();
						dynamicHiddenElement('dataCollectForm','custNo',custNo);
	 	                dynamicHiddenElement('dataCollectForm','custName',custName);
						dataCollectForm.action = "<%=basePath%>intoApplyController.do?method=searchElecBill";
						dataCollectForm.submit();
					}else{
						bootbox.alert("提交失败");
					}
				}
			});
	   	}
	}
    //详情页面
	function goBillDetail(rgctId){
		var diag = new top.Dialog();
		 diag.Drag = true;
		 diag.Title ="详情";
		 diag.URL = "<%=basePath%>elecBillDetailController.do?method=goDetail&rgctId="+rgctId;
		 diag.Width = 930;
		 diag.Height = 575;
		 diag.CancelEvent = function(){ //关闭事件
			diag.close();
		 };
		 diag.show(); 
	}
	//根据客户经理编号查询信息并填充
	function fill(){ 
		var custManagerNo = $('#custManagerNo').val();
		$.ajax({
			type: "POST",
			url: "<%=basePath%>custManagerController.do?method=custMagInfo",
	    	data: {'custManagerNo': custManagerNo},
			dataType:'json',
			beforeSend:function(){
				if(custManagerNo==null||custManagerNo==""){
					bootbox.alert("请输入客户经理编号");
					$("#deptName").val("");
					$("#custManagerName").val("");
			   		return false;
				}
			},
			cache: false,
			success: function(data){	
				if(data.success){  //处理成功
					$("#deptName").val(data.attributes.deptName);
					$("#custManagerName").val(data.attributes.custManagerName);
				}else{
					$("#deptName").val("");
					$("#custManagerName").val("");
					bootbox.alert(data.msg);
					var trHtml = "<tr><td colspan=\"100\" class=\"center\">没有相关数据</td></tr>";
					$("#dataBody").html(trHtml);$("#page").html("");
				}
			}
		});
	}
</script>
</body>
</html>