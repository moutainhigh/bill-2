<%-- 
 * 文件名称: disc_review_account_detail_list.jsp
 * 系统名称: 票据管理平台
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: @version2.0.0.1
 * 开发人员: songzx
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
	<script type="text/javascript" src="webpage/system/addPrint/commonFunc.js"></script>
</head>
<body>
	<div class="clearfix">
		<div id="page-content" class="page-content">
			<div id="header">
				<form action=""	method="post" name="Form" id="Form" class="form-search detail-form">
					<input type="hidden" name="acctNo" value="${query.acctNo}"/> 
					<%-- 查询区  --%>
					<div class="row-fluid">
						<label for="batchNo" class="pull-left control-label">批次号</label>
						<div class="pull-left batch">${batch.batchNo}</div>
						<label for="industry_investment">票据类型</label>
						<input type="text" class="input-medium" name="billType" readonly value="${fns:getDictLabel('K_BILL_TYPE',batch.billType)}"/>
						<label for="totalNum">票据种类</label>
						<input type="text" class="input-medium" name="totalNum" disabled value="${fns:getDictLabel('K_BILL_CLASS',batch.billClass)}" />
						<label for="custManage">产品名称</label>
						<input type="text" class="input-medium" name="custManage" value="${ProdName}" disabled />
					</div>
					<div class="row-fluid">
						<label for="totalMoney">付息方式</label>
						<input type="text" class="input-medium" id="payType" name="payType" disabled value="${fns:getDictLabel('K_PAY_TYPE',batch.payType)}" />
						<c:if test="${batch.payType==0}">
							<label for="totalMoney">买方付息比例</label>
							<input type="text" class="input-medium" name="totalMoney" disabled value="${batch.buyPayRate}" />
						</c:if>
						<label for="rate">贴现利率</label>
						<input type="text" class="input-medium" name="rate" disabled value="${batch.rate}"/>
						<label for="totalNum">成本利率</label>
						<input type="text" class="input-medium" name="totalNum" disabled value="${batch.cbRate}" />
					</div>
					<div class="row-fluid">
						<label for="industry_investment">行业投向</label>
						<input type="text" class="input-medium" name="professionName" disabled value="${batch.professionName}"/>
						<label for="discDt">贴现日</label>
						<input type="text" class="input-medium" name="discDt" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'});" value="${batch.discDt}"/>
					</div>
					<div class="row-fluid">
						<label for="socialCreditCode">社会信用代码</label>
						<input type="text" class="input-medium" name="socialCreditCode" value="${batch.socialCreditCode}" disabled/>
						<label for="isRuralEnterprises">是否三农企业</label>
						<input type="text" class="input-medium" name="isRuralEnterprises" value="${fns:getDictLabel('K_IS_RURAL',batch.isRuralEnterprises)}" disabled/>
						<label for="enterprise_scale">企业规模</label>
						<input type="text" class="input-medium" name="enterpriseScale" disabled value="${fns:getDictLabel('K_SCALE',batch.enterpriseScale)}" disabled/>
					</div>
					<div class="row-fluid">
						<label for="custName">客户名称</label>
						<input type="text" class="input-medium" id="custName" name="custName" value="${batch.custName}" disabled />
						<label for="custNo">客户号</label>
						<input type="text" class="input-medium" id="custNo" name="custNo" disabled value="${batch.custNo}" />
						<label for="accountnumber_status">卖方账号类型</label>
						<input type="text" class="input-medium" name="accountnumber_status" disabled value="结算账号"/>
						<label for="custAccount">客户账号</label>
						<input type="text" class="input-medium" id="ruzhang" name="ruzhang" disabled value="${batch.custAccount }" />
					</div>
					<div class="row-fluid">
						<label for="custManagerName">客户经理名称</label>
						<input type="text" class="input-medium" name="custManagerName" value="${batch.custManagerName}" disabled />
						<label for="custManage">客户经理编号</label>
						<input type="text" class="input-medium" name="custManage" value="${batch.custManage}" disabled />
						<label for="deptName">客户经理部门</label>
						<input type="text" class="input-medium" disabled name="deptName" value="${batch.deptName}" />
					</div>
					<div class="row-fluid">
						<label for="totalNum">票据张数</label>
						<input type="text" class="input-medium" name="totalNum" disabled value="${batch.totalNum}" />
						<label for="totalMoney">总金额</label>
						<input type="text" class="input-medium" name="totalMoney" disabled value="${fns:formateMoney(batch.totalMoney)}" />
						<label for="profOwner">总利息</label>
						<input type="text" class="input-medium" name="profOwner" disabled value="${fns:formateMoney(batch.totalInterest)}" />
						<label for="profOwner">实付金额</label>
						<input type="text" class="input-medium" name="profOwner" disabled value="${fns:formateMoney(batch.actualAmount)}" />
					</div>
				</form>
			</div>
			<%-- 按钮操作区 --%>
			<div id="center">
				<form  id="btnForm">
					<div class="row-fluid">
						<div class="span6" id="btn-left">
							<a class="btn-mini" onclick="infoDownload();">贴现信息下载</a>
							<a class="btn-mini" onclick="ownershipDownload();">权属下载</a>
			  			</div>
						 <div class="span6" id="btn-right">
							<a class="btn-mini pull-right" onclick="doAccount();">提交</a>
							<a class="btn-mini pull-right" onclick="printPZ();">打印凭证</a>					
							<a class="btn-mini pull-right" onclick="printList();">打印清单</a>
							<a class="btn-mini pull-right" onclick="goHistory();">返回</a>	
						 </div>	
		  			</div>
				</form>
			</div>
			<div id="footer" class="footer">
				<table class="table table-striped table-bordered table-hover" id="tab" cellpadding="0" cellspacing="0">
					<thead>
						<tr>
							<th class="center"><input type="checkbox" name="discNos" id="discNos" onclick="selectAll('discNos','ids')"/></th>
							<th class="center">票号</th>
							<th class="center">票据类型</th>
							<th class="center">票面到期日</th>
							<th class="center">计息到期日</th>
							<th class="center">计息天数</th>
							<th class="center">票面金额</th>
							<th class="center">贴现利息</th>
							<th class="center">实付金额</th>
							<th class="center">票面状态</th>
							<th class="center">详情</th>
						</tr>
					</thead>	
					<tbody>
						<c:choose>
							<c:when test="${not empty resultList}">
								<c:forEach items="${resultList}" var="disc" varStatus="vs">
									<tr>
										<label><td class="center"><input type="checkbox" name="ids" value="${disc.discmxId}"/><span class="lbl"><label>
										</td>
										<td class="center">${disc.billNo}</td>
										<td class="center">${fns:getDictLabel('K_BILL_TYPE',disc.billType)}</td>
										<td class="center">${disc.dueDt}</td>
										<td class="center">${disc.galeDate}</td>
										<td class="center">${disc.interestDays}</td>
										<td class="center">${fns:formateMoney(disc.billMoney)}</td>
										<td class="center">${fns:formateMoney(disc.interest)}</td>
										<td class="center">${fns:formateMoney(disc.payMoney)}</td>
										<c:if test="${disc.curStatus!='1'}">
										<td class="center">票据待记账</td>
										</c:if>
										<td class="center"><a href="javascript:goDetail('${disc.discmxId}','${disc.discId}')">查看</a></td>
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
			<form action="" name="publicForm" method="post"></form>
			<form action=""  method="post" name="dataCollectForm" id="dataCollectForm" class="form-search" >
				<input type="hidden" name="discmx_Id" id="discmx_id" value="${disc.discmxId}">
			</form>
			<input type="hidden" id="batchid" value="${query.discId}" disabled="disabled"></input>
			<%-- /列表操作区 --%>
			<div id="page" class="pagination">
				<form action="<%=basePath%>discAccountController.do?method=reviewDetailList" method="post" name="pageForm">
					<%@ include file="/webpage/system/admin/pageFormContent.jsp"%> 
					<input type="hidden" name="discId" value="${batch.discId}"/>
				</form>
			</div>
		</div>
	</div>
<%@ include file="/webpage/system/admin/modalDialog.jsp"%>
<%@ include file="/webpage/system/admin/footer.jsp"%>
<script>
	//行业投向字段的长度判断
	$(document).ready(function(){
		var explorer = window.navigator.userAgent ;//判断何种浏览器
		if (explorer.indexOf("MSIE") >= 0) {//IE浏览器
			$("#s2id_status").width('160');
		}else if (explorer.indexOf("Firefox") >= 0) {//火狐浏览器
			$("#s2id_status").width('166');	
		}else if(explorer.indexOf("Chrome") >= 0){//谷歌浏览器
			$("#s2id_status").width('166');				
		}
		if("${batch.payType}"==0){
				
		}else{
			$(".discLabeFirst").css("margin-left","25px");
			$(".discLabeSecond span:eq(0)").css("margin","5px");
			$(".discLabeSecond span:eq(1)").css("margin","4.5px");
			$(".discLabeSecond span:eq(2)").css("margin","5px");
		}
	})
	//返回上一页
	function goHistory(){
		modal("#layer_loading,#image");//点击票据管理按钮，弹出‘加载中’的遮罩层
		publicForm.action="<%=basePath%>discAccountController.do?method=reviewAccountList";
		publicForm.submit();
	}
	function doAccount(){
	 	var checkNum = getCheckNum("ids");
   	 	if (checkNum == 0){
	   				bootbox.alert("请先选择要提交的记录");
	   		return;
	 	}
		var ids = getCheckStr("ids");
		$.ajax({
			url:"<%=basePath%>discAccountController.do?method=doAccount",
			type:"POST",
			data:{"ids":ids,"discId":"${batch.discId}"},
			dataType:"json",
			success:function(rs){
				if(rs == "yes"){
					modal("#layer_loading,#image");//点击票据管理按钮，弹出‘加载中’的遮罩层
					publicForm.action="<%=basePath%>discAccountController.do?method=reviewAccountList";
					publicForm.submit();
				}else{
					bootbox.alert("记账失败");
				}
			}
		});
	}
	//详情页面
	function goDetail(discmxId,discId){
	   	 var diag = new top.Dialog();
		 diag.Drag = true;
		 diag.Title ="详情";
		 diag.URL ="<%=basePath%>discAuditController.do?method=goDetail&discmxId="+discmxId+"&discId="+discId;
		 diag.Width = 920;
		 diag.Height = 550;
		 diag.CancelEvent = function(){ //关闭事件
			diag.close();
		 };
		 diag.show();
	}
	 //打印贴现申请清单
    //operStatusString 票据状态
	function printList(){
		var payType=$("#payType").val();//付息方式 1买 2卖 3协议
		var batch_id=$("#batchid").val();
		var checkNum = getCheckNum("ids");
	   	 if (checkNum == 0){
	   		bootbox.alert("请先选择要打印的记录");
	   		return;
	   	 }   	 
		var ids = getCheckStr("ids");
		$.ajax({
			url:"discApplyController.do?method=isInterestTrial",
			type:"POST",
			data:{"ids":ids},
			dataType:"json",
			success:function(rs){
				if(rs.success){//是否计算过利息
				 	doPrint("addPrintController.do?method=doPrint&ids="+ids+"&moduleId=1030601&type=DiscAuditPrint&baid="+batch_id+"&handleType=记 账"); 	 
				}else{
					bootbox.alert("请先进行利息试算");
				}
			},
			error:function(rs){
				bootbox.alert("系统异常");
			}
		}); 	
	}
	//打印贴现申请凭证
	function printPZ(){
		var payType="${batch.payType}";//付息方式 1买 2卖 3协议
		var batch_id=$("#batchid").val();
		var checkNum = getCheckNum("ids");
	   	 if (checkNum == 0){
	   		bootbox.alert("请先选择要打印的记录");
	   		return;
	   	 }   	 
		var ids = getCheckStr("ids");
		$.ajax({
			url:"discApplyController.do?method=isInterestTrial",
			type:"POST",
			data:{"ids":ids},
			dataType:"json",
			success:function(rs){
				if(rs.success){//是否计算过利息
					if(payType!="2"){
					
						doPrint("addPrintController.do?method=doPrint&ids="+ids+"&moduleId=1030301&type=DiscPrint&baid="+batch_id); 	 						 
	        		}else{
						doPrint("addPrintController.do?method=doPrint&ids="+ids+"&moduleId=1030102&type=DiscPrint&baid="+batch_id); 	 

	        		}
				}else{
					bootbox.alert("请先进行利息试算");
				}
			},
			error:function(rs){
				bootbox.alert("系统异常");
			}
		});
			
	}
	
	function ownershipDownload(){
	publicForm.action="<%=basePath%>discAccountController.do?method=exportBill";
	publicForm.submit();
	}
//贴现信息下载
	function infoDownload(){
		var checkNum = getCheckNum("ids");
   	 	if (checkNum == 0){
	   				bootbox.alert("请先选择要下载的记录");
	   		return;
	 	}
		var ids = getCheckStr("ids");
		$("#discmx_id").val(ids);
		var diag = new top.Dialog();
		diag.Drag = true;
		diag.Title ="详情";
		diag.URL = "<%=basePath%>exportController.do?method=getExportInfo&tableName=vw_export_discinfo&action=exportController.do?method=commonExport&formName=dataCollectForm";
		diag.Width = 800;
		diag.Height = 575;
		diag.CancelEvent = function(){ //关闭事件
		diag.close();
		};
		diag.show();
	}
	//权属下载
	function ownershipDownload(){
		var checkNum = getCheckNum("ids");
   	 	if (checkNum == 0){
	   				bootbox.alert("请先选择要下载的记录");
	   		return;
	 	}
		var ids = getCheckStr("ids");
		$("#discmx_id").val(ids);
		var diag = new top.Dialog();
		diag.Drag = true;
		diag.Title ="详情";
		diag.URL = "<%=basePath%>exportController.do?method=getExportInfo&tableName=TDISC_BILL_INFO&action=exportController.do?method=commonExport&formName=dataCollectForm";
		diag.Width = 800;
		diag.Height = 575;
		diag.CancelEvent = function(){ //关闭事件
		diag.close();
		};
		diag.show();
	}
</script>
</body>
</html>