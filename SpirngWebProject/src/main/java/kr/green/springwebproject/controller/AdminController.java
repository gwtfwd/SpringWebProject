package kr.green.springwebproject.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.green.springwebproject.dao.Board;
import kr.green.springwebproject.dao.BoardMapper;
import kr.green.springwebproject.pagenation.Criteria;
import kr.green.springwebproject.pagenation.PageMaker;

@Controller
@RequestMapping(value="/admin/*")
public class AdminController {

	@Autowired
	BoardMapper boardMapper;		// 게시판 정보를 불러오기 위해
	
	
	// 관리자페이지
	@RequestMapping(value="/main", method=RequestMethod.GET)
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
		System.out.println(pageMaker);
		
		
		return "admin/admin";
	}
	
	@RequestMapping(value="/board/disable")
	public String boardDisable(Model model, Integer number, String disable) throws Exception {
		
		Board board = boardMapper.getBoardByNumber(number);
		board.setDisable(disable);
		boardMapper.updateBoardDisable(board);
		
		return "redirect:/admin/main";
	}
		
}