package com.mc.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class E_Cache
 */
@WebServlet("/cache")
public class E_Cache extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public E_Cache() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Cache
		// 브라우저는 정적 리소스에 대한 요청이 발생할 경우, 매번 서버에 직접 요청하는 것이 아니라
		// 브라우저의 Cache 저장소에 있는 정적리소스를 응답하는 경우가 있다.
		
		// ETag : 리소스가 수정된 이력이 있는지 체크하기 위한 식별자
		//		리소스가 생성되거나 수정될 때 새롭게 부여되는 식별자
		//		1바이트라도 변경이 발생하거나, 또는 최종 수정일이 변경되면 새로운 ETag값을 부여
		
		// 브라우저는, 서버로부터 정적리소스를 받을 때 ETag값도 함께 받아 저장한다.
		// 사용자가 브라우저를 통해 정적리소스에 대한 요청을 발생시킬 경우 브라우저는 서버에게 
		// 해당 리소스의 ETag 값을 확인 해줄 것을 요청한다.
		
		// 만약 브라우저의 ETag와 서버의 ETag가 같을 경우 브라우저는 캐시저장소에서 정적리소스를 가져와 클라이언트에게 제공 
		
		// Http Message의 Cache-Control : 캐시 정책을 지정
		// no-store : 캐시를 저장하지 않음.
		// no-cache : 매번 서버에서 해당 리소스의 ETag를 확인
		// max-age : 캐시의 수명을 지정, 단위는 초, 수명이 끝나기 전에는 서버에서 ETag 확인 없이 리소스를 캐시에서 반환
		//			수명이 만료되면 no-cache와 동일하게 동작
		
		// response 객체에 setHeader를 통해 속성값을 지정
		// forward : 재지정한 요청으로 기존 요청의 request, response 객체가 전달
		// redirect : 요청에 대한 응답이 발생하고, HTTP 서버는 stateless하기 때문에 응답이 되고나면 요청에 대한 모든 상태값을 지움
		//			따라서, response 객체에 지정한 header 내용이 소실
		response.setHeader("cache-control", "no-store");
		response.setHeader("content-disposition", "attachment; filename=cat.jpg");
		request.getRequestDispatcher("/resources/image/image03.jpg").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
