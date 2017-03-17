<%-- 
 * 文件名称: sale_apply_batch_edit.jsp
 * 系统名称: 票据管理平台
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 批次编辑页面
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
			<form action="saleApplyController.do?method=save" method="POST"
				name="Form" id="Form" class="form-search" role="form">
				<input type="hidden" name="isedit" value="${isedit}" /> 
				<input type="hidden" name="saleId" value="${saleApplyInfo.saleId }"/>
				<input type="hidden" name="batchNo" value="${saleApplyInfo.batchNo }"/>
				<fieldset class="field">
		   			<legend>客户经理信息</legend>
					<div class="row-fluid">
						<label for="custManage" class="pdTop"><span class="star">*</span>客户经理编号</label>
						<input type="text" class="input-medium" name="custManage" id="custManage" value="${saleApplyInfo.custManage }" placeholder="请输入客户经理编号" valid="required" />
						<label for="custManagerName" class="pdTop"><span class="star">*</span>客户经理名称</label>
						<input type="text" class="input-medium" name="custManagerName" id="custManagerName"
								value="${saleApplyInfo.custManagerName }"
								readonly="readonly" valid="required" />
					</div>
					<div class="row-fluid">
						<label for="deptName" class="pdTop"><span class="star">*</span>部门</label>
						<input type="text" class="input-medium" name="deptName" id="deptName"
								value="${saleApplyInfo.deptName }"
								readonly="readonly" valid="required" />
					</div>
				</fieldset>
				<fieldset class="field">
					<legend>客户信息（交易对手信息)</legend>
					<div class="row-fluid">
						<label for="custType" class="pdTop">客户类型</label>
						<t:dictSelect  name="custType" className="select-medium" other="" dictGroup="K_KHLX" defaultVal="${saleApplyInfo.custType}" haveHead="true" onchange="custTypeChanged();" valid="required"></t:dictSelect>
						<label for="custName" class="pdTop">客户名称</label>
						<input type="text" class="input-medium" name="custName" id="custName"
								value="${saleApplyInfo.custName}" placeholder="" valid="required" readonly="true"/>
					</div>
					<div class="row-fluid outer1">
						<div id="outer" class="outer1" style="display:inline;">
							<label for="aimBranchNo" class="pdTop"><span class="star">*</span>客户开户行行号</label>
							<input type="text" class="input-medium" name="aimBranchNo" id="aimBranchNo" value="${saleApplyInfo.aimBranchNo }" placeholder="请输入客户开户行行号" valid="required"/>
						</div>
						<div id="outer" class="outer1" style="display:inline;">
							<label for=" innerAccount" class="pdTop"><span class="star">*</span>客户开户行帐号</label>
							<input type="text" class="input-medium" name="innerAccount" id="innerAccount" value="${saleApplyInfo.innerAccount}" placeholder="" valid="required"/>
								<%--<span class="pull-left" style="margin:5px 0 0 -17px">交易对手直连行号默认为0，若为代理银行或代理财务公司请输入实际帐号</span>--%>
						</div>
					</div>
					<div class="row-fluid" id="inner" style="display:none;">
						<label for="branchNo" class="pdTop"><span class="star">*</span>客户机构号</label>
						<input type="text" class="input-medium" name="branchNo" id="branchNo" value="${saleApplyInfo.branchNo }" placeholder="" valid="required" onblur="branchInfoFill();"/>
					</div>
				</fieldset>
				<fieldset class="field">
					<legend>其他信息</legend>
					<div class="row-fluid">
						<label for="billClass" class="pdTop"><span class="star">*</span>票据品种</label>
						<t:dictSelect  name="billClass" className="select-medium" other="" dictGroup="K_BILL_CLASS" defaultVal="2" haveHead="true" valid="required" disabled="true"></t:dictSelect>
						<input type="hidden" name="billClass" id="billClass"  value="2" />
						<label for="billType" class="pdTop"><span class="star">*</span>票据类型</label>
						<c:if test="${isedit=='0'}">
							<t:dictSelect  name="billType" className="select-medium" other="" dictGroup="K_BILL_TYPE" defaultVal="${saleApplyInfo.billType }" haveHead="true" valid="required" ></t:dictSelect> 
						</c:if>
						<c:if test="${isedit=='1'}">
							<t:dictSelect  name="billType" className="select-medium" other="" dictGroup="K_BILL_TYPE" defaultVal="${saleApplyInfo.billType }" haveHead="true" valid="required" disabled="true"></t:dictSelect>
							<input type="hidden" name="billType" id="billType" value="${saleApplyInfo.billType }"/> 
						</c:if>
					</div>
					<div class="row-fluid">
						<label for="prodNo" class="pdTop"><span class="star">*</span>产品名称</label>
						<t:select id="prodNo" className="select-medium" other="" name="prodNo" list="prodList" listKey="prodName" listValue="prodNo" headerKey='产品类型' headerValue='-1' defaultVal="${saleApplyInfo.prodNo}" onchange="prodNoChanged()"></t:select>
						<label for="saleType" class="pdTop">转卖类型</label>
						<input type="text" class="input-medium" name="saleType" id="saleType" value="${saleApplyInfo.saleType }" placeholder="" readonly="readonly" valid="required"/>
					</div>
					<div class="row-fluid">
						<label for="saleDt" class="pdTop"><span class="star">*</span>转卖日</label>
						<input name="saleDt" id="saleDt" value ="${saleApplyInfo.saleDt}" valid="required dateISO" type="text" class="input-medium input-date" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});">
						<label for="rate" class="pdTop"><span class="star">*</span>利率</label>
						<input type="text" class="input-medium" name="rate" id="rate" value="${fns:formateRate(saleApplyInfo.rate)}" valid="required"  />
					</div>
					<div class="row-fluid outer1">
						<label for="inAcctType" class="pdTop"><span class="star">*</span>入账账号类型</label>
						<input type="text" class="input-medium" name="inAcctType" id="inAcctType" value="${saleApplyInfo.inAcctType}" readonly="readonly" valid="required" />
						<label for="inAcctNo" class="pdTop"><span class="star">*</span>入账账号</label>
						<input type="text" class="input-medium" name="inAcctNo" id="inAcctNo" value="${saleApplyInfo.inAcctNo }" placeholder="请输入入账账号" valid="required"/>
					</div>
					<div class="row-fluid outer1">
						<div class="outer1">
							<label for="inAcctName" class="pdTop">账户名称</label>
							<input type="text" class="input-medium" name="inAcctName" id="inAcctName"
									value="${saleApplyInfo.inAcctName }" placeholder="" readonly="readonly"/>
						</div>
					</div>
					<div class="row-fluid">
						<label for="isOnline" class="pdTop"><span class="star">*</span>清算方式</label>
						<c:if test="${isedit=='0'}">
							<t:dictSelect  name="isOnline" className="select-medium" other="" dictGroup="K_ISONLINE" defaultVal="0" valid="required" haveHead="true"></t:dictSelect>
						</c:if>
						<c:if test="${isedit=='1'}">
							<t:dictSelect  name="isOnline" className="select-medium" other="" dictGroup="K_ISONLINE" defaultVal="${saleApplyInfo.isOnline}" valid="required" haveHead="true"></t:dictSelect>
						</c:if>
						<label for="forbidFlag" class="pdTop"><span class="star">*</span>是否禁止背书</label>
						<c:if test="${isedit=='0'}">
							<t:dictSelect  name="forbidFlag" className="select-medium" other="" dictGroup="K_YORN" defaultVal="0" valid="required" haveHead="true"></t:dictSelect>
						</c:if>
						<c:if test="${isedit=='1'}">
							<t:dictSelect  name="forbidFlag" className="select-medium" other="" dictGroup="K_YORN" defaultVal="${saleApplyInfo.forbidFlag}" valid="required" haveHead="true"></t:dictSelect>
						</c:if>
					</div>
					<div class="row-fluid rebuyDt">
						<label for="buybackOpenDt" class="pdTop">赎回开放日</label>
						<input name="buybackOpenDt" id="buybackOpenDt" value ="${saleApplyInfo.buybackOpenDt}" valid="required dateISO" type="text" class="input-medium input-date" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});">
						<label for="rebuyDueDt" class="pdTop">赎回截止日</label>
						<input name="rebuyDueDt" id="rebuyDueDt" value ="${saleApplyInfo.rebuyDueDt}" valid="required dateISO" type="text" class="input-medium input-date" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});">
					</div>
				</fieldset>
				<div class="row-fluid save">
					<div class="center">
						<a id="savePage" class="btn-mini" onclick="save();">保存</a>
						<a class="btn-mini" onclick="top.Dialog.close();">取消</a>
					</div>
				</div>
			</form>
		</div>
	</div>
</body> 
<%@ include file="/webpage/system/admin/footer.jsp"%> 
<%@ include file="/webpage/system/admin/modalDialog.jsp"%>
<script type="text/javascript">
	var date="${dateTime}";
	$("#innerAccount").val('0');
	//保存
	var isedit = '${isedit}';
	if(isedit=='0'){
		$("#saleDt").val('${dateTime}');
	}
	$(function(){
		if(isedit=='1'){
			prodNoChanged();
			custTypeChanged();
		}
	});
	function prodNoChanged(){
		if(isedit!='1'){
			prodNo=$("#prodNo").find('option:selected').attr('value');
		}else{
			prodNo=$("#prodNo").val();
		}
		//getSaleTypeByProdNo(prodNo);
		var buybackOpenDt =  $("#buybackOpenDt").val();
		if(prodNo=='001003001'){
			$("#saleType").val('系统内卖断');
			$("#inAcctType").val('结算账户');
			$("#buybackOpenDt").val(' ');
			$(".rebuyDt").hide();
	 	}else if(prodNo=='001003002'){
	 		$("#saleType").val('同业间卖断');
	 		$("#inAcctType").val('结算账户');
	 		$("#buybackOpenDt").val(' ');
	 		$(".rebuyDt").hide();
	 	}else if(prodNo=='001003003'){
	 		$("#saleType").val('再贴现卖断');
	 		$("#inAcctType").val('内部账户');
	 		$("#buybackOpenDt").val(' ');
	 		$(".rebuyDt").hide();
		}else if(prodNo=='001003004'){
			$("#saleType").val('系统内回购式卖出');
			if(buybackOpenDt==null || buybackOpenDt==" " || buybackOpenDt==""){
				$("#buybackOpenDt").val('${nextDateTime}');
			}
			$("#inAcctType").val('结算账户');
			$(".rebuyDt").show();
		}else if(prodNo=='001003005'){
			$("#saleType").val('同业间回购式卖出');
			if(buybackOpenDt==null || buybackOpenDt==" "|| buybackOpenDt==""){
				$("#buybackOpenDt").val('${nextDateTime}');
			}
			$("#inAcctType").val('结算账户');
			$(".rebuyDt").show();
	 	}else if(prodNo=='001003006'){
	 		$("#saleType").val('回购式再贴现');
	 		if(buybackOpenDt==null || buybackOpenDt==" "|| buybackOpenDt==""){
				$("#buybackOpenDt").val('${nextDateTime}');
			}
	 		$("#inAcctType").val('内部账户');
	 		$(".rebuyDt").show();
		}
	}
	function custTypeChanged(){
		var custType = $("#custType").val();
		if(custType=="1"){
			$("#inner").hide();
			$(".outer1").show();
			$("#isOnline").removeAttr("disabled");
		}else if(custType=="2"){//系统内
			$(".outer1").hide();
			$("#inner").show();
			//系统内默认为线下清算
			$("#isOnline").attr("disabled",true);
			$("#isOnline").val("0");
		}
		$.ajax({
			url:"<%=basePath%>saleApplyController.do?method=searchProdinfo",
			type:"POST",
			data:{"custType":custType},
			dataType:"json",
			success:function(data){
				var prodHtml = "<option value='-1'>请选择产品</option>";
				if(data.success){
					for(var i=0;i<data.obj.length;i++){
						var product = data.obj[i];
						prodHtml+="<option value='"+product.prodNo+"'";
						prodHtml+=">"+product.prodName+"</option>";
					}
					$("#prodNo").empty();
					$("#prodNo").append(prodHtml);
					$("#prodNo").val("${saleApplyInfo.prodNo}");
				}else{
					bootbox.alert(data.msg);
				}
			}
		});
	}
	function save() {
		if($("#Form").valid()){
			//validDT("saleDt");
			//form.submit;
			var prodNo=$("#prodNo").val();
			var date="${dateTime}";
			var buyBackOpenDt=$("#buybackOpenDt").val();
 			var rebuyDueDt=$("#rebuyDueDt").val();
 			var saleDt=$("#saleDt").val();
			/* if(prodNo=='001003004'){
				validDT("buybackOpenDt");
				validDT("rebuyDueDt");
			}else if(prodNo=='001003005'){
				validDT("buybackOpenDt");
				validDT("rebuyDueDt");
		 	}else if(prodNo=='001003006'){
		 		validDT("buybackOpenDt");
				validDT("rebuyDueDt");
			} */
			if(saleDt<"${dateTime}")
	 			{
	 				showTips("saleDt","转卖日必须大于等于当前营业日");
	 				return;
	 			}
	 			if(buyBackOpenDt!=" "){
	 		   		 if(buyBackOpenDt<="${dateTime}")
	 				{
	 					showTips("buybackOpenDt","赎回开放日必须大于转卖日");
	 					return;
	 				}
	 				if(rebuyDueDt<buyBackOpenDt)
	 				{
	 					showTips("rebuyDueDt","赎回截止日必须大于赎回开放日");
						return;
	 				}
	 			}
			if(prodNo==null||prodNo==""||prodNo=="-1")
			{
				showTips("s2id_prodNo","请选择产品名称！！");
			}else{
				$("#isOnline").removeAttr("disabled");
				$("#Form").submit();
				modal("#layer_loading,#image");//点击票据管理按钮，弹出‘加载中’的遮罩层
				$("#Form").hide();
			}
		}
	}
	//根据机构号填充机构信息
	function branchInfoFill(){
		$("#custName").val("");
		var branchNo = $('#branchNo').val();
		$.ajax({
			type: "POST",
			url: "<%=basePath%>branchController.do?method=branchInfo",
	    	data: {'branchNo': branchNo},
			dataType:'json',
			beforeSend:function(){
				if(branchNo==null||branchNo==""){
					showTips("branchNo","请输入机构号");
					$("#custName").val("");
			   		return false;
				}
			},
			cache: false,
			success: function(data){	
				if(data.success){  //处理成功
					$("#custName").val(data.attributes.branchName);
					/*$("#deptName").val(data.obj.deptName);
					$("#custManagerName").val(data.obj.custManagerName);*/
				}else{
					$("#custName").val("");
					bootbox.alert(data.msg);
				}
			}
		});
	}
	//根据客户经理编号填充客户经理信息
	$('#custManage').blur(function(){
		custManageFill();
	})
	function custManageFill(){
		var custManage = $('#custManage').val();
		$.ajax({
			type: "POST",
			url: "<%=basePath%>custManagerController.do?method=custMagInfo",
	    	data: {'custManagerNo': custManage},
			dataType:'json',
			beforeSend:function(){
				if(custManage==null||custManage==""){
					showTips("custManage","请输入客户经理编号");
					$("#custManagerName").val("");
					$("#deptName").val("");
			   		return false;
				}
			},
			cache: false,
			success: function(data){	
				if(data.success){  //处理成功
					$("#deptName").val(data.attributes.deptName);
					$("#custManagerName").val(data.attributes.custManagerName);
					/*$("#deptName").val(data.obj.deptName);
					$("#custManagerName").val(data.obj.custManagerName);*/
				}else{
					$("#deptName").val("");
					$("#custManagerName").val("");
					bootbox.alert(data.msg);
				}
			}
		});
	}
	//根据交易对手机构号填充交易对手机构名称
	$('#aimBranchNo').blur(function(){
		custBankFill();
	})
	function custBankFill(){
		var aimBranchNo = $('#aimBranchNo').val();
		$.ajax({
			type: "POST",
			url: "<%=basePath%>saleApplyController.do?method=custBankInfo",
	    	data: {'bankNo': aimBranchNo},
			dataType:'json',
			beforeSend:function(){
				if(aimBranchNo==null||aimBranchNo==""){
					showTips("aimBranchNo","请输入客户开户行行号");
					$("#custName").val("");
			   		return false;
				}
			},
			cache: false,
			success: function(data){	
				if(data.success){  //处理成功
					$("#custName").val(data.obj.actorFullCall);
				}else{
					$("#custName").val("");
					bootbox.alert(data.msg);
				}
			}
		});
	}
	//根据账户填充账户名称
	$('#inAcctNo').blur(function(){
		custInfo();
	})
	function custInfo(){ //根据客户账号查询客户信息并填充
			var inAcctNo = $('#inAcctNo').val();
			$.ajax({
				type: "POST",
				url: "<%=basePath%>custInfoController.do?method=custInfo",
		    	data: {'custAcctNo': inAcctNo},
				dataType:'json',
				beforeSend:function(){
					if(inAcctNo==null||inAcctNo==""){
						showTips("inAcctNo","请输入入账账号");
						$("#inAcctName").val("");
				   		return false;
					}
				},
				cache: false,
				success: function(data){	
					if(data.success){  //处理成功
						$("#inAcctName").val(data.attributes.custName);
					}else{
						showTips("inAcctNo","请输入正确的入账账号");
						$("#inAcctNo").val("");
						$("#inAcctName").val("");
					}
				}
			});
		}
	//验证利率
	function validateData(){
		var rate = $("#rate").val();
		var re1 =/^[0-9]+[\.]?[0-9]*$/;
		var msg = 1;
		if(re1.test(rate)){
			if(rate.substring(0,1)=='0'&&(rate.length>1 && rate.substring(1,2)!='.')) {
	    		msg = "利率应大于等于0且小于等于100";
	    		showTips("rate",msg);
	    		return ;
    		} 
		}else{
			msg = "利率应大于等于0且小于等于100";
			showTips("rate",msg);
			return ;
		}
		if(rate<0 || rate>100){
			msg = "利率应大于等于0且小于等于100'";
			showTips("rate",msg);
			return ;
		}
		//校验精度
  		var array = rate.split(".",-1);
  		if(array.length>2){
  		  msg = "格式不正确";
  		showTips("rate",msg);
  		return ;
  		}else{
  			 if(array.length==2){
	  			if(array[1]!=null && array[1]!=""){
		  			if(array[1].length>4){
		  				msg = "精确到小数点后4位";
		  				showTips("rate",msg);
		  				return ;
		  				//return msg;
		  			}
  				}
  			}
  		}
  		return ;	
  	}
</script>
</html>