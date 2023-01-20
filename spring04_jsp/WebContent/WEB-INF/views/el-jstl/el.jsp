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
	
	<hr>
	
	<h2>EL을 사용해 List, Array에서 데이터를 꺼내쓰기</h2>
	<span>이름 : ${students[0].name}</span>
	<span>국어 : ${students[0].kor}</span>
	<span>수학 : ${students[0].math}</span>
	<span>영어 : ${students[0].eng}</span>
	<span>코딩 : ${students[0].coding}</span>
	<span>합계 : ${students[0].sum}</span>
	<span>평균 : ${students[0].avg}</span>

	<hr>

	<h2>EL을 사용해 Map에서 데이터를 꺼내쓰기</h2>
	<span>이름 : ${data.name}</span>
	<span>국어 : ${data.kor}</span>
	<span>수학 : ${data.math}</span>
	<span>영어 : ${data.eng}</span>
	<span>코딩 : ${data.coding}</span>
	
	<hr>
	
	<h2>List에 담긴 Map이나 DTO 접근</h2>
	<span>이름 dto: ${objs[0].name}</span>
	<span>국어 map: ${objs[1].kor}</span>
	<span>수학 dto: ${objs[0].math}</span>
	<span>영어 map: ${objs[1].eng}</span>
	<span>코딩 dto: ${objs[0].coding}</span>
	<span>합계 map: ${objs[1].sum}</span>
	<span>평균 dto: ${objs[0].avg}</span>
	
	<hr>
	
	<h2>Session에 담긴 데이터에 접근</h2>
	<span>${session}</span>
	<pre>std 속성을 호출하면 현재 스코프인 PageScope에서 가까운 RequestScope에 담긴 속성이 반환된다.
		sessionScope 레퍼런스를 앞에 붙여 스코프를 명시 해주면 HttpSession 객체에 담긴 데이터를 반환받을 수 있다.
	</pre>
	<span>${sessionScope.std}</span>
	 
	 <hr>
	 
	 <h2>Cookie에 접근</h2>
	 <h3>cookie.name.value</h3>
	 <span>JSESSIONID : ${cookie.JSESSIONID.value}</span>
	 <span>JSESSIONID : ${cookie.JSESSIONID.name}</span>
	 <span>JSESSIONID : ${cookie.JSESSIONID.secure}</span>
	 <span>TEST : ${c.name}</span>
	 <span>TEST : ${c.value}</span>
	 <span>TEST : ${c.path}</span>
	 
	  <hr>
	 
	 <h2>EL literal</h2>
	 <span>문자열 : ${"문자열 테스트"}</span>
	 <span>문자열 : ${'"문자열 테스트"'}</span>
	 <span>정수 : ${100}</span>
	 <span>실수 : ${100.01}</span>
	 <span>boolean : ${true}</span>
	 <span>null : ${null}</span>
	 
	  <hr>
	 
	 <h2>EL 연산자</h2>
	 <pre>산술연산자, 비교연산자, 논리연산자, 삼항연산자, empty연산자</pre>
	 
	 <h3>산술연산자 ( +, -, *, /, % ) </h3>
	 <span>1+1 : ${1+1}</span>
	 <span>1-1 : ${1-1}</span>
	 <span>1*1 : ${1*1}</span>
	 <span>4/2 : ${4/2}</span>
	 <span>3%2 : ${3%2}</span>
	 
	  <hr>
	 
	 <h3>비교연산자</h3>
	 <pre>
	 	같다		: ==, eq
	 	다르다		: !=, ne
	 	크다		: >, gt
	 	작다		: &lt lt
	 	크거나 같다 : >=  ge
	 	작거나 같다 : &lt= le
	 </pre>
	 <span>1 == 2 : ${1 == 2}</span>
	 <span>1 eq 2 : ${1 eq 2}</span>
	 <span>1 != 2 : ${1 != 2}</span>
	 <span>1 ne 2 : ${1 ne 2}</span>
	 <span>1 > 2 : ${ 1 > 2}</span>
	 <span>1 gt 2 : ${ 1 gt 2}</span>
	 <span>1 &lt 2 : ${ 1 < 2 } </span>
	 <span>1 lt 2 : ${1 lt 2}</span>
	 <span>1 >= 1 : ${ 1 >= 1 }</span>
	 <span>1 ge 1 : ${ 1 ge 1 }</span>
	 <span>1 &lt= 2 : ${ 1 <= 2}</span>
	 <span>1 le 2 : ${ 1 le 2}</span>
	 
	 <hr>
	 
	 <h2>논리연산자</h2>
	 <pre> and, or, not, &&, ||, !</pre>
	 <span>true and true :  ${true and true}</span>
	 <span>true or true  :  ${true or true}</span>
	 <span>true && true  :  ${true && true}</span>
	 <span>true || true  :  ${true || true}</span>
	 <span>not true      :  ${not true }</span>
	 <span>!true         :  ${!true }</span>
	 
	 <h2>삼항연산자</h2>
	 <pre> 조건식 ? true연산 : false연산</pre>
	 <span> 1 > 2 ? "크다" : 작다 >>>>>>>>>>>>>>>>>>>  ${1 > 2? "[크다]":"[작다]" }</span>
	 
	 
	 <h2>empty 연산자</h2>
	 <pre>
	 	객체가 null 이면 true
	 	문자열, 배열, 리스트의 길이가 0이면 true
	 	이외에는 false
	 </pre>
	 
	 <span>empty null : ${empty null }</span>
	 <span>empty "" : ${empty "" }</span>
	 <span>empty emptyList : ${empty emptyList}</span>
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
</body>
</html>