package com.example.demo.web;

import com.example.demo.interceptor.LoginInterceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 对静态资源和拦截器进行功能扩展
 * WebMvcConfigurerAdapter
 */
@Configuration
public class MyWebMvcConfigAdapter extends WebMvcConfigurerAdapter {

    /**
     * 增加自定义的资源文件映射
     *
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/image/**").addResourceLocations("classpath:/image/");
        super.addResourceHandlers(registry);
    }

    /**
     * 为页面跳转添加映射
     *
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/user/toLogin").setViewName("login");
        registry.addViewController("/learn").setViewName("learn-resource");
        registry.addViewController("/file/toupload").setViewName("upload");  // 跳转到上传页面
        registry.addViewController("/file/uploadresult").setViewName("uploadresult");  // 跳转上传结果页面
        super.addViewControllers(registry);
    }

    /**
     * 添加拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**").excludePathPatterns("/user/toLogin", "/user/login");
        super.addInterceptors(registry);
    }
}
