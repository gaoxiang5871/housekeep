package com.jimikongjian.dao;

import com.jimikongjian.models.Apartment;
import com.jimikongjian.models.Servant;

import java.util.List;


public interface IApartment {
	
	 /**
     * 通过公寓id，查询数据库中相应的公寓
     * @param id
     * @return
     */
    public List<Apartment> getApartmentById(Integer apartment);
    
    /**
     * 查询数据库中所有的房东
     * @return
     */
    public List<Servant> getAllOwner();
    
    /**
     * 通过公寓名称，查询数据库中相应的公寓
     * @param apartment
     * @return
     */
    public List<Apartment> getApartmentByName(Apartment apartment);
}
