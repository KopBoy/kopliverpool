<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<jsp:include page="common/common.jsp"></jsp:include>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- <title>登录页面</title> -->

<script type="text/javascript" src="login.js"></script>

</head>

<body>
	<div>
		<center>
		<form id="formByJson">
		<table>
			<tr><td><label>姓名：</label><input type="text" width="150" id="username" name="username"/></td></tr>
			<tr><td><label>密码：</label><input type="password" width="150" id="password" name="password"/></td></tr>
			<tr><td><input id="formByJsonBut" type="button" value="loginByJson登录"/></td></tr>
		</table>
		</form>
		</center>
	</div>
	
	<div>
		<center>
		<form id="loginByModelMap" action="app/demo/pub/loginByModelMap.do" method="post">
		<!-- <form id="loginByModelMap" action="/ctosii_middle/j_spring_security_check" method="post"> -->
		<table>
			<tr><td><label>姓名：</label><input type="text" width="150" id="j_username" name="j_username"/></td></tr>
			<tr><td><label>密码：</label><input type="password" width="150" id="j_password" name="j_password"/></td></tr>
			<tr><td><input id="loginBut" type="submit" value="loginByModelMap登录"/></td></tr>
		</table>
		</form>
		</center>
	</div>
	
	<div>
		<center>
		<form id="loginByForm" action="app/demo/pub/loginByForm.do" method="post">
		<table>
			<tr><td><label>姓名：</label><input type="text" width="150" id="username" name="username"/></td></tr>
			<tr><td><label>密码：</label><input type="password" width="150" id="password" name="password"/></td></tr>
			<tr><td><input id="loginBut" type="submit" value="loginByForm登录"/></td></tr>
		</table>
		</form>
		</center>
	</div>
</body>
</html>