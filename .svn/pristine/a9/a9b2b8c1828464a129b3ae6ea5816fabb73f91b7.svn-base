<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="sys" tagdir="/WEB-INF/tags/sys" %>

<%@ attribute name="id" type="java.lang.String" required="true" description="编号"%>
<%@ attribute name="name" type="java.lang.String" required="true" description="隐藏域名称（ID）"%>
<%@ attribute name="value" type="java.lang.String" required="true" description="隐藏域值（ID）"%>
<%@ attribute name="labelName" type="java.lang.String" required="true" description="输入框名称（Name）"%>
<%@ attribute name="labelValue" type="java.lang.String" required="true" description="输入框值（Name）"%>
<%@ attribute name="title" type="java.lang.String" required="true" description="选择框标题"%>
<%@ attribute name="url" type="java.lang.String" required="true" description="树结构数据地址"%>
<%@ attribute name="checked" type="java.lang.Boolean" required="false" description="是否显示复选框，如果不需要返回父节点，请设置notAllowSelectParent为true"%>
<%@ attribute name="extId" type="java.lang.String" required="false" description="排除掉的编号（不能选择的编号）"%>
<%@ attribute name="isAll" type="java.lang.Boolean" required="false" description="是否列出全部数据，设置true则不进行数据权限过滤（目前仅对Office有效）"%>
<%@ attribute name="notAllowSelectRoot" type="java.lang.Boolean" required="false" description="不允许选择根节点"%>
<%@ attribute name="notAllowSelectParent" type="java.lang.Boolean" required="false" description="不允许选择父节点"%>
<%@ attribute name="module" type="java.lang.String" required="true" description="树类型"%>
<%@ attribute name="subnode" type="java.lang.String" required="false" description="树形节点 数据库字段"%>
<%@ attribute name="pnode" type="java.lang.String" required="false" description="树形父节点 数据库字段"%>
<%@ attribute name="nodename" type="java.lang.String" required="false" description="树形节点名称 数据库字段"%>
<%@ attribute name="sourcename" type="java.lang.String" required="false" description="树形数据表名(单表)"%>
<%@ attribute name="selectScopeModule" type="java.lang.Boolean" required="false" description="选择范围内的模型（控制不能选择公共模型，不能选择本栏目外的模型）（仅针对CMS的Category树）"%>
<%@ attribute name="allowClear" type="java.lang.Boolean" required="false" description="是否允许清除"%>
<%@ attribute name="allowInput" type="java.lang.Boolean" required="false" description="文本框可填写"%>
<%@ attribute name="valid" type="java.lang.String" required="false" description="验证规则"%>
<%@ attribute name="cssStyle" type="java.lang.String" required="false" description="css样式"%>
<%@ attribute name="className" type="java.lang.String" required="false" description="class属性"%>
<%@ attribute name="placeholder" type="java.lang.String" required="false" description="placeholder属性"%>
<%@ attribute name="smallBtn" type="java.lang.Boolean" required="false" description="缩小按钮显示"%>
<%@ attribute name="hideBtn" type="java.lang.Boolean" required="false" description="是否显示按钮"%>
<%@ attribute name="disabled" type="java.lang.String" required="false" description="是否限制选择，如果限制，设置为disabled"%>
<%@ attribute name="dataMsgRequired" type="java.lang.String" required="false" description=""%>
<%@ attribute name="isedit" type="java.lang.String" required="false" description="是否限制编辑，如果限制，设置为1"%>
<%@ attribute name="fullAcctBankNo" type="java.lang.String" required="false" description="是否带出机构行号，如果带出，设置为1"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
 %>
<div class="input-append">
	<div class="input-group">
		<input id="${id}-Id" name="${name}" type="hidden" value="${value}"/>
		<input id="${id}-Name" name="${labelName}" valid="${valid}" ${allowInput?'':'readonly="readonly"'} type="text" value="${labelValue}" data-msg-required="${dataMsgRequired}"
		 class="${className}"  style="${cssStyle}" placeholder="${placeholder}"/>
		<%-- <span class="input-group-addon">
			<i id="${id}Button" isedit="${isedit}" class="icon-search"></i>
		</span> --%>
</div>
</div>
<script type="text/javascript">
	$("#${id}Button, #${id}-Name").click(function(){
		// 是否限制选择，如果限制，设置为disabled
		if ($("#${id}Button").attr("isedit") == '1'){
			return true;
		}
		// 正常打开	
		top.$.jBox.open("iframe:<%=basePath%>tagController.do?method=treeselect&url="+encodeURIComponent("${url}")+"&module=${module}&checked=${checked}&extId=${extId}&isAll=${isAll}&subnode=${subnode}&pnode=${pnode}&nodename=${nodename}&sourcename=${sourcename}", "选择${title}", 300, 420, {
			ajaxData:{selectIds: $("#${id}-Id").val()},buttons:{"确定":"ok", ${allowClear?"\"清除\":\"clear\", ":""}"关闭":true}, submit:function(v, h, f){
				if (v=="ok"){
					var tree = h.find("iframe")[0].contentWindow.tree;//h.find("iframe").contents();
					var ids = [], names = [], nodes = [];
					if ("${checked}" == "true"){
						nodes = tree.getCheckedNodes(true);
					}else{
						nodes = tree.getSelectedNodes();
					}
					for(var i=0; i<nodes.length; i++) {//<c:if test="${checked && notAllowSelectParent}">
						
						/* if (nodes[i].level == 0){
							top.$.jBox.tip("不能选择根节点（"+nodes[i].name+"）请重新选择。");
							return false;
						}
						if (nodes[i].isParent){
							top.$.jBox.tip("不能选择父节点（"+nodes[i].name+"）请重新选择。");
							return false;
						} */
						ids.push(nodes[i].id);
						names.push(nodes[i].name);//<c:if test="${!checked}">
						break; // 如果为非复选框选择，则返回第一个选择  </c:if>
					}
					$("#${id}-Id").val(ids.join(",").replace(/u_/ig,""));
					$("#${id}-Name").val(names.join(","));
					if("${fullAcctBankNo}" =='1'){
						var acctBranchNo = $("#${id}-Id").val();
						$.ajax({
							type: "POST",
							url: "<%=basePath%>branchController.do?method=branchInfo",
		    				data: {'branchNo': acctBranchNo},
							dataType:'json',
							cache: false,
							success: function(data){	
								if(data.success){  //处理成功
									$("#acct_branch_name").val(data.attributes.branchName);
									$("#acct_bank_no").val(data.attributes.bankNo);
									//fillBatch(1,5);
								}else{
									$("#acct_branch_name").val("");
									$("#acct_bank_no").val("");
									bootbox.alert(data.msg);
									var trHtml = "<tr><td colspan=\"100\" class=\"center\">没有相关数据</td></tr>";
									$("#dataBody").html(trHtml);$("#page").html("");
								}
							}
						});
					}
				}//<c:if test="${allowClear}">
				else if (v=="clear"){
					$("#${id}-Id").val("");
					$("#${id}-Name").val("");
                }//</c:if>
				if(typeof ${id}TreeselectCallBack == 'function'){
					${id}TreeselectCallBack(v, h, f);
				}
			}, loaded:function(h){
				$(".jbox-content", top.document).css("overflow-y","hidden");
			}, closed: function () {
				$("#indexBody", top.parent.document,parent.document).css("font-family","微软雅黑");
			}
		});
	});
</script>