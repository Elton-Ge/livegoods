package com.livegoods.login.pojo;

import lombok.Data;

import java.util.Date;

/**
 * @Auther: Elton Ge
 * @Date: 21/9/20
 * @Description: com.livegoods.login.pojo
 * @version: 1.0
 *  登录日志实体
 *  记录一个用户登录的日志数据。
 *  当前系统是一个无注册逻辑的系统，用户只要提供有效的手机号，即可通过验证码登录。
 *
 */
@Data
public class LoginLog {
    private String id;
    private String username; // 用户名，就是手机号。
    private String type; // 登录方式， 1 - 验证码登录； 2 - 用户名密码登录
    private Date loginTime; // 登录时间
    private Boolean isSuccess; // 是否登录成功， true - 成功； false - 失败
    private String message; // 日志消息， 如：手机号不存在； 验证码错误； 验证码过期等。
}
