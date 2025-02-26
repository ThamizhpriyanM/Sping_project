package com.project1.__project.controller;

import com.project1.__project.model.User;
import com.project1.__project.model.UserAuth;
import com.project1.__project.service.JwtService;
import com.project1.__project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtService jwtService;

    @GetMapping("/list")
    public ResponseEntity<List<User>> getInfo(){
        return new ResponseEntity<>(userService.getInfo(), HttpStatus.OK);
    }

    @PostMapping("/register")
    public UserAuth register(@RequestBody UserAuth user){
        return userService.register(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody UserAuth userAuth){
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(userAuth.getUsername(),userAuth.getPassword()));
        if(authentication.isAuthenticated()){
            return jwtService.getToken(userAuth.getUsername());
        }
        else{
            return "Failure";
        }
    }
}
