<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
  <head>
  <meta charset="utf-8" />
  <base href="<%=basePath%>">
<title>导入错误信息</title> 

   <style type="text/css"> 
        body { background-color: #fff; color: #666; text-align: center; font-family: arial, sans-serif; }
        div.dialog {
            width: 80%;
            padding: 1em 4em;
            margin: 4em auto 0 auto;
            border: 1px solid #ccc;
            border-right-color: #999;
            border-bottom-color: #999;
        }
        h1 { font-size: 100%; color: #f00; line-height: 1.5em; }
    </style> 
</head> 
 
<body> 
  <div class="dialog"> 
    <h1>导入失败信息,本次导入${countAll}条信息，失败${failureNum}条!!!</h1>  
    <table id="table_report"
				class="table table-striped table-bordered table-hover">
				<thead>
					<tr>
						<th class="center" onclick="selectAll('zcheckbox', 'ids')"><label><input
								type="checkbox" id="zcheckbox" /><span class="lbl"></span> </label>
						</th>
						<th class="center" style="width:50px;">序号</th>
						<th class="center">员工号</th>
						<th class="center">所属机构</th>
						<th class="center">所属部门</th>
						<th class="center">用户名称</th>
						<th class="center">用户邮箱</th>
						<th class="center">用户电话</th>
						<th class="center">备注1</th>
						<th class="center">备注2</th>
						<th class="center">备注3</th>
					</tr>
				</thead>
				<c:choose>
					<c:when test="${not empty errorList}">
						<c:forEach items="${errorList}" var="sysparam" varStatus="vs">
							<tr>
								<td class="center" style="width: 30px;"><label><input
										type='checkbox' name='ids' value="${sysparam.userId}" /><span
										class="lbl"></span> </label>
								</td>
								<td class="center">${vs.index+1}</td>
								<td class="center">${sysparam.userId}</td>
								<td class="center">${sysparam.branchNo}</td>
								<td class="center">${sysparam.depCode}</td>
								<td class="center">${sysparam.userName}</td>
								<td class="center">${sysparam.userEmail}</td>
								<td class="center">${sysparam.userPhone}</td>
								<td class="center">${sysparam.remark1}</td>
								<td class="center">${sysparam.remark2}</td>
								<td class="center">${sysparam.remark3}</td>

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
    
    
    
    <p><a href="javascript:showErr();">详 情</a> 
<a href="javascript:history.back(-1)">返 回</a> 
  </div>
  
</body> 
</html>
