<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="sys" tagdir="/WEB-INF/tags/sys" %>
<%@ attribute name="id" type="java.lang.String" required="true" description="编号"%>
<%@ attribute name="name" type="java.lang.String" required="true" description="输入框名称"%>
<%@ attribute name="value" type="java.lang.String" required="true" description="输入框值"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
 %>
<i id="${id}Icon" class="icon-${not empty value?value:' hide'}"></i>&nbsp;<label id="${id}IconLabel" style="width:auto;">${not empty value?value:'无'}</label>&nbsp;
<input id="${id}" name="${name}" type="hidden" value="${value}"/><a id="${id}Button" href="javascript:" class="btn" style="background:url(weblib/assets/images/gallery/navbg.jpg) !important;border-color:#7dc9fb;border-width:1px;padding:3px 6px;">选择</a>&nbsp;&nbsp;
<script type="text/javascript">
	$("#${id}Button").click(function(){
		top.$.jBox.open("iframe:<%=basePath%>tagController.do?method=iconselect&value="+$("#${id}").val(), "选择图标", 900, $(top.document).height()-180, {
            buttons:{"确定":"ok", "清除":"clear", "关闭":true}, submit:function(v, h, f){
                if (v=="ok"){
                	var icon = h.find("iframe")[0].contentWindow.$("#icon").val();
                	icon = $.trim(icon).substr(5);
                	$("#${id}Icon").attr("class", "icon-"+icon);
	                $("#${id}IconLabel").text(icon);
	                $("#${id}").val(icon);
                }else if (v=="clear"){
	                $("#${id}Icon").attr("class", "icon- hide");
	                $("#${id}IconLabel").text("无");
	                $("#${id}").val("");
                }
            }, loaded:function(h){
                $(".jbox-content", top.document).css("overflow-y","hidden");
            }, closed: function () {
				$("#indexBody", top.parent.document,parent.document).css("font-family","微软雅黑");
			}
        });
	});
</script>
