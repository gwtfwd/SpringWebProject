package kr.green.springwebproject.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class UnAuthInterceptor extends HandlerInterceptorAdapter {

	// �α��� ���� ���¿����� �� �� �ְ� �� -> join
	
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
		    throws Exception {
		
		HttpSession session = request.getSession();
		
		if(session.getAttribute("user") != null ) {		// ���������� ������ �������� ���ϵ��� : �α����� ���¿��� /join���� ������ �ϸ� /board/list�� �̵�
			response.sendRedirect("/board/list");
			return false;
		}
		
		// ���������� ������ �׳� ���θ� ��
			return true;
		}
	
}
