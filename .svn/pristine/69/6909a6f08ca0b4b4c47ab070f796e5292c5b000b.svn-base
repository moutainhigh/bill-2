<%-- 
 * 文件名称: disc_elec_apply_receive_bill.jsp
 * 系统名称: 票据管理平台
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: @version2.0.0.1
 * 开发人员: cgc
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
				<form action="<%=basePath%>discApplyController.do?method=electricReceive"  method="post" name="receiveForm" id="receiveForm" class="form-search">
					<%-- 查询区  --%>
						<div class="row-fluid">
							<label class="no-padding-right" for="acctNo"><span class="star">*</span>客户账号</label>
							<input type="text" id="custAcctNo" name="custAcctNo" class="input-medium" valid="required" value="${acctNo}" placeholder="请输入客户账号"/>
							<a class="btn-mini" id="search" onclick="fill();">填充</a>
							<label class="no-padding-right" for="custNo">客户号</label>
							<input readonly="true" type="text" class="input-medium" id="custNo" name="custNos" valid="required" value="${custNo}"/>
							<label class="no-padding-right" for="custName">客户名称</label>
							<input readonly="true" class="input-medium" type="text" id="custName" name="custName" valid="required" value="${custName}"/>
							<input readonly="true" class="hide" type="hidden" id="custAcctType" name="custAcctType"/>
						</div>
					</form>
				</div>
				<%-- 按钮操作区 --%>
				<div id="center">
					<form  id="btnForm" name="btnForm" action="<%=basePath%>discApplyController.do?method=elecBatchList" method="post">
						<div class="row-fluid">
							<div class="span6" id="btn-left" >
							   <a class="btn-mini" onclick="receive();">接收</a>
							   <a class="btn-mini" onclick="noReceive();">拒绝</a>
						   </div>
						   <div class="span6" id="btn-right">
						   </div>
					  	</div>
					 </form>
				 </div>
	   			 <%-- /按钮操作区 --%>
    			 <div id="footer">
				 <table class="table table-striped table-bordered table-hover" id="tab" cellpadding="0" cellspacing="0" style="min-width:100%;max-width:1600px;width:1600px;">
					<thead>
						<tr>
							<th class="center" onclick="selectAll('zcheckbox', 'ids')">
								<input type="checkbox" id="zcheckbox" /><span class="lbl"></span>
							</th>
							<th class="center">票号</th>
							<th class="center">入账账号</th>
							<th class="center">入账行号</th>
							<th class="center">票据类型</th>
							<th class="center">出票日</th>
							<th class="center">票面到期日</th>
							<th class="center">贴现日</th>
							<th class="center">利率</th>
							<th class="center">票面金额</th>
							<th class="center">不得转让标记</th>
							<th class="center">报文实付金额</th>
							<th class="center">承兑行</th>
							<th class="center">出票人</th>
							<th class="center">收款人</th>
							<th class="center">详情</th>
						</tr>
					</thead>
					<tbody id="dataBody">
						<c:choose>
								<c:when test="${not empty resultList}">
									<c:forEach items="${resultList}" var="disc" varStatus="vs">
										<tr>
											<td class="center"><input type="checkbox" name="ids" value="${disc.discmxId}" onclick="getall('ids')" price="${disc.billMoney}"/></td>
											<td class="center">${disc.billNo}</td>
											<td class="center">${disc.inAcctNo}</td>
											<td class="center">${disc.inBankNo}</td>
											<td class="center">${fns:getDictLabel('K_BILL_TYPE',disc.billType)}</td>
											<td class="center">${disc.issueDt}</td>
											<td class="center">${disc.dueDt}</td>
											<td class="center">${disc.discDt}</td>
											<td class="center">${disc.rate}</td>
											<td class="center">${fns:formateMoney(disc.billMoney)}</td>
											<td class="center">${fns:getDictLabel('K_FORBIDFLAG',disc.forbidFlag)}</td>
											<td class="center">${fns:formateMoney(disc.draftPayMoney)}</td>
											<td class="center">${disc.acceptor}</td>
											<td class="center">${disc.remitter}</td>
											<td class="center">${disc.payee}</td>
											<td class="center"><a href="javascript:goDetail('${disc.rgctId}')">查看</a></td>
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
					<form  action="#" name="dataCollectForm" method="post"></form>
					<div id="selectInfo"><center>暂时没有相关数据</center></div>
				</div>
				<%-- /列表操作区 --%>
				<div id="page" class="pagination hid">
					<form action="<%=basePath%>discApplyController.do?method=electricReceive"  name="pageForm" method="post" >
						<%@ include file="/webpage/system/admin/pageFormContent.jsp"%>
						<%-- <input type="hidden" name="custNo" value="${custNo}"/> --%>
						<input type="hidden" name="custAcctNo" value="${acctNo}"/>
						<input type="hidden" name="orgCode" value="${orgCode}"/> 
					</form>
				</div>
			</div>
		</div>
<%@ include file="/webpage/system/admin/modalDialog.jsp"%> 
<%@ include file="/webpage/system/admin/footer.jsp"%>
<script>
    var custAcctNo = $("#custAcctNo").val();
    if(custAcctNo!=""){
        $(".hid").show();
    }else{
	    $(".hid").hide();
    }
    //生成批次
    function addBatch(ids,rate,discDt,billType,orgCode){
         var custAcctNo = $("#custAcctNo").val();
         dynamicHiddenElement('dataCollectForm','custAcctNo',custAcctNo);
		 dynamicHiddenElement('dataCollectForm','ids',ids);
		 dynamicHiddenElement('dataCollectForm','rate',rate);
		 dynamicHiddenElement('dataCollectForm','discDt',discDt);
		 dynamicHiddenElement('dataCollectForm','billType',billType);
		 dynamicHiddenElement('dataCollectForm','orgCode',orgCode);
		 modal("#layer_loading,#image");
		 dataCollectForm.action = "<%=basePath%>discApplyController.do?method=receiveAddBatch";
		 dataCollectForm.submit();
    }
	//详情页面
	function goDetail(rgctId){
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
	//电票接受
	function receive(){
		 var checkNum = getCheckNum("ids");
	   	 if (checkNum == 0){
	   		bootbox.alert("请先选择要接收的记录");
	   		return;
	   	 }
		var ids = getCheckStr("ids");
		bootbox.confirm("确定要接收选中的记录吗?", function(result) {
		if(result){
			$.ajax({
					type: "POST",
					url: '<%=basePath%>discApplyController.do?method=checkElecBill',
			    	data: {'ids': ids},
					dataType:'json',
					//beforeSend: validateData,
					cache: false,
					success: function(data){	
						if(data.success){  //处理成功
							 addBatch(ids,data.attributes.rate,data.attributes.discDt,data.attributes.billType,data.attributes.orgCode);
						} else {
							top.hangge();
							bootbox.alert(data.msg); 
						}
						
					}
				});
			}
		});
	}
	//电票拒绝
	function noReceive(){
		 var checkNum = getCheckNum("ids");
	   	 if (checkNum <= 0){
	   		bootbox.alert("请先选择要拒收的记录");
	   		return;
	   	 }
	   	var custAcctNo = $("#custAcctNo").val();
	   	var custNo = $("#custNo").val();
	   	var custName = $("#custName").val();
		var discNos = getCheckStr("ids");
		bootbox.confirm("确定要拒收选中的记录吗?", function(result) {
		if(result){
			modal("#layer_loading,#image");
			$.ajax({
					type: "POST",
					url: '<%=basePath%>discApplyController.do?method=noReceive',
			    	data: {'discNos': discNos},
					dataType:'json',
					//beforeSend: validateData,
					cache: false,
					success: function(data){	
						if (data.success){  //处理成功
						    dynamicHiddenElement('dataCollectForm','custAcctNo',custAcctNo);
						    dynamicHiddenElement('dataCollectForm','custNos',custNo);
                            dynamicHiddenElement('dataCollectForm','custName',custName);
		                    dataCollectForm.action = "<%=basePath%>discApplyController.do?method=electricReceive";
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
	$("#tab th,td").addClass("center");
	$(function(){
		var acctNo = "${acctNo}";
		if(""!=acctNo && acctNo!=null){
			$.ajax({
				type: "POST",
				url: "<%=basePath%>custInfoController.do?method=custInfo",
		    	data: {'custAcctNo':acctNo},
				dataType:'json',
				success: function(data){
					if(data.success){  //处理成功
						$("#custAcctNo").val(acctNo);
						$("#custNo").val(data.attributes.custNo);
						$("#custName").val(data.attributes.custName);
						
						fillBatch(1,5);
					}else{
						$("#custNo").val("");
						$("#custName").val("");
						bootbox.alert(data.msg); 
					}
				}
			});	
		 }
	});
	function fill(){ //根据客户账号查询客户信息并填充
		var custAcctNo = $('#custAcctNo').val();
		$.ajax({
			type: "POST",
			url: "<%=basePath%>custInfoController.do?method=custInfo",
	    	data: {'custAcctNo': custAcctNo},
			dataType:'json',
			beforeSend:function(){
				if(custAcctNo==null||custAcctNo==""){
					bootbox.alert("请输入客户账号");
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
					modal("#layer_loading,#image")
					//dynamicHiddenElement('receiveForm','custNo',data.attributes.custNo);
					dynamicHiddenElement('receiveForm','orgCode',data.attributes.orgCode);
					$("#receiveForm").submit();
				}else{
					$("#custNo").val("");
					$("#custName").val("");
					bootbox.alert(data.msg);
					var trHtml = "<tr><td colspan=\"100\" class=\"center\">没有相关数据</td></tr>";
					$("#dataBody").html(trHtml);$("#page").html("");
				}
			}
		});
	}
	function reset(){
		resetForm('custForm');
		var trHtml = "<tr><td colspan=\"100\" class=\"center\">没有相关数据</td></tr>";
		$("#dataBody").html(trHtml);
		$("#page").html("");
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