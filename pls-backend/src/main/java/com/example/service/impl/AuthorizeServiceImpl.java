package com.example.service.impl;

//实现类，继承AuthorizeService接口
import com.example.entity.Account;
import com.example.mapper.UserMapper;
import com.example.service.AuthorizeService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
//权限校验服务
    public class AuthorizeServiceImpl implements AuthorizeService {
//引入spring-mail-username
        @Value("${spring.mail.username}")
        String from;


//登录接口
        @Resource
        UserMapper mapper;

//邮件发送接口
        @Resource
        MailSender mailSender;

//引入redis
        @Resource
    StringRedisTemplate template;

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

    //实现发送邮件
    /*
     * 1.生成对应验证码
     * 2.把邮箱和对应验证码放在redis里面（过期时间5分钟，如果此时重新要求发邮件，
     * 那么，只要剩余时间低于2分钟，就可以重复发一次，重复次过程）
     * 3.发送验证码在指定邮箱
     * 4.如果发送失败，把redis中刚刚插入的删除
     * 5.用户在注册时，再从redis中取出对应键值对，然后进行验证（看验证码是否一致）
     */
    @Override
    public boolean sendValidEmail(String email, String sessionId) {
        String key = "email" + sessionId + ":" +email;
        if (Boolean.TRUE.equals(template.hasKey(key))){
            Long expire = Optional.ofNullable(template.getExpire(key, TimeUnit.SECONDS)).orElse(0L);
            if (expire > 120) //剩余时间高于2分钟，不允许重复发一次
                return false;
        }

        Random random = new Random();
        int code = random.nextInt(899999) +10000;
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);//发送信息来源
        message.setTo(email);//发送地址
        message.setSubject("您的验证邮件");//发送标题
        message.setText("你的验证码："+code
                );//内容
        try{
            mailSender.send(message);
            template.opsForValue().set(key, String.valueOf(code),
                    3 , TimeUnit.MINUTES);//验证码及其邮件地址存在redis中3分钟
            return true;
        } catch (MailException e){
            e.printStackTrace();    //捕获异常打印
        }

        return false;
    }




}
