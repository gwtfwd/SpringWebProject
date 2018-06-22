package kr.green.springwebproject.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.green.springwebproject.pagenation.Criteria;


// DB�� �ִ� �Խ��ǿ� ���� ������ ó���ϱ� ���� �޼ҵ带 ������ �ִ� �������̽� �ش� ������
// BoardMapper.xml�� ���������� �ۼ��Ǿ� ����

public interface BoardMapper {

	public List<Board> getBoard();
	
	public Board getBoardByNumber(@Param("number") int number);
	
	public void modifyBoard(@Param("board") Board board);
	
	
	// public void insertBoard(@Param("title") String title, @Param("contents") String contents, @Param("author") String author);
	// = public void insertBoard(@Param("board") Board board);
	public void insertBoard(@Param("board") Board board);
	
	
	// ������ ������ �̿��ؼ� �Խ��� ���� ����Ʈ�� �������� �޼ҵ�
	public List<Board> getListPage(@Param("cri") Criteria cri);
	
	public int getCountBoard();
	
	
	// ����˻�
	public Integer getCountBoardByTitle(@Param("search") String search);
	public List<Board> getListPageByTitle(@Param("cri") Criteria cri, @Param("search") String search);

	// ���ڰ˻�
	public Integer getCountBoardByAuthor(@Param("search") String search);
	public List<Board> getListPageByAuthor(@Param("cri") Criteria cri, @Param("search") String search);
	
	// ����˻�
	public Integer getCountBoardByContents(@Param("search") String search);
	public List<Board> getListPageByContents(@Param("cri") Criteria cri, @Param("search") String search);
	
	
	public void updateBoardDisable(@Param("board") Board board);
	public List<Board> getBoardAll();
	
	public List<Board> getListPageByAdmin(@Param("cri") Criteria cri);
	
	public int getCountBoardByAdmin();
	
	////��������////////////////////////////////////////////////////
	
	public int getCountBoardMyList(@Param("user") User user);
	public List<Board> getMyListPage(@Param("cri") Criteria cri,@Param("user") User user);
	
	public Integer getCountBoardMyListByTitle(@Param("search") String search,@Param("user") User user);
	public List<Board> getMyListPageByTitle(@Param("cri") Criteria cri, @Param("search") String search,@Param("user") User user);

	public Integer getCountBoardMyListByAuthor(@Param("search") String search,@Param("user") User user);
	public List<Board> getMyListPageByAuthor(@Param("cri") Criteria cri, @Param("search") String search,@Param("user") User user);
	
	public Integer getCountBoardMyListByContents(@Param("search") String search,@Param("user") User user);
	public List<Board> getMyListPageByContents(@Param("cri") Criteria cri, @Param("search") String search,@Param("user") User user);
	
	
	
}












