package com.jimikongjian.dao;

import com.jimikongjian.models.User;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * Created by baidu on 17/2/12.
 * @author fuxiuyang
 */
public interface IUser {
	
	 /**
     * 通过用户名，查询数据库中相应的用户
     * @param username
     * @return
     */
    public List<User> getUserListByUserName(String userName);
    

    /**
     * 注册新用户
     * @param username
     * @param password
     * @return
     */
    public boolean addUser(@Param("username") String username, @Param("password") String password);

    /**
     * 注册新用户
     * @param username
     * @param password
     * @return
     */
    public boolean setApartment(User user);

    /**
     * 注册新用户
     * @param username
     * @param password
     * @return
     */
    public boolean changeWord(@Param("username") String username, @Param("password") String password);
    
    /**
     * 通过邮箱地址查询用户信息,返回一个用户列表,如果列表中含有一个或者没有数据,说明正确,如果含有两个以上包含两个,说明查询错误,或者是数据库中数据发生了错误
     * @param email
     * @return List<User>
     */
    public List<User> getUserListByEmail(String email);

    /**
     * 通过邮箱地址查询用户信息,返回一个用户列表,如果列表中含有一个或者没有数据,说明正确,如果含有两个以上包含两个,说明查询错误,或者是数据库中数据发生了错误
     * @param email
     * @return List<User>
     */
    public User getUserByEmail(String email);

    /**
     * 通过邮箱地址获取响应用户的id号
     * @param email
     * @return 如果存在该用户,咱返回该用户的id号,如果不存在则返回-1
     */
    public int getUserIdByEmail(String email);

    /**
     * 插入用户信息,用户邮箱:email和用户密码:password,必须填写,用户其他属性可以选填
     * @param user
     * @return 插入成功返回true,否则返回false
     */
    public boolean insertUser(User user);

    /**
     * 插入用户信息
     * @param email
     * @param password
     * @return 插入成功则返回ture,否则返回false
     */
    public boolean insertUser(@Param("email") String email, @Param("password") String password);

    /**
     * 更新用户密码,
     * 用户对象属性必填项:email,password
     * @param user
     * @return 更新成功返回true,否则返回false
     */
    public boolean updateUserPassword(User user);

    /**
     * 通过emailAddress更新用户的密码password
     * @param email
     * @param password
     * @return 更新成功返回ture,否则返回false
     */
    public boolean updateUserPassword(@Param("email") String email, @Param("password") String password);

    /**
     * 更新用户状态,
     * 用户对象属性必填项:email,status
     * @param user
     * @return 更新成功返回true,否则返回false
     */
    public boolean updateUserStatus(User user);

    /**
     * 更改邮箱地址为email用户的用户状态为status
     * @param email
     * @param status
     * @return 如果更新成功则返回true,否则返回false
     */
    public boolean updateUserStatus(@Param("email") String email, @Param("status") int status);

    /**
     * 更新帐号状态
     * 用户对象属性必填项:email,type
     * @param user
     * @return 更新成功返回true,否则返回false
     */
    public boolean updateUserType(User user);

    /**
     * 更改邮箱地址为email用户的用户状态为type
     * @param email
     * @param type
     * @return 如果更新成功则返回true,否则返回false
     */
    public boolean updateUserType(@Param("email") String email, @Param("type") int type);

    /**
     * 更新用户最后登录的信息
     * 用户对象属性必填项:email,lastLoginTime,lastLoginIp
     * @param user
     * @return 插入成功返回true,否则返回false
     */
    public boolean updateUserLastLoginMessage(User user);

    /**
     * 更新用户
     * @param user
     * @return 更新成功返回true,否则返回false
     */
    public boolean updateUser(User user);

    /**
     * 更新邮箱为emailAddress的用户的最后登录时间lastLoginTime和最后登录Ip
     * @param email
     * @param lastLoginTime
     * @param lastLoginIp
     * @return 如果更新成功则返回true,否则返回false
     */
    public boolean updateUserLastLoginMessage(@Param("email") String email,
                                              @Param("lastLoginTime") Date lastLoginTime,
                                              @Param("lastLoginIp") String lastLoginIp);

    /**
     * 删除用户信息
     * 用户对象属性必填项:email
     * @param user
     * @return 删除成功返回true,否则返回false
     */
    public boolean deleteUser(User user);

    /**
     * 通过用户的邮箱地址删除用户
     * @param email
     * @return 删除成功则返回true,否则返回false
     */
    public boolean deleteUser(String email);
}
