<%-- 
 * 文件名称: collate_elec_modify_add_bill.jsp
 * 系统名称: 票据管理平台
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: @version2.0.0.1
 * 开发人员: v
 * 开发时间: 2016-8-10
 * 审核人员:
 * 相关文档:
 * 修改记录: 修改日期    修改人员    修改说明
 --%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/webpage/system/admin/taglib.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
  <head>
    <base href="<%=basePath%>">
    <meta charset="UTF-8">
	<title>票据管理页面</title>
	<%@ include file="/webpage/system/admin/top.jsp"%>
</head>
<body>
	<div class="clearfix">
		<div id="page-content" class="page-content">
			<%-- 按钮操作区 --%>
			<div id="center">
				<form action="<%=basePath%>collateralizationApplyController.do?method=elecAddBill"  method="post" id="elecReviewForm" name="elecReviewForm" style="padding:0px;margin-bottom:0px;" class="form-search">
					<div class="row-fluid">
					   <div class="span6" id="btn-left">
							<a class="btn-mini" onclick="add();">新增</a>
							<a class="btn-mini" onclick="goHistory();">返回</a>
					   </div>
				   </div>
			   </form>
			</div>
			<div id="footer" class="footer">
				<table class="table table-striped table-bordered table-hover" id="tab" cellpadding="0" cellspacing="0">
					<thead>
						<tr>
							<th class="center" onclick="selectAll('zcheckbox', 'ids')">
								<input type="checkbox" id="zcheckbox" /><span class="lbl"></span>
							</th>
							<th class="center">票号</th>
							<th class="center">票据类型</th>
							<th class="center">出票日</th>
							<th class="center">票面到期日</th>
							<th class="center">票面金额</th>
							<th class="center">承兑人</th>
							<th class="center">出票人</th>
							<th class="center">详情</th>
						</tr>
					</thead>
					<tbody>
						<c:choose>
							<c:when test="${not empty resultList}">
								<c:forEach items="${resultList}" var="save" varStatus="vs">
									<tr>
										<td class="center"><input type="checkbox" name="ids" value="${save.savemxId}" onclick="getall('ids')" price="${save.billMoney}"/></td>
										<td class="center">${save.billNo}</td>
										<td class="center">${fns:getDictLabel('K_BILL_TYPE',save.billType)}</td>
										<td class="center">${save.issueDt}</td>
										<td class="center">${save.dueDt}</td>
										<td class="center">${fns:formateMoney(save.billMoney)}</td>
										<td class="center">${save.acceptor}</td>
										<td class="center">${save.remitter}</td>
										<td class="center"><a href="javascript:goBillDetail('${save.rgctId}')">查看</a></td>
									</tr>
								</c:forEach>
							</c:when>
							<c:otherwise>
								<tr>
									<td colspan="100" class="center">没有相关数据</td>
								</tr>
							</c:otherwise>
						</c:choose>
					</tbody>
				</table>
			</div>
			<div id="select-Info">
				<div id="selectInfo"><center>暂时没有相关数据</center></div>
			</div>
			<%-- /列表操作区 --%>
			<form  action="#" name="dataCollectForm" method="post"></form>
				<div id="page" class="pagination">
					<form action="<%=basePath%>collateralizationApplyController.do?method=elecAddBill"  name="pageForm" method="post" >
						<%@ include file="/webpage/system/admin/pageFormContent.jsp"%>
						<input type="hidden" id="billType" name="billType" value="${query.billType}"/>
						<input type="hidden" id="custNo" name="custNo" value="${custNo}"/>
						<input type="hidden" id="saveIds" name="saveIds" value="${saveIds}"/>
					</form>
				</div>
			</div>
		</div>
<%@ include file="/webpage/system/admin/modalDialog.jsp"%>
<%@ include file="/webpage/system/admin/footer.jsp"%>
<script>
	function goHistory(){
	    dynamicHiddenElement('dataCollectForm','custNo1',$('#custNo').val());
		dynamicHiddenElement('dataCollectForm','saveId',$('#saveIds').val());
		modal("#layer_loading,#image");
		dataCollectForm.action ="<%=basePath%>collateralizationApplyController.do?method=elecModifyApplyBillList";
		dataCollectForm.submit();
	}
	//详情页面
	function goBillDetail(rgctId){
		var diag = new top.Dialog();
		 diag.Drag = true;
		 diag.Title ="详情";
		 diag.URL = "<%=basePath%>elecBillDetailController.do?method=goDetail&rgctId="+rgctId;
		 diag.Width = 930;
		 diag.Height = 575;
		 diag.CancelEvent = function(){ //关闭事件
			diag.close();
		 };
		 diag.show(); 
	}
	//增加
	function add(){
	    var checkNum = getCheckNum("ids");
	   	 if (checkNum == 0){
	   		bootbox.alert("请先选择要新增的记录!");
	   		return;
	   	 }
	   	var ids = getCheckStr("ids");
	   	var saveId = "${saveIds}";
	   	var custNo = "${custNo}";
	   	modal("#layer_loading,#image");//点击票据管理按钮，弹出‘加载中’的遮罩层
	    $.ajax({
			type: "POST",
			url: '<%=basePath%>collateralizationApplyController.do?method=elecModifyAddBill',
			data: {'savemxId': ids,'saveId': saveId,'custNo': custNo},
			dataType:'json',
			//beforeSend: validateData,
			cache: false,
			success: function(data){	
				if (data.success){  //处理成功
					dynamicHiddenElement('dataCollectForm','custNo',$('#custNo').val());
			        dynamicHiddenElement('dataCollectForm','saveId',saveId);
			        modal("#layer_loading,#image");
			        dataCollectForm.action ="<%=basePath%>collateralizationApplyController.do?method=elecModifyApplyBillList";
			        dataCollectForm.submit();
				} else {
					top.hangge();
					bootbox.alert('新增失败!');
				}
			}
		});
	}
</script>
</body>
</html>