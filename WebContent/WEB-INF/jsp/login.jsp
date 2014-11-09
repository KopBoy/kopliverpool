<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登录页面</title>
</head>
<body>
	<form action="j_spring_security_check" method="post">
		<label for="j_username">用户名：</label>: 
		<input id="username" name="username" size="20" maxlength="50" type="text" /> 
		<br />
		
		<label for="password">密 码：</label> : 
		<input id="password" name="password" size="20" maxlength="50" type="password" />
		<br />
		<input id="_spring_security_remember_me" name="_spring_security_remember_me" type="checkbox" value="true" />
		<label for="_spring_security_remember_me">Remember Me?</label>
		<br />
		<input type="submit" value="Login" />
	</form>
</body>
</html>