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
 * Servlet implementation class D_Redirect
 */
@WebServlet("/cat/*")
public class D_Redirect extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// 게시글 작성 : 사용자로부터 게시글의 내용을 전달받아, 테이블에 저장,  /board/write
	// 게시글 목록 : 사용자에게 최근에 작성된 게시글 10개를 표 형태로 제공	/board/list
	
	// 게시글 작성 후 게시글의 목록을 사용자에게 보여준다.
	// forward : 브라우저의 주소창에 /board/write 로 그대로 남아있음.
	// redirect : 브라우저의 주소창에 /board/list 로 변경되어있음.
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public D_Redirect() {
        super();
        // TODO Auto-generated constructor stub
    }

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
				//브라우저에게 /spring04_servlet/puppy/name 로 다시 요청할 것을 응답
				//http status code  : 302
				//location header	: /spring04_servlet/cat/name
				response.sendRedirect("/spring04_servlet/cat/name");
			}else {
				//브라우저에게 /spring04_servlet/resources/image/image03.jpg 로 다시 요청할 것을 응답
				//http status code  : 302
				//location header	: /spring04_servlet/resources/image/image03.jpg
				response.sendRedirect("/spring04_servlet/resources/image/image03.jpg");
			}
			
			return;
		}
		
		switch (uriArr[3]) {
			case "name":
				responseName(request, response);
				return;
				
			default:response.setStatus(404);
				return;
		}
	}
    
    private void responseName(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html; charset=utf-8");
		
		String type = request.getParameter("type");
		System.out.println("responseName : " +  type);
		
		response.getWriter().println("나비");
	}
    
    
    
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
