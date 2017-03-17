<%-- 
 * 文件名称: export_info.jsp
 * 系统名称: 票据管理平台
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 余额查询首页面
 * 系统版本: @version2.0.0.1
 * 开发人员: 
 * 开发时间: 2016-10-17
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期    修改人员    修改说明
 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ include file="/webpage/system/admin/taglib.jsp"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<%-- jsp文件头和头部 --%>
		<%@ include file="/webpage/system/admin/top.jsp"%>
<title></title>
<style type="text/css">
#one {
	width: 200px;
	height: 180px;
	float: left
}

#two {
	width: 50px;
	height: 180px;
	float: left
}

#three {
	width: 200px;
	height: 180px;
	float: left
}

.btn {
	width: 45px;
}

* {
	margin: 0;
	padding: 0;
}

div.centent {
	float: left;
	text-align: center;
	margin: 10px;
}
span {
	display: block;
	margin: 2px 2px;
	padding: 4px 10px;
	cursor: pointer;
}
</style>
<%--   引入jQuery --%>
<script type="text/javascript">
	$(function() {
		//移到右边
		$('#add').click(function() {
			//获取选中的选项，删除并追加给对方
			$('#select1 option:selected').appendTo('#select2');
		});
		//移到左边
		$('#remove').click(function() {
			$('#select2 option:selected').appendTo('#select1');
		});
		//全部移到右边
		$('#add_all').click(function() {
			//获取全部的选项,删除并追加给对方
			$('#select1 option').appendTo('#select2');
		});
		//全部移到左边
		$('#remove_all').click(function() {
			$('#select2 option').appendTo('#select1');
		});
		//双击选项
		$('#select1').dblclick(function() { //绑定双击事件
			//获取全部的选项,删除并追加给对方
			$("option:selected", this).appendTo('#select2'); //追加给对方
		});
		//双击选项
		$('#select2').dblclick(function() {
			$("option:selected", this).appendTo('#select1');
		});
	});
</script>

</head>
<body style="background-color:#f4f8fb;font-family:'微软雅黑';">
<div class="clearfix">
	<div class="page-content" id="jump-content">
	<form action="" name="Form" id="Form" method="post" class="form-search">
		<input type="hidden" name="tableName" value="${tableName}" />
		<div id="zhongxin">
			<div style="margin-left:90px;">
				<table width="700" align="center" border="0" cellpadding="0"
					cellspacing="0" class="newContTab">
					<tr>
						<td><div>
								<div>
									<select multiple="multiple" id="select1"
										style="width:240px;height:450px; float:left; border:1px #A0A0A4 outset; padding:4px; ">
									<c:forEach items="${leftList}" var="export">
										<option value="${export.fieldName}">${export.exFieldName}</option>
									</c:forEach>
									</select>
								</div>
								<div style="float:left;margin-top: 55px;">
									<span id="add"> <input type="button" class="btn-mini"
										value=">" /> </span><span id="add_all"> <input name="button"
										type="button" class="btn-mini" value=">>" /> </span><span id="remove">
										<input name="button2" type="button" class="btn-mini" value="<"/>
							</span><span id="remove_all">
							<input name="button3" type="button" class="btn-mini" value="<<"/>
							</span>
					  </div>
							<div>
								<select multiple="multiple" id="select2"
									style="width: 240px;height:450px; float:left;border:1px #A0A0A4 outset; padding:4px;">
									<c:forEach items="${rightList}" var="export">
										<option value="${export.fieldName}">${export.exFieldName}</option>
									</c:forEach>
								</select>
							</div>
						</div>
					</td>
				</tr>
			</table>
			</div>
			<div class="row-fluid">
				<div class="save center" style="margin-top:40px">
					<a class="btn-mini" onClick="doExport();">导出</a> 
					<a class="btn-mini" onClick="top.Dialog.close();">取消</a>
				</div>
			</div>
		</div>
	</form>
	</div>
</div>
</body>
<%@ include file="/webpage/system/admin/modalDialog.jsp"%> 
<%@ include file="/webpage/system/admin/footer.jsp"%> 
<script>
	function doExport(){
		$("#layer_loading,#image").show();
		var role_code = "";
		var left = document.getElementById("select1");
		var right = document.getElementById("select2");
		if (right.length == 0) {
			showTips("select2", "请选择导出列");
			return false;
		}
		var rightList = "";
		for (i = 0; i < right.length; i++) {
			rightList += right[i].value + ",";
		}
		rightList = rightList.substring(0,rightList.length-1);
		var leftList = "";
		for (i = 0; i < left.length; i++) {
			leftList += left[i].value + ",";
		}
		if(leftList!=""){
			leftList = leftList.substring(0,leftList.length-1);
		}
		var curr="${frameId}";
		var form1 = $("#mainFrame",parent.document).contents().find("#"+curr).contents().find("#"+"${formName}")[0];
        for(var i=0;i <form1.elements.length;i++)   
        {
       		var e = form1.elements[i];
        	if(e.name!=""&&e.value!=""){
        		dynamicHiddenElement('Form', e.name, e.value);
        	}
        }   
		dynamicHiddenElement('Form', 'leftList', leftList);
		dynamicHiddenElement('Form', 'rightList', rightList);
		dynamicHiddenElement('Form', 'excelName', "${excelName}");
		$("#Form").attr('action',"${action}");
		$("#Form").submit();
		setTimeout(function () {
        	top.Dialog.close();
   		}, 16000);
	}
	
</script>
</html>