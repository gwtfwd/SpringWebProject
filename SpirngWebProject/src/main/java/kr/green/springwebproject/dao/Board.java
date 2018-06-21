package kr.green.springwebproject.dao;

// DB���� �Խ��� ������ ������ ������ Ŭ���� 

public class Board {

	// ������� : DB�� ���̺�(board)�� �ִ� �Ӽ��� �̸��� ��ġ���Ѿ� ��
	private Integer number;
	private String title;
	private String author;
	private String contents;
	private String admin;
	private String disable = "false";
	
	
	public String getAdmin() {
		return admin;
	}
	public void setAdmin(String admin) {
		this.admin = admin;
	}
	public String getDisable() {
		return disable;
	}
	public void setDisable(String disable) {
		this.disable = disable;
	}
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
