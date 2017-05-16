package com.jimikongjian.dao;

import com.jimikongjian.models.Apartment;
import com.jimikongjian.models.Order;
import com.jimikongjian.models.Servant;

import java.util.List;


public interface IOrder {
	
	 /**
     * 通过公寓id，查询数据库中相应的公寓
     * @param order
     * @return
     */
    public List<Order> getOrder(Order order);
    
    /**
     * 插入订单
     * @return
     */
    public void insertOrder(Order order);
}
