package com.livegoods.details.controller;

import com.livegoods.commons.Results;
import com.livegoods.details.service.DetailsService;
import com.livegoods.pojo.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: Elton Ge
 * @Date: 20/9/20
 * @Description: com.livegoods.details.controller
 * @version: 1.0
 */
@RestController
@CrossOrigin
public class DetailsController {
    @Autowired
    private DetailsService detailsService;
    @GetMapping("/details")
    public Item detailsController(String id){
          return detailsService.selectById(id);
    }
}
