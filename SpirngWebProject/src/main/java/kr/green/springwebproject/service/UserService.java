package kr.green.springwebproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import kr.green.springwebproject.dao.User;
import kr.green.springwebproject.dao.UserMapper;


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
	
	
}





