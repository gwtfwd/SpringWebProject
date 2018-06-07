package kr.green.springwebproject.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller

@RequestMapping(value="/board/*")

public class BoardController {
	
	@RequestMapping(value = "/board/list", method = RequestMethod.GET)
	public String boardListGet() {
		
		
		return "/board/list";
	}
	
	/*@RequestMapping(value="/board/list", method= RequestMethod.POST)
	public String boardListPost() {
		
		return "list";
	}*/
	
}


