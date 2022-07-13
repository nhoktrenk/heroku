package com.bezkoder.springjwt.controllers;


import com.bezkoder.springjwt.Service.FeedBackService;
import com.bezkoder.springjwt.models.FeedBack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @RequestMapping(method = RequestMethod.GET, path = "{id}")
    public ResponseEntity<?> getDetail(@PathVariable Long id) {
        Optional<FeedBack> optionalFeedBack = feedBackService.findById(id);
        if (!optionalFeedBack.isPresent()) {
            ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(optionalFeedBack.get());
    }
    @RequestMapping(method = RequestMethod.PUT, path = "{id}")
    public ResponseEntity<FeedBack> update(@PathVariable Long id, @RequestBody FeedBack feedBack) {
        Optional<FeedBack> optionalFeedBack = feedBackService.findById(id);
        if (!optionalFeedBack.isPresent()) {
            ResponseEntity.badRequest().build();
        }
        FeedBack existFeedBack = optionalFeedBack.get();
        existFeedBack.setContent(feedBack.getContent());
        existFeedBack.setTitle(feedBack.getTitle());
        return ResponseEntity.ok(feedBackService.save(existFeedBack));
    }


    @RequestMapping(method = RequestMethod.DELETE, path = "{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        if (!feedBackService.findById(id).isPresent()) {
            ResponseEntity.badRequest().build();
        }
        feedBackService.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
