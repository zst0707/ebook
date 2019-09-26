<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>添加分类</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

	<script type="text/javascript" src="<c:url value='/jquery/jquery-1.5.1.js'/>"></script>
	<script type="text/javascript">
		function checkForm() {
			if(!$("#cname").val()) {
				alert("分类名不能为空！");
				return false;
			}
			if(!$("#pid").val()) {
				alert("一级分类不能为空！");
				return false;
			}
			if(!$("#desk").val()) {
				alert("分类描述不能为空！");
				return false;
			}
			return true;
		}
	</script>
<style type="text/css">
	
</style>
  </head>
  
  <body style="background-image:url(../images/welcome.jpg); background-repeat:no-repeat;background-attachment:fixed">
  <div style="padding-left:30%;">
    <h3>添加2级分类</h3>
    <h1></h1>
    <p style="font-weight: 900; color: red">${msg }</p>
    <form action="<c:url value='/AdminCategoryServlet'/>" method="post" onsubmit="return checkForm()">
    	<input type="hidden" name="method" value='addChild'/>
    	分类名称：<input type="text" name="cname" id="cname"/><br/>
    	一级分类：<select name="pid" id="pid">
    		<option value="">===选择1级分类===</option>
<c:forEach items="${parents }" var="parent">
    		<option value="${parent.cid }" <c:if test="${parent.cid eq pid }">selected="selected"</c:if> >${parent.cname }</option>
</c:forEach>
    	</select><br/>
    	分类描述：<textarea rows="5" cols="50" name="desk" id="desk"></textarea><br/>
    	<input type="submit" value="添加二级分类"/>
    	<input type="button" value="返回" onclick="history.go(-1)"/>
    </form>
    </div>
  </body>
</html>
