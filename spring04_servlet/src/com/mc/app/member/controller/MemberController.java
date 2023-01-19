package com.mc.app.member.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mc.app.member.dto.Member;
import com.mc.app.member.service.MemberService;

/**
 * Servlet implementation class MemberController
 */
@WebServlet("/member/*")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private MemberService memberService = new MemberService();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String uri = request.getRequestURI();
		String[] uriArr = uri.split("/");
		
		if(uriArr.length == 3) {
			response.setStatus(404);
			return;
		}
		
		System.out.println(Arrays.toString(uriArr));
		
		switch (uriArr[3]) {
			case "login":
				login(request, response);
				return;
			case "logout":
				logout(request, response);
				return;
			default:
				response.setStatus(404);
				return;
		}
		
	}

	private void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.getSession().removeAttribute("user");
		response.sendRedirect("/spring04_servlet/index.html");
	}

	private void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
		//userId, password
		String userId = request.getParameter("userId");
		String password = request.getParameter("password");
		
		Map<String, Object> res = memberService.userAuthenticate(userId, password);
		
		if(!(boolean)res.get("isSuccess")) {
			response.getWriter().println(res.get("msg"));
			return;
		}
		
		Member authed = (Member) res.get("member");
		
		HttpSession session = request.getSession();
		session.setAttribute("user", authed);
		response.sendRedirect("/spring04_servlet/index.html");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}
