<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>顶部页面</title>
    <base target="body">
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

<style type="text/css">
	body {font-size: 10pt;}
	a {color:	#FF7F50; font-size:11pt; font-weight: 900;}
</style>
  </head>
  
  <body style="background:	#FFC1C1;color:#FFFAFA;">
<h1 style="text-align: center; line-height: 30px;">Bobby书城后台管理</h1>
<div style="line-height: 10px;">
	<span>管理员：${sessionScope.admin.adminname }</span>
	<a target="_top" href="<c:url value='/index.jsp'/>">退出</a>
	<span style="padding-left:50px;">
		<a href="<c:url value='/AdminCategoryServlet?method=findAll'/>">分类管理</a>
		<a href="<c:url value='/adminjsps/admin/book/main.jsp'/>">图书管理</a>
		<a href="<c:url value='/AdminOrderServlet?method=findAll'/>">订单管理</a>
		<a href="<c:url value='/AdminUserServlet?method=findAll'/>">会员管理</a>
	</span>
</div>
  </body>
</html>
