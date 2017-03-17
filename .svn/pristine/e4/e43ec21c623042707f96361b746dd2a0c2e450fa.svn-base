<%-- 
 * 文件名称: modify_rebuy_apply_add_bill_elec.jsp
 * 系统名称: 票据管理平台
 * 模块名称: 电票转入修改申请添加票据弹出页面
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: @version2.0.0.1
 * 开发人员: cgc
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
			<%-- 按钮操作区 --%>
			<div id="center">
				<form action="<%=basePath%>rebuyApplyController.do?method=addElecBill"  method="post" id="elecReviewForm" name="elecReviewForm" style="padding:0px;margin-bottom:0px;" class="form-search">
					<div class="row-fluid">
					   <div class="span6" id="btn-left">
							<a class="btn-mini" onclick="add();">新增</a>
							<a class="btn-mini" onclick="goHistory();">返回</a>
					   </div>
				  </div>
			  </form>
		</div>
	    <div id="footer" class="footer">
			<table class="table table-striped table-bordered table-hover" style="min-width:100%;max-width:1800px;width:1800px;" id="tab" cellpadding="0" cellspacing="0">
				<thead>
					<tr>
						<th class="center" onclick="selectAll('allcheck', 'rebuymxId')">
							<input type="checkbox" id="allcheck" /><span class="lbl"></span>
						</th>
						<th class="center">票号</th>
						<th class="center">票据类型</th>
						<th class="center">是否央行卖票</th>
						<th class="center">转入类型</th>
						<th class="center">清算方式</th>
						<th class="center">利率</th>
						<th class="center">转入日</th>
						<th class="center">赎回开放日</th>
						<th class="center">赎回截止日</th>
						<th class="center">票面到期日</th>
						<th class="center">票面金额</th>
						<th class="center">报文实付金额</th>
						<th class="center">承兑人</th>
						<th class="center">出票人</th>
						<th class="center">详细</th>
					</tr>
				</thead>
				<tbody id="dataBody">
					<c:choose>
						<c:when test="${not empty billList}">
							<c:forEach items="${billList}" var="billList" varStatus="vs">
								<tr>
									<td class="center">
										<input type='checkbox' name='rebuymxId' value="${billList.rebuymxId}" onclick="getall('rebuymxId')" price="${billList.billMoney}"/><span class="lbl"></span>
									</td>
									<td class="center">${billList.billNo}</td>
									<td class="center">${fns:getDictLabel('K_BILL_TYPE',billList.billType)}</td>
									<td class="center">${fns:getDictLabel('K_YORN',billList.isRediscCenter)}</td>
									<td class="center">${fns:getDictLabel('K_ISREGRESS',billList.isRegress)}</td>
									<td class="center">${fns:getDictLabel('K_ISONLINE',billList.isOnline)}</td>
									<td class="center">${billList.rate}</td>
									<td class="center">${billList.rebuyDt}</td>
									<td class="center">${billList.resaleStaDt}</td>
									<td class="center">${billList.resaleDueDt}</td>
									<td class="center">${billList.dueDt}</td>
									<td class="center">${fns:formateMoney(billList.billMoney)}</td>
									<td class="center">${fns:formateMoney(billList.payMoney)}</td>
									<td class="center">${billList.acceptor}</td>
									<td class="center">${billList.remitter}</td>
									<td class="center"><a onclick="goDetail('${billList.rgctId}');" style="cursor:pointer">详细</a></td>
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
		<form  action="#" name="dataCollectForm" method="post">
			<t:token></t:token>
		</form>
		 <%-- /列表操作区 --%>
		 <div>
			<div id="page" class="pagination">
				<form action="<%=basePath%>rebuyApplyController.do?method=toAddBillPage"  name="pageForm" method="post">
					<%@ include file="/webpage/system/admin/pageFormContent.jsp"%>
					<input type="hidden" name="rebuyId" value="${rebuyId}"></input>
				</form>
			</div>
		</div>
	</div>	
</div>
<%@ include file="/webpage/system/admin/modalDialog.jsp"%>
<%@ include file="/webpage/system/admin/footer.jsp"%>
<script>
	function goHistory(){
	    top.Dialog.close();
	}
	//详情页面
	function goDetail(rgctId){
		var diag = new top.Dialog();
		 diag.Drag = true;
		 diag.Title ="详情";
		 diag.URL = "<%=basePath%>elecBillDetailController.do?method=goDetail&rgctId="+rgctId;;
		 diag.Width = 930;
		 diag.Height = 575;
		 diag.CancelEvent = function(){ //关闭事件
			diag.close();
		 };
		 diag.show();
	}
	function add(){
	    var checkNum = getCheckNum("rebuymxId");
	   	 if (checkNum == 0){
	   		bootbox.alert("请先选择要添加的记录");
	   		return;
	   	 }
	   	var ids = getCheckStr("rebuymxId");
	   	var rebuyId = "${rebuyId}";
	   	var oldId = "${rebuymxIds}";
	   	var newIds = oldId+","+ids;
	   	$.ajax({
			type: "POST",
			url: '<%=basePath%>rebuyApplyController.do?method=checkElecBill',
			data: {'ids':newIds},
			dataType:'json',
			cache: false,
			success: function(data){
				if (data.success){  //处理成功
					addBills(ids,rebuyId);
				} else {
					bootbox.alert(data.msg);
				}
			}
		});
	}
	function addBills(ids,rebuyId){
		$.ajax({
			type: "POST",
			url: '<%=basePath%>rebuyApplyController.do?method=addElecBill',
			data: {'ids':ids,
					'rebuyId':rebuyId},
			dataType:'json',
			cache: false,
			success: function(data){
				if (data.success){  //处理成功
					modal("#layer_loading,#image");
			   	
			   		top.Dialog.close();
				} else {
					top.hangge();
					bootbox.alert(data.msg);
				}
			}
		});
	}
</script>
</body>
</html>