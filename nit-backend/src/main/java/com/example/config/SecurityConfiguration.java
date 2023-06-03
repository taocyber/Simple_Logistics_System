package com.example.config;

import com.alibaba.fastjson.JSONObject;
import com.example.entity.RestBean;
import com.example.service.AuthorizeService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.sql.DataSource;
import java.io.IOException;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    @Resource //使用校验用户
    AuthorizeService authorizeService;

    @Resource
    DataSource dataSource;//引入数据源

    @Bean
        public SecurityFilterChain filterChain(HttpSecurity http,
                                              PersistentTokenRepository repository )  throws Exception {
        return http
                .authorizeHttpRequests()
                .requestMatchers("/api/auth/**").permitAll()  //验证相关全部放行
                .anyRequest().authenticated()
                .and()
                //登录表单
                .formLogin()
                //登录接口地址
                .loginProcessingUrl("/api/auth/login")
                //返回告诉前端是否登录成功
                .successHandler(this::onAuthenticationSuccess)//登录成功
                .failureHandler(this::onAuthenticationFailure)//登录失败
                .and()
                //登出接口
                .logout()
                //登出接口表单
                .logoutUrl("/api/auth/logout")
                .logoutSuccessHandler(this::onAuthenticationSuccess)
                .and()
                .rememberMe()
                .rememberMeParameter("remember")//修改默认参数
                .tokenRepository(repository)
                .tokenValiditySeconds(3600 * 24 * 7)//记住我时间限制为7天
                .and()
                .csrf()
                .disable()
                //请求跨域实现
                .cors()
                .configurationSource(this.CorsConfigurationSource())
                .and()
//认证异常处理       .exceptionHandling()
//                .authenticationEntryPoint(this::commence)
//                .and()
                .build();

        }

//引入JDBC ，对”记住我“功能的’token‘进行数据存储
        @Bean
        public PersistentTokenRepository tokenRepository(){
            JdbcTokenRepositoryImpl jdbcTokenRepository=new JdbcTokenRepositoryImpl();
            jdbcTokenRepository.setDataSource(dataSource);//设定数据源
            jdbcTokenRepository.setCreateTableOnStartup(false);//创建表
            return jdbcTokenRepository;

        }




//前后端进行请求跨域
        public CorsConfigurationSource CorsConfigurationSource(){
            CorsConfiguration cors  = new CorsConfiguration();
            cors.addAllowedOriginPattern("*");
            cors.setAllowCredentials(true);
            cors.addAllowedHeader("*");
            cors.addAllowedMethod("*");
            cors.addExposedHeader("*");
            UrlBasedCorsConfigurationSource source= new UrlBasedCorsConfigurationSource();
            source.registerCorsConfiguration("/**",cors);
            return source;
        }


        //连接数据库校验用户名和密码
        @Bean
        public AuthenticationManager authenticationManager(HttpSecurity security) throws Exception {
            return security
                    .getSharedObject(AuthenticationManagerBuilder.class)
                    .userDetailsService(authorizeService)
                    .and()
                    .build();
        }

        @Bean
        public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();

        }



    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        response.setHeader("Content-type", "text/html;charset=UTF-8");//浏览器用utf8来解析返回的数据
        response.setCharacterEncoding("utf-8");//告诉servlet用UTF-8转码，而不是用默认的ISO8859
        if (request.getRequestURI().endsWith("/login"))
            response.getWriter().write(JSONObject.toJSONString(RestBean.success("登录成功")));//登录成功响应返回，把返回值转换成jsonString输出。
        else if (request.getRequestURI().endsWith("/logout"))
        response.getWriter().write(JSONObject.toJSONString(RestBean.success("退出登录成功")));//登出成功响应返回，把返回值转换成jsonString输出。
    }

    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException {
        response.setHeader("Content-type", "text/html;charset=UTF-8");//浏览器用utf8来解析返回的数据
        response.setCharacterEncoding("utf-8");//告诉servlet用UTF-8转码，而不是用默认的ISO8859
        response.getWriter().write(JSONObject.toJSONString(RestBean.failure(401,exception.getMessage())));


    }

//    用户请求处理过程中遇到认证异常时,返回处理
//    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException {
//        response.setHeader("Content-type", "text/html;charset=UTF-8");//浏览器用utf8来解析返回的数据
//        response.setCharacterEncoding("utf-8");//告诉servlet用UTF-8转码，而不是用默认的ISO8859
//        response.getWriter().write(JSONObject.toJSONString(RestBean.failure(401,exception.getMessage())));
//    }
}