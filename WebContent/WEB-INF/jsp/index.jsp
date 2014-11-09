<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="/common/common.jsp"></jsp:include>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>欢迎来到首页面</title>
</head>
<body>
<!-- ../../../j_spring_security_logout -->
<c:url value="../../../j_spring_security_logout" var="logoutUrl"/>
<center><a href="${logoutUrl }">退出</a></center>
<center><h1>欢迎${requestScope.user.username}来到首页面.....测试提交三</h1></center>
</body>
</html>