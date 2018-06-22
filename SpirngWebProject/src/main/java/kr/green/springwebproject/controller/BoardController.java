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

@RequestMapping(value="/board/*")

public class BoardController {
	
	@Autowired
	BoardMapper boardMapper;
	
	
	@RequestMapping(value = "list")
	public String boardListGet(Model model, Criteria cri, String search, Integer type, HttpServletRequest request) {
		
		System.out.println("Search: "+ search);
		System.out.println("Type : "+ type);
		
		//Criteria cri = new Criteria(1,10);
		
		if(cri == null) {
			cri = new Criteria();
		}
		
		int totalCount=0;
		PageMaker pageMaker = new PageMaker();
		ArrayList<Board> list;
		pageMaker.setCriteria(cri);
		
		if( type == null ) {
			type = 0;
		}
		if( type == 0 ) {
			totalCount = boardMapper.getCountBoard();
			list = (ArrayList)boardMapper.getListPage(cri);
		}
		else if( type == 1 ) {
			totalCount = boardMapper.getCountBoardByTitle("%"+search+"%");
			list = (ArrayList)boardMapper.getListPageByTitle(cri, "%"+search+"%");
		}
		else if( type == 2 ) {
			totalCount = boardMapper.getCountBoardByAuthor("%"+search+"%");
			list = (ArrayList)boardMapper.getListPageByAuthor(cri, "%"+search+"%");
		}
		else {
			totalCount = boardMapper.getCountBoardByContents("%"+search+"%");
			list = (ArrayList)boardMapper.getListPageByContents(cri, "%"+search+"%");
		}
		
		//ArrayList<Board> list = (ArrayList)boardMapper.getBoard();
		
		pageMaker.setTotalCount(totalCount);
		model.addAttribute("list",list);
		model.addAttribute("pageMaker", pageMaker);
		model.addAttribute("search", search);
		model.addAttribute("type", type);
		System.out.println(pageMaker);
		
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
			
		boolean admin = false;
			
		if(user.getAdmin().compareTo("admin") == 0)
			admin = true;
		model.addAttribute("admin", admin);
		
		return "/board/list";
	}
	
	@RequestMapping(value = "detail", method = RequestMethod.GET)
	public String boardDetailGet(HttpServletRequest request, Model model) {
		
		int number = Integer.parseInt(request.getParameter("number"));
		
		Board board = boardMapper.getBoardByNumber(number);
		
		
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		
		
		model.addAttribute("board", board);
		
		model.addAttribute("user", user);
		
		/*System.out.println(boardMapper.getBoardByNumber(number));*/
		
		boolean isAuthor = false;
		
		if(user.getId().compareTo(board.getAuthor())==0) {		// 유저의 id와 게시글의 저자가 같으면
			isAuthor = true;
		}else {
			isAuthor = false;
		}
		
		model.addAttribute("isAuthor",isAuthor);
		
		return "/board/detail";
	}
	
	@RequestMapping(value = "modify", method = RequestMethod.GET)
	public String boardModifyGet(HttpServletRequest request, Model model) {
		
		int number = Integer.parseInt(request.getParameter("number"));
		
		Board board = boardMapper.getBoardByNumber(number);
		
		model.addAttribute("board", board);
		
		return "/board/modify";
	}
	
	@RequestMapping(value="modify", method= RequestMethod.POST)
	public String boardDetailPost(Model model, HttpServletRequest request, Board board) {
		
		boardMapper.modifyBoard(board);
		
		return "redirect:/board/list";
	}
	
	@RequestMapping(value = "write", method = RequestMethod.GET)
	public String boardWriteGet(HttpServletRequest request, Model model) {
		
		return "/board/write";
	}
	
	@RequestMapping(value = "write", method = RequestMethod.POST)
	public String boardWritePost(HttpServletRequest request, Model model, Board board) {
		
		HttpSession session = request.getSession();
		
		User user = (User)session.getAttribute("user");
		
		board.setAuthor(user.getId());
		
		boardMapper.insertBoard(board);
		
		return "redirect:/board/list";
	}
	
	
	@RequestMapping(value = "mylist")
	public String boardMyListGet(Model model, Criteria cri, String search, Integer type, HttpServletRequest request, User user) {
		
		HttpSession session = request.getSession();
		user = (User)session.getAttribute("user");
		
		if(cri == null) {
			cri = new Criteria();
		}
		
		int totalCount=0;
		PageMaker pageMaker = new PageMaker();
		ArrayList<Board> list;
		pageMaker.setCriteria(cri);
		
		if( type == null ) {
			type = 0;
		}
		if( type == 0 ) {
			totalCount = boardMapper.getCountBoardMyList(user);
			list = (ArrayList)boardMapper.getMyListPage(cri, user);
		}
		else if( type == 1 ) {
			totalCount = boardMapper.getCountBoardMyListByTitle("%"+search+"%", user);
			list = (ArrayList)boardMapper.getMyListPageByTitle(cri, "%"+search+"%", user);
		}
		else if( type == 2 ) {
			totalCount = boardMapper.getCountBoardMyListByAuthor("%"+search+"%", user);
			list = (ArrayList)boardMapper.getMyListPageByAuthor(cri, "%"+search+"%", user);
		}
		else {
			totalCount = boardMapper.getCountBoardMyListByContents("%"+search+"%", user);
			list = (ArrayList)boardMapper.getMyListPageByContents(cri, "%"+search+"%", user);
		}
		
		pageMaker.setTotalCount(totalCount);
		model.addAttribute("list",list);
		model.addAttribute("pageMaker", pageMaker);
		model.addAttribute("search", search);
		model.addAttribute("type", type);
		System.out.println(pageMaker);
		
		
		boolean admin = false;
		
		if(user.getAdmin().compareTo("admin") == 0)
			admin = true;
		model.addAttribute("admin", admin);
		
		
		return "/board/mylist";
	}
	
}


