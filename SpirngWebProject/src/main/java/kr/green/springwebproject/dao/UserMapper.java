package kr.green.springwebproject.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import kr.green.springwebproject.pagenation.Criteria;

// java�� mybatis�� �����ϴµ� �����ͱ�ȯ�� ���� java�ʿ��� 

public interface UserMapper {

	public User login(@Param("id") String id, @Param("pw") String pw);
	
	public void join(User user);
	
	public User loginById(@Param("id") String id);
	
	public void updateUser(User user);
	
	public void updateAccountDisable(@Param("user") User user);
	
	
	public List<User> getListPageByAdmin(@Param("cri") Criteria cri, @Param("user") User user);
	
	public int getCountAccountByAdmin();
	
	public User getAccountById(@Param("user") User user);

	public void deleteUser(@Param("user") User user);


}









