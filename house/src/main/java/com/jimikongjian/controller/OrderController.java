package com.jimikongjian.controller;

import com.jimikongjian.dao.IOrder;
import com.jimikongjian.models.Message;
import com.jimikongjian.models.Order;
import com.jimikongjian.models.ResponseMessage;
import com.jimikongjian.service.message.ResponseMessageService;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/order")
public class OrderController {
	
	@Autowired
	private IOrder orderMapper;
	
	@Autowired
	private ResponseMessageService res;
	//用户登录判断
	@RequestMapping(value="/search", method = RequestMethod.POST)
	@ResponseBody
	public ResponseMessage login(@RequestBody Order order){
		List<Order> list = orderMapper.getOrder(order);
		if (list.size() > 0) {
			return res.makeMessage(list, Message.SUCCESS);
		}
		return res.makeMessage(Message.NO_HAVE_USER);
	}
	
	/*新建链接*/
	@RequestMapping(value="/addOrder", method = RequestMethod.POST)
	@ResponseBody
	public ResponseMessage newUser(@RequestBody Order order){
		orderMapper.insertOrder(order);
		return res.makeMessage(Message.SUCCESS);
	}
}
