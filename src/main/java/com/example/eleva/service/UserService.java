package com.example.eleva.service;

import com.example.eleva.repository.UserRepository;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
