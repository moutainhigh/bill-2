<%-- 
 * 文件名称: signProd_list.jsp
 * 系统名称: 票据管理平台
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: @version2.0.0.1
 * 开发人员: dq
 * 开发时间: 2016-7-12 下午04:28:22
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
				<form action="<%=basePath%>signProdController.do?method=list" method="post" name="userForm" id="userForm" class="form-search">
					<div class="row-fluid">
						<label for="prodNo">产品名称</label>
						<t:dictSelect className="select-medium" name="prodNo" other="" dictGroup="K_PRDTYPE" defaultVal="${mapField.prodId}" haveHead="true">
						</t:dictSelect>
						<label for="busSettleAcct">客户账号</label>
						<input class="input-medium" type="text" name="busSettleAcct" value="${mapField.busSettleAcct}" placeholder="请输入客户账号"/>
						<label for=signStatusCd>签约状态</label>
						<t:dictSelect className="select-medium" name="signStatusCd" other="" dictGroup="K_SIGNSTATUS" defaultVal="${mapField.signStatusCd}" haveHead="true">
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
				   		</div>
				   		<div class="span6" id="btn-right"></div>
			 	 	</div>
			 	 </form>
			</div>
		    <%-- /按钮操作区 --%>
			<%-- 列表操作区 --%>
			<div id="footer">
				<table id="table_report" class="table table-striped table-bordered table-hover" style="width:2000px;min-width:100%;max-width:2000px;">
					<thead>
						<tr>
							<th class="center" onclick="selectAll('zcheckbox', 'ids')">
								<input type="checkbox" id="zcheckbox" />
							</th>
							<th class="center">序号</th>
							<th class='center sort-column bus_settle_acct'>客户账号</th>
							<th class="center">客户名称</th>
							<th class='center sort-column cust_no'>客户号</th>
							<th class="center">服务产品名称</th>
							<th class="center">签约状态</th>
							<th class="center">签约机构</th>
							<th class="center">组织机构代码</th>
							<th class="center">签约时间</th>
							<th class="center">评级信息</th>
							<th class="center">评级机构</th>
							<th class='center sort-column credit_due_dt'>评级有效期</th>
							<th class="center">票据池开通标志</th>
						</tr>
					</thead>
					<c:choose>
						<c:when test="${not empty resultList}">
							<c:forEach items="${resultList}" var="signProd" varStatus="vs">
								<tr>
									<td class="center">
										<input type='checkbox' name='ids' value="${signProd.id}" />
									</td>
									<td class="center">${vs.index+1}</td>
									<td class="center">${signProd.busSettleAcct}</td>
									<td class="center">${signProd.custName}</td>
									<td class="center">${signProd.custNo}</td>
									<td class="center">${fns:getDictLabel('K_PRDTYPE',signProd.prodNo)}</td>
									<td class="center">${fns:getDictLabel('K_SIGNSTATUS',signProd.signStatusCd)}</td>
									<td class="center">${signProd.signOrg}</td>
									<td class="center">${signProd.idNumber}</td>
									<td class="center">${signProd.transTime}</td>
									<td class="center">${signProd.creditInfo}</td>
									<td class="center">${signProd.creditAgency}</td>
									<td class="center">${signProd.creditDueDt}</td>
									<td class="center">${fns:getDictLabel('K_OPENFLAG',signProd.cmsFlag)}</td>
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
	   			<%-- /列表操作区 --%>
	   	 		<div id="page" class="pagination">
					<form action="<%=basePath%>signProdController.do?method=list" name="pageForm" method="post">
						<%@ include file="/webpage/system/admin/pageFormContent.jsp"%>
						<input type="hidden" name="prodNo" value="${mapField.prodNo}" />
						<input type="hidden" name="busSettleAcct" value="${mapField.busSettleAcct}" />
						<input type="hidden" name="signStatusCd" value="${mapField.signStatusCd}" />
					</form>
				</div>
		  	</div>
		</div>	
<%@ include file="../admin/modalDialog.jsp"%>
<%@ include file="../admin/footer.jsp"%>
<script type="text/javascript">
	//检索
	function searchd(){
		modal("#layer_loading,#image");
		$("#userForm").submit();
	}
	//新增
	function add(){
		 var diag = new top.Dialog();
		 diag.Drag = true;
		 diag.Title ="新增";
		 diag.URL = "<%=basePath%>signProdController.do?method=toAdd";
		 diag.Width = 500;
		 diag.Height = 535;
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
	   	 var id = getCheckStr("ids");
	   	 var diag = new top.Dialog();
		 diag.Drag = true;
		 diag.Title ="修改";
		 diag.URL = '<%=basePath%>signProdController.do?method=toEdit&id='+id;
		 diag.Width = 500;
		 diag.Height = 535;
		 diag.CancelEvent = function(){ //关闭事件
			pageForm.submit();
			diag.close();
		 };
		 diag.show();
	}
	function page(){
		$("#userForm").submit();
		return false;
    }
</script>
</body>
</html>