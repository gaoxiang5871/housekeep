package com.jimikongjian.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.jimikongjian.dao.IApartment;
import com.jimikongjian.models.Apartment;
import com.jimikongjian.models.Message;
import com.jimikongjian.models.ResponseMessage;
import com.jimikongjian.models.Servant;
import com.jimikongjian.service.message.ResponseMessageService;

@RestController
@RequestMapping(value="/apartment")
public class ApartmentController {
	
	@Autowired
	private IApartment apartmentMapper;
	
	@Autowired
	private ResponseMessageService res;
	/*查询绑定公寓信息*/
	@RequestMapping(value="/search", method = RequestMethod.GET)
	@ResponseBody
	public ResponseMessage search(@RequestParam(name="apartment", defaultValue = "0") Integer apartment){
		List<Apartment> list = apartmentMapper.getApartmentById(apartment);
		if (list.size() > 0) {
			Apartment apart = list.get(0);
			return res.makeMessage(apart, Message.SUCCESS);
		}
		return res.makeMessage(Message.NO_HAVE_USER);
	}
	
	/*查询所有房东*/
	@RequestMapping(value="/allServant", method = RequestMethod.GET)
	@ResponseBody
	public ResponseMessage allServant(){
		List<Servant> list = apartmentMapper.getAllOwner();
		if (list.size() > 0) {
			return res.makeMessage(list, Message.SUCCESS);
		}
		return res.makeMessage(Message.UNKNOWN_ERROR);
	}
	
	/*添加公寓信息*/
	@RequestMapping(value="/addApartment", method = RequestMethod.POST)
	@ResponseBody
	public ResponseMessage addApartment(@RequestBody Apartment apartment){
		List<Apartment> list = apartmentMapper.getApartmentByName(apartment);
		if (list.size() > 0) {
			return res.makeMessage(list, Message.SUCCESS);
		}
		return res.makeMessage(Message.UNKNOWN_ERROR);
	}
}
