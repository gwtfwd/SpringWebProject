package kr.green.springwebproject.controller;


import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import kr.green.springwebproject.dao.User;
import kr.green.springwebproject.service.UserService;


/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private JavaMailSender mailSender;

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
		
		User user;
		
		if( (user = userService.login(id, pw)) != null ) {
			
			// LoginInterceptor에게 보낼 유저 정보
			model.addAttribute("user",user);
			
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
		
		
	     if( !userService.join(user)) {
	    	 
	        return "redirect:/signup";
	     }
	     else {
	    	 
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
		
		User nowUser = (User)session.getAttribute("user");
		user = userService.update(nowUser, user);
		
		// user의 정보를 이용해서 UserMapper에 있는 update메소드를 호출하여 DB의 정보를 수정
		session.removeAttribute("user");
		session.setAttribute("user", user);
		
		return "redirect:/board/list";
	}
	
	
	@RequestMapping(value = "/mail/mailForm")
	public String mailForm() {

	    return "mail";
	}  

	// mailSending 코드
	@RequestMapping(value = "/mail/mailSending")
	public String mailSending(HttpServletRequest request) {

	    String setfrom = "gwtfwd@gmail.com";         
	    String tomail  = request.getParameter("tomail");     // 받는 사람 이메일
	    String title   = request.getParameter("title");      // 제목
	    String content = request.getParameter("content");    // 내용

	    try {
	        MimeMessage message = mailSender.createMimeMessage();
	        MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "UTF-8");

	        messageHelper.setFrom(setfrom);  // 보내는사람 생략하거나 하면 정상작동을 안함
	        messageHelper.setTo(tomail);     // 받는사람 이메일
	        messageHelper.setSubject(title); // 메일제목은 생략이 가능하다
	        messageHelper.setText(content);  // 메일 내용

	        mailSender.send(message);
	        
	    } catch(Exception e){
	        System.out.println(e);
	    }

	    return "redirect:/mail/mailForm";
	}
	
	
	@RequestMapping(value = "/member/unjoin")
	public String unjoin(HttpServletRequest request) throws Exception{
		
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		
		userService.unjoin(user);
				
		return "redirect:/member/logout";
	}
	
	
	@RequestMapping(value="/test", method=RequestMethod.GET)
	public String test() {
		
		return "test";
	}
	
	@ResponseBody
	@RequestMapping(value="/test", method=RequestMethod.POST, produces="text/plain;charset=UTF-8")
	public ResponseEntity<String> testPost(MultipartFile file) {
		
		return new ResponseEntity<String>(file.getOriginalFilename(), HttpStatus.OK);
	}
	
	
	
}









