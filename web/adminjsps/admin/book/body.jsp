<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>图书管理页面</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

  </head>
  
  <body>
<h1 align="center" style="color: #FF7F50">图书管理</h1>
<p align="center">
<a href="<c:url value='/AdminBookServlet?method=addPre'/>" style="margin: 20px;color: #FF7F50; font-size: 20px;">添加图书</a>
 <a href="<c:url value='/adminjsps/admin/book/gj.jsp'/>" style="margin: 20px;color: #FF7F50; font-size: 20px;">搜索图书</a>
</p>
  </body>
</html>
