<%-- 
 * 文件名称:disc_audit_detail_list.jsp
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
				<input type="hidden" name="acctNo" value="${query.acctNo}"/> 
					<%-- 查询区  --%>
					<div class="row-fluid">
						<label for="batchNo" class="pull-left control-label">批次号</label>
						<div class="pull-left batch">${batch.batchNo}</div>
						<label for="industry_investment">票据类型</label>
						<input type="text" class="input-medium" name="billType" readonly value="${fns:getDictLabel('K_BILL_TYPE',batch.billType)}"/>
						<label for="totalNum">票据种类</label>
						<input type="text" class="input-medium" name="totalNum" disabled value="${fns:getDictLabel('K_BILL_CLASS',batch.billClass)}" />
						<label for="custManage">产品名称</label>
						<input type="text" class="input-medium" name="custManage" value="${ProdName}" disabled />
					</div>
					<div class="row-fluid" >
						<label for="totalMoney">付息方式</label>
						<input type="text" class="input-medium" name="totalMoney" disabled value="${fns:getDictLabel('K_PAY_TYPE',batch.payType)}" />
						<c:if test="${batch.payType==0}">
							<label for="totalMoney">买方付息比例</label>
							<input type="text" class="input-medium" name="totalMoney" disabled value="${batch.buyPayRate}" />
						</c:if>
						<label for="rate">贴现利率</label>
						<input type="text" class="input-medium" name="rate" disabled value="${batch.rate}"/>
						<label for="totalNum">成本利率</label>
						<input type="text" class="input-medium" name="totalNum" disabled value="${batch.cbRate}" />
					</div>
					<div class="row-fluid">
						<label for="industry_investment">行业投向</label>
						<input type="text" class="input-medium" name="professionName" disabled value="${batch.professionName}"/>
						<label for="discDt">贴现日</label>
						<input type="text" class="input-medium" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});" name="discDt" value="${batch.discDt}"/>
					</div>
					<div class="row-fluid">
						<label for="custName">客户名称</label>
						<input type="text" class="input-medium" id="custName" name="custName" value="${batch.custName}" disabled />
						<label for="custNo">客户号</label>
						<input type="text" class="input-medium" id="custNo" name="custNo" disabled value="${batch.custNo}" />
						<label for="accountnumber_status">卖方账号类型</label>
						<input type="text" class="input-medium" name="accountnumber_status" disabled value="结算账号"/>
						<label for="custAccount">客户账号</label>
						<input type="text" class="input-medium" id="ruzhang" name="ruzhang" disabled value="${batch.custAccount }" />
					</div>
					<div class="row-fluid">
						<label for="custManagerName">客户经理名称</label>
						<input type="text" class="input-medium" name="custManagerName" value="${batch.custManagerName}" disabled />
						<label for="custManage">客户经理编号</label>
						<input type="text" class="input-medium" name="custManage" value="${batch.custManage}" disabled />
						<label for="deptName">客户经理部门</label>
						<input type="text" class="input-medium" disabled name="deptName" value="${batch.deptName}" />
					</div>
					<div class="row-fluid">
						<label for="totalNum">票据张数</label>
						<input type="text" class="input-medium" name="totalNum" disabled value="${batch.totalNum}" />
						<label for="totalMoney">总金额</label>
						<input type="text" class="input-medium" name="totalMoney" disabled value="${fns:formateMoney(batch.totalMoney)}" />
						<label for="profOwner">总利息</label>
						<input type="text" class="input-medium" name="profOwner" disabled value="${fns:formateMoney(batch.totalInterest)}" />
						<label for="profOwner">实付金额</label>
						<input type="text" class="input-medium" name="profOwner" disabled value="${fns:formateMoney(batch.actualAmount)}" />
					</div>
				</form>
			</div>
			<%-- 按钮操作区 --%>
			<div id="center">
				<form id="btnForm" name="btnForm" action="<%=basePath%>discAuditController.do?method=auditList" method="post">
					<div class="row-fluid">
						<div class="span6" id="btn-left">
						</div>
						<div class="span6" id="btn-right">
							<a class="btn-mini pull-right" onclick="printPZ();">打印凭证</a>					
							<a class="btn-mini pull-right" onclick="printList();">打印清单</a>
							<a class="btn-mini pull-right" href="javascript:audit();">审核</a>				
							<a class="btn-mini pull-right" onclick="goHistory();">返回</a>
						</div>
		  			</div>
				</form>
			</div>
			<div id="footer" class="footer">
				<table class="table table-striped table-bordered table-hover" id="tab" cellpadding="0" cellspacing="0" style="min-width:100%;max-width:1600px;width:1600px;">
					<thead>
						<tr>
							<th class="center"><input type="checkbox" name="allcheck" id="allcheck" onclick="selectAll('allcheck','ids')"/></th>
							<th class="center">票号</th>
							<th class="center">票据类型</th>
							<th class="center">出票日</th>
							<th class="center">票面到期日</th>
							<th class="center">计息到期日</th>
							<th class="center">票面金额</th>
							<th class="center">利息</th>
							<th class="center">实付金额</th>
							<th class="center">承兑行</th>
							<th class="center">出票人</th>
							<th class="center">出票人开户行行号</th>
							<th class="center">收款人</th>
							<th class="center">详情</th>
						</tr>
					</thead>
					<tbody>
						<c:choose>
							<c:when test="${not empty resultList}">
								<c:forEach items="${resultList}" var="disc" varStatus="vs">
									<tr>
										<td class="center"><input type="checkbox" name="ids" value="${disc.discmxId}"/></td>
										<td class="center">${disc.billNo}</td>
										<td class="center">${fns:getDictLabel('K_BILL_TYPE',disc.billType)}</td>
										<td class="center">${disc.issueDt}</td>
										<td class="center">${disc.dueDt}</td>
										<td class="center">${disc.galeDate}</td>
										<td class="center">${fns:formateMoney(disc.billMoney)}</td>
										<td class="center">${fns:formateMoney(disc.interest)}</td>
										<td class="center">${fns:formateMoney(disc.payMoney)}</td>
										<td class="center">${disc.acceptorBankName}</td>
										<td class="center">${disc.remitter}</td>
										<td class="center">${disc.remitterBankNo}</td>
										<td class="center">${disc.payee}</td>
										<td class="center"><a href="javascript:goDetail('${disc.discmxId}','${disc.discId}')">查看</a></td>
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
			<form>
				<input type="hidden" id="audit-result" name="audit-result" value=""/>
			</form>
			<%-- /列表操作区 --%>
			<div id="page" class="pagination">
				<form action="<%=basePath%>discAuditController.do?method=auditDetailList"  name="pageForm" method="post">
					<%@ include file="/webpage/system/admin/pageFormContent.jsp"%>
					<input type="hidden" name="discId" value="${batch.discId}"/>
				</form>
			</div>
		</div>
	</div>
<%@ include file="/webpage/system/admin/modalDialog.jsp"%>
<%@ include file="/webpage/system/admin/footer.jsp"%>
<script>
	function audit(){
		 var checkNum = getCheckNum("ids");
	   	 if (checkNum == 0){
	   		bootbox.alert("请先选择要审核的记录");
	   		return;
	   	 }
		var ids = getCheckStr("ids");
	   	 var diag = new top.Dialog();
		 diag.Drag = true;
		 diag.Title ="审核";
		 diag.URL = "<%=basePath%>discAuditController.do?method=toAudit&ids="+ids;
		 diag.Width = 430;
		 diag.Height =200;
		 diag.CancelEvent = function(){ //关闭事件
		 	//var val=localStorage.getItem('value');
		 	//alert(val);
		 	if($("#audit-result").val()==1){
		 		btnForm.submit();
		 	}
		 	//localStorage.removeItem('value');
		 	diag.close();
		 };
		 diag.show();
	}
	//详情页面
	function goDetail(discmxId,discId){
	   	 var diag = new top.Dialog();
		 diag.Drag = true;
		 diag.Title ="详情";
		 diag.URL ="<%=basePath%>discAuditController.do?method=goDetail&discmxId="+discmxId+"&discId="+discId;
		 diag.Width = 920;
		 diag.Height = 540;
		 diag.CancelEvent = function(){ //关闭事件
			diag.close();
		 };
		 diag.show();
	}
	function goHistory(){
		modal("#layer_loading,#image");//点击票据管理按钮，弹出‘加载中’的遮罩层
		btnForm.action = "<%=basePath%>discAuditController.do?method=auditList";
		btnForm.submit();
	}
	 //打印贴现申请清单
    //operStatusString 票据状态
	function printList(){
		var batch_id="${batch.discId}";
		var checkNum = getCheckNum("ids");
	   	 if (checkNum == 0){
	   		bootbox.alert("请先选择要打印的记录");
	   		return;
	   	 }   	 
		var ids = getCheckStr("ids");
		$.ajax({
			url:"discApplyController.do?method=isInterestTrial",
			type:"POST",
			data:{"ids":ids},
			dataType:"json",
			success:function(rs){
				if(rs.success){//是否计算过利息
				 	doPrint("addPrintController.do?method=doPrint&ids="+ids+"&moduleId=1030601&type=DiscAuditPrint&baid="+batch_id+"&handleType=审 核"); 	 
				}else{
					bootbox.alert("请先进行利息试算");
				}
			},
			error:function(rs){
				bootbox.alert("系统异常");
			}
		}); 	
	}
	//打印贴现申请凭证
	function printPZ(){
		var payType="${batch.payType}";//付息方式 1买 2卖 3协议
		var batch_id="${batch.discId}";
		var checkNum = getCheckNum("ids");
	   	 if (checkNum == 0){
	   		bootbox.alert("请先选择要打印的记录");
	   		return;
	   	 }   	 
		var ids = getCheckStr("ids");
		$.ajax({
			url:"discApplyController.do?method=isInterestTrial",
			type:"POST",
			data:{"ids":ids},
			dataType:"json",
			success:function(rs){
				if(rs.success){//是否计算过利息
					if(payType!="2"){
						doPrint("addPrintController.do?method=doPrint&ids="+ids+"&moduleId=1030301&type=DiscPrint&baid="+batch_id); 	 						 
	        		}else{
						doPrint("addPrintController.do?method=doPrint&ids="+ids+"&moduleId=1030102&type=DiscPrint&baid="+batch_id); 	 
					}
				}else{
					bootbox.alert("请先进行利息试算");
				}
			},
			error:function(rs){
				bootbox.alert("系统异常");
			}
		});
	}
</script>
</body>
</html>