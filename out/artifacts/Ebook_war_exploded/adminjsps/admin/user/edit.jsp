<%--
  Created by IntelliJ IDEA.
  User: 郑思婷
  Date: 2019/9/24
  Time: 9:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>修改信息</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">

    <script type="text/javascript" src="<c:url value='/jquery/jquery-1.5.1.js'/>"></script>
    <script type="text/javascript">
        function checkForm() {
            if(!$("#loginname").val()) {
                alert("用户名不能为空！");
                return false;
            }
            if(!$("#loginpass").val()) {
                alert("密码不能为空！");
                return false;
            }
            if(!$("#email").val()) {
                alert("邮箱不能为空！");
                return false;
            }
            return true;
        }
    </script>
</head>
<body>
<div style="padding-left:30%;">
    <h3>修改用户信息</h3>
    <h1></h1>
    <p style="font-weight: 900; color: red">${msg }</p>
    <form action="<c:url value='/AdminUserServlet'/>" method="post" onsubmit="return checkForm()">
        <input type="hidden" name="uid" value="${user.uid }"/>
        <input type="hidden" name="method" value="update"/>
        用户名：<input type="text" name="loginname" id="loginname" value="${user.loginname }"/><br/>
        密码：<input type="text" name="loginpass" id="loginpass" value="${user.loginpass }"/><br/>
        邮箱：<input type="text" name="email" id="email" value="${user.email }"/><br/>
        <input type="submit" value="确认修改"/>
        <input type="button" value="返回" onclick="history.go(-1)"/>
    </form>
</div>
</body>
</html>
