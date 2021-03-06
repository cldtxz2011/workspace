package cn.tedu.store.mapper;

import cn.tedu.store.entity.User;

public interface UserMapper {
	/*
	 * 插入用户数据
	 * @param user 用户数据
	 * @return 受影响的行数
	 */
	
	Integer addnew (User user);
	/*
	 * 根据用户名查询用户数据
	 * @param username 用户名
	 * @return 匹配的用户数据，如果没有匹配值，则返回null
	 */
	
	User findByUsername(String username);

}
