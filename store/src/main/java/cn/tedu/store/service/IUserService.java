package cn.tedu.store.service;

import org.springframework.dao.DuplicateKeyException;

import cn.tedu.store.entity.User;
import cn.tedu.store.exception.InsertException;
import cn.tedu.store.exception.PasswordNotMatchException;
import cn.tedu.store.exception.UserNotFoundException;

public interface IUserService {
	/*
	 * 用户注册
	 * @param user 用户信息
	 * @return 成功注册的用户信息
	 * 
	 */
	User reg(User user) throws DuplicateKeyException,InsertException;
	
	
	User login(String username,String password) throws UserNotFoundException,PasswordNotMatchException;
}
