package com.bezkoder.springjwt.controllers;


import com.bezkoder.springjwt.Service.FeedBackService;
import com.bezkoder.springjwt.models.FeedBack;
import com.bezkoder.springjwt.models.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/feedbacks")
public class FeedBackController {
    @Autowired
    FeedBackService feedBackService;
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<FeedBack>> getList() {
        return ResponseEntity.ok(feedBackService.findAll());
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<FeedBack> createNewPost(@RequestBody FeedBack feedBack) {
        return new ResponseEntity<>(feedBackService.save(feedBack), HttpStatus.OK);
    }


}
