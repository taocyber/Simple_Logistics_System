package com.example.entity;


import lombok.Data;
//登录返回写成一个实体类
@Data
public class RestBean<T> { //<T>响应类型不知
    private int status;//当前状态
    private boolean success;//是否成功
    private T message;//返回数据

    //RestBean类的一个构造器
    private RestBean(int status, boolean success,T message){
       //构造器的实现---初始化对象
        this.status= status;
        this.success= success;
        this.message= message;
    }
    //返回写成工具方法

    public static <T>RestBean<T> success (){
        return new RestBean<>(200,true,null);

    }//默认登录成功状态码：200
    public static <T>RestBean<T> success (T data){
        return new RestBean<>(200,true,data);

    }//登录成功后返回值
    public static <T>RestBean<T> failure (int status){
            return new RestBean<>(status,false,null);

    }//默认登陆失败状态码
    public static <T>RestBean<T> failure (int status, T data){
        return new RestBean<>(status,false,data);

    }//登录失败后返回值
}
