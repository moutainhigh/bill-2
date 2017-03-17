<%-- 
 * 文件名称: custinfo_list.jsp
 * 系统名称: 票据管理平台
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: @version2.0.0.1
 * 开发人员: cgc
 * 开发时间: 2016-7-7 上午06:28:22
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
				<form action="<%=basePath%>custInfoController.do?method=listCustInfo" method="post" name="custInfoForm" id="custInfoForm" class="form-search">
					<div class="row-fluid">
						<label for="cust_name">客户名称</label>
						<input class="input-medium" type="text" name="cust_name" id="cust_name" value="${mapField.cust_name}" placeholder="请输入客户名称"/>
						<label for="cust_no">客户号</label>
						<input class="input-medium" type="text" name="cust_no" id="cust_no" value="${mapField.cust_no}" placeholder="请输入客户号"/>
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
							<a class="btn-mini" onclick="searchCust();">查看账号</a>
							<a class="btn-mini"  onclick="del();">删除</a>
				   		</div>
				   		<div class="span6" id="btn-right"></div>
			  		</div>
				</form>
			</div>
		    <%-- /按钮操作区 --%>
			<%-- 列表操作区 --%>
			<div id="footer">
			<table id="table_report" class="table table-striped table-bordered table-hover" style="min-width:100%;max-width:1400px;width:1400px;">
				<thead>
					<tr>
						<th class="center" onclick="selectAll('zcheckbox', 'ids')">
							<input type="checkbox" id="zcheckbox" />
						</th>
						<th class="center">序号</th>
						<th class='center sort-column cust_name'>客户名称</th>
						<th class='center sort-column cust_type'>客户类型</th>
						<th class='center sort-column cust_no'>客户号</th>
						<th class='center sort-column org_code'>组织机构代码</th>
						<th class='center sort-column flag'>标记</th>
						<th class='center sort-column create_dt'>创建日期</th>
						<th class='center sort-column create_time'>创建时间</th>
						<th class='center sort-column update_dt'>更新日期</th>
						<th class='center sort-column update_time'>更新时间</th>
						<th class='center sort-column partner_type'>参与者类别</th>
						<th class='center sort-column address'>地址</th>
						<th class='center sort-column credit_agency'>评级机构</th>
						<th class='center sort-column credit_duedt'>评级到期日</th>
						<th class='center sort-column credit_rate'>信用等级</th>
					</tr>
				</thead>
				<c:choose>
					<c:when test="${not empty resultList}">
						<c:forEach items="${resultList}" var="custinfoparam" varStatus="vs">
							<tr>
								<td class="center">
									<input type='checkbox' name='ids' value="${custinfoparam.custNo}" />
								</td>
								<td class='center'>${vs.index+1}</td>
								<td class='center'>${custinfoparam.custName}</td>
								<td class='center'>${fns:getDictLabel('K_CUSTTYPE',custinfoparam.custType)}</td>
								<td class='center'>${custinfoparam.custNo}</td>
								<td class='center'>${custinfoparam.orgCode}</td>
								<td class='center'>${fns:getDictLabel('K_KHZHZH',custinfoparam.flag)}</td>
								<td class='center'>${custinfoparam.createDt}</td>
								<td class='center'>${custinfoparam.createTime}</td>
								<td class='center'>${custinfoparam.updateDt}</td>
								<td class='center'>${custinfoparam.updateTime}</td>
								<td class='center'>${fns:getDictLabel('K_BUSSINESS_ROLE',custinfoparam.partnerType)}</td>
								<td class='center'>${custinfoparam.address}</td>
								<td class='center'>${custinfoparam.creditAgency}</td>
								<td class='center'>${custinfoparam.creditDuedt}</td>
								<td class='center'>${custinfoparam.creditRate}</td>
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
		<div>
	   <%-- /列表操作区 --%>
	   	<form  action="#" name="dataCollectForm" method="post"></form>
		<div id="page" class="pagination">
			<form action="<%=basePath%>custInfoController.do?method=listCustInfo"  name="pageForm" method="post" >
				<%@ include file="/webpage/system/admin/pageFormContent.jsp"%>
				<input type="hidden" name="cust_name" value="${mapField.cust_name}" />
				<input type="hidden" name="cust_no" value="${mapField.cust_no}" />
			</form>
		</div>
  	</div>
</div>	
<%@ include file="/webpage/system/admin/modalDialog.jsp"%> 
<%@ include file="../admin/footer.jsp"%>
<script type="text/javascript">
	//检索
	function searchd(){
		modal("#layer_loading,#image");
		$("#custInfoForm").submit();
	}
	//新增
	function add(){
		 var diag = new top.Dialog();
		 diag.Drag = true;
		 diag.Title ="新增";
		 diag.URL = "<%=basePath%>custInfoController.do?method=toAdd";
		 diag.Width = 680;
		 diag.Height = 270;
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
	   		bootbox.alert('请选择一条记录编辑');
	   		return;
	   	 }
	   	 var cust_id = getCheckStr("ids");
	   	 var diag = new top.Dialog();
		 diag.Drag = true;
		 diag.Title ="修改";
		 diag.URL = '<%=basePath%>custInfoController.do?method=toEdit&custid='+cust_id;
		 diag.Width = 680;
		 diag.Height = 270;
		 diag.CancelEvent = function(){ //关闭事件
			pageForm.submit();
			diag.close();
		 };
		 diag.show();
	}
	//查看账号
	function searchCust(){
	 var checkNum = getCheckNum("ids");
   	 if (checkNum !=1){
   		bootbox.alert('请选择一条记录查看');
   		return;
   	 }
   	 var cust_id = getCheckStr("ids");
  	 var diag = new top.Dialog();
	 diag.Drag = true;
	 diag.Title ="查看账号";
	 diag.URL = '<%=basePath%>custInfoController.do?method=toSearth&custid='+cust_id;
	 diag.Width = 1200;
	 diag.Height = 200;
	 diag.show();
	}	
	//删除
	function del(){
		var checkNum = getCheckNum("ids");
	   	if (checkNum <= 0){
	   		bootbox.alert('请选择删除记录');
	   		return;
	   	}
	   	var cust_ids = getCheckStr("ids");	
	   	bootbox.confirm("是否确定要删除选择的记录吗?", function(result) {
		if(result){
			$.ajax({
				type: "POST",
				url: '<%=basePath%>custInfoController.do?method=del',
		    	data: {'cust_ids': cust_ids},
				dataType:'json',
				//beforeSend: validateData,
				cache: false,
				success: function(data){	
					if (data.success){  //处理成功
						custInfoForm.submit();
					} else {
						bootbox.alert('删除失败!');
					}
				}
			});
		 }
	   });
	}
	
    function page(){
		$("#custInfoForm").submit();
		return false;
    }
</script>
</body>
</html>