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

<h1>회원 가입 양식</h1>
    <form action="${context}/member/mailauth" method="post" id="frmJoin">
     <table>
        <tr>
           <td>ID : </td>
           <td>
           	  <input type="text" name="userId" id="id" size="10" />
              <button type="button">check</button>
              <span class="valid_info" id="idCheck"></span>
           </td>
        </tr>
        <tr>
           <td>PASSWORD : </td>
           <td>
           	  <input type="password" name="password" id="pw" />
           	  <span id="pwConfirm" class="valid_info"></span>
           </td>
        </tr>
        <tr>
           <td>휴대폰번호 : </td>
           <td>
           	  <input type="tel" name="tell" required/>
           </td>
        </tr>
        <tr>
           <td>email : </td>
           <td>
           	  <input type="email" name="email" required/>
           </td>
        </tr>
        <tr>
           <td>
              <input type="submit" value="가입" />
              <input type="reset" value="취소" />
           </td>
       </tr>
   </table>
   </form>

</body>
</html>