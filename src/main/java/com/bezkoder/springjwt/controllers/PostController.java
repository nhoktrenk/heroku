package com.bezkoder.springjwt.controllers;


import com.bezkoder.springjwt.Service.PostService;
import com.bezkoder.springjwt.models.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @RequestMapping(method = RequestMethod.GET, path = "{id}")
    public ResponseEntity<?> getDetail(@PathVariable Long id) {
        Optional<Post> optionalPost = postService.findById(id);
        if (!optionalPost.isPresent()) {
            ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(optionalPost.get());
    }

    @RequestMapping(method = RequestMethod.PUT, path = "{id}")
    public ResponseEntity<Post> update(@PathVariable Long id, @RequestBody Post post) {
        Optional<Post> optionalPost = postService.findById(id);
        if (!optionalPost.isPresent()) {
            ResponseEntity.badRequest().build();
        }
        Post existCategory = optionalPost.get();
        existCategory.setContent(post.getContent());
        existCategory.setImage(post.getImage());
        existCategory.setTitle(post.getTitle());
        return ResponseEntity.ok(postService.save(existCategory));
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        if (!postService.findById(id).isPresent()) {
            ResponseEntity.badRequest().build();
        }
        postService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
