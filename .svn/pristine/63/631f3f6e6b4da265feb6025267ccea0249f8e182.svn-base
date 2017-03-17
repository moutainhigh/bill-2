<%-- 
 * 文件名称: riskbill_list.jsp
 * 系统名称: 票据管理平台
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: @version2.0.0.1
 * 开发人员: wuzhichen
 * 开发时间: 2016-7-13 上午06:28:22
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期    修改人员    修改说明
 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../admin/taglib.jsp"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<base href="<%=basePath%>">
		<%-- jsp文件头和头部 --%>
		<%@ include file="../admin/top.jsp"%>
	</head>
<body>
<div class="clearfix">
	<div id="page-content" class="page-content">
	<%-- 查询区  --%>
		<div id="header">	
			<form action="<%=basePath%>riskBillController.do?method=list" method="post" name="userForm" id="userForm" class="form-search">
				<div class="row-fluid">
					<label for="bill2">票号后 8 位</label>
					<input class="input-medium" type="text" name="bill2" value="${mapField.bill2}"  maxLength="8" placeholder="请输入票号后8位"/>
					<label for="acceptor">承兑人</label>
					<input class="input-medium" type="text" name="acceptor" value="${mapField.acceptor}" placeholder="请输入承兑人"/>
					<label for="status">状态</label>
					<t:dictSelect className="select-medium" name="status" other="" dictGroup="K_RISKSTATUS" defaultVal="${mapField.status}" haveHead="true">
					</t:dictSelect>
				</div>
				<div class="row-fluid">
					<label for="remitter">出票人全称</label>
					<input class="input-medium" type="text" name="remitter" value="${mapField.remitter}" placeholder="请输入出票人全称"/>
					<label for="description">风险描述</label>
					<input class="input-medium" type="text" name="description" value="${mapField.description}" placeholder="请输入风险描述"/>
					<label for="bill_type">票据类型</label>
				    <t:dictSelect className="select-medium" name="bill_type" other="" dictGroup="K_BILL_TYPE" defaultVal="${mapField.billType}" haveHead="true" valid="required">
					</t:dictSelect>
					<a class="btn-mini" id="search" onclick="searchd();">查询</a>
				</div>
				<sys:tableSort id="order_by" name="order_by" value="${mapField.order_by}" callback="page();"/>
			</form>
		</div>
	<%-- /查询区  --%>
	<%-- 按钮操作区 --%>
		<div id="center">
			<form  id="btnForm">
				<div class="row-fluid">
					<div class="span6" id="btn-left">
				    	<a class="btn-mini"  onclick="add();">新增</a>
						<a class="btn-mini" onclick="edit();">修改</a>
						<a class="btn-mini"  onclick="del();">删除</a>
			   		</div>
			   		<div class="span6" id="btn-right">
			  		</div>
		  		</div>
			</form>
		</div>
	<%-- /按钮操作区 --%>
	<%-- 列表操作区 --%>
		<div id="footer">
			<table id="table_report" class="table table-striped table-bordered table-hover" style="min-width:100%;max-width:1600px;width:1600px;">
				<thead>
					<tr>
						<th class="center" nowrap="nowrap" onclick="All('zcheckbox', 'ids')">
							<input type="checkbox" id="zcheckbox" />
						</th>
						<th class='center sort-column bill_no' nowrap="nowrap">票号</th>
						<th class='center sort-column status'  nowrap="nowrap">状态</th>
						<th class='center sort-column bill_type'nowrap="nowrap">票据类型</th>
						<th class='center sort-column bill1'nowrap="nowrap">票号前8位</th>
						<th class='center sort-column bill2'nowrap="nowrap">票号后8位</th>
						<th class='center sort-column postdate'nowrap="nowrap">公告日期</th>
						<th class='center sort-column enterdate'nowrap="nowrap">录入日期</th>
						<th class='center sort-column risk_amount'nowrap="nowrap">票面金额</th>
			    		<th class='center sort-column issue_dt' nowrap="nowrap">出票日期</th>
						<th class='center sort-column bill_end_date' nowrap="nowrap">到期日期</th>
						<th class='center sort-column acceptor' nowrap="nowrap">承兑人</th>
						<th class='center sort-column remitter' nowrap="nowrap">出票人全称</th>
						<th class='center sort-column ureapplyname' nowrap="nowrap">催告申请人</th>
						<th class='center sort-column courtname' nowrap="nowrap">公告法院</th>
						<th class='center sort-column description' nowrap="nowrap">风险描述</th>
						<th class='center sort-column matchfileno' nowrap="nowrap">档案编号</th>
					</tr>
				</thead>
				<c:choose>
					<c:when test="${not empty resultList}">
						<c:forEach items="${resultList}" var="riskbill" varStatus="vs">
							<tr>
								<td class="center">
									<input type='checkbox' name='ids' value="${riskbill.id}" />
								</td>
								<td class="center"nowrap="nowrap">${riskbill.billNo}</td>
								<td class="center"nowrap="nowrap">${fns:getDictLabel('K_RISKSTATUS',riskbill.status)}</td>
								<td class="center"nowrap="nowrap">${fns:getDictLabel('K_BILL_TYPE',riskbill.billType)}</td>
								<td class="center"nowrap="nowrap">${riskbill.bill1}</td>
								<td class="center"nowrap="nowrap">${riskbill.bill2}</td>
								<td class="center"nowrap="nowrap">${riskbill.postdate}</td>
								<td class="center"nowrap="nowrap">${riskbill.enterdate}</td>
								<td class="center"nowrap="nowrap">${fns:formateMoney(riskbill.billMoney)}</td>
								<td class="center"nowrap="nowrap">${riskbill.issueDt}</td>
								<td class="center"nowrap="nowrap">${riskbill.dueDt}</td>
								<td class="center"nowrap="nowrap">${riskbill.acceptor}</td>
								<td class="center"nowrap="nowrap">${riskbill.remitter}</td>
								<td class="center"nowrap="nowrap">${riskbill.urgeapplyname}</td>
								<td class="center"nowrap="nowrap">${riskbill.courtname}</td>
								<td class="center"nowrap="nowrap">${riskbill.description}</td>
								<td class="center"nowrap="nowrap">${riskbill.matchfileno}</td>
							</tr>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<tr>
							<td colspan="100" class="center">没有相关数据</td>
						</tr>
					</c:otherwise>
				</c:choose>
			</table>
		</div>
	   <%-- /列表操作区 --%>
		<form  action="#" name="dataCollectForm" method="post"></form>
	    <div id="page" class="pagination">
			<form action="<%=basePath%>riskBillController.do?method=list"  name="pageForm" method="post" >
				<%@ include file="/webpage/system/admin/pageFormContent.jsp"%>
				<input type="hidden" name="bill2" value="${mapField.bill2}" />
				<input type="hidden" name=acceptor value="${mapField.acceptor}" />
				<input type="hidden" name="remitter" value="${mapField.remitter}" />
				<input type="hidden" name="bill_type" value="${mapField.bill_type}" />  
				<input type="hidden" name="description" value="${mapField.description}" /> 
				<input type="hidden" name="status" value="${mapField.status}" /> 
			</form>
		</div>
  	</div>
</div>	
<%@ include file="../admin/modalDialog.jsp"%>
<%@ include file="../admin/footer.jsp"%> 
<script type="text/javascript" src="weblib/bizjs/changeLine.js"></script>
<script type="text/javascript">
	//检索
	function searchd(){
		modal("#layer_loading,#image");
		$("#userForm").submit();
	}
	//新增
	function add(){
		 top.jzts();
		 var diag = new top.Dialog();
		 diag.Drag = true;
		 diag.Title ="新增";
		 diag.URL = "<%=basePath%>riskBillController.do?method=toAdd";
		 diag.Width = 700;
		 diag.Height = 400;
		 diag.CancelEvent = function(){ //关闭事件
			pageForm.submit();
			 diag.close();
		 };
		 diag.show(); 
	}
	//修改
	function edit(){
		 var checkNum = getCheckNum("ids");
	   	 if (checkNum !=1){
	   		bootbox.alert("一次只能对一条记录进行修改");
	   		return;
	   	 }
	   	 var id = getCheckStr("ids");
	   	 var diag = new top.Dialog();
		 diag.Drag = true;
		 diag.Title ="修改";
		 diag.URL = '<%=basePath%>riskBillController.do?method=toEdit&id='+id;
		 diag.Width = 700;
		 diag.Height = 400;
		 diag.CancelEvent = function(){ //关闭事件
			 pageForm.submit();
			diag.close();
		 };
		 diag.show();
	}
	//删除
	function del(){
		var checkNum = getCheckNum("ids");
	   	if (checkNum <= 0){
	   		bootbox.alert("请先选择要删除的记录");
	   		return;
	   	}
	   	var ids = getCheckStr("ids");	
		bootbox.confirm("确定要删除选中的记录吗？", function(result) {	
		if(result){
				top.jzts();
				$.ajax({
					type: "POST",
					url: '<%=basePath%>riskBillController.do?method=del',
			    	data: {'ids': ids},
					dataType:'json',
					cache: false,
					success: function(data){	
						if (data.success){  //处理成功
							userForm.submit();
						} else {
							bootbox.alert("删除失败!"); 
						}
					}
			});
		}
	});
}
    function page(){
		$("#userForm").submit();
		return false;
    }
</script>
</body>
</html>