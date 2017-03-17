<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="../admin/taglib.jsp"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html><head>
<meta http-equiv="content-type" content="text/html; charset=gbk">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<meta name="Content-Type" content="text/html; charset=utf-8">
		<meta name="title" content="Bootstrap year calendar">
		<meta name="description" content="The fully customizable year calendar widget, for bootstrap !">
		<meta name="keywords" content="bootstrap, jquery, javascript, widget, calendar, year, component, library, framework, html, css, api">
		<meta name="author" content="Paul DAVID-SIVELLE">
		<title>节假日</title>
		<link rel="stylesheet" type="text/css" href="<%=basePath%>/plugins/calendar/bootstrap.css">
		<link rel="stylesheet" type="text/css" href="<%=basePath%>/weblib/assets/css/bootstrap-datepicker.min.css">
		<link rel="stylesheet" type="text/css" href="<%=basePath%>/plugins/calendar/bootstrap-theme.css">
		<link rel="stylesheet" type="text/css" href="<%=basePath%>/plugins/calendar/bootstrap-year-calendar.css">
		<link rel="stylesheet" type="text/css" href="<%=basePath%>/weblib/assets/css/font-awesome.min.css">
		<link rel="stylesheet" type="text/css" href="<%=basePath%>/plugins/calendar/style.css">
		<style>
			.event-tooltip-content:not(:last-child) {
				border-bottom:1px solid #ddd;
				padding-bottom:5px;
				margin-bottom:5px;
			}

			.event-tooltip-content .event-title {
				font-size:18px;
			}

			.event-tooltip-content .event-location {
				font-size:12px;
			}
		</style>
		<script type="text/javascript" src="<%=basePath%>/plugins/calendar/jquery-1.js"></script>
	</head>
<body class="" style="">
	<div class="sub-content-container" style="padding-left:0px;">
		<div class="sub-content"><div class="panel panel-default" >
		<div class="panel-heading" >假期管理</div>
		<div class="panel-body" id="holidayid">
			<button type="button" class="btn btn-primary" style="background:url(weblib/assets/images/gallery/navbg.jpg) repeat-x;border:1px solid #7dc0db;" onClick="addyear();"/>
				<i class="icon-plus"></i>新增一年
			</button>
			<div class="calendar" id="calendar"><div class="calendar-header panel panel-default"><table><th class="prev"><span class="glyphicon glyphicon-chevron-left"></span></th><th class="year-title year-neighbor2 hidden-sm hidden-xs">2014</th><th class="year-title year-neighbor hidden-xs">2015</th><th class="year-title">2016</th><th class="year-title year-neighbor hidden-xs">2017</th><th class="year-title year-neighbor2 hidden-sm hidden-xs">2018</th><th class="next"><span class="glyphicon glyphicon-chevron-right"></span></th></table></div><div style="display: block;" class="months-container"><div class="month-container col-xs-3"><table class="month"><thead><tr><th colspan="7" class="month-title">January</th></tr><tr><th class="day-header">Su</th><th class="day-header">Mo</th><th class="day-header">Tu</th><th class="day-header">We</th><th class="day-header">Th</th><th class="day-header">Fr</th><th class="day-header">Sa</th></tr></thead><tbody><tr><td class="day old"></td><td class="day old"></td><td class="day old"></td><td class="day old"></td><td class="day old"></td><td class="day"><div class="day-content">1</div></td><td class="day"><div class="day-content">2</div></td></tr><tr><td class="day"><div class="day-content">3</div></td><td class="day"><div class="day-content">4</div></td><td class="day"><div class="day-content">5</div></td><td class="day"><div class="day-content">6</div></td><td class="day"><div class="day-content">7</div></td><td class="day"><div class="day-content">8</div></td><td class="day"><div class="day-content">9</div></td></tr><tr><td class="day"><div class="day-content">10</div></td><td class="day"><div class="day-content">11</div></td><td class="day"><div class="day-content">12</div></td><td class="day"><div class="day-content">13</div></td><td class="day"><div class="day-content">14</div></td><td class="day"><div class="day-content">15</div></td><td class="day"><div class="day-content">16</div></td></tr><tr><td class="day"><div class="day-content">17</div></td><td class="day"><div class="day-content">18</div></td><td class="day"><div class="day-content">19</div></td><td class="day"><div class="day-content">20</div></td><td class="day"><div class="day-content">21</div></td><td class="day"><div class="day-content">22</div></td><td class="day"><div class="day-content">23</div></td></tr><tr><td class="day"><div class="day-content">24</div></td><td class="day"><div class="day-content">25</div></td><td class="day"><div class="day-content">26</div></td><td class="day"><div class="day-content">27</div></td><td class="day"><div class="day-content">28</div></td><td class="day"><div class="day-content">29</div></td><td class="day"><div class="day-content">30</div></td></tr><tr><td class="day"><div class="day-content">31</div></td><td class="day new"></td><td class="day new"></td><td class="day new"></td><td class="day new"></td><td class="day new"></td><td class="day new"></td></tr></tbody></table></div><div class="month-container col-xs-3"><table class="month"><thead><tr><th colspan="7" class="month-title">February</th></tr><tr><th class="day-header">Su</th><th class="day-header">Mo</th><th class="day-header">Tu</th><th class="day-header">We</th><th class="day-header">Th</th><th class="day-header">Fr</th><th class="day-header">Sa</th></tr></thead><tbody><tr><td class="day old"></td><td class="day"><div class="day-content">1</div></td><td class="day"><div class="day-content">2</div></td><td class="day"><div class="day-content">3</div></td><td class="day"><div class="day-content">4</div></td><td class="day"><div class="day-content">5</div></td><td class="day"><div class="day-content">6</div></td></tr><tr><td class="day"><div class="day-content">7</div></td><td class="day"><div class="day-content">8</div></td><td class="day"><div class="day-content">9</div></td><td class="day"><div class="day-content">10</div></td><td class="day"><div class="day-content">11</div></td><td class="day"><div class="day-content">12</div></td><td class="day"><div class="day-content">13</div></td></tr><tr><td class="day"><div class="day-content">14</div></td><td class="day"><div class="day-content">15</div></td><td class="day"><div class="day-content">16</div></td><td class="day"><div class="day-content">17</div></td><td class="day"><div class="day-content">18</div></td><td class="day"><div class="day-content">19</div></td><td class="day"><div class="day-content">20</div></td></tr><tr><td class="day"><div class="day-content">21</div></td><td class="day"><div class="day-content">22</div></td><td class="day"><div class="day-content">23</div></td><td class="day"><div class="day-content">24</div></td><td class="day"><div class="day-content">25</div></td><td class="day"><div class="day-content">26</div></td><td class="day"><div class="day-content">27</div></td></tr><tr><td class="day"><div class="day-content">28</div></td><td class="day"><div class="day-content">29</div></td><td class="day new"></td><td class="day new"></td><td class="day new"></td><td class="day new"></td><td class="day new"></td></tr></tbody></table></div><div class="month-container col-xs-3"><table class="month"><thead><tr><th colspan="7" class="month-title">March</th></tr><tr><th class="day-header">Su</th><th class="day-header">Mo</th><th class="day-header">Tu</th><th class="day-header">We</th><th class="day-header">Th</th><th class="day-header">Fr</th><th class="day-header">Sa</th></tr></thead><tbody><tr><td class="day old"></td><td class="day old"></td><td class="day"><div class="day-content">1</div></td><td class="day"><div class="day-content">2</div></td><td class="day"><div class="day-content">3</div></td><td class="day"><div class="day-content">4</div></td><td class="day"><div class="day-content">5</div></td></tr><tr><td class="day"><div class="day-content">6</div></td><td class="day"><div class="day-content">7</div></td><td class="day"><div class="day-content">8</div></td><td class="day"><div class="day-content">9</div></td><td class="day"><div class="day-content">10</div></td><td class="day"><div class="day-content">11</div></td><td class="day"><div class="day-content">12</div></td></tr><tr><td class="day"><div class="day-content">13</div></td><td class="day"><div class="day-content">14</div></td><td class="day"><div class="day-content">15</div></td><td style="box-shadow: 0px -4px 0px 0px rgb(156, 183, 3) inset;" class="day"><div class="day-content">16</div></td><td style="box-shadow: 0px -4px 0px 0px rgb(156, 183, 3) inset;" class="day"><div class="day-content">17</div></td><td style="box-shadow: 0px -4px 0px 0px rgb(156, 183, 3) inset;" class="day"><div class="day-content">18</div></td><td style="box-shadow: 0px -4px 0px 0px rgb(156, 183, 3) inset;" class="day"><div class="day-content">19</div></td></tr><tr><td class="day"><div class="day-content">20</div></td><td class="day"><div class="day-content">21</div></td><td class="day"><div class="day-content">22</div></td><td class="day"><div class="day-content">23</div></td><td class="day"><div class="day-content">24</div></td><td style="box-shadow: 0px -4px 0px 0px rgb(44, 143, 201) inset;" class="day"><div class="day-content">25</div></td><td style="box-shadow: 0px -4px 0px 0px rgb(44, 143, 201) inset;" class="day"><div class="day-content">26</div></td></tr><tr><td class="day"><div class="day-content">27</div></td><td class="day"><div class="day-content">28</div></td><td class="day"><div class="day-content">29</div></td><td class="day"><div class="day-content">30</div></td><td class="day"><div class="day-content">31</div></td><td class="day new"></td><td class="day new"></td></tr></tbody></table></div><div class="month-container col-xs-3"><table class="month"><thead><tr><th colspan="7" class="month-title">April</th></tr><tr><th class="day-header">Su</th><th class="day-header">Mo</th><th class="day-header">Tu</th><th class="day-header">We</th><th class="day-header">Th</th><th class="day-header">Fr</th><th class="day-header">Sa</th></tr></thead><tbody><tr><td class="day old"></td><td class="day old"></td><td class="day old"></td><td class="day old"></td><td class="day old"></td><td class="day"><div class="day-content">1</div></td><td class="day"><div class="day-content">2</div></td></tr><tr><td class="day"><div class="day-content">3</div></td><td class="day"><div class="day-content">4</div></td><td class="day"><div class="day-content">5</div></td><td class="day"><div class="day-content">6</div></td><td class="day"><div class="day-content">7</div></td><td class="day"><div class="day-content">8</div></td><td class="day"><div class="day-content">9</div></td></tr><tr><td class="day"><div class="day-content">10</div></td><td class="day"><div class="day-content">11</div></td><td class="day"><div class="day-content">12</div></td><td class="day"><div class="day-content">13</div></td><td class="day"><div class="day-content">14</div></td><td class="day"><div class="day-content">15</div></td><td class="day"><div class="day-content">16</div></td></tr><tr><td class="day"><div class="day-content">17</div></td><td class="day"><div class="day-content">18</div></td><td class="day"><div class="day-content">19</div></td><td class="day"><div class="day-content">20</div></td><td class="day"><div class="day-content">21</div></td><td class="day"><div class="day-content">22</div></td><td class="day"><div class="day-content">23</div></td></tr><tr><td class="day"><div class="day-content">24</div></td><td class="day"><div class="day-content">25</div></td><td class="day"><div class="day-content">26</div></td><td class="day"><div class="day-content">27</div></td><td class="day"><div class="day-content">28</div></td><td style="box-shadow: 0px -4px 0px 0px rgb(245, 187, 0) inset;" class="day"><div class="day-content">29</div></td><td style="box-shadow: 0px -4px 0px 0px rgb(245, 187, 0) inset;" class="day"><div class="day-content">30</div></td></tr></tbody></table></div><div class="month-container col-xs-3"><table class="month"><thead><tr><th colspan="7" class="month-title">May</th></tr><tr><th class="day-header">Su</th><th class="day-header">Mo</th><th class="day-header">Tu</th><th class="day-header">We</th><th class="day-header">Th</th><th class="day-header">Fr</th><th class="day-header">Sa</th></tr></thead><tbody><tr><td style="box-shadow: 0px -4px 0px 0px rgb(245, 187, 0) inset;" class="day"><div class="day-content">1</div></td><td class="day"><div class="day-content">2</div></td><td class="day"><div class="day-content">3</div></td><td class="day"><div class="day-content">4</div></td><td class="day"><div class="day-content">5</div></td><td class="day"><div class="day-content">6</div></td><td class="day"><div class="day-content">7</div></td></tr><tr><td class="day"><div class="day-content">8</div></td><td class="day"><div class="day-content">9</div></td><td class="day"><div class="day-content">10</div></td><td class="day"><div class="day-content">11</div></td><td class="day"><div class="day-content">12</div></td><td class="day"><div class="day-content">13</div></td><td class="day"><div class="day-content">14</div></td></tr><tr><td class="day"><div class="day-content">15</div></td><td class="day"><div class="day-content">16</div></td><td class="day"><div class="day-content">17</div></td><td class="day"><div class="day-content">18</div></td><td class="day"><div class="day-content">19</div></td><td class="day"><div class="day-content">20</div></td><td class="day"><div class="day-content">21</div></td></tr><tr><td class="day"><div class="day-content">22</div></td><td class="day"><div class="day-content">23</div></td><td class="day"><div class="day-content">24</div></td><td class="day"><div class="day-content">25</div></td><td class="day"><div class="day-content">26</div></td><td class="day"><div class="day-content">27</div></td><td style="box-shadow: 0px -4px 0px 0px rgb(44, 143, 201) inset;" class="day"><div class="day-content">28</div></td></tr><tr><td style="box-shadow: 0px -4px 0px 0px rgb(44, 143, 201) inset;" class="day"><div class="day-content">29</div></td><td class="day"><div class="day-content">30</div></td><td class="day"><div class="day-content">31</div></td><td class="day new"></td><td class="day new"></td><td class="day new"></td><td class="day new"></td></tr></tbody></table></div><div class="month-container col-xs-3"><table class="month"><thead><tr><th colspan="7" class="month-title">June</th></tr><tr><th class="day-header">Su</th><th class="day-header">Mo</th><th class="day-header">Tu</th><th class="day-header">We</th><th class="day-header">Th</th><th class="day-header">Fr</th><th class="day-header">Sa</th></tr></thead><tbody><tr><td class="day old"></td><td class="day old"></td><td class="day old"></td><td class="day"><div class="day-content">1</div></td><td class="day"><div class="day-content">2</div></td><td class="day"><div class="day-content">3</div></td><td class="day"><div class="day-content">4</div></td></tr><tr><td class="day"><div class="day-content">5</div></td><td class="day"><div class="day-content">6</div></td><td class="day"><div class="day-content">7</div></td><td class="day"><div class="day-content">8</div></td><td class="day"><div class="day-content">9</div></td><td class="day"><div class="day-content">10</div></td><td class="day"><div class="day-content">11</div></td></tr><tr><td class="day"><div class="day-content">12</div></td><td class="day"><div class="day-content">13</div></td><td class="day"><div class="day-content">14</div></td><td class="day"><div class="day-content">15</div></td><td class="day"><div class="day-content">16</div></td><td class="day"><div class="day-content">17</div></td><td class="day"><div class="day-content">18</div></td></tr><tr><td class="day"><div class="day-content">19</div></td><td class="day"><div class="day-content">20</div></td><td class="day"><div class="day-content">21</div></td><td class="day"><div class="day-content">22</div></td><td class="day"><div class="day-content">23</div></td><td class="day"><div class="day-content">24</div></td><td class="day"><div class="day-content">25</div></td></tr><tr><td class="day"><div class="day-content">26</div></td><td class="day"><div class="day-content">27</div></td><td class="day"><div class="day-content">28</div></td><td class="day"><div class="day-content">29</div></td><td class="day"><div class="day-content">30</div></td><td class="day new"></td><td class="day new"></td></tr></tbody></table></div><div class="month-container col-xs-3"><table class="month"><thead><tr><th colspan="7" class="month-title">July</th></tr><tr><th class="day-header">Su</th><th class="day-header">Mo</th><th class="day-header">Tu</th><th class="day-header">We</th><th class="day-header">Th</th><th class="day-header">Fr</th><th class="day-header">Sa</th></tr></thead><tbody><tr><td class="day old"></td><td class="day old"></td><td class="day old"></td><td class="day old"></td><td class="day old"></td><td class="day"><div class="day-content">1</div></td><td class="day"><div class="day-content">2</div></td></tr><tr><td class="day"><div class="day-content">3</div></td><td class="day"><div class="day-content">4</div></td><td class="day"><div class="day-content">5</div></td><td class="day"><div class="day-content">6</div></td><td class="day"><div class="day-content">7</div></td><td class="day"><div class="day-content">8</div></td><td class="day"><div class="day-content">9</div></td></tr><tr><td class="day"><div class="day-content">10</div></td><td class="day"><div class="day-content">11</div></td><td class="day"><div class="day-content">12</div></td><td class="day"><div class="day-content">13</div></td><td class="day"><div class="day-content">14</div></td><td class="day"><div class="day-content">15</div></td><td class="day"><div class="day-content">16</div></td></tr><tr><td class="day"><div class="day-content">17</div></td><td class="day"><div class="day-content">18</div></td><td class="day"><div class="day-content">19</div></td><td class="day"><div class="day-content">20</div></td><td class="day"><div class="day-content">21</div></td><td class="day"><div class="day-content">22</div></td><td class="day"><div class="day-content">23</div></td></tr><tr><td class="day"><div class="day-content">24</div></td><td class="day"><div class="day-content">25</div></td><td class="day"><div class="day-content">26</div></td><td class="day"><div class="day-content">27</div></td><td class="day"><div class="day-content">28</div></td><td class="day"><div class="day-content">29</div></td><td class="day"><div class="day-content">30</div></td></tr><tr><td class="day"><div class="day-content">31</div></td><td class="day new"></td><td class="day new"></td><td class="day new"></td><td class="day new"></td><td class="day new"></td><td class="day new"></td></tr></tbody></table></div><div class="month-container col-xs-3"><table class="month"><thead><tr><th colspan="7" class="month-title">August</th></tr><tr><th class="day-header">Su</th><th class="day-header">Mo</th><th class="day-header">Tu</th><th class="day-header">We</th><th class="day-header">Th</th><th class="day-header">Fr</th><th class="day-header">Sa</th></tr></thead><tbody><tr><td class="day old"></td><td class="day"><div class="day-content">1</div></td><td class="day"><div class="day-content">2</div></td><td class="day"><div class="day-content">3</div></td><td class="day"><div class="day-content">4</div></td><td class="day"><div class="day-content">5</div></td><td class="day"><div class="day-content">6</div></td></tr><tr><td class="day"><div class="day-content">7</div></td><td class="day"><div class="day-content">8</div></td><td class="day"><div class="day-content">9</div></td><td class="day"><div class="day-content">10</div></td><td class="day"><div class="day-content">11</div></td><td class="day"><div class="day-content">12</div></td><td class="day"><div class="day-content">13</div></td></tr><tr><td class="day"><div class="day-content">14</div></td><td class="day"><div class="day-content">15</div></td><td class="day"><div class="day-content">16</div></td><td class="day"><div class="day-content">17</div></td><td class="day"><div class="day-content">18</div></td><td class="day"><div class="day-content">19</div></td><td class="day"><div class="day-content">20</div></td></tr><tr><td class="day"><div class="day-content">21</div></td><td class="day"><div class="day-content">22</div></td><td class="day"><div class="day-content">23</div></td><td class="day"><div class="day-content">24</div></td><td style="box-shadow: 0px -4px 0px 0px rgb(156, 183, 3) inset;" class="day"><div class="day-content">25</div></td><td style="box-shadow: 0px -4px 0px 0px rgb(156, 183, 3) inset;" class="day"><div class="day-content">26</div></td><td class="day"><div class="day-content">27</div></td></tr><tr><td class="day"><div class="day-content">28</div></td><td class="day"><div class="day-content">29</div></td><td class="day"><div class="day-content">30</div></td><td class="day"><div class="day-content">31</div></td><td class="day new"></td><td class="day new"></td><td class="day new"></td></tr></tbody></table></div><div class="month-container col-xs-3"><table class="month"><thead><tr><th colspan="7" class="month-title">September</th></tr><tr><th class="day-header">Su</th><th class="day-header">Mo</th><th class="day-header">Tu</th><th class="day-header">We</th><th class="day-header">Th</th><th class="day-header">Fr</th><th class="day-header">Sa</th></tr></thead><tbody><tr><td class="day old"></td><td class="day old"></td><td class="day old"></td><td class="day old"></td><td style="box-shadow: 0px -4px 0px 0px rgb(255, 74, 50) inset;" class="day"><div class="day-content">1</div></td><td class="day"><div class="day-content">2</div></td><td class="day"><div class="day-content">3</div></td></tr><tr><td class="day"><div class="day-content">4</div></td><td class="day"><div class="day-content">5</div></td><td class="day"><div class="day-content">6</div></td><td class="day"><div class="day-content">7</div></td><td class="day"><div class="day-content">8</div></td><td style="box-shadow: 0px -4px 0px 0px rgb(181, 108, 226) inset;" class="day"><div class="day-content">9</div></td><td class="day"><div class="day-content">10</div></td></tr><tr><td class="day"><div class="day-content">11</div></td><td class="day"><div class="day-content">12</div></td><td class="day"><div class="day-content">13</div></td><td class="day"><div class="day-content">14</div></td><td class="day"><div class="day-content">15</div></td><td class="day"><div class="day-content">16</div></td><td class="day"><div class="day-content">17</div></td></tr><tr><td class="day"><div class="day-content">18</div></td><td class="day"><div class="day-content">19</div></td><td class="day"><div class="day-content">20</div></td><td class="day"><div class="day-content">21</div></td><td class="day"><div class="day-content">22</div></td><td class="day"><div class="day-content">23</div></td><td class="day"><div class="day-content">24</div></td></tr><tr><td class="day"><div class="day-content">25</div></td><td class="day"><div class="day-content">26</div></td><td class="day"><div class="day-content">27</div></td><td class="day"><div class="day-content">28</div></td><td class="day"><div class="day-content">29</div></td><td class="day"><div class="day-content">30</div></td><td class="day new"></td></tr></tbody></table></div><div class="month-container col-xs-3"><table class="month"><thead><tr><th colspan="7" class="month-title">October</th></tr><tr><th class="day-header">Su</th><th class="day-header">Mo</th><th class="day-header">Tu</th><th class="day-header">We</th><th class="day-header">Th</th><th class="day-header">Fr</th><th class="day-header">Sa</th></tr></thead><tbody><tr><td class="day old"></td><td class="day old"></td><td class="day old"></td><td class="day old"></td><td class="day old"></td><td class="day old"></td><td class="day"><div class="day-content">1</div></td></tr><tr><td class="day"><div class="day-content">2</div></td><td class="day"><div class="day-content">3</div></td><td class="day"><div class="day-content">4</div></td><td class="day"><div class="day-content">5</div></td><td class="day"><div class="day-content">6</div></td><td class="day"><div class="day-content">7</div></td><td class="day"><div class="day-content">8</div></td></tr><tr><td class="day"><div class="day-content">9</div></td><td class="day"><div class="day-content">10</div></td><td class="day"><div class="day-content">11</div></td><td class="day"><div class="day-content">12</div></td><td class="day"><div class="day-content">13</div></td><td class="day"><div class="day-content">14</div></td><td class="day"><div class="day-content">15</div></td></tr><tr><td class="day"><div class="day-content">16</div></td><td class="day"><div class="day-content">17</div></td><td class="day"><div class="day-content">18</div></td><td class="day"><div class="day-content">19</div></td><td class="day"><div class="day-content">20</div></td><td class="day"><div class="day-content">21</div></td><td class="day"><div class="day-content">22</div></td></tr><tr><td class="day"><div class="day-content">23</div></td><td class="day"><div class="day-content">24</div></td><td class="day"><div class="day-content">25</div></td><td class="day"><div class="day-content">26</div></td><td class="day"><div class="day-content">27</div></td><td class="day"><div class="day-content">28</div></td><td class="day"><div class="day-content">29</div></td></tr><tr><td class="day"><div class="day-content">30</div></td><td class="day"><div class="day-content">31</div></td><td class="day new"></td><td class="day new"></td><td class="day new"></td><td class="day new"></td><td class="day new"></td></tr></tbody></table></div><div class="month-container col-xs-3"><table class="month"><thead><tr><th colspan="7" class="month-title">November</th></tr><tr><th class="day-header">Su</th><th class="day-header">Mo</th><th class="day-header">Tu</th><th class="day-header">We</th><th class="day-header">Th</th><th class="day-header">Fr</th><th class="day-header">Sa</th></tr></thead><tbody><tr><td class="day old"></td><td class="day old"></td><td class="day"><div class="day-content">1</div></td><td class="day"><div class="day-content">2</div></td><td class="day"><div class="day-content">3</div></td><td class="day"><div class="day-content">4</div></td><td class="day"><div class="day-content">5</div></td></tr><tr><td class="day"><div class="day-content">6</div></td><td class="day"><div class="day-content">7</div></td><td class="day"><div class="day-content">8</div></td><td class="day"><div class="day-content">9</div></td><td class="day"><div class="day-content">10</div></td><td class="day"><div class="day-content">11</div></td><td class="day"><div class="day-content">12</div></td></tr><tr><td class="day"><div class="day-content">13</div></td><td class="day"><div class="day-content">14</div></td><td class="day"><div class="day-content">15</div></td><td class="day"><div class="day-content">16</div></td><td style="box-shadow: 0px -2px 0px 0px rgb(69, 165, 151) inset, 0px -4px 0px 0px rgb(255, 74, 50) inset;" class="day"><div class="day-content">17</div></td><td style="box-shadow: 0px -4px 0px 0px rgb(69, 165, 151) inset;" class="day"><div class="day-content">18</div></td><td class="day"><div class="day-content">19</div></td></tr><tr><td class="day"><div class="day-content">20</div></td><td class="day"><div class="day-content">21</div></td><td class="day"><div class="day-content">22</div></td><td class="day"><div class="day-content">23</div></td><td class="day"><div class="day-content">24</div></td><td class="day"><div class="day-content">25</div></td><td class="day"><div class="day-content">26</div></td></tr><tr><td class="day"><div class="day-content">27</div></td><td class="day"><div class="day-content">28</div></td><td class="day"><div class="day-content">29</div></td><td class="day"><div class="day-content">30</div></td><td class="day new"></td><td class="day new"></td><td class="day new"></td></tr></tbody></table></div><div class="month-container col-xs-3"><table class="month"><thead><tr><th colspan="7" class="month-title">December</th></tr><tr><th class="day-header">Su</th><th class="day-header">Mo</th><th class="day-header">Tu</th><th class="day-header">We</th><th class="day-header">Th</th><th class="day-header">Fr</th><th class="day-header">Sa</th></tr></thead><tbody><tr><td class="day old"></td><td class="day old"></td><td class="day old"></td><td class="day old"></td><td style="box-shadow: 0px -4px 0px 0px rgb(245, 187, 0) inset;" class="day"><div class="day-content">1</div></td><td style="box-shadow: 0px -4px 0px 0px rgb(245, 187, 0) inset;" class="day"><div class="day-content">2</div></td><td style="box-shadow: 0px -4px 0px 0px rgb(245, 187, 0) inset;" class="day"><div class="day-content">3</div></td></tr><tr><td style="box-shadow: 0px -4px 0px 0px rgb(245, 187, 0) inset;" class="day"><div class="day-content">4</div></td><td class="day"><div class="day-content">5</div></td><td class="day"><div class="day-content">6</div></td><td class="day"><div class="day-content">7</div></td><td class="day"><div class="day-content">8</div></td><td class="day"><div class="day-content">9</div></td><td class="day"><div class="day-content">10</div></td></tr><tr><td class="day"><div class="day-content">11</div></td><td class="day"><div class="day-content">12</div></td><td class="day"><div class="day-content">13</div></td><td class="day"><div class="day-content">14</div></td><td class="day"><div class="day-content">15</div></td><td class="day"><div class="day-content">16</div></td><td class="day"><div class="day-content">17</div></td></tr><tr><td class="day"><div class="day-content">18</div></td><td class="day"><div class="day-content">19</div></td><td class="day"><div class="day-content">20</div></td><td class="day"><div class="day-content">21</div></td><td class="day"><div class="day-content">22</div></td><td class="day"><div class="day-content">23</div></td><td class="day"><div class="day-content">24</div></td></tr><tr><td class="day"><div class="day-content">25</div></td><td class="day"><div class="day-content">26</div></td><td class="day"><div class="day-content">27</div></td><td class="day"><div class="day-content">28</div></td><td class="day"><div class="day-content">29</div></td><td class="day"><div class="day-content">30</div></td><td class="day"><div class="day-content">31</div></td></tr></tbody></table></div></div></div>
		</div>
	</div>
	<div class="modal modal-fade" id="event-modal">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">×</span><span class="sr-only">Close</span></button>
					<h4 class="modal-title">
						节假日
					</h4>
				</div>
				<div class="modal-body"  id="delete-onlyread">
					<input name="event-index" type="hidden">
					<form class="form-search">
						<div class="row-fluid">
							<label for="min-date" class="col-sm-4 control-label">时间</label>
							<div class="col-sm-7" >
								<div class="input-group input-daterange"  readonly="readonly">
									<input name="event-start-date" class="input-medium"  readonly="readonly" type="text">
									<span class="input-group-addon">~</span>
									<input name="event-end-date" class="input-medium" readonly="readonly" type="text">
								</div>
							</div>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
					<button type="button" class="btn btn-primary" id="save-event">
						保存
					</button>
					<button type="button" class="btn btn-primary" id="delete-event" onclick="deleteEvent();">
						删除
					</button>
					
					<%--<button type="button" class="btn btn-primary" onClick="nextworkday();"/>
						下一工作日
					</button>
				--%></div>
			</div>
		</div>
	</div>
	<div id="context-menu"></div>
	<div id="footer">
		<div class="site-content">
		</div>
	</div>
	<div style="display: none;" class="calendar-context-menu"></div>
<script type="text/javascript" src="<%=basePath%>/weblib/assets/js/respond.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/weblib/assets/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=basePath%>/plugins/calendar/bootstrap-datepicker.js"></script>
<script type="text/javascript" src="<%=basePath%>/plugins/calendar/bootstrap-year-calendar.js"></script>
<script type="text/javascript" src="<%=basePath%>/plugins/calendar/bootstrap-popover.js"></script>
<script type="text/javascript" src="<%=basePath%>/plugins/calendar/scripts.js"></script>
<script type="text/javascript" class="publish">
	function editEvent(event) {
		$('#event-modal input[name="event-index"]').val(event ? event.id : '');
		$('#event-modal input[name="event-name"]').val(event ? event.name : '');
		//$('#event-modal input[name="event-location"]').val(event ? event.location : '');
		$('#event-modal input[name="event-start-date"]').datepicker('update', event ? event.startDate : '');
		$('#event-modal input[name="event-end-date"]').datepicker('update', event ? event.endDate : '');
		$('#event-modal').modal();
	}
	//删除节假日
	function deleteEvent() {
		//获得数据
		document.all("delete-onlyread").readOnly=true;
		var dataSource = $('#calendar').data('calendar').getDataSource();
		var id = $('#event-modal input[name="event-index"]').val();
		var holidayname = $('#event-modal input[name="event-name"]').val();
		var startday =  $('#event-modal input[name="event-start-date"]').val();
		var endday = $('#event-modal input[name="event-end-date"]').val();
		var url = "sysHolidayController.do?method=delete";
		$.ajax({
			url:url,
			type:"POST",
			contentType:'application/x-www-form-urlencoded; charset=UTF-8',
			data:{"startDate":startday,"endDate":endday},
			dataType:"JSON",
			success:function(result){
				alert(result);
				window.location=window.location;
			},
			error:function(result){
			alert("删除失败");	
			}
		}
	);
    $('#calendar').data('calendar').setDataSource(dataSource);
		$('#event-modal').modal('hide');
	}
	//保存假期日
	function saveEvent() {
	 	var dataSource = $('#calendar').data('calendar').getDataSource();
		var event = {
			id: $('#event-modal input[name="event-index"]').val(),
			name: $('#event-modal input[name="event-name"]').val(),
			startDate: $('#event-modal input[name="event-start-date"]').datepicker('getDate'),
			endDate: $('#event-modal input[name="event-end-date"]').datepicker('getDate')
		}
		 var dataSource = $('#calendar').data('calendar').getDataSource();
		//获得数据
		var id = $('#event-modal input[name="event-index"]').val();
		var holidayname = $('#event-modal input[name="event-name"]').val();
		var startday =  $('#event-modal input[name="event-start-date"]').val();
		var endday = $('#event-modal input[name="event-end-date"]').val();
		//ajax传数据到后台
		var url = "sysHolidayController.do?method=save";
		//判断是否在系统日期之前
		$.ajax({
			url:url,
			type:"POST",
			contentType:'application/x-www-form-urlencoded; charset=UTF-8',
			data:{"holidayname":holidayname,"startday":startday,"endday":endday},
			dataType:"JSON",
			success:function(result){
				if(result.msg=="0"){
					alert("此时间已过期不能被保存");
				}else{
					alert(result.msg);
					if(event.id) {
				        for(var i in dataSource) {
				            if(dataSource[i].id == event.id) {
				                dataSource[i].name = event.name;
				                dataSource[i].startDate = event.startDate;
				                dataSource[i].endDate = event.endDate;
				            }
				        }
				    }
    				else{
				        var newId = 0;
				        for(var i in dataSource) {
				            if(dataSource[i].id > newId) {
				                newId = dataSource[i].id;
				            }
				        }
				        newId++;
				        event.id = newId;
				        dataSource.push(event);
				    }	
					window.location=window.location;
				}
			},
			error:function(result){
				alert("保存失败");	
			}
		}
	);
    $('#calendar').data('calendar').setDataSource(dataSource);
	$('#event-modal').modal('hide');
}
$(function() {//页面加载时调用
	var currentYear = new Date().getFullYear();
	//ajax查询
	var url = "sysHolidayController.do?method=search";
	$.ajax({
		type:"post",
		url:url,
		data:{"nowyear":currentYear},
		dataType:"json",
		success:function(data){
	        var holidaysdata = data.deliverholidays;                                        //取出后台传过来的假期集合
	        var jsonstring=JSON.stringify(holidaysdata).replace(/\"/g,"");                //json（假期集合）转换成字符串，并去除双引号
	     	var jsondata = eval(jsonstring);                                               //字符串再转换回json对象
            $('#calendar').calendar({ 
				enableContextMenu: true,
				enableRangeSelection: true,
				contextMenuItems:[
					{
						text: 'Update',
						click: editEvent
					},
					{
						text: 'Delete',
						click: deleteEvent
					}
				],
				selectRange: function(e) {
					editEvent({ startDate: e.startDate, endDate: e.endDate });
				},
					dataSource:jsondata
				});
			},
			error:function(){
				alert("失败");
			}
		});
		//ajax结束
		$('#save-event').click(function() {
			saveEvent();
		});	
	});
	//加一年
	function addyear(){
		var currentYear = new Date().getFullYear();
		$.ajax({
			url:"sysHolidayController.do?method=addOneYear",
			data:{"nowyear":currentYear},
			type:"POST",
			dataType:"JSON",
			 beforeSend:function(XMLHttpRequest){
			 $("#holidayid").css({"width":"100%","height":"100%"})
			 $("#holidayid").html("<img src='plugins/zTree/2.6/img/loading.gif'/>");
			 },
			success:function(data){
				alert("添加成功");
				window.location=window.location;
			},
			error:function(data){
				alert("失败");
			}
		});
	}
	//下一工作日
	function nextworkday(){
		var endday = $('#event-modal input[name="event-end-date"]').val();
		$('#event-modal').modal('hide');
		$.ajax({
			url:"sysHolidayController.do?method=nextworkday",
			contentType:'application/x-www-form-urlencoded; charset=UTF-8',
			data:{"nowday":endday},
			type:"POST",
			dataType:"JSON",
			success:function(data){
				alert(data);
			},
			error:function(data){
				alert("失败");
			}
		});
	}
</script>
</body>
</html>