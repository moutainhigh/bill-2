<%-- 
 * 文件名称: cust_balance_edit.jsp
 * 系统名称: 票据管理平台
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: @version2.0.0.1
 * 开发人员: cgc
 * 开发时间: 2016-7-7 上午06:28:22
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
		<%@ include file="../admin/top.jsp"%>
	</head>
<body style="font-family:'微软雅黑';background:#f4f8fb;">
<div class="clearfix">
<div  class="page-content" id="jump-content">
	<form  action="<%=basePath%>custLimitController.do?method=doSave" name="Form" id="Form" method="post" class="form-search">
		<input type="hidden" name="isedit" value="${isedit}"/>
		<div id="zhongxin">
		<div class="row-fluid" >
				<label for="cust_no" class="text-right"><span class="star">*</span>客户号</label>
				<input type="text" class="input-medium" name="custNo" id="custNo" placeholder="请输入客户号" value="${cb.custNo}" valid="required"/>
				<label for="cust_type" class="text-right"><span class="star">*</span>客户类型</label>
				<input type="text" class="input-medium" readonly="readonly" name="custTypeText" id="custTypeText" />
			</div>
			<div class="row-fluid">
				<label for="cust_name" class="text-right"><span class="star">*</span>客户名称</label>
				<input type="text" class="input-medium" name="custName" id="custName" placeholder="请输入客户名称" value="${cb.custName}" valid="required"/>
				<label for="loan_no" class="text-right"><span class="star">*</span>额度品种</label>
				<t:dictSelect className="select-medium" name="balanceClass" other="" dictGroup="K_EDPZ" defaultVal="${cb.balanceClass}" haveHead="true" valid="required">
				</t:dictSelect>
		</div>
		<div class="row-fluid">
				<label for="org_code" class="text-right"><span class="star">*</span>总金额</label>
				<input type="text" class="input-medium" name="totalBalance" id="totalBalance" placeholder="请输入总金额" value="${fns:double2String(cb.totalBalance)}" valid="required"/>
				<label for="org_code" class="text-right">可用金额</label>
				<input type="text" class="input-medium" name="ableBalance" readonly="readonly" id="ableBalance" value="${fns:double2String(cb.ableBalance)}" />
		</div>
		<div class="row-fluid">
				<label for="partner_type" class="text-right"><span class="star">*</span>已用金额</label>
				<input type="text" class="input-medium" name="usedBalance" readonly="readonly" id="usedBalance" value="${fns:double2String(cb.usedBalance)}" />
				<label for="address" class="text-right">有效日期</label>
				<input class="input-medium input-date" id="effectiveDate" name="effectiveDate" value="${cb.effectiveDate}" type="text" placeholder="请输入有效日期" valid="required dateISO" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});">
		</div>
		<div class="row-fluid">
				<label for="credit_agency" class="text-right">是否可循环利用</label>
					<c:if test="${cb.isCircle == 0}">
						<input type="hidden" name="isCircle" value="${cb.isCircle}" />
						<a type="text" class="form-control" readonly="readonly">否</a>
						<input type="text" class="input-medium" readonly="readonly" value="否" />
					</c:if>
					<c:if test="${cb.isCircle == 1}">
						<t:dictSelect className="select-medium" name="isCircle" other="" dictGroup="K_YORN" defaultVal="${cb.isCircle}" haveHead="true"  valid="required">
						</t:dictSelect>
					</c:if>
				<label for="credit_duedt" class="text-right">是否启用</label>
				<t:dictSelect className="select-medium" name="status" other="" dictGroup="K_YORN" defaultVal="${cb.status}" haveHead="true"  valid="required">
				</t:dictSelect>
		</div>
		<div class="row-fluid save">
			<div class="center" >
					<a class="btn-mini" onclick="save();">保存</a>
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
    //初始化页面控件
	$(function() {
		$('#totalBalance').on({
			keyup: function() {
				var totalBalance = parseFloat($('#totalBalance').val());
				var usedBalance = parseFloat($('#usedBalance').val());
				$('#ableBalance').val(totalBalance-usedBalance);
			}
		});
	  });
	 $(document).ready(function() {
  		$("#Form").validate({
  			 onfocusout: function(element){
        		$(element).valid();
    		 }
  		});  
	}); 
	var isedit = '${isedit}';
	if(isedit == '1'){
		document.getElementById("custNo").readOnly = true;
		if("${cb.custType}"=="1"){
			$("#custTypeText").val("企业客户");
		}else{
			$("#custTypeText").val("同业客户");
		}
		if("${cb.isCircle}"=="0"){
			document.getElementById("isCircle").readOnly = true;
		}
	}
	//保存
	function save(){
		if($("#Form").valid()){
			if (isedit== '1'){
				$("#Form").submit();
				modal("#layer_loading,#image");
			} else {
				checkExist();
			}
		}
	}
	//检查客户是否已经在余额表中存在
	function checkExist(){
		$.ajax({
				type: "POST",
				url: "<%=basePath%>custLimitController.do?method=checkExist",
		    	data: {'custNo': $("#custNo").val()},
				dataType:'json',
				success: function(data){	
					if(data.success){  //处理成功
						bootbox.alert(data.msg);
					}else{
						$("#Form").submit();
						modal("#layer_loading,#image");
					}
				}
			});
	}
	//根据客户号查询客户信息并填充
	$('#custNo').blur(function(){
		fill();
	})
		function fill(){ 
			var custNo = $('#custNo').val();
			$.ajax({
				type: "POST",
				url: "<%=basePath%>custLimitController.do?method=getCustInfo",
		    	data: {'custNo': custNo},
				dataType:'json',
				success: function(data){	
					if(data.success){  //处理成功
						$("#custType").val(data.obj.custType);
						if(data.obj.custType=="2"){
							$("#custTypeText").val("同业客户");
						}else{
							$("#custTypeText").val("企业客户");
						}
						$("#custName").val(data.obj.custName);
					}else{
						$("#custNo").val("");
						$("#custType").val("");
						$("#custName").val("");
						showTips("custNo",data.msg);
					}
				}
			});
		}
</script>			
</body>
</html>