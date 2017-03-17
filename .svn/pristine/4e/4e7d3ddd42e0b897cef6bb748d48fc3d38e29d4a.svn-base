<%-- 
 * 文件名称: custinfoacct_edit.jsp
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
<body>
	<div class="clearfix">
		<div  class="page-content" id="jump-content">
			<form  action="custInfoAcctController.do?method=save" name="Form" id="Form" method="post" class="form-search">
				<input type="hidden" name="isedit" value="${isedit}"/>
				<div id="zhongxin">
					<div class="row-fluid" >
						<label for="cust_no" class="text-right"><span class="star">*</span>客户号</label>
						<input type="text" class="input-medium" name="cust_no" id="cust_no" placeholder="请输入客户号" value="${custinfoacctparam.custNo}" valid="required"/>
					</div>
					<div class="row-fluid">
						<label for="acct_no" class="text-right"><span class="star">*</span>客户帐号</label>
						<input type="text" class="input-medium" name="acct_no" id="acct_no" placeholder="请输入客户帐号" value="${custinfoacctparam.acctNo}" valid="required"/>
					</div>
					<div class="row-fluid">
						<label for="acct_type" class="text-right"><span class="star">*</span>账户类型</label>
						<t:dictSelect className="select-medium" name="acct_type" other="" dictGroup="K_ZHLX" defaultVal="${custinfoacctparam.acctType}" haveHead="true"  valid="required">
						</t:dictSelect>
					</div>
					<input type="hidden" name="open_date" id="open_date" value="${custinfoacctparam.openDate}"/>
					<input type="hidden" name="open_time" id="open_time" value="${custinfoacctparam.openTime}"/>
					<input type="hidden" class="input-medium" name="acct_branch_name" id="acct_branch_name" placeholder="请输入账户开户机构名" value="${custinfoacctparam.acctBranchName}" readonly="true" valid="required"/>
					<div class="row-fluid">
						<label for="acct_branch_no" class="text-right"><span class="star">*</span>账户开户机构</label>
						<sys:treeselect className="input-medium" id="acct_branch_no" name="acct_branch_no"  isedit="0" fullAcctBankNo="1" value="${custinfoacctparam.acctBranchNo}" module="2" labelName="机构名称"  labelValue="${custinfoacctparam.acctBranchName}" title="机构" url="tagController.do?method=treeData"   valid="required"></sys:treeselect>
					</div>
					<div class="row-fluid">
						<label for="acct_bank_no" class="text-right"><span class="star">*</span>开户行行号</label>
						<input type="text" class="input-medium" name="acct_bank_no" id="acct_bank_no" placeholder="请输入开户行行号" value="${custinfoacctparam.acctBankNo}" readonly="true" valid="required" />
					</div>
					<div class="row-fluid">
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
		document.getElementById("acct_no").readOnly = true;
	}
	function rfillbybranch(){ //根据用户输入机构获取开户行行号
		var acctBranchNo = $('#acct_branch_no').val();
		$.ajax({
			type: "POST",
			url: "<%=basePath%>branchController.do?method=branchInfo",
	    	data: {'branchNo': acctBranchNo},
			dataType:'json',
			cache: false,
			success: function(data){	
				if(data.success){  //处理成功
					$("#acct_branch_name").val(data.attributes.branchName);
					$("#acct_bank_no").val(data.attributes.bankNo);
					//fillBatch(1,5);
				}else{
					$("#acct_branch_name").val("");
					$("#acct_bank_no").val("");
					bootbox.alert(data.msg);
					$(".modal").css({"left":"77%","width":"400px"});
				}
			}
		});
	}
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
	//判断客户号是否存在
	$("#cust_no").blur(function(){
		checkCustNo();
	})
	function checkCustNo(){
		var id = $("#acct_no").val();
		var ids = $("#cust_no").val();
		var url = "custInfoAcctController.do?method=checkExists&id="+id+"&ids="+ids;
		$.ajax({
			url:url,
			type:"POST",
			dataType:"JSON",
			success: function(data){
  				if(!data.success){
  					if(data.attributes.checkCustNo==1){
						showTips("cust_no", "该客户号不在企业客户中无法创建成功");
						//$("#cust_no").focus();
  					}
				}
  			}
  		});
	}
	//判断编码是否存在
	function checkExist(){
		var id = $("#acct_no").val();
		var ids = $("#cust_no").val();
		var url = "custInfoAcctController.do?method=checkExists&id="+id+"&ids="+ids;
		$.ajax({
			url:url,
			type:"POST",
			dataType:"JSON",
			success: function(data){
  				if(!data.success){
  					if(data.attributes.checkCustNo==1){
					showTips("cust_no", "该客户号不在企业客户中无法创建成功");
					$("#cust_no").focus();
  					}
  					if(data.attributes.checkCustAcctNo==1){
				    showTips("acct_no", "该客户帐号已存在,请重新填写");
					$("#acct_no").focus();
  					}
				} else{
					modal("#layer_loading,#image");
					$("#Form").submit();
					$("#zhongxin").hide();
				}
  			 }
  		});
	}
</script>		
</body>
</html>