<%-- 
 * 文件名称: rebuy_apply_receive_elec_inner.jsp
 * 系统名称: 票据管理平台
 * 模块名称: 系统内电票转入接收页面
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: @version2.0.0.1
 * 开发人员: sherry
 * 开发时间: 2016-10-18
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
				<form action="<%=basePath%>rebuyApplyController.do?method=searchInnerReceivableBill"  method="post" name="bankForm" id="bankForm" class="form-search">
					<%-- 查询区  --%>
					<div class="row-fluid">
						<label class="no-padding-right" for="custType"><span class="star">*</span>客户类型</label>
						<select class="select2 input-medium" name="custType" id="custType" onchange="changeCustType();">
							<option value="-1">请选择</option>
							<option value="1">同业</option>
							<option value="2" selected="true">系统内</option>
						</select>
						<label class="no-padding-right" for="brchNo" id="brchNoLabel">交易对手机构号</label>
						<input type="text" id="brchNo" name="brchNo" value="${brchNo}" class="input-medium"/>
						<a class="btn-mini" id="search" onclick="searchBills();">查询</a>
					</div>
				</form>
			</div>
			<%-- 按钮操作区 --%>
			<div id="center">
				<form  id="btnForm">
					<div class="row-fluid" style="margin-bottom:0px;">
						<div class="span6" id="btn-left">
						    <a class="btn-mini" href="javascript:receive();">接收</a>
							<a class="btn-mini" href="javascript:reject();">拒绝</a>
					    </div>
					    <div class="span6" id="btn-right">
					    </div>
				    </div>
				</form>
			</div>
		    <div id="footer">
					<table class="table table-striped table-bordered table-hover" style="min-width:100%;max-width:1800px;width:1800px;" id="tab" cellpadding="0" cellspacing="0">
						<thead>
							<tr>
								<th class="center" onclick="selectAll('allcheck', 'rebuymxId')">
									<input type="checkbox" id="allcheck" /><span class="lbl"></span>
								</th>
								<th class="center">票号</th>
								<th class="center">票据类型</th>
								<th class="center">是否央行卖票</th>
								<th class="center">转入类型</th>
								<th class="center">清算方式</th>
								<th class="center">利率</th>
								<th class="center">转入日</th>
								<th class="center">赎回开放日</th>
								<th class="center">赎回截止日</th>
								<th class="center">票面到期日</th>
								<th class="center">票面金额</th>
								<th class="center">报文实付金额</th>
								<th class="center">承兑人</th>
								<th class="center">出票人</th>
								<th class="center">详细</th>
							</tr>
						</thead>
						<tbody id="dataBody">
							<c:choose>
								<c:when test="${not empty billList}">
									<c:forEach items="${billList}" var="billList" varStatus="vs">
										<tr>
											<td class="center">
												<label><input type='checkbox' name='rebuymxId' value="${billList.rebuymxId}" onclick="getall('rebuymxId')" price="${billList.billMoney}"/><span class="lbl"></span></label>
											</td>
											<td class="center">${billList.billNo}</td>
											<td class="center">${fns:getDictLabel('K_BILL_TYPE',billList.billType)}</td>
											<td class="center">${fns:getDictLabel('K_YORN',billList.isRediscCenter)}</td>
											<td class="center">${fns:getDictLabel('K_ISREGRESS',billList.isRegress)}</td>
											<td class="center">${fns:getDictLabel('K_ISONLINE',billList.isOnline)}</td>
											<td class="center">${billList.rate}</td>
											<td class="center">${billList.rebuyDt}</td>
											<td class="center">${billList.resaleStaDt}</td>
											<td class="center">${billList.resaleDueDt}</td>
											<td class="center">${billList.dueDt}</td>
											<td class="center">${fns:formateMoney(billList.billMoney)}</td>
											<td class="center">${fns:formateMoney(billList.payMoney)}</td>
											<td class="center">${billList.acceptor}</td>
											<td class="center">${billList.remitter}</td>
											<td class="center"><a onclick="goDetail('${billList.rgctId}');" style="cursor:pointer">详细</a></td>
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
					<form  action="#" name="dataCollectForm" method="post"></form>
				</div>
			 <%-- /列表操作区 --%>
			 <div>
				<div id="page" class="pagination">
					<form action="<%=basePath%>rebuyApplyController.do?method=searchInnerReceivableBill"  name="pageForm" method="post">
						<%@ include file="/webpage/system/admin/pageFormContent.jsp"%>
						<input type="hidden" name="brchNo" value="${brchNo}"></input>
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
		var brchNo = "${brchNo}";
		$("#brchNo").val(brchNo);
	}); 
	//客户类型下拉框修改事件
	function changeCustType(){
		var custType = $("#custType").val();
		dynamicHiddenElement('dataCollectForm','custType',custType);
		dataCollectForm.action = "<%=basePath%>rebuyApplyController.do?method=applyMainElec";
		dataCollectForm.submit();
	}
	function searchBills(){ 
		var brchNo = $("#brchNo").val();
		checkbrchNo(brchNo);
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
	//接收
	function receive(){
		var checkNum = getCheckNum("rebuymxId");
	   	if (checkNum < 1){
	   		bootbox.alert("请先选择要接收的记录");
	   		return;
	   	}
	   	var ids = getCheckStr("rebuymxId");
	   	//校验提交的数据
	   	$.ajax({
	   		type: "POST",
			url: '<%=basePath%>rebuyApplyController.do?method=checkElecBill',
	    	data: {'ids': ids},
			dataType:'json',
			cache: false,
			success: function(data){	
				if(data.success){  //处理成功
					toReceivePage(ids);
				} else {
					top.hangge();
					bootbox.alert(data.msg); 
				}
			}
	   	});
	}
	//跳转到接收明细页面
	function toReceivePage(ids){
		dynamicHiddenElement('bankForm','ids',ids);
		bankForm.action = "<%=basePath%>rebuyApplyController.do?method=toInnerReceivePage";
		bankForm.submit();
	}
	//拒绝
	function reject(){
		var checkNum = getCheckNum("rebuymxId");
	   	if (checkNum < 1){
	   		bootbox.alert("请先选择要拒绝的记录");
	   		return;
	   	}
	   	var ids = getCheckStr("rebuymxId");
	   	bootbox.confirm("确定要拒绝选中的记录吗？", function(result) {
	   		if(result){
		   		dynamicHiddenElement('bankForm','ids',ids);
		   		bankForm.action = "<%=basePath%>rebuyApplyController.do?method=rejectInnerApply";
		   		bankForm.submit();
		   	}
	   	});
	   	
	}
	//查询机构号是否存在
	function checkbrchNo(){ 
		var brchNo = $("#brchNo").val();
		$.ajax({
			type: "POST",
			url: "<%=basePath%>branchController.do?method=checkBrchNo",
	    	data: {'branchNo': brchNo},
			dataType:'json',
			beforeSend:function(){
				if(brchNo==null||brchNo==""){
					bootbox.alert("请输入机构号");
					$("#brchNo").val("");
			   		return false;
				}
			},
			cache: false,
			success: function(data){	
				if(data.success){  //处理成功
					modal("#layer_loading,#image");
					bankForm.submit();
				}else{
					bootbox.alert(data.msg);
					$("#brchNo").val("");
				}
			}
		});
	}
</script>
</body>
</html>