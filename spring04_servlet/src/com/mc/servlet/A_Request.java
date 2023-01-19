package com.mc.servlet;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class A_Request
 */
@WebServlet("/req")
public class A_Request extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public A_Request() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("doGet 메서드 호출");
		
		response.setHeader("content-type", "text/html; charset=utf-8");
		
		String name = request.getParameter("name");
		int age = Integer.parseInt(request.getParameter("age"));
		System.out.println(name + " : " + age);
		
		//////////////////////////////////////
		// session  객체로 부터 user 정보를 가져오는 코드
		PrintWriter pw = response.getWriter();
		HttpSession session = request.getSession();
		
		if(session.getAttribute("user") != null) {
			pw.println("<h3>user : " + session.getAttribute("user") + "</h3>");
		}
		
		
		///////////////////////////////////////////////////////
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("doPost 메서드 호출");
		request.setCharacterEncoding("utf-8");
		
		String name = request.getParameter("name");
		int age = Integer.parseInt(request.getParameter("age"));
		
		System.out.println(name + " : " + age);
		System.out.println(request.getContentType());
		
		//////////////////////////////////////
		// session  객체로 부터 user 정보를 가져오는 코드
		PrintWriter pw = response.getWriter();
		HttpSession session = request.getSession();
		
		if(session.getAttribute("user") != null) {
			pw.println("<h3>user : " + session.getAttribute("user") + "</h3>");
		}
		
		
		/////////////////////////////////////////////////////
		
		if(request.getContentType().startsWith("multipart/form-data")) {
			getHttpMessage(request);
		}
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	private void getHttpMessage(HttpServletRequest request) {
		
		InputStream is = null;
		OutputStream os = null;
		try {
			
			is = request.getInputStream();
			os = new FileOutputStream("C:\\Program files\\CODE\\httpmessage.txt");
			int b = 0;
			while((b=is.read()) != -1) {
				os.write(b);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				is.close();
				os.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
