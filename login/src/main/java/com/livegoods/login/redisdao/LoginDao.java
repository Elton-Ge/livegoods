package com.livegoods.login.redisdao;

import com.livegoods.login.pojo.LoginLog;
import com.livegoods.login.pojo.ValidateCode;

/**
 * @Auther: Elton Ge
 * @Date: 21/9/20
 * @Description: com.livegoods.login.redisdao
 * @version: 1.0
 * 从redis中存储和读取验证码、手机号
 */
public interface LoginDao {
    //验证码缓存到redis中
    void  set(String key, Object validateCode);
    ValidateCode   getCode(String key);
    Boolean deleteCode(String key);

    //新增登录日志到Mongodb
    void  insertLoginLog(LoginLog loginLog);

}
