<%-- 
 * 文件名称: duePayment_edit.jsp
 * 系统名称: 票据管理平台
 * 模块名称: 纸票到期付款
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: @version2.0.0.1
 * 开发人员: wuzhichen
 * 开发时间: 2016-9-13 上午06:28:22
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
			<form  action="acptAccountController.do?method=paymentSave" name="Form" id="Form" method="post" class="form-search">
				<input type="hidden" name="isedit" value="${isedit}"/>
				<input type="hidden" name="acptmxId" value="${query.acptmxId}"/>
				<input type="hidden" name="billNo" value="${query.billNo}"/>
				<div id="zhongxin">
					<c:if test="${isedit=='0'}">
				        <div class="row-fluid" >
							<label for="payeeName" class="text-right">收款人名称</label>
							<input type="text" class="input-medium" class="input-medium" name="payeeName" id="payeeName" placeholder="请输入收款人名称" value="${obj.payeeName}" />
						</div>
						<div class="row-fluid" >
							<label for="payeeBankName" class="text-right">收款行行名</label>
							<input type="text" class="input-medium" class="input-medium" name="payeeBankName" id="payeeBankName" placeholder="请输入收款行行名" value="${obj.payeeBankName}" />
						</div>
				        <div class="row-fluid" >
							<label  for="payeeBankNo" class="text-right">收款行行号</label>
							<input  name="payeeBankNo" id="payeeBankNo"  type="text" class="input-medium" placeholder="请输入收款行行号" value="${obj.payeeBankNo}"/>
						</div>
						<div class="row-fluid" >
							<label for="payeeAcct" class="text-right">收款人账号</label>
							<input  name="payeeAcct" id="payeeAcct"  type="text" class="input-medium" placeholder="收款行账号" value="${obj.payeeAcct}"/>
						</div>
					    <div class="row-fluid" >
							<label for="custRemark" class="text-right">客户附言</label>
							<textarea  name="custRemark" id="custRemark" placeholder="客户附言" /></textarea>
						</div>
			       		<div class="row-fluid" >
							<label for="bankRemark" class="text-right">银行附言</label>
							<textarea  name="bankRemark" id="bankRemark" placeholder="银行附言" /></textarea>
						</div>
					 </c:if>
					 <c:if test="${isedit=='1'}">
				     	<div class="row-fluid" >
							<label for="payeeName" class="text-right">收款人名称</label>
							<input type="text" class="input-medium" class="input-medium" name="payeeName" id="payeeName" placeholder="请输入收款人名称" value="${obj.payeeName}" />
						</div>
						<div class="row-fluid">
							<label for="payeeBankName" class="text-right">收款行行名</label>
							<input type="text" class="input-medium" class="input-medium" name="payeeBankName" id="payeeBankName" placeholder="请输入收款行行名" value="${obj.payeeBankName}" />
						</div>
				        <div class="row-fluid">
							<label  for="payeeBankNo" class="text-right">收款行行号</label>
							<input  name="payeeBankNo" id="payeeBankNo"  type="text" class="input-medium" placeholder="请输入收款行行号" value="${obj.payeeBankNo}"/>
						</div>
						<div class="row-fluid">
							<label for="payeeAcctNo" class="text-right">收款行账号</label>
							<input  name="payeeAcctNo" id="payeeAcctNo"  type="text" class="input-medium" placeholder="收款行账号" value="${obj.payeeAcctNo}"/>
						</div>
					    <div class="row-fluid" >
							<label for="custRemark" class="text-right">客户附言</label>
							<textarea  name="custRemark" id="custRemark" placeholder="客户附言" value="${obj.custRemark}"/></textarea>
						</div>
				        <div class="row-fluid" >
							<label for="bankRemark" class="text-right">银行附言</label>
							<textarea  name="bankRemark" id="bankRemark" placeholder="银行附言" value="${obj.bankRemark}"/></textarea>
						</div>
					    <div class="row-fluid" >
							<label for="rejectReason" class="text-right">拒绝原因</label>
							<textarea  name="rejectReason" id="rejectReason" placeholder="拒付原因"   maxLength="20" valid="required"  onblur="rejectReasonCheck();"/></textarea>
						</div>
						<div class="row-fluid" >
							<label for="rejectCode" class="text-right">拒绝代码</label>
							<t:dictSelect name="rejectCode" className="select-medium" other="" dictGroup="K_REJECTCODE" defaultVal="${obj.rejectCode}" haveHead="true"  title="票据类型"  valid="required" >
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
							<a class="btn-mini" onclick="save();">保存</a>
							<a class="btn-mini" onclick="top.Dialog.close();">取消</a>
						</div>
					</div>
				</div>
			</form>
		 </div>
	</div>			
<%@ include file="/webpage/system/admin/modalDialog.jsp"%>
<%@ include file="/webpage/system/admin/footer.jsp"%> 
<script type="text/javascript">
	document.getElementById("billNo").readOnly = true;
	//保存
	function save(){
		if($("#Form").valid()){
			modal("#layer_loading,#image");//点击票据管理按钮，弹出‘加载中’的遮罩层
			$("#Form").submit();
			$("#zhongxin").hide();
			 pageForm.action = "<%=basePath%>acptAccountController.do?method=paymentSave";
		}
	}	
</script>
</body>
</html>