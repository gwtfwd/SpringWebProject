package kr.green.springwebproject.dao;

import org.apache.ibatis.annotations.Param;

// java와 mybatis를 연동하는데 데이터교환을 위해 java쪽에서 

public interface UserMapper {

	public String getEmail(@Param("id") String id);
	
	
	
	
	
	
	
	
	
	
}









