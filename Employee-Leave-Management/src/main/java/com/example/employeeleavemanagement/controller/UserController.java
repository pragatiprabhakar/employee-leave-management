package com.example.employeeleavemanagement.controller;

import com.example.employeeleavemanagement.model.Users;
import com.example.employeeleavemanagement.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController("/api/user")
public class UserController {

    @Autowired
    UserService userService;

//    ----------------------------------Register User ----------------------------------
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Users user)
    {
        Users savedUser = userService.register(user);
        log.info("API HIT: POST /api/user/register — User is created");
        return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully : " + savedUser.getUsername());
    }
//    --------------------------------List of Users--------------------------------------
    @GetMapping("/getAllUser")
    public ResponseEntity<List<Users>> allUsers()
    {
        log.info("API HIT: GET /api/user/getAllUser — List of all Users");
        return ResponseEntity.status(HttpStatus.FOUND).body(userService.getAllUser());
    }

////    -------------------------------------Delete USer------------------------------------
//    @DeleteMapping("/removeUser/{id}")
//    public ResponseEntity<?> removeUser(@PathVariable Long id)
//    {
//        log.info("API HIT: GET /api/user/removeUser — List of all Users");
//        return ResponseEntity.ok(userService.removeUser(id));
//    }


//    @GetMapping("/public")
//    public String publicEndpoint()
//    {
//        return "Public endpoint, no need to login";
//    }
//
//    @PreAuthorize("hasRole('USER')")
//    @GetMapping("/user")
//
//    public String userEndpoint()
//    {
//        return "Only logged in user can see this";
//    }
//
//    @GetMapping("/admin")
//    public String adminEndpoint()
//    {
//        return "Only admin can see this";
//    }


}
