package com.jimikongjian.dao;

import com.jimikongjian.models.Order;

import java.util.List;


public interface IOrder {
	
	 /**
     * 通过查询条件，查询数据库中相应的订单信息
     * @param order
     * @return
     */
    public List<Order> getOrder(Order order);
    
    /**
     * 新建订单
     * @return
     */
    public void insertOrder(Order order);

    /**
     * 修改订单状态
     * @param id
     * @param orderTag
     * @return
     */
    public boolean changeOrder(Order order);
}
