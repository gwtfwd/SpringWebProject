package kr.green.springwebproject.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;


// DB�� �ִ� �Խ��ǿ� ���� ������ ó���ϱ� ���� �޼ҵ带 ������ �ִ� �������̽� �ش� ������
// BoardMapper.xml�� ���������� �ۼ��Ǿ� ����

public interface BoardMapper {

	public List<Board> getBoard();
	
	public Board getBoardByNumber(@Param("number") int number);
	
	public void modifyBoard(@Param("board") Board board);
	
	
	// public void insertBoard(@Param("title") String title, @Param("contents") String contents, @Param("author") String author);
	// = public void insertBoard(@Param("board") Board board);
	public void insertBoard(@Param("board") Board board);
	
	
	
	
	
}












