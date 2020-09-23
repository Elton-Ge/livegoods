package com.livegoods.login.service.impl;

import com.livegoods.commons.Results;
import com.livegoods.login.pojo.LoginLog;
import com.livegoods.login.pojo.ValidateCode;
import com.livegoods.login.redisdao.LoginDao;
import com.livegoods.login.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Random;
import java.util.UUID;

/**
 * @Auther: Elton Ge
 * @Date: 21/9/20
 * @Description: com.livegoods.login.service.impl
 * @version: 1.0
 */
@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private LoginDao loginDao;

    /**
     * 发送验证码
     * 动态生成一个长度为4的随机数字字符串作为验证码。
     * 保存手机号和验证码到redis，有效时长为2分钟。
     * <p>
     * 在验证码仍旧有效的时候，再次申请验证码，应该如何处理？
     * 1、 生成新的验证码，有效时间刷新为2分钟，覆盖旧验证码。 对客户端比较友好，服务端成本提高。
     * 2、 通知客户端，验证码仍旧可用，不重新发送。 对客户端来说，友好度降低；服务器压力降低，成本降低。
     *
     * @param phone 手机号。
     * @param phone
     * @return
     */
    @Override
    public Results sendCode(String phone) {
      /*  ValidateCode oldCode = loginDao.getCode(phone);
        if (oldCode != null) {
            // 已有验证码，且有效。 不生成新的验证码，直接通知客户，使用现有的验证码
            return Results.error("原验证码仍旧可用，请不要重复申请验证码！");
        }*/

        Random random = new Random();
        int end3 = random.nextInt(9999);
        //如果不足四位前面补0
        String randomCode = String.format("%04d", end3);
        ValidateCode validateCode = new ValidateCode();
        validateCode.setPhone(phone);
        validateCode.setRandomCode(randomCode);
        System.out.println(phone + ":  " + randomCode);
        loginDao.set(phone, validateCode);

        Results results = Results.ok();
        results.setMsg("验证码发送成功");


        return results;
    }

    /**
     * 登录逻辑实现
     * @param username
     * @param password
     * @return
     */
    @Override
    public Results loginService(String username, String password) {
        ValidateCode code = loginDao.getCode(username);
        LoginLog loginLog = new LoginLog();
//        loginLog.setId(UUID.randomUUID().toString());
        loginLog.setUsername(username);
        Date date = new Date();
        loginLog.setLoginTime(date);
        loginLog.setType("1");
        if (code!=null&&code.getRandomCode().equals(password)){
            loginLog.setIsSuccess(true);
            loginLog.setMessage(username+"用户登录成功了");
            loginDao.insertLoginLog(loginLog);
            loginDao.deleteCode(username);
            Results results = Results.ok();
            results.setMsg("登录成功");
            return  results;
        } else {
            loginLog.setMessage(username+"用户尝试登录，但该次登录失败");
            loginLog.setIsSuccess(false);
            loginDao.insertLoginLog(loginLog);
            return Results.error("手机号或验证码错误，请重新登录");
        }
    }
}
