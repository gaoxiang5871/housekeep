package com.jimikongjian.service;

import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by baidu on 17/2/12.
 * @author fuxiuyang
 */
@Service("ip")
public class IP {
//    private String ip;

    /**
     * 判断一串字符串是否为ip地址
     * @param ip
     * @return 如果是IP地址则返回true,否则返回false
     */
    public boolean isIP(String ip){
        if (null==ip || "".equals(ip)) return false;
        String ipRegex = "([1-9]|[1-9][0-9]|1\\d\\d|2[0-4]\\d|25[0-5])\\." +
                "([1-9]|[1-9][0-9]|1\\d\\d|2[0-4]\\d|25[0-5])\\." +
                "([1-9]|[1-9][0-9]|1\\d\\d|2[0-4]\\d|25[0-5])\\." +
                "([1-9]|[1-9][0-9]|1\\d\\d|2[0-4]\\d|25[0-5])";
        Pattern p = Pattern.compile(ipRegex);
        Matcher m = p.matcher(ip);
        return m.matches();
    }
}
