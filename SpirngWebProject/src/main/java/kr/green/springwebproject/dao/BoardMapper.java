package kr.green.springwebproject.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.green.springwebproject.pagenation.Criteria;


// DB에 있는 게시판에 대한 정보를 처리하기 위한 메소드를 가지고 있는 인터페이스 해당 내용은
// BoardMapper.xml에 쿼리문으로 작성되어 있음

public interface BoardMapper {

	public List<Board> getBoard();
	
	public Board getBoardByNumber(@Param("number") int number);
	
	public void modifyBoard(@Param("board") Board board);
	
	
	// public void insertBoard(@Param("title") String title, @Param("contents") String contents, @Param("author") String author);
	// = public void insertBoard(@Param("board") Board board);
	public void insertBoard(@Param("board") Board board);
	
	
	// 페이지 정보를 이용해서 게시판 글의 리스트를 가져오는 메소드
	public List<Board> getListPage(@Param("cri") Criteria cri);
	
	public int getCountBoard();
	
	
	// 제목검색
	public Integer getCountBoardByTitle(@Param("search") String search);
	public List<Board> getListPageByTitle(@Param("cri") Criteria cri, @Param("search") String search);

	// 저자검색
	public Integer getCountBoardByAuthor(@Param("search") String search);
	public List<Board> getListPageByAuthor(@Param("cri") Criteria cri, @Param("search") String search);
	
	// 내용검색
	public Integer getCountBoardByContents(@Param("search") String search);
	public List<Board> getListPageByContents(@Param("cri") Criteria cri, @Param("search") String search);
	
	
}












