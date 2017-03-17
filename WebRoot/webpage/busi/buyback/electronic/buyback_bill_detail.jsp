<%-- 
 * 文件名称: disc_bill_detail.jsp
 * 系统名称: 票据管理平台
 * 模块名称:
 * 软件版权: 北京合融科技有限公司
 * 功能说明: 
 * 系统版本: @version2.0.0.1
 * 开发人员: songzx
 * 开发时间: 2016-08-18
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
</head>
<body style="font-family:'微软雅黑';background:#f4f8fb;">
<div class="page-content" id="jump-content">
	<table class="table table-bordered table-condensed" id="tab1" cellpadding="0" cellspacing="0">
		<tbody>
			<tr>
				<td colspan="2" style="padding-left:18px">票号</td>
				<td colspan="4">${list.billNo}</td>
			</tr>
			<tr>
				<td rowspan="3" class="line">出<br/>票<br/>人</td>
				<td class="wid">名称</td>
				<td class="lenwids">${list.remitter}</td>
				<td rowspan="3" class="line">收<br/>款<br/>人</td>
				<td class="wid">名称</td>
				<td class="lenwid">${list.payee}</td>
			</tr>
			<tr>
				<td class="wid">账号</td>
				<td class="lenwids">${list.remitterAcct}</td>
				<td class="wid">账号</td>
				<td class="lenwid">${list.payeeAcct}</td>
			</tr>
			<tr>
				<td class="wid">开户行</td>
				<td class="lenwids">${list.remitterBankName}</td>
				<td class="wid">开户行</td>
				<td class="lenwid">${list.payeeBankName}</td>
			</tr>
		</tbody>
	</table>
	<table class="table table-bordered table-condensed" id="tab2" cellpadding="0" cellspacing="0">
		<tbody>
			<tr>
				<td>票据品种</td>
				<td>${fns:getDictLabel('K_BILL_CLASS',list.billClass)}</td>
				<td>票据来源</td>
				<td>${fns:getDictLabel('K_BUY_TYPE',list.billSource)}</td>
				<td>票据类型</td>
				<td>${fns:getDictLabel('K_BILL_TYPE',list.billType)}</td>
			</tr>
			<tr>
				<td>承兑人</td>
				<td style="width:260px;" class="bank">${list.acceptor}</td>
				<td>客户名称</td>
				<td>${applylist.custName}</td>
				<td>金额</td>
				<td>${fns:formateMoney(list.billMoney)}</td>
			</tr>
			<tr>
				<td>出票日</td>
				<td>${list.issueDt}</td>
				<td>票面到期日</td>
				<td style="width:150px;">${list.dueDt}</td>
				<td>档案编号</td>
				<td>${list.fileNo}</td>
			</tr>
			<tr>
				<td>产品属性</td>
				<td>${list.acceptorBankNo}</td>
				<td>操作状态</td>
				<td>${list.operStatus}</td>
				<td>批次号</td>
				<td style="width:200px;">${applylist.batchNo}</td>
			</tr>
			<tr>
				<td>交易对手机构号</td>
				<td>${list.acceptorBankNo}</td>
				<td>入账账号</td>
				<td>${list.inAcctNo}</td>
				<td>票据前手</td>
				<td>${list.billBeforeOwner}</td>
			</tr>
			<tr>
				<td>卖方信息</td>
				<td></td>
				<td>顺延天数</td>
				<td>${list.delayDays}</td>
				<td>计息到期日</td>
				<td>${list.galeDate}</td>
			</tr>
			<tr>
				<td>实付金额</td>
				<td>${fns:formateMoney(list.payMoney)}</td>
				<td>同城异地</td>
				<td>${fns:getDictLabel('K_ISSAMECITY',list.isSameCity)}</td>
				<td>计息天数</td>
				<td>${list.interestDays}</td>
			</tr>
			<tr>
				<td>付款方地址</td>
				<td>${list.draweeAddr}</td>
				<td style="width:108px;">是否贴查同步</td>
				<td>${applylist.isTc}</td>
				<td style="width:111px;">银承协议编号</td>
				<td>${list.protocalNo}</td>
			</tr>
			<tr>
				<td>是否本行承兑</td>
				<td>${fns:getDictLabel('K_YORN',list.isAccpt)}</td>
				<td>贴现利率</td>
				<td>${applylist.rate}</td>
				<td>付息方式</td>
				<td>${fns:getDictLabel('K_PAY_TYPE',applylist.payType)}</td>
			</tr>
			<tr>
				<td>付息比例</td>
				<td>${applylist.buyPayRate}</td>
				<td>贴现日</td>
				<td>${list.discDt}</td>
				<td>贴现类型</td>
				<td>${fns:getDictLabel('K_BUY_TYPE',list.discType)}</td>
			</tr>
			<tr>
				<td style="width:144px;">出票人开户行行号</td>
				<td>${list.remitterBankNo}</td>
				<td>赎回日</td>
				<td></td>
				<td>备注</td>
				<td>${list.remark}</td>
			</tr>
			<tr>
				<td>查询内容</td>
				<td colspan="2"></td>
				<td>查询内容</td>
				<td colspan="2"></td>
			</tr>
			<tr>
				<td colspan="6" class="center"><a class="btn-mini" onclick="top.Dialog.close();">关闭</a></td>
			</tr>
		</tbody>
	</table>
	</div>
</body>
</html>