<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<h1>원하는 숫자를 하나 입력하세요.</h1>
<form action="/spring04_jsp/quiz" method="post">
	<input type="number" name="number">
	<button>전송</button>	
</form>

</body>
</html>