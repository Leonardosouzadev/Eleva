package com.example.eleva.controller;

import com.example.eleva.controller.dto.request.UserRequestDTO;
import com.example.eleva.controller.dto.response.UserResponseDTO;
import com.example.eleva.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    public ResponseEntity<UserResponseDTO> create(@RequestBody UserRequestDTO request) {
        UserResponseDTO response = userService.create(request);
        return ResponseEntity.ok(response);
    }

//    @DeleteMapping("/delete")
//    public ResponseEntity<UserResponseDTO> deleteById(@RequestBody UserRequestDTO request) {
//        UserResponseDTO response = userService.deleteById(request);
//        return ResponseEntity.ok(response);
//    }
}
