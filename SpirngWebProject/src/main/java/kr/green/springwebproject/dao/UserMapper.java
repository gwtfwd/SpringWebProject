package kr.green.springwebproject.dao;

import org.apache.ibatis.annotations.Param;

// java와 mybatis를 연동하는데 데이터교환을 위해 java쪽에서 

public interface UserMapper {

	public User login(@Param("id") String id, @Param("pw") String pw);
	
	public void join(User user);
	
	public User loginById(@Param("id") String id);
	
	public void updateUser(User user);
	
	
	
}









