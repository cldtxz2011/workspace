package cn.tedu.store.impl;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import cn.tedu.store.entity.User;
import cn.tedu.store.exception.InsertException;
import cn.tedu.store.exception.PasswordNotMatchException;
import cn.tedu.store.exception.UserNotFoundException;
import cn.tedu.store.mapper.UserMapper;
import cn.tedu.store.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {
	@Autowired
	private UserMapper userMapper;

	@Override
	public User login(String username, String password) throws UserNotFoundException, PasswordNotMatchException {
		User user = findByUsername(username);
		if (user == null) {
			throw new UserNotFoundException("用户名不存在");
		}
		String salt = user.getSalt();
		String md5password = getMD5Password(password, salt);
		if (user.getPassword().equals(md5password)) {
			if (user.getIsdelete() == 0) {
				user.setSalt(null);
				user.setPassword(null);
				return user;
			} else {
				throw new UserNotFoundException("用户名不存在");
			}
		} else {
			throw new PasswordNotMatchException("密码错误");
		}

	}

	@Override
	public User reg(User user) throws DuplicateKeyException, InsertException {
		/*
		 * 根据注册用户名查询用户数据 判断数据库中是否存在该用户名。 若存在，则返回duplicatekeyexception 若不存在，则注册成功
		 */
		User cuser = findByUsername(user.getUsername());
		if (cuser == null) {
			Date now = new Date();
			user.setIsdelete(0);
			user.setCreatedUser(user.getUsername());
			user.setCreatedTime(now);
			user.setModifiedUser(user.getUsername());
			user.setModifiedTime(now);

			String salt = UUID.randomUUID().toString();
			String oldpassword = user.getPassword();
			String md5password = getMD5Password(oldpassword, salt);
			user.setPassword(md5password);
			user.setSalt(salt);

			addnew(user);
			return user;
		} else {
			throw new DuplicateKeyException("用户名（" + user.getUsername() + "）已经被占用");
		}

	}

	/**
	 * 将密码加盐（MD5）
	 * 
	 * @param oldpassword
	 * @param salt
	 * @return
	 */
	private String getMD5Password(String oldpassword, String salt) {
		String str = salt + oldpassword + salt;
		for (int i = 0; i < 10; i++) {
			str = DigestUtils.md5DigestAsHex(str.getBytes());
		}
		return str;
	}

	/*
	 * 插入用户数据
	 * 
	 * @param user 用户数据
	 * 
	 * 
	 */

	private void addnew(User user) {
		Integer rows = userMapper.addnew(user);
		if (rows != 1) {
			throw new InsertException("增加用户数据出现未知错误");
		}

	}
	/*
	 * 根据用户名查询用户数据
	 * 
	 * @param username 用户名
	 * 
	 * @return 匹配的用户数据，如果没有匹配值，则返回null
	 */

	private User findByUsername(String username) {

		return userMapper.findByUsername(username);
	}

}
