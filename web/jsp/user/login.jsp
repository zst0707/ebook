<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>登录</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="content-type" content="text/html;charset=utf-8">
	<link rel="stylesheet" type="text/css" href="<c:url value='/jsp/css/user/login.css'/>">
	<script type="text/javascript" src="<c:url value='/jquery/jquery-1.5.1.js'/>"></script>
	<script type="text/javascript" src="<c:url value='/jsp/js/user/login.js'/>"></script>

<script type="text/javascript">
	$(function() {
		var loginname = window.decodeURI("${cookie.loginname.value}");
		if("${requestScope.user.loginname}") {
			loginname = "${requestScope.user.loginname}";
		}
		$("#loginname").val(loginname);
	});
</script>
  </head>
  <body>
  <div style="padding-left:10%;padding-top:10%;">
	<div class="main">
	  <div>
	    <div class="imageDiv"><img class="img" src="<c:url value='/images/zj.png'/>"/></div>
        <div class="login1">
	      <div class="login2">
            <div class="loginTopDiv">
              <span class="loginTop">会员登录</span>
              <span>
                <a href="<c:url value='/jsp/user/regist.jsp'/>" class="registBtn"></a>
              </span>
            </div>
            <div style="background-color: #fdf5ce">
              <form target="_top" action="<c:url value='/UserServlet'/>" method="post" id="loginForm">
                <input type="hidden" name="method" value="login" />
                  <table>
                      <tr>
                          <td width="50"></td>
                          <td><label class="error" id="msg">${msg }</label></td>
                      </tr>
                    <tr>
                      <td width="50">用户名</td>
                      <td><input class="input" type="text" name="loginname" id="loginname"/></td>
                    </tr>
                    <tr>
                      <td height="20">&nbsp;</td>
                      <td><label id="loginnameError" class="error"></label></td>
                    </tr>
                    <tr>
                      <td>密　码</td>
                      <td><input class="input" type="password" name="loginpass" id="loginpass" value="${user.loginpass }"/></td>
                    </tr>
                    <tr>
                      <td height="20">&nbsp;</td>
                      <td><label id="loginpassError" class="error"></label></td>
                    </tr>
                    <tr>
                      <td>&nbsp;</td>
                      <td align="left">
                        <input type="image"  src="<c:url value='/images/login1.jpg'/>" class="loginBtn"/>
                      </td>
                    </tr>																				
                 </table>
              </form>
            </div>
          </div>
        </div>
      </div>
	</div>
	</div>
  </body>
</html>
	