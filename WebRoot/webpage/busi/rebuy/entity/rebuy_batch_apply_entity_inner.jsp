<%-- 
 * 文件名称: rebuy_batch_apply_entity_inner.jsp
 * 系统名称: 票据管理平台
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: @version2.0.0.1
 * 开发人员: sherry
 * 开发时间: 2016-9-10
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
				<form action="<%=basePath%>rebuyApplyController.do?method=searchBatch"  method="post" name="bankForm" id="bankForm" class="form-search">
					<%-- 查询区  --%>
					<div class="row-fluid">
						<label class="no-padding-right" for="custType"><span class="star">*</span>客户类型</label>
						<select class='select2' class="input-medium" name="custType" id="custType" onchange="changeCustType();">
								<option value="-1">请选择</option>
								<option value="1">同业</option>
								<option value="2" selected="true">系统内</option>
						</select>
					</div>
				</form>
			</div>
			<%-- 按钮操作区 --%>
			<div id="center">
				<form  id="btnForm">
					<div class="row-fluid">
						<div class="span6" id="btn-left">
						    <a class="btn-mini" href="javascript:add();">新增</a>
							<a class="btn-mini" href="javascript:edit();">修改</a>
					   </div>
					   <div class="span6" id="btn-right">
					   </div>
				  </div>
		  		</form>
			</div>
	    	<%-- /按钮操作区 --%>
    		<div id="footer">
				<table class="table table-striped table-bordered table-hover" id="tab" cellpadding="0" cellspacing="0">
					<thead>
						<tr>
							<th class="center" onclick="selectAll('zcheckbox', 'rebuyId')">
								<input type="checkbox" id="zcheckbox" /><span class="lbl"></span>
							</th>
							<th class="center">批次号</th>
							<th class="center">票据类型</th>
							<th class="center">票据品种</th>
							<th class="center">合计张数</th>
							<th class="center">合计金额</th>
							<th class="center">申请创建日</th>
							<th class="center">利率</th>
							<th class="center">入账账号</th>
							<th class="center">账户名称</th>
							<th class="center">操作</th>
						</tr>
					</thead>
					<tbody id="dataBody">
					</tbody>
				</table>
			</div>
			<form  action="#" name="dataCollectForm" method="post"></form>
	 		<%-- /列表操作区 --%>
	 		<div>
				<div id="page" class="pagination">${page.pageStr}</div>
			</div>
		</div>
	</div>
<%@ include file="/webpage/system/admin/footer.jsp"%> 
<script>
	$("#tab th,td").addClass("center");
	$(function(){
		var bankNo = "${bankNo}";
		if(""!=bankNo && bankNo!=null){
			$.ajax({
				type: "POST",
				url: "<%=basePath%>rebuyApplyController.do?method=bankInfo",
		    	data: {'bankNo':bankNo},
				dataType:'json',
				success: function(data){
					if(data.success){  //处理成功
						$("#bankNo").val(bankNo);
						$("#bankName").val(data.attributes.bankName);
						fillBatch(1,5);
					}else{
						$("#bankNo").val("");
						$("#bankName").val("");
						bootbox.alert(data.msg); 
					}
				}
			});	
		}
	});
	//客户类型下拉框修改事件
	function changeCustType(){
		var custType = $("#custType").val();
		dynamicHiddenElement('dataCollectForm','custType',custType);
		dataCollectForm.action = "<%=basePath%>rebuyApplyController.do?method=apply";
		dataCollectForm.submit();
	}
	function fill(){ //根据联行行号查询银行信息并填充
		var bankNo = $("#bankNo").val();
		$.ajax({
			type: "POST",
			url: "<%=basePath%>rebuyApplyController.do?method=bankInfo",
	    	data: {'bankNo': bankNo},
			dataType:'json',
			beforeSend:function(){
				if(bankNo==null||bankNo==""){
					bootbox.alert("请输入联行行号");
					$("#bankName").val("");
			   		return false;
				}
			},
			cache: false,
			success: function(data){	
				if(data.success){  //处理成功
					$("#bankName").val(data.attributes.bankName);
					fillBatch(1,5);
				}else{
					$("#bankName").val("");
					bootbox.alert(data.msg);
					var trHtml = "<tr><td colspan=\"100\" class=\"center\">没有相关数据</td></tr>";
					$("#dataBody").html(trHtml);$("#page").html("");
				}
			}
		});
	}
	function fillBatch(page,count){
		var trHtml = "";
		$.ajax({
			type: "POST",
			url: "<%=basePath%>rebuyApplyController.do?method=searchBatch&currentPage="+page+"&showCount="+count,
	    	data: $("#bankForm").serialize(),// 要提交的表单 
			dataType:'json',
			success: function(data){	
				if(data.success){  //处理成功
					if(data.obj.length==0){
						trHtml = "<tr><td colspan=\"100\" class=\"center\">没有相关数据</td></tr>";
					}else{
						for(var i=0;i<data.obj.length;i++){
							var obj = data.obj[i];
							trHtml += "<tr><td class="center" style='width: 30px;'>"
							+"<label><input type='checkbox' name='rebuyId' value='"+obj.rebuyId
							+"' /><span class='lbl'></span></label></td><td class="center">"
							+obj.batchNo+"</td><td class="center">"
							+(obj.billType=="1"?"银票":"商票")+"</td><td class="center">"
							+(obj.billClass=="1"?"纸质":"电子")+"</td><td class="center">"
							+obj.totalNum+"</td><td class="center">"+obj.totalMoney+"</td><td class="center">"
							+obj.createDt+"</td><td class="center">"+obj.rate+"</td><td class="center">"
							+obj.tradeAcct+"</td><td class="center">"+obj.tradeAcctName+"</td><td>"
							+"<a class=\"btn btn-minier btn-info\"  onclick=\"rebuyManage('"
							+obj.rebuyId+"');\">票据管理</a></td></tr>";
						}
					}
					$("#dataBody").html(trHtml);
					var pages = data.attributes.page
						+"<input type='hidden' name='totalResult' value='"+data.attributes.totalResult+"'/>";
					$("#page").html(pages);
				}
			}
		});
	}
	//新增
	function add(){
		bootbox.alert("系统内转入业务不允许手动新增批次！");
	}
	//编辑
	function edit(){
		 var checkNum = getCheckNum("rebuyId");
	   	 if (checkNum !=1){
	   		bootbox.alert("请选择一条记录编辑");
	   		return;
	   	 }
	   	 var rebuyId = getCheckStr("rebuyId");
	   	 var diag = new top.Dialog();
		 diag.Drag = true;
		 diag.Title ="修改";
		 diag.URL = "<%=basePath%>rebuyApplyController.do?method=toEditBatch&rebuyId="+rebuyId;
		 diag.Width = 800;
		 diag.Height = 320;
		 diag.CancelEvent = function(){ //关闭事件
				var num = '${page.currentPage}';
			 	if(num == '0'){
			 		location.href = location.href;
			 	}else{
			 		nextPage(${page.currentPage});
			 	}
			diag.close();
		 };
		 diag.show(); 
	}
	function rebuyManage(rebuyId){
		dynamicHiddenElement('dataCollectForm','isInner','1');
		dynamicHiddenElement('dataCollectForm','rebuyId',rebuyId);
		dataCollectForm.action = "<%=basePath%>rebuyApplyController.do?method=billManage";
		dataCollectForm.submit();
	}
	function reset(){
		resetForm('bankForm');
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