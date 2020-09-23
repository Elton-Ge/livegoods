package com.livegoods.comment.controller;

import com.livegoods.comment.service.CommentService;
import com.livegoods.commons.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Auther: Elton Ge
 * @Date: 20/9/20
 * @Description: com.livegoods.comment.controller
 * @version: 1.0
 */
@RestController
//@CrossOrigin
public class CommentController {
    @Autowired
    private CommentService commentService;
    @GetMapping("/comment")
    public Results commentController(@RequestParam(value = "id") String itemId, int page , @RequestParam(defaultValue = "4") int rows){
        return commentService.findComment(itemId, page, rows);
    }

    @PostMapping("/feelback")
    public Results feelback(  String feelback, String orderId) {
        return commentService.getfeelback(feelback, orderId);
    }
}
