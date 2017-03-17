<%-- 
 * 文件名称: rebuy_editBatch.jsp
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
			<form action="<%=basePath%>rebuyApplyController.do?method=saveBatch" class="form-search" method="post" name="batchForm" id="batchForm">
				<fieldset class="field">
		   			<legend>交易对手信息</legend>
					<div class="row-fluid">
						<label for="custBankNo" class="pdTop text-right"><span class="star">*</span>交易对手行号</label>
						<input type="text" class="input-medium" name="custBankNo" id="custBankNo" value="${apply.custBankNo}" valid="required" placeholder=""/>
						<label for="custBankName" class="pdTop text-right"><span class="star">*</span>交易对手名称</label>
						<input type="text" class="input-medium" name="custBankName" id="custBankName" value="${apply.custBankName}" valid="required" placeholder=""/>
					</div>
				</fieldset>
				<fieldset class="field">
					<legend>客户经理信息</legend>
					<div class="row-fluid">
						<label for="custManage" class="pdTop text-right"><span class="star">*</span>客户经理编号</label>
						<input type="text" class="input-medium" name="custManage" id="custManage" value="${apply.custManage}" valid="required"  placeholder="请输入客户经理编号"/>
						<label for="custManagerName" class="pdTop text-right"><span class="star">*</span>客户经理名称</label>
						<input type="text" class="input-medium" name="custManagerName" id="custManagerName" value="${apply.custManagerName}" readonly valid="required" placeholder="请输入客户经理名称"/>
					</div>
					<div class="row-fluid">
						<label for="deptName" class="pdTop text-right">客户经理部门</label>
						<input type="text" class="input-medium" name="deptName" id="deptName" value="${apply.deptName}" placeholder="请输入客户经理部门" readonly="readonly"/>
					</div>
				</fieldset>
				<fieldset class="field">
					<legend>批次信息</legend>
					<div class="row-fluid">
						<label for="prodType" class="pdTop text-right"><span class="star">*</span>票据类型</label>
						<t:dictSelect  name="billType" onchange="getProdType();" className="select-medium" other="" dictGroup="K_BILL_TYPE" defaultVal="${apply.billType}" valid="required">
						</t:dictSelect> 
						<label for="prodType" class="pdTop text-right"><span class="star">*</span>票据种类</label>
						<input value="纸票" valid="required" readonly type="text" class="input-medium"></input> 
						<input name="billClass" value="${apply.billClass}" valid="required" type="hidden"></input> 
					</div>
					<div class="row-fluid">
						<label for="prodType" class="pdTop text-right"><span class="star">*</span>产品类型</label>
						<t:select other="" className="select-medium" id="prodNo" name="prodNo" headerKey='产品类型' headerValue='-1' list="prodList" listKey="prodName" listValue="prodNo" defaultVal="${apply.prodNo}"  onchange="showBack();"></t:select>
						<label for="rebuyDt" class="pdTop text-right"><span class="star">*</span>转入日</label>
						<input type="text" class="input-medium input-date" name="rebuyDt" id="rebuyDt" valid="required dateISO" value="${apply.rebuyDt}" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"/> 
					</div>
					<div class="row-fluid hide" id="buyback">
						<label for="resaleStaDt" class="pdTop text-right"><span class="star">*</span>返售开放日</label>
						<input name="resaleStaDt" id="resaleStaDt" value="${apply.resaleStaDt}" type="text" class="input-medium input-date" valid="required dateISO" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"/>
						<label for="resaleDueDt" class="pdTop text-right"><span class="star">*</span>返售到期日</label>
						<input name="resaleDueDt" id="resaleDueDt" value="${apply.resaleDueDt}" type="text" class="input-medium input-date" valid="required dateISO" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"/>
					</div>
					<div class="row-fluid">
						<label for="acctType" class="pdTop text-right"><span class="star">*</span>入账账号类型</label>
						<input type="text" class="input-medium" value="结算账号" placeholder=""/>
						<input type="hidden" name="acctType" id="acctType" value="${apply.tradeAcctType}" placeholder=""/>
					</div>
					<div class="row-fluid">
						<label for="tradeAcct" class="pdTop text-right"><span class="star">*</span>入账账号</label>
						<input type="text" class="input-medium" <c:if test="${isedit=='1'}"> readonly="readonly"</c:if> name="tradeAcct" id="tradeAcct" valid="required" value="${apply.tradeAcct}" placeholder="请输入入账账号" />
						<label for="tradeAcctName" class="pdTop text-right"><span class="star">*</span>账户名称</label>
						<input type="text" class="input-medium" name="tradeAcctName" id="tradeAcctName" valid="required" value="${apply.tradeAcctName}" placeholder="" readonly/>
					</div>
					<div class="row-fluid">
						<label for="rate" class="pdTop text-right"><span class="star">*</span>利率</label>
						<input type="text" class="input-medium" name="rate" id="rate" value="${apply.rate}" valid="required" onblur="checkRate();"/>
						<label for="cbRate" class="pdTop text-right">成本利率</label>
						<input type="text" class="input-medium" name="cbRate" id="cbRate" value="${apply.cbRate}" valid="required" onblur="checkCbRate();" placeholder=""/>
					</div>
					</fieldset>
					<div class="row-fluid">
						<div class="center save">
							<a type="submit" id="savePage" class="btn-mini" onclick="save()">提交</a>
							<input type="hidden" name="isedit" value="${isedit}"></input>
							<input type="hidden" name="rebuyId" value="${apply.rebuyId}"></input>
						</div>
					</div>
			    </form>
		    </div>
    	</div>
<%@ include file="/webpage/system/admin/modalDialog.jsp"%>	
<%@ include file="/webpage/system/admin/footer.jsp"%>
<script>
	$(document).ready(function(){
		var isedit = '${isedit}';
		var type = '${type}';
		if(isedit=='0'){
			//页面加载完毕产品名称是没有下拉选项的
			$("#prodNo").html("<option value=''>请选择产品</option>");
		}
		if(isedit == '1'){
			//若为回购，显示返售开放日和返售到期日
			if(type == '1'){
				$("#buyback").addClass("hide");
			}else{
				$("#buyback").removeClass("hide");
			}
		}
		$("#rebuyDt").val("${workday}");
		$("#resaleStaDt").val("${resaleStaDt}");
	})
	//提交
	function save(){
		if($("#batchForm").valid()){
			if(!checkProdNo()){
				return;
			}
			if(!checkRate()){
				return;
			}
			if(!checkCbRate()){
				return;
			}
			var rebuyDt = $("#rebuyDt").val();
			var workday = "${workday}";
			if(workday!=rebuyDt){
				bootbox.confirm('转入日不是当前营业日,是否继续?',function(result){  
		            if(result) {  
		            	modal("#layer_loading,#image");
		            	batchForm.submit();
		    			$("#zhongxin").hide();
		            }
		        })
			}else{
				modal("#layer_loading,#image");
				batchForm.submit();
				$("#zhongxin").hide();
			}
		}
	}
	//校验转入日
	/* function checkRebuyDt(){
		var rebuyDt = $("#rebuyDt").val();
		var workday = "${workday}";
		if(workday!=rebuyDt){
			bootbox.confirm('转入日不是当前营业日,是否继续?',function(result){  
	            if(result) {  
	            	$("#batchForm").submit();
	            }
	        })
		}else{
			return true;
		}
	} */
	//根据票据类型获取产品类型列表
	function getProdType(){
		var billType=$("#billType").val();
		$.ajax({
			url:"<%=basePath%>rebuyApplyController.do?method=getProdType",
			type:"POST",
			data:{"billType":billType},
			dataType:"json",
			success:function(data){
				var prodHtml = "<option selected value='-1'>请选择产品类型</option>";
				if(data.success){
					for(var i=0;i<data.obj.length;i++){
						var product = data.obj[i];
						prodHtml+="<option value='"+product.prodNo+"'";
						prodHtml+=">"+product.prodName+"</option>";
					}
					$("#prodNo").empty();
					$("#prodNo").append(prodHtml);
				}else{
					bootbox.alert(data.msg);
				}
			}
		});
	}	
	/**根据prodNo判断是否回购式，若为回购式则显示返售开放日和截止日字段，否则隐藏*/
	function showBack(){
		var prodNo = $("#prodNo").val();
		$.ajax({
			url:"<%=basePath%>rebuyApplyController.do?method=getBuyType",
			type:"POST",
			data:{"prodNo":prodNo},
			dataType:"json",
			success:function(data){
				if(data.success){
					if(data.obj=='1'){
						$("#buyback").removeClass("hide");
					}else{
						$("#buyback").addClass("hide");
					}
				}else{
					bootbox.alert(data.msg);
				}
			}
		});
	}	
	//根据客户经理编号填充客户经理信息
	$("#custManage").blur(function(){
		fillCustManager();
	})
	function fillCustManager(){
		var custManage = $("#custManage").val();
		if(custManage == null || custManage == ""){
			showTips("custManage","请输入客户经理编号");
			$("#deptName").val("");
			$("#custManagerName").val("");
	   		return false;
		}
		$.ajax({
			type: "POST",
			url: "<%=basePath%>rebuyApplyController.do?method=custManagerInfo",
	    	data: {'custManagerNo': custManage},
			dataType:'json',
			cache: false,
			success: function(data){	
				if(data.success){  //处理成功
					$("#custManage").val(data.attributes.custManage);
					$("#deptName").val(data.attributes.deptName);
					$("#custManagerName").val(data.attributes.custManagerName);
				}else{
					$("#deptName").val("");
					$("#custManagerName").val("");
					bootbox.alert(data.msg);
				}
			}
		});
	}
	//根据账号填充账户信息
	$("#tradeAcct").blur(function(){
		fillAcctInfo();
	})
	function fillAcctInfo(){
		var custAcctNo = $('#tradeAcct').val();
		$.ajax({
			type: "POST",
			url: "<%=basePath%>custInfoController.do?method=custInfo",
	    	data: {'custAcctNo': custAcctNo},
			dataType:'json',
			beforeSend:function(){
				if(custAcctNo==null||custAcctNo==""){
					showTips("tradeAcct","请输入入账账号");
					$("#tradeAcct").val("");
					$("#tradeAcctName").val("");
			   		return false;
				}
			},
			cache: false,
			success: function(data){	
				if(data.success){  //处理成功
					$("#tradeAcct").val(data.attributes.custAcctNo);
					$("#tradeAcctName").val(data.attributes.custName);
				}else{
					$("#tradeAcct").val("");
					$("#tradeAcctName").val("");
					bootbox.alert(data.msg);
				}
			}
		});
	}
	//校验转入利率
	function checkRate(){
		var rate = $("#rate").val();
		var result = validateData(rate);
		if(rate == null || $.trim(rate) == "") {
			showTips("rate","这是必填字段");
			return false;
		}
		if(result != 1 ){
			showTips("rate","利率"+result);
			return false;
		}
		return true;
	}
	//校验成本利率
	function checkCbRate(){
		var cbRate = $("#cbRate").val();
		var result = validateData(cbRate);
		if(cbRate == null || $.trim(cbRate) == "") {
			showTips("cbRate","这是必填字段");
			return false;
		}
  	    if(result != 1 ){
  	   		showTips("cbRate","利率"+result);
  	   		return false;
  	    }
  	    return true;
	}
	//校验利率
  	//成功返回1，失败返回提示信息
  	function validateData(rate){
		var re1 =/^[0-9]+[\.]?[0-9]*$/;
		var msg = 1;
		if(!re1.test(rate)){
			msg = "只能为数字";
			return msg;
		}
		if(rate<0 || rate>100){
			msg = "利率应该大于等于0且小于等于100";
			return msg;
		}
		//校验精度
  		var array = rate.split(".",-1);
  		if(array.length > 2){
  		  msg = "格式不正确";
  		  return msg;
  		}
		if(array[0]!=null && $.trim(array[0])!=""){
			var intPart = array[0];
			if("0" == intPart.substring(0,1) && intPart.length > 1){
				msg = "格式不正确";
	  		  	return msg; 
			}
		 }
  		if(array.length==2 && array[1]!=null && $.trim(array[1])!=""){
  			if(array[1].length>4){
  				msg = "精确到小数点后4位";
  				return msg;
  			}
  		}
		return msg;	
  	}
	//校验产品类型
	function checkProdNo(){
		var prodNo = $("#prodNo").val();
		if(prodNo==null||prodNo==""||prodNo=="-1"){
			showTips("prodNo","请选择产品类型");
			return false;
		}
		return true;
	}
</script>
</body>
</html>