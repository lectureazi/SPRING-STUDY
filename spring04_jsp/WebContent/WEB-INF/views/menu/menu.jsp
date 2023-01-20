<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/views/include/head.jsp" %>

<body>
	<h1>메뉴 주문 시스템</h1>
	<h2>메뉴를 선택하세요</h2>
	<form action="${context}/order" method="post">
		<select name="food" multiple="multiple" style="width:300px">
			<option value="피자">피자</option>
			<option value="햄버거">햄버거</option>
			<option value="치킨">치킨</option>
			<option value="회">회</option>
		</select>
		<button>전송</button>
	</form>
</body>

</html>