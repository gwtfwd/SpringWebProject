package kr.green.springwebproject.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AuthInterceptor extends HandlerInterceptorAdapter {

	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) 
			throws Exception {
		
		// 세션에 저장된 유저정보를 얻기위해 request를 이용하여 세션정보를 가져오고 세션에서 유저정보가 있는지 확인함
		
		HttpSession session = request.getSession();
		Object user = session.getAttribute("user");			// (User)로 형변환 할 필요 없으므로 Object

		
		if(user == null) {
			response.sendRedirect("/");						// 유저정보가 없으면 로그인창으로 보냄
			return false;
		}
		
		return true;
	}
	
	
	
	
	
}




