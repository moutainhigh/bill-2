<%-- 
 * 文件名称: custinfo_edit.jsp
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
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="t" uri="/WEB-INF/tld/poseui.tld"%>
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
			<form  action="custInfoController.do?method=save" name="Form" id="Form" method="post" class="form-search">
				<input type="hidden" name="isedit" value="${isedit}"/>
				<div id="zhongxin">
					<div class="row-fluid" >
							<label for="cust_name" class="text-right"><span class="star">*</span>客户名称</label>
							<input type="text" class="input-medium" name="cust_name" id="cust_name" placeholder="请输入客户名称" value="${custinfoparam.custName}" valid="required"/>
							<label for="cust_type" class="text-right"><span class="star">*</span>客户类型</label>
							<t:dictSelect className="select-medium" name="cust_type" other="" dictGroup="K_CUSTTYPE" defaultVal="2" haveHead="true" valid="required" disabled="true"></t:dictSelect>
							<input type="hidden" name="cust_type" id="cust_type"  value="2" />
					</div>
					<div class="row-fluid">
							<label for="cust_no" class="text-right"><span class="star">*</span>客户号</label>
							<input type="text" class="input-medium" name="cust_no" id="cust_no" placeholder="请输入客户号" value="${custinfoparam.custNo}" onblur="fill();" valid="required"/>
							<label for="org_code" class="text-right"><span class="star">*</span>组织机构代码</label>
							<input type="text" class="input-medium" name="org_code" id="org_code" onblur="orgcodevalidate();" placeholder="请输入组织机构代码" value="${custinfoparam.orgCode}" valid="required"/>
					</div>
					<div class="row-fluid">
							<label for="org_code" class="text-right">标记</label>
							<t:dictSelect  className="select-medium" name="flag" other="" dictGroup="K_KHZHZH" defaultVal="0" haveHead="true" valid="required" disabled="true"></t:dictSelect>
							<input type="hidden" name="flag" id="flag"  value="0" />
							<label for="partner_type" class="text-right"><span class="star">*</span>参与者类别</label>
							<t:dictSelect  className="select-medium" name="partner_type" other="" dictGroup="K_BUSSINESS_ROLE" defaultVal="RC01" haveHead="true" valid="required" disabled="true"></t:dictSelect>
						    <input type="hidden" name="partner_type" id="partner_type"  value="RC01" />
					</div>
					<div class="row-fluid">
							<label for="address" class="text-right">地址</label>
							<input type="text" class="input-medium" name="address" id="address" placeholder="请输入地址" value="${custinfoparam.address}"/>
							<label for="credit_agency" class="text-right">评级机构</label>
							<input type="text" class="input-medium" name="credit_agency" id="credit_agency" placeholder="请输入评级机构" value="${custinfoparam.creditAgency}" />
					</div>
					<div class="row-fluid">
							<label for="credit_duedt" class="text-right">评级到期日</label>
							<input type="text" class="input-medium" name="credit_duedt" id="credit_duedt" placeholder="请输入评级到期日" value="${custinfoparam.creditDuedt}"/>
							<label for="credit_rate" class="text-right">信用等级</label>
							<input type="text" class="input-medium" name="credit_rate" id="credit_rate" placeholder="请输入信用等级" value="${custinfoparam.creditRate}"/>
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
	 $(document).ready(function() {  
  		$("#Form").validate({
  			 onfocusout: function(element){
        		$(element).valid();
    		 }
  		});  
	}); 
	var isedit = '${isedit}';
	if(isedit == '1'){
		document.getElementById("cust_no").readOnly = true;
	}
	//保存
	function save(){
		if($("#Form").valid()){
			var str=$("#org_code").val();
	    	if(str!=""){
 				var reg = /^[A-Z0-9]{8}-[A-Z0-9]$/;   
 				if (reg.test(str)) {  
 					if (isedit == '1'){
					$("#Form").submit();
					$("#zhongxin").hide();
					modal("#layer_loading,#image");
					} else {
						checkExist();
					}
 				}else{
 					$("#org_code").tips({
						side : 3,
						msg :"组织机构代码输入不规范", 
						bg : '#AE81FF',
						time : 5
 						});
 					}
 				}
			
			}
		}
	//判断编码是否存在
	function checkExist(){
		var id = $("#cust_no").val();
		var url = "custInfoController.do?method=checkExists&id="+id;
		$.ajax({
			url:url,
			type:"POST",
			dataType:"JSON",
			success: function(data){
  				if(!data.success){
					showTips("cust_no", "客户号已存在");
					$("#cust_no").focus();
				} else{
					modal("#layer_loading,#image");
					$("#Form").submit();
					$("#zhongxin").hide();
				}
  			}
  		});
	}
	//判断组织机构代码是否规范
	function orgcodevalidate(){
		var str=$("#org_code").val();
	    if(str!=""){
 			var reg = /^[A-Z0-9]{8}-[A-Z0-9]$/;   
 			if (reg.test(str)) {  
 				return true ; 
 			}else{
 				$("#org_code").tips({
					side : 5,
					msg :"组织机构代码输入不规范", 
					bg : '#AE81FF',
					time : 2
 				});
 			}
 		}
    }
</script>			
</body>
</html>