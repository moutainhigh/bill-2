<%-- 
 * 文件名称: edit_bill_next.jsp.jsp
 * 系统名称: 票据管理平台
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 批次下清单列表
 * 系统版本: @version2.0.0.1
 * 开发人员: dq
 * 开发时间: 2016-8-25
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
<body style="font-family:'微软雅黑';">
	<div class="clearfix">
		<div id="page-content" class="page-content">
			<div id="header">
				<form action="<%=basePath%>subcollApplyController.do?method=billManage" method="post" name="subcollManage" id="subcollManage" class="form-search">
					<div class="row-fluid">
						<label for="isOnline">清算方式</label>
						<t:dictSelect name="isOnline" className="select-medium" other="" dictGroup="K_ISONLINE" defaultVal="${query.isOnline}" haveHead="true"  title="是否线上清算" >
						</t:dictSelect>
						<label for="isOverdue">是否逾期</label>
						<t:dictSelect name="isOverdue" className="select-medium" other="" dictGroup="K_ISOVERDUE" defaultVal="${query.isOverdue}" haveHead="true"  title="是否线上清算" >
						</t:dictSelect>
						<input type="hidden" name="isOverdue" id="isOverdue" value="${query.isOverdue}"/>
					</div>
					<div class="row-fluid ">
						<label for="remark">提示付款人备注</label>
						<textarea class="input-medium" name="remark" id="remark" >${query.remark}</textarea>
						<label for="overdueReason" class="col-md-2 control-label no-padding-right yincang"><span class="star">*</span>逾期原因</label>
						<textarea class="form-control yincang" name="overdueReason" valid="required" id="overdueReason" >${query.overdueReason}</textarea>
					</div>
				</form>
			</div>
			<div id="center">
				<form  id="btnForm">
					<div class="row-fluid">
						<div class="span6" id="btn-left">
							<a class="btn-mini" onclick="printDocument();">打印凭证</a>
							<a class="btn-mini" onclick="printList();">打印清单</a>
					   </div>
					   <div class="span6" id="btn-right">
					   		<a class="btn-mini pull-right" onclick="apply();">提交</a>
							<a class="btn-mini pull-right" onclick="goHistory();">返回</a>
					   </div>
				  </div>
				</form>
			</div>
			<div id="footer" class="footer">
				<table class="table table-striped table-bordered table-hover" id="tab" cellpadding="0" cellspacing="0" style="min-width:100%;max-width:1600px;width:1600px;">
					<thead>
						<tr>
							<th><input type="checkbox" name="allcheck" id="allcheck" onclick="selectAll('allcheck','ids')"/></th>
							<th class="center">票号</th>
							<th class="center">票据类型</th>
							<th class="center">票据品种</th>
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
								<c:forEach items="${resultList}" var="subcoll" varStatus="vs">
									<tr>
										<td class="center"><input type="checkbox" name="ids" value="${subcoll.id}" onclick="getall('ids')" price="${subcoll.billMoney}"/></td>
										<td class="center">${subcoll.billNo}</td>
										<td class="center">${fns:getDictLabel('K_BILL_TYPE',subcoll.billType)}</td>
										<td class="center">${fns:getDictLabel('K_BILL_CLASS',subcoll.billClass)}</td>
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
	</div>
	<form  action="#" name="dataCollectForm" method="post"></form>
	<%-- /列表操作区 --%>
	<div>
		<div id="page" class="pagination">
			<form action="<%=basePath%>subcollApplyController.do?method=elecNextView"  name="pageForm" method="post">
				<%@ include file="/webpage/system/admin/pageFormContent.jsp"%> 
				<input type="hidden" name="subcollId" value="${query.subcollId}"/>
				<input type="hidden" name="id" value="${id}"/>
				<input type="hidden" name="billClass" id="billClass" value="${query.billClass}"/>
			</form>
		</div>
	</div>
	<input type="hidden" name="subcollId" id="subcollId" value="${subcollId}"/>
	<input type="hidden" name="billClass" id="billClass" value="${billClass}"/>
</div>
</div>
<%@ include file="/webpage/system/admin/modalDialog.jsp"%>
<%@ include file="/webpage/system/admin/footer.jsp"%>
<script>
    var isOverdue = $("#isOverdue").val();
    if(isOverdue == "1"){
    	$(".yincang").hide();
    }
    $("#isOverdue").change(function(){
    	var isOverdue = $("#isOverdue").val();
        if(isOverdue == "1"){
        	$(".yincang").hide();
        }else{
        $(".yincang").show();
        }
    });
	//返回上一页
	function goHistory(){
		var ids = getCheckStr("ids");
		var billClass= "${query.billClass}";
		var billType= "${query.billType}";
		var startDay= "${query.startDay}";
		var endDay= "${query.endDay}";
		var bigMoney = "${query.bigMoney}";
		var smallMoney = "${query.smallMoney}";
		var acceptor = "${query.acceptor}";
		var billNo = "${query.billNo}";
		dynamicHiddenElement('dataCollectForm','ids',ids);
		dynamicHiddenElement('dataCollectForm','billClass',billClass);
		dynamicHiddenElement('dataCollectForm','billType',billType);
		dynamicHiddenElement('dataCollectForm','startDay',startDay);
		dynamicHiddenElement('dataCollectForm','endDay',endDay);
		dynamicHiddenElement('dataCollectForm','bigMoney',bigMoney);
		dynamicHiddenElement('dataCollectForm','smallMoney',smallMoney);
		dynamicHiddenElement('dataCollectForm','acceptor',acceptor);
		dynamicHiddenElement('dataCollectForm','billNo',billNo);
		modal("#layer_loading,#image");//点击票据管理按钮，弹出‘加载中’的遮罩层
		//if(bigMoney!="" || smallMoney!="" || acceptor!="" || billNo!=""){
			//dataCollectForm.action = "<%=basePath%>subcollApplyController.do?method=advancedQuery";
		//}else{
			//dataCollectForm.action = "<%=basePath%>subcollApplyController.do?method=elecBillManage";
		//}
		dataCollectForm.action = "<%=basePath%>subcollApplyController.do?method=elecBillManage";
		dataCollectForm.submit();
	}
	//先检查，符合后再提交
	function apply(){
		 var checkNum = getCheckNum("ids");
	  	 if (checkNum == 0){
	  		bootbox.alert("请先选择要提交的记录");
	  		return;
	  	 }
	  	var overdueReason = $("#overdueReason").val();
	  	var No = /^([\w\s\S\u4e00-\u9fa5]{0,60}|d*)$/;
	  	if(!No.test(overdueReason)){
			bootbox.alert("逾期原因最多可输入60位");
			return;
		    	}
	  	if($("#subcollManage").valid()){
			var id = getCheckStr("ids");
			var isOnline = $("#isOnline").val();
			var isOverdue = $("#isOverdue").val();
			var overdueReason = $("#overdueReason").val();
			var remark = $("#remark").val();
			var billClass= "${query.billClass}";
			var billType= "${query.billType}";
			var startDay= "${query.startDay}";
			var endDay= "${query.endDay}";
			var bigMoney = "${query.bigMoney}";
			var smallMoney = "${query.smallMoney}";
			var acceptor = "${query.acceptor}";
			var billNo = "${query.billNo}";
			$.ajax({
				url:"subcollApplyController.do?method=checkBill",
				type:"post",
				dataType:"JSON",
				data:{"ids":id},
				success:function(data){
	  				if(data.obj=="billType"){
						bootbox.alert("选择的票据类型不同,请选择相同票据类型");
					}else if(data.obj=="isOnline"){
						bootbox.confirm("本次选择含有行内提示付款的票据，只能选择线下清算，是否确认继续?", function(result) {
							if(result) {
								dynamicHiddenElement('dataCollectForm','ids',id);
								dynamicHiddenElement('dataCollectForm','isOnline',0);
								dynamicHiddenElement('dataCollectForm','isOverdue',isOverdue);
								dynamicHiddenElement('dataCollectForm','overdueReason',overdueReason);
								dynamicHiddenElement('dataCollectForm','remark',remark);
								dynamicHiddenElement('dataCollectForm','billClass',billClass);
								dynamicHiddenElement('dataCollectForm','billType',billType);
								dynamicHiddenElement('dataCollectForm','startDay',startDay);
								dynamicHiddenElement('dataCollectForm','endDay',endDay);
								dynamicHiddenElement('dataCollectForm','bigMoney',bigMoney);
								dynamicHiddenElement('dataCollectForm','smallMoney',smallMoney);
								dynamicHiddenElement('dataCollectForm','acceptor',acceptor);
								dynamicHiddenElement('dataCollectForm','billNo',billNo);
								modal("#layer_loading,#image");//点击票据管理按钮，弹出‘加载中’的遮罩层
								dataCollectForm.action = "<%=basePath%>subcollApplyController.do?method=apply";
								dataCollectForm.submit();
							}
						});
					}else{
						dynamicHiddenElement('dataCollectForm','ids',id);
						dynamicHiddenElement('dataCollectForm','isOnline',isOnline);
						dynamicHiddenElement('dataCollectForm','isOverdue',isOverdue);
						dynamicHiddenElement('dataCollectForm','overdueReason',overdueReason);
						dynamicHiddenElement('dataCollectForm','remark',remark);
						dynamicHiddenElement('dataCollectForm','billClass',billClass);
						dynamicHiddenElement('dataCollectForm','billType',billType);
						dynamicHiddenElement('dataCollectForm','startDay',startDay);
						dynamicHiddenElement('dataCollectForm','endDay',endDay);
						dynamicHiddenElement('dataCollectForm','bigMoney',bigMoney);
						dynamicHiddenElement('dataCollectForm','smallMoney',smallMoney);
						dynamicHiddenElement('dataCollectForm','acceptor',acceptor);
						dynamicHiddenElement('dataCollectForm','billNo',billNo);
						modal("#layer_loading,#image");//点击票据管理按钮，弹出‘加载中’的遮罩层
						dataCollectForm.action = "<%=basePath%>subcollApplyController.do?method=apply";
						dataCollectForm.submit();
					} 
	  			},
	  			error:function(data){
	  				alert("error");
	  			}
	  		});
	  	}
	}
	//详情页面
	function goDetail(id){
	   	 var diag = new top.Dialog();
		 diag.Drag = true;
		 diag.Title ="详情";
		 diag.URL = '<%=basePath%>elecBillDetailController.do?method=goDetail&rgctId='+id;
		 diag.Width = 930;
		 diag.Height = 575;
		 diag.CancelEvent = function(){ //关闭事件
			diag.close();
		 };
		 diag.show();
	}
	//打印清单
	function printList(){
		var checkNum = getCheckNum("ids");
  			if (checkNum == 0){
  				bootbox.alert("请先选择要打印的记录");
	  			return;
  	 		}
  	 		var ids = getCheckStr("ids");
			//批次号
			//var mysubId =$("#mxid").val();
			var mysubId="${batchId}";
			var overdueReason = $("#overdueReason").val();
	  	var No = /^(\w{0,60}|d*)$/;
	  	if(!No.test(overdueReason)){
			bootbox.alert("逾期原因最多可输入60位");
			return;
		    	}
	  	if($("#subcollManage").valid()){
			var id = getCheckStr("ids");
			var isOnline = $("#isOnline").val();
			var isOverdue = $("#isOverdue").val();
			var overdueReason = $("#overdueReason").val();
			var remark = $("#remark").val();
			var billClass= "${query.billClass}";
			var billType= "${query.billType}";
			var startDay= "${query.startDay}";
			var endDay= "${query.endDay}";
			var bigMoney = "${query.bigMoney}";
			var smallMoney = "${query.smallMoney}";
			var acceptor = "${query.acceptor}";
			var billNo = "${query.billNo}";
			$.ajax({
				url:"subcollApplyController.do?method=checkBill",
				type:"post",
				dataType:"JSON",
				data:{"ids":id},
				success:function(data){
	  				if(data.obj=="save"){
						doPrint("addPrintController.do?method=doPrint&ids="+ids+"&baid="+mysubId+"&moduleId=1070401&type=SubCollListPrint");	
	  				}else if(data.obj=="billType"){
						bootbox.alert("选择的票据类型不同,请选择相同票据类型");
					}else if(data.obj=="isOnline"){
						bootbox.confirm("本次选择含有行内提示付款的票据，只能选择线下清算，是否确认继续?", function(result) {
							if(result) {
								dynamicHiddenElement('dataCollectForm','ids',id);
								dynamicHiddenElement('dataCollectForm','isOnline',0);
								dynamicHiddenElement('dataCollectForm','isOverdue',isOverdue);
								dynamicHiddenElement('dataCollectForm','overdueReason',overdueReason);
								dynamicHiddenElement('dataCollectForm','remark',remark);
								dynamicHiddenElement('dataCollectForm','billClass',billClass);
								dynamicHiddenElement('dataCollectForm','billType',billType);
								dynamicHiddenElement('dataCollectForm','startDay',startDay);
								dynamicHiddenElement('dataCollectForm','endDay',endDay);
								dynamicHiddenElement('dataCollectForm','bigMoney',bigMoney);
								dynamicHiddenElement('dataCollectForm','smallMoney',smallMoney);
								dynamicHiddenElement('dataCollectForm','acceptor',acceptor);
								dynamicHiddenElement('dataCollectForm','billNo',billNo);
								modal("#layer_loading,#image");//点击票据管理按钮，弹出‘加载中’的遮罩层
								dataCollectForm.action = "<%=basePath%>subcollApplyController.do?method=save";
								dataCollectForm.submit();
								doPrint("addPrintController.do?method=doPrint&ids="+ids+"&baid="+mysubId+"&moduleId=1070401&type=SubCollListPrint");	
							}
						});
					}else{
						dynamicHiddenElement('dataCollectForm','ids',id);
						dynamicHiddenElement('dataCollectForm','isOnline',isOnline);
						dynamicHiddenElement('dataCollectForm','isOverdue',isOverdue);
						dynamicHiddenElement('dataCollectForm','overdueReason',overdueReason);
						dynamicHiddenElement('dataCollectForm','remark',remark);
						dynamicHiddenElement('dataCollectForm','billClass',billClass);
						dynamicHiddenElement('dataCollectForm','billType',billType);
						dynamicHiddenElement('dataCollectForm','startDay',startDay);
						dynamicHiddenElement('dataCollectForm','endDay',endDay);
						dynamicHiddenElement('dataCollectForm','bigMoney',bigMoney);
						dynamicHiddenElement('dataCollectForm','smallMoney',smallMoney);
						dynamicHiddenElement('dataCollectForm','acceptor',acceptor);
						dynamicHiddenElement('dataCollectForm','billNo',billNo);
						modal("#layer_loading,#image");//点击票据管理按钮，弹出‘加载中’的遮罩层
						dataCollectForm.action = "<%=basePath%>subcollApplyController.do?method=save";
						dataCollectForm.submit();
						doPrint("addPrintController.do?method=doPrint&ids="+ids+"&baid="+mysubId+"&moduleId=1070401&type=SubCollListPrint");	
					} 
	  			},
	  			error:function(data){
	  				bootbox.alert("系统错误");
	  			}
	  		});
	  	}
	}
	//凭证打印
	function printDocument(){
		var checkNum = getCheckNum("ids");
  			if (checkNum == 0){
  				bootbox.alert("请先选择要打印的记录");
	  			return;
  	 		}
  	 		var ids = getCheckStr("ids");
			//批次号
			//var mysubId =$("#mxid").val();
			var mysubId="${batchId}";
			var overdueReason = $("#overdueReason").val();
	  	var No = /^(\w{0,60}|d*)$/;
	  	if(!No.test(overdueReason)){
			bootbox.alert("逾期原因最多可输入60位");
			return;
		    	}
	  	if($("#subcollManage").valid()){
			var id = getCheckStr("ids");
			var isOnline = $("#isOnline").val();
			var isOverdue = $("#isOverdue").val();
			var overdueReason = $("#overdueReason").val();
			var remark = $("#remark").val();
			var billClass= "${query.billClass}";
			var billType= "${query.billType}";
			var startDay= "${query.startDay}";
			var endDay= "${query.endDay}";
			var bigMoney = "${query.bigMoney}";
			var smallMoney = "${query.smallMoney}";
			var acceptor = "${query.acceptor}";
			var billNo = "${query.billNo}";
			$.ajax({
				url:"subcollApplyController.do?method=checkBill",
				type:"post",
				dataType:"JSON",
				data:{"ids":id},
				success:function(data){
	  				if(data.obj=="save"){
    	  				doPrint("addPrintController.do?method=doPrint&ids="+ids+"&moduleId=1070101&type=SubCollPzPrint&baid="+mysubId);	
	  				}else if(data.obj=="billType"){
						bootbox.alert("选择的票据类型不同,请选择相同票据类型");
					}else if(data.obj=="isOnline"){
						bootbox.confirm("本次选择含有行内提示付款的票据，只能选择线下清算，是否确认继续?", function(result) {
							if(result) {
								dynamicHiddenElement('dataCollectForm','ids',id);
								dynamicHiddenElement('dataCollectForm','isOnline',0);
								dynamicHiddenElement('dataCollectForm','isOverdue',isOverdue);
								dynamicHiddenElement('dataCollectForm','overdueReason',overdueReason);
								dynamicHiddenElement('dataCollectForm','remark',remark);
								dynamicHiddenElement('dataCollectForm','billClass',billClass);
								dynamicHiddenElement('dataCollectForm','billType',billType);
								dynamicHiddenElement('dataCollectForm','startDay',startDay);
								dynamicHiddenElement('dataCollectForm','endDay',endDay);
								dynamicHiddenElement('dataCollectForm','bigMoney',bigMoney);
								dynamicHiddenElement('dataCollectForm','smallMoney',smallMoney);
								dynamicHiddenElement('dataCollectForm','acceptor',acceptor);
								dynamicHiddenElement('dataCollectForm','billNo',billNo);
								modal("#layer_loading,#image");//点击票据管理按钮，弹出‘加载中’的遮罩层
								dataCollectForm.action = "<%=basePath%>subcollApplyController.do?method=save";
								dataCollectForm.submit();
    	  				doPrint("addPrintController.do?method=doPrint&ids="+ids+"&moduleId=1070101&type=SubCollPzPrint&baid="+mysubId);	
							}
						});
					}else{
						dynamicHiddenElement('dataCollectForm','ids',id);
						dynamicHiddenElement('dataCollectForm','isOnline',isOnline);
						dynamicHiddenElement('dataCollectForm','isOverdue',isOverdue);
						dynamicHiddenElement('dataCollectForm','overdueReason',overdueReason);
						dynamicHiddenElement('dataCollectForm','remark',remark);
						dynamicHiddenElement('dataCollectForm','billClass',billClass);
						dynamicHiddenElement('dataCollectForm','billType',billType);
						dynamicHiddenElement('dataCollectForm','startDay',startDay);
						dynamicHiddenElement('dataCollectForm','endDay',endDay);
						dynamicHiddenElement('dataCollectForm','bigMoney',bigMoney);
						dynamicHiddenElement('dataCollectForm','smallMoney',smallMoney);
						dynamicHiddenElement('dataCollectForm','acceptor',acceptor);
						dynamicHiddenElement('dataCollectForm','billNo',billNo);
						modal("#layer_loading,#image");//点击票据管理按钮，弹出‘加载中’的遮罩层
						dataCollectForm.action = "<%=basePath%>subcollApplyController.do?method=save";
						dataCollectForm.submit();
    	  				doPrint("addPrintController.do?method=doPrint&ids="+ids+"&moduleId=1070101&type=SubCollPzPrint&baid="+mysubId);	
					} 
	  			},
	  			error:function(data){
	  				bootbox.alert("系统错误");
	  			}
	  		});
	  	}
	}
</script>
</body>
</html>