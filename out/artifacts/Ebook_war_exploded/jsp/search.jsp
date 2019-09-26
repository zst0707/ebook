<%--
  Created by IntelliJ IDEA.
  User: 郑思婷
  Date: 2019/9/19
  Time: 16:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>搜索功能页面</title>
    <meta http-equiv="content-type" content="text/html;charset=UTF-8">
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <style type="text/css">
        body {
            margin-top: 5px;
            margin-bottom: 0px;
            margin-left:200px;
            color: #404040;
        }
        input {
            width: 300px;
            height: 30px;
            border-style:solid;
            margin:0px;
            border-color: #FFAEB9;
        }
        a {
            text-transform:none;
            text-decoration:none;
            border-width: 0px;
        }
        a:hover {
            text-decoration:underline;
            border-width: 0px;
        }
        span {
            margin: 0px;
        }
    </style>
</head>
<body>
<body>
<form action="<c:url value='/BookServlet'/>" method="get" target="body" id="form1">
    <input type="hidden" name="method" value="findByBname"/>
    <input type="text" placeholder="请输入图书名" name="bname"/>
    <span>
    		<a href="javascript:document.getElementById('form1').submit();"><img align="top" border="0" src="../images/btn.bmp"/></a>
    		<a href="<c:url value='/jsp/gj.jsp'/>" style="font-size: 10pt; color: #404040;" target="body">高级搜索</a>
    </span>
</form>

</body>
</body>
</html>
