package com.mc.mvc.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

import com.mc.mvc.board.dto.Board;
import com.mc.mvc.board.repository.BoardRepository;
import com.mc.mvc.common.code.ErrorCode;
import com.mc.mvc.common.exception.HandlableException;
import com.mc.mvc.member.dto.Member;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class BoardAuthInterceptor implements HandlerInterceptor{
	
	private final BoardRepository boardRepository;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		
		String[] uriArr = request.getRequestURI().split("/");
		
		switch (uriArr[2]) {
		case "board-form":
			if(session.getAttribute("auth") == null) throw new HandlableException(ErrorCode.UNAUTHORIZED_REQUEST);
			break;
		
		case "upload":
			if(session.getAttribute("auth") == null) throw new HandlableException(ErrorCode.UNAUTHORIZED_REQUEST);
			break;
		
		case "remove":
			if(session.getAttribute("auth") == null) throw new HandlableException(ErrorCode.UNAUTHORIZED_REQUEST);
			
			if(request.getParameter("bdIdx") == null) throw new HandlableException(ErrorCode.NOT_EXISTS);
			
			int bdIdx = Integer.parseInt(request.getParameter("bdIdx"));
			Board board = boardRepository.selectBoardByBdIdx(bdIdx);
			
			if(board == null) throw new HandlableException(ErrorCode.NOT_EXISTS);

			Member member = (Member) session.getAttribute("auth");

			if(!board.getUserId().equals(member.getUserId())) throw new HandlableException(ErrorCode.UNAUTHORIZED_REQUEST);
			break;
			
		default:
			break;
		}

		return true;
	}

	
	
	
	
	
	
	
}
