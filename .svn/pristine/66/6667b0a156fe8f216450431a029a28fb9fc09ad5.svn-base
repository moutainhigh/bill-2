<%-- 
 * 文件名称: disc_elec_apply_bill_list.jsp
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
				<form action="<%=basePath%>discApplyController.do?method=afterPage"	method="post" name="userForm" id="userForm" class="form-search ele-detail-form">
					<%-- 查询区  --%>
					<input type="hidden" name="acctNo" id="acctNo" value="${acctNo}"/>
					<input type="hidden" name="discId" id="discId" value="${batch.discId}"/>
					<input type="hidden" name="batchNos" id="batchNos" value="${batch.batchNo}"/>
					<div class="row-fluid">
						<label for="batchNo" id="singleMar" class="pull-left control-label">批次号</label>
						<div class="pull-left batch">${batch.batchNo}</div>
						<label for="deptName"><span class="star">*</span>行业投向</label>
						<t:select other="" className="select-medium" onchange="selectValue();" id="professionName" name="professionNames" list="professionList" listKey="professionName" listValue="professionName" defaultVal="${batch.professionName}"></t:select>
						<input type="hidden" name="professionName" id="professionNames" value="${batch.professionName}"/>
						<label for="custManagerName"><span class="star">*</span>贴现日</label>
						<input class="input-medium" name="discDt" id="discDt" value ="${batch.discDt}" readonly = "true" valid="required" type="text">
					</div>
					<div class="row-fluid">
						<label for="tradeAcct"><span class="star">*</span>票据类型</label>
						<t:dictSelect name="billType" className="select-medium" other="" dictGroup="K_BILL_TYPE" defaultVal="${batch.billType}" haveHead="true" valid="required" disabled="disabled">
						</t:dictSelect> 
				        <input type="hidden" id="billType" name="billType" value="${batch.billType}"/> 
						<label for="cbRate"><span class="star">*</span>票据品种</label>
						<t:dictSelect  className="select-medium"  name="billClass" other="" dictGroup="K_BILL_CLASS" defaultVal="${batch.billClass}" haveHead="true" valid="required" disabled="disabled">
				        </t:dictSelect> 
					    <input type="hidden" name="billClass" value="${batch.billClass}"/> 
						<label for="tradeAcctName"><span class="star">*</span>产品名称</label>
						<t:select id="prodNo" className="select-medium" other="" name="prodNo" list="prodList" listKey="prodName" listValue="prodNo" defaultVal="${batch.prodNo}"></t:select>
					</div>
					<div class="row-fluid">
						<label for=""><span class="star">*</span>付息方式</label>
						<input type="text" name="payTypes" id="payType" class="input-medium" readonly="true" valid="required"/> 
				        <input type="hidden" name="payType" id="payTypes" value="${batch.payType}"/>
						<label for="totalNum"><span class="star">*</span>利率类型</label>
						<input type="text" class="input-medium" name="rateType" readonly="true" id="rateType" value="年利率" placeholder=""/>
						<label for="rebuyDt"><span class="star">*</span>利率</label>
						<input type="text" class="input-medium" name="rate" id="rate" valid="required" value="${fns:formateRate(batch.rate)}" placeholder="" readonly="true"/>
					</div>
					<div class="row-fluid">
						<label for="custManage">成本利率</label>
						<input type="text" class="input-medium" name="cbRate" id="cbRate" title="请输入0-100之间千分位的小数或整数" onblur = "checkCb();" value="${fns:formateRate(batch.cbRate)}" placeholder="" />
					</div>
					<div class="row-fluid">
						<label for="custBankNo"><span class="star">*</span>客户号</label>
						<input type="text" class="input-medium" name="custNo" readonly="true" id="custNo" valid="required" value="${batch.custNo}" placeholder=""/>
						<label for="custBankName"><span class="star">*</span>客户名称</label>
						<input type="text" class="input-medium" name="custName" readonly="true" id="custName" value="${batch.custName}" placeholder=""/>
						<label for="rate"><span class="star">*</span>客户账号</label>
						<input type="text" class="input-medium" name="custAccount" readonly="true" id="custAccount" value="${batch.custAccount}" placeholder=""/>
					</div>
					<div class="row-fluid">
						<label for="custManage"><span class="star">*</span>客户经理编号</label>
						<input type="text" class="input-medium" name="custManage" id="custManage" valid="required" value="${batch.custManage}" placeholder="" />
						<label for="tradeAcct"><span class="star">*</span>客户经理名称</label>
						<input type="text" class="input-medium" readonly="true" name="custManagerName" valid="required" id="custManagerName"  value="${batch.custManagerName}" placeholder=""/>
						<label for="rebuyDt"><span class="star">*</span>客户经理部门</label>
						<input type="text" class="input-medium" name="deptName" readonly="true" id="deptName" valid="required" value="${batch.deptName}" placeholder=""/>
					</div>
					<div class="row-fluid">
						<label for="totalNum" id="singleMarLeft">票据张数</label>
						<input type="text" class="input-medium" name="totalNum" readonly="true" value="${batch.totalNum}" />
						<label for="totalMoney">总金额</label>
						<input type="text"  class="input-medium" name="totalMoney" readonly="true" value="${fns:formateMoney(batch.totalMoney)}" />
						<input type="hidden" name="totalMoneys" id="totalMoneys" value="${batch.totalMoney}"/>
						<label for="profOwner">总利息</label>
						<input type="text" class="input-medium" name="totalInterest" readonly="true" value="${fns:formateMoney(batch.totalInterest)}" />
						<input type="hidden" name="totalInterests" id="totalInterests" value="${batch.totalInterest}"/>
					</div>
					<div class="row-fluid buyer">
						<label for="totalInterest"><span class="star">*</span>付息账号类型</label>
						<t:dictSelect  name="payAccountType" other="" className="select-medium" valid="required" dictGroup="K_ZHLX" defaultVal="${batch.payAccountType}" haveHead="true" disabled="disabled" >
					    </t:dictSelect> 
					    <input type="hidden" id="payAccountType" name="payAccountType" value="结算账号"/> 
						<label for="totalMoney"><span class="star">*</span>买方付息人账号</label>
						<input type="text" class="input-medium" name="payAccount" valid="required" onblur="buyFill();" id="payAccount" value="${batch.payAccount}" placeholder=""/>
						<label for="actualAmount"><span class="star">*</span>买方付息人名称</label>
						<input type="text" class="input-medium" name="payCustName" readonly="true" valid="required" id="payCustName" value="${batch.payCustName}" placeholder=""/>
					</div>
					<div class="row-fluid">
						<label for="profOwner" id="singleLeft">试算实付金额</label>
						<input type="text" class="input-medium" name="actualAmount" readonly="true" value="${fns:formateMoney(batch.actualAmount)}" />
						<input type="hidden" name="actualAmounts" id="actualAmounts" value="${batch.actualAmount}"/>
						<label for="actualAmount" class="saler"><span class="star">*</span>买方付息比例</label>
						<input type="text" class="input-medium saler" name="buyPayRate" valid="required number" title="请输入0-100之间千分位的小数或整数" onblur = "check();" id="buyPayRate" value="${fns:formateRate(batch.buyPayRate)}" placeholder=""/>
					</div>
				</form>
			</div>
			<%-- 按钮操作区 --%>
			<div id="center">
				<form action="<%=basePath%>discApplyController.do?method=elecBillList"  method="post" id="elecReviewForm" name="elecReviewForm" style="margin:0px;" class="row-fluid">
					<div class="row-fluid">
						<div class="span6" id="btn-left">
							<a class="btn-mini" onclick="add();">新增</a>
							<a class="btn-mini" onclick="del();">删除</a>
							<a class="btn-mini" onclick="trial();">利息复核</a>
							<a class="btn-mini" onclick="printDocument();">打印凭证</a>					
						    <a class="btn-mini" onclick="printList();">打印清单</a>
					   </div>
					   <div class="span6" id="btn-right">
							<a class="btn-mini pull-right" onclick="doSubmit();">提交</a>
							<a class="btn-mini pull-right" onclick="goHistory();">返回</a>
					   </div>
				  	</div>
			   </form>
			</div>
			<div id="footer" class="footer">
				<table class="table table-striped table-bordered table-hover" id="tab" cellpadding="0" cellspacing="0" style="min-width:100%;max-width:2600px;width:2600px;">
					<thead>
						<tr>
							<th class="center" onclick="selectAll('zcheckbox', 'ids')">
								<input type="checkbox" id="zcheckbox" /><span class="lbl"></span>
							</th>
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
	<%-- /列表操作区 --%>
	<div id="select-Info">
		<input type="hidden" name="billType" id="billType" value="${batch.billType}"/> 
		<form  action="#" name="dataCollectForm" method="post"></form>
		<div id="selectInfo"><center>暂时没有相关数据</center></div>
	</div>
	<div id="page" class="pagination">
			<form action="<%=basePath%>discApplyController.do?method=afterPage"  name="pageForm" method="post" >
				<%@ include file="/webpage/system/admin/pageFormContent.jsp"%>
				<input type="hidden" name="acctNo" id="acctNo" value="${acctNo}"/>
				<input type="hidden" name="discId" id="discId" value="${batch.discId}"/>
			</form>
		</div>
	</div>
<%@ include file="/webpage/system/admin/modalDialog.jsp"%>
<%@ include file="/webpage/system/admin/footer.jsp"%>
<script>
	$(document).ready(function(){
		var professionName='${discApplyInfo.professionName}';
	    $("#professionName").val(professionName);
	    $("#userForm").validate({
	      onfocusout:function(element){
	      		$(element).valid();
	      }
	   });
	});
    var buyPayRate = '${batch.payType}';
    if(buyPayRate=='3'){
        $("#buyPayRate").show();
    }else{
        $("#buyPayRate").hide();
    }
    var prodNo='${batch.prodNo}';
	    if(prodNo=='001001003'){
    			$("#payType").val('卖方付息');
    			$("#payTypes").val('2');
    			$(".buyer").hide();
    			$(".saler").hide();
    			$("#buyPayRate").val('0');
    			$("#footer").height($("#footer").height()+40);
   	 		}
    		else if(prodNo=='001001002'){
    			$("#payType").val('买方付息');
    			$("#payTypes").val('1');
    			$(".buyer").show();
    			$(".saler").hide();
    			$("#buyPayRate").val('0');
   	 		}else if(prodNo=='001001001'){
   	 			$("#payType").val('协议付息');
   	 		    $("#payTypes").val('3');
    			$(".buyer").show();
    			$(".saler").show();
    		}
    		else if(prodNo=='001001006'){
   	 			$("#payType").val('卖方付息');
   	 		    $("#payTypes").val('2');
    			$(".buyer").hide();
    			$(".saler").hide();
    			$("#buyPayRate").val('0');
    			$("#footer").height($("#footer").height()+40);
    		}
    		else if(prodNo=='001001005'){
    			$("#payType").val('买方付息');
    			$("#payTypes").val('1');
    			$(".buyer").show();
    			$(".saler").hide();
    			$("#buyPayRate").val('0');
   	 		}else if(prodNo=='001001004'){
   	 			$("#payType").val('协议付息');
   	 		    $("#payTypes").val('3');
    			$(".buyer").show();
    			$(".saler").show();
    		}
		//$("#cbRate").val('0')
		//var custAccount = localStorage.getItem("key");
		$("#custAccount").val("${acctNo}");
		$(document).ready(function(){
    	$("#prodNo").change(function(){
    		if($("#prodNo").find('option:selected').attr('value')=='001001003'){
    			$("#payType").val('卖方付息');
    			$("#payTypes").val('2');
    			$(".buyer").hide();
    			$(".saler").hide();
    			$("#buyPayRate").val('0');
    			$("#footer").height($("#footer").height()+40);
   	 		}
    		else if($("#prodNo").find('option:selected').attr('value')=='001001002'){
    			$("#payType").val('买方付息');
    			$("#payTypes").val('1');
    			$(".buyer").show();
    			$(".saler").hide();
    			$("#buyPayRate").val('0');
   	 		}else if($("#prodNo").find('option:selected').attr('value')=='001001001'){
   	 			$("#payType").val('协议付息');
   	 		    $("#payTypes").val('3');
    			$(".buyer").show();
    			$(".saler").show();
    		}
    		else if($("#prodNo").find('option:selected').attr('value')=='001001006'){
   	 			$("#payType").val('卖方付息');
   	 		    $("#payTypes").val('2');
    			$(".buyer").hide();
    			$(".saler").hide();
    			$("#buyPayRate").val('0');
    			$("#footer").height($("#footer").height()+40);
    		}
    		else if($("#prodNo").find('option:selected').attr('value')=='001001005'){
    			$("#payType").val('买方付息');
    			$("#payTypes").val('1');
    			$(".buyer").show();
    			$(".saler").hide();
    			$("#buyPayRate").val('0');
   	 		}else if($("#prodNo").find('option:selected').attr('value')=='001001004'){
   	 			$("#payType").val('协议付息');
   	 		    $("#payTypes").val('3');
    			$(".buyer").show();
    			$(".saler").show();
    		}
   		});
    });
    //根据客户账号查询客户信息并填充
    $("#custManage").blur(function(){
    	fill();
    })
	function fill(){ 
		var custManagerNo = $('#custManage').val();
		$.ajax({
			type: "POST",
			url: "<%=basePath%>custManagerController.do?method=custMagInfo",
	    	data: {'custManagerNo': custManagerNo},
			dataType:'json',
			beforeSend:function(){
				if(custManagerNo==null||custManagerNo==""){
					showTips("custManage","请输入客户经理编号");
					$("#deptName").val("");
					$("#custManagerName").val("");
					//$("#custName").val("");
			   		return false;
				}
			},
			cache: false,
			success: function(data){	
				if(data.success){  //处理成功
					$("#deptName").val(data.attributes.deptName);
					$("#custManagerName").val(data.attributes.custManagerName);
					//fillBatch(1,5);
				}else{
					$("#deptName").val("");
					$("#custManagerName").val("");
					bootbox.alert(data.msg);
				}
			}
		});
	}
	//自动根据客户账号查询客户信息并填充
    rfill();
	function rfill(){ 
			var custAcctNo = $('#custAccount').val();
			$.ajax({
				type: "POST",
				url: "<%=basePath%>custInfoController.do?method=custInfo",
		    	data: {'custAcctNo': custAcctNo},
				dataType:'json',
				cache: false,
				success: function(data){	
					if(data.success){  //处理成功
						$("#custNo").val(data.attributes.custNo);
						$("#custName").val(data.attributes.custName);
						//fillBatch(1,5);
					}else{
						$("#custNo").val("");
						$("#custName").val("");
						bootbox.alert(data.msg);
					}
				}
			});
		}
	function check(){
	    var r = /^(\d{1,2}(\.\d{1,2})?|100)$/;
	    var s = $("#buyPayRate").val();
	    if(r.test(s)){
	        return true;
	    }else{
	    	showTips("buyPayRate","请输入0-100之间千分位的小数或整数");
	        return false;
	    }
	}
	function checkCb(){
	    var r = /^(\d{1,2}(\.\d{1,2})?|100)$/;
	    var s = $("#cbRate").val();
	    if(s.length>0){
	        if(r.test(s)){
	            return true;
	        }else{
	        	showTips("cbRate","请输入0-100之间千分位的小数或整数");
	            return false;
	        }
	    }else{
	        return true;
	    }
	}
	function buyFill(){ //根据客户账号查询客户信息并填充
		var payAccount = $('#payAccount').val();
		$.ajax({
			type: "POST",
			url: "<%=basePath%>custInfoController.do?method=custInfo",
	    	data: {'custAcctNo': payAccount},
			dataType:'json',
			beforeSend:function(){
				if(payAccount==null||payAccount==""){
					showTips("payAccount","请输入买方付息人帐号");
					$("#payCustName").val("");
			   		return false;
				}
			},
			cache: false,
			success: function(data){	
				if(data.success){  //处理成功
					$("#payCustName").val(data.attributes.custName);
				}else{
					$("#payCustName").val("");
					bootbox.alert(data.msg);
				}
			}
		});
	}
	function goHistory(){
	    modal("#layer_loading,#image");
		dataCollectForm.action = "<%=basePath%>discApplyController.do?method=elecBatchList";
		dataCollectForm.submit();
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
	function doSubmit(){
		var checkNum = getCheckNum("ids");
	   	if (checkNum <= 0){
	   		bootbox.alert("请先选择要提交的记录");
	   		return;
	   	}
	   	var bill_ids = getCheckStr("ids");
	   	var billType = "${batch.billType}";
	   	var rate = "${batch.rate}";
	   	var discId = "${batch.discId}";
	   	var discDt = "${batch.discDt}";
	   	var cbRate =$("#cbRate").val();
	 	if(cbRate == ""){
	 		$("#cbRate").val(0);
	 	}
	 	if($("#userForm").valid()){
	 		if(check()){
				if(checkCb()){
					bootbox.confirm("确定要提交选中的记录吗?", function(result) {
						if(result){
							$.ajax({
								type: "POST",
								url: '<%=basePath%>discApplyController.do?method=elecBillSubmit',
						    	data: {'discmxIds': bill_ids,'billType': billType,'rate': rate,'discDt': discDt,'discId': discId},
								dataType:'json',
								//beforeSend: validateData,
								cache: false,
								success: function(data){	
									if (data.success){  //处理成功
									   /* if(data.attributes.count == "0"){ */
									    modal("#layer_loading,#image");
									    dataCollectForm.action = "<%=basePath%>discApplyController.do?method=elecBatchList";
					                    dataCollectForm.submit();
									} else {
										bootbox.alert(data.msg);
									}
								}
						   });
					 	}
				   	});
				}
			}
	   	}
	}
	function del(){
		var checkNum = getCheckNum("ids");
	   	if (checkNum <= 0){
	   		bootbox.alert("请先选择要删除的记录");
	   		return;
	   	}
	   	var bill_ids = getCheckStr("ids");
	   	var billType = "${batch.billType}";
	   	var rate = "${batch.rate}";
	   	var discId = "${batch.discId}";
	   	var discDt = "${batch.discDt}";
	   	var custAccount = "${batch.custAccount}";
		bootbox.confirm("确定要删除选中的记录吗?", function(result) {
			if(result){
				$.ajax({
					type: "POST",
					url: '<%=basePath%>discApplyController.do?method=delElecBill',
			    	data: {'discmxIds': bill_ids,'billType': billType,'rate': rate,'discDt': discDt,'discId': discId,'custAccount': custAccount},
					dataType:'json',
					//beforeSend: validateData,
					cache: false,
					success: function(data){	
						if (data.success){  //处理成功
						   if(data.attributes.count == "0"){
							    modal("#layer_loading,#image");
							    dataCollectForm.action = "<%=basePath%>discApplyController.do?method=elecBatchList";
			                    dataCollectForm.submit();
						   }else{
		                    	userForm.submit();
						   }
						} else {
							bootbox.alert('删除失败!');
						}
					}
			    });
		     }
	   	});
	}
	function add(){
	    dynamicHiddenElement('dataCollectForm','custAccount',$('#custAccount').val());
	    dynamicHiddenElement('dataCollectForm','discIds',$('#discId').val());
		dynamicHiddenElement('dataCollectForm','rate',$('#rate').val());
		dynamicHiddenElement('dataCollectForm','discDt',$('#discDt').val());
		dynamicHiddenElement('dataCollectForm','billType',$('#billType').val());
		modal("#layer_loading,#image");
		dataCollectForm.action = "<%=basePath%>discApplyController.do?method=addElecBill";
		dataCollectForm.submit();
	}
	//利息试算
	function toTrial(){
	     var discId = $("#discId").val();
		 var ids = getCheckStr("ids"); 
	   	 var diag = new top.Dialog();
		 diag.Drag = true;
		 diag.Title ="利息复核";
		 diag.URL = "<%=basePath%>discApplyController.do?method=toElecInterestTrial&ids="+ids+"&discId="+discId;
		 diag.Width = 480;
		 diag.Height = 170;
		 diag.CancelEvent = function(){ //关闭事件
			userForm.submit();
			diag.close();
		 };
		 diag.show(); 
	}
	function goHistory(){
	    modal("#layer_loading,#image");
		elecReviewForm.action = "<%=basePath%>discApplyController.do?method=elecBatchList";
		elecReviewForm.submit();
	}
	function selectValue(){
	     var  myselect=document.getElementById("professionName");
		 var indexs=myselect.selectedIndex ;
	   	 var professionName = myselect.options[indexs].value;
	   	 $("#professionNames").val(professionName);
	}
	//利息试算
	function trial(){
		var checkNum = getCheckNum("ids");
	   	var discId = $("#discId").val();
	    if (checkNum == 0){
	   		bootbox.alert("请先选择要进行利息复核的记录");
	   		return;
	   	}
		//var ids = getCheckStr("ids");
		var  myselects=document.getElementById("prodNo");
		var index=myselects.selectedIndex ;
	   	var prodNo = myselects.options[index].value;
	   	var  myselect=document.getElementById("professionName");
		var indexs=myselect.selectedIndex ;
	   	var professionName = myselect.options[indexs].value;
	   	var custManage = $('#custManage').val();
	   	var custManagerName = $('#custManagerName').val();
	   	var deptName = $('#deptName').val();
	   	var payAccountType = $('#payAccountType').val();
	   	var payAccount = $('#payAccount').val();
	   	var payCustName = $('#payCustName').val();
	   	var buyPayRate = $('#buyPayRate').val();
	   	var custAccount = $('#custAccount').val();
	   	var payType = $('#payTypes').val();
	   	var cbRate =$("#cbRate").val();
		if(cbRate == ""){
		 	$("#cbRate").val(0);
		}
		if($("#userForm").valid()){
		 	if(check()){
				if(checkCb()){
					$.ajax({
						type: "POST",
						url: '<%=basePath%>discApplyController.do?method=trialUpdateBatch',
				    	data: {'discId': discId,
	                          'prodNo': prodNo,'cbRate': cbRate,'professionName': professionName,'custManage': custManage,'custManagerName': custManagerName,
	                          'deptName': deptName,'payAccountType': payAccountType,'payAccount': payAccount,'payCustName': payCustName,'buyPayRate': buyPayRate,
	                          'payType': payType
	                          },
						dataType:'json',
						cache: false,
						success: function(data){	
							if (data.success){  //处理成功
							   // modal("#layer_loading,#image");
							    toTrial();
							} else {
								bootbox.alert(data.msg);
							}
						}
				   });
			 	}
		 	}
	   	}		
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
	//打印贴现审核凭证
	function printDocument(){
		var payType="${discApplyInfo.payType}";//付息方式 1买 2卖 3协议
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