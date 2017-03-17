<%-- 
 * 文件名称: reportOfLossAndSp_edit.jsp
 * 系统名称: 票据管理平台
 * 模块名称:
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
		<link rel="stylesheet" href="weblib/bizcss/public.css">	
	</head>

<body>
	<div class="clearfix">
		<div  class="page-content" id="jump-content">
			<form  action="acptAccountController.do?method=save" name="Form" id="Form" method="post" class="form-search">
				<input type="hidden" name="isedit" value="${isedit}"/>
				<input type="hidden" name="acptmxId" value="${query.acptmxId}"/>
				<div id="zhongxin">
					<div class="row-fluid" >
						<label for="bill_no" class="text-right">票号</label>
						<input type="text" class="input-medium" class="input-medium" name="billNo" id="billNo"  value="${query.billNo}"/>
						<label for="remark" class="text-right">备注</label>
						<input type="text" class="input-medium" class="input-medium" name="remark" id="remark" placeholder="请输入备注" value="${obj.remark}"/>
					</div>
					<c:if test="${isedit=='0'}">
						 <div class="row-fluid" >
						    <label for="sspd_acct" class="text-right">挂失人账号</label>
							<input type="text" class="input-medium" class="input-medium" name="sspdAcct" id="sspdAcct" placeholder="请输入挂失人账号" value="${obj.sspdAcct}" title="挂失人账号" valid="required"/>
							<label for="sspd_name" class="text-right">挂失人名称</label>
							<input type="text" class="input-medium" class="input-medium" name="sspdName" id="sspdName" placeholder="请输入挂失人名称" value="${obj.sspdName}" title="挂失人名称" valid="required"/>
						</div>
						<div class="row-fluid" >
							<label for="sspd_dt" class="text-right">挂失日期</label>
							<input onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});" name="sspdDt" id="sspdDt"  type="text" class="input-medium input-date" placeholder="挂失日期" value="${obj.sspdDt}"/>
							<label for="is_pub_exhort" class="text-right">是否办理公示催告</label>
							<t:dictSelect name="isPubExhort" className="select-medium" other="" dictGroup="K_YORN" defaultVal="${obj.isPubExhort}" haveHead="true"  tips="是否办理公示催告" valid="required" >
							</t:dictSelect>
					     </div>
					  </c:if>
					  <c:if test="${isedit=='1'}">
						 <div class="row-fluid">
							<label for="anlg_sspd_acct" class="text-right">解挂人账号</label>
							<input type="text" class="input-medium" class="input-medium" name="anlgSspdAcct" id="anlgSspdAcct" value="${obj.anlgSspdAcct}" placeholder="请输入解挂人账号"  title="解挂人账号" valid="required"/>
							<label for="anlg_sspd_name" class="text-right">解挂人名称</label>
							<input type="text" class="input-medium" class="input-medium" name="anlgSspdName" id="anlgSspdName"  value="${obj.anlgSspdName}" placeholder="请输入解挂人名称"  title="解挂人名称" valid="required" />
						</div>
						<div class="row-fluid" >
							<label for="anlg_sspd_dt" class="text-right">解挂日期</label>
							<input onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});" name="anlgSspdDt" id="anlgSspdDt"  type="text" class="input-medium input-date" placeholder="解挂日期"  value="${obj.anlgSspdDt}"/>
							<label for="is_anlg_notification" class="text-right">是否有解挂通知书</label>
							<t:dictSelect name="isAnlgNotification" className="select-medium" other="" dictGroup="K_YORN" defaultVal="${obj.isAnlgNotification}" haveHead="true"  tips="是否有解挂通知书" valid="required" >
							</t:dictSelect>
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
			 pageForm.action = "<%=basePath%>acptAccountController.do?method=reportOfLossAndSp";
		}
	}
</script>
</body>
</html>