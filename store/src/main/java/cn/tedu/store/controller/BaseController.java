package cn.tedu.store.controller;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tedu.store.exception.InsertException;
import cn.tedu.store.exception.PasswordNotMatchException;
import cn.tedu.store.exception.ServiceException;
import cn.tedu.store.exception.UserNotFoundException;
import cn.tedu.store.util.ResponseResult;

/*
 * 当前项目中所有控制器类的基类
 */
public abstract class BaseController {
	public static final Integer SUCCESS=200;
	
	@ExceptionHandler(ServiceException.class)
	@ResponseBody
	public ResponseResult<Void> handleException(Exception e) {
		if (e instanceof DuplicateKeyException) {
			return new ResponseResult<>(400, e);
		} else if (e instanceof InsertException) {
			return new ResponseResult<>(500, e);
		}else if(e instanceof UserNotFoundException) {
			return new ResponseResult<>(401, e);
		}else if(e instanceof PasswordNotMatchException) {
			return new ResponseResult<>(402, e);
		}
		
		
		return null;
	}

}
