<%-- 
 * 文件名称: syserrmsg_edit.jsp
 * 系统名称: 票据管理平台
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: @version2.0.0.1
 * 开发人员: dq
 * 开发时间: 2016-7-11 下午04:28:22
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
		<%@ include file="/webpage/system/admin/top.jsp"%>
	</head>
<body>
	<div class="clearfix">
		<div  class="page-content" id="jump-content">
			<form  action="subcollApplyController.do?method=advancedQuery" name="Form" id="Form" method="post" class="form-search">
				<div id="zhongxin">
					<div class="row-fluid" >
						<label for="billNo" class="text-right">票号</label>
						<input type="text" class="input-medium" name="billNo" id="billNo" placeholder="请输入票号" value="${billNo}"/>
					</div>
					<div class="row-fluid">
						<label for="acceptor" class="text-right">承兑人</label>
						<input type="text" class="input-medium" name="acceptor" id="acceptor"  placeholder="请输入承兑人" value="${acceptor}" />
					</div>
					<div class="row-fluid">
						<label for="smallMoney" class="text-right">最小金额</label>
						<input type="text" class="input-medium" name="smallMoney" id="smallMoney" placeholder="0.00~1,000,000,000" value="${smallMoney}" />
					</div>
					<div class="row-fluid">
						<label for="bigMoney" class="text-right">最大金额</label>
						<input type="text" class="input-medium" name="bigMoney" id="bigMoney" placeholder="0.00~1,000,000,000" value="${bigMoney}" />
					</div>
					<div class="row-fluid">
						<div class="center save">
							<a class="btn-mini" onclick="query();">查询</a> 
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
//高级查询
function query(){
	var billNo = $("#billNo").val();
	var acceptor = $("#acceptor").val();
	var smallMoney = $("#smallMoney").val();
	var bigMoney = $("#bigMoney").val();
	var No = /^(\w{0,16}|d*)$/;
	var Money =/^(\d{0,10}(\.\d{0,2})?|d*)$/;
	var name=/^([\u4E00-\u9FFF]+|d*)$/;
	if(!No.test(billNo)){
		bootbox.alert("票号最多可输入16位");
		return;
    }else if(!Money.test(smallMoney)){
		bootbox.alert("最小金额只能输入数字且小数点后只能输入两位");
		return;
   	}else if(!Money.test(bigMoney)){
		bootbox.alert("最大金额只能输入数字且只能输入十亿以下的金额");
		return;
    } else if(!name.test(acceptor)){
		bootbox.alert("请在输入承兑人时使用文字");
		return;
	}else{
		modal("#layer_loading,#image");//点击票据管理按钮，弹出‘加载中’的遮罩层
		$("#subManage").submit();
	}
	$.ajax({
		url:"<%=basePath%>subcollApplyController.do?method=valueToJump",
		type:"POST",
		dataType:"JSON",
		success: function(data){
			if(data.success){
				top.Dialog.close();
			} else{
				
			}
		}
 	});
}
</script>
</body>
</html>