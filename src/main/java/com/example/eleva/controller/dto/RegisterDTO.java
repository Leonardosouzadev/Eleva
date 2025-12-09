package com.example.eleva.controller.dto;

import com.example.eleva.entity.UserRole;

public record RegisterDTO(String name, String email, String password, UserRole role) {

}
