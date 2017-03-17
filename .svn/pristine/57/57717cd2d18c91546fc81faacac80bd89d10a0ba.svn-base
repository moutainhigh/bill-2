<%-- 
 * 文件名称: assu_elec_apply_billinfo_manage.jsp
 * 系统名称: 票据管理平台
 * 模块名称: 可保证票据查询
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: @version2.0.0.1
 * 开发人员: dq
 * 开发时间: 2016-11-17
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
			<form action="<%=basePath%>assuApplyController.do?method=searchElecBill"  method="post" name="custForm" id="custForm" class="form-search">
				<%-- 查询区  --%>
				<div class="row-fluid">
					<label class="no-padding-right" for="custNo"><span class="star">*</span>客户号</label>
					<input type="text" class="input-medium" id="custNo" name="custNo" valid="required" value="${custNo}" placeholder="请输入客户号"/>
					<a class="btn-mini" id="search" onclick="fill();">填充</a>
					<label class="no-padding-right" for="custName"><span class="star">*</span>客户名称</label>
					<input readonly="readonly" class="input-medium" type="text" id="custName" name="custName" valid="required" value="${custName}"/>
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
			<table class="table table-striped table-bordered table-hover" id="tab" cellpadding="0" cellspacing="0" style="min-width:100%;width:1600px;max-width:1600px;">
				<thead>
					<tr>
						<th class="center" onclick="selectAll('zcheckbox', 'ids')">
							<input type="checkbox" id="zcheckbox" /><span class="lbl"></span>
						</th>
						<th class="center">票号</th>
						<th class="center">票据类型</th>
						<th class="center">票据品种</th>
						<th class="center">保证类型</th>
						<th class="center">出票日</th>
						<th class="center">票面到期日</th>
						<th class="center">票面金额</th>
						<th class="center">承兑人</th>
						<th class="center">保证人</th>
						<th class="center">承兑人开户行行号</th>
						<th class="center">详情</th>
					</tr>
				</thead>
				<tbody id="dataBody">
					<c:choose>
							<c:when test="${not empty resultList}">
								<c:forEach items="${resultList}" var="assu" varStatus="vs">
									<tr>
										<td class="center">
										<input type='checkbox' name='ids' value="${assu.assumxId}" onclick="getall('ids')" price="${assu.billMoney}"/><span class="lbl"></span></td>
										<td class="center">${assu.billNo}</td>
										<td class="center">${fns:getDictLabel('K_BILL_TYPE',assu.billType)}</td>
										<td class="center">${fns:getDictLabel('K_BILL_CALSS',assu.billType)}</td>
										<td class="center">${fns:getDictLabel('K_ASSU_TYPE',assu.assuType)}</td>
										<td class="center">${assu.issueDt}</td>
										<td class="center">${assu.dueDt}</td>
										<td class="center">${fns:formateMoney(assu.billMoney)}</td>
										<td class="center">${assu.acceptor}</td>
										<td class="center">${assu.guartrName}</td>
										<td class="center">${assu.acceptorBankNo}</td>
										<td class="center"><a href="javascript:goBillDetail('${assu.rgctId}')">查看</a></td>
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
			<div id="select-Info">
				<div id="selectInfo"><center>暂时没有相关数据</center></div>
			</div>
			<form action="#" name="dataCollectForm" method="post"></form>
			<%-- /列表操作区 --%>
			<div>
				<div id="page" class="pagination hid">
						<form action="<%=basePath%>assuApplyController.do?method=searchElecBill" name="pageForm" method="post">
						<%@ include file="/webpage/system/admin/pageFormContent.jsp"%>
						<input type="hidden" name="custNo" value="${custNo}"/>
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
				url: '<%=basePath%>assuApplyController.do?method=checkuser',
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
	   		bootbox.alert("请先选择要接收的记录!");
	   		return;
	   	 }
		var ids = getCheckStr("ids");
				modal("#layer_loading,#image");
				$.ajax({
					type: "POST",
					url: '<%=basePath%>assuApplyController.do?method=elecCheckBill',
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
	//签收页面
	function toSign(ids,billType){
		 modal("#layer_loading,#image");
		 dynamicHiddenElement('dataCollectForm','ids',ids);
		 dynamicHiddenElement('dataCollectForm','custNo',$("#custNo").val());
		 dynamicHiddenElement('dataCollectForm','custName',$("#custName").val());
		 dataCollectForm.action = "<%=basePath%>assuApplyController.do?method=elecSignBill";
		 dataCollectForm.submit();
   }
	//拒绝
	function refuse(){
		 var checkNum = getCheckNum("ids");
	   	 if (checkNum <= 0){
	   		bootbox.alert("请先选择要拒收的记录!");
	   		return;
	   	 }
	   	var custNo = $("#custNo").val();
		var ids = getCheckStr("ids");
		bootbox.confirm("确定要拒收选中的记录吗?", function(result) {
			if(result){
	            dynamicHiddenElement('dataCollectForm','ids',ids);
				dynamicHiddenElement('dataCollectForm','custNo',custNo);
	            dataCollectForm.action = "<%=basePath%>assuApplyController.do?method=elecRefuseBill";
	            dataCollectForm.submit();
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
			var custNo = $('#custNo').val();
			$.ajax({
				type: "POST",
				url: "<%=basePath%>assuApplyController.do?method=checkuser",
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