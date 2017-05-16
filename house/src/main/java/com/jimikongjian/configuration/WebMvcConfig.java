package com.jimikongjian.configuration;

import com.jimikongjian.filter.CORSFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by fuxiuyang on 17/4/1.
 * 该类是配置拦截器的
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter{

    /**
     * 需要在该拦截器上添加@Configuration注解才能注入
     */
    @Autowired
    private CORSFilter corsFilter;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        super.addInterceptors(registry);
//        添加拦截器，并指定拦截器拦截的路径
        registry.addInterceptor(corsFilter).addPathPatterns("/**");
        super.addInterceptors(registry);


    }
}
