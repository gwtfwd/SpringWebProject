package kr.green.springwebproject.dao;

// DB���� �ҷ��� User������ �����ϱ� ���� Ŭ����

public class User {

	// �� ��������� DB�� �ִ� User���� ���̺�(account)�� �ִ� �Ӽ���� ��ġ�ؾ���
	private String id;
	private String pw;
	private String email;
	
	
	// ��������� �����ϱ� ���� getter�� setter
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	/* toString �޼ҵ� �������̵� : system.out.println�� �� �� �Ű������� ��ü�� �Ѱ��ָ�
	   toString �� ȣ��ǰ� �̶� ���ϴ� ���ڿ��� ������� toString �޼ҵ带 �������̵���  */
	@Override
	public String toString() {
		return "User [id=" + id + ", pw=" + pw + ", email=" + email + "]";
	}
		
}