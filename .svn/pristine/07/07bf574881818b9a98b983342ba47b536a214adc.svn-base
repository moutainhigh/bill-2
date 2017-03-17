<%-- 
 * 文件名称: disc_elec_apply_add_bill.jsp
 * 系统名称: 票据管理平台
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: @version2.0.0.1
 * 开发人员: cgc
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
				<form action="<%=basePath%>discApplyController.do?method=addElecBill"  method="post" id="elecReviewForm" name="elecReviewForm" class="form-search">
				<div class="row-fluid">
					   <div class="span6" id="btn-left">
							<a class="btn-mini" onclick="add();">新增</a>
							<a class="btn-mini" onclick="goHistory();">返回</a>
					   </div>
				  </div>
			   </form>
			</div>
			<div id="footer" class="footer">
				<table class="table table-striped table-bordered table-hover" id="tab" cellpadding="0" cellspacing="0" style="min-width:100%;max-width:2600px;width:2600px;">
					<thead>
						<tr>
							<th class="center" onclick="selectAll('zcheckbox', 'ids')">
								<input type="checkbox" id="zcheckbox" /><span class="lbl"></span>
							</th>
							<th class="center">票号</th>
							<th class="center">入账账号</th>
							<th class="center">入账行号</th>
							<th class="center">票据类型</th>
							<th class="center">出票日</th>
							<th class="center">票面到期日</th>
							<th class="center">计息到期日</th>
							<th class="center">贴现日</th>
							<th class="center">利率</th>
							<th class="center">票面金额</th>
							<th class="center">不得转让标记</th>
							<th class="center">报文实付金额</th>
							<th class="center">试算实付金额</th>
							<th class="center">承兑行</th>
							<th class="center">出票人</th>
							<th class="center">收款人</th>
							<th class="center">详情</th>
						</tr>
					</thead>
				<tbody>
				<c:choose>
					<c:when test="${not empty resultList}">
						<c:forEach items="${resultList}" var="disc" varStatus="vs">
							<tr>
								<td class="center"><input type="checkbox" name="ids" value="${disc.discmxId}" onclick="getall('ids')" price="${disc.billMoney}"/></td>
								<td class="center">${disc.billNo}</td>
								<td class="center">${disc.inAcctNo}</td>
								<td class="center">${disc.inBankNo}</td>
								<td class="center">${fns:getDictLabel('K_BILL_TYPE',disc.billType)}</td>
								<td class="center">${disc.issueDt}</td>
								<td class="center">${disc.dueDt}</td>
								<td class="center">${disc.galeDate}</td>
								<td class="center">${disc.discDt}</td>
								<td class="center">${disc.rate}</td>
								<td class="center">${fns:formateMoney(disc.billMoney)}</td>
								<td class="center">${fns:getDictLabel('K_FORBIDFLAG',disc.forbidFlag)}</td>
								<td class="center">${fns:formateMoney(disc.draftPayMoney)}</td>
								<td class="center">${fns:formateMoney(disc.localPayMoney)}</td>
								<td class="center">${disc.acceptor}</td>
								<td class="center">${disc.remitter}</td>
								<td class="center">${disc.payee}</td>
								<td class="center"><a href="javascript:goDetail('${disc.rgctId}')">查看</a></td>
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
	<%-- /列表操作区 --%>
	<div id="select-Info">
		<form  action="#" name="dataCollectForm" method="post"></form>
		<div id="selectInfo"><center>暂时没有相关数据</center></div>
	</div>
	<div id="page" class="pagination">
			<form action="<%=basePath%>discApplyController.do?method=addElecBill"  name="pageForm" method="post" >
				<%@ include file="/webpage/system/admin/pageFormContent.jsp"%>
				<input type="hidden" id="rate" name="rate" value="${query.rate}"/>
				<input type="hidden" id="discDt" name="discDt" value="${query.discDt}"/>
				<input type="hidden" id="billType" name="billType" value="${query.billType}"/>
				<input type="hidden" id="custAccount" name="custAccount" value="${custAccount}"/>
				<input type="hidden" id="discIds" name="discIds" value="${discIds}"/>
			</form>
		</div>
	</div>
</div>
<%@ include file="/webpage/system/admin/modalDialog.jsp"%>
<%@ include file="/webpage/system/admin/footer.jsp"%>
<script>
	function goHistory(){
	    dynamicHiddenElement('dataCollectForm','acctNo',$('#custAccount').val());
		dynamicHiddenElement('dataCollectForm','discId',$('#discIds').val());
		modal("#layer_loading,#image");
		dataCollectForm.action ="<%=basePath%>discApplyController.do?method=elecBillList";
		dataCollectForm.submit();
	}
	//详情页面
	function goDetail(rgctId){
		var diag = new top.Dialog();
		 diag.Drag = true;
		 diag.Title ="详情";
		 diag.URL = "<%=basePath%>elecBillDetailController.do?method=goDetail&rgctId="+rgctId;
		 diag.Width = 900;
		 diag.Height = 575;
		 diag.CancelEvent = function(){ //关闭事件
			diag.close();
		 };
		 diag.show(); 
	}
	function add(){
	    var checkNum = getCheckNum("ids");
	   	 if (checkNum == 0){
	   		bootbox.alert("请先选择要新增的记录");
	   		return;
	   	 }
	   	var bill_ids = getCheckStr("ids");
	   	var discId = "${discIds}";
	   	var custAccount = "${custAccount}";
	    $.ajax({
			type: "POST",
			url: '<%=basePath%>discApplyController.do?method=saveAddElecBill',
			data: {'discmxId': bill_ids,'discId': discId,'acctNo': custAccount},
			dataType:'json',
			//beforeSend: validateData,
			cache: false,
			success: function(data){	
				if (data.success){  //处理成功
					dynamicHiddenElement('dataCollectForm','acctNo',$('#custAccount').val());
			        dynamicHiddenElement('dataCollectForm','discId',discId);
			        modal("#layer_loading,#image");
			        dataCollectForm.action ="<%=basePath%>discApplyController.do?method=elecBillList";
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