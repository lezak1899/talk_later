package edu.lingnan.talklater.config;

import edu.lingnan.talklater.modules.security.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @description:跨域不拦截
 * @author: likunzhu
 * @date:
 */

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    /**
     * 跨域不拦截
     * @param registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
//                .maxAge(168000)
//                .allowedOrigins("http://localhost:8080")
                .allowedOrigins("*")
                .allowedMethods("*")
                .allowedHeaders("*")
                .allowCredentials(true);
    }

    /**
     * 登录拦截器
     * @param registry
     */
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/api/**").excludePathPatterns(
//                "/api/user/login","/api/user/logout");
//    }
}