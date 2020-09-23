package com.livegoods.login.pojo;

import lombok.Data;

/**
 * @Auther: Elton Ge
 * @Date: 21/9/20
 * @Description: com.livegoods.login.pojo
 * @version: 1.0
 * 用户登录验证码
 */
@Data
public class ValidateCode {
    private String phone;
    private String randomCode;
    
}
