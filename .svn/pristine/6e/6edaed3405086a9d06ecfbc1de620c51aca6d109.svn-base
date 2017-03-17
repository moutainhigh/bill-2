<%-- 
 * 文件名称: auditProd_edit.jsp
 * 系统名称: 票据管理平台
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: @version2.0.0.1
 * 开发人员: fanqq
 * 开发时间: 2016-9-28 上午09:28:22
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
			<form  action="auditProdController.do?method=saveAuditProd" name="Form" id="Form" method="post" class="form-search">
				<input type="hidden" name="isedit" value="${isedit}"/>
				<input type="hidden" name="id" value="${reArProd.id}"/>
				<div id="zhongxin">
				<div class="row-fluid">
						<label for="param_value" class="pdTop"><span class="star">*</span>机构名称</label>
						<sys:treeselect className="input-medium" id="branchNo" name="branchNo"  isedit="0" value="${reArProd.branchNo}" module="2" labelName="机构名称"  labelValue="${reArProd.branchNo}" title="机构" url="tagController.do?method=treeData"   valid="required"></sys:treeselect>
					</div>
					<div class="row-fluid">
						<label for="param_value" class="text-right"><span class="star">*</span>产品编号</label>
						<sys:treeselect className="input-medium" id="prodNo" name="prodNo" value="${reArProd.prodNo}" module="0" labelName="reArProd.prodName" labelValue="${reArProd.prodNo}"
							title="产品" url="tagController.do?method=treeData" extId="${menu.menuCode}"  subnode="prod_no"  pnode = "parent_prod_no" nodename="prod_name" sourcename="tproduct"/>
					</div>
					<div class="row-fluid">
						<label class="text-right" placeholder="请选择是否需要审批" for="custAcctNo"><span class="star">*</span>是否需要审批</label>
						<t:dictSelect className="select-medium" name="auditFlag" valid="required" other="" defaultVal="${reArProd.auditFlag}" dictGroup="K_YORN"></t:dictSelect>
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
	$(document).ready(function(){
	   $("#Form").validate({
	      onfocusout:function(element){
	      		$(element).valid();
	      }
	   });
	});
	//保存
	var isedit='${isedit}';
	function save(){
      	if($("#Form").valid()){                                                                         
			if (isedit == '1'){
				$("#Form").submit();
				$("#zhongxin").hide();
				modal("#layer_loading,#image");
			} else {
				$("#Form").submit();
			}
	  	}
	}
</script>
</body>
</html>