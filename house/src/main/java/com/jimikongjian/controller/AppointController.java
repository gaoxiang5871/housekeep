package com.jimikongjian.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.jimikongjian.dao.IAppoint;
import com.jimikongjian.models.Appoint;
import com.jimikongjian.models.Message;
import com.jimikongjian.models.ResponseMessage;
import com.jimikongjian.service.message.ResponseMessageService;

@RestController
@RequestMapping(value="/appoint")
public class AppointController {
	
	@Autowired
	private IAppoint appointMapper;
	
	@Autowired
	private ResponseMessageService res;
	/*查询绑定公寓信息*/
	@RequestMapping(value="/search", method = RequestMethod.POST)
	@ResponseBody
	public ResponseMessage search(@RequestBody Appoint appoint){
		List<Appoint> list = appointMapper.getAppointList(appoint);
		if (list.size() > 0) {
			return res.makeMessage(list, Message.SUCCESS);
		}
		return res.makeMessage(Message.NO_APPOINT);
	}
	
}
