<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>管理员登录页面</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src="<c:url value='/jquery/jquery-1.5.1.js'/>"></script>
	<script type="text/javascript">
		function checkForm() {
			if(!$("#adminname").val()) {
				alert("管理员名称不能为空！");
				return false;
			}
			if(!$("#adminpwd").val()) {
				alert("管理员密码不能为空！");
				return false;
			}
			return true;
		}
	</script>

	  <style type="text/css">
		  body{
			  font-family: 'Cabin', sans-serif !important;
			  background:url(/Ebook/images/pink.jpg) 0px 0px;
		  }
		  .main{
			  width: 35%;
			  margin: 8em auto 0;
			  padding: 0em 4em 5em;
		  }
		  .main h1{
			  color: #7e8fad;
			  text-align: center;
			  font-size: 2.5em;
			  margin: 0 0 1.5em;
		  }
		  .main input[type="text"],.main input[type="password"]{
			  outline: none;
			  border: 1px solid #FFF;
			  background: #fed2ed;
			  border-radius: 5px;
			  -webkit-border-radius: 5px;
			  -moz-border-radius: 5px;
			  -o-border-radius: 5px;
			  -ms-border-radius: 5px;
			  padding: 15px 10px;
			  width: 96%;
			  font-size: 1.3em;
			  color: #7e8fad;
			  text-align: center;
			  font-family: 'Syncopate', sans-serif;
		  }
		  .main input[type="password"]{
			  margin:1.5em 0;
		  }
		  .main input[type="submit"]{
			  outline:none;
			  border:1px solid #DBA9C8;
			  background:#b47c9f;
			  border-radius:5px;
			  -webkit-border-radius:5px;
			  -moz-border-radius:5px;
			  -o-border-radius:5px;
			  -ms-border-radius:5px;
			  padding:15px 10px;
			  width:96%;
			  color:#fdbfe6;
			  font-size:1.5em;
			  font-family: 'Syncopate', sans-serif;
			  cursor:pointer;
		  }
		  .main input[type="submit"]:hover{
			  background:#fed2ed;
			  color:#b47c9f;
			  border: 1px solid #FFF;
		  }
	  </style>
  </head>
  
  <body>
  <div class="main">
<h1>管理员登录页面</h1>

  <p style="font-weight: 900; color: red">${msg }</p>
<form action="<c:url value='/AdminServlet'/>" method="post" onsubmit="return checkForm()" target="_top">
	<input type="hidden" name="method" value="login"/>
	<input type="text" name="adminname" value="用户名" id="adminname"  onFocus="this.value = '';" onBlur="if (this.value == '') {this.value = '用户名';}"
				 required="">
	<input type="password" name="adminpwd" id="adminpwd" value="Password" onFocus="this.value = '';" onBlur="if (this.value == '') {this.value = '';}"
				 required="">
	<input type="submit" value="进入后台"/>
</form>
</div>
 </body>
</html>
