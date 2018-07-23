package kr.green.springwebproject.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import kr.green.springwebproject.dao.Board;
import kr.green.springwebproject.dao.User;
import kr.green.springwebproject.dao.UserMapper;
import kr.green.springwebproject.pagenation.Criteria;


@Service

public class UserService {
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	
	
	/* ȸ������ ���������� �Է¹��� ���������� �Է¹޾� ȸ�������� ���� ������
	 * true�� ��ȯ�ϰ�, �������� �������� (���������� ������) false ��ȯ */
	public boolean join(User user) {
		
		User searchuser = userMapper.login(user.getId(), user.getPw());
		
		if( searchuser != null ) {
			
			return false;
		}
		else {
			
			String encPw = passwordEncoder.encode(user.getPw());
			user.setPw(encPw);
			userMapper.join(user);
			user.setAdmin("user");
			
			return true;
		}
		
	}

	
	
	public User login(String id, String pw) {
		
		User user = userMapper.loginById(id);
		
		if(user != null && passwordEncoder.matches(pw, user.getPw())) {

			return user;
		}
		else
			return null;
		
	}
	
	
	
	public User update(User nowUser, User newUser) {
		
		try {
			
			newUser.setId(nowUser.getId());
			newUser.setAdmin(nowUser.getAdmin());
			
			if(newUser.getPw() != null || newUser.getPw().length() != 0) {
				newUser.setPw(nowUser.getPw());
			}
			else {
				String encPw = passwordEncoder.encode(newUser.getPw());
				newUser.setPw(encPw);
			}
			userMapper.updateUser(newUser);
			
			return newUser;
			
		}catch(Exception e) {
			
			System.out.println("ȸ��Ż�𿡼� ����ó�� �߻�!!");
			e.printStackTrace();
			
			return null;
		}
		
	}
	
	public boolean unjoin(User user) {
		
		try {
			userMapper.deleteUser(user);
			
			return true;
			
		}catch(Exception e){
			
			System.out.println("ȸ��Ż�𿡼� ����ó�� �߻�!!");
			e.printStackTrace();
			return false;
		}
	}

	// ���������� �������� ����������(true) �ƴ���(false) �˷���
	public boolean isAdmin(User user) {
		
		if(user.getAdmin().compareTo("user") !=0) {
			return true;
		}
		return false;
	}
	
	
	public boolean isSuperadmin(User user) {
		
		if(user.getAdmin().compareTo("superadmin") ==0)
			return true;
		return false;
	}
	
	public int getCountAccountByAdmin() {
		
		int totalCount = 0;
		
		totalCount = userMapper.getCountAccountByAdmin();
		
		return totalCount;
	}
	
	public ArrayList<User> getListPageByAdmin(Criteria cri, User user) {
		
		ArrayList<User> list = null;
		
		list = (ArrayList)userMapper.getListPageByAdmin(cri, user);
		
		return list;
	}
	
	public User getUser(User user) {
		
		return userMapper.getAccountById(user);
	}
	
	public boolean updateAccountDisable(User user) {
		
		userMapper.updateAccountDisable(user);
		
		return true;
	}
	
	public void setUserById(String id, String admin) {
		
		User user = userMapper.getUserById(id);
		
		user.setAdmin(admin);
		userMapper.updateUser(user);
	}
	
	
	public boolean checkUser(String id) {
		
		User user = userMapper.loginById(id);
		
		if( user != null ) {
			return true;
		}
		
		return false;
	}
	
	
	
	
}





