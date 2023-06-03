package com.example.service;
//验证相关，继承接口及其延展

import org.springframework.security.core.userdetails.UserDetailsService;

public interface AuthorizeService extends UserDetailsService {
  //实现邮件发送方法
        boolean sendValidEmail(String email, String sessionId);//发送邮件方法

}
