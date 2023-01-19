<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	span{
		display: block;
		font-size:1.5vw;
		color:red;
	}
</style>
</head>
<body>
	<h1>EL 표현식</h1>
	<%--
		el표기법 : 자바 bean 객체의 속성을 편하게 사용하기 위해 제공되는 표현식
		
		${} : 객체 프로퍼티에서 값을 꺼낼 때 사용.
		#{} : 객체 프로퍼티에 값을 넣을 때 사용.
		
		
		el표현식에서 제공되는 reference
		
		requestScope : request 객체의 attribute에 저장된 값에 접근
		sessionScope : session 객체의 attribute에 저장된 값에 접근
		applicationScope : servletContext 객체의 attribute에 저장된 값에 접근
		param 		: request의 요청파라미터에 접근
		paramValues : 같은 이름의 요청파라미터에 값이 여러개 있을 경우 배열로 요청파라미터값을 받아올 수 있다.
		cookie		: request의 Cookie에 접근		
		
		만약 requestScope, sessionScope, applicationScope를 지정하여 스코프를 명시하지 않을 경우
		page 스코프부터 속성값을 탐색한다.
	
	 --%>
	 
	 <h2>EL 사용하기</h2>
	 <span>이름 : ${user}</span>
	 <span>합계 : ${sum}</span>
	 <span>평균 : ${avg}</span>

	<hr>

	<h2>EL을 사용해 DTO에서 데이터 꺼내쓰기</h2>
	<pre>
		DTO의 속성명을 사용해 값을 꺼낼 수 있다.
		내부적으로 getter 를 호출한다.
	</pre>
	<span>이름 : ${std.name}</span>
	<span>국어 : ${std.kor}</span>
	<span>수학 : ${std.math}</span>
	<span>영어 : ${std.eng}</span>
	<span>코딩 : ${std.coding}</span>
	<span>합계 : ${std.sum}</span>
	<span>평균 : ${std.avg}</span>





	 
	 
	 
	 
	 
	 
	 
	 
</body>
</html>