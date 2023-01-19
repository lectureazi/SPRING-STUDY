package com.mc.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class F_Cookie
 */
@WebServlet("/cookie")
public class F_Cookie extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public F_Cookie() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		// Max-Age : 쿠키의 수명을 지정, 단위 : 초
		// path    : 웹사이트의 어떤 url로 요청을 할 때 쿠키를 요청헤더에 담을 지를 지정
		//		   : deafult : 사이트의 context path가 된다., 따라서 사이트의 모든 페이지에 대한 요청에 쿠키가 담겨오게 된다.
		// httpOnly : httpOnly로 지정된 쿠키는 자바스크립트에서 접근이 불가능
		//		    사용자의 페이지에 스크립트코드를 주입해서 공격하는 방식인 script injection 공격으로부터
		//			쿠키를 안전하게 사용하고 싶을 때 지정
		
		// http header에 set-cookie 헤더를 통해 쿠키를 지정할 수 있다., key=value
		
		String search = request.getParameter("search");
		String prev = "이전 검색어가 존재하지 않습니다.";
		
		response.setContentType("text/html; charset=utf-8");
		// set-cooki:cookieName=value;[options...]
		response.setHeader("set-cookie", "prev="+search+ "; Max-Age=1000; Path=/spring04_servlet/cookie; httpOnly");
		
		Cookie[] cookies = request.getCookies();
		
		if(cookies != null) {
			for (Cookie cookie : cookies) {
				if(cookie.getName().equals("prev")) {
					prev = cookie.getValue();
				}
			}
		}
		
		PrintWriter pw = response.getWriter();
		
		//////////////////////////////////////
		// session  객체로 부터 user 정보를 가져오는 코드
		HttpSession session = request.getSession();
		
		if(session.getAttribute("user") != null) {
			pw.println("<h3>user : " + session.getAttribute("user") + "</h3>");
		}
		
		pw.println("<h1>검색결과</h1>");
		pw.println("<h3 style='color:lightcoral'> 이전 검색어 : " +  prev + "</h3>");
		
		switch (search) {
		case "java":
			pw.println("<h2>자바는 객체지향 프로그래밍 언어입니다. 이 사이트는 자바로 구현되었습니다.</h2>");
			break;
		case "mysql":
			pw.println("<h2>mysql은 오라클사의 DBMS입니다. 무료입니다.</h2>");
			break;
		case "html":
			pw.println("<h2>HTML은 웹페이지 제작을 위한 마크업 언어입니다.</h2>");
			break;
		case "css":
			pw.println("<h2>css는 웹페이지를 이쁘게 꾸며줍니다.</h2>");
			break;
		case "javascript":
			pw.println("<h2>자바스크립트는 프로토타입객체지향프로그래밍언어입니다.</h2>");
			break;
		default:
			break;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
