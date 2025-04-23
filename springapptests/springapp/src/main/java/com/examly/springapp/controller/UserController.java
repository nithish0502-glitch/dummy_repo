package com.examly.springapp.controller;

import com.examly.springapp.model.User;
import com.examly.springapp.service.UserServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserServiceImpl service;

    public UserController(UserServiceImpl service) {
        this.service = service;
    }

    @PostMapping("/assignLaptop")
    public ResponseEntity<Map<String, String>> assignLaptop(@RequestBody Map<String, Long> body) {
        String result = service.assignLaptopToUser(body.get("userId"), body.get("laptopId"));
        return ResponseEntity.ok(Map.of("status", result));
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        return ResponseEntity.ok(service.getUserById(id));
    }
}
