<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/webpage/system/admin/taglib.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head><title>打印</title>
 <base href="<%=basePath%>">
    <meta charset="UTF-8">
    <%@ include file="/webpage/system/admin/top.jsp"%>
<link href="/bbsp/public/css/xtree.css" rel="stylesheet" type="text/css" />
<link href="webpage/system/addPrint/print.css" rel="stylesheet" type="text/css" />
<link href="webpage/system/addPrint/style.css" rel="stylesheet" type="text/css" />
<script>

var HKEY_Root,HKEY_Path,HKEY_Key;
HKEY_Root="HKEY_CURRENT_USER";
HKEY_Path="\\Software\\Microsoft\\Internet Explorer\\PageSetup\\";

function clearPageing(){
	try{
  		var Wsh=new ActiveXObject("WScript.Shell");
		HKEY_Key="header";
		//Wsh.RegWrite(HKEY_Root+HKEY_Path+HKEY_Key,"print header");
		Wsh.RegWrite(HKEY_Root+HKEY_Path+HKEY_Key,"");
		HKEY_Key="footer";
		//Wsh.RegWrite(HKEY_Root+HKEY_Path+HKEY_Key,"print footer");
		Wsh.RegWrite(HKEY_Root+HKEY_Path+HKEY_Key,"");
		
		/*
		HKEY_Key="margin_bottom";
		Wsh.RegWrite(HKEY_Root+HKEY_Path+HKEY_Key,"0.37");
		HKEY_Key="margin_left";
		Wsh.RegWrite(HKEY_Root+HKEY_Path+HKEY_Key,"0");
		HKEY_Key="margin_right";
		Wsh.RegWrite(HKEY_Root+HKEY_Path+HKEY_Key,"0.15");
		HKEY_Key="margin_top";
		Wsh.RegWrite(HKEY_Root+HKEY_Path+HKEY_Key,"0.37");
		*/
	 }catch(e){}
}

function  resetPaging(){
 	try{
	  var Wsh=new ActiveXObject("WScript.Shell");
	  HKEY_Key="header";
	  Wsh.RegWrite(HKEY_Root+HKEY_Path+HKEY_Key,"&w&b页码,&p/&P");
	  HKEY_Key="footer";
	  Wsh.RegWrite(HKEY_Root+HKEY_Path+HKEY_Key,"&u&b&d");
		/*
		HKEY_Key="margin_bottom";
		Wsh.RegWrite(HKEY_Root+HKEY_Path+HKEY_Key,"0");
		HKEY_Key="margin_left";
		Wsh.RegWrite(HKEY_Root+HKEY_Path+HKEY_Key,"1.5");
		HKEY_Key="margin_right";
		Wsh.RegWrite(HKEY_Root+HKEY_Path+HKEY_Key,"1.5");
		HKEY_Key="margin_top";
		Wsh.RegWrite(HKEY_Root+HKEY_Path+HKEY_Key,"0");
		*/
	 }catch(e){}
}

function pageSetup(){
　　// 打印页面设置
	wb.execwb(8,1);
}

function printPreview(){
　　// 打印页面预览
	pagePrint.style.display="none";
	if(null!=fileList){
		fileList.style.display="block";
	}
　	wb.execwb(7,1);
	pagePrint.style.display="";
	if(null!=fileList){
		fileList.style.display="";
	}
}

function printPage(){
		pagePrint.style.display="none";
　		wb.execwb(6,1);
		pagePrint.style.display="";
		if(null!=fileList){
			fileList.style.display="";
		}
	
}

function closePage(){
		if(null!=window.opener){
			window.opener.location.reload(true);
		}
		window.close();
	}

clearPageing();
</script>
</head>
<body width="100%" style="margin-top:0px;">
	<OBJECT classid="CLSID:8856F961-340A-11D0-A96B-00C04FD705A2" height=0 id=wb name=wb width=0></OBJECT> 
	<div id="pagePrint" style="float:right;width:100px;">
	<div>
		<div class="mainbtn_title">
   		<div style="background:#ffffff">					
			<button onClick="pageSetup();" style="width:80px;height:25px;background:#e9e7ef;" >页面设置</button>
			<button onClick="printPreview();" style="width:80px;height:25px;background:#e9e7ef;">打印预览</button>
			<button onClick="printPage();" style="width:80px;height:25px;background:#e9e7ef;">打印</button>	
        </div>
    	</div>
	</div>
	</div>
	<div id="fileList" style="margin-top:0px;">
		${tpl}
	</div>
</body>
</html>
