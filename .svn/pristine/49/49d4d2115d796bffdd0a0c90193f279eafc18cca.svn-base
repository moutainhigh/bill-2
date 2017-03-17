<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/webpage/system/admin/taglib.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>票据详细信息</title>
<style type="text/css">
#zhengmian {
	background-image: url('${pageContext.request.contextPath}/webpage/busi/common/background.jpg'); 
	height:540px;
	background-repeat:repeat-y;
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
.main_contentdetail {
	border: solid 1px #666600;

}

#Layer1 {
	position:absolute;
	width:200px;
	height:115px;
	z-index:1;
	clip: rect(auto,30,auto,30);
	overflow: hidden;
	left: 17px;
	top: 10px;
}
.STYLE1 {font-size: 23px;}
.STYLE2 {font-family: "宋体"}
.STYLE3 {
	border: solid 1px #666600;
	font-family: "宋体";
	font-size: 12px;
}
.STYLE4 {border: solid 1px #666600; font-size: 12px; }
.STYLE6 {font-family: "宋体"; font-size: 12px; }
.STYLE7 {font-size: 12px}

table{
	line-height:20px;
}
table tr{
	background:none !important;
}
#navcontainer {
	margin: 0;
	padding: 0;
	height: 20px;
}

#navcontainer ul {
	border: 0;
	margin: 0;
	padding: 0;
	list-style-type: none;
	text-align: center;
}

#navcontainer ul li {
	display: block;
	float: left;
	text-align: center;
	padding: 0;
	margin: 0;
	line-height:27px;
}

#navcontainer ul li a {
	background: #fff;
	width: 100px;
	height: 25px;
	border-top: 1px solid #cccccc;
	border-left: 1px solid #cccccc;
	border-bottom: 1px solid #cccccc;
	border-right: 1px solid #cccccc;
	padding: 0px;
	margin: 0px;
	color: navy;
	text-decoration: none;
	display: block;
	text-align: center;
}

#navcontainer ul li a:hover {
	color: #000000;
	 background: lightsteelblue; 
}

/* #navcontainer a:active {
	background: lightsteelblue;
	color: #000000;
} */

#navcontainer li#active a {
	/* background: lightsteelblue; */
	border: 1px solid #75AEDB;
	color: #000000;
}
#tab3{background-image:url('/bbsp/webpage/busi/common/background.jpg');}
#tab3 #tabChild .main_contentdetail tr:not(:first-child) td{width:50%;}
</style>
<%@ include file="/webpage/system/admin/top.jsp"%>
<%@ include file="/webpage/system/admin/footer.jsp"%>
</head>

<body onload="myload();">
<div class="clearfix">
<div class="page-content" id="jump-content" style="padding:0px;">
<div id="content">
		<div style="margin: 8px 10px 6px;">						
			<div id="navcontainer">
				<ul id="navlist">
					<li id="active">
						<a href="javascript:void(0);">正面信息</a>
					</li>
					<li>
						<a href="javascript:void(0);">背面信息</a>
					</li>
				</ul>
		</div>
	</div>

<%-- style="background-image: url('${pageContext.request.contextPath}/bbsp/bc/billentireinfo/background.jpg');height:700px;" --%>
<div id="zhengmian">
<table id="tab1" align="center" style="padding:5px 50px;" ><tr><td>				
<table width="100%" align="center" >
<tr>
 <td>
	<img src="${pageContext.request.contextPath}/webpage/busi/common/ECDS_LOGO1.gif" id="ele-img" width="200" height="40" align="middle" longdesc="ECDS_LOGO.gif" /></td>
</tr>
</table>
<table width="800" border="0" align="center">
  <tr>
    <td align="center"><span class="STYLE1" id="billType"></span></td>
    <script>
     bills();
     function bills(){
  	   var billType=${elecBillBefore.billType};
  	   if(billType==1){
  	     document.getElementById("billType").innerHTML="电子银行承兑汇票";
  	   }else{
  	     document.getElementById("billType").innerHTML="电子商业承兑汇票";
  	   }
  	}
    </script>
  </tr>
</table>
<table width="800" border="0" align="center">
  <tr>
    <td><span class="STYLE7">出票日期：${elecBillBefore.issueDt}</span></td>
    <td><span class="STYLE7">票据状态：${elecBillBefore.curStatusName}</span><span id="states"></span></td>
  </tr>
  <tr>
    <td><span class="STYLE7">汇票到期：${elecBillBefore.dueDt}<span class="STYLE2"></span></span></td>
    <td><span class="STYLE7">票据号码：${elecBillBefore.billNo}</span></td>
  </tr>
</table>
<table width="800"  align="center" cellspacing="0"  class="main_contentdetail" style="border-collapse: collapse;">
  <tr>
    <td class="main_contentdetail" width="16" rowspan="3"><div align="center" class="STYLE7">出<br />
        <br />
    票<br />
    <br />
    人</div></td>
    <td width="103" class="STYLE4">全 称</td>
    <td width="267" class="STYLE4">${elecBillBefore.remitter}</td>
    <td class="main_contentdetail" width="37" rowspan="3"><div align="center" class="STYLE7">收<br />
        <br />
    款<br />
    <br />
    人</div></td>
    <td width="117" class="STYLE4">全 称</td>
    <td width="246" colspan="3" class="STYLE4">${elecBillBefore.payee}</td>
  </tr>
  <tr>
    <td width="103" class="STYLE4">账 号</td>
    <td width="267" class="STYLE4">${elecBillBefore.remitterAcct}</td>
    <td width="117" class="STYLE4">账 号</td>
    <td colspan="3" class="STYLE4">${elecBillBefore.payeeAcct}</td>
  </tr>
  <tr>
    <td width="103" class="STYLE4">开户银行</td>
    <td width="267" class="STYLE4">${elecBillBefore.remitterBank}</td>
    <td width="117" class="STYLE4">开户银行</td>
    <td colspan="3" class="STYLE4">${elecBillBefore.payeeBank}</td>
  </tr>
<tr>
    <td colspan="2" class="STYLE4">出 票 保 证 信 息</td>
    <td  class="main_contentdetail" colspan="6"><table width="100%" border="0" cellpadding="0" cellspacing="0">

	 <c:if test="${assuBillList!=null}">
	     <c:forEach items="${assuBillList}" var="assuBillInfo" varStatus="vs">
	        <c:if test="${assuBillInfo.assuType=='1'}">
      			<tr>
       	 			<td class="STYLE7">保证人名称：</td>
       	 			    <td class="STYLE7">${assuBillInfo.guartrName}</td>
       	 			
       				<td class="STYLE7">保证人地址:</td>
       				    <td class="STYLE7">${assuBillInfo.guartrAddr}</td>
       			
        			<td class="STYLE7">保证日期:</td>
        			    <td class="STYLE7">${assuBillInfo.warteeDt}</td>
     	 		</tr>
     	     </c:if>
     	     <c:if test="${assuBillInfo.assuType!='1'}" >
     	         <tr>
			     	 <td class="STYLE7">保证人名称：</td>
			         <td class="STYLE7">保证人地址：</td>
			         <td class="STYLE7">保证日期：</td>
		         </tr>
     	     </c:if>
     	 </c:forEach>
     </c:if>
     
     <c:if test="${empty assuBillList}">
     	<tr>
     	 <td class="STYLE7">保证人名称：</td>
         <td class="STYLE7">保证人地址：</td>
         <td class="STYLE7">保证日期：</td>
        </tr>
     </c:if>
    </table></td>
  </tr>

  <tr>
    <td colspan="2" class="STYLE4">票 据 金 额</td>
    <td  colspan="6" style="border-collapse: collapse;padding: 0px;">
   
    <table width="100%" cellpadding="0" cellspacing="0"  
	style="border-collapse: collapse;border-top: solid 0px #FF0000;border-left: 0px #FF0000 solid;border-right: 0px #FF0000 solid;border-bottom: 0px #FF0000 solid;">
      <tr>
        <td style="border-left: 0px #FF0000 solid;border-top: 0px #FF0000 solid;"width="40%" rowspan="2" align="center" class="STYLE4"><div align="left">人民币<br />
          （大写）<span id="billMoney"></span></div></td>
        <td style="border-top: 0px #FF0000 solid;"width="5%" align="center" class="STYLE4">十</td>
        <td style="border-top: 0px #FF0000 solid;"width="5%" align="center" class="STYLE4">亿</td>
        <td style="border-top: 0px #FF0000 solid;"width="5%" align="center" class="STYLE4">千</td>
        <td style="border-top: 0px #FF0000 solid;"width="5%" align="center" class="STYLE4">百</td>
        <td style="border-top: 0px #FF0000 solid;"width="5%" align="center" class="STYLE4">十</td>
        <td style="border-top: 0px #FF0000 solid;"width="5%" align="center" class="STYLE4">万</td>
        <td style="border-top: 0px #FF0000 solid;"width="5%" align="center" class="STYLE4">千</td>
        <td style="border-top: 0px #FF0000 solid;"width="5%" align="center" class="STYLE4">百</td>
        <td style="border-top: 0px #FF0000 solid;"width="5%" align="center" class="STYLE4">十</td>
        <td style="border-top: 0px #FF0000 solid;"width="5%" align="center" class="STYLE4">元</td>
        <td style="border-top: 0px #FF0000 solid;"width="5%" align="center" class="STYLE4">角</td>
        <td style="border-top: 0px #FF0000 solid;border-right: 0px #FF0000 solid;"width="5%" align="center" class="STYLE4">分</td>
      </tr>
      <tr>
        <td align="center" class="STYLE4" id="je12"><input type="hidden" id="hiddenje"/></td>
        <td align="center" class="STYLE4" id="je11">&nbsp;</td>
        <td align="center" class="STYLE4" id="je10">&nbsp;</td>
        <td align="center" class="STYLE4" id="je9">&nbsp;</td>
        <td align="center" class="STYLE4" id="je8">&nbsp;</td>
        <td align="center" class="STYLE4" id="je7">&nbsp;</td>
        <td align="center" class="STYLE4" id="je6">&nbsp;</td>
        <td align="center" class="STYLE4" id="je5">&nbsp;</td>
        <td align="center" class="STYLE4" id="je4">&nbsp;</td>
        <td align="center" class="STYLE4" id="je3">&nbsp;</td>
        <td align="center" class="STYLE4" id="je2">&nbsp;</td>
        <td align="center" class="STYLE4" id="je1">&nbsp;</td>
       <%-- <s:hidden value="%{getText('global.format.money',{bean.frontBean.billMoney})}" id="tempBillMoney"></s:hidden>--%>
       <%-- <s:hidden name="bean.frontBean.billMoney" id="tempBillMoney"></s:hidden> --%>
       <input type="hidden" id="tempBillMoney" value = "${elecBillBefore.billMoney}"/>
      </tr>
       <script type="text/javascript">
        var temp = ${'tempBillMoney'}.value.split(",").join('');
    	getMoney(temp);
    	function getMoney(obj){
			var aa = obj;
			document.getElementById("hiddenje").innerText=aa;
			var j2 = aa.substring(aa.length-2,aa.length-1);
			if(j2==".")
			{
				var j1 = aa.substring(aa.length-1);
				document.getElementById("je1").innerText = "0";
				document.getElementById("je2").innerText = j1;
				for(var i=0;i<aa.length-2;i++)
				{
					document.getElementById("je"+(i+3)).innerText=aa.substring(aa.length-(i+2),aa.length-(i+3));
				}
				if(aa.length<13)
				{
					document.getElementById("je"+(aa.length+1)).innerText="￥";
				}
			}else if(aa.substring(aa.length-3,aa.length-2)==".")
			{
				var j1 = aa.substring(aa.length-1);
				document.getElementById("je1").innerText = j1;
				document.getElementById("je2").innerText = j2;
				for(var i=0;i<aa.length-3;i++)
				{
					document.getElementById("je"+(i+3)).innerText=aa.substring(aa.length-(i+3),aa.length-(i+4));
				}
				if(aa.length<13)
				{
					document.getElementById("je"+(aa.length)).innerText="￥";
				}
			}else
			{
				document.getElementById("je1").innerText = "0";
				document.getElementById("je2").innerText = "0";
				for(var i=0;i<aa.length;i++)
				{
					document.getElementById("je"+(i+3)).innerText=aa.substring(aa.length-i,aa.length-(i+1));
				}
				if(aa.length<13)
				{
					document.getElementById("je"+(aa.length+3)).innerText="￥";
				}
			}		
		}	
    	var billMoney = convertCurrency(temp);
    	 function convertCurrency(currencyDigits) {
    			var MAXIMUM_NUMBER = 99999999999.99;
    			var CN_ZERO = "零";
    			var CN_ONE = "壹";
    			var CN_TWO = "贰";
    			var CN_THREE = "叁";
    			var CN_FOUR = "肆";
    			var CN_FIVE = "伍";
    			var CN_SIX = "陆";
    			var CN_SEVEN = "柒";
    			var CN_EIGHT = "捌";
    			var CN_NINE = "玖";
    			var CN_TEN = "拾";
    			var CN_HUNDRED = "佰";
    			var CN_THOUSAND = "仟";
    			var CN_TEN_THOUSAND = "万";
    			var CN_HUNDRED_MILLION = "亿";
    			//var CN_SYMBOL = "人民币";
    			var CN_DOLLAR = "元";
    			var CN_TEN_CENT = "角";
    			var CN_CENT = "分";
    			var CN_INTEGER = "整";
    			var integral; 
    			var decimal; 
    			var outputCharacters;
    			var parts;
    			var digits, radices, bigRadices, decimals;
    			var zeroCount;
    			var i, p, d;
    			var quotient, modulus;
    			var befStr="";
    			currencyDigits = currencyDigits.toString();
    		    if(currencyDigits!=""){
    		    var strSp=currencyDigits.split(",");
    		    if(strSp.length>0){
    		    	for(var i=0;i<strSp.length;i++){
    		    		befStr+=strSp[i];
    		    	}
    		    	currencyDigits=befStr;
    		    }
    			var pointPosition=currencyDigits.indexOf(".");
    			var afterPointStr=currencyDigits.substring(pointPosition+1,currencyDigits.length);
    			var beforePointStr=currencyDigits.substring(0,pointPosition);
    			if(pointPosition!=-1){
    				
    			if (currencyDigits == "") {
    				alert("不能为空!");
    				return "";
    			}
    			if (currencyDigits.match(/[^,.\d]/) != null) {
    				alert("非法字符!");
    				document.getElementById("billMoney").value="";
    				return "";
    			}
    			if ((currencyDigits).match(/^((\d{1,3}(,\d{3})*(.((\d{3},)*\d{1,3}))?)|(\d+(.\d+)?))$/) == null) {
    				alert("格式不正确!");
    				document.getElementById("billMoney").value="";
    				return "";
    			}
    			currencyDigits = currencyDigits.replace(/,/g, "");
    			currencyDigits = currencyDigits.replace(/^0+/, "");
    			if (Number(currencyDigits) > MAXIMUM_NUMBER) {
    				alert("数字长度太长!");
    				document.getElementById("billMoney").value="";
    				return "";
    			}
    			parts = currencyDigits.split(".");
    			if (parts.length > 1) {
    				integral = parts[0];
    				decimal = parts[1];
    				decimal = decimal.substr(0, 2);
    			}else {
    				integral = parts[0];
    	            decimal = "";
    			}
    			digits = new Array(CN_ZERO, CN_ONE, CN_TWO, CN_THREE, CN_FOUR, CN_FIVE, CN_SIX, CN_SEVEN, CN_EIGHT, CN_NINE);
    			radices = new Array("", CN_TEN, CN_HUNDRED, CN_THOUSAND);
    			bigRadices = new Array("", CN_TEN_THOUSAND, CN_HUNDRED_MILLION);
    			decimals = new Array(CN_TEN_CENT, CN_CENT);
    			outputCharacters = "";
    			if (Number(integral) > 0) {
    				zeroCount = 0;
    				for (i = 0; i < integral.length; i++) {
    					p = integral.length - i - 1;
    					d = integral.substr(i, 1);
    					quotient = p / 4;
    					modulus = p % 4;
    					if (d == "0") {
    						zeroCount++;
    					}else {
    						if (zeroCount > 0){
    							outputCharacters += digits[0];
    						}

    						zeroCount = 0;
    						outputCharacters += digits[Number(d)] + radices[modulus];
    					}
    						if (modulus == 0 && zeroCount < 4) {

    						outputCharacters += bigRadices[quotient];
    					}

    			}
    			outputCharacters += CN_DOLLAR;
    			}
    				if (decimal != "") {
    				var decimalStr= decimal.substr(0,decimal.length);
    			  if(decimalStr =="" || decimalStr=="0" || decimalStr=="00"){
    			     decimal="";
    			  }else{//小数点末尾以0结尾
    			  	var subDecimal=decimal.substr(decimal.length-1,decimal.length);
    				if(subDecimal=="" || subDecimal=="0"){
    				    decimal=decimal.substr(0,1);
    				}
    					for (i = 0; i < decimal.length; i++) {
    					d = decimal.substr(i, 1);
    					if (d=="0") {
    						outputCharacters+="零";
    					}else{
    					    outputCharacters += digits[Number(d)] + decimals[i];
    					}
    				}
    			}
    		}
    			if (outputCharacters == "") {
    				outputCharacters = CN_ZERO + CN_DOLLAR;
    			}
    			if (decimal == "") {
    				outputCharacters += CN_INTEGER;
    			}
    		   var separator = ",";
    		 	var re=/(\d{3})\B/g;
    	        var strLength=currencyDigits.length;
    		 	var dataNum="";
    		 	var findPoint=currencyDigits.indexOf(".");
    	                var a=currencyDigits.length%3;
    	                var b=currencyDigits.length/3;
    	            
    	          if(findPoint<0){
    	             if(currencyDigits.length%3==0){
    	                 if(b<=1){
    	                     dataNum=currencyDigits;
    	                }else{
    	                    for(var i=0;i<b;i++){
    	                   var points=currencyDigits.substring((b-i)*3,strLength);
    	                  dataNum=currencyDigits.substring(0,(b-i)*3)+points.split('').reverse().join('').replace(/(\d{3})/g, '$1,').split('').reverse().join('');
    	              }               
    	}
    	             }else{
    	               dataNum=currencyDigits.split('').reverse().join('').replace(/(\d{3})/g, '$1,').split('').reverse().join('');
    	            }                  
    	          }else{
    		          var beforePoint=currencyDigits.substring(0,findPoint);
    		 	      var afterPoint=currencyDigits.substring(findPoint+1,strLength);
    	                   var c=beforePoint.length/3;
    	                  
    	              if(beforePoint.length%3==0){
    	                   if(c<=1){dataNum=beforePoint+"."+afterPoint.replace(re,"$1" + separator);}
    	                   else{
    	                     for(var j=0;j<c;j++){
    	                         var ccc=beforePoint.substring((c-j)*3,beforePoint.length);  
    	                         dataNum=beforePoint.substring(0,(c-j)*3)+ccc.split('').reverse().join('').replace(/(\d{3})/g, '$1,').split('').reverse().join('')+"."+afterPoint.replace(re,"$1" + separator);
    	                      }
    	                  
    						}
    	              }else{
    	                 if(beforePoint.length<=6){
    	                  dataNum=beforePoint.substring(0,3)+"."+afterPoint.replace(re,"$1" + separator);
    	                    }
    	                 dataNum=beforePoint.split('').reverse().join('').replace(/(\d{3})/g, '$1,').split('').reverse().join('')+"."+afterPoint.replace(re,"$1" + separator);
    	             }
    	          }
    				 outputCharacters=outputCharacters;
    				return outputCharacters;
    			}else{
    					 outputCharacters="";
    				}
    			}		
    		}
    	document.getElementById("billMoney").innerHTML="<span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+billMoney+"</span>";
    </script>
    </table></td>
  </tr>
  <tr>
    <td colspan="2" class="STYLE4">承 兑 人 信 息</td>
    <td colspan="6"  style="padding: 0px;border-collapse: collapse;"><table width="100%" border="0"  cellspacing="0" style="border-collapse: collapse;">
      <tr>
        <td  style="border-left: 0px #FF0000 solid;border-top: 0px #FF0000 solid;" width="11%" class="STYLE4">全 称</td>
        <td   style="border-left: 0px #FF0000 solid;border-top: 0px #FF0000 solid;" width="34%" class="STYLE4">${elecBillBefore.acceptor}</td>
        <td  style="border-left: 0px #FF0000 solid;border-top: 0px #FF0000 solid;" width="17%" class="STYLE4">开户行行号</td>
        <td  style="border-left: 0px #FF0000 solid;border-top: 0px #FF0000 solid;border-right: 0px #FF0000 solid;" width="38%" class="STYLE4">${elecBillBefore.acceptorBankNo}</td>
      </tr>
      <tr>
        <td style="border-left: 0px #FF0000 solid;border-bottom: 0px #FF0000 solid;"class="STYLE4">账 号</td>
        <td style="border-left: 0px #FF0000 solid;border-bottom: 0px #FF0000 solid;"class="STYLE4">${elecBillBefore.acceptorAcct}</td>
        <td style="border-left: 0px #FF0000 solid;border-bottom: 0px #FF0000 solid;"class="STYLE4">开户行名称</td>
        <td style="border-left: 0px #FF0000 solid;border-bottom: 0px #FF0000 solid;border-right: 0px #FF0000 solid;"class="STYLE4">${elecBillBefore.acceptor}</td>
      </tr>
    </table></td>
  </tr>
  
  <tr>
    <td colspan="2" class="STYLE4">交 易 合 同 号</td>
    <td class="STYLE4">${elecBillBefore.conferNo}</td>
    <td rowspan="2" class="main_contentdetail"><div align="center" class="STYLE7">承<br />
      兑<br />
      信<br />
    息</div></td>
    <td colspan="4" class="STYLE4">出票人承诺：本汇票请予以承兑，到期无条件付款</td>
  </tr>
  <tr>
    <td colspan="2" class="STYLE4">能 否 转 让</td>
    <td class="STYLE4">${fns:getDictLabel('K_FORBIDFLAG',elecBillBefore.banEndorsementMark)}</td>
    <td colspan="4" class="STYLE3">承兑人承兑：本汇票已经承兑，到期无条件付款<br /> <br/>
      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;承兑日期&nbsp;${elecBillBefore.acceptDt}</td>
  </tr>
<tr>
    <td colspan="2" class="STYLE4">承 兑 保 证 信 息</td>
    <td class="main_contentdetail" colspan="6"><table width="100%" border="0" cellspacing="0">
    	<c:if test="${assuBillList !=null}">
    	    <c:forEach items="${assuBillList}" var="assuBillInfo" varStatus="vs">
    	        <c:if test="${assuBillInfo.assuType=='2'}">
	    			<tr>
		         	 	<td class="STYLE4">保证人名称：</td>
		         	 	      <td class="STYLE4">${assuBillInfo.guartrName}</td>
		         	 	
		          		<td class="STYLE4">保证人地址：</td>
		          			 <td class="STYLE4">${assuBillInfo.guartrAddr}</td>
		          
		          		<td class="STYLE4">保证日期：</td>
		          			 <td class="STYLE4">${assuBillInfo.warteeDt}</td>
	        		</tr>
	            </c:if>
	            <c:if test="${assuBillInfo.assuType!='2'}">
	       	        <tr>
		     	 		<td class="STYLE7">保证人名称：</td>
		        	    <td class="STYLE7">保证人地址：</td>
		        	 	<td class="STYLE7">保证日期：</td>
	       		    </tr>
                </c:if>
       	     </c:forEach>
       	 </c:if>
       	 <c:if test="${empty assuBillList }">
       	 	<tr>
     	 		<td class="STYLE7">保证人名称：</td>
        	    <td class="STYLE7">保证人地址：</td>
        	 	<td class="STYLE7">保证日期：</td>
       		 </tr>
       	 </c:if>
  
    </table></td>
  </tr>
  <tr>
    <td colspan="2" rowspan="2" class="STYLE4">评级信息（由出票人，承兑人自己记载，仅供参考）</td>
    <td class="STYLE4">出 票 人</td>
    <td class="main_contentdetail" colspan="3"><table width="100%" border="0" cellspacing="0">
      <tr>
        <td class="STYLE7">评级主体：${elecBillBefore.remitterCreditAgency}</td>
        <td class="STYLE7">信用等级：${elecBillBefore.remitterCreditClass}</td>
        <td class="STYLE7">评级到期日：${elecBillBefore.remitterCreditDueDt}</td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td class="STYLE4">承 兑 人</td>
    <td class="main_contentdetail" colspan="3"><table width="100%" border="0" cellspacing="0" >
      <tr>
        <td class="STYLE7">评级主体：${elecBillBefore.acceptorCreditAgency}</td>
        <td class="STYLE7">信用等级：${elecBillBefore.acceptorCreditClass}</td>
        <td class="STYLE7">评级到期日：${elecBillBefore.acceptorCreditDueDt}</td>
      </tr>
    </table></td>
  </tr>
</table>
</td></tr></table>
<%-- 背面信息 start --%>
<table id="tab3" align="center"><tr><td>
<table id="tabChild" width="884" border="1" align="center" cellspacing="0" class="main_contentdetail" style="margin:10px 6px;">
  <c:forEach items="${elecBillBack}" var="endoBean" varStatus="vs">
  	<c:if test="${endoBean.endoType=='0'}">
 	 <tr><td>
		    <table  border="1" align="center" cellspacing="0" class="main_contentdetail" width="100%">
				<tr>
				   <br><br> <td colspan="2"><div align="center">转让背书</div></td>
				  </tr>
				  <tr>
				    <td>背书人名称</td>
				    <td>${endoBean.fromName}</td>
				  </tr>
				  <tr>
				    <td>被背书人名称</td>
				    <td>${endoBean.toName}</td>
				  </tr>
				  <tr>
				   <td>不得转让标记</td>
				       <td>${fns:getDictLabel('K_FORBIDFLAG',endoBean.protEndors)}</td>
				  </tr>
				  <tr>
				    <td>背书日期</td>
				    <td>${endoBean.endoDate}</td>
				  </tr>
				  <tr>
				    <td colspan="2"></td>
				  </tr>
			</table>
	  </td></tr>
	 </c:if>
	 <c:if test="${endoBean.endoType=='8'}">
	  <tr><td>
		    <table  border="1" align="center" cellspacing="0" class="main_contentdetail" width="100%">
				  <tr>
				    <br><br><td colspan="2"><div align="center">保证</div></td>
				  <tr>
				    <td>被保证人名称<br><br></td>
				    <td>${endoBean.fromName}</td>
				  </tr>
				  <tr>
				    <td>保证人名称<br><br></td>
				    <td>${endoBean.toName}</td>
				  </tr>
				  <tr>
				    <td>保证人地址<br><br></td>
				    <td>${endoBean.assuAdrr}</td>
				  </tr>
				  <tr>
				    <td>保证日期<br><br></td>
				    <td>${endoBean.endoDate}</td>
				  </tr>
				  <tr>
				    <td colspan="2">&nbsp;<br><br></td>
				  </tr>
			</table>
	  </td></tr>
	 </c:if>
	 <c:if test="${endoBean.endoType=='9'}">
	  <tr><td>
		   <table  border="1" align="center" cellspacing="0" class="main_contentdetail" width="100%">
				  <tr>
				   <br><br> <td colspan="2"><div align="center">质押背书</div></td>
				  </tr>
				  <tr>
				    <td>出质人名称<br><br></td>
				    <td>${endoBean.fromName}</td>
				  </tr>
				  <tr>
				    <td>质权人名称<br><br></td>
				    <td>${endoBean.toName}</td>
				  </tr>
				  <tr>
				    <td>出质日期<br><br></td>
				    <td>${endoBean.endoDate}</td>
				  </tr>
				  <tr>
				    <td>质押解除日期<br><br></td>
				    <td>${endoBean.signDate}</td>
				  </tr>
				  <tr>
				    <td colspan="2"><br><br></td>
				  </tr>
			</table>
	  <br><br></td></tr>
	 </c:if>
	 <c:if test="${endoBean.endoType=='1'}">
	   <tr><td>
		    <table border="1" cellspacing="0" class="main_contentdetail" width="100%" align="center">
				  <tr>
				    <br><br><td colspan="2"><div align="center">买断式贴现背书</div></td>
				  </tr>
				  <tr>
				    <td>背书人名称</td>
				    <td>${endoBean.fromName}</td>
				  </tr>
				  <tr>
				    <td>被背书人名称</td>
				    <td>${endoBean.toName}</td>
				  </tr>
				  <tr>
				    <td>不得转让标记</td>
				       <td>${fns:getDictLabel('K_FORBIDFLAG',endoBean.protEndors)}</td>
				  </tr>
				  <tr>
				    <td>背书日期</td>
				    <td>${endoBean.endoDate}</td>
				  </tr>
				  <tr>
				    <td colspan="2">&nbsp;</td>
				  </tr>
			 </table>
	   </td></tr>
	 </c:if>
	 <c:if test="${endoBean.endoType=='1_1'}">
	  <tr><td>
		   <table border="1" cellspacing="0" class="main_contentdetail" width="100%" align="center">
				  <tr>
				   <br><br> <td colspan="2"><div align="center">回购式贴现背书</div></td>
				  </tr>
				  <tr>
				    <td>背书人名称</td>
				    <td>${endoBean.fromName}</td>
				  </tr>
				  <tr>
				    <td>被背书人名称</td>
				    <td>${endoBean.toName}</td>
				  </tr>
				  <tr>
				    <td>背书日期</td>
				    <td>${endoBean.endoDate}</td>
				  </tr>
				  <tr>
				    <td>赎回开放日</td>
				    <td>${endoBean.redeemOpenDate}</td>
				  </tr>
				  <tr>
				    <td>赎回截止日</td>
				    <td>${endoBean.redeemEndDate}</td>
				  </tr>
				  <tr>
				    <td colspan="2">&nbsp;</td>
				  </tr>
			</table>
	  </td></tr>
	 </c:if>
	 <c:if test="${endoBean.endoType=='2'}">
	  <tr><td>
		  	<table border="1" cellspacing="0" class="main_contentdetail" width="100%" align="center">
				  <tr>
				    <br><br><td colspan="2"><div align="center">回购式贴现赎回背书</div></td>
				  </tr>
				  <tr>
				    <td>背书人名称</td>
				    <td>${endoBean.fromName}</td>
				  </tr>
				  <tr>
				    <td>被背书人名称</td>
				    <td>${endoBean.toName}</td>
				  </tr>
				  <tr>
				    <td>不得转让标记</td>
				       <td>${fns:getDictLabel('K_FORBIDFLAG',endoBean.protEndors)}</td>
				  </tr>
				  <tr>
				    <td>背书日期</td>
				    <td>${endoBean.endoDate}</td>
				  </tr>
				  <tr>
				    <td colspan="2">&nbsp;</td>
				  </tr>
			  </table>
	  </td></tr>
	 </c:if>
	 <c:if test="${endoBean.endoType=='3'}">
	  <tr><td>
			  <table  border="1" cellspacing="0" class="main_contentdetail" width="100%" align="center">
				  <tr>
				    <br><br><td colspan="2"><div align="center">买断式转贴现背书</div></td>
				  </tr>
				  <tr>
				    <td>背书人名称</td>
				    <td>${endoBean.fromName}</td>
				  </tr>
				  <tr>
				    <td>被背书人名称</td>
				    <td>${endoBean.toName}</td>
				  </tr>
				  <tr>
				    <td>不得转让标记</td>
				       <td>${fns:getDictLabel('K_FORBIDFLAG',endoBean.protEndors)}</td>
				  </tr>
				  <tr>
				    <td>背书日期</td>
				    <td>${endoBean.endoDate}</td>
				  </tr>
				  <tr>
				    <td colspan="2">&nbsp;</td>
				  </tr>
			  </table>
	  </td></tr>
	 </c:if>
	 <c:if test="${endoBean.endoType=='3_1'}">
	  <tr><td>
		  <table  border="1" cellspacing="0" class="main_contentdetail" width="100%" align="center">
			  <tr>
			    <br><br><td colspan="2"><div align="center">回购式转贴现背书</div></td>
			  </tr>
			  <tr>
			    <td>背书人名称</td>
			    <td>${endoBean.fromName}</td>
			  </tr>
			  <tr>
			    <td>被背书人名称</td>
			    <td>${endoBean.toName}</td>
			  </tr>
			  <tr>
			    <td>背书日期</td>
			    <td>${endoBean.endoDate}</td>
			  </tr>
			  <tr>
			    <td>赎回开放日</td>
			    <td>${endoBean.redeemOpenDate}</td>
			  </tr>
			  <tr>
			    <td>赎回截止日</td>
			    <td>${endoBean.redeemEndDate}</td>
			  </tr>
			  <tr>
			    <td colspan="2">&nbsp;</td>
			  </tr>
		  </table>
	 </td></tr>
	</c:if>
	<c:if test="${endoBean.endoType=='4'}">
     <tr><td>
		  <table  border="1" cellspacing="0" class="main_contentdetail" width="100%" align="center">
			  <tr>
			    <br><br><td colspan="2"><div align="center">回购式转贴现赎回背书</div></td>
			  </tr>
			  <tr>
			    <td>背书人名称</td>
			    <td>${endoBean.fromName}</td>
			  </tr>
			  <tr>
			    <td>被背书人名称</td>
			    <td>${endoBean.toName}</td>
			  </tr>
			  <tr>
			    <td>不得转让标记</td>
				       <td>${fns:getDictLabel('K_FORBIDFLAG',endoBean.protEndors)}</td>
			  </tr>
			  <tr>
			    <td>背书日期</td>
			    <td>${endoBean.endoDate}</td>
			  </tr>
			  <tr>
			    <td colspan="2">&nbsp;</td>
			  </tr>
	     </table>
     </td></tr>
    </c:if>
    <c:if test="${endoBean.endoType=='5'}">
	 <tr><td>
		  <table border="1" cellspacing="0" class="main_contentdetail" width="100%" align="center">
			  <tr>
			   <br><br> <td colspan="2"><div align="center">买断式再贴现背书</div></td>
			  </tr>
			  <tr>
			    <td>背书人名称</td>
			    <td>${endoBean.fromName}</td>
			  </tr>
			  <tr>
			    <td>被背书人名称</td>
			    <td>${endoBean.toName}</td>
			  </tr>
			  <tr>
			    <td>不得转让标记</td>
				       <td>${fns:getDictLabel('K_FORBIDFLAG',endoBean.protEndors)}</td>
			  </tr>
			  <tr>
			    <td>背书日期</td>
			    <td>${endoBean.endoDate}</td>
			  </tr>
			  <tr>
			    <td colspan="2">&nbsp;</td>
			  </tr>
		  </table>
	 </td></tr>
	</c:if>
	<c:if test="${endoBean.endoType=='5_1'}">
	 <tr><td>
		   <table border="1" cellspacing="0" class="main_contentdetail" width="100%" align="center">
			  <tr>
			    <br><br><td colspan="2"><div align="center">赎回式再贴现背书</div></td>
			  </tr>
			  <tr>
			    <td>背书人名称</td>
			    <td>${endoBean.fromName}</td>
			  </tr>
			  <tr>
			    <td>被背书人名称</td>
			    <td>${endoBean.toName}</td>
			  </tr>
			  <tr>
			    <td>背书日期</td>
			    <td>${endoBean.endoDate}</td>
			  </tr>
			  <tr>
			    <td>赎回开放日</td>
			    <td>${endoBean.redeemOpenDate}</td>
			  </tr>
			  <tr>
			    <td>赎回截止日</td>
			    <td>${endoBean.redeemEndDate}</td>
			  </tr>
			  <tr>
			    <td colspan="2"></td>
			  </tr>
	  	 </table>
     </td></tr>
    </c:if>
    <c:if test="${endoBean.endoType=='6'}">
     <tr><td>
		  <table border="1" cellspacing="0" class="main_contentdetail" width="100%" align="center">
			  <tr>
			    <br><br><td colspan="2"><div align="center">回购式再贴现赎回背书</div></td>
			  </tr>
			  <tr>
			    <td>背书人名称</td>
			    <td>${endoBean.fromName}</td>
			  </tr>
			  <tr>
			    <td>被背书人名称</td>
			    <td>${endoBean.toName}</td>
			  </tr>
			  <tr>
			    <td>不得转让标记</td>
				       <td>${fns:getDictLabel('K_FORBIDFLAG',endoBean.protEndors)}</td>
			  </tr>
			  <tr>
			    <td>背书日期</td>
			    <td>${endoBean.endoDate}</td>
			  </tr>
			  <tr>
			    <td colspan="2">&nbsp;</td>
			  </tr>
		   </table>
	  </td></tr>
	 </c:if>
     <c:if test="${endoBean.endoType=='7'}">
	  <tr><td>
		    <table border="1" cellspacing="0" class="main_contentdetail" width="100%" align="center">
			  <tr>
			    <br><br><td colspan="2"><div align="center">央行卖出商业汇票</div></td>
			  </tr>
			  <tr>
			    <td>背书人名称</td>
			    <td>${endoBean.fromName}</td>
			  </tr>
			  <tr>
			    <td>被背书人名称</td>
			    <td>${endoBean.toName}</td>
			  </tr>
			  <tr>
			    <td>不得转让标记</td>
				       <td>${fns:getDictLabel('K_FORBIDFLAG',endoBean.protEndors)}</td>
			  </tr>
			  <tr>
			    <td>背书日期</td>
			    <td>${endoBean.endoDate}</td>
			  </tr>
			  <tr>
			    <td colspan="2">&nbsp;</td>
			  </tr>
		    </table>
	  </td></tr>
	 </c:if>
	  <c:if test="${endoBean.endoType=='10'}">
     <tr><td>
        <table border="1" cellspacing="0" class="main_contentdetail" width="100%" align="center">
		  <tr>
		   <br><br> <td colspan="2"><div align="center">提示付款</div></td>
		  </tr>
		  <tr>
		    <td>提示付款人名称</td>
		    <td>${endoBean.fromName}</td>
		  </tr>
		  <tr>
		    <td>提示付款日期</td>
		    <td>${endoBean.endoDate}</td>
		  </tr>
		  <tr>
		    <td>付款或拒付</td>
		    <td>
		  		<c:if test="${endoBean.payFlag=='0'}">
		  			付款
		  		</c:if>
		  		<c:if test="${endoBean.payFlag=='1'}">
		  			拒付
		  		</c:if>
		    </td>
		  </tr>
		  <tr>
		    <td>付款或拒付日期</td>
		    <td>${endoBean.signDate}</td>
		  </tr>
		  <tr>
		    <td>拒付理由</td>
		    <td>
		    	<c:if test="${endoBean.payFlag=='1'}">
		    		${endoBean.payRefuReson}&nbsp;
		    	</c:if>
		    	
		    </td>
		  </tr>
		  <tr>
		    <td colspan="2">&nbsp;</td>
		  </tr>
	    </table>
	 </td></tr>
	 </c:if>
	 <c:if test="${endoBean.endoType=='11'}">
	   <tr><td>
		    <table border="1" cellspacing="0" class="main_contentdetail " width="100%" align="center">
				  <tr>
				    <br><br><td colspan="2"><div align="center">追索清偿</div></td>
				  </tr>
				  <tr>
				    <td>追索人名称</td>
				    <td>${endoBean.fromName}</td>
				  </tr>
				  <tr>
				    <td>清偿人名称</td>
				    <td>${endoBean.toName}</td>
				  </tr>
				  <tr>
				    <td>追索日期</td>
				    <td>${endoBean.endoDate}</td>
				  </tr>
				  <tr>
				   <td>追索类型</td>
				      <c:if test="${endoBean.recType=='1'}">
				       <td>非拒付追索</td>
				      </c:if>
				      <c:if test="${endoBean.recType=='0'}">
				       <td>拒付追索</td>
				      </c:if>
				  </tr>
				  <tr>
				    <td>清偿日期</td>
				    <td>${endoBean.signDate}</td>
				  </tr>
				  <tr>
				    <td colspan="2">&nbsp;</td>
				  </tr>
			 </table>
	   </td></tr>
	 </c:if>
</c:forEach>
</table>
</td></table>
</div>
<%-- 背面信息 end --%>
</div>
</div>
</div>
<script>
	function myload() {
		document.getElementById("tab1").style.display="block";
		document.getElementById("tab3").style.display="none";
	}
	changeTab();
	function changeTab(){
		var ul=document.getElementById("navlist");
		var oli=document.getElementsByTagName("li");
		var oA=document.getElementsByTagName("a");
		 oA[0].style.background="lightsteelblue";
		for(var i=0;i<oli.length;i++){
			oli[i].index=i;
			oli[i].onclick=function(){
				var index=this.index;
				if(this.index==0){
		    		document.getElementById("tab1").style.display="block"; 
				    document.getElementById("tab3").style.display="none"; 
				    oA[index].style.background="lightsteelblue";
				    oA[index+1].style.background="";
				}else if(this.index==1){
				    document.getElementById("tab3").style.display="block"; 
				    document.getElementById("tab1").style.display="none"; 
				    oA[index].style.background="lightsteelblue";
				    oA[index-1].style.background="";
				}
			}
		}
	}	
</script>
</body>
</html>

