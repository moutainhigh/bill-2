<%-- 
 * 文件名称: bill_detail.jsp
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
				<td colspan="4">${bill.billNo }</td>
			</tr>
			<tr>
				<td rowspan="3" class="line">出<br/>票<br/>人</td>
				<td class="wid">名称</td>
				<td class="lenwids">${bill.remitter }</td>
				<td rowspan="3" class="line">收<br/>款<br/>人</td>
				<td class="wid">名称</td>
				<td class="lenwid">${bill.payeeName }</td>
			</tr>
			<tr>
				<td class="wid">账号</td>
				<td class="lenwids">${bill.remitterAcct }</td>
				<td class="wid">账号</td>
				<td class="lenwid">${bill.payeeAcct }</td>
			</tr>
			<tr>
				<td class="wid">开户行</td>
				<td class="lenwids">${bill.remitterBankName }</td>
				<td class="wid">开户行</td>
				<td class="lenwid">${bill.payeeBankName }</td>
			</tr>
		</tbody>
	</table>
	<table class="table table-bordered table-condensed" id="tab2" cellpadding="0" cellspacing="0">
		<tbody>
			
			<tr>
				<td>是否本行承兑</td>
				<td>${bill.isAccpt }</td>
				<td>承兑人</td>
				<td style="width:260px;" class="bank">${bill.acceptor }</td>
				<td>承兑人开户行</td>
				<td>${bill.acceptorBankName }</td>
			</tr>
			<tr>
				<td>票据品种</td>
				<td>${fns:getDictLabel('K_BILL_CLASS',bill.billClass)}</td>
				<td>票据来源</td>
				<td>${bill.billSource }</td>
				<td>票据类型</td>
				<td>${fns:getDictLabel('K_BILL_TYPE',bill.billType)}</td>
			</tr>
			<tr>
				<td>出票日</td>
				<td>${bill.issueDt }</td>
				<td>票面到期日</td>
				<td style="width:137px;">${bill.dueDt }</td>
				<td>金额</td>
				<td>${fns:formateMoney(bill.billMoney)}</td>
			</tr>
			<tr>
				<td>利率</td>
				<td>${fns:formateRate(bill.rate)}%</td>
				<td>利率类型</td>
				<td>${fns:getDictLabel('K_RATETYPE',bill.rateType)}</td>
				<td>顺延类型</td>
				<td>${fns:getDictLabel('K_DELAYTYPE',bill.delayType)}</td>
			</tr>
			<tr>
				<td>同城异地</td>
				<td>${fns:getDictLabel('K_ISSAMECITY',bill.isSameCity)}</td>
				<td>顺延天数</td>
				<td>${bill.delayDays }</td>
				<td>计息到期日</td>
				<td>${bill.galeDate }</td>
			</tr>
			<tr>
				<td>计息天数</td>
				<td>${bill.interestDays }</td>
				<td>利息</td>
				<td>${fns:formateMoney(bill.interest)}</td>
				<td>实收金额</td>
				<td>${fns:formateMoney(bill.receiveMoney)}</td>
			</tr>
			<tr>
				<td>是否线上清算</td>
				<td>${fns:getDictLabel('K_ISONLINE',bill.isOnline)}</td>
				<td>禁止背书标记</td>
				<td>${fns:getDictLabel('K_YRON',bill.forbidFlag)}</td>
				<td>操作状态</td>
				<td>${bill.operStatus }</td>
			</tr>
			<tr>
				<td>转卖流水号</td>
				<td>${bill.saleId }</td>
				<td>批次号</td>
				<td>${bill.batchId }</td>
				<td>当前状态</td>
				<td>${fns:getDictLabel('K_CURSTATUS',bill.curStatus) }</td>
			</tr>
			<tr>
				<td colspan="6" class="center"><a class="btn-mini" onclick="top.Dialog.close();">关闭</a></td>
			</tr>
		</tbody>
	</table>
	</div>
</body>
</html>