<%-- 
 * 文件名称: repurCollate_apply_bill_list.jsp
 * 系统名称: 票据管理平台
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: @version2.0.0.1
 * 开发人员: v
 * 开发时间: 2016-8-10
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期    修改人员    修改说明
 --%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/webpage/system/admin/taglib.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
  <head>
    <base href="<%=basePath%>">
    <meta charset="UTF-8">
	<title>票据管理页面</title>
	<%@ include file="/webpage/system/admin/top.jsp"%>
</head>
<body>
<div class="clearfix">
		<div id="page-content" class="page-content">
			<div id="header">
				<form action=""	method="post" name="userForm" id="userForm" class="form-search detail-form">
					<div class="row-fluid">
						<label for="batchNo" class="pull-left control-label">批次号</label>
						<div class="pull-left batch">${batch.batchNo}</div>
						<label for="batchType">批次类型</label>
						<input type="text" class="input-medium" name="batchType" id="batchType" value="${fns:getDictLabel('K_BILL_TYPE',batch.batchType)}" readonly />
						<label for="custManagerName">批次种类</label>
						<input type="text" class="input-medium" name="batchClass" value="${fns:getDictLabel('K_BILL_CLASS',batch.batchClass)}" readonly />
						<label for="prodName">产品名称</label>
						<input type="text" class="input-medium" name="prodName" value="${prodName}" readonly />
					</div>
				    <div class="row-fluid">
						<label for="custNo">客户号</label>
						<input type="text" class="input-medium" name="custNo" id="custNo" readonly value="${batch.custNo}" />
						<label for="custName">客户名称</label>
						<input type="text" class="input-medium" name="custName" id="custName" value="${batch.custName}" readonly id="custName"/>
						<label for="totalNum">票据张数</label>
						<input type="text" class="input-medium" name="totalNum" readonly value="${batch.totalNum}" />
						<label for="totalMoney">总金额</label>
						<input type="text" class="input-medium" name="totalMoney" readonly value="${fns:formateMoney(batch.totalMoney)}" />
					</div>
					<div class="row-fluid">
						<label for="custManager">客户经理编号</label>
						<input type="text" class="input-medium" name="custManager" value="${batch.custManager}" placeholder="请输入客户经理编号" id="custManagerNo" valid="required" onblur="fill();"/>
						<label for="custManagerName">客户经理名称</label>
						<input type="text" class="input-medium" name="custManagerName" id="custManagerName" value="${batch.custManagerName}" readonly />
						<label for="deptName">所属部门</label>
						<input type="text" class="input-medium" name="deptName" id="deptName" value="${batch.deptName}" readonly/>
					</div>
				</form>
			</div>
			<%-- 按钮操作区 --%>
			<div id="center">
				<form action="<%=basePath%>repurCollateApplyController.do?method=applyBillList"  method="post" id="elecReviewForm" name="elecReviewForm" style="padding:0px;margin-bottom:0px;" class="form-search">
					<div class="row-fluid">
						<div class="span6" id="btn-left">
					   </div>
					   <div class="span6" id="btn-right">
							<a class="btn-mini pull-right" onclick="apply('${batch.custNo}','${batch.custName}');">提交</a>
							<a class="btn-mini pull-right" onclick="goHistory();">返回</a>
					   </div>
				  </div>
			   </form>
			</div>
			<div id="footer" class="footer">
				<table class="table table-striped table-bordered table-hover" id="tab" cellpadding="0" cellspacing="0">
					<thead>
						<tr>
							<th class="center" onclick="selectAll('zcheckbox', 'ids')">
								<input type="checkbox" id="zcheckbox" /><span class="lbl"></span>
							</th>
							<th class="center">票号</th>
							<th class="center">票据类型</th>
							<th class="center">出票日</th>
							<th class="center">票面到期日</th>
							<th class="center">票面金额</th>
							<th class="center">承兑人</th>
							<th class="center">出票人</th>
							<th class="center">详情</th>
						</tr>
					</thead>
					<tbody>
						<c:choose>
							<c:when test="${not empty resultList}">
								<c:forEach items="${resultList}" var="save" varStatus="vs">
									<tr>
										<td class="center"><input type="checkbox" name="ids" value="${save.savemxId}"/></td>
										<td class="center">${save.billNo}</td>
										<td class="center">${fns:getDictLabel('K_BILL_TYPE',save.billType)}</td>
										<td class="center">${save.issueDt}</td>
										<td class="center">${save.dueDt}</td>
										<td class="center">${fns:formateMoney(save.billMoney)}</td>
										<td class="center">${save.acceptor}</td>
										<td class="center">${save.remitter}</td>
										<td class="center"><a href="javascript:goBillDetail('${save.savemxId}')">查看</a></td>
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
			<form  action="#" name="dataCollectForm" method="post"></form>
			<div id="page" class="pagination">
					<form action="<%=basePath%>repurCollateApplyController.do?method=applyBillList"  name="pageForm" method="post" >
						<%@ include file="/webpage/system/admin/pageFormContent.jsp"%>
						<input type="hidden" name="custNo" id="custNo" value="${custNo}"/>
						<input type="hidden" name="batchNo" id="batchNo" value="${batch.batchNo}"/>
						<input type="hidden" name="batchClass1" id="batchClass1" value="${batch.batchClass}"/>
						<input type="hidden" name="batchType1" id="batchType1" value="${batch.batchType}"/>
						<input type="hidden" name="isTc" id="isTc" value="${batch.isTc}"/>
						<input type="hidden" name="prodNo" id="prodNo" value="${batch.prodNo}"/>
					</form>
				</div>
			</div>
		</div>
<%@ include file="/webpage/system/admin/modalDialog.jsp"%>
<%@ include file="/webpage/system/admin/footer.jsp"%>
<script>
	//返回上一页
	function goHistory(){
		dynamicHiddenElement('dataCollectForm','custNo',${batch.custNo});
		dynamicHiddenElement('dataCollectForm','custName',$("#custName").val());
		modal("#layer_loading,#image");
	    dataCollectForm.action="<%=basePath%>repurCollateApplyController.do?method=searchBatch";
	    dataCollectForm.submit();
	}
	//详情页面
	function goBillDetail(savemxId){
		var diag = new top.Dialog();
		 diag.Drag = true;
		 diag.Title ="详情";
		 diag.URL = "<%=basePath%>collateralizationApplyController.do?method=goBillDetail&savemxId="+savemxId;
		 diag.Width = 990;
		 diag.Height = 260;
		 diag.CancelEvent = function(){ //关闭事件
			diag.close();
		 };
		 diag.show();
	}
	//申请
	function apply(custNo,custName){
		 var checkNum = getCheckNum("ids");
	   	 if (checkNum == 0){
	   		bootbox.alert("请先选择要申请的记录!");
	   		return;
	   	 }
		var ids = getCheckStr("ids");
		var batchClass = $("#batchClass1").val();
		var batchNo = $("#batchNo").val();
		var batchType = $("#batchType1").val();
		var custNo = $("#custNo").val();
		var custManager = $("#custManagerNo").val();
		var isTc = $("#isTc").val();
		var prodNo = $("#prodNo").val();
		if($("#userForm").valid()){
		modal("#layer_loading,#image");//点击票据管理按钮，弹出‘加载中’的遮罩层
		$.ajax({
			url:"<%=basePath%>repurCollateApplyController.do?method=apply",
			type:"POST",
			data:{"ids":ids,"batchNo":batchNo,"batchType":batchType,"batchClass":batchClass,"custNo":custNo,"custManager":custManager,"isTc":isTc,"prodNo":prodNo},
			dataType:"json",
			success:function(rs){
				if(rs == "yes"){
					dynamicHiddenElement('dataCollectForm','custNo',custNo);
	                dynamicHiddenElement('dataCollectForm','custName',custName);
	                dataCollectForm.action = "<%=basePath%>repurCollateApplyController.do?method=searchBatch"
	                dataCollectForm.submit();
				}else{
					bootbox.alert("服务异常!");
				}
			}
		});
		}
	}
	//根据客户经理编号查询信息并填充
	function fill(){ 
		var custManagerNo = $('#custManagerNo').val();
		$.ajax({
			type: "POST",
			url: "<%=basePath%>custManagerController.do?method=custMagInfo",
	    	data: {'custManagerNo': custManagerNo},
			dataType:'json',
			beforeSend:function(){
				if(custManagerNo==null||custManagerNo==""){
					bootbox.alert("请输入客户经理编号!");
					$("#deptName").val("");
					$("#custManagerName").val("");
			   		return false;
				}
			},
			cache: false,
			success: function(data){	
				if(data.success){  //处理成功
					$("#deptName").val(data.attributes.deptName);
					$("#custManagerName").val(data.attributes.custManagerName);
				}else{
					$("#deptName").val("");
					$("#custManagerName").val("");
					bootbox.alert(data.msg);
					var trHtml = "<tr><td colspan=\"100\" class=\"center\">没有相关数据</td></tr>";
					$("#dataBody").html(trHtml);$("#page").html("");
				};
			}
		});
	}
</script>
</body>
</html>