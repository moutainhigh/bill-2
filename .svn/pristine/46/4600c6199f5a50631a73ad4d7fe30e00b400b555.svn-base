<%-- 
 * 文件名称: disc_elec_apply_add_batch.jsp
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
</head>
<body>
	<div class="clearfix">
		<div id="page-content" class="page-content">
			<div id="header">
				<form action="<%=basePath%>discApplyController.do?method=saveAfterPage" method="post" name="addBatchForm" id="addBatchForm" class="form-search ele-detail-form">
					<%-- 查询区  --%>
					<input type="hidden" name="ids" id="ids" value="${ids}"/>
					<input type="hidden" name="isedit" id="isedit" value="${isedit}"/>
					<input type="hidden" name="discIds" id="discIds" value="${discIds}"/>
					<input type="hidden" name="batchNos" id="batchNos" value="${batchNos}"/>
					<div class="row-fluid">
						<label for="tradeAcct"><span class="star">*</span>票据类型</label>
						<t:dictSelect name="billType" other="" className="select-medium" dictGroup="K_BILL_TYPE" defaultVal="${billType}" haveHead="true" valid="required" disabled="disabled">
						</t:dictSelect> 
				        <input type="hidden" id="billType" name="billType" value="${billType}"/> 
						<label for="cbRate"><span class="star">*</span>票据品种</label>
						<t:dictSelect  name="billClass" other="" className="select-medium" dictGroup="K_BILL_CLASS" defaultVal="${billClass}" haveHead="true" valid="required" disabled="disabled">
				        </t:dictSelect> 
					    <input type="hidden" name="billClass" value="${billClass}"/> 
						<label for="tradeAcctName"><span class="star">*</span>产品名称</label>
						<t:select id="prodNo" other="" className="select-medium" name="prodNo" list="prodList" listKey="prodName" listValue="prodNo" defaultVal="${discApplyInfo.prodNo}"></t:select>
					</div>
					<div class="row-fluid">
						<label for=""><span class="star">*</span>付息方式</label>
						<input type="text" name="payTypes" id="payType" class="input-medium" readonly="true" valid="required"/> 
						<input type="hidden" name="payType" id="payTypes" value="${discApplyInfo.payType}"/> 
						<label for="totalNum"><span class="star">*</span>利率类型</label>
						<input type="text" class="input-medium" name="rateType" readonly="true" id="rateType" value="年利率" placeholder=""/>
						<label for="rebuyDt"><span class="star">*</span>利率</label>
						<input type="text" class="input-medium" name="rate" id="rate" valid="required" value="${fns:formateRate(rate)}" placeholder="" readonly="true"/>
					</div>
					<div class="row-fluid">
						<label for="custManage">成本利率</label>
						<input type="text" class="input-medium" name="cbRate" id="cbRate" title="请输入0-100之间千分位的小数或整数" onblur = "checkCb();" value="${fns:formateRate(discApplyInfo.cbRate)}" placeholder=""/>
						<label for="deptName" class="discLabeFirst"><span class="star">*</span>行业投向</label>
						<t:select id="professionName" className="select-medium" name="professionName" list="professionList" listKey="professionName" listValue="professionName" defaultVal="${discApplyInfo.professionName}"></t:select>
						<label for="custManagerName"><span class="star">*</span>贴现日</label>
						<input class="input-medium" name="discDt"id="discDt" value ="${discDt}" readonly="true" valid="required" type="text">
					</div>
					<div class="row-fluid">
						<label for="custBankNo"><span class="star">*</span>客户号</label>
						<input type="text" class="input-medium" name="custNo" readonly="true" id="custNo" valid="required" value="${discApplyInfo.custNo}" placeholder=""/>
						<label for="custBankName"><span class="star">*</span>客户名称</label>
						<input type="text" class="input-medium" name="custName" readonly="true" id="custName" value="${discApplyInfo.custName}" placeholder=""/>
						<label for="rate"><span class="star">*</span>客户账号</label>
						<input type="text" class="input-medium" name="custAccount" readonly="true" id="custAccount" value="${acctNo}" placeholder=""/>
					</div>
					<div class="row-fluid">
						<label for="custManage"><span class="star">*</span>客户经理编号</label>
						<input type="text" class="input-medium" name="custManage" id="custManage" valid="required" onblur="fill();" value="${discApplyInfo.custManage}" placeholder=""/>
						<label for="tradeAcct"><span class="star">*</span>客户经理名称</label>
						<input type="text" class="input-medium" readonly="true" name="custManagerName" valid="required" id="custManagerName"  value="${discApplyInfo.custManagerName}" placeholder=""/>
						<label for="rebuyDt"><span class="star">*</span>客户经理部门</label>
						<input type="text" class="input-medium" name="deptName" readonly="true" id="deptName" valid="required" value="${discApplyInfo.deptName}" placeholder=""/>
					</div>
					<div class="row-fluid buyer">
						<label for="totalInterest"><span class="star">*</span>付息账号类型</label>
						<t:dictSelect  name="payAccountType" className="select-medium" other="" valid="required" dictGroup="K_ZHLX" defaultVal="${discApplyInfo.payAccountType}" haveHead="true" disabled="disabled" >
					    </t:dictSelect> 
					    <input type="hidden" id="payAccountType" name="payAccountType" value="${discApplyInfo.payAccountType}"/> 
						<label for="totalMoney">买方付息人账号</label>
						<input type="text" class="input-medium" name="payAccount" valid="required" onblur="buyFill();" id="payAccount" value="${discApplyInfo.payAccount}" placeholder=""/>
						<label for="actualAmount"><span class="star">*</span>买方付息人名称</label>
						<input type="text" class="input-medium" name="payCustName" readonly="true" valid="required" id="payCustName" value="${discApplyInfo.payCustName}" placeholder=""/>
					</div>
					<span class="row-fluid saler">
						<label for="actualAmount"><span class="star">*</span>买方付息比例</label>
						<input type="text" class="input-medium" name="buyPayRate" valid="required number" title="请输入0-100之间千分位的小数或整数" onblur = "check();" id="buyPayRate" value="${fns:formateRate(discApplyInfo.buyPayRate)}" placeholder=""/>
					</span>
				</form>
			</div>
			<%-- 按钮操作区 --%>
			<div id="center">
				<form  id="btnForm"> 
					<div class="row-fluid">
						 <div class="span6" id="btn-left">
						 </div>
					   <div class="span6" id="btn-right">
							<a class="btn-mini pull-right" onclick="save();">保存并继续</a>
						    <a class="btn-mini pull-right" onclick="history();">返回</a>
					   </div>
				  	</div>
				</form> 
			</div>
			<div id="footer" class="footer">
				<table class="table table-striped table-bordered table-hover" id="tab" cellpadding="0" cellspacing="0" style="min-width:100%;max-width:2600px;width:2600px;">
					<thead>
						<tr>
							<th class="center">票号</th>
							<th class="center">入账账号</th>
							<th class="center">入账行号</th>
							<th class="center">票据类型</th>
							<th class="center">出票日</th>
							<th class="center">票面到期日</th>
							<th class="center">贴现日</th>
							<th class="center">利率</th>
							<th class="center">票面金额</th>
							<%--<th class="center">清算方式</th>--%>
							<th class="center">不得转让标记</th>
							<th class="center">报文实付金额</th>
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
										<td class="center">${disc.billNo}</td>
										<td class="center">${disc.inAcctNo}</td>
										<td class="center">${disc.inBankNo}</td>
										<td class="center">${fns:getDictLabel('K_BILL_TYPE',disc.billType)}</td>
										<td class="center">${disc.issueDt}</td>
										<td class="center">${disc.dueDt}</td>
										<td class="center">${disc.discDt}</td>
										<td class="center">${disc.rate}</td>
										<td class="center">${fns:formateMoney(disc.billMoney)}</td>
										<%--<td class="center">${fns:getDictLabel('K_ISONLINE',disc.isOnline)}</td>--%>
										<td class="center">${fns:getDictLabel('K_FORBIDFLAG',disc.forbidFlag)}</td>
										<td class="center">${fns:formateMoney(disc.draftPayMoney)}</td>
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
			<form  action="#" name="dataCollectForm" method="post"></form>
			<%-- /列表操作区 --%>
		</div>
	</div>
<%@ include file="/webpage/system/admin/modalDialog.jsp"%>
<%@ include file="/webpage/system/admin/footer.jsp"%>
<script>
var prodNo='${discApplyInfo.prodNo}';
	    if(prodNo=='001001003'){
    			$("#payType").val('卖方付息');
    			$("#payTypes").val('2');
    			$(".buyer").hide();
    			$(".saler").hide();
    			$("#buyPayRate").val('0');
    			$("#footer").height($("#footer").height()+32);
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
    			$("#footer").height($("#footer").height()+32);
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
    		var footerHeight;
    		if($("#prodNo").find('option:selected').attr('value')=='001001003'){
    			$("#payType").val('卖方付息');
    			$("#payTypes").val('2');
    			$(".buyer").hide();
    			$(".saler").hide();
    			$("#footer").height($("#footer").height()+32);
    			$("#buyPayRate").val('0');
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
    			$("#footer").height($("#footer").height()+32);
    			$("#buyPayRate").val('0');
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
    rfill();
	function fill(){ //根据客户账号查询客户信息并填充
			var custManagerNo = $('#custManage').val();
			$.ajax({
				type: "POST",
				url: "<%=basePath%>custManagerController.do?method=custMagInfo",
		    	data: {'custManagerNo': custManagerNo},
				dataType:'json',
				beforeSend:function(){
					if(custManagerNo==null||custManagerNo==""){
						bootbox.alert("请输入客户经理编号");
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
						var trHtml = "<tr><td colspan=\"100\" class=\"center\">没有相关数据</td></tr>";
						$("#dataBody").html(trHtml);$("#page").html("");
					}
				}
			});
		}
	function rfill(){ //自动根据客户账号查询客户信息并填充
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
						var trHtml = "<tr><td colspan=\"100\" class=\"center\">没有相关数据</td></tr>";
						$("#dataBody").html(trHtml);$("#page").html("");
					}
				}
			});
		}
		$(document).ready(function(){
		 var professionName='${discApplyInfo.professionName}';
	     $("#professionName").select2().select2("val", professionName);
	   $("#userForm").validate({
	      onfocusout:
	      function(element){
	      		$(element).valid();
	      }
	   });
	});	 
		//保存
		function save(){
		 	var cbRate =$("#cbRate").val();
		 	if(cbRate == ""){
		 		$("#cbRate").val(0);
		 	}
		 	if($("#addBatchForm").valid()){
		 		if(check()){
		 			if(checkCb()){
		 				$.ajax({
							type: "POST",
							url: '<%=basePath%>discApplyController.do?method=saveElecBatch',
					    	data: $('#addBatchForm').serialize(),
							dataType:'json',
							cache: false,
							success: function(data){	
								if(data.success){  //处理成功
				 	                toReceiveBillList(data.attributes.discId,data.attributes.batchNo);
								} else {
									bootbox.alert(data.msg); 
								}
							}
						});
		 			}
		 		}
			}
		}
		//跳转
		function toReceiveBillList(discId,batchNo){
			modal("#layer_loading,#image");
			dynamicHiddenElement('addBatchForm','batchNos',batchNo);
		    dynamicHiddenElement('addBatchForm','discIds',discId);
		 	addBatchForm.submit();
		}
		function check(){
		    var r = /^(\d{1,2}(\.\d{1,2})?|100)$/;
		    var s = $("#buyPayRate").val();
		    if(r.test(s)){
		        return true;
		    }else{
		    	bootbox.alert("请输入0-100之间千分位的小数或整数");
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
		        	bootbox.alert("请输入0-100之间千分位的小数或整数");
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
						bootbox.alert("请输入买方付息人帐号");
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
						var trHtml = "<tr><td colspan=\"100\" class=\"center\">没有相关数据</td></tr>";
						$("#dataBody").html(trHtml);$("#page").html("");
					}
				}
			});
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
	
	//返回
	function history(){
		var custAcctNo =$("#custAccount").val();
		var custNo =$("#custNo").val();
		var orgCode ='${orgCode}';
	    dynamicHiddenElement('dataCollectForm','custNos',custNo);
        dynamicHiddenElement('dataCollectForm','custAcctNo',custAcctNo);
        dynamicHiddenElement('dataCollectForm','orgCode',orgCode);
        modal("#layer_loading,#image");
        dataCollectForm.action = "<%=basePath%>discApplyController.do?method=electricReceive";
        dataCollectForm.submit();
	}
</script>
</body>
</html>