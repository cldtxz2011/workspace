package cn.tedu.store.service;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.tedu.store.entity.User;
import cn.tedu.store.mapper.UserMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTestCase {
	@Autowired
	private IUserService iuserservice;

	@Test
	public void reg() {
		try {
			User user = new User();
			user.setUsername("fuck1");
			user.setPassword("123");
			user.setGender(0);
			user.setPhone("132");
			user.setEmail("132@cl.com");

			User reuser = iuserservice.reg(user);
			System.err.println(reuser);

		} catch (Exception e) {
			System.err.println("错误类型：" + e.getClass().getName());
			System.err.println("错误描述：" + e.getMessage());
		}

	}
	
	@Test
	public void login() {
		try {
		
			String username="cl";
			String password="cl";
			
			User user = iuserservice.login(username, password);
			System.err.println(user);
			
		} catch (Exception e) {
			System.err.println("错误类型：" + e.getClass().getName());
			System.err.println("错误描述：" + e.getMessage());
		}
		
	}

}
