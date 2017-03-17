<%-- 
 * 文件名称: rebuy_bill_apply_outer.jsp
 * 系统名称: 票据管理平台
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: @version2.0.0.1
 * 开发人员: sherry
 * 开发时间: 2016-9-10
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
				<form action=""	method="post" name="batchForm" id="batchForm" class="form-search detail-form">
					<%-- 查询区  --%>
					<div class="row-fluid">
						<label for="batchNo" class="pull-left control-label">批次号</label>
						<div class="pull-left batch">
							${batch.batchNo}
						</div>
						<label for="custManage">票据类型</label>
						<input type="text" class="input-medium" name="billType" readonly value="${fns:getDictLabel('K_BILL_TYPE',batch.billType)}"/>
						<label for="tradeAcct">票据种类</label>
						<input type="text" class="input-medium" name="billClass" value="${fns:getDictLabel('K_BILL_CLASS',batch.billClass)}" readonly />
						<label for="rebuyDt">产品类型</label>
						<input type="text" class="input-medium" name="prodNo" id="prodNo" readonly />
					</div>
					<div class="row-fluid">
						<label for="custBankNo">交易对手行号</label>
						<input type="text" class="input-medium" name="custBankNo" value="${batch.custBankNo}" readonly />
						<label for="custBankName">交易对手名称</label>
						<input type="text" class="input-medium" name="custBankName" value="${batch.custBankName}" readonly />
						<label for="rate">利率</label>
						<input type="text" class="input-medium" name="rate" readonly value="${batch.rate}"/>
						<label for="cbRate">成本利率</label>
						<input type="text" class="input-medium" name="cbRate" readonly value="${batch.cbRate}" />
					</div>
					<div class="row-fluid">
						<label for="">入账账号类型</label>
						<input type="text" class="input-medium" name="" value="结算账号" readonly />
						<label for="tradeAcct">入账账号</label>
						<input type="text" class="input-medium" name="tradeAcct" value="${batch.tradeAcct}" readonly />
						<label for="tradeAcctName">账户名称</label>
						<input type="text" class="input-medium" name="tradeAcctName" readonly value="${batch.tradeAcctName}" />
						<label for="rebuyDt">转入日</label>
						<input type="text" class="input-medium" name="rebuyDt" value="${batch.rebuyDt}" readonly />
					</div>
					<div class="row-fluid">
						<label for="custManage">客户经理编号</label>
						<input type="text" class="input-medium" name="custManage" readonly value="${batch.custManage}"/>
						<label for="custManagerName">客户经理名称</label>
						<input type="text" class="input-medium" name="custManagerName" readonly value="${batch.custManagerName}"/>
						<label for="deptName">部门名称</label>
						<input type="text" class="input-medium" name="deptName" value="${batch.deptName}" readonly />
					</div>
					<div class="row-fluid">
						<label for="totalNum">总笔数</label>
						<input type="text" class="input-medium" name="totalNum" readonly value="${batch.totalNum}"/>
						<label for="totalMoney">总金额</label>
						<input type="text" class="input-medium" name="totalMoney" value="${fns:formateMoney(batch.totalMoney)}" readonly />
						<label for="totalInterest">总利息</label>
						<input type="text" class="input-medium" name="totalInterest" readonly value="${fns:formateMoney(batch.totalInterest)}" />
						<label for="actualAmount">总实付</label>
						<input type="text" class="input-medium" name="actualAmount" readonly value="${fns:formateMoney(batch.actualAmount)}" />
					</div>
				</form>
			</div>
			<%-- 按钮操作区 --%>
			<div id="center">
				<form  id="btnForm">
					<div class="row-fluid">
						<div class="span6" id="btn-left">
							<a class="btn-mini" onclick="add('${batch.rebuyId}','${batch.billType}');">录入</a>
							<a class="btn-mini" onclick="doImport('${batch.rebuyId}','${batch.billType}');">批量录入</a>
							<a class="btn-mini" onclick="copyAdd('${batch.rebuyId}','${batch.billType}');">复制录入</a>
							<a class="btn-mini" onclick="edit();">修改</a>
							<a class="btn-mini" onclick="del();">删除</a>
							<a class="btn-mini" onclick="calculateInterest();">计算利息</a>
					  	 </div>
					   	 <div class="span6" id="btn-right">
							<a class="btn-mini pull-right" onclick="apply('${batch.rebuyId}');">提交</a>
							<a class="btn-mini pull-right" onclick="printPZ();">打印凭证</a>					
							<a class="btn-mini pull-right" onclick="printList();">打印清单</a>
					   		<a class="btn-mini pull-right" onclick="goHistory('${batch.custBankNo}');">返回</a>		
					   	</div>
				  	</div>
				</form>
			</div>
			<div id="footer" class="footer">
				<table class="table table-striped table-bordered table-hover" id="tab" cellpadding="0" cellspacing="0">
					<thead>
						<tr>
							<th class="center" onclick="selectAll('allcheck', 'ids')">
								<input type="checkbox" id="allcheck" /><span class="lbl"></span>
							</th>
							<th class="center">票号</th>
							<th class="center">票据类型</th>
							<th class="center">出票日</th>
							<th class="center">票面到期日</th>
							<th class="center">计息到期日</th>
							<th class="center">票面金额</th>
							<th class="center">利息</th>
							<th class="center">实付金额</th>
							<th class="center">出票人</th>
							<th class="center">出票人开户行行号</th>
							<th class="center">收款人</th>
							<th class="center">详情</th>
						</tr>
					</thead>
					<tbody>
					<c:choose>
						<c:when test="${not empty resultList}">
							<c:forEach items="${resultList}" var="rebuy" varStatus="vs">
								<tr>
									<td class="center"><input type="checkbox" name="ids" value="${rebuy.rebuymxId}"/></td>
									<td class="center">${rebuy.billNo}</td>
									<td class="center">${fns:getDictLabel('K_BILL_TYPE',rebuy.billType)}</td>
									<td class="center">${rebuy.issueDt}</td>
									<td class="center">${rebuy.dueDt}</td>
									<td class="center">${rebuy.galeDate}</td>
									<td class="center">${fns:formateMoney(rebuy.billMoney)}</td>
									<td class="center">${fns:formateMoney(rebuy.interest)}</td>
									<td class="center">${fns:formateMoney(rebuy.payMoney)}</td>
									<td class="center">${rebuy.remitter}</td>
									<td class="center">${rebuy.remitterBankNo}</td>
									<td class="center">${rebuy.payee}</td>
									<td class="center"><a href="javascript:goDetail('${rebuy.rebuymxId}')">查看</a></td>
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
			<form  action="#" name="dataCollectForm" method="post">
				<t:token></t:token>
			</form>
			<input type="hidden" id="batchid" value="${searchBean.rebuyId}" disabled="disabled"></input>
			<%-- /列表操作区 --%>
			<div>
				<div id="page" class="pagination">
					<form action="<%=basePath%>rebuyApplyController.do?method=billManage" method="post" name="pageForm">
						<%@ include file="/webpage/system/admin/pageFormContent.jsp"%> 
						<input type="hidden" name="rebuyId" value="${batch.rebuyId}"/>
						<input type="hidden" name="isInner" value="${batch.isInner}"/>
					</form>
				</div>
			</div>
		</div>
	</div>
<%@ include file="/webpage/system/admin/modalDialog.jsp"%>	
<%@ include file="/webpage/system/admin/footer.jsp"%>
<script>
	$(function(){
		var prodNo="${batch.prodNo}";
		if(prodNo.length==9){
			$.ajax({
				url:"<%=basePath%>rebuyApplyController.do?method=getProdName",
				type:"POST",
				data:{"prodNo":prodNo},
				dataType:"json",
				success:function(data){
					if(data.success){
						$("#prodNo").val(data.obj);
					}
				}
			});
		}
	});	
	//返回上一页
	function goHistory(custBankNo){
		modal("#layer_loading,#image");
		dynamicHiddenElement('dataCollectForm','isInner','${batch.isInner}');
		dynamicHiddenElement('dataCollectForm','custBankNo','${batch.custBankNo}');
		dynamicHiddenElement('dataCollectForm','custBankName','${batch.custBankName}');
		dataCollectForm.action = "<%=basePath%>rebuyApplyController.do?method=searchBatch";
		dataCollectForm.submit();
	}
	//详情页面
	function goDetail(rebuymxId){
		var rebuyId="${batch.rebuyId}";
	   	var diag = new top.Dialog();
		diag.Drag = true;
		diag.Title ="详情";
		diag.URL = "<%=basePath%>rebuyApplyController.do?method=goDetail&rebuymxId="+rebuymxId+"&rebuyId="+rebuyId;
		diag.Width = 920;
		diag.Height = 450;
		diag.CancelEvent = function(){ //关闭事件
			diag.close();
		};
		diag.show();
	}
	function add(rebuyId,billType){
	   	 var diag = new top.Dialog();
		 diag.Drag = true;
		 diag.Title ="录入";
		 diag.URL = "<%=basePath%>rebuyApplyController.do?method=toAddBill&action=rebuyApplyController.do?method=saveBill&rebuyId="+rebuyId+"&billType="+billType;
		 diag.Width = 800;
		 diag.Height =500;
		 diag.CancelEvent = function(){ //关闭事件
			pageForm.submit();
			diag.close();
		 };
		 diag.show();
	}
	function copyAdd(rebuyId,billType){
		var checkNum = getCheckNum("ids");
		if (checkNum ==0){
			bootbox.alert("请先选择要复制的记录");
			return;
		}
		if (checkNum != 1){
			bootbox.alert("一次只能对一条记录进行复制");
			return;
		}
	   	var id = getCheckStr("ids");
	   	var diag = new top.Dialog();
		 diag.Drag = true;
		 diag.Title ="录入";
		 diag.URL = "<%=basePath%>rebuyApplyController.do?method=toCopyBill&action=rebuyApplyController.do?method=saveBill&rebuyId="+rebuyId+"&billType="+billType+"&rebuymxId="+id;
		 diag.Width = 800;
		 diag.Height =470;
		 diag.CancelEvent = function(){ //关闭事件
			pageForm.submit();
			diag.close();
		 };
		 diag.show();
	}
	function edit(){
		 var checkNum = getCheckNum("ids");
		 if (checkNum ==0){
	   		bootbox.alert("请先选择要修改的记录");
	   		return;
		 }
		 if (checkNum != 1){
	   		bootbox.alert("一次只能对一条记录进行修改");
	   		return;
		 }
		 var id = getCheckStr("ids");
	   	 var diag = new top.Dialog();
		 diag.Drag = true;
		 diag.Title ="编辑";
		 diag.URL = "<%=basePath%>rebuyApplyController.do?method=toEditBill&action=rebuyApplyController.do?method=saveBill&rebuymxId="+id;
		 diag.Width = 800;
		 diag.Height =470;
		 diag.CancelEvent = function(){ //关闭事件
			 pageForm.submit();
			 diag.close();
		 };
		 diag.show();
	}
	function del(){
		var checkNum = getCheckNum("ids");
		if (checkNum == 0){
	   		bootbox.alert("请先选择要删除的记录");
	   		return;
	   	}
	   	var ids = getCheckStr("ids");
	   	bootbox.confirm("确定要删除选中的记录吗?", function(result) {
	   		if(result){
	   			$.ajax({
					type: "POST",
					url: "<%=basePath%>rebuyApplyController.do?method=delBill",
			    	data: {'ids': ids},
					dataType:'json',
					cache: false,
					success: function(data){	
						if(data.success){  //处理成功
							pageForm.submit();
						}else{
							bootbox.alert(data.msg);
						}
					}
				});
	   		 }
	   	});
	}
	function apply(rebuyId){
		var checkNum = getCheckNum("ids");
	   	if (checkNum == 0){
	   		bootbox.alert("请先选择要提交的记录");
	   		return;
	   	}
		var ids = getCheckStr("ids");
		//校验是否计算过利息
		$.ajax({
			url:"rebuyApplyController.do?method=isRate",
			type:"POST",
			data:{"ids":ids,"rebuyId":rebuyId},
			dataType:"json",
			success:function(rs){
				if(rs.success){
					checkRisk(ids,rebuyId);
				}else{
					bootbox.alert("请先进行利息试算");
				}
			},
			error:function(rs){
				bootbox.alert(rs.msg);
			}
		}); 	
	}
	function checkRisk(ids,rebuyId){
		$.ajax({
			url:"<%=basePath%>rebuyApplyController.do?method=checkRiskBills",
			type:"POST",
			data:{"ids":ids},
			dataType:"json",
			success:function(data){
				if(data.success){
					submit(ids,rebuyId);
				}else{
					var diag = new top.Dialog();
					diag.Drag = true;
					diag.Title ="风险票据";
					diag.URL = "<%=basePath%>rebuyApplyController.do?method=toRiskBillsPage&ids="+ids;
					diag.Width = 990;
					diag.Height = 500;
					diag.CancelEvent = function(){ //关闭事件
						
					 		submit(ids,rebuyId);
					 	
					 	diag.close();
					};
					diag.show(); 
				}
			 }
		});
	}
	function submit(ids,rebuyId){
		dynamicHiddenElement('dataCollectForm','ids',ids);
		dynamicHiddenElement('dataCollectForm','rebuyId',rebuyId);
		dynamicHiddenElement('dataCollectForm','isInner','${batch.isInner}');
		dynamicHiddenElement('dataCollectForm','custBankNo','${batch.custBankNo}');
		dynamicHiddenElement('dataCollectForm','custBankName','${batch.custBankName}');
		dataCollectForm.action = "<%=basePath%>rebuyApplyController.do?method=applySubmit";
		modal("#layer_loading,#image");
		dataCollectForm.submit();
	}
	//利息计算
	function calculateInterest(){
	     var checkNum = getCheckNum("ids");
	   	 if (checkNum == 0){
	   		bootbox.alert("请先选择要计算利息的记录");
	   		return;
	   	 }
		 var ids = getCheckStr("ids");
	   	 var diag = new top.Dialog();
		 diag.Drag = true;
		 diag.Title ="利息试算";
		 diag.URL = "<%=basePath%>rebuyApplyController.do?method=toCalculateInterestPage&ids="+ids;
		 diag.Width = 500;
		 diag.Height = 220;
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
		diag.URL = "<%=basePath%>webpage/busi/common/import_result.jsp?batchId="+discId+"&billType="+billType+"&serviceName=com.herongtech.console.service.busiservice.rebuy.RebuyImportDataService";
		diag.Width = 1500;
		diag.Height =500;
		diag.CancelEvent = function(){ //关闭事件
			pageForm.submit();
			diag.close();
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
			url:"rebuyApplyController.do?method=isRate",
			type:"POST",
			data:{"ids":ids,"rebuyId":batch_id},
			dataType:"json",
			success:function(rs){
				if(rs.success){//是否计算过利息
				 	doPrint("addPrintController.do?method=doPrint&ids="+ids+"&moduleId=1050101&type=RebuyListPrint&baid="+batch_id+"&handleType=申 请"); 	 
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
		var payType=$("#payType").val();//付息方式 1买 2卖 3协议
		var batch_id=$("#batchid").val();
		var checkNum = getCheckNum("ids");
	   	 if (checkNum == 0){
	   		bootbox.alert("请先选择要打印的记录");
	   		return;
	   	 }   	 
		var ids = getCheckStr("ids");
		$.ajax({
			url:"rebuyApplyController.do?method=isRate",
			type:"POST",
			data:{"ids":ids,"rebuyId":batch_id},
			dataType:"json",
			success:function(rs){
				if(rs.success){//是否计算过利息
				 	doPrint("addPrintController.do?method=doPrint&ids="+ids+"&moduleId=1050301&type=RebuyPZPrint&baid="+batch_id); 	 
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