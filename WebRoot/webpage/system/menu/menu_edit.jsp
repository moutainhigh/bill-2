<%-- 
 * 文件名称: menu_edit.jsp
 * 系统名称: 票据管理平台
 * 模块名称:		菜单编辑
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: @version2.0.0.1
 * 开发人员: songzx
 * 开发时间: 2016-7-17 上午08:28:22
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
			<form  action="menuController.do?method=save" name="Form" id="Form" method="post" class="form-search">
				<input type="hidden" name="isedit" value="${isedit}"/>
				<div id="zhongxin">
					<div class="row-fluid" >
						<label for="menuName" class="text-right"><span class="star">*</span>菜单名称</label>
						<input type="text" class="input-medium" name="menuName" id="menuName" placeholder="请输入菜单名称" value="${menu.menuName}" valid="required"/>
						<label for="menuType" class="text-right"><span class="star">*</span>菜单类型</label>
						<t:dictSelect className="select-medium" other="" valid="required" name="menuType" dictGroup="K_MENUTYPE" defaultVal="0" haveHead="true">
						</t:dictSelect>
					</div>
					<div class="row-fluid" >
						<label for="menuUrl" class="text-right"><span class="star">*</span>菜单URL</label>
						<input type="text" class="input-medium" name="menuUrl" id="menuUrl" placeholder="请输入菜单URL" value="${menu.menuUrl}"/>
						<label for="menuIcon" class="text-right" ><span class="star">*</span>菜单图标</label>
						<sys:iconselect id="menuIcon" name="menuIcon" value="${menu.menuIcon}"/>
				    </div>
					<div class="row-fluid" >
						<label for="orderNo" class="text-right"><span class="star">*</span>菜单排序</label>
						<input type="text" class="input-medium" name="orderNo" id="orderNo" valid="required" placeholder="请输入菜单排序（数字型）" value="${menu.orderNo}" valid="number"/>
						<label for="parentMenuCode" class="text-right"><span class="star">*</span>上级菜单</label>
						<sys:treeselect className="input-medium" id="menu" name="parentMenuCode" isedit="${edit}" value="${menu.parentMenuCode}" module="1" labelName="parent.menuName" labelValue="${menu.parentName}"
						title="菜单" placeholder="请选择上级菜单" url="tagController.do?method=treeData" extId="${menu.menuCode}"  valid="required"/>
					</div>
					<div class="row-fluid" >
						<label for="openFlag" class="text-right"><span class="star">*</span>是否展开</label>
						<t:dictSelect className="select-medium" other="" valid="required" name="openFlag" dictGroup="K_YORN" defaultVal="0" haveHead="true">
						</t:dictSelect>
						<label for="logonFlag" class="text-right"><span class="star">*</span>是否登录校验</label>			
						<c:if test="${isedit=='0'}">
							<t:dictSelect className="select-medium"  name="logonFlag" other="" dictGroup="K_YORN" defaultVal="1" haveHead="true" valid="required" disabled="true"></t:dictSelect> 
						</c:if>
						<c:if test="${isedit=='1'}">
							<t:dictSelect className="select-medium" name="logonFlag" other="" dictGroup="K_YORN" defaultVal="${menu.logonFlag}" haveHead="true" valid="required" ></t:dictSelect>
						</c:if>
					</div>
					<div class="row-fluid" >
						<label for="iconDisplay" class="text-right"><span class="star">*</span>图标展现模式</label>
						<t:dictSelect className="select-medium" other="" valid="required" name="iconDisplay" dictGroup="K_YORN" defaultVal="${menu.iconDisplay}" haveHead="true">
						</t:dictSelect>
						<label for="remark" class="text-right"><span class="star">*</span>备注</label>
						<input type="text" class="input-medium" valid="required" name="remark" id="remark" placeholder="请输入备注" value="${menu.remark}"/>
					</div>
					<div class="row-fluid">
						<div class="center save">
							<input type="hidden" name="menuLevel" id="menuLevel" value="${menu.menuLevel}"/>
							<input type="hidden" name="parentName" id="parentName" value="${parentName}"/>
							<input type="hidden" name="logonFlag" id="logonFlag" value="${logonFlag}"/>
							<input type="hidden" name="treeIdx" id="treeIdx" value="${menu.treeIdx}"/>
							<input type="hidden" name="menuClass" id="menuClass" value="${menu.menuClass}"/>
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
		document.getElementById("menuCode").readOnly = true;
	}
	var parentName = '${parentName}';
	if(parentName == '1'){
		$(".parentMenu").hide();
	}
	//保存
	function save(){
		if($("#Form").valid()){
		if($('#menuIcon').val()==" "||$('#menuIcon').val()==""){
			showTips("menuIcon","请选择图标");
		}else{
			if (isedit == '1'){
				$("#Form").submit();
				$("#zhongxin").hide();
				modal("#layer_loading,#image");
			} else {
				checkExist();
				
			}
		  }
		}
	}
	//判断编码是否存在
	function checkExist(){
		var menuCode = $("#menuCode").val();
		var url = "menuController.do?method=checkExists&menuCode="+menuCode;
		$.ajax({
			url:url,
			type:"POST",
			dataType:"JSON",
			success: function(data){
  				if(!data.success){
					showTips("menuCode", "角色编码已存在");
					$("#menuCode").focus();
				
				} else{
					modal("#layer_loading,#image");
					$("#Form").submit();
					$("#zhongxin").hide();
				}
  			}
  		});
	}
	function toMenuTree(){
		 var diag = new top.Dialog();
		 diag.Drag = true;
		 diag.Title ="新增";
		 diag.URL = "<%=basePath%>menuController.do?method=toMenuTree";
		 diag.Width = 450;
		 diag.Height = 480;
		 diag.CancelEvent = function(){ //关闭事件
			 if (diag.innerFrame.contentWindow.document.getElementById('zhongxin').style.display == 'none'){
				Form.action="<%=basePath%>menuController.do?method=toMenuTree";
				Form.submit();
			}
			 diag.close();
		 };
		 diag.show(); 
	}
</script>
</body>
</html>