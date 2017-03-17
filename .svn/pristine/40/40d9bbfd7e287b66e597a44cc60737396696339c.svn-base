<%-- 
 * 文件名称: elecDuePayment_edit.jsp
 * 系统名称: 票据管理平台
 * 模块名称: 电票到期付款
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: @version2.0.0.1
 * 开发人员: wuzhichen
 * 开发时间: 2016-10-08 下午04:28:22
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
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<%@ include file="/webpage/system/admin/top.jsp"%> 
	</head>
<body>
	<div class="clearfix">
		<div  class="page-content" id="jump-content">
				<form  action="acptAccountController.do?method=elecPaymentSave" name="Form" id="Form" method="post" class="form-search">
					<input type="hidden" name="isedit" value="${isedit}"/>
					<input type="hidden" name="acptmxId" value="${query.acptmxId}"/>
					<input type="hidden" name="billNo" value="${query.billNo}"/>
					<div id="zhongxin">
						<c:if test="${isedit=='0'}">
					       <div class="row-fluid" >
								<label for="payeeName" class="text-right">收款人名称</label>
								<input type="text" class="input-medium" name="payeeName" id="payeeName" placeholder="请输入收款人名称" value="${obj.payeeName}" readonly="readonly"/>
							</div>
						   <div class="row-fluid" >
								<label for="payeeBankName" class="text-right">收款行行名</label>
								<input type="text" class="input-medium" name="payeeBankName" id="payeeBankName" placeholder="请输入收款行行名" value="${obj.payeeBankName}" readonly="readonly"/>
							</div>
					       <div class="row-fluid" >
								<label  for="payeeBankNo" class="text-right">收款行行号</label>
								<input  name="payeeBankNo" id="payeeBankNo" type="text" class="input-medium" placeholder="请输入收款行行号" value="${obj.payeeBankNo}" readonly="readonly"/>
							</div>
							<div class="row-fluid" >
								<label for="payeeAcct" class="text-right">收款人账号</label>
								<input  name="payeeAcct" id="payeeAcct"  type="text" class="input-medium" placeholder="收款行账号" value="${obj.payeeAcct}" readonly="readonly"/>
								</div>
						    <div class="row-fluid">
								<label for="custRemark" class="text-right">客户附言</label>
								<textarea  name="custRemark" id="custRemark" class="input-medium" placeholder="客户附言" value="${custRemark}" /></textarea>
								</div>
					        <div class="row-fluid">
								<label for="bankRemark" class="text-right">银行附言</label>
								<textarea  name="bankRemark" id="bankRemark" class="input-medium" placeholder="银行附言" value="${bankRemark}"/></textarea>
							</div>
						</c:if>
						<c:if test="${isedit=='1'}">
					    	<div class="row-fluid" >
								<label for="payeeName" class="text-right">收款人户名</label>
								<input type="text" class="input-medium" name="payeeName" id="payeeName" placeholder="请输入收款人户名" value="${obj.payeeName}" readonly="readonly" />
							</div>
							<div class="row-fluid" >
								<label for="payeeBankName" class="text-right">收款行行名</label>
								<input type="text" class="input-medium" name="payeeBankName" id="payeeBankName" placeholder="请输入收款行行名" value="${obj.payeeBankName}" readonly="readonly"/>
							</div>
					        <div class="row-fluid" >
								<label for="payeeBankNo" class="text-right">收款行行号</label>
								<input name="payeeBankNo" id="payeeBankNo"  type="text" class="input-medium" placeholder="请输入收款行行号" value="${obj.payeeBankNo}" readonly="readonly"/>
							</div>
							<div class="row-fluid" >	
								<label for="payeeAcct" class="text-right">收款行账号</label>
								<input  name="payeeAcct" id="payeeAcct"  type="text" class="input-medium" placeholder="收款行账号" value="${obj.payeeAcct}" readonly="readonly"/>
							</div>
						    <div class="row-fluid" >
								<label for="custRemark" class="text-right">客户附言</label>
								<textarea   name="custRemark" id="custRemark" class="input-medium" placeholder="客户附言" value="${custRemark}"/></textarea>
							</div>
					        <div class="row-fluid" >
								<label for="bankRemark" class="text-right">银行附言</label>
								<textarea  name="bankRemark" id="bankRemark" class="input-medium" placeholder="银行附言" value="${bankRemark}"/></textarea>
							</div>
						    <div class="row-fluid" >
								<label for="rejectReason" class="text-right">拒绝原因</label>
								<textarea  name="rejectReason" id="rejectReason" class="input-medium" placeholder="拒付原因" value="${rejectReason}" maxLength="20" valid="required"  onblur="rejectReasonCheck();"/></textarea>
							</div>
						    <div class="row-fluid" >
								<label for="rejectCode" class="text-right">拒绝代码</label>
								<t:dictSelect name="rejectCode" className="select-medium" other="" dictGroup="K_REJECTCODE" defaultVal="${rejectCode}" haveHead="true"  title="票据类型"  valid="required" >
								</t:dictSelect>
							</div>
						<div style="margin-left:35px;">
						    <div style="color:red;">注意:</div>
						    <ul style="list-style:none;">
							    <li>DC00  与自己有直接债权债务关系的持票人未履行约定义务；</li>
							    <li>DC01  持票人以欺诈、偷盗或者胁迫等手段取得票据；</li>
							    <li>DC02  持票人明知有欺诈、偷盗或者胁迫等情形，出于恶意取得票据；</li>
							    <li>DC03  持票人明知债务人与出票人或者持票人的前手之间存在抗辩事由而取得票据；</li>
							    <li>DC04  持票人因重大过失取得不符合《票据法》规定的票据；</li>
							    <li>DC05  超过提示付款期；</li>
							    <li>DC06  被法院冻结或收到法院止付通知书；</li>
							    <li>DC07  票据未到期；</li>
							    <li>DC08  商业承兑汇票承兑人账户余额不足;</li>
							    <li>DC09  其他（必须注明）;</li>
						    </ul>
              			</div>	    
						</c:if>
						<div class="row-fluid">
							<div class="center save">
								<a class="btn-mini" onclick="save();">登记</a>
								<a class="btn-mini" onclick="top.Dialog.close();">取消</a>
							</div>
						</div>
					</div>
				</form>
				<form  action="#" name="dataCollectForm" method="post"></form>
			  </div>
		</div>			
<%@ include file="/webpage/system/admin/modalDialog.jsp"%>
<%@ include file="/webpage/system/admin/footer.jsp"%> 
<script type="text/javascript">
	document.getElementById("billNo").readOnly = true;
	$(top.hangge());
	//保存
	function save(){
		if($("#Form").valid()){
			var custRemark = $("#custRemark").val();
			var bankRemark = $("#bankRemark").val();
			var rejectReason = $("#rejectReason").val();
			var rejectCode = $("#rejectCode").val();
			var acptmx_ids = "${acptmx_ids}";
			var isedit = "${isedit}";
			dynamicHiddenElement('dataCollectForm','custRemark',custRemark);
			dynamicHiddenElement('dataCollectForm','bankRemark',bankRemark);
			dynamicHiddenElement('dataCollectForm','rejectReason',rejectReason);
			dynamicHiddenElement('dataCollectForm','rejectCode',rejectCode);
			dynamicHiddenElement('dataCollectForm','acptmx_ids',acptmx_ids);
			dynamicHiddenElement('dataCollectForm','isedit',isedit);
			modal("#layer_loading,#image");//点击票据管理按钮，弹出‘加载中’的遮罩层
			dataCollectForm.action = "<%=basePath%>acptAccountController.do?method=elecPaymentSave";
			dataCollectForm.submit();
		}
	}
	//拒付原因校验
	function rejectReasonCheck(){
		var rejectReason = $("#rejectReason").val();
	    var No = /^([\w\s\S\u4e00-\u9fa5]{0,20}|d*)$/; 
	  	if(!No.test(rejectReason)){
			showTips("rejectReason","拒付原因最多为20位字符");
			return;
		}
	}
</script>
</body>
</html>