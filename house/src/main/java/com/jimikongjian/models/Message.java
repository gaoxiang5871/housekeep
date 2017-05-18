package com.jimikongjian.models;

import org.springframework.stereotype.Service;


@Service("message")
public class Message {

    /**
     * 没有该用户
     */
    public static final int NO_HAVE_USER = -1;

    /**
     * 密码错误
     */
    public static final int PASSWORD_ERROR = 1003;
    /**
     * 已有用户
     */
    public static final int IS_HAVE_USER = 1000;

    /**
     * 没有符合的房源
     */
    public static final int NO_HOUSE = 2000;
    
    /**
     * 没有该公寓数据
     */
    public static final int NO_APARTMENT = 4000;
    
    /**
     * 没有符合的预约信息
     */
    public static final int NO_APPOINT = 3000;

    /**
     * 密码格式错误
     */
    public static final int PASSWORD_FORMAT_ERROR = 10021;

    /**
     * 邮箱已被注册
     */
    public static final int EMAIL_HAVE_BE_REGISTER = 10030;

    /**
     * 邮箱格式不正确
     */
    public static final int EMAIL_FORMAT_ERROR = 10031;

    /**
     * 验证码不正确
     */
    public static final int VERIFICATION_CODE_ERROR = 10040;

    /**
     * 未登录访问
     */
    public static final int NOT_LOGIN_ACCESS = 10050;

    /**
     * 系统错误
     */
    public static final int SYSTEM_ERROR = 10060;

    /**
     * 不支持的方法
     */
    public static final int UNSUPPORT_METHOD_ERROR = 10070;

    /**
     * 参数错误
     */
    public static final int PARAMS_ERROR = 10080;

    /**
     * 请求时间有误
     */
    public static final int REQUEST_TIME_ERROR = 10090;

    /**
     * 没有权限
     */
    public static final int NO_PERMISSION_ERROR = 10100;

    /**
     * URI路径不存在
     */
    public static final int NO_SUCH_URI_ERROR = 10110;

    /**
     * 未知错误
     */
    public static final int UNKNOWN_ERROR = 10120;

    /**
     * 该空间不存在
     */
    public static final int NO_HAVE_ROOM = 10130;

    /**
     * 该文章不存在
     */
    public static final int NO_HAVE_ARTICLE = 10140;

    /**
     * 该相册不存在
     */
    public static final int NO_HAVE_ALBUM = 10150;

    /**
     * 没有收藏
     */
    public static final int NO_HAVE_LIKE = 10160;

    /**
     * 已经存在该未读评论
     */
    public static final int HAS_UNREAD_COMMENT = 10170;

    /**
     * 验证成功
     */
    public static final int SUCCESS = 0;


}
