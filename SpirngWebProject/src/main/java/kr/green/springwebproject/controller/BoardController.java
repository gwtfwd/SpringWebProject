package kr.green.springwebproject.controller;


import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import kr.green.springwebproject.dao.Board;
import kr.green.springwebproject.dao.BoardMapper;
import kr.green.springwebproject.dao.User;
import kr.green.springwebproject.dao.UserMapper;
import kr.green.springwebproject.pagenation.Criteria;
import kr.green.springwebproject.pagenation.PageMaker;
import kr.green.springwebproject.utils.UploadFileUtils;


@Controller

@RequestMapping(value="/board/*")



public class BoardController {
	
	@Autowired
	BoardMapper boardMapper;
	
	@Resource
	private String uploadPath;
	
	
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
			
		if(user.getAdmin().compareTo("user") !=0)
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
	public String boardWritePost(HttpServletRequest request, Model model, Board board, MultipartFile file) throws Exception {
		
		HttpSession session = request.getSession();
		
		User user = (User)session.getAttribute("user");
		
		board.setAuthor(user.getId());
		
		boardMapper.insertBoard(board);
		
		UploadFileUtils.uploadFile(uploadPath, file.getOriginalFilename(),file.getBytes());
		
		return "redirect:/board/list";
	}
	
	/* 서버에 저장 */
	private String uploadFile(String name, byte[] data) throws Exception{

	    /* 고유한 파일명을 위해 UUID를 이용 */
		UUID uid = UUID.randomUUID();
		String savaName = uid.toString() + "_" + name;
		File target = new File(uploadPath, savaName);
		FileCopyUtils.copy(data, target);
		return savaName;
	}
	
	@ResponseBody
	@RequestMapping("download")
	public ResponseEntity<byte[]> downloadFile(String fileName)throws Exception{
		
	    InputStream in = null;
	    ResponseEntity<byte[]> entity = null;
	    
	    try{
	        String FormatName = fileName.substring(fileName.lastIndexOf(".")+1);
	        HttpHeaders headers = new HttpHeaders();
	        in = new FileInputStream(uploadPath+fileName);

	        fileName = fileName.substring(fileName.indexOf("_")+1);
	        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
	        headers.add("Content-Disposition",  "attachment; filename=\"" 
				+ new String(fileName.getBytes("UTF-8"), "ISO-8859-1")+"\"");
	        entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in),headers,HttpStatus.CREATED);
	    }catch(Exception e) {
	        e.printStackTrace();
	        entity = new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
	    }finally {
	        in.close();
	    }
	    return entity;
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


