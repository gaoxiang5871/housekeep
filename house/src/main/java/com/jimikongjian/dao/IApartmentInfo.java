package com.jimikongjian.dao;

import com.jimikongjian.models.ApartmentInfo;

import java.util.List;


public interface IApartmentInfo {
	
	 /**
     * 通过公寓id，查询数据库中相应的公寓出租信息
     * @param apartmentId
     * @return
     */
    public List<ApartmentInfo> getApartmentInfoByapartmentId(int apartmentId);
}
