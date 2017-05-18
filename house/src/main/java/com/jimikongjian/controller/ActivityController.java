package com.jimikongjian.controller;

import com.jimikongjian.dao.IActivity;
import com.jimikongjian.dao.IOrder;
import com.jimikongjian.models.Activity;
import com.jimikongjian.models.Message;
import com.jimikongjian.models.Order;
import com.jimikongjian.models.ResponseMessage;
import com.jimikongjian.service.message.ResponseMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/activity")
public class ActivityController {
	
	@Autowired
	private IActivity activityMapper;
	
	@Autowired
	private ResponseMessageService res;
	//订单哈讯
	@RequestMapping(value="/search", method = RequestMethod.GET)
	@ResponseBody
	public ResponseMessage search(){
		List<Activity> list = activityMapper.getActivity();
		if (list.size() > 0) {
			return res.makeMessage(list, Message.SUCCESS);
		}
		return res.makeMessage(Message.NO_HAVE_USER);
	}
	
	/*新建订单*/
	@RequestMapping(value="/addActivity", method = RequestMethod.POST)
	@ResponseBody
	public ResponseMessage addActivity(@RequestBody Activity activity){
		activityMapper.insertActivity(activity);
		return res.makeMessage(Message.SUCCESS);
	}
}
