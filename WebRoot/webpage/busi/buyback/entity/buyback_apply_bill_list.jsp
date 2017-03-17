<%-- 
 * 文件名称: buyback_apply_bill_list.jsp
 * 系统名称: 票据管理平台
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: @version2.0.0.1
 * 开发人员: songzx
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
				<form action=""	method="post" name="Form" id="Form" class="form-search detail-form">
					<input type="hidden" id="buybackId" value="${batch.buybackId}"></input>
					<input type="hidden" id="isInner" value="${isInner}"></input>
					<input type="hidden" id="pBuybackDt" value="${buybackDt}"></input>
					<input type="hidden" id="pBuybackRate" value="${buybackRate}"></input>
					<%-- 查询区  --%>
					<div class="row-fluid">
						<label for="batchNo" class="pull-left control-label">批次号</label>
						<div class="pull-left batch">${batch.batchNo}</div>
						<label for="industry_investment">票据类型</label>
						<input type="text" class="input-medium" name="billType" disabled value="${fns:getDictLabel('K_BILL_TYPE',batch.billType)}"/>
						<label for="totalNum">票据种类</label>
						<input type="text" class="input-medium" name="totalNum" disabled value="${fns:getDictLabel('K_BILL_CLASS',batch.billClass)}" />
						<label for="custName">客户名称</label>
						<input type="text" class="input-medium" id="custName" name="custName" value="${batch.custName}" disabled />
					</div>
					<div class="row-fluid">
						<label for="totalNum">票据张数</label>
						<input type="text" class="input-medium" name="totalNum" disabled value="${batch.totalNum}" />
						<label for="totalMoney">总金额</label>
						<input type="text" class="input-medium" name="totalMoney" disabled value="${fns:formateMoney(batch.totalMoney)}" />
						<label for="profOwner">总利息</label>
						<input type="text" class="input-medium" name="profOwner" disabled value="${fns:formateMoney(batch.totalInterest)}" />
						<label for="profOwner">实付金额</label>
						<input type="text" class="input-medium" name="totalDraweeMoney" disabled value="${fns:formateMoney(batch.totalDraweeMoney)}" />
					</div>
					<div class="row-fluid">
						<label for="buybackDt">实际赎回日</label>
						<input name="buybackDt" id="buybackDt" value ="${buybackDt}" valid="required dateISO" type="text" class="input-medium" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"/>
						<label for="buybackRate">赎回利率</label>
						<input type="text" class="input-medium" name="buybackRate" id="buybackRate" title="请输入0-100之间千分位的小数或整数" onblur = "checkRate();" value="${fns:formateRate(buybackRate)}"/>
					</div>
				</form>
			</div>
			<%-- 按钮操作区 --%>
			<div id="center">
				<form  id="btnForm" name="btnForm" method="post">
				<div class="row-fluid">
					<div class="span6" id="btn-left">
						<a class="btn-mini" onclick="trial('${batch.saleId}');">计算利息</a>
						<%--<a class="btn-mini" onclick="toCheckTrial();">调整利息</a>
						--%><a class="btn-mini" onclick="printList();">打印清单</a>
				   </div>
				   <div class="span6" id="btn-right">
						<a class="btn-mini pull-right" onclick="apply();">申请</a>
						<a class="btn-mini pull-right" onclick="goHistory();">返回</a>
				   </div>
				</div>
			</form>
		</div>
		<div id="footer" class="footer">
			<table class="table table-striped table-bordered table-hover" id="tab" cellpadding="0" cellspacing="0">
				<thead>
					<tr>
						<th class="center"><input type="checkbox" name="allcheck" id="allcheck" onclick="selectAll('allcheck','ids')"/></th>
						<th class="center">票号</th>
						<th class="center">原卖出日</th>
						<th class="center">回购到期日</th>
						<th class="center">计息天数</th>
						<th class="center">票面金额</th>
						<th class="center">回购利息</th>
						<th class="center">实付金额</th>
						<th class="center">原转出实收金额</th>
						<th class="center">承兑行</th>
						<th class="center">出票人</th>
						<th class="center">详情</th>
					</tr>
				</thead>
				<tbody>
					<c:choose>
						<c:when test="${not empty resultList}">
							<c:forEach items="${resultList}" var="buyback" varStatus="vs">
								<tr>
									<td class="center"><input type="checkbox" name="ids" value="${buyback.buybackmxId}"/></td>
									<td class="center">${buyback.billNo}</td>
									<td class="center">${buyback.saleDt}</td>
									<td class="center">${buyback.regressDt}</td>
									<td class="center">${buyback.interestDays}</td>
									<td class="center">${fns:formateMoney(buyback.billMoney)}</td>
									<td class="center">${fns:formateMoney(buyback.interest)}</td>
									<td class="center">${fns:formateMoney(buyback.buybackMoney)}</td>
									<td class="center">${fns:formateMoney(buyback.saleReceiveMoney)}</td>
									<td class="center">${buyback.acceptorBankName}</td>
									<td class="center">${buyback.remitter}</td>
									<td class="center"><a href="javascript:goDetail('${buyback.buybackmxId}')">查看</a></td>
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
		<form action="#" name="searchForm" method="post">
			<t:token></t:token>
		</form>
		<%-- /列表操作区 --%>
		<div>
			<div id="page" class="pagination">
				<form action="<%=basePath%>buybackApplyController.do?method=entityBuybackBillList" method="post" name="pageForm">
					<%@ include file="/webpage/system/admin/pageFormContent.jsp"%> 
					<input type="hidden" name="saleId" value="${batch.saleId}"/>
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
		modal("#layer_loading,#image")
		var isInner=$("#isInner").val();
		btnForm.action = "<%=basePath%>buybackApplyController.do?method=entityBuybackApplyList&isInner="+isInner;
		btnForm.submit();
	}
	//详情页面
	function goDetail(buybackmxId){
		var diag = new top.Dialog();
		 diag.Drag = true;
		 diag.Title ="详情";
		 diag.URL = "<%=basePath%>buybackApplyController.do?method=goBillDetail&buybackmxId="+buybackmxId;
		 diag.Width = 930;
		 diag.Height = 320;
		 diag.CancelEvent = function(){ //关闭事件
			diag.close();
		 };
		 diag.show(); 
	}
	//利息试算
	function trial(saleId){
		if(checkRate()){
			var checkNum = getCheckNum("ids");
			var buybackDt=$("#buybackDt").val();
			var buybackRate=$("#buybackRate").val();
			var buybackId="${batch.buybackId}";
			var batchNo="${batch.batchNo}";
		   	 if (checkNum == 0){
		   		bootbox.alert("请先选择要利息试算的记录");
		   		return;
		   	 }
		   	if (buybackDt==""||buybackDt==null){
		   		bootbox.alert("请先输入实际赎回日");
		   		return;
		   	 }
		   	if (buybackRate==""||buybackRate==null){
		   		bootbox.alert("请先输入赎回利率");
		   		return;
		   	 }
			 var ids = getCheckStr("ids");
			 var isInner=$("#isInner").val();
		   	 var diag = new top.Dialog();
			 diag.Drag = true;
			 diag.Title ="利息试算";
			 diag.URL = "<%=basePath%>buybackApplyController.do?method=toInterestTrialForEntity&ids="+ids+"&buybackDt="+buybackDt+"&buybackRate="+buybackRate+"&buybackId="+buybackId+"&batchNo="+batchNo+"&saleId="+saleId;
			 diag.Width = 480;
			 diag.Height = 200;
			 diag.CancelEvent = function(){ //关闭事件
				dynamicHiddenElement('searchForm','saleId',saleId);
				dynamicHiddenElement('searchForm','buybackDt',buybackDt);
		        dynamicHiddenElement('searchForm','buybackRate',buybackRate);
		        dynamicHiddenElement('searchForm','isInner',isInner);
		        dynamicHiddenElement('searchForm','buybackId',buybackId);
		        searchForm.action="<%=basePath%>buybackApplyController.do?method=entityBuybackBillList";
		        searchForm.submit();
				diag.close();
			 };
			 diag.show(); 
		}
	}
	//申请提交
	function apply(){
		var checkNum = getCheckNum("ids");
	   	 if (checkNum == 0){
	   		bootbox.alert("请先选择要提交的记录");
	   		return;
	   	 }
	   	var pBuybackDt = $("#pBuybackDt").val();
	   	var pBuybackRate = parseInt($("#pBuybackRate").val(),10);
	   	var buybackDt = $("#buybackDt").val();
	   	var buybackRate = parseInt($("#buybackRate").val(),10);
		var ids = getCheckStr("ids");
		var buybackId="${batch.buybackId}";
		var batchNo="${batch.batchNo}";
		var saleId="${batch.saleId}";
		$.ajax({
			url:"buybackApplyController.do?method=isInterestTrial",
			type:"POST",
			data:{"ids":ids},
			dataType:"json",
			success:function(rs){
				if(rs.success){//是否计算过利息
					if(pBuybackDt!=buybackDt||pBuybackRate!=buybackRate){
				   		bootbox.alert("修改实际赎回日或赎回利率请重新计算利息");
				   		return;
					 }else{
					modal("#layer_loading,#image");
					dynamicHiddenElement('searchForm','ids',ids);
					dynamicHiddenElement('searchForm','buybackId',buybackId);
					dynamicHiddenElement('searchForm','batchNo',batchNo);
					dynamicHiddenElement('searchForm','saleId',saleId);
					dynamicHiddenElement('searchForm','buybackDate',buybackDt);
					dynamicHiddenElement('searchForm','backRate',buybackRate);
					searchForm.action = "<%=basePath%>buybackApplyController.do?method=entityApply";
					searchForm.submit();
					 }
				}else{
					bootbox.alert("请先进行利息试算");
				}
			},
			error:function(rs){
				bootbox.alert("系统异常!");
			}
		}); 	
	}
	//检查利率
	function checkRate(){
	    var r = /^(\d{1,2}(\.\d{1,2})?|100)$/;
	    var rate = $("#buybackRate").val();
	    if(rate.length>0){
	        if(r.test(rate)){
	            return true;
	        }else{
	        	bootbox.alert("请输入0-100之间千分位的小数或整数");
	            return false;
	        }
	    }else{
	        return true;
	    }
	}
	//检查是否利息试算
	function toCheckTrial(){
		var checkNum = getCheckNum("ids");
	   	 if (checkNum == 0){
	   		bootbox.alert("请先选择要调整利息的记录");
	   		return;
	   	 }
	   	if (checkNum !=1){
  			bootbox.alert("一次只能对一条记录进行操作");
  			return;
  	 	}
	   	var id = getCheckStr("ids");
	   	$.ajax({
			url:"buybackApplyController.do?method=isInterestTrial",
			type:"POST",
			data:{"ids":id},
			dataType:"json",
			success:function(rs){
				if(rs.success){//是否计算过利息
					interestAdjust(id);
				}else{
					bootbox.alert("请先进行利息试算");
				}
			},
			error:function(rs){
				bootbox.alert("系统异常!");
			}
		});
	}
	//调整利息
	function interestAdjust(id){
	   	var diag = new top.Dialog();
	   	var saleId="${batch.saleId}";
		var pBuybackDt = $("#pBuybackDt").val();
	   	var pBuybackRate = parseInt($("#pBuybackRate").val(),10);
	   	var buybackDt=$("#buybackDt").val();
	   	var buybackRate=parseInt($("#buybackRate").val(),10);
	   	var isInner=$("#isInner").val();
	   	if(pBuybackDt!=buybackDt||pBuybackRate!=buybackRate){
	   		bootbox.alert("修改实际赎回日或赎回利率请重新计算利息");
	   		return;
		 }
		diag.Drag = true;
		diag.Title ="调整利息";
		diag.URL = "<%=basePath%>buybackApplyController.do?method=toAdjustInterest&id="+id;
		diag.Width = 430;
		diag.Height =230;
		diag.CancelEvent = function(){ //关闭事件
			diag.close();
			dynamicHiddenElement('searchForm','saleId',saleId);
			dynamicHiddenElement('searchForm','buybackDt',buybackDt);
			dynamicHiddenElement('searchForm','buybackRate',buybackRate);
			dynamicHiddenElement('searchForm','isInner',isInner);
			searchForm.action = "<%=basePath%>buybackApplyController.do?method=entityBuybackBillList"
			searchForm.submit();
		 };
		 diag.show();
	}
	//回购打印清单
	function printList(){
	var batch_id = "${batch.buybackId}";
	 	 var checkNum = getCheckNum("ids");
	   	 if (checkNum == 0){
	   		bootbox.alert("请先选择要打印的记录");
	   		return;
	   	 }   	 
		var ids = getCheckStr("ids");
	   	$.ajax({
			url:"buybackApplyController.do?method=isInterestTrial",
			type:"POST",
			data:{"ids":ids},
			dataType:"json",
			success:function(rs){
				if(rs.success){//是否计算过利息
					doPrint("addPrintController.do?method=doPrint&ids="+ids+"&moduleId=1060701&type=BuybackListPrint&baid="+batch_id+"&handleType=申 请"); 	 
					
				}else{
					bootbox.alert("请先进行利息试算");
				}
			},
			error:function(rs){
				bootbox.alert("系统异常!");
			}
		});
	}
</script>
</body>
</html>