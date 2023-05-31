package com.example.service;


import com.example.entity.Account;
import com.example.mapper.UserMapper;
import jakarta.annotation.Resource;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
//权限校验服务
public class AuthorizeService implements UserDetailsService {

        @Resource
        UserMapper mapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        if (username ==null)//判断用户名为空，抛出
           throw new UsernameNotFoundException("用户名不能为空");
        Account account = mapper.findAccountByNameDrEmail(username);
        if (account ==null)//判断用户没有找到
           throw new UsernameNotFoundException("用户名或密码错误");

        return User //sql中的User
                .withUsername(account.getUsername())//查找出的用户
                .password(account.getPassword())//数据库中的密码
                .roles("user")//用户角色
                .build();

    }
}
