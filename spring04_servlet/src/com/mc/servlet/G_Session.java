package com.mc.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class G_Session
 */
@WebServlet("/session/*")
public class G_Session extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public G_Session() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Session : 한번의 연결
		// HttpSession : 웹어플리케이션 관점에서 한번의 연결(Session)을 수명주기로 가지는 데이터를 저장하기 위한 객체
		//			웹어플리케이션 관점에서 한번의 연결(Session) : 브라우저 session
		
		// HttpSession은 heap영역에 저장되는 일반적인 객체이다.
		// HttpSession 객체에 접근하기 위해서는 sessionId 키값이 필요하다.
		
		// request.getSession() 메서드가 호출되면, 클라이언트로부터 전달된
		// jsessionId 라는 쿠키에 담긴 sessionId를 사용해서 클라이언트의 정보가 담긴 HttpSession객체를 찾아 반환해준다.
		
		// 1. 처음 getSession() 메서드를 호출하면, 클라이언트의 정보를 저장하기 위한 Session객체와, 해당 Session 객체에
		//  접근하기 위한 sessionId값을 생성한다.
		
		// 2. 클라이언트에게 응답할 때 생성한 sessionId값을 응답헤더에 jsessionId라는 쿠키에 담아 보낸다.
		//    jsessionid의 스코프는 session이다.
		
		// 3. 클라이언트는 매 요청마다 jsessionid 쿠키를 요청헤더에 담에 서버로 전송
		
		// 4. 서버는 요청헤더 담겨온 jsessionid를 사용해 클라이언트의 정보를 저장하고 있는 session객체를 찾는다.
		
		// 5. 클라이언트로부터 jsessionid를 받지 못하면 session객체에도 접근할 수 없기 때문에, 
		// 	 jsessionid 쿠키가 없는 상태에서 getSession() 메서드를 호출 하면 새로운 session객체를 생성한다.
		
		// 6. HttpSession객체는 자체적인 timout값을 가지고 있어, 일정시간 이상 해당 Session객체에 대한 호출이 없으면
		//  메모리에서 삭제된다.
		
		String nickname = request.getParameter("nickname");
		
		// session 객체 가져오기
		HttpSession session = request.getSession();
		
		// session crud
		// setAttribute : 세션에 데이터 저장, 수정
		// getAttribute : 세션에서 데이터 가져오기
		// removeAttribute : 세션에서 데이터를 삭제
		
		session.setAttribute("user", nickname);
		session.setMaxInactiveInterval(3600);
		response.setHeader("set-cookie", "JSESSIONID="+session.getId()+"; Max-Age=3600; path=/spring04_servlet");
		response.sendRedirect("/spring04_servlet/index.html");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
