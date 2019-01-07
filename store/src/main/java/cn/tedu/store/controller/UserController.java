package cn.tedu.store.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.tedu.store.entity.User;
import cn.tedu.store.service.IUserService;
import cn.tedu.store.util.ResponseResult;

@RestController
@RequestMapping("/user")
public class UserController extends BaseController {
	@Autowired
	private IUserService userservice;

	@PostMapping("/reg.do")
	public ResponseResult<Void> handleReg(User user) {
		userservice.reg(user);
		return new ResponseResult<>(SUCCESS);
	}

	@PostMapping("/login.do")
	public ResponseResult<Void> handleLogin(@RequestParam("username") String username,
			@RequestParam("password") String password, HttpSession session) {
		User user = userservice.login(username, password);
		session.setAttribute("uid", user.getId());
		session.setAttribute("username", user.getUsername());
		return new ResponseResult<>(SUCCESS);
	}
}
