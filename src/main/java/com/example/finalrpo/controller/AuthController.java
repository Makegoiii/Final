package com.example.finalrpo.controller;

import com.example.finalrpo.model.UserModel;
import com.example.finalrpo.service.MyUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final MyUserService myUserService;

    @PostMapping("/registr")
    public ResponseEntity<?> registr(@RequestBody UserModel model) {
        myUserService.registr(model);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/check")
    public String check() {
        return "OK";
    }
}