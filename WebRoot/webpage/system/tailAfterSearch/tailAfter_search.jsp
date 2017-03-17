<%-- 
 * 文件名称: tailAfter_search.jsp
 * 系统名称: 票据管理平台
 * 模块名称: 跟踪查询信息
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: @version2.0.0.1
 * 开发人员: wzc
 * 开发时间: 2016-12-20
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
		<%-- jsp文件头和头部 --%>
		<%@ include file="/webpage/system/admin/top.jsp"%> 
		 <style type="text/css">  
        nodeSize
        {
            position: absolute;
            width: 80px;
            height: 30px;           
            top: 0px;
            
            z-index: 0;
        }
         .nodeSize{
        	display:inline-block;
        	text-align:center;
        	line-height:45px;
        }
         
         #zhushi{
        	width:205px;
        	height:138px;
         	position: relative;
         	top:-767px;
         	left:27px;
         	
         }
         .nodeInfo {
         position: absolute;
         top:-120px;
         left:-94px;
	     width: 280px;
		 height: auto;
		 background: palegoldenrod no-repeat;		
		 text-align: center;
		 padding: 20px 12px 10px;
		 h6-style: normal;
		 z-index: 2;		   
		 display:none; 
		 border-radius: 15px;		
		 }
		  ul,li{
		 	list-style: none;
		 	margin-bottom: 0px;
		 	text-align:justify;
		 }
		
        </style>  
        	<script type="text/javascript">
        	function myBrowser(){
		    var userAgent = navigator.userAgent; //取得浏览器的userAgent字符串
		    var isOpera = userAgent.indexOf("Opera") > -1;
		    if (isOpera) {
		        return "Opera"
		    }; //判断是否Opera浏览器
		    if (userAgent.indexOf("Firefox") > -1) {
		        return "FF";
		    } //判断是否Firefox浏览器
		    if (userAgent.indexOf("Chrome") > -1){
			  return "Chrome";
			 }
		    if (userAgent.indexOf("Safari") > -1) {
		        return "Safari";
		    } //判断是否Safari浏览器
		    if (userAgent.indexOf("compatible") > -1 && userAgent.indexOf("MSIE") > -1 && !isOpera) {
		        return "IE";
		    }; //判断是否IE浏览器
		}
        function createGigrph(myscript){
        			
 	            //用来存储节点的顺序
 	            var connections = [];
 	            var shapes = [];
 	            //创建绘图对象
 	            var r = Raphael("holder", 1600, 800);//这里如何设置为窗口宽度,因为有滚轴,则窗口宽度以后的图形展示不出来。
 	            eval(myscript);
 	            $("path").attr("stroke-width","1.5");
	    		
 	        }      
       //随着节点位置的改变动态改变箭头
         Raphael.fn.drawArr = function (obj) {
             var point = getStartEnd(obj.obj1, obj.obj2);
             var path1 = getArr(point.start.x, point.start.y, point.end.x, point.end.y,8);
             if (obj.arrPath) {
                 obj.arrPath.attr({ path: path1});
            
             } else {
                 if(obj.obj1.attr("fill")=="pink" ){
	       	 		 obj.arrPath = this.path(path1).attr({stroke:"red"});
	       	 		 
	       	 	} else{
	       	 		 obj.arrPath = this.path(path1).attr({stroke:"#595959"});
	       	 	 }
             }
             return obj;
         };
         Raphael.fn.drawBackArr = function (obj) {
             var bb1 = obj.obj1.getBBox(),
             bb2 = obj.obj2.getBBox();
             var x1 = bb1.x + bb1.width / 2;
        	 var y1 = bb1.y + bb1.height;
        	 var x4 = bb2.x + bb2.width / 2;
        	 var y4 = bb2.y + bb2.height;
        	 var x2=x1;
        	 var y2=y1+Math.abs((y4-y1))+20;
        	 var x3=x4;
        	 var y3=y2;
        	 var pathStr=["M",x1-8,y1,x2-8,y2,"M",x2-8,y2,x3,y3,"M",x3,y3,x4,y4];
        	 var path;
        	 if($("rect[fill='#ffc0cb']").attr('x') == bb1.x  && $("rect[fill='#ffc0cb']").attr('y')==bb1.y){
	       	 		path = this.path(pathStr).attr({stroke:"red"});
	       	 }else{
	       	 	path = this.path(pathStr).attr({stroke:"#595959"});
	       	 }
        	 var path1 = getArr(x3, y3,x4, y4,  8);
             if($("rect[fill='#ffc0cb']").attr('x') == bb1.x  && $("rect[fill='#ffc0cb']").attr('y')==bb1.y){
	       	 		this.path(path1).attr({stroke:"red"});
	       	 } else{
	       	 		this.path(path1).attr({stroke:"#595959"});
	       	 }
         };
        function getArr(x1, y1, x2, y2, size) {
            var angle = Raphael.angle(x1, y1, x2, y2);//得到两点之间的角度
            var a45 = Raphael.rad(angle - 45);//角度转换成弧度
            var a45m = Raphael.rad(angle + 45);
            var x2a = x2 + Math.cos(a45) * size;
            var y2a = y2 + Math.sin(a45) * size;
            var x2b = x2 + Math.cos(a45m) * size;
            var y2b = y2 + Math.sin(a45m) * size;
            var result = ["M", x1, y1, "L", x2, y2, "L", x2a, y2a, "M", x2, y2, "L", x2b, y2b];
           
            return result;
        }
         function getStartEnd(obj1, obj2) {
             var bb1 = obj1.getBBox(),
                 bb2 = obj2.getBBox();
             var p = [
                     { x: bb1.x + bb1.width / 2, y: bb1.y - 1 },
                     { x: bb1.x + bb1.width / 2, y: bb1.y + bb1.height + 1 },
                     { x: bb1.x - 1, y: bb1.y + bb1.height / 2 },
                     { x: bb1.x + bb1.width + 1, y: bb1.y + bb1.height / 2 },
                     { x: bb2.x + bb2.width / 2, y: bb2.y - 1 },
                     { x: bb2.x + bb2.width / 2, y: bb2.y + bb2.height + 1 },
                     { x: bb2.x - 1, y: bb2.y + bb2.height / 2 },
                     { x: bb2.x + bb2.width + 1, y: bb2.y + bb2.height / 2 }
                 ];
             var d = {}, dis = [];
             for (var i = 0; i < 4; i++) {
                 for (var j = 4; j < 8; j++) {
                     var dx = Math.abs(p[i].x - p[j].x),
                         dy = Math.abs(p[i].y - p[j].y);
                     if (
                          (i == j - 4) ||
                          (((i != 3 && j != 6) || p[i].x < p[j].x) &&
                          ((i != 2 && j != 7) || p[i].x > p[j].x) &&
                          ((i != 0 && j != 5) || p[i].y > p[j].y) &&
                          ((i != 1 && j != 4) || p[i].y < p[j].y))
                        ) {
                         dis.push(dx + dy);
                         d[dis[dis.length - 1]] = [i, j];
                     }
                 }
             }
             if (dis.length == 0) {
                 var res = [0, 4];
             } else {
                 res = d[Math.min.apply(Math, dis)];
             }
             var result = {};
             result.start = {};
             result.end = {};
             result.start.x = p[res[0]].x;
             result.start.y = p[res[0]].y;
             result.end.x = p[res[1]].x;
             result.end.y = p[res[1]].y;
             return result;
         }
        </script>
	</head>
<body>
	<div class="clearfix">
		<div id="page-content" class="page-content">
			<%-- 查询区  --%>
			<div id="header">
				<form action="<%=basePath%>tailAfterSearchController.do?method=search" method="post" id="searchForm" name="searchForm" style="padding:0px;" class="form-search">
					<div class="row-fluid">
						<label class="no-padding-right" for="billNo"><span class="star">*</span>票号</label>
						<input class="input-medium" type="text" name="billNo" valid="required" value="${query.billNo}" placeholder="请输入票号" />
						<a class="btn-mini" id="search" onclick="searchd();">查询</a>
					</div>
				</form>
			</div>
			<%-- /查询区  --%>
			<%-- 列表操作区 --%>
		    <div id="footer">
				<table class="table table-striped table-bordered table-hover" id="tab" cellpadding="0" cellspacing="0" style="min-width:100%;width:1600px;max-width:1600px;">
					<thead>
						<tr>
							<th class="center">票号</th>
							<th class="center">业务类型</th>
							<th class="center">票据种类</th>
							<th class="center">票面金额</th>
							<th class="center">票据类型</th>
							<th class="center">出票日</th>
							<th class="center">是否我行承兑</th>
							<th class="center">出票人全称</th>
							<th class="center">出票人账号</th>
							
						</tr>
					</thead>
					<tbody id="dataBody">
						<c:choose>
							<c:when test="${not empty result}">
									<tr>
										<td class="center">${result.billNo}</td>
										<td class="center">${curCode[2]}</td>
										<td class="center">${fns:getDictLabel('K_BILL_CLASS',result.billClass)}</td>
										<td class="center">${fns:getDictLabel('K_BILL_TYPE',result.billType)}</td>
										<td class="center">${fns:formateMoney(result.billMoney)}</td>
										<td class="center">${result.issueDt}</td> 
										<td class="center">${fns:getDictLabel('K_YORN',result.isAccpt)}</td>  									
										<td class="center">${result.remitter}</td>
										<td class="center">${result.remitterAcct}</td>
										
									</tr>
							</c:when>
							<c:otherwise>
								<tr>
									<td colspan="100" class="center">没有相关数据</td>
								</tr>
							</c:otherwise>
						</c:choose>
					</tbody>
					
				</table>
				
				 
				<div id="holder">
					<div id="zhushi">
					</div>
				</div>
    			<c:forEach items="${statusDictList}" varStatus="sts" var="dict">   				   				
					<div class="nodeSize" id="test${dict.curStatus}_${dict.previousStatus}">
						${dict.curStatusDesc}
						<div class="nodeInfo" id="info${dict.curStatus}_${dict.previousStatus}"></div>
					</div>
					 <c:choose>
                           <c:when test="${sts.last==true}">
                             <script language='javascript'>
                             //先写注释
			        		 $("#zhushi").html("<img src='weblib/assets/images/gallery/flownote.jpg'></img>");
                             createGigrph('${myscript}');
                             
							</script>
                           </c:when>
                        </c:choose>
				</c:forEach> 
    			</div>	
    		</div>
		</div>
	</div>
<%@ include file="/webpage/system/admin/modalDialog.jsp"%> 
<%@ include file="/webpage/system/admin/footer.jsp"%>
<script>
      //查询控制 
		function searchd(){
				//调用后台查询方法查询客户账号对应的批次信息
				//modal("#layer_loading,#image");
				if($("#searchForm").valid()){
		        	$("#searchForm").submit();
				}else{
					bootbox.alert("请输入票号进行查询！");
				}
		}
		$(document).ready(function(){//这里我注释掉的代码,去掉之后就是当前模块才弹出提示框！其他情况则不可以，取消掉可以直接用					
			 var obj="";
			 $(".nodeSize").hover(function() {
			   var obj1=$(this).attr("id");
		        obj=obj1.substring(4,obj1.length);	  	
					$("#info"+obj).css("display","block");								
				    $("#info"+obj).html("<ul>");    
					$("#info"+obj).append("<li>客户号:<span>${curCode[6]}</span></li>");		
					$("#info"+obj).append("<li>交易日期:<span>${curCode[4]}</span></li>");
					$("#info"+obj).append("<li>产品编码:<span>${curCode[5]}</span></li>");
					$("#info"+obj).append("<li>批次信息:<span>${curCode[3]}</span></li>");
					$("#info"+obj).append("</ul>");								
			    },function(){
			 	$("#info"+obj).css('display','none');				 
			 	});
			});
</script>
</body>
</html>