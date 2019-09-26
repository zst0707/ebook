<%--
  Created by IntelliJ IDEA.
  User: 郑思婷
  Date: 2019/9/23
  Time: 20:45
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
            background: #FFC1C1;
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
        <th>用户名</th>
        <th>密码</th>
        <th>邮箱</th>
        <th>状态(0冻结,1正常)</th>
        <th>操作</th>
    </tr>
        <c:forEach items="${user }" var="user">
            <tr class="trTwoLevel">
                <td width="200px;" align="center">${user.loginname }</td>
                <td width="200px;" align="center">${user.loginpass}</td>
                <td width="200px;" align="center">${user.email}</td>
                <td width="200px;" align="center">${user.status}</td>
                <td width="200px;" align="center">
                    <a href="<c:url value='/AdminUserServlet?method=updateUser&uid=${user.uid }'/>">修改</a>
                    <a onclick="return confirm('您是否真要冻结该账号？')" href="<c:url value='/AdminUserServlet?method=freezing&uid=${user.uid }'/>">冻结</a>
                    <a onclick="return confirm('您是否真要解冻账号？')" href="<c:url value='/AdminUserServlet?method=unfreeze&uid=${user.uid }'/>">解冻</a>
                </td>
            </tr>
        </c:forEach>
</table>
</body>
</html>
