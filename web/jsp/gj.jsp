<%--
  Created by IntelliJ IDEA.
  User: 郑思婷
  Date: 2019/9/19
  Time: 16:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>组合查询</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">

    <style type="text/css">
        table {
            color: #404040;
            font-size: 10pt;
        }
    </style>

</head>

<body>
<form action="<c:url value='/BookServlet'/>" method="get">
    <input type="hidden" name="method" value="findByCombination"/>
    <table align="center">
        <tr>
            <td>书名：</td>
            <td><input type="text" name="bname"/></td>
        </tr>
        <tr>
            <td>作者：</td>
            <td><input type="text" name="author"/></td>
        </tr>
        <tr>
            <td>出版社：</td>
            <td><input type="text" name="press"/></td>
        </tr>
        <tr>
            <td>&nbsp;</td>
            <td>
                <input type="submit" value="搜　　索"/>
                <input type="reset" value="重新填写"/>
            </td>
        </tr>
    </table>
</form>
</body>
</html>
