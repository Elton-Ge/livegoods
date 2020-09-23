package com.livegoods.buyaction.controller;

import com.livegoods.buyaction.service.BuyactionService;
import com.livegoods.commons.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: Elton Ge
 * @Date: 22/9/20
 * @Description: com.livegoods.buyaction.controller
 * @version: 1.0
 */
@RestController
@CrossOrigin
public class BuyactionController {
    @Autowired
    private BuyactionService buyactionService;
    @GetMapping("/buyaction")
    public Results buyactionController(String id,String user){
        return buyactionService.buyAction(id, user);

    }
}
