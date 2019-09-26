<%--
  Created by IntelliJ IDEA.
  User: 郑思婷
  Date: 2019/9/25
  Time: 16:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>用户信息</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <style type="text/css">
        .trTitle {
            background: #FFAEB9;
            color: #fff;
        }
        .trTwoLevel {
            text-align: right;
            font-size: 10pt;
        }
    </style>
</head>
<body>

<table align="center" border="1" cellpadding="0" cellspacing="0" style="border-color: #FF7F50">
    <tr class="trTitle">
        <th>ID</th>
        <th>用户名</th>
        <th>密码</th>
        <th>邮箱</th>
        <th>操作</th>
    </tr>
        <tr class="trTwoLevel">
            <td width="200px;" align="center">${user.uid}</td>
            <td width="200px;" align="center">${user.loginname }</td>
            <td width="200px;" align="center">${user.loginpass}</td>
            <td width="200px;" align="center">${user.email}</td>
            <td width="200px;" align="center">
                <a href="<c:url value='/jsp/user/pwd.jsp'/>">修改密码</a>
            </td>
        </tr>
    <tr style="color: #cd0a0a" align="center">
    <td colspan="5">*其他信息均不可修改</td>
    </tr>
</table>
</body>
</html>
