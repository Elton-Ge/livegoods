package com.livegoods.search.controller;

import com.livegoods.commons.Results;
import com.livegoods.search.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: Elton Ge
 * @Date: 20/9/20
 * @Description: com.livegoods.search.controller
 * @version: 1.0
 */
@RestController
@CrossOrigin
public class SearchController {
    @Autowired
    private SearchService searchService;
    @GetMapping("/search")
    public Results search( int page, @RequestParam(defaultValue = "4") int rows, String city, String content){
       return searchService.search(page, rows, city, content);
    }

}
