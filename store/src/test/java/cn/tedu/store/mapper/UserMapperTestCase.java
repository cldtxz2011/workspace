package cn.tedu.store.mapper;

import java.util.Date;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import cn.tedu.store.entity.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTestCase {
	@Autowired
	private UserMapper usermapper;

	@Test
	public void addnew() {
		Date now = new Date();
		User user = new User();
		user.setUsername("root");
		user.setPassword("12345");
		user.setGender(1);
		user.setPhone("1232");
		user.setEmail("as@f.com");
		user.setSalt("asdfg");
		user.setIsdelete(0);

		user.setCreatedUser("admin");
		user.setCreatedTime(now);
		user.setModifiedUser("admin");
		user.setModifiedTime(now);

		Integer rows = usermapper.addnew(user);
		System.err.println("rows=" + rows);
	}

	@Test
	public void findByUsername() {
		String username = "root";
		User user = usermapper.findByUsername(username);
		System.err.println(user);
	}

}
