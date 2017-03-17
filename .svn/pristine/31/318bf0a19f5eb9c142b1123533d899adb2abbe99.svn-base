<%-- 
 * 文件名称: collate_elec_modify_batch_list.jsp
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
						<input type="text" class="input-medium" name="batchType" value="${fns:getDictLabel('K_BILL_TYPE',batch.batchType)}" readonly />
						<label for="custNo">客户号</label>
						<input type="text" class="input-medium" name="custNo" id="custNo" value="${batch.custNo}" readonly />
						<label for="custName">客户名称</label>
						<input type="text" class="input-medium" name="custName" id="custName" value="${batch.custName}" readonly />
					</div>
					<div class="row-fluid">
						<label for="custManager">客户经理编号</label>
						<input type="text" class="input-medium" name="custManager" value="${batch.custManager}" readonly />
						<label for="custManagerName">客户经理名称</label>
						<input type="text" class="input-medium" name="custManagerName" value="${batch.custManagerName}" readonly />
						<label for="deptName">所属部门</label>
						<input type="text" class="input-medium" readonly name="deptName" value="${batch.deptName}" />
						<label for="param_value" class="pdTop">产品名称</label>
				        <input type="text" class="input-medium" name="prodName" id="prodName" value="${prodName}" readonly/>
				    </div>
					<div class="row-fluid">
						<label for="totalNum">票据张数</label>
						<input type="text" class="input-medium" name="totalNum" readonly value="${batch.totalNum}" />
						<label for="totalMoney">总金额</label>
						<input type="text" class="input-medium" name="totalMoney" readonly value="${fns:formateMoney(batch.totalMoney)}" />
					</div>
				</form>
				</div>
			<%-- 按钮操作区 --%>
			<div id="center">
				<form action="<%=basePath%>collateralizationApplyController.do?method=elecModifyApplyBillList"  method="post" id="elecReviewForm" name="elecReviewForm" style="padding:0px;margin-bottom:0px;" class="form-search detail-form">
					<div class="row-fluid">
						<div class="span6" id="btn-left">
							<a class="btn-mini" onclick="add();">添加</a>
							<a class="btn-mini" onclick="del();">删除</a>
					   </div>
					   <div class="span6" id="btn-right">
							<a class="btn-mini pull-right" onclick="elecApply();">提交</a>
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
										<td class="center"><input type="checkbox" name="ids" value="${save.savemxId}" onclick="getall('ids')" price="${save.billMoney}"/></td>
										<td class="center">${save.billNo}</td>
										<td class="center">${fns:getDictLabel('K_BILL_TYPE',save.billType)}</td>
										<td class="center">${save.issueDt}</td>
										<td class="center">${save.dueDt}</td>
										<td class="center">${fns:formateMoney(save.billMoney)}</td>
										<td class="center">${save.acceptor}</td>
										<td class="center">${save.remitter}</td>
										<td class="center"><a href="javascript:goBillDetail('${save.rgctId}')">查看</a></td>
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
			<div id="select-Info">
				<div id="selectInfo"><center>暂时没有相关数据</center></div>
			</div>
			<%-- /列表操作区 --%>
			<form  action="#" name="dataCollectForm" method="post"></form>
			<div id="page" class="pagination">
			<form action="<%=basePath%>collateralizationApplyController.do?method=elecModifyApplyBillList"  name="pageForm" method="post" >
					<%@ include file="/webpage/system/admin/pageFormContent.jsp"%>
					<input type="hidden" name="custNo" id="custNo" value="${custNo}"/>
					<input type="hidden" name="saveId" id="saveId" value="${batch.saveId}"/> 
				</form>
			</div>
		</div>
	</div>
<%@ include file="/webpage/system/admin/modalDialog.jsp"%>
<%@ include file="/webpage/system/admin/footer.jsp"%>
<script>
	//返回上一页
	function goHistory(){
		modal("#layer_loading,#image");
	    dataCollectForm.action="<%=basePath%>collateralizationApplyController.do?method=elecModifyApplyBatchList";
	    dataCollectForm.submit();
	}
	//详情页面
	function goBillDetail(rgctId){
		var diag = new top.Dialog();
		 diag.Drag = true;
		 diag.Title ="详情";
		 diag.URL = "<%=basePath%>elecBillDetailController.do?method=goDetail&rgctId="+rgctId;
		 diag.Width = 930;
		 diag.Height = 575;
		 diag.CancelEvent = function(){ //关闭事件
			diag.close();
		 };
		 diag.show(); 
	}
	//电票申请
	function elecApply(){
		var checkNum = getCheckNum("ids");
	   	if (checkNum <= 0){
	   		bootbox.alert("请先选择要提交的记录!");
	   		return;
	   	}
	   	var ids = getCheckStr("ids");
	   	var billType = "${batch.batchType}";
	   	var saveId = "${batch.saveId}";
	   	var custNo = "${batch.custNo}";
	   	bootbox.confirm("确定要提交选中的记录吗?", function(result) {
	   		if(result){
	   			top.jzts();
	   			modal("#layer_loading,#image");//点击票据管理按钮，弹出‘加载中’的遮罩层
				$.ajax({
						url:"<%=basePath%>collateralizationApplyController.do?method=elecModifyApplySubmit",
						type:"POST",
						data:{'savemxIds': ids,'billType': billType,'saveId': saveId,'custNo': custNo},
						dataType:"json",
						cache: false,
						success:function(data){
							if(data.success){
								if(data.attributes.count == "0"){
									dataCollectForm.action = "<%=basePath%>collateralizationApplyController.do?method=elecModifyApplyBatchList";
									dataCollectForm.submit();
								}else{
									dynamicHiddenElement('dataCollectForm','custNo',custNo);
				                    dynamicHiddenElement('dataCollectForm','saveId',saveId);
				                    modal("#layer_loading,#image");
				                    dataCollectForm.action = "<%=basePath%>collateralizationApplyController.do?method=elecModifyApplyBillList";
				                    dataCollectForm.submit();
								}
								   }else{
									bootbox.alert("提交失败!");
							}
						}
					});
	   			}
			});
	   	}
	//增加
	function add(){
	    var billType = "${batch.batchType}";
	    dynamicHiddenElement('dataCollectForm','custNo1',$('#custNo').val());
	    dynamicHiddenElement('dataCollectForm','saveIds',$('#saveId').val());
		dynamicHiddenElement('dataCollectForm','billType',billType);
		modal("#layer_loading,#image");
		dataCollectForm.action = "<%=basePath%>collateralizationApplyController.do?method=elecAddBill";
		dataCollectForm.submit();
	}
	//移除
	function del(){
		var checkNum = getCheckNum("ids");
	   	if (checkNum <= 0){
	   		bootbox.alert("请先选择要移除的记录!");
	   		return;
	   	}
	   	var bill_ids = getCheckStr("ids");
	   	var billType = "${batch.batchType}";
	   	var saveId = "${batch.saveId}";
	   	var custNo = "${batch.custNo}";
		bootbox.confirm("确定要移除选中的记录吗?", function(result) {
			if(result){
					top.jzts();
					modal("#layer_loading,#image");
					$.ajax({
						type: "POST",
						url: '<%=basePath%>collateralizationApplyController.do?method=elecDelBill',
				    	data: {'savemxIds': bill_ids,'billType': billType,'saveId': saveId,'custNo': custNo},
						dataType:'json',
						cache: false,
						success: function(data){	
							if (data.success){  //处理成功
							   if(data.attributes.count == "0"){
							    modal("#layer_loading,#image");
							    dataCollectForm.action = "<%=basePath%>collateralizationApplyController.do?method=elecModifyApplyBatchList";
			                    dataCollectForm.submit();
							   }else{
								dynamicHiddenElement('dataCollectForm','custNo',custNo);
			                    dynamicHiddenElement('dataCollectForm','saveId',saveId);
			                    modal("#layer_loading,#image");
			                    dataCollectForm.action = "<%=basePath%>collateralizationApplyController.do?method=elecModifyApplyBillList";
			                    dataCollectForm.submit();
							   }
							} else {
								bootbox.alert('移除失败!');
							}
						}
				  });
			 }
	   	});
	}
</script>
</body>
</html>