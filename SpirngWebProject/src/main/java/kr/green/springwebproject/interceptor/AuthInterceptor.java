package kr.green.springwebproject.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class AuthInterceptor extends HandlerInterceptorAdapter {

	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) 
			throws Exception {
		
		// ���ǿ� ����� ���������� ������� request�� �̿��Ͽ� ���������� �������� ���ǿ��� ���������� �ִ��� Ȯ����
		
		HttpSession session = request.getSession();
		Object user = session.getAttribute("user");			// (User)�� ����ȯ �� �ʿ� �����Ƿ� Object

		
		if(user == null) {
			response.sendRedirect("/");						// ���������� ������ �α���â���� ����
			return false;
		}
		
		return true;
	}
	
	
	
	
	
}




