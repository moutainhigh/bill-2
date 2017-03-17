<%-- 
 * 文件名称: collate_elec_apply_bill_manage.jsp
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
			<form action="<%=basePath%>intoApplyController.do?method=searchElecBill"  method="post" name="custForm" id="custForm" class="form-search">
				<%-- 查询区  --%>
				<div class="row-fluid">
					<label class="no-padding-right" for="custNo"><span class="star">*</span>客户号</label>
					<input type="text" class="input-medium" id="custNo" name="custNo" valid="required" value="${query.custNo}" placeholder="请输入客户号"/>
					<a class="btn-mini" id="search" onclick="fill();">填充</a>
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
					    <a class="btn-mini" onclick="toCheck();">接收</a>
					    <a class="btn-mini" onclick="refuse();">拒收</a>
				    </div>
				    <div class="span6" id="btn-right"></div>
		  		</div>
		 	</form>
		 </div>
	    <div id="footer">
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
						<th class="center">承兑人</th>
						<th class="center">出票人</th>
						<th class="center">详情</th>
					</tr>
				</thead>
				<tbody id="dataBody">
					<c:choose>
							<c:when test="${not empty resultList}">
								<c:forEach items="${resultList}" var="into" varStatus="vs">
									<tr>
										<td class="center" style="width: 30px;">
										<input type='checkbox' name='ids' value="${into.intomxId}" /><span class="lbl"></span></td>
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
				<div id="page" class="pagination hid">
						<form action="<%=basePath%>intoApplyController.do?method=searchElecBill" name="pageForm" method="post">
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
				url: "<%=basePath%>intoApplyController.do?method=custInfo",
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
	//检查所勾选票据
	function toCheck(){
		 var checkNum = getCheckNum("ids");
	   	 if (checkNum == 0){
	   		bootbox.alert("请选择电票进行接收");
	   		return;
	   	 }
		var ids = getCheckStr("ids");
		bootbox.confirm("是否确定是要接收的电票吗?", function(result) {
			if(result){
				modal("#layer_loading,#image");
				$.ajax({
					type: "POST",
					url: '<%=basePath%>intoApplyController.do?method=elecCheckBill',
			    	data: {'ids': ids},
					dataType:'json',
					//beforeSend: validateData,
					cache: false,
					success: function(data){	
						if(data.success){  //处理成功
							toSign(ids,data.attributes.billType);
						} else {
							top.hangge();
							bootbox.alert(data.msg); 
						}
						
					}
				});
			}
		});
	}
	//签收页面
	function toSign(ids,billType){
		 modal("#layer_loading,#image");
		 dynamicHiddenElement('dataCollectForm','ids',ids);
		 dynamicHiddenElement('dataCollectForm','custNo',$("#custNo").val());
		 dynamicHiddenElement('dataCollectForm','custName',$("#custName").val());
		 dynamicHiddenElement('dataCollectForm','billType',billType);
		 dataCollectForm.action = "<%=basePath%>intoApplyController.do?method=elecSignBill"
		 dataCollectForm.submit();
   }
	//电票拒绝
	function refuse(){
		 var checkNum = getCheckNum("ids");
	   	 if (checkNum <= 0){
	   		bootbox.alert("请选择要拒收的电票");
	   		return;
	   	 }
		 var ids = getCheckStr("ids");
		 var custNo = $("#custNo").val();
	   	 var custName = $("#custName").val();
	  	 var diag = new top.Dialog();
		 diag.Drag = true;
		 diag.Title ="拒收意见";
		 diag.URL = "<%=basePath%>intoApplyController.do?method=elecRefuseRemark&ids="+ids;
		 diag.Width = 430;
		 diag.Height =200;
		 diag.CancelEvent = function(){ //关闭事件
		 diag.close();
         dataCollectForm.action = "<%=basePath%>intoApplyController.do?method=searchElecBill&custNo="+custNo+"&custName="+custName
         dataCollectForm.submit();
		 };
		 diag.show();
	}
	function refuse1(){
		 var checkNum = getCheckNum("ids");
	   	 if (checkNum <= 0){
	   		bootbox.alert("请选择要拒收的电票");
	   		return;
	   	 }
	   	var custNo = $("#custNo").val();
	   	var custName = $("#custName").val();
		var ids = getCheckStr("ids");
		bootbox.confirm("是否确定要拒收电票吗?", function(result) {
		if(result){
			$.ajax({
					type: "POST",
					url: '<%=basePath%>intoApplyController.do?method=elecRefuseBill',
			    	data: {'ids': ids},
					dataType:'json',
					//beforeSend: validateData,
					cache: false,
					success: function(data){	
						if (data.success){  //处理成功
						    dynamicHiddenElement('dataCollectForm','custNo',custNo);
                            dynamicHiddenElement('dataCollectForm','custName',custName);
		                    dataCollectForm.action = "<%=basePath%>intoApplyController.do?method=searchElecBill";
		                    dataCollectForm.submit();
						} else {
							top.hangge();
							bootbox.alert("拒绝失败!"); 
						}
						
					}
				});
			}
		
		});
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
	function fill(){ //根据客户号查询客户信息并填充
			var custNo = $("#custNo").val();
			$.ajax({
				type: "POST",
				url: "<%=basePath%>intoApplyController.do?method=custInfo",
		    	data: {'custNo': custNo},
				dataType:'json',
				beforeSend:function(){
					if(custNo==null||custNo==""){
						bootbox.alert("请输入客户号");
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