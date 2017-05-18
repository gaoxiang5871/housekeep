package com.jimikongjian.dao;

import com.jimikongjian.models.Activity;
import com.jimikongjian.models.Apartment;
import com.jimikongjian.models.Servant;

import java.util.List;


public interface IActivity {
	
	 /**
     * 查询所有的操作
     * @return
     */
    public List<Activity> getActivity();

    /**
     * 插入操作
     * @param activity
     */
    public void insertActivity(Activity activity);
}
