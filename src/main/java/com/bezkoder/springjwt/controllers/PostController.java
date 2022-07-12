package com.bezkoder.springjwt.controllers;


import com.bezkoder.springjwt.Service.PostService;
import com.bezkoder.springjwt.models.Category;
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
@RequestMapping("api/posts")
public class PostController {
    @Autowired
    PostService postService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Post>> getList() {
        return ResponseEntity.ok(postService.findAll());
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Post> createNewPost(@RequestBody Post post) {
        return new ResponseEntity<>(postService.save(post), HttpStatus.OK);
    }
}
