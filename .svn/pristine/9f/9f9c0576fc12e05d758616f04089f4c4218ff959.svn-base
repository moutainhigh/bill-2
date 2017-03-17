<%-- 
 * 文件名称: accountFlow_search.jsp
 * 系统名称: 票据管理平台
 * 模块名称: 账务流水查询批次信息
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: @version2.0.0.1
 * 开发人员: wzc
 * 开发时间: 2016-12-14
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
				<form action="<%=basePath%>accountFlowSearchController.do?method=accountFlowSearch" method="post" id="searchForm" name="searchForm" style="padding:0px;" class="form-search">
				<%-- 查询区  --%>
					<div class="row-fluid">
						<label class="no-padding-right" for="transDt">交易日期</label>
						<input class="date-medium input-date" name="minTransDt"id="minTransDt" value="${query.minTransDt}"  valid="required dateISO" type="text"  placeholder="最小交易日期"  onblur="transDt();"  onfocus="transDt();" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"/>
						<span class="wave-line">~</span>
						<input class="date-medium input-date" name="maxTransDt"id="maxTransDt" value="${query.maxTransDt}"  valid="required dateISO" type="text"  placeholder="最大交易日期"   onblur="transDt();"  onfocus="transDt();" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"/>
						<label class="no-padding-right" for="prodNo">产品类型</label>
						  	<select  class="select2" name="prodNo" defaultVal="${prodNo}" valid="required" id="prodNo">
						 		<option value="0">请选择</option>
								<option value="1" <c:if test="${prodNo==1}">selected</c:if>>贴现</option>
								<option value="2" <c:if test="${prodNo==2}">selected</c:if>>转贴现买入</option>
								<option value="3" <c:if test="${prodNo==3}">selected</c:if>>转贴现卖出</option>
								<option value="4" <c:if test="${prodNo==4}">selected</c:if>>质押</option>
								<option value="5" <c:if test="${prodNo==5}">selected</c:if>>解质押</option>
								<option value="6" <c:if test="${prodNo==6}">selected</c:if>>收回记账</option>
								<option value="7" <c:if test="${prodNo==7}">selected</c:if>>存票</option>
								<option value="8" <c:if test="${prodNo==8}">selected</c:if>>取票</option>
								<option value="9" <c:if test="${prodNo==9}">selected</c:if>>到期回购</option>
								<option value="10" <c:if test="${prodNo==10}">selected</c:if>>到期返售</option>
					    	</select>
						<a class="btn-mini" id="search" onclick="searchd();">查询</a>
					</div>
				</form>
			</div>
			<%-- 按钮操作区 --%>
			<div id="center">
				<form  id="btnForm">
					<div class="row-fluid">
						<div class="span6" id="btn-left" >
							<a class="btn-mini" href="javascript:acctlList();">下一步</a>
					   </div>
					   <div class="span6" id="btn-right">
					   </div>
				  	</div>
			   	</form>
			</div>
	    	<%-- /按钮操作区 --%>
		    <div id="footer">
				<table class="table table-striped table-bordered table-hover" id="tab" cellpadding="0" cellspacing="0" style="min-width:100%;width:1600px;max-width:1600px;">
					<thead>
						<tr>
						    <th class="center"><input type="checkbox" name="ids" value="" disabled="disabled" /></th>
							<th class='center sort-column foreFlowNo'>前台流水号</th>
							<th class='center sort-column transNo'>交易编码</th>
							<th class='center sort-column transDt'>交易日期</th>
							<th class='center sort-column transTm'>交易时间</th>
							<th class='center sort-column acctType'>交易类型</th>
							<th class='center sort-column prodNo'>产品类型</th>
							<th class='center sort-column transBranchNo'>交易机构号</th>
							<th class='center sort-column transBranchName'>交易机构名称</th>
							<th class='center sort-column transUserNo'>交易记账柜员</th>
							<th class='center sort-column acctBranchNo'>账务所属机构号</th>
							<th class='center sort-column totalAmount'>票面总额</th>
							<th class='center sort-column settlementMoney'>实收（付）金额</th>
							<th class='center sort-column settlementInterest'>应收（付）利息</th>
						</tr>
					</thead>
					<tbody id="dataBody">
						<c:choose>
							<c:when test="${not empty batchList}">
								<c:forEach items="${batchList}" var="acct" varStatus="vs">
									<tr>
									    <td class="center"><input type="checkbox"  name="afId" value="${acct.afId}"/></td>
										<td class='center'>${acct.foreFlowNo}</td>
										<td class='center'>${acct.transNo}</td>
										<td class='center'>${acct.transDt}</td>
										<td class='center'>${acct.transTm}</td> 										
										<td class='center'>${fns:getDictLabel('K_ACCT_TYPE',acct.acctType)}</td>									
										<td class='center'>${fns:getDictLabel('K_PROD_NO',acct.prodNo)}</td>
										<td class='center'>${acct.transBranchNo}</td>
										<td class='center'>${acct.transBranchName}</td>
										<td class='center'>${acct.transUserNo}</td>
										<td class='center'>${acct.acctBranchNo}</td>
										<td class='center'>${fns:formateMoney(acct.totalAmount)}</td>
										<td class='center'>${fns:formateMoney(acct.settlementMoney)}</td>
										<td class='center'>${fns:formateMoney(acct.settlementInterest)}</td>
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
			<div id="page" class="pagination">
				<form action="<%=basePath%>accountFlowSearchController.do?method=accountFlowSearch"  name="pageForm" method="post">
					<%@ include file="/webpage/system/admin/pageFormContent.jsp"%> 
				</form>
			</div>
		</div>
	</div>
<%@ include file="/webpage/system/admin/modalDialog.jsp"%> 
<%@ include file="/webpage/system/admin/footer.jsp"%>
<script src="weblib/bizjs/checkDate.js"></script>
<script>
      //查询控制 
		function searchd(){
				//调用后台查询方法查询客户账号对应的批次信息
				modal("#layer_loading,#image");
		        $("#searchForm").submit();
		}
		function acctlList(){
		      var checkNum = getCheckNum("afId");
	   		 if (checkNum !=1){
	   			bootbox.alert("一次只能对一个批次进行查看");
	   			return;
	   	 	}
	   		var afId = getCheckStr("afId");	
	   		modal("#layer_loading,#image");
			location.href="<%=basePath%>accountFlowSearchController.do?method=acctBillList&afId="+afId;
		}
		
    //交易日期校验
	function transDt(){
		var minTransDt = $("#minTransDt").val();
        var arr=minTransDt.split("-"); 
		var maxTransDt = $("#maxTransDt").val(); 
		var endArr = maxTransDt.split("-");
		var tem=(endArr[2]-arr[2])+((endArr[1]-arr[1])*30)+((endArr[0]-arr[0])*360);
		if(tem<0){
				showTips("maxTransDt","最大交易日必须大于最小交易日");
				return;
			}
		}
</script>
</body>
</html>