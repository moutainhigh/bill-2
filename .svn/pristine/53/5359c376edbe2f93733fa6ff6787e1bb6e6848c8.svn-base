<%-- 
 * 文件名称: freeformatdraftsend.jsp
 * 系统名称: 票据管理平台
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: @version2.0.0.1
 * 开发人员: lijiangtao
 * 开发时间: 2016-8-31 
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
		<%-- jsp文件头和头部 --%>
		<%@ include file="../admin/top.jsp"%> 
	</head>
<body>
<div class="clearfix">
	<div id="page-content" class="page-content">
		<div class="main_edittable" id="header">
			<form action="<%=basePath%>sysFreeFormatController.do?method=send" method="post" name="userForm" id="userForm" class="form-search">
				<div class="row-fluid">
					<label for="param_id"><span id="mustfill" style="color:red">*</span>广播级别</label>
					<select class="select2 select-medium" name="borderLevel" id="borderLevel" onchange="setReceiveTypeName(this);">
						<option value="BC00" selected="selected">非广播</option>
						<option value="BC01">一级广播</option>
						<option value="BC02">二级广播</option>
					</select>
					<label for="param_id"><span id="mustfill" style="color:red">*</span>发起行行号</label>
					<input class="input-medium" id="frombankno" type="text" value="${map.branchbankno}"/>
					<label for="param_id" id="receiveType"><span id="mustfill" style="color:red">*</span>接收行行号</label>
					<input class="input-medium" type="text" name="freeDraft.receiveBankNo" onblur="checkIsEmpty(this,3);" id="t3" maxLength="12" placeholder="请输入接收行行号"></input>
				</div>
				<div class="row-fluid">
					<label for="param_id"><span id="mustfill" style="color:red">*</span>报文内容</label>
					<textarea  name="drafttext" onblur="checkIsEmpty(this);"  id="t1" placeholder="请输入报文内容"></textarea>
					<a type="button" id="search" style="margin-top:35px;" onclick="queryEcdsBankData();">查询</a>
				</div>
			</form>
		</div>	
		<div id="center">
			<div class="span6" id="btn-left">
				<a class="btn-mini"  onclick="send();" >发送</a>
	       	</div>
	    </div>	
	</div>	
</div>	
<%@ include file="../admin/modalDialog.jsp"%>
<%@ include file="../admin/footer.jsp"%> 
<script type="text/javascript">
	function setReceiveTypeName(obj){
		var auditpro = document.getElementById("borderLevel");
		var recevieType = auditpro.options[auditpro.selectedIndex].value;
	   if(recevieType=='BC00'){
	      document.getElementById("receiveType").innerHTML="接受行行号";
	   }
	   if(recevieType=='BC01'){
	      document.getElementById("receiveType").innerHTML="CCPC代码";
	   }
	   if(recevieType=='BC02'){
	     document.getElementById("receiveType").innerHTML="接入点号";
	   }
	}
	//检查
	function checkIsEmpty(type,flag){
		if(type.value==""){ 
		 	showTips("t3","内容不许为空!");
			return;
		}
		if(flag==3){
		  if(type.value.length<1||type.value.length>12){
		  	showTips("t3","行号必须为1到12位的数字!");
		  }
		}
	}
	//发送报文		
	function send(){
		  var reqCheck = true;
		  var meetcode = $("#t3").val();;
		  var drafttext = document.getElementById("t1").innerHTML;
		  if(meetcode==""||drafttext==""){
		    reqCheck=false;
		  }
		  if(reqCheck==true){
			  var bankNo="";
			  var rombankno=document.getElementById("frombankno");
			  var frombankno=rombankno.innerText;
			  if(frombankno != null){
			  	bankNo = frombankno;
			  }else{
				bankNo=null;	
			  }	
			  if(bankNo==null || bankNo.length<12){
			    showTips("frombankno","发起行号填写错误");
			     return;
		  	  } 
			  var auditpro = document.getElementById("borderLevel");
			  var recevieType = auditpro.options[auditpro.selectedIndex].value;
			  $.ajax({
			  	url:"sysFreeFormatController.do?method=send",
			  	data:{"recevieType":recevieType,"drafttext":drafttext,"bankNo":bankNo,"meetcode":meetcode},
			    type:"POST",  
			    dataTyoe:"JSON",
			    success:function(result){
			    	bootbox.alert(result);
			    },
			    error:function(data){
			    	bootbox.alert("失败");
			    }
			  });
			}
			else{
			 bootbox.alert("有些输入项不合法,请检查输入项!");
			  return;
			}
		}
</script>			
</body>
</html>
