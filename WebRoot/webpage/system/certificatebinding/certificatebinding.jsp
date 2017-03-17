<%-- 
 * 文件名称: sysparam_list.jsp
 * 系统名称: 票据管理平台
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: @version2.0.0.1
 * 开发人员: yanjl
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
			<div id="header">
				<form action="<%=basePath%>certificateBindingController.do?method=select" method="post" name="userForm" id="userForm" class="form-search">
					<%-- 查询区  --%>
					<div class="row-fluid">
						<label for="param_id">银行行号</label>
						<input class="input-medium" type="text" name="row_number" id="row_number" placeholder="请输入银行行号" value="${row_number}"/>
						<label for="operationType">变更类型</label>
						<select class="select2" name="operationType" id="operationType">
							<option value="">请选择</option>
							<option value="AB00">新增</option>
							<option value="AB01">删除</option>
						</select>
						<label for="certBindingStatus">绑定状态</label>
						<select class="select2" name="certBindingStatus" id="certBindingStatus">
							<option value="">请选择</option>
							<c:if test="${certBindingStatus=='00'}">
								<option value="00" selected="selected">未绑定</option>
								<option value="01">已绑定</option>
							</c:if>
							<c:if test="${certBindingStatus=='01'}">
								<option value="00" >未绑定</option>
								<option value="01" selected="selected">已绑定</option>
							</c:if>
							<c:if test="${certBindingStatus!='00'&&certBindingStatus!='01'}">
								<option value="00" >未绑定</option>
								<option value="01">已绑定</option>
							</c:if>				
						</select>
				    	<a class="btn-mini" id="search" onclick="searchd();">查询</a>
					</div>
		 		</form>
	    	</div>
			<%-- /查询区  --%>
			<div id="center">
				<form  id="btnForm">
				<div class="row-fluid">
					<div class="span6" id="btn-left">
						<a class="btn-mini" onclick="add();">证书变更</a>
						<a class="btn-mini" onclick="batchAdd();">批量绑定</a>
						<a class="btn-mini" onclick="batchCancel();">批量解除</a>
					</div>	
					<div class="span6" id="btn-right"></div>	
					</div>	
		    	</form>
		    </div>
			<%-- 列表操作区 --%>
			<div id="footer">
				<table id="table_report" class="table table-striped table-bordered table-hover">
					<thead>
						<tr>
							<th class="center" onclick="selectAll('zcheckbox', 'ids')">
								<input type="checkbox" id="zcheckbox" />
							</th>
							<th class="center">序号</th>
							<th class='center sort-column param_id'>行号</th>
							<th class='center sort-column param_name'>行名</th>
							<th class='center sort-column param_value'>接入点号</th>
							<th class='center sort-column value_name'>证书绑定状态</th>
						</tr>
					</thead>
					<c:choose>
						<c:when test="${not empty ecdsbankDatas}">
							<c:forEach items="${ecdsbankDatas}" var="sysparam" varStatus="vs">
								<tr>
									<td class="center">
										<input type='checkbox' name='ids' value="${sysparam.rowNumber}" />
									</td>
									<td class="center">${fns:getDictLabel('sex',vs.index+1)}</td>
									<td class="center">${sysparam.rowNumber}</td>
									<td class="center">${sysparam.actorFullCall}</td>
									<td class="center">${sysparam.meetIncomeCode}</td>
									<c:if test="${sysparam.certBindStatus=='01'}">
										<td class="center">已绑定</td>
									</c:if>
									<c:if test="${sysparam.certBindStatus=='00'}">
										<td class="center">未绑定</td>
									</c:if>
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
			<div id="page" class="pagination">
				<form action="<%=basePath%>certificateBindingController.do?method=select"  name="pageForm" method="post">
					<%@ include file="/webpage/system/admin/pageFormContent.jsp"%> 
					<input type="hidden" name=certBindingStatus value="${certBindingStatus}"/>
					<input type="hidden" name=row_number value="${row_number}"/>
				</form>
			</div>
		</div>
	</div>	
<%@ include file="../admin/footer.jsp"%> 
<script type="text/javascript">
	//根据状态查询
	function getInfobyBindStatus(obj){
		if(obj!=null){
		$("#userForm").submit();
		}
	} 
	//查询
	function searchd(){
		$("#userForm").submit();
	}
	 //单张增加   
	 function add(){
		var no = document.getElementById("row_number").value;
		var operationType = document.getElementById("operationType").value;
		if(no==null||operationType==null||no==""||operationType==""||no=='-1'||operationType=='-1'){
			bootbox.alert("请填写正确的银行行号和选择变更类型");
		}else{
			$.ajax({
				url:"certificateBindingController.do?method=update",
				data:{"row_number":no,"operationType":operationType},
				type:"POST",
				dataType:"JSON",
				success:function(data){
				},
				error:function(data){
				}
			});
		}
	}	
	//多张邦定	
	function batchAdd(){
		var selectids = getCheckNum("ids");
		var no = getCheckStr("ids");	
		if(selectids<=0){
	 		bootbox.alert("请选择至少一条记录");
	 		return;
 		}
 		var bindingStatus = document.getElementById('certBindingStatus').value;
 		var operationType;
 		if("00"==bindingStatus){operationType = "AB00";}
 		else{
 			bootbox.alert("当前查询的行号已经绑定,如需再次绑定,请先进行撤销!");
 			return;
 		}
 		$.ajax({
			url:"certificateBindingController.do?method=update",
			data:{"row_number":no,"operationType":operationType},
			type:"POST",
			dataType:"JSON",
			success:function(data){
			},
			error:function(data){
			}
		});	
	}
	//多张解除
	function batchCancel(){
		var selectids = getCheckNum("ids");
		if(selectids<=0){
	 		bootbox.alert("请选择一条记录");
	 		return;
 		}
 		var bindingStatus = document.getElementById('certBindingStatus').value;
 		var operationType;
 		if("01"==bindingStatus){operationType = "AB01";}
 		else{
 			bootbox.alert("当前查询的行号还未绑定,请进行绑定操作!");
 			return;
 		}
		var no = getCheckStr("ids");	
		$.ajax({
			url:"certificateBindingController.do?method=update",
			data:{"row_number":no,"operationType":operationType},
			type:"POST",
			dataType:"JSON",
			success:function(data){
			},
			error:function(data){
			}
		});
	}
</script>
</body>
</html>