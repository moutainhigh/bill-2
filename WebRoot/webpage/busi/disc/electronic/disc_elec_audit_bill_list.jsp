<%-- 
 * 文件名称:disc_elec_audit_bill_list.jsp
 * 系统名称: 票据管理平台
 * 模块名称:
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
	<script type="text/javascript" src="webpage/system/addPrint/commonFunc.js"></script>
</head>
<body>
	<div class="clearfix">
		<div id="page-content" class="page-content">
			<div id="header">
				<form action=""	method="post" name="Form" id="Form" class="form-search detail-form">
				<%-- <input type="hidden" name="acctNo" value="${query.acctNo}"/>  --%>
					<%-- 查询区  --%>
					<div class="row-fluid">
						<label for="batchNo" class="pull-left control-label">批次号</label>
						<div class="pull-left batch">${batch.batchNo}</div>
						<label for="industry_investment">票据类型</label>
						<input type="text" class="input-medium" name="billType" readonly value="${fns:getDictLabel('K_BILL_TYPE',batch.billType)}"/>
						<label for="totalNum">票据种类</label>
						<input type="text" class="input-medium" name="billClass" disabled value="${fns:getDictLabel('K_BILL_CLASS',batch.billClass)}" />
						<label for="tradeAcctName">产品名称</label>
						<input type="text" class="input-medium" name="prodName" disabled value="${prodName}" />
					</div>
					<div class="row-fluid">
						<label for="totalMoney">付息方式</label>
						<t:dictSelect  name="payType" className="select-medium" other="" dictGroup="K_PAY_TYPE" defaultVal="${batch.payType}" haveHead="true" valid="required" disabled="disabled">
				        </t:dictSelect>
						<span class="temp buyPayRate">
				        	<input type="hidden" name="payType" value="${batch.payType}"/> 
						    <label for="totalMoney">付息比例</label>
						    <input type="text" class="input-medium" name="buyPayRate" disabled value="${fns:formateRate(batch.buyPayRate)}" />
						</span>
						<label for="rate">利率</label>
						<input type="text" class="input-medium" id="rate" name="rate" disabled value="${fns:formateRate(batch.rate)}"/>
						<label for="totalNum">成本利率</label>
						<input type="text" class="input-medium" name="cbRate" disabled value="${fns:formateRate(batch.cbRate)}" />
					</div>
					<div class="row-fluid">
						<label for="industry_investment">行业投向</label>
						<input type="text" class="input-medium" name="professionName" disabled value="${batch.professionName}"/>
						<label for="discDt">贴现日</label>
						<input type="text" class="input-medium" id="discDt" name="discDt" disabled value="${batch.discDt}"/>
					</div>
					<div class="row-fluid">
						<label for="custName">客户名称</label>
						<input type="text" class="input-medium" name="custName" value="${batch.custName}" disabled />
						<label for="custNo">客户号</label>
						<input type="text" class="input-medium" name="custNo" disabled value="${batch.custNo}" />
						<label for="accountnumber_status">账号类型</label>
						<input type="text" class="input-medium" name="custAccountType" disabled value="结算账号"/>
						<label for="custAccount">客户账号</label>
						<input type="text" class="input-medium" id="custAccount" name="custAccount" disabled value="${batch.custAccount }" />
					</div>
					<div class="row-fluid">
						<label for="custManagerName">客户经理名称</label>
						<input type="text" class="input-medium" name="custManagerName" value="${batch.custManagerName}" disabled />
						<label for="custManage">客户经理编号</label>
						<input type="text" class="input-medium" name="custManage" value="${batch.custManage}" disabled />
						<label for="deptName">部门</label>
						<input type="text" class="input-medium" disabled name="deptName" value="${batch.deptName}" />
					</div>
					<div class="row-fluid">
						<label for="totalNum">票据张数</label>
						<input type="text" class="input-medium" name="totalNum" disabled value="${batch.totalNum}" />
						<label for="totalMoney">总金额</label>
						<input type="text" class="input-medium" name="totalMoney" disabled value="${fns:formateMoney(batch.totalMoney)}" />
						<label for="profOwner">总利息</label>
						<input type="text" class="input-medium" name="totalInterest" disabled value="${fns:formateMoney(batch.totalInterest)}" />
						<label for="profOwner">试算实付金额</label>
						<input type="text" class="input-medium" name="actualAmount" disabled value="${fns:formateMoney(batch.actualAmount)}" />
					</div>
				</form>
			</div>
			<%-- 按钮操作区 --%>
			<div id="center">
				<form id="btnForm" name="btnForm" action="<%=basePath%>discAuditController.do?method=auditListElec" method="post">
					<div class="row-fluid">
						<div class="span6" id="btn-left">
						<a class="btn-mini" onclick="printDocument();">打印凭证</a>
							<a class="btn-mini" onclick="printList();">打印清单</a>
						</div>
						<div class="span6" id="btn-right">
							<a class="btn-mini pull-right" href="javascript:audit();">审核</a>
							<a class="btn-mini pull-right" onclick="goHistory();">返回</a>
					   </div>
				  </div>
			</form>
		</div>
			<div id="footer" class="footer">
				<table class="table table-striped table-bordered table-hover" id="tab" cellpadding="0" cellspacing="0" style="min-width:100%;max-width:2600px;width:2600px;">
					<thead>
						<tr>
							<th class="center"><input type="checkbox" name="allcheck" id="allcheck" onclick="selectAll('allcheck','ids')"/></th>
							<th class="center">票号</th>
							<th class="center">入账账号</th>
							<th class="center">入账行号</th>
							<th class="center">票据类型</th>
							<th class="center">出票日</th>
							<th class="center">票面到期日</th>
							<th class="center">计息到期日</th>
							<th class="center">贴现日</th>
							<th class="center">利率</th>
							<th class="center">票面金额</th>
							<th class="center">不得转让标记</th>
							<th class="center">报文实付金额</th>
							<th class="center">试算实付金额</th>
							<th class="center">计息天数</th>
							<th class="center">贴现利息</th>
							<th class="center">承兑行</th>
							<th class="center">出票人</th>
							<th class="center">收款人</th>
							<th class="center">详情</th>
						</tr>
					</thead>
					<tbody>
					<c:choose>
						<c:when test="${not empty resultList}">
							<c:forEach items="${resultList}" var="disc" varStatus="vs">
								<tr>
									<td class="center"><input type="checkbox" name="ids" value="${disc.discmxId}" onclick="getall('ids')" price="${disc.billMoney}"/></td>
									<td class="center">${disc.billNo}</td>
									<td class="center">${disc.inAcctNo}</td>
									<td class="center">${disc.inBankNo}</td>
									<td class="center">${fns:getDictLabel('K_BILL_TYPE',disc.billType)}</td>
									<td class="center">${disc.issueDt}</td>
									<td class="center">${disc.dueDt}</td>
									<td class="center">${disc.galeDate}</td>
									<td class="center">${disc.discDt}</td>
									<td class="center">${disc.rate}</td>
									<td class="center">${fns:formateMoney(disc.billMoney)}</td>
									<td class="center">${fns:getDictLabel('K_FORBIDFLAG',disc.forbidFlag)}</td>
									<td class="center">${fns:formateMoney(disc.draftPayMoney)}</td>
									<td class="center">${fns:formateMoney(disc.localPayMoney)}</td>
									<td class="center">${disc.interestDays}</td>
									<td class="center">${fns:formateMoney(disc.interest)}</td>
									<td class="center">${disc.acceptor}</td>
									<td class="center">${disc.remitter}</td>
									<td class="center">${disc.payee}</td>
									<td class="center"><a href="javascript:goDetail('${disc.rgctId}')">查看</a></td>
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
		<form>
			<input type="hidden" id="audit-result" name="audit-result" value=""/>
		</form>
		<%-- /列表操作区 --%>
		<div id="page" class="pagination">
			<form action="<%=basePath%>discAuditController.do?method=auditBillList"  name="pageForm" method="post">
				<%@ include file="/webpage/system/admin/pageFormContent.jsp"%>
				<input type="hidden" name="discId" value="${batch.discId}"/>
			</form>
		</div>
	</div>
</div>
<%@ include file="/webpage/system/admin/modalDialog.jsp"%> 
<%@ include file="/webpage/system/admin/footer.jsp"%>
<script>
    var buyPayRate = '${batch.payType}';
    if(buyPayRate=='3'){
        $(".buyPayRate").show();
    }else{
        $(".buyPayRate").hide();
    }
	function audit(){
		 var checkNum = getCheckNum("ids");
	   	 if (checkNum == 0){
	   		bootbox.alert("请先选择要审核的记录");
	   		return;
	   	 }
		var ids = getCheckStr("ids");
		$.ajax({
			type: "POST",
			url: '<%=basePath%>discAuditController.do?method=checkAudit',
	    	data: {'ids': ids},
			dataType:'json',
			cache: false,
			success: function(data){	
				if(data.success){  //处理成功
 	                toAuditPage(ids);
				} else {
					bootbox.alert(data.msg); 
				}
			}
		});
	}
	function toAuditPage(ids){
	    //var ids = getCheckStr("ids");
	    var diag = new top.Dialog();
		 diag.Drag = true;
		 diag.Title ="审核";
		 diag.URL = "<%=basePath%>discAuditController.do?method=toAuditElec&ids="+ids;
		 diag.Width = 430;
		 diag.Height =180;
		 diag.CancelEvent = function(){ //关闭事件
		 	//var val=localStorage.getItem('value');
		 	if($("#audit-result").val()==1){
		 		btnForm.submit();
		 	}
		 	//localStorage.removeItem('value');
		 	diag.close();
		 };
		 diag.show();
	}
	function goHistory(){
	    modal("#layer_loading,#image");
		btnForm.action = "<%=basePath%>discAuditController.do?method=auditListElec";
		btnForm.submit();
	}
	//详情页面
	function goDetail(rgctId){
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
	 //打印贴现申请清单
    //operStatusString 票据状态
	function printList(){
		var payType=$("#payType").val();//付息方式 1买 2卖 3协议
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
	//打印贴现审核凭证
	function printDocument(){
		var payType=$("#payType").val();//付息方式 1买 2卖 3协议
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