package com.briup.config;

import com.briup.web.interceptor.LoginCheckInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * springmvc配置类
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    // 添加拦截器
    @Autowired
    private LoginCheckInterceptor loginCheckInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginCheckInterceptor)
                // 所有资源都要拦截
                .addPathPatterns("/**")
                // 哪些资源不需要拦截【不要登录也能访问，不走拦截器】
                .excludePathPatterns(
                        "/login",
                        "/doc.html","/v2/api-docs",
                        "/webjars/**","/swagger-resources","/error",
                        "/favicon.ico");
    }


    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // 设置允许跨域的路径
        registry.addMapping("/**")
                // 设置允许跨域请求的域名
                .allowedOriginPatterns("*")
                // 是否允许cookie
                .allowCredentials(true)
                // 设置允许的请求方式
                .allowedMethods("GET", "POST", "DELETE", "PUT","HEAD","OPTIONS")
                // 设置允许的header属性
                .allowedHeaders("*")
                // 跨域允许时间
                .maxAge(3600);
    }
}
