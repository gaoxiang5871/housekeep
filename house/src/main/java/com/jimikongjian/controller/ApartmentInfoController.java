package com.jimikongjian.controller;

import com.jimikongjian.dao.IApartmentInfo;
import com.jimikongjian.models.*;
import com.jimikongjian.service.message.ResponseMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/apartment")
public class ApartmentInfoController {
	
	@Autowired
	private IApartmentInfo apartmentInfoMapper;
	
	@Autowired
	private ResponseMessageService res;
	/*查询绑定公寓信息*/
	@RequestMapping(value="/data", method = RequestMethod.GET)
	@ResponseBody
	public ResponseMessage search(@RequestParam(name="apartmentId", defaultValue = "0") Integer apartmentId){
		List<ApartmentInfo> list = apartmentInfoMapper.getApartmentInfoByapartmentId(apartmentId);
		if (list.size() > 0) {
			return res.makeMessage(list, Message.SUCCESS);
		}
		return res.makeMessage(Message.NO_APARTMENT);
	}
}
