<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
<h1>JSTL</h1>
<pre>
	JSP에서 사용하는 스크립틀릿의 복잡함을 해결하기 위해 등장한 사용자정의 태그
</pre>
<h2>1. c:set 변수 생성</h2>
<pre>
	var : 변수명
	value : 값
	JSTL에서 c:set으로 생성한 변수는 PageContext객체에 등록이 된다. 따라서 EL표현식으로 해당 변수를 사용할 수 있다.
</pre>

<c:set var="num1" value="100"/>
<c:set var="num2" value="200"/>
<c:set var="context" value="${pageContext.request.contextPath}"/>

<c:set var="html" value="<a href='https://www.naver.com'>네이버로 이동 </a>"/>
<c:set var="js" value="<script>alert('너는 공격당했다.');</script>"/>

<hr>

<h2>2. c:out 변수 출력</h2>
<span><c:out value="${num1}"/></span>
<span><c:out value="${num2}"/></span>
<span><c:out value="${context}"/></span>

<pre>
	el태그만 사용하면 html태그가 브라우저에 의해서 랜더링된다.
	
	만약에 el에 의해서 랜더링되는 데이터가, 사용자로부터 입력받은 값일 경우에 
	악의적인 사용자가 작성한 js코드가 그대로 클라이언트의 화면에서 실행되는 문제가 있다.
	
	el태그는 이러한 script injection 공격에 대처가 되지 않음으로, 사용자가 입력했던 문자열 데이터를
	화면에 표현할 때 사용하는 것은 위험하다.
	
	JSTL의 c:out 태그를 사용해 데이터에 대한 이스케이프 처리를 해줌으로써 Script injection 공격을 방지
</pre>

<span>${html}</span>
<c:out value="${js}"/>

<hr>

<h2>3. jstl을 사용한 배열 생성</h2>

<c:set var="jstlArr">
	red,blue,yellow,pink,green
</c:set>
<span>jstlArr : <c:out value="${jstlArr}"/></span>

<hr>

<h2>4. jstl 조건문</h2>

<h3>if</h3>

<c:if test="${ num1 < num2 }">
	<span>num1은 num2 보다 작습니다.</span>
</c:if>

<c:if test="${ num1 > num2 }">
	<span>num1은 num2 보다 큽니다.</span>
</c:if>

<hr>

<h3>choose when otherwise</h3>

<c:set var="score" value="89"/>
<c:choose>
	<c:when test="${score ge 90 }">
		<span>당신의 학점은 A 입니다.</span>
	</c:when>
	<c:when test="${score ge 80 }">
		<span>당신의 학점은 B 입니다.</span>
	</c:when>
	<c:when test="${score ge 70 }">
		<span>당신의 학점은 C 입니다.</span>
	</c:when>
	<c:when test="${score ge 60 }">
		<span>당신의 학점은 D 입니다.</span>
	</c:when>
	<c:otherwise>
		<span>당신은 F입니다.</span>
	</c:otherwise>
</c:choose>

<hr>

<h2>5. jstl 반복문</h2>
<h3>forEach,  for문처럼 사용하기</h3>
<pre>
	var : i
	begin : 시작값
	end : 끝값
	step : 증가하는 크기
</pre>

<span>
	<c:forEach var="i" begin="0" end="10" step="1">
		${i}
	</c:forEach>
</span>

<span>
	<c:forEach var="i" begin="0" end="10" step="2">
		${i}
	</c:forEach>
</span>

<h3>forEach, forEach처럼 사용하기</h3>
<pre>
	var : 배열 또는 리스트의 요소를 받을 레퍼런스
	items: forEach로 탐색할 배열 또는 리스트
	varStatus : index, count 속성을 가진 객체
			index : 현재 탐색중인 요소의 인덱스(시작이 0)
			count : 현재 탐색중인 요소의 순서(시작이 1)
</pre>

<table border="1">
	<tr>
		<th>index</th>
		<th>count</th>
		<th>var</th>
	</tr>
	
	<c:forEach var="color" items="${jstlArr}" varStatus="status">
		<tr>
			<td><span>${status.index }</span></td>
			<td><span>${status.count }</span></td>
			<td><span><c:out value="${color}"/></span></td>
		</tr>
	</c:forEach>
</table>

<hr>

<h2>c:forTokens, String.split()와 유사한 기능</h2>
<pre>
	var : 잘라진 문자열을 받을 레퍼런스
	items : 자를 문자열
	delims : 구분자
</pre>
<ul>
	<c:forTokens items="java html css js mysql servlet jsp" delims=" " var="lang">
		<li>${lang}</li>	
	</c:forTokens>
</ul>









</body>
</html>