package com.mc.jsp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mc.jsp.dto.Student;

/**
 * Servlet implementation class B_EL
 */
@WebServlet("/el")
public class B_EL extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public B_EL() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("el-jstl/el-form").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 요청파리미터로 넘어온 데이터를 파싱
		String name = request.getParameter("name");
		int kor = Integer.parseInt(request.getParameter("kor"));
		int math = Integer.parseInt(request.getParameter("math"));
		int eng = Integer.parseInt(request.getParameter("eng"));
		int coding = Integer.parseInt(request.getParameter("coding"));
		
		// 사용자에게 총점, 평균을 알려주자.
		int sum = kor + math + eng + coding;
		double avg = sum/4;
		
		// jsp로 전달될 request객체에 연산을 마친 합계, 평균 값을 저장
		request.setAttribute("sum", sum);
		request.setAttribute("avg", avg);
		request.setAttribute("user", name);
		
		// request에 자바빈 규약을 따르는 객체를 전달
		Student std = new Student();
		std.setName(name);
		std.setKor(kor);
		std.setEng(eng);
		std.setMath(math);
		std.setCoding(coding);
		request.setAttribute("std", std);
		
		// List, Array에 데이터를 담아서 전송
		//Student[] students = {std};
		List<Student> students = new ArrayList<Student>();
		students.add(std);
		request.setAttribute("students", students);
		
		// Map에 데이터를 담아서 전송
		Map<String,Object> commandMap = new HashMap<>();
		commandMap.put("name", name);
		commandMap.put("kor", kor);
		commandMap.put("eng", eng);
		commandMap.put("math", math);
		commandMap.put("coding", coding);
		request.setAttribute("data", commandMap);
		
		List objs = new ArrayList<>();
		objs.add(std);
		objs.add(commandMap);
		request.setAttribute("objs", objs);
		
		
		// Session에 데이터를 저장하고 el에서 꺼내쓰기
		HttpSession session = request.getSession();
		session.setAttribute("session", "세션에 담긴 값입니다.");
		session.setAttribute("std", "request 객체의 std와 동일한 속성명에 저장된 값 입니다.");
		
		Cookie cookie = new Cookie("TEST", "prev");
		cookie.setPath("/spring04_jsp/el");
		response.addCookie(cookie);
		
		request.setAttribute("c", cookie);
		
		System.out.println(cookie);
		System.out.println(cookie.getPath());
		
		request.setAttribute("emptyList", List.of());
		
		request.getRequestDispatcher("el-jstl/el").forward(request, response);
		
		
		
		
		
		
		
		
		
		
		
	}
}
