package kr.green.springwebproject.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class UnAuthInterceptor extends HandlerInterceptorAdapter {

	// 로그인 안한 상태에서만 들어갈 수 있게 함 -> join
	
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
		    throws Exception {
		
		HttpSession session = request.getSession();
		
		if(session.getAttribute("user") != null ) {		// 유저정보가 있으면 접근하지 못하도록 : 로그인한 상태에서 /join으로 가려고 하면 /board/list로 이동
			response.sendRedirect("/board/list");
			return false;
		}
		
		// 유저정보가 없으면 그냥 냅두면 됨
			return true;
		}
	
}
