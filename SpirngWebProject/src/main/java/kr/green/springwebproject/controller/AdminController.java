package kr.green.springwebproject.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.green.springwebproject.dao.Board;
import kr.green.springwebproject.dao.BoardMapper;
import kr.green.springwebproject.dao.User;
import kr.green.springwebproject.dao.UserMapper;
import kr.green.springwebproject.pagenation.Criteria;
import kr.green.springwebproject.pagenation.PageMaker;

@Controller
@RequestMapping(value="/admin/*")
public class AdminController {

	@Autowired
	BoardMapper boardMapper;		// 게시판 정보를 불러오기 위해
	
	@Autowired
	UserMapper userMapper;
	
	
	// 관리자페이지
	@RequestMapping(value="/board", method=RequestMethod.GET)
	public String adminMainGet(Model model, Criteria cri, HttpServletRequest request) {
		
		if(cri == null) {
			cri = new Criteria();
		}
		
		int totalCount=0;
		
		
		ArrayList<Board> list = null;
		
		totalCount = boardMapper.getCountBoardByAdmin();
		list = (ArrayList)boardMapper.getListPageByAdmin(cri);
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCriteria(cri);
		
		pageMaker.setTotalCount(totalCount);
		/* jsp에 해당게시글을 모두 보내고
		   보낼 때 이름을 list로 하면 편함 */
		model.addAttribute("list",list);
		model.addAttribute("pageMaker", pageMaker);
		
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		
		boolean admin = false;
		
		if(user.getAdmin().compareTo("user") !=0)
			admin = true;
		model.addAttribute("admin", admin);
		
		return "admin/board";
	}
	
	@RequestMapping(value="/board/disable")
	public String boardDisable(Model model, Integer number, String disable, Integer page) throws Exception {
		
		Board board = boardMapper.getBoardByNumber(number);
		System.out.println(board);
		board.setDisable(disable);
		boardMapper.updateBoardDisable(board);
		
		if(page == null)
			page = 1;
		model.addAttribute("page",page);
		
		return "redirect:/admin/board";
	}
		
	@RequestMapping(value="/user", method=RequestMethod.GET)
	public String adminAccountGet(Model model, Criteria cri, HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		
		if(cri == null) {
			cri = new Criteria();
		}
		
		int totalCount=0;
		totalCount = userMapper.getCountAccountByAdmin();
		ArrayList<User> list = (ArrayList)userMapper.getListPageByAdmin(cri, user);
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCriteria(cri);
		
		pageMaker.setTotalCount(totalCount);
		model.addAttribute("list",list);
		model.addAttribute("pageMaker", pageMaker);
		
		return "admin/user";
	}
	
	
	@RequestMapping(value="/user/disable")
	public String AccountDisable(Model model, User user, String disable, Integer page) throws Exception {
		
		user = userMapper.getAccountById(user);
		System.out.println(user);
		user.setAdmin(disable);
		userMapper.updateAccountDisable(user);
		
		if(page == null)
			page = 1;
		model.addAttribute("page",page);
		
		return "redirect:/admin/user";
	}
	
	@RequestMapping(value="/user/set")
	public String superadminUserSet(Model model, String admin, Integer page, String id) throws Exception {
		
		User user = userMapper.loginById(id);
		user.setAdmin(admin);
		userMapper.updateUser(user);
		model.addAttribute("page",page);
		
		return "redirect:/admin/user";
	}
	
	
	
}