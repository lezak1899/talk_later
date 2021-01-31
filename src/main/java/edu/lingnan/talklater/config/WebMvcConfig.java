package edu.lingnan.talklater.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @description:跨域不拦截
 * @author: likunzhu
 * @date:
 */

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
//                .allowedOrigins("http://localhost:8080")
                .allowedOrigins("*")
                .allowedMethods("*")
                .allowedHeaders("*");
    }
}