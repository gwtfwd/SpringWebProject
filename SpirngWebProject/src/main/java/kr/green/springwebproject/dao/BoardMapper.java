package kr.green.springwebproject.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;


// DB에 있는 게시판에 대한 정보를 처리하기 위한 메소드를 가지고 있는 인터페이스 해당 내용은
// BoardMapper.xml에 쿼리문으로 작성되어 있음

public interface BoardMapper {

	public List<Board> getBoard();
	
	public Board getBoardByNumber(@Param("number") int number);
	
	public void modifyBoard(@Param("board") Board board);
	
	
	// public void insertBoard(@Param("title") String title, @Param("contents") String contents, @Param("author") String author);
	// = public void insertBoard(@Param("board") Board board);
	public void insertBoard(@Param("board") Board board);
	
	
	
	
	
}












