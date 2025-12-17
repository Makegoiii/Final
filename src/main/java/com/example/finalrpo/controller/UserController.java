package com.example.finalrpo.controller;
import com.example.finalrpo.dto.UserDTO;
import com.example.finalrpo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody UserDTO userDTO){
        userService.createUser(userDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping
    public List<UserDTO> getAllUsers(){
        return userService.getAll();
    }
    @GetMapping("/{id}")
    public UserDTO getById (@PathVariable Long id   ){
        return userService.getById(id);
    }

    @PutMapping( "/{id}")
    public ResponseEntity<?> updateById(@PathVariable Long id, @RequestBody UserDTO userDTO ){
        userService.updateUser(id, userDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}" )
    public void deleteById( @PathVariable Long id) {
        userService.delete(id);
    }

}
