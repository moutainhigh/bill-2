<%-- 
 * 文件名称: riskbill_edit.jsp
 * 系统名称: 票据管理平台
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: @version2.0.0.1
 * 开发人员: wuzhichen
 * 开发时间: 2016-7-13 上午06:28:22
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期    修改人员    修改说明
 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../admin/taglib.jsp"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
	<head>
		<base href="<%=basePath%>">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<%@ include file="../admin/top.jsp"%> 
	</head>
<body>
	<div class="clearfix">
		<div  class="page-content" id="jump-content">
			<form  action="riskBillController.do?method=save" name="Form" id="Form" method="post" class="form-search">
				<input type="hidden" name="isedit" value="${isedit}"/>
				<input type="hidden" name="id" value="${riskbill.id}"/>
				<div id="zhongxin">
					<div class="row-fluid" >
						<label for="bill1" class="text-right">票号前 8 位</label>
						<input type="text" class="input-medium" name="bill1" id="bill1" placeholder="请输入票号前8位" value="${riskbill.bill1}" maxLength="8" onblur="bill1Check();" />											
						<label for="bill2" class="text-right"><span class="star">*</span>票号后 8 位</label>
						<input type="text" class="input-medium" name="bill2" id="bill2" placeholder="输入票号后8位" value="${riskbill.bill2}" maxLength="8" onblur="bill2Check();" valid="required"/>
					</div>
					<div class="row-fluid" >
						<label for="bill_no" class="text-right"><span class="star">*</span>票号</label>
						<input type="text" class="input-medium" name="bill_no" value="${riskbill.billNo}" id="bill_no" placeholder="请输入票号"  maxLength="16"/>
						<label for="bill_money" class="text-right"><span class="star">*</span>票 面金 额</label>
						<input type="text" class="input-medium" name="bill_money" id="bill_money" placeholder="请输入票面金额" value="${riskbill.billMoney}"/>
					</div>
					<div class="row-fluid" >
						<label for="bill_type" class="text-right">票 据类 型</label>
						<t:dictSelect name="bill_type" dictGroup="K_BILL_TYPE" defaultVal="${riskbill.billType}" haveHead="true">
						</t:dictSelect>
						<label for="status" class="text-right">状态</label>
						<t:dictSelect name="status" dictGroup="K_RISKSTATUS" defaultVal="${riskbill.status}" haveHead="true">
						</t:dictSelect>
					</div>
					<c:if test="${isedit=='0'}">
						<div class="row-fluid">
						    <label for="issue_dt" class="text-right"><span class="star">*</span>出 票日 期</label>
						    <input class="input-medium input-date" name="issue_dt" id="issue_dt" value="${riskbill.issueDt}" type="text" valid="required dateISO" placeholder="请输入出票日期" />
							<label for="due_dt" class="text-right"><span class="star">*</span>到 期日 期</label>
							<input class="input-medium input-date" name="due_dt" id="due_dt" value="${riskbill.dueDt}" type="text" valid="required dateISO" placeholder="请输入到期日期"  onblur="dueDtCheck();" /></td>	
						</div>
					</c:if>
					<c:if test="${isedit=='1'}">
						<div class="row-fluid">
						    <label for="issue_dt" class="text-right">出 票日 期</label>
						    <input class="input-medium input-date" name="issue_dt" id="issue_dt" value="${riskbill.issueDt}" type="text" valid="required dateISO" placeholder="出票日期" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"/>
							<label for="due_dt" class="text-right">到 期日 期</label>
							<input class="input-medium input-date" name="due_dt" id="due_dt" value="${date.dueDt}" type="text" valid="required dateISO" placeholder="到期日期" onblur="dueDtCheck();" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"/></td>	
						</div>
					</c:if>
					<div class="row-fluid" >
						<label for="postdate" class="text-right">公 告日 期</label>
						<input class="input-medium input-date" name="postdate" id="postdate" value="${riskbill.postdate}" type="text" valid="dateISO" placeholder="公告日期" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"/>
						<label for="enterdate" class="text-right">录 入日 期</label>
						<input class="input-medium input-date" name="enterdate" id="enterdate" value="${riskbill.enterdate}" type="text" valid="dateISO" placeholder="录入日期" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"/>
					</div>
					<div class="row-fluid">
						<label for="remitter" class="text-right">出票人</label>
						<input type="text" class="input-medium" name="remitter" id="remitter" placeholder="请输入出票人" value="${riskbill.remitter}"/>
						<label for="urgeapplyname" class="text-right">催告申请人</label>
						<input type="text" class="input-medium" name="urgeapplyname" id="urgeapplyname" placeholder="请输入催告申请人" value="${riskbill.urgeapplyname}"/>
					</div>
					<div class="row-fluid">
						<label for="acceptor" class="text-right">承兑人</label>
						<input type="text" class="input-medium" name="acceptor" id="acceptor" placeholder="请输入承兑人" value="${riskbill.acceptor}"/>
						<label for="courtname" class="text-right">公 告法 院</label>
						<input type="text" class="input-medium" name="courtname" id="courtname" placeholder="请输入公告法院" value="${riskbill.courtname}"/>
					</div>
					<div class="row-fluid" >
						<label for="matchfileno" class="text-right">档 案编 号</label>
						<input type="text" class="input-medium" name="matchfileno" id="matchfileno" placeholder="请输入档案编号" value="${riskbill.matchfileno}"/>
						<label for="description" class="text-right">风 险描 述</label>
						<input type="text" class="input-medium" name="description" id="description" placeholder="请输入风险描述" value="${riskbill.description}"/>
					</div>
					<div class="row-fluid">
						<div class="center save">
							<a id="savePage" class="btn-mini" onclick="save()">保存</a>
							<a class="btn-mini" onclick="top.Dialog.close();">取消</a>
						</div>
					</div>
					</div>
				</form>
			  </div>
			</div>			
<%@ include file="/webpage/system/admin/modalDialog.jsp"%>
<%@ include file="../admin/footer.jsp"%> 
<script type="text/javascript">
	//给录入日期塞入营业日
     var isedit = '${isedit}';
     if(isedit=='0'){
	    $("#enterdate").val('${dt}');
	}
	//保存
	function save(){
	    //票号，金额，出票日，到期日至少有一个必输
	    var bill_no = $("#bill_no").val();
		var bill_money=$("#bill_money").val();
		var issueDt = $("#issue_dt").val();
	    var dueDt = $("#due_dt").val(); 
	    if(bill_no==""&&bill_money==""&&issueDt==""&&dueDt==""){//目前是所有日期框都校验，若是票号，金额，出票日，到期日有一个输入了，就要对不同情况进行分析，若是输入的是其中一个日期框，则需校验日期，另票号后 8位的验证应该在保存方法中验证
	           showTips("bill_no","票号，金额，出票日，到期日至少有一个必输");
	           showTips("bill_money","票号，金额，出票日，到期日至少有一个必输");
	           showTips("issue_dt","票号，金额，出票日，到期日至少有一个必输");
	           showTips("due_dt","票号，金额，出票日，到期日至少有一个必输");
	           return;
	    }	
		//提交
			Form.submit();
			$("#zhongxin").hide();
			modal("#layer_loading,#image");
	}
    //票号校验
    $("#bill_no").blur(function(){
   		billNoCheck();
    })
	function billNoCheck(){
		var bill_no = $("#bill_no").val();
		var bill_type = $("#bill_type").val();
		var billNoValidate = /^(\d{16})$/;
		if (!billNoValidate.exec(bill_no)) {
			showTips("bill_no","票号应为16位数字");
			return;
		}
		else if (bill_no.substr(6, 1) != "5"&&bill_type=="1") {
			showTips("bill_no","银票第7位必须为数字5");
			return;
		}
		else if (bill_no.substr(6, 1) != "6"&&bill_type=="2") {
			showTips("bill_no","商票第7位必须为数字6");
			return;
		}else{
			$("#bill1").val(bill_no.substring(0,8));
			$("#bill2").val(bill_no.substring(8,16));
		}
	}
	//票号前八位校验
	function bill1Check(){
		var bill1 = $("#bill1").val();
		var bill_no = $("#bill_no").val();
		if (bill_no.substr(0, 8)!=bill1) {
				showTips("bill1","票号前八位必须和票号的相同");
				return;
			}
		}
	$("#bill1").change(function(){
		var laterVal=$("#bill_no").val().substring(8,16);
		$("#bill_no").val( $("#bill1").val() + laterVal);
	})
	//票号后八位校验
	function bill2Check(){
	var bill2 = $("#bill2").val();
	var bill_no = $("#bill_no").val();
	if (bill_no.substr(7, 8)!=bill2) {
			showTips("bill2","票号后八位必须和票号的相同");
			return;
		}
	}
	$("#bill2").change(function(){
		var beforeVal=$("#bill_no").val().substring(0,8);
		$("#bill_no").val( beforeVal + $("#bill2").val());
	})
   //金额校验
   $("#bill_money").blur(function(){
  		addMoneyFormat();
   })
	function addMoneyFormat(){
		//金额校验
		var currencyDigits=$("#bill_money").val();
		var pointPosition=currencyDigits.indexOf(".");
		var afterPointStr=currencyDigits.substring(pointPosition+1,currencyDigits.length);
		var beforePointStr=currencyDigits.substring(0,pointPosition);
		if (currencyDigits.match(/[^,.\d]/) != null) {
			showTips("bill_money","出票金额录入错误");
			return;
		}
		if ((currencyDigits).match(/^((\d{1,3}(,\d{3})*(.((\d{3},)*\d{1,3}))?)|(\d+(.\d+)?))$/) == null) {
			showTips("bill_money","格式不正确");
			return;
		}
		if (Number(currencyDigits) > 9999999999.99) {
			showTips("bill_money","数字长度应小于等于13位");
			return;
		}	
		if(pointPosition!=-1){
			if(afterPointStr.length>2){
		  		showTips("bill_money","小数点后只能有2位");
		   		return;
				}
			if(new String(Math.round(beforePointStr)).length>9){
			  showTips("bill_money","票面金额不能达到十亿");
		   	  return;
			}
			if(Math.round(beforePointStr)==0&&(afterPointStr=="0"||afterPointStr=="00")){
				showTips("bill_money","出票金额不能为0");
				return;
			}		
		}else{
		var numberValue=new String(Math.round(currencyDigits));
			if(numberValue.length>9){
				showTips("bill_money","票面金额不能达到十亿");
		   		return;
			}
			if(Math.round(currencyDigits)=="0"){
				showTips("bill_money","出票金额不能为0");
				return;
			}
		}
	}
	//汇票到期日校验
	function dueDtCheck(){
		var issueDt = $("#issue_dt").val();
        var arr=issueDt.split("-"); 
		var dueDt = $("#due_dt").val(); 
		var endArr = dueDt.split("-");
		var tem=(endArr[2]-arr[2])+((endArr[1]-arr[1])*30)+((endArr[0]-arr[0])*360);
		if(tem<0){
				showTips("due_dt","票面到期日必须大于出票日");
				return;
			}
	    if(tem>180){ //此算法：平均每月30天，6个月180天，实际6个月最大相差184天
				showTips("due_dt","票面到期日,出票日相差不能超过6个月");
				return;
			}
		}
	//录入日期校验
	function enterdateCheck(){
		var postdate = $("#postdate").val();
        var postArr=postdate.split("-"); 
		var enterdate = $("#enterdate").val(); 
		var entArr = enterdate.split("-");
		var t=(entArr[2]-postArr[2])+((entArr[1]-postArr[1])*30)+((entArr[0]-postArr[0])*360);
		if(t<0){
				showTips("enterdate","录入日期必须大于公告日期");
				return;
			}
		}
</script>
</body>
</html>