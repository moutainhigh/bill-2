<%-- 
 * 文件名称: modify_rebuy_apply_save_elec.jsp
 * 系统名称: 票据管理平台
 * 模块名称: 电票转入修改申请批次清单保存页面
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: @version2.0.0.1
 * 开发人员: sherry
 * 开发时间: 2016-10-24
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
				<form action="<%=basePath%>rebuyApplyController.do?method=saveElecBill" method="post" name="batchForm" id="batchForm" class="form-search detail-form">
					<input type="hidden" name="ids" value="${ids}"/>
					<input type="hidden" name="isEdit" value="1"/>
					<input type="hidden" name="rebuyId" value="${batch.rebuyId}"/>
					<input type="hidden" name="billType" value="${batch.billType}"/>
					<input type="hidden" name="billClass" value="${batch.billClass}" />
					<input type="hidden" name="discType" value="${batch.discType}"/>
					<input type="hidden" name="isRediscCenter" value="${batch.isRediscCenter}"/>
					<input type="hidden" name="isOnline" value="${batch.isOnline}"/>
					<input type="hidden" name="isInner" value="${batch.isInner}"/>
					<%-- 查询区  --%>
					<div class="row-fluid">
						<label for="batchNo" class="pull-left control-label">批次号</label>
						<div class="pull-left batch" name="batchNo" id="batchNo">${batch.batchNo}</div>
						<label for="rebuyDt">产品类型</label>
						<input type="text" class="input-medium" name="prodName" value="${prodName}" readonly />
						<input type="hidden" name="prodNo" id="prodNo" value="${batch.prodNo}"/>
						<label for="rebuyDt">转入日</label>
						<input type="text" class="input-medium" name="rebuyDt" id="rebuyDt" value="${batch.rebuyDt}" readonly />
					</div>
					<div class="row-fluid">
						<label for="custBankNo">交易对手行号</label>
						<input type="text" class="input-medium" name="custBankNo" id="custBankNo" value="${batch.custBankNo}" readonly />
						<label for="custBankName">交易对手名称</label>
						<input type="text" class="input-medium" name="custBankName" id="custBankName" value="${batch.custBankName}" readonly />
						<label for="rate">利率</label>
						<input type="text" class="input-medium" name="rate" id="rate" readonly value="${batch.rate}"/>
						<label for="cbRate">成本利率</label>
						<input type="text" class="input-medium" name="cbRate" id="cbRate" <c:if test="${isReadonly=='1'}">readonly="readonly"</c:if> value="${batch.cbRate}" />
					</div>
					<div class="row-fluid">
						<label for="">入账账号类型</label>
						<input type="text" class="input-medium" name="" value="结算账号" readonly />
						<label for="tradeAcct"><span class="star">*</span>入账账号</label>
						<input type="text" class="input-medium" name="tradeAcct" id="tradeAcct" valid="required" value="${batch.tradeAcct}" <c:if test="${isReadonly=='1'}">readonly="readonly"</c:if> onblur="fillAcctInfo();"/>
						<label for="tradeAcctName">账户名称</label>
						<input type="text" class="input-medium" name="tradeAcctName" id="tradeAcctName" valid="required" readonly value="${batch.tradeAcctName}" />
						<c:if test="${batch.discType=='1'}">
							<label for="resaleStaDt">赎回开放日</label>
							<input type="text" class="input-medium" name="resaleStaDt" id="resaleStaDt" value="${batch.resaleStaDt}" readonly />
						</c:if>
					</div>
					<div class="row-fluid">
						<label for="custManage"><span class="star">*</span>客户经理编号</label>
						<input type="text" class="input-medium" name="custManage" id="custManage" valid="required" <c:if test="${isReadonly=='1'}">readonly="readonly"</c:if> value="${batch.custManage}" onblur="fillCustManager();"/>
						<label for="custManagerName">客户经理名称</label>
						<input type="text" class="input-medium" name="custManagerName" id="custManagerName" valid="required" readonly value="${batch.custManagerName}"/>
						<label for="deptName">部门名称</label>
						<input type="text" class="input-medium" name="deptName" id="deptName" value="${batch.deptName}" readonly />
						<c:if test="${batch.discType=='1'}">
							<label for="resaleDueDt" >赎回截止日</label>
							<input type="text" class="input-medium" name="resaleDueDt" id="resaleDueDt" value="${batch.resaleDueDt}" readonly />
						</c:if>
					</div>
					<div class="row-fluid">
						<label for="totalMoney">票面汇总金额</label>
						<input type="text" class="input-medium" name="totalMoney" id="totalMoney" value="${batch.totalMoney}" readonly/>
						<label for="actualAmount">汇总实付金额</label>
						<input type="text" class="input-medium" name="actualAmount" id="actualAmount" readonly value="${batch.actualAmount}"/>
						<label for="totalNum">汇总张数</label>
						<input type="text" class="input-medium" name="totalNum" id="totalNum" readonly value="${batch.totalNum}"/>
						<label for="totalInterest">汇总利息</label>
						<input type="text" class="input-medium" name="totalInterest" id="totalInterest" value="${batch.totalInterest}" readonly />
					</div>
				</form>
			</div>
			<%-- 按钮操作区 --%>
			<div id="center">
				<form  id="btnForm">
					<div class="row-fluid">
						<div class="span6" id="btn-left">
							<a class="btn-mini" onclick="goHistory();">上一步</a>
							<a class="btn-mini" onclick="addBill();">添加票据</a>
							<a class="btn-mini" onclick="deleteBill();">移除票据</a>
					  	 </div>
					   	 <div class="span6" id="btn-right">			
							<a class="btn-mini pull-right" onclick="save();">保存并继续</a>		
					   	</div>
				  	</div>
				</form>
			</div>
			<div id="footer" class="footer">
				<table class="table table-striped table-bordered table-hover" style="min-width:100%;max-width:1800px;width:1800px;" id="tab" cellpadding="0" cellspacing="0">
					<thead>
						<tr>
							<th class="center" onclick="selectAll('allcheck', 'rebuymxId')">
								<input type="checkbox" id="allcheck" /><span class="lbl"></span>
							</th>
							<th class="center">票号</th>
							<th class="center">票据类型</th>
							<th class="center">是否央行卖票</th>
							<th class="center">转入类型</th>
							<th class="center">清算方式</th>
							<th class="center">利率</th>
							<th class="center">转入日</th>
							<th class="center">赎回开放日</th>
							<th class="center">赎回截止日</th>
							<th class="center">票面到期日</th>
							<th class="center">票面金额</th>
							<th class="center">报文实付金额</th>
							<th class="center">承兑人</th>
							<th class="center">出票人</th>
							<th class="center">详细</th>
						</tr>
					</thead>
					<tbody id="dataBody">
						<c:choose>
							<c:when test="${not empty billList}">
								<c:forEach items="${billList}" var="billList" varStatus="vs">
									<tr>
										<td class="center">
											<input type='checkbox' name='rebuymxId' value="${billList.rebuymxId}" onclick="getall('rebuymxId')" price="${billList.billMoney}"/><span class="lbl"></span>
										</td>
										<td class="center">${billList.billNo}</td>
										<td class="center">${fns:getDictLabel('K_BILL_TYPE',billList.billType)}</td>
										<td class="center">${fns:getDictLabel('K_YORN',billList.isRediscCenter)}</td>
										<td class="center">${fns:getDictLabel('K_ISREGRESS',billList.isRegress)}</td>
										<td class="center">${fns:getDictLabel('K_ISONLINE',billList.isOnline)}</td>
										<td class="center">${billList.rate}</td>
										<td class="center">${billList.rebuyDt}</td>
										<td class="center">${billList.resaleStaDt}</td>
										<td class="center">${billList.resaleDueDt}</td>
										<td class="center">${billList.dueDt}</td>
										<td class="center">${fns:formateMoney(billList.billMoney)}</td>
										<td class="center">${fns:formateMoney(billList.payMoney)}</td>
										<td class="center">${billList.acceptor}</td>
										<td class="center">${billList.remitter}</td>
										<td class="center"><a onclick="goDetail('${billList.rgctId}');" style="cursor:pointer">详细</a></td>
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
			<form  action="#" name="dataCollectForm" method="post">
				<t:token></t:token>
			</form>
			<input type="hidden" id="batchid" value="${searchBean.rebuyId}" disabled="disabled"></input>
			<%-- /列表操作区 --%>
			<div>
				<div id="page" class="pagination">
					<form action="<%=basePath%>rebuyApplyController.do?method=toModifyPage" method="post" name="pageForm">
						<%@ include file="/webpage/system/admin/pageFormContent.jsp"%> 
						<input type="hidden" name="rebuyId" value="${batch.rebuyId}"/>
					</form>
				</div>
			</div>
		</div>
	</div>
<%@ include file="/webpage/system/admin/modalDialog.jsp"%>	
<%@ include file="/webpage/system/admin/footer.jsp"%>
<script>
	//返回上一页
	function goHistory(){
		modal("#layer_loading,#image");
		dataCollectForm.action = "<%=basePath%>rebuyApplyController.do?method=searchEditableApplyBatchElec";
		dataCollectForm.submit();
	}
	//根据客户经理编号填充客户经理信息
	function fillCustManager(){
		var custManage = $('#custManage').val();
		$.ajax({
			type: "POST",
			url: "<%=basePath%>rebuyApplyController.do?method=custManagerInfo",
	    	data: {'custManagerNo': custManage},
			dataType:'json',
			beforeSend:function(){
				if(custManage==null||custManage==""){
					showTips("custManage","请输入客户经理编号");
					$("#deptName").val("");
					$("#custManagerName").val("");
			   		return false;
				}
			},
			cache: false,
			success: function(data){	
				if(data.success){  //处理成功
					$("#custManage").val(data.attributes.custManage);
					$("#deptName").val(data.attributes.deptName);
					$("#custManagerName").val(data.attributes.custManagerName);
				}else{
					$("#deptName").val("");
					$("#custManagerName").val("");
					showTips("custManage",data.msg);
				}
			}
		});
	}
	//根据账号填充账户信息
	function fillAcctInfo(){
		var custAcctNo = $('#tradeAcct').val();
		$.ajax({
			type: "POST",
			url: "<%=basePath%>custInfoController.do?method=custInfo",
	    	data: {'custAcctNo': custAcctNo},
			dataType:'json',
			beforeSend:function(){
				if(custAcctNo==null||custAcctNo==""){
					showTips("tradeAcct","请输入客户账号");
					$("#tradeAcct").val("");
					$("#tradeAcctName").val("");
			   		return false;
				}
			},
			cache: false,
			success: function(data){	
				if(data.success){  //处理成功
					$("#tradeAcct").val(data.attributes.custAcctNo);
					$("#tradeAcctName").val(data.attributes.custName);
					searchForm.submit();
				}else{
					$("#tradeAcct").val("");
					$("#tradeAcctName").val("");
					showTips("tradeAcct",data.msg);
				}
			}
		});
	}
	//详情页面
	function goDetail(rgctId){
		var diag = new top.Dialog();
		 diag.Drag = true;
		 diag.Title ="详情";
		 diag.URL = "<%=basePath%>elecBillDetailController.do?method=goDetail&rgctId="+rgctId;;
		 diag.Width = 930;
		 diag.Height = 575;
		 diag.CancelEvent = function(){ //关闭事件
			diag.close();
		 };
		 diag.show();
	}
	//提交
	function save(){
		var ids = "${ids}";
		if(ids==null||ids.length==0){
			bootbox.alert("该批次下不含有待提交票据，请添加票据或返回申请页面重新接收。");
			return;
		}
		if($("#batchForm").valid()){
			if(!checkProdNo()){
				return;
			}
			if(!checkRate()){
				return;
			}
			if(!checkCbRate()){
				return;
			}
			var rebuyDt = $("#rebuyDt").val();
			var workday = "${workday}";
			if(workday!=rebuyDt){
				bootbox.confirm('转入日不是当前营业日,是否继续?',function(result){  
		            if(result) {  
		            	modal("#layer_loading,#image");
		    			$("#batchForm").submit();
		            }
		        })
			}else{
				modal("#layer_loading,#image");
				$("#batchForm").submit();
			}
		}
	}
	//校验转入利率
	function checkRate(){
		var rate = $("#rate").val();
		var result = validateData(rate);
		if(result != 1 ){
			showTips("rate","利率"+result);
			return false;
		}
		return true;
	}
	//校验成本利率
	function checkCbRate(){
		var cbRate = $("#cbRate").val();
		var result = validateData(cbRate);
  	    if(result != 1 ){
  	   		showTips("cbRate","利率"+result);
  	   		return false;
  	    }
  	    return true;
	}
	//校验利率
  	//成功返回1，失败返回提示信息
  	function validateData(rate){
		var re1 =/^[0-9]+[\.]?[0-9]*$/;
		var msg = 1;
		if(rate == null || $.trim(rate) == "") {
			msg = "不能为空";
			return msg;
		}
		if(!re1.test(rate)){
			msg = "只能为数字";
			return msg;
		}
		if(rate<0 || rate>100){
			msg = "利率应该大于等于0且小于等于100";
			return msg;
		}
		//校验精度
  		var array = rate.split(".",-1);
  		if(array.length > 2){
  		  msg = "格式不正确";
  		  return msg;
  		}
		if(array[0]!=null && $.trim(array[0])!=""){
			var intPart = array[0];
			if("0" == intPart.substring(0,1) && intPart.length > 1){
				msg = "格式不正确";
	  		  	return msg; 
			}
		 }
  		if(array.length==2 && array[1]!=null && $.trim(array[1])!=""){
  			if(array[1].length>4){
  				msg = "精确到小数点后4位";
  				return msg;
  			}
  		}
		return msg;	
  	}
	//校验转入日
	function checkRebuyDt(){
		var rebuyDt = $("#rebuyDt").val();
		var workday = "${workday}";
		if(rebuyDt==null || $.trim(rebuyDt)==""){
			showTips("rebuyDt","转入日不能为空");
			return false;
		}
		if(workday!=rebuyDt){
			if(!confirm("转入日不是当前营业日,是否继续?")){
				return false;
			}
			/* bootbox.confirm("转入日不是当前营业日,是否继续?",function(result){
				if(result){
					return false;
				}
			}) */
		}
		return true;
	}
	//校验产品类型
	function checkProdNo(){
		var prodNo = $("#prodNo").val();
		if(prodNo==null||prodNo==""||prodNo=="-1"){
			showTips("prodNo","请选择产品类型");
			return false;
		}
		return true;
	}
	//新增票据
	function addBill(){
	   	 $("#_ButtonClose_0").css({'width':'27px','top':'4px','background-position': '-1px 0px'});
	   	 var diag = new top.Dialog();
		 diag.Drag = true;
		 diag.Title ="新增票据";
		 diag.URL = "<%=basePath%>rebuyApplyController.do?method=toAddBillPage&rebuyId="+"${batch.rebuyId}";
		 diag.Width = 1000;
		 diag.Height = 500;
		 diag.CancelEvent = function(){ //关闭事件
		 	modal("#layer_loading,#image");
		 	pageForm.submit();
			diag.close();
		 };
		 diag.show(); 
	}
	function deleteBill(){
		var checkNum = getCheckNum("rebuymxId");
		if(checkNum==0){
			bootbox.alert("请先选择要移除的记录！");
			return;
		}
		var ids = getCheckStr("rebuymxId");
		bootbox.confirm("确定要移除选中的记录吗？",function(result){
			if(result){
				dynamicHiddenElement('dataCollectForm','ids',ids);
				dataCollectForm.action = "<%=basePath%>rebuyApplyController.do?method=removeElecBill";
				modal("#layer_loading,#image");
				dataCollectForm.submit();
			}
		});		
	}
</script>
</body>
</html>