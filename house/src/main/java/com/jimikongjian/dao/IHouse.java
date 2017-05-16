package com.jimikongjian.dao;

import com.jimikongjian.models.Appoint;
import com.jimikongjian.models.House;


import java.util.List;



public interface IHouse {
	
	 /**
     * 通过搜索条件，查询数据库中符合的房源
     * @param house
     * @return
     */
    public List<House> getHouseList(House house);
    
    /**
     * 添加预约信息
     * @param appoint
     * @return
     */
    public void addAppoint(Appoint appoint);

    /**
     * 添加房源
     * @param house
     * @return
     */
    public void addHouse(House house);
    
    /**
     * 修改房东
     * @param house
     * @return
     */
    public boolean changeOwner(House house);
    
    /**
     * 修改租赁状态
     * @param house
     * @return
     */
    public boolean changeStyle(House house);
}
