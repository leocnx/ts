<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@include file="../../common/taglib/taglib.jsp"%>
<%@include file="../common/dwz.jsp"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<title>交易结算 - 后台管理首页</title>
</head>
<body scroll="no">
	<div id="layout">
		<div id="header">
			<div class="headerNav">
				
				<ul class="nav">
					<li><a target="dialog" mask="true" width="500" height="380" href="${baseURL }/user/userInfo/view?userNo=${userNo}">${userName} </a></li>
					
					<li><a href="${baseURL }/logout">退出系统</a></li>
				</ul>
				<ul class="themeList" id="themeList">
					<!-- <li theme="default">
						<div class="selected">蓝色</div>
					</li>
					<li theme="green">
						<div>绿色</div>
					</li>
					<li theme="purple">
						<div>紫色</div>
					</li>
					<li theme="silver">
						<div>银色</div>
					</li>
					<li theme="azure">
						<div>天蓝</div>
					</li> -->
				</ul>
			</div>
		</div>

		<div id="leftside">
			<div id="sidebar_s">
				<div class="collapse">
					<div class="toggleCollapse">
						<div></div>
					</div>
				</div>
			</div>
			<div id="sidebar">
				<div class="toggleCollapse">
					<h2>系统菜单</h2>
					<div>收缩</div>
				</div>
				<div class="accordion" fillSpace="sidebar">${tree }</div>
			</div>
		</div>
		<div id="container">
			<div id="navTab" class="tabsPage">
				<div class="tabsPageHeader">
					<div class="tabsPageHeaderContent">
						<!-- 显示左右控制时添加 class="tabsPageHeaderMargin" -->
						<ul class="navTab-tab">
							<li tabid="main" class="main"><a href="javascript:;"> <span> <span class="home_icon">Home</span>
								</span>
							</a></li>
						</ul>
					</div>
					<div class="tabsLeft">left</div>
					<!-- 禁用只需要添加一个样式 class="tabsLeft tabsLeftDisabled" -->
					<div class="tabsRight">right</div>
					<!-- 禁用只需要添加一个样式 class="tabsRight tabsRightDisabled" -->
					<div class="tabsMore">more</div>
				</div>
				<ul class="tabsMoreList">
					<li><a href="javascript:;">主页</a></li>
				</ul>
				<div class="navTab-panel tabsPageContent layoutBox">
					<div class="page unitBox">
						<div class="accountInfo">
							<p>
								<span>交易结算 - 后台管理</span>
							</p>
							<p>
								Powerd by mavenStar <!-- <a href="http://www.auok.top" target="_blank">http://www.auok.top</a> -->
							</p>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div id="footer">
		Copyright &copy; 2017 <a href="http://www.auok.top" target="_blank">迈文天宸（深圳）科技有限公司</a>
	</div>
</body>
</html>