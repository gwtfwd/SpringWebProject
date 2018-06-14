package kr.green.springwebproject.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.green.springwebproject.dao.BoardMapper;
import kr.green.springwebproject.dao.User;
import kr.green.springwebproject.dao.UserMapper;


/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private BoardMapper boardMapper;
	
	@Autowired
	BCryptPasswordEncoder passwordEncoder;

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(HttpServletRequest request, Model model) {
		
		return "home";
	}
	
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String homePost(HttpServletRequest request, Model model) {
		
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		User user = userMapper.loginById(id);
		
		if(user != null && passwordEncoder.matches(pw, user.getPw())) {
			model.addAttribute("user", user);
			return "redirect:/board/list";
		}
		
		return "redirect:/";
	}
	
	@RequestMapping(value = "/member/join", method = RequestMethod.GET)
	public String memberJoinGet() {
		
		return "/member/join";
	}
	
	@RequestMapping(value = "/member/join", method = RequestMethod.POST)
	public String memberJoinPost(HttpServletRequest request, Model model, User user) {
		
		User searchuser = userMapper.login(user.getId(), user.getPw());
		
		if( searchuser != null ) {
			return "redirect:/member/join";
		}
		else {
			String encPw = passwordEncoder.encode(user.getPw());
			user.setPw(encPw);
			userMapper.join(user);
			return "redirect:/";
		}
	}
	
	@RequestMapping(value = "/member/logout")
	public String logout(HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		session.removeAttribute("user");
		
		return "redirect:/";
	}
	
	@RequestMapping(value = "/member/update", method = RequestMethod.GET)
	public String memberUpdateGet(HttpServletRequest request, Model model) {
		
		HttpSession session = request.getSession();
		User nowUser = (User)session.getAttribute("user");
		model.addAttribute("nowUser", nowUser);
		
		return "/member/update";
	}
	
	@RequestMapping(value = "/member/update", method = RequestMethod.POST)
	public String memberUpdatePost(HttpServletRequest request, User user, Model model) {
		
		HttpSession session = request.getSession();
		
		String encPw = passwordEncoder.encode(user.getPw());
		user.setPw(encPw);
		
		// user의 정보를 이용해서 UserMapper에 있는 update메소드를 호출하여 DB의 정보를 수정
		userMapper.updateUser(user);
		session.removeAttribute("user");
		session.setAttribute("user", user);
		
		return "redirect:/board/list";
	}
	
}
