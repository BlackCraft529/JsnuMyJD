package com.jsnu.jd.jsnujd.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author 魏荣轩
 * @date 2020/7/29 0:34
 *
 * 拓展Spring MVC
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Autowired
    private UserSecurityInterceptor securityInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration addInterceptor = registry.addInterceptor(securityInterceptor);
        // 排除配置
        addInterceptor.excludePathPatterns("/error");
        //排除静态资源
        addInterceptor.excludePathPatterns("/static/**");
        addInterceptor.excludePathPatterns("/view/login");
        addInterceptor.excludePathPatterns("/login/check");
        // 拦截配置
        addInterceptor.addPathPatterns("/**");
    }
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //路径
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }
}
