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
import kr.green.springwebproject.dao.User;
import kr.green.springwebproject.pagenation.Criteria;
import kr.green.springwebproject.pagenation.PageMaker;
import kr.green.springwebproject.service.BoardService;
import kr.green.springwebproject.service.UserService;

@Controller
@RequestMapping(value="/admin/*")
public class AdminController {

	@Autowired
	BoardService boardService;
	
	@Autowired
	UserService userService;
	
	// 관리자페이지
	@RequestMapping(value="/board", method=RequestMethod.GET)
	public String adminMainGet(Model model, Criteria cri, HttpServletRequest request) {
		
		if(cri == null) {
			cri = new Criteria();
		}
		
		int totalCount=0;
		
		ArrayList<Board> list;
		
		totalCount = boardService.getCountBoardByAdmin();
		list = (ArrayList)boardService.getListPageByAdmin(cri);
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCriteria(cri);
		
		pageMaker.setTotalCount(totalCount);
		/* jsp에 해당게시글을 모두 보내고
		   보낼 때 이름을 list로 하면 편함 */
		model.addAttribute("list",list);
		model.addAttribute("pageMaker", pageMaker);
		
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		
		
		boolean admin = userService.isSuperadmin(user);
		
		model.addAttribute("admin", admin);
		
		return "admin/board";
	}
	
	@RequestMapping(value="/board/disable")
	public String boardDisable(Model model, Integer number, String disable, Integer page) throws Exception {
		
		Board board = boardService.getBoard(number);
		board.setDisable(disable);
		boardService.updateBoardDisable(board);
		
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
		
		int totalCount;
		totalCount = userService.getCountAccountByAdmin();
		
		ArrayList<User> list;
		list = (ArrayList)userService.getListPageByAdmin(cri, user);
		
		
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCriteria(cri);
		
		pageMaker.setTotalCount(totalCount);
		model.addAttribute("list",list);
		model.addAttribute("pageMaker", pageMaker);
		
		return "admin/user";
	}
	
	
	@RequestMapping(value="/user/disable")
	public String AccountDisable(Model model, User user, String disable, Integer page) throws Exception {

		
		user = userService.getUser(user);
		user.setAdmin(disable);
		userService.updateAccountDisable(user);
		
		
		if(page == null)
			page = 1;
		model.addAttribute("page",page);
		
		return "redirect:/admin/user";
	}
	
	@RequestMapping(value="/user/set")
	public String superadminUserSet(Model model, String admin, Integer page, String id) throws Exception {
		
		
		
		User user = userService.getUserById(id);
		user.setAdmin(admin);
		userService.updateUser(user);
		model.addAttribute("page",page);
		
		return "redirect:/admin/user";
	}
	
	@RequestMapping(value = "/board/delete")
	public String boardDeletePost(Model model, Integer page, Integer number, Board board) throws Exception {
		
		
		if(page == null)
			page =1;
		
		model.addAttribute("page",page);
		
		if(number != null) {
			board = boardService.getBoard(number);
			boardService.deleteBoardReal(board);
		}
		
		return "redirect:/admin/board";
	}
	
	
}