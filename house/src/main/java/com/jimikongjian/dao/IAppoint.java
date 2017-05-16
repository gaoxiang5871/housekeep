package com.jimikongjian.dao;

import com.jimikongjian.models.Appoint;

import java.util.List;



public interface IAppoint {
	
	 /**
     * 通过搜索条件，查询数据库中符合的预约信息
     * @param house
     * @return
     */
    public List<Appoint> getAppointList(Appoint appoint);
    
}
