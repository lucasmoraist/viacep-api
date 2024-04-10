package com.example.viacep.user.controller;

import com.example.viacep.user.model.User;
import com.example.viacep.user.repository.UserRepository;
import com.example.viacep.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping
    public ResponseEntity<User> create(@RequestBody User user) throws Exception{
        var newUser = this.service.createUser(user);
        return ResponseEntity.ok().body(newUser);
    }

    @GetMapping
    public List<User> getAll(){
        return this.service.findAll();
    }

}
