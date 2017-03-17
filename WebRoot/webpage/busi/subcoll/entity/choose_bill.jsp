<%-- 
 * 文件名称: choose_bill.jsp
 * 系统名称: 票据管理平台
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 转卖申请选票页面
 * 系统版本: @version2.0.0.1
 * 开发人员: 
 * 开发时间: 2016-8-10
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期    修改人员    修改说明
 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/webpage/system/admin/taglib.jsp"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>票据添加</title>
<base href="<%=basePath%>">
<%-- jsp文件头和头部 --%>
<%@ include file="/webpage/system/admin/top.jsp"%>
</head>
<body style="font-family:'微软雅黑';padding:10px;background:#f4f8fb;">
	<div class="clearfix">
		<div id="page-content" class="page-content" style="background:#f4f8fb;">
			<%-- 按钮部分 --%>
			<div id="center">
				<form id="btnForm">
					<div class="row-fluid">
						<div class="span6" id="btn-left">
							<a class="btn-mini" onclick="submit();">确定</a> 
							<a class="btn-mini" onclick="top.Dialog.close();">取消</a>
						</div>
					</div>
				</form>
			</div>
			<%--  表格部分 --%>
			<div id="footer">
				<table class="table table-striped table-bordered table-hover"
					id="tab" cellpadding="0" cellspacing="0" style="min-width:100%;max-width:1600px;width:1600px">
					<thead>
						<tr>
							<th><input type="checkbox" name="allcheck" id="allcheck" onclick="selectAll('allcheck','ids')"/></th>
							<th class="center">票号</th>
							<th class="center">票据类型</th>
							<th class="center">票据品种</th>
							<th class="center">业务类型</th>
							<th class="center">出票日期</th>
							<th class="center">票面到期日</th>
							<th class="center">票面金额</th>
							<th class="center">付款方行号</th>
							<th class="center">承兑人</th>
							<th class="center">承兑人开户行行号</th>
							<th class="center">备注</th>
							<th class="center">详情</th>
						</tr>
					</thead>
					<tbody>
						<c:choose>
							<c:when test="${not empty resultList}">
								<c:forEach items="${resultList}" var="subcoll"
									varStatus="vs">
									<tr>
										<td class="center"><input type="checkbox" name="ids"
											value="${subcoll.id}" onclick="getall('ids')" price="${subcoll.billMoney}"/>
										</td>
										<td class="center">${subcoll.billNo}</td>
										<td class="center">${fns:getDictLabel('K_BILL_TYPE',subcoll.billType)}</td>
										<td class="center">${fns:getDictLabel('K_BILL_CLASS',subcoll.billClass)}</td>
										<td class="center">${fns:getDictLabel('K_BUY_TYPE',subcoll.buyType)}</td>
										<td class="center">${subcoll.issueDt}</td>
										<td class="center">${subcoll.dueDt}</td>
										<td class="center">${fns:formateMoney(subcoll.billMoney)}</td>
										<td class="center">${subcoll.draweeBankNo}</td>
										<td class="center">${subcoll.acceptor}</td>
										<td class="center">${subcoll.acceptorBankNo}</td>
										<td class="center">${subcoll.remark}</td>
										<td class="center"><a href="javascript:goDetail('${subcoll.id}')">查看</a></td>
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
				<form  action="#" name="dataCollectForm" method="post"></form>
			</div>
			<%-- /列表操作区 --%>
			<div>
				<div id="page" class="pagination">
					<form action="<%=basePath%>subcollApplyController.do?method=toAddBill"  name="pageForm" method="post">
						<%@ include file="/webpage/system/admin/pageFormContent.jsp"%> 
						 <input type="hidden" name="subcollId" value="${query.subcollId}">
						 <input type="hidden" name="billType" value="${query.billType}">
						 <input type="hidden" name="billClass" value="${query.billClass}">
					</form>
				</div>
			</div>
			<input type="hidden" name="subcollId" id="subcollId" value="${query.subcollId}">
		</div>
	</div>
<%@ include file="/webpage/system/admin/modalDialog.jsp"%>
<%@ include file="/webpage/system/admin/footer.jsp"%>
<script type="text/javascript">
	//确定添加前先检查，符合后再保存
	function submit(){
		var checkNum = getCheckNum("ids");
	   	 if (checkNum <= 0){
	   		bootbox.alert("请先选择要添加的记录");
	   		return;
	   	 }
	   	var id = getCheckStr("ids");
	   	var isOverdue="${query.isOverdue}";
		var isOnline="${query.isOnline}";
		var billClass = "${query.billClass}";
		var subcollId = "${query.subcollId}";
		$.ajax({
			url:"subcollApplyController.do?method=addCheckBill",
			type:"post",
			dataType:"JSON",
			data:{"ids":id,"subcollId":subcollId,"billClass":billClass},
			success:function(data){
  				if(data.obj=="notAdd"){
  					bootbox.alert("该批次下部分票据已提交且清算方式为线上清算，不能继续加入行内提示付款的票据，请重新选择！");
				}else if(data.obj=="isOnline"){
					bootbox.confirm("本批次下含有行内提示付款的票据，只能选择线下清算，是否确认继续?", function(result) {
						if(result) {
							dynamicHiddenElement('dataCollectForm','ids',id);
						   	dynamicHiddenElement('dataCollectForm','isOverdue',isOverdue);
						   	dynamicHiddenElement('dataCollectForm','isOnline',0);
						   	dynamicHiddenElement('dataCollectForm','subcollId',$("#subcollId").val());
							modal("#layer_loading,#image");//点击票据管理按钮，弹出‘加载中’的遮罩层
							dataCollectForm.action = "<%=basePath%>subcollApplyController.do?method=addBill";
							dataCollectForm.submit();
						}
					});
				}else{
					dynamicHiddenElement('dataCollectForm','ids',id);
				   	dynamicHiddenElement('dataCollectForm','isOverdue',isOverdue);
				   	dynamicHiddenElement('dataCollectForm','isOnline',isOnline);
				   	dynamicHiddenElement('dataCollectForm','subcollId',$("#subcollId").val());
					modal("#layer_loading,#image");//点击票据管理按钮，弹出‘加载中’的遮罩层
					dataCollectForm.action = "<%=basePath%>subcollApplyController.do?method=addBill";
					dataCollectForm.submit();
				} 
  			},
  			error:function(data){
  				bootbox.alert("系统错误");
  			}
  		});
	}
	//详情页面
	function goDetail(id){
	   	 var billClass = "${query.billClass}";
	   	 var diag = new top.Dialog();
		 diag.Drag = true;
		 diag.Title ="详情";
		 if(billClass=='1'){
		 	diag.URL = '<%=basePath%>subcollApplyController.do?method=goDetail&id='+id;
		 }else{
			diag.URL = '<%=basePath%>elecBillDetailController.do?method=goDetail&rgctId='+id;
		 }
		 diag.URL = '<%=basePath%>subcollApplyController.do?method=goDetail&id='+id;
		 diag.Width = 920;
		 diag.Height = 330;
		 diag.CancelEvent = function(){ //关闭事件
			diag.close();
		 };
		 diag.show();
	}
</script>
</body>
</html>