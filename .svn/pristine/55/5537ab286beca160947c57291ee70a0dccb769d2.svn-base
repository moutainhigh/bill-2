<%-- 
 * 文件名称: bank-fund.jsp
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
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/webpage/system/admin/taglib.jsp"%>
<%@ page import="java.util.*"%>
<%@ page import="java.text.*"%> 
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<base href="<%=basePath%>">
		<%-- jsp文件头和头部 --%>
		<%@ include file="/webpage/system/admin/top.jsp"%> 
		<script type="text/javascript" src="<%=basePath%>webpage/busi/common/transfChinese.js"></script>
	</head>
<body style="font-family:'微软雅黑';background:#f4f8fb;">
<div class="page-content" id="jump-content">
<form action="<%=basePath%>${action}" name="Form" id="Form" method="post">
	<table class="table table-bordered" id="tabd" cellpadding="0" cellspacing="0" style="margin-bottom:0px">
		<tbody>
			<tr>
				<td colspan="6" class="text-center" style="font-weight:bolder;text-align:center;font-size:16px;">银 行 承 兑 汇 票</td>
			</tr>
			<tr>
				<td colspan="2" id="">出 &nbsp;票&nbsp;&nbsp;日 &nbsp;期</td>
				<td>
					<input class="input-medium input-date" id="issueDt" name="issueDt" value="${bill.issueDt}" type="text" placeholder="请输入出票日期" valid="required dateISO" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"><span class="star">*</span>
				</td>
				<td colspan="2">票<span style="margin:0 20px"></span>号</td>
				<td>
					<input type="text" class="input-medium" name="billNo" value="${bill.billNo}" id="billNo" placeholder="请输入票号" valid="required" maxLength="16"/><span class="star">*</span>
					<c:if test="${copyadd!=''&&copyadd!=null&&copyadd=='1'}">
						<input type="hidden"id="startbillNo" name="startbillNo" disabled="disabled" value="${bill.billNo}"></input>					
					</c:if>
				</td>
			</tr>
			<tr>
				<td rowspan="3" class="text-center">出<br/>票<br/>人</td>
				<td>出票人全称</td>
				<td>
					<input type="text" class="input-medium" name="remitter" value="${bill.remitter}" id="remitter" placeholder="请输入出票人" valid="required" maxLength="60"/><span class="star">*</span>
				</td>
				<td rowspan="3" class="text-center">收<br/>款<br/>人</td>
				<td>收款人全称</td>
				<td>
					<input type="text" class="input-medium" name="payee" value="${bill.payee}" id="payee" placeholder="请输入收款人" valid="required" maxLength="60"/><span class="star">*</span>
				</td>
			</tr>
			<tr>
				<td>出票人账号</td>
				<td>
					<input type="text" class="input-medium" name="remitterAcct" value="${bill.remitterAcct}" id="remitterAcct" placeholder="请输入出票人账号" maxLength="30">
				</td>
				<td>收款人账号</td>
				<td>
					<input type="text" class="input-medium" name="payeeAcct" value="${bill.payeeAcct}" placeholder="请输入收款人账号" maxLength="30">
				</td>
			</tr>
			<tr>
				<td>付款行全称</td>
				<td>
					<input type="text" class="input-medium" id="remitterBankName" name="remitterBankName" value="${bill.remitterBankName}" placeholder="请输入付款行全称" valid="required" maxlength="60" disabled onblur="remitterBankNameCheck();"><span class="star">*</span>
				</td>
				<td>收款行全称</td>
				<td>
					<input type="text" class="input-medium" id="payeeBankName" name="payeeBankName" value="${bill.payeeBankName}" placeholder="请输入收款行全称" maxlength="60">
				</td>
			</tr>
			<tr>
				<td colspan="2">出票金额(人民币)</td>
				<td colspan="3">(大写):<span id="upperMoney"></span></td>
				<td>
					<input type="text" class="input-medium" id='billMoney' name="billMoney" value="${fns:double2String(bill.billMoney)}" placeholder="请输入出票金额" valid="required number"><span class="star">*</span>
				</td>
			</tr>
			<tr>
				<td colspan="2">汇 票 到 期 日</td>
				<td>
					<input id="dueDt" class="input-medium input-date" name="dueDt" value="${bill.dueDt}" type="text" placeholder="请输入汇票到期日" valid="required dateISO" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"><span class="star">*</span>
				</td>
				<td colspan="2">付款行行号</td>
				<td>
					<input type="text" class="input-medium" name="remitterBankNo" value="${bill.remitterBankNo}" id="remitterBankNo" placeholder="请输入付款行行号" valid="required" maxLength="12"/><span class="star">*</span>
				</td>
			</tr>
			<tr>
				<td colspan="2">承兑协议编号</td>
				<td>
					<input type="text" class="input-medium" name="protocalNo" id="protocalNo" value="${bill.protocalNo}" placeholder="请输入承兑协议编号" maxLength="20">
				</td>
				<td colspan="2">付款行地址</td>
				<td>
					<input type="text" class="input-medium" name="draweeAddr" id="draweeAddr" value="${bill.draweeAddr}" placeholder="请输入付款行地址" maxLength="100" readonly="true">
				</td>
			</tr>
			<tr>
				<td colspan="2">承<span style="margin:0 10px"></span>兑<span style="margin:0 11px"></span>行</td>
				<td>
					<input type="text" class="input-medium" name="acceptorBankName" id="acceptorBankName" value="${bill.acceptorBankName}" placeholder="请输入承兑行">
				</td>
				<td colspan="2">是 否 同 城</td>
				<td>
					<select class="select2 select-medium" id="select">
						<option value="1" select="selected"> 是</option>
						<option value="0">否</option>
					</select>
					<input type="hidden" id="isSameCity" name="isSameCity" value="${bill.isSameCity}"/>
				</td>
			</tr>
			<tr>
				<td colspan="2">备<span style="margin:0 28px"></span>注</td>
				<td>
					<input type="text" class="input-medium" name="remark" id="remark" value="${bill.remark}" placeholder="请输入备注" maxLength="200">
				</td>
				<td colspan="2">票 据 前 手</td>
				<td>
					<input type="text" class="input-medium" name="billBeforeOwner" id="billBeforeOwner" value="${bill.billBeforeOwner}" placeholder="请输入票据前手" maxLength="200">
				</td>
			</tr>
		</tbody>
	</table>
	<div class="row-fluid" id="bank">
		<div class="save center">
			<input type="hidden" name="isedit" value="${isedit}">
			<input type="hidden" name="billType" value="${billType}">
			<input type="hidden" name="batchId" value="${batchId}">
			<input type="hidden" name="billId" value="${billId}">
			<input type="hidden" name="custNo" value="${batch.custNo}">
			<input type="hidden" name="custAccount" value="${batch.custAccount}">
			<input type="hidden" name="branchNo" value="${batch.branchNo}">
			<input type="hidden" name="discmxId" value="${discmxId}">
			<input type="hidden" name="custName" value="${batch.custName}">
			<input type="hidden" name="prodNo" value="${prodNo}">
			<a id="savePage" class="btn-mini" onclick="save();">保存 </a>
			<a class="btn-mini" onclick="top.Dialog.close();">取消</a>
		</div>
	</div>
</form>
</div>
<%@ include file="/webpage/system/admin/modalDialog.jsp"%>
<%@ include file="/webpage/system/admin/footer.jsp"%>
<script type="text/javascript">
	var isedit = "${isedit}";
	//票号校验
	$("#billNo").blur(function(){
    	billNoCheck();
    })
	function billNoCheck(){
		var billNo = $("#billNo").val();
		var billNoValidate = /^(\d{16})$/;
		if(billNo==""){
			showTips("billNo","这是必填字段");
			return;
		}
		if($("#startbillNo").val()==billNo){
			showTips("billNo","请修改票号");
			return;
		}
		if (!billNoValidate.exec(billNo)) {
			showTips("billNo","票号应为16位数字");
			return;
		}
		if (!(/\d{3}/).exec(billNo.substring(0, 3))) {
			showTips("billNo","16位票号前3位应为银行机构代码");
			return;
		}
		if (billNo.substr(6, 1) != "5") {
			showTips("billNo","银承第7位必须为数字5");
			return;
		}
	}
	//出票人全称校验
	$("#remitter").blur(function(){
    	remitterCheck();
    })
	function remitterCheck(){
		var remitter = $("#remitter").val();
		if(remitter==""){
			showTips("remitter","这是必填字段");
	 		return;
		}
		if(remitter.length>60){
	 		showTips("remitter","出票人名称小于60位");
	 		return;
	 	}
	}
	//收款人全称校验
	$("#payee").blur(function(){
    	payeeCheck();
    })
	function payeeCheck(){
		var payee = $("#payee").val();
		if (payee.length=="") {
			showTips("payee","这是必填字段");
			return;
		}
	 	if (payee.length > 60) {
			showTips("payee","收款人全称小于60位");
			return;
		}
	}
	//付款行全称校验
	function remitterBankNameCheck(){
		var remitterBankName = $("#remitterBankName").val();
		if (remitterBankName.length > 60) {
			showTips("remitterBankName","付款行全称小于60位");
			return;
		}
	}
	//提交前付款行行号校验
	$("#remitterBankNo").blur(function(){
    	remitterbanknocheck();
    })
	function remitterbanknocheck(){
		var remitterBankNo = $("#remitterBankNo").val();
		var remitterBankNoValidate = /^(\d{12})$/;
		if (remitterBankNo.length=="") {
			showTips("remitterBankNo","这是必填字段");
			return;
		}
		if(!remitterBankNoValidate.exec(remitterBankNo)){
			showTips("remitterBankNo","付款行行号必须是12位数字");
			return;
		}
		var result = "";
		$.ajax({
			url:"discApplyController.do?method=checkDraweeBankNo",
			data:{"draweeBankNo":remitterBankNo},
			type:"POST",
			dataType:"JSON",
			success:function(data){
				result=data.success;
				if(!result){
					showTips("remitterBankNo",data.msg);	
					return;				
				}else{
					document.getElementById("remitterBankName").value=data.attributes.ActorFullCall;
					document.getElementById("acceptorBankName").value=data.attributes.ActorFullCall;
					document.getElementById("draweeAddr").value=data.attributes.Address;
				}
			},
			error:function(data){
				showTips("remitterBankNo","付款行行号查询异常");
			}
		});
	}
	//保存
	function save(){
		if($("#Form").valid()){
			//出票日校验
			var issueDt = $("#issueDt").val();
			var myDate = new Date(); 
			var arr=issueDt.split("-");  
			var startYear = myDate.getFullYear();
			var startMonth = myDate.getMonth();
			var startDay = myDate.getDate();
			var temp=(arr[2]-startDay)+((arr[1]-startMonth-1)*30)+((arr[0]-startYear)*360);
			if(temp>0){
				showTips("issueDt","出票日必须小于当前营业日");
				return;
			}
			//票号校验
			var billNo = $("#billNo").val();
			var billNoValidate = /^(\d{16})$/;
			if($("#startbillNo").val()==billNo){
				showTips("billNo","请修改票号");
				return;
			}
			if (!billNoValidate.exec(billNo)) {
				showTips("billNo","票号应为16位数字");
				return;
			}
			if (!(/\d{3}/).exec(billNo.substring(0, 3))) {
				showTips("billNo","16位票号前3位应为银行机构代码");
				return;
			}
			if (billNo.substr(6, 1) != "5") {
				showTips("billNo","银承第7位必须为数字5");
				return;
			}
			//出票人全称校验
			var remitter = $("#remitter").val();
			if(remitter.length>60){
	 			showTips("remitter","出票人名称小于60位");
	 			return;
	 		}
			//付款行全称校验
			var remitterBankName = $("#remitterBankName").val();
			if (remitterBankName.length > 60) {
				showTips("remitterBankName","付款行全称小于60位");
				return;
			}
	 		//收款人校验
	 		var payee = $("#payee").val();
	 		if (payee.length > 60) {
				showTips("payee","收款人全称小于60位");
				return;
			}
			//金额校验
			var currencyDigits=$("#billMoney").val();
			var pointPosition=currencyDigits.indexOf(".");
			var afterPointStr=currencyDigits.substring(pointPosition+1,currencyDigits.length);
			var beforePointStr=currencyDigits.substring(0,pointPosition);
			if (currencyDigits.match(/[^,.\d]/) != null) {
				showTips("billMoney","出票金额录入错误!");
				return;
			}
			if ((currencyDigits).match(/^((\d{1,3}(,\d{3})*(.((\d{3},)*\d{1,3}))?)|(\d+(.\d+)?))$/) == null) {
				showTips("billMoney","出票金额格式不正确!");
				return;
			}
			if (Number(currencyDigits) > 9999999999.99) {
				showTips("billMoney","数字长度应小于等于13位!");
				return;
			}	
			if(pointPosition!=-1){
				if(afterPointStr.length>2){
			  		showTips("billMoney","小数点后只能有2位!");
			   		return;
				}
				if(new String(Math.round(beforePointStr)).length>9){
				  showTips("billMoney","票面金额不能达到十亿!");
			   	  return;
				}
				if(Math.round(beforePointStr)==0&&(afterPointStr=="0"||afterPointStr=="00")){
					showTips("billMoney","出票金额不能为0");
					return;
				}		
			}else{
				var numberValue=new String(Math.round(currencyDigits));
				if(numberValue.length>9){
					showTips("billMoney","票面金额不能达到十亿!");
			   		return;
				}
				if(Math.round(currencyDigits)=="0"){
					showTips("billMoney","出票金额不能为0");
					return;
				}
			}
			//汇票到期日校验
		    var dueDt = $("#dueDt").val(); 
		    var endArr = dueDt.split("-");
		    var tem=(endArr[2]-arr[2])+((endArr[1]-arr[1])*30)+((endArr[0]-arr[0])*360);
		    if(dueDt==null||dueDt==""){
		 		showTips("dueDt","这是必填字段");
				return;
		 	}
			if(tem<0){
				showTips("dueDt","票面到期日必须大于出票日");
				return;
			}
			if(tem>180){ //此算法：平均每月30天，6个月180天，实际6个月最大相差184天
				showTips("dueDt","票面到期日,出票日相差不能超过6个月");
				return;
			}
			//付款行行号
			var remitterBankNo = $("#remitterBankNo").val();
			var remitterBankNoValidate = /^(\d{12})$/;
			if(!remitterBankNoValidate.exec(remitterBankNo)){
				showTips("remitterBankNo","付款行行号必须是12位数字");
				return;
			}
			var result = "";
			$.ajax({
				url:"discApplyController.do?method=checkDraweeBankNo",
				data:{"draweeBankNo":remitterBankNo},
				type:"POST",
				async:false,
				dataType:"JSON",
				success:function(data){
					result=data.success;
					if(!result){
						showTips("remitterBankNo",data.msg);			
					}else{
						document.getElementById("remitterBankName").value=data.attributes.ActorFullCall;
						document.getElementById("draweeAddr").value=data.attributes.Address;
						document.getElementById("isSameCity").value=$("#select").val();
						modal("#layer_loading,#image");
						Form.submit();
						$("#zhongxin").hide();	
					}
				},
				error:function(data){
					showTips("remitterBankNo","付款行行号查询异常");
				}
		    });	
		}
	}
	//金额校验
	$('#billMoney').keyup(function(){
		addMoneyFormat();
	});
	if("${bill.billMoney}"!=null&&"${bill.billMoney}"!=""){//修改
		addMoneyFormat();//addMoneyFormat("${bill.billMoney}");
	}
	function addMoneyFormat(){
		//金额校验
		var currencyDigits=$("#billMoney").val();
		var pointPosition=currencyDigits.indexOf(".");
		var afterPointStr=currencyDigits.substring(pointPosition+1,currencyDigits.length);
		var beforePointStr=currencyDigits.substring(0,pointPosition);
		if (currencyDigits.match(/[^,.\d]/) != null) {
			showTips("billMoney","出票金额录入错误!");
			return;
		}
		if ((currencyDigits).match(/^((\d{1,3}(,\d{3})*(.((\d{3},)*\d{1,3}))?)|(\d+(.\d+)?))$/) == null) {
			showTips("billMoney","出票金额格式不正确!");
			return;
		}
		if (Number(currencyDigits) > 9999999999.99) {
			showTips("billMoney","数字长度应小于等于13位!");
			return;
		}	
		if(pointPosition!=-1){
			if(afterPointStr.length>2){
		  		showTips("billMoney","小数点后只能有2位!");
		   		return;
			}
			if(new String(Math.round(beforePointStr)).length>9){
			  	showTips("billMoney","票面金额不能达到十亿!");
		   	 	return;
			}
			if(Math.round(beforePointStr)==0&&(afterPointStr=="0"||afterPointStr=="00")){
				showTips("billMoney","出票金额不能为0");
				return;
			}		
		}else{
			var numberValue=new String(Math.round(currencyDigits));
			if(numberValue.length>9){
				showTips("billMoney","票面金额不能达到十亿!");
		   		return;
			}
			if(Math.round(currencyDigits)=="0"){
				showTips("billMoney","出票金额不能为0");
				return;
			}
		}
		$("#billMoney").valid();
		document.getElementById("upperMoney").innerHTML = cmycurd(currencyDigits);
	}
</script>
</body>
</html>