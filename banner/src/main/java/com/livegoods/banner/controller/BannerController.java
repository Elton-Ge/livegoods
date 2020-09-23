package com.livegoods.banner.controller;

import com.livegoods.banner.service.BannerService;
import com.livegoods.commons.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: Elton Ge
 * @Date: 17/9/20
 * @Description: com.livegoods.banner.controller
 * @version: 1.0
 */
@RestController
@CrossOrigin
public class BannerController {
    @Autowired
    private BannerService bannerService;
    @RequestMapping("/banner")
    public Results banner(){
        return bannerService.getBanners();
    }
}
