package com.techer.todoapp.service;

import com.techer.todoapp.entity.User;
import com.techer.todoapp.payload.request.UserDTO;
import com.techer.todoapp.payload.response.UserResponseDTO;

import java.util.Optional;

public interface UserService {

    UserResponseDTO register(UserDTO userRegisterDTO);
    UserResponseDTO login(String email, String password);
    Optional<User> getUserByToken(String token);
    User getAuthenticatedUser();



}
