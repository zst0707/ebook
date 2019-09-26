<%--
  Created by IntelliJ IDEA.
  User: 郑思婷
  Date: 2019/9/19
  Time: 21:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>左边分类页面</title>
    <base target="body"/>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <meta http-equiv="content-type" content="text/html;charset=utf-8">

    <script type="text/javascript" src="<c:url value='/jquery/jquery-1.5.1.js'/>"></script>
    <script type="text/javascript" src="<c:url value='/menu/mymenu.js'/>"></script>
    <link rel="stylesheet" href="<c:url value='/menu/mymenu.css'/>" type="text/css" media="all">
    <link rel="stylesheet" type="text/css" href="<c:url value='/jsp/css/left.css'/>">
    <script language="javascript">

        var bar = new Q6MenuBar("bar", "Bobby书城");
        $(function() {
            bar.colorStyle = 4;
            bar.config.imgDir = "<c:url value='/menu/img/'/>";
            bar.config.radioButton=true;
            <c:forEach items="${parents}" var="parent">//一级分类名称
            <c:forEach items="${parent.children}" var="child">//二级分类名称
            bar.add("${parent.cname}", "${child.cname}", "/Ebook/BookServlet?method=findByCategory&cid=${child.cid}", "body");
            </c:forEach>
            </c:forEach>

            $("#menu").html(bar.toString());
        });
    </script>
</head>
<body>
    <div id="menu"></div>
</body>
</html>
