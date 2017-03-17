<%-- 
 * 文件名称: disc_editBatch.jsp
 * 系统名称: 票据管理平台
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: @version2.0.0.1
 * 开发人员: sherry
 * 开发时间: 2016-8-10
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期    修改人员    修改说明
 --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/webpage/system/admin/taglib.jsp"%>
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
	</head>
<body>
	<div class="clearfix">
		<div class="page-content" id="jump-content">
			<form action="discApplyController.do?method=saveBatch" method="post" name="Form" id="Form" class="row-fluid" role="form">
				<input type="hidden" name="isedit" value="${isedit}"/>
	   			<div id="zhongxin">
	   				<fieldset class="field">
		   				<legend>客户经理信息</legend>
						<div class="row-fluid" >
						 	<input type="hidden" name="discId" value="${discApplyInfo.discId}"/>
						 	<input type="hidden" name="batchNo" value="${discApplyInfo.batchNo}"/>
						 	<label for="param_value" class="pdTop"><span class="star">*</span></span>客户经理编号</label>
							<input type="text" class="input-medium" name="custManage" id="custManage" value="${discApplyInfo.custManage}" placeholder="请输入客户经理编号" valid="required"/>
							<label for="param_value" class="pdTop"><span class="star">*</span></span>客户经理名称</label>
							<input type="text" class="input-medium" readonly="true" name="custManagerName" id="custManagerName" value="${discApplyInfo.custManagerName}" valid="required" placeholder=""/>
						</div>
						<div class="row-fluid">
							<label for="param_value" class="pdTop"><span class="star">*</span>客户经理部门</label>
							<input type="text" class="input-medium" name="deptName" readonly="true" id="deptName" valid="required" value="${discApplyInfo.deptName}" placeholder=""/>
						</div>
					</fieldset>
					<fieldset class="field">
						<legend>客户信息（交易对手信息)</legend>
						<div class="row-fluid">
							<label for="param_value" class="pdTop"><span class="star">*</span>客户号</label>
							<input type="text" class="input-medium" name="custNo" readonly="true" id="custNo" valid="required" value="${discApplyInfo.custNo}" placeholder=""/>
							<label for="param_value" class="pdTop"><span class="star">*</span>客户名称</label>
							<input type="text" class="input-medium" name="custName" readonly="true" id="custName" valid="required" value="${discApplyInfo.custName}" placeholder=""/>
						</div>
						<div class="row-fluid">
							<label for="param_value" class="pdTop"><span class="star">*</span>客户账号</label>
							<input type="text" class="input-medium" name="custAccount" readonly="true" id="custAccount" valid="required" value="${discApplyInfo.custAccount}" placeholder=""/>
							<label for="param_value" class="pdTop"><span class="star">*</span>社会信用代码</label>
							<input class="input-medium" type="text" name="socialCreditCode" id="socialCreditCode" value="${discApplyInfo.socialCreditCode}" id="socialCreditCode" placeholder="请输入社会信用代码" valid="required" maxLength="18"/>
						</div>
						<div class="row-fluid">
							<label for="param_value" class="pdTop"><span class="star">*</span>企业规模</label>
							<t:dictSelect  name="enterpriseScale" className="select-medium" other="" valid="required" dictGroup="K_SCALE" defaultVal="${discApplyInfo.enterpriseScale}" haveHead="true"  >
								</t:dictSelect> 
							<label for="param_value" class="pdTop"><span class="star">*</span>是否为三农企业</label>
							<t:dictSelect name="isRuralEnterprises" className="select-medium" other="" dictGroup="K_IS_RURAL" defaultVal="0" valid="required" haveHead="true" >
								</t:dictSelect> 
						</div>
					</fieldset>
					<fieldset class="field">
						<legend>批次信息</legend>
						<div class="row-fluid">
							<label for="param_value" class="pdTop"><span class="star">*</span>票据类型</label>
							<t:dictSelect  name="billType" className="select-medium" other="" dictGroup="K_BILL_TYPE" defaultVal="${discApplyInfo.billType}" haveHead="true" valid="required" onchange="billTypeChanged();" title="票据类型">
							</t:dictSelect> 
							<label for="param_value" class="pdTop"><span class="star">*</span>产品名称</label>
							<t:select id="prodNo" className="select-medium" other="" name="prodNo" list="prodList" listKey="prodName" listValue="prodNo" defaultVal="${discApplyInfo.prodNo}" onchange="prodNoChanged();"></t:select>
						</div>
						<div class="row-fluid">
							<label for="param_value" class="pdTop"><span class="star">*</span>票据种类</label>
							<input type="text" class="input-medium" name="billClass" readonly="true" id="billClass"  valid="required" value="纸质" placeholder=""/> 
							<label for="param_value" class="pdTop"><span class="star">*</span>付息方式</label>
							<input type="text" class="input-medium" name="payType" id="payType" valid="required" readonly="true" value="${discApplyInfo.payType}" placeholder=""/>
						</div>
						<div class="row-fluid">
							<label for="param_value" class="pdTop"><span class="star">*</span>贴现利率</label>
							<input type="text" class="input-medium" name="rate" id="rate" valid="required" value="${discApplyInfo.rate}" valid="required" placeholder="请输入贴现利率" maxlength="7"/>%
							<label for="param_value" class="pdTop">成本利率</label>
							<input type="text" class="input-medium" name="cbRate" id="cbRate" value="${discApplyInfo.cbRate}" placeholder=""/>
						</div>
						<div class="row-fluid">
							<label for="param_value" class="pdTop"><span class="star">*</span>贴现日</label>
							<input type="text" class="input-medium input-date" name="discDt" id="discDt" value="${discApplyInfo.discDt}" placeholder="请选择有效日期 " valid="required dateISO" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"/> 
							<label for="param_value"  class="pdTop" ><span class="star">*</span>行业投向</label>
							<t:select id="professionName" className="select-medium" other="" name="professionName" list="resultList" listKey="professionName" listValue="professionName" headerKey='行业投向' headerValue='-1' defaultVal="${discApplyInfo.professionName}" ></t:select>
						</div>
						<div class="row-fluid">
							<label for="param_value" class="pdTop"><span class="star">*</span>卖方账号类型</label>
							<input type="text" class="input-medium" name="custAccountType" valid="required" readonly="true" id="custAccountType" value="结算账号" placeholder=""/>
							<label for="param_value" class="pdTop"><span class="star">*</span>申请生成日期</label>
							<input type="text" class="input-medium" name="createDt" readonly="true" id="createDt" value="${discApplyInfo.createDt}" valid="required" placeholder=""/>
						</div>
						<div class="row-fluid buyer" style="display:none;">
							<label for="param_value" class="pdTop"><span class="star">*</span>利率类型</label>
							<input type="text" class="input-medium" name="rateType" readonly="true"  id="rateType" value="年利率" valid="required" placeholder=""/>
							<label for="param_value" class="pdTop"><span class="star">*</span>买方付息人账号</label>
							<input type="text" class="input-medium" name="payAccount"  id="payAccount" valid="required" value="${discApplyInfo.payAccount}" placeholder=""/>
						</div>
						<div class="row-fluid buyer" style="display:none;">
							<label for="param_value" class="pdTop"><span class="star">*</span>买方账号类型</label>
							<t:dictSelect  name="payAccountType" className="select-medium" other="" valid="required" dictGroup="K_ZHLX" defaultVal="${discApplyInfo.payAccountType}" haveHead="true"  >
								</t:dictSelect> 
							<label for="param_value" class="pdTop"><span class="star">*</span>买方付息人名称</label>
							<input type="text" class="input-medium" name="payCustName" id="payCustName" valid="required" value="${discApplyInfo.payCustName}" placeholder=""/>
						</div>
						<div class="row-fluid">
							<div class="saler">
								<label for="param_value" class="pdTop"><span class="star">*</span>买方付息比例</label>
								<input type="text" class="input-medium" name="buyPayRate" valid="required" id="buyPayRate" value="${discApplyInfo.buyPayRate}" placeholder=""/>
							</div>
						</div>
					</fieldset>
					<div class="row-fluid save center">
						<a id="savePage" class="btn-mini" onclick="save();">提交</a>
					</div>
				</div>
			</form>
		</div>
	</div>
<%@ include file="/webpage/system/admin/modalDialog.jsp"%>
<%@ include file="/webpage/system/admin/footer.jsp"%>
<script type="text/javascript">
	var isedit = '${isedit}';
	if(isedit=='0'){
		//页面加载完毕产品名称是没有下拉选项的
		$("#prodNo").html("<option value=''>请选择产品</option>");
	}
	function prodNoChanged(){
		if(isedit!='1'){
			prodNo=$("#prodNo").find('option:selected').attr('value');
		}else{
			prodNo=$("#prodNo").val();
		}
		if($("#prodNo").find('option:selected').attr('value')=='001001003'){
   			$("#payType").val('卖方付息');
   			$(".buyer").hide();
   			$(".saler").hide();
   			$("#buyPayRate").val('0');
  	 	}else if($("#prodNo").find('option:selected').attr('value')=='001001002'){
   			$("#payType").val('买方付息');
   			$(".buyer").show();
   			$(".saler").hide();
   			$("#buyPayRate").val('0');
  	 	}else if($("#prodNo").find('option:selected').attr('value')=='001001001'){
  	 		$("#payType").val('协议付息');
   			$(".buyer").show();
   			$(".saler").show();
   		}else if($("#prodNo").find('option:selected').attr('value')=='001001006'){
  	 		$("#payType").val('卖方付息');
   			$(".buyer").hide();
   			$(".saler").hide();
   		}else if($("#prodNo").find('option:selected').attr('value')=='001001005'){
  			$("#payType").val('买方付息');
  			$(".buyer").show();
  			$(".saler").hide();
  			$("#buyPayRate").val('0')
 	 	}else if($("#prodNo").find('option:selected').attr('value')=='001001004'){
 	 		$("#payType").val('协议付息');
  			$(".buyer").show();
  			$(".saler").show();
  		}
	}
	function billTypeChanged(){
		var billType = $("#billType").val();
		$.ajax({
			type: "POST",
			url: "<%=basePath%>discApplyController.do?method=searchProdinfo",
	   		data: {'billType': $('#billType').val()},
			dataType:'json',
			success: function(data){
				var html = "<option value=''>请选择产品</option>";	
				if(data.success){  //处理成功
					for(var i = 0 ; i < data.obj.length ; i++ ){
					  html += "<option value=\""+data.obj[i].prodNo+"\">"+data.obj[i].prodName+"</option>";
					}
					$("#prodNo").empty();
					$("#prodNo").append(html);
				}
			}
		});
	}
	$(function(){
		if(isedit=='1'){
			prodNoChanged();
		}
	});
	if(isedit=='0'){
		$("#createDt").val('${date}');
		$("#cbRate").val('0')
		//var custAccount = localStorage.getItem("key");
		var custAccount=${custAccount};
  		$("#custAccount").val(custAccount);
	}
    $("#custManage").blur(function(){
    	fill();
    })
	function fill(){ //根据客户账号查询客户信息并填充
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
			/* beforeSend:function(){
				if(custAcctNo==null||custAcctNo==""){
					bootbox.alert("请输入客户账号");
					//$("#custNo").val("");
					$("#custName").val("");
			   		return false;
				}
			}, */
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
	/* $(document).ready(function(){
		$("#Form").validate({
			submitHandler:function(form){
		       //save(form);  
		    }
		});
		$("#Form").validate({
			onfocusout:
			function(element){
				$(element).valid();
			}
	    });
	}); */
	//保存
	function save(){
		if($("#Form").valid()){
			var rate = $("#rate").val();
			var cbrate = $("#cbRate").val();
			var prodNo = $("#prodNo").val();
			var professionName = $("#professionName").val();
			var socialCreditCode = $("#socialCreditCode").val();
			var socialCreditCodeValidate = /^(\d{18})$/;
			if (!socialCreditCodeValidate.exec(socialCreditCode)) {
				showTips("socialCreditCode","信用代码应为18位数字");
				return;
			}
			if(professionName==null||professionName==""||professionName=="-1")
			{
				showTips("professionName","请选择行业投向！");
			}else{
				if(prodNo==0)
				{
					showTips("prodNo","请选择产品名称！");
				}else{
					if((rate>=0)&&(rate<=100)){
						if(cbrate==""){
							$("#cbRate").val('0');
						}
						if(cbrate!=0)
						{
							if((cbrate>=0)&&(cbrate<=100))
							{
								if (isedit == '1'){
									$("#Form").submit();
									$("#zhongxin").hide();
									modal("#layer_loading,#image");//点击票据管理按钮，弹出‘加载中’的遮罩层
			 					}else{
			 						$("#Form").submit();
		     	  					modal("#layer_loading,#image");//点击票据管理按钮，弹出‘加载中’的遮罩层
								}	
							}else{
								showTips("cbRate","成本利率必须为0到100之间的数字");
								return;
							}
						}else{
							if (isedit == '1'){
								$("#Form").submit();
								$("#zhongxin").hide();
								modal("#layer_loading,#image");//点击票据管理按钮，弹出‘加载中’的遮罩层
		 					}else{
		 						$("#Form").submit();
	       					 	modal("#layer_loading,#image");//点击票据管理按钮，弹出‘加载中’的遮罩层
							}
						}
					}else{
						showTips("rate","利率必须为0到100之间的数字");
						return;
					}  
			 	}
			}
		}
	} 
	$("#buyPayRate").blur(function(){
    	buyPayRateCheck();
    })
	function buyPayRateCheck(){
		var buyPayRate = $("#buyPayRate").val();
		var buyPayRateValidate =/^\d+(\.\d{1,5})?$/;
		if(buyPayRate==""){
			showTips("buyPayRate","这是必填字段");
			return;
		}else if(!buyPayRateValidate.exec(buyPayRate)) {
			showTips("buyPayRate","请输入正确比例格式,保留小数1到5位");
			return;
		}else if(buyPayRate>100||buyPayRate<0){
			showTips("buyPayRate","输入比例应在0~100之间");
			return;
		}else if(buyPayRate.length>7){
			showTips("buyPayRate","请输入正确比例格式,保留小数1到5位");
			return;
		}
	}		
	$("#cbRate").blur(function(){
    	cbRateCheck();
    })
	function cbRateCheck(){
		var cbRate = $("#cbRate").val();
		var cbRateValidate =/^\d+(\.\d{1,5})?$/;
		if(cbRate==""){
			showTips("cbRate","这是必填字段")
		    return;
		}
		if (!cbRateValidate.exec(cbRate)) {
			showTips("cbRate","请输入正确比例格式,保留小数1到5位");
			return;
		} else if(cbRate>100||cbRate<0){
			showTips("cbRate","输入比例应在0~100之间");
			return;
		}else if(cbRate.length>7){
			showTips("cbRate","请输入正确比例格式,保留小数1到5位");
			return;
		}
	}	
	$("#rate").blur(function(){
		validateData();
    })
	function rateCheck(){
		var rate = $("#rate").val();
		var rateValidate =/^\d+(\.\d{1,5})?$/;
		if(rate==""){
			showTips("rate","这是必填字段");
			return;
		}else if(!rateValidate.exec(rate)) {
			showTips("rate","请输入正确比例格式,保留小数1到5位");
			return;
		}else if(rate>100||rate<0){
			showTips("rate","输入比例应在0~100之间");
			return;
		}else if(rate.length>7){
			showTips("rate","请输入正确比例格式,保留小数1到5位");
			return;
		}
	}
	
	function validateData(){
		var rate = $("#rate").val();
		var re1 =/^[0-9]+[\.]?[0-9]*$/;
		var msg = 1;
		
		if(rate==null||rate=="") {
			showTips("rate","这是必填字段");
			return;
		}
		if(re1.test(rate)){
			
		}else{
			showTips("rate","利率只能为数字");
			$("#rate").val("");
			return;
		}
		if(rate<0 || rate>100){
			showTips("rate","利率应大于等于0且小于等于100");
			$("#rate").val("");
			return;
		}
		
		//校验精度
  		var array = rate.split(".",-1);
  		if(array.length>2){
  		  showTips("rate","格式不正确");
  		$("#rate").val("");
		  return;
  		}else{
  			 if(array.length==2){
	  			if(array[1]!=null && array[1]!=""){
		  			if(array[1].length>4){
		  				showTips("rate","精确到小数点后4位");
		  				$("#rate").val("");
		  				return;
		  			}
  				}
  			}
  		
  		
  		}
  		
		return msg;	
  	}
  	
	//贴现申请人社会信用代码
	$("#socialCreditCode").blur(function(){
		socialCreditCodeCheck();
	})
	function socialCreditCodeCheck(){
		var socialCreditCode = $("#socialCreditCode").val();
		var socialCreditCodeValidate = /^(\d{18})$/;
		if(socialCreditCode==""){
			showTips("socialCreditCode","这是必填字段");
			return;
		}else if(!socialCreditCodeValidate.exec(socialCreditCode)) {
			showTips("socialCreditCode","信用代码应为18位数字");
			return;
		}
	}	
</script>
</body>
</html>