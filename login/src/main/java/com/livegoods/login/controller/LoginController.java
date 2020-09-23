package com.livegoods.login.controller;

import com.livegoods.commons.Results;
import com.livegoods.login.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Auther: Elton Ge
 * @Date: 21/9/20
 * @Description: com.livegoods.login.controller
 * @version: 1.0
 */
@RestController
@CrossOrigin
public class LoginController {
    @Autowired
    private LoginService loginService;

    @PostMapping("/sendyzm")
    public Results sendCode(String phone){
          return loginService.sendCode(phone) ;
    }
    @PostMapping("/login")
    public  Results login(String username,String password){
        return loginService.loginService(username, password);
    }
}
