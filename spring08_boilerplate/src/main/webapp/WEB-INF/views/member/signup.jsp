<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/head.jsp" %>

<%-- signup 페이지에서만 사용할 head태그의 추가 내용 --%>
<head>
	<style type="text/css">
		.valid_info{
			display:block; 
			color:red;
			font-size: 0.5vw;
		}
	</style>
</head>

<body>
<%@ include file="/WEB-INF/views/include/header.jsp" %>

<div class="container wrap_content">
	<h1>회원 가입 양식</h1>
	
   <form:form action="${context}/member/signup" method="post" id="frmJoin"
   	modelAttribute="signUpForm" >
     <table>
        <tr>
           <td>ID : </td>
           <td>
           	  <input type="text" name="userId" id="inpId" size="10" 
           	  
           	  />
           	  
              <button type="button" id="btnIdCheck">check</button>
              <span class="valid_info" id="idCheck"></span>
              
              <form:errors path="userId" />
              
           </td>
        </tr>
        <tr>
           <td>PASSWORD : </td>
           <td>
           	  <input type="password" name="password" id="pw" 
           	  	
           	  />
           	  
           	  <span id="pwConfirm" class="valid_info">
           	 	 <c:if test="${not empty param.passwordError}">
           	  		${param.passwordError}
           	  	</c:if>  
           	  </span>
           	  
           	  
           </td>
        </tr>
        
        <tr>
           <td>휴대폰번호 : </td>
           <td>
           	  <input type="tel" name="tell" required  
           	/>
           </td>
        </tr>
        <tr>
           <td>email : </td>
           <td>
           	  <input type="email" name="email" required 
           	  	
           	  />
           	  
           </td>
        </tr>
        <tr>
           <td>
              <input type="submit" value="가입" />
              <input type="reset" value="취소" />
           </td>
       </tr>
   </table>
   </form:form>

</div>
<%@ include file="/WEB-INF/views/include/footer.jsp" %>



 <script type="text/javascript">
 
 let idCheckFlg = false;
 
 btnIdCheck.addEventListener("click", ev => {

  let userId = inpId.value;
  
  if(userId){
   
   fetch("/member/checkId?userId=" + userId)
   .then(response => response.json())
   .then(obj => {
	   if(obj.exist){
		   idCheck.innerHTML = '이미 존재하는 아이디 입니다.';
		   idCheckFlg = false;
		   return;
	   }
	   
	   idCheck.innerHTML = '사용 가능한 아이디 입니다.';
	   idCheckFlg = true;
	   
   });
  }
 })
 
 frmJoin.addEventListener('submit', ev => {
  if(!idCheckFlg){
   ev.preventDefault();
   alert("아이디 중복검사에 실패했습니다.");
   inpId.focus();   
  }
  
 })
 
 
 
 
 </script>




</body>
</html>