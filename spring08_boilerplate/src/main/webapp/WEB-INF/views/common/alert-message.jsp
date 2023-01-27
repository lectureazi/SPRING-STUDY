<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<c:set var="context" value="${pageContext.request.contextPath}"/>
</head>
<body>

<%-- model attribute에 msg 속성에 담겨온 값을 alert하고, 
	 redirect 속성에 담겨온 경로로 redirect --%>
	 
<script type="text/javascript">

	<c:if test="${msg != null}">
		alert("${msg}");
	</c:if>

	location.href = "${redirect}";
	
</script>	 
	 
	 
	 


</body>
</html>