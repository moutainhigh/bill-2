<%--
 * 文件名称: saleback_entity_apply_batch_main.jsp
 * 系统名称: 票据管理平台
 * 模块名称: 纸票返售申请批次首页
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: @version2.0.0.1
 * 开发人员: sherry
 * 开发时间: 2016-11-03
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
		<%--jsp文件头和头部--%>
		<%@ include file="/webpage/system/admin/top.jsp"%>	
	</head>
<body>
	<div class="clearfix">
		<div id="page-content" class="page-content">
		<div id="header">
				<form action="<%=basePath%>rebuyApplyController.do?method=searchBatch"  method="post" name="bankForm" id="bankForm" class="form-search">
					<%--查询区 --%>
					<div class="row-fluid">
						<label class="no-padding-right" for="custType"><span class="star">*</span>客户类型</label>
						<input type="hidden" name="isinner" id="isinner" value="${isinner}"></input>
						<select class="select2 select-medium" name="custType" id="custType" onchange="changeCustType();">
							<c:choose>			
					   			<c:when test="${isinner=='0'}">
					    		<option value="0" selected="true">同业</option>
								<option value="1">系统内</option>
					  		 </c:when>
					  		 <c:when test="${isinner=='1'}">
					   			 <option value="0" >同业</option>
								<option value="1" selected="true">系统内</option>
					  		 </c:when>
					   		<c:otherwise>
					    		<option value="-1" >请选择</option>
								<option value="0" >同业</option>
								<option value="1">系统内</option>
					 		</c:otherwise>			
							</c:choose>	
						</select>
					</div>
					</form>
				</div>	
			<div id="center">
				<div class="row-fluid">
					<div class="span6" id="btn-left" >
						<a class="btn-mini" href="javascript:billmanager();">票据管理</a>
					</div>
					<div class="span6" id="btn-right">
					</div>
				</div>
			</div>
		    <div id="footer">
				<table class="table table-striped table-bordered table-hover" style="min-width:100%;max-width:1600px;width:1600px;" id="tab" cellpadding="0" cellspacing="0">
					<thead>
						<tr>
							<th class="center" onclick="selectAll('allcheck', 'rebuyId')">
								<input type="checkbox" id="allcheck" /><span class="lbl"></span>
							</th>
							<th class="center">批次号</th>
							<th class="center">客户名称</th>
							<th class="center">票据类型</th>
							<th class="center">票据品种</th>
							<th class="center">合计张数</th>
							<th class="center">合计金额</th>
							<th class="center">利率</th>
							<th class="center">转入日</th>
							<th class="center">买入返售开放日</th>
							<th class="center">买入返售到期日</th>
							
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
										<td class="center">${batchList.custBankName}</td>
										<td class="center">${fns:getDictLabel('K_BILL_TYPE',batchList.billType)}</td>
										<td class="center">${fns:getDictLabel('K_BILL_CLASS',batchList.billClass)}</td>
										<td class="center">${batchList.totalNum}</td>
										<td class="center">${fns:formateMoney(batchList.totalMoney)}</td>
										<td class="center">${batchList.rate}</td>
										<td class="center">${batchList.rebuyDt}</td>
										<td class="center">${batchList.resaleStaDt}</td>
										<td class="center">${batchList.resaleDueDt}</td>
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
			 <%--/列表操作区--%>
			 <div>
				<div id="page" class="pagination">
					<form action="<%=basePath%>SalebackApplyController.do?method=entityapplybatch"  name="pageForm" method="post">
						<%@ include file="/webpage/system/admin/pageFormContent.jsp"%>
							
					</form>
				</div>
			</div>
		</div>
	</div>
<%@ include file="/webpage/system/admin/modalDialog.jsp"%>	
<%@ include file="/webpage/system/admin/footer.jsp"%> 
<script>
	function billmanager(){
		var choseisinner = $("#isinner").val();
		if(choseisinner!="1"&&choseisinner!="0"){
			bootbox.alert("请先选择客户类型");
	  		return;
		}
		var checkNum = getCheckNum("rebuyId");
		if (checkNum==0){
	  		bootbox.alert("请先选择要返售申请的记录");
	  		return;
	  	}
		if (checkNum !=1){
	  		bootbox.alert("一次只能对一个批次进行返售申请");
	  		return;
	  	}
	  	var newsalebackId="${newsalebackId}";
	  	modal("#layer_loading,#image");
	  	var rebuyId = getCheckStr("rebuyId");	
	  	var isInner = $("#custType").val();
		dynamicHiddenElement('dataCollectForm','isInner',isInner);
	  	dynamicHiddenElement('dataCollectForm','rebuyId',rebuyId);
	  	dynamicHiddenElement('dataCollectForm','billClass',"1");
	  	dynamicHiddenElement('dataCollectForm','rebuyId',rebuyId);
	  	dynamicHiddenElement('dataCollectForm','newsalebackId',newsalebackId);
		dataCollectForm.action = "<%=basePath%>SalebackApplyController.do?method=billManage";
		dataCollectForm.submit();
	}
	//客户类型下拉框修改事件
	function changeCustType(){
		var isInner = $("#custType").val();
		dynamicHiddenElement('dataCollectForm','billClass',"1");
		dynamicHiddenElement('dataCollectForm','isinner',isInner);
		dataCollectForm.action = "<%=basePath%>SalebackApplyController.do?method=entityapplybatch";
		dataCollectForm.submit();
	}	
</script>
</body>
</html>