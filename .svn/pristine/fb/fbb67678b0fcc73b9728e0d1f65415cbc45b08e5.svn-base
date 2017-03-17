<%-- 
 * 文件名称: delayrule_edit.jsp
 * 系统名称: 票据管理平台
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: @version2.0.0.1
 * 开发人员: vvv
 * 开发时间: 2016-7-12
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
			<form  action="delayRuleController.do?method=save" name="Form" id="Form" method="post" class="form-search">
				<input type="hidden" name="isedit" value="${isedit}"/>
				<div id="zhongxin">
					<div class="row-fluid">
						<label for="id" class="text-right"><span class="star">*</span>编号</label>
						<input type="text" class="input-medium" name="id" id="id" placeholder="请输入编号" value="${delayrule.id}" valid="required"/>
					</div>
					<div class="row-fluid">
						<label for="prod_no" class="text-right"><span class="star">*</span>产品编号</label>
						<t:dictSelect className="select-medium" name="prod_no" other="" dictGroup="K_PRDTYPE" defaultVal="${delayrule.prodNo}" haveHead="true" valid="required">
						</t:dictSelect>
					</div>
					<div class="row-fluid">
						<label for="bill_class" class="text-right"><span class="star">*</span>票据分类</label>
						<input type="text" class="input-medium" name="bill_class" id="bill_class" placeholder="请输入票据分类" value="${delayrule.billClass}" valid="required"/>
					</div>
					<div class="row-fluid">
						<label for="bill_type" class="text-right"><span class="star">*</span>票据种类</label>
						<t:dictSelect className="select-medium" name="bill_type" other="" dictGroup="K_BILL_TYPE" defaultVal="${delayrule.billType}" haveHead="true" valid="required">
						</t:dictSelect>
					</div>
					<div class="row-fluid">
						<label for="delay_type" class="text-right"><span class="star">*</span>顺延规则</label>
						<t:dictSelect className="select-medium" name="delay_type" other="" dictGroup="K_SYFS" defaultVal="${delayrule.delayType}" haveHead="true"  valid="required">
						</t:dictSelect>
					</div>
					<div class="row-fluid" id="delay_days">
						<label for="delay_days" class="text-right"><span class="star">*</span>顺延天数</label>
						<input type="text" class="input-medium" name="delay_days" id="delay_days" placeholder="请输入顺延天数" value="${delayrule.delayDays}" valid="required"/>
					</div>
					<div class="row-fluid">
						<label for="oper_type" class="text-right"><span class="star">*</span>执行方式</label>
						<t:dictSelect className="select-medium" name="oper_type" other="" dictGroup="K_DELAYCZFS" defaultVal="${delayrule.operType}" haveHead="true" valid="required">
						</t:dictSelect>
					</div>
					<div class="row-fluid center">
						<div class="center save">
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
	var isedit = '${isedit}';
	if(isedit == '1'){
		document.getElementById("id").readOnly = true;
	}
	$("#delay_type").click(function(){
		var obj = document.getElementById("delay_type").value
		//alert(obj);
		if(obj == "5"){
		    document.getElementById("delay_days").style.display="block";
		}else{
		    document.getElementById("delay_days").style.display="none";
		}
	})
	$(document).ready(function(){
		var obj = document.getElementById("delay_type").value;
		if(obj == "5"){
		    document.getElementById("delay_days").style.display="block";
		}else{
		    document.getElementById("delay_days").style.display="none";
		}
	})
	//保存
	function save(){
		if($("#Form").valid()){
			if (isedit == '1'){
				$("#Form").submit();
				$("#zhongxin").hide();
				modal("#layer_loading,#image");
			} else {
				checkExist();
			}
		}
	}
	//判断编码是否存在
	function checkExist(){
		var id = $("#id").val();
		var url = "delayRuleController.do?method=checkExists&id="+id;
		$.ajax({
			url:url,
			type:"POST",
			dataType:"JSON",
			success: function(data){
  				if(!data.success){
					showTips("id", "编号已存在");
					$("#id").focus();
				} else{
					modal("#layer_loading,#image");
					$("#Form").submit();
					$("#zhongxin").hide();
				}
  			}
  		});
	}
	var isedit = '${isedit}';
	if(isedit == '1'){
		document.getElementById("id").readOnly = true;
	}
</script>	
</body>
</html>