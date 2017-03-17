<%-- 
 * 文件名称: bill_manage.jsp.jsp
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
	<script type="text/javascript" src="webpage/system/addPrint/commonFunc.js"></script>
</head>
<body>
	<div class="clearfix">
		<div id="page-content" class="page-content">
			<div id="header">
				<form action=""	method="post" name="userForm" id="userForm" class="form-search detail-form">
					<%-- 查询区  --%>
					<div class="row-fluid">
						<label for="batchNo" class="pull-left control-label">批次号</label>
						<div class="pull-left batch">${batch.batchNo}</div>
						<label for="batchType">批次类型</label>
						<input type="text" class="input-medium" name="batchType" value="${fns:getDictLabel('K_BILL_TYPE',batch.batchType)}" readonly />
						<label for="custManagerName">批次种类</label>
						<input type="text" class="input-medium" name="batchClass" value="${fns:getDictLabel('K_BILL_CLASS',batch.batchClass)}" readonly />
						<label for="prodName">产品名称</label>
						<input type="text" class="input-medium" name="prodName" value="${prodName}" readonly />
					</div>
					<div class="row-fluid">
						<label for="custNo">客户号</label>
						<input type="text" class="input-medium" name="custNo" readonly value="${batch.custNo}" />
						<label for="custName">客户名称</label>
						<input type="text" class="input-medium" name="custName" value="${batch.custName}" readonly id="custName"/>
						<label for="totalNum">票据张数</label>
						<input type="text" class="input-medium" name="totalNum" readonly value="${batch.totalNum}" />
						<label for="totalMoney">总金额</label>
						<input type="text" class="input-medium" name="totalMoney" readonly value="${fns:formateMoney(batch.totalMoney)}" />
					</div>
					<div class="row-fluid">
						<label for="custManager">客户经理编号</label>
						<input type="text" class="input-medium" name="custManager" value="${batch.custManager}" readonly />
						<label for="custManagerName">客户经理名称</label>
						<input type="text" class="input-medium" name="custManagerName" value="${batch.custManagerName}" readonly />
						<label for="deptName">所属部门</label>
						<input type="text" class="input-medium" readonly name="deptName" value="${batch.deptName}" />
					</div>
				</form>
			</div>
			<%-- 按钮操作区 --%>
			<div id="center">
				<form  id="btnForm">
					<div class="row-fluid">
						<div class="span6" id="btn-left">
							<a class="btn-mini" onclick="add('${query.saveId}','${batch.batchType}');">录入</a>
							<a class="btn-mini" onclick="doImport('${query.saveId}','${batch.batchType}');">批量录入</a>
							<a class="btn-mini" onclick="copyAdd('${query.saveId}','${batch.batchType}','${batch.custNo}','${batch.custName}');">复制录入</a>
							<a class="btn-mini" onclick="edit('${batch.batchType}');">修改</a>
							<a class="btn-mini" onclick="del();">删除</a>
							<a class="btn-mini" onclick="printList();">打印清单</a>
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
								<c:forEach items="${resultList}" var="batchList" varStatus="vs">
								<tr>
									<td class="center"><input type="checkbox" name="ids" value="${batchList.savemxId}"/></td>
									<td class="center">${batchList.billNo}</td>
									<td class="center">${fns:getDictLabel('K_BILL_TYPE',batchList.billType)}</td>
									<td class="center">${batchList.issueDt}</td>
									<td class="center">${batchList.dueDt}</td>
									<td class="center">${fns:formateMoney(batchList.billMoney)}</td>
									<td class="center">${batchList.acceptor}</td>
									<td class="center">${batchList.remitter}</td>
									<td class="center"><a href="javascript:goBillDetail('${batchList.savemxId}')">查看</a></td>
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
		<form action="#" name="dataCollectForm" method="post"></form>
		<%-- /列表操作区 --%>
		<div>
			<div id="page" class="pagination">
					<form action="<%=basePath%>collateralizationApplyController.do?method=billManage"  name="pageForm" method="post">
					<%@ include file="/webpage/system/admin/pageFormContent.jsp"%>
					<input type="hidden" name="saveId" value="${saveId}"/>
					<input type="hidden" name="savemxId" value="${batchList.savemxId}"/>
				</form>
			</div>
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
		modal("#layer_loading,#image");//点击票据管理按钮，弹出‘加载中’的遮罩层
        dataCollectForm.action="<%=basePath%>collateralizationApplyController.do?method=searchBatch";
        dataCollectForm.submit();
	}
	//详情页面
	function goBillDetail(savemxId){
	   	 var diag = new top.Dialog();
		 diag.Drag = true;
		 diag.Title ="详情";
		 diag.URL = "<%=basePath%>collateralizationApplyController.do?method=goBillDetail&savemxId="+savemxId;
		 diag.Width = 900;
		 diag.Height = 260;
		 diag.CancelEvent = function(){ //关闭事件
			diag.close();
		 };
		 diag.show();
	}
    //录入
    function add(saveId,batchType){
	   	 var diag = new top.Dialog();
		 diag.Drag = true;
		 diag.Title ="录入";
		 diag.URL = "<%=basePath%>collateralizationApplyController.do?method=toAddBill&action=collateralizationApplyController.do?method=saveBill&saveId="+saveId+"&batchType="+batchType;
		 diag.Width = 800;
		 diag.Height =500;
		 diag.CancelEvent = function(){ //关闭事件
			pageForm.submit();
			diag.close();
		 };
		 diag.show();
	}
	//修改
	function edit(batchType){
		 var checkNum = getCheckNum("ids");
	   	 if (checkNum != 1){
	   		bootbox.alert("请先选择要编辑的记录!");
	   		return;
	   	 }
		var id = getCheckStr("ids");
	   	 var diag = new top.Dialog();
		 diag.Drag = true;
		 diag.Title ="修改";
		 diag.URL = "<%=basePath%>collateralizationApplyController.do?method=toEditBill&action=collateralizationApplyController.do?method=saveBill&savemxId="+id+"&batchType="+batchType;
		 diag.Width = 800;
		 diag.Height =500;
		 diag.CancelEvent = function(){ //关闭事件
		 pageForm.submit();
		 diag.close();
		 };
		 diag.show();
	}
	//删除
	function del(){
		 var checkNum = getCheckNum("ids");
	   	 if (checkNum == 0){
	   		bootbox.alert("请先选择要删除的记录!");
	   		return;
	   	 }
		var ids = getCheckStr("ids");
		bootbox.confirm("确定要删除选中的记录吗?", function(result) {
			if(result) {
				dynamicHiddenElement('pageForm','ids',ids);
				pageForm.action = "<%=basePath%>collateralizationApplyController.do?method=delBill";
				pageForm.submit();
			}
		});
	}
	//申请
	function apply(custNo,custName){
		 var checkNum = getCheckNum("ids");
	   	 if (checkNum == 0){
	   		bootbox.alert("请先选择要申请的记录!");
	   		return;
	   	 }
		var ids = getCheckStr("ids");
		modal("#layer_loading,#image");//点击票据管理按钮，弹出‘加载中’的遮罩层
		$.ajax({
			url:"<%=basePath%>collateralizationApplyController.do?method=apply",
			type:"POST",
			data:{"ids":ids},
			dataType:"json",
			success:function(rs){
				if(rs == "yes"){
					dynamicHiddenElement('dataCollectForm','custNo',custNo);
	                dynamicHiddenElement('dataCollectForm','custName',custName);
	                dataCollectForm.action = "<%=basePath%>collateralizationApplyController.do?method=searchBatch"
	                dataCollectForm.submit();
				}else{
					bootbox.alert("申请失败!");
				}
			 }
		});
	}
	//复制录入
	function copyAdd(saveId,batchType,custNo,custName){
		var checkNum = getCheckNum("ids");
		if (checkNum != 1){
		bootbox.alert("请先选择要复制录入的记录!");
		return;
		}
	   	var id = getCheckStr("ids");
	   	var saveId = saveId;
		var diag = new top.Dialog();
		 diag.Drag = true;
		 diag.Title ="复制录入";                 															
		 diag.URL = "<%=basePath%>collateralizationApplyController.do?method=toCopyBill&saveId="+saveId+"&billType="+batchType+"&savemxId="+id+"&custNo="+custNo+"&custName="+custName;
		 diag.Width = 800;
		 diag.Height =500;
		 diag.CancelEvent = function(){ //关闭事件
			pageForm.submit();
			diag.close();
		 };
		 diag.show();
	}
	//显示导入框
	function doImport(discId,billType){
		var diag = new top.Dialog();
		diag.Drag = true;
		diag.Title ="导入结果";                 															
		diag.URL = "<%=basePath%>webpage/busi/common/import_result.jsp?batchId="+discId+"&billType="+billType+"&serviceName=com.herongtech.console.service.busiservice.collateralization.SaveImportDataService";
		diag.Width = 1500;
		diag.Height =500;
		diag.CancelEvent = function(){ //关闭事件
			pageForm.submit();
			diag.close();
		};
		diag.show();
	}
	//打印质押清单
    //operStatusString 票据状态
	function printList(){
		var batch_id="${batch.saveId}";
		var checkNum = getCheckNum("ids");
	   	 if (checkNum == 0){
	   		bootbox.alert("请先选择要打印的记录");
	   		return;
	   	 }   	 
		var ids = getCheckStr("ids");
		doPrint("addPrintController.do?method=doPrint&ids="+ids+"&moduleId=1110204&type=CollateralizationListPrint&baid="+batch_id+"&handleType=申 请"); 	 	
	}
</script>
</body>
</html>