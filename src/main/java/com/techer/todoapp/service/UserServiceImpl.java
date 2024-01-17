package com.techer.todoapp.service;

import com.techer.todoapp.entity.User;
import com.techer.todoapp.payload.request.UserDTO;
import com.techer.todoapp.payload.response.UserResponseDTO;
import com.techer.todoapp.repository.UserRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> getUserByToken(String token){
        return userRepository.findByToken(token);
    }

    @Override
    public UserResponseDTO login(String email, String password) {
        Optional<User> userOptional = userRepository.findByEmailAndPassword(email,password);
        if(userOptional.isPresent()){
            String token = UUID.randomUUID().toString();
            User user = userOptional.get();
            user.setToken(token);
            return new UserResponseDTO(email,token);
        }
        return null;
    }

    @Override
    public UserResponseDTO register(UserDTO userRegisterDTO){
        User user = userRegisterDTO.toUser();
        user.setCreateDate(Instant.now());
        userRepository.save(user);
        return new UserResponseDTO(user.getEmail(),user.getToken());
    }

    @Override
    public User getAuthenticatedUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return userRepository.findByEmail(((org.springframework.security.core.userdetails.User)authentication.getPrincipal()).getUsername())
                .orElseThrow(() -> new RuntimeException("User not found!"));
    }

}
