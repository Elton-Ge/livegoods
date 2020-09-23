package com.livegoods.buytime.controller;

import com.livegoods.buytime.service.BuytimeService;
import com.livegoods.commons.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: Elton Ge
 * @Date: 20/9/20
 * @Description: com.livegoods.buytime.controller
 * @version: 1.0
 */
@RestController
@CrossOrigin
public class BuytimeController {
    @Autowired
    private BuytimeService buytimeService;
    @GetMapping("/buytime")
    public Results buytimeController(@RequestParam(value = "id") String itemId) {
        return buytimeService.getBuytime(itemId);
    }
}
