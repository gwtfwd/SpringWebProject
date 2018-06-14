package kr.green.springwebproject.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.ui.ModelMap;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import kr.green.springwebproject.dao.User;

public class LoginInterceptor extends HandlerInterceptorAdapter {
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		
		HttpSession session = request.getSession();
		Object user = session.getAttribute("user");
		
		if( user != null ) {
			response.sendRedirect("/board/list");		// 로그인 된 상태에서 "/"(로그인페이지) 경로로 가려고 하면 /board/list로 넘어감
		}
		
		//System.out.println("preHandle");
		return true;
	}
	
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
			throws Exception {
		
		ModelMap modelMap = modelAndView.getModelMap();
		User user = (User)modelMap.get("user");
		
		if(user != null) {
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
		}
		
		//System.out.println("postHandle");
		
	}

}
