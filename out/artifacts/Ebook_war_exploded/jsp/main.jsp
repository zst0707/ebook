<%--
  Created by IntelliJ IDEA.
  User: 郑思婷
  Date: 2019/9/19
  Time: 16:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>商城主页</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <meta http-equiv="content-type" content="text/html;charset=utf-8">
    <link rel="stylesheet" type="text/css" href="<c:url value='/jsp/css/main.css'/>">
</head>
<body>
<table class="table" align="center">
    <tr class="trTop">
        <td colspan="2" class="tdTop">
            <iframe frameborder="0" src="<c:url value='/jsp/top.jsp'/>" name="top"></iframe>
        </td>
    </tr>
    <tr>
        <td class="tdLeft" rowspan="2">
            <iframe frameborder="0" src="<c:url value='/CategoryServlet?method=findAll'/>" name="left"></iframe>
        </td>
        <td class="tdSearch" style="border-bottom-width: 0px;">
            <iframe frameborder="0" src="<c:url value='/jsp/search.jsp'/>" name="search"></iframe>
        </td>
    </tr>
    <tr>
        <td style="border-top-width: 0px;">
            <iframe frameborder="0" src="<c:url value='/jsp/body.jsp'/>" name="body"></iframe>
        </td>
    </tr>
</table>
</body>
</html>
