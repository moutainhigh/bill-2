<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="taglib.jsp"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
	<head>
	<base href="<%=basePath%>">
	<%-- <%@ include file="../admin/top.jsp"%>  --%>
    <link rel="Stylesheet" href="plugins/jerichotab/css/jquery.jerichotab.css" />
	<script type="text/javascript" src="plugins/jerichotab/js/jquery-1.8.3.min.js"></script>
	<script type="text/javascript" src="plugins/jerichotab/js/jquery.jerichotab.js"></script>
    <style type="text/css">
   		#myJerichoTab .tabs{width:96.5%}
   		#zDialogCon {
				background: url(weblib/assets/images/gallery/index_bg1.jpg) no-repeat;
	background-size:cover;font-family:'微软雅黑';margin:0px;line-height:0px;
			}
   		 /* @media only screen and (max-width:1360px) {
			#zDialogCon {
				height:575px;
			}
		}  */
    </style>
    <script type="text/javascript">
    var jericho = {
        buildTree: function(id,fid,title,dataLink) {
           $.fn.jerichoTab.addTab({
           		id:id,
               	tabFirer: $(this),
               	code:fid,
               	title: title,
               	closeable: true,
               	data: {
                   dataType: "iframe",
                   dataLink: dataLink
               	}
           	}).loadData();
        },
        buildTabpanel: function() {
            $.fn.initJerichoTab({
                renderTo: '#tab_menu',
                uniqueId: 'myJerichoTab',
                contentCss: { 'height': $('.pull-left').height() - 50 },
                tabs: [{
                        title: '工作台',
                        closeable: false,
                        data: { dataType: "iframe", dataLink: 'loginController.do?method=goMain' }
                    }],
                activeTabIndex: 1,
                loadOnce: true
            });
        }
    }
    $().ready(function() {
   		$('#myJerichoTab .tabs').width('96.5%');
        var w = $(document).width();
        var h = $(document).height();
     	//alert(h);
     	//alert($("#sidebar-collapsed",parent.document).height());
        $('#tab_menu').css({ width: '100%' , 'height': h-6 , 'display': 'block','margin-top':0 });
        jericho.buildTabpanel();
        $('.tabs ul li:first .tab_text').removeClass('tab_text');
        $(".tabs #jerichotab_0").css({"background":"#428bca","width":"85px"});
        var explorer = window.navigator.userAgent ;
    	browser(32,32,32);
        var btnArrow=$("#sidebar-collapsed,parent.document");
        var off=true;
        btn();
        function btn(){
        	 btnArrow.bind("click", function(){
        		if(off==true){
        			browser(32,32,32);
					$('#myJerichoTab .tabs').width('96.5%');
					$('#tab_menu').width('100%');
				}else{
					browser(27,27,27);
					$('#myJerichoTab .tabs').width('96.5%');
					$('#tab_menu').width('100%');
				}
			});
        }
		function browser(val1,val2,val3){
    		if (navigator.userAgent.search(/Trident/i)>=0) {
				$('#jerichotab_contentholder,.jericho_tab iframe').height($("#zDialogCon").height()-val1);
			}else if (explorer.indexOf("Firefox") >= 0) {
				$('#jerichotab_contentholder,.jericho_tab iframe').height($("#zDialogCon").height()-val2);
			}else if(explorer.indexOf("Chrome") >= 0){
				$('#jerichotab_contentholder,.jericho_tab iframe').height($("#zDialogCon").height()-val3);
			}
   	 	} 
    });
   	$(window).resize(function(){
   	 	var w = $(document).width();
       	var h = $(document).height();
        	//browser(37,37,37);
        if($("body",parent.document).width()<1360){
			$("#zDialogCon").height($("mainFrame",parent.document).height());
		}else{
			$("#zDialogCon").css("height","100%");
		}
       	$('#tab_menu').css({' width': '99%','height':h });
       	$('#myJerichoTab .tabs').width('96.5%');
       	//btn();
   	})
</script>	
<body id="zDialogCon">
	<div id="tab_menu" class="pull-left"></div>
</body>
</html>

