package kr.green.springwebproject.dao;

import org.apache.ibatis.annotations.Param;

// java�� mybatis�� �����ϴµ� �����ͱ�ȯ�� ���� java�ʿ��� 

public interface UserMapper {

	public User login(@Param("id") String id, @Param("pw") String pw);
	
	public void join(User user);
	
	public User loginById(@Param("id") String id);
	
	public void updateUser(User user);
	
	
	
}









