//package edu.lingnan.talklater.config;
//
//
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
///**
// * Description:
// * date: 2021/1/31 0:05
// * author likunzhu
// * version
// * since JDK 1.8
// */
//
//
//@EnableWebSecurity
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        super.configure(http);//默认处理
//        //授权定制规则，你能干什么
//        //设置白名单，即不需要Security做处理的
//        //需要作处理的，针对的都是请求
//    }
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        super.configure(auth);
//        //定义认证规则，用户登录。证明你是谁
//    }
//
//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        super.configure(web);
//        //平时用的不多
//        //设置白名单，即不需要Security做处理的。主要是针对静态资源
//    }
//}