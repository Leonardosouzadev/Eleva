package com.example.eleva.service;

import com.example.eleva.controller.dto.request.UserRequestDTO;
import com.example.eleva.controller.dto.response.UserResponseDTO;
import com.example.eleva.entity.User;
import com.example.eleva.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserResponseDTO create(UserRequestDTO request) {
        User user = new User(request.getName(), request.getEmail(), request.getPassword());
        User newUser = userRepository.save(user);
        return new UserResponseDTO(newUser.getName(), newUser.getEmail());
    }

    public UserResponseDTO deleteById(UserRequestDTO request) {
        User user  = userRepository.findByEmail(request.getEmail());
        userRepository.delete(user);

        return new UserResponseDTO(user.getName(), user.getEmail());
    }
}
