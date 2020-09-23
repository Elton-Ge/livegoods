package com.livegoods.login.service;

import com.livegoods.commons.Results;

/**
 * @Auther: Elton Ge
 * @Date: 21/9/20
 * @Description: com.livegoods.login.service
 * @version: 1.0
 */
public interface LoginService {
    Results sendCode(String phone);

    Results loginService(String username,String password);
    
}
