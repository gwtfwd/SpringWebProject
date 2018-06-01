package kr.green.springwebproject.dao;

// DB���� �Խ��� ������ ������ ������ Ŭ���� 

public class Board {

	// ������� : DB�� ���̺�(board)�� �ִ� �Ӽ��� �̸��� ��ġ���Ѿ� ��
	private Integer number;
	private String title;
	private String author;
	private String contents;
	
	
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	
	
	@Override
	public String toString() {
		return "Board [number=" + number + ", title=" + title + ", author=" + author + ", contents=" + contents + "]";
	}
	
		
}
