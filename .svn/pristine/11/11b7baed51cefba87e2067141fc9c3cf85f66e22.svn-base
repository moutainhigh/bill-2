<%-- 
 * 文件名称: signProd_edit.jsp
 * 系统名称: 票据管理平台
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: @version2.0.0.1
 * 开发人员: dq
 * 开发时间: 2016-7-21 下午04:28:22
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
<body>
	<div class="clearfix">
		<div  class="page-content" id="jump-content">
			<form  action="signProdController.do?method=save" name="Form" id="Form" method="post" class="form-search">
				<input type="hidden" name="isedit" value="${isedit}"/>
				<input type="hidden" name="id" value="${signProd.id}"/>
				<div id="zhongxin">
					<div class="row-fluid" >
						<label for="busSettleAcct" class="text-right"><span class="star">*</span>客户账号</label>
						<input type="text" class="input-medium" name="busSettleAcct" id="busSettleAcct" placeholder="请输入客户账号" value="${signProd.busSettleAcct}" valid="required number" />
			        </div>
					<div class="row-fluid" >
						<label for="custName" class="text-right"><span class="star">*</span>客户名称</label>
						<input type="text" class="input-medium" name="custName" id="custName" placeholder="请输入客户名称" value="${signProd.custName}" readonly="readonly" valid="required"/>
					</div>
					<div class="row-fluid">
						<label for="custNo" class="text-right"><span class="star">*</span>客户号</label>
						<input type="text" class="input-medium" name="custNo" id="custNo" placeholder="请输入客户号" value="${signProd.custNo}"  readonly="readonly" valid="required number"/>
					</div>
					<div class="row-fluid">
						<label for="prodNo" class="text-right"><span class="star">*</span>服务产品编号</label>
						<t:dictSelect className="select-medium" name="prodNo" dictGroup="K_PRDTYPE"  other="" defaultVal="${signProd.prodNo}" onchange="changed();" haveHead="true" valid="required">
						</t:dictSelect>
					</div>
					<div class="row-fluid">
						<label for="signStatusCd" class="text-right"><span class="star">*</span>签约状态</label>
						<c:if test="${isedit=='0'}">
							<t:dictSelect className="select-medium"  name="signStatusCd" other="" dictGroup="K_SIGNSTATUS" defaultVal="01" haveHead="true" valid="required" disabled="true"></t:dictSelect> 
							<input type="hidden" name="signStatusCd" id="signStatusCd" value="01"/>
						</c:if>
						<c:if test="${isedit=='1'}">
							<t:dictSelect className="select-medium" name="signStatusCd" other="" dictGroup="K_SIGNSTATUS" defaultVal="${signProd.signStatusCd}" haveHead="true" valid="required" ></t:dictSelect>
						</c:if>
					</div>
					<div class="row-fluid" >
						<label for="creditInfo" class="text-right">评级信息</label>
						<input type="text" class="input-medium" name="creditInfo" id="creditInfo" placeholder="请输入评级信息" value="${signProd.creditInfo}"/>
					</div>
					<div class="row-fluid" >
						<label for="creditAgency" class="text-right">评级机构</label>
						<input type="text" class="input-medium" name="creditAgency" id="creditAgency" placeholder="请输入评级机构" value="${signProd.creditAgency}"/>
					</div>
					<div class="row-fluid">
						<label for="creditDueDt" class="text-right">评级有效期</label>
						<input class="input-medium input-date" name="creditDueDt" id="creditDueDt" value="${signProd.creditDueDt}"  type="text" placeholder="评级有效期" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"/>
					</div>
					<div class="row-fluid cms">
						<label for="cmsFlag" class="text-right"><span class="star">*</span>票据池开通标志</label>
						<t:dictSelect className="select-medium" name="cmsFlag" dictGroup="K_OPENFLAG"  other="" defaultVal="${signProd.cmsFlag}" onchange="changed();" haveHead="true" valid="required">
						</t:dictSelect>
					</div>
					<div class="row-fluid mar" >
						<label for="marginAccount" class="text-right">保证金账号</label>
						<input type="text" class="input-medium" name="marginAccount" id="marginAccount" placeholder="请输入保证金账号" value="${signProd.marginAccount}"/>
					</div>
					<div class="row-fluid mar">
						<label for="discountProportion" class="text-right">打折比例</label>
						<input type="text" class="input-medium" name="discountProportion" id="discountProportion" placeholder="请输入打折比例" value="${signProd.discountProportion}" onblur="discountProportionCheck();"/>（%）
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
	//根据账户填充账户名称
	$('#busSettleAcct').blur(function(){
		custInfo();
	})
	function custInfo(){ //根据客户账号查询客户信息并填充
			var busSettleAcct = $('#busSettleAcct').val();
			$.ajax({
				type: "POST",
				url: "<%=basePath%>custInfoController.do?method=custInfo",
		    	data: {'custAcctNo': busSettleAcct},
				dataType:'json',
				beforeSend:function(){
					if(busSettleAcct==null||busSettleAcct==""){
						showTips("busSettleAcct","请输入客户账号");
						$("#custName").val("");
						$("#custNo").val("");
				   		return false;
					}
				},
				cache: false,
				success: function(data){	
					if(data.success){  //处理成功
						$("#custName").val(data.attributes.custName);
						$("#custNo").val(data.attributes.custNo);
					}else{
						$("#custName").val("");
						$("#custNo").val("");
						showTips("busSettleAcct",data.msg);
					}
				}
			});
		}
	var isedit = '${isedit}';
	if(isedit == '1'){
		changed();//是否带出票据池开通标志和保证金账号
		document.getElementById("busSettleAcct").readOnly = true;
		document.getElementById("prodNo").disabled = true;
		document.getElementById("signStatusCd").disabled = false;
	}
	$(document).ready(function(){
		$("#Form").validate({
			onfocusout:
			function(element){
				$(element).valid();
			}
		});
	});
	//保存
	function save(){
		if($("#Form").valid()){
			if (isedit == '1'){
				Form.submit();
				$("#zhongxin").hide();
				modal("#layer_loading,#image");
			} else {
				checkExist();
			}
		}
	}
	  //判断客户账号是否存在
	function checkExist(){
		var busSettleAcct = $("#busSettleAcct").val();
		var prodNo=$("#prodNo").val();
		var url = "signProdController.do?method=checkExists&busSettleAcct="+busSettleAcct+"&prodNo="+prodNo;
		$.ajax({
			url:url,
			type:"POST",
			dataType:"JSON",
			success: function(data){
  				if(!data.success){
					showTips("busSettleAcct", data.msg);
					$("#busSettleAcct").focus();
				}else{
					modal("#layer_loading,#image");
					Form.submit(); 
					$("#zhongxin").hide();
				} 
  			}
  		});
	} 
	//带出票据池开通标志和保证金账号
	function changed(){
		var prodNo = $("#prodNo").val();
		var cmsFlag = $("#cmsFlag").val();
		var marginAccount = $("#marginAccount").val();
		if(prodNo=="S511001001"){
			$(".cms").show();
			$(".mar").show();
			if(cmsFlag=="0"){
				$(".mar").hide();
			}else{
				$(".mar").show();
			}
		}else{
			$(".cms").hide();
			$(".mar").hide();
		}
	}
	//验证打折比例
	function discountProportionCheck(){
		var discountProportion = $("#discountProportion").val();
		var discountProportionValidate =/^\d+(\.\d{1,5})?$/;
		if(discountProportion==""||discountProportion==null||discountProportion=='-1')
		{
			return -1;
		}else{
			if(!discountProportionValidate.exec(discountProportion)) {
				showTips("discountProportion","请输入正确比例格式");
				return;
			}else
			if(discountProportion>100||discountProportion<0){
			showTips("discountProportion","输入比例应在0~100之间");
				return;
			}else
			if(discountProportion.length>7){
				showTips("discountProportion","请输入正确比例格式");
				return;
			}else{
				return -1;
			}
		}
	}		
</script>
</body>
</html>