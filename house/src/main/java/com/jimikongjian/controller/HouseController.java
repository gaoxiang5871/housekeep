package com.jimikongjian.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.jimikongjian.dao.IHouse;
import com.jimikongjian.models.Appoint;
import com.jimikongjian.models.House;
import com.jimikongjian.models.Message;
import com.jimikongjian.models.ResponseMessage;
import com.jimikongjian.service.message.ResponseMessageService;

@RestController
@RequestMapping(value="/house")
public class HouseController {
	
	@Autowired
	private IHouse houseMapper;
	
	@Autowired
	private ResponseMessageService res;
	/*查询绑定公寓信息*/
	@RequestMapping(value="/search", method = RequestMethod.POST)
	@ResponseBody
	public ResponseMessage search(@RequestBody House house){
		List<House> list = houseMapper.getHouseList(house);
		if (list.size() > 0) {
			return res.makeMessage(list, Message.SUCCESS);
		}
		return res.makeMessage(Message.NO_HOUSE);
	}
	
	/*添加预约信息*/
	@RequestMapping(value="/addAppoint", method = RequestMethod.POST)
	@ResponseBody
	public ResponseMessage appoint(@RequestBody Appoint appoint){
		houseMapper.addAppoint(appoint);
		return res.makeMessage(Message.SUCCESS);
	}
	
	/*添加房源*/
	@RequestMapping(value="/addHouse", method = RequestMethod.POST)
	@ResponseBody
	public ResponseMessage house(@RequestBody House house0){
		houseMapper.addHouse(house0);
		return res.makeMessage(Message.SUCCESS);
	}
	
	/*修改租赁状态*/
	@RequestMapping(value="/changeStyle", method = RequestMethod.POST)
	@ResponseBody
	public ResponseMessage changeStyle(@RequestBody House house1){
		houseMapper.changeStyle(house1);
		return res.makeMessage(Message.SUCCESS);
	}
	
	/*修改房东信息*/
	@RequestMapping(value="/changeOwner", method = RequestMethod.POST)
	@ResponseBody
	public ResponseMessage changeOwner(@RequestBody House house2){
		houseMapper.changeOwner(house2);
		return res.makeMessage(Message.SUCCESS);
	}
}
