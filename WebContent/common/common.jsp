<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- jstl -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctxPath" value="${pageContext.request.contextPath}"/>

<!-- tempo -->
<script src="${ctxPath}/script/lib/tempo/tempo.js" type="text/javascript"></script>
<link href="${ctxPath}/script/lib/tempo/tempo.css" rel="stylesheet" type="text/css" ></link>

<!-- jquery dev -->
<script src="${ctxPath}/script/lib/jquery/jquery-1.11.1.js" type="text/javascript"></script>
<script src="${ctxPath}/script/lib/jquery/jquery.json-2.4.js" type="text/javascript"></script>
<script src="${ctxPath}/script/lib/jquery/jqueryjson-extend.js" type="text/javascript"></script>

<!-- jquery release -->
<!-- 
<script src="${ctxPath}/script/lib/jquery/jquery-1.11.1.min.js" type="text/javascript"></script>
<script src="${ctxPath}/script/lib/jquery/jquery.json-2.4.min.js" type="text/javascript"></script>
<script src="${ctxPath}/script/lib/jquery/jqueryjson-extend.min.js" type="text/javascript"></script>
-->

<!-- 日志记录库 -->
<script src="${ctxPath}/script/lib/blackbirdjs/blackbird.js"></script>
<link rel="stylesheet" type="text/css" href="${ctxPath}/script/lib/blackbirdjs/blackbird.css"/></link>