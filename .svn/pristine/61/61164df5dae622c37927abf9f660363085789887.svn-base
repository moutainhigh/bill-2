<%-- 
 * 文件名称: bbspProduct_edit.jsp
 * 系统名称: 票据管理平台
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: @version2.0.0.1
 * 开发人员: dq
 * 开发时间: 2016-7-12 下午04:28:22
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
			<form  action="bbspProductController.do?method=save" name="Form" id="Form" method="post" class="form-search">
				<input type="hidden" name="isedit" value="${isedit}"/>
				<div id="zhongxin">
					<div class="row-fluid" >
							<label for="prodName" class="text-right"><span class="star">*</span>产品名称</label>
							<input class="input-medium" type="text" class="form-control" name="prodName" id="prodName" placeholder="请输入产品名称" value="${bbspProduct.prodName}" valid="required"/>
							<label for="prodNo" class="text-right"><span class="star">*</span>产品编号</label>
							<input class="input-medium" type="text" class="form-control" name="prodNo" id="prodNo" placeholder="请输入产品编号" value="${bbspProduct.prodNo}" valid="required number"/>
					</div>
					<div class="row-fluid">
							<label for="createDate" class="text-right"><span class="star">*</span>开立时间</label>
							<input class="input-medium input-date" class="col-xs-12" name="createDate" id="createDate" value="${bbspProduct.createDate}"  type="text" placeholder="开立时间" valid="required dateISO" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});"/>
							<label for="prodStatus" class="text-right"><span class="star">*</span>启用标志</label>
							<t:dictSelect className="select-medium" name="prodStatus" other="" dictGroup="K_QYBZ" defaultVal="${bbspProduct.prodStatus}" haveHead="true"  valid="required">
							</t:dictSelect>
					</div>
					<div class="row-fluid">
							<label for="prodType" class="text-right"><span class="star">*</span>产品类型</label>
							<t:dictSelect className="select-medium" name="prodType" other="" dictGroup="K_PJCPLX" defaultVal="${bbspProduct.prodType}" haveHead="true"  valid="required">
							</t:dictSelect>
							<label for="createUserName" class="text-right"><span class="star">*</span>创建柜员名称</label>
							<input type="text" class="input-medium" name="createUserName" id="createUserName" placeholder="请输入创建柜员名称" value="${bbspProduct.createUserName}" valid="required" />
					</div>
					<div class="row-fluid" >
							<label for="createUserNo" class="text-right"><span class="star">*</span>创建柜员号</label>
							<input type="text" class="input-medium" name="createUserNo" id="createUserNo" placeholder="请输入创建柜员号" value="${bbspProduct.createUserNo}" valid="required number" />
							<label for="prodCode" class="text-right"><span class="star">*</span>产品编码</label>
							<input type="text" class="input-medium" name="prodCode" id="prodCode" placeholder="请输入产品编码" value="${bbspProduct.prodCode}" valid="required number" />
			        </div>
					<div class="row-fluid">
							<label for="branchsPower" class="text-right"><span class="star">*</span>开通标志</label>
							<t:dictSelect className="select-medium" name="branchsPower" other="" dictGroup="K_KTBZ" defaultVal="${bbspProduct.branchsPower}" haveHead="true"  valid="required">
							</t:dictSelect>
					</div>
					<div class="row-fluid save">
						<div class="center">
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
	//初始化页面控件
		var isedit = '${isedit}';
		if(isedit == '1'){
			document.getElementById("prodNo").readOnly = true;
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
	//判断产品编号是否存在
	function checkExist(){
		var prodNo = $("#prodNo").val();
		var url = "bbspProductController.do?method=checkExists&prodNo="+prodNo;
		$.ajax({
			url:url,
			type:"POST",
			dataType:"JSON",
			success: function(data){
  				if(!data.success){
					showTips("prodNo", "产品编号已存在");
					$("#prodNo").focus();
				}else{
					 modal("#layer_loading,#image");
					 Form.submit(); 
					$("#zhongxin").hide();
				} 
  			 }
  	    });
	} 
	//验证提交日期
  	function checkValid(newObj){
		var newStr=$("#"+newObj).val();
		var newReg=/^(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))-02-29)$/;
		if(!newReg.test(newStr) || newStr.length>10){
			$("#" + newObj).tips({
				side : 3,
				msg : "请输入YYYY-MM-DD格式的日期",
				bg : "#AE81FF",
				time : 1
			})
		}
		return true;
	} 
</script>
</body>
</html>