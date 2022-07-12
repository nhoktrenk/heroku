package com.bezkoder.springjwt.controllers;


import com.bezkoder.springjwt.Service.UserService;
import com.bezkoder.springjwt.models.User;
import com.bezkoder.springjwt.payload.response.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/users")
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    PasswordEncoder encoder;


    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<User>> getList() {
        return ResponseEntity.ok(userService.findAll());
    }


    @RequestMapping(method = RequestMethod.DELETE, path = "{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        if (!userService.findById(id).isPresent()) {
            ResponseEntity.badRequest().build();
        }
        userService.deleteById(id);
        return ResponseEntity.ok(new MessageResponse("Xóa thành công người dùng!"));
    }

    @RequestMapping(method = RequestMethod.GET, path = "{id}")
    public ResponseEntity<?> getDetail(@PathVariable Long id) {
        Optional<User> optionalUser = userService.findById(id);
        if (!optionalUser.isPresent()) {
            ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(optionalUser.get());
    }

    @RequestMapping(method = RequestMethod.PUT, path = "{id}")
    public ResponseEntity<User> update(@PathVariable Long id, @RequestBody User user) {
        Optional<User> optionalUser = userService.findById(id);
        if (!optionalUser.isPresent()) {
            ResponseEntity.badRequest().build();
        }
        User existUser = optionalUser.get();
        existUser.setEmail(user.getEmail());
        existUser.setPassword(user.getPassword());
        existUser.setUsername(user.getUsername());
        return ResponseEntity.ok(userService.save(existUser));
    }
}
