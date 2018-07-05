package kr.green.springwebproject.service;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import kr.green.springwebproject.dao.Board;
import kr.green.springwebproject.dao.BoardMapper;
import kr.green.springwebproject.dao.User;
import kr.green.springwebproject.pagenation.Criteria;
import kr.green.springwebproject.utils.UploadFileUtils;

@Service
public class BoardService {

	@Autowired
	private BoardMapper boardMapper;
	
	
	public ArrayList<Board> getListBoard(Integer type, String search, Criteria cri) {

		ArrayList<Board> list = null;
		
		if( type == null ) {
			type = 0;
		}
		if( type == 0 ) {
			list = (ArrayList)boardMapper.getListPage(cri);
		}
		else if( type == 1 ) {
			list = (ArrayList)boardMapper.getListPageByTitle(cri, "%"+search+"%");
		}
		else if( type == 2 ) {
			list = (ArrayList)boardMapper.getListPageByAuthor(cri, "%"+search+"%");
		}
		else {
			list = (ArrayList)boardMapper.getListPageByContents(cri, "%"+search+"%");
		}
		
		return list;
	}
	
	public int getCountByBoardList(Integer type, String search, Criteria cri) {
		
		int totalCount = 0;
		
		if( type == null ) {
			type = 0;
		}
		if( type == 0 ) {
			totalCount = boardMapper.getCountBoard();
		}
		else if( type == 1 ) {
			totalCount = boardMapper.getCountBoardByTitle("%"+search+"%");
		}
		else if( type == 2 ) {
			totalCount = boardMapper.getCountBoardByAuthor("%"+search+"%");
		}
		else {
			totalCount = boardMapper.getCountBoardByContents("%"+search+"%");
		}
		
		return totalCount;
	} 
	
	
	public Board getBoard(int number) {
		
		return boardMapper.getBoardByNumber(number);
	}
	
	public boolean isAuthor(User user,Board board){
		
	      if(user != null) {
	    	  
	         if(user.getId().compareTo(board.getAuthor()) == 0)
	            return true;
	         
	         else
	            return false;
	      }
	      
	      return true;
	   }
	
	public boolean modifyBoard(Board board, MultipartFile file, String uploadPath, Integer del) throws Exception{
		
		// ������ ��¥�� created_date ������Ʈ
		Date created_date = new Date();		
		board.setCreated_date(created_date); 
		
		// ���� ÷������ ��θ� �������� ����
		Board tmp = boardMapper.getBoardByNumber(board.getNumber());
	
		
		// ������ ÷�������� �ִ� ���
		if (file != null && file.getOriginalFilename().length() != 0) {
			String filepath = UploadFileUtils.uploadFile(uploadPath, file.getOriginalFilename(), file.getBytes());
			board.setFilepath(filepath);
		}
		
		// ������ ÷�������� ������ ���� ÷�������� �������� �ϴ� ���
		else if(del != null && tmp.getFilepath() != null) {
			
			// ���� ������ ����
			new File(uploadPath + tmp.getFilepath().replace('/', File.separatorChar)).delete();
			board.setFilepath(null);
		}
		
		// ������ ������ ���� ���� ������ �����ϴ� ���
		else {
			board.setFilepath(tmp.getFilepath());
		}
		
		boardMapper.modifyBoard(board);
		
		return true;
		
	}
	
	
}








