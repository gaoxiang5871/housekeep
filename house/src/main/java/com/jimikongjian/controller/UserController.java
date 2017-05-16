package com.jimikongjian.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.jimikongjian.dao.IUser;
import com.jimikongjian.models.Message;
import com.jimikongjian.models.ResponseMessage;
import com.jimikongjian.models.User;
import com.jimikongjian.service.message.ResponseMessageService;

@RestController
@RequestMapping(value="/user")
public class UserController {
	
	@Autowired
	private IUser userMapper;
	
	@Autowired
	private ResponseMessageService res;
	/*用户登录判断*/
	@RequestMapping(value="/login", method = RequestMethod.GET)
	@ResponseBody
	public ResponseMessage login(@RequestParam(name="username", defaultValue = "") String username,
								@RequestParam(name="password", defaultValue = "") String password){
		List<User> list = userMapper.getUserListByUserName(username);
		if (list.size() > 0) {
			User user = list.get(0);
			if (user.getPassWord().equals(password)) {
				return res.makeMessage(user, Message.SUCCESS);
			} else {
				return res.makeMessage(Message.PASSWORD_ERROR);
			}
		}
		return res.makeMessage(Message.NO_HAVE_USER);
	}
	
	/*注册新用户*/
	@RequestMapping(value="/addUser", method = RequestMethod.GET)
	@ResponseBody
	public ResponseMessage newUser(@RequestParam(name="username", defaultValue = "") String username,
								@RequestParam(name="password", defaultValue = "") String password){
		List<User> list = userMapper.getUserListByUserName(username);
		if (list.size() > 0) {
			return res.makeMessage(Message.IS_HAVE_USER);
		}
		userMapper.addUser(username, password);
		return res.makeMessage(Message.SUCCESS);
	}
	
	/*修改密码*/
	@RequestMapping(value="/changeWord", method = RequestMethod.GET)
	@ResponseBody
	public ResponseMessage changeWord(@RequestParam(name="username", defaultValue = "") String username,
								@RequestParam(name="password", defaultValue = "") String password,
								@RequestParam(name="pass", defaultValue = "") String pass){
		List<User> list = userMapper.getUserListByUserName(username);
		User user1 = list.get(0);
		if (user1.getPassWord().equals(password)) {
			userMapper.changeWord(username, pass);
			return res.makeMessage(Message.SUCCESS);
		} else {
			return res.makeMessage(Message.PASSWORD_ERROR);
		}
	}
	
	/*修改密码*/
	@RequestMapping(value="/demo", method = RequestMethod.GET)
	@ResponseBody
	public String demo(){
		return "Hello";
	}
}
