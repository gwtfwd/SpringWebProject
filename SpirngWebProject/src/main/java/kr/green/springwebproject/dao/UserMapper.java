package kr.green.springwebproject.dao;

import org.apache.ibatis.annotations.Param;

// java�� mybatis�� �����ϴµ� �����ͱ�ȯ�� ���� java�ʿ��� 

public interface UserMapper {

	public User login(@Param("id") String id, @Param("pw") String pw);
	
	
	
	
	
	
	
	
	
}









