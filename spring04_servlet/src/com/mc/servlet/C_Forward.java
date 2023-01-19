package com.mc.servlet;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class C_forward
 */

// /forward/*  : forward 시작하는 모든 요청을 이 servlet에서 받겠다.
@WebServlet("/puppy/*")
public class C_Forward extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public C_Forward() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// url이 forward로 시작하는 요청의 url을 파싱하여 알맞은 기능을 수행할 메서드를 호출
		
		// 요청 url 받아오기
		String uri = request.getRequestURI();
		System.out.println("요청 uri : " + uri);
		String[] uriArr = uri.split("/");
		
		System.out.println(Arrays.toString(uriArr));
		
		// C_Forward,  D_Redirect
		// 요청의 재지정, 요청의 재요청
		
		// 요청 재지정 : 클라이언트의 요청에 대해서 작업을 수행 한 뒤, 우리 서버가 제공하는 다른 요청을 서버 내부에서 재지정
		//				get /board-list  => 게시판 목록을 조회 => /board-list/view 라는 요청으로 내부적으로 요청을 재지정
		
		// /puppy?type=name   == 요청을 재지정 ==>  /puppy/name
		// /puppy?type=img   == 요청을 재지정 ==>  /puppy/img
		
		//  spring04_servlet/puppy
		if(uriArr.length == 3) {
			String type = request.getParameter("type");
			
			if(type.equals("name")) {
				RequestDispatcher view = request.getRequestDispatcher("/puppy/name");
				view.forward(request, response);
			}else {
				RequestDispatcher view = request.getRequestDispatcher("/puppy/img");
				view.forward(request, response);
			}
			
			return;
		}
		
		switch (uriArr[3]) {
			case "name":
				responseName(request, response);
				return;
			case "img":
				responseImg(request, response);
				return;
			
			// puppy/name, puppy/img 가 아닌 puppy로 시작하는 다른 요청이 들어왔을 경우,
			// @WebServlet()에 지정한 url 패턴은 puppuy로 시작하는 모든 요청을 정상적인 요청으로 처리하기 때문에,
			// 알맞은 응답인 404 response가 작동하지 않는다.
			default:response.setStatus(404);
				return;
		}
	}
	
	// forward
	// puppy?type=img  == (forward) ==> puppy/img  == (forward) ==> /resources/image/image01.jpg
	
	private void responseImg(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type = request.getParameter("type");
		System.out.println("responseImg : " +  type);
		request.getRequestDispatcher("/resources/image/image01.jpg").forward(request, response);
	}

	private void responseName(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html; charset=utf-8");
		
		String type = request.getParameter("type");
		System.out.println("responseName : " +  type);
		
		response.getWriter().println("뽀삐");
	}
	
	
	
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
