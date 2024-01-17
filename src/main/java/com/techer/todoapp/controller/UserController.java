package com.techer.todoapp.controller;


import com.techer.todoapp.payload.request.UserDTO;
import com.techer.todoapp.payload.response.UserResponseDTO;
import com.techer.todoapp.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> register(@RequestBody UserDTO userRegisterDTO){
        return ResponseEntity.ok(userService.register(userRegisterDTO));
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponseDTO> login(@RequestBody UserDTO userLoginDTO){
        return ResponseEntity.ok(userService.login(userLoginDTO.getEmail(),userLoginDTO.getPassword()));
    }

}
