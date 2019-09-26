<%--
  Created by IntelliJ IDEA.
  User: 郑思婷
  Date: 2019/9/19
  Time: 16:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>顶部</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <meta http-equiv="content-type" content="text/html;charset=utf-8">

    <style type="text/css">
        body {
            background:#FFAEB9;
            margin: 0px;
            color: #ffffff;
        }
        a {
            text-decoration:none;
            color: #ffffff;
            font-weight: 900;
        }
        a:hover {
            text-decoration: underline;
            color: #ffffff;
            font-weight: 900;
        }
    </style>
</head>
<body>
<h1 style="text-align: center;">Bobby书城</h1>
<div style="font-size: 11pt; line-height: 5px;">
    <c:choose>
        <c:when test="${empty sessionScope.sessionUser }">
            <a href="<c:url value='/jsp/user/login.jsp'/>" target="_parent">会员登录</a> |&nbsp;
            <a href="<c:url value='/jsp/user/regist.jsp'/>" target="_parent">注册会员账号</a>&nbsp;&nbsp;|&nbsp;&nbsp;
            <a href="<c:url value='/adminjsps/login.jsp'/>" target="_blank">管理员登录</a>&nbsp;&nbsp;|&nbsp;&nbsp;
        </c:when>
        <c:otherwise>
            尊敬的会员：${sessionScope.sessionUser.loginname }&nbsp;&nbsp;|&nbsp;
            <a href="<c:url value='/CartItemServlet?method=myCart'/>" target="body">我的购物车</a>&nbsp;&nbsp;|&nbsp;
            <a href="<c:url value='/OrderServlet?method=myOrders'/>" target="body">我的订单</a>&nbsp;&nbsp;|&nbsp;
            <a href="<c:url value='/UserServlet?method=load&uid=${sessionUser.uid}'/>" target="body">个人信息</a>&nbsp;&nbsp;|&nbsp;&nbsp;
            <a href="<c:url value='/UserServlet?method=quit'/>" target="_parent">退出</a>&nbsp;&nbsp;|&nbsp;
        </c:otherwise>
    </c:choose>
</div>
</body>
</html>
