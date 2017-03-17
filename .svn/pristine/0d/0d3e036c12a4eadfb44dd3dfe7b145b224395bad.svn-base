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
	<title>票据管理页面</title>
	<%@ include file="/webpage/system/admin/top.jsp"%>
	<script type="text/javascript" src="webpage/system/addPrint/commonFunc.js"></script>
</head>
<body>
	<div class="clearfix">
		<div id="page-content" class="page-content">
			<div id="header">
				<form action=""	method="post" name="Form" id="Form" class="form-search detail-form">
					<input type="hidden" id="discId" value="${query.discId}"></input>
					<%-- 查询区  --%>
					<div class="row-fluid">
						<label for="batchNo" class="pull-left control-label">批次号</label>
						<div class="pull-left batch">${batch.batchNo}</div>
						<label class="control-label" for="industry_investment">票据类型</label>
						<input type="text" class="input-medium" name="billType" disabled value="${fns:getDictLabel('K_BILL_TYPE',batch.billType)}"/>
						<label class="control-label" for="totalNum">票据种类</label>
						<input type="text" class="input-medium" name="totalNum" disabled value="${fns:getDictLabel('K_BILL_CLASS',batch.billClass)}" />
						<label class="control-label" for="custManage">产品名称</label>
						<input type="text" class="input-medium" name="custManage" value="${ProdName}" disabled />
					</div>
					<div class="row-fluid" id="havediscPercent">
						<label for="totalMoney">付息方式</label>
						<input type="text" class="input-medium" id="payType" name="totalMoney" disabled value="${fns:getDictLabel('K_PAY_TYPE',batch.payType)}" />
						<c:if test="${batch.payType==0}">
							<label for="totalMoney">买方付息比例</label>
							<input type="text" class="input-medium" name="totalMoney" disabled value="${batch.buyPayRate}" />
						</c:if>
						<label for="rate" class="discLabeFirst">贴现利率</label>
						<input type="text" class="input-medium" name="rate" disabled value="${batch.rate}"/>
						<label for="totalNum" class="discLabeSecond">成本利率</label>
						<input type="text" class="input-medium" name="totalNum" disabled value="${batch.cbRate}" />
						</div>
					<div class="row-fluid">
						<label for="industry_investment">行业投向</label>
						<input type="text" class="input-medium" name="professionName" disabled value="${batch.professionName}"/>
						<label for="discDt">贴现日</label>
						<input type="text" class="input-medium" name="discDt" value="${batch.discDt}"/>
						<c:if test="${batch.payAccountType!=' '}">
							<label for="discDt">买方账号类型</label>
							<input type="text" class="input-medium" name="payAccountType" id ="payAccountType" value="${fns:getDictLabel('K_ZHLX',batch.payAccountType)}"/>
						</c:if>
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
				<form  action="#" name="dataCollectForm" method="post" style="height:0px;"></form>
			</div>
			<%-- 按钮操作区 --%>
			<div id="center">
				<form  id="btnForm" name="btnForm" method="post">
				<input type="hidden" name="custAccount" value="${batch.custAccount}" />
				<div class="row-fluid">
					<div class="span6" id="btn-left">
						<a class="btn-mini" onclick="add('${query.discId}','${batch.billType}');">录入</a>
						<a class="btn-mini" onclick="doImport('${query.discId}','${batch.billType}');">批量录入</a>
						<a class="btn-mini" onclick="copyAdd('${query.discId}','${batch.billType}');">复制录入</a>
						<a class="btn-mini" onclick="trial('${batch.discId}');">计算利息</a>
						<a class="btn-mini" onclick="edit('${batch.billType}');">修改</a>
						<a class="btn-mini" onclick="del();">删除</a>
				   </div>
				   <div class="span6" id="btn-right">
						<a class="btn-mini pull-right" onclick="apply();">提交</a>
				   		<a class="btn-mini pull-right" onclick="printPZ();">打印凭证</a>					
						<a class="btn-mini pull-right" onclick="printList();">打印清单</a>
						<a class="btn-mini pull-right" onclick="goHistory();">返回</a>					
				   </div>
				</div>
			</form>
		</div>
		<div id="footer" class="footer">
			<table class="table table-striped table-bordered table-hover" id="tab" cellpadding="0" cellspacing="0" style="min-width:100%;width:1600px;max-width:1600px;">
				<thead>
					<tr>
						<th class="center"><input type="checkbox" name="allcheck" id="allcheck" onclick="selectAll('allcheck','ids')"/></th>
						<th class="center">票号</th>
						<th class="center">票据类型</th>
						<th class="center">出票日</th>
						<th class="center">票面到期日</th>
						<th class="center">计息到期日</th>
						<th class="center">计息天数</th>
						<th class="center">票面金额</th>
						<th class="center">贴现利息</th>
						<th class="center">实付金额</th>
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
									<td class="center"><input type="checkbox" name="ids" value="${disc.discmxId}"/></td>
									<td class="center">${disc.billNo}</td>
									<td class="center">${fns:getDictLabel('K_BILL_TYPE',disc.billType)}</td>
									<td class="center">${disc.issueDt}</td>
									<td class="center">${disc.dueDt}</td>
									<td class="center">${disc.galeDate}</td>
									<td class="center">${disc.interestDays}</td>
									<td class="center">${fns:formateMoney(disc.billMoney)}</td>
									<td class="center">${fns:formateMoney(disc.interest)}</td>
									<td class="center">${fns:formateMoney(disc.payMoney)}</td>
									<td class="center">${disc.acceptorBankName}</td>
									<td class="center">${disc.remitter}</td>
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
		<form action="#" name="searchForm" method="post">
			<t:token></t:token>
		</form>
		<input type="hidden" id="batchid" value="${query.discId}" disabled="disabled"></input>
		<%-- /列表操作区 --%>
		<div>
			<div id="page" class="pagination">
				<form action="<%=basePath%>discApplyController.do?method=billManage" method="post" name="pageForm">
					<%@ include file="/webpage/system/admin/pageFormContent.jsp"%> 
					<input type="hidden" name="discId" value="${query.discId}"/>
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
		dynamicHiddenElement('btnForm','acctNo',$("#ruzhang").val());
		dynamicHiddenElement('btnForm','custNo',$("#custNo").val());
		dynamicHiddenElement('btnForm','custName',$("#custName").val());
		modal("#layer_loading,#image")
		btnForm.action = "<%=basePath%>discApplyController.do?method=searchBatch";
		btnForm.submit();
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
	function add(discId,billType){
	   	 var diag = new top.Dialog();
		 diag.Drag = true;
		 diag.Title ="录入";
		 diag.URL = "<%=basePath%>discApplyController.do?method=toAddBill&action=discApplyController.do?method=saveBill&discId="+discId+"&billType="+billType;
		 diag.Width = 800;
		 diag.Height =480;
		 
		 diag.CancelEvent = function(){ //关闭事件
			pageForm.submit();
			diag.close();
		 };
		 diag.show();
	}
		function edit(billType){
		 var checkNum = getCheckNum("ids");
	   	 if (checkNum ==0){
	   		bootbox.alert("请先选择要修改的记录");
	   		return;
	   	 }
	   	  if (checkNum !=1){
	   		bootbox.alert("一次只能对一条记录进行修改");
	   		return;
	   	 }
		var id = getCheckStr("ids");
	   	 var diag = new top.Dialog();
		 diag.Drag = true;
		 diag.Title ="编辑";
		 diag.URL = "<%=basePath%>discApplyController.do?method=toEditBill&action=discApplyController.do?method=saveBill&discmxId="+id+"&billType="+billType;
		 diag.Width = 800;
		 diag.Height = 480;
		 diag.CancelEvent = function(){ //关闭事件
		 	pageForm.submit();
			diag.close();
		 };
		 diag.show();
	}
	function apply(){
		var checkNum = getCheckNum("ids");
	   	 if (checkNum == 0){
	   		bootbox.alert("请先选择要提交的记录");
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
					dynamicHiddenElement('searchForm','ids',ids);
					dynamicHiddenElement('searchForm','acctNo',$("#ruzhang").val());
					dynamicHiddenElement('searchForm','custName',$("#custName").val());
					dynamicHiddenElement('searchForm','custNo',$("#custNo").val());
					modal("#layer_loading,#image");
					searchForm.action = "<%=basePath%>discApplyController.do?method=apply";
					searchForm.submit();
				}else{
					bootbox.alert("请先进行利息试算");
				}
			},
			error:function(rs){
				bootbox.alert("系统异常");
			}
		}); 	
		
	}
	function del(){
		 var checkNum = getCheckNum("ids");
	   	 if (checkNum == 0){
	   		bootbox.alert("请先选择要删除的记录");
	   		return;
	   	 }
		var ids = getCheckStr("ids");
		bootbox.confirm("确定要删除选中的记录吗？", function(result) {
			if(result) {
				dynamicHiddenElement('dataCollectForm','custNo',"${query.custNo}");
				dynamicHiddenElement('dataCollectForm','acctNo',"${query.acctNo}");
				dynamicHiddenElement('dataCollectForm','ids',ids);
				dynamicHiddenElement('dataCollectForm','discId',"${query.discId}");
				dataCollectForm.action = "<%=basePath%>discApplyController.do?method=delBill";
				dataCollectForm.submit();
			}
		});
	}
	//利息试算
	function trial(discId){var checkNum = getCheckNum("ids");
	   	 if (checkNum == 0){
	   		bootbox.alert("请先选择要进行利息试算的记录");
	   		return;
	   	 }
		var ids = getCheckStr("ids"); 
	   	 $("#_ButtonClose_0").css({'width':'27px','top':'4px','background-position': '-1px 0px'});
	   	 var diag = new top.Dialog();
		 diag.Drag = true;
		 diag.Title ="利息试算";
		 diag.URL = "<%=basePath%>discApplyController.do?method=toInterestTrial&ids="+ids+"&discId="+discId;
		 diag.Width = 480;
		 diag.Height = 210;
		 diag.CancelEvent = function(){ //关闭事件
			pageForm.submit();
			diag.close();
		 };
		 diag.show(); 
	}
	//复制录入
	function copyAdd(discId,billType){
		var checkNum = getCheckNum("ids");
		 if (checkNum ==0){
	   		bootbox.alert("请先选择要复制录入的记录");
	   		return;
	   	 }
	   	  if (checkNum !=1){
	   		bootbox.alert("一次只能对一条记录进行复制录入");
	   		return;
	   	 }
	   	var id = getCheckStr("ids");
		var diag = new top.Dialog();
		 diag.Drag = true;
		 diag.Title ="复制录入";                 															
		 diag.URL = "<%=basePath%>discApplyController.do?method=toCopyBill&discId="+discId+"&billType="+billType+"&discmxId="+id;
		 diag.Width = 800;
		 diag.Height =470;
		 
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
		diag.URL = "<%=basePath%>webpage/busi/common/import_result.jsp?batchId="+discId+"&billType="+billType+"&serviceName=com.herongtech.console.service.busiservice.disc.DiscImportDataService";
		diag.Width = 1500;
		diag.Height =500;
		diag.CancelEvent = function(){ //关闭事件
			diag.close();
			pageForm.submit();
		};
		diag.show();
	}
	 //打印贴现申请清单
    //operStatusString 票据状态
	function printList(){
		var payType=$("#payType").val();//付息方式 1买 2卖 3协议
		var batch_id=$("#batchid").val();
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
				 	doPrint("addPrintController.do?method=doPrint&ids="+ids+"&moduleId=1030601&type=DiscAuditPrint&baid="+batch_id+"&handleType=申 请"); 	 
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
		var batch_id=$("#batchid").val();
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