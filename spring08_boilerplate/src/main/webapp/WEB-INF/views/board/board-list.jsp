<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/head.jsp" %>
<head>
	<link href="/resources/css/board/board.css" rel="stylesheet"> 
</head>
<body>
<%@ include file="/WEB-INF/views/include/header.jsp" %>

<div class="container">
<div class="row">
 <h2 class="tit">* 게시판</h2>
    <a href="${context}/board/board-form">게시글 쓰기</a>
 	   <table style="text-align: center" border="1">
       <thead>
           <tr>
               <th style="width: 10%; height:20%;"><span>번호</span></th>
               <th style="width: 70%;"><span>제목</span></th>
               <th style="width: 10%;"><span>작성자</span></th>
               <th style="width: 10%;"><span>등록일</span></th>
           </tr>
       </thead>
       <tbody>
       <c:forEach items="${boardList}" var="board">
           <tr>
               <td>${board.bdIdx}</td>
               <td><a href="${context}/board/detail?bdIdx=${board.bdIdx}">${board.title}</a></td>
               <td>${board.userId}</td>
               <td>${board.regDateAsTime}</td>
           </tr>
        </c:forEach>
       </tbody>
    </table>
	</div>
	
	
	<div class="row mt-3">
		<nav aria-label="Page navigation example">
		  <ul class="pagination justify-content-center">
		   
		    <li class="page-item">
		      <a class="page-link" href="/board/list?page=${paging.prevPage}">Previous</a>
		    </li>
		   
		    <c:forEach begin="${paging.blockStart}" end="${paging.blockEnd}" var="page">
		    	<li class="page-item"><a class="page-link" href="/board/list?page=${page}">${page}</a></li>
		    </c:forEach>
		  
		    <li class="page-item">
		      <a class="page-link" href="/board/list?page=${paging.nextPage}">Next</a>
		    </li>
		    
		  </ul>
		</nav>
	</div>
	
</div>



<%@ include file="/WEB-INF/views/include/footer.jsp" %>
</body>
</html>