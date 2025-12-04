package com.example.eleva.controller.dto.response;

import com.example.eleva.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserResponseDTO {
    private String name;
    private String email;

    public UserResponseDTO(String name, String email) {
        this.name = name;
        this.email = email;
    }
}
