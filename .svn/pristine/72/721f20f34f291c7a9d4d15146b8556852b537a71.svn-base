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
	<%-- jsp文件头和头部 --%>
	<%@ include file="../admin/top.jsp"%> 
	<%@include file="/webpage/system/admin/modalDialog.jsp"%>
	<%@ include file="/webpage/system/admin/footer.jsp"%>
	<script type="text/javascript">
		function gernateBill(){
			dataCollectForm.action="<%=basePath%>xmlController.do?method=gernateBill";
			dataCollectForm.submit();
		}
		function discAcctBill(){
			modal("#layer_loading,#image");//点击票据管理按钮，弹出‘加载中’的遮罩层
			dataCollectForm.action="<%=basePath%>xmlController.do?method=discAcctBill";
			dataCollectForm.submit();
		}
		function rebuyAcctBill(){
			modal("#layer_loading,#image");//点击票据管理按钮，弹出‘加载中’的遮罩层
			dataCollectForm.action="<%=basePath%>xmlController.do?method=rebuyAcctBill";
			dataCollectForm.submit();
		}
		function saleAcctBill(){
			modal("#layer_loading,#image");//点击票据管理按钮，弹出‘加载中’的遮罩层
			dataCollectForm.action="<%=basePath%>xmlController.do?method=saleAcctBill";
			dataCollectForm.submit();
		}
		function discEleGernateBill(){
			modal("#layer_loading,#image");//点击票据管理按钮，弹出‘加载中’的遮罩层
			dataCollectForm.action="<%=basePath%>xmlController.do?method=discEleGernateBill";
			dataCollectForm.submit();
		}
		function rebuyEleGernateBill(){
			modal("#layer_loading,#image");//点击票据管理按钮，弹出‘加载中’的遮罩层
			dataCollectForm.action="<%=basePath%>xmlController.do?method=rebuyEleGernateBill";
			dataCollectForm.submit();
		}
		function saleEleGernateBill(){
			modal("#layer_loading,#image");//点击票据管理按钮，弹出‘加载中’的遮罩层
			dataCollectForm.action="<%=basePath%>xmlController.do?method=saleEleGernateBill";
			dataCollectForm.submit();
		}
		function entityCollectionBill(){
		    modal("#layer_loading,#image");//点击票据管理按钮，弹出‘加载中’的遮罩层
			dataCollectForm.action="<%=basePath%>xmlController.do?method=entityCollectionBill";
			dataCollectForm.submit();
		}
		function elecAcceptBill(){
		    modal("#layer_loading,#image");//点击票据管理按钮，弹出‘加载中’的遮罩层
			dataCollectForm.action="<%=basePath%>xmlController.do?method=elecAcceptBill";
			dataCollectForm.submit();
		}
		function entityAcceptBill(){
		    modal("#layer_loading,#image");//点击票据管理按钮，弹出‘加载中’的遮罩层
			dataCollectForm.action="<%=basePath%>xmlController.do?method=entityAcceptBill";
			dataCollectForm.submit();
		}
		function elecCollectionBill(){
		    modal("#layer_loading,#image");//点击票据管理按钮，弹出‘加载中’的遮罩层
			dataCollectForm.action="<%=basePath%>xmlController.do?method=elecCollectionBill";
			dataCollectForm.submit();
		}
		function Pager(){
		    var  showCount = getCheckStr("showCount");
		    dynamicHiddenElement('userForm','showCount',showCount);
		    modal("#layer_loading,#image");//点击票据管理按钮，弹出‘加载中’的遮罩层
			dataCollectForm.action="<%=basePath%>xmlController.do?method=Pager";
			dataCollectForm.submit();
		}
	</script>
</head>
<body style="font-family:'微软雅黑';">
	<div class="clearfix">
		<div id="page-content" class="page-content">
		<div id="header">
		  <form action="<%=basePath%>/xmlController.do?method=xmlList" method="post" name="userForm" id="userForm" class="form-search">
					<div class="row-fluid" >
		   				<label for="code">编码集</label>
						<input class="input-medium" type="text" name="code" id="code" value="UTF-8" readonly= "true"/>
					</div>
					<div class="row-fluid" >
						<label for="data">报文</label>
						<textarea name="data" id="data" style="width:300px;height:200px"></textarea>
						<a class="btn-mini" type="submit" id="submit" style="margin-left:30px">发送</a>
						<label for="data">接收报文</label>
						<textarea name="data" id="data" style="width:400px;height:200px">${StrReq}</textarea>
					</div>
				</form>
			</div>	
			<div id="center">
					<div class="row-fluid">
						<div class="" id="btn-left">
							<a class="btn-mini" onclick="gernateBill();">收票签收</a>
				  			<a class="btn-mini" onclick="discAcctBill();">纸票贴现记账</a>
				  			<a class="btn-mini" onclick="discEleGernateBill();">电票贴现记账</a>
				  			<a class="btn-mini" onclick="saleAcctBill();">纸票转卖记账</a>
				  			<a class="btn-mini" onclick="saleEleGernateBill();">电票转卖记账</a>
				  			<a class="btn-mini" onclick="rebuyAcctBill();">纸票转入记账</a>
				  			<a class="btn-mini" onclick="rebuyEleGernateBill();">电票转入记账</a>
			  		</div>
	    		    <div class="row-fluid" style="margin:5px 0">
						<div class="" id="btn-left">
							<a class="btn-mini" onclick="entityCollectionBill();">纸票托收申请查询，纸票转卖申请查询</a>
			  			</div>
	    			</div>
	    		    <div class="row-fluid">
						<div class="" id="btn-left">
							<a class="btn-mini" onclick="elecAcceptBill();">电票承兑记账岗</a>
			  			</div>
	    			</div>
	    			<div class="row-fluid" style="margin:5px 0">
						<div class="" id="btn-left">
							<a class="btn-mini" onclick="entityAcceptBill();">纸票承兑记账岗</a>
			  			</div>
	    			</div>
	    			<div class="row-fluid">
						<div class="" id="btn-left">
							<a class="btn-mini" onclick="elecCollectionBill();">电票托收申请查询</a>
			  			</div>
	    			</div>
	    		</div>
		</div>
			<div id="">
				<form  action="#" name="dataCollectForm" method="post">
					<div class="row-fluid">
						<label for="data" style="text-align:right">生成条数</label>
						<input class="input-medium" type="text" id="count" name="count" valid="required" >
					</div>
					<div class="row-fluid" style="margin-top:10px;">
						<label for="showCount" style="text-align:right">每页显示记录数</label>
						<input class="input-medium" type="text" name="showCount"  id="showCount" placeholder="请输入每页显示记录数"  />
						<a class="btn-mini" id="Pager" onclick="Pager();">修改</a>
					</div>
				</form>
			</div>
	  	</div>
	</div>
</body>
</html>